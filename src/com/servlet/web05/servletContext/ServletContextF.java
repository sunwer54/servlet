package com.servlet.web05.servletContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@WebServlet("/ServletContextF")
public class ServletContextF extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取tomcat中的ServletContext对象，tomcat中有且只有一个ServletContext对象
        ServletContext sc1 = this.getServletContext();
        /*
        给服务器创建存储文件的目录
         */
        //1.创建文件夹或者文件
        //1.1获取当前项目的根路径
        String rootPath = sc1.getRealPath("/");
        //1.2在根目录下创建文件夹
        File images = new File(rootPath,"images");
        //创建images文件夹,如果不存在就才创建，存在就不要创建
        if (!images.exists()) {
            Boolean b = images.mkdir();
            System.out.println(b);
        }
        //下载文件
        //1.拿到文件的绝对路径
        String realPath = sc1.getRealPath("images/4.jpg");
        System.out.println(realPath);

        //创建文件的数据流，从文件中读取数据
        FileInputStream fis = new FileInputStream(realPath);
        //获取要下载的文件的名称
        String fileName = null;
        File[] files = images.listFiles();
        for (File file:files){
            if (file.getName().equals("4.jpg")){
               fileName = file.getName();
            }
        }
        //使用附件的形式下载
        //设置响应头(必须这么写)
        resp.setHeader("Content-Disposition", "attachment;filename="+fileName);
        //下载，把服务器中的文件响应给浏览器
        ServletOutputStream out = resp.getOutputStream();
        byte[] bytes=new byte[1024*8];
        int length;
        while ((length=fis.read(bytes))!=-1){
            out.write(bytes,0,length);
        }
        out.close();
        fis.close();
    }
}
