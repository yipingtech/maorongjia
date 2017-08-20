/*
 * ProductAttrDao.java
 * 
 * Create by MCGT
 * 
 * Create time 2015-04-15
 * 
 * Version 1.0
 * 
 * Copyright 2013 Messcat, Inc. All rights reserved.
 * 
 */
package cc.messcat.dao.product;

import java.util.List;

import cc.modules.commons.Pager;
import cc.messcat.bases.BaseDao;
import cc.messcat.entity.McProductInfo;
import cc.messcat.entity.ProductAttr;

public interface ProductAttrDao extends BaseDao{

	public void save(ProductAttr productAttr);
	
	public void update(ProductAttr productAttr);
	
	public void delete(ProductAttr productAttr);
	
	public void delete(Long id);
	
	public ProductAttr get(Long id);
	
	@SuppressWarnings("unchecked")
	public List findAll();
	
	public Pager getPager(int pageSize, int pageNo);
	
	public List<ProductAttr> queryByProductAndAttrType(Long productId, String attrType);

	public ProductAttr findAttrByProduct(McProductInfo mcProductInfo);
}