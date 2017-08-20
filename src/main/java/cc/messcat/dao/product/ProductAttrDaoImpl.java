/*
 * ProductAttrDaoImpl.java
 * 
 * Create by MCGT
 * 
 * Create time 2015-04-15
 * 
 * Version 1.0
 * 
 * Copyright 2013 Messcat, Inc. All rights reserved.
 * 
 */
package cc.messcat.dao.product;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;

import cc.modules.commons.Pager;
import cc.modules.util.ObjValid;
import cc.messcat.entity.McProductInfo;
import cc.messcat.entity.ProductAttr;
import cc.messcat.bases.BaseDaoImpl;

public class ProductAttrDaoImpl extends BaseDaoImpl implements ProductAttrDao {

	public void save(ProductAttr productAttr) {
		getHibernateTemplate().save(productAttr);
	}
	
	public void update(ProductAttr productAttr) {
		getHibernateTemplate().update(productAttr);
	}
	
	public void delete(ProductAttr productAttr) {
		getHibernateTemplate().delete(productAttr);
	}

	public void delete(Long id) {
		try {
    		Session session = this.getSession();
    		StringBuffer str = new StringBuffer();
    		str.append("update ProductAttr set status = 0 where id="+id);
			Query query = session.createQuery(str.toString());
    		query.executeUpdate();
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
	}
	
	public ProductAttr get(Long id) {
		List<ProductAttr> productAttrs = getHibernateTemplate().find("from ProductAttr where id="+id+" and status = 1");
		if (ObjValid.isValid(productAttrs)) {
			return productAttrs.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List findAll() {
		return getHibernateTemplate().find("from ProductAttr where status = 1");
	}

	@SuppressWarnings("unchecked")
	public Pager getPager(int pageSize, int pageNo) {
		List<ProductAttr> result = null;
    	int rowCount = 0;
    	try {
    		Session session = this.getSession();
    		StringBuffer str = new StringBuffer();
			//基础数据
			str.append(" from ProductAttr p where status = 1 ");
			Query query = session.createQuery(str.toString());
			if (pageSize==0&&pageNo==0) {
				pageSize = 1;
				pageNo = 1;
				rowCount = 1;
			}else {
				int startIndex = pageSize * (pageNo - 1);
				query.setFirstResult(startIndex);
				query.setMaxResults(pageSize);
				rowCount = coutALLResult();
			}
			result = query.list();
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return new Pager(pageSize, pageNo, rowCount, result);
	}
	
	public int coutALLResult(){
		int count = 0;
		try{
			StringBuffer str = new StringBuffer();
			//基础数据
			str.append("select count(*) from ProductAttr p where status = 1");
			Session session = this.getSession();
			Query query = session.createQuery(str.toString());
			count = ((Long) query.uniqueResult()).intValue();
		}catch(Exception e){
			e.printStackTrace();
		}
		return count;
		
	}
	
	@SuppressWarnings("unchecked")
	public List<ProductAttr> queryByProductAndAttrType(Long productId, String attrType){
		List<ProductAttr> result = null;
    	try {
    		Session session = this.getSession();
    		StringBuffer str = new StringBuffer();
			//基础数据
			str.append("select * from product_attr p where p.STATUS = 1 and p.PRODUCT_ID = "+productId+" ");
    		str.append("and p.ATTR_ID in");
    		str.append("(select ID from attribute a where a.ATTR_TYPE = '"+attrType+"' and a.STATUS = '1') ");
			Query query = session.createSQLQuery(str.toString()).addEntity(ProductAttr.class);
			result = query.list();
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
		return result;
	}

	@Override
	public ProductAttr findAttrByProduct(McProductInfo mcProductInfo) {
		List<ProductAttr> result = null;
    	try {
    		Session session = this.getSession();
    		StringBuffer str = new StringBuffer();
			//基础数据
			str.append("select * from product_attr p where p.STATUS = 1 and p.PRODUCT_ID = "+mcProductInfo.getId()+" ");
			Query query = session.createSQLQuery(str.toString()).addEntity(ProductAttr.class);
			result = query.list();
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
		return result.get(0);
	}
	
}