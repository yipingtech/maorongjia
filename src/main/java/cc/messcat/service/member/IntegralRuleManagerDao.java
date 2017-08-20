/*
 * IntegralRuleManagerDao.java
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
package cc.messcat.service.member;

import java.util.List;
import cc.modules.commons.Pager;
import cc.messcat.entity.IntegralRule;

public interface IntegralRuleManagerDao {

	public void addIntegralRule(IntegralRule integralRule);
	
	public void modifyIntegralRule(IntegralRule integralRule);
	
	public void removeIntegralRule(IntegralRule integralRule);
	
	public void removeIntegralRule(Long id);
	
	public IntegralRule retrieveIntegralRule(Long id);
	
	@SuppressWarnings("unchecked")
	public List retrieveAllIntegralRules();
	
	public Pager retrieveIntegralRulesPager(int pageSize, int pageNo);
	
}