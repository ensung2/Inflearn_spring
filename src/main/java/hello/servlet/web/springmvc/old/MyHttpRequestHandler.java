package hello.servlet.web.springmvc.old;

import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component("/springmvc/request-handler")    // 1. 찾기
            // 2. 확인
public class MyHttpRequestHandler implements HttpRequestHandler {
    @Override    // 3. 적발 -> 핸들호출
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("MyHttpRequestHandler.handleRequest");   // 4. 해당 메서드 호출
    }
}
