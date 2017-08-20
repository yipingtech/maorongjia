package cc.messcat.web.front;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionContext;

import cc.messcat.entity.Address;
import cc.messcat.entity.AgentOrder;
import cc.messcat.entity.Member;
import cc.messcat.entity.OrderInfo;
import cc.messcat.entity.PayOrder;
import cc.modules.util.ObjValid;

/**
 * 	
 *  Class Name: EpPayAction.java
 *  Function:	支付功能，在购物车进行商品结算时会调用Action
 *  Modifications:   
 *  @author Kael  DateTime 2015-05-04 上午11:17:28
 */
@SuppressWarnings("serial")
public class EpPayAction extends FrontAction{
	
	private List<String> choose;
	private Long payOrderId;
	private Long addressId;
	private double totalPrice;
	private String pageType;
	private Long payType;
	private String payStatus;
	private String url;
	
	private static Logger log = LoggerFactory.getLogger(EpPayAction.class); 
	/**
	 * json字符串集合 
	 */
	private String json;
	private String config;
	
	
	public String execute() throws Exception {
		String result=null;
		if ("goPay".equals(this.pageType)) {
			result = this.goPay();
		}
		return result;
	}
	/**
	 * 生成代理商订单
	 * */
	public String payAgent(){
		HttpSession session = getSession();
		Member member = (Member) session.getAttribute("member");
		//生成代理商订单
		AgentOrder agentOrder = new AgentOrder();
		agentOrder = this.memberManagerDao.agentOrder(member);
		//微信统一下单接口拼接参数
		json = unifiedorderResult(getRequest(), agentOrder.getOrderNum(), "授权代理商费用", agentOrder.getAmount()==null?"0.0" :agentOrder.getAmount().toString());
		this.payOrderId = agentOrder.getId();
		return "payAgent";
	}
	
	/**
	 * 生成订单并且跳到支付页面
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String goPay(){
		try {
			Map session = ActionContext.getContext().getSession();
			String addressDetail = "";
			this.payStatus = "success";
			PayOrder payOrder = new PayOrder();
			payOrder.setShippingFee(0D);
			//判断订单是否已存在
			if (!ObjValid.isValid(this.payOrderId)) {
				if (ObjValid.isValid(this.addressId)) {
					Address address = this.addressManagerDao.retrieveAddress(addressId);
					addressDetail = address.getProvince().getProvince()+address.getCity().getCity()+address.getArea().getArea()+address.getAddress();
					payOrder.setAddress(addressDetail);
					payOrder.setMember(address.getMember());
					payOrder.setName(address.getConsignee());
					payOrder.setPhone(address.getCellphone());
					payOrder.setCreateTime(new Date());
					
					String addressId2 =null;
					if(address.getCity().getId()!=null){
						addressId2 = address.getProvince().getId()+","+address.getCity().getId();
					}else{
						addressId2 = String.valueOf(address.getProvince().getId());
					}
					payOrder.setAddressId(addressId2);
					
					//送货方式、运费、配送方式名称
					//支付方式、支付方式名称
					//使用积分金额，优惠券金额、红包金额、总金额、实际需要支付金额
				}
				if (ObjValid.isValid(this.choose)) {
					List<OrderInfo> orderInfos = this.orderInfoManagerDao.createOrderInfos(this.choose);
					if (!ObjValid.isValid(orderInfos.size())) {
						this.payStatus="fail";
					}else{
						payOrder = this.payOrderManagerDao.addPayOrder(payOrder, orderInfos,this.choose);
						//付款消息提醒：
//					if (ObjValid.isValid(payOrder.getMember())) {
//						TemplateMessageResult templateMessageResult = MessageManager.sendNewOrderTemplateMessage(null,"", "百丽春平台", "澜妃健康卫生巾", DateHelper.dataToString(payOrder.getCreateTime(), "yyyy-MM-dd HH:mm:ss"), payOrder.getProductAmount()+"", "1".equals(payOrder.getPayStatus())?"已付款":"未付款", null, payOrder.getMember().getLoginName());
//						LinkedHashMap<String, TemplateMessageItem> templateMessageItem = new LinkedHashMap<String, TemplateMessageItem>();
//						TemplateMessageItem fatherTemplateMessageItem1 = new TemplateMessageItem("下单通知", "#000000");
//	        			TemplateMessageItem fatherTemplateMessageItem2 = new TemplateMessageItem(payOrder.getMember().getNickname(), "#000000");
//	        			TemplateMessageItem fatherTemplateMessageItem3 = new TemplateMessageItem(DateHelper.dataToString(payOrder.getCreateTime(), "yyyy-MM-dd HH:mm:ss"), "#000000");
//	        			TemplateMessageItem fatherTemplateMessageItem4 = new TemplateMessageItem("下单金额(待付款):"+payOrder.getOrderAmount().toString(), "#000000");
//	        			templateMessageItem.put("first", fatherTemplateMessageItem1);
//	        			templateMessageItem.put("keyword1", fatherTemplateMessageItem2);
//	        			templateMessageItem.put("keyword2", fatherTemplateMessageItem3);
//	        			templateMessageItem.put("remark", fatherTemplateMessageItem4);
//	        			
//	        			TemplateMessage templateMessage1 = new TemplateMessage();
//	        			templateMessage1.setTemplate_id(Constants.TEMPLATE_MESSAGE_SUBMIT);
//	        			templateMessage1.setTopcolor("#000000");
//	        			templateMessage1.setTouser(payOrder.getMember().getLoginName());
//	    				log.info("tempmMember.getNickname()::"+payOrder.getMember().getNickname());
//
//	        			templateMessage1.setUrl("");
//	        			templateMessage1.setData(templateMessageItem);
//	        			
//	        			String access_token = TokenManager.getToken(Constants.APPID);
//	        			TemplateMessageResult templateMessageResult2 =  MessageAPI.messageTemplateSend(access_token, templateMessage1);
//					}
						//给上一级发通知
//					if (ObjValid.isValid(payOrder.getMember()) && ObjValid.isValid(payOrder.getMember().getMemberFather())) {
//						TemplateMessageResult templateMessageResult = MessageManager.sendNewOrderTemplateMessage("一级","", "百丽春平台", "澜妃健康卫生巾", DateHelper.dataToString(payOrder.getCreateTime(), "yyyy-MM-dd HH:mm:ss"), payOrder.getProductAmount()+"", "1".equals(payOrder.getPayStatus())?"已付款":"未付款", null, payOrder.getMember().getMemberFather().getLoginName());
//						LinkedHashMap<String, TemplateMessageItem> templateMessageItem = new LinkedHashMap<String, TemplateMessageItem>();
//						TemplateMessageItem fatherTemplateMessageItem1 = new TemplateMessageItem("您的【一级】已下单", "#000000");
//	        			TemplateMessageItem fatherTemplateMessageItem2 = new TemplateMessageItem(payOrder.getMember().getNickname(), "#000000");
//	        			TemplateMessageItem fatherTemplateMessageItem3 = new TemplateMessageItem(DateHelper.dataToString(payOrder.getCreateTime(), "yyyy-MM-dd HH:mm:ss"), "#000000");
//	        			TemplateMessageItem fatherTemplateMessageItem4 = new TemplateMessageItem("下单金额(未付款):"+payOrder.getOrderAmount().toString(), "#000000");
//	        			templateMessageItem.put("first", fatherTemplateMessageItem1);
//	        			templateMessageItem.put("keyword1", fatherTemplateMessageItem2);
//	        			templateMessageItem.put("keyword2", fatherTemplateMessageItem3);
//	        			templateMessageItem.put("remark", fatherTemplateMessageItem4);
//	        			
//	        			TemplateMessage templateMessage1 = new TemplateMessage();
//	        			templateMessage1.setTemplate_id(Constants.TEMPLATE_MESSAGE_SUBMIT);
//	        			templateMessage1.setTopcolor("#000000");
//	        			templateMessage1.setTouser(payOrder.getMember().getMemberFather().getLoginName());
//	    				log.info("tempmMember.getNickname()::"+payOrder.getMember().getMemberFather().getNickname());
//
//	        			templateMessage1.setUrl("");
//	        			templateMessage1.setData(templateMessageItem);
//	        			
//	        			String access_token = TokenManager.getToken(Constants.APPID);
//	        			TemplateMessageResult templateMessageResult2 =  MessageAPI.messageTemplateSend(access_token, templateMessage1);
//					}
					}
				}
			}else {
				payOrder = this.payOrderManagerDao.retrievePayOrderById(payOrderId);
			}
			payOrderId = payOrder.getId();
			//payOrder.getOrderAmount()
			
			session.put("WIDtotal_fee", payOrder.getOrderAmount());
			session.put("WIDsubject", payOrder.getOrderNum());
			if (this.payType==1) {
				json = unifiedorderResult(getRequest(), payOrder.getOrderNum(), "商品信息", payOrder.getOrderAmount()==null?"0.0" :payOrder.getOrderAmount().toString());
				return "goPayByWeChat";
			}
			else if (this.payType==2) {
				return this.payByBalance();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goPay";
	}
	
	@SuppressWarnings("rawtypes")
	public String payByBalance(){
		Map session = ActionContext.getContext().getSession();
		Member member0 = (Member)session.get("member");
		Double totalFee = (Double)session.get("WIDtotal_fee");
		this.memberManagerDao.payByBalance(member0, totalFee);
		payOrderManagerDao.finishTrade(session.get("WIDsubject").toString());
		return "afterPay";
	}


	public Long getAddressId() {
		return addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

	public List<String> getChoose() {
		return choose;
	}

	public void setChoose(List<String> choose) {
		this.choose = choose;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Long getPayOrderId() {
		return payOrderId;
	}

	public void setPayOrderId(Long payOrderId) {
		this.payOrderId = payOrderId;
	}

	public String getPageType() {
		return pageType;
	}

	public void setPageType(String pageType) {
		this.pageType = pageType;
	}

	public Long getPayType() {
		return payType;
	}

	public void setPayType(Long payType) {
		this.payType = payType;
	}

	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}

	public String getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}
	public String getConfig() {
		return config;
	}
	public void setConfig(String config) {
		this.config = config;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

	
}
