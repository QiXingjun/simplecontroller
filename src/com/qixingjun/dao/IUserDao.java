package com.qixingjun.dao;

import com.qixingjun.pojo.UserBean;

/**
 * @Author XingJun Qi
 * @MyBlog www.qixingjun.tech
 * @Version 1.1.0
 * @Date 2016/11/26
 * @Description 根据用户名,密码来查找用户，找到返回true，否则返回false
 */
public interface IUserDao {
    Boolean findUser(UserBean userBean);
}
