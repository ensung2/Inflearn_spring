package hello.servlet.web.springmvc.v2;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/springmvc/v2/members")  // 해당 매핑url과 하단 매핑 메서드에 담긴 url(논리경로)가 합쳐진다
// 물리 경로가 중복되기 때문에 분리
public class SpringMemberControllerV2 {

    MemberRepository memberRepository = MemberRepository.getInstance();

    // v1에 있던 컨트롤러를 하나의 컨트롤러에 합침 (v2로 변경)

    // 1. 회원 가입 폼(form)
    // 요청 정보를 매핑한다 (해당 url이 호출되면 해당 메서드가 호출됨)
    @RequestMapping("/new-form")   // 메서드 단위로 매핑됨
    public ModelAndView newForm() {
        return new ModelAndView("new-form");
    }

    // 2. 회원 가입 저장(save)
    @RequestMapping("/save")
    public ModelAndView save(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        Member member = new Member(username, age);
        memberRepository.save(member);

        ModelAndView mv = new ModelAndView("save-result");

//        mv.getModel().put("member",member);
        mv.addObject("member", member);
        return mv;

    }


    // 회원 목록(list)
    @RequestMapping /*("/members") -> 겹치는 부분이기 떄문에 생략! */
    public ModelAndView members(Map<String, String> paramMap) {
        List<Member> members = memberRepository.findAll();
        ModelAndView mv = new ModelAndView("members");
        mv.addObject("members", members);

        return mv;

    }
}