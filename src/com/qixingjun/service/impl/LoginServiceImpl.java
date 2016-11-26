package com.qixingjun.service.impl;

import com.qixingjun.dao.impl.UserDaoImpl;
import com.qixingjun.service.ILoginService;

/**
 * @Author XingJun Qi
 * @MyBlog www.qixingjun.tech
 * @Version 1.0.0
 * @Date 2016/11/26
 * @Description 登录接口的实现
 */
public class LoginServiceImpl implements ILoginService {
    @Override
    public String findUser(String userName) {
        UserDaoImpl userDaoImpl = new UserDaoImpl();
        return userDaoImpl.findUser(userName);
    }
}
