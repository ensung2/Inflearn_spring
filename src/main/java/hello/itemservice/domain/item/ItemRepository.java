package hello.itemservice.domain.item;


import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ItemRepository {

    private static final Map<Long, Item> store = new HashMap<>();   // static 사용, 실제로는 haspMap 사용 x
    private static long sequence = 0L;

    // 저장 기능
    public Item save(Item item) {
        item.setId(++sequence);
        store.put(item.getId(), item);
        return item;
    }

    // 조회 기능(아이디 1개)
    public Item findById(Long id) {
        return store.get(id);
    }

    // 전체 조회 기능
    public List<Item> findAll() {
        return new ArrayList<>(store.values());
    }

    // 업데이트
    public void update(Long itemId, Item updateParam) {
        Item fintItem = findById(itemId);
        fintItem.setItemName(updateParam.getItemName());
        fintItem.setPrice(updateParam.getPrice());
        fintItem.setQuantity(updateParam.getQuantity());
    }

    public void clearStore() {
        store.clear();
    }

}
