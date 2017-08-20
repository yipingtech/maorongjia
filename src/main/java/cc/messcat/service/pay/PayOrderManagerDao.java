/*
 * PayOrderManagerDao.java
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
package cc.messcat.service.pay;

import java.util.Date;
import java.util.List;
import java.util.Map;

import cc.messcat.entity.AgentOrder;
import cc.messcat.entity.Member;
import cc.messcat.entity.OrderInfo;
import cc.messcat.entity.ParameterSet;
import cc.messcat.entity.PayOrder;
import cc.messcat.entity.ProductAttr;
import cc.modules.commons.Pager;

public interface PayOrderManagerDao {
	/**
	 * 查找所有代理商订单接口
	 * */
	public List<String> findAllAgentOrder();
	/**
	 * 更新订单
	 * */
	public void updatePayOrder(PayOrder payOrder);
	
	public List<PayOrder> findPayOrder(); 
	
	/**
	 * 查找配置文件信息
	 * */
	public ParameterSet findParameterSet();
	/**
	 * @描述 添加订单
	 * @author Seven
	 * @date 2015-10-15
	 * @param payOrder
	 * @param orderInfoList
	 * @param cartIdList (购物车id)
	 */
	public PayOrder addPayOrder(PayOrder payOrder,List<OrderInfo> orderInfoList,List<String> cartIdList);

	/**
	 * @描述 添加订单
	 * @author karhs
	 * @date 2015-4-13
	 * @param payOrder
	 * @param orderInfoList
	 */
	public PayOrder addPayOrder(PayOrder payOrder,List<OrderInfo> orderInfoList);
	
	/**
	 * @描述 修改订单
	 * @author karhs
	 * @date 2015-4-13
	 * @param payOrder
	 */
	public void modifyPayOrder(PayOrder payOrder);
	
	/**
	 * @描述 根据id删除数据(把数据修改为删除态)
	 * @author karhs
	 * @date 2015-4-13
	 * @param orderNum
	 */
	public void deletePayOrder(PayOrder payOrder,String startStatus,String endStatus);
	
	/**
	 * @描述  根据条件来查询数据(查询所有订单列表（过滤掉删除态）)
	 * @author karhs
	 * @date 2015-4-14
	 * @param payOrderList
	 * @return
	 */
	public Pager queryByAttrs(PayOrder payOrder,int pageStart,int pageSize,String flag);
	
	/**
	 * @描述  根据条件模糊查询订单数据(查询商家已经发货到签收的所有订单)
	 * @author karhs
	 * @date 2015-4-14
	 * @param payOrderList
	 * @return
	 */
	public Pager queryByAttrs1(PayOrder payOrder,int pageStart,int pageSize,String flag);
	
	/**
	 * @描述  根据条件模糊查询订单数据(订单回收站)
	 * @author karhs
	 * @date 2015-4-14
	 * @param payOrder
	 * @param pageStart
	 * @param pageSize
	 * @return
	 */
	public Pager queryByAttrsCycle(PayOrder payOrder,int pageStart,int pageSize);
	
	/**
	 * @描述 根据id查询
	 * @param id
	 * @return
	 */
	public PayOrder retrievePayOrderById(Long id);
	
	/**
	 * 根据id彻底删除数据
	 * @param id
	 */
	public void deletePayOrder(Long id);
	
	/**
	 * @描述 查询单个订单的所有信息，包括清单
	 * @author karhs
	 * @date 2015-4-14
	 * @param id
	 * @return
	 */
	public List<OrderInfo> queryPayAndOrder(PayOrder payOrder);
	
	/**
	 * @描述 查询出单个清单的详细内容
	 * @author karhs
	 * @date 2015-4-14
	 * @param id
	 * @return
	 */
	public List<OrderInfo> queryOneOrderInfo(String orderInfoNum);
	
	/**
	 * @描述 已成功发送货单
	 * @author karhs
	 * @date 2015-4-14
	 * @return
	 */
	public Pager querySendProduct(int pageStart,int pageSize);
	
	/**
	 * @描述 查询商家已经发货到签收的所有订单
	 * @author karhs
	 * @date 2015-4-14
	 * @return
	 */
	public Pager queryAllSendToReceiveOrder(int pageStart,int pageSize);
	
	/**
	 * @描述 查询所有已经移除了的订单（订单回收站）
	 * @author karhs
	 * @date 2015-5-11
	 * @param pageStart
	 * @param pageSize
	 * @return
	 */
	public Pager queryAllCycleOrder(int pageStart,int pageSize);
	
	/**
	 * @描述 商家一周和一个月内的订单
	 * @author karhs
	 * @date 2015-5-11
	 * @param pageStart
	 * @param pageSize
	 * @return
	 */
	public Pager queryByWeekAndMonth(int pageStart,int pageSize,String flag,String judge);

	
	/**
	 * @描述 确认付款
	 * @author karhs
	 * @date 2015-4-14
	 * @param payOrder
	 * @return
	 */
	public String editPayType(PayOrder payOrder);
	
	/**
	 * @描述 商家发货
	 * @author karhs
	 * @date 2015-4-14
	 * @param payOrder
	 * @return
	 */
	public String editSendShippingType(PayOrder payOrder);
	
	/**
	 * @描述 用户确认收货(修改收货状态)
	 * @author karhs
	 * @date 2015-4-15
	 * @param payOrder
	 * @return
	 */
	public String editReceiveShippingType(PayOrder payOrder);
	
	/**
	 * @描述 用户的待付款订单
	 * @author karhs
	 * @date 2015-4-15
	 * @param payOrder
	 * @return
	 */
	public List<OrderInfo> noPayOrderByUser(String loginName);
	
	/**
	 * @描述 用户的待收货订单和清单(或者已经收获的订单)	
	 * @author karhs
	 * @date 2015-4-15
	 * @param payOrder
	 * @return
	 */
	public List<OrderInfo> noReceiveOrderByUser(String loginName,String shippingStatus);
	
	/**
	 * @描述 用户的订单	
	 * @author karhs
	 * @date 2015-4-15
	 * @param payOrder
	 * @return
	 * @throws Exception 
	 */
	public Pager PayOrerByUser(PayOrder payOrder,int pageStart,int pageSize) throws Exception;
	
	/**
	 * 用户的所有已购买订单
	 */
	public List<PayOrder> queryAllPayOrderByUser(Member member);
	
	/**
	 * @描述 用户下单到收货的全部订单数量
	 * @author karhs
	 * 		   Silver 修改
	 * @date 2015-4-15
	 * @param member
	 * @return
	 */
	public Long TotalOrder(Member member,PayOrder payOrder);
	
	/**
	 * @描述 用户分销的全部清单
	 * @author karhs
	 * @date 2015-4-15
	 * @param member
	 * @return
	 */
	public Pager shareOrderInfoByFather(Member member,int pageStart,int pageSize);
	
	/**
	 * 用户分销的全部订单
	 * @param member
	 * @param pageStart
	 * @param pageSize
	 * @return
	 */
	public Pager sharePayOrderByFather(List<Double> rateList,Member member,int pageStart,int pageSize);
	
	/**
	 * 查询用户的三级客户订单
	 * @param member
	 * @return
	 */
	public Map<String,List<PayOrder>> queryMemberThreeCosTomer(Member member);
		
	/**
	 * @描述 用户批量确认收货
	 * @author karhs
	 * @date 2015-4-27
	 * @param orderNum
	 */
	public void moreReceiveOrderByUser(String orderNum);
	
	/**
	 * @描述 查询清单商品的属性
	 * @author karhs
	 * @date 2015-5-25
	 * @param orderInfos
	 * @return
	 */
	public List<ProductAttr> queryProductAttr(List<OrderInfo> orderInfos);
	
	public Pager retrieveAllPayOrders(int pageStart,int pageSize);
	
	public Pager retrievePayOrdersPager(int pageSize, int pageNo);
	
	/**
	 * 导出订单
	 * */
	public List<PayOrder> exportOrder();
	
	/**
	 * 支付完成返回记录数据
	 * @param payOrderNum
	 */
	public void finishTrade(String payOrderNum);
	
	public Double findAgentWork(Member member, Date begin, Date end, int pageSize, int pageNo);
	
	public Double findAgentBonus(Member member);

	/**
	 * 查找区域代理订单
	 * @param agent
	 * @param begin
	 * @param end
	 * @param pageSize
	 * @param pageNo
	 * @return
	 */
	public Pager findMyAgentOrder(Member member, Date begin, Date end, int pageSize, int pageNo);
	
	/**
	 * 计算区域代理人数
	 * @param member
	 * @return
	 */
	public int countMyTeamByAgent(Member member);
	
	
	/**
	 * 根据订单号查代理商订单
	 * */
	public AgentOrder queryAgentByOrderNum(String payOrderNum);
	/**
	 * 根据订单号查订单
	 * */
	public PayOrder queryByOrderNum(String payOrderNum);
	
	 /**
     * @param pageSize
     * 			当前分页大小
     * @param pageNo
     * 			当前页
     * @param object
     * 			查找的对象
     * @param orderAttr
     * 			排序字段
     * @param orderType
     * 			排序规则（逆序：DESC， 循序：ESC）
     * @param status
     * 			状态（1：启用， 0：禁用）
     * @param startTime
	 *            开始时间
	 * @param endTime
	 *            截止时间     
	 * @return
	 * 			pager
	 * 		分页查找订单信息
     */
	public Pager retrievePayOrdersPager(int pageSize, int pageNo, final PayOrder payOrder, String orderAttr,String orderType, String status, String startTime, String endTime);
	
}