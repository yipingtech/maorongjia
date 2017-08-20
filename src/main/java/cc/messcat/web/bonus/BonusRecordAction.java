/*
 * BonusRecordAction.java
 * 
 * Create by MCGT
 * 
 * Create time 2015-07-08
 * 
 * Version 1.0
 * 
 * Copyright 2013 Messcat, Inc. All rights reserved.
 * 
 */
package cc.messcat.web.bonus;

import java.util.List;

import cc.modules.commons.PageAction;
import cc.messcat.entity.BonusRecord;

public class BonusRecordAction extends PageAction {
	
	private Long id;
	
	private BonusRecord bonusRecord;
	
	private List<BonusRecord> bonusRecords;
	
	@SuppressWarnings("unchecked")
	public String retrieveAllBonusRecords() throws Exception {
		try {
			super.pager = this.bonusRecordManagerDao.retrieveBonusRecordsPager(pageSize, pageNo);
			this.bonusRecords = super.pager.getResultList();
		} catch (Exception ex) {
			addActionMessage("Load page error!");
		}
		return "success";
	}
	
	public String retrieveBonusRecordById() throws Exception {
		try {
			this.bonusRecord = this.bonusRecordManagerDao.retrieveBonusRecord(id);
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
			this.bonusRecord = this.bonusRecordManagerDao.retrieveBonusRecord(id);
		} catch (Exception ex) {
			addActionMessage("Load page error!");
		}
		return "viewPage";
	}
	
	public String newBonusRecord() throws Exception {
		try {
			this.bonusRecordManagerDao.addBonusRecord(this.bonusRecord);
			addActionMessage("New successfully!");
		} catch (Exception ex) {
			this.addActionError(ex.getMessage());
			addActionMessage("New fail!");
		}
		return "edit_success";
	}
	
	public String editBonusRecord() throws Exception {
		try {
			BonusRecord bonusRecord0 = this.bonusRecordManagerDao.retrieveBonusRecord(this.id);
			bonusRecord0.setMoney(this.bonusRecord.getMoney());
			bonusRecord0.setMember(this.bonusRecord.getMember());
			bonusRecord0.setSendTime(this.bonusRecord.getSendTime());
			bonusRecord0.setStatus(this.bonusRecord.getStatus());

			this.bonusRecordManagerDao.modifyBonusRecord(bonusRecord0);
			addActionMessage("Update successfully!");
		} catch (Exception ex) {
			this.addActionError(ex.getMessage());
			addActionMessage("Update fail!");
		}
		return "edit_success";
	}
	
	public String delBonusRecord() throws Exception {
		try {
			this.bonusRecordManagerDao.removeBonusRecord(this.id);
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

	public BonusRecord getBonusRecord() {
		return bonusRecord;
	}

	public void setBonusRecord(BonusRecord bonusRecord) {
		this.bonusRecord = bonusRecord;
	}

	public List<BonusRecord> getBonusRecords() {
		return bonusRecords;
	}

	public void setBonusRecords(
			List<BonusRecord> bonusRecords) {
		this.bonusRecords = bonusRecords;
	}

}