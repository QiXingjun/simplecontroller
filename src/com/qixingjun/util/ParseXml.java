package com.qixingjun.util;

import com.qixingjun.pojo.ActionXmlBean;
import com.qixingjun.pojo.InterceptorXmlBean;
import com.qixingjun.pojo.ResultXmlBean;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @Author XingJun Qi
 * @MyBlog www.qixingjun.tech
 * @Version 1.0.0
 * @Date 2016/12/2
 * @Description 解析XML文件
 */
public class ParseXml {
    private Map<String,ActionXmlBean> allActions;
    private Map<String,InterceptorXmlBean> allInterceptors;

    public ParseXml(){
        allActions = new HashMap<>();
        allInterceptors = new HashMap<>();
        this.init();
    }

    public ActionXmlBean getAction(String ActionName){
        if (ActionName==null) {
            throw new RuntimeException("传入参数有误，请查看配置文件配置路径");
        }
        ActionXmlBean actionXmlBean = allActions.get(ActionName);
        if (actionXmlBean==null) {
            throw new RuntimeException("在配置文件中找不到路径");
        }
        return actionXmlBean;
    }

    public InterceptorXmlBean getInterceptor(String InterceptorName){
        if (InterceptorName==null) {
            throw new RuntimeException("传入参数有误，请查看配置文件配置路径");
        }
        InterceptorXmlBean interceptorXmlBean = allInterceptors.get(InterceptorName);
        if (interceptorXmlBean==null) {
            throw new RuntimeException("在配置文件中找不到路径");
        }
        return interceptorXmlBean;
    }

    // 初始化allActions和allInterceptor集合
    private void init(){
        //读取配置文件
        try {
            // 1.得到解析器
            SAXReader reader = new SAXReader();

            // 2. 加载文件
            InputStream inputStream = this.getClass().getResourceAsStream("/controller.xml");
            Document doc = reader.read(inputStream);

            //3.获取根
            Element root = doc.getRootElement();

            //4.1得到所有的action子节点
            List<Element> listActions = root.elements("action");

            //4.2得到所有的interceptor子节点
            List<Element> listInterceptors = root.elements("interceptor");

            //5.1遍历，封装 action下的节点
            for (Element ele_action : listActions) {
                ActionXmlBean actionXmlBean = new ActionXmlBean();
                actionXmlBean.setInterceptorName(ele_action.element("interceptor-ref").getText());
                actionXmlBean.setName(ele_action.element("name").getText());
                actionXmlBean.setClassName(ele_action.element("class").element("name").getText());
                actionXmlBean.setMethod(ele_action.element("class").element("method").getText());

                //封装当前aciton节点下的results
                HashMap<String, ResultXmlBean> results = new HashMap<>();

                //得到当前action节点下所有的result子节点
                Iterator<Element> iterator = ele_action.elementIterator("result");

                while(iterator.hasNext()){
                    // 当前迭代的每一个元素都是 <result...>
                    Element ele_result = iterator.next();
                    ResultXmlBean resultXmlBean = new ResultXmlBean();
                    resultXmlBean.setName(ele_result.element("name").getText());
                    resultXmlBean.setType(ele_result.element("type").getText());
                    resultXmlBean.setPage(ele_result.element("value").getText());
                    results.put(resultXmlBean.getName(), resultXmlBean);
                }
                actionXmlBean.setResults(results);
                allActions.put(actionXmlBean.getName(), actionXmlBean);
            }

            //5.2遍历，封装 interceptor下的节点
            for (Element ele_interceptor : listInterceptors) {
                InterceptorXmlBean interceptorXmlBean = new InterceptorXmlBean();
                interceptorXmlBean.setName(ele_interceptor.element("name").getText());
                interceptorXmlBean.setClassName(ele_interceptor.element("class").element("name").getText());
                interceptorXmlBean.setMethod(ele_interceptor.element("class").element("method").getText());
                allInterceptors.put(interceptorXmlBean.getName(), interceptorXmlBean);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
