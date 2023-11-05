package hello.itemservice.web.validation;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/validation/v2/items")
@RequiredArgsConstructor
public class ValidationItemControllerV2 {

    private final ItemRepository itemRepository;
    private final ItemValidator itemValidator;

    @GetMapping
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "validation/v2/items";
    }

    // 컨트롤러가 호출 될때 마다 항상 호출(검증)됨 (webDataBinder가 항상 새로 생성됨)
    @InitBinder
    public void init(WebDataBinder dataBinder) {
        log.info("InitBinder={}", dataBinder);
        dataBinder.addValidators(itemValidator);
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "validation/v2/item";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("item", new Item());
        return "validation/v2/addForm";
    }

/*    @PostMapping("/add")                             // @ModelAttribute 뒤에 와야한다. 꼭!
    public String addItemV1(@ModelAttribute Item item, BindingResult bindingResult,
                          RedirectAttributes redirectAttributes,
                          Model model) {

        *//* 검증 실패시 - 오류 결과를 담는 객체 생성 = BindingResult*//*
//        Map<String, String> errors = new HashMap<>();

        *//* 검증 로직 *//*
                        // 아이템에 글자가 존재하는가? (만약 없다면)
        if (!StringUtils.hasText(item.getItemName())) {
                                    // 해당 텍스트 화면에 출력
//            errors.put("itemName", "상품 이름은 필수입니다.");
            bindingResult.addError(new FieldError("item", "itemName", "상품 이름은 필수 입니다."));
        }
        if (item.getPrice()==null||item.getPrice()<1000||item.getPrice()>1000000) {
//            errors.put("price", "가격은 1000~1000000까지 허용합니다.");
            bindingResult.addError(new FieldError("item", "price", "가격은 1000~1000000까지 허용합니다."));

        }
        if (item.getQuantity()==null||item.getQuantity()>=9999) {
//            errors.put("quantity", "수량은 최대 9999까지 허용합니다.");
            bindingResult.addError(new FieldError("item", "quantity", "수량은 최대 9999까지 허용합니다."));

        }

        *//* 복합 롤 검증 *//*
        if (item.getPrice()!=null&&item.getQuantity()!=null) {
            int resultPrice = item.getPrice() * item.getQuantity();
            if(resultPrice < 10000) {
//                errors.put("globalError", "가격*수량의 합은 10000원 이상이여야 합니다. 현재값 = " + resultPrice);
                bindingResult.addError(new ObjectError("item",
                        "가격*수량의 합은 10000원 이상이여야 합니다. 현재값 = " + resultPrice));

            }
        }

        *//* 검증 실패 시 - 다시 입력 폼으로 돌아오기 *//*
        if (bindingResult.hasErrors()) {
            log.info("errors = {}", bindingResult); // bindingResult는 자동으로 뷰로 넘어감 (모델 필요 x)
            return "validation/v2/addForm";
        }

        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/validation/v2/items/{itemId}";
    }*/

/*    @PostMapping("/add")                             // @ModelAttribute 뒤에 와야한다. 꼭!
    public String addItemV2(@ModelAttribute Item item, BindingResult bindingResult,
                            RedirectAttributes redirectAttributes,
                            Model model) {

        *//* 검증 로직 *//*
        // 아이템에 글자가 존재하는가? (만약 없다면)
        if (!StringUtils.hasText(item.getItemName())) {
//            bindingResult.addError(new FieldError("item", "itemName", "상품 이름은 필수 입니다."));
            bindingResult.addError(new FieldError("item", "itemName", item.getItemName(),
                    false, null, null, "상품 이름은 필수입니다."));
        }
        if (item.getPrice()==null||item.getPrice()<1000||item.getPrice()>1000000) {
            bindingResult.addError(new FieldError("item", "price", item.getPrice(),
                    false, null, null, "가격은 1000~1000000까지 허용합니다."));

        }
        if (item.getQuantity()==null||item.getQuantity()>=9999) {
            bindingResult.addError(new FieldError("item", "quantity", item.getQuantity(),
                    false, null, null, "수량은 최대 9999까지 허용합니다."));

        }

        *//* 복합 롤 검증 *//*
        if (item.getPrice()!=null&&item.getQuantity()!=null) {
            int resultPrice = item.getPrice() * item.getQuantity();
            if(resultPrice < 10000) {
                bindingResult.addError(new ObjectError("item", null, null,
                        "가격*수량의 합은 10000원 이상이여야 합니다. 현재값 = " + resultPrice));

            }
        }

        *//* 검증 실패 시 - 다시 입력 폼으로 돌아오기 *//*
        if (bindingResult.hasErrors()) {
            log.info("errors = {}", bindingResult); // bindingResult는 자동으로 뷰로 넘어감 (모델 필요 x)
            return "validation/v2/addForm";
        }

        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/validation/v2/items/{itemId}";
    }*/

/*    @PostMapping("/add")
    public String addItemV3(@ModelAttribute Item item, BindingResult bindingResult,
                            RedirectAttributes redirectAttributes) {

        log.info("objectName ={}", bindingResult.getObjectName());
        log.info("target = {}", bindingResult.getTarget());

        if (!StringUtils.hasText(item.getItemName())) {
            bindingResult.addError(new FieldError("item", "itemName",
                    item.getItemName(), false, new String[]{"required.item.itemName"}, null,
                    null));
        }
        if (item.getPrice() == null || item.getPrice() < 1000 || item.getPrice() >
                1000000) {
            bindingResult.addError(new FieldError("item", "price", item.getPrice(),
                    false, new String[]{"range.item.price"}, new Object[]{1000, 1000000}, null));
        }
        if (item.getQuantity() == null || item.getQuantity() > 10000) {
            bindingResult.addError(new FieldError("item", "quantity",
                    item.getQuantity(), false, new String[]{"max.item.quantity"}, new Object[]
                    {9999}, null));
        }
        //특정 필드 예외가 아닌 전체 예외
        if (item.getPrice() != null && item.getQuantity() != null) {
            int resultPrice = item.getPrice() * item.getQuantity();
            if (resultPrice < 10000) {
                bindingResult.addError(new ObjectError("item", new String[]
                        {"totalPriceMin"}, new Object[]{10000, resultPrice}, null));
            }
        }
        if (bindingResult.hasErrors()) {
            log.info("errors={}", bindingResult);
            return "validation/v2/addForm";
        }
        //성공 로직
        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/validation/v2/items/{itemId}";
    }*/

/*    @PostMapping("/add")
    public String addItemV5(@ModelAttribute Item item, BindingResult bindingResult,
                            RedirectAttributes redirectAttributes) {

        itemValidator.validate(item, bindingResult);

        // 검증에 실패하면 다시 입력폼으로 돌아가기
        if (bindingResult.hasErrors()) {
            log.info("errors={}", bindingResult);
            return "validation/v2/addForm";
        }

        //성공 로직
        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/validation/v2/items/{itemId}";
    }*/

    @PostMapping("/add")  // @validated 자동으로 검증 수행
    public String addItemV6(@Validated @ModelAttribute Item item, BindingResult bindingResult,
                            RedirectAttributes redirectAttributes) {


        // 검증에 실패하면 다시 입력폼으로 돌아가기
        if (bindingResult.hasErrors()) {
            log.info("errors={}", bindingResult);
            return "validation/v2/addForm";
        }

        //성공 로직
        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/validation/v2/items/{itemId}";
    }

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "validation/v2/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId, @ModelAttribute Item item) {
        itemRepository.update(itemId, item);
        return "redirect:/validation/v2/items/{itemId}";
    }

}
