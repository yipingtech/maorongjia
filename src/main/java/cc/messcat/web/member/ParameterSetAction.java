/*
 * ParameterSetAction.java
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
package cc.messcat.web.member;

import java.util.List;

import cc.modules.commons.PageAction;
import cc.messcat.entity.ParameterSet;

public class ParameterSetAction extends PageAction {
	
	private static final long serialVersionUID = 1L;
	private Long id;	
	private ParameterSet parameterSet;	
	private List<ParameterSet> parameterSets;
	
	/**
	 * 查询当前配置
	 * @return
	 * @throws Exception
	 */
	public String retrieveParameterSetById() throws Exception {
		try {
			this.parameterSet = this.parameterSetManagerDao.retrieveParameterSet();
		} catch (Exception ex) {
			addActionMessage("Load page error!");
		}
		return "editPage";
	}
	
	/**
	 * 修改默认配置
	 * @return
	 * @throws Exception
	 */
	public String editParameterSet() throws Exception {
		try {
			this.parameterSetManagerDao.modifyParameterSet(parameterSet);
			addActionMessage("Update successfully!");
		} catch (Exception ex) {
			this.addActionError(ex.getMessage());
			addActionMessage("Update fail!");
		}
		return this.retrieveParameterSetById();
	}
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ParameterSet getParameterSet() {
		return parameterSet;
	}

	public void setParameterSet(ParameterSet parameterSet) {
		this.parameterSet = parameterSet;
	}

	public List<ParameterSet> getParameterSets() {
		return parameterSets;
	}

	public void setParameterSets(
			List<ParameterSet> parameterSets) {
		this.parameterSets = parameterSets;
	}

}