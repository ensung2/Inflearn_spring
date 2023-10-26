package hello.servlet.web.servletmvc;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "MvcMemberSerivlet", urlPatterns = "/servlet-mvc/members/new-form")
public class MvcMemberServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // controller를 거쳐서 view로 들어가야 하기 때문에 controller를 먼저 설정
        String viewPath = "/WEB-INF/views/new-form.jsp";

        // controller에서 view로 이동할때 사용
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        // (서블릿에서 jsp를 찾아가서 호출 = 제어권을 넘겨줌)
        dispatcher.forward(request, response);

    }
}
