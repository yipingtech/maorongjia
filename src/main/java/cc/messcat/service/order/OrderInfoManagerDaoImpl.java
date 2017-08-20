/*
 * OrderInfoManagerDaoImpl.java
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
package cc.messcat.service.order;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cc.modules.commons.Pager;
import cc.modules.util.ObjValid;
import cc.messcat.entity.CartInfo;
import cc.messcat.entity.OrderInfo;
import cc.messcat.entity.PayOrder;
import cc.messcat.bases.BaseManagerDaoImpl;

public class OrderInfoManagerDaoImpl extends BaseManagerDaoImpl implements OrderInfoManagerDao {

	@Override
	public void addOrderInfo(OrderInfo orderInfo) {
		this.orderInfoDao.save(orderInfo);
	}
	
	public void modifyOrderInfo(OrderInfo orderInfo) {
		this.orderInfoDao.update(orderInfo);
	}
	
	public void removeOrderInfo(OrderInfo orderInfo) {
		this.orderInfoDao.delete(orderInfo);
	}

	public void removeOrderInfo(Long id) {
		this.orderInfoDao.delete(id);
	}
	
	public OrderInfo retrieveOrderInfo(Long id) {
		return (OrderInfo) this.orderInfoDao.get(id);
	}

	@SuppressWarnings("unchecked")
	public List retrieveAllOrderInfos() {
		return this.orderInfoDao.findAll();
	}
	
	public Pager retrieveOrderInfosPager(int pageSize, int pageNo) {
		return this.orderInfoDao.getPager(pageSize, pageNo);
	}
	
	
	/**
	 * 创建清单列表
	 * @param cartIdList（购物车id）
	 * @return
	 */
	public List<OrderInfo> createOrderInfos(List<String> cartIdList){
		List<OrderInfo> orderInfos = new ArrayList<OrderInfo>();
		List<String> listOrderNum = this.createOrderNum(cartIdList.size());
		for (int i = 0; i < cartIdList.size(); i++) {
			CartInfo cartInfo = this.cartInfoDao.get(Long.parseLong(cartIdList.get(i)));
			if (ObjValid.isValid(cartInfo)) {
				OrderInfo orderInfo = new OrderInfo();
				orderInfo.setAmount(cartInfo.getBuyAmount());
				orderInfo.setCreateTime(new Date());
				orderInfo.setMember(cartInfo.getMember());
				orderInfo.setOrderinfoNum(listOrderNum.get(i));
				orderInfo.setOrderStatus("0");
				orderInfo.setPrice(cartInfo.getProductPrice());
				orderInfo.setTotalPrice(cartInfo.getProductTotal());
				orderInfo.setProduct(cartInfo.getProduct());
				orderInfo.setProductAttrIds(cartInfo.getProductAttrIds());
				orderInfo.setStatus("1");
				orderInfos.add(orderInfo);
			}
		}
		return orderInfos;
	}
	
	/**
	 * 生成一串唯一字符串列表
	 * 生成订单号（包括订单号和清单号）
	 * */
	public List<String> createOrderNum(int num) {
		List<String> orderNumList = new ArrayList<String>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		int i;
		for(i = 1; i <= num; i++) {
			orderNumList.add(sdf.format(new Date())+String.format("%1$04d",i));
		}
		return orderNumList;
	}

	@SuppressWarnings("unchecked")
	public List<OrderInfo> searchByPayOrder(PayOrder payOrder){
		Map<String,Object> attrs = new HashMap<String, Object>();
		attrs.put("payOrder", payOrder);
		List<OrderInfo> orderInfos = this.orderInfoDao.queryList(OrderInfo.class, "id", "desc", attrs);
		return orderInfos;
	}
	
}