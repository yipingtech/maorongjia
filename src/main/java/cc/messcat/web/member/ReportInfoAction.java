/*
 * ReportInfoAction.java
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
package cc.messcat.web.member;

import java.util.List;

import cc.modules.commons.PageAction;
import cc.messcat.entity.IntergralInfo;
import cc.messcat.entity.Member;
import cc.messcat.entity.ReportInfo;

public class ReportInfoAction extends PageAction {
	
	private static final long serialVersionUID = 1L;
	private Long id;	
	private ReportInfo reportInfo;	
	private List<ReportInfo> reportInfos;
	private IntergralInfo intergralInfo;
	private Member member;
	
	@SuppressWarnings("unchecked")
	public String retrieveAllReportInfos() throws Exception {
		try {
			super.pager = this.reportInfoManagerDao.retrieveReportInfosPager(pageSize, pageNo);
			this.reportInfos = super.pager.getResultList();
		} catch (Exception ex) {
			addActionMessage("Load page error!");
		}
		return "success";
	}
	
	//商家查询当个用户所有签到记录
	public String retrieveAllReportByMember() throws Exception {
		try {
			member=memberManagerDao.retrieveMemberByLoginName(member);
			pager=reportInfoManagerDao.retrieveAllReportByProducter(member, pageNo, pageSize);
			reportInfos=pager.getResultList();
			addActionMessage("New successfully!");
		} catch (Exception ex) {
			this.addActionError(ex.getMessage());
			addActionMessage("New fail!");
		}
		return "success";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ReportInfo getReportInfo() {
		return reportInfo;
	}

	public void setReportInfo(ReportInfo reportInfo) {
		this.reportInfo = reportInfo;
	}

	public List<ReportInfo> getReportInfos() {
		return reportInfos;
	}

	public void setReportInfos(
			List<ReportInfo> reportInfos) {
		this.reportInfos = reportInfos;
	}

	public IntergralInfo getIntergralInfo() {
		return intergralInfo;
	}

	public void setIntergralInfo(IntergralInfo intergralInfo) {
		this.intergralInfo = intergralInfo;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

}