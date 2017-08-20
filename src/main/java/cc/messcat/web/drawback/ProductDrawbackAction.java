/*
 * ProductDrawbackAction.java
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
package cc.messcat.web.drawback;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cc.messcat.entity.OrderInfo;
import cc.messcat.entity.PayOrder;
import cc.messcat.entity.ProductAttr;
import cc.messcat.entity.ProductDrawback;
import cc.modules.commons.PageAction;

public class ProductDrawbackAction extends PageAction {
	
	private static Logger log = LoggerFactory.getLogger(ProductDrawbackAction.class); 
	
	private Long id;
	private Long productId;
	private String flag;
	private String dateStr;
	private ProductDrawback productDrawback;	
	private List<ProductDrawback> productDrawbacks;
	private String orderInfoNum;
	private PayOrder payOrder;
	private List<OrderInfo> orderInfoList;
	private List<ProductAttr> productAttrs;
	
	public String retrieveProductDrawbackById() throws Exception {
		try {
			this.productDrawback = this.productDrawbackManagerDao.retrieveProductDrawback(id);
		} catch (Exception ex) {
			addActionMessage("Load page error!");
		}
		return "editPage";
	}
	
	public String newPage() throws Exception {
		return "newPage";
	}
	
	//查看申请退款商品详情根据id
	public String queryDrawBackIsProducter(){
		try {
			this.productDrawback = this.productDrawbackManagerDao.retrieveProductDrawback(id);
			productAttrs=payOrderManagerDao.queryProductAttr(payOrderManagerDao.queryOneOrderInfo(productDrawback.getOrderNum()));
		} catch (Exception ex) {
			addActionMessage("Load page error!");
		}
		return "viewPage";	
	}
	
	//查看申请退款清单详情根据订单号
	public String queryDrawBackIsOrder(){
		try {
			orderInfoList = payOrderManagerDao.queryOneOrderInfo(orderInfoNum);
			payOrder = orderInfoList.get(0).getPayOrder();
			addActionMessage("查看申请退款订单详情根据订单号 successfully!");
		} catch (Exception ex) {
		this.addActionError(ex.getMessage());
			addActionMessage("Delete fail!");
		}
		return "payOrder";	
	}
			
	//用户已经收到退款或换货的全部订单
	public String allPassDrawBackByUser(){
		try {
			super.pager=this.productDrawbackManagerDao.allPassDrawBackByUser(pageNo, pageSize, productDrawback.getMemberId());
			this.productDrawbacks=super.pager.getResultList();
			addActionMessage("Delete successfully!");
		} catch (Exception ex) {
			this.addActionError(ex.getMessage());
			addActionMessage("Delete fail!");
		}
		return "edit_success";	
	}

	//商家审核通过
	public String passDrawBack(){
		try {
			productDrawback = this.productDrawbackManagerDao.retrieveProductDrawback(id);
			this.productDrawbackManagerDao.passDrawBack(productDrawback,"1");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "apply_redirect";	
	}
		
	//商家审核不通过
	public String editDrawBackStatus(){
		try {
			productDrawback = this.productDrawbackManagerDao.retrieveProductDrawback(id);
			productDrawbackManagerDao.passDrawBack(productDrawback,"2");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "apply_redirect";	
	}
	
	//商家审核通过,商家确认是退款
	/*public String passDrawBackIsMoney(){
		try {
			productDrawbackManagerDao.passDrawBack(productDrawback,"3");
			addActionMessage("确认是退款 successfully!");
		} catch (Exception ex) {
			this.addActionError(ex.getMessage());
			addActionMessage("确认是退款 fail!");
		}
		return "apply_redirect";	
	}*/
	
	//模糊查询申请订单(申请或通过),商家需要审核的全部订单
	public String queryLikeDrawBackByApply(){
		try {
			pager = productDrawbackManagerDao.queryLikeDrawBack(pageNo, pageSize,productDrawback,flag,dateStr);
			productDrawbacks=pager.getResultList();
			addActionMessage("模糊查询申请订单 successfully!");
		} catch (Exception ex) {
			this.addActionError(ex.getMessage());
			addActionMessage("模糊查询申请订单 fail!");
		}
		return "applyPage";	
	}
	
	//模糊查询申请订单(已退款或换货),商家已经退款或换货的全部订单
	public String queryLikeDrawBackByDraw(){
		try {
			log.info(flag+"flag");
			pager = productDrawbackManagerDao.queryLikeDrawBack1(pageNo, pageSize,productDrawback,flag,dateStr);
			productDrawbacks=pager.getResultList();
			addActionMessage("模糊查询申请订单 successfully!");
		} catch (Exception ex) {
			this.addActionError(ex.getMessage());
			addActionMessage("模糊查询申请订单 fail!");
		}
		return "drawPage";	
	}

	//删除退款订单根据id
	public String delProductDrawback() throws Exception {
		try {
			this.productDrawbackManagerDao.removeProductDrawback(this.id);
			addActionMessage("Delete successfully!");
		} catch (Exception ex) {
			this.addActionError(ex.getMessage());
			addActionMessage("Delete fail!");
		}
		return "draw_redirect";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ProductDrawback getProductDrawback() {
		return productDrawback;
	}

	public void setProductDrawback(ProductDrawback productDrawback) {
		this.productDrawback = productDrawback;
	}

	public List<ProductDrawback> getProductDrawbacks() {
		return productDrawbacks;
	}

	public void setProductDrawbacks(
			List<ProductDrawback> productDrawbacks) {
		this.productDrawbacks = productDrawbacks;
	}

	public PayOrder getPayOrder() {
		return payOrder;
	}

	public void setPayOrder(PayOrder payOrder) {
		this.payOrder = payOrder;
	}

	public List<OrderInfo> getOrderInfoList() {
		return orderInfoList;
	}

	public void setOrderInfoList(List<OrderInfo> orderInfoList) {
		this.orderInfoList = orderInfoList;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getDateStr() {
		return dateStr;
	}

	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
	}

	public String getOrderInfoNum() {
		return orderInfoNum;
	}

	public void setOrderInfoNum(String orderInfoNum) {
		this.orderInfoNum = orderInfoNum;
	}

	public List<ProductAttr> getProductAttrs() {
		return productAttrs;
	}

	public void setProductAttrs(List<ProductAttr> productAttrs) {
		this.productAttrs = productAttrs;
	}

}