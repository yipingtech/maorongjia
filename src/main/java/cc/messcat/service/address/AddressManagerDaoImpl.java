/*
 * AddressManagerDaoImpl.java
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
package cc.messcat.service.address;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cc.modules.commons.Pager;
import cc.messcat.entity.Address;
import cc.messcat.entity.Member;
import cc.messcat.bases.BaseManagerDaoImpl;

public class AddressManagerDaoImpl extends BaseManagerDaoImpl implements AddressManagerDao {

	private static final long serialVersionUID = 1L;

	//添加地址
	public void addAddress(Address address) {
		//判断是否为第一条数据，是则设为默认
		List<Address> addressList=this.retrieveAllAddresss(address.getMember());
		address.setAddTime(new Date());
		if (addressList.size()==0) {
			address.setIsdefault("1");          //默认地址
		} else {
			address.setIsdefault("0");
		}
		this.addressDao.save(address);
	}
	
	//修改地址
	public void modifyAddress(Address address) {
		this.addressDao.update(address);
	}
	
	/**
	 * 获取默认地址
	 * @param member
	 * @return
	 */
	public Address getDefaultAddress(Member member){
		Map<String, Object> attrs = new HashMap<String, Object>();
		attrs.put("member",member);
		attrs.put("isdefault","1");
		Address address = this.addressDao.query(Address.class, attrs);
		return address;
	}
	
	//设默认地址
	public void setDefaultAddress(Address address){
		Map<String, Object> attrs1 = new HashMap<String, Object>();
		Map<String, Object> props1 = new HashMap<String, Object>();
		Map<String, Object> attrs2 = new HashMap<String, Object>();
		Map<String, Object> props2 = new HashMap<String, Object>();
		attrs1.put("member",address.getMember());
		attrs1.put("isdefault","1");
		props1.put("isdefault", "0");
		addressDao.update(Address.class, props1, attrs1);
		attrs2.put("id",address.getId());
		props2.put("isdefault", "1");
		//List<Address> addressList=addressDao.setDefaultAddress(address);       //查询出当前的默认地址将其修改		
		addressDao.update(Address.class, props2, attrs2);
	}
	
	//移除地址根据对象
	public void removeAddress(Address address) {
		this.addressDao.delete(address);
	}

	//移除地址根据id
	public void removeAddress(Long id) {
		this.addressDao.delete(id);
	}
	
	//查询地址根据id
	public Address retrieveAddress(Long id) {
		return (Address) this.addressDao.get(id);
	}

	//用户查询自己所有的地址
	public List<Address> retrieveAllAddresss(Member member) {
		Map<String, Object> attrs = new HashMap<String, Object>();
		attrs.put("member", member);
		return addressDao.queryList(Address.class, "id", "desc", attrs);
	}
	
	public Pager retrieveAddresssPager(int pageSize, int pageNo) {
		return this.addressDao.getPager(pageSize, pageNo);
	}

}