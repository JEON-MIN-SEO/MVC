package hello.servlet.web.froncontroller.v5.adapter;

import hello.servlet.web.froncontroller.ModelView;
import hello.servlet.web.froncontroller.v3.ControllerV3;
import hello.servlet.web.froncontroller.v5.MyHandlerAdapter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ControllerV3HandlerAdapter implements MyHandlerAdapter {
    @Override
    public boolean supports(Object handler) {
        return (handler instanceof ControllerV3);
    }

    @Override
    public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {
        ControllerV3 controller = (ControllerV3) handler;

        Map<String, String> paramMap = createParamMap(request);
        ModelView mv = controller.process(paramMap); //컨트롤러에 req의 파라미터 값을 전달 후 모델뷰 return값을 그대로 전달

        return mv;
    }

    //req에서 파라미터 값을 모두 뽑아서 Map<String, String>에 저장 후 전달
    private static Map<String, String> createParamMap(HttpServletRequest req){
        Map<String, String> paramMap = new HashMap<>();
        req.getParameterNames(). asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, req.getParameter(paramName)));
        return paramMap;

    }
}
