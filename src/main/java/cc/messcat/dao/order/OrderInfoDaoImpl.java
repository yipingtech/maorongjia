/*
 * OrderInfoDaoImpl.java
 * 
 * Create by MCGT
 * 
 * Create time 2015-04-10
 * 
 * Version 1.0
 * 
 * Copyright 2013 Messcat, Inc. All rights reserved.
 * 
 */
package cc.messcat.dao.order;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import cc.modules.commons.Pager;
import cc.messcat.entity.OrderInfo;
import cc.messcat.bases.BaseDaoImpl;

public class OrderInfoDaoImpl extends BaseDaoImpl implements OrderInfoDao {

	public void save(OrderInfo orderInfo) {
		orderInfo.setEvaluateStatus("0");
		getHibernateTemplate().save(orderInfo);
	}
	
	public void update(OrderInfo orderInfo) {
		getHibernateTemplate().update(orderInfo);
	}
	
	public void delete(OrderInfo orderInfo) {
		getHibernateTemplate().delete(orderInfo);
	}

	public void delete(Long id) {
		getHibernateTemplate().delete(this.get(id));
	}
	
	public OrderInfo get(Long id) {
		return (OrderInfo) getHibernateTemplate().get(OrderInfo.class, id);
	}

	@SuppressWarnings("unchecked")
	public List findAll() {
		return getHibernateTemplate().find("from OrderInfo");
	}

	@SuppressWarnings("unchecked")
	public Pager getPager(int pageSize, int pageNo) {
		Session session = this.getSession();
		Criteria criteria = session.createCriteria(OrderInfo.class);
		int rowCount = ((Integer) criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
		criteria.setProjection(null);
		int startIndex = pageSize * (pageNo - 1);
		criteria.setFirstResult(startIndex);
		criteria.setMaxResults(pageSize);
		List result = criteria.list();
		return new Pager(pageSize, pageNo, rowCount, result);
	}

}