package com.servlet.web04.session;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/loginOut")
public class LoginOutServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        //resp.setContentType("text/html;charset=utf-8");
        HttpSession session = req.getSession();
        //强制Session失效（清楚Session存储的数据，不会销毁对象）
        session.invalidate();
        System.out.println("session已失效");
        //req.getRequestDispatcher("myWelcomePage.html").forward(req,resp);
        //resp.sendRedirect("login.html");
        req.getRequestDispatcher("login.html").forward(req,resp);
    }
}
