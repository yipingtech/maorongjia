/*
 * ReportInfoManagerDao.java
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
import cc.messcat.entity.IntergralInfo;
import cc.messcat.entity.Member;
import cc.messcat.entity.ReportInfo;

public interface ReportInfoManagerDao {

	/**
	 * @描述 添加签到记录
	 * @author karhs
	 * @date 2015-4-27
	 * @param reportInfo
	 */
	public String addReportInfo(Member member,IntergralInfo intergralInfo);
	
	
	/**
	 * @描述 商家查询所有签到记录
	 * @author karhs
	 * @date 2015-4-27
	 * @param member
	 * @param pageStart
	 * @param pageSize
	 * @return
	 */
	public Pager retrieveAllReportByProducter(Member member,int pageStart,int pageSize);
	
	public Pager retrieveReportInfosPager(int pageSize, int pageNo);
	
}