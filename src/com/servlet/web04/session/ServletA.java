package com.servlet.web04.session;

import com.bean.UserInfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/servletA")
public class ServletA extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置接收浏览器请求数据的编码
        req.setCharacterEncoding("utf-8");
        //设置响应到浏览器的数据的编码
        resp.setContentType("text/html;charset=utf-8");
        //获取浏览器提交的数据
        String userName = req.getParameter("userName");
        String pwd = req.getParameter("pwd");
        //把接收到的数据封装到UserInfo对象中
        UserInfo user = new UserInfo();
        user.setUserName(userName);
        user.setPwd(pwd);

        /*
        把从浏览器接收到的值UserInfo对象中的值共享给ServletB用，通过Session来共享，
        Session对象在tomcat容器中有且只有一个
         */
        /*创建Session对象（如果tomcat中没有Session对象就创建Session新的对象，如果
          tomcat中有就直接从tomcat对象中获取Session对象）。
          Session对象一旦创建，默认30分钟有效(关闭浏览器会立即失效)，在开发中一般用来存储用户登录的身份信息*/
        HttpSession session = req.getSession();

        //往Session对象中存储数据user，这个Session对象会自动将它的jsessionid放入Cookie中（这个操作不需要我们手写）
        session.setAttribute("userInfo",user);

        //设置session的有效时间，单位是秒，关闭浏览器即失效
        session.setMaxInactiveInterval(600);
    }
}
