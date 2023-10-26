package hello.servlet.basic.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.servlet.basic.HelloData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ResponseJsonServlet", urlPatterns = "/response-json")
public class ResponseJsonServlet extends HttpServlet {

    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // json 형식 타입 = content-type : application/json
        response.setContentType("application/json");

        // json은 기본스펙상 utf-8이므로 따로 써줄 필요 없다
        response.setCharacterEncoding("utf-8");

        HelloData helloData = new HelloData();
        helloData.setUsername("eun sung");
        helloData.setAge(29);

        // json 형식이므로 위 파라미터 값을 {"username":"eun sung", "age":29} 형식으로 만들어줘야함

        String result = objectMapper.writeValueAsString(helloData);
        response.getWriter().write(result);
    }
}
