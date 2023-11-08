package hello.typeconverter.controller;

import hello.typeconverter.type.IpPort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class HelloController {  // 형변환 예제

    // 서블릿 버전
    @GetMapping("/hello-v1")
    public String helloV1(HttpServletRequest request) {
        String data = request.getParameter("data"); // 문자 타입 조회
        Integer intValue = Integer.valueOf(data);   // 문자타입인 data를 숫자타입으로 변경
        System.out.println("intValue = " + intValue);
        return "ok";
    }

    // 스프링 버전
    @GetMapping("/hello-v2")
    public String helloV2(@RequestParam Integer data) {
        System.out.println("data = " + data);
        return "ok";
    }

    // 직접 만든 컨버터 사용
    @GetMapping("/ip-port")
    public String ipPort(@RequestParam IpPort ipPort){
        System.out.println("ipPort ip = " + ipPort.getIp());
        System.out.println("ipPort port = " + ipPort.getPort());
        return "직접 만든 컨버터 테스트";
    }
}
