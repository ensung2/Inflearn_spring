package hello.core.order;

// 주문 서비스 역할 (최종 주문 결과 반환)
public interface OrderService {

    Order createOrder(Long memberId, String itemName, int itemPrice);
}
