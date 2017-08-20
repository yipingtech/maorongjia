package cc.messcat.bases;

import java.util.List;
import java.util.Map;

import cc.modules.commons.Pager;

public interface BaseManagerDao {

	/**
	 * 添加对象
	 */
	public void save(Object obj);

	/**
	 * 修改对象
	 */
	public void update(Object obj);

	/**
	 * 根据ID删除对象
	 */
	public void delete(Long id, String objName);

	/**
	 * 根据ID查找对象
	 */
	public Object get(Long id, String objName);

	/**
	 * 查找所有对象
	 */
	@SuppressWarnings("unchecked")
	public List getAll(String objName);
	
	/**
	 * @param objName
	 * @param status
	 * @return
	 */
	public List findAllByStatus(String objName, String status);
	
	
	/**
	 * 批量修改记录
	 * @param entityClass
	 * @param propNames
	 * @param attrs
	 */
	public int update(Class entityClass,Map<String,Object> props, Map<String,Object> attrs);
	
	/**
	 * 删除对象
	 * @param entity
	 */
	public void delete(Object entity);
	
	
	/**
	 * 根据entityClass查询最大id
	 * @param entityClass
	 * @return
	 */
	public int queryMaxId(Class entityClass);
	
	/**
	 * 根据entityClass及attrs属性删除记录
	 * @param entityClass
	 * @param attrs
	 * @return
	 */
	public int delete(Class entityClass, Map<String,Object> attrs);
	
	/**
	 * 根据entityClass及attrs属性or逻辑或删除记录
	 * @param entityClass
	 * @param attrs
	 * @return
	 */
	public int deleteOr(Class entityClass, Map<String,Object> attrs);
	
	/**
	 * 根据entityClass及ids查询记录
	 * @param entityClass
	 * @param ids
	 * @return
	 */
	public <T> List<T> queryList(Class entityClass, String ids);
	
	/**
	 * 根据entityClass及不属于ids的记录
	 * @param entityClass
	 * @param ids
	 * @return
	 */
	public <T> List<T> queryNotInList(Class entityClass, String ids);
	
	/**
	 * 根据entityClass及attrs属性查询单个对象
	 * @param entityClass
	 * @param attrs
	 * @return
	 */
	public <T> T query(Class<T> entityClass, Map<String,Object> attrs);
	
	/**
	 * 根据entityClass及attrs属性模糊查询单个对象
	 * @param entityClass
	 * @param attrs
	 * @return
	 */
	public <T> T query(Class<T> entityClass,String likeAttr,String likeValue,Map<String,Object>... attrs);
	
	
	/**
	 * 根据entityClass及attrs属性统计记录条数
	 * @param entityClass
	 * @param attrs
	 * @return
	 */
	public long queryTotalRecord(Class entityClass, Map<String,Object>... attrs);
	
	/**
	 * 根据entityClass按模糊查询统计记录条数
	 * likeType=1:'%文字'
	 * likeType=2:'%文字%'
	 * @param entityClass
	 * @param attrs
	 * @return
	 */
	public long queryTotalRecord(Class entityClass,String likeAttr,String likeValue);
	
	/**
	 * 根据entityClass及attrs属性查询指定页面记录,支持分页	
	 */
	public <T> List<T> queryList(Class<T> entityClass, int pageStart,int pageSize,String orderAttr,String orderType, Map<String,Object>... attrs);
	
	/**
	 * 根据entityClass查询指定页面记录,支持分页及模糊查询
	 */
	public <T> List<T> queryList2(Class<T> entityClass, int pageStart,int pageSize,String orderAttr,String orderType, String likeAttr,String likeValue);

	/**
	 * 根据entityClass及attrs属性查询记录	
	 */
	public <T> List<T> queryList(Class<T> entityClass,String orderAttr,String orderType, Map<String,Object>... attrs);
	
	/**
	 * 根据entityClass及attrs属性以or逻辑或关系查询记录	
	 */
	public <T> List<T> queryOrList(Class<T> entityClass,String orderAttr,String orderType, Map<String,Object>... attrs);
	
	/**
	 * 根据entityClass及attrs属性查询记录，支持分组
	 */
	public <T> List<T> queryList(Class<T> entityClass,String orderAttr,String orderType, String groupAttr, Map<String,Object>... attrs);
	
	
	/**
	 * 模糊查询
	 */
	public <T> List<T> likeQueryList(Class<T> entityClass,String orderAttr,String orderType, String likeAttr,String likeValue,int... limit);
	
	/**
     * 实现某个属性查询结果
     * @param condition
     * @param clazz
     * @return
     */
    public Object getObjectByProperty(String propertyName, Object value, Class _entityType, String status);
	
	/**
     * @param pageSize
     * 			当前分页大小
     * @param pageNo
     * 			当前页
     * @param object
     * 			查找的对象
     * @param orderAttr
     * 			排序字段
     * @param orderType
     * 			排序规则（逆序：DESC， 循序：ESC）
     * @param status
     * 			状态（1：启用， 0：禁用）
     * @return
     */
	public Pager retrieveObjectsPager(int pageSize, int pageNo, Object object, String orderAttr,String orderType, String status);
}
