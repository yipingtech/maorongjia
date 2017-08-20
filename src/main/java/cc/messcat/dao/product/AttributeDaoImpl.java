/*
 * AttributeDaoImpl.java
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
package cc.messcat.dao.product;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.providers.dao.DaoAuthenticationProvider;

import cc.messcat.bases.BaseDaoImpl;
import cc.messcat.entity.Attribute;
import cc.messcat.entity.ProductAttr;
import cc.modules.commons.Pager;
import cc.modules.util.ObjValid;

public class AttributeDaoImpl extends BaseDaoImpl implements AttributeDao {

	public void save(Attribute attribute) {
		getHibernateTemplate().save(attribute);
	}
	
	public void update(Attribute attribute) {
		getHibernateTemplate().update(attribute);
	}
	
	public void delete(Attribute attribute) {
		getHibernateTemplate().delete(attribute);
	}

	public void delete(Long id) {
		try {
    		Session session = this.getSession();
    		StringBuffer str = new StringBuffer();
    		str.append("update Attribute set status = 0 where id="+id);
			Query query = session.createQuery(str.toString());
    		query.executeUpdate();
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
	}
	//根据typeid删除该类型对应的attribute
	public void deleteFormTypeId(Long id) {
		try {
			Session session = this.getSession();
			StringBuffer str = new StringBuffer();
			str.append("update attribute set status = 0 where TYPE_ID ="+id);
			Query query = session.createSQLQuery(str.toString());
			query.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public Attribute get(Long id) {
		List<Attribute> attributes = getHibernateTemplate().find("from Attribute where id="+id+" and status = 1");
		if (ObjValid.isValid(attributes)) {
			return attributes.get(0);
		}
		return null;
	}

	@SuppressWarnings("rawtypes")
	public List findAll() {
		return getHibernateTemplate().find("from Attribute where status = 1");
	}

	//查找是否有商品应用了该属性
	public boolean findProductAttr(Long attrId){
		List<ProductAttr> result = null;
    	try {
    		Session session = this.getSession();
    		StringBuffer str = new StringBuffer();
			//基础数据
			str.append("select * from product_attr p where p.STATUS = 1 and p.ATTR_ID = "+attrId+" ");
			Query query = session.createSQLQuery(str.toString()).addEntity(ProductAttr.class);
			result = query.list();
			if(ObjValid.isValid(result)){
				return true;
			}
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return false;
	}
	
	/**
	 * 通过产品类别ID查询出对应的单选属性
	 */
	@SuppressWarnings("unchecked")
	public Pager getByProductType(int pageSize, int pageNo, Long typeId){
		List<Attribute> result = null;
    	int rowCount = 0;
    	try {
    		Session session = this.getSession();
    		StringBuffer str = new StringBuffer();
			//基础数据
			str.append("from Attribute where productType.id = "+typeId+" and status = '1' order by sortOrder");
			Query query = session.createQuery(str.toString());
			if (pageSize==0&&pageNo==0) {
				pageSize = 1;
				pageNo = 1;
				rowCount = 1;
			}else {
				int startIndex = pageSize * (pageNo - 1);
				query.setFirstResult(startIndex);
				query.setMaxResults(pageSize);
				rowCount = coutALLResult(typeId);
			}
			result = query.list();
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return new Pager(pageSize, pageNo, rowCount, result);
	}
	
	public int coutALLResult(Long typeId){
		int count = 0;
		try{
			StringBuffer str = new StringBuffer();
			//基础数据
			str.append("select count(*) from Attribute a where status = 1 and productType.id ="+typeId);
			Session session = this.getSession();
			Query query = session.createQuery(str.toString());
			count = ((Long) query.uniqueResult()).intValue();
		}catch(Exception e){
			e.printStackTrace();
		}
		return count;
	}
/*	 public static void main(String[] args) {
		  ApplicationContext ct = new ClassPathXmlApplicationContext("applicationContext*.xml");
		  AttributeDaoImpl dao= (AttributeDaoImpl)ct.getBean("attributeDao");
		  log.info(dao.findProductAttr(80L));
		 }*/
	
}