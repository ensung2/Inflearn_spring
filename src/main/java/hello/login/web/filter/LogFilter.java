package hello.login.web.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;

@Slf4j

// 1. 필터 사용을 위한 구현
public class LogFilter implements Filter {
    @Override
    // 필터 초기화시 호출
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("log filter init");
    }

    @Override
    // 필터 소멸시 호출
    public void destroy() {
       log.info("log filter destroy");
    }

    @Override   // http 요청이 오면 dofilter가 호출
    // 필터가 여러개가 존재하면 체이닝(chaining)으로 필터가 연결되는데
    // 필터에서 처리하고자 하는 구문을 여기에 오버라이딩합니다. 그런 후 체이닝된 다음 필터로 이동하게 됩니다
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        log.info("log filter doFilter");

        // servletRequest 다운그레이드 하기 (httpServlet 부모)
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI();

        // 요청 구분을 위한 임의의 아이디
        String uuid = UUID.randomUUID().toString();

        try {
            log.info("REQUEST [{}][{}]", uuid, requestURI);

            // 있으면 다음 필터 호출, 없으면 서블릿 호출 (해당 로직이 없으면 다음단계로 진행되지 않는다.)
            chain.doFilter(request, response);
        }catch (Exception e) {
            throw e;
        }finally {  // 항상 호출
            log.info("RESPONSE [{}][{}]", uuid, requestURI);
        }
    }
}
