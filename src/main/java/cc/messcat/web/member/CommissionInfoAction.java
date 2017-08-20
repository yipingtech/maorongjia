/*
 * CommissionInfoAction.java
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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cc.messcat.entity.CashInfo;
import cc.messcat.entity.CommissionInfo;
import cc.messcat.entity.Member;
import cc.messcat.entity.ParameterSet;
import cc.modules.commons.PageAction;

public class CommissionInfoAction extends PageAction {
	
	private static final long serialVersionUID = 1L;
	private Long id;	
	private Member member;
	private CommissionInfo commissionInfo;	
	private CashInfo cashInfo;	


	private List<CommissionInfo> commissionInfos;
	private List<CashInfo> cashInfos;
	private List<Member> ml;
	
	private static Logger log = LoggerFactory.getLogger(CommissionInfoAction.class); 
	
	@SuppressWarnings("unchecked")
	public String retrieveAllCommissionInfos() throws Exception {
		try {
			super.pager = this.commissionInfoManagerDao.retrieveCommissionInfosPager(pageSize, pageNo);
			this.commissionInfos = super.pager.getResultList();
		} catch (Exception ex) {
			addActionMessage("Load page error!");
		}
		return "success";
	}
	
	//跳转到添加页面
	public String newPage() throws Exception {
		return "newPage";
	}
	
	//根据id查询单笔佣金充值消费信息
	public String retrieveCommissionInfoById() throws Exception {
		try {
			this.commissionInfo = this.commissionInfoManagerDao.retrieveCommissionInfo(id);
		} catch (Exception ex) {
			addActionMessage("Load page error!");
		}
		return "editPage";
	}

	//显示详细佣金记录页面
	public String viewPage() throws Exception {
		try {
			this.cashInfo = this.commissionInfoManagerDao.findByCashInfoId(id);
		} catch (Exception ex) {
			addActionMessage("Load page error!");
		}
		return "viewPage";
	}
	
	//添加获得佣金记录
	public String newCommissionInfo() throws Exception {
		try {
			commissionInfo.setMemberId(member);     //通过session获取用户
			Double commissionDouble=this.commissionInfoManagerDao.addCommissionInfo(this.commissionInfo);
			memberManagerDao.editCommissionByLoginName(member, commissionDouble);     //去修改用户的佣金金额
			addActionMessage("New successfully!");
		} catch (Exception ex) {
			this.addActionError(ex.getMessage());
			addActionMessage("New fail!");
		}
		return "edit_success";
	}
	
	
	//商家查询所有用户的佣金记录（分页）
	public String queryCommissionByProducter() throws Exception {
		try {
			pager = commissionInfoManagerDao.queryAllCommissionByProducter(pageNo, pageSize,member);
			commissionInfos=pager.getResultList();
		} catch (Exception ex) {
			addActionMessage("Load page error!");
		}
		return "success";
	}
	
	//商家查询单个用户的佣金记录（分页）
	public String queryCommissionByMember() throws Exception {
		try {
			member=memberManagerDao.retrieveMemberByLoginName(member);
			pager = commissionInfoManagerDao.queryAllCommissionByProducter(pageNo, pageSize,member);
			commissionInfos=pager.getResultList();
		} catch (Exception ex) {
			addActionMessage("Load page error!");
		}
		return "success";
	}


	//编辑佣金信息记录
	public String editCommissionInfo() throws Exception {
		try {
			CommissionInfo commissionInfo0 = this.commissionInfoManagerDao.retrieveCommissionInfo(this.id);
			commissionInfo0.setCommission(this.commissionInfo.getCommission());
			commissionInfo0.setAddTime(this.commissionInfo.getAddTime());
			commissionInfo0.setCommissionStatus(this.commissionInfo.getCommissionStatus());
			commissionInfo0.setStatus(this.commissionInfo.getStatus());

			this.commissionInfoManagerDao.modifyCommissionInfo(commissionInfo0);
			addActionMessage("Update successfully!");
		} catch (Exception ex) {
			this.addActionError(ex.getMessage());
			addActionMessage("Update fail!");
		}
		return "edit_success";
	}
	
	//删除佣金信息记录
	public String delCommissionInfo() throws Exception {
		try {
			this.commissionInfoManagerDao.removeCommissionInfo(this.id);
		} catch (Exception ex) {
			this.addActionError(ex.getMessage());
			addActionMessage("Delete fail!");
		}
		return "deleteSuccess";
	}
	
	//商家查询所有用户的佣金提现申请
	public String queryAllCashApplyBySeller() throws Exception {
		try {
			//member=new Member();
			pager=commissionInfoManagerDao.queryCashInfosByUser(null,pageNo, pageSize);
			cashInfos=pager.getResultList();
			log.info(cashInfos.size()+"");
			addActionMessage("提现申请成功 successfully!");
		} catch (Exception ex) {
			this.addActionError(ex.getMessage());
			addActionMessage("Delete fail!");
		}
		return "toCashInfo";
	}
	
	/**
	 * 查询所有到达发放佣金条件的会员
	 * */
	public String queryAllNestToSend() throws Exception {
		try {
			ParameterSet parameterSet =new ParameterSet(); 
			parameterSet = this.parameterSetManagerDao.retrieveParameterSet();
			Double conditionLine = parameterSet.getConditionSend();
			pager = this.commissionInfoManagerDao.queryAllUserByCondition(pageNo, pageSize, conditionLine);
			ml = pager.getResultList();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "toCheckInfo";
	}
	/**
	 * 查询发放佣金的记录
	 * */
	public String queryAllCommissionInfo(){
		try{
			commissionInfos = new ArrayList<CommissionInfo>();
			pager = this.commissionInfoManagerDao.retrieveCommissionInfos(pageSize, pageNo);
			commissionInfos = new ArrayList<CommissionInfo>();
			commissionInfos = pager.getResultList();
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return "toShow";
	}
	/**
	 * 发放佣金
	 * @throws Exception 
	 * */
	public String sendCommission() throws Exception{
		ParameterSet parameterSet =new ParameterSet(); 
		parameterSet = this.parameterSetManagerDao.retrieveParameterSet();
		Double conditionLine = parameterSet.getConditionSend();
		member  = new Member();
		member = this.memberManagerDao.retrieveMember(this.id);
		Double d = Math.floor(member.getCommissionLine()/conditionLine); //计算到达红包条件的倍数
		//member.setBalance(member.getBalance()+conditionLine*d);
		member.setCommissionLine(member.getCommissionLine()-conditionLine*d);
		member.setCommission(member.getCommission()+conditionLine*d);
		this.memberManagerDao.modifyMember(member);
		CommissionInfo ci = new CommissionInfo();
		ci.setAddTime(new Date());
		ci.setCommission(conditionLine*d);
	    ci.setCommissionStatus("2");
	    ci.setCommissionComments("提成红包发放");
	    ci.setMemberId(member);
	    ci.setStatus("0");
	    this.commissionInfoManagerDao.addCommissionInfo(ci);
		return this.queryAllNestToSend();
	}
	
	public String failToCash() throws Exception{
		try {
			commissionInfoManagerDao.failToCash(id);
		} catch (Exception ex) {
			this.addActionError(ex.getMessage());
			addActionMessage("fail!");
		}
		return queryAllCashApplyBySeller();
	}
	
	//商家处理所有用户的佣金提现申请
	public String updateCashApplyBySeller() throws Exception {
		try {
			commissionInfoManagerDao.updateCashApplyBySeller(id);
			addActionMessage("Action successfully!");
		} catch (Exception ex) {
			this.addActionError(ex.getMessage());
			addActionMessage("Action fail!");
		}
		return queryAllCashApplyBySeller();
	}
	
	//根据Id查询提现信息
	public String viewPageByCashId(){
		try {
			this.cashInfo = commissionInfoManagerDao.findByCashInfoId(id);
		} catch (Exception ex) {
			this.addActionError(ex.getMessage());
			addActionMessage("Delete fail!");
		}
		return "cash_view";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public CommissionInfo getCommissionInfo() {
		return commissionInfo;
	}

	public void setCommissionInfo(CommissionInfo commissionInfo) {
		this.commissionInfo = commissionInfo;
	}

	public List<CommissionInfo> getCommissionInfos() {
		return commissionInfos;
	}

	public void setCommissionInfos(
			List<CommissionInfo> commissionInfos) {
		this.commissionInfos = commissionInfos;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public List<CashInfo> getCashInfos() {
		return cashInfos;
	}

	public void setCashInfos(List<CashInfo> cashInfos) {
		this.cashInfos = cashInfos;
	}
	public List<Member> getMl() {
		return ml;
	}

	public void setMl(List<Member> ml) {
		this.ml = ml;
	}
	public CashInfo getCashInfo() {
		return cashInfo;
	}

	public void setCashInfo(CashInfo cashInfo) {
		this.cashInfo = cashInfo;
	}

}