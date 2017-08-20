package cc.messcat.service.pay;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.record.formula.functions.T;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cc.messcat.bases.BaseManagerDaoImpl;
import cc.messcat.entity.AgentOrder;
import cc.messcat.entity.CartInfo;
import cc.messcat.entity.CommissionInfo;
import cc.messcat.entity.IntergralInfo;
import cc.messcat.entity.McProductInfo;
import cc.messcat.entity.Member;
import cc.messcat.entity.OrderInfo;
import cc.messcat.entity.ParameterSet;
import cc.messcat.entity.PayOrder;
import cc.messcat.entity.ProductAttr;
import cc.messcat.entity.Stock;
import cc.messcat.wechat.weixin.popular.api.MessageAPI;
import cc.messcat.wechat.weixin.popular.bean.templatemessage.TemplateMessage;
import cc.messcat.wechat.weixin.popular.bean.templatemessage.TemplateMessageItem;
import cc.messcat.wechat.weixin.popular.bean.templatemessage.TemplateMessageResult;
import cc.messcat.wechat.weixin.popular.support.MessageManager;
import cc.messcat.wechat.weixin.popular.support.TokenManager;
import cc.modules.commons.Pager;
import cc.modules.constants.Constants;
import cc.modules.util.CalendarHelper;
import cc.modules.util.DateHelper;
import cc.modules.util.FormatStringUtil;
import cc.modules.util.HQLUtil;
import cc.modules.util.ObjValid;

@SuppressWarnings("serial")
public class PayOrderManagerDaoImpl extends BaseManagerDaoImpl implements PayOrderManagerDao {
	private static Logger log = LoggerFactory.getLogger(PayOrderManagerDaoImpl.class);

	/**
	 * 修改订单
	 */
	public void modifyPayOrder(PayOrder payOrder) {
		Map<String, Object> attrs = new HashMap<String, Object>();
		Map<String, Object> props = new HashMap<String, Object>();
		props.put("name", payOrder.getName());
		props.put("phone", payOrder.getPhone());
		props.put("fixPhone", payOrder.getFixPhone());
		props.put("pastalcode", payOrder.getPastalcode());
		props.put("address", payOrder.getAddress());
		props.put("comments", payOrder.getComments());
		props.put("shippingName", payOrder.getShippingName());
		props.put("invoiceNum", payOrder.getInvoiceNum());
		attrs.put("status", "1");
		attrs.put("orderNum", payOrder.getOrderNum());
		payOrderDao.update(PayOrder.class, props, attrs);
	}

	/**
	 * 根据id删除数据或还原(把数据修改删除态)
	 */
	public void deletePayOrder(PayOrder payOrder, String startStatus, String endStatus) {
		Map<String, Object> props = new HashMap<String, Object>();
		Map<String, Object> attrs = new HashMap<String, Object>();
		Map<String, Object> attrs1 = new HashMap<String, Object>();
		attrs.put("id", payOrder.getId());
		payOrder = payOrderDao.query(PayOrder.class, attrs);
		attrs1.put("payOrder", payOrder);
		attrs.put("status", startStatus);
		props.put("status", endStatus);
		payOrderDao.update(PayOrder.class, props, attrs);
		payOrderDao.update(OrderInfo.class, props, attrs1);
	}

	/**
	 * 根据id查询订单
	 */
	public PayOrder retrievePayOrderById(Long id) {
		Map<String, Object> attrs = new HashMap<String, Object>();
		attrs.put("id", id);
		return payOrderDao.query(PayOrder.class, attrs);
	}

	/**
	 * 根据id彻底删除数据
	 */
	public void deletePayOrder(Long id) {
		Map<String, Object> attrs1 = new HashMap<String, Object>();
		Map<String, Object> attrs2 = new HashMap<String, Object>();
		attrs1.put("id", id);
		attrs2.put("payOrder", retrievePayOrderById(id));
		payOrderDao.delete(OrderInfo.class, attrs2);
		payOrderDao.delete(PayOrder.class, attrs1);
	}

	/**
	 * @描述 保存订单和清单 及删除指定购物车内容
	 * @author Seven
	 * @date 2015-10-15
	 * @param payOrder
	 * @param orderInfoList
	 * @param cartIdList
	 *            (购物车id)
	 */
	public PayOrder addPayOrder(PayOrder payOrder, List<OrderInfo> orderInfoList, List<String> cartIdList) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String payOrderNum = sdf.format(new Date()) + String.format("%1$04d", 1);
		payOrder.setOrderNum(payOrderNum);
		payOrder.setCreateTime(new Date());
		// payOrder.setBestTime(new Date()); //送货最佳时间
		payOrder.setShippingStatus("0"); // 未发货状态
		payOrder.setPayStatus("0");
		payOrder.setStatus("1");
		payOrderDao.save(payOrder);
		double totoalPrice = 0;
		for (OrderInfo orderInfo : orderInfoList) {
			orderInfo.setMember(payOrder.getMember());
			orderInfo.setCreateTime(new Date());
			orderInfo.setOrderTime(new Date());
			orderInfo.setPayOrder(payOrder);
			orderInfo.setOrderStatus("0"); // 未付款状态
			orderInfo.setStatus("1");
			orderInfoDao.save(orderInfo);
			// 删除购物车中的物品
			// Map<String, Object> attrs = new HashMap<String, Object>();
			// attrs.put("member", orderInfo.getMember());
			// attrs.put("product", orderInfo.getProduct());
			// attrs.put("productAttrIds", orderInfo.getProductAttrIds());
			// this.orderInfoDao.delete(CartInfo.class, attrs);
			totoalPrice = totoalPrice + orderInfo.getTotalPrice();
		}
		// 删除购物车中的物品
		for (int i = 0; i < cartIdList.size(); i++) {
			this.cartInfoDao.delete(Long.parseLong(cartIdList.get(i)));
		}
		totoalPrice = totoalPrice + payOrder.getShippingFee();
		// 计算应付金额
		// Double orderAmount =
		// totoalPrice-payOrder.getIntegralMoney()-payOrder.getBonus()-payOrder.getCouponAmount();
		payOrder.setProductAmount(totoalPrice);
		payOrder.setOrderAmount(totoalPrice);
		// payOrder.setOrderAmount(orderAmount);
		payOrderDao.update(payOrder);

		// 此处还需要添加修改用户的红包数量金额和卡券等
		return payOrder;
	}

	/**
	 * 保存订单和清单
	 */
	public PayOrder addPayOrder(PayOrder payOrder, List<OrderInfo> orderInfoList) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String payOrderNum = sdf.format(new Date()) + String.format("%1$04d", 1);
		payOrder.setOrderNum(payOrderNum);
		payOrder.setCreateTime(new Date());
		// payOrder.setBestTime(new Date()); //送货最佳时间
		payOrder.setShippingStatus("0"); // 未发货状态
		payOrder.setPayStatus("0");
		payOrder.setStatus("1");
		payOrderDao.save(payOrder);
		double totoalPrice = 0;
		for (OrderInfo orderInfo : orderInfoList) {
			orderInfo.setMember(payOrder.getMember());
			orderInfo.setCreateTime(new Date());
			orderInfo.setOrderTime(new Date());
			orderInfo.setPayOrder(payOrder);
			orderInfo.setOrderStatus("0"); // 未付款状态
			orderInfo.setStatus("1");
			orderInfoDao.save(orderInfo);
			// 删除购物车中的物品
			Map<String, Object> attrs = new HashMap<String, Object>();
			attrs.put("member", orderInfo.getMember());
			attrs.put("product", orderInfo.getProduct());
			attrs.put("productAttrIds", orderInfo.getProductAttrIds());
			this.orderInfoDao.delete(CartInfo.class, attrs);
			totoalPrice = totoalPrice + orderInfo.getTotalPrice();
		}
		totoalPrice = totoalPrice + payOrder.getShippingFee();
		// 计算应付金额
		// Double orderAmount =
		// totoalPrice-payOrder.getIntegralMoney()-payOrder.getBonus()-payOrder.getCouponAmount();
		payOrder.setProductAmount(totoalPrice);
		payOrder.setOrderAmount(totoalPrice);
		// payOrder.setOrderAmount(orderAmount);
		payOrderDao.update(payOrder);

		// 此处还需要添加修改用户的红包数量金额和卡券等
		return payOrder;
	}

	/**
	 * 查询所有订单列表（过滤掉删除态）
	 */
	@SuppressWarnings("unchecked")
	public Pager retrieveAllPayOrders(int pageStart, int pageSize) {

		Map<String, Object> attrs = new HashMap<String, Object>();
		attrs.put("status", "1");
		int count = (int) payOrderDao.queryTotalRecord(PayOrder.class, attrs);
		
		List<PayOrder> payOrderList = payOrderDao.queryList(PayOrder.class, pageStart, pageSize, "id", "desc", attrs);
		return new Pager(pageSize, pageStart, count, payOrderList);
		}

	/**
	 * 根据条件模糊查询订单数据(查询所有订单列表（过滤掉删除态）)
	 */
	public Pager queryByAttrs(PayOrder payOrder, int pageStart, int pageSize, String flag) {
		if (null == payOrder) {
			if (flag.equals("day")) {
				return payOrderDao.queryByDay(pageStart, pageSize, "0");
			} else if (flag.equals("week")) {
				return queryByWeekAndMonth(pageStart, pageSize, flag, "0");
			} else if (flag.equals("month")) {
				return queryByWeekAndMonth(pageStart, pageSize, flag, "0");
			}
			return retrieveAllPayOrders(pageStart, pageSize);
		} else {
			if (payOrder.getOrderNum().equals("") && payOrder.getName().equals("") && payOrder.getShippingStatus().equals("")) {
				return retrieveAllPayOrders(pageStart, pageSize);
			} else {
				return payOrderDao.queryNameAndOrderByLike("1", pageStart, pageSize, payOrder, 0);
			}
		}

	}

	/**
	 * 根据条件模糊查询订单数据(查询商家已经发货到签收的所有订单)
	 */
	public Pager queryByAttrs1(PayOrder payOrder, int pageStart, int pageSize, String flag) {
		if (payOrder == null) {
			if (flag.equals("day")) {
				return payOrderDao.queryByDay(pageStart, pageSize, "1");
			} else if (flag.equals("week")) {
				return queryByWeekAndMonth(pageStart, pageSize, flag, "1");
			} else if (flag.equals("month")) {
				return queryByWeekAndMonth(pageStart, pageSize, flag, "1");
			}
			return queryAllSendToReceiveOrder(pageStart, pageSize);
		} else {
			if (payOrder.getOrderNum().equals("") && payOrder.getName().equals("") && payOrder.getShippingStatus().equals("")) {
				return queryAllSendToReceiveOrder(pageStart, pageSize);
			} else {
				return payOrderDao.queryNameAndOrderByLike("1", pageStart, pageSize, payOrder, 1);
			}
		}
	}

	/**
	 * 根据条件模糊查询订单数据(订单回收站)
	 */
	public Pager queryByAttrsCycle(PayOrder payOrder, int pageStart, int pageSize) {
		if (payOrder == null) {
			return queryAllCycleOrder(pageStart, pageSize);
		} else {
			if (!ObjValid.isValid(payOrder.getOrderNum()) && !ObjValid.isValid(payOrder.getName()) && !ObjValid.isValid(payOrder.getShippingStatus())) {
				return queryAllCycleOrder(pageStart, pageSize);
			} else {
				return payOrderDao.queryNameAndOrderByLike("0", pageStart, pageSize, payOrder, 0);
			}
		}
	}

	/**
	 * 查询出单个订单和其清单的详细内容
	 */
	public List<OrderInfo> queryPayAndOrder(PayOrder payOrder) {
		return payOrderDao.queryPayAndOrder(payOrder);
	}

	/**
	 * 查询出单个清单的详细内容
	 */
	@SuppressWarnings("unchecked")
	public List<OrderInfo> queryOneOrderInfo(String orderInfoNum) {
		Map<String, Object> attrs = new HashMap<String, Object>();
		attrs.put("status", "1");
		attrs.put("orderinfoNum", orderInfoNum);
		return payOrderDao.queryList(OrderInfo.class, "id", "desc", attrs);
	}

	/**
	 * 已成功收货列表
	 */
	@SuppressWarnings("unchecked")
	public Pager querySendProduct(int pageStart, int pageSize) {
		Map<String, Object> attrs = new HashMap<String, Object>();
		attrs.put("shippingStatus", "2");
		attrs.put("status", "1");
		List<PayOrder> list = payOrderDao.queryList(PayOrder.class, "id", "desc", attrs);
		return new Pager(pageSize, pageStart, list.size(), list);
	}

	/**
	 * 查询商家已经发货到签收的所有订单
	 */
	public Pager queryAllSendToReceiveOrder(int pageStart, int pageSize) {
		return payOrderDao.queryAllSendToReceiveOrder(pageStart, pageSize);
	}

	/**
	 * 查询所有已经移除了的订单（订单回收站）
	 */
	@SuppressWarnings("unchecked")
	public Pager queryAllCycleOrder(int pageStart, int pageSize) {
		/*Map<String, Object> attrs = new HashMap<String, Object>();
		attrs.put("status", "0");
		List list = payOrderDao.queryList(PayOrder.class, (pageStart - 1) * pageSize, pageSize, "id", "desc", attrs);
		return new Pager(pageSize, pageStart, (int) payOrderDao.queryTotalRecord(PayOrder.class, attrs),
			list);*/
		return payOrderDao.queryAllCycleOrder(pageStart,pageSize);
	}

	/**
	 * 商家一周内、一个月内的订单
	 */
	public Pager queryByWeekAndMonth(int pageStart, int pageSize, String flag, String judge) {
		CalendarHelper calendarHelper = new CalendarHelper();
		List<String> list = calendarHelper.calendDate(flag);
		return payOrderDao.queryByWeek(list.get(0), list.get(1), pageStart, pageSize, judge);
	}

	/**
	 * 确认付款
	 */
	public String editPayType(PayOrder payOrder) {
		Map<String, Object> props = new HashMap<String, Object>();
		Map<String, Object> attrs = new HashMap<String, Object>();
		Map<String, Object> props1 = new HashMap<String, Object>();
		Map<String, Object> attrs1 = new HashMap<String, Object>();
		props.put("payTime", new Date());
		props.put("shippingStatus", "3"); // 修改为备货中
		props.put("payStatus", "1");

		props1.put("payTime", new Date());
		props1.put("orderStatus", "1");
		// props1.put("comments", payOrder.getComments());

		attrs.put("orderNum", payOrder.getOrderNum());
		payOrder = payOrderDao.query(PayOrder.class, attrs);
		attrs1.put("payOrder", payOrder);
		attrs.put("status", "1");
		attrs1.put("status", "1");

		payOrderDao.update(PayOrder.class, props, attrs);
		payOrderDao.update(OrderInfo.class, props1, attrs1);
		return "付款成功";

	}

	/**
	 * 商家发货
	 */
	public String editSendShippingType(PayOrder payOrder) {
		Map<String, Object> props = new HashMap<String, Object>();
		Map<String, Object> attrs = new HashMap<String, Object>();
		props.put("shippingTime", new Date());
		props.put("shippingStatus", "1"); // 修改为发货状态
		props.put("invoiceNum", payOrder.getInvoiceNum());
		props.put("shippingName", payOrder.getShippingName());
		attrs.put("orderNum", payOrder.getOrderNum());
		attrs.put("status", "1");
		payOrderDao.update(PayOrder.class, props, attrs);
		payOrder = queryByOrderNum(payOrder.getOrderNum());
		try {
			TemplateMessageResult templateMessageResult = MessageManager.sendExpressTemplateMessage(payOrder);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "发货成功";
	}

	/**
	 * 微信模板消息
	 */
	public void weixinMessage(PayOrder payOrder) throws Exception {
		PayOrder p = this.queryByOrderNum(payOrder.getOrderNum());
		Member member = p.getMember();
		LinkedHashMap<String, TemplateMessageItem> templateMessageItem = new LinkedHashMap<String, TemplateMessageItem>();
		TemplateMessageItem templateMessageItem1 = new TemplateMessageItem("尊敬的客户您好，您的订单" + p.getOrderNum() + "已发货", "#000000");
		TemplateMessageItem templateMessageItem2 = new TemplateMessageItem(member.getNickname(), "#000000");
		TemplateMessageItem templateMessageItem3 = new TemplateMessageItem(
			DateHelper.dataToString(p.getShippingTime(), "yyyy-MM-dd HH:mm:ss"), "#000000");
		TemplateMessageItem templateMessageItem4 = new TemplateMessageItem(
			"物流公司:" + p.getShippingName() + ",运单号为" + p.getInvoiceNum() + "。感谢您的支持，欢迎再次光临~", "#000000");
		templateMessageItem.put("first", templateMessageItem1);
		templateMessageItem.put("keyword1", templateMessageItem2);
		templateMessageItem.put("keyword2", templateMessageItem3);
		templateMessageItem.put("remark", templateMessageItem4);

		TemplateMessage templateMessage = new TemplateMessage();
		// templateMessage.setTemplate_id(Constants.TEMPLATE_MESSAGE_SUBMIT);
		templateMessage.setTopcolor("#000000");
		templateMessage.setTouser(member.getLoginName());
		// templateMessage.setTouser("orxiEtyV5w-K2G7SIYu9cejrCMbU");
		log.info("member.getLoginName()：" + member.getLoginName());
		templateMessage.setUrl("");
		templateMessage.setData(templateMessageItem);
		TemplateMessageResult templateMessageResult = MessageAPI.messageTemplateSend(TokenManager.getToken(Constants.APPID),
			templateMessage);
		log.info("ReceiveServlet:" + templateMessageResult.getErrcode() + " --" + templateMessageResult.getErrmsg());
	}

	/**
	 * 用户确认收货(修改收货状态)
	 */
	public String editReceiveShippingType(PayOrder payOrder) {
		Map<String, Object> props = new HashMap<String, Object>();
		Map<String, Object> attrs = new HashMap<String, Object>();
		props.put("shippingStatus", "2"); // 修改为收货状态
		props.put("shippingName", payOrder.getShippingName());
		props.put("bestTime",new Date());
		attrs.put("orderNum", payOrder.getOrderNum());
		attrs.put("status", "1");
		payOrderDao.update(PayOrder.class, props, attrs);
		return "确认收货成功";
	}

	/**
	 * 用户的待付款订单
	 */
	public List<OrderInfo> noPayOrderByUser(String loginName) {

		return payOrderDao.PayOrderByUser(loginName);
	}

	/**
	 * 用户的待收货订单和清单或用户已购买的所有订单
	 */
	public List<OrderInfo> noReceiveOrderByUser(String loginName, String shippingStatus) {
		return payOrderDao.noReceiveOrderByUser(loginName, shippingStatus);
	}

	/**
	 * 用户的所有已购买订单
	 */
	public List<PayOrder> queryAllPayOrderByUser(Member member) {
		Map<String, Object> attrs = new HashMap<String, Object>();
		// 用户下单到收货的全部订单数量
		attrs.put("member", member);
		attrs.put("status", "1");
		return payOrderDao.queryList(PayOrder.class, "id", "desc", attrs);
	}

	/**
	 * 用户订单数量
	 */
	@SuppressWarnings("unchecked")
	public Long TotalOrder(Member member, PayOrder payOrder) {
		Map<String, Object> attrs = new HashMap<String, Object>();
		// 用户下单到收货的全部订单数量
		attrs.put("member", member);
		attrs.put("status", "1");
		if (ObjValid.isValid(payOrder)) {
			if (ObjValid.isValid(payOrder.getPayStatus())) {
				// 查询未付款的
				attrs.put("payStatus", payOrder.getPayStatus());
			}
			if (ObjValid.isValid(payOrder.getShippingStatus())) {
				payOrder.setShippingStatus(null);
				return payOrderDao.queryTotalRecord(PayOrder.class, attrs);
			}
		}
		// 查询全部订单
		return payOrderDao.queryTotalRecord(PayOrder.class, attrs);
	}

	/**
	 * 用户订单(只查订单不查清单)
	 * 
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public Pager PayOrerByUser(PayOrder payOrder, int pageStart, int pageSize) throws Exception {
		Map<String, Object> attrs = new HashMap<String, Object>();
		attrs.put("member", payOrder.getMember());
		attrs.put("status", "1");
		if (ObjValid.isValid(payOrder.getPayStatus())) {
			attrs.put("payStatus", payOrder.getPayStatus());
		}
		if (ObjValid.isValid(payOrder.getShippingStatus())) {
			attrs.put("shippingStatus", payOrder.getShippingStatus());
		}
		return new Pager(pageSize, pageStart, Integer.parseInt(String.valueOf(payOrderDao.queryTotalRecord(PayOrder.class, attrs))),
			payOrderDao.queryList(PayOrder.class, pageStart, pageSize, "id", "desc", attrs));

	}

	/**
	 * 用户分销的全部清单
	 */
	public Pager shareOrderInfoByFather(Member member, int pageStart, int pageSize) {
		return payOrderDao.shareOrderInfoByFather(member, pageStart, pageSize);
	}

	/**
	 * 用户分销的全部订单
	 */
	public Pager sharePayOrderByFather(List<Double> rateList, Member member, int pageStart, int pageSize) {
		return payOrderDao.sharePayOrderByFather(rateList, member, pageStart, pageSize);
	}

	/**
	 * 查询用户的三级客户订单  -->二级
	 * 
	 * @param member
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map<String, List<PayOrder>> queryMemberThreeCosTomer(Member member) {
		Map<String, List<PayOrder>> payOrderMap = new HashMap<String, List<PayOrder>>();
		Map<String, Object> attrs = new HashMap<String, Object>();
		attrs.put("memberFather", member.getId());
		attrs.put("status", "1");
		List<Member> members1 = memberDao.queryList(Member.class, "id", "desc", attrs); // 第一级客户
		int count = 1;
		if (members1.size() > 0) {
			for (Member memberPayOrder1 : members1) {
				payOrderMap.put("payOrders1" + (++count) + "", queryAllPayOrderByUser(memberPayOrder1));
			}
			for (Member member1 : members1) {
				// 先根据第一级客户查询第二级客户
				Map<String, Object> attrs1 = new HashMap<String, Object>();
				attrs1.put("memberFather", member1.getId());
				attrs1.put("status", "1");
				List<Member> members2 = memberDao.queryList(Member.class, "id", "desc", attrs1);
				if (members2.size() > 0) {
					for (Member memberPayOrder2 : members2) {
						payOrderMap.put("payOrders1" + (++count) + "", queryAllPayOrderByUser(memberPayOrder2));
					}
					/*for (Member member2 : memberDao.queryList(Member.class, "id", "desc", attrs1)) {
						// 根据第二级客户查询第三级
						Map<String, Object> attrs2 = new HashMap<String, Object>();
						attrs2.put("memberFather", member2.getId());
						attrs2.put("status", "1");
						if (memberDao.queryList(Member.class, "id", "desc", attrs2).size() > 0) {
							for (Member memberPayOrder3 : memberDao.queryList(Member.class, "id", "desc", attrs2)) {
								payOrderMap.put("payOrders1" + (++count) + "", queryAllPayOrderByUser(memberPayOrder3));
							}
						}
					}*/
				}
			}
		}
		return payOrderMap;
	}

	/**
	 * 用户批量确认收货（此功能暂时不用）
	 */
	public void moreReceiveOrderByUser(String orderNum) {
		payOrderDao.moreReceiveOrderByUser(orderNum);
	}

	/**
	 * 查询清单商品的属性值
	 */
	public List<ProductAttr> queryProductAttr(List<OrderInfo> orderInfos) {
		List<ProductAttr> productAttrs = new ArrayList<ProductAttr>();
		Map<String, Object> attrs = new HashMap<String, Object>();
//		attrs.put("status", "1");
		for (OrderInfo orderInfo : orderInfos) {
			String[] str = orderInfo.getProductAttrIds().split(",");
			for (int i = 0; i < str.length; i++) {
				attrs.put("id", Long.valueOf(str[i]));
				productAttrs.add(payOrderDao.query(ProductAttr.class, attrs));
			}
		}
		for (int i = 0; i < productAttrs.size(); i++){//外循环是循环的次数
            for (int j = productAttrs.size() - 1 ; j > i; j--){//内循环是 外循环一次比较的次数
                if (productAttrs.get(i) == productAttrs.get(j)){
                    productAttrs.remove(j);
                }
            }
        }
		return productAttrs;
	}

	public Pager retrievePayOrdersPager(int pageSize, int pageNo) {
		return this.payOrderDao.getPager(pageSize, pageNo);
	}

	public PayOrder queryByOrderNum(String payOrderNum) {
		Map<String, Object> attrs = new HashMap<String, Object>();
		attrs.put("orderNum", payOrderNum);
		return this.payOrderDao.query(PayOrder.class, attrs);
	}

	@SuppressWarnings("unchecked")
	public void finishTrade(String payOrderNum) {
		log.error("PayOrderManagerDaoImpl----finishTrade-----payOrderNum:" + payOrderNum);
		Long integral = 0L;
		Map<String, Object> attrs = new HashMap<String, Object>();
		attrs.put("orderNum", payOrderNum);
		PayOrder payOrder = this.payOrderDao.query(PayOrder.class, attrs);
		//开放后门去修改订单状态，此判断避免重复的修改
		if(payOrder.getPayStatus().equals("1")){
			log.error("此订单已经为已付款状态");
			return;
		}
		// 通过订单查找清单 并把清单改为已付
		Map<String, Object> attrsMap = new HashMap<String, Object>();
		attrsMap.put("payOrder", payOrder);
		List<OrderInfo> orderInfoList = this.orderInfoDao.queryList(OrderInfo.class, "id", "desc", attrsMap);
		StringBuilder goodsInfo = new StringBuilder(); // 商品信息字符串存储
		if (ObjValid.isValid(orderInfoList)) {
			for (OrderInfo orderInfo : orderInfoList) {
				//integral = integral + orderInfo.getProduct().getGiveIntegral();
				orderInfo.setPayTime(new Date());
				orderInfo.setOrderStatus("1");
				this.orderInfoDao.update(orderInfo);
				log.error("PayOrderManagerDaoImpl----finishTrade-----完成对清单的修改，清单ID：" + orderInfo.getId());
				// 修改库存
				Stock condition = new Stock();
				condition.setProduct(orderInfo.getProduct());
				condition.setProductAttrIds(orderInfo.getProductAttrIds());
				Stock stock = this.stockDao.findByProAttr(condition);
				stock.setAmount(stock.getAmount() - orderInfo.getAmount());
				this.stockDao.update(stock);
				McProductInfo mcProductInfo =orderInfo.getProduct();
				mcProductInfo.setRepertory(mcProductInfo.getRepertory()-orderInfo.getAmount());
				this.mcProductInfoDao.update(mcProductInfo);
				log.error("PayOrderManagerDaoImpl----finishTrade-----完成对库存的修改");
				// 拼接字符串，商品信息，如：机油001x1,机油002x1等
				goodsInfo.append(orderInfo.getProduct().getTitle() + "x" + orderInfo.getAmount() + ",");
			}
			goodsInfo.deleteCharAt(goodsInfo.length() - 1);
		}
		// 把订单改为已付
		payOrder.setPayStatus("1");
		payOrder.setPayTime(new Date());
		this.payOrderDao.update(payOrder);
		log.error("PayOrderManagerDaoImpl----finishTrade-----完成对订单的修改");
		// 购买完成后推送信息
		// TODO：推送给买的人；
		Member member = payOrder.getMember();

		// 查询佣金表是否已存在记录，（防止网络原因，造成微信多次回调导致产生多次佣金-----一个订单只对应一个回调）
		if (!this.commissionInfoDao.findByPayOrder(payOrder)) {
			// 购买完成后推送信息
			// TODO：推送给买的人；
			try {
				TemplateMessageResult templateMessageResult = MessageManager.sendNewOrderTemplateMessage(null, "", goodsInfo.toString(),
					payOrder.getProductAmount() + "", "1".equals(payOrder.getPayStatus()) ? "已付款" : "未付款", null, member.getLoginName(),
						payOrder);
				log.error("payNotifyAction1:" + templateMessageResult.getErrcode() + " --" + templateMessageResult.getErrmsg());
			} catch (Exception e) {
				log.error("PayOrderManagerDaoImpl----finishTrade-----消息推送异常"+e);
			}
			log.error("PayOrderManagerDaoImpl----finishTrade-----完成消息推送");
			// 计算佣金 - -
			this.calculateCommission(payOrder);
			log.error("PayOrderManagerDaoImpl----finishTrade-----完成对分销商的佣金分配");
			// 计算代理商提成
			if (ObjValid.isValid(payOrder.getMember().getFirstFather())
				&& ObjValid.isValid(payOrder.getMember().getFirstFather().getAgent())) {
				this.calculateBonus(payOrder);
				log.error("PayOrderManagerDaoImpl----finishTrade-----完成对代理商的佣金分配");
			}
		}
	}

	public void calculateBonus(PayOrder payOrder) {
		List<ParameterSet> sl = this.parameterSetDao.findAll();
		ParameterSet ps = sl.get(0);
		String bonusRate = ps.getAgentBonusSet();
		Double rateDouble = Double.valueOf(bonusRate);
		Double bonus = payOrder.getProductAmount() * rateDouble * 0.01;
		Member member = payOrder.getMember().getFirstFather();
		member.setCommission(member.getCommission() + bonus);
		member.setAgentBonus(member.getAgentBonus() + bonus);
		member.setEditTime(new Date());
		this.memberDao.update(member);
		CommissionInfo commissionInfo = new CommissionInfo();
		commissionInfo.setCommission(bonus);
		commissionInfo.setAddTime(new Date());
		commissionInfo.setCommissionComments(String.valueOf(payOrder.getProductAmount()));
		commissionInfo.setCommissionStatus("3");
		commissionInfo.setMemberId(member);
		commissionInfo.setFromMemberId(payOrder.getMember());
		commissionInfo.setStatus("1");
		commissionInfo.setPayOrder(payOrder);
		this.commissionInfoDao.save(commissionInfo);
	}

	/**
	 * 记录赠送积分
	 * 
	 * @param member
	 * @param integral
	 */
	public void logIntergral(Member member, Long integral) {
		IntergralInfo intergralInfo = new IntergralInfo();
		intergralInfo.setAddTime(new Date());
		intergralInfo.setComments("购买赠送");
		intergralInfo.setIntergral(integral);
		intergralInfo.setMemberId(member);
		intergralInfo.setStatus("1");
		this.intergralInfoDao.save(intergralInfo);
	}

	public void updateBonus(PayOrder payOrder) {
		List<String> bonusList = FormatStringUtil.splitBySign(payOrder.getBonusId(), ",");
		for (int i = 0; i < bonusList.size(); i++) {
			this.memberBonusDao.changeBonusUnable(Long.parseLong(bonusList.get(i)));
		}
	}

	/**
	 * 计算佣金
	 * 
	 * @param payOrder
	 * @param member
	 */
	@SuppressWarnings("unchecked")
	public void calculateCommission(PayOrder payOrder) {
		List<ParameterSet> parameterSetList = this.parameterSetDao.findAll();
		ParameterSet parameterSet = new ParameterSet();
		if (ObjValid.isValid(parameterSetList)) {
			parameterSet = parameterSetList.get(0);
		}
		List<String> rateList = FormatStringUtil.splitBySign(parameterSet.getSaleRoyaltyRate(), ",");
		int times = 0;
		if (ObjValid.isValid(payOrder.getMember().getMemberFather())) {
														//上级状态为"0"时,不发放佣金
			if (payOrder.getMember().getMemberFather().getStatus().equals("0")) {
				
						times = 1;
						if (ObjValid.isValid(payOrder.getMember().getFirstFather())) {
							//上上级状态为"1"时,发放佣金
							if (payOrder.getMember().getFirstFather().getStatus().equals("1")) {
								this.updateCommission(times, rateList, payOrder.getMember().getFirstFather(), payOrder.getProductAmount(),
										payOrder.getMember(), payOrder);
							}
						}
						
			}else {
				//上级状态为"1"时,发放佣金
				this.updateCommission(times, rateList, payOrder.getMember().getMemberFather(), payOrder.getProductAmount(),
						payOrder.getMember(), payOrder);
			}
		}
	}

	/**
	 * 递归循环
	 * 
	 * @param times
	 * @param rateList
	 * @param member
	 * @param totalPrice
	 * @param payOrder
	 */
	public void updateCommission(int times, List<String> rateList, Member member, Double totalPrice, Member fromMember,
		PayOrder payOrder) {
		if (ObjValid.isValid(member)) {
			if (times < rateList.size()) {
				Double rate = Double.parseDouble(rateList.get(times));
				times++;
				// 如果会员为分销商身份则计算佣金，否则不计算佣金
				// if (member.getDistributor().equals(Constants.DISTRIBUTOR)) {
				Double commission = totalPrice * rate / 100;
				CommissionInfo commissionInfo = new CommissionInfo();
				commissionInfo.setAddTime(new Date());
				commissionInfo.setCommission(commission);
				commissionInfo.setMemberId(member);
				commissionInfo.setCommissionComments(String.valueOf(payOrder.getOrderAmount()));
				commissionInfo.setCommissionStatus("0");
				commissionInfo.setStatus("1");
				commissionInfo.setFromMemberId(fromMember);
				commissionInfo.setPayOrder(payOrder);
				this.commissionInfoDao.save(commissionInfo);
				member.setCommission(member.getCommission() + commission);
				member.setCommissionLine(member.getCommissionLine() + payOrder.getProductAmount()); //　<--＋总金额?  ?---＞member.getCommissionLine()＋commission佣金
				member.setEditTime(new Date());
				// member.setCommission(member.getCommission()+commission);
				this.memberDao.update(member);

				String userLevel = "";
				if (times == 1) {
					userLevel = "一级";
				}
				if (times == 2) {
					userLevel = "二级";
				}
				/*if (times == 3) {
					userLevel = "三级";
				}*/
				Iterator<OrderInfo> iterator = payOrder.getOrderInfos().iterator();
				StringBuilder goodsInfo = new StringBuilder();
				while (iterator.hasNext()) {
					OrderInfo orderInfo = (OrderInfo) iterator.next();
					goodsInfo.append(orderInfo.getProduct().getTitle() + "x" + orderInfo.getAmount() + ",");
				}
				goodsInfo.deleteCharAt(goodsInfo.length() - 1);
				TemplateMessageResult templateMessageResult = MessageManager.sendNewOrderTemplateMessage(userLevel,
					fromMember.getNickname(), goodsInfo.toString(), payOrder.getProductAmount() + "",
					"1".equals(payOrder.getPayStatus()) ? "已付款" : "未付款", "获得红包:￥" + commission, member.getLoginName(), payOrder);
				// }
				Member fatherMember = member.getMemberFather();
				//状态正常才发放佣金
				if (ObjValid.isValid(fatherMember) && fatherMember.getStatus().equals("1")) {
					this.updateCommission(times, rateList, fatherMember, totalPrice, fromMember, payOrder);
				}
				
			}
		}
	}

	/**
	 * 递归循环
	 * 
	 * @param times
	 * @param rateList
	 * @param member
	 * @param totalPrice
	 */
	/*
	 * public void updateCommission2(int times, List<String> rateList, Member
	 * member, Double totalPrice){ Double rate =
	 * Double.parseDouble(rateList.get(times)); Double commission = totalPrice *
	 * rate/100; CommissionInfo commissionInfo = new CommissionInfo();
	 * commissionInfo.setAddTime(new Date());
	 * commissionInfo.setCommission(commission);
	 * commissionInfo.setMemberId(member);
	 * commissionInfo.setCommissionComments("分销订单的红包");
	 * commissionInfo.setCommissionStatus("0"); commissionInfo.setStatus("1");
	 * this.commissionInfoDao.save(commissionInfo);
	 * member.setCommission(member.getCommission()+commission);
	 * this.memberDao.update(member); times++; if (times < rateList.size()) {
	 * //Member fatherMember = this.memberDao.get(member.getMemberFather());
	 * Member fatherMember = member.getMemberFather();
	 * this.updateCommission(times, rateList, fatherMember, totalPrice); } }
	 */

	/**
	 * 计算代理商一定时间内的业绩
	 */

	@Override
	public Double findAgentWork(Member member, Date begin, Date end, int pageSize, int pageNo) {
		// 查询所有订单
		Double money = 0.0;
		Pager pager = this.payOrderDao.findByAgent(member, begin, end, pageSize, pageNo);
		List<PayOrder> pl = (List<PayOrder>) pager.getResultList();
		// 代理商的业绩从第四级开始算起，需要减去前三级的订单
		/*
		 * List<Double> rateList = new ArrayList<Double>(); rateList.add(0.1);
		 * rateList.add(0.2); rateList.add(0.3); Pager pager2 =
		 * this.payOrderDao.sharePayOrderByFather(rateList, member, -1, -1);
		 * List<PayOrderVo> list = pager2.getResultList(); List<PayOrder> pl2 =
		 * new ArrayList<PayOrder>(); pl2 = pl; for(int i =
		 * pl2.size()-1;i>=0;i--){ for(int y=0;y<list.size();y++){
		 * if(pl2.get(i).getId().equals(list.get(y).getId())){ pl.remove(i);
		 * break; } } }
		 */
		// List<PayOrder> p2 = new ArrayList<PayOrder>();
		/*
		 * Map<Long, PayOrder> map1 = new HashMap<Long, PayOrder>(); for(int
		 * i=0;i<pl.size();i++){ for(int x=0;x<list.size();x++){
		 * if(!pl.get(i).getId().equals(list.get(x).getId())){
		 * if(map1.size()==0){ map1.put(pl.get(i).getId(), pl.get(i)); money =
		 * money +pl.get(i).getOrderAmount(); }else if(map1.size()!=0 &&
		 * !map1.containsKey(pl.get(i).getId())){ map1.put(pl.get(i).getId(),
		 * pl.get(i)); money = money +pl.get(i).getOrderAmount(); } //break; } }
		 * }
		 */
		// 计算从第四级开始的业绩
		for (int i = 0; i < pl.size(); i++) {
			money = money + pl.get(i).getOrderAmount();
		}
		log.info("");
		return money;
	}

	public Pager findMyAgentOrder(Member member, Date begin, Date end, int pageSize, int pageNo) {
		Pager pager = this.payOrderDao.findByAgent(member, begin, end, pageSize, pageNo);
		return pager;
	}

	@Override
	public Double findAgentBonus(Member member) {
		Date begin = this.bonusRecordDao.sendTime(member);
		Date end = new Date();
		int pageSize = -1;
		int pageNo = -1;
		Double bonus = this.findAgentWork(member, begin, end, pageSize, pageNo);
		List<ParameterSet> sl = this.parameterSetDao.findAll();
		ParameterSet ps = sl.get(0);
		String bonusRate = ps.getAgentBonusSet();
		List<String> bonusRateList = FormatStringUtil.splitBySign(bonusRate, ",");
		Double money = 0.0;
		for (int i = 0; i < bonusRateList.size(); i++) {
			String rate = bonusRateList.get(i);
			String rateIndexStr = rate.split("#")[0];
			Double rateIndex = Double.valueOf(rateIndexStr);
			if (bonus <= rateIndex) {
				String rateSecondStr = rate.split("#")[1];

				Double rateSecond = Double.valueOf(rateSecondStr);
				// 计算提成
				money = bonus * (rateSecond * 0.01);
				break;
			}
		}
		return money;
	}

	public int countMyTeamByAgent(Member member) {
		return this.payOrderDao.countMyTeamByAgent(member);
	}

	@Override
	public List<PayOrder> exportOrder() {
		Date today = new Date();// 今天
		Date yestoday = DateHelper.getDateByCalculateDays(today, -2); // 2天前
		String yestodayStr = DateHelper.dataToString(yestoday, "yyyy-MM-dd 00:00:00");
		String todayStr = DateHelper.dataToString(today, "yyyy-MM-dd 00:00:00");
		return this.payOrderDao.getTwoDayOrder(todayStr, yestodayStr);
	}

	@Override
	public Pager retrievePayOrdersPager(int pageSize, int pageNo, final PayOrder payOrder, String orderAttr, String orderType,
		String status, String startTime, String endTime) {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map = ObjValid.objectToMap(map, payOrder, false, null);
			Map<String, Object> likeMap = new HashMap<String, Object>();
			likeMap.put("orderNum", true);
			likeMap.put("name", true);
			
			if (ObjValid.isValid(status)) {
				map.put("status", status);
			}
			List<T> list = null;

			long rowCount = 0;
			StringBuffer hql = HQLUtil.createQueryWhereHQL(map, likeMap);

			if (ObjValid.isValid(hql, startTime)) {
				hql.append(" and o.createTime >= '" + startTime + "' ");
			}

			if (ObjValid.isValid(hql, endTime)) {
				hql.append(" and o.createTime < '" + endTime + "' ");
			}
			hql.append(" order by o.").append(orderAttr).append(" ").append(orderType);
			list = baseDao.retrieveObjectsPager(pageSize, pageNo, " from PayOrder o " + hql.toString());
			rowCount = baseDao.queryTotalRecord("select count(*) from PayOrder o " + hql.toString());

			return new Pager(pageSize, pageNo, rowCount, list);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public ParameterSet findParameterSet() {
		List<ParameterSet> parameterSetList = this.parameterSetDao.findAll();
		if (ObjValid.isValid(parameterSetList)) {
			return parameterSetList.get(0);
		}
		return null;
	}

	@Override
	public List<PayOrder> findPayOrder() {
		return this.payOrderDao.findPayOrder();
	}

	@Override
	public void updatePayOrder(PayOrder payOrder) {
		this.payOrderDao.update(payOrder);
	}

	@Override
	public AgentOrder queryAgentByOrderNum(String payOrderNum) {
		return this.payOrderDao.queryAgentByOrderNum(payOrderNum);
	}

	@Override
	public List<String> findAllAgentOrder() {
		
		return this.payOrderDao.findAllAgentOrder();
	}

}