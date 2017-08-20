/*
 * ParseXmlToBean.java
 * 
 * Create by Michael Tang
 * 
 * Create time 2015-3-22
 * 
 * Version 1.0
 * 
 * Copyright 2015 Messcat, Inc. All rights reserved.
 */
package cc.modules.util;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * 
 * @author Michael Tang
 * 
 *         使用此类必须严格遵守以下规则： 
 *         1、bean的类名与xml里的主节点名字一致 
 *         2、bean和xml文件要放到相应的路径下
 *         3、bean里的子bean列表属性名必须以Chirdren结尾
 * 
 */
@SuppressWarnings("unchecked")
public class ParseXmlToBean {

	private static final String beanPacage = "cc.messcat.bean.";
	private static final String xmlPacage = "cc\\modules\\config\\";

	public static List parserXml(String beanName) {

		List beanList = new ArrayList();

		try {
			String thisClassPath = ParseXmlToBean.class.getClassLoader().getResource("").getPath();
			thisClassPath = URLDecoder.decode(thisClassPath, "utf-8");
			String xmlPath = thisClassPath + xmlPacage + beanName + ".xml";
			File inputXml = new File(xmlPath);
			SAXReader saxReader = new SAXReader();
			Document document = saxReader.read(inputXml);
			Element element = document.getRootElement();
			for (Iterator i = element.elementIterator(); i.hasNext();) {
				Element beanElement = (Element) i.next();
				if (beanElement.getName().equals(beanName.toLowerCase())) {
					Object beanObj = parseBeanXml(beanElement, beanName);
					beanList.add(beanObj);
				}
			}
		} catch (DocumentException e) {
			System.err.println("XML解析异常：" + e.toString());
		} catch (Exception e) {
			System.err.println("未知异常：" + e.toString());
		}

		return beanList;
	}

	private static Object parseBeanXml(Element beanElement, String beanName) throws Exception {

		List beanChildren = new ArrayList();

		// 根据beanName来加载类
		Object bean = Class.forName(beanPacage + beanName).newInstance();

		// 将bean里的所有属性作为节点名称获取xml里的节点值
		Field[] fields = bean.getClass().getDeclaredFields();
		for (Iterator j = beanElement.elementIterator(); j.hasNext();) {
			Element beanProperty = (Element) j.next();

			// 节点名称
			String propertyName = beanProperty.getName();

			// 节点的值
			String propertyText = beanProperty.getText();
			for (Field field : fields) {
				String fieldName = field.getName();
				if (fieldName.equals(propertyName)) {
					Method getterMethod = bean.getClass().getMethod(StringUtil.getGetterMethodName(fieldName), new Class[] {});
					Method setterMethod = bean.getClass().getMethod(StringUtil.getSetterMethodName(fieldName),
						getterMethod.getReturnType());
					setterMethod.invoke(bean, new Object[] { propertyText });
					break;
				}
			}

			// 子bean列表的递归循环
			if (propertyName.equals(beanName.toLowerCase())) {
				beanChildren.add(parseBeanXml(beanProperty, beanName));
			}
		}

		// 赋值给子bean列表
		String getterName = "get" + beanName + "Children";
		String setterName = "set" + beanName + "Children";
		Method getterMethod = bean.getClass().getMethod(getterName, new Class[] {});
		Method setterMethod = bean.getClass().getMethod(setterName, getterMethod.getReturnType());
		setterMethod.invoke(bean, new Object[] { beanChildren });

		return bean;
	}
}
