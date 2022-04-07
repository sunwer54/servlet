package com.servlet.web05.servletContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ServletContextD")
public class ServletContextD extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取tomcat中的ServletContext对象，tomcat中有且只有一个ServletContext对象
        ServletContext sc1 = this.getServletContext();
        //通过ServletContext对象获取web.xml中的全局配置信息
        String value = sc1.getInitParameter("flag");
        if ("true".equals(value)){
            System.out.println("定期执行清理缓存的操作");
        }

    }
}
