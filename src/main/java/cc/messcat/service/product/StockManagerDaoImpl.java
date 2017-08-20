/*
 * StockManagerDaoImpl.java
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

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cc.messcat.bases.BaseManagerDaoImpl;
import cc.messcat.entity.McProductInfo;
import cc.messcat.entity.Stock;
import cc.modules.commons.Pager;
import cc.modules.constants.Constants;
import cc.modules.util.FormatStringUtil;
import cc.modules.util.ObjValid;

public class StockManagerDaoImpl extends BaseManagerDaoImpl implements StockManagerDao {

	public void addStock(Stock stock) {
		this.stockDao.save(stock);
	}
	
	public void addStockByAttr(McProductInfo product, String productAttrId, Long amount, Double price){
		List<Stock> listStock = this.searchByProductAndLikePattr(product,productAttrId);
		if(ObjValid.isValid(listStock)){
			for(int i=0;i<listStock.size();i++){
				Stock stock = listStock.get(i);
				stock.setAmount(amount);
				stock.setPrice(price);
				stock.setAddTime(new Date());
				stock.setStatus("1");
				this.stockDao.update(stock);
			}
		}else{
			Stock stock = new Stock();
			stock.setAmount(amount);
			stock.setPrice(price);
			stock.setProduct(product);
			stock.setProductAttrIds(productAttrId);
			stock.setAddTime(new Date());
			stock.setStatus("1");
			this.stockDao.save(stock);
		}
	}
	
	public void modifyStock(Stock stock) {
		this.stockDao.update(stock);
	}
	
	public void removeStock(Stock stock) {
		//删除产品属性关联
		List<String> proAttrList = FormatStringUtil.splitBySign(stock.getProductAttrIds(), ",");
		for (int i = 0; i < proAttrList.size(); i++) {
			this.productAttrDao.delete(Long.parseLong(proAttrList.get(i)));
		}
		this.stockDao.delete(stock);
	}

	public void removeStock(Long id) {
		this.stockDao.delete(id);
	}
	
	public Stock retrieveStock(Long id) {
		return (Stock) this.stockDao.get(id);
	}

	@SuppressWarnings("unchecked")
	public List retrieveAllStocks() {
		return this.stockDao.findAll();
	}
	
	public Pager retrieveStocksPager(int pageSize, int pageNo) {
		return this.stockDao.getPager(pageSize, pageNo);
	}
	
	@SuppressWarnings("unchecked")
	public List<Stock> searchByProduct(McProductInfo product){
		Map<String, Object> attrs = new HashMap<String, Object>();
		attrs.put("product", product);
		attrs.put("status", Constants.ENABLE);
		return this.stockDao.queryList(Stock.class, "id", "", attrs);
	}
	
	@SuppressWarnings("unchecked")
	public List<Stock> searchByProductAndLikePattr(McProductInfo product, String productAttrId){
		Stock stock = new Stock();
		stock.setProduct(product);
		stock.setProductAttrIds(productAttrId);
		return this.stockDao.searchByCondition(stock);
	}
	
	public Stock findByProAttr(Stock condition){
		return this.stockDao.findByProAttr(condition);
	}

}