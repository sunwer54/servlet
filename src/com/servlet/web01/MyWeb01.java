package com.servlet.web01;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 通过浏览器访问java代码,并响应数据给浏览器
 */
//@WebServlet("/myweb01") //该注解表示为浏览提供了访问java类的地址，地址是myweb
public class MyWeb01 extends HttpServlet {//HTTpServlet类可以用来基于tomcat服务器开发java程序

    /**
     * 为浏览器提供网络响应服务
     * @param req：用来接收浏览器提交的请求数据
     * @param resp：用来响应数据给浏览器
     * @throws ServletException
     * @throws IOException
     */
    @Override //该注解表示这个方法是重写HttpServlet类里的
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //响应数据给浏览器
        resp.getWriter().write("hello javaweb");

        //接收浏览器发送的请求数据
        String s = req.getParameter("userName");
        System.out.println("服务器接收到浏览器的请求数据："+s);
    }

    /**
     * init()是初始化方法：提前初始化在程序运行时要使用的数据，且只会被调用一次
     * @throws ServletException
     */
    @Override
    public void init() throws ServletException {
        System.out.println("初始化已经被调用了");
    }

    /**
     * destroy()是销毁方法：在服务器停止或者从tomcat中删除项目时
     * 调用执行，用来结束一些程序，关闭一些资源，避免资源浪费
     */
    @Override
    public void destroy() {
        System.out.println("销毁方法已经被调用了");
    }
}
