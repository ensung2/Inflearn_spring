package hello.core.discount;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.stereotype.Component;

@Component
@MainDiscountPolicy
public class FixDiscountPolicy implements DiscountPolicy{

    private  int discountFixAmount = 1000; //1000원 할인
    @Override
    public int discount(Member member, int price) {
        // 등급이 vip면 1000원할인 아니면 없음!
        if(member.getGrade() == Grade.VIP) {
            return discountFixAmount;
        }
        else {return 0;}

    }
}
