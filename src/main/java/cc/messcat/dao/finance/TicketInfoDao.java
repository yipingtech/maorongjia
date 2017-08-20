/*
 * TicketInfoDao.java
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
package cc.messcat.dao.finance;

import cc.modules.commons.Pager;
import cc.messcat.bases.BaseDao;
import cc.messcat.entity.Member;
import cc.messcat.entity.TicketInfo;

public interface TicketInfoDao extends BaseDao{
	
	public TicketInfo get(Long id);
	
	/**
	 * 查询用户的过期卡券和未过期卡券
	 * @param member
	 * @param day
	 * @param pageStart
	 * @param pageSize
	 * @return
	 */
	public Pager queryTicketByMember(Member member,int pageStart,int pageSize,int flag);
	
	public Pager getPager(int pageSize, int pageNo);
	
}