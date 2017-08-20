/*
 * AreaDaoImpl.java
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
import cc.messcat.entity.Area;
import cc.modules.commons.Pager;

public class AreaDaoImpl extends BaseDaoImpl implements AreaDao {

	public Area getByAreaId(Long areaId){
		List areaList = getHibernateTemplate().find("from Area as a where a.areaId = ?", areaId);
		if (areaList.size() > 0)
			return (Area) areaList.get(0);
		else
			return null;
	}
	
	public List<Area> findAreaByCity(Long cityId) {
		//getHibernateTemplate().clear();
		return getHibernateTemplate().find("from Area where 1=1 and fatherId="+cityId);
	}
	
	public void save(Area area) {
		getHibernateTemplate().save(area);
	}
	
	public void update(Area area) {
		getHibernateTemplate().update(area);
	}
	
	public void delete(Area area) {
		getHibernateTemplate().delete(area);
	}

	public void delete(Long id) {
		getHibernateTemplate().delete(this.get(id));
	}
	
	public Area get(Long id) {
		return (Area) getHibernateTemplate().get(Area.class, id);
	}

	@SuppressWarnings("unchecked")
	public List findAll() {
		return getHibernateTemplate().find("from Area");
	}

	@SuppressWarnings("unchecked")
	public Pager getPager(int pageSize, int pageNo) {
		Session session = getHibernateTemplate().getSessionFactory().openSession();
		Criteria criteria = session.createCriteria(Area.class);
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