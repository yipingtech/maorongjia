/*
 * CommissionInfoDaoImpl.java
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

import cc.messcat.bases.BaseDaoImpl;
import cc.messcat.entity.CommissionInfo;
import cc.messcat.entity.Member;
import cc.messcat.entity.PayOrder;
import cc.modules.commons.Pager;
import cc.modules.util.ObjValid;

public class CommissionInfoDaoImpl extends BaseDaoImpl implements CommissionInfoDao {
	
	public void update(CommissionInfo commissionInfo) {
		getHibernateTemplate().update(commissionInfo);
	}
	
	public void delete(CommissionInfo commissionInfo) {
		getHibernateTemplate().delete(commissionInfo);
	}
	
	public CommissionInfo get(Long id) {
		return (CommissionInfo) getHibernateTemplate().get(CommissionInfo.class, id);
	}

	//查询该订单的佣金是否已存在
	@Override
	public boolean findByPayOrder(PayOrder payOrder) {
		boolean result = false;
		Session session = this.getSession();
		StringBuffer sql = new StringBuffer();
		sql.append("select count(*) from CommissionInfo c WHERE c.payOrder.id = '"+payOrder.getId()+"'");
		Query query = session.createQuery(sql.toString());
		int count = ((Long) query.uniqueResult()).intValue();
		if(count !=0 && count>=1){
			result = true;
		}
		return result;
	}

	
	/**
	 * 查询用户的所有佣金记录(分页)
	 */
	public Pager queryAllCommission(int pageStart,int pageSize,Member member){
		int pageIndex=(pageStart-1)*pageSize;
		String hql="select c from CommissionInfo as c left join c.memberId as m where m.loginName="+member.getLoginName()+" and c.status='1'";
		Session session = this.getSession();
		return new Pager(pageSize, pageStart, this.getHibernateTemplate().find(hql).size(),session.createQuery(hql.toString()).setMaxResults(pageSize).setFirstResult(pageIndex).list());		
	}
	
	/**
	 * 查询历史累计提现
	 */
	public Double queryTotalCashInfo(Member member){
		String hql="select sum(c.drawAmount) from CashInfo as c where c.status = '1' and c.member.id='"+member.getId()+"'";
		Session session = this.getSession();
		Query query = session.createQuery(hql);
		Double count=((Double) query.uniqueResult()).doubleValue();
		return count;
	}
	

	@SuppressWarnings("unchecked")
	public Pager getPager(int pageSize, int pageNo) {
		Session session = this.getSession();
		Criteria criteria = session.createCriteria(CommissionInfo.class);
		int rowCount = ((Integer) criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
		criteria.setProjection(null);
		int startIndex = pageSize * (pageNo - 1);
		criteria.setFirstResult(startIndex);
		criteria.setMaxResults(pageSize);
		List result = criteria.list();
		return new Pager(pageSize, pageNo, rowCount, result);
	}
	public Pager queryAllUserByCondition(int pageSize, int pageNo,Double conditionSend){
		Session session = this.getSession();
		StringBuffer sql = new StringBuffer();
		sql.append(" from Member m where m.commissionLine >= "+conditionSend);
		Query query = session.createQuery(sql.toString());
		List result = query.list();
		int rowCount = result.size();
		return new Pager(pageSize, pageNo, rowCount, result);
	}

	public Pager retrieveCommissionInfos(int pageSize, int pageNo) {
		Session session = this.getSession();
		StringBuffer sql = new StringBuffer();
		sql.append(" from CommissionInfo c where c.commissionStatus = 2 order by c.addTime desc");
		Query query = session.createQuery(sql.toString());
		List result = query.list();
		int rowCount = result.size();
		return new Pager(pageSize, pageNo, rowCount, result);
	}

	@Override
	public Double findMemberBonus(Member member) {
		String hql="select sum(c.commission) from CommissionInfo as c where c.status = '1' and (c.commissionStatus ='0' or c.commissionStatus='3' or c.commissionStatus='4') and c.memberId.id='"+member.getId()+"'";
		Session session = this.getSession();
		Query query = session.createQuery(hql);
		Double bonus = 0.0;
		try {
		      bonus = Double.valueOf(((Double)query.uniqueResult()).doubleValue());
		    } catch (Exception e) {
		    }
		return bonus;
	}

	@Override
	public Double findAgentCommissionInfo(Member member) {
		String hql="select sum(c.commission) from CommissionInfo as c where c.status = '1' and  c.commissionStatus='3' and c.memberId.id='"+member.getId()+"'";
		Session session = this.getSession();
		Query query = session.createQuery(hql);
		Double bonus = 0.0;
		try {
		      bonus = Double.valueOf(((Double)query.uniqueResult()).doubleValue());
		    } catch (Exception e) {
		    }
		return bonus;
	}

	@Override
	public Pager queryAllCommissionByMember(int pageSize, int pageNo, Member member) {
		Session session = this.getSession();
		StringBuffer sql = new StringBuffer();
		sql.append(" from CommissionInfo c where c.memberId.id='"+member.getId()+"' and (c.commissionStatus = '0' or c.commissionStatus = '3' or c.commissionStatus = '4') and c.status = '1' order by c.addTime desc");
		Query query = session.createQuery(sql.toString());
		List result = query.list();
		int rowCount = result.size();
		return new Pager(pageSize, pageNo, rowCount, result);
	}

	/**
	 * Stephen
	 */
	@Override
	public Pager queryCashInfosByUser(Member member, int pageStart, int pageSize) {
		Session session = this.getSession();
		StringBuffer hql = new StringBuffer();
		hql.append(" from CashInfo c  ");
		if (ObjValid.isValid(member)) {
			hql.append(" where c.member.id = "+member.getId());
		}
		hql.append(" order by id desc ");
		List result = session.createQuery(hql.toString()).setMaxResults(pageSize).setFirstResult((pageStart - 1) * pageSize).list();
		String hqlCount = "select count(id) " + hql;
		Long count = (Long)session.createQuery(hqlCount).uniqueResult();
		return new Pager(pageSize, pageStart, count.intValue(), result);
	}

	

}