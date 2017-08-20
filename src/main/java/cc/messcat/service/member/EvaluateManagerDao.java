/*
 * EvaluateManagerDao.java
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

import java.util.List;
import cc.modules.commons.Pager;
import cc.messcat.entity.Evaluate;
import cc.messcat.entity.McProductInfo;
import cc.messcat.entity.OrderInfo;

public interface EvaluateManagerDao {

	public void addEvaluate(Evaluate evaluate, OrderInfo orderInfo);
	
	public void modifyEvaluate(Evaluate evaluate);
	
	public void removeEvaluate(Evaluate evaluate);
	
	public void removeEvaluate(Long id);
	
	public Evaluate retrieveEvaluate(Long id);
	
	@SuppressWarnings("unchecked")
	public List retrieveAllEvaluates();
	
	public Pager retrieveEvaluatesPager(int pageSize, int pageNo);
	
	public Pager searchByLikeCondition(Evaluate condition, int pageSize, int pageNo);
	
	public double countLevelAvg(McProductInfo condition);
	
	/**
	 * 查询当个商品的所有评论
	 */
	public Pager queryEvaluates(int pageStart,int pageSize,Long productId);
	
	/**
	 * 商家回复
	 * @param evaluatesId
	 * @param evaluate
	 * @return
	 */
	public String updateEvaluatesReply(Evaluate evaluate);
	
	/**
	 * 商家删除评论
	 * @param evaluateId
	 * @return
	 */
	public String deleteEvaluates(Long evaluateId);
	
	public int countAmount(McProductInfo condition);
	
}