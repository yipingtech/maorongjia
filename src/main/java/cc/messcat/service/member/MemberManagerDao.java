/*
 * MemberManagerDao.java
 * 
 * Create by MCGT
 * 
 * Create time 2015-04-21
 * 
 * Version 1.0
 * 
 * Copyright 2013 Messcat, Inc. All rights reserved.
 * 
 */
package cc.messcat.service.member;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import cc.modules.commons.Pager;
import cc.messcat.entity.AgentOrder;
import cc.messcat.entity.CashInfo;
import cc.messcat.entity.CommissionInfo;
import cc.messcat.entity.IntergralInfo;
import cc.messcat.entity.Member;
import cc.messcat.entity.ParameterSet;
import cc.messcat.entity.RechargeInfo;
import cc.messcat.entity.ReportInfo;

public interface MemberManagerDao {
	//给代理商上级发补偿
	public void sendCompensate(AgentOrder agentOrder);
	//将代理商订单状态改为已付
	public void changeAgentOrder(AgentOrder agentOrder);
	//代理商订单
	public AgentOrder agentOrder(Member AgentMember);
	/**
	 * 会员获取积分
	 * @param ruleName
	 * @param member
	 * @return
	 */
	public void becomeAgent(AgentOrder agentOrder);
	/**
	 * 会员获取积分
	 * @param ruleName
	 * @param member
	 * @return
	 */
	public String memberEarnIntegral(String ruleName, Member member) throws ParseException;
	/**
	 * 查询平台下所有用户
	 * */
	public List<Member> queryAllMember(String objName);
	/**
	 * 查询分销商团队总人数
	 * */
	public int queryAllTeam(Member member);
	/**
	 * @描述 注册用户
	 * @author karhs
	 * @date 2015-4-21
	 * @param member
	 * @param fatherId s
	 */
	public void addMember(Member member);
	
	/**
	 * @描述 商家修改用户根据对象
	 * @author karhs
	 * @date 2015-4-21
	 * @param member
	 */
	public void modifyMember(Member member);
	
	/**
	 * 用户修改自己的资料
	 * @param member
	 */
	public void editMemberByUser(Member member,String year,String month,String day);
	
	/**
	 * 绑定手机号并计算积分
	 * @param member
	 * @param mobileNum
	 */
	public void editMemberPhone(Member member, String oldMobile);
	
	/**
	 * @描述 删除用户根据id
	 * @author karhs
	 * @date 2015-4-21
	 * @param id
	 */
	public void removeMember(Long id);
	
	/**
	 * @描述 查询用户根据id
	 * @author karhs
	 * @date 2015-4-21
	 * @param id
	 */
	public Member retrieveMember(Long id);
	
	/**
	 * @描述 查询用户根据微信号
	 * @author karhs
	 * @date 2015-4-21
	 * @param member
	 */
	public Member retrieveMemberByLoginName(Member member);
	
	/**
	 * 联合查询用户的三级客户
	 * @param member
	 * @param pageSrtart
	 * @param pageSize
	 * @return
	 */
	public Pager queryUnionMemberFather(Member member,int pageSrtart,int pageSize);
	
	/**
	 * 查询用户的第一级客户
	 * @param member
	 * @return
	 */
	public Pager queryMemberFather(Member member,int count,int pageSrtart,int pageSize);
	
	/**
	 * 分开查询用户的三级客户(分页)
	 * @param member
	 * @param pageStart
	 * @param pageSize
	 * @param flag
	 * @return
	 */
	public Pager queryMemberThreeCosTomerPage(Member member,int pageStart,int pageSize,int flag);
	
	/**
	 * 查询用户的三级客户
	 * @param member
	 * @return
	 */
	public Map<String,List<Member>> queryMemberThreeCosTomer(Member member);
	
	/**
	 * 查询用户的三级客户的总数
	 * @param member
	 * @return
	 */
	public int queryTotalThreeCosTomer(Member member);
	
	/**
	 * @描述 查询所有用户(过滤掉删除态)
	 * @author karhs
	 * @date 2015-4-21
	 */
	public Pager retrieveAllMembers(int pageStart,int pageSize);
	
	/**
	 * @描述 模糊查询用户，根据会员级别查询
	 * @author karhs
	 * @date 2015-4-22
	 */
	public Pager queryLikeMember(int pageStart,int pageSize,Member member);
	
	/**
	 * @描述 修改用户的总积分（增加或者消费）
	 * @author karhs
	 * @date 2015-4-21
	 * @param member
	 * @return
	 */
	public void editIntergralByLoginName(Member member,Double intergral);
		
	/**
	 * @描述 修改用户的总佣金（消费）
	 * @author karhs
	 * @date 2015-4-21
	 * @param member
	 * @return
	 */
	public void editCommissionByLoginName(Member member,Double commission);
		
	/**
	 * @描述 修改用户的余额(充值或消费)
	 * @author karhs
	 * @date 2015-4-21
	 * @param member
	 * @return
	 */
	public void editRechargeByLoginName(Member member,Double balance);
	
	
	/**
	 * 余额支付
	 * @param member
	 * @param balance
	 */
	public void payByBalance(Member member,Double balance);
	
	/**
	 * @描述 会员充值升级
	 * @author karhs
	 * @date 2015-5-5
	 * @param member
	 */
	public void editRankById(Member member);
		

	public Pager retrieveMembersPager(int pageSize, int pageNo);
	
	public void updateMember(Member member);
	
	public Pager checkAllAgent(int pageSize, int pageNo);
	
	public boolean findAgent(String address);
	
	public Pager findMyFans(int pageSize, int pageNo,Member member);
	
	public CashInfo findByCashInfoId(Long id);
	
	public Double queryMoneyToSend(Member member,ParameterSet parameterSet);

	/**
	 * 查找代理商订单
	 * */
	public AgentOrder receiveAgentOrder(Long id);
	public void newComissionInfo(AgentOrder agentOrder);
	
}