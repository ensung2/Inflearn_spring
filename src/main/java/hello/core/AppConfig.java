package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// AppConfig = 애플리케이션 환경설정에 관한 모든것
// 애플리케이션의 실제 동작에 필요한 구현 객체 생성
// 이후 생성한 객체 인스턴스의 레퍼런스를 생성자를 통해 주입(연결)

// spring으로 변경 => @Configuration과 @Bean 붙여주기

@Configuration // 생략할 수 있으나 싱글톤 컨테이너를 보장해주지 못함. (사용자 직접 호출이 됨으로
                // 스프링 컨테이너의 관리외가 됨)
public class AppConfig {

    @Bean
    public MemberService memberService(){
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService(){
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy() {
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}
