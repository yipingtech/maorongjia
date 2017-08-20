/*
 * BankCardAction.java
 * 
 * Create by MCGT
 * 
 * Create time 2015-08-07
 * 
 * Version 1.0
 * 
 * Copyright 2013 Messcat, Inc. All rights reserved.
 * 
 */
package cc.messcat.web.bankcard;

import java.util.List;

import cc.modules.commons.PageAction;
import cc.messcat.entity.BankCard;

public class BankCardAction extends PageAction {
	
	private Long id;
	
	private BankCard bankCard;
	
	private List<BankCard> bankCards;
	
	
	@SuppressWarnings("unchecked")
	public String retrieveAllBankCards() throws Exception {
		try {
			super.pager = this.bankCardManagerDao.retrieveBankCardsPager(pageSize, pageNo);
			this.bankCards = super.pager.getResultList();
		} catch (Exception ex) {
			addActionMessage("Load page error!");
		}
		return "success";
	}
	
	public String retrieveBankCardById() throws Exception {
		try {
			this.bankCard = this.bankCardManagerDao.retrieveBankCard(id);
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
			this.bankCard = this.bankCardManagerDao.retrieveBankCard(id);
		} catch (Exception ex) {
			addActionMessage("Load page error!");
		}
		return "viewPage";
	}
	
	public String newBankCard() throws Exception {
		try {
			this.bankCardManagerDao.saveBankCard(this.bankCard);
			addActionMessage("New successfully!");
		} catch (Exception ex) {
			this.addActionError(ex.getMessage());
			addActionMessage("New fail!");
		}
		return "edit_success";
	}
	
	public String editBankCard() throws Exception {
		try {
			BankCard bankCard0 = this.bankCardManagerDao.retrieveBankCard(this.id);
			bankCard0.setBankCardFlag(this.bankCard.getBankCardFlag());
			bankCard0.setBankCardNum(this.bankCard.getBankCardNum());
			bankCard0.setBankCardMember(this.bankCard.getBankCardMember());
			bankCard0.setBankCardAddress(this.bankCard.getBankCardAddress());
			bankCard0.setBankCardPoint(this.bankCard.getBankCardPoint());
			bankCard0.setBankCardTime(this.bankCard.getBankCardTime());
			bankCard0.setBankCardRemarks(this.bankCard.getBankCardRemarks());
			bankCard0.setStatus(this.bankCard.getStatus());

			this.bankCardManagerDao.modifyBankCard(bankCard0);
			addActionMessage("Update successfully!");
		} catch (Exception ex) {
			this.addActionError(ex.getMessage());
			addActionMessage("Update fail!");
		}
		return "edit_success";
	}
	
	public String delBankCard() throws Exception {
		try {
			this.bankCardManagerDao.removeBankCard(this.id);
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

	public BankCard getBankCard() {
		return bankCard;
	}

	public void setBankCard(BankCard bankCard) {
		this.bankCard = bankCard;
	}

	public List<BankCard> getBankCards() {
		return bankCards;
	}

	public void setBankCards(
			List<BankCard> bankCards) {
		this.bankCards = bankCards;
	}

}