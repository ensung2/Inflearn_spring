package hello.core.common;


import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;

@Component
@Scope(value = "request")   // http 요청 당 하나씩 생성되고 요청이 끝나는 시점에 소멸된다

// 로그를 출력하기 위한 클래스 생성
public class MyLogger {

    private String uuid;
    private String requestURL;

    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    public void log(String message) {
        System.out.println("[" + uuid + "]" + "[" + requestURL + "]" + message);
    }

    // 고객 요청이 들어올 때 스프링에서 호출 (uuid를 생성하여 저장, http 요청당하나씩 생성)
    @PostConstruct
    public void init() {
        uuid = UUID.randomUUID().toString();
        System.out.println("[" + uuid + "] request scope bean create : " + this);

    }

    // 고객 요청이 빠져나갈때 호출
    @PreDestroy
    public void close() {
        System.out.println("[" + uuid + "] request scope bean close : " + this);
    }

}
