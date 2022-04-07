package com.servlet.web03;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/handlerServlet")
public class HandlerServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //UserInfo ui =  (UserInfo)req.getAttribute("user");

        //设置响应到浏览器的数据的编码，避免乱码
        resp.setContentType("text/html;charset=utf-8");

        resp.getWriter().write("<h1>hello</h1>");//类似于js中的innerHTML("")
        //响应到浏览器的信息可以解析标签
        resp.getWriter().write("<font color='red'>登录成功，欢迎回来</font>");
    }
}
