package com.qixingjun.dao;


import com.qixingjun.pojo.UserBean;

import java.sql.Connection;

/**
 * @Author XingJun Qi
 * @MyBlog www.qixingjun.tech
 * @Version 1.1.0
 * @Date 2016/11/26
 * @Description 根据用户名,密码来查找用户，找到返回true，否则返回false
 */
public interface IUserDao {

    Boolean findUser(UserBean userBean);
    UserBean queryUser(String username);
//    //负责打开MySQL数据库连接
//    Connection openDBConnection();
//    //负责关闭MySQL数据库连接
//    boolean closeDBConnection(Connection con);
//    //负责根据参数查询对象表记录
//    UserBean queryUser(String userName);
//    //负责根据参数增加对象表记录
//    boolean insertUser(UserBean u);
//    //负责根据参数修改对象表记录
//    boolean updateUser(UserBean u);
//    //负责根据参数删除对象表记录
//    boolean deleteUser(UserBean u);

}
