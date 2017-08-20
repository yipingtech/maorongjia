/*
 * IntegralRuleDaoImpl.java
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
import cc.messcat.entity.IntegralRule;
import cc.messcat.bases.BaseDaoImpl;

public class IntegralRuleDaoImpl extends BaseDaoImpl implements IntegralRuleDao {

	public void save(IntegralRule integralRule) {
		getHibernateTemplate().save(integralRule);
	}
	
	public void update(IntegralRule integralRule) {
		getHibernateTemplate().update(integralRule);
	}
	
	public void delete(IntegralRule integralRule) {
		getHibernateTemplate().delete(integralRule);
	}

	public void delete(Long id) {
		try {
    		Session session = this.getSession();
    		StringBuffer str = new StringBuffer();
    		str.append("update IntegralRule set status=0 where id="+id);
			Query query = session.createQuery(str.toString());
    		query.executeUpdate();
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
	}
	
	public IntegralRule get(Long id) {
		List<IntegralRule> integralRules = getHibernateTemplate().find("from IntegralRule where id="+id+" and status = 1");
		if (ObjValid.isValid(integralRules)) {
			return integralRules.get(0);
		}
		return null; 
	}

	@SuppressWarnings("unchecked")
	public List findAll() {
		return getHibernateTemplate().find("from IntegralRule where status = 1");
	}

	@SuppressWarnings("unchecked")
	public IntegralRule getIntegralRuleByName(String name){
		List<IntegralRule> integralRules = getHibernateTemplate().find("from IntegralRule where name='"+name+"' and status = 1");
		if (ObjValid.isValid(integralRules)) {
			return integralRules.get(0);
		}
		return null; 
	}
	
	@SuppressWarnings("unchecked")
	public Pager getPager(int pageSize, int pageNo) {
		List<IntegralRule> result = null;
    	int rowCount = 0;
    	try {
    		Session session = this.getSession();
    		StringBuffer str = new StringBuffer();
			//基础数据
			str.append(" from IntegralRule m where status = 1 ");
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
			str.append("select count(*) from IntegralRule m where status = 1");
			Session session = this.getSession();
			Query query = session.createQuery(str.toString());
			count = ((Long) query.uniqueResult()).intValue();
		}catch(Exception e){
			e.printStackTrace();
		}
		return count;
	}

}