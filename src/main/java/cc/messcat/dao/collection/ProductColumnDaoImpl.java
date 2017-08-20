package cc.messcat.dao.collection;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;

import cc.messcat.bases.BaseDaoImpl;
import cc.messcat.entity.EnterpriseColumn;
import cc.messcat.entity.McProductInfo;
import cc.messcat.entity.ProductColumn;
import cc.modules.commons.Pager;
import cc.modules.util.ObjValid;

public class ProductColumnDaoImpl extends BaseDaoImpl implements ProductColumnDao {

	public void save(ProductColumn productColumn) {
		getHibernateTemplate().save(productColumn);
	}

	public void update(ProductColumn productColumn) {
		getHibernateTemplate().update(productColumn);
	}

	public void delete(ProductColumn productColumn) {
		getHibernateTemplate().delete(productColumn);
	}

	public void deleteByProduct(Long id) {
		try {
			Session session = this.getSession();
			StringBuffer str = new StringBuffer();
			str.append("delete from ProductColumn where mcProduct.id=" + id);
			Query query = session.createQuery(str.toString());
			query.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 判断是否有商品被加为栏目商品
	 * @param id
	 * @return true 为有  false 为无
	 */
	public boolean findByProduct(Long id){
		List productColumnList = getHibernateTemplate().find(
				"from ProductColumn WHERE PRODUCT_ID = ?",id);
		if(!productColumnList.isEmpty()){
				return true;
		}
		return false;
	}

	public void delete(Long id) {
		getHibernateTemplate().delete(this.get(id));
	}

	public ProductColumn get(Long id) {
		return (ProductColumn) getHibernateTemplate().get(ProductColumn.class, id);
	}

	@SuppressWarnings("unchecked")
	public List findAll() {
		return getHibernateTemplate().find("from ProductColumn p order by p.mcProduct.orderPara asc ");
	}

	@SuppressWarnings("unchecked")
	public Pager getPager(int pageSize, int pageNo) {
		Session session = this.getSession();
		Criteria criteria = session.createCriteria(ProductColumn.class);
		int rowCount = ((Integer) criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
		criteria.setProjection(null);
		int startIndex = pageSize * (pageNo - 1);
		criteria.setFirstResult(startIndex);
		criteria.setMaxResults(pageSize);
		List result = criteria.list();
		return new Pager(pageSize, pageNo, rowCount, result);
	}

	/**
	 * 
	 * @param mcProduct
	 *            产品
	 * @param enterpriseColumn
	 *            栏目
	 * @return 产品栏目列表
	 * 
	 *         根据产品、栏目分页查找对应列表
	 */
	public Pager findProductByColumnPro(McProductInfo mcProduct, EnterpriseColumn enterpriseColumn, int pageSize, int pageNo) {
		try {
			/*Session session = this.getSession();
			session.clear();
			Criteria criteria = session.createCriteria(ProductColumn.class,"p");
			if (mcProduct != null && mcProduct.getId() != null) {
				criteria.add(Restrictions.eq("p.mcProduct.id", mcProduct.getId()));
			}
			if (enterpriseColumn != null && enterpriseColumn.getId() != null) {
				criteria.add(Restrictions.eq("p.enterpriseColumn.id", enterpriseColumn.getId()));
			}
			int rowCount = ((Integer) criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
			criteria.setProjection(null);
			int startIndex = pageSize * (pageNo - 1);
			criteria.setFirstResult(startIndex);
			criteria.setMaxResults(pageSize);
			List result = criteria.list();
			return new Pager(pageSize, pageNo, rowCount, result);*/
			
			StringBuffer sb = new StringBuffer("  from ProductColumn pro where pro.mcProduct.status='1'  ");
			if (mcProduct != null && mcProduct.getId() != null) {
				sb.append(" and pro.mcProduct.id ="+mcProduct.getId());
			}
			if (enterpriseColumn != null && enterpriseColumn.getId() != null) {
				sb.append(" and pro.enterpriseColumn.id ="+enterpriseColumn.getId());
			}
			sb.append(" order by pro.mcProduct.orderPara asc");
			List result = getHibernateTemplate().find(sb.toString());
			return new Pager(pageSize, pageNo, result.size(), result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @param enterpriseColumn
	 *            栏目
	 * @param mcProduct
	 *            产品
	 * @return 产品栏目列表
	 * 
	 *         根据栏目、产品分页查找非该栏目下的产品
	 */
	public Pager findProductByNoColumn(McProductInfo mcProduct, EnterpriseColumn enterpriseColumn, int pageSize, int pageNo) {
		Pager pager = null;
		try {
			StringBuffer sb = new StringBuffer("  from McProductInfo pro where pro.status='1'  ");
			if (mcProduct != null && mcProduct.getTitle() != null) {
				sb.append(" and pro.title like '%" + mcProduct.getTitle() + "%' ");
			}
			sb.append(" and pro.id not in (select pc.mcProduct.id from ProductColumn pc where pc.enterpriseColumn.id = '"
				+ enterpriseColumn.getId() + "') order by pro.orderPara asc");
			Session session = this.getSession();
			Query query = session.createQuery(sb.toString());

			pager = new Pager(pageSize, pageNo, query.list().size(), new ArrayList());
			query.setFirstResult(pager.getStartIndex());
			query.setMaxResults(pageSize);
			pager.setResultList(query.list());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pager;
	}

	/**
	 * 
	 * @param mcProduct
	 *            产品
	 * @param enterpriseColumn
	 *            栏目
	 * @return 产品栏目列表
	 * 
	 *         根据产品、栏目查找对应列表
	 */
	public List<?> findProductByColumnPro(McProductInfo mcProduct, EnterpriseColumn enterpriseColumn) {
		List<?> list = null;
		if (!ObjValid.isValid(mcProduct) && !ObjValid.isValid(enterpriseColumn)) {
			list = null;
		} else {
			StringBuffer sBuffer = new StringBuffer();
			sBuffer.append("from ProductColumn pc where pc.mcProduct.status='1'  ");
			if (ObjValid.isValid(mcProduct)) {
				if (ObjValid.isValid(mcProduct.getId())) {
					sBuffer.append(" and pc.mcProduct.id = " + mcProduct.getId());
				}
				if (ObjValid.isValid(mcProduct.getTitle())) {
					sBuffer.append(" and pc.mcProduct.title='" + mcProduct.getTitle() + "' ");
					// sBuffer.append(" (select pro.id from McProductInfo pro where pro.title='"+mcProduct.getTitle()+"') ");
				}
			}
			if (ObjValid.isValid(enterpriseColumn)) {
				if (ObjValid.isValid(enterpriseColumn, enterpriseColumn.getId())) {
					sBuffer.append(" and pc.enterpriseColumn.id = " + enterpriseColumn.getId());
				}
			}
			sBuffer.append(" order by pc.mcProduct.orderPara asc");
			list = getHibernateTemplate().find(sBuffer.toString());
		}
		return list;
	}
	
	/**
	 * 根据产品、栏目查找对应列表
	 * 产品是上架的产品
	 * @param mcProduct
	 * @param enterpriseColumn
	 * @return
	 */
	public List<?> findIsSaleProductByColumnPro(McProductInfo mcProduct, EnterpriseColumn enterpriseColumn){
		List<?> list = null;
		if (!ObjValid.isValid(mcProduct) && !ObjValid.isValid(enterpriseColumn)) {
			list = null;
		} else {
			StringBuffer sBuffer = new StringBuffer();
			sBuffer.append("from ProductColumn pc where pc.mcProduct.status='1' and pc.mcProduct.isSale='1' ");
			if (ObjValid.isValid(mcProduct)) {
				if (ObjValid.isValid(mcProduct.getId())) {
					sBuffer.append(" and pc.mcProduct.id = " + mcProduct.getId());
				}
				if (ObjValid.isValid(mcProduct.getTitle())) {
					sBuffer.append(" and pc.mcProduct.title='" + mcProduct.getTitle() + "' ");
					// sBuffer.append(" (select pro.id from McProductInfo pro where pro.title='"+mcProduct.getTitle()+"') ");
				}
			}
			if (ObjValid.isValid(enterpriseColumn)) {
				if (ObjValid.isValid(enterpriseColumn, enterpriseColumn.getId())) {
					sBuffer.append(" and pc.enterpriseColumn.id = " + enterpriseColumn.getId());
				}
			}
			sBuffer.append(" order by pc.mcProduct.orderPara asc");
			list = getHibernateTemplate().find(sBuffer.toString());
		}
		return list;
	}
	

	/**
	 * 
	 * @param enterpriseColumn
	 *            栏目
	 * @return 产品栏目列表
	 * 
	 *         根据栏目查找非该栏目下的产品
	 */
	public List<?> findProductByNoColumn(EnterpriseColumn enterpriseColumn) {
		StringBuffer sBuffer = new StringBuffer();

		sBuffer.append("from ProductColumn pc where pc.mcProduct.status='1'  ");
		if (enterpriseColumn != null && enterpriseColumn.getId() != null) {
			sBuffer.append(" and pc.enterpriseColumn.id != " + enterpriseColumn.getId());
		}
		sBuffer.append(" order by pc.mcProduct.orderPara asc");
		return getHibernateTemplate().find(sBuffer.toString());
	}

	/**
	 * 
	 * @param enterpriseColumn
	 *            栏目
	 * @return 产品栏目列表
	 * 
	 *         根据产品搜索条件查找产品
	 */
	public Pager findProductByCondition(McProductInfo mcProduct, Long columnId, int pageSize, int pageNo) {
		Pager pager = null;
		try {
			StringBuffer sb = new StringBuffer("from ProductColumn pro where 1=1  ");
			if (ObjValid.isValid(columnId)) {
				sb.append(" and pro.enterpriseColumn.id =" + columnId + " ");
			}
			if (ObjValid.isValid(mcProduct)) {
				if (ObjValid.isValid(mcProduct.getTitle())) {
					sb.append(" and pro.mcProduct.title like '%" + mcProduct.getTitle() + "%' ");
				}
				if (ObjValid.isValid(mcProduct.getAddtime())) {
					sb.append(" order by pro.mcProduct.addtime desc,pro.mcProduct.orderPara asc ");
				}
				if (ObjValid.isValid(mcProduct.getSaleValume())) {
					if (mcProduct.getSaleValume() == 21) {
						sb.append(" order by pro.mcProduct.saleValume desc,pro.mcProduct.orderPara asc ");
					}
					if (mcProduct.getSaleValume() == 12) {
						sb.append(" order by pro.mcProduct.saleValume asc,pro.mcProduct.orderPara asc ");
					}
				}
				if (ObjValid.isValid(mcProduct.getShopPrice())) {
					if (mcProduct.getShopPrice() == 21) {
						sb.append(" order by pro.mcProduct.shopPrice desc,pro.mcProduct.orderPara asc ");
					}
					if (mcProduct.getShopPrice() == 12) {
						sb.append(" order by pro.mcProduct.shopPrice asc,pro.mcProduct.orderPara asc ");
					}
				}
				if (ObjValid.isValid(mcProduct.getGrade())) {
					if (mcProduct.getGrade() == 21) {
						sb.append(" order by pro.mcProduct.grade desc,pro.mcProduct.orderPara asc ");
					}
					if (mcProduct.getGrade() == 12) {
						sb.append(" order by pro.mcProduct.grade asc,pro.mcProduct.orderPara asc ");
					}
				}
			}
			Session session = this.getSession();
			Query query = session.createQuery(sb.toString());
			// log.info(sb.toString());
			int startIndex = pageSize * (pageNo - 1);
			query.setFirstResult(startIndex);
			query.setMaxResults(pageSize);
			// pager.setResultList(query.list());
			pager = new Pager(pageSize, pageNo, query.list().size(), query.list());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pager;
	}

	/**
	 * @param columnId  栏目id
	 * @return mcproductinfo 
	 * 
	 * 通过栏目查询 产品
	 */
	public List<?> findProductByColumn(Long columnId){
		StringBuffer sb = new StringBuffer("  from McProductInfo pro where pro.status='1  ");
		if (ObjValid.isValid(columnId)) {
			sb.append(" and pro.id in (select pc.mcProduct.id from ProductColumn pc where pc.enterpriseColumn.id ="+columnId+") order by pro.orderPara asc");
		}
		Session session = this.getSession();
		Query query = session.createQuery(sb.toString());
		return (List<McProductInfo>)query.list();
	}
	
	/**
	 * @param columnId  栏目id 
	 * @return mcproductinfo 
	 * 
	 * 通过栏目查询 上架产品
	 */
	public List<?> findProductByColumnIsSale(Long columnId){
		StringBuffer sb = new StringBuffer("  from McProductInfo pro where pro.status=1 and pro.isSale=1");
		if (ObjValid.isValid(columnId)) {
			sb.append(" and pro.id in (select pc.mcProduct.id from ProductColumn pc where pc.enterpriseColumn.id ="+columnId+") order by pro.orderPara asc");
		}
		Session session = this.getSession();
		Query query = session.createQuery(sb.toString());
		return (List<McProductInfo>)query.list();
	}

	/**
	 * 获取产品的第一个栏目（品牌列表栏目）
	 * */
	@Override
	public EnterpriseColumn findByProduct(McProductInfo mcProduct) {
		StringBuffer sb = new StringBuffer("from ProductColumn p where p.mcProduct.id = "+mcProduct.getId()+" and p.enterpriseColumn.father = '49' order by id desc");
		Session session = this.getSession();
		Query query = session.createQuery(sb.toString());
		List result = query.list();
		if(ObjValid.isValid(result) && ObjValid.isValid((ProductColumn) result.get(0))){
			ProductColumn p = (ProductColumn) result.get(0);
			return p.getEnterpriseColumn();
		}
		return null;
	}
	
/*	 public static void main(String[] args) {
		  ApplicationContext ct = new ClassPathXmlApplicationContext("applicationContext*.xml");
		  ProductColumnDaoImpl dao= (ProductColumnDaoImpl)ct.getBean("productColumnDao");
//		  dao.deleteByProduct(102L);
		  System.out.println(dao.findProductByColumnIsSale(47L).size());
		 }*/


}