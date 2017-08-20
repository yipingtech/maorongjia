/*
 * ProvinceManagerDao.java
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
import cc.messcat.bases.BaseDao;
import cc.messcat.bases.BaseManagerDao;
import cc.messcat.entity.Province;

public interface ProvinceManagerDao extends BaseManagerDao{

	public Province getByProvinceId(Long provinceId);
	
	public void addProvince(Province province);
	
	public void modifyProvince(Province province);
	
	public void removeProvince(Province province);
	
	public void removeProvince(Long id);
	
	public Province retrieveProvince(Long id);
	
	@SuppressWarnings("unchecked")
	public List retrieveAllProvinces();
	
	public Pager retrieveProvincesPager(int pageSize, int pageNo);
	
}