package hello.springmvc.basic.request;

import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Controller
public class RequestParamController {

    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request,
                               HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        log.info("username = {}, age = {}", username, age);

        response.getWriter().write("ok");
    }

    @ResponseBody   //response.getWriter().write("ok"); 를 함축
    @RequestMapping("/request-param-v2")
    public String requestParamV2(@RequestParam("username") String memberName,
                                 @RequestParam("age") int memberAge) {
        log.info("username = {}, age = {}", memberName, memberAge);
        return "ok";
    }

    @ResponseBody   //response.getWriter().write("ok"); 를 함축
    @RequestMapping("/request-param-v3")
                                // requestParam name과 같아야 한다. (명시적 버전)
    public String requestParamV3(@RequestParam String username,
                                 @RequestParam int age) {
        log.info("username = {}, age = {}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-v4")
                                 // requestParam 생략 가능 (암묵적 버전)
    public String requestParamV4(String username, int age) {
        log.info("username = {}, age = {}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-required")
                                        // required가 true(기본값)면 해당 파라미터가 url에 무조건 있어야 한다.(필수)
                                        // true만 지정해줘도 오류가 난다 (null지정이 안되어있기 때문)
                                        // int = null불가, integer = null 가능
                                        // age를 안써줬을때 null이라고 표현됨
    public String requestParamRequired(@RequestParam(required = true) String username,
                                        @RequestParam(required = false) /*int*/ Integer age) {
        log.info("username = {}, age = {}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-default")
    // defaultValue = 값이 안들어가면 기본값으로 지정해준다.
    // 기본값이 정해져 있기 때문에 required를 적어주지 않아도 된다.
    public String requestParamDefault(@RequestParam(required = true, defaultValue = "guest") String username,
                                       @RequestParam(required = false, defaultValue = "-1") int age) {
        log.info("username = {}, age = {}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-map")
    // Map<> 변수명 : 모든 요청 파라미터를 다 받고싶을 때 사용
    // 변수명.get("파라미터명") 으로 출력
    // 파라미터에 작성하지 않았을 시 모두 null로 출력됨
    public String requestParamMap(@RequestParam Map<String, Objects> paramMap) {
        log.info("username = {}, age = {}", paramMap.get("username"), paramMap.get("age"));
        return "ok";
    }

//    @ResponseBody
//    @RequestMapping("/model-attribute-v1")
//    public String modelAttributeV1 (@RequestParam String username,
//                                    @RequestParam int age) {
//
//        HelloData helloData = new HelloData();
//        helloData.setUsername(username);
//        helloData.setAge(age);
//
//        log.info("username = {}, age = {}", helloData.getUsername(), helloData.getAge());
//        log.info("helloData = {}" ,helloData);
//        return "data ok";
//    }

    // 위 코드를 @modelAttribute를 이용하여 변경 (모델 객체 생성, 요청 파라미터 값도 모두 출력!!)
    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1 (@ModelAttribute HelloData helloData) {

        log.info("username = {}, age = {}", helloData.getUsername(), helloData.getAge());
        log.info("helloData = {}" ,helloData);
        return "data ok";
    }

    // @ModelAttribute는 생략이 가능하다.
    // 개발자가 직접 만든 객체는 자동으로 @ModelAttribute가 생성되기 때문에 생략 가능!
    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    public String modelAttributeV2 (HelloData helloData) {

        log.info("username = {}, age = {}", helloData.getUsername(), helloData.getAge());
        log.info("helloData = {}" ,helloData);
        return "data ok";
    }


}
