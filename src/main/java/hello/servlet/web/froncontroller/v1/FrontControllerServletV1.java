package hello.servlet.web.froncontroller.v1;

import hello.servlet.web.froncontroller.v1.controller.MemberFromControllerV1;
import hello.servlet.web.froncontroller.v1.controller.MemberListControllerV1;
import hello.servlet.web.froncontroller.v1.controller.MemberSaveControllerV1;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV1", urlPatterns = "/front-controller/v1/*")
public class FrontControllerServletV1 extends HttpServlet {

    private Map<String, ControllerV1> controllerMap = new HashMap<>();

    public FrontControllerServletV1() {
        controllerMap.put("/front-controller/v1/members/new-form", new MemberFromControllerV1());
        controllerMap.put("/front-controller/v1/members/save", new MemberSaveControllerV1());
        controllerMap.put("/front-controller/v1/members", new MemberListControllerV1());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("FrontControllerV1");

        String requestURI = req.getRequestURI(); //요청한 uri 작성 값이 들어간다.(xxx,xx/.../ ...값이 들어간다.

        ControllerV1 controllerSearch = controllerMap.get(requestURI);
        if (controllerSearch == null){
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        controllerSearch.process(req, resp);
    }
}
