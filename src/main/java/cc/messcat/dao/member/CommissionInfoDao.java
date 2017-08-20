/*
 * CommissionInfoDao.java
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

import cc.messcat.bases.BaseDao;
import cc.messcat.entity.CommissionInfo;
import cc.messcat.entity.Member;
import cc.messcat.entity.PayOrder;
import cc.modules.commons.Pager;

public interface CommissionInfoDao extends BaseDao{
	
	public Double findMemberBonus(Member member);
	
	public void update(CommissionInfo commissionInfo);
	
	public void delete(CommissionInfo commissionInfo);
	
	public boolean findByPayOrder(PayOrder payOrder);
	
	public CommissionInfo get(Long id);
	
	/**
	 * 查询该用户的所有佣金记录(分页)
	 * @param pageStart
	 * @param pageSize
	 * @param member
	 * @return
	 */
	public Pager queryAllCommission(int pageStart,int pageSize,Member member);
	
	/**
	 * 查询历史累计提现
	 * @param member
	 * @return
	 */
	public Double queryTotalCashInfo(Member member);

	/**
	 * 提现 信息记录明细(商家和用户都可以查)
	 * @param member
	 * @param pageStart
	 * @param pageSize
	 * @return
	 */
	public Pager queryCashInfosByUser(Member member,int pageStart,int pageSize);

	public Pager getPager(int pageSize, int pageNo);
	
	
	public Pager retrieveCommissionInfos(int pageSize, int pageNo);
	
	public Pager queryAllUserByCondition(int pageSize, int pageNo,Double conditionSend);
	
	public Pager queryAllCommissionByMember(int pageSize, int pageNo,Member member);

	public Double findAgentCommissionInfo(Member member);
	
}