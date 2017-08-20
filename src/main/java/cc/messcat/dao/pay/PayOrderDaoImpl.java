/*
 * PayOrderDaoImpl.java
 * 
 * Create by MCGT
 * 
 * Create time 2015-04-10
 * 
 * Version 1.0
 * 
 * Copyright 2013 Messcat, Inc. All rights reserved.
 * 
 */
package cc.messcat.dao.pay;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cc.messcat.bases.BaseDaoImpl;
import cc.messcat.entity.AgentOrder;
import cc.messcat.entity.Member;
import cc.messcat.entity.OrderInfo;
import cc.messcat.entity.PayOrder;
import cc.messcat.entity.ProductAttr;
import cc.messcat.vo.PayOrderVo;
import cc.modules.commons.Pager;
import cc.modules.util.ObjValid;

public class PayOrderDaoImpl extends BaseDaoImpl implements PayOrderDao {

	private static Logger log = LoggerFactory.getLogger(PayOrderDaoImpl.class); 
	/**
	 * 查询所有
	 */
	public List findAll() {
		return getHibernateTemplate().find("from PayOrder where status='1' order by id desc");
	}
	/**
	 * 查询所有待确认收货的订单
	 */
	public List<PayOrder> findPayOrder() {
		return getHibernateTemplate().find("from PayOrder where status='1' and shippingStatus='1' order by id desc");
	}
	/**
	 * 查询出该代理商某段时间内的业绩
	 * */
	@SuppressWarnings({ "unused", "rawtypes" })
	public Pager findByAgent(Member member, Date begin, Date end, int pageSize, int pageNo){
		List<PayOrder> pl = new ArrayList<PayOrder>();
		Session session = this.getSession();
		StringBuffer sql = new StringBuffer();
	    SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    String beginStr=df.format(begin);
	    String endStr = df.format(end);
		sql.append(" from PayOrder p where p.member.firstFather.id = '"+member.getId()+"'");
		sql.append(" and (p.payTime between "+"'"+beginStr+"'"+" and "+"'"+endStr+"')");
		Query query = session.createQuery(sql.toString());
		if(pageNo!=-1&&pageSize!=-1){
			query.setFirstResult((pageNo-1)*pageSize);
			query.setMaxResults(pageSize);
		}
		List result = query.list();
		int rowCount = result.size();
		return new Pager(pageSize, pageNo, rowCount, result);
	}
	
	/**
	 * 查找区域代理的团队人数
	 * @param agent
	 * @return
	 */
	public int countMyTeamByAgent(Member member){
		Session session = this.getSession();
		StringBuffer sql = new StringBuffer();
		sql.append("from Member m where m.firstFather.id = '"+member.getId()+"' ");
		Query query = session.createQuery(sql.toString());
		List list = query.list();
		int count = 0;
		if (list != null && list.size() > 0) {
			count = list.size();
		}
		return count;
	}
	
	/**
	 * 查询出单个订单和其清单的详细内容
	 */
	@SuppressWarnings("unchecked")
	public List<OrderInfo> queryPayAndOrder(PayOrder payOrder){
		String hql = "select o from OrderInfo as o left join o.payOrder as p where p.orderNum=?";
		return getHibernateTemplate().find(hql,payOrder.getOrderNum());
	}
	
	/**
	 * 查询商家已经发货到签收的所有订单
	 */
	public Pager queryAllSendToReceiveOrder(int pageStart,int pageSize){
		int pageIndex=(pageStart-1)*pageSize;
		StringBuffer hql = new StringBuffer("from PayOrder where (shippingStatus='1' or shippingStatus='2') and status='1' order by id desc");
			Session session = this.getSession();
			return new Pager(pageSize, pageStart, getHibernateTemplate().find(hql.toString()).size(),
					session.createQuery(hql.toString()).setMaxResults(pageSize).setFirstResult(pageIndex).list());	
	}
	
