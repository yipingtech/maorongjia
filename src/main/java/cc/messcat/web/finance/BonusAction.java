/*
 * BonusAction.java
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
package cc.messcat.web.finance;

import java.util.List;

import cc.modules.commons.PageAction;
import cc.messcat.entity.Bonus;

public class BonusAction extends PageAction {
	
	private Long id;
	
	private Bonus bonus;
	
	private List<Bonus> bonuss;
	
	@SuppressWarnings("unchecked")
	public String retrieveAllBonuss() throws Exception {
		try {
			super.pager = this.bonusManagerDao.retrieveBonussPager(pageSize, pageNo);
			this.bonuss = super.pager.getResultList();
		} catch (Exception ex) {
			addActionMessage("Load page error!");
		}
		return "success";
	}
	
	public String retrieveBonusById() throws Exception {
		try {
			this.bonus = this.bonusManagerDao.retrieveBonus(id);
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
			this.bonus = this.bonusManagerDao.retrieveBonus(id);
		} catch (Exception ex) {
			addActionMessage("Load page error!");
		}
		return "viewPage";
	}
	
	public String newBonus() throws Exception {
		try {
			this.bonusManagerDao.addBonus(this.bonus);
			addActionMessage("New successfully!");
		} catch (Exception ex) {
			this.addActionError(ex.getMessage());
			addActionMessage("New fail!");
		}
		return "edit_success";
	}
	
	public String editBonus() throws Exception {
		try {
			Bonus bonus0 = this.bonusManagerDao.retrieveBonus(this.id);
			bonus0.setAmount(this.bonus.getAmount());
			bonus0.setStatus(this.bonus.getStatus());

			this.bonusManagerDao.modifyBonus(bonus0);
			addActionMessage("Update successfully!");
		} catch (Exception ex) {
			this.addActionError(ex.getMessage());
			addActionMessage("Update fail!");
		}
		return "edit_success";
	}
	
	public String delBonus() throws Exception {
		try {
			this.bonusManagerDao.removeBonus(this.id);
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

	public Bonus getBonus() {
		return bonus;
	}

	public void setBonus(Bonus bonus) {
		this.bonus = bonus;
	}

	public List<Bonus> getBonuss() {
		return bonuss;
	}

	public void setBonuss(
			List<Bonus> bonuss) {
		this.bonuss = bonuss;
	}

}