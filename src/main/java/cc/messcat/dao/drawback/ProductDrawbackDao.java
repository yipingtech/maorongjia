/*
 * ProductDrawbackDao.java
 * 
 * Create by MCGT
 * 
 * Create time 2015-04-17
 * 
 * Version 1.0
 * 
 * Copyright 2013 Messcat, Inc. All rights reserved.
 * 
 */
package cc.messcat.dao.drawback;

import java.util.List;
import cc.modules.commons.Pager;
import cc.messcat.bases.BaseDao;
import cc.messcat.entity.ProductDrawback;

public interface ProductDrawbackDao extends BaseDao{

	public ProductDrawback get(Long id);
	
	/**
	 * 查询商家需要的所有订单
	 * @return
	 */
	public Pager queryAllDrawBackByProduct(int pageIndex,int pageSize);
	
	/**
	 * 查询商家已经退货的所有订单
	 * @return
	 */
	public Pager queryAllPassDrawBackByProduct(int pageIndex,int pageSize);
	
	/**
	 * 用户已经收到退款或换货的全部订单
	 * @return
	 */
	public List<ProductDrawback> queryAllPassDrawBackByUser(String memberId);
	
	/**
	 * 商家当天的退货申请订单
	 * @return
	 */
	public Pager queryDrawByDay(String flag,int pageStart,int pageSize);	
	
	/**
	 * 商家一周和一个月内的退货申请订单
	 * @param start
	 * @param end
	 * @return
	 */
	public Pager queryDrawByWeekAndMonth(String start,String end,String flag,int pageStart,int pageSize);
	
	/**
	 * 商家模糊查询申请退款和退货的订单
	 * @param productDrawback
	 * @return
	 */
	public Pager queryLikeDrawBack(ProductDrawback productDrawback,String flag,int pageStart,int pageSize);
	
//	@SuppressWarnings("unchecked")
//	public List findAll();
	
	public Pager getPager(int pageSize, int pageNo);
	
}