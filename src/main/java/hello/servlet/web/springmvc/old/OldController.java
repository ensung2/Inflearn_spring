package hello.servlet.web.springmvc.old;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// url이랑 똑같은 이름의 스프링 빈의 이름(springmvc)을 찾아서 실행  (url 패턴으로 맞춤)
// = BeanNameUrlHandlerMapping
@Component("/springmvc/old-controller")
public class OldController implements Controller {

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("OldController.handleRequest");

        // 해당 뷰리졸버를 실행하기 위해 application.properties에
        /*spring.mvc.view.prefix=/WEB-INF/views/
                spring.mvc.view.suffix=.jsp*/
        // 를 추가해 주었다.


        return new ModelAndView("new-form");
    }
}
