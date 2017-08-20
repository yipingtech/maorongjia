/*
 * BankCardManagerDao.java
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

public interface BankCardManagerDao {

	public void saveBankCard(BankCard bankCard);
	
	public void modifyBankCard(BankCard bankCard);
	
	public void removeBankCard(BankCard bankCard);
	
	public void removeBankCard(Long id);
	
	public BankCard retrieveBankCard(Long id);
	
	@SuppressWarnings("unchecked")
	public List retrieveAllBankCards();
	
	public Pager retrieveBankCardsPager(int pageSize, int pageNo);
	
	public List<BankCard> findByMember(Member members);
	
	public Pager findCashInfoRecord(Member member,int pageSize, int pageNo);
	
}