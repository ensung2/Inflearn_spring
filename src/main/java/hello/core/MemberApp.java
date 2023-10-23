package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import org.springframework.boot.web.reactive.context.AnnotationConfigReactiveWebApplicationContext;
import org.springframework.context.ApplicationContext;

public class MemberApp {

    // 순수 자바로만 테스트 실행할때 (한계가 있음, 좋은 방법이 아님!)
    // Junit을 사용하여 테스트 하는것 = 좋은 방법!!

    public static void main(String[] args) {
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();
//      MemberService memberSerivice = new MemberServiceImpl();

        // AppConfig에 있는 정보를 spring이 (bean을)관리해줌
        // ApplicatioContext : 스프링에서 제공하는 인터페이스, 스프링 컨테이너를 생성하고 관리하는 역할
        // 스프링부트에서는 내부적으로 생성하여 관리해줌 (개발자가 직접 생성하고 사용할 필요 없음)!
        ApplicationContext applicationContext = new AnnotationConfigReactiveWebApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);


        Member member = new Member(1L,"memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new member = " + findMember.getName());
        System.out.println("find Member = " + findMember.getName());
    }
}

