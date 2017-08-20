/*
 * StockDaoImpl.java
 * 
 * Create by MCGT
 * 
 * Create time 2015-04-16
 * 
 * Version 1.0
 * 
 * Copyright 2013 Messcat, Inc. All rights reserved.
 * 
 */
package cc.messcat.dao.product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;

import cc.modules.commons.Pager;
import cc.modules.util.ObjValid;
import cc.messcat.entity.Attribute;
import cc.messcat.entity.Stock;
import cc.messcat.bases.BaseDaoImpl;

public class StockDaoImpl extends BaseDaoImpl implements StockDao {

	public void save(Stock stock) {
		getHibernateTemplate().save(stock);
	}
	
	public void update(Stock stock) {
		getHibernateTemplate().update(stock);
	}
	
	public void delete(Stock stock) {
		getHibernateTemplate().delete(stock);
	}

	public void delete(Long id) {
		try {
    		Session session = this.getSession();
    		StringBuffer str = new StringBuffer();
    		str.append("update Stock set status = 0 where id="+id);
			Query query = session.createQuery(str.toString());
    		query.executeUpdate();
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
	}
	
	@SuppressWarnings("unchecked")
	public Stock get(Long id) {
		List<Stock> stocks = getHibernateTemplate().find("from Stock where id="+id+" and status = 1");
		if (ObjValid.isValid(stocks)) {
			return stocks.get(0);
		}
		return null;
	}

	@SuppressWarnings("rawtypes")
	public List findAll() {
		return getHibernateTemplate().find("from Stock  where status = 1");
	}

	@SuppressWarnings("unchecked")
	public Pager getPager(int pageSize, int pageNo) {
		List<Stock> result = null;
    	int rowCount = 0;
    	try {
    		Session session = this.getSession();
    		StringBuffer str = new StringBuffer();
			//基础数据
			str.append(" from Stock s where status = 1 ");
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
			str.append("select count(*) from Stock s where status = 1");
			Session session = this.getSession();
			Query query = session.createQuery(str.toString());
			count = ((Long) query.uniqueResult()).intValue();
		}catch(Exception e){
			e.printStackTrace();
		}
		return count;
	}
	
	public List<Stock> searchByCondition(Stock condition) {
		List<Stock> result = null;
    	try {
    		Session session = this.getSession();
    		StringBuffer str = new StringBuffer();
			//基础数据
			str.append(" from Stock s where status = 1 ");
			if (ObjValid.isValid(condition)) {
				if (ObjValid.isValid(condition.getProduct())) {
					str.append("and s.product.id = "+condition.getProduct().getId()+" ");
				}
				if (ObjValid.isValid(condition.getProductAttrIds())) {
					str.append("and s.productAttrIds like '"+condition.getProductAttrIds()+"%'");
				}
			}
			Query query = session.createQuery(str.toString());
			result = query.list();
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return result;
	}
	
	/**
	 * @return
	 * 通过产品、关联属性获取库存
	 */
	@SuppressWarnings("unchecked")
	public Stock findByProAttr(Stock condition){
		List<Stock> result = null;
    	try {
    		Session session = this.getSession();
    		StringBuffer str = new StringBuffer();
			//基础数据
			str.append("from Stock s where status=1");
			if (ObjValid.isValid(condition)) {
				if (ObjValid.isValid(condition.getProduct())) {
					if (ObjValid.isValid(condition.getProduct().getId())){
						str.append(" and s.product.id = "+condition.getProduct().getId()+" ");
					}
				}
				if(ObjValid.isValid(condition.getProductAttrIds())){
						//拿出字符串中用逗号隔开的每一个值
						if(condition.getProductAttrIds().indexOf(",")>0){
							List proAttrIdsList = new ArrayList();
							String otherString ="";
							boolean flag=true;
							otherString = condition.getProductAttrIds();
							while(flag){
								proAttrIdsList.add(Integer.parseInt(otherString.substring(0, otherString.indexOf(","))));
								otherString = otherString.substring(otherString.indexOf(",")+1,otherString.length());
								if(otherString.indexOf(",")>0){
									flag = true;
								}else{
									proAttrIdsList.add(Integer.parseInt(otherString));
									flag = false;
								}
							}
							//比较值的大小进行排序
							Collections.sort(proAttrIdsList);
							String proAttrIds="";
							//重新生成proAttrIds  顺序
							for(int i = 0;i < proAttrIdsList.size();i++){
								proAttrIds = proAttrIds+proAttrIdsList.get(i)+",";
							}
							proAttrIds = proAttrIds.substring(0, proAttrIds.length()-1);
							//------------------------------------------------------
							str.append(" and ( s.productAttrIds = '"+proAttrIds.trim()+"' ");
							//------------------------------------------------------
						    proAttrIds="";
							//重新生成proAttrIds  逆序
							for(int i = proAttrIdsList.size()-1;i >=  0;i--){
								proAttrIds = proAttrIds+proAttrIdsList.get(i)+",";
							}
							proAttrIds = proAttrIds.substring(0, proAttrIds.length()-1);
							str.append(" or s.productAttrIds = '"+proAttrIds.trim()+"' ) ");
						}else{
							str.append(" and s.productAttrIds = '"+condition.getProductAttrIds().trim()+"' ");
						}
				}
			}
			Query query = session.createQuery(str.toString());
			result = query.list();
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	if (ObjValid.isValid(result)) {
    		return result.get(0);
		}
		return null;
		
	}

}