/*
 * BonusRecordManagerDao.java
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
package cc.messcat.service.bonus;

import java.util.Date;
import java.util.List;

import cc.modules.commons.Pager;
import cc.messcat.entity.BonusRecord;
import cc.messcat.entity.Member;

public interface BonusRecordManagerDao {

	public void addBonusRecord(BonusRecord bonusRecord);
	
	public void modifyBonusRecord(BonusRecord bonusRecord);
	
	public void removeBonusRecord(BonusRecord bonusRecord);
	
	public void removeBonusRecord(Long id);
	
	public BonusRecord retrieveBonusRecord(Long id);
	
	@SuppressWarnings("unchecked")
	public List retrieveAllBonusRecords();
	
	public Pager retrieveBonusRecordsPager(int pageSize, int pageNo);
	
	public Date getSendTime(Member member);
	
}