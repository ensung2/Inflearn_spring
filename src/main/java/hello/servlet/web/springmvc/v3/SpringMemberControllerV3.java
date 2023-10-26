package hello.servlet.web.springmvc.v3;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/springmvc/v3/members")
public class SpringMemberControllerV3 {

    MemberRepository memberRepository = MemberRepository.getInstance();

    // v2 컨트롤러를 더 실용적인 방식으로 바꿈 (v3)

    // 1. 회원 가입 폼(form) / method=RequestMethod.GET =  get 전송 방식 일때만 호출됨
    // @RequestMapping(value = "/new-form", method = RequestMethod.GET)    // 단순 전송일 경우 get
    @GetMapping("/new-form")    // @RequestMapping = 메소드 get 방식으로 사용시 줄여쓰는 방법!!!!
    public String newForm() {
        return "new-form";
    }

    // 2. 회원 가입 저장(save) / method=RequestMethod.GET =  get 전송 방식 일때만 호출됨
    // @RequestMapping(value = "/save", method = RequestMethod.POST)
    @PostMapping("/save")   // @RequestMapping = 메소드 post 방식으로 사용시 줄여쓰는 방법!!!!
    public String save(@RequestParam("username") String username,
                       // request.getParameter("username") 과 같은 말
                       @RequestParam("age") int age,
                       Model model) {

        Member member = new Member(username, age);
        memberRepository.save(member);

        model.addAttribute("member", member);
        return "save-result";
    }

    // 회원 목록(list)
//    @RequestMapping(method = RequestMethod.GET)
    @GetMapping
    public String members(Model model) {
        List<Member> members = memberRepository.findAll();
        model.addAttribute("members", members);
        return "members";

    }
}
