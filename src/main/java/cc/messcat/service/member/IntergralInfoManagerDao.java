/*
 * IntergralInfoManagerDao.java
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

import cc.modules.commons.Pager;
import cc.messcat.entity.IntergralInfo;
import cc.messcat.entity.Member;

public interface IntergralInfoManagerDao {

	public int countEarnTimes(Member member, String startDate, String endDate);
	
	public Double addIntergralInfo(IntergralInfo intergralInfo);
	
	/**
	 * @描述 查询历史累计积分
	 * @author karhs
	 * @date 2015-5-26
	 * @param member
	 * @return
	 */
	public int queryTotalInterGralInfo(Member member);
	
	public void modifyIntergralInfo(IntergralInfo intergralInfo);
	
	public void removeIntergralInfo(IntergralInfo intergralInfo);
	
	public void removeIntergralInfo(Long id);
	
	public IntergralInfo retrieveIntergralInfo(Long id);


	/**
	 * @描述 用户查询自己的所有积分记录
	 * @author karhs
	 * @date 2015-4-22
	 * @param member
	 * @return
	 */
	public Pager retrieveAllIntergralByUser(Member member,int pageStart,int pageSize);
	
	public Pager retrieveIntergralInfosPager(int pageSize, int pageNo);
	
}