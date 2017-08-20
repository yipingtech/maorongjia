/*
 * ReportInfoDaoImpl.java
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
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import cc.modules.commons.Pager;
import cc.messcat.entity.Member;
import cc.messcat.entity.ReportInfo;
import cc.messcat.bases.BaseDaoImpl;

public class ReportInfoDaoImpl extends BaseDaoImpl implements ReportInfoDao {

	
	public ReportInfo get(Long id) {
		return (ReportInfo) getHibernateTemplate().get(ReportInfo.class, id);
	}
	
	/**
	 * 判断当天是否已经签到
	 * @param member
	 * @return
	 */
	@Override
	public List<ReportInfo> judgeReport(Member member,String date) {
		String hqlString  = "select r from ReportInfo as r left join r.memberId as m where m.loginName="+member.getLoginName()+" and r.status='1' and r.reportTime like '"+date+"%'";
		return this.getHibernateTemplate().find(hqlString);
	}
	
	
	@SuppressWarnings("unchecked")
	public Pager getPager(int pageSize, int pageNo) {
		Session session = this.getSession();
		Criteria criteria = session.createCriteria(ReportInfo.class);
		int rowCount = ((Integer) criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
		criteria.setProjection(null);
		int startIndex = pageSize * (pageNo - 1);
		criteria.setFirstResult(startIndex);
		criteria.setMaxResults(pageSize);
		List result = criteria.list();
		return new Pager(pageSize, pageNo, rowCount, result);
	}

}