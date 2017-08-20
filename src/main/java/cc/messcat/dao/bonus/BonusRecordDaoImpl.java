/*
 * BonusRecordDaoImpl.java
 * 
 * Create by MCGT
 * 
 * Create time 2015-07-08
 * 
 * Version 1.0
 * 
 * Copyright 2013 Messcat, Inc. All rights reserved.
 * 
 */
package cc.messcat.dao.bonus;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;

import cc.modules.commons.Pager;
import cc.messcat.entity.BonusRecord;
import cc.messcat.entity.Member;
import cc.messcat.bases.BaseDaoImpl;

public class BonusRecordDaoImpl extends BaseDaoImpl implements BonusRecordDao {

	public void save(BonusRecord bonusRecord) {
		getHibernateTemplate().save(bonusRecord);
	}
	
	public void update(BonusRecord bonusRecord) {
		getHibernateTemplate().update(bonusRecord);
	}
	
	public void delete(BonusRecord bonusRecord) {
		getHibernateTemplate().delete(bonusRecord);
	}

	public void delete(Long id) {
		getHibernateTemplate().delete(this.get(id));
	}
	
	public BonusRecord get(Long id) {
		return (BonusRecord) getHibernateTemplate().get(BonusRecord.class, id);
	}

	@SuppressWarnings("unchecked")
	public List findAll() {
		return getHibernateTemplate().find("from BonusRecord");
	}

	@SuppressWarnings("unchecked")
	public Pager getPager(int pageSize, int pageNo) {
		Session session = this.getSession();
		Criteria criteria = session.createCriteria(BonusRecord.class);
		int rowCount = ((Integer) criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
		criteria.setProjection(null);
		int startIndex = pageSize * (pageNo - 1);
		criteria.setFirstResult(startIndex);
		criteria.setMaxResults(pageSize);
		List result = criteria.list();
		return new Pager(pageSize, pageNo, rowCount, result);
	}

	@Override
	public Date sendTime(Member member) {
		Session session = this.getSession();
		StringBuffer sb = new StringBuffer();
		sb.append(" from BonusRecord b where b.member.id = "+member.getId()+" order by b.id desc");
		Query query = session.createQuery(sb.toString());
		List<BonusRecord> bl = new ArrayList<BonusRecord>();
		bl = query.list();
		return bl.get(0).getSendTime();
	}

}