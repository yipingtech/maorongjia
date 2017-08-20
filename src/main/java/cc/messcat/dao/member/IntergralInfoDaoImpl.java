/*
 * IntergralInfoDaoImpl.java
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
import cc.modules.util.ObjValid;
import cc.messcat.entity.IntergralInfo;
import cc.messcat.entity.Member;
import cc.messcat.bases.BaseDaoImpl;

public class IntergralInfoDaoImpl extends BaseDaoImpl implements IntergralInfoDao {
	
	public void update(IntergralInfo intergralInfo) {
		getHibernateTemplate().update(intergralInfo);
	}
	
	public void delete(IntergralInfo intergralInfo) {
		getHibernateTemplate().delete(intergralInfo);
	}
	
	public IntergralInfo get(Long id) {
		return (IntergralInfo) getHibernateTemplate().get(IntergralInfo.class, id);
	}
	
	public int countEarnTimes(Member member, String startDate, String endDate){
		int count = 0;
		try{
			StringBuffer str = new StringBuffer();
			//基础数据
			str.append("select count(i.comments) from IntergralInfo i left join i.intergralRule r where i.status = 1 and r.id=3");
			
			if(ObjValid.isValid(member)) {
				str.append(" and i.memberId.id = '"+member.getId()+"' ");
			}
			if(ObjValid.isValid(startDate)&&ObjValid.isValid(endDate)){
				str.append(" and i.addTime between '"+startDate+"' and '"+endDate+"' ");
			}
			Session session = this.getSession();
			Query query = session.createQuery(str.toString());
			count = ((Long) query.uniqueResult()).intValue();
		}catch(Exception e){
			e.printStackTrace();
		}
		return count;
	}
	
	/**
	 * 查询历史累计积分
	 */
	public int queryTotalIntergralInfos(Member member){
		String hql="select sum(i.intergral) from IntergralInfo as i where i.status = '1' and i.memberId.id='"+member.getId()+"'";
		Session session = this.getSession();
		Query query = session.createQuery(hql);
		int count=((Long) query.uniqueResult()).intValue();
		return count;
	}

	@SuppressWarnings("unchecked")
	public Pager getPager(int pageSize, int pageNo) {
		Session session = this.getSession();
		Criteria criteria = session.createCriteria(IntergralInfo.class);
		int rowCount = ((Integer) criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
		criteria.setProjection(null);
		int startIndex = pageSize * (pageNo - 1);
		criteria.setFirstResult(startIndex);
		criteria.setMaxResults(pageSize);
		List result = criteria.list();
		return new Pager(pageSize, pageNo, rowCount, result);
	}

}