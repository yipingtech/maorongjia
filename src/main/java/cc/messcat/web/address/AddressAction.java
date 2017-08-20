/*
 * AddressAction.java
 * 
 * Create by MCGT
 * 
 * Create time 2015-04-17
 * 
 * Version 1.0
 * 
 * Copyright 2013 Messcat, Inc. All rights reserved.
 * 
 */
package cc.messcat.web.address;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import cc.modules.commons.PageAction;
import cc.messcat.entity.Address;
import cc.messcat.entity.Area;
import cc.messcat.entity.City;
import cc.messcat.entity.Member;
import cc.messcat.entity.Province;

public class AddressAction extends PageAction {
	

	private static final long serialVersionUID = 1L;
	private Long id;	
	private Address address;	
	private List<Address> addresss;
	private Member member;   
	private List<City> cities;
	private List<Area> areas;
	private Long province_id;
	private Long city_id;
	private int flag;
	//通过session获取用户信息
	
	
	
	public String findCityByProvince() throws Exception{
		Province province = this.provinceManagerDao.retrieveProvince(province_id);
		this.cities=this.cityManagerDao.findCityByProvince(province.getProvinceId());
		return "success_city";
	}
    public String findAreaByCity() throws Exception{
    	City city = this.cityManagerDao.retrieveCity(city_id);
		this.areas=this.areaManagerDao.findAreaByCity(city.getCityId());
		return "success_area";
	}
	
	//查询所有地址
	@SuppressWarnings("unchecked")
	public String retrieveAllAddresss() throws Exception {
		try {
			super.pager = this.addressManagerDao.retrieveAddresssPager(pageSize, pageNo);
			this.addresss = super.pager.getResultList();
		} catch (Exception ex) {
			addActionMessage("Load page error!");
		}
		return "success";
	}
	
	//查询所有地址根据用户
	public String retrieveAllresssByUser() throws Exception {
		try {
			addresss = this.addressManagerDao.retrieveAllAddresss(member);
		} catch (Exception ex) {
			addActionMessage("Load page error!");
		}
		return "success";
	}

	//查询地址根据id
	public String retrieveAddressById() throws Exception {
		try {
			this.address = this.addressManagerDao.retrieveAddress(id);
		} catch (Exception ex) {
			addActionMessage("Load page error!");
		}
		return "editPage";
	}

	//跳转到添加的页面
	public String newPage() throws Exception {
		return "newPage";
	}
	
	//显示地址详情
	public String viewPage() throws Exception {
		try {
			this.address = this.addressManagerDao.retrieveAddress(id);
		} catch (Exception ex) {
			addActionMessage("Load page error!");
		}
		return "viewPage";
	}
	
	//添加新的地址
	public String newAddress() throws Exception {
		try {
			address.setMember(member);
			this.addressManagerDao.addAddress(this.address);
			addActionMessage("New successfully!");
		} catch (Exception ex) {
			this.addActionError(ex.getMessage());
			addActionMessage("New fail!");
		}
		return "edit_success";
	}
	
	//设为默认地址
	public String setDefaultAddress() throws Exception {
		try {
			address.setMember(member);
			addressManagerDao.setDefaultAddress(address);
			addActionMessage("New successfully!");
		} catch (Exception ex) {
			this.addActionError(ex.getMessage());
			addActionMessage("New fail!");
		}
		return "edit_success";
	}
	
	
	//编辑地址
	public String editAddress() throws Exception {
		try {
			Address address0 = this.addressManagerDao.retrieveAddress(this.id);
			address0.setProvince(this.address.getProvince());
			address0.setCity(this.address.getCity());
			address0.setArea(this.address.getArea());
			address0.setAddress(this.address.getAddress());
			address0.setEditTime(new Date());
			address0.setConsignee(this.address.getConsignee());
			address0.setPostcode(this.address.getPostcode());
			address0.setTelephone(this.address.getTelephone());
			address0.setCellphone(this.address.getCellphone());
			address0.setEmail(this.address.getEmail());
			//address0.setIsdefault(this.address.getIsdefault());

			this.addressManagerDao.modifyAddress(address0);
			addActionMessage("Update successfully!");
		} catch (Exception ex) {
			this.addActionError(ex.getMessage());
			addActionMessage("Update fail!");
		}
		return "edit_success";
	}
	
	//删除地址
	public String delAddress() throws Exception {
		try {
			this.addressManagerDao.removeAddress(this.id);
			addActionMessage("Delete successfully!");
		} catch (Exception ex) {
			this.addActionError(ex.getMessage());
			addActionMessage("Delete fail!");
		}
		return "edit_success";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<Address> getAddresss() {
		return addresss;
	}

	public void setAddresss(
			List<Address> addresss) {
		this.addresss = addresss;
	}
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	public List<City> getCities() {
		return cities;
	}
	public void setCities(List<City> cities) {
		this.cities = cities;
	}
	public List<Area> getAreas() {
		return areas;
	}
	public void setAreas(List<Area> areas) {
		this.areas = areas;
	}
	public Long getProvince_id() {
		return province_id;
	}
	public void setProvince_id(Long province_id) {
		this.province_id = province_id;
	}
	public Long getCity_id() {
		return city_id;
	}
	public void setCity_id(Long city_id) {
		this.city_id = city_id;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}

}