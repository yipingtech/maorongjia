/*
 * CityDao.java
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
import cc.messcat.entity.City;

public interface CityDao {

	public City getByCityId(Long cityId);
	
	public List<City> findCityByProvince(Long provinceId);
	
	public void save(City city);
	
	public void update(City city);
	
	public void delete(City city);
	
	public void delete(Long id);
	
	public City get(Long id);
	
	@SuppressWarnings("unchecked")
	public List findAll();
	
	public Pager getPager(int pageSize, int pageNo);
	
}