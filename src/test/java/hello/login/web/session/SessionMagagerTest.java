package hello.login.web.session;

import hello.login.domain.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import static org.assertj.core.api.Assertions.assertThat;

class SessionMagagerTest {

    SessionMagager sessionMagager = new SessionMagager();

    @Test
    void sessionTest() {

        // 세션 생성

        MockHttpServletResponse response = new MockHttpServletResponse();

        Member member = new Member();
        // 생성한 쿠키를 response에 담아놓은것 = 응답 나감
        sessionMagager.createSession(member, response);

        // 요청에 응답쿠키 저장
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setCookies(response.getCookies());  // 유효 세션 아이디 담겨 있음


        // 세션 조회
        Object result = sessionMagager.getSession(request);
        assertThat(result).isEqualTo(member);


        // 세션 만료
        sessionMagager.expire(request);
        Object expired = sessionMagager.getSession(request);
        assertThat(expired).isNull();

    }
}