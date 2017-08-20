/*
 * IntergralInfoAction.java
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

public class IntergralInfoAction extends PageAction {
	
	private Long id;	
	private IntergralInfo intergralInfo;	
	private List<IntergralInfo> intergralInfos;
	private Member member;
	
	@SuppressWarnings("unchecked")
	public String retrieveAllIntergralInfos() throws Exception {
		try {
			super.pager = this.intergralInfoManagerDao.retrieveIntergralInfosPager(pageSize, pageNo);
			this.intergralInfos = super.pager.getResultList();
		} catch (Exception ex) {
			addActionMessage("Load page error!");
		}
		return "success";
	}
	
	//添加积分信息
	public String newIntergralInfo() throws Exception {
		try {
			intergralInfo.setMemberId(member);   //通过session获取用户,并关联
			Double intergralDouble=this.intergralInfoManagerDao.addIntergralInfo(this.intergralInfo);
			memberManagerDao.editIntergralByLoginName(member, intergralDouble);         //修改用户的总积分
			addActionMessage("New successfully!");
		} catch (Exception ex) {
			this.addActionError(ex.getMessage());
			addActionMessage("New fail!");
		}
		return "edit_success";
	}
	
	//查询积分根据id
	public String retrieveIntergralInfoById() throws Exception {
		try {
			this.intergralInfo = this.intergralInfoManagerDao.retrieveIntergralInfo(id);
		} catch (Exception ex) {
			addActionMessage("Load page error!");
		}
		return "editPage";
	}

	public String newPage() throws Exception {
		return "newPage";
	}
	
	//商家查询单个用户的所有积分记录(分页)
	public String queryAllIntergralByMember() throws Exception {
		try {
			member=memberManagerDao.retrieveMemberByLoginName(member);
			pager = intergralInfoManagerDao.retrieveAllIntergralByUser(member, pageNo, pageSize);
			intergralInfos=pager.getResultList();
		} catch (Exception ex) {
			addActionMessage("Load page error!");
		}
		return "success";
	}
	
	//商家查询所有用户的积分记录（分页）
	public String queryAllIntergralByProducter() throws Exception {
		try {
			pager = intergralInfoManagerDao.retrieveAllIntergralByUser(member, pageNo, pageSize);
			intergralInfos=pager.getResultList();
		} catch (Exception ex) {
			addActionMessage("Load page error!");
		}
		return "success";
	}
	
	//显示单个积分的信息
	public String viewPage() throws Exception {
		try {
			this.intergralInfo = this.intergralInfoManagerDao.retrieveIntergralInfo(id);
		} catch (Exception ex) {
			addActionMessage("Load page error!");
		}
		return "viewPage";
	}
	
	//编辑积分信息
	public String editIntergralInfo() throws Exception {
		try {
			IntergralInfo intergralInfo0 = this.intergralInfoManagerDao.retrieveIntergralInfo(this.id);
			intergralInfo0.setIntergral(this.intergralInfo.getIntergral());
			intergralInfo0.setAddTime(this.intergralInfo.getAddTime());
			intergralInfo0.setComments(this.intergralInfo.getComments());
			intergralInfo0.setIntergralStatus(this.intergralInfo.getIntergralStatus());
			intergralInfo0.setStatus(this.intergralInfo.getStatus());

			this.intergralInfoManagerDao.modifyIntergralInfo(intergralInfo0);
			addActionMessage("Update successfully!");
		} catch (Exception ex) {
			this.addActionError(ex.getMessage());
			addActionMessage("Update fail!");
		}
		return "edit_success";
	}
	
	//删除积分信息
	public String delIntergralInfo() throws Exception {
		try {
			this.intergralInfoManagerDao.removeIntergralInfo(this.id);
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

	public IntergralInfo getIntergralInfo() {
		return intergralInfo;
	}

	public void setIntergralInfo(IntergralInfo intergralInfo) {
		this.intergralInfo = intergralInfo;
	}

	public List<IntergralInfo> getIntergralInfos() {
		return intergralInfos;
	}

	public void setIntergralInfos(
			List<IntergralInfo> intergralInfos) {
		this.intergralInfos = intergralInfos;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

}