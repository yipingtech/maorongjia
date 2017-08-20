/*
  * EvaluateDaoImpl.java
 * 
 * Create by MCGT
 * 
 * Create time 2015-04-07
 * 
 * Version 1.0
 * 
 * Copyright 2013 Messcat, Inc. All rights reserved.
 * 
 */
package cc.messcat.dao.member;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import cc.modules.commons.Pager;
import cc.modules.util.ObjValid;
import cc.messcat.entity.Evaluate;
import cc.messcat.entity.McProductInfo;
import cc.messcat.bases.BaseDaoImpl;

/**
 * @author Administrator
 *
 */
public class EvaluateDaoImpl extends BaseDaoImpl implements EvaluateDao {

	public void save(Evaluate evaluate) {
		getHibernateTemplate().save(evaluate);
	}
	
	public void update(Evaluate evaluate) {
		getHibernateTemplate().update(evaluate);
	}
	
	public void delete(Evaluate evaluate) {
		getHibernateTemplate().delete(evaluate);
	}

	public void delete(Long id) {
		try {
    		Session session = this.getSession();
    		StringBuffer str = new StringBuffer();
    		str.append("update Evaluate set status=0 where id="+id);
			Query query = session.createQuery(str.toString());
    		query.executeUpdate();
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
	}
	
	public Evaluate get(Long id) {
		List<Evaluate> evaluates = getHibernateTemplate().find("from Evaluate where id="+id+" and status = 1");
		if (ObjValid.isValid(evaluates)) {
			return evaluates.get(0);
		}
		return null; 
	}

	@SuppressWarnings("unchecked")
	public List findAll() {
		return getHibernateTemplate().find("from Evaluate where status = '1' order by parendId");
	}

	@SuppressWarnings("unchecked")
	public Pager getPager(int pageSize, int pageNo) {
		List<Evaluate> result = null;
    	int rowCount = 0;
    	try {
    		Session session = this.getSession();
    		StringBuffer str = new StringBuffer();
			//基础数据
			str.append(" from Evaluate m where status = 1 ");
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
			str.append("select count(*) from Evaluate m where status = 1");
			Session session = this.getSession();
			Query query = session.createQuery(str.toString());
			count = ((Long) query.uniqueResult()).intValue();
		}catch(Exception e){
			e.printStackTrace();
		}
		return count;
	}
	
	/**
	 * 根据条件查找分类
	 * @param condition
	 * @param pageSize
	 * @param pageNo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Pager searchByLikeCondition(Evaluate condition, int pageSize, int pageNo){
		List<Evaluate> result = null;
    	int rowCount = 0;
    	try {
    		Session session = this.getSession();
    		StringBuffer str = new StringBuffer();
    		str.append("from Evaluate m where status=1");
			if (ObjValid.isValid(condition)) {
				if (ObjValid.isValid(condition.getMember())) {
					if (ObjValid.isValid(condition.getMember().getId())) {
						str.append(" and m.member.id = '"+condition.getMember().getId()+"' ");
					}
				}
				if(ObjValid.isValid(condition.getProduct())){
					if (ObjValid.isValid(condition.getProduct().getId())) {
						str.append(" and m.product.id = '"+condition.getProduct().getId()+"' ");
					}
				}
			}
			Query query = session.createQuery(str.toString());
    		if (pageNo!=0 && pageSize!=0) {
    			int startIndex = pageSize * (pageNo - 1);
    			query.setFirstResult(startIndex);
    			query.setMaxResults(pageSize);
			}else {
				pageSize = 1;
				pageNo = 1;
			}
			result = query.list();
			rowCount = coutResult(condition);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return new Pager(pageSize, pageNo, rowCount, result);
	}
	
	public int coutResult(Evaluate condition){
		int count = 0;
		try{
			StringBuffer str = new StringBuffer();
			//基础数据
			str.append("select count(*) from Evaluate m where status=1");
			if (ObjValid.isValid(condition)) {
				if (ObjValid.isValid(condition.getMember())) {
					if (ObjValid.isValid(condition.getMember().getId())) {
						str.append(" and m.member.id = '"+condition.getMember().getId()+"' ");
					}
				}
				if(ObjValid.isValid(condition.getProduct())){
					if (ObjValid.isValid(condition.getProduct().getId())) {
						str.append(" and m.product.id = '"+condition.getProduct().getId()+"' ");
					}
				}
			}
			
			Session session = this.getSession();
			Query query = session.createQuery(str.toString());
			
			count = ((Long) query.uniqueResult()).intValue();
		}catch(Exception e){
			e.printStackTrace();
		}
		return count;
	}

	
	/* 
	 * 计算产品的平均评价
	 */
	public double countLevelAvg(McProductInfo condition){
		double level = 0;
		try{
			StringBuffer str = new StringBuffer();
			//基础数据
			str.append("select avg(level) from Evaluate e where status=1");
			if (ObjValid.isValid(condition)) {
				if(ObjValid.isValid(condition.getId())){
					str.append(" and e.product.id = "+condition.getId()+" ");
				}
			}
			Session session = this.getSession();
			Query query = session.createQuery(str.toString());
			Object object = query.uniqueResult();
			if (ObjValid.isValid(object)) {
				level = ((Double) object).doubleValue();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return level;
	}
	/* 
	 * 计算产品的评价数
	 */
	public int countAmount(McProductInfo condition){
		int level = 0;
		try{
			StringBuffer str = new StringBuffer();
			//基础数据
			str.append("select count(*) from Evaluate e where status=1");
			if (ObjValid.isValid(condition)) {
				if(ObjValid.isValid(condition.getId())){
					str.append(" and e.product.id = '"+condition.getId()+"' ");
				}
			}
			Session session = this.getSession();
			Query query = session.createQuery(str.toString());
			level = ((Long) query.uniqueResult()).intValue();
		}catch(Exception e){
			e.printStackTrace();
		}
		return level;
	}
	
}