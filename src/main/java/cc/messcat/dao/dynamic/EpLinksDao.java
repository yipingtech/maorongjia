package cc.messcat.dao.dynamic;

import java.util.List;

import cc.modules.commons.Pager;
import cc.messcat.entity.EnterpriseLinks;

/**
 * @author Messcat
 * @version 1.1
 *
 */
public interface EpLinksDao {

	public EnterpriseLinks get(Long long1);

	public void save(EnterpriseLinks enterpriselinks);

	public void update(EnterpriseLinks enterpriselinks);

	public void delete(EnterpriseLinks enterpriselinks);

	public void delete(Long long1);

	public List findAll();

	public Pager getObjectListByClass(int i, int j, Class class1, String s);

	public List getLinksAndAdByClassAndSize(Class class1, Long long1);

	public List<String> getDistinctFrontNum();

	public List findAllByFrontNum(String frontNum);

}