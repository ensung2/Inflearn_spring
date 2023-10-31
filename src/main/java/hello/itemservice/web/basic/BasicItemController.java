package hello.itemservice.web.basic;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor // => final이 붙은 객체의 생성자를 자동으로 생성해줌
public class BasicItemController {

    private final ItemRepository itemRepository;

    /*@Autowired  // => BasicItemController를 Bean에 등록(생성자 주입)
    // 생성자가 1개만 존재시 @Autowired 생성 가능
    public BasicItemController(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }*/

    @GetMapping
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "basic/items";
    }

    /* 상품 상세 페이지 */
    @GetMapping("/{itemId}")
    // REST API에서 URI에 변수 넣을 때 사용
    public String item(@PathVariable long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/item";
    }

    /* 상품 등록 폼 */
    @GetMapping("/add")
    public String addForm() {
        return "basic/addForm";
    }

    /* 상품 등록 폼 v1*//*
    @PostMapping("/add")
    // addForm의 input name으로 생성
    public String addItemV1(@RequestParam String itemName,
                       @RequestParam Integer price,
                       @RequestParam Integer quantity,
                       Model model) {
        Item item = new Item();
        item.setItemName(itemName);
        item.setPrice(price);
        item.setQuantity(quantity);

        itemRepository.save(item);
        // 저장된 결과물 확인
        model.addAttribute("item", item);

        return "basic/item";
    }*/

    /* 상품 등록 폼 v2*//*
    @PostMapping("/add")
    // addForm의 input name으로 생성
    public String addItemV2(@ModelAttribute("item") Item item) {
        itemRepository.save(item);
        // 저장된 결과물 확인
//        model.addAttribute("item", item); => 자동 생성되어 생략 가능

        return "basic/item";
    }*/

    // 상품 등록 폼 v3
//    @PostMapping("/add")
    // addForm의 input name으로 생성
    public String addItemV3(@ModelAttribute Item item) {
        itemRepository.save(item);
        return "basic/item";
    }


    // 상품 등록 폼 v4
//    @PostMapping("/add")
    // addForm의 input name으로 생성
    public String addItemV4(Item item) {
        itemRepository.save(item);
        return "basic/item";
    }

    // 상품 등록 후 새로고침을 하면 post때문에 동일제품이 연속으로 등록되는 오류가 있었음
    // redirect를 사용하여 새로고침시 get이 호출되게 만듬
    // 상품 등록 폼 v5
//    @PostMapping("/add")
    public String addItemV5(Item item) {
        itemRepository.save(item);
        return "redirect:/basic/items/" + item.getId();
    }

    // 리다이렉트에 파라미터를 붙여서 보내기
    @PostMapping("/add")
    public String addItemV6(Item item, RedirectAttributes redirectAttributes) {
        Item saveItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", saveItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/basic/items/{itemId}";
    }

    // 상품 수정 폼 (get)
    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/editForm";
    }

    // 상품 수정 폼 - 저장 (post)
    @PostMapping("/{itemId}/edit")
    // addForm의 input name으로 생성
    public String edit(@PathVariable Long itemId,
                       @ModelAttribute Item item) {
        itemRepository.update(itemId, item);
        return "redirect:/basic/items/{itemId}";
    }

    /**
     * 테스트용 데이터 추가
     */
    @PostConstruct
    public void init() {
        itemRepository.save(new Item("itemA", 10000, 10));
        itemRepository.save(new Item("itemB", 20000, 20));
        itemRepository.save(new Item("itemC", 30000, 30));
    }
}
