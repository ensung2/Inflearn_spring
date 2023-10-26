package hello.servlet.web.frontcontroller.v2.controller;

import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v2.ControllerV2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MemberFormControllerV2 implements ControllerV2 {

    @Override
    public MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        /* // 원래 있던 코드
        String viewPath = "/WEB-INF/views/new-form.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);*/

//        inline 하기 전 (ctrl + shitf + alt + t = 바로 안보이면 inline 검색)
        /*MyView myView;
        return new MyView("/WEB-INF/views/new-form.jsp");*/

        // 중복제거하여 한줄로 표현
        return new MyView("/WEB-INF/views/new-form.jsp");
    }
}
