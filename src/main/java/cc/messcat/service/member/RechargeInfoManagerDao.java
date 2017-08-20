/*
 * RechargeInfoManagerDao.java
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
package cc.messcat.service.member;

import java.util.List;
import cc.modules.commons.Pager;
import cc.messcat.entity.Member;
import cc.messcat.entity.RechargeInfo;

public interface RechargeInfoManagerDao {

	public void addRechargeInfo(RechargeInfo rechargeInfo);
	
	public void modifyRechargeInfo(RechargeInfo rechargeInfo);
	
	public void removeRechargeInfo(Long id);
	
	public RechargeInfo retrieveRechargeInfo(Long id);
		
	/**
	 * @描述 商家查询单个用户所有余额充值记录(单个用户的列表分页)
	 * @author karhs
	 * @date 2015-4-23
	 * @param pageStart
	 * @param pageSize
	 * @param member
	 * @return
	 */
	public Pager retrieveAllRechargeInfos(int pageStart,int pageSize,Member member);
	
	/**
	 * 查询历史累计佣金余额
	 * @param member
	 * @return
	 */
	public Double queryTotalCommission(Member member);
	
	
	public Pager retrieveRechargeInfosPager(int pageSize, int pageNo);
	
}