package com.qixingjun.pojo;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author XingJun Qi
 * @MyBlog www.qixingjun.tech
 * @Version 1.0.0
 * @Date 2016/12/2
 * @Description 封装action节点
 */
public class ActionXmlBean {
    //拦截器的名称
    private  String interceptorName;
    // 请求路径名称
    private String name;
    // 处理aciton类的全名
    private String className;
    // 处理方法
    private String method;
    // 结果视图集合
    private Map<String,ResultXmlBean> results;

    public ActionXmlBean() {
    }

    public String getInterceptorName() {
        return interceptorName;
    }

    public void setInterceptorName(String interceptorName) {
        this.interceptorName = interceptorName;
    }

    public Map<String, ResultXmlBean> getResults() {
        return results;
    }

    public void setResults(Map<String, ResultXmlBean> results) {
        this.results = results;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
