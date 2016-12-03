package com.qixingjun.service.impl;

import com.qixingjun.dao.impl.UserDaoImpl;
import com.qixingjun.pojo.UserBean;
import com.qixingjun.service.ILoginService;

/**
 * @Author XingJun Qi
 * @MyBlog www.qixingjun.tech
 * @Version 1.1.0
 * @Date 2016/11/26
 * @Description  登录接口的实现
 */
public class LoginServiceImpl implements ILoginService {
    @Override
    public Boolean findUser(UserBean userBean) {
        UserDaoImpl userDaoImpl = new UserDaoImpl();
        return userDaoImpl.findUser(userBean);
    }
}
