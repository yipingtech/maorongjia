/*
 * PayOrderDao.java
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
package cc.messcat.dao.pay;

import java.util.Date;
import java.util.List;

import cc.messcat.bases.BaseDao;
import cc.messcat.entity.AgentOrder;
import cc.messcat.entity.Member;
import cc.messcat.entity.OrderInfo;
import cc.messcat.entity.PayOrder;
import cc.messcat.entity.ProductAttr;
import cc.modules.commons.Pager;

public interface PayOrderDao extends BaseDao{

//	public void save(PayOrder payOrder);
//	
//	public void update(PayOrder payOrder);
//	
//	public void delete(PayOrder payOrder);
//	
//	public void delete(Long id);
	
//	public PayOrder get(Long id);
	
	public List<String> findAllAgentOrder();
	
	@SuppressWarnings("unchecked")
	public List findAll();
	public List<PayOrder> findPayOrder();
	
	/**
	 * 查询出单个订单和其清单的详细内容
	 * @param payOrder
	 * @return
	 */
	public List<OrderInfo> queryPayAndOrder(PayOrder payOrder);
	
	/**
	 * 商家当天的订单
	 * @return
	 */
	public Pager queryByDay(int pageStart,int pageSize,String judge);

	/**
	 * 商家一周和一个月内的订单
	 * @return
	 */
	public Pager queryByWeek(String start,String end,int pageStart,int pageSize,String judge);

	
	/**
	 * 用户的待收货订单或全部的订单(已收货)
	 * @param loginName
	 * @param orderStatus
	 * @return
	 */
	public List<OrderInfo> noReceiveOrderByUser(String loginName,String shippingStatus);
	
	/**
	 * 用户的待收货订单(不包括清单)
	 * @param member
	 * @return
	 */
	public Pager noReceivePayByUser(Member member,int pageStart,int pageSize);
	
	/**
	 * 查询商家已经发货到签收的所有订单
	 * @return
	 */
	public Pager queryAllSendToReceiveOrder(int pageStart,int pageSize);
	
	/**
	 * 用户待付款或全部的订单
	 * @param loginName
	 * @param orderStatus
	 * @return
	 */
	public List<OrderInfo> PayOrderByUser(String loginName);
	
	/**
	 * 用户分销的清单
	 * @param member
	 * @param pageStart
	 * @param pageSize
	 * @return
	 */
	public Pager shareOrderInfoByFather(Member member,int pageStart,int pageSize);
	
	 /**
	  * 用户分销的订单
	  * @param member
	  * @param pageStart
	  * @param pageSize
	  * @return
	  */
	public Pager sharePayOrderByFather(List<Double> rateList, Member member,int pageStart,int pageSize);
	
	/**
	 * 查询清单商品的属性
	 * @param productAttr
	 * @return
	 */
	public List<ProductAttr> queryProductAttr(String productAttr);
	
	/**
	 * 用户批量确认收货
	 * @param orderNum
	 */
	public void moreReceiveOrderByUser(String orderNum);
	
	/**
	 * 订单模糊查询
	 * @param status
	 * @param pageIndex
	 * @param pageSize
	 * @param payOrder
	 * @param flag
	 * @return
	 */
	public Pager queryNameAndOrderByLike(String status,int pageIndex,int pageSize,PayOrder payOrder,int flag);
	
	public Pager getPager(int pageSize, int pageNo);
	
	public Pager findByAgent(Member member, Date begin, Date end, int pageSize, int pageNo);
	
	public int countMyTeamByAgent(Member member);

	public List<PayOrder> getTwoDayOrder(String todayStr, String yestodayStr);
	
	public AgentOrder queryAgentByOrderNum(String payOrderNum);
	
	public Pager queryAllCycleOrder(int pageStart, int pageSize);
	
	
}