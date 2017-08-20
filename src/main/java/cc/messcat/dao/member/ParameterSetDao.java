/*
 * ParameterSetDao.java
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
package cc.messcat.dao.member;

import java.util.List;
import cc.modules.commons.Pager;
import cc.messcat.bases.BaseDao;
import cc.messcat.entity.ParameterSet;

public interface ParameterSetDao extends BaseDao{

	public void save(ParameterSet parameterSet);
	
	public void update(ParameterSet parameterSet);
	
	public void delete(ParameterSet parameterSet);
	
	public void delete(Long id);
	
	public ParameterSet get(Long id);
	
	@SuppressWarnings("unchecked")
	public List findAll();
	
	public Pager getPager(int pageSize, int pageNo);
	
}