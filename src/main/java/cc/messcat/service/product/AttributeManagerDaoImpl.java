/*
 * AttributeManagerDaoImpl.java
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

import cc.messcat.bases.BaseManagerDaoImpl;
import cc.messcat.entity.Attribute;

public class AttributeManagerDaoImpl extends BaseManagerDaoImpl implements AttributeManagerDao {

	public void addAttribute(Attribute attribute) {
		this.attributeDao.save(attribute);
	}
	
	public void modifyAttribute(Attribute attribute) {
		this.attributeDao.update(attribute);
	}
	
	public void removeAttribute(Attribute attribute) {
		this.attributeDao.delete(attribute);
	}

	public void removeAttribute(Long id) {
		this.attributeDao.delete(id);
	}

	public void removeAttributeFromId(Long id) {
		this.attributeDao.deleteFormTypeId(id);
	}
	
	public Attribute retrieveAttribute(Long id) {
		return (Attribute) this.attributeDao.get(id);
	}

	@SuppressWarnings("unchecked")
	public List retrieveAllAttributes() {
		return this.attributeDao.findAll();
	}
	
	//查找是否有商品应用了该属性
	public boolean findProductAttr(Long attrId){
		return this.attributeDao.findProductAttr(attrId);
	}
	
}