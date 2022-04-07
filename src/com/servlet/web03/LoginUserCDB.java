package com.servlet.web03;

import com.bean.UserInfo;
import com.dao.UserDao;
import com.dao.daoImpl.UserDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/loginUserCDB")
public class LoginUserCDB extends HttpServlet {//tomcat在底层执行了 LoginUser loginUser = new LoginUser()
    private UserDao userDao;
    public LoginUserCDB() {
        userDao = new UserDaoImpl();
    }

    /**
     * HttpServletRequest req：该对象中封装了用户的请求数据
     * HttpServletResponse resp：该对象封装了响应给浏览器的数据
     * @param req：接收浏览器提交的请求数据
     * @param resp：响应数据给浏览器
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*
          以下两个对象由tomcat服务器创建，用来封装请求数据和响应数据的
          HttpServletRequest req = new HttpServletRequest()
          HttpServletResponse resp = new HttpServletResponse()
         */

        //浏览器用的字符编码默认是utf-8，java程序默认的字符编码是iso8859-1或者GBK
        //设置请求提交的数据的编码，避免乱码
        req.setCharacterEncoding("UTF-8");
        //设置响应到浏览器的数据的编码
        resp.setContentType("text/html;charset=UTF-8");

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
            //resp.getWriter().write("登录成功，欢迎"+ui.getUserName()+"回来");

            /*
            请求转发：页面跳转，但url不会跳转到其他页面,如果刷新页面，是一次重复提交请求并登录的操作
            因此在登录或转账的场景使用req.getRequestDispatcher方法不合适，不推荐 。

            //跳转到html页面
            //req.getRequestDispatcher("success.html").forward(req,resp);
            //请求对象可以携带数据，传到handlerServlet中,通过req携带的数可以在不同的Servlet程序之间共享
            req.setAttribute("user",ui);
            //跳转到其他的servlet程序
            req.getRequestDispatcher("handlerServlet").forward(req,resp);*/

            /*在登录或转账场景下推荐使用resp.sendRedirect方法重定向页面跳转，
            刷新页面不会重复登录操作，url会跳转到目标指定路径，但不能携带数据*/
            resp.sendRedirect("handlerServlet");

        }else{
            //请求转发推荐在登录失败时使用
            req.getRequestDispatcher("login.html").forward(req,resp);
            //resp.getWriter().write("用户名或密码错误，请重新输入");
        }
    }
}