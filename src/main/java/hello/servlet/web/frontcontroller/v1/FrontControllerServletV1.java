package hello.servlet.web.frontcontroller.v1;

import hello.servlet.web.frontcontroller.v1.controller.MemberFormControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberListControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberSaveControllerV1;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// * : 하위 경로에서 어떤게 호출이 되더라도 해당 프론트 컨트롤러가 먼저 호출된다
@WebServlet(name = "FrontControllerServletV1", urlPatterns = "/front-controller/v1/*")
public class FrontControllerServletV1 extends HttpServlet {

    // mapping 정보 (controllerV1을 호출해라)
    private Map<String, ControllerV1> controllerV1Map = new HashMap<>();

    public FrontControllerServletV1() {
        // "-"가 요청이 들어오면 new -가 실행된다
        controllerV1Map.put("/front-controller/v1/members/new-form", new MemberFormControllerV1());
        controllerV1Map.put("/front-controller/v1/members/save", new MemberSaveControllerV1());
        controllerV1Map.put("/front-controller/v1/members", new MemberListControllerV1());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("FrontControllerServletV1.service");

        // logic 설계 (/front-controller/v1/members)
        String requestURI = request.getRequestURI();

        // ControllerV1 인터페이스 호출
        ControllerV1 controller = controllerV1Map.get(requestURI);

        // 예외처리
        if (controller==null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        } controller.process(request, response);

    }
}