	/**
	 * 商家当天的订单
	 */
	public Pager queryByDay(int pageStart,int pageSize,String judge){
		int pageIndex=(pageStart-1)*pageSize;
		StringBuffer hql = new StringBuffer("from PayOrder as p where DATE(p.createTime) = CURDATE() and p.status='1'");
		if (judge.equals("1")) {
			hql.append(" and (p.shippingStatus='1' or p.shippingStatus='2') order by p.id desc");
		} else {
			hql.append(" order by p.id desc");
		}
		Session session = this.getSession();
		return new Pager(pageSize, pageStart, getHibernateTemplate().find(hql.toString()).size(),
				session.createQuery(hql.toString()).setMaxResults(pageSize).setFirstResult(pageIndex).list());
	}
	
	/**
	 * 商家一周和一个月内的订单
	 */
	public Pager queryByWeek(String start,String end,int pageStart,int pageSize,String judge){
		int pageIndex=(pageStart-1)*pageSize;
		StringBuffer hql = new StringBuffer("from PayOrder as p where p.createTime >= '"+start+"' and p.createTime <= '"+end+"' and p.status='1'");
		if (judge.equals("1")) {
			hql.append(" and (p.shippingStatus='1' or p.shippingStatus='2') order by p.id desc");
		} else {
			hql.append(" order by p.id desc");
		}
		Session session = this.getSession();
		return new Pager(pageSize, pageStart, getHibernateTemplate().find(hql.toString()).size(),
				session.createQuery(hql.toString()).setMaxResults(pageSize).setFirstResult(pageIndex).list());	
	}

	/**
	 * 用户的待收货订单或全部的订单(已收货) 
	 */
	@SuppressWarnings("unchecked")
	public List<OrderInfo> noReceiveOrderByUser(String loginName,String shippingStatus){
		StringBuffer hql=new StringBuffer(" from OrderInfo as o where o.status=1 and o.payOrder.status=1");
		if (ObjValid.isValid(shippingStatus)) {
			hql.append(" and o.payOrder.shippingStatus="+shippingStatus);
		}
		if (ObjValid.isValid(loginName)) {
			hql.append(" and o.member.loginName='"+loginName+"'");
		}
		
		hql.append(" order by o.id desc");
		return this.getHibernateTemplate().find(hql.toString());		
	}
	
	/**
	 * 用户的待收货订单(不包括清单)
	 */
	public Pager noReceivePayByUser(Member member,int pageStart,int pageSize){
		StringBuffer hql=new StringBuffer("select p from PayOrder as p left join p.member as m where p.status='1' and m.loginName='"+member.getLoginName()+"' and (p.shippingStatus='1' or p.shippingStatus='3') order by p.id desc");	
		Session session = this.getSession();
		return new Pager(pageSize, pageStart, getHibernateTemplate().find(hql.toString()).size(),
				session.createQuery(hql.toString()).setMaxResults(pageSize).setFirstResult((pageStart-1)*pageSize).list());
	}
	
	/**
	 * 用户待付款
	 */
	@SuppressWarnings("unchecked")
	public List<OrderInfo> PayOrderByUser(String loginName){ 
		String hql="select o from OrderInfo as o left join o.member as m where m.loginName='"+loginName+"' and o.orderStatus='0' and o.status='1' order by o.id desc";
		return this.getHibernateTemplate().find(hql);
	}
	
	/**
	 * 用户批量确认收货
	 */
	public void moreReceiveOrderByUser(String orderNum){
		String hql="update PayOrder set shippingStatus='2' where orderNum in ('"+orderNum+"')";
		Session session = this.getSession();
		session.createQuery(hql).executeUpdate();
	}
	
