/*
 * ParameterSetManagerDao.java
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
package cc.messcat.service.member;

import java.util.List;
import cc.modules.commons.Pager;
import cc.messcat.entity.ParameterSet;

public interface ParameterSetManagerDao {

	public void addParameterSet(ParameterSet parameterSet);
	
	public void modifyParameterSet(ParameterSet parameterSet);
	
	public void removeParameterSet(ParameterSet parameterSet);
	
	public void removeParameterSet(Long id);
	
	/**
	 * 查询当前的配置
	 * @return
	 */
	public ParameterSet retrieveParameterSet();
	
	@SuppressWarnings("unchecked")
	public List retrieveAllParameterSets();
	
	//查询佣金等配置
	public List<String> queryParameter();
	
	public Pager retrieveParameterSetsPager(int pageSize, int pageNo);
	
}