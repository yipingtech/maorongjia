/*
 * IntegralRuleAction.java
 * 
 * Create by MCGT
 * 
 * Create time 2015-04-07
 * 
 * Version 1.0
 * 
 * Copyright 2013 Messcat, Inc. All rights reserved.
 * 
 */
package cc.messcat.web.member;

import java.util.List;

import cc.modules.commons.PageAction;
import cc.messcat.entity.IntegralRule;

public class IntegralRuleAction extends PageAction {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;	
	private IntegralRule integralRule;	
	private List<IntegralRule> integralRules;
	
	@SuppressWarnings("unchecked")
	public String retrieveAllIntegralRules() throws Exception {
		try {
			super.pager = this.integralRuleManagerDao.retrieveIntegralRulesPager(pageSize, pageNo);
			this.integralRules = super.pager.getResultList();
		} catch (Exception ex) {
			addActionMessage("Load page error!");
		}
		return "success";
	}
	
	public String retrieveIntegralRuleById() throws Exception {
		try {
			this.integralRule = this.integralRuleManagerDao.retrieveIntegralRule(id);
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
			this.integralRule = this.integralRuleManagerDao.retrieveIntegralRule(id);
		} catch (Exception ex) {
			addActionMessage("Load page error!");
		}
		return "viewPage";
	}
	
	public String newIntegralRule() throws Exception {
		try {
			this.integralRule.setStatus("1");
			this.integralRuleManagerDao.addIntegralRule(this.integralRule);
			addActionMessage("New successfully!");
		} catch (Exception ex) {
			this.addActionError(ex.getMessage());
			addActionMessage("New fail!");
		}
		return this.retrieveAllIntegralRules();
	}
	
	public String editIntegralRule() throws Exception {
		try {
			IntegralRule integralRule0 = this.integralRuleManagerDao.retrieveIntegralRule(this.id);
			integralRule0.setName(this.integralRule.getName());
			integralRule0.setPeriod(this.integralRule.getPeriod());
			integralRule0.setMaxtmie(this.integralRule.getMaxtmie());
			integralRule0.setIntegral(this.integralRule.getIntegral());
			integralRule0.setStatus("1");

			this.integralRuleManagerDao.modifyIntegralRule(integralRule0);
			addActionMessage("Update successfully!");
		} catch (Exception ex) {
			this.addActionError(ex.getMessage());
			addActionMessage("Update fail!");
		}
		return this.retrieveAllIntegralRules();
	}
	
	public String delIntegralRule() throws Exception {
		try {
			this.integralRuleManagerDao.removeIntegralRule(this.id);
			addActionMessage("Delete successfully!");
		} catch (Exception ex) {
			this.addActionError(ex.getMessage());
			addActionMessage("Delete fail!");
		}
		return this.retrieveAllIntegralRules();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public IntegralRule getIntegralRule() {
		return integralRule;
	}

	public void setIntegralRule(IntegralRule integralRule) {
		this.integralRule = integralRule;
	}

	public List<IntegralRule> getIntegralRules() {
		return integralRules;
	}

	public void setIntegralRules(
			List<IntegralRule> integralRules) {
		this.integralRules = integralRules;
	}

}