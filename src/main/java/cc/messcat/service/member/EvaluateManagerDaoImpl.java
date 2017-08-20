/*
 * EvaluateManagerDaoImpl.java
 * 
 * Create by MCGT
 * 
 * Create time 2015-04-07
 * 
 * Version 1.0
 * 
 * Copyright 2013 Messcat, Inc. All rights reserved.
 * 
 */
package cc.messcat.service.member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.record.formula.functions.Date;

import cc.modules.commons.Pager;
import cc.messcat.entity.Evaluate;
import cc.messcat.entity.McProductInfo;
import cc.messcat.entity.OrderInfo;
import cc.messcat.bases.BaseManagerDaoImpl;

@SuppressWarnings("serial")
public class EvaluateManagerDaoImpl extends BaseManagerDaoImpl implements EvaluateManagerDao {

	public void addEvaluate(Evaluate evaluate, OrderInfo orderInfo) {
		this.evaluateDao.save(evaluate);
		orderInfo.setEvaluateStatus("1");
		this.orderInfoDao.update(orderInfo);
	}
	
	public void modifyEvaluate(Evaluate evaluate) {
		this.evaluateDao.update(evaluate);
	}
	
	public void removeEvaluate(Evaluate evaluate) {
		this.evaluateDao.delete(evaluate);
	}

	public void removeEvaluate(Long id) {
		this.evaluateDao.delete(id);
	}
	
	public Evaluate retrieveEvaluate(Long id) {
		return (Evaluate) this.evaluateDao.get(id);
	}

	@SuppressWarnings("unchecked")
	public List retrieveAllEvaluates() {
		return this.evaluateDao.findAll();
	}
	
	/**
	 * 查询当个商品的所有评论或者所有商品的评论
	 */
	@SuppressWarnings("unchecked")
	public Pager queryEvaluates(int pageStart,int pageSize,Long productId){
		Map<String, Object> attrs = new HashMap<String, Object>();
		if (null!=productId&&!"".equals(productId)) {
			attrs.put("product", mcProductInfoDao.get(productId));
		}		
		attrs.put("status", "1");
		return new Pager(pageSize, pageStart, Integer.parseInt(String.valueOf(evaluateDao.queryTotalRecord(Evaluate.class, attrs))),
				evaluateDao.queryList(Evaluate.class,(pageStart-1)*pageSize,pageSize, "id", "desc", attrs));
	}
	
	/**
	 * 商家回复评论
	 */
	public String updateEvaluatesReply(Evaluate evaluate){
		Map<String, Object> attrs = new HashMap<String, Object>();
		Map<String, Object> props = new HashMap<String, Object>();
		evaluate.setReplyTime(new java.util.Date());
		attrs.put("id", evaluate.getId());
		attrs.put("status", "1");
		props.put("replyTime",evaluate.getReplyTime());
		props.put("evaluateReply", evaluate.getEvaluateReply());
		evaluateDao.update(Evaluate.class, props, attrs);
		return "商家回复成功";
	}
	
	/**
	 * 商家删除评论
	 * @param evaluateId
	 * @return
	 */
	public String deleteEvaluates(Long evaluateId){
		Map<String, Object> attrs = new HashMap<String, Object>();
		Map<String, Object> props = new HashMap<String, Object>();
		attrs.put("id", evaluateId);
		props.put("status", "0");
		evaluateDao.update(Evaluate.class, props, attrs);
		return "商家删除成功";
	}
	
	public Pager retrieveEvaluatesPager(int pageSize, int pageNo) {
		return this.evaluateDao.getPager(pageSize, pageNo);
	}

	public Pager searchByLikeCondition(Evaluate condition, int pageSize, int pageNo){
		return this.evaluateDao.searchByLikeCondition(condition, pageSize, pageNo);
	}
	
	
	
	public double countLevelAvg(McProductInfo condition){
		return this.evaluateDao.countLevelAvg(condition);
	}
	
	public int countAmount(McProductInfo condition){
		return this.evaluateDao.countAmount(condition);
	}
	
}