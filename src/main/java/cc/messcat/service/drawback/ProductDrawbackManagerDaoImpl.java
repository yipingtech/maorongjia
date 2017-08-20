/*
 * ProductDrawbackManagerDaoImpl.java
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

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cc.messcat.bases.BaseManagerDaoImpl;
import cc.messcat.entity.CommissionInfo;
import cc.messcat.entity.Member;
import cc.messcat.entity.OrderInfo;
import cc.messcat.entity.PayOrder;
import cc.messcat.entity.ProductDrawback;
import cc.modules.commons.Pager;
import cc.modules.util.CalendarHelper;

public class ProductDrawbackManagerDaoImpl extends BaseManagerDaoImpl implements ProductDrawbackManagerDao {
	

	//删除退款根据id
	public void removeProductDrawback(Long id) {
		Map<String,Object> props = new HashMap<String, Object>();
		Map<String,Object> attrs = new HashMap<String, Object>();
		props.put("status", "0");
		attrs.put("id", id);
		productDrawbackDao.update(ProductDrawback.class, props, attrs);
	}
	
	//查看申请退款订单详情根据id
	public ProductDrawback retrieveProductDrawback(Long id) {
		return (ProductDrawback) this.productDrawbackDao.get(id);
	}
	
	//查看申请退款订单详情根据清单号
	public ProductDrawback DrawAddressByOrderInfoNum(ProductDrawback productDrawback) {
		Map<String,Object> attrs = new HashMap<String, Object>();
		attrs.put("status","1");
		attrs.put("orderNum",productDrawback.getOrderNum());
		return this.productDrawbackDao.query(ProductDrawback.class, attrs);
	}

	//商家需要审核的全部订单
	public Pager allDrawBackByProducter(int pageStart,int pageSize){
		return productDrawbackDao.queryAllDrawBackByProduct(pageStart,pageSize);
	}
	
	//商家已经退款或换货的全部订单
	public Pager allPassDrawBackByProducter(int pageStart,int pageSize){
		return productDrawbackDao.queryAllPassDrawBackByProduct(pageStart,pageSize);
	}
	
	//用户已经收到退款或换货的全部订单
	public Pager allPassDrawBackByUser(int pageStart,int pageSize,String memberId){
		List<ProductDrawback> list = productDrawbackDao.queryAllPassDrawBackByUser(memberId);	
		return new Pager(pageSize, pageStart, list.size(), list);
	}
	
	//用户申请退货
	public void applyDrawBack(ProductDrawback productDrawback,OrderInfo orderInfo){
		orderInfo=orderInfoDao.get(orderInfo.getId());
		productDrawback.setProductId(orderInfo.getProduct());
		//productDrawback.setMemberId(orderInfo.getMember().getLoginName());   //获取用户微信号
		productDrawback.setApplyTime(new Date());
		productDrawback.setOrderNum(orderInfo.getOrderinfoNum());            //清单号
		productDrawback.setReturnStatus("0");                                //申请中...
		productDrawback.setStatus("1");
		Map<String,Object> props1 = new HashMap<String, Object>();
		Map<String,Object> attrs1 = new HashMap<String, Object>();
	
		attrs1.put("id", orderInfo.getId());                                //修改清单的商品状态
		props1.put("productStatus", "0");                                   //修改清单商品为申请退货中
		attrs1.put("status", "1");
		productDrawbackDao.save(productDrawback);
		productDrawbackDao.update(OrderInfo.class, props1, attrs1);	
	}
	
	//商家审核通过或不通过或退款还是换货
	public void passDrawBack(ProductDrawback productDrawback,String status){
		if(status=="1"){
			//审核通过
			productDrawback.setAuditTime(new Date());
			if(productDrawback.getReturnStatus().equals("1")){
				//申请退货通过
				productDrawback.setReturnStatus("2");
				PayOrder p = new PayOrder();
				//根据退换表查订单
				Map<String,Object> attrs = new HashMap<String, Object>();
				attrs.put("orderNum", productDrawback.getOrderNum());   
				p = this.payOrderDao.query(PayOrder.class, attrs);
				//根据订单查佣金表
				List<CommissionInfo> lc = new ArrayList<CommissionInfo>();
				attrs.clear();
				attrs.put("payOrder.id",p.getId());
				attrs.put("status", "1");
				System.out.println(p.getId());
				//删除对应佣金
				try {
					//若此订单不存在佣金，则跳过
					lc = this.commissionInfoDao.queryList(CommissionInfo.class, "id", "desc", attrs);	
					for(int i = 0;i<lc.size();i++){
						lc.get(i).setStatus("0");
						this.commissionInfoDao.update(lc.get(i));
						//在用户佣金数据中减去这一部分
						Map<String,Object> param = new HashMap<String, Object>();
						param.put("id", lc.get(i).getMemberId().getId());
						Member member = this.memberDao.query(Member.class, param);
						member.setCommission(member.getCommission()-lc.get(i).getCommission());
						member.setCommissionLine(member.getCommissionLine()-p.getProductAmount());
						this.memberDao.update(member);
					}
				} catch (Exception e) {
					//e.printStackTrace();
				}
			}else if(productDrawback.getReturnStatus().equals("4")){
				//申请换货通过
				productDrawback.setReturnStatus("5");
			}
			this.productDrawbackDao.update(productDrawback);
		}else if(status=="2"){
			//审核不通过
			productDrawback.setAuditTime(new Date());
			if(productDrawback.getReturnStatus().equals("1")){
				//申请退货不通过
				productDrawback.setReturnStatus("3");
			}else if(productDrawback.getReturnStatus().equals("4")){
				//申请换货不通过
				productDrawback.setReturnStatus("6");
			}
			this.productDrawbackDao.update(productDrawback);
		}
	}
	
	//模糊查询申请的订单(退货审核表)
	@Override
	public Pager queryLikeDrawBack(int pageNo, int pageSize,ProductDrawback productDrawback,String flag,String dateStr) {
		if (null==productDrawback) {
			if (dateStr.equals("day")) {
				return productDrawbackDao.queryDrawByDay(flag,pageNo,pageSize);
			}else if (dateStr.equals("week")) {
				return queryDrawByWeekAndMonth(pageNo, pageSize, flag,dateStr);
			}else if (dateStr.equals("month")) {
				return queryDrawByWeekAndMonth(pageNo, pageSize, flag,dateStr);
			}
			return allDrawBackByProducter(pageNo,pageSize);
		} else {
			if (productDrawback.getOrderNum().equals("")&&productDrawback.getMemberId().equals("")&&productDrawback.getReturnStatus().equals("")) {
				return allDrawBackByProducter(pageNo,pageSize);
			} else {
				return productDrawbackDao.queryLikeDrawBack(productDrawback,flag,pageNo,pageSize);
			}
		}		
	}
	
	//模糊查询申请的订单(审核后退货表)
		@Override
		public Pager queryLikeDrawBack1(int pageNo, int pageSize,ProductDrawback productDrawback,String flag,String dateStr) {
			System.out.println();
			if (null==productDrawback) {
				if (dateStr.equals("day")) {
					return productDrawbackDao.queryDrawByDay(flag,pageNo,pageSize);
				}else if (dateStr.equals("week")) {
					return queryDrawByWeekAndMonth(pageNo, pageSize,flag,dateStr);
				}else if (dateStr.equals("month")) {
					return queryDrawByWeekAndMonth(pageNo, pageSize,flag,dateStr);
				}
				return allPassDrawBackByProducter(pageNo,pageSize);
			} else {
				if (productDrawback.getOrderNum().equals("")&&productDrawback.getMemberId().equals("")&&productDrawback.getReturnStatus().equals("")) {
					return allPassDrawBackByProducter(pageNo,pageSize);
				} else {
					return productDrawbackDao.queryLikeDrawBack(productDrawback,flag,pageNo,pageSize);
				}
			}		
		}

	//商家查询一周内、一个月内退货审核的订单
	public Pager queryDrawByWeekAndMonth(int pageStart,int pageSize,String flag,String dateStr){
		CalendarHelper calendarHelper = new CalendarHelper();
		List<String> list = calendarHelper.calendDate(dateStr);
		return productDrawbackDao.queryDrawByWeekAndMonth(list.get(0),list.get(1),flag,pageStart,pageSize);		
	}
	
	public Pager retrieveProductDrawbacksPager(int pageSize, int pageNo) {
		return this.productDrawbackDao.getPager(pageSize, pageNo);
	}

	@Override
	public void addProductDrawback(ProductDrawback productDrawback) {
		this.productDrawbackDao.save(productDrawback);
	}

}