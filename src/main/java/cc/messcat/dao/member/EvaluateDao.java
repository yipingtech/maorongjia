/*
 * EvaluateDao.java
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
package cc.messcat.dao.member;

import java.util.List;
import cc.modules.commons.Pager;
import cc.messcat.bases.BaseDao;
import cc.messcat.entity.Evaluate;
import cc.messcat.entity.McProductInfo;

public interface EvaluateDao extends BaseDao{

	public void save(Evaluate evaluate);
	
	public void update(Evaluate evaluate);
	
	public void delete(Evaluate evaluate);
	
	public void delete(Long id);
	
	public Evaluate get(Long id);
	
	@SuppressWarnings("unchecked")
	public List findAll();
	
	public Pager getPager(int pageSize, int pageNo);
	
	public Pager searchByLikeCondition(Evaluate condition, int pageSize, int pageNo);
	
	public double countLevelAvg(McProductInfo condition);
	
	public int countAmount(McProductInfo condition);
	
}