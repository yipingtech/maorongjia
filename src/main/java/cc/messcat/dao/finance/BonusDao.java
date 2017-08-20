/*
 * BonusDao.java
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
package cc.messcat.dao.finance;

import java.util.List;
import cc.modules.commons.Pager;
import cc.messcat.entity.Bonus;

public interface BonusDao {

	public void save(Bonus bonus);
	
	public void update(Bonus bonus);
	
	public void delete(Bonus bonus);
	
	public void delete(Long id);
	
	public Bonus get(Long id);
	
	@SuppressWarnings("unchecked")
	public List findAll();
	
	public Pager getPager(int pageSize, int pageNo);
	
}