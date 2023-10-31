package hello.itemservice.domain.item;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ItemRepositoryTest {

    ItemRepository itemRepository = new ItemRepository();


    // 테스트가 끝날때마다 실행됨
    @AfterEach
    void afterEach() {
        itemRepository.clearStore();
    }

    @Test
    void save() {
        //given (아이템 클래스를 불러들여 임의의 itemA를 생성)
        Item item = new Item("itemA", 10000, 10);

        //when (itemRepository에 상단 itemA(item)을 saveItem에 저장)
        Item saveItem = itemRepository.save(item);

        //then  (itemRepository에 item을 찾는 findItem 객체를 생성, 찾은 아이템(findeItem과)
        // 저장한 아이템(saveItem)이 동일한지 테스트!
        Item findItem = itemRepository.findById(item.getId());
        assertThat(findItem).isEqualTo(saveItem);
    }


    @Test
    void findAll() {
        //given (임의의 item1,2를 생성하여 itemRepository에 저장)
        Item item1 = new Item("item1", 10000, 10);
        Item item2 = new Item("item2", 20000, 20);

        itemRepository.save(item1);
        itemRepository.save(item2);

        //when (itemRepository에서 findAll을 할 수 있는 (Item을 갖고있는) list 변수 result를 생성
        List<Item> result = itemRepository.findAll();

        //then  (size() = item1,2 =2개)
        //      (contains = 해당 변수가 가지고있는 여부 확인)
        assertThat(result.size()).isEqualTo(2);
        assertThat(result).contains(item1, item2);
    }

    @Test
    void updateItem() {
        //given (item 생성하여 savedItem이라는 이름으로 저장)
        Item item = new Item("item1", 10000, 10);

        Item savedItem = itemRepository.save(item);
        Long itemId = savedItem.getId();

        //when (updateParam 생성 후 update 호출)
        Item updateParam = new Item("item2", 20000, 20);
        itemRepository.update(itemId, updateParam);

        //then (itemRepository에서 findiTEM 과 updateParam 의 값과 동일한지 확인
        Item findItem = itemRepository.findById(itemId);
        assertThat(findItem.getItemName()).isEqualTo(updateParam.getItemName());
        assertThat(findItem.getPrice()).isEqualTo(updateParam.getPrice());
        assertThat(findItem.getQuantity()).isEqualTo(updateParam.getQuantity());
    }

}