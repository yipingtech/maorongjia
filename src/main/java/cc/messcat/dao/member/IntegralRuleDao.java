/*
 * IntegralRuleDao.java
 * 
 * Create by MCGT
 * 
 * Create time 2015-04-07
 * 
 * Version 1.0
 * 
 * Copyright 2013 Messcat, Inc. All rights reserved.
 * 
 */
package cc.messcat.dao.member;

import java.util.List;
import cc.modules.commons.Pager;
import cc.messcat.entity.IntegralRule;

public interface IntegralRuleDao {

	public void save(IntegralRule integralRule);
	
	public void update(IntegralRule integralRule);
	
	public void delete(IntegralRule integralRule);
	
	public void delete(Long id);
	
	public IntegralRule get(Long id);
	
	@SuppressWarnings("unchecked")
	public List findAll();
	
	public Pager getPager(int pageSize, int pageNo);
	
	public IntegralRule getIntegralRuleByName(String name);
	
}