/*
 * TicketInfoManagerDao.java
 * 
 * Create by MCGT
 * 
 * Create time 2015-06-01
 * 
 * Version 1.0
 * 
 * Copyright 2013 Messcat, Inc. All rights reserved.
 * 
 */
package cc.messcat.service.finance;

import java.util.List;
import cc.modules.commons.Pager;
import cc.messcat.entity.Member;
import cc.messcat.entity.MemberTicket;
import cc.messcat.entity.TicketInfo;

public interface TicketInfoManagerDao {

	public void addTicketInfo(TicketInfo ticketInfo);
	
	public void modifyTicketInfo(TicketInfo ticketInfo);
	
	public void removeTicketInfo(TicketInfo ticketInfo);
	
	public void removeTicketInfo(Long id);
	
	public TicketInfo retrieveTicketInfo(Long id);
	
	public Pager retrieveAllTicketInfos(int pageStart,int pageSize);
	
	/**
	 * 用户获得卡券
	 * @param member
	 * @param ticketInfo
	 * @return
	 */
	public String addMemberTicket(Member member,Long id);
	
	/**
	 * 用户消费卡券
	 * @param id
	 * @return
	 */
	public String updateMemberTicket(Long id);
	
	/**
	 * 用户查询自己的所有卡券（未使用，已使用，已过期）
	 * @param member
	 * @return
	 */
	public Pager queryTicketByMember(Member member,int pageStart,int pageSize,int flag);
	
	public Pager retrieveTicketInfosPager(int pageSize, int pageNo);
	
}