/*
 * ProductTypeManagerDao.java
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
package cc.messcat.service.product;

import java.util.List;

import cc.messcat.bases.BaseManagerDao;
import cc.messcat.entity.ProductType;

public interface ProductTypeManagerDao extends BaseManagerDao {

	public void addProductType(ProductType productType);
	
	public void modifyProductType(ProductType productType);
	
	public void removeProductType(ProductType productType);
	
	public void removeProductType(Long id);
	
	public ProductType retrieveProductType(Long id);
	
	@SuppressWarnings("unchecked")
	public List retrieveAllProductTypes();
	
	public boolean findOrderByTypeId(Long id);
	
	public void updateMcProductInfo(Long typeId);
	
	/*
	 *删除属性 及删除该属性的商品资料
	 *@para id 
	 */
	public void removeAttributeProduct(Long id,Long attributeId);
	/**
	 * 根据产品类型名称查找是否存在
	 * @param productTypeName
	 * @return boolean
	 */
	public Boolean FindProductTypeByTypeName(String productTypeName);
	
	/*
	 *判断是否有该商品存在订单或者购物车中
	 *@param productId 
	 */
	public boolean findOrderAndCartInfobyProductId(Long productId);
}