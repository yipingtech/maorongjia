/*
 * ProductDrawbackDaoImpl.java
 * 
 * Create by MCGT
 * 
 * Create time 2015-04-17
 * 
 * Version 1.0
 * 
 * Copyright 2013 Messcat, Inc. All rights reserved.
 * 
 */
package cc.messcat.dao.drawback;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;

import cc.modules.commons.Pager;
import cc.modules.util.ObjValid;
import cc.messcat.entity.PayOrder;
import cc.messcat.entity.ProductDrawback;
import cc.messcat.bases.BaseDaoImpl;

public class ProductDrawbackDaoImpl extends BaseDaoImpl implements ProductDrawbackDao {

	public ProductDrawback get(Long id) {
		return (ProductDrawback) getHibernateTemplate().get(ProductDrawback.class, id);
	}

	
	//查询商家已经退货的所有订单
	@SuppressWarnings("unchecked")
	public Pager queryAllDrawBackByProduct(int pageStart,int pageSize){
		int pageIndex=(pageStart-1)*pageSize;
		String hql="from ProductDrawback where  status='1' order by id desc";
			Session session=this.getSession();
			return new Pager(pageSize, pageStart, this.getHibernateTemplate().find(hql).size(), 
					session.createQuery(hql).setMaxResults(pageSize).setFirstResult(pageIndex).list()) ;	
	}
	
	//查询商家已经退货的所有订单
	@SuppressWarnings("unchecked")
	public Pager queryAllPassDrawBackByProduct(int pageStart,int pageSize){
		int pageIndex=(pageStart-1)*pageSize;
		String hql="from ProductDrawback where  status='1' order by id desc";
		Session session=this.getSession();
		return new Pager(pageSize, pageStart, this.getHibernateTemplate().find(hql).size(), 
				session.createQuery(hql).setMaxResults(pageSize).setFirstResult(pageIndex).list()) ;	
	}

	//用户已经收到退款或换货的全部订单
	@SuppressWarnings("unchecked")
	public List<ProductDrawback> queryAllPassDrawBackByUser(String memberId){
		return this.getHibernateTemplate().find("from ProductDrawback where (returnStatus='3' or returnStatus='4') and status='1' and memberId="+memberId+" order by id desc");
	}
	
	//商家当天的退货申请订单
	@SuppressWarnings("unchecked")
	public Pager queryDrawByDay(String flag,int pageStart,int pageSize){
		StringBuffer hql= new StringBuffer("select p from ProductDrawback as p where DATE(p.applyTime) = CURDATE() and p.status='1'");
		/*if (flag.equals("0")) {
			hql.append("  and (p.returnStatus='0' or p.returnStatus='1')");
		} else {
			hql.append(" and (p.returnStatus='2' or p.returnStatus='3' or p.returnStatus='4')");
		}*/
		hql.append(" order by id desc");
		int pageIndex=(pageStart-1)*pageSize;
		Session session = this.getSession();
		return new Pager(pageSize, pageStart, getHibernateTemplate().find(hql.toString()).size(),
				session.createQuery(hql.toString()).setMaxResults(pageSize).setFirstResult(pageIndex).list());			
	}
	
	//商家一周和一个月内的退货申请订单
	@SuppressWarnings("unchecked")
	public Pager queryDrawByWeekAndMonth(String start,String end,String flag,int pageStart,int pageSize){
		StringBuffer hql= new StringBuffer("from ProductDrawback as p where p.applyTime >= '"+start+"' and p.applyTime <= '"+end+"' and p.status='1' order by id desc");
/*		if (flag.equals("0")) {
			hql.append("  and (p.returnStatus='0' or p.returnStatus='1')");
		} else {
			hql.append(" and (p.returnStatus='2' or p.returnStatus='3' or p.returnStatus='4')");
		}*/
		int pageIndex=(pageStart-1)*pageSize;
		Session session = this.getSession();
		return new Pager(pageSize, pageStart, getHibernateTemplate().find(hql.toString()).size(),
				session.createQuery(hql.toString()).setMaxResults(pageSize).setFirstResult(pageIndex).list());			
	}

	//商家模糊查询申请退款和退货的订单
	@SuppressWarnings("unchecked")
	public Pager queryLikeDrawBack(ProductDrawback productDrawback,String flag,int pageStart,int pageSize){
		int pageIndex=(pageStart-1)*pageSize;
		StringBuffer hql = new StringBuffer("from ProductDrawback as p where p.status='1'");
		if(ObjValid.isValid(productDrawback)){
			if(ObjValid.isValid(productDrawback.getOrderNum())){
				hql.append(" and p.orderNum = '"+productDrawback.getOrderNum()+"'");
			}
			if(ObjValid.isValid(productDrawback.getMemberId())){
				hql.append(" and p.memberId = '"+productDrawback.getMemberId()+"'");
			}
			if(ObjValid.isValid(productDrawback.getReturnStatus())&&!productDrawback.getReturnStatus().equals("")){
				hql.append(" and p.returnStatus = '"+productDrawback.getReturnStatus()+"'");
			}
		}
		hql.append(" order by id desc");
		Session session = this.getSession();
		return new Pager(pageSize, pageStart, this.getHibernateTemplate().find(hql.toString()).size(), 
				session.createQuery(hql.toString()).setMaxResults(pageSize).setFirstResult(pageIndex).list());
	}
	
	//订单分类sql拼接
	public StringBuffer mySql(StringBuffer hql,ProductDrawback productDrawback){
		hql.append(" and ");
		if (productDrawback.getReturnStatus().equals("0")) {
			hql.append("p.returnStatus="+productDrawback.getReturnStatus()+"");
		}
		else if (productDrawback.getReturnStatus().equals("1")) {
			hql.append("p.returnStatus="+productDrawback.getReturnStatus()+"");
		}
		else if (productDrawback.getReturnStatus().equals("2")) {
			hql.append("p.returnStatus="+productDrawback.getReturnStatus()+"");
		}
		else if (productDrawback.getReturnStatus().equals("3")) {
			hql.append("p.returnStatus="+productDrawback.getReturnStatus()+"");
		}
		else if (productDrawback.getReturnStatus().equals("4")) {
			hql.append("p.returnStatus="+productDrawback.getReturnStatus()+"");
		}
		return hql;	
	}
		


	public Pager getPager(int pageSize, int pageNo) {
		Session session = this.getSession();
		Criteria criteria = session.createCriteria(ProductDrawback.class);
		int rowCount = ((Integer) criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
		criteria.setProjection(null);
		int startIndex = pageSize * (pageNo - 1);
		criteria.setFirstResult(startIndex);
		criteria.setMaxResults(pageSize);
		List result = criteria.list();
		return new Pager(pageSize, pageNo, rowCount, result);
	}

}