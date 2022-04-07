package com.dao.daoImpl;

import com.bean.UserInfo;
import com.dao.UserDao;
import com.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl implements UserDao {
    @Override
    public UserInfo loginUser(UserInfo userInfo) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
                UserInfo user = null;
        try {
            conn = JDBCUtil.getConnection();
            String sql = "select userName,password from loginuser where userName=? and password=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,userInfo.getUserName());
            ps.setString(2,userInfo.getPwd());
            resultSet = ps.executeQuery();
            while (resultSet.next()){
                String uName = resultSet.getString("userName");
                String pwd = resultSet.getString("password");
                user = new UserInfo();
                user.setUserName(uName);
                user.setPwd(pwd);
                System.out.println("查询到了user："+user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtil.release(resultSet,ps,conn);
        }
        System.out.println("返回user："+user);
        return user;
    }
}
