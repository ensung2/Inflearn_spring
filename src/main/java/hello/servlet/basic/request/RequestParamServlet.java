package hello.servlet.basic.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

/**
 * 1. 파라미터 전송 기능
 * http://localhost:8080/request-param?username=hello&age=20
 *
 */
@WebServlet(name = "requestParamServlet", urlPatterns = "/request-param")
public class RequestParamServlet extends HttpServlet {


    // service라고 타이핑 하면 완성형 나옴!
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 1) 파라미터 전체 조회(requestParameter 꺼내기)

        System.out.println("[전체 파라미터 조회] ---start---");
        Enumeration<String> parameterNames = request.getParameterNames();

        // paramName = 파라미터 이름(username)
        // request.getParameter(key values) = 파라미터 값(hello)
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName-> System.out.println(paramName + "=" + request.getParameter(paramName)));

        System.out.println("[전체 파라미터 조회] ---end---");
        System.out.println();

        // 2) 개별 파라미터 조회
        System.out.println("[단일 파라미터 조회] ---start---");

        String username = request.getParameter("username");
        System.out.println("username = " + username);
        String age = request.getParameter("age");
        System.out.println("age = " + age);

        System.out.println("[단일 파라미터 조회] ---end---");
        System.out.println();

        // 3) 동일한 파라미터명을 갖는 파라미터 조회
        System.out.println("[이름이 같은 복수 파라미터 조회] ---start---");
/*         배열로 나오는걸 알수있음!(username에 hello와 servlet 두개가 설정되어있기 떄문!
         [] (iter 사용)
         중복일때는 getParameterValues를 사용 해줌
         기본적으로는 단 하나의 값만 사용해야함, 만약 중복이라면 맨 첫번째 값을 반환*/
        String[] usernames = request.getParameterValues("username");
        for (String name : usernames) {
            System.out.println("username = " + name);
        }
        System.out.println("[이름이 같은 복수 파라미터 조회] ---end---");
        System.out.println();

        response.getWriter().write("ok");
    }

}
