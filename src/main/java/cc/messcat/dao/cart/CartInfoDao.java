/*
 * CartInfoDao.java
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
package cc.messcat.dao.cart;

import java.util.List;

import cc.modules.commons.Pager;
import cc.messcat.bases.BaseDao;
import cc.messcat.entity.CartInfo;
import cc.messcat.entity.McProductInfo;

public interface CartInfoDao extends BaseDao {

	public void save(CartInfo cartInfo);
	
	public void update(CartInfo cartInfo);
	
	public void delete(CartInfo cartInfo);
	
	public void delete(Long id);
	
	public CartInfo get(Long id);
	
	@SuppressWarnings("unchecked")
	public List findAll();
	
	public Pager getPager(int pageSize, int pageNo);
	
	public void updateByProduct(McProductInfo productInfo);
	
	/**
	 * 根据ids查找entityClass
	 * @param entityClass
	 * @param ids
	 */
	public <T> List<T> queryListByIds(Class entityClass, String ids);
	
}