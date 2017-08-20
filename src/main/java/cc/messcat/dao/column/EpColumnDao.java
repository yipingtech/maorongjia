package cc.messcat.dao.column;

import java.util.List;

import cc.messcat.entity.EnterpriseColumn;

/**
 * @author Messcat
 * @version 1.1
 */
public interface EpColumnDao {

	public EnterpriseColumn get(Long id);

	public void save(EnterpriseColumn enterpriseColumn);

	public void update(EnterpriseColumn enterpriseColumn);

	public void delete(EnterpriseColumn enterpriseColumn);

	public void delete(Long id);

	public List findAll();

	public List findByFatherAndState(Long father, String state, String order);
	
	public List findProductColumn(Long id);

	public List findSubColumn(Long father);

	public List findAllColumn();

	public boolean isNameUnique(String names, Long father);

	public EnterpriseColumn getColumnByFrontNum(String s);

	public List getColumnByFrontNumNotNull();

	public List findAjaxByFatherAndState(Long father, String state, String order);

	//根据父级栏目获取最大栏目排序
	public Long getMaxOrderByFather(Long father);

	//根据模板类型查找栏目数量
	public Long findByPageTypeId(Long id);
	/**
	 * 查找某个页面类型的栏目
	 * @param fatherId
	 * @param pageTypeId
	 * @return
	 */
	public List<EnterpriseColumn> findColumnsByPageType(Long fatherId, Long pageTypeId);
}