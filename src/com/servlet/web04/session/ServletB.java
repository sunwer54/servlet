package com.servlet.web04.session;

import com.bean.UserInfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/servletB")
public class ServletB extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置接收浏览器请求数据的编码
        req.setCharacterEncoding("utf-8");
        //设置响应到浏览器的数据的编码
        resp.setContentType("text/html;charset=utf-8");
        //获取到Session对象
        HttpSession session = req.getSession();
        //获取session中的数据
        UserInfo userInfo = (UserInfo)session.getAttribute("userInfo");

        System.out.println(userInfo.getUserName()+"-----"+userInfo.getPwd());


    }
}