    /**
     * 用户分销的清单
     */
	@Override
	public Pager shareOrderInfoByFather(Member member,int pageStart,int pageSize) {
		/*String sql="SELECT o1.* FROM order_info o1,(SELECT n2.* FROM member n2 WHERE n2.MEMBER_FATHER ='"+member.getId()+"' and n2.STATUS='1') m1 WHERE m1.ID=o1.MEMBER and o1.STATUS='1' UNION " +
				   "SELECT o2.* FROM order_info o2,(SELECT n3.* FROM member n3,(SELECT n2.* FROM member n2 WHERE n2.MEMBER_FATHER ='"+member.getId()+"' and n2.STATUS='1') t2 WHERE t2.ID = n3.MEMBER_FATHER and n3.STATUS='1') m2 WHERE m2.ID=o2.MEMBER and o2.STATUS='1' UNION " +
				   "SELECT o3.* FROM order_info o3,(SELECT n4.* FROM member n4,(SELECT n3.* FROM member n3,(SELECT n2.* FROM member n2 WHERE n2.MEMBER_FATHER ='"+member.getId()+"' and n2.STATUS='1') t2 WHERE t2.ID = n3.MEMBER_FATHER and n3.STATUS='1') t3 WHERE t3.ID = n4.MEMBER_FATHER and n4.STATUS='1') m3 WHERE m3.ID=o3.MEMBER and o3.STATUS='1'";	*/	
		String sql="SELECT o1.* FROM order_info o1,(SELECT n2.* FROM member n2 WHERE n2.MEMBER_FATHER ='"+member.getId()+"' and n2.STATUS='1') m1 WHERE m1.ID=o1.MEMBER and o1.STATUS='1' UNION " +
				   "SELECT o2.* FROM order_info o2,(SELECT n3.* FROM member n3,(SELECT n2.* FROM member n2 WHERE n2.MEMBER_FATHER ='"+member.getId()+"' and n2.STATUS='1') t2 WHERE t2.ID = n3.MEMBER_FATHER and n3.STATUS='1') m2 WHERE m2.ID=o2.MEMBER and o2.STATUS='1'";
		Session session = this.getSession();
		int pageIndex=(pageStart-1)*pageSize;
		return new Pager(pageSize, pageStart, session.createSQLQuery(sql).addEntity(OrderInfo.class).list().size(),
				session.createSQLQuery(sql).addEntity(OrderInfo.class).setMaxResults(pageSize).setFirstResult(pageIndex).list());
	}
	
	 /**
     * 用户分销的订单
     */
	@Override
	public Pager sharePayOrderByFather(List<Double> rateList,Member member,int pageStart,int pageSize) {
		/*String sb = "SELECT "+rateList.get(0)+" AS rate, o1.ID AS id, o1.MEMBER_ID AS member, m1.NICKNAME AS name, o1.ORDER_AMOUNT AS orderAmount, o1.PAY_TIME AS payTime FROM pay_order o1,(SELECT n2.* FROM member n2 WHERE n2.MEMBER_FATHER ='"+member.getId()+"' and n2.STATUS='1') m1 WHERE m1.ID=o1.MEMBER_ID and o1.STATUS='1' UNION " +
				    "SELECT "+rateList.get(1)+" AS rate, o2.ID AS id, o2.MEMBER_ID AS member, m2.NICKNAME AS name, o2.ORDER_AMOUNT AS orderAmount, o2.PAY_TIME AS payTime FROM pay_order o2,(SELECT n3.* FROM member n3,(SELECT n2.* FROM member n2 WHERE n2.MEMBER_FATHER ='"+member.getId()+"' and n2.STATUS='1') t2 WHERE t2.ID = n3.MEMBER_FATHER and n3.STATUS='1') m2 WHERE m2.ID=o2.MEMBER_ID and o2.STATUS='1' UNION " +
				    "SELECT "+rateList.get(2)+" AS rate, o3.ID AS id, o3.MEMBER_ID AS member, m3.NICKNAME AS name, o3.ORDER_AMOUNT AS orderAmount, o3.PAY_TIME AS payTime FROM pay_order o3,(SELECT n4.* FROM member n4,(SELECT n3.* FROM member n3,(SELECT n2.* FROM member n2 WHERE n2.MEMBER_FATHER ='"+member.getId()+"' and n2.STATUS='1') t2 WHERE t2.ID = n3.MEMBER_FATHER and n3.STATUS='1') t3 WHERE t3.ID = n4.MEMBER_FATHER and n4.STATUS='1') m3 WHERE m3.ID=o3.MEMBER_ID and o3.STATUS='1'";*/
		String sb = "SELECT "+rateList.get(0)+" AS rate, o1.ID AS id, o1.MEMBER_ID AS member, m1.NICKNAME AS name, o1.ORDER_AMOUNT AS orderAmount, o1.PAY_TIME AS payTime FROM pay_order o1,(SELECT n2.* FROM member n2 WHERE n2.MEMBER_FATHER ='"+member.getId()+"' and n2.STATUS='1') m1 WHERE m1.ID=o1.MEMBER_ID and o1.STATUS='1' UNION " +
			    "SELECT "+rateList.get(1)+" AS rate, o2.ID AS id, o2.MEMBER_ID AS member, m2.NICKNAME AS name, o2.ORDER_AMOUNT AS orderAmount, o2.PAY_TIME AS payTime FROM pay_order o2,(SELECT n3.* FROM member n3,(SELECT n2.* FROM member n2 WHERE n2.MEMBER_FATHER ='"+member.getId()+"' and n2.STATUS='1') t2 WHERE t2.ID = n3.MEMBER_FATHER and n3.STATUS='1') m2 WHERE m2.ID=o2.MEMBER_ID and o2.STATUS='1'";
		Session session = this.getSession();
		Query query = session.createSQLQuery(sb.toString()).addScalar("rate",Hibernate.DOUBLE).addScalar("id",Hibernate.LONG).addScalar("member", Hibernate.LONG)
				.addScalar("name", Hibernate.STRING).addScalar("orderAmount", Hibernate.DOUBLE).addScalar("payTime", Hibernate.DATE)
				.setResultTransformer(Transformers.aliasToBean(PayOrderVo.class));
		if(pageStart!=-1){
			int startIndex = (pageStart-1)*pageSize;
			query.setFirstResult(startIndex);
			query.setMaxResults(pageSize);
			
		}
		List<PayOrderVo> list = query.list();
		return new Pager(pageSize, pageStart, 0, list);
		
	}
	
	/**
	 * 收件人和订单模糊查询
	 */
	public Pager queryNameAndOrderByLike(String status,int pageStart,int pageSize,PayOrder payOrder,int flag){
		
		//flag 值为0表示所有订单，1表示发货后的订单
		int pageIndex=(pageStart-1)*pageSize;
		StringBuffer hql = new StringBuffer("from PayOrder as p where p.status='"+status+"'");
		//订单号不为空，收件人和分类为空
		if (!payOrder.getOrderNum().equals("")&&payOrder.getName().equals("")&&payOrder.getShippingStatus().equals("")) {
			hql.append(" and p.orderNum like '%"+payOrder.getOrderNum()+"%'");
			if (flag==1) {
				hql.append(" and (p.shippingStatus='1' or p.shippingStatus='2')");
			}
		}
		//收件人不为空，订单号和分类为空
		else if (payOrder.getOrderNum().equals("")&&!payOrder.getName().equals("")&&payOrder.getShippingStatus().equals("")) {
			hql.append(" and p.name like '%"+payOrder.getName()+"%'");
			if (flag==1) {
				hql.append(" and (p.shippingStatus='1' or p.shippingStatus='2')");
			}
		}
		//分类不为空，收件人和订单号为空
		else if (payOrder.getOrderNum().equals("")&&payOrder.getName().equals("")&&!payOrder.getShippingStatus().equals("")) {
			hql=mySql(hql, payOrder);			      	
		}
		//订单号、收件人不为空，分类为空
		else if (!payOrder.getOrderNum().equals("")&&!payOrder.getName().equals("")&&payOrder.getShippingStatus().equals("")) {
			hql.append(" and ");
    	   hql.append("p.orderNum like '%"+payOrder.getOrderNum()+"%' and p.name like '%"+payOrder.getName()+"%'");
    	   if (flag==1) {
				hql.append(" and (p.shippingStatus='1' or p.shippingStatus='2')");
			}
        }
		//订单号和分类不为空，收件人为空
		else if (!payOrder.getOrderNum().equals("")&&payOrder.getName().equals("")&&!payOrder.getShippingStatus().equals("")) {
			mySql(hql, payOrder);
			hql.append(" and p.orderNum like '%"+payOrder.getOrderNum()+"%'");
     	   
         }
		//分类、收件人不为空，订单号为空
		else if (payOrder.getOrderNum().equals("")&&!payOrder.getName().equals("")&&!payOrder.getShippingStatus().equals("")) {
			mySql(hql, payOrder);
			hql.append(" and p.name like '%"+payOrder.getName()+"%'");
         }
		//订单号，收件人和分类都不为空
		else if (!payOrder.getOrderNum().equals("")&&!payOrder.getName().equals("")&&!payOrder.getShippingStatus().equals("")) {
			mySql(hql, payOrder);
			hql.append(" and p.orderNum like '%"+payOrder.getOrderNum()+"%' and p.name like '%"+payOrder.getName()+"%'");
         }
		hql.append(" order by p.id desc");
		log.info(hql.toString());
		Session session = this.getSession();
		return new Pager(pageSize, pageStart, getHibernateTemplate().find(hql.toString()).size(),
				session.createQuery(hql.toString()).setMaxResults(pageSize).setFirstResult(pageIndex).list());
	}
	
	//订单分类sql拼接
	public StringBuffer mySql(StringBuffer hql,PayOrder payOrder){
		hql.append(" and ");
		if (payOrder.getShippingStatus().equals("0")) {       //未付款
			hql.append("p.payStatus='0'");
		}
		else if (payOrder.getShippingStatus().equals("1")) { 
			hql.append("p.shippingStatus='0' and p.payStatus='1'");    //付款完备货
		}
		else if (payOrder.getShippingStatus().equals("2")) {            //发货但未确认
			hql.append("p.shippingStatus='1'");
		}
		else if (payOrder.getShippingStatus().equals("3")) {            //已签收
			hql.append("p.shippingStatus='2'");
		}
		return hql;	
	}
	
	/**
	 * 查询清单商品的属性
	 */
	@SuppressWarnings("unchecked")
	public List<ProductAttr> queryProductAttr(String productAttr){
		String hql="select p from ProductAttr as p where p.id in('"+productAttr+"')";
		Session session = this.getSession();
		return (List<ProductAttr>) session.createSQLQuery(hql);
		//return this.getHibernateTemplate().find(hql,productAttr);
	}


	public Pager getPager(int pageSize, int pageNo) {
		Session session = this.getSession();
		Criteria criteria = session.createCriteria(PayOrder.class);
		int rowCount = ((Integer) criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
		criteria.setProjection(null);
		int startIndex = pageSize * (pageNo - 1);
		criteria.setFirstResult(startIndex);
		criteria.setMaxResults(pageSize);
		List result = criteria.list();
		return new Pager(pageSize, pageNo, rowCount, result);
	}
	@Override
	public List<PayOrder> getTwoDayOrder(String todayStr, String yestodayStr) {
		Session session = this.getSession();
		StringBuffer hsql = new StringBuffer();
		hsql.append("from PayOrder p where (p.payTime between  '"+yestodayStr+"' and '"+todayStr+"') and p.status = '1' and p.shippingStatus = '0' and p.payStatus ='1' order by p.id desc");
		Query query = session.createQuery(hsql.toString());
		List result = query.list();
		List<PayOrder> lp = result;
		return lp;
	}
	@Override
	public AgentOrder queryAgentByOrderNum(String payOrderNum) {
		Session session = this.getSession();
		StringBuffer hsql = new StringBuffer();
		hsql.append("from AgentOrder a where a.orderNum ="+payOrderNum);
		Query query = session.createQuery(hsql.toString());
		List result = query.list();
		List<AgentOrder> ao = result;
		return ao.get(0);
	}
	@Override
	public List<String> findAllAgentOrder() {
		Session session = this.getSession();
		StringBuffer hsql = new StringBuffer();
		hsql.append("from AgentOrder a where a.status = 1 order by id desc");
		Query query = session.createQuery(hsql.toString());
		List result = query.list();
		List<AgentOrder> ao = result;
		List<String> lsList = new ArrayList<String>();
		for(int i = 0;i<ao.size();i++){
			lsList.add(ao.get(i).getOrderNum());
		}
		return lsList;
	}
	@Override
	public Pager queryAllCycleOrder(int pageStart, int pageSize) {
		Session session = this.getSession();
		String hql = "from PayOrder p where p.status = '0' order by p.id desc";
		List list = session.createQuery(hql).setMaxResults(pageSize).setFirstResult((pageStart-1)*pageSize).list();
		String countHql = "select count(id) "+hql;
		int rowcount = ((Long)session.createQuery(countHql).uniqueResult()).intValue();
		return new Pager(pageSize, pageStart, rowcount, list);
	}
}