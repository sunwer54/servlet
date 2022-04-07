package com.servlet.web02;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@WebServlet("/loginUser02")
public class LoginUser02 extends HttpServlet {//tomcat在底层执行了 LoginUser loginUser = new LoginUser()
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
        resp.setContentType("text/html;charset=utf-8");

        //响应数据到浏览器
        resp.getWriter().write("响应数据到浏览器");
        resp.getWriter().print("响应数据到浏览器");
        //可以响应头中设置信息
        resp.setHeader("keybord","hello");

        //接收浏览器提交的用户名和密码数据
        String userName = req.getParameter("userName");
        String pwd = req.getParameter("pwd");
        System.out.println(userName+"---"+pwd);

        //接收数组
        String[] favors = req.getParameterValues("favor");
        System.out.println(Arrays.toString(favors));

        //模拟数据的校验
        if (userName.equals("admin")&&pwd.equals("123456")){//开发中药从数据库中查询
            //给浏览器反馈响应数据
            resp.getWriter().write("loginSucceed");
        }else{
            resp.getWriter().write("loginFailed");
        }

        //获取浏览器的其他数据
        String accept = req.getHeader("Accept");//传入请求头的key，可以获取请求头的值
        System.out.println(accept);

        String agent = req.getHeader("User-Agent");//这个信息可以反映出客户使用的是终端还是pc还是平板
        System.out.println(agent);//浏览器类型

        String name = Thread.currentThread().getName();//当前线程的名字
        System.out.println("当前线程名字："+name);

        System.out.println("Servlet对象的哈希码值："+this.hashCode());//当前对象的哈希码值
        System.out.println("req对象的哈希码值："+req.hashCode());
        System.out.println("resp对象的哈希码值："+resp.hashCode());

    }
}
