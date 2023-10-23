package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;

public class OrderServiceImpl implements OrderService{
    // [주문 생성 요청이 올 시]

    // 1. 회원 저장소에서 회원찾기
    private final MemberRepository memberRepository;

    // 2. 할인정책 찾기
    // 기존 fixDiscountPolicy -> rate로 변경 -> DiscountPolicy만 의존하도록 설정
    private final DiscountPolicy discountPolicy;

    // 회원저장소와 할인정책의 생성자 생성
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        // 1. 회원 정보 조회
        Member member = memberRepository.findById(memberId);
        // 2. 할인 정책에 회원 보내기 (할인 받을 수 있는 회원 인지 확인)
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);

    }
}
