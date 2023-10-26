package hello.servlet.web.frontcontroller.v2;

import hello.servlet.web.frontcontroller.MyView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ControllerV2 {

    // 기존에는 MyView가 아닌 void로 설정 해주어 컨트롤러가 다 이동시켰으나
    // MyView를 생성하여 넘길 수 있게 인터페이스를 설정하였다!
    MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

}
