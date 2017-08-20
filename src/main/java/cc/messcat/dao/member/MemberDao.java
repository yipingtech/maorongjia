/*
 * MemberDao.java
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
package cc.messcat.dao.member;

import java.util.List;

import cc.modules.commons.Pager;
import cc.messcat.bases.BaseDao;
import cc.messcat.entity.AgentOrder;
import cc.messcat.entity.CashInfo;
import cc.messcat.entity.Member;

public interface MemberDao extends BaseDao{

	public void saveAgentOrder(AgentOrder agentOrder);
	
	public List<Member> findMember(Member member);
	public void save(Member member);
	
	public void update(Member member);
	
	/**
	 * 分销商团队总人数
	 * */
	
	public Pager queryAllTeam(Member member,int pageStart, int pageSize);
	
	public Member get(Long id);
	
	/**
	 * 联合查询三级客户
	 * @param pageStart
	 * @param pageSize
	 * @return
	 */
	public Pager queryUnionMemberFather(Member member,int pageStart, int pageSize);
	
	/**
	 * 分开查询三级客户
	 * @param member
	 * @param pageStart
	 * @param pageSize
	 * @param flag
	 * @return
	 */
	public Pager queryThreeMember(Member member,int pageStart, int pageSize,int flag);
	
	/**
	 * 会员列表的模糊查询
	 * @param pageStart
	 * @param pageSize
	 * @param member
	 * @return
	 */
	public Pager queryCardAndRankByLike(int pageStart,int pageSize,Member member);
	
	public Pager getPager(int pageSize, int pageNo);
	
	/**
	 * 查找代理商订单
	 * */
	public AgentOrder receiveAgentOrder(Long id);
	
	public Pager checkAllAgent(int pageSize, int pageNo);
	
	
	public boolean findAgent(String address);
	
	public Pager queryMember(int pageSize, int pageNo);
	
	public Pager findMyFans(int pageSize, int pageNo,Member member);
	
	public CashInfo findCashInfoId(Long id);
	
	public Double queryMoneyToSend(Member member,String beginTime);

	public double queryAgentMoneyToSend(Member member, String threeDate);
	
}