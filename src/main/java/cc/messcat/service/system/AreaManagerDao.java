/*
 * AreaManagerDao.java
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

public interface AreaManagerDao {

	public Area getByAreaId(Long areaId);
	
	public List<Area> findAreaByCity(Long cityId);
	
	public void addArea(Area area);
	
	public void modifyArea(Area area);
	
	public void removeArea(Area area);
	
	public void removeArea(Long id);
	
	public Area retrieveArea(Long id);
	
	@SuppressWarnings("unchecked")
	public List retrieveAllAreas();
	
	public Pager retrieveAreasPager(int pageSize, int pageNo);
	
}