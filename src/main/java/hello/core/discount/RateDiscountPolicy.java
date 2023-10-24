package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.stereotype.Component;

@Component
//@MainDiscountPolicy
public class RateDiscountPolicy implements DiscountPolicy{

    // 할인율 지정
    private int discountPercent = 10;
    @Override
    public int discount(Member member, int price) {

        // 만약 회원 등급이 == vip일 경우, 원가에서 10% 할인된다
        if (member.getGrade() == Grade.VIP) {
            return price * discountPercent / 100;
        } else {
            return 0;
        }
    }
}
