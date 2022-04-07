package com.servlet.web05.servletContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ServletContextB")
public class ServletContextB extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取tomcat中的ServletContext对象，tomcat中有且只有一个ServletContext对象
        ServletContext sc1 = this.getServletContext();

        //获取ServletContext对象中的数据
        String value = (String) sc1.getAttribute("name");
        System.out.println("ServletContext的值是："+value);
    }
}
