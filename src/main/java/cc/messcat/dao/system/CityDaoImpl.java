/*
 * CityDaoImpl.java
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
package cc.messcat.dao.system;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;

import cc.messcat.bases.BaseDaoImpl;
import cc.messcat.entity.City;
import cc.modules.commons.Pager;

public class CityDaoImpl extends BaseDaoImpl implements CityDao {
	
	public City getByCityId(Long cityId){
		List cityList = getHibernateTemplate().find("from City as c where c.cityId = ?", cityId);
		if (cityList.size() > 0)
			return (City) cityList.get(0);
		else
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<City> findCityByProvince(Long provinceId) {
		//getHibernateTemplate().clear();
		return getHibernateTemplate().find("from City where 1=1 and fatherId="+provinceId);
	}
	
	public void save(City city) {
		getHibernateTemplate().save(city);
	}
	
	public void update(City city) {
		getHibernateTemplate().update(city);
	}
	
	public void delete(City city) {
		getHibernateTemplate().delete(city);
	}

	public void delete(Long id) {
		getHibernateTemplate().delete(this.get(id));
	}
	
	public City get(Long id) {
		return (City) getHibernateTemplate().get(City.class, id);
	}

	@SuppressWarnings("unchecked")
	public List findAll() {
		return getHibernateTemplate().find("from City");
	}

	@SuppressWarnings("unchecked")
	public Pager getPager(int pageSize, int pageNo) {
		Session session = getHibernateTemplate().getSessionFactory().openSession();
		Criteria criteria = session.createCriteria(City.class);
		int rowCount = ((Integer) criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
		criteria.setProjection(null);
		int startIndex = pageSize * (pageNo - 1);
		criteria.setFirstResult(startIndex);
		criteria.setMaxResults(pageSize);
		List result = criteria.list();
		session.close();
		return new Pager(pageSize, pageNo, rowCount, result);
	}

}