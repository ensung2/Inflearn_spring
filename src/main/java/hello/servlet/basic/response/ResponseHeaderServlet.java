package hello.servlet.basic.response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ResponseHeaderServlet", urlPatterns = "/response-header")
public class ResponseHeaderServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 응답 코드(status-line) 만들기 ex) 100, 101, 300등 코드를 불러옴, 숫자 그대로 적어두 됨
        // SC_BAD_REQUEST 로 지정하면 400이 뜸
        response.setStatus(HttpServletResponse.SC_OK);

        // response headers (텍스트 타입, utf-8(한글이 깨지지 않게 하기 위함)
        response.setHeader("Content-Type", "text/plain;charset=utf-8");
        // 캐쉬를 완전히 무효화 하겠다
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        // 헤더 캐쉬 무효화 (과거 버전 cache 까지 제거)
        response.setHeader("Pragma", "no-cache");
        // 내가 원하는 임의의 헤더 만들기
        response.setHeader("my-header", "hello");

        //[Header 편의 메서드]
        content(response);
        cookie(response);
        redirect(response);


        PrintWriter writer = response.getWriter();
        writer.println("ok!");

    }

    private void content(HttpServletResponse response) {
        //Content-Type: text/plain;charset=utf-8
        //Content-Length: 2
        //response.setHeader("Content-Type", "text/plain;charset=utf-8");
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
        //response.setContentLength(2); //(생략시 자동 생성)
    }

    private void cookie(HttpServletResponse response) {
//        response.setHeader("Set-Cookie", "myCookie=good; Max - Age = 600 ");
        Cookie cookie = new Cookie("myCookies", "good");
        cookie.setMaxAge(600);  // 해당 쿠키는 600초 동안 유효하다
        response.addCookie(cookie);
    }

    private void redirect(HttpServletResponse response) throws IOException {
        //Status Code 302
        //Location: /basic/hello-form.html
        //response.setStatus(HttpServletResponse.SC_FOUND); //302
        //response.setHeader("Location", "/basic/hello-form.html");
        response.sendRedirect("/basic/hello-form.html");
    }
}
