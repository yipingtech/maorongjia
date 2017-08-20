/*
 * AddressDaoImpl.java
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
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import cc.modules.commons.Pager;
import cc.messcat.entity.Address;
import cc.messcat.bases.BaseDaoImpl;

public class AddressDaoImpl extends BaseDaoImpl implements AddressDao {

	public void save(Address address) {
		getHibernateTemplate().save(address);
	}
	
	public void update(Address address) {
		getHibernateTemplate().update(address);
	}
	
	public void delete(Address address) {
		getHibernateTemplate().delete(address);
	}

	public void delete(Long id) {
		getHibernateTemplate().delete(this.get(id));
	}
	
	public Address get(Long id) {
		return (Address) getHibernateTemplate().get(Address.class, id);
	}

//	//查询当个用户所有地址
//	public List findAll(String memberId) {
//		String hql="select a from Address as a left join a.member as m where m.loginName=?";
//		return getHibernateTemplate().find(hql,memberId);
//	}
	
	//设置默认地址
	public List<Address> setDefaultAddress(Address address){
		String hql="select a from Address as a left join a.member as m where a.isdefault='1' and m.loginName=?";
		return getHibernateTemplate().find(hql,address.getMember().getLoginName());
	}

	public Pager getPager(int pageSize, int pageNo) {
		Session session = this.getSession();
		Criteria criteria = session.createCriteria(Address.class);
		int rowCount = ((Integer) criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
		criteria.setProjection(null);
		int startIndex = pageSize * (pageNo - 1);
		criteria.setFirstResult(startIndex);
		criteria.setMaxResults(pageSize);
		List result = criteria.list();
		return new Pager(pageSize, pageNo, rowCount, result);
	}

}