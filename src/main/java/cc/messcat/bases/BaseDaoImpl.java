package cc.messcat.bases;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.record.formula.functions.T;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import cc.messcat.entity.EnterpriseNews;
import cc.modules.commons.Pager;
import cc.modules.util.DateHelper;
import cc.modules.util.HQLUtil;
import cc.modules.util.ObjValid;

/**
 * @author Messcat
 * @version 1.1
 */
@SuppressWarnings("unchecked")
public class BaseDaoImpl extends BaseHibernateDaoSupport implements BaseDao {

	public BaseDaoImpl() {
	}

	/**
	 * 添加对象
	 */
	public void save(Object obj) {
		getHibernateTemplate().save(obj);
	}

	/**
	 * 修改对象
	 */
	public void update(Object obj) {
		getHibernateTemplate().update(obj);
	}
	
	/**
	 * 根据ID删除对象
	 */
	public void delete(Long id, String objName) {
		getHibernateTemplate().delete(get(id, objName));
	}

	/**
	 * 查找所有对象
	 */
	public List getAll(String objName) {
		List all = getHibernateTemplate().find("from " + objName);
		return all;
	}
	
	/**
	 * @param objName
	 * 				对象名
	 * @param status
	 * 				状态
	 */
	public List<T> findAllByStatus(String objName, String status) {
		List<T> all = getHibernateTemplate().find("from " + objName+" where status = '"+status+"' ");
		return all;
	}

	/**
	 * 根据ID查找对象
	 */
	public Object get(Long id, String objName) {
		Object obj = getEntityClass(objName);
		if (obj == null) {
			return null;
		} else {
			return getHibernateTemplate().get(obj.getClass(), id);
		}
	}
	
	@Override
	public int update(Class entityClass, Map<String, Object> props,
			Map<String, Object> attrs) {
		// TODO Auto-generated method stub
		Object[] propsValue =props.values().toArray();          //获取value值的数组
		Object[] attrsValue =attrs.values().toArray();
		Object[] objs = new Object[propsValue.length + attrsValue.length];
		System.arraycopy(propsValue,0,objs,0,propsValue.length);
		System.arraycopy(attrsValue,0,objs,propsValue.length,attrsValue.length);
		return this.updateObjects(HQLUtil.createUpdateHQL(entityClass.getSimpleName(),props.keySet(),attrs.keySet()),objs);		
	}
	
	

	@Override
	public void delete(Object entity) {
		this.getHibernateTemplate().delete(entity);		
	}

	@Override
	public int delete(Class entityClass, Map<String, Object> attrs) {
		// TODO Auto-generated method stub
		return this.updateObjects(HQLUtil.createDeleteHQL(entityClass.getSimpleName(), attrs.keySet()),attrs.values().toArray());
	}
	
	@Override
	public int deleteOr(Class entityClass, Map<String, Object> attrs) {
		// TODO Auto-generated method stub
		return this.updateObjects(HQLUtil.createDeleteOrHQL(entityClass.getSimpleName(), attrs.keySet()),attrs.values().toArray());
	}

	@Override
	public <T> List<T> queryList(Class entityClass, String ids) {
		// TODO Auto-generated method stub
		return (List<T>)this.getHibernateTemplate().find(HQLUtil.createQueryHQL(entityClass.getSimpleName(), ids),HQLUtil.changeToLongArray(ids));
	}

	@Override
	public <T> T query(Class<T> entityClass, Map<String,Object> attrs) {		
		return this.findObject(HQLUtil.createQueryHQL(entityClass.getSimpleName(), attrs.keySet()), attrs.values().toArray());
	}
	
	@Override
	public <T> T query(Class<T> entityClass,String likeAttr,String likeValue, Map<String,Object>... attrs) {
		String hql;
		if(attrs.length > 0 ){
			hql = HQLUtil.createLikeQueryHQL(entityClass.getSimpleName(),likeAttr,attrs[0].keySet());
			return this.findObject(hql,new Object[]{likeValue,attrs[0].values().toArray()});
		}else{
			hql = HQLUtil.createLikeQueryHQL(entityClass.getSimpleName(),likeAttr);
			return this.findObject(hql,likeValue);
		}	
	}

	@Override
	public long queryTotalRecord(Class entityClass, Map<String, Object>... attrs) {
		String hql;
		if(attrs.length > 0 ){
			hql = HQLUtil.createQueryTotalRecordHQL(entityClass.getSimpleName(),attrs[0].keySet());
			return this.findObject(hql,attrs[0].values().toArray());
		}else{
			hql = HQLUtil.createQueryTotalRecordHQL(entityClass.getSimpleName());
			return this.findObject(hql);
		}	
	}
	
	@Override
	public long queryTotalRecord(Class entityClass,String likeAttr,String likeValue){
		StringBuffer hql = new StringBuffer("select count(o) from ");		
		hql.append(entityClass.getSimpleName()).append(" as o where o.").append(likeAttr).append(" like ?");		
		return this.findObject(hql.toString(),likeValue);
	}
	
	@Override
	public int queryMaxId(Class entityClass) {		
		StringBuffer hql = new StringBuffer("select max(o.id) from ").append(entityClass.getSimpleName()).append(" as o");
		return this.findObject(hql.toString());
	}

	@Override
	public <T> List<T> queryList(Class<T> entityClass, int pageStart,
			int pageSize,String orderAttr,String orderType, Map<String, Object>... attrs) {
		// TODO Auto-generated method stub
		String hql;
		if(attrs.length > 0 ){
			hql = HQLUtil.createQueryListHQL(entityClass.getSimpleName(),orderAttr,orderType,attrs[0].keySet());
			return this.findPageObjects(hql, pageStart, pageSize,attrs[0].values().toArray());
		}else{
			hql = HQLUtil.createQueryListHQL(entityClass.getSimpleName(),orderAttr,orderType);
			return this.findPageObjects(hql, pageStart, pageSize);
		}	
	}
	
	
	public <T> List<T> queryNotInList(Class entityClass, String ids) {
		// TODO Auto-generated method stub
		if(null==ids || ids.trim().equals("")){
			return	(List<T>)this.getHibernateTemplate().find(HQLUtil.createQueryNotInHQL(entityClass.getSimpleName(), ids));
		}else {
			return (List<T>)this.getHibernateTemplate().find(HQLUtil.createQueryNotInHQL(entityClass.getSimpleName(), ids),HQLUtil.changeToIntegerArray(ids));
		}
	}
	
	@Override
	public <T> List<T> queryList2(Class<T> entityClass, int pageStart,int pageSize,String orderAttr,String orderType, String likeAttr,String likeValue){
		StringBuffer hql = new StringBuffer("from ").append(entityClass.getSimpleName()).append(" as o where o.").append(likeAttr).append(" like ? ");
		hql.append("order by o.").append(orderAttr).append(" ").append(orderType);
		return this.findPageObjects(hql.toString(),pageStart,pageSize,likeValue);
	}


	@Override
	public <T> List<T> queryList(Class<T> entityClass, String orderAttr,
			String orderType, Map<String, Object>... attrs) {
		String hql;
		if(attrs.length > 0 ){
			hql = HQLUtil.createQueryListHQL(entityClass.getSimpleName(),orderAttr,orderType,attrs[0].keySet());
			return (List<T>)this.getHibernateTemplate().find(hql,attrs[0].values().toArray());
		}else{
			hql = HQLUtil.createQueryListHQL(entityClass.getSimpleName(),orderAttr,orderType);
			return (List<T>)this.getHibernateTemplate().find(hql);
		}	
	}
	
	@Override
	public <T> List<T> queryOrList(Class<T> entityClass, String orderAttr,
			String orderType, Map<String, Object>... attrs) {
		String hql;
		if(attrs.length > 0 ){
			hql = HQLUtil.createQueryOrListHQL(entityClass.getSimpleName(),orderAttr,orderType,attrs[0].keySet());
			return (List<T>)this.getHibernateTemplate().find(hql,attrs[0].values().toArray());
		}else{
			hql = HQLUtil.createQueryListHQL(entityClass.getSimpleName(),orderAttr,orderType);
			return (List<T>)this.getHibernateTemplate().find(hql);
		}	
	}
	
	@Override
	public <T> List<T> queryList(Class<T> entityClass, String orderAttr,
			String orderType, String groupAttr, Map<String, Object>... attrs) {
		String hql;
		if(attrs.length > 0 ){
			hql = HQLUtil.createQueryListHQL(entityClass.getSimpleName(),orderAttr,orderType,groupAttr,attrs[0].keySet());
			return (List<T>)this.getHibernateTemplate().find(hql,attrs[0].values().toArray());
		}else{
			hql = HQLUtil.createQueryListHQL(entityClass.getSimpleName(),orderAttr,orderType,groupAttr);
			return (List<T>)this.getHibernateTemplate().find(hql);
		}	
	}	
	
	@Override
	public <T> List<T> likeQueryList(Class<T> entityClass,String orderAttr,String orderType, String likeAttr, String likeValue,int... limit){
		StringBuffer hql = new StringBuffer("from ").append(entityClass.getSimpleName()).append(" as o where o.").append(likeAttr).append(" like ? ");
		hql.append("order by o.").append(orderAttr).append(" ").append(orderType);
		if(limit.length>0){
			return this.findLimitObjects(hql.toString(),limit[0],likeValue);
		}else{
			return this.findObjects(hql.toString(),likeValue);
		}
	}
	
	
	/**
	 * 获取相应的entity name
	 * @param objName
	 * @return
	 */
	private Object getEntityClass(String objName) {
		Object obj = null;
		try {
			obj = Class.forName(objName).newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}

	public Pager getObjectListByClass(int pageSize, int pageNo, Class classObject, String statu) {
		Session session = null;
		Pager pager = null;
		try {
			session = getHibernateTemplate().getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(classObject);
			if (!"-1".equals(statu))
				criteria.add(Restrictions.eq("state", statu));
			int rowCount = ((Integer) criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
			criteria.setProjection(null);
			int startIndex = pageSize * (pageNo - 1);
			criteria.setFirstResult(startIndex);
			criteria.setMaxResults(pageSize);
			List result = criteria.list();
			pager = new Pager(pageSize, pageNo, rowCount, result);
		} catch (Exception ex) {

		} finally {
			session.close();
		}

		return pager;
	}

	public List getInfoByClassAndSize(String classObject, Long size, Long clickTimes, String isprimPhoto, String isCommend,
		String b_or_s, Long columnId, String isIndexPhoto) {
		StringBuffer SQL = new StringBuffer();
		SQL.append("from ").append(classObject).append(" as temp where 1 = 1 ").toString();
		if (!"-1".equals(isprimPhoto))
			SQL.append(" and temp.isPrimPhoto = 1 ");
		if (!"-1".equals(isIndexPhoto))
			SQL.append(" and temp.isIndexPhoto = 1 ");
		if (!"-1".equals(isCommend))
			SQL.append(" and temp.isCommend = 1 ");
		SQL.append(" and temp.state = 1 ");
		if (!"-1".equals(columnId.toString().trim()))
			SQL.append(" and temp.enterpriseColumn.id = ").append(columnId).append(" ").toString();
		String date = (new DateHelper()).getCurrentDate().toString();
		if (!"EnterpriseInfo".equals(classObject)) {
			SQL.append(" and temp.initTime <= '").append(date.trim()).append("' ");
			SQL.append(" and temp.endTime >= '").append(date.trim()).append("' ");
		}
		if (!"-1".equals(clickTimes.toString()))
			SQL.append(" order by temp.clickTimes desc");
		if (!"-1".equals(clickTimes.toString()))
			SQL.append(" ,temp.isTop desc");
		else
			SQL.append(" order by temp.isTop desc");

		SQL.append(" ,temp.id desc");

		List result = getHibernateTemplate().find(SQL.toString());
		result = result.subList(0, (int) (size <= result.size() ? size : result.size()));
		return result;
	}

	public List getLinksAndAdByClassAndSize(Class classObject, Long size) {
		Session session = getHibernateTemplate().getSessionFactory().openSession();
		Criteria criteria = session.createCriteria(classObject);
		criteria.add(Restrictions.eq("state", "1"));
		//		criteria.addOrder(Order.desc("id"));
		criteria.setProjection(null);
		criteria.setFirstResult(0);
		criteria.setMaxResults(Integer.valueOf(size.toString()).intValue());
		List result = criteria.list();
		session.close();
		return result;
	}

	public EnterpriseNews getNews(Long id) {
		List find = getHibernateTemplate().find(
			"from EnterpriseNews as e where e.state = 1 and e.isPrimPhoto = 1 and e.enterpriseColumn.id = ?", id);
		if (find.size() > 0)
			return (EnterpriseNews) find.get(0);
		else
			return null;
	}

	public List findNews(Long id) {
		List find = getHibernateTemplate().find(
			"from EnterpriseNews as e where e.state = 1 and e.enterpriseColumn.id = ?", id);
		return find;
	}
	
	/**
	 * 判断条件是否有效
	 * @param obj
	 */
	protected boolean isValid(Object... objs) {
		for (int i = 0; i < objs.length; i++) {
			if(objs[i] == null) {
				return false;
			}
			if(objs[i] instanceof String) {
				String key = (String)objs[i];
				if(key.trim().equals("")) {
					return false;
				}
				if(key.trim().equals("-1")) {
					return false;
				}
			} else if(objs[i] instanceof Integer) {
				Integer key = (Integer)objs[i];
				if(key.intValue()<=0) {
					return false;
				}
			} else if(objs[i] instanceof Double) {
				Double key = (Double)objs[i];
				if(key.doubleValue()<=0) {
					return false;
				}
			} else if(objs[i] instanceof Byte) {
				Byte key = (Byte)objs[i];
				if(key.byteValue() < 0) {
					return false;
				}
			}else if (objs[i] instanceof Long) {
				Long key = (Long)objs[i];
				if(key.longValue() <= 0) {
					return false;
				}
			}
		}
		return true;
	}
	
	/**
     * 实现某个属性查询结果
     * @param condition
     * @param clazz
     * @return
     */
    public Object getObjectByProperty(String propertyName, Object value, Class _entityType, String status) {
		String className = _entityType.getName();
		String queryString = "from "+className+" as entity where entity."+ propertyName + "= ?";
		if (ObjValid.isValid(status)) {
			queryString+=" and entity.status="+status;
		}
		Query queryObject = getSession().createQuery(queryString);
		
		queryObject.setParameter(0, value);
		queryObject.setCacheable(true);
		List<?> list = queryObject.list();
		Object obj = null;
		if (ObjValid.isValid(list)) {
			obj = list.get(0);
		}
		return obj;
	}
	
	@Override
	public Pager retrieveObjectsPager(int pageSize, int pageNo, final Object object, String orderAttr,String orderType, String status) {
		Map<String, Object> map = new HashMap<String, Object>();
		map = ObjValid.objectToMap(map,object,false, null);
		if (ObjValid.isValid(status)) {
			map.put("status", status);
		}
		List<T> list = null;
		long rowCount = 0;
//		int pageIndex=(pageNo-1)*pageSize;
		if (map.size()<=0) {
			list = (List<T>) this.queryList(object.getClass(), pageNo, pageSize, orderAttr, orderType);
			rowCount = queryTotalRecord(object.getClass());
		}else {
			list = (List<T>) this.queryList(object.getClass(), pageNo, pageSize, orderAttr, orderType, map); 
			rowCount = queryTotalRecord(object.getClass(), map);
		}
		return new Pager(pageSize, pageNo, rowCount, list);
	}

	@Override
	public List<T> retrieveObjectsPager(int pageSize, int pageNo, String hql) {
		return findPageObjects(hql, pageNo, pageSize);
	}
	
	@Override
	public long queryTotalRecord(String hql){
		return this.findObject(hql.toString());
	}
}