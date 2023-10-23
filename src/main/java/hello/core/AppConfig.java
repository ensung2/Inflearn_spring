package hello.core;

import hello.core.discount.FixDiscountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

// AppConfig = 애플리케이션 환경설정에 관한 모든것
// 애플리케이션의 실제 동작에 필요한 구현 객체 생성
// 이후 생성한 객체 인스턴스의 레퍼런스를 생성자를 통해 주입(연결)
public class AppConfig {

    public MemberService memberService(){
        return new MemberServiceImpl(new MemoryMemberRepository());
    }

    public OrderService orderService(){
        return new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
    }
}
