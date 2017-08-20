/*
 * BankCardManagerDaoImpl.java
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
package cc.messcat.service.bankcard;

import java.util.List;

import cc.modules.commons.Pager;
import cc.messcat.entity.BankCard;
import cc.messcat.entity.CashInfo;
import cc.messcat.entity.Member;
import cc.messcat.bases.BaseManagerDaoImpl;

public class BankCardManagerDaoImpl extends BaseManagerDaoImpl implements BankCardManagerDao {

	public void saveBankCard(BankCard bankCard) {
		this.bankCardDao.save(bankCard);
	}
	
	public void modifyBankCard(BankCard bankCard) {
		this.bankCardDao.update(bankCard);
	}
	
	public void removeBankCard(BankCard bankCard) {
		this.bankCardDao.delete(bankCard);
	}

	public void removeBankCard(Long id) {
		this.bankCardDao.delete(id);
	}
	
	public BankCard retrieveBankCard(Long id) {
		return (BankCard) this.bankCardDao.get(id);
	}

	@SuppressWarnings("unchecked")
	public List retrieveAllBankCards() {
		return this.bankCardDao.findAll();
	}
	
	public Pager retrieveBankCardsPager(int pageSize, int pageNo) {
		return this.bankCardDao.getPager(pageSize, pageNo);
	}

	@Override
	public List<BankCard> findByMember(Member member) {
		return this.bankCardDao.findByMember(member);
	}

	@Override
	public Pager findCashInfoRecord(Member member,int pageSize, int pageNo){
		return this.bankCardDao.findCashRecordByMember(member,pageSize,pageNo);
	}

}