package com.qixingjun.controller;

import com.qixingjun.action.ILoginAction;
import com.qixingjun.action.LoginActionFactory;
import com.qixingjun.pojo.ActionXmlBean;
import com.qixingjun.pojo.BeanXmlBean;
import com.qixingjun.pojo.ResultXmlBean;
import com.qixingjun.util.DiConfiguration;
import com.qixingjun.util.ParseXml;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * @Author XingJun Qi
 * @MyBlog www.qixingjun.tech
 * @Version 1.1.0
 * @Date 2016/11/26
 * @Description  全局的控制器，拦截所有满足以"*.scaction"为后缀的请求
 */

@WebServlet(name = "ActionController",urlPatterns = "*.scaction")
public class ActionController extends HttpServlet {
    private ParseXml parseXml;
    private DiConfiguration diConfiguration;

    //启动时候执行执行一次
    @Override
    public void init() throws ServletException {
        parseXml = new ParseXml();
        diConfiguration = new DiConfiguration();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            //1.获取请求的uri,得到请求路径名称
            String uri = request.getRequestURI();
            //得到login
            String actionName = uri.substring(uri.lastIndexOf("/")+1, uri.indexOf(".scaction"));

            //2.根据路径名称，读取配置文件，得到类的全名
            ActionXmlBean actionXmlBean = parseXml.getAction(actionName);
            BeanXmlBean beanXmlBean = diConfiguration.getBean("loginAction");

            Method m = null;
            ILoginAction loginAction = null;
            String returnFlag = null;
            if (beanXmlBean!=null){
                String className = actionXmlBean.getClassName();
                //当前请求的处理方法
                String method = actionXmlBean.getMethod();
                //反射：创建对象，调用方法，获取方法返回的标记
                Class<?> clazz = Class.forName(className);
                Object object = clazz.newInstance();
                m = clazz.getDeclaredMethod(method,HttpServletRequest.class,HttpServletResponse.class);
                //调用方法的的标记
                returnFlag = (String) m.invoke(object,request,response);
            }

//            else{
//                System.out.println(beanXmlBean.getBeanProperties().get("userBean").getRef_class()+"___)_)_)");
//                if (beanXmlBean.getBeanProperties().get("userBean").getRef_class()!=null){
//                    //反射：创建对象，调用方法，获取方法返回的标记
//                    String className = beanXmlBean.getClazz();
//                    //当前请求的处理方法
//                    String method = actionXmlBean.getMethod();
//                    Class<?> clazz = Class.forName(className);
//                    Object object = clazz.newInstance();
//                    m = clazz.getDeclaredMethod(method,HttpServletRequest.class,HttpServletResponse.class);
//                    //调用方法的的标记
//                    returnFlag = (String) m.invoke(object,request,response);
//                }
//            }
//            String className = actionXmlBean.getClassName();
//            //当前请求的处理方法
//            String method = actionXmlBean.getMethod();

            //3.反射：创建对象，调用方法，获取方法返回的标记
//            Class<?> clazz = Class.forName(className);
//            Object object = clazz.newInstance();
//            Method m = clazz.getDeclaredMethod(method,HttpServletRequest.class,HttpServletResponse.class);
            //调用方法的的标记
//            ILoginAction loginAction = LoginActionFactory.getInstance();
//            String returnFlag = loginAction.login(request,response);
            //String returnFlag = (String) m.invoke(object,request,response);

            //4.拿到标记，读取配置文件得到标记对应的页面，跳转类型
            ResultXmlBean result = actionXmlBean.getResults().get(returnFlag);
            //类型
            String type = result.getType();
            //页面
            String page = result.getPage();

            if ("redirect".equals(type)) {
                response.sendRedirect(request.getContextPath()+page);
            }else {
                request.getRequestDispatcher(page).forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        doGet(req, res);
    }
}
