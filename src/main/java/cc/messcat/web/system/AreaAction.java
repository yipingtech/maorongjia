/*
 * AreaAction.java
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
import cc.messcat.entity.Area;

public class AreaAction extends PageAction {
	
	private Long id;
	
	private Area area;
	
	private List<Area> areas;
	
	@SuppressWarnings("unchecked")
	public String retrieveAllAreas() throws Exception {
		try {
			super.pager = this.areaManagerDao.retrieveAreasPager(pageSize, pageNo);
			this.areas = super.pager.getResultList();
		} catch (Exception ex) {
			addActionMessage("Load page error!");
		}
		return "success";
	}
	
	public String retrieveAreaById() throws Exception {
		try {
			this.area = this.areaManagerDao.retrieveArea(id);
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
			this.area = this.areaManagerDao.retrieveArea(id);
		} catch (Exception ex) {
			addActionMessage("Load page error!");
		}
		return "viewPage";
	}
	
	public String newArea() throws Exception {
		try {
			this.areaManagerDao.addArea(this.area);
			addActionMessage("New successfully!");
		} catch (Exception ex) {
			this.addActionError(ex.getMessage());
			addActionMessage("New fail!");
		}
		return this.retrieveAllAreas();
	}
	
	public String editArea() throws Exception {
		try {
			Area area0 = this.areaManagerDao.retrieveArea(this.id);
			area0.setAreaId(this.area.getAreaId());
			area0.setArea(this.area.getArea());
			area0.setFatherId(this.area.getFatherId());

			this.areaManagerDao.modifyArea(area0);
			addActionMessage("Update successfully!");
		} catch (Exception ex) {
			this.addActionError(ex.getMessage());
			addActionMessage("Update fail!");
		}
		return this.retrieveAllAreas();
	}
	
	public String delArea() throws Exception {
		try {
			this.areaManagerDao.removeArea(this.id);
			addActionMessage("Delete successfully!");
		} catch (Exception ex) {
			this.addActionError(ex.getMessage());
			addActionMessage("Delete fail!");
		}
		return this.retrieveAllAreas();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public List<Area> getAreas() {
		return areas;
	}

	public void setAreas(
			List<Area> areas) {
		this.areas = areas;
	}

}