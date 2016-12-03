package com.qixingjun.service;

import com.qixingjun.pojo.UserBean;

/**
 * @Author XingJun Qi
 * @MyBlog www.qixingjun.tech
 * @Version 1.0.0
 * @Date 2016/11/26
 * @Description 登录接口
 */
public interface ILoginService {
    Boolean findUser(UserBean userBean);
}
