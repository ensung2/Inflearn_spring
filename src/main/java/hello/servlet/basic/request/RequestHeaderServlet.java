package hello.servlet.basic.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.lang.System.*;


// F12 -> network 내의 정보 받아보기 (refresh하여 확인)
@WebServlet(name = "RequestHeaderServlet", urlPatterns = "/request-header")
public class RequestHeaderServlet extends HttpServlet {

    // public이 아닌 protected로 설정해줘야한다 꼭!
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        printStartLine(request);
        printHeaders(request);
        printHeaderUtils(request);
        printEtc(request);
    }

    private void printStartLine(HttpServletRequest request) {
        out.println("--- REQUEST-LINE - start ---");
        out.println("request.getMethod() = " + request.getMethod()); //GET
        out.println("request.getProtocol() = " + request.getProtocol());

        // HTTP/1.1
        out.println("request.getScheme() = " + request.getScheme()); //http
        // http://localhost:8080/request-header
        out.println("request.getRequestURL() = " + request.getRequestURL());
        // /request-header
        out.println("request.getRequestURI() = " + request.getRequestURI());
        //username=hi
        out.println("request.getQueryString() = " +
                request.getQueryString());
        out.println("request.isSecure() = " + request.isSecure());

        //http 사용 유무
        out.println("--- REQUEST-LINE - end ---");
        out.println();
    }

    //Header 모든 정보 (전체 조회)
    private void printHeaders(HttpServletRequest request) {
        out.println("--- Headers - start ---");

        // 예전방법
        /*Enumeration<String> headerNames = request.getHeaderNames(); while (headerNames.hasMoreElements()) {
        String headerName = headerNames.nextElement();
        System.out.println(headerName + ": " + request.getHeader(headerName));
        }*/


        request.getHeaderNames().asIterator()
                .forEachRemaining(headerName -> out.println(headerName + ":" + request.getHeader(headerName)));
        out.println("--- Headers - end ---");
        out.println();
    }

    //Header 편리한 조회
    private void printHeaderUtils(HttpServletRequest request) {
        out.println("--- Header 편의 조회 start ---");
        out.println("[Host 편의 조회]");
        out.println("request.getServerName() = " +
                request.getServerName()); //Host 헤더
        out.println("request.getServerPort() = " +
                request.getServerPort()); //Host 헤더
        out.println();

        // localse : (셋팅된 언어 중) 웹브라우저가 선호하는 언어 순서
        out.println("[Accept-Language 편의 조회]");
        request.getLocales().asIterator()   // 가장 선호하는 언어가 쓰임
                .forEachRemaining(locale -> out.println("locale = " +
                        locale));
        out.println("request.getLocale() = " + request.getLocale());
        out.println();

        out.println("[cookie 편의 조회]");
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                out.println(cookie.getName() + ": " + cookie.getValue());
            }
        }
        out.println();

        // get방식이라서 type이 null (postman으로 간편하게 조작해 볼 수 있음)
        out.println("[Content 편의 조회]");
        out.println("request.getContentType() = " + request.getContentType());
        out.println("request.getContentLength() = " + request.getContentLength());
        out.println("request.getCharacterEncoding() = " + request.getCharacterEncoding());
        out.println("--- Header 편의 조회 end ---");
        out.println();
    }

    //기타 정보
    private void printEtc(HttpServletRequest request) {
        out.println("--- 기타 조회 start ---");
        out.println("[Remote 정보]");
        out.println("request.getRemoteHost() = " +
                request.getRemoteHost()); //
        out.println("request.getRemoteAddr() = " +
                request.getRemoteAddr()); //
        out.println("request.getRemotePort() = " +
                request.getRemotePort()); //
        out.println();
        out.println("[Local 정보]");
        out.println("request.getLocalName() = " +
                request.getLocalName()); //
        out.println("request.getLocalAddr() = " +
                request.getLocalAddr()); //
        out.println("request.getLocalPort() = " +
                request.getLocalPort()); //
        out.println("--- 기타 조회 end ---");
        out.println();
    }

}
