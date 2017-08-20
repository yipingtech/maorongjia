/*
 * CommissionInfoManagerDao.java
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

import cc.messcat.entity.CashInfo;
import cc.messcat.entity.CommissionInfo;
import cc.messcat.entity.Member;
import cc.modules.commons.Pager;

public interface CommissionInfoManagerDao {
	/*
	 * 
	 * 查看某条提现信息的具体内容（如提现的银行卡的支行）
	 * **/
	public CashInfo findByCashInfoId(Long id);
	
	
	public Double findMemberBonus(Member member);

	public Double addCommissionInfo(CommissionInfo commissionInfo);
	
	public void modifyCommissionInfo(CommissionInfo commissionInfo);
	
	public void removeCommissionInfo(CommissionInfo commissionInfo);
	
	public void removeCommissionInfo(Long id);
	
	public CommissionInfo retrieveCommissionInfo(Long id);
	
	
	/**
	 * @描述 商家查询所有用户的佣金记录(所有用户的列表分页)
	 * @author karhs
	 * @date 2015-4-23
	 * @param pageStart
	 * @param pageSize
	 * @return
	 */
	public Pager queryAllCommissionByProducter(int pageStart,int pageSize,Member member);
	/**
	 * @描述 用户的佣金记录
	 * @author karhs
	 * @date 2015-4-23
	 * @param pageStart
	 * @param pageSize
	 * @return
	 */
	public Pager queryAllCommissionByMember(int pageStart,int pageSize,Member member);
		
	
	/**
	 * @描述 用户佣金提现信息显示
	 * @author karhs
	 * @date 2015-5-12
	 * @return
	 */
	public String predictTiem();
	
	/**
	 * @描述 用户佣金提现确认
	 * @author karhs
	 * @date 2015-5-12
	 * @param cashInfo
	 * @param member
	 * @return
	 */
	public String SureDrawCommission(CashInfo cashInfo,Member member);
	
	/**
	 * @描述 提现信息记录明细
	 * @author karhs
	 * @date 2015-5-13
	 * @param member
	 * @return
	 */
	public Pager queryCashInfosByUser(Member member,int pageStart,int pageSize);
	
	
	/**
	 * @描述 商家处理用户提现申请
	 * @author karhs
	 * @date 2015-5-13
	 * @param id
	 * @return
	 */
	public String updateCashApplyBySeller(Long id);
	
	/**
	 * 提现审核失败
	 * */
	public void failToCash(Long id);
	/**
	 * 查询历史累计提现
	 * @param member
	 * @return
	 */
	public Double queryTotalCashInfo(Member member);
	
	
	public Pager retrieveCommissionInfosPager(int pageSize, int pageNo);
	
	public Pager retrieveCommissionInfos(int pageSize, int pageNo);
	
	
	public Pager queryAllUserByCondition(int pageNo,int pageSize,Double conditionLine);


	public Double findAgentCommissionInfo(Member member);
	
}