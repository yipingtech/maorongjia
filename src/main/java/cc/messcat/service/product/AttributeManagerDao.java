/*
 * AttributeManagerDao.java
 * 
 * Create by MCGT
 * 
 * Create time 2015-04-10
 * 
 * Version 1.0
 * 
 * Copyright 2013 Messcat, Inc. All rights reserved.
 * 
 */
package cc.messcat.service.product;

import java.util.List;

import cc.messcat.bases.BaseManagerDao;
import cc.messcat.entity.Attribute;

public interface AttributeManagerDao extends BaseManagerDao {

	public void addAttribute(Attribute attribute);
	
	public void modifyAttribute(Attribute attribute);
	
	public void removeAttribute(Attribute attribute);
	
	public void removeAttribute(Long id);

	public void removeAttributeFromId(Long id);
	
	public Attribute retrieveAttribute(Long id);
	
	@SuppressWarnings("unchecked")
	public List retrieveAllAttributes();
	
	//查找是否有商品应用了该属性
	public boolean findProductAttr(Long attrId);
}