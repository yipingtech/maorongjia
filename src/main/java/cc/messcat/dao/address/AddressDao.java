/*
 * AddressDao.java
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
package cc.messcat.dao.address;

import java.util.List;
import cc.modules.commons.Pager;
import cc.messcat.bases.BaseDao;
import cc.messcat.entity.Address;

public interface AddressDao extends BaseDao{

	public void save(Address address);
	
	public void update(Address address);
	
	public void delete(Address address);
	
	public void delete(Long id);
	
	public Address get(Long id);
	
	//设置默认地址
	public List<Address> setDefaultAddress(Address address);
	
	public Pager getPager(int pageSize, int pageNo);
	
}