/*
 * ReportInfoDao.java
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

import java.util.List;
import cc.modules.commons.Pager;
import cc.messcat.bases.BaseDao;
import cc.messcat.entity.Member;
import cc.messcat.entity.ReportInfo;

public interface ReportInfoDao extends BaseDao{
	
	public ReportInfo get(Long id);
	
	/**
	 * 判断当天是否已经签到
	 * @param member
	 * @return
	 */
	public List<ReportInfo> judgeReport(Member member,String date);
	
	public Pager getPager(int pageSize, int pageNo);
	
}