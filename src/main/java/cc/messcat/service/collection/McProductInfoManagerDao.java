package cc.messcat.service.collection;

import java.util.LinkedHashMap;
import java.util.List;

import cc.messcat.entity.Attribute;
import cc.messcat.entity.McProductInfo;
import cc.messcat.entity.ProductAttr;
import cc.modules.commons.Pager;

public interface McProductInfoManagerDao {

	public void addMcProductInfo(McProductInfo mcProductInfo);
	
	public void modifyMcProductInfo(McProductInfo mcProductInfo);
	
	public void removeMcProductInfo(McProductInfo mcProductInfo);
	
	public void removeMcProductInfo(Long id);
	
	public McProductInfo retrieveMcProductInfo(Long id);
	
	@SuppressWarnings("unchecked")
	public List retrieveAllMcProductInfos();
	
	/**
	 * 查找所有上架的商品
	 * @return list
	 */
	public List retrieveAllMcProductInfosIsSale();
	
	public List retrieveAllMcProductInfosFromTypeId(Long id);
	
	public Pager retrieveMcProductInfosPager(int pageSize, int pageNo);
	
	public Pager findFrontMCProductInfo(int pageSize, int pageNo, McProductInfo mcProductInfo);
	
	public Pager findMCProductInfo(int pageSize, int pageNo, McProductInfo mcProductInfo);
	
	public List findMCProductInfoByName(String name);
	
	public List findHotSeries(Long id);
	/**
	 * @return
	 */
	public List retrieveAllDateClass();
	
	public List retrieveAllProductByNewsAndDateClass(String date);
	
	/**
	 * 初始化产品详细页面的单选属性、以及其值(未测试)
	 * @param product
	 * @param attrType
	 * @return
	 */
	public LinkedHashMap<Attribute, List<ProductAttr>> initProductDetail(McProductInfo product, String attrType);
	
	/**
	 * 修改购物车、订单中的产品价格
	 * @param mcProductInfo
	 * @param price
	 */
	public void updateProductAndPrice(McProductInfo mcProductInfo, double price);
	
	//判断该商品有没有被下过订单
	public boolean findOrderByProductId(Long productId);
	
	//判断该商品有没有被下过订单
    public boolean findCartByProductId(Long productId);
    
    public List findAllSeries(String id);
	
}