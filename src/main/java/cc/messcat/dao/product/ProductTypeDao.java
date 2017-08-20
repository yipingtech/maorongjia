/*
 * ProductTypeDao.java
 * 
 * Create by MCGT
 * 
 * Create time 2015-04-09
 * 
 * Version 1.0
 * 
 * Copyright 2013 Messcat, Inc. All rights reserved.
 * 
 */
package cc.messcat.dao.product;

import java.util.List;

import cc.messcat.bases.BaseDao;
import cc.messcat.entity.ProductType;

public interface ProductTypeDao extends BaseDao{

	public void save(ProductType productType);
	
	public void update(ProductType productType);
	
	public void delete(ProductType productType);
	
	public void delete(Long id);
	
	public ProductType get(Long id);
	
	@SuppressWarnings("unchecked")
	public List findAll();
	
	//通过类型id 去修改商品资料下架
	public void updateMcProductInfo(Long typeId);
	
	/**
	 * 根据产品类型名称查找是否存在
	 * @param productTypeName
	 * @return boolean
	 */
	public Boolean FindProductTypeByTypeName(String productTypeName);
	
	
}