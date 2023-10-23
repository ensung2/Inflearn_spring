package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;

public class MemberApp {

    // 순수 자바로만 테스트 실행할때 (한계가 있음, 좋은 방법이 아님!)
    // Junit을 사용하여 테스트 하는것 = 좋은 방법!!

    public static void main(String[] args) {
        MemberService memberSerivice = new MemberServiceImpl(memberRepository);
        Member member = new Member(1L,"memberA", Grade.VIP);
        memberSerivice.join(member);

        Member findMember = memberSerivice.findMember(1L);
        System.out.println("new member = " + findMember.getName());
        System.out.println("find Member = " + findMember.getName());
    }
}

