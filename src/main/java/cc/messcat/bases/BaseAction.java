package cc.messcat.bases;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionSupport;

import cc.messcat.entity.Member;
import cc.messcat.service.activity.ActivityInfoManagerDao;
import cc.messcat.service.address.AddressManagerDao;
import cc.messcat.service.bankcard.BankCardManagerDao;
import cc.messcat.service.bonus.BonusRecordManagerDao;
import cc.messcat.service.cart.CartInfoManagerDao;
import cc.messcat.service.collection.EpNewsManagerDao;
import cc.messcat.service.collection.McProductInfoManagerDao;
import cc.messcat.service.collection.ProductColumnManagerDao;
import cc.messcat.service.column.EpColumnManagerDao;
import cc.messcat.service.data.DataHandlerManagerDao;
import cc.messcat.service.drawback.ProductDrawbackManagerDao;
import cc.messcat.service.dynamic.EpAdManagerDao;
import cc.messcat.service.dynamic.EpLinksManagerDao;
import cc.messcat.service.finance.BonusManagerDao;
import cc.messcat.service.finance.TicketInfoManagerDao;
import cc.messcat.service.member.CommissionInfoManagerDao;
import cc.messcat.service.member.EvaluateManagerDao;
import cc.messcat.service.member.IntegralRuleManagerDao;
import cc.messcat.service.member.IntergralInfoManagerDao;
import cc.messcat.service.member.MemberBonusManagerDao;
import cc.messcat.service.member.MemberManagerDao;
import cc.messcat.service.member.ParameterSetManagerDao;
import cc.messcat.service.member.RechargeInfoManagerDao;
import cc.messcat.service.member.ReportInfoManagerDao;
import cc.messcat.service.order.OrderInfoManagerDao;
import cc.messcat.service.pay.PayOrderManagerDao;
import cc.messcat.service.product.AttributeManagerDao;
import cc.messcat.service.product.ProductAttrManagerDao;
import cc.messcat.service.product.ProductTypeManagerDao;
import cc.messcat.service.product.StockManagerDao;
import cc.messcat.service.style.WebSkinColorManagerDao;
import cc.messcat.service.style.WebSkinManagerDao;
import cc.messcat.service.system.AlterUrlManagerDao;
import cc.messcat.service.system.AreaManagerDao;
import cc.messcat.service.system.AuthoritiesManagerDao;
import cc.messcat.service.system.CityManagerDao;
import cc.messcat.service.system.McParameterManagerDao;
import cc.messcat.service.system.PageTypeManagerDao;
import cc.messcat.service.system.ProvinceManagerDao;
import cc.messcat.service.system.RolesAuthoritiesManagerDao;
import cc.messcat.service.system.RolesManagerDao;
import cc.messcat.service.system.StandbyManagerDao;
import cc.messcat.service.system.UsersManagerDao;
import cc.messcat.service.system.UsersRolesManagerDao;
import cc.messcat.service.system.WebSiteManagerDao;
import cc.messcat.wechat.weixin.popular.api.PayMchAPI;
import cc.messcat.wechat.weixin.popular.bean.paymch.Unifiedorder;
import cc.messcat.wechat.weixin.popular.bean.paymch.UnifiedorderResult;
import cc.messcat.wechat.weixin.popular.support.TicketManager;
import cc.messcat.wechat.weixin.popular.util.JsUtil;
import cc.messcat.wechat.weixin.popular.util.MapUtil;
import cc.messcat.wechat.weixin.popular.util.PayUtil;
import cc.messcat.wechat.weixin.popular.util.SignatureUtil;
import cc.modules.constants.Constants;
import cc.modules.util.ObjValid;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class BaseAction extends ActionSupport {
	private Logger logger = LoggerFactory.getLogger(BaseAction.class);
	private static final long serialVersionUID = 1L;
	
	private static Logger log = LoggerFactory.getLogger(BaseAction.class); 
	
	protected UsersManagerDao usersManagerDao;
	protected RolesManagerDao rolesManagerDao;
	protected UsersRolesManagerDao usersRolesManagerDao;
	protected AuthoritiesManagerDao authoritiesManagerDao;
	protected RolesAuthoritiesManagerDao rolesAuthoritiesManagerDao;
	protected EpColumnManagerDao epColumnManagerDao;
	protected EpNewsManagerDao epNewsManagerDao;
	protected EpAdManagerDao epAdManagerDao;
	protected EpLinksManagerDao epLinksManagerDao;
	protected WebSiteManagerDao webSiteManagerDao;
	protected WebSkinManagerDao webSkinManagerDao;
	protected WebSkinColorManagerDao webSkinColorManagerDao;
	protected DataHandlerManagerDao dataHandlerManagerDao;
	//备用字段模块
	protected StandbyManagerDao standbyManagerDao;
	//产品模块
	protected McParameterManagerDao mcParameterManagerDao;
	protected McProductInfoManagerDao mcProductInfoManagerDao;
	//页面类型模块
	protected PageTypeManagerDao pageTypeManagerDao;
	//批量修改URL模块
	protected AlterUrlManagerDao alterUrlManagerDao;
	protected BaseManagerAction baseManagerAction;
	
	protected ProductColumnManagerDao productColumnManagerDao;
	
	protected ProductTypeManagerDao productTypeManagerDao;
	
	protected AttributeManagerDao attributeManagerDao;
	
	protected PayOrderManagerDao payOrderManagerDao;
	
	protected CartInfoManagerDao cartInfoManagerDao;
	
	protected OrderInfoManagerDao orderInfoManagerDao;
	
	protected ProductAttrManagerDao productAttrManagerDao;
	
	protected StockManagerDao stockManagerDao;
	
	//地区
	protected AreaManagerDao areaManagerDao;
	
	protected CityManagerDao cityManagerDao;
	
	protected ProvinceManagerDao provinceManagerDao;
	
	protected AddressManagerDao addressManagerDao;
	
	protected ProductDrawbackManagerDao productDrawbackManagerDao;
	
	protected MemberManagerDao memberManagerDao;
	
	protected CommissionInfoManagerDao commissionInfoManagerDao;
	
	protected IntergralInfoManagerDao intergralInfoManagerDao;
	
	protected RechargeInfoManagerDao rechargeInfoManagerDao;
	
	protected ReportInfoManagerDao reportInfoManagerDao;
	
	protected ParameterSetManagerDao parameterSetManagerDao;
	
	protected BonusManagerDao bonusManagerDao;
	
	protected MemberBonusManagerDao memberBonusManagerDao;
	
	protected IntegralRuleManagerDao integralRuleManagerDao;
	
	protected EvaluateManagerDao evaluateManagerDao;
	
	protected ActivityInfoManagerDao activityInfoManagerDao;
	
	protected TicketInfoManagerDao ticketInfoManagerDao;
	
	protected BonusRecordManagerDao bonusRecordManagerDao;
	
	protected BankCardManagerDao bankCardManagerDao;
	
	protected BaseManagerDao baseManagerDao;
	
	
	

	/**
	 * 微信configJson 参数
	 */
	private String configJson;
	
	/**
	 * 分享url
	 */
	protected String shareUrl;
	

	public BaseAction() {
	}

	protected String renderText(String text) {
		return render(text, "text/plain;charset=UTF-8");
	}

	protected String render(String text, String contentType) {
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType(contentType);
			response.getWriter().write(text);
		} catch (IOException ioexception) {
		}
		return null;
	}
	
	/**
	 * @param request
	 * @param out_trade_no
	 * @return
	 * 		js微信支付接口需要的json串
	 */
	protected String unifiedorderResult(HttpServletRequest request, String out_trade_no, String body, String total_fee){
		HttpSession session = request.getSession();
		
		Unifiedorder unifiedorder = new Unifiedorder();
		
		unifiedorder.setAppid(Constants.APPID);
    	unifiedorder.setMch_id(Constants.PARTNER_ID);
    	unifiedorder.setNonce_str(UUID.randomUUID().toString().replace("-", ""));
    	
    	unifiedorder.setBody(body);
    	if (ObjValid.isValid(out_trade_no)) {
    		unifiedorder.setOut_trade_no(out_trade_no);
		}else {
			unifiedorder.setOut_trade_no(UUID.randomUUID().toString().replace("-", ""));
		}
    	
    	float sessionmoney = Float.parseFloat(total_fee)*100;
    	unifiedorder.setTotal_fee(String.format("%.0f", sessionmoney));
    	unifiedorder.setSpbill_create_ip(request.getRemoteHost());
    	//unifiedorder.setSpbill_create_ip("127.0.0.1");
    	unifiedorder.setNotify_url(Constants.PAY_NOTIFY_URL);
    	unifiedorder.setTrade_type("JSAPI");
    	
    	//String openid = "o1s4Ftwupa4R7jVt_avzb7kh6NXc"; 
    	String openid = (String) session.getAttribute("openid"); 
    	unifiedorder.setOpenid(openid);
    	
    	Map<String, String> mapS = MapUtil.objectToMap(unifiedorder,"sign");
    	String sign = SignatureUtil.generateSign(mapS, Constants.PARTNER_KEY);
    	unifiedorder.setSign(sign);
    	
    	List<Unifiedorder> list = new ArrayList<Unifiedorder>();  
    	list.add(unifiedorder);
    	 JSONArray jsonArray = JSONArray.fromObject(list);  
    	log.error(jsonArray.toString());
    	
    	
    	UnifiedorderResult unifiedorderResult = PayMchAPI.payUnifiedorder(unifiedorder);
    	log.error("unifiedorderResult:" + unifiedorderResult.getReturn_code() +"-"+unifiedorderResult.getReturn_msg() + "-unifiedorderResult.getResult_code()-" + 
    	    	unifiedorderResult.getResult_code() + "-unifiedorderResult.getPrepay_id()：" + unifiedorderResult.getPrepay_id());
    	//网页端调起支付API
    	String json = PayUtil.generateMchPayJsRequestJson(unifiedorderResult.getPrepay_id(), Constants.APPID, Constants.PARTNER_KEY);
    	log.error("PayJsAction:"+JSONObject.fromObject(json));
    	
		return json;
	}
	
//	/**
//	 * 获取微信Token
//	 * @throws InterruptedException 
//	 */
//	protected String getWxToken() throws InterruptedException {
//		String tokenString = TokenManager.getToken(Constants.APPID);
//		if (!ObjValid.isValid(tokenString)) {
//			TokenManager.init(Constants.APPID, Constants.APPSECRET);
//			tokenString = TokenManager.getToken(Constants.APPID);
//		}
//		return tokenString;
//	}
	
	/**
	 * 获取微信Ticket
	 * @throws InterruptedException 
	 */
//	protected String getWxTicket() throws InterruptedException {
//		String ticketString = TicketManager.getTicket(Constants.APPID);
//		if (!ObjValid.isValid(ticketString)){
//			TicketManager.init(Constants.APPID);
//			ticketString = TicketManager.getTicket(Constants.APPID);
//		}
//		return ticketString;
//	}
	
	/**
	 * @param url
	 * 			当前页面的微信分享json
	 * 获取分享的configJson
	 * @throws Exception 
	 */
	protected String getWechatShareUrl() throws Exception {
		String fullPath  = getCurrentUrl();
		log.info("getWechatShareUrl--"+fullPath);
		String jsapi_ticket = TicketManager.getTicket(Constants.APPID);
		configJson = JsUtil.generateConfigJson(jsapi_ticket, false, Constants.APPID, fullPath, "onMenuShareTimeline", "onMenuShareAppMessage");
		log.info("configJson--"+configJson);
		return configJson;
	}
	
	/**
	 * @param url
	 * 			产品的微信分享json
	 * 获取分享的configJson
	 * @throws Exception 
	 */
	protected String getProductShareUrl(Long id,Long fatherId) throws Exception {
		HttpServletRequest request = getRequest();
		String fullPath  = Constants.WEBSITE_URL + "/epProductAction!tampons.action?"+request.getQueryString();
		String jsapi_ticket = TicketManager.getTicket(Constants.APPID);
		log.error("getProductShareUrl--"+fullPath);
		log.error("jsapi_ticket--"+jsapi_ticket);
		configJson = JsUtil.generateConfigJson(jsapi_ticket, false, Constants.APPID, fullPath, "onMenuShareTimeline", "onMenuShareAppMessage");
		log.error("configJson--"+configJson);
		return configJson;
	}
	
	
			/**
			 * @return
			 * 			返回当前访问的url
			 * @throws Exception
			 */
		  protected String getCurrentUrl() throws Exception
		  {
		    HttpServletRequest request = getRequest();

		    String basePath = Constants.WEBSITE_URL;
		    String requestURI = request.getRequestURI();
		    String queryString = request.getQueryString();
		    String fullPath = basePath + requestURI;
		    if (ObjValid.isValid(new Object[] { queryString })) {
		      fullPath = fullPath + "?" + queryString;
		    }
		    this.logger.debug("fullPath:" + fullPath);
		    return fullPath;
		  }
	
	/*TODO：微信分享：
	 *  
	 * 调用微信分享：
	 * String jsapi_ticket = getWxTicket();
	 * 
	 * 其中url参数待定
	 * String configJson = JsUtil.generateConfigJson(jsapi_ticket, false, Constants.APPID, url, "onMenuShareTimeline", "onMenuShareAppMessage");
	 * 
	 * */
	
	/**
	 * @return
	 * 		request
	 */
	protected HttpServletRequest getRequest () {
		return ServletActionContext.getRequest();
	}
	
	/**
	 * @return
	 * 		response
	 */
	protected HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}
	
	/**
	 * @return
	 * 		session
	 */
	protected HttpSession getSession() {
		return ServletActionContext.getRequest().getSession();
	}
	
	/**
	 * 获取项目根路径
	 */
	protected String getBasePath() {
		HttpServletRequest request = getRequest();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
		return basePath;
	}
	
	/**
	 * 获取session中的member
	 */
	protected Member getMember() {
		HttpSession session = getSession();
		return (Member) session.getAttribute("member");
	}
	
	/**
	 * 获取session中的openID
	 * @return
	 */
	protected String getOpenId(){
		HttpSession session = getSession();
		String openId = (String) session.getAttribute("openid");
		if (ObjValid.isValid(openId)) {
			return openId;
		}
		return null;
	}

	public ProductColumnManagerDao getProductColumnManagerDao() {
		return productColumnManagerDao;
	}

	public void setProductColumnManagerDao(
			ProductColumnManagerDao productColumnManagerDao) {
		this.productColumnManagerDao = productColumnManagerDao;
	}

	public WebSiteManagerDao getWebSiteManagerDao() {
		return webSiteManagerDao;
	}

	public void setWebSiteManagerDao(WebSiteManagerDao webSiteManagerDao) {
		this.webSiteManagerDao = webSiteManagerDao;
	}

	public EpNewsManagerDao getEpNewsManagerDao() {
		return epNewsManagerDao;
	}

	public void setEpNewsManagerDao(EpNewsManagerDao epNewsManagerDao) {
		this.epNewsManagerDao = epNewsManagerDao;
	}

	public EpColumnManagerDao getEpColumnManagerDao() {
		return epColumnManagerDao;
	}

	public void setEpColumnManagerDao(EpColumnManagerDao epColumnManagerDao) {
		this.epColumnManagerDao = epColumnManagerDao;
	}

	public EpAdManagerDao getEpAdManagerDao() {
		return epAdManagerDao;
	}

	public void setEpAdManagerDao(EpAdManagerDao epAdManagerDao) {
		this.epAdManagerDao = epAdManagerDao;
	}

	public EpLinksManagerDao getEpLinksManagerDao() {
		return epLinksManagerDao;
	}

	public void setEpLinksManagerDao(EpLinksManagerDao epLinksManagerDao) {
		this.epLinksManagerDao = epLinksManagerDao;
	}

	public UsersManagerDao getUsersManagerDao() {
		return usersManagerDao;
	}

	public void setUsersManagerDao(UsersManagerDao usersManagerDao) {
		this.usersManagerDao = usersManagerDao;
	}

	public RolesManagerDao getRolesManagerDao() {
		return rolesManagerDao;
	}

	public void setRolesManagerDao(RolesManagerDao rolesManagerDao) {
		this.rolesManagerDao = rolesManagerDao;
	}

	public UsersRolesManagerDao getUsersRolesManagerDao() {
		return usersRolesManagerDao;
	}

	public void setUsersRolesManagerDao(UsersRolesManagerDao usersRolesManagerDao) {
		this.usersRolesManagerDao = usersRolesManagerDao;
	}

	public AuthoritiesManagerDao getAuthoritiesManagerDao() {
		return authoritiesManagerDao;
	}

	public void setAuthoritiesManagerDao(AuthoritiesManagerDao authoritiesManagerDao) {
		this.authoritiesManagerDao = authoritiesManagerDao;
	}

	public RolesAuthoritiesManagerDao getRolesAuthoritiesManagerDao() {
		return rolesAuthoritiesManagerDao;
	}

	public void setRolesAuthoritiesManagerDao(RolesAuthoritiesManagerDao rolesAuthoritiesManagerDao) {
		this.rolesAuthoritiesManagerDao = rolesAuthoritiesManagerDao;
	}

	public WebSkinColorManagerDao getWebSkinColorManagerDao() {
		return webSkinColorManagerDao;
	}

	public void setWebSkinColorManagerDao(WebSkinColorManagerDao webSkinColorManagerDao) {
		this.webSkinColorManagerDao = webSkinColorManagerDao;
	}

	public WebSkinManagerDao getWebSkinManagerDao() {
		return webSkinManagerDao;
	}

	public void setWebSkinManagerDao(WebSkinManagerDao webSkinManagerDao) {
		this.webSkinManagerDao = webSkinManagerDao;
	}

	public DataHandlerManagerDao getDataHandlerManagerDao() {
		return dataHandlerManagerDao;
	}

	public void setDataHandlerManagerDao(DataHandlerManagerDao dataHandlerManagerDao) {
		this.dataHandlerManagerDao = dataHandlerManagerDao;
	}

	public StandbyManagerDao getStandbyManagerDao() {
		return standbyManagerDao;
	}

	public void setStandbyManagerDao(StandbyManagerDao standbyManagerDao) {
		this.standbyManagerDao = standbyManagerDao;
	}

	public McParameterManagerDao getMcParameterManagerDao() {
		return mcParameterManagerDao;
	}

	public void setMcParameterManagerDao(McParameterManagerDao mcParameterManagerDao) {
		this.mcParameterManagerDao = mcParameterManagerDao;
	}

	public McProductInfoManagerDao getMcProductInfoManagerDao() {
		return mcProductInfoManagerDao;
	}

	public void setMcProductInfoManagerDao(
			McProductInfoManagerDao mcProductInfoManagerDao) {
		this.mcProductInfoManagerDao = mcProductInfoManagerDao;
	}

	public PageTypeManagerDao getPageTypeManagerDao() {
		return pageTypeManagerDao;
	}

	public void setPageTypeManagerDao(PageTypeManagerDao pageTypeManagerDao) {
		this.pageTypeManagerDao = pageTypeManagerDao;
	}

	public AlterUrlManagerDao getAlterUrlManagerDao() {
		return alterUrlManagerDao;
	}

	public void setAlterUrlManagerDao(AlterUrlManagerDao alterUrlManagerDao) {
		this.alterUrlManagerDao = alterUrlManagerDao;
	}

	public BaseManagerAction getBaseManagerAction() {
		return baseManagerAction;
	}

	public void setBaseManagerAction(BaseManagerAction baseManagerAction) {
		this.baseManagerAction = baseManagerAction;
	}

	public ProductTypeManagerDao getProductTypeManagerDao() {
		return productTypeManagerDao;
	}

	public void setProductTypeManagerDao(ProductTypeManagerDao productTypeManagerDao) {
		this.productTypeManagerDao = productTypeManagerDao;
	}

	public AttributeManagerDao getAttributeManagerDao() {
		return attributeManagerDao;
	}

	public void setAttributeManagerDao(AttributeManagerDao attributeManagerDao) {
		this.attributeManagerDao = attributeManagerDao;
	}

	public PayOrderManagerDao getPayOrderManagerDao() {
		return payOrderManagerDao;
	}

	public void setPayOrderManagerDao(PayOrderManagerDao payOrderManagerDao) {
		this.payOrderManagerDao = payOrderManagerDao;
	}

	public CartInfoManagerDao getCartInfoManagerDao() {
		return cartInfoManagerDao;
	}

	public void setCartInfoManagerDao(CartInfoManagerDao cartInfoManagerDao) {
		this.cartInfoManagerDao = cartInfoManagerDao;
	}

	public OrderInfoManagerDao getOrderInfoManagerDao() {
		return orderInfoManagerDao;
	}

	public void setOrderInfoManagerDao(OrderInfoManagerDao orderInfoManagerDao) {
		this.orderInfoManagerDao = orderInfoManagerDao;
	}

	public ProductAttrManagerDao getProductAttrManagerDao() {
		return productAttrManagerDao;
	}

	public void setProductAttrManagerDao(ProductAttrManagerDao productAttrManagerDao) {
		this.productAttrManagerDao = productAttrManagerDao;
	}

	public StockManagerDao getStockManagerDao() {
		return stockManagerDao;
	}

	public void setStockManagerDao(StockManagerDao stockManagerDao) {
		this.stockManagerDao = stockManagerDao;
	}

	public AreaManagerDao getAreaManagerDao() {
		return areaManagerDao;
	}

	public void setAreaManagerDao(AreaManagerDao areaManagerDao) {
		this.areaManagerDao = areaManagerDao;
	}

	public CityManagerDao getCityManagerDao() {
		return cityManagerDao;
	}

	public void setCityManagerDao(CityManagerDao cityManagerDao) {
		this.cityManagerDao = cityManagerDao;
	}

	public ProvinceManagerDao getProvinceManagerDao() {
		return provinceManagerDao;
	}

	public void setProvinceManagerDao(ProvinceManagerDao provinceManagerDao) {
		this.provinceManagerDao = provinceManagerDao;
	}

	public AddressManagerDao getAddressManagerDao() {
		return addressManagerDao;
	}

	public void setAddressManagerDao(AddressManagerDao addressManagerDao) {
		this.addressManagerDao = addressManagerDao;
	}

	public ProductDrawbackManagerDao getProductDrawbackManagerDao() {
		return productDrawbackManagerDao;
	}

	public void setProductDrawbackManagerDao(
			ProductDrawbackManagerDao productDrawbackManagerDao) {
		this.productDrawbackManagerDao = productDrawbackManagerDao;
	}

	public MemberManagerDao getMemberManagerDao() {
		return memberManagerDao;
	}

	public void setMemberManagerDao(MemberManagerDao memberManagerDao) {
		this.memberManagerDao = memberManagerDao;
	}

	public CommissionInfoManagerDao getCommissionInfoManagerDao() {
		return commissionInfoManagerDao;
	}

	public void setCommissionInfoManagerDao(
			CommissionInfoManagerDao commissionInfoManagerDao) {
		this.commissionInfoManagerDao = commissionInfoManagerDao;
	}

	public IntergralInfoManagerDao getIntergralInfoManagerDao() {
		return intergralInfoManagerDao;
	}

	public void setIntergralInfoManagerDao(
			IntergralInfoManagerDao intergralInfoManagerDao) {
		this.intergralInfoManagerDao = intergralInfoManagerDao;
	}

	public RechargeInfoManagerDao getRechargeInfoManagerDao() {
		return rechargeInfoManagerDao;
	}

	public void setRechargeInfoManagerDao(
			RechargeInfoManagerDao rechargeInfoManagerDao) {
		this.rechargeInfoManagerDao = rechargeInfoManagerDao;
	}

	public ReportInfoManagerDao getReportInfoManagerDao() {
		return reportInfoManagerDao;
	}

	public void setReportInfoManagerDao(ReportInfoManagerDao reportInfoManagerDao) {
		this.reportInfoManagerDao = reportInfoManagerDao;
	}

	public ParameterSetManagerDao getParameterSetManagerDao() {
		return parameterSetManagerDao;
	}

	public void setParameterSetManagerDao(
			ParameterSetManagerDao parameterSetManagerDao) {
		this.parameterSetManagerDao = parameterSetManagerDao;
	}

	public BonusManagerDao getBonusManagerDao() {
		return bonusManagerDao;
	}

	public void setBonusManagerDao(BonusManagerDao bonusManagerDao) {
		this.bonusManagerDao = bonusManagerDao;
	}

	public MemberBonusManagerDao getMemberBonusManagerDao() {
		return memberBonusManagerDao;
	}

	public void setMemberBonusManagerDao(MemberBonusManagerDao memberBonusManagerDao) {
		this.memberBonusManagerDao = memberBonusManagerDao;
	}

	public IntegralRuleManagerDao getIntegralRuleManagerDao() {
		return integralRuleManagerDao;
	}

	public void setIntegralRuleManagerDao(
			IntegralRuleManagerDao integralRuleManagerDao) {
		this.integralRuleManagerDao = integralRuleManagerDao;
	}

	public EvaluateManagerDao getEvaluateManagerDao() {
		return evaluateManagerDao;
	}

	public void setEvaluateManagerDao(EvaluateManagerDao evaluateManagerDao) {
		this.evaluateManagerDao = evaluateManagerDao;
	}

	public ActivityInfoManagerDao getActivityInfoManagerDao() {
		return activityInfoManagerDao;
	}

	public void setActivityInfoManagerDao(
			ActivityInfoManagerDao activityInfoManagerDao) {
		this.activityInfoManagerDao = activityInfoManagerDao;
	}

	public TicketInfoManagerDao getTicketInfoManagerDao() {
		return ticketInfoManagerDao;
	}

	public void setTicketInfoManagerDao(TicketInfoManagerDao ticketInfoManagerDao) {
		this.ticketInfoManagerDao = ticketInfoManagerDao;
	}
	public BonusRecordManagerDao getBonusRecordManagerDao() {
		return bonusRecordManagerDao;
	}

	public void setBonusRecordManagerDao(BonusRecordManagerDao bonusRecordManagerDao) {
		this.bonusRecordManagerDao = bonusRecordManagerDao;
	}

	public String getConfigJson() {
		return configJson;
	}

	public void setConfigJson(String configJson) {
		this.configJson = configJson;
	}

	public String getShareUrl() {
		return shareUrl;
	}

	public void setShareUrl(String shareUrl) {
		this.shareUrl = shareUrl;
	}

	public BankCardManagerDao getBankCardManagerDao() {
		return bankCardManagerDao;
	}

	public void setBankCardManagerDao(BankCardManagerDao bankCardManagerDao) {
		this.bankCardManagerDao = bankCardManagerDao;
	}

	public BaseManagerDao getBaseManagerDao() {
		return baseManagerDao;
	}

	public void setBaseManagerDao(BaseManagerDao baseManagerDao) {
		this.baseManagerDao = baseManagerDao;
	}


}