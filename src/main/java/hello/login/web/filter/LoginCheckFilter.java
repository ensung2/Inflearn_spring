package hello.login.web.filter;

import hello.login.web.SessionConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Slf4j
public class LoginCheckFilter implements Filter {

    // 오버라이드 할때 init과 destroy는 default값으로 호출하지 않아도 된다.

    // 로그인 하지 않아도(인증 필터를 적용해도) 들어올 수 있는 경로 작성
    private static final String[] whitelist = {"/", "/members/add", "/login", "/logout", "/css/*"};

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI();

        HttpServletResponse httpResponse = (HttpServletResponse) response;

        try {
            log.info("인증 체크 필터 시작 {}", requestURI);
            if (isLoginChechPath(requestURI)) {
                log.info("인증 체크 로직 실행 {}", requestURI);
                HttpSession session = httpRequest.getSession(false);
                if (session == null || session.getAttribute(SessionConst.LOGIN_MEMBER) == null) {
                    log.info("미인증 사용자 요청 {}", requestURI);
                    // 로그인으로 리다이렉트 (미인증 사용자가 들어오면 로그인 페이지로 보냄, 이후 로그인 성공시 다시 돌아오기)
                    httpResponse.sendRedirect("/login?redirectURL=" + requestURI);
                    return; // 미인증 사용자는 여기서 끝!
                }
            }

            // 화이트 리스트 케이스는 바로 dofilter로 넘어감
            chain.doFilter(request, response);
        } catch (Exception e) {
            throw e;

        } finally {
            log.info("인증 체크 필터 종료 {}", requestURI);
        }
    }

    /**
     * 화이트 리스트의 경우 인증 체크X
     */
    private boolean isLoginChechPath(String requestURI) {
        // 화이트 리스트를 제외할 경우 false가 되면서 접근이 불가됨(로그인 체크확인)
        return !PatternMatchUtils.simpleMatch(whitelist, requestURI);

    }

}
