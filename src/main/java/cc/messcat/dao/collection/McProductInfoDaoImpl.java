package cc.messcat.dao.collection;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;

import cc.messcat.bases.BaseDaoImpl;
import cc.messcat.entity.McProductInfo;
import cc.modules.commons.Pager;
import cc.modules.util.ObjValid;

public class McProductInfoDaoImpl extends BaseDaoImpl implements McProductInfoDao {

	public void save(McProductInfo mcProductInfo) {
		getHibernateTemplate().save(mcProductInfo);
	}

	public void update(McProductInfo mcProductInfo) {
		getHibernateTemplate().update(mcProductInfo);
	}

	public void delete(McProductInfo mcProductInfo) {
		getHibernateTemplate().delete(mcProductInfo);
	}

	public void delete(Long id) {
		try {
    		Session session = this.getSession();
    		StringBuffer str = new StringBuffer();
    		str.append("update McProductInfo set status = 0 where id="+id);
			Query query = session.createQuery(str.toString());
    		query.executeUpdate();
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
	}

	public McProductInfo get(Long id) {
		return (McProductInfo) getHibernateTemplate().get(McProductInfo.class, id);
	}

	@SuppressWarnings("unchecked")
	public List findAll() {
		return getHibernateTemplate().find("from McProductInfo m where m.status='1' order by m.orderPara asc");
	}

	/*
	 * 查找所有为上架的商品
	 */
	public List findAllIsSale(){
		return getHibernateTemplate().find("from McProductInfo m where m.status='1' and m.isSale='1' order by m.orderPara asc");
	}
	
	public List findAllFromTypeId(Long id) {
		return getHibernateTemplate().find("from McProductInfo m where m.status='1' and product_type="+id+" order by m.orderPara asc");
	}

	@SuppressWarnings("unchecked")
	public Pager getPager(int pageSize, int pageNo) {
		Session session = getHibernateTemplate().getSessionFactory().openSession();
		Criteria criteria = session.createCriteria(McProductInfo.class);
		criteria.addOrder(Order.asc("orderPara"));
		int rowCount = ((Integer) criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
		criteria.setProjection(null);
		int startIndex = pageSize * (pageNo - 1);
		criteria.setFirstResult(startIndex);
		criteria.setMaxResults(pageSize);
		List result = criteria.list();
		session.close();
		return new Pager(pageSize, pageNo, rowCount, result);
	}

	public List findMcProductInfoByWhere(String where) {
		List ecList = getHibernateTemplate().find(
			(new StringBuffer("from McProductInfo ")).append(where).append(" order by orderPara asc,updatetime DESC ").toString());
		return ecList;
	}

	public List retrieveAllDateClass() {
		List ecList = getHibernateTemplate().find(
			"select new McProductInfo(addtime) from McProductInfo WHERE is_new = 1 GROUP BY ADDTIME");
		return ecList;
	}

	public List retrieveAllProductByNewsAndDateClass(String date) {
		List ecList = getHibernateTemplate().find(
			"from McProductInfo WHERE is_new = 1 and ADDTIME = ? order by order_para asc ",date);
		return ecList;
	}
	
	//判断该商品有没有被下过订单
	public boolean findOrderByProductId(Long productId){
		List orderList = getHibernateTemplate().find(
				"from OrderInfo WHERE PRODUCT = ?",productId);
		if(ObjValid.isValid(orderList)){
			return true;
		}
		return false;
	}
	
	//判断该商品有没有被加入购物车
	public boolean findCartByProductId(Long productId){
		List cartList = getHibernateTemplate().find(
				"from CartInfo WHERE PRODUCT_ID = ?",productId);
		if(ObjValid.isValid(cartList)){
			return true;
		}
		return false;
	}

	@Override
	public List findMCProductInfoByName(String name) {
		List list = getHibernateTemplate().find("from ProductColumn p WHERE p.mcProduct.status = '1' and p.mcProduct.isSale = '1' and p.mcProduct.title like ? order by p.mcProduct.orderPara asc,p.mcProduct.saleValume desc","%"+name.trim().toString()+"%");
		return list;
	}

	@Override
	public List findHotSeries(Long id) {
		List list = getHibernateTemplate().find(
				"from ProductColumn p WHERE p.mcProduct.status = '1' and p.mcProduct.isSale = '1' and  p.enterpriseColumn.id = "+id+" order by p.mcProduct.orderPara asc");
		return list;
	}

	@Override
	public List findAllSeries(String id) {
		List list = getHibernateTemplate().find(
				"from ProductColumn p WHERE p.mcProduct.status = '1' and p.mcProduct.isSale = '1' and  p.enterpriseColumn.id in("+id+") order by p.mcProduct.orderPara asc");
		return list;
	}
	
/*	 public static void main(String[] args) {
		  ApplicationContext ct = new ClassPathXmlApplicationContext("applicationContext*.xml");
		  McProductInfoDaoImpl dao= (McProductInfoDaoImpl)ct.getBean("mcProductInfoDao");
		  System.out.println(dao.findAllIsSale().size());
		 }*/
}