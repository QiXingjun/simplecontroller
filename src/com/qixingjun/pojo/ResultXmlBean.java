package com.qixingjun.pojo;

/**
 * @Author XingJun Qi
 * @MyBlog www.qixingjun.tech
 * @Version 1.0.0
 * @Date 2016/12/2
 * @Description 封装结果视图
 */
public class ResultXmlBean {
    // 跳转的结果标记
    private String name;
    // 跳转类型，默认为转发； "redirect"为重定向
    private String type;
    // 跳转的页面
    private String page;

    public ResultXmlBean() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }
}
