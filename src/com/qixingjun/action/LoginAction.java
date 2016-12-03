package com.qixingjun.action;

import com.qixingjun.pojo.UserBean;
import com.qixingjun.service.impl.LoginServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author XingJun Qi
 * @MyBlog www.qixingjun.tech
 * @Version 1.1.0
 * @Date 2016/11/30
 * @Description 负责处理login请求的Action
 */
public class LoginAction{
    LoginServiceImpl loginService = new LoginServiceImpl();
    //登录的处理数据的方法
    public String login(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        UserBean userBean = new UserBean();
        userBean.setUserName(req.getParameter("username"));
        userBean.setPassWord(req.getParameter("password"));
        if (loginService.findUser(userBean)){
            return "success";
        }else{
            return "fail";
        }
    }
}
