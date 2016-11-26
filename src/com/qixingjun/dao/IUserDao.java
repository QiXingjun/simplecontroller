package com.qixingjun.dao;

/**
 * @Author XingJun Qi
 * @MyBlog www.qixingjun.tech
 * @Version 1.0.0
 * @Date 2016/11/26
 * @Description 根据用户名来查找用户的密码
 */
public interface IUserDao {
    String findUser(String userName);
}
