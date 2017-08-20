/*
 * BonusDaoImpl.java
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
package cc.messcat.dao.finance;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import cc.modules.commons.Pager;
import cc.messcat.entity.Bonus;
import cc.messcat.bases.BaseDaoImpl;

public class BonusDaoImpl extends BaseDaoImpl implements BonusDao {

	public void save(Bonus bonus) {
		getHibernateTemplate().save(bonus);
	}
	
	public void update(Bonus bonus) {
		getHibernateTemplate().update(bonus);
	}
	
	public void delete(Bonus bonus) {
		getHibernateTemplate().delete(bonus);
	}

	public void delete(Long id) {
		getHibernateTemplate().delete(this.get(id));
	}
	
	public Bonus get(Long id) {
		return (Bonus) getHibernateTemplate().get(Bonus.class, id);
	}

	@SuppressWarnings("unchecked")
	public List findAll() {
		return getHibernateTemplate().find("from Bonus");
	}

	@SuppressWarnings("unchecked")
	public Pager getPager(int pageSize, int pageNo) {
		Session session = this.getSession();
		Criteria criteria = session.createCriteria(Bonus.class);
		int rowCount = ((Integer) criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
		criteria.setProjection(null);
		int startIndex = pageSize * (pageNo - 1);
		criteria.setFirstResult(startIndex);
		criteria.setMaxResults(pageSize);
		List result = criteria.list();
		return new Pager(pageSize, pageNo, rowCount, result);
	}

}