package com.qixingjun.dao.impl;

import com.qixingjun.dao.IUserDao;
import com.qixingjun.pojo.UserBean;
import com.qixingjun.util.Conversation;

import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * @Author XingJun Qi
 * @MyBlog www.qixingjun.tech
 * @Version 1.1.0
 * @Date 2016/11/26
 * @Description IUserDao接口的实现类，数据的持久化
 */
public class UserDaoImpl implements IUserDao {
    Conversation conversation = new Conversation();
    @Override

    public Boolean findUser(UserBean userBean) {
        if (queryUser(userBean.getUserName())!= null){
            return true;
        }
        return false;
    }

    @Override
    public UserBean queryUser(String username) {
        return conversation.queryUser("userName",username);
    }

//    //负责打开MySQL数据库连接
//    @Override
//    public Connection openDBConnection() {
//        Connection con = null;
//        //mysql
////        String driver ="com.mysql.jdbc.Driver";
////        String url ="jdbc:mysql://localhost:3306/simplecontroller";
////        String user ="root";
////        String password ="root";
//
//        //postgresql
//        String driver ="org.postgresql.Driver";
//        String url ="jdbc:postgresql://localhost:5432/simplecontroller";
//        String user ="postgres";
//        String password ="root";
//        try {
//            Class.forName(driver);
//            con = DriverManager.getConnection(url, user, password);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return con;
//    }
//
//    //负责关闭MySQL数据库连接
//    @Override
//    public boolean closeDBConnection(Connection con) {
//        if (con!=null){
//            try {
//                con.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//            return true;
//        }
//        return false;
//    }
//
//    //负责根据参数查询对象表记录
//    @Override
//    public UserBean queryUser(String userName) {
//        PreparedStatement pstmt;
//        Connection con = openDBConnection();
//        ResultSet rs;
//        UserBean userBean = new UserBean();
//        String sql = "SELECT * FROM t_user WHERE username=?";
//        try {
//            pstmt = con.prepareStatement(sql);
//            pstmt.setString(1,userName);
//            rs = pstmt.executeQuery();
//            if(rs==null){
//                return null;
//            }
//            if(rs.next()){
//                userBean.setUserName(rs.getString(1));
//                userBean.setPassWord(rs.getString(2));
//                closeDBConnection(con);
//                return userBean;
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    //负责根据参数增加对象表记录
//    @Override
//    public boolean insertUser(UserBean u) {
//        PreparedStatement pstmt;
//        Connection con = openDBConnection();
//        String sql = "INSERT INTO t_user where VALUES ("+u.getUserName()+","+u.getPassWord()+")";
//        try {
//            pstmt = con.prepareStatement(sql);
//            pstmt.executeQuery();
//            closeDBConnection(con);
//            return true;
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return false;
//    }
//
//    //负责根据参数修改对象表记录
//    @Override
//    public boolean updateUser(UserBean u) {
//        PreparedStatement pstmt;
//        Connection con = openDBConnection();
//        String sql = "UPDATE t_user SET username = "+u.getUserName()+",password = "+u.getPassWord();
//        try {
//            pstmt = con.prepareStatement(sql);
//            pstmt.executeQuery();
//            closeDBConnection(con);
//            return true;
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return false;
//    }
//
//    //负责根据参数删除对象表记录
//    @Override
//    public boolean deleteUser(UserBean u) {
//        PreparedStatement pstmt;
//        Connection con = openDBConnection();
//        String sql = "DELETE FROM t_user WHERE username = ? AND password = ?";
//        try {
//            pstmt = con.prepareStatement(sql);
//            pstmt.setString(1,u.getUserName());
//            pstmt.setString(2,u.getPassWord());
//            pstmt.executeQuery();
//            closeDBConnection(con);
//            return true;
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return false;
//    }
}
