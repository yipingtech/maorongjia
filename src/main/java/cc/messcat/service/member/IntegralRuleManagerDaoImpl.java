/*
 * IntegralRuleManagerDaoImpl.java
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
import cc.messcat.bases.BaseManagerDaoImpl;

public class IntegralRuleManagerDaoImpl extends BaseManagerDaoImpl implements IntegralRuleManagerDao {

	public void addIntegralRule(IntegralRule integralRule) {
		this.integralRuleDao.save(integralRule);
	}
	
	public void modifyIntegralRule(IntegralRule integralRule) {
		this.integralRuleDao.update(integralRule);
	}
	
	public void removeIntegralRule(IntegralRule integralRule) {
		this.integralRuleDao.delete(integralRule);
	}

	public void removeIntegralRule(Long id) {
		this.integralRuleDao.delete(id);
	}
	
	public IntegralRule retrieveIntegralRule(Long id) {
		return (IntegralRule) this.integralRuleDao.get(id);
	}

	@SuppressWarnings("unchecked")
	public List retrieveAllIntegralRules() {
		return this.integralRuleDao.findAll();
	}
	
	public Pager retrieveIntegralRulesPager(int pageSize, int pageNo) {
		return this.integralRuleDao.getPager(pageSize, pageNo);
	}

}