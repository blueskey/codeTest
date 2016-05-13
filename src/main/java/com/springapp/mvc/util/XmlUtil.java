package com.springapp.mvc.util;

import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/10/10 0010.
 */
public class XmlUtil {
    public static Map<String, String> xml2Map(String xmlStr)
            throws JDOMException, IOException {

        Map<String, String> rtnMap = new HashMap<String, String>();
        SAXBuilder builder = new SAXBuilder();
        Document document = builder.build(new StringReader(xmlStr));
        // 得到根节点
        Element element = document.getRootElement();
        String rootName = element.getName();
        rtnMap.put("root.name", rootName);

        // 调用递归函数，得到所有最底层元素的名称和值，加入map中
        convert(element, rtnMap, rootName);
        return rtnMap;
    }

    /**
     * 递归函数，找出最下层的节点并加入到map中，由xml2Map方法调用。
     *
     * @param element  xml节点，包括根节点
     * @param map      目标map
     * @param lastName 从根节点到上一级节点名称连接的字串
     */
    public static void convert(Element element, Map<String, String> map,
                               String lastName) {
        if (element.getAttributes().size() > 0) {
            Iterator iterator = element.getAttributes().iterator();
            while (iterator.hasNext()) {
                Attribute attribute = (Attribute) iterator.next();
                String attrName = attribute.getName();
                String attrValue = element.getAttributeValue(attrName);
                map.put(lastName + "." + attrName, attrValue);
            }
        }
        List children = element.getChildren();
        Iterator it = children.iterator();
        while (it.hasNext()) {
            Element child = (Element) it.next();
            String name = lastName + "." + child.getName();
            // 如果有子节点，则递归调用
            if (child.getChildren().size() > 0) {
                convert(child, map, name);
            } else {
                // 如果没有子节点，则把值加入map
                map.put(name, child.getText());
                // 如果该节点有属性，则把所有的属性值也加入map
                if (child.getAttributes().size() > 0) {
                    Iterator attr = child.getAttributes().iterator();
                    while (attr.hasNext()) {
                        Attribute attribute = (Attribute) attr.next();
                        String attrName = attribute.getName();
                        String attrValue = child.getAttributeValue(attrName);
                        map.put(name + "." + attrName, attrValue);
                    }
                }
            }
        }
    }
}
