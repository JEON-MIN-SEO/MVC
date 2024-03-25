package hello.servlet.web.froncontroller.v2.controller;

import hello.servlet.web.froncontroller.MyView;
import hello.servlet.web.froncontroller.v2.ControllerV2;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class MemberFormControllerV2 implements ControllerV2 {
    @Override
    public MyView process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        return new MyView("/WEB-INF/views/new-form.jsp");
    }
}
