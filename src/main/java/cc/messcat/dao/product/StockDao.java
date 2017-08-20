/*
 * StockDao.java
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
package cc.messcat.dao.product;

import java.util.List;
import cc.modules.commons.Pager;
import cc.messcat.bases.BaseDao;
import cc.messcat.entity.Stock;

public interface StockDao extends BaseDao{

	public void save(Stock stock);
	
	public void update(Stock stock);
	
	public void delete(Stock stock);
	
	public void delete(Long id);
	
	public Stock get(Long id);
	
	@SuppressWarnings("unchecked")
	public List findAll();
	
	public Pager getPager(int pageSize, int pageNo);
	
	public List<Stock> searchByCondition(Stock condition);
	
	public Stock findByProAttr(Stock condition);
	
}