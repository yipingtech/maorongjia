/*
 * ProductDrawbackManagerDao.java
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
package cc.messcat.service.drawback;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cc.modules.commons.Pager;
import cc.messcat.entity.OrderInfo;
import cc.messcat.entity.ProductDrawback;

public interface ProductDrawbackManagerDao {

	public void removeProductDrawback(Long id);
	
	public ProductDrawback retrieveProductDrawback(Long id);
	
	/**
	 * @描述 新增退换货记录
	 * 
	 * */
	public void addProductDrawback(ProductDrawback productDrawback);
	/**
	 * @描述 查看申请退款订单详情根据id
	 * @author Karhs
	 * @date 2015-5-8
	 * @param productDrawback
	 * @return
	 */
	public ProductDrawback DrawAddressByOrderInfoNum(ProductDrawback productDrawback);

	public Pager retrieveProductDrawbacksPager(int pageSize, int pageNo);
	
	/**
	 * @描述 商家需要审核的全部订单
	 * @author Karhs
	 * @date 2015-4-17
	 * @param pageStart
	 * @param pageSize
	 * @return
	 */
	public Pager allDrawBackByProducter(int pageStart,int pageSize);
	
	/**
	 * @描述 商家已经退款或换货的全部订单
	 * @author Karhs
	 * @date 2015-4-17
	 * @param pageStart
	 * @param pageSize
	 * @return
	 */
	public Pager allPassDrawBackByProducter(int pageStart,int pageSize);

	/**
	 * @描述 用户已经收到退款或换货的全部订单
	 * @author Karhs
	 * @date 2015-4-17
	 * @param pageStart
	 * @param pageSize
	 * @param memberId
	 * @return
	 */
	public Pager allPassDrawBackByUser(int pageStart,int pageSize,String memberId);

	/**
	 * @描述 用户申请退货
	 * @author Karhs
	 * @date 2015-4-17
	 * @param productDrawback
	 */
	public void applyDrawBack(ProductDrawback productDrawback,OrderInfo orderInfo);

	/**
	 * @描述 商家审核通过或不通过
	 * @author Karhs
	 * @date 2015-4-17
	 * @param productDrawback
	 */
	public void passDrawBack(ProductDrawback productDrawback,String status);

	
	/**
	 * @描述 模糊查询申请的订单
	 * @author Karhs
	 * @date 2015-4-20
	 * @param productDrawback
	 */
	public Pager queryLikeDrawBack(int pageNo, int pageSize,ProductDrawback productDrawback,String flag,String dateStr);
	
	/**
	 * @描述 模糊查询申请的订单(审核后退货表)
	 * @author Karhs
	 * @date 2015-4-20
	 * @param productDrawback
	 */
    public Pager queryLikeDrawBack1(int pageNo, int pageSize,ProductDrawback productDrawback,String flag,String dateStr);
    
}