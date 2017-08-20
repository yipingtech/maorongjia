/*
 * ProvinceManagerDaoImpl.java
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
package cc.messcat.service.system;

import java.util.List;
import cc.modules.commons.Pager;
import cc.messcat.entity.Province;
import cc.messcat.bases.BaseManagerDaoImpl;

public class ProvinceManagerDaoImpl extends BaseManagerDaoImpl implements ProvinceManagerDao {

	public Province getByProvinceId(Long provinceId){
		return this.provinceDao.getByProvinceId(provinceId);
	}
	
	public void addProvince(Province province) {
		this.provinceDao.save(province);
	}
	
	public void modifyProvince(Province province) {
		this.provinceDao.update(province);
	}
	
	public void removeProvince(Province province) {
		this.provinceDao.delete(province);
	}

	public void removeProvince(Long id) {
		this.provinceDao.delete(id);
	}
	
	public Province retrieveProvince(Long id) {
		return (Province) this.provinceDao.get(id);
	}

	@SuppressWarnings("unchecked")
	public List retrieveAllProvinces() {
		return this.provinceDao.findAll();
	}
	
	public Pager retrieveProvincesPager(int pageSize, int pageNo) {
		return this.provinceDao.getPager(pageSize, pageNo);
	}

}