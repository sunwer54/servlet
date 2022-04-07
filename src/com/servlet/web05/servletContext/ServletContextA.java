package com.servlet.web05.servletContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ServletContextA")
public class ServletContextA extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取tomcat中的ServletContext对象（三种方式），tomcat中有且只有一个ServletContext对象
        //1.第一种，这种方式既是创建又是获取
        ServletContext sc1 = this.getServletContext();
        //2.第二种
        ServletContext sc2 = req.getSession().getServletContext();
        //3.第三种
        ServletContext sc3 = this.getServletConfig().getServletContext();
        //打印三个对象的哈希码值
        System.out.println(sc1.hashCode());
        System.out.println(sc2.hashCode());
        System.out.println(sc3.hashCode());
        //以上三个对象的哈希码值是相等的，说明三个对象是同一个对象，

        //往ServletContext对象放入数据
        sc1.setAttribute("name","value");
    }
}
