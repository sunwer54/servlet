package com.servlet.web02;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/myweb02")
public class MyWeb02 extends HttpServlet {
    /**
     * doGet方法：只会用来接收浏览器提交的get请求
     * @param req ：接收浏览器提交的请求数据
     * @param resp：响应数据给浏览器
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doGet方法被调用了");
    }

    /**
     * doPost方法：只会用来处理浏览器提交的post请求
     * @param req：接收浏览器提交的请求数据
     * @param resp：响应数据给浏览器
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);//当浏览器提交的是哪种请求时，可以采用这种方式，不管是get还post都可以进行处理
        //无论浏览器提交的get请求还是post请求，都会调用service方法，所以在实际
        //开发中只需重写service方法即可，但一定要删除this.super(req resp)这个
        //父类方法的调用，否则通过它会去调用父类中doGet或者doPost方法

        System.out.println("doPost方法被调用了");
    }

    /**
     * @param req：接收浏览器提交的请求数据
     * @param resp：响应数据给浏览器
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //这个是调用父类中的service(req, resp)方法，从源码中可以看出，实际上就是调用对应的doGet或者doPost方法
        //super.service(req, resp);

        System.out.println("service方法");
    }

    @Override
    public void init() throws ServletException {
        System.out.println("init方法是初始化方法");
    }

    @Override
    public void destroy() {
        System.out.println("这是销毁方法");
    }
}
