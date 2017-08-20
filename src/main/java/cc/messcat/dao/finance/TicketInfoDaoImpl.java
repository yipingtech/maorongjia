/*
 * TicketInfoDaoImpl.java
 * 
 * Create by MCGT
 * 
 * Create time 2015-06-01
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
import org.hibernate.criterion.Restrictions;

import cc.messcat.bases.BaseDaoImpl;
import cc.messcat.entity.Member;
import cc.messcat.entity.MemberTicket;
import cc.messcat.entity.TicketInfo;
import cc.modules.commons.Pager;

public class TicketInfoDaoImpl extends BaseDaoImpl implements TicketInfoDao {
	
	/**
	 * 根据id查询卡券
	 */
	public TicketInfo get(Long id) {
		return (TicketInfo) getHibernateTemplate().get(TicketInfo.class, id);
	}
	
	/**
	 * 查询用户的过期卡券和未过期卡券
	 * @param member
	 * @param day
	 * @param pageStart
	 * @param pageSize
	 * @return
	 */
	public Pager queryTicketByMember(Member member,int pageStart,int pageSize,int flag){
		StringBuffer sql=new StringBuffer();
		if (flag==0) {
			//查询未使用
			sql.append("SELECT * FROM member_ticket WHERE NOW() < OVER_TIME AND STATUS='0'");
			//sql.append("SELECT * FROM member_ticket WHERE ADD_TIME>=ADDDATE(NOW(),-"+day+") and MEMBER_ID="+member.getId()+" and STATUS='0'");
		} else if(flag==1) {
			//查询已经使用
			sql.append("SELECT * FROM member_ticket WHERE NOW() < OVER_TIME AND STATUS='1'");
			//sql.append("SELECT * FROM member_ticket WHERE ADD_TIME>=ADDDATE(NOW(),-"+day+") and MEMBER_ID="+member.getId()+" and STATUS='1'");
		}else if(flag==2){
			//查询已过期
			sql.append("SELECT * FROM member_ticket WHERE NOW() > OVER_TIME AND STATUS='0'");
			//sql.append("SELECT * FROM member_ticket WHERE ADD_TIME<=ADDDATE(NOW(),-"+day+") and MEMBER_ID="+member.getId()+" and STATUS='0'");
		}
		sql.append(" order by ID desc");
		Session session = this.getSession();
		int pageIndex=(pageStart-1)*pageSize;
		return new Pager(pageSize, pageStart, session.createSQLQuery(sql.toString()).addEntity(MemberTicket.class).list().size(),
				session.createSQLQuery(sql.toString()).addEntity(MemberTicket.class).setMaxResults(pageSize).setFirstResult(pageIndex).list());
	}
	

	@SuppressWarnings("rawtypes")
	public Pager getPager(int pageSize, int pageNo) {
		Session session = this.getSession();
		Criteria criteria = session.createCriteria(TicketInfo.class);
		criteria.add(Restrictions.eq("status", "1"));
		int rowCount = ((Integer) criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
		criteria.setProjection(null);
		int startIndex = pageSize * (pageNo - 1);
		criteria.setFirstResult(startIndex);
		criteria.setMaxResults(pageSize);
		List result = criteria.list();
		return new Pager(pageSize, pageNo, rowCount, result);
	}

}