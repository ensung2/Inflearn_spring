package hello.servlet.basic.response;

import hello.servlet.basic.HelloServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ResponseHtmlServlet", urlPatterns = "/response-html")
public class ResponseHtmlServlet extends HelloServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // content-type : text/html:charset=utf-8
        response.setContentType("text/html");   // 무조건 지정해줘야함!
        response.setCharacterEncoding("utf-8");


        // 페이지 소스보기에서 자세히 확인 가능
        PrintWriter writer = response.getWriter();
        writer.println("<html>");
        writer.println("<body>");
        writer.println("<div>HttpServlet으로 html 렌더링하기</div>");
        writer.println("</body>");
        writer.println("</html>");
    }
}
