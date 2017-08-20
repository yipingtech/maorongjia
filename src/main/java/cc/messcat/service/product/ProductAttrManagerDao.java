/*
 * ProductAttrManagerDao.java
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
package cc.messcat.service.product;

import java.util.List;

import cc.messcat.entity.Attribute;
import cc.messcat.entity.McProductInfo;
import cc.messcat.entity.ProductAttr;
import cc.modules.commons.Pager;

public interface ProductAttrManagerDao {
	
	public ProductAttr findAttrByProduct(McProductInfo mcProductInfo);

	public void addProductAttr(ProductAttr productAttr);
	
	public void modifyProductAttr(ProductAttr productAttr);
	
	public void removeProductAttr(ProductAttr productAttr);
	
	public void removeProductAttr(Long id);
	
	public ProductAttr retrieveProductAttr(Long id);
	
	@SuppressWarnings("unchecked")
	public List retrieveAllProductAttrs();
	
	public Pager retrieveProductAttrsPager(int pageSize, int pageNo);
	
	public void saveProcutAttr(McProductInfo mcProductInfo, Attribute attribute, String attrVal);
	
	
	/**
	 * 查询condition，若不存在则新增，存在则返回
	 * @param condition
	 * @return
	 */
	public ProductAttr searchProductAttr(ProductAttr condition);
	
	/**
	 * 通过产品，属性类型查 产品-属性
	 * @param productId
	 * @param attrType
	 * @return
	 */
	public List<ProductAttr> queryByProductAndAttrType(Long productId, String attrType);
	
	public List<ProductAttr> findByIds(String ids);
}