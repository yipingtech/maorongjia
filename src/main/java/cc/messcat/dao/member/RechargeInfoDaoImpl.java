/*
 * RechargeInfoDaoImpl.java
 * 
 * Create by MCGT
 * 
 * Create time 2015-04-22
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
import cc.messcat.entity.CommissionInfo;
import cc.messcat.entity.Member;
import cc.messcat.entity.RechargeInfo;
import cc.messcat.bases.BaseDaoImpl;

public class RechargeInfoDaoImpl extends BaseDaoImpl implements RechargeInfoDao {

	
	public void update(RechargeInfo rechargeInfo) {
		getHibernateTemplate().update(rechargeInfo);
	}
	
	public RechargeInfo get(Long id) {
		return (RechargeInfo) getHibernateTemplate().get(RechargeInfo.class, id);
	}
	
	/**
	 * 查询历史累计佣金余额
	 */
	public Double queryTotalRechargeInfo(Member member){
		String hql="select sum(r.rechargeAmount) from RechargeInfo as r where r.status = '1' and r.memberId.id='"+member.getId()+"'";
		Session session = this.getSession();
		Query query = session.createQuery(hql);
		Double count=((Double) query.uniqueResult()).doubleValue();
		return count;
	}

	public Pager getPager(int pageSize, int pageNo) {
		Session session = this.getSession();
		Criteria criteria = session.createCriteria(RechargeInfo.class);
		int rowCount = ((Integer) criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
		criteria.setProjection(null);
		int startIndex = pageSize * (pageNo - 1);
		criteria.setFirstResult(startIndex);
		criteria.setMaxResults(pageSize);
		List result = criteria.list();
		return new Pager(pageSize, pageNo, rowCount, result);
	}


}