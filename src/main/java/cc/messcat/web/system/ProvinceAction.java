/*
 * ProvinceAction.java
 * 
 * Create by MCGT
 * 
 * Create time 2014-08-25
 * 
 * Version 1.0
 * 
 * Copyright 2013 Messcat, Inc. All rights reserved.
 * 
 */
package cc.messcat.web.system;

import java.util.List;

import cc.modules.commons.PageAction;
import cc.messcat.entity.Province;

public class ProvinceAction extends PageAction {
	
	private Long id;	
	private Province province;	
	private List<Province> provinces;
	
	@SuppressWarnings("unchecked")
	public String retrieveAllProvinces() throws Exception {
		try {
			super.pager = this.provinceManagerDao.retrieveProvincesPager(pageSize, pageNo);
			this.provinces = super.pager.getResultList();
		} catch (Exception ex) {
			addActionMessage("Load page error!");
		}
		return "success";
	}
	
	public String retrieveProvinceById() throws Exception {
		try {
			this.province = this.provinceManagerDao.retrieveProvince(id);
		} catch (Exception ex) {
			addActionMessage("Load page error!");
		}
		return "editPage";
	}

	public String newPage() throws Exception {
		return "newPage";
	}
	
	public String viewPage() throws Exception {
		try {
			this.province = this.provinceManagerDao.retrieveProvince(id);
		} catch (Exception ex) {
			addActionMessage("Load page error!");
		}
		return "viewPage";
	}
	
	public String newProvince() throws Exception {
		try {
			this.provinceManagerDao.addProvince(this.province);
			addActionMessage("New successfully!");
		} catch (Exception ex) {
			this.addActionError(ex.getMessage());
			addActionMessage("New fail!");
		}
		return this.retrieveAllProvinces();
	}
	
	public String editProvince() throws Exception {
		try {
			Province province0 = this.provinceManagerDao.retrieveProvince(this.id);
			province0.setProvinceId(this.province.getProvinceId());
			province0.setProvince(this.province.getProvince());

			this.provinceManagerDao.modifyProvince(province0);
			addActionMessage("Update successfully!");
		} catch (Exception ex) {
			this.addActionError(ex.getMessage());
			addActionMessage("Update fail!");
		}
		return this.retrieveAllProvinces();
	}
	
	public String delProvince() throws Exception {
		try {
			this.provinceManagerDao.removeProvince(this.id);
			addActionMessage("Delete successfully!");
		} catch (Exception ex) {
			this.addActionError(ex.getMessage());
			addActionMessage("Delete fail!");
		}
		return this.retrieveAllProvinces();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Province getProvince() {
		return province;
	}

	public void setProvince(Province province) {
		this.province = province;
	}

	public List<Province> getProvinces() {
		return provinces;
	}

	public void setProvinces(
			List<Province> provinces) {
		this.provinces = provinces;
	}

}