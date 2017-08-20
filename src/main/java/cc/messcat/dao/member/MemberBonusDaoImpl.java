/*
 * MemberBonusDaoImpl.java
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
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import cc.modules.commons.Pager;
import cc.messcat.entity.Member;
import cc.messcat.entity.MemberBonus;
import cc.messcat.bases.BaseDaoImpl;

public class MemberBonusDaoImpl extends BaseDaoImpl implements MemberBonusDao {

	public void save(MemberBonus memberBonus) {
		getHibernateTemplate().save(memberBonus);
	}
	
	public void update(MemberBonus memberBonus) {
		getHibernateTemplate().update(memberBonus);
	}
	
	public void delete(MemberBonus memberBonus) {
		getHibernateTemplate().delete(memberBonus);
	}

	public void delete(Long id) {
		getHibernateTemplate().delete(this.get(id));
	}
	
	/**
	 * 红包标为已用
	 */
	public void changeBonusUnable(Long id) {
		try {
    		Session session = this.getSession();
    		StringBuffer str = new StringBuffer();
    		str.append("update MemberBonus set status = 0 where id="+id);
			Query query = session.createQuery(str.toString());
    		query.executeUpdate();
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
	}
	
	public MemberBonus get(Long id) {
		return (MemberBonus) getHibernateTemplate().get(MemberBonus.class, id);
	}

	@SuppressWarnings("unchecked")
	public List findAll() {
		return getHibernateTemplate().find("from MemberBonus");
	}
	
	/**
	 * 查询过期红包(设定的时间小于当前时间)
	 */
	@SuppressWarnings("unchecked")
	public List<MemberBonus> queryOverdueBonus(Member member) {
		String hqlString = "select b from MemberBonus as b left join b.member as m where m.loginName=? and b.status='1' and b.validPeriod < CURDATE()";
		return this.getHibernateTemplate().find(hqlString,member.getLoginName());
	}
	
	/**
	 * 查询未用和已用红包(设定的时间还大于当前时间就是未过期)
	 */
	@SuppressWarnings("unchecked")
	public List<MemberBonus> queryBonus(Member member,String status) {
		StringBuffer hql = new StringBuffer("select b from MemberBonus as b left join b.member as m where m.loginName=? and b.status=?");
		if (status.equals("1")) {
			hql.append(" and b.validPeriod > CURDATE()");
			return this.getHibernateTemplate().find(hql.toString(),new Object[]{member.getLoginName(),status});
		} else {
			return this.getHibernateTemplate().find(hql.toString(),new Object[]{member.getLoginName(),status});
		}
	}

	public Pager getPager(int pageSize, int pageNo) {
		Session session = this.getSession();
		Criteria criteria = session.createCriteria(MemberBonus.class);
		int rowCount = ((Integer) criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
		criteria.setProjection(null);
		int startIndex = pageSize * (pageNo - 1);
		criteria.setFirstResult(startIndex);
		criteria.setMaxResults(pageSize);
		List result = criteria.list();
		return new Pager(pageSize, pageNo, rowCount, result);
	}

}