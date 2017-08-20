/*
 * ProductColumnManagerDaoImpl.java
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

import cc.messcat.bases.BaseManagerDaoImpl;
import cc.messcat.entity.EnterpriseColumn;
import cc.messcat.entity.McProductInfo;
import cc.messcat.entity.ProductColumn;
import cc.modules.commons.Pager;

public class ProductColumnManagerDaoImpl extends BaseManagerDaoImpl implements ProductColumnManagerDao {

	public void addProductColumn(ProductColumn productColumn) {
		this.productColumnDao.save(productColumn);
	}
	
	public void modifyProductColumn(ProductColumn productColumn) {
		this.productColumnDao.update(productColumn);
	}
	
	public void removeProductColumn(ProductColumn productColumn) {
		this.productColumnDao.delete(productColumn);
	}

	public void removeProductColumn(Long id) {
		this.productColumnDao.delete(id);
	}
	
	public ProductColumn retrieveProductColumn(Long id) {
		return (ProductColumn) this.productColumnDao.get(id);
	}

	@SuppressWarnings("unchecked")
	public List retrieveAllProductColumns() {
		return this.productColumnDao.findAll();
	}
	
	public Pager retrieveProductColumnsPager(int pageSize, int pageNo) {
		return this.productColumnDao.getPager(pageSize, pageNo);
	}
	
	public Pager findProductByColumnPro(McProductInfo mcProduct, EnterpriseColumn enterpriseColumn, int pageSize, int pageNo){
		return this.productColumnDao.findProductByColumnPro(mcProduct, enterpriseColumn, pageSize, pageNo);
	}
	
	public Pager findProductByNoColumn(McProductInfo mcProduct, EnterpriseColumn enterpriseColumn, int pageSize, int pageNo){
		return this.productColumnDao.findProductByNoColumn(mcProduct, enterpriseColumn, pageSize, pageNo);
	}
	
	public List<?> findProductByColumnPro(McProductInfo mcProduct, EnterpriseColumn enterpriseColumn){
		return this.productColumnDao.findProductByColumnPro(mcProduct, enterpriseColumn);
	}
	
	public List<?> findProductByNoColumn(EnterpriseColumn enterpriseColumn){
		return this.productColumnDao.findProductByNoColumn(enterpriseColumn);
	}

	public Pager findProductByCondition(McProductInfo mcProductInfo, Long columnId, int pageSize, int pageNo) {
		return this.productColumnDao.findProductByCondition(mcProductInfo, columnId, pageSize, pageNo);
	}
	
	/**
	 * 根据商品id删除产品栏目数据
	 * @param id
	 */
	public void deleteByProduct(Long id){
		this.productColumnDao.deleteByProduct(id);
	}
	
	/**
	 * 判断是否有商品被加为栏目商品
	 * @param id
	 * @return true 为有  false 为无
	 */
	public boolean findByProduct(Long id){
		return this.productColumnDao.findByProduct(id);
	}
	
	/**
	 * @param columnId  栏目id
	 * @return mcproductinfo 
	 * 
	 * 通过栏目查询 产品
	 */
	public List<?> findProductByColumn(Long columnId){
		return this.productColumnDao.findProductByColumn(columnId);
	}
	/**
	 * @param columnId  栏目id
	 * @return mcproductinfo 
	 * 
	 * 通过栏目查询 上架产品
	 */
	public List<?> findProductByColumnIsSale(Long columnId){
		return this.productColumnDao.findProductByColumnIsSale(columnId);
	}
	
	/**
	 * 根据类型查找商品
	 * @param typeId
	 * @return List
	 */
	public List<McProductInfo> retrieveAllProductInfos(Long typeId){
		return this.mcProductInfoDao.findAllFromTypeId(typeId);
	}
	/**
	 * 根据产品查找栏目
	 * @param 
	 * @return EnterpriseColumn
	 */
	@Override
	public EnterpriseColumn findByProduct(McProductInfo mcProduct) {
		return this.productColumnDao.findByProduct(mcProduct);
	}
}