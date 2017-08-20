
package cc.messcat.service.order;

import java.util.List;
import cc.modules.commons.Pager;
import cc.messcat.entity.OrderInfo;
import cc.messcat.entity.PayOrder;

public interface OrderInfoManagerDao{

	public void addOrderInfo(OrderInfo orderInfo);
	
	public void modifyOrderInfo(OrderInfo orderInfo);
	
	public void removeOrderInfo(OrderInfo orderInfo);
	
	public void removeOrderInfo(Long id);
	
	public OrderInfo retrieveOrderInfo(Long id);
	
	@SuppressWarnings("unchecked")
	public List retrieveAllOrderInfos();
	
	public Pager retrieveOrderInfosPager(int pageSize, int pageNo);
	
	/**
	 * 创建清单列表
	 * @param cartIdList（购物车id）
	 * @return
	 */
	public List<OrderInfo> createOrderInfos(List<String> cartIdList);
	
	/**
	 * 通过订单查找清单
	 * @param payOrder
	 * @return
	 */
	public List<OrderInfo> searchByPayOrder(PayOrder payOrder);
	
}