/*
 * RechargeInfoDao.java
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
package cc.messcat.dao.member;

import cc.modules.commons.Pager;
import cc.messcat.bases.BaseDao;
import cc.messcat.entity.Member;
import cc.messcat.entity.RechargeInfo;

public interface RechargeInfoDao extends BaseDao{
	
	public void update(RechargeInfo rechargeInfo);
	
	public RechargeInfo get(Long id);
	
	/**
	 * 查询历史累计佣金余额
	 * @param member
	 * @return
	 */
	public Double queryTotalRechargeInfo(Member member);
	
	public Pager getPager(int pageSize, int pageNo);
	
}