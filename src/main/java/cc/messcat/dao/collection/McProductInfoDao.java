package cc.messcat.dao.collection;

import java.util.List;

import cc.messcat.bases.BaseDao;
import cc.messcat.entity.McProductInfo;
import cc.modules.commons.Pager;

public interface McProductInfoDao extends BaseDao{

	public void save(McProductInfo mcProductInfo);
	
	public void update(McProductInfo mcProductInfo);
	
	public void delete(McProductInfo mcProductInfo);
	
	public void delete(Long id);
	
	public McProductInfo get(Long id);

	public List findAllFromTypeId(Long id);
	
	@SuppressWarnings("unchecked")
	public List findAll();

	/*
	 * 查找所有为上架的商品
	 */
	public List findAllIsSale();
	
	public Pager getPager(int pageSize, int pageNo);
	
	public  List findMcProductInfoByWhere(String where);
	
	public List retrieveAllDateClass();
	
	public List retrieveAllProductByNewsAndDateClass(String date);
	
	//判断该商品有没有被下过订单
	public boolean findOrderByProductId(Long productId);
	
	//判断该商品有没有被加入购物车
	public boolean findCartByProductId(Long productId);
	
	public List findMCProductInfoByName(String name) ;
	
	public List findHotSeries(Long id);
	
	public List findAllSeries(String id);
	
}