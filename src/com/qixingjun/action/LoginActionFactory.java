package com.qixingjun.action;

import com.qixingjun.action.impl.LoginActionImpl;
import com.qixingjun.pojo.ActionXmlBean;
import com.qixingjun.pojo.InterceptorXmlBean;
import com.qixingjun.util.ParseXml;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author XingJun Qi
 * @MyBlog www.qixingjun.tech
 * @Version 1.0.0
 * @Date 2016/12/8
 * @Description 代理对象的工厂
 */
public class LoginActionFactory {
    private static ILoginAction loginAction = new LoginActionImpl();
    public static ILoginAction getInstance(){
        ILoginAction loginActionProxy = (ILoginAction) Proxy.newProxyInstance(loginAction.getClass().getClassLoader(), loginAction.getClass().getInterfaces(),
                new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                HttpServletRequest request = (HttpServletRequest) args[0];
                //获取请求的uri,得到请求路径名称
                String uri = request.getRequestURI();
                //得到login
                String actionName = uri.substring(uri.lastIndexOf("/")+1, uri.indexOf(".scaction"));

                ParseXml parseXml = new ParseXml();
                //得到interceptorXmlBean
                InterceptorXmlBean interceptorXmlBean = parseXml.getInterceptor("logWriter");
                ActionXmlBean actionXmlBean = parseXml.getAction(actionName);
                String name = null;
                String type = null;
                String s_time = null;
                String e_time = null;
                if (interceptorXmlBean!=null){
                    String interceptorClassName = interceptorXmlBean.getClassName();
                    String interceptorMethod = interceptorXmlBean.getMethod();
                    //反射：创建对象，调用方法，获取方法返回的标记
                    Class<?> clazz = Class.forName(interceptorClassName);
                    Object object = clazz.newInstance();
                    Method m = clazz.getDeclaredMethod(interceptorMethod,String.class,String.class,String.class,String.class,String.class);
                    //调用方法
                    name = actionName;
                    Date date = new Date();
                    DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    s_time = format.format(date);
                    //String e_time,String result
                    m.invoke(object,name,"",s_time,"","");
                }
                Object object = method.invoke(loginAction,args);
                if (interceptorXmlBean!=null){
                    String interceptorClassName = interceptorXmlBean.getClassName();
                    String interceptorMethod = interceptorXmlBean.getMethod();
                    //反射：创建对象，调用方法，获取方法返回的标记
                    Class<?> clazz = Class.forName(interceptorClassName);
                    Object object2 = clazz.newInstance();
                    Method m = clazz.getDeclaredMethod(interceptorMethod,String.class,String.class,String.class,String.class,String.class);
                    //调用方法
                    name = actionName;
                    type = actionXmlBean.getResults().get(object).getType();
                    Date date = new Date();
                    DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    e_time = format.format(date);
                    m.invoke(object2,name,type,s_time,e_time,object);
                }
                return object;
            }
        });
        return loginActionProxy;
    }
}
