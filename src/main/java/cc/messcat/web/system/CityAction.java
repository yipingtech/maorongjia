/*
 * CityAction.java
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
import cc.messcat.entity.City;

public class CityAction extends PageAction {
	
	private Long id;	
	private City city;	
	private List<City> citys;
	
	@SuppressWarnings("unchecked")
	public String retrieveAllCitys() throws Exception {
		try {
			super.pager = this.cityManagerDao.retrieveCitysPager(pageSize, pageNo);
			this.citys = super.pager.getResultList();
		} catch (Exception ex) {
			addActionMessage("Load page error!");
		}
		return "success";
	}
	
	public String retrieveCityById() throws Exception {
		try {
			this.city = this.cityManagerDao.retrieveCity(id);
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
			this.city = this.cityManagerDao.retrieveCity(id);
		} catch (Exception ex) {
			addActionMessage("Load page error!");
		}
		return "viewPage";
	}
	
	public String newCity() throws Exception {
		try {
			this.cityManagerDao.addCity(this.city);
			addActionMessage("New successfully!");
		} catch (Exception ex) {
			this.addActionError(ex.getMessage());
			addActionMessage("New fail!");
		}
		return this.retrieveAllCitys();
	}
	
	public String editCity() throws Exception {
		try {
			City city0 = this.cityManagerDao.retrieveCity(this.id);
			city0.setCityId(this.city.getCityId());
			city0.setCity(this.city.getCity());
			city0.setFatherId(this.city.getFatherId());

			this.cityManagerDao.modifyCity(city0);
			addActionMessage("Update successfully!");
		} catch (Exception ex) {
			this.addActionError(ex.getMessage());
			addActionMessage("Update fail!");
		}
		return this.retrieveAllCitys();
	}
	
	public String delCity() throws Exception {
		try {
			this.cityManagerDao.removeCity(this.id);
			addActionMessage("Delete successfully!");
		} catch (Exception ex) {
			this.addActionError(ex.getMessage());
			addActionMessage("Delete fail!");
		}
		return this.retrieveAllCitys();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public List<City> getCitys() {
		return citys;
	}

	public void setCitys(
			List<City> citys) {
		this.citys = citys;
	}

}