/*
 * CityManagerDaoImpl.java
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
import cc.messcat.bases.BaseManagerDaoImpl;

public class CityManagerDaoImpl extends BaseManagerDaoImpl implements CityManagerDao {

	public City getByCityId(Long cityId){
		return this.cityDao.getByCityId(cityId);
	}
	
	public List<City> findCityByProvince(Long provinceId){
		return this.cityDao.findCityByProvince(provinceId);
	}
	
	public void addCity(City city) {
		this.cityDao.save(city);
	}
	
	public void modifyCity(City city) {
		this.cityDao.update(city);
	}
	
	public void removeCity(City city) {
		this.cityDao.delete(city);
	}

	public void removeCity(Long id) {
		this.cityDao.delete(id);
	}
	
	public City retrieveCity(Long id) {
		return (City) this.cityDao.get(id);
	}

	@SuppressWarnings("unchecked")
	public List retrieveAllCitys() {
		return this.cityDao.findAll();
	}
	
	public Pager retrieveCitysPager(int pageSize, int pageNo) {
		return this.cityDao.getPager(pageSize, pageNo);
	}

}