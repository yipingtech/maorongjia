/*
 * StockManagerDao.java
 * 
 * Create by MCGT
 * 
 * Create time 2015-04-16
 * 
 * Version 1.0
 * 
 * Copyright 2013 Messcat, Inc. All rights reserved.
 * 
 */
package cc.messcat.service.product;

import java.util.List;

import cc.messcat.entity.McProductInfo;
import cc.messcat.entity.Stock;
import cc.modules.commons.Pager;

public interface StockManagerDao {

	public void addStock(Stock stock);
	
	public void modifyStock(Stock stock);
	
	/**
	 * 删除库存，并删除产品-属性关联表数据
	 * @param stock
	 */
	public void removeStock(Stock stock);
	
	public void removeStock(Long id);
	
	public Stock retrieveStock(Long id);
	
	@SuppressWarnings("unchecked")
	public List retrieveAllStocks();
	
	public Pager retrieveStocksPager(int pageSize, int pageNo);
	
	public void addStockByAttr(McProductInfo product, String productAttrId, Long amount, Double price);
	
	/**
	 * 根据产品查库存
	 * @param product
	 * @return
	 */
	public List<Stock> searchByProduct(McProductInfo product);
	
	/**
	 * 根据产品及产品-属性id模糊查询值查询
	 * @param product
	 * @param productAttrId
	 * @return
	 */
	public List<Stock> searchByProductAndLikePattr(McProductInfo product, String productAttrId);
	
	/**
	 * 查找产品库存量
	 * @param condition
	 * @return
	 */
	public Stock findByProAttr(Stock condition);
	
}