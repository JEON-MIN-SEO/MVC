package hello.servlet.web.froncontroller.v3;

import hello.servlet.web.froncontroller.ModelView;
import hello.servlet.web.froncontroller.MyView;
import hello.servlet.web.froncontroller.v2.ControllerV2;
import hello.servlet.web.froncontroller.v2.controller.MemberFormControllerV2;
import hello.servlet.web.froncontroller.v2.controller.MemberListControllerV2;
import hello.servlet.web.froncontroller.v2.controller.MemberSaveControllerV2;
import hello.servlet.web.froncontroller.v3.controller.MemberFormControllerV3;
import hello.servlet.web.froncontroller.v3.controller.MemberListControllerV3;
import hello.servlet.web.froncontroller.v3.controller.MemberSaveControllerV3;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV3", urlPatterns = "/front-controller/v3/*")
public class FrontControllerServletV3 extends HttpServlet {

    private Map<String, ControllerV3> controllerMap = new HashMap<>();

    public FrontControllerServletV3() {
        controllerMap.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
        controllerMap.put("/front-controller/v3/members/save", new MemberSaveControllerV3());
        controllerMap.put("/front-controller/v3/members", new MemberListControllerV3());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("FrontControllerV3");

        String requestURI = req.getRequestURI(); //요청한 uri 작성 값이 들어간다.(xxx,xx/.../ ...값이 들어간다.

        ControllerV3 controllerSearch = controllerMap.get(requestURI);
        if (controllerSearch == null){
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

//        Map<String, String> paramMap = createParamMap(req);
//        ModelView mv = ModuleLayer.Controller.process

//        MyView view = controllerSearch.process(req, resp) ;
//        view.render(req, resp);
    }
}
