package com.qixingjun.dao.impl;

import com.qixingjun.dao.IUserDao;
import com.qixingjun.pojo.UserBean;

import java.sql.*;

/**
 * @Author XingJun Qi
 * @MyBlog www.qixingjun.tech
 * @Version 1.1.0
 * @Date 2016/11/26
 * @Description IUserDao接口的实现类，数据的持久化
 */
public class UserDaoImpl implements IUserDao {
    @Override
    public Boolean findUser(UserBean userBean) {
        Connection con =null;
        PreparedStatement pstmt =null;
        ResultSet rs = null;
        try {
            String driver ="com.mysql.jdbc.Driver";
            String url ="jdbc:mysql://localhost:3306/simplecontroller";
            String user ="root";
            String password ="root";
            Class.forName(driver);
            con = DriverManager.getConnection(url, user, password);
            String sql = "select * from t_user where username=? AND password=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1,userBean.getUserName());
            pstmt.setString(2,userBean.getPassWord());
            rs = pstmt.executeQuery();
            if(rs==null){
                return false;
            }
            if(rs.next()){
                return true;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                if(pstmt!=null)pstmt.close();
                if(con!=null)con.close();
            }
            catch (SQLException e) {
            }
        }
        return false;
    }
}
