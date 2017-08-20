/*
 * CityManagerDao.java
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
import cc.messcat.entity.City;

public interface CityManagerDao {

	public City getByCityId(Long cityId);
	
	public List<City> findCityByProvince(Long provinceId);
	
	public void addCity(City city);
	
	public void modifyCity(City city);
	
	public void removeCity(City city);
	
	public void removeCity(Long id);
	
	public City retrieveCity(Long id);
	
	@SuppressWarnings("unchecked")
	public List retrieveAllCitys();
	
	public Pager retrieveCitysPager(int pageSize, int pageNo);
	
}