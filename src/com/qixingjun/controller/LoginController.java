package com.qixingjun.controller;

import com.qixingjun.service.impl.LoginServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author XingJun Qi
 * @MyBlog www.qixingjun.tech
 * @Version 1.0.0
 * @Date 2016/11/26
 * @Description 登录的控制器
 */
@WebServlet(name = "LoginController",urlPatterns = "/loginController")
public class LoginController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String userName = request.getParameter("username");
        String passWord = request.getParameter("password");
        LoginServiceImpl loginServiceImpl = new LoginServiceImpl();
        if (passWord.equals(loginServiceImpl.findUser(userName))){
            request.getRequestDispatcher("/jsp/login_success.jsp").forward(request,response);
        }else{
            request.getRequestDispatcher("/jsp/login_fail.jsp").forward(request,response);
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
