/*
 * BonusManagerDao.java
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

public interface BonusManagerDao {

	public void addBonus(Bonus bonus);
	
	public void modifyBonus(Bonus bonus);
	
	public void removeBonus(Bonus bonus);
	
	public void removeBonus(Long id);
	
	public Bonus retrieveBonus(Long id);
	
	@SuppressWarnings("unchecked")
	public List retrieveAllBonuss();
	
	public Pager retrieveBonussPager(int pageSize, int pageNo);
	
}