package com.qixingjun.action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author XingJun Qi
 * @MyBlog www.qixingjun.tech
 * @Version 1.0.0
 * @Date 2016/12/8
 * @Description
 */
public interface ILoginAction {
    String login(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException;
}
