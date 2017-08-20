/*
 * ProductColumnManagerDao.java
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
package cc.messcat.service.collection;

import java.util.List;

import cc.messcat.entity.EnterpriseColumn;
import cc.messcat.entity.McProductInfo;
import cc.messcat.entity.ProductColumn;
import cc.modules.commons.Pager;

public interface ProductColumnManagerDao {

	public void addProductColumn(ProductColumn productColumn);
	
	public void modifyProductColumn(ProductColumn productColumn);
	
	public void removeProductColumn(ProductColumn productColumn);
	
	public void removeProductColumn(Long id);
	
	public ProductColumn retrieveProductColumn(Long id);
	
	@SuppressWarnings("unchecked")
	public List retrieveAllProductColumns();
	
	/**
	 * 根据类型查找商品
	 * @param typeId
	 * @return List
	 */
	public List<McProductInfo> retrieveAllProductInfos(Long typeId);

	/**
	 * 根据商品查关联的栏目，以便取得品牌故事
	 * */
	public EnterpriseColumn findByProduct(McProductInfo mcProduct);
	
	/**
	 * 根据商品id删除产品栏目数据
	 * @param id
	 */
	public void deleteByProduct(Long id);
	
	/**
	 * 判断是否有商品被加为栏目商品
	 * @param id
	 * @return true 为有  false 为无
	 */
	public boolean findByProduct(Long id);
	
	public Pager retrieveProductColumnsPager(int pageSize, int pageNo);
	
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
	public Pager findProductByCondition(McProductInfo mcProductInfo, Long columnId, int pageSize, int pageNo);
	
	/**
	 * @param enterpriseColumn  栏目
	 * @param productSearchCondition  搜索条件
	 * @return  
	 * 
	 * 通过产品、栏目和搜索条件查询 （产品栏目） 返回列表
	 */
	//public List<?> findProductColumnBySearchCondition(EnterpriseColumn enterpriseColumn, ProductSearchCondition productSearchCondition);

	
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
}