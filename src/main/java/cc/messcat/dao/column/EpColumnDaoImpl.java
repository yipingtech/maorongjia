package cc.messcat.dao.column;

import java.util.List;

import cc.messcat.bases.BaseDaoImpl;
import cc.messcat.entity.EnterpriseColumn;
import cc.modules.util.StringUtil;

/**
 * @author Messcat
 * @version 1.1
 */
public class EpColumnDaoImpl extends BaseDaoImpl implements EpColumnDao {

	public EpColumnDaoImpl() {
	}

	public void delete(EnterpriseColumn enterpriseColumn) {
		getHibernateTemplate().delete(enterpriseColumn);
	}

	public void delete(Long id) {
		getHibernateTemplate().delete(this.get(id));
	}

	public List<?> findAll() {
		List<?> list = getHibernateTemplate().find("from EnterpriseColumn order by father");
		return list;
	}

	public EnterpriseColumn get(Long id) {
		return (EnterpriseColumn) getHibernateTemplate().load(EnterpriseColumn.class, id);
	}

	public void save(EnterpriseColumn enterpriseColumn) {
		getHibernateTemplate().save(enterpriseColumn);

	}

	public void update(EnterpriseColumn enterpriseColumn) {
		// this.getSessionFactory().getCurrentSession().clear();
		getHibernateTemplate().merge(enterpriseColumn);
	}

	public List findByFatherAndState(Long father, String state, String order) {
		StringBuffer SQL = new StringBuffer("from EnterpriseColumn where father = ? ");
		if (!"-1".equals(state))
			SQL = SQL.append(" and state = ").append(state);
		if (order != null && !"".equals(order))
			SQL = SQL.append(" order by ").append(order);
		List find = getHibernateTemplate().find(SQL.toString(), father);
		return find;
	}
	
	public List findProductColumn(Long id){
		List list = getHibernateTemplate().find("from ProductColumn p where p.mcProduct.status = '1' and p.mcProduct.isSale = '1' and p.enterpriseColumn.id = ? order by p.mcProduct.orderPara asc,p.id ASC",id);
		return list;
	}

	/**
	 * ajax response little data
	 */
	public List<?> findAjaxByFatherAndState(Long father, String state, String order) {
		StringBuffer sql = new StringBuffer(
			"from EnterpriseColumn where father = ? ");

		if (!"-1".equals(state))
			sql = sql.append(" and state = ").append(state);
		if (StringUtil.isNotBlank(order))
			sql = sql.append(" order by ").append(order);

		List<?> find = getHibernateTemplate().find(sql.toString(), father);
		return find;
	}

	public List<?> findAllColumn() {
		return getHibernateTemplate().find("from EnterpriseColumn where father!=0 and state = 1 order by orderColumn");
	}

	public boolean isNameUnique(String names, Long father) {
		List temp = getHibernateTemplate().find("from EnterpriseColumn where names = '" + names + "' and father = " + father);
		return temp.size() <= 0;
	}

	public EnterpriseColumn getColumnByFrontNum(String frontNum) {
		List<?> list = getHibernateTemplate().find("from EnterpriseColumn where frontNum = ?", frontNum);
		EnterpriseColumn obj = null;
		if (list.size() > 0)
			obj = (EnterpriseColumn) list.get(0);
		return obj;
	}

	public List getColumnByFrontNumNotNull() {
		List list = getHibernateTemplate().find("from EnterpriseColumn where frontNum != ''");
		return list;
	}
	
	/**
	 * 12-2-3 21:45:12 org.apache.catalina.core.ApplicationDispatcher invoke 严重:
	 * Servlet.service() for servlet jsp threw exception
	 * org.hibernate.SessionException: Session is closed!
	 */
	public List findSubColumn(Long father) {
		// Session session =
		// getHibernateTemplate().getSessionFactory().openSession();
		// Criteria criteria = session.createCriteria(EnterpriseColumn.class);
		// criteria.add(Restrictions.eq("state", "1"));
		// criteria.add(Restrictions.eq("father", father));
		// criteria.addOrder(Order.asc("orderColumn"));
		// criteria.setProjection(null);
		// List result = criteria.list();
		// session.close();

		List result = getHibernateTemplate().find("from EnterpriseColumn where state = 1 and father = ?  order by orderColumn ASC",
			father);

		return result;
	}

	/**
	 * 根据父级栏目获取最大栏目排序
	 */
	public Long getMaxOrderByFather(Long father) {
		List result = getHibernateTemplate().find("select max(orderColumn) from EnterpriseColumn where state = 1 and father = ?",
			father);
		Long maxOrder;
		if(result != null && result.size() > 0 && result.get(0) != null){
			maxOrder = (Long)result.get(0);
		}else{
			maxOrder = 0L;
		}
		return maxOrder;
	}

	/**
	 * 根据模板类型查找栏目数量
	 */
	public Long findByPageTypeId(Long id) {
		List result = getHibernateTemplate().find("select count(typeColumn) from EnterpriseColumn where typeColumn.id = ?",
			id);
		Long amount;
		if(result != null && result.size() > 0 && result.get(0) != null){
			amount = (Long)result.get(0);
		}else{
			amount = 0L;
		}
		return amount;
	}
	
	/**
	 * 查找某个页面类型的栏目
	 * @param fatherId
	 * @param pageTypeId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<EnterpriseColumn> findColumnsByPageType(Long fatherId, Long pageTypeId){
		return getHibernateTemplate().find("from EnterpriseColumn where father="+fatherId+" and typeColumn.id = "+pageTypeId+" and state = 1 order by orderColumn");
	}
}