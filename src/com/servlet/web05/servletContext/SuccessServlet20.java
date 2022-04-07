package com.servlet.web05.servletContext;

import com.bean.UserInfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/SuccessServlet20")
public class SuccessServlet20 extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置浏览器提交的数据的编码，避免乱码
        req.setCharacterEncoding("utf-8");
        //设置响应到浏览器的数据的编码，避免乱码
        resp.setContentType("text/html;charset=utf-8");
        //获取tomcat中的Session对象
        HttpSession session = req.getSession();
        //获取Session对象中的值
        UserInfo ui = (UserInfo) session.getAttribute("ui");
        //响应到浏览器的信息可以解析标签
        resp.getWriter().write("<font color='red'>登录成功，"+ui.getUserName()+"欢迎回来</font>");
        //点击退出跳转到登录页面
        resp.getWriter().write("<a href='loginOutServlet'>退出</a>");
    }
}
