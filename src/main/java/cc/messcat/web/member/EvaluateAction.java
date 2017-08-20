/*
 * CommissionInfoAction.java
 * 
 * Create by MCGT
 * 
 * Create time 2015-04-22
 * 
 * Version 1.0
 * 
 * Copyright 2013 Messcat, Inc. All rights reserved.
 * 
 */
package cc.messcat.web.member;

import java.util.List;

import cc.messcat.entity.Evaluate;
import cc.modules.commons.PageAction;


public class EvaluateAction extends PageAction {

	private static final long serialVersionUID = 1L;
	private Long evaluatesId;
	private Evaluate evaluate;
	private List<Evaluate> evaluates;
	
	/**
	 * 查询所有评论
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String queryAllEvaluates() throws Exception {
		try {
			super.pager = this.evaluateManagerDao.queryEvaluates(pageNo, pageSize, null);
			this.evaluates = super.pager.getResultList();
		} catch (Exception ex) {
			addActionMessage("Load page error!");
		}
		return "query_evaluate";
	}

	/**
	 * 商家回复评论
	 * @return
	 * @throws Exception
	 */
	public String replyEvaluates() throws Exception {
		try {
			evaluateManagerDao.updateEvaluatesReply(evaluate);
		} catch (Exception ex) {
			addActionMessage("Load page error!");
		}
		return this.queryAllEvaluates();
	}
	
	
	/**
	 * 商家删除评论
	 * @return
	 * @throws Exception
	 */
	public String deleteEvaluates() throws Exception {
		try {
			evaluateManagerDao.deleteEvaluates(evaluatesId);
		} catch (Exception ex) {
			addActionMessage("Load page error!");
		}
		return this.queryAllEvaluates();
	}

	public Long getEvaluatesId() {
		return evaluatesId;
	}

	public void setEvaluatesId(Long evaluatesId) {
		this.evaluatesId = evaluatesId;
	}

	public Evaluate getEvaluate() {
		return evaluate;
	}

	public void setEvaluate(Evaluate evaluate) {
		this.evaluate = evaluate;
	}

	public List<Evaluate> getEvaluates() {
		return evaluates;
	}

	public void setEvaluates(List<Evaluate> evaluates) {
		this.evaluates = evaluates;
	}

	

}