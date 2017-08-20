/*
 * MemberBonusAction.java
 * 
 * Create by MCGT
 * 
 * Create time 2015-04-24
 * 
 * Version 1.0
 * 
 * Copyright 2013 Messcat, Inc. All rights reserved.
 * 
 */
package cc.messcat.web.member;

import java.util.List;

import cc.modules.commons.PageAction;
import cc.messcat.entity.MemberBonus;

public class MemberBonusAction extends PageAction {
	
	private Long id;	
	private MemberBonus memberBonus;	
	private List<MemberBonus> memberBonuss;
	
	@SuppressWarnings("unchecked")
	public String retrieveAllMemberBonuss() throws Exception {
		try {
			super.pager = this.memberBonusManagerDao.retrieveMemberBonussPager(pageSize, pageNo);
			this.memberBonuss = super.pager.getResultList();
		} catch (Exception ex) {
			addActionMessage("Load page error!");
		}
		return "success";
	}
	
	public String retrieveMemberBonusById() throws Exception {
		try {
			this.memberBonus = this.memberBonusManagerDao.retrieveMemberBonus(id);
		} catch (Exception ex) {
			addActionMessage("Load page error!");
		}
		return "editPage";
	}

	public String newPage() throws Exception {
		return "newPage";
	}
	
	public String viewPage() throws Exception {
		try {
			this.memberBonus = this.memberBonusManagerDao.retrieveMemberBonus(id);
		} catch (Exception ex) {
			addActionMessage("Load page error!");
		}
		return "viewPage";
	}
	
	public String newMemberBonus() throws Exception {
		try {
			this.memberBonusManagerDao.addMemberBonus(this.memberBonus);
			addActionMessage("New successfully!");
		} catch (Exception ex) {
			this.addActionError(ex.getMessage());
			addActionMessage("New fail!");
		}
		return "edit_success";
	}
	
	public String editMemberBonus() throws Exception {
		try {
			MemberBonus memberBonus0 = this.memberBonusManagerDao.retrieveMemberBonus(this.id);
			memberBonus0.setValidPeriod(this.memberBonus.getValidPeriod());
			memberBonus0.setMember(this.memberBonus.getMember());
			memberBonus0.setBonus(this.memberBonus.getBonus());
			memberBonus0.setStatus(this.memberBonus.getStatus());

			this.memberBonusManagerDao.modifyMemberBonus(memberBonus0);
			addActionMessage("Update successfully!");
		} catch (Exception ex) {
			this.addActionError(ex.getMessage());
			addActionMessage("Update fail!");
		}
		return "edit_success";
	}
	
	public String delMemberBonus() throws Exception {
		try {
			this.memberBonusManagerDao.removeMemberBonus(this.id);
			addActionMessage("Delete successfully!");
		} catch (Exception ex) {
			this.addActionError(ex.getMessage());
			addActionMessage("Delete fail!");
		}
		return "edit_success";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public MemberBonus getMemberBonus() {
		return memberBonus;
	}

	public void setMemberBonus(MemberBonus memberBonus) {
		this.memberBonus = memberBonus;
	}

	public List<MemberBonus> getMemberBonuss() {
		return memberBonuss;
	}

	public void setMemberBonuss(
			List<MemberBonus> memberBonuss) {
		this.memberBonuss = memberBonuss;
	}

}