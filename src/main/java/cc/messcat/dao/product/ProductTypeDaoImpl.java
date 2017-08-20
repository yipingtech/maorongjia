/*
 * ProductTypeDaoImpl.java
 * 
 * Create by MCGT
 * 
 * Create time 2015-04-09
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

import cc.messcat.bases.BaseDaoImpl;
import cc.messcat.entity.ProductType;
import cc.modules.util.ObjValid;

public class ProductTypeDaoImpl extends BaseDaoImpl implements ProductTypeDao {

	public void save(ProductType productType) {
		getHibernateTemplate().save(productType);
	}
	
	public void update(ProductType productType) {
		getHibernateTemplate().update(productType);
	}
	
	public void delete(ProductType productType) {
		getHibernateTemplate().delete(productType);
	}

	public void delete(Long id) {
		try {
    		Session session = this.getSession();
    		StringBuffer str = new StringBuffer();
    		str.append("update ProductType set status=0 where id="+id);
			Query query = session.createQuery(str.toString());
    		query.executeUpdate();
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
	}
	
	/**
	 * 根据产品类型名称查找是否存在
	 * @param productTypeName
	 * @return boolean
	 */
	public Boolean FindProductTypeByTypeName(String productTypeName){
		List productTypeList = getHibernateTemplate().find(
    			    "from ProductType WHERE name = ? and status=1",productTypeName);
    	if(ObjValid.isValid(productTypeList)){
    		return false;
    	}
    	return true;
	}
	
	@SuppressWarnings("unchecked")
	public ProductType get(Long id) {
		List<ProductType> productTypes = getHibernateTemplate().find("from ProductType where id="+id+" and status = 1");
		if (ObjValid.isValid(productTypes)) {
			return productTypes.get(0);
		}
		return null;
	}

	@SuppressWarnings({ "rawtypes" })
	public List findAll() {
		return getHibernateTemplate().find("from ProductType where status = 1");
	}

	public int coutALLResult(){
		int count = 0;
		try{
			StringBuffer str = new StringBuffer();
			//基础数据
			str.append("select count(*) from ProductType p where status = 1");
			Session session = this.getSession();
			Query query = session.createQuery(str.toString());
			count = ((Long) query.uniqueResult()).intValue();
		}catch(Exception e){
			e.printStackTrace();
		}
		return count;
	}

	//通过类型id 去修改商品资料下架
	public void updateMcProductInfo(Long typeId){
		try {
    		Session session = this.getSession();
    		StringBuffer str = new StringBuffer();
    		str.append("update mc_product_info set is_sale=0 where product_type="+typeId);
			Query query = session.createSQLQuery(str.toString());
    		query.executeUpdate();
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
	}
	
/*	 public static void main(String[] args) {
		  ApplicationContext ct = new ClassPathXmlApplicationContext("applicationContext*.xml");
		  ProductTypeDaoImpl dao= (ProductTypeDaoImpl)ct.getBean("productTypeDao");
		  System.out.println(dao.FindProductTypeByTypeName("65"));
	 }*/
}