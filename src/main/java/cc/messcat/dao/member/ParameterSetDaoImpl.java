/*
 * ParameterSetDaoImpl.java
 * 
 * Create by MCGT
 * 
 * Create time 2015-04-24
 * 
 * Version 1.0
 * 
 * Copyright 2013 Messcat, Inc. All rights reserved.
 * 
 */
package cc.messcat.dao.member;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import cc.modules.commons.Pager;
import cc.messcat.entity.ParameterSet;
import cc.messcat.bases.BaseDaoImpl;

public class ParameterSetDaoImpl extends BaseDaoImpl implements ParameterSetDao {

	public void save(ParameterSet parameterSet) {
		getHibernateTemplate().save(parameterSet);
	}
	
	public void update(ParameterSet parameterSet) {
		getHibernateTemplate().update(parameterSet);
	}
	
	public void delete(ParameterSet parameterSet) {
		getHibernateTemplate().delete(parameterSet);
	}

	public void delete(Long id) {
		getHibernateTemplate().delete(this.get(id));
	}
	
	public ParameterSet get(Long id) {
		return (ParameterSet) getHibernateTemplate().get(ParameterSet.class, id);
	}

	@SuppressWarnings("unchecked")
	public List findAll() {
		return getHibernateTemplate().find("from ParameterSet");
	}

	@SuppressWarnings("unchecked")
	public Pager getPager(int pageSize, int pageNo) {
		Session session = this.getSession();
		Criteria criteria = session.createCriteria(ParameterSet.class);
		int rowCount = ((Integer) criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
		criteria.setProjection(null);
		int startIndex = pageSize * (pageNo - 1);
		criteria.setFirstResult(startIndex);
		criteria.setMaxResults(pageSize);
		List result = criteria.list();
		return new Pager(pageSize, pageNo, rowCount, result);
	}

}