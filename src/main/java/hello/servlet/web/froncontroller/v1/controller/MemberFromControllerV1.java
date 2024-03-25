package hello.servlet.web.froncontroller.v1.controller;

import hello.servlet.web.froncontroller.v1.ControllerV1;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class MemberFromControllerV1 implements ControllerV1 {
    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String viewPath = "/WEB-INF/views/new-form.jsp";
        RequestDispatcher requestDispatcher = req.getRequestDispatcher(viewPath);//컨트롤러에서 뷰로 이동할떄
        requestDispatcher.forward(req, resp); //서블릿에서 jsp 호출
    }
}
