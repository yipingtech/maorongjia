/*
 * ProductColumnDao.java
 * 
 * Create by MCGT
 * 
 * Create time 2014-02-13
 * 
 * Version 1.0
 * 
 * Copyright 2013 Messcat, Inc. All rights reserved.
 * 
 */
package cc.messcat.dao.collection;

import java.util.List;

import cc.messcat.entity.EnterpriseColumn;
import cc.messcat.entity.McProductInfo;
import cc.messcat.entity.ProductColumn;
import cc.modules.commons.Pager;

public interface ProductColumnDao {
	
	public EnterpriseColumn findByProduct(McProductInfo mcProduct);

	public void save(ProductColumn productColumn);
	
	public void update(ProductColumn productColumn);
	
	public void delete(ProductColumn productColumn);
	
	public void delete(Long id);
	
	public ProductColumn get(Long id);
	
	@SuppressWarnings("unchecked")
	public List findAll();
	
	public Pager getPager(int pageSize, int pageNo);
	
	public void deleteByProduct(Long id);
	
	/**
	 * 判断是否有商品被加为栏目商品
	 * @param id
	 * @return true 为有  false 为无
	 */
	public boolean findByProduct(Long id);
	/**
	 *  
	 * @param mcProduct   产品
	 * @param enterpriseColumn  栏目
	 * @return 产品栏目列表
	 * 
	 * 根据产品、栏目分页查找对应列表
	 */
	public Pager findProductByColumnPro(McProductInfo mcProduct, EnterpriseColumn enterpriseColumn, int pageSize, int pageNo);
	
	/**
	 *  
	 * @param enterpriseColumn  栏目
	 * @param mcProduct  产品
	 * @return 产品栏目列表
	 * 
	 * 根据栏目、产品分页查找非该栏目下的产品
	 */
	public Pager findProductByNoColumn(McProductInfo mcProduct, EnterpriseColumn enterpriseColumn, int pageSize, int pageNo);
	
	
	
	
	/**
	 *  
	 * @param mcProduct   产品
	 * @param enterpriseColumn  栏目
	 * @return 产品栏目列表
	 * 
	 * 根据产品、栏目查找对应列表
	 */
	public List<?> findProductByColumnPro(McProductInfo mcProduct, EnterpriseColumn enterpriseColumn);
	
	/**
	 *  
	 * @param enterpriseColumn  栏目
	 * @return 产品栏目列表
	 * 
	 * 根据栏目查找非该栏目下的产品
	 */
	public List<?> findProductByNoColumn(EnterpriseColumn enterpriseColumn);

	/**
	 *  
	 * @param enterpriseColumn  栏目
	 * @return 产品栏目列表
	 * 
	 * 根据产品搜索条件查找产品
	 */
	public Pager findProductByCondition(McProductInfo mcProduct, Long columnId,
			int pageSize, int pageNo);
	
	/**
	 * @param columnId  栏目id
	 * @return mcproductinfo 
	 * 
	 * 通过栏目查询 产品
	 */
	public List<?> findProductByColumn(Long columnId);
	
	/**
	 * @param columnId  栏目id 
	 * @return mcproductinfo 
	 * 
	 * 通过栏目查询 上架产品
	 */
	public List<?> findProductByColumnIsSale(Long columnId);

	/**
	 * 根据产品、栏目查找对应列表
	 * 产品是上架的产品
	 * @param mcProduct
	 * @param enterpriseColumn
	 * @return
	 */
	public List<?> findIsSaleProductByColumnPro(McProductInfo mcProduct, EnterpriseColumn enterpriseColumn);
	
}