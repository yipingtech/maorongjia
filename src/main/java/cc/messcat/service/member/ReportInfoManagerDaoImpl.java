/*
 * ReportInfoManagerDaoImpl.java
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

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cc.modules.commons.Pager;
import cc.modules.util.DateHelper;
import cc.messcat.entity.IntergralInfo;
import cc.messcat.entity.Member;
import cc.messcat.entity.ReportInfo;
import cc.messcat.bases.BaseManagerDaoImpl;

public class ReportInfoManagerDaoImpl extends BaseManagerDaoImpl implements ReportInfoManagerDao {

	//记录签到,判断当天是否已经签到
	public String addReportInfo(Member member,IntergralInfo intergralInfo){
		String date = DateHelper.dataToString(new Date(), "yyyy-MM-dd");
		List<ReportInfo> list = reportInfoDao.judgeReport(member,date);
		if (null!=list&&list.size()>0) {          
			return "1";
		} else {
			Map<String, Object> attrs = new HashMap<String, Object>();
			Map<String, Object> props = new HashMap<String, Object>();
			ReportInfo reportInfo=new ReportInfo();
			reportInfo.setReportTime(new Date());
			reportInfo.setStatus("1");
			reportInfo.setReportStatus("1");
			reportInfo.setMemberId(member);
			this.reportInfoDao.save(reportInfo);
			props.put("report", member.getReport()+1);                                                //签到数+1
			props.put("intergal", member.getIntergal()+intergralInfo.getIntergral());                 //签到加积分
			attrs.put("loginName", member.getLoginName());
			attrs.put("status", "1");
			reportInfoDao.update(Member.class, props, attrs);
			return "0";
		}	
	}
	
	//商家查询用户的签到记录
	@SuppressWarnings("unchecked")
	public Pager retrieveAllReportByProducter(Member member,int pageStart,int pageSize){
		Map<String, Object> attrs = new HashMap<String, Object>();
		attrs.put("status", "1");
		if (null!=member) {
			attrs.put("memberId", member);
		}
		return new Pager(pageSize, pageStart, (int) reportInfoDao.queryTotalRecord(ReportInfo.class, attrs), reportInfoDao.queryList(ReportInfo.class, "id", "desc",attrs));
	}
	
	public Pager retrieveReportInfosPager(int pageSize, int pageNo) {
		return this.reportInfoDao.getPager(pageSize, pageNo);
	}

}