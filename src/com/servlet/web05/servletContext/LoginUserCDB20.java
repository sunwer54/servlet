package com.servlet.web05.servletContext;

import com.bean.UserInfo;
import com.dao.UserDao;
import com.dao.daoImpl.UserDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/LoginUserCDB20")
public class LoginUserCDB20 extends HttpServlet {
    private UserDao userDao;
    public LoginUserCDB20(){
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

            /*登录成功，把身份信息放入Session对象中，再重定向页面跳转*/
            //获取Session对象
            HttpSession session = req.getSession();
            //把数据放入到Session对象中
            session.setAttribute("ui",user);
            resp.sendRedirect("SuccessServlet20");

        }else{
            //请求转发推荐在登录失败时使用
            req.getRequestDispatcher("login.html").forward(req,resp);
        }
    }
}