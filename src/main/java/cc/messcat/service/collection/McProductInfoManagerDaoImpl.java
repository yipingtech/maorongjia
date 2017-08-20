package cc.messcat.service.collection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.message.BasicHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cc.messcat.bases.BaseManagerDaoImpl;
import cc.messcat.entity.Attribute;
import cc.messcat.entity.McProductInfo;
import cc.messcat.entity.OrderInfo;
import cc.messcat.entity.PayOrder;
import cc.messcat.entity.ProductAttr;
import cc.messcat.entity.Stock;
import cc.messcat.wechat.weixin.popular.client.LocalHttpClient;
import cc.modules.commons.Pager;
import cc.modules.util.ObjValid;

public class McProductInfoManagerDaoImpl extends BaseManagerDaoImpl implements McProductInfoManagerDao {
	private static Logger log = LoggerFactory.getLogger(McProductInfoManagerDaoImpl.class); 
	/**
	 * 初始化产品详细页面的单选属性、以及其值
	 * 
	 * @param product
	 * @param attrType
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public LinkedHashMap<Attribute, List<ProductAttr>> initProductDetail(McProductInfo product, String attrType) {
		LinkedHashMap<Attribute, List<ProductAttr>> productAttrMap = new LinkedHashMap<Attribute, List<ProductAttr>>();
		 Pager pager = this.attributeDao.getByProductType(0, 0,product.getProductType().getId());
//		Pager pager = attributeDao.retrieveObjectsPager(1, 1, product, "id", Constants.ASC, Constants.ENABLE);
		List<Attribute> attributeList = pager.getResultList();
		for (int i = attributeList.size() - 1; i >= 0; i--) {
			Attribute attribute = attributeList.get(i);
			Map<String, Object> attrs = new HashMap<String, Object>();
			attrs.put("product", product);
			attrs.put("attr.attrInputType", attrType);
			attrs.put("attr", attribute);
			attrs.put("status", "1");
			List<ProductAttr> productAttrList = this.productAttrDao.queryList(ProductAttr.class, "id", "", attrs);
			if (ObjValid.isValid(productAttrList)) {
				productAttrMap.put(attribute, productAttrList);
			}
		}
		return productAttrMap;
	}

	public void addMcProductInfo(McProductInfo mcProductInfo) {
		this.mcProductInfoDao.save(mcProductInfo);
	}

	public void modifyMcProductInfo(McProductInfo mcProductInfo) {
		this.mcProductInfoDao.update(mcProductInfo);
	}

	@SuppressWarnings("unchecked")
	public void updateProductAndPrice(McProductInfo mcProductInfo, double price) {
		this.mcProductInfoDao.update(mcProductInfo);
		if (mcProductInfo.getShopPrice() != price) {
			// 更新购物车价格
			this.cartInfoDao.updateByProduct(mcProductInfo);
			// 更新清单价格
			Map<String, Object> attrs = new HashMap<String, Object>();
			attrs.put("product", mcProductInfo);
			attrs.put("orderStatus", "0");
			attrs.put("status", "1");
			List<OrderInfo> orderInfos = this.orderInfoDao.queryList(OrderInfo.class, "id", "asc", attrs);
			for (OrderInfo orderInfo : orderInfos) {
				Double orderInfoTotalPrice = mcProductInfo.getShopPrice() * orderInfo.getAmount();
				// balance（清单差额）
				Double balance = orderInfoTotalPrice - orderInfo.getTotalPrice();
				orderInfo.setPrice(mcProductInfo.getShopPrice());
				orderInfo.setTotalPrice(orderInfoTotalPrice);
				this.orderInfoDao.update(orderInfo);
				PayOrder payOrder = orderInfo.getPayOrder();
				// 订单原需要支付金额加上每条清单差额
				if (balance > 0) {
					payOrder.setOrderAmount(payOrder.getOrderAmount() + balance);
					payOrder.setProductAmount(payOrder.getProductAmount() + balance);
				} else {
					balance = Math.abs(balance);
					payOrder.setOrderAmount(payOrder.getOrderAmount() - balance);
					payOrder.setProductAmount(payOrder.getProductAmount() - balance);
				}
				this.payOrderDao.update(payOrder);
			}
		}
	}

	public void removeMcProductInfo(McProductInfo mcProductInfo) {
		this.mcProductInfoDao.delete(mcProductInfo);
	}

	public void removeMcProductInfo(Long id) {
		McProductInfo mcProductInfo = this.mcProductInfoDao.get(id);
		Map<String, Object> props = new HashMap<String, Object>();
		props.put("status", "0");
		Map<String, Object> attrs = new HashMap<String, Object>();
		attrs.put("product", mcProductInfo);
		this.stockDao.update(Stock.class, props, attrs);
		this.productAttrDao.update(ProductAttr.class, props, attrs);
		this.mcProductInfoDao.delete(id);
	}

	public McProductInfo retrieveMcProductInfo(Long id) {
		return (McProductInfo) this.mcProductInfoDao.get(id);
	}

	@SuppressWarnings("unchecked")
	public List retrieveAllMcProductInfos() {
		return this.mcProductInfoDao.findAll();
	}
	
	/**
	 * 查找所有上架的商品
	 * @return list
	 */
	public List retrieveAllMcProductInfosIsSale(){
		return this.mcProductInfoDao.findAllIsSale();
	}

	public List retrieveAllMcProductInfosFromTypeId(Long id) {
		return this.mcProductInfoDao.findAllFromTypeId(id);
	}

	public Pager retrieveMcProductInfosPager(int pageSize, int pageNo) {
		return this.mcProductInfoDao.getPager(pageSize, pageNo);
	}

	/**
	 * 带权限校验的产品搜索
	 */
	public Pager findMCProductInfo(int pageSize, int pageNo, McProductInfo mcProductInfo) {

		StringBuffer sb = new StringBuffer(" where status = 1 ");

		List result = null;

		if (mcProductInfo.getTitle() != null && !"".equals(mcProductInfo.getTitle().toString()))
			sb.append(" and title like  '%").append(mcProductInfo.getTitle().trim().toString()).append("%' ");
		
		sb.append(" order by orderPara asc,updatetime desc");
		result = this.mcProductInfoDao.findMcProductInfoByWhere(sb.toString());

		int rowCount = result.size();

		if (rowCount < pageSize)
			pageNo = 1;
		int startIndex = pageSize * (pageNo - 1);

		result = result.subList(startIndex, (pageSize + startIndex) <= result.size() ? (pageSize + startIndex) : result.size());

		return new Pager(pageSize, pageNo, rowCount, result);
	}

	/**
	 * 不带权限的产品列表查询
	 * 
	 * @param pageSize
	 * @param pageNo
	 * @param mcProductInfo
	 * @return
	 */
	public Pager findFrontMCProductInfo(int pageSize, int pageNo, McProductInfo mcProductInfo) {

		StringBuffer sb = new StringBuffer(" where status = 1 ");

		List result = null;

		if (mcProductInfo.getIsNew() != null && !"".equals(mcProductInfo.getIsNew()))
			sb.append(" and isNew = " + mcProductInfo.getIsNew());

		if (mcProductInfo.getTitle() != null && !"".equals(mcProductInfo.getTitle().toString()))
			sb.append(" and title like  '%").append(mcProductInfo.getTitle().trim().toString()).append("%' ");
		sb.append(" order by orderPara asc,updatetime desc");
		result = this.mcProductInfoDao.findMcProductInfoByWhere(sb.toString());

		int rowCount = result.size();

		if (rowCount < pageSize)
			pageNo = 1;
		int startIndex = pageSize * (pageNo - 1);

		result = result.subList(startIndex, (pageSize + startIndex) <= result.size() ? (pageSize + startIndex) : result.size());

		return new Pager(pageSize, pageNo, rowCount, result);
	}

	public List retrieveAllDateClass() {
		return this.mcProductInfoDao.retrieveAllDateClass();
	}

	public List retrieveAllProductByNewsAndDateClass(String date) {
		return this.mcProductInfoDao.retrieveAllProductByNewsAndDateClass(date);
	}

	public static void main(String[] args) throws UnsupportedOperationException, IOException {
		int flag = 1;
		while (true) {
			log.info("start " + flag);

			Header textHeader = new BasicHeader(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_FORM_URLENCODED.toString());
			HttpUriRequest httpUriRequest = RequestBuilder.post().setHeader(textHeader)
				.setUri("http://vote.cebnet.com.cn/data/receive.cc").addParameter("id", "8").addParameter("answerId16", "112")
				.build();
			HttpResponse response = LocalHttpClient.execute(httpUriRequest);

			try {
				// wait for 1 seconds
				int b = (int) (Math.random() * 10);
				Thread.sleep(b * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			log.info("1 seconds passed, end " + flag++);
			InputStream in = response.getEntity().getContent();
			InputStreamReader ir = new InputStreamReader(in);
			BufferedReader br = new BufferedReader(ir);
			String s;
			while ((s=br.readLine())!=null) {
				log.info(s);
				s=br.readLine();
			}
			in.close();
			ir.close();
			br.close();
		}
	}
	//判断该商品有没有被下过订单
	public boolean findOrderByProductId(Long productId){
		return this.mcProductInfoDao.findOrderByProductId(productId);
	}
	
	//判断该商品有没有被下过订单
    public boolean findCartByProductId(Long productId){
		return this.mcProductInfoDao.findCartByProductId(productId);
	}

    //首页 -- 顶部搜索栏 模糊查询
	@Override
	public List findMCProductInfoByName(String name) {
		return this.mcProductInfoDao.findMCProductInfoByName(name);
	}

	//首页 -- 热门系列 -- 更多
	@Override
	public List findHotSeries(Long id) {
		return this.mcProductInfoDao.findHotSeries(id);
	}

	@Override
	public List findAllSeries(String id) {
		// TODO Auto-generated method stub
		return this.mcProductInfoDao.findAllSeries(id);
	}
	
}