/*
 * BonusRecordManagerDaoImpl.java
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
import cc.messcat.bases.BaseManagerDaoImpl;

public class BonusRecordManagerDaoImpl extends BaseManagerDaoImpl implements BonusRecordManagerDao {

	public void addBonusRecord(BonusRecord bonusRecord) {
		this.bonusRecordDao.save(bonusRecord);
	}
	
	public void modifyBonusRecord(BonusRecord bonusRecord) {
		this.bonusRecordDao.update(bonusRecord);
	}
	
	public void removeBonusRecord(BonusRecord bonusRecord) {
		this.bonusRecordDao.delete(bonusRecord);
	}

	public void removeBonusRecord(Long id) {
		this.bonusRecordDao.delete(id);
	}
	
	public BonusRecord retrieveBonusRecord(Long id) {
		return (BonusRecord) this.bonusRecordDao.get(id);
	}

	@SuppressWarnings("unchecked")
	public List retrieveAllBonusRecords() {
		return this.bonusRecordDao.findAll();
	}
	
	public Pager retrieveBonusRecordsPager(int pageSize, int pageNo) {
		return this.bonusRecordDao.getPager(pageSize, pageNo);
	}

	@Override
	public Date getSendTime(Member member) {
		return this.bonusRecordDao.sendTime(member);
	}

}