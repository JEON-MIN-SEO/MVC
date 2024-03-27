package hello.servlet.web.froncontroller.v5;

import hello.servlet.web.froncontroller.ModelView;
import hello.servlet.web.froncontroller.MyView;
import hello.servlet.web.froncontroller.v3.ControllerV3;
import hello.servlet.web.froncontroller.v3.controller.MemberFormControllerV3;
import hello.servlet.web.froncontroller.v3.controller.MemberListControllerV3;
import hello.servlet.web.froncontroller.v3.controller.MemberSaveControllerV3;
import hello.servlet.web.froncontroller.v4.controller.MemberFormControllerV4;
import hello.servlet.web.froncontroller.v4.controller.MemberListControllerV4;
import hello.servlet.web.froncontroller.v4.controller.MemberSaveControllerV4;
import hello.servlet.web.froncontroller.v5.adapter.ControllerV3HandlerAdapter;
import hello.servlet.web.froncontroller.v5.adapter.ControllerV4HandlerAdapter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "frontControllerV5", urlPatterns = "/front-controller/v5/*")
public class FrontControllerV5 extends HttpServlet {

    private final Map<String, Object> handlerMappingMap = new HashMap<>();
    //원래 아래와 같이 <String, ControllerV3> 이어야하는데 Object로 모두 들어올 수 있게 만듬
    //private Map<String, ControllerV3> controllerMap = new HashMap<>();
     private final List<MyHandlerAdapter> handlerAdapters = new ArrayList<>();

    public FrontControllerV5() {
        initHandlerMappingMap();
        initHandlerAdapters();
    }

    private void initHandlerAdapters() {
        handlerAdapters.add(new ControllerV3HandlerAdapter()); //handlerAdapters 즉, List에 V3,V4다 담
        handlerAdapters.add(new ControllerV4HandlerAdapter());
    }

    private void initHandlerMappingMap() {
        handlerMappingMap.put("/front-controller/v5/v3/members/new-form", new MemberFormControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members/save", new MemberSaveControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members", new MemberListControllerV3());

        handlerMappingMap.put("/front-controller/v5/v4/members/new-form", new MemberFormControllerV4());
        handlerMappingMap.put("/front-controller/v5/v4/members/save", new MemberSaveControllerV4());
        handlerMappingMap.put("/front-controller/v5/v4/members", new MemberListControllerV4());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //"/front-controller/v5/v3/members/new-form"에 대해서 handler = new MemberFormControllerV3() 반환
        Object handler = getHandler(req);
        if (handler == null){
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        //getHandlerAdapter = ControllerV3HandlerAdapter
        //handler = MemberFormControllerV3()
        MyHandlerAdapter adapter = getHandlerAdapter(handler);

        ModelView mv = adapter.handle(req, resp, handler);

        //viewName: new-form
        String viewName = mv.getViewName();
        MyView view = viewResolver(viewName); //viewName을 가지고 viewResolver호출 + MyView를 반환
        //"/WEB-INF/views" + viewName + ".jsp"

        view.render(mv.getModel(), req, resp); //이때 모델도 같이 넘긴다.
    }

    //handler = MemberFormControllerV3()
    private MyHandlerAdapter getHandlerAdapter(Object handler) {
        for (MyHandlerAdapter adapter : handlerAdapters) {
            if(adapter.supports(handler)){
                return adapter;
                //ControllerV3HandlerAdapter를 반환
            }
        }
        throw new IllegalArgumentException("handler adapter를 찾을 수 없습니다 ");
    }

    private Object getHandler(HttpServletRequest req) {
        String requestURI = req.getRequestURI();
        return handlerMappingMap.get(requestURI);
    }

    private static MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views" + viewName + ".jsp");
    }
}
