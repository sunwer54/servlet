package com.servlet.web05.servletConfig;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/servletConfigA")
public class ServletConfigA extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取web.xml中的局部配置信息
        //首先获取ServletConfig对象
        ServletConfig servletConfig = this.getServletConfig();
        //获取局部配置信息
        String value = servletConfig.getInitParameter("city");
        System.out.println(value);
    }
}
