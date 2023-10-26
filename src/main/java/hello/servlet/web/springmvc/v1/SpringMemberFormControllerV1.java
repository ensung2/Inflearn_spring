package hello.servlet.web.springmvc.v1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller // 스프링이 자동으로 스프링빈 등록한다 / 애노테이션 기반 컨트롤러로 인식 (@Component가 포함되어있는 애노테이션)
public class SpringMemberFormControllerV1 {

    // 요청 정보를 매핑한다 (해당 url이 호출되면 해당 메서드가 호출됨)
    @RequestMapping("/springmvc/v1/members/new-form")
    public ModelAndView process() {
        return new ModelAndView("new-form");
    }
}
