package com.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class JDBCUtil {
    //JDBC的驱动Driver的全完全限定名
    static String classDriver;
    //参数String url：连接数据的路径
    static String url;
    //参数String user:mysql登录用户名
    static String user;
    //参数String password：mysql登录密码
    static String password;

    /*静态代码块：类加载后自动执行，在程序执行中最优先执行，
    这里使用静态代码块使程序加载后自动加载配置文件数据，为程序执行做准备*/
    static {
        //获取src目录下的配置文件的字节输入流对象，开启src目录下配置文件的读取通道
        InputStream is = null;
        //创建属性对象，用来封装加载的配置文件的数据
        Properties pro = new Properties();
        try {
            is = JDBCUtil.class.getClassLoader().getResourceAsStream("com/util/jdbc.properties");
            //将配置文件中的数据加载到Properties对象中
            pro.load(is);
            //从属性类对象pro中依此获取数据
            classDriver = pro.getProperty("classDriver");//"com.mysql.jdbc.Driver";
            url = pro.getProperty("url");//"jdbc:mysql://localhost:3306/user?useUNicode=true&characterEncoding=utf8";
            user = pro.getProperty("user");//"root";
            password = pro.getProperty("pwd");//"li545426";

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            //关闭输入流
            closeInputStream(is);
        }

    }
    /*
    注册驱动和与数据库创建连接
     */
    public static Connection getConnection(){
        Connection conn = null;
        try {
            System.out.println(classDriver);
            //注册驱动
            Class.forName(classDriver);
            //与数据库建立连接
            conn = DriverManager.getConnection(url,user,password);
        }catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    /*
    6.释放JDBC资源
     */
    public static void release(ResultSet rs,Statement st,Connection conn){
        closeResultSet(rs);
        closeStatement(st);
        closeConnection(conn);
    }

    public static void release(Statement st,Connection conn){
        closeStatement(st);
        closeConnection(conn);
    }

    public static void release(ResultSet rs,Connection conn){
        closeResultSet(rs);
        closeConnection(conn);
    }

    public static void closeResultSet(ResultSet rs){
        try {
            if (rs!=null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            rs = null;
        }
    }
    public static void closeStatement(Statement st){
        try {
            if (st != null) {
                st.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            st = null;
        }
    }
    public static void closeConnection(Connection conn){
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            conn = null;
        }
    }
    public static void closeInputStream(InputStream is){
        try {
            if (is != null) {
                is.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            is = null;
        }
    }
}
