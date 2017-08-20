/*
 * BankCardDao.java
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
package cc.messcat.dao.bankcard;

import java.util.List;

import cc.modules.commons.Pager;
import cc.messcat.entity.BankCard;
import cc.messcat.entity.CashInfo;
import cc.messcat.entity.Member;

public interface BankCardDao {

	public void save(BankCard bankCard);
	
	public void update(BankCard bankCard);
	
	public void delete(BankCard bankCard);
	
	public void delete(Long id);
	
	public BankCard get(Long id);
	
	@SuppressWarnings("unchecked")
	public List findAll();
	
	public Pager getPager(int pageSize, int pageNo);
	
	public List<BankCard> findByMember(Member member);
	
	public Pager findCashRecordByMember(Member member,int pageSize, int pageNo);
	
}