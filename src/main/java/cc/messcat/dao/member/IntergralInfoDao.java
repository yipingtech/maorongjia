/*
 * IntergralInfoDao.java
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
import cc.messcat.entity.IntergralInfo;
import cc.messcat.entity.Member;

public interface IntergralInfoDao extends BaseDao{
	
	public void update(IntergralInfo intergralInfo);
	
	public void delete(IntergralInfo intergralInfo);
	
	public IntergralInfo get(Long id);	
	
	/**
	 * 查询历史累计积分
	 * @param member
	 * @return
	 */
	public int queryTotalIntergralInfos(Member member);
	
	public Pager getPager(int pageSize, int pageNo);
	
	public int countEarnTimes(Member member, String startDate, String endDate);
	
}