/*
 * ProvinceDaoImpl.java
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
import cc.modules.commons.Pager;
import cc.messcat.entity.Province;
import cc.messcat.entity.Users;
import cc.messcat.bases.BaseDaoImpl;

public class ProvinceDaoImpl extends BaseDaoImpl implements ProvinceDao {

	public Province getByProvinceId(Long provinceId){
		List provinceList = getHibernateTemplate().find("from Province as p where p.provinceId = ?", provinceId);
		if (provinceList.size() > 0)
			return (Province) provinceList.get(0);
		else
			return null;
	}
	
	public void save(Province province) {
		getHibernateTemplate().save(province);
	}
	
	public void update(Province province) {
		getHibernateTemplate().update(province);
	}
	
	public void delete(Province province) {
		getHibernateTemplate().delete(province);
	}

	public void delete(Long id) {
		getHibernateTemplate().delete(this.get(id));
	}
	
	public Province get(Long id) {
		return (Province) getHibernateTemplate().get(Province.class, id);
	}

	@SuppressWarnings("unchecked")
	public List findAll() {
		return getHibernateTemplate().find("from Province");
	}

	@SuppressWarnings("unchecked")
	public Pager getPager(int pageSize, int pageNo) {
		Session session = getHibernateTemplate().getSessionFactory().openSession();
		Criteria criteria = session.createCriteria(Province.class);
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