/*
 * BonusRecordDao.java
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
package cc.messcat.dao.bonus;

import java.util.Date;
import java.util.List;

import cc.modules.commons.Pager;
import cc.messcat.entity.BonusRecord;
import cc.messcat.entity.Member;

public interface BonusRecordDao {

	public void save(BonusRecord bonusRecord);
	
	public void update(BonusRecord bonusRecord);
	
	public void delete(BonusRecord bonusRecord);
	
	public void delete(Long id);
	
	public BonusRecord get(Long id);
	
	@SuppressWarnings("unchecked")
	public List findAll();
	
	public Pager getPager(int pageSize, int pageNo);
	
	public Date sendTime(Member member);
	
}