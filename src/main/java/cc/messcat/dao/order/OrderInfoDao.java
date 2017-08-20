/*
 * OrderInfoDao.java
 * 
 * Create by MCGT
 * 
 * Create time 2015-04-10
 * 
 * Version 1.0
 * 
 * Copyright 2013 Messcat, Inc. All rights reserved.
 * 
 */
package cc.messcat.dao.order;

import java.util.List;
import cc.modules.commons.Pager;
import cc.messcat.bases.BaseDao;
import cc.messcat.entity.OrderInfo;

public interface OrderInfoDao extends BaseDao{

	public void save(OrderInfo orderInfo);
	
	public void update(OrderInfo orderInfo);
	
	public void delete(OrderInfo orderInfo);
	
	public void delete(Long id);
	
	public OrderInfo get(Long id);
	
	@SuppressWarnings("unchecked")
	public List findAll();
	
	public Pager getPager(int pageSize, int pageNo);
	
}