package com.qixingjun.pojo;

/**
 * @Author XingJun Qi
 * @MyBlog www.qixingjun.tech
 * @Version 1.3.0
 * @Date 2016/12/6
 * @Description 封装interceptor节点
 */
public class InterceptorXmlBean {
    // 请求路径名称
    private String name;
    // 处理interceptor类的全名
    private String className;
    // 处理方法
    private String method;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
