package com.servlet.web04.cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/SuccessServlet04")
public class SuccessServlet04 extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置浏览器提交的数据的编码，避免乱码
        req.setCharacterEncoding("utf-8");
        //设置响应到浏览器的数据的编码，避免乱码
        resp.setContentType("text/html;charset=utf-8");
        //通过浏览器中请求头中携带的Cookie：userName=lijian找到tomcat中Cookie对象的数据
        //获取Cookie
        Cookie[] cookies = req.getCookies();
        //遍历Cookie数组对象，拿到存储在Cookie对象中的用户名信息
        String value = null;
        for(Cookie c:cookies){
            //从其中找到key是userName的Cookie的对象
            if ("userName".equals(c.getName())){
                //拿到Cookie对象的值
                value = c.getValue();
            }
        }
        //响应到浏览器的信息可以解析标签
        resp.getWriter().write("<font color='red'>登录成功，欢迎"+value+"回来</font>");
    }
}
