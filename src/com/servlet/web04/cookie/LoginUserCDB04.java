package com.servlet.web04.cookie;

import com.bean.UserInfo;
import com.dao.UserDao;
import com.dao.daoImpl.UserDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/LoginUserCDB04")
public class LoginUserCDB04 extends HttpServlet {
    private UserDao userDao;
    public LoginUserCDB04(){
        userDao = new UserDaoImpl();
    }
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //设置请求提交的数据的编码
        req.setCharacterEncoding("UTF-8");
        //设置响应到浏览器的数据的编码
        resp.setContentType("text/html;charset=utf-8");

        //接收浏览器提交的用户名和密码数据
        String userName = req.getParameter("userName");
        String pwd = req.getParameter("pwd");
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName(userName);
        userInfo.setPwd(pwd);
        System.out.println("客户端提交的数据："+userInfo);

        //连接数据库验证用户名和密码
        UserInfo user = userDao.loginUser(userInfo);
        System.out.println("数据库查询到user："+user);
        if (user!=null){
            //创建Cookie对象，把数据存储到Cookie对象中，Cookie中存储的数据的格式是：键值对的形式
            /*
            Cookie对存的数据字符有要求，只能存数字、字符、下划、等不能存对象，汉字，标点符号等，
            Cookie在开发中不是用来存大量数据的，一般情况下在开发中是用来存session的id的
             */
            Cookie c = new Cookie("userName",user.getUserName());
            /*
            设置Cookie的有效时间，单位是秒，这个Cookie数据存到了浏览器的硬盘中;只要在有效时间内，
            关闭浏览器也不会失效。
            默认情况下（不设置有效时间的情况下）这个Cookie是存在浏览器中（内存中）关闭浏览器即失效
            设置三天免登陆：c.setMaxAge(24*3600*3);
             */
            c.setMaxAge(300);
            //通过响应对象resp.addCookie方法，把Cookie对象中的数据响应到浏览器并存储到浏览器的内存中
            resp.addCookie(c);
            //重定向跳转到登录成功的处理程序
            resp.sendRedirect("SuccessServlet04");

        }else{
            //请求转发推荐在登录失败时使用
            req.getRequestDispatcher("login.html").forward(req,resp);
        }
    }
}