/*
 * CartInfoDaoImpl.java
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
package cc.messcat.dao.cart;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cc.modules.commons.Pager;
import cc.modules.util.HQLUtil;
import cc.messcat.entity.CartInfo;
import cc.messcat.entity.McProductInfo;
import cc.messcat.bases.BaseDaoImpl;

public class CartInfoDaoImpl extends BaseDaoImpl implements CartInfoDao {

	public void save(CartInfo cartInfo) {
		getHibernateTemplate().save(cartInfo);
	}
	
	public void update(CartInfo cartInfo) {
		getHibernateTemplate().update(cartInfo);
	}
	
	public void delete(CartInfo cartInfo) {
		getHibernateTemplate().delete(cartInfo);
	}
	
	public void delete(Long id) {
		getHibernateTemplate().delete(this.get(id));
	}
	
	public CartInfo get(Long id) {
		return (CartInfo) getHibernateTemplate().get(CartInfo.class, id);
	}

	@SuppressWarnings("unchecked")
	public List findAll() {
		return getHibernateTemplate().find("from CartInfo");
	}

	@SuppressWarnings("unchecked")
	public Pager getPager(int pageSize, int pageNo) {
		Session session = this.getSession();
		Criteria criteria = session.createCriteria(CartInfo.class);
		int rowCount = ((Integer) criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
		criteria.setProjection(null);
		int startIndex = pageSize * (pageNo - 1);
		criteria.setFirstResult(startIndex);
		criteria.setMaxResults(pageSize);
		List result = criteria.list();
		return new Pager(pageSize, pageNo, rowCount, result);
	}
	
	/**
	 * 修改购物车产品价格
	 * @param productInfo
	 */
	public void updateByProduct(McProductInfo productInfo){
		Session session = this.getSession();
		StringBuffer str = new StringBuffer();
		str.append("update CartInfo set productPrice="+productInfo.getShopPrice()+", productTotal=(productPrice*buyAmount) where product.id="+productInfo.getId());
		Query query = session.createQuery(str.toString());
		query.executeUpdate();
	}

	/**
	 * 根据ids查找entityClass
	 * @param entityClass
	 * @param ids
	 */
	public <T> List<T> queryListByIds(Class entityClass, String ids) {
		// TODO Auto-generated method stub
		return (List<T>)this.getHibernateTemplate().find(HQLUtil.createQueryHQL(entityClass.getSimpleName(), ids),HQLUtil.changeToLongArray(ids));
	}
	
//	public static void main(String[] args) {
//		  ApplicationContext ct = new ClassPathXmlApplicationContext("applicationContext*.xml");
//		  CartInfoDaoImpl dao= (CartInfoDaoImpl)ct.getBean("cartInfoDao");
//		  List<CartInfo> cartList = dao.queryListByIds(CartInfo.class,"834,835");
//		  System.out.println(cartList.size());
//		 }
}