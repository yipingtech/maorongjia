/*
 * OrderInfoAction.java
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
package cc.messcat.web.order;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cc.messcat.entity.OrderInfo;
import cc.modules.commons.PageAction;

public class OrderInfoAction extends PageAction {
	
	private Long id;
	
	private OrderInfo orderInfo;
	
	private List<OrderInfo> orderInfos;
	private String proAttrIds;
	
	private static Logger log = LoggerFactory.getLogger(OrderInfoAction.class); 
	

	public String addOrder(){
		log.info(proAttrIds);
		return "showOrder";
	}
	@SuppressWarnings("unchecked")
	public String retrieveAllOrderInfos() throws Exception {
		try {
			super.pager = this.orderInfoManagerDao.retrieveOrderInfosPager(pageSize, pageNo);
			this.orderInfos = super.pager.getResultList();
		} catch (Exception ex) {
			addActionMessage("Load page error!");
		}
		return "success";
	}
	
	public String retrieveOrderInfoById() throws Exception {
		try {
			this.orderInfo = this.orderInfoManagerDao.retrieveOrderInfo(id);
		} catch (Exception ex) {
			addActionMessage("Load page error!");
		}
		return "editPage";
	}

	public String newPage() throws Exception {
		return "newPage";
	}
	
	public String viewPage() throws Exception {
		try {
			this.orderInfo = this.orderInfoManagerDao.retrieveOrderInfo(id);
		} catch (Exception ex) {
			addActionMessage("Load page error!");
		}
		return "viewPage";
	}
	
	public String newOrderInfo() throws Exception {
		try {
			this.orderInfoManagerDao.addOrderInfo(this.orderInfo);
			addActionMessage("New successfully!");
		} catch (Exception ex) {
			this.addActionError(ex.getMessage());
			addActionMessage("New fail!");
		}
		return "edit_success";
	}
	
	public String editOrderInfo() throws Exception {
		try {
			OrderInfo orderInfo0 = this.orderInfoManagerDao.retrieveOrderInfo(this.id);
			orderInfo0.setOrderinfoNum(this.orderInfo.getOrderinfoNum());
			orderInfo0.setMember(this.orderInfo.getMember());
			orderInfo0.setProduct(this.orderInfo.getProduct());
			orderInfo0.setPrice(this.orderInfo.getPrice());
			orderInfo0.setAmount(this.orderInfo.getAmount());
			orderInfo0.setComments(this.orderInfo.getComments());
			orderInfo0.setCreateTime(this.orderInfo.getCreateTime());
			orderInfo0.setOrderTime(this.orderInfo.getOrderTime());
			orderInfo0.setPayTime(this.orderInfo.getPayTime());
			orderInfo0.setPayOrder(this.orderInfo.getPayOrder());
			orderInfo0.setOrderStatus(this.orderInfo.getOrderStatus());
			orderInfo0.setStatus(this.orderInfo.getStatus());

			this.orderInfoManagerDao.modifyOrderInfo(orderInfo0);
			addActionMessage("Update successfully!");
		} catch (Exception ex) {
			this.addActionError(ex.getMessage());
			addActionMessage("Update fail!");
		}
		return "edit_success";
	}
	
	public String delOrderInfo() throws Exception {
		try {
			this.orderInfoManagerDao.removeOrderInfo(this.id);
			addActionMessage("Delete successfully!");
		} catch (Exception ex) {
			this.addActionError(ex.getMessage());
			addActionMessage("Delete fail!");
		}
		return "edit_success";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public OrderInfo getOrderInfo() {
		return orderInfo;
	}

	public void setOrderInfo(OrderInfo orderInfo) {
		this.orderInfo = orderInfo;
	}

	public List<OrderInfo> getOrderInfos() {
		return orderInfos;
	}

	public void setOrderInfos(
			List<OrderInfo> orderInfos) {
		this.orderInfos = orderInfos;
	}
	public String getProAttrIds() {
		return proAttrIds;
	}
	public void setProAttrIds(String proAttrIds) {
		this.proAttrIds = proAttrIds;
	}

}