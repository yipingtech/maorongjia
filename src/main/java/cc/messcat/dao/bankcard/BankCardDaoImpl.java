/*
 * BankCardDaoImpl.java
 * 
 * Create by MCGT
 * 
 * Create time 2015-08-07
 * 
 * Version 1.0
 * 
 * Copyright 2013 Messcat, Inc. All rights reserved.
 * 
 */
package cc.messcat.dao.bankcard;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;

import cc.modules.commons.Pager;
import cc.messcat.entity.BankCard;
import cc.messcat.entity.CashInfo;
import cc.messcat.entity.Member;
import cc.messcat.bases.BaseDaoImpl;

public class BankCardDaoImpl extends BaseDaoImpl implements BankCardDao {

	public void save(BankCard bankCard) {
		getHibernateTemplate().save(bankCard);
	}
	
	public void update(BankCard bankCard) {
		getHibernateTemplate().update(bankCard);
	}
	
	public void delete(BankCard bankCard) {
		getHibernateTemplate().delete(bankCard);
	}

	public void delete(Long id) {
		getHibernateTemplate().delete(this.get(id));
	}
	
	public BankCard get(Long id) {
		return (BankCard) getHibernateTemplate().get(BankCard.class, id);
	}

	@SuppressWarnings("unchecked")
	public List findAll() {
		return getHibernateTemplate().find("from BankCard");
	}

	@SuppressWarnings("unchecked")
	public Pager getPager(int pageSize, int pageNo) {
		Session session = this.getSession();
		Criteria criteria = session.createCriteria(BankCard.class);
		int rowCount = ((Integer) criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
		criteria.setProjection(null);
		int startIndex = pageSize * (pageNo - 1);
		criteria.setFirstResult(startIndex);
		criteria.setMaxResults(pageSize);
		List result = criteria.list();
		return new Pager(pageSize, pageNo, rowCount, result);
	}

	@Override
	public List<BankCard> findByMember(Member member) {
		Session session = this.getSession();
		StringBuffer sql = new StringBuffer();
		sql.append("from BankCard b where b.status = '1' and b.member.id = "+member.getId()+" order by b.bankCardTime desc");
		Query query = session.createQuery(sql.toString());
		return query.list();
	}

	@Override
	public Pager findCashRecordByMember(Member member,int pageSize, int pageNo) {
		Session session = this.getSession();
		StringBuffer sql = new StringBuffer();
		sql.append("from CashInfo c where c.member.id = "+member.getId()+" order by c.id desc");
		Query query = session.createQuery(sql.toString());
		int startIndex = pageSize * (pageNo - 1);
		query.setFirstResult(startIndex);
		query.setMaxResults(pageSize);
		List result = query.list();
	    int rowCount = result.size();
		return new Pager(pageSize, pageNo, rowCount, result);
	}

}