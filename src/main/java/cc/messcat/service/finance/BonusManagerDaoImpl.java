/*
 * BonusManagerDaoImpl.java
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
package cc.messcat.service.finance;

import java.util.List;
import cc.modules.commons.Pager;
import cc.messcat.entity.Bonus;
import cc.messcat.bases.BaseManagerDaoImpl;

public class BonusManagerDaoImpl extends BaseManagerDaoImpl implements BonusManagerDao {

	public void addBonus(Bonus bonus) {
		this.bonusDao.save(bonus);
	}
	
	public void modifyBonus(Bonus bonus) {
		this.bonusDao.update(bonus);
	}
	
	public void removeBonus(Bonus bonus) {
		this.bonusDao.delete(bonus);
	}

	public void removeBonus(Long id) {
		this.bonusDao.delete(id);
	}
	
	public Bonus retrieveBonus(Long id) {
		return (Bonus) this.bonusDao.get(id);
	}

	@SuppressWarnings("unchecked")
	public List retrieveAllBonuss() {
		return this.bonusDao.findAll();
	}
	
	public Pager retrieveBonussPager(int pageSize, int pageNo) {
		return this.bonusDao.getPager(pageSize, pageNo);
	}

}