package com.qixingjun.util;

import com.qixingjun.pojo.ActionXmlBean;
import com.qixingjun.pojo.ResultXmlBean;
import org.dom4j.Document;
import org.dom4j.DocumentException;
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
 * @Description 创建解析XML的类文件
 */
public class ParseXml {
    private Map<String,ActionXmlBean> allActions;

    public ParseXml(){
        allActions = new HashMap<String, ActionXmlBean>();
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

    // 初始化allActions集合
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

            //4.得到所有的action子节点
            List<Element> listActions = root.elements("action");

            //5.遍历，封装
            for (Element ele_action : listActions) {
                ActionXmlBean actionXmlBean = new ActionXmlBean();
                actionXmlBean.setName(ele_action.element("name").getText());
                actionXmlBean.setClassName(ele_action.element("class").element("name").getText());
                actionXmlBean.setMethod(ele_action.element("class").element("method").getText());

                //封装当前aciton节点下的results
                HashMap<String, ResultXmlBean> results = new HashMap<String, ResultXmlBean>();

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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
