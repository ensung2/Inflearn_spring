package hello.exception.servlet;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Controller
public class ServletExceptionController {

    @GetMapping("/error-ex")
    public void errorEx() {
        throw new RuntimeException("예외 발생");    // HTTP Status 500 – Internal Server Error
    }

    @GetMapping("/error-404")
    public void error404(HttpServletResponse response) throws IOException { // HTTP Status 404 – Not Found
        // 서블릿 컨테이너에게 오류가 발생했다는 점을 전달 (당장 예외가 발생하는 것은 아님)
                            // http 상태코드 / 오류메세지
        response.sendError(404, "404 오류");
    }

    @GetMapping("/error-400")
    public void error400(HttpServletResponse response) throws IOException {
        response.sendError(400, "400 오류");
    }

    @GetMapping("/error-500")
    public void error500(HttpServletResponse response) throws IOException { // HTTP Status 500 – Internal Server Error
        response.sendError(500, "500 오류");
    }
}
