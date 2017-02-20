package com.qixingjun.interceptor;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStreamWriter;

/**
 * @Author XingJun Qi
 * @MyBlog www.qixingjun.tech
 * @Version 1.3.0
 * @Date 2016/12/6
 * @Description 拦截器,在该类中定义方法log()，log 方法实现的功能为记录每次客户端请求的action 名称、
 * 类型、访问开始时间、访问结束时间、请求返回结果result 值，并将信息追加至log.xml，保存在PC 磁盘上。
 */
public class LogWriter {
    public void log(String name,String type,String s_time,String e_time,String result){
        //读取配置文件
        try {
            // 1.得到解析器
            SAXReader reader = new SAXReader();

            // 2.加载文件
            InputStream inputStream = this.getClass().getResourceAsStream("/log.xml");
            Document doc = reader.read(inputStream);

            //3.获取根
            Element root = doc.getRootElement();

            //4.添加action节点
            Element newActionElement = root.addElement("action");

            //5.在新的action节点下，添加name,type,s-time,e-time,result节点
            Element newNameElement = newActionElement.addElement("name");
            newNameElement.setText(name);
            Element newTypeElement = newActionElement.addElement("type");
            newTypeElement.setText(type);
            Element newSTimeElement = newActionElement.addElement("s_time");
            newSTimeElement.setText(s_time);
            Element newETimeElement = newActionElement.addElement("e_time");
            newETimeElement.setText(e_time);
            Element newResultElement = newActionElement.addElement("result");
            newResultElement.setText(result);
            writer(doc);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 把document对象写入新的文件
     * @param document
     * @throws Exception
     */
    public void writer(Document document) throws Exception {
        // 紧凑的格式
        // OutputFormat format = OutputFormat.createCompactFormat();
        // 排版缩进的格式
        OutputFormat format = OutputFormat.createPrettyPrint();
        // 设置编码
        format.setEncoding("UTF-8");
        // 创建XMLWriter对象,指定了写出文件及编码格式
        XMLWriter writer = new XMLWriter(new OutputStreamWriter(
                new FileOutputStream(new File("G:/USTC/IdeaWorkSpace/simplecontroller/src/log.xml")), "UTF-8"), format);
        // 写入
        writer.write(document);
        // 立即写入
        writer.flush();
        // 关闭操作
        writer.close();
    }
}
