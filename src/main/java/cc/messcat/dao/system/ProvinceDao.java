/*
 * ProvinceDao.java
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
package cc.messcat.dao.system;

import java.util.List;
import cc.modules.commons.Pager;
import cc.messcat.bases.BaseDao;
import cc.messcat.entity.Province;

public interface ProvinceDao extends BaseDao{

	public Province getByProvinceId(Long provinceId);
	
	public void save(Province province);
	
	public void update(Province province);
	
	public void delete(Province province);
	
	public void delete(Long id);
	
	public Province get(Long id);
	
	@SuppressWarnings("unchecked")
	public List findAll();
	
	public Pager getPager(int pageSize, int pageNo);
	
}