package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// * 반환값이 String 이라면?
// @Controller는 해당 String의 이름=뷰이름으로 인식하여 반영
// @RestController는 http 메시지 바디에 string이 바로 반영된다.
@RestController

@Slf4j
public class LogTestController {

    // 클래스 지정 (@Slf4j 사용시 해당 클래스 자동 지정됨)
//    private final Logger log = LoggerFactory.getLogger(getClass());

    @GetMapping("/log-test")
    public String logTest() {

        String name = "Spring";
        // 원래 사용하던 방법(실무에선 해당 방법으로 사용 x) : soutv
        System.out.println("name = " + name);
        // [출력 형태] : name = Spring

        // 로그 상태 레벨 - 등급 높은 순 (로그 생성시 사용 해야 하는 출력 방법)
        log.trace("trace log={}", name);
        // [출력 형태] : 2023-10-27 10:36:07.236 TRACE 5972 --- [nio-8080-exec-1] hello.springmvc.basic.LogTestController  : trace log=Spring

        log.debug("debug log={}", name);    // 디버그(개발서버)할때 보는 로그
        // [출력 형태] : 2023-10-27 10:36:07.237 DEBUG 5972 --- [nio-8080-exec-1] hello.springmvc.basic.LogTestController  : debug log=Spring

        log.info("info log={}", name);  // 중요한 정보(원인 에러, 기본적으로 확인)
        // [출력 형태] : 2023-10-27 10:36:07.237  INFO 5972 --- [nio-8080-exec-1] hello.springmvc.basic.LogTestController  : info log=Spring

        log.warn("warn log={}", name);  // 경고
        // [출력 형태] : 2023-10-27 10:36:07.237  WARN 5972 --- [nio-8080-exec-1] hello.springmvc.basic.LogTestController  : warn log=Spring

        log.error("error log={}", name);    // 에러(빨리 조치를 취해야 함)
        // [출력 형태] : 2023-10-27 10:36:07.237 ERROR 5972 --- [nio-8080-exec-1] hello.springmvc.basic.LogTestController  : error log=Spring


        // log.info("info log=" + name) => (x) 문자열+문자열이 되어버려 출력이 되지 않는다.
        return "ok";
    }
}
