/*
 * AddressManagerDao.java
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

import java.util.List;

import cc.modules.commons.Pager;
import cc.messcat.entity.Address;
import cc.messcat.entity.Member;

public interface AddressManagerDao {

	public void addAddress(Address address);
	
	public void modifyAddress(Address address);

	public void setDefaultAddress(Address address);
	
	public void removeAddress(Address address);
	
	public void removeAddress(Long id);
	
	public Address retrieveAddress(Long id);
	
	public List<Address> retrieveAllAddresss(Member member);
	
	public Pager retrieveAddresssPager(int pageSize, int pageNo);
	
	/**
	 * 获取默认地址
	 * @param member
	 * @return
	 */
	public Address getDefaultAddress(Member member);
}