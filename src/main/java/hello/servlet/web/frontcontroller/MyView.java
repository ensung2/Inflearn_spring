package hello.servlet.web.frontcontroller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

// v1, v2등에서도 공통으로 사용할 클래스라서 frontcontroller 하위에 생성
public class MyView {

   private String viewPath;

    public MyView(String viewPath) {
        this.viewPath = viewPath;
    }
    
    // 뷰를 만드는 행위 자체를 랜더링 한다
    public void render(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
                                                // viewPath : "/WEB-INF/views/save-result.jsp"
        // jsp이기 떄문에 dispathcher을 넣어주는것!
        dispatcher.forward(request, response);
    }
                                // 모델의 값을 jsp에 넘겨주기
    public void render(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 렌더가 오면 모델에 있는 데이터 다 꺼내기
        modelToRequestAttribute(model, request);
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);
    }

    private void modelToRequestAttribute(Map<String, Object> model, HttpServletRequest request) {
        model.forEach((key, Value) -> request.setAttribute(key, Value));
    }
}
