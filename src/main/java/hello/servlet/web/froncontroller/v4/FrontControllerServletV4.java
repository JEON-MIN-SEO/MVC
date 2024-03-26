package hello.servlet.web.froncontroller.v4;

import hello.servlet.web.froncontroller.ModelView;
import hello.servlet.web.froncontroller.MyView;
import hello.servlet.web.froncontroller.v3.ControllerV3;
import hello.servlet.web.froncontroller.v3.controller.MemberFormControllerV3;
import hello.servlet.web.froncontroller.v3.controller.MemberListControllerV3;
import hello.servlet.web.froncontroller.v3.controller.MemberSaveControllerV3;
import hello.servlet.web.froncontroller.v4.controller.MemberFormControllerV4;
import hello.servlet.web.froncontroller.v4.controller.MemberListControllerV4;
import hello.servlet.web.froncontroller.v4.controller.MemberSaveControllerV4;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV4", urlPatterns = "/front-controller/v3/*")
public class FrontControllerServletV4 extends HttpServlet {

    private Map<String, ControllerV4> controllerMap = new HashMap<>();

    public FrontControllerServletV4() {
        controllerMap.put("/front-controller/v4/members/new-form", new MemberFormControllerV4());
        controllerMap.put("/front-controller/v4/members/save", new MemberSaveControllerV4());
        controllerMap.put("/front-controller/v4/members", new MemberListControllerV4());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("FrontControllerV4");

        String requestURI = req.getRequestURI(); //요청한 uri 작성 값이 들어간다.(xxx,xx/.../ ...값이 들어간다.

        //new MemberFormControllerV3()
        ControllerV4 controller = controllerMap.get(requestURI);
        if (controller == null){
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        Map<String, String> paramMap = createParamMap(req); //파라미터 값을 모두 뽑는 작업
        Map<String, Object> model = new HashMap<>();
        String viewName = controller.process(paramMap, model); //new-form 이렇게 return 값만 넘어옴 파라미터는 상관x

        MyView view = viewResolver(viewName); //viewName을 가지고 viewResolver호출 + MyView를 반환
        //"/WEB-INF/views" + viewName + ".jsp"

        view.render(model, req, resp); //이때 모델도 같이 넘긴다.

    }

    private static MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views" + viewName + ".jsp");
    }

    private static Map<String, String> createParamMap(HttpServletRequest req) {
        Map<String, String > paramMap = new HashMap<>();
        req.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, req.getParameter(paramName)));
        return paramMap;
//        <form action="/submit" method="post">
//          <input type="text" name="username">
//          <input type="password" name="password">
//          <button type="submit">Submit</button>
//        </form>
//    {
//        "username": "johndoe",
//            "password": "123"
//    }
//      즉,req.getParameterNames()으로 "username"과 "password"을 뽑아내고 ("username", value)를 넣는다.
    }

}
