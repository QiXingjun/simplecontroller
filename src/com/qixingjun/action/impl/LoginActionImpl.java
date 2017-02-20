package com.qixingjun.action.impl;

import com.qixingjun.action.ILoginAction;
import com.qixingjun.dao.impl.UserDaoImpl;
import com.qixingjun.pojo.UserBean;
import com.qixingjun.service.impl.LoginServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @Author XingJun Qi
 * @MyBlog www.qixingjun.tech
 * @Version 1.1.0
 * @Date 2016/11/30
 * @Description 负责处理login请求的Action
 */
public class LoginActionImpl implements ILoginAction {
//    LoginServiceImpl loginService = new LoginServiceImpl();
    UserDaoImpl userDao = new UserDaoImpl();
    //登录的处理数据的方法
    @Override
    public String login(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        UserBean userBean = new UserBean();
        if (userDao.queryUser(req.getParameter("username"))==null){
            return "fail";
        }else {
            userBean.setPassWord(userDao.queryUser(req.getParameter("username")).getPassWord());
        }
        if (userBean.getPassWord().equals(req.getParameter("password"))){
            return "success";
        }else{
            return "fail";
        }
    }
}
