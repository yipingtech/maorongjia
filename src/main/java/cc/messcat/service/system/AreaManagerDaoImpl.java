/*
 * AreaManagerDaoImpl.java
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
import cc.messcat.entity.Area;
import cc.messcat.bases.BaseManagerDaoImpl;

public class AreaManagerDaoImpl extends BaseManagerDaoImpl implements AreaManagerDao {

	public Area getByAreaId(Long areaId){
		return this.areaDao.getByAreaId(areaId);
	}
	
	public List<Area> findAreaByCity(Long cityId){
		return this.areaDao.findAreaByCity(cityId);
	}
	
	public void addArea(Area area) {
		this.areaDao.save(area);
	}
	
	public void modifyArea(Area area) {
		this.areaDao.update(area);
	}
	
	public void removeArea(Area area) {
		this.areaDao.delete(area);
	}

	public void removeArea(Long id) {
		this.areaDao.delete(id);
	}
	
	public Area retrieveArea(Long id) {
		return (Area) this.areaDao.get(id);
	}

	@SuppressWarnings("unchecked")
	public List retrieveAllAreas() {
		return this.areaDao.findAll();
	}
	
	public Pager retrieveAreasPager(int pageSize, int pageNo) {
		return this.areaDao.getPager(pageSize, pageNo);
	}

}