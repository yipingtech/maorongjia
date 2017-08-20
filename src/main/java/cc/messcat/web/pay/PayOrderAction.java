/*
 * PayOrderAction.java
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
package cc.messcat.web.pay;

import java.util.ArrayList;
import java.util.List;

import cc.messcat.entity.Member;
import cc.messcat.entity.OrderInfo;
import cc.messcat.entity.PayOrder;
import cc.messcat.entity.ProductAttr;
import cc.messcat.vo.PayOrderVo2;
import cc.modules.commons.PageAction;
import cc.modules.constants.Constants;

public class PayOrderAction extends PageAction {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String orderNum;
	private String flag;
	private String message;
	private PayOrder payOrder;
	private OrderInfo orderInfo;
	private OrderInfo orderInfo1;
	private List<PayOrder> payOrders;
	private List<OrderInfo> orderInfoList;
	private List<ProductAttr> productAttrs;
	private List<PayOrderVo2> pv2List;
	private Member member;
	private PayOrderVo2 pv;
	/**
	 * 开始时间
	 */
	private String startTime;
	/**
	 * 结束时间
	 */
	private String endTime;

	public String export() {
		super.pager = payOrderManagerDao.retrievePayOrdersPager(Integer.MAX_VALUE, pageNo,
			payOrder == null ? new PayOrder() : payOrder, "id", "DESC", Constants.ENABLE, startTime, endTime);
		this.payOrders = super.pager.getResultList();

		pv2List = new ArrayList<PayOrderVo2>();
		for (int i = 0; i < payOrders.size(); i++) {
			pv = new PayOrderVo2();
			pv.setPayTimeStr(payOrders.get(i).getPayTime());
			pv.setReceiver(payOrders.get(i).getName());
			pv.setReceiverPhone(payOrders.get(i).getPhone());
			pv.setReceiverAddress(payOrders.get(i).getAddress());
			pv.setSender("百丽春平台");
			pv.setSenderAddress("");
			pv.setSenderPhone("0755-29926111");
			pv2List.add(pv);
		}
		return "export";
	}

	// 跳转新页面
	public String newPage() throws Exception {
		return "newPage";
	}

	// 跳转到发货页面
	public String newSendProPage() throws Exception {
		return "newSendPro";
	}

	// 查询所有订单列表
	@SuppressWarnings("unchecked")
	public String retrieveAllPayOrders() throws Exception {
		try {
			super.pager = this.payOrderManagerDao.retrievePayOrdersPager(pageSize, pageNo);
			this.payOrders = super.pager.getResultList();
		} catch (Exception ex) {
			addActionMessage("Load page error!");
		}
		return "success";
	}

	// 根据id查询商品订单编辑
	public String retrievePayOrderById() throws Exception {
		try {
			// this.payOrder = this.payOrderManagerDao.retrievePayOrder(id);
			this.queryOrderAndPay();
		} catch (Exception ex) {
			addActionMessage("Load page error!");
		}
		return "editPage";
	}

	// 用户确认付款(修改支付状态，修改支付方式和使用红包)
	public String editPayType() throws Exception {
		try {
			message = payOrderManagerDao.editPayType(payOrder);
			retrieveAllPayOrders();
		} catch (Exception ex) {
			addActionMessage("Load page error!");
		}
		return "success";
	}

	// 显示商品订单详情,根据id
	public String viewPage() throws Exception {
		try {
			this.payOrder = this.payOrderManagerDao.retrievePayOrderById(id);
		} catch (Exception ex) {
			addActionMessage("Load page error!");
		}
		return "viewPage";
	}

	// 显示商品订单详情,根据订单号
	public String queryOrderAndPay() throws Exception {
		try {
			orderInfoList = payOrderManagerDao.queryPayAndOrder(payOrder);
			payOrder = orderInfoList.get(0).getPayOrder();
			productAttrs = payOrderManagerDao.queryProductAttr(orderInfoList);
		} catch (Exception ex) {
			addActionMessage("Load page error!");
		}
		return "viewPage";
	}

	// 添加新订单,同时提交清单
	public String newPayOrder() throws Exception {
		try {
			// 获取session (测试)
			// 此处应该是直接从界面获取购物车商品list，此处要修改
			orderInfo.setProduct(mcProductInfoManagerDao.retrieveMcProductInfo((long) 11));
			orderInfoList = new ArrayList<OrderInfo>();
			orderInfoList.add(orderInfo);
			orderInfo1.setProduct(mcProductInfoManagerDao.retrieveMcProductInfo((long) 16));
			orderInfoList.add(orderInfo1);
			payOrder.setMember(memberManagerDao.retrieveMember((long) 44));
			this.payOrderManagerDao.addPayOrder(payOrder, orderInfoList);
			addActionMessage("New successfully!");
		} catch (Exception ex) {
			this.addActionError(ex.getMessage());
			addActionMessage("New fail!");
		}
		return "edit_success";
	}

	// 商家查询所有订单（排除掉删除态的数据）,根据条件进行模糊查询（订单列表里）
	@SuppressWarnings("unchecked")
	public String queryAllOrderPay() throws Exception {
		try {
			super.pager = payOrderManagerDao.retrievePayOrdersPager(pageSize, pageNo, payOrder == null ? new PayOrder() : payOrder,
				"id", "DESC", Constants.ENABLE, startTime, endTime);
			this.payOrders = super.pager.getResultList();
		} catch (Exception ex) {
			ex.printStackTrace();
			addActionMessage("Load page error!");
		}
		return "success";
	}

	// 查询商家已经发货到签收的所有订单,根据条件进行模糊查询
	public String queryAllSendToReceiveOrder() throws Exception {
		try {
			super.pager = payOrderManagerDao.queryByAttrs1(payOrder, pageNo, pageSize, flag);
			this.payOrders = super.pager.getResultList();
		} catch (Exception ex) {
			addActionMessage("Load page error!");
		}
		return "sendPage";
	}

	// 根据条件进行模糊查询(订单回收站)
	public String queryByLikeCycle() throws Exception {
		try {
			super.pager = payOrderManagerDao.queryByAttrsCycle(payOrder, pageNo, pageSize);
			this.payOrders = super.pager.getResultList();
		} catch (Exception ex) {
			addActionMessage("Load page error!");
			ex.printStackTrace();
		}
		return "cyclePage";
	}

	// 商家确认发货(修改发货状态)
	public String editSendShippingType() throws Exception {
		try {
			message = payOrderManagerDao.editSendShippingType(payOrder);
		} catch (Exception ex) {
			addActionMessage("Load page error!");
		}
		return "edit_success";
	}

	// 商家查看用户已成功收货列表
	@SuppressWarnings("unchecked")
	public String querySendProduct() throws Exception {
		try {
			pager = payOrderManagerDao.querySendProduct(pageNo, pageSize);
			payOrders = pager.getResultList();
		} catch (Exception ex) {
			addActionMessage("Load page error!");
		}
		return "viewPage";
	}

	// 用户确认收货(修改收货状态)
	public String editReceiveShippingType() throws Exception {
		try {
			orderInfoList = payOrderManagerDao.queryPayAndOrder(payOrder);
			payOrder = orderInfoList.get(0).getPayOrder();
		} catch (Exception ex) {
			addActionMessage("Load page error!");
		}
		return "edit_success";
	}

	// 修改订单资料
	public String editPayOrder() throws Exception {
		try {
			this.payOrderManagerDao.modifyPayOrder(payOrder);
			addActionMessage("Update successfully!");
		} catch (Exception ex) {
			this.addActionError(ex.getMessage());
			addActionMessage("Update fail!");
		}
		return "edit_success";
	}

	// 还原移除的订单（订单回收站中）
	public String returnCyclePayOrder() throws Exception {
		try {
			this.payOrderManagerDao.deletePayOrder(payOrder, "0", "1");
			addActionMessage("Update successfully!");
		} catch (Exception ex) {
			this.addActionError(ex.getMessage());
			addActionMessage("Update fail!");
		}
		return queryByLikeCycle();
	}

	// 根据id移除数据(把数据修改为删除态)
	public String delPayOrderByOrderNum() throws Exception {
		try {
			this.payOrderManagerDao.deletePayOrder(payOrder, "1", "0");
			addActionMessage("Delete successfully!");
		} catch (Exception ex) {
			this.addActionError(ex.getMessage());
			addActionMessage("Delete fail!");
		}
		return "delete_success";
	}

	// 根据id彻底删除(在订单回收站中)
	public String deletePayOrder() throws Exception {
		try {
			this.payOrderManagerDao.deletePayOrder(id);
			addActionMessage("Delete successfully!");
		} catch (Exception ex) {
			this.addActionError(ex.getMessage());
			addActionMessage("Delete fail!");
		}
		return queryByLikeCycle();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PayOrder getPayOrder() {
		return payOrder;
	}

	public void setPayOrder(PayOrder payOrder) {
		this.payOrder = payOrder;
	}

	public List<PayOrder> getPayOrders() {
		return payOrders;
	}

	public void setPayOrders(List<PayOrder> payOrders) {
		this.payOrders = payOrders;
	}

	public OrderInfo getOrderInfo() {
		return orderInfo;
	}

	public void setOrderInfo(OrderInfo orderInfo) {
		this.orderInfo = orderInfo;
	}

	public List<OrderInfo> getOrderInfoList() {
		return orderInfoList;
	}

	public void setOrderInfoList(List<OrderInfo> orderInfoList) {
		this.orderInfoList = orderInfoList;
	}

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public OrderInfo getOrderInfo1() {
		return orderInfo1;
	}

	public void setOrderInfo1(OrderInfo orderInfo1) {
		this.orderInfo1 = orderInfo1;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public List<ProductAttr> getProductAttrs() {
		return productAttrs;
	}

	public void setProductAttrs(List<ProductAttr> productAttrs) {
		this.productAttrs = productAttrs;
	}

	public List<PayOrderVo2> getPv2List() {
		return pv2List;
	}

	public void setPv2List(List<PayOrderVo2> pv2List) {
		this.pv2List = pv2List;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
}