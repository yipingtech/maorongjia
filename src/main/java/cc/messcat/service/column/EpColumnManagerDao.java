package cc.messcat.service.column;

import java.util.List;

import cc.messcat.entity.Authorities;
import cc.messcat.entity.EnterpriseColumn;
import cc.messcat.front.IndexInfoBean;

/**
 * @author Messcat
 * @version 1.1
 */
public interface EpColumnManagerDao {

	public List findEnterpriseColumn();

	public List findAllEnterpriseColumn();

	public EnterpriseColumn getEnterpriseColumn(Long id);

	public void saveEnterpriseColumn(EnterpriseColumn enterprisecolumn);

	public void updateEnterpriseColumn(EnterpriseColumn enterprisecolumn, Authorities auth);

	public void deleteEnterpriseColumn(Long id);

	public List findSubColumn(Long father);

	public boolean isNameUnique(String names, String orgName, Long father);

	public EnterpriseColumn getEnterpriseColumn(String state);

	public List findFrontInfoFrontNumNotNull();
	
	public IndexInfoBean findProductClassify();

	public List findTreeByFather(Long father);

	//判断是否叶节点
	public boolean isLeafNode(Long columnId);
	
	//根据父级栏目查找出本次新增的栏目排序
	public Long getOrderColumnByFather(Long father);
	
	/**
	 * 查找某个页面类型的栏目
	 * @param fatherId
	 * @param pageTypeId
	 * @return
	 */
	public List<EnterpriseColumn> findColumnsByPageType(Long fatherId, Long pageTypeId); 
}