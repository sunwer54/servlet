package com.servlet.web05.servletContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@WebServlet("/ServletContextE")
public class ServletContextE extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取tomcat中的ServletContext对象，tomcat中有且只有一个ServletContext对象
        ServletContext sc1 = this.getServletContext();

        /*通过ServletContext对象获取路径*/
        //先尝试使用传统file对象获取路径
        //创建File对象
        File file = new File("files/file.txt");
        String filePath = file.getAbsolutePath();
        System.out.println(filePath);
        //D:\Program Files\apache-tomcat-8.0.50\apache-tomcat-8.0.50\bin\files\file.txt
        //通过该路径无法查找到该文件，说明通过传统file对象方式无法获取到该工程下文件的真实绝对路径

        //如何获取该工程下文件的真实路径？使用ServletContext对象来实现
        String realPath = sc1.getRealPath("files/file.txt");
        System.out.println(realPath);
        //D:\threeClass\mystudy\javaweb\javawebProgram\out\artifacts\javawebProgram_war_exploded\files\file.txt
        //该路径才是file.txt文件在该项目下的真实绝对路径
        FileInputStream fis = new FileInputStream(realPath);
        System.out.println((char)fis.read());
        System.out.println((char)fis.read());
        //获取当前项目的根路径
        String rootPath = sc1.getRealPath("/");
        System.out.println(rootPath);
        //D:\threeClass\mystudy\javaweb\javawebProgram\out\artifacts\javawebProgram_war_exploded\

    }
}
