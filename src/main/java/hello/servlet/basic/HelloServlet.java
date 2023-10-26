package hello.servlet.basic;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// 중복이 있어서는 안됨!!            // servlet 주소
@WebServlet(name = "helloServlet", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("HelloServlet.service");
        System.out.println("request = " + request);
        System.out.println("response = " + response);

        // http 파라미터 확인 (?username="kim"으로 설정해둔 상태)
        String username = request.getParameter("username");
        System.out.println("username = " + username);

        // 응답 메세지 보내보기
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
        // http 메세지 바디에 들어가는 문구
        response.getWriter().write("hello " + username);


    }
}
