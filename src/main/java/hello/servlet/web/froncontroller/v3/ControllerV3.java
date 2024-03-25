package hello.servlet.web.froncontroller.v3;

import hello.servlet.web.froncontroller.ModelView;

import java.util.Map;

public interface ControllerV3 {

    ModelView process(Map<String, String> paramMap);
}
