/*
 * AttributeDao.java
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
package cc.messcat.dao.product;

import java.util.List;

import cc.messcat.bases.BaseDao;
import cc.messcat.entity.Attribute;
import cc.modules.commons.Pager;

public interface AttributeDao extends BaseDao {

	public void save(Attribute attribute);
	
	public void update(Attribute attribute);
	
	public void delete(Attribute attribute);
	
	public void delete(Long id);
	
	public void deleteFormTypeId(Long id);
	
	public Attribute get(Long id);
	
	@SuppressWarnings("unchecked")
	public List findAll();
	
	//查找是否有商品应用了该属性
	public boolean findProductAttr(Long attrId);
	/**
	 * 通过产品类别ID查询出对应的单选属性
	 */
	@SuppressWarnings("unchecked")
	public Pager getByProductType(int pageSize, int pageNo, Long typeId);
	
	public int coutALLResult(Long typeId);
}