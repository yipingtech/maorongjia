package cc.messcat.web.front;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.providers.encoding.Md5PasswordEncoder;

import com.opensymphony.xwork2.ActionContext;

import cc.messcat.entity.Address;
import cc.messcat.entity.Area;
import cc.messcat.entity.BankCard;
import cc.messcat.entity.CashInfo;
import cc.messcat.entity.City;
import cc.messcat.entity.CommissionInfo;
import cc.messcat.entity.Evaluate;
import cc.messcat.entity.IntergralInfo;
import cc.messcat.entity.McProductInfo;
import cc.messcat.entity.Member;
import cc.messcat.entity.MemberBonus;
import cc.messcat.entity.MemberTicket;
import cc.messcat.entity.OrderInfo;
import cc.messcat.entity.ParameterSet;
import cc.messcat.entity.PayOrder;
import cc.messcat.entity.ProductAttr;
import cc.messcat.entity.ProductDrawback;
import cc.messcat.entity.Province;
import cc.messcat.entity.RechargeInfo;
import cc.messcat.entity.ReportInfo;
import cc.messcat.entity.Stock;
import cc.messcat.vo.PayOrderVo;
import cc.messcat.wechat.weixin.popular.api.QrcodeAPI;
import cc.messcat.wechat.weixin.popular.bean.QrcodeTicket;
import cc.messcat.wechat.weixin.popular.bean.templatemessage.TemplateMessageResult;
import cc.messcat.wechat.weixin.popular.support.MessageManager;
import cc.messcat.wechat.weixin.popular.support.TokenManager;
import cc.modules.constants.Constants;
import cc.modules.util.DateHelper;
import cc.modules.util.FormatStringUtil;
import cc.modules.util.ObjValid;

/**
 * 处理并获取会员中心的数据及属性
 * 
 * @author Administrator
 * 
 */
public class EpMemberCenterAction extends FrontAction {

	private static final long serialVersionUID = 1L;
	
	private Logger log = LoggerFactory.getLogger(EpMemberCenterAction.class); 
	
	private Long id;
	private int count1;
	private long count2;
	private int count3;
	private int tabNum;
	private Long productId;
	private Double cashDouble;
	private String productFlag;
	private String productFlag2;

	private String message;
	private String year;
	private String month;
	private String day;
	private String predictTime;
	private String orderNum;
	private PayOrder payOrder;
	private CashInfo cashInfo;
	private OrderInfo orderInfo;
	private ReportInfo reportInfo;
	private MemberBonus memberBonu;
	private RechargeInfo rechargeInfo;
	private IntergralInfo intergralInfo;
	private CommissionInfo commissionInfo;
	private ProductDrawback productDrawback;
	private Evaluate evaluate;
	private String oldMobile;
	private String proAttrIds;
	private Long memberId;

	private List<Member> members;
	private List<PayOrder> payOrders1;
	private List<PayOrderVo> payOrderVoList;
	private List<OrderInfo> orderInfos1;
	private List<PayOrder> payOrders2;
	private List<OrderInfo> orderInfos2;
	private List<PayOrder> payOrders3;
	private List<OrderInfo> orderInfos3;
	private List<Address> addressList;

	private Long orderInfoId;
	/**
	 * 订单id
	 */
	private Long payOrderId;
	private Long addressId;
	private Long provinceId;
	private Long cityId;
	private Long areaId;
	private Address address;

	private List<Province> provinceList;
	private List<City> cities;
	private List<Area> areas;
	private List<ReportInfo> reportInfos;
	private List<RechargeInfo> rechargeInfos;
	private List<MemberBonus> memberBonus1;
	private List<MemberBonus> memberBonus2;
	private List<MemberBonus> memberBonus3;
	private List<CashInfo> cashInfos;
	private List<IntergralInfo> intergralInfos;
	private List<CommissionInfo> commissionInfos;
	private List<MemberTicket> memberTickets;

	private Map<String, List<Member>> memberMap;
	private Map<String, List<PayOrder>> payOrderMap;
	
	private Map<String, String> payOrderStatusMap = new HashMap();//订单状态
	private Map<String, String> orderInfoStatusMap = new HashMap();//清单商品状态
	private Map<String, String> orderInfoAttrStrMap = new HashMap();//清单商品属性

	private String flag;
	private String payPassword;
	private ParameterSet parameterSet;
	private int level;
	private Double rateSecond;
	private int teamNum;
	private Double sum;
	private Double moneyToSend;

	private Double bonus;

	/**
	 * 生成二维码图片名称
	 */
	private String qrCodeName;
	/**
	 * 从前台传过的银行卡的信息
	 * */
	public BankCard bankCard;

	public String bankCardFlag; // 银行卡归属

	public String bankCardNum;// 银行卡号

	public String bankCardMember;// 持卡人

	public String bankCardAddress;// 开户省市

	public String bankCardPoint;// 开户网点

	public String provinceIdStr;

	public String cityIdStr;

	public List<BankCard> bankCardList;
	
	private Member memberInfo;
	
	/**
	 * 二维码分享父级id
	 */
	private String qrcodeFatherId;
	private Long fatherId;
	
	/**
	 * 退款原因
	 * */
	private String reason;
	private String otherReason;
	private String payOrderAmount;
	private List<String> agentOrderList;

	/**
	 * 拿取所有代理商订单接口
	 * */
	public String findAllAgentOrder(){
		agentOrderList = this.payOrderManagerDao.findAllAgentOrder();
		return "findAllAgentOrder";
	}

	/**
	 * 首页入口
	 */
	public String execute() throws Exception {
		//super.init();
		this.initMemberCenter();
		return "initMember";
	}
	
	/**
	 * 重置代理商
	 * */
	public String showRecharge(){
		this.parameterSet = this.parameterSetManagerDao.retrieveParameterSet();
		return "showRecharge";
	}
	
	/**
	 * 退货申请
	 * */
	public String rechargeAgent(){
		
		return "rechargeAgent";
	}
	
	/**
	 * 退货申请
	 * */
	public String returnedPurchase(){
		ProductDrawback p = new ProductDrawback();
		p.setApplyTime(new Date());
		if(ObjValid.isValid(reason)){
			reason = reason+", 其他原因：";
		}else{
			reason = "其他原因：";
		}
		p.setDrawbackCause(reason+otherReason);
		p.setOrderNum(orderNum);
		PayOrder payOrder = new PayOrder();
		List<OrderInfo> orderInfos = new ArrayList<>();
		payOrder = this.payOrderManagerDao.queryByOrderNum(orderNum);
		orderInfos = orderInfoManagerDao.searchByPayOrder(payOrder);
		for (int i = 0; i < orderInfos.size(); i++) {
			p.setProductId(orderInfos.get(i).getProduct());
		}
		p.setMemberId(String.valueOf(getMember().getId()));
		p.setFlag(flag);
		if(flag.endsWith("0")){
			p.setReturnStatus("1"); //申请退货
		}else if(flag.endsWith("1")){
			p.setReturnStatus("4"); //申请换货
		}
		p.setDrawbackMoney(Double.valueOf(payOrderAmount));
		payOrder.setProductDrawback(p);
		p.setStatus("1");
		this.productDrawbackManagerDao.addProductDrawback(p);
		this.payOrderManagerDao.modifyPayOrder(payOrder);
		return "delete_success_font";
	}
	
	/**
	 * 订单修改状态后门的方法（未付款---》已付款）
	 * */
	public String editOrder(){
		this.orderNum = orderNum;
		payOrderManagerDao.finishTrade(orderNum);
		return "toshowEditOrder";
	}
	
	/**
	 * 跳转到订单修改状态后门
	 * */
	public String showEditOrder(){
		return "showEditOrder";
	}

	/**
	 * 提款记录的页面
	 * */
	public String queryCashRecord() {
		Member member = getMember();
		pager = this.bankCardManagerDao.findCashInfoRecord(member, 5, pageNo);
		cashInfos = pager.getResultList();
		return "queryCashRecord";
	}

	/**
	 * 提款分页返回页面
	 * */
	public String queryMoreRecord() {
		Member member = getMember();
		pager = this.bankCardManagerDao.findCashInfoRecord(member, pageSize, pageNo);
		cashInfos = pager.getResultList();
		return "queryMoreRecord";
	}

	/**
	 * 我的代理 ---分页
	 * */
	public String queryMyAgentMore() {
		Member member = getMember();
		Date begin = member.getAgentTime();
		this.pager = this.payOrderManagerDao.findMyAgentOrder(member, begin, new Date(), pageSize, pageNo);
		this.payOrders1 = this.pager.getResultList();
		return "queryMyAgentMore";
	}

	/**
	 * 更换绑定银行卡的方法
	 * 
	 * @throws Exception
	 * */
	public String changeCard() throws Exception {
		//super.init();
		bankCard = this.bankCardManagerDao.retrieveBankCard(id);
		bankCard.setBankCardTime(new Date());
		this.bankCardManagerDao.modifyBankCard(bankCard);
		return "changeCard";
	}

	/**
	 * 跳到可以更换绑定银行卡界面
	 * 
	 * @throws Exception
	 * */
	public String toChangeCard() throws Exception {
		//super.init();
		Member member = getMember();
		bankCardList = new ArrayList<BankCard>();
		bankCardList = this.bankCardManagerDao.findByMember(member);
		// 标示从哪里跳转进来的，0；更改银行卡，1：申请提现
		this.flag = flag;
		return "findBankCardByMember";
	}

	/**
	 * 编辑银行卡页面
	 * 
	 * @throws Exception
	 * */
	public String toEditCard() throws Exception {
	    //super.init();
		bankCard = this.bankCardManagerDao.retrieveBankCard(id);
		this.provinceList = this.provinceManagerDao.retrieveAllProvinces();
		if (ObjValid.isValid(bankCard.getProvince().getId())) {
			cities = cityManagerDao.findCityByProvince(bankCard.getProvince().getProvinceId());
		}
		this.flag = flag;
		return "toEditCard";
	}

	/**
	 * 用户某张银行卡的详情页面
	 * 
	 * @throws Exception
	 * */
	public String bankCardDetail() throws Exception {
		//super.init();
		bankCard = this.bankCardManagerDao.retrieveBankCard(id);
		this.flag = flag;
		return "bankCardDetail";
	}

	/**
	 * 去添加银行卡的界面
	 * 
	 * @throws Exception
	 * */
	public String toAddCard() throws Exception {
		//super.init();
		this.provinceList = this.provinceManagerDao.retrieveAllProvinces();
		this.flag = flag;
		return "toAddCard";
	}

	/**
	 * 添加银行卡
	 * 
	 * @throws Exception
	 * */
	public String addBankCard() throws Exception {
		bankCard = new BankCard();
		provinceId = Long.valueOf(provinceIdStr);
		Province province = this.provinceManagerDao.retrieveProvince(provinceId);
		bankCard.setProvince(province);
		if (ObjValid.isValid(cityIdStr)) {
			cityId = Long.valueOf(cityIdStr);
			City city = this.cityManagerDao.retrieveCity(cityId);
			bankCard.setCity(city);
			bankCard.setBankCardAddress(province.getProvince() + city.getCity());
		}else {
			bankCard.setBankCardAddress(province.getProvince());
		}
		bankCard.setBankCardFlag(bankCardFlag);
		bankCard.setBankCardMember(bankCardMember);
		bankCard.setBankCardNum(bankCardNum);
		bankCard.setBankCardPoint(bankCardPoint);
		bankCard.setBankCardTime(new Date());
		bankCard.setMember(getMember());
		bankCard.setStatus("1");
		this.bankCardManagerDao.saveBankCard(bankCard);
		this.flag = flag;
		return "findBankCardByMember2";
	}

	/**
	 * 编辑银行卡
	 * 
	 * @throws Exception
	 * */
	public String editBankCard() throws Exception {
		bankCard = new BankCard();
		bankCard = this.bankCardManagerDao.retrieveBankCard(id);
		if (ObjValid.isValid(provinceIdStr)) {
			provinceId = Long.valueOf(provinceIdStr);
			Province province = this.provinceManagerDao.retrieveProvince(provinceId);
			bankCard.setProvince(province);
			if (ObjValid.isValid(cityIdStr)) {
				cityId = Long.valueOf(cityIdStr);
				City city = this.cityManagerDao.retrieveCity(cityId);
				bankCard.setCity(city);
				bankCard.setBankCardAddress(province.getProvince() + city.getCity());
			}else {
				bankCard.setBankCardAddress(province.getProvince());
			}
		}
		bankCard.setBankCardFlag(bankCardFlag);
		bankCard.setBankCardMember(bankCardMember);
		bankCard.setBankCardNum(bankCardNum);
		bankCard.setBankCardPoint(bankCardPoint);
		bankCard.setBankCardTime(new Date());
		bankCard.setStatus("1");
		this.bankCardManagerDao.modifyBankCard(bankCard);
		this.flag = flag;
		return "findBankCardByMember2";
	}

	/**
	 * 删除银行卡
	 * 
	 * @throws Exception
	 * */
	public String deleteBankCard() throws Exception {
		bankCard = new BankCard();
		bankCard = this.bankCardManagerDao.retrieveBankCard(id);
		bankCard.setStatus("0");
		this.bankCardManagerDao.modifyBankCard(bankCard);
		this.flag = flag;
		return "findBankCardByMember2";
	}

	/**
	 * 查询用户所拥有的银行卡
	 * 
	 * @throws Exception
	 * */
	public String findBankCardByMember() throws Exception {
		//super.init();
		Member member = getMember();
		bankCardList = new ArrayList<BankCard>();
		bankCardList = this.bankCardManagerDao.findByMember(member);
		return "findBankCardByMember";
	}

	public String goEvaluate() throws Exception {
		//super.init();
		this.orderInfo = this.orderInfoManagerDao.retrieveOrderInfo(this.orderInfoId);
		return "evaluate";
	}

	/**
	 * 分页查询出该用户的粉丝
	 * 后台
	 * @throws Exception
	 * */
	public String queryMemberFans() throws Exception {
		super.init();
		pager = this.memberManagerDao.findMyFans(pageSize, pageNo, getMember());
		//pager = this.memberManagerDao.findMyFans(50, 1, getMember());
		members = this.pager.getResultList();
		return "my-fans";
	}

	/**
	 * 评价商品
	 * 
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public String evaluateProduct() throws UnsupportedEncodingException {
		this.evaluate.setMember(getMember());
		this.evaluate.setAddTime(new Date());
		this.evaluate.setStatus("1");
		this.orderInfo = this.orderInfoManagerDao.retrieveOrderInfo(this.orderInfoId);
		this.evaluate.setProduct(orderInfo.getProduct());
		this.evaluateManagerDao.addEvaluate(this.evaluate, this.orderInfo);
		McProductInfo product = evaluate.getProduct();
		double grade = this.evaluateManagerDao.countLevelAvg(product);
		product.setGrade(grade);
		this.mcProductInfoManagerDao.modifyMcProductInfo(product);
		return this.noPayOrderByUser();
	}

	/**
	 * 初始化会员中心
	 */
	public void initMemberCenter() {
		payOrder = new PayOrder();
		Member member = getMember();
		
		member = memberManagerDao.retrieveMember(member.getId());
		//member = memberManagerDao.retrieveMember(568L);
		Map session = ActionContext.getContext().getSession();
		session.put("member", member);
		
		//实时去查会员总佣金，该会员现在的佣金 = 总佣金 - 已提现佣金    allBonus为总佣金，bonus为现有佣金   
		Double allBonus = commissionInfoManagerDao.findMemberBonus(member);
		if(ObjValid.isValid(member.getSendCommission())){
			bonus = allBonus - member.getSendCommission();
		}else{
			bonus = allBonus;
		}
        //订单数		
		count1 = Integer.parseInt(String.valueOf(payOrderManagerDao.TotalOrder(member, payOrder)));
		
		
		//查询团队总人数，分销商--则查询所有下级 ,分销商则查询下三级
		if (ObjValid.isValid(member.getAgent())) {
			this.teamNum = this.payOrderManagerDao.countMyTeamByAgent(member);
		} else {
			this.teamNum = memberManagerDao.queryAllTeam(member);
		}
	}

	/**
	 * 显示用户信息详情
	 * 
	 * @return
	 * @throws Exception
	 */
	public String viewMember() throws Exception {
		try {
			super.init();
			log.info(getMember().getId() + "---" + getMember().getNickname());
			Member member = memberManagerDao.retrieveMember(this.getMember().getId());
		} catch (Exception ex) {
			addActionMessage("Load page error!");
		}
		return "viewMember";
	}

	/**
	 * 跳转到会员信息修改界面
	 * 
	 * @return
	 */
	public String newEditMemberPage() {
		try {
			//super.init();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "toEditMenber";
	}

	/**
	 * 跳转到会员更改手机号界面
	 * 
	 * @return
	 */
	public String newMemberPhonePage() {
		try {
			//super.init();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "toEditPhone";
	}

	/**
	 * 绑定会员手机号
	 * 
	 * @return
	 */
	public String EditMemberPhonePage() {
		try {
			//super.init();
			// memberManagerDao.editMemberPhone(member);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// return "giving_presents";
		return "toEditPhone";
	}

	/**
	 * 绑定手机号
	 * 
	 * @return
	 * @throws Exception
	 */
	public String bindingPhone() throws Exception {
		memberManagerDao.editMemberPhone(getMember(), oldMobile);
		if (!ObjValid.isValid(oldMobile)) {
			return "giving_presents";
		}
		return "binding_success";
	}

	/**
	 * 跳转到重置支付密码页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String resetPasswordPage() throws Exception {
		super.init();
		return "resetPwd";
	}

	/**
	 * 重置支付密码
	 * 
	 * @return
	 * @throws Exception
	 */
	public String resetPayPassword() throws Exception {
		Member member = getMember();
		member.setPayPassword((new Md5PasswordEncoder()).encodePassword(member.getPayPassword(), member.getLoginName()));
		this.memberManagerDao.updateMember(member);
		return "binding_success";
	}

	public String checkPayPassword() {
		this.payPassword = new Md5PasswordEncoder().encodePassword(this.payPassword, getMember().getLoginName());

		//

		if (this.payPassword.equals(getMember().getPayPassword())) {
			this.flag = "true";
		} else {
			this.flag = "false";
		}
		// this.flag="true";
		return "success";
	}

	/**
	 * 用户信息修改
	 * 
	 * @return
	 * @throws Exception
	 */
	public String editMember() throws Exception {
		try {
			HttpSession session = getSession();
			memberInfo.setLoginName(getMember().getLoginName());
			memberManagerDao.editMemberByUser(memberInfo,year,month,day);
			getMember().setAddress(memberInfo.getAddress());
			getMember().setRealname(memberInfo.getRealname());
			getMember().setSex(memberInfo.getSex());
			getMember().setMobile(memberInfo.getMobile());
		} catch (Exception ex) {
			ex.printStackTrace();
			addActionMessage("Load page error!");
		}
		return "viewMember";
	}

	@SuppressWarnings("unchecked")
	public String editAddressPage() throws Exception {
		//super.init();
		this.provinceList = this.provinceManagerDao.retrieveAllProvinces();
		if (ObjValid.isValid(addressId)) {
			this.address = this.addressManagerDao.retrieveAddress(addressId);
			if (ObjValid.isValid(address.getProvince())) {
				cities = cityManagerDao.findCityByProvince(address.getProvince().getProvinceId());
			}
			if (ObjValid.isValid(address.getCity())) {
				areas = areaManagerDao.findAreaByCity(address.getCity().getCityId());
			}
		}
		this.flag = flag;
		this.productFlag2 = productFlag2;
		return "editPage";
	}

	/**
	 * 收货地址列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String addAddress() throws Exception {
		//super.init();
		addressList = this.addressManagerDao.retrieveAllAddresss(getMember());
		this.flag = flag;
		this.productFlag2 = productFlag2;
		return "addPage";
	}

	/**
	 * 新增或修改地址
	 * 
	 * @return
	 * @throws Exception
	 */
	public String editAddress() throws Exception {
		Province province = this.provinceManagerDao.retrieveProvince(this.provinceId);
		City city = new City();
		Area area = new Area();
		if (ObjValid.isValid(cityId)) {
			city = this.cityManagerDao.retrieveCity(this.cityId);
		}
		if (ObjValid.isValid(areaId)) {
			area = this.areaManagerDao.retrieveArea(this.areaId);
		}
		Member addressMember = this.memberManagerDao.retrieveMember(memberId);
		this.productFlag2 = productFlag2;
		if (ObjValid.isValid(addressId)) {
			Address address0 = this.addressManagerDao.retrieveAddress(addressId);
			address0.setProvince(province);
			if (null!=city.getId()) {
				address0.setCity(city);
			}else {
				address0.setCity(null);
			}
			if (null!=area.getId()) {
				address0.setArea(area);
			}else {
				address0.setArea(null);
			}
			address0.setCellphone(this.address.getCellphone());
			address0.setConsignee(this.address.getConsignee());
			address0.setAddress(this.address.getAddress());
			address0.setMember(addressMember);
			this.addressManagerDao.modifyAddress(address0);
		} else {
			address.setProvince(province);
			if (null!=city.getId()) {
				address.setCity(city);
			}else {
				address.setCity(null);
			}
			if (null!=area.getId()) {
				address.setArea(area);
			}else {
				address.setArea(null);
			}
			address.setMember(addressMember);
			this.addressManagerDao.addAddress(address);
		}
		if (ObjValid.isValid(flag) && flag.equals("toOrder")) {
			this.addressId = address.getId();
			return "toOrder";
		}
		return "addPageAction";
	}

	/**
	 * 删除收货地址
	 * 
	 * @return
	 * @throws Exception
	 */
	public String deleteAddress() throws Exception {
		try {
			//super.init();
			addressManagerDao.removeAddress(addressId);
		} catch (Exception ex) {
			addActionMessage("Load page error!");
		}
		return this.addAddress();
	}

	/**
	 * 用户确认付款(修改支付状态，修改支付方式和使用红包)
	 * 
	 * @return
	 * @throws Exception
	 */
	public String editPayType() throws Exception {
		try {
			//super.init();
			message = payOrderManagerDao.editPayType(payOrder);
			noReceiveOrderByUser();
		} catch (Exception ex) {
			addActionMessage("Load page error!");
		}
		return "noReceiveorder";
	}

	/**
	 * 用户的待付款订单
	 * 
	 * @return
	 */
	public String noPayOrderByUser() {
		try {
			//super.init();
			parameterSet = this.payOrderManagerDao.findParameterSet();
			payOrder = new PayOrder();
			Member member = getMember();
			payOrder.setMember(member);
			// 待付款
			payOrder.setPayStatus("0");
			pager = payOrderManagerDao.PayOrerByUser(payOrder, pageNo, pageSize);
			payOrders1 = pager.getResultList();
			orderInfos1 = payOrderManagerDao.noPayOrderByUser(member.getLoginName());
			//循环orderInfos1 判断商品是否下架   选择的属性是否存在 payOrderStatusMap orderInfoStatusMap
			for(int i=0;i<orderInfos1.size();i++){
				if(!"1".equals(orderInfos1.get(i).getProduct().getIsSale())){
					for(int j=0;j<payOrders1.size();j++){
						if(orderInfos1.get(i).getPayOrder().getOrderNum()==payOrders1.get(j).getOrderNum()){
							this.payOrderStatusMap.put(payOrders1.get(j).getOrderNum(), "2");//状态2 为该商品下架
						}
					}
				}
				if (orderInfos1.get(i).getOrderStatus().equals("0")) {
					Stock condition = new Stock();
					condition.setProduct(orderInfos1.get(i).getProduct());
					condition.setProductAttrIds(orderInfos1.get(i).getProductAttrIds());
					Stock result = stockManagerDao.findByProAttr(condition);
					if (ObjValid.isValid(result)) {
						double a = orderInfos1.get(i).getPrice();
						double b = result.getPrice();
						if (!(Double.doubleToLongBits(a)==Double.doubleToLongBits(b))) {
							this.payOrderStatusMap.put(orderInfos1.get(i).getOrderinfoNum(), "2");// 商品价格已更新
							this.orderInfoStatusMap.put(orderInfos1.get(i).getProductAttrIds(), "2");//商品价格已更新
							continue;
						}
					}
				}
				//选择的属性是否存在
				List<String> proAttrIdList = FormatStringUtil.splitBySign(orderInfos1.get(i).getProductAttrIds(), ",");
				boolean flag = true;
				String AttrStr = "";
				for (int k = 0; k < proAttrIdList.size(); k++) {
					ProductAttr productAttr = this.productAttrManagerDao.retrieveProductAttr(Long.parseLong(proAttrIdList.get(k)));
					if(!ObjValid.isValid(productAttr)){
						this.orderInfoStatusMap.put(orderInfos1.get(i).getProductAttrIds(), "2");//状态2为该商品属性不存在
						for(int j=0;j<payOrders1.size();j++){
							if(orderInfos1.get(i).getPayOrder().getOrderNum()==payOrders1.get(j).getOrderNum()){
								this.payOrderStatusMap.put(payOrders1.get(j).getOrderNum(), "2");//状态2为该商品属性不存在
							}
						}
						flag = false;
					}else{
						AttrStr = AttrStr + productAttr.getAttrValue()+"   ";
					}
				}
				if(flag){
					orderInfoAttrStrMap.put(orderInfos1.get(i).getProductAttrIds(), AttrStr);
				}
			}
			// count1待付款
			count1 = Integer.parseInt(String.valueOf(payOrderManagerDao.TotalOrder(member, payOrder)));
			// payOrder.setPayStatus(null);
			// payOrder.setShippingStatus("1");
			// count2未收货---改成已付款未发货
			// count2=Integer.parseInt(String.valueOf(payOrderManagerDao.TotalOrder(member,payOrder,1,10)));
			// payOrder.setShippingStatus("0");
			// payOrder.setPayStatus("1");
			// pager=payOrderManagerDao.PayOrerByUser(payOrder,pageNo,pageSize);
			// payOrders2=pager.getResultList();
			// orderInfos2=payOrderManagerDao.noReceiveOrderByUser(member.getLoginName(),
			// "1");
			payOrder.setPayStatus(null);
			payOrder.setShippingStatus("3");
			count2 = Integer.parseInt(String.valueOf(payOrderManagerDao.TotalOrder(member, payOrder)));

			if (ObjValid.isValid(payOrderId)) {
				PayOrder temPayOrder = payOrderManagerDao.retrievePayOrderById(payOrderId);
				// 付款消息提醒：
				if (ObjValid.isValid(member)) {
					Iterator<OrderInfo> iterator = temPayOrder.getOrderInfos().iterator();
					StringBuilder goodsInfo = new StringBuilder();
					while (iterator.hasNext()) {
						OrderInfo orderInfo = (OrderInfo) iterator.next();
						goodsInfo.append(orderInfo.getProduct().getTitle() + "x" + orderInfo.getAmount() + ",");
					}
					goodsInfo.deleteCharAt(goodsInfo.length() - 1);
					TemplateMessageResult templateMessageResult = MessageManager.sendNewOrderTemplateMessage(null, "", goodsInfo
						.toString(), temPayOrder.getProductAmount() + "", "1".equals(temPayOrder.getPayStatus()) ? "已付款" : "未付款",
						null, member.getLoginName(),temPayOrder);
				}
				// 给上一级发通知
				if (ObjValid.isValid(temPayOrder.getMember()) && ObjValid.isValid(temPayOrder.getMember().getMemberFather())) {

					Iterator<OrderInfo> iterator = temPayOrder.getOrderInfos().iterator();
					StringBuilder goodsInfo = new StringBuilder();
					while (iterator.hasNext()) {
						OrderInfo orderInfo = (OrderInfo) iterator.next();
						goodsInfo.append(orderInfo.getProduct().getTitle() + "x" + orderInfo.getAmount() + ",");
					}
					goodsInfo.deleteCharAt(goodsInfo.length() - 1);
					TemplateMessageResult templateMessageResult = MessageManager.sendNewOrderTemplateMessage("一级",
						member.getNickname(), goodsInfo.toString(), temPayOrder.getProductAmount() + "",
						"1".equals(temPayOrder.getPayStatus()) ? "已付款" : "未付款", null, member.getMemberFather().getLoginName(),temPayOrder);
				}
			}

			// count2=pager.getRowCount();
			addActionMessage("Delete successfully!");
		} catch (Exception ex) {
			this.addActionError(ex.getMessage());
			ex.printStackTrace();
			addActionMessage("Delete fail!");
		}
		return "noPayOrder";
	}

	// 根据id移除数据(把数据修改为删除态)  --前端待付款页面
	public String delPayOrderByOrderNumFont() throws Exception {
			try {
				this.payOrderManagerDao.deletePayOrder(payOrder, "1", "0");
				addActionMessage("Delete successfully!");
			} catch (Exception ex) {
				this.addActionError(ex.getMessage());
				addActionMessage("Delete fail!");
			}
			return "delete_success_font";
		}
	
	/**
	 * 用户的未收货订单---改成已付款未发货
	 * 
	 * @return
	 */
	public String noReceiveOrderByUser() {
		try {
			//super.init();
			// 待收货 ---改成已付款未发货
			payOrder = new PayOrder();
			Member member = getMember();
			payOrder.setMember(member);
			payOrder.setShippingStatus("0");
			payOrder.setPayStatus("1");
			pager = payOrderManagerDao.PayOrerByUser(payOrder, pageNo, pageSize);
			// 已付款，未发货
			payOrders2 = pager.getResultList();
			orderInfos2 = payOrderManagerDao.noReceiveOrderByUser(member.getLoginName(), "1");
			count2 = Integer.parseInt(String.valueOf(payOrderManagerDao.TotalOrder(member, payOrder)));
			// count2=pager.getRowCount();
			// count3=2;
			payOrder.setPayStatus("0");
			count1 = Integer.parseInt(String.valueOf(payOrderManagerDao.TotalOrder(member, payOrder)));
			addActionMessage("Delete successfully!");
		} catch (Exception ex) {
			this.addActionError(ex.getMessage());
			ex.printStackTrace();
			addActionMessage("Delete fail!");
		}
		return "noReceiveOrder";
	}

	/**
	 * 用户的全部已收货订单
	 * 
	 * @return
	 */
	public String allPayOrderByUser() {
		try {
			//super.init();
			// 全部收货订单
			payOrder = new PayOrder();
			Member member = getMember();
			payOrder.setMember(member);
			// 待付款
			payOrder.setPayStatus("0");
			// pager=payOrderManagerDao.PayOrerByUser(payOrder,pageNo,pageSize);
			// payOrders1=pager.getResultList();
			// orderInfos1=payOrderManagerDao.noPayOrderByUser(member.getLoginName());
			count1 = Integer.parseInt(String.valueOf(payOrderManagerDao.TotalOrder(member, payOrder)));

			// count2=Integer.parseInt(String.valueOf(payOrderManagerDao.TotalOrder(member,payOrder1,1,10)));

			payOrder.setPayStatus(null);
			payOrder.setShippingStatus("3");
			count2 = Integer.parseInt(String.valueOf(payOrderManagerDao.TotalOrder(member, payOrder)));
			// pager=payOrderManagerDao.PayOrerByUser(payOrder,pageNo,pageSize);
			// payOrders2=pager.getResultList();
			// orderInfos2=payOrderManagerDao.noReceiveOrderByUser(member.getLoginName(),
			// "1");
			// count2=pager.getRowCount();
			// count2=Integer.parseInt(String.valueOf(payOrderManagerDao.TotalOrder(member,payOrder)));

			payOrder.setShippingStatus(null);
			payOrder.setPayStatus(null);
			pager = payOrderManagerDao.PayOrerByUser(payOrder, pageNo, pageSize);
			payOrders3 = pager.getResultList();
			//判断是否确认付款后的第N天，是的话关闭退换货入口
			Date today = new Date();// 今天
			String timeLimit = "0";
			ParameterSet parameterSet = this.payOrderManagerDao.findParameterSet();
			if(ObjValid.isValid(parameterSet.getTimeLimit())){
				timeLimit = "-"+parameterSet.getTimeLimit();
			}
			Date sevenDate = DateHelper.getDateByCalculateDays(today, Integer.valueOf(timeLimit)); // N天前
			for(int i=0;i<payOrders3.size();i++){
				if(ObjValid.isValid(payOrders3.get(i).getBestTime())){
					
					if(payOrders3.get(i).getBestTime().getTime()>sevenDate.getTime()){
						// 1 标示，退换货入口打开
						payOrders3.get(i).setReturnStatus("1");
					}else{
						// 0标示，退换货入口关闭
						payOrders3.get(i).setReturnStatus("0");
					}
				}
			}
			// END
			orderInfos3 = payOrderManagerDao.noReceiveOrderByUser(member.getLoginName(), null);
			// count3=3;
			//循环orderInfos3 判断商品是否下架   选择的属性是否存在 payOrderStatusMap orderInfoStatusMap
			for(int i=0;i<orderInfos3.size();i++){
				if(!"1".equals(orderInfos3.get(i).getProduct().getIsSale())){
					for(int j=0;j<payOrders3.size();j++){
						if(orderInfos3.get(i).getPayOrder().getOrderNum()==payOrders3.get(j).getOrderNum()){
							this.payOrderStatusMap.put(payOrders3.get(j).getOrderNum(), "2");//状态2 为该商品下架
						}
					}
				}
				if (orderInfos3.get(i).getOrderStatus().equals("0")) {
					Stock condition = new Stock();
					condition.setProduct(orderInfos3.get(i).getProduct());
					condition.setProductAttrIds(orderInfos3.get(i).getProductAttrIds());
					Stock result = stockManagerDao.findByProAttr(condition);
					if (ObjValid.isValid(result)) {
						double a = orderInfos3.get(i).getPrice();
						double b = result.getPrice();
						if (!(Double.doubleToLongBits(a)==Double.doubleToLongBits(b))) {
							this.payOrderStatusMap.put(orderInfos3.get(i).getOrderinfoNum(), "2");// 商品价格已更新
							this.orderInfoStatusMap.put(orderInfos3.get(i).getProductAttrIds(), "2");//商品价格已更新
							continue;
						}
					}
				}
				//选择的属性是否存在
				List<String> proAttrIdList = FormatStringUtil.splitBySign(orderInfos3.get(i).getProductAttrIds(), ",");
				boolean flag = true;
				String AttrStr = "";
				for (int k = 0; k < proAttrIdList.size(); k++) {
					ProductAttr productAttr = this.productAttrManagerDao.retrieveProductAttr(Long.parseLong(proAttrIdList.get(k)));
					if(!ObjValid.isValid(productAttr)){
						this.orderInfoStatusMap.put(orderInfos3.get(i).getProductAttrIds(), "2");//状态2为该商品属性不存在
						for(int j=0;j<payOrders3.size();j++){
							if(orderInfos3.get(i).getPayOrder().getOrderNum()==payOrders3.get(j).getOrderNum()){
								this.payOrderStatusMap.put(payOrders3.get(j).getOrderNum(), "2");//状态2为该商品属性不存在
							}
						}
						flag = false;
					}else{
						AttrStr = AttrStr + productAttr.getAttrValue()+"   ";
					}
				}
				if(flag){
					orderInfoAttrStrMap.put(orderInfos3.get(i).getProductAttrIds(), AttrStr);
				}
			}
			

		} catch (Exception ex) {
			this.addActionError(ex.getMessage());
			ex.printStackTrace();
			addActionMessage("Delete fail!");
		}
		return "allPayOrder";
	}

	/**
	 * 用户确认收货
	 * 
	 * @return
	 */
	public String editReceiveShippingType() {
		try {
			message = payOrderManagerDao.editReceiveShippingType(payOrder);
			addActionMessage("Delete successfully!");
		} catch (Exception ex) {
			this.addActionError(ex.getMessage());
			addActionMessage("Delete fail!");
		}
		return "editShipping";
	}

	/**
	 * 用户申请退货(添加到退货表)
	 * 
	 * @return
	 */
	public String applyDrawBackByUser() {
		try {
			productDrawbackManagerDao.applyDrawBack(productDrawback, orderInfo);
			noReceiveOrderByUser();
			addActionMessage("用户申请退货successfully!");
		} catch (Exception ex) {
			this.addActionError(ex.getMessage());
			addActionMessage("用户申请退货 fail!");
		}
		return "applyDrawBack";
	}

	/**
	 * 查询退货地址
	 * 
	 * @return
	 */
	public String queryAddressByOrderInfoNum() {
		try {
			// message=productDrawbackManagerDao.DrawAddressByOrderInfoNum(productDrawback).getSendAddress();
			message = parameterSetManagerDao.retrieveParameterSet().getSellerAddress();
			addActionMessage("用户根据清单号查询退货地址successfully!");
		} catch (Exception ex) {
			this.addActionError(ex.getMessage());
			addActionMessage("用户根据清单号查询退货地址 fail!");
		}
		return "drawAddress";
	}

	/**
	 * 添加积分信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String newIntergralInfo() throws Exception {
		try {
			intergralInfo = new IntergralInfo();
			Member member = getMember();
			intergralInfo.setMemberId(memberManagerDao.retrieveMember(member.getId()));
			Double intergralDouble = intergralInfoManagerDao.addIntergralInfo(intergralInfo);
			memberManagerDao.editIntergralByLoginName(memberManagerDao.retrieveMember(member.getId()), intergralDouble); // 修改用户的总积分
			addActionMessage("New successfully!");
		} catch (Exception ex) {
			this.addActionError(ex.getMessage());
			addActionMessage("New fail!");
		}
		return "viewIntergra";
	}

	/**
	 * 用户查询自己的积分记录
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String queryAllIntergralByUser() throws Exception {
		try {
			super.init();
			count3 = 0;
			Member member = getMember();
			intergralInfos = intergralInfoManagerDao.retrieveAllIntergralByUser(member, pageNo, pageSize).getResultList();
			count1 = intergralInfoManagerDao.queryTotalInterGralInfo(member);
		} catch (Exception ex) {
			addActionMessage("Load page error!");
		}
		return "viewIntergra";
	}

	/**
	 * 用户查询自己的余额明细
	 * 
	 * @return
	 * @throws Exception
	 */
	public String queryAllRechargeByUser() throws Exception {
		try {
			super.init();
			count3 = 1;
			Member member = getMember();
			rechargeInfos = rechargeInfoManagerDao.retrieveAllRechargeInfos(pageNo, pageSize, member).getResultList();
			cashDouble = rechargeInfoManagerDao.queryTotalCommission(member);
		} catch (Exception ex) {
			addActionMessage("Load page error!");
		}
		return "viewIntergra";
	}

	/**
	 * 用户查询自己的提现明细
	 * 
	 * @return
	 * @throws Exception
	 */
	public String queryAllDrawCashByUser() throws Exception {
		try {
			//super.init();
			count3 = 2;
			Member member = getMember();
			cashInfos = commissionInfoManagerDao.queryCashInfosByUser(member, pageNo, pageSize).getResultList();
			cashDouble = commissionInfoManagerDao.queryTotalCashInfo(member);
		} catch (Exception ex) {
			addActionMessage("Load page error!");
		}
		return "viewIntergra";
	}

	/**
	 * 跳转到会员特权介绍
	 * 
	 * @return
	 * @throws Exception
	 */
	public String newVipPaticularIntro() throws Exception {
		try {
			super.init();
		} catch (Exception ex) {
			addActionMessage("Load page error!");
		}
		return "toVipPaticularIntro";
	}

	/**
	 * 查询1级客户
	 * 
	 * @return
	 */
	public String queryMemberFirstLevel() {
		this.level = 1;
		return this.queryMemberCosTomer();
	}

	/**
	 * 查询2级客户
	 * 
	 * @return
	 */
	public String queryMemberSecondLevel() {
		this.level = 2;
		return this.queryMemberCosTomer();
	}

	/**
	 * 查询3级客户
	 * 
	 * @return
	 */
	public String queryMemberThirdLevel() {
		this.level = 3;
		return this.queryMemberCosTomer();
	}

	/**
	 * 我的代理
	 * 
	 * @return
	 */
	public String queryMyAgent() {
		Member member = getMember();
		Date begin = member.getAgentTime();
		//该代理商任职期间内的订单
		this.pager = this.payOrderManagerDao.findMyAgentOrder(member, begin, new Date(), 10, pageNo);
		this.payOrders1 = this.pager.getResultList();
		//该代理商所有下级
		this.teamNum = this.payOrderManagerDao.countMyTeamByAgent(member);
		//该代理商所有业绩
		this.sum = this.payOrderManagerDao.findAgentWork(member, member.getAgentTime(), new Date(), -1, -1);// 计算上一次结算到今天的代理绩效
		//该代理商所有总提成
		this.bonus = this.commissionInfoManagerDao.findAgentCommissionInfo(member);
		//代理商提成设置
		List<ParameterSet> list = this.parameterSetManagerDao.retrieveAllParameterSets();
		ParameterSet ps = list.get(0);
		if(ObjValid.isValid(ps.getAgentBonusSet())){
			String bonusRate = ps.getAgentBonusSet();
			this.rateSecond = Double.valueOf(bonusRate);
		}
		
		return "myAgent"; 
	}

	/**
	 * 我的客户 ---改成我的团队了
	 */
	@SuppressWarnings("unchecked")
	public String queryMemberCosTomer() {
		try {
			//super.init();
			super.findStandBy();
			this.tabNum = 1;
			Member member = getMember();
			// 我的客户
			// members=memberManagerDao.queryMemberFather(member,count1,pageNo,pageSize).getResultList();
			// members=memberManagerDao.queryUnionMemberFather(member, pageNo,
			// pageSize).getResultList();
			this.pager = memberManagerDao.queryMemberThreeCosTomerPage(member, pageNo, 10, this.level);
			members = this.pager.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "my-team";
	}

	/**
	 * 分销订单
	 */
	@SuppressWarnings("unchecked")
	public String queryMemberShareOrder() {
		try {
			super.init();
			Member member = getMember();
			// 分销订单
			// orderInfos1=payOrderManagerDao.noPayOrderByUser(loginName);
			ParameterSet parameterSet = (ParameterSet) this.parameterSetManagerDao.retrieveAllParameterSets().get(0);
			List<String> rates = FormatStringUtil.splitBySign(parameterSet.getSaleRoyaltyRate(), ",");
			List<Double> rateList = new ArrayList<Double>();
			for (int i = 0; i < rates.size(); i++) {
				rateList.add(Double.parseDouble(rates.get(i)));
			}
			this.payOrderVoList = payOrderManagerDao.sharePayOrderByFather(rateList, member, pageNo, pageSize).getResultList();
			// cashDouble=Double.valueOf(String.valueOf(parameterSetManagerDao.queryParameter().get(0)));
			// //获取第一级客户的佣金比例
			this.tabNum = 2;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "viewFather";
	}

	/**
	 * 我的佣金
	 */
	public String queryMemberCommission() {
		try {
			//super.init();
			super.findStandBy();
			// 我的佣金
			Member member = getMember();
			//commissionInfos = commissionInfoManagerDao.queryAllCommissionByProducter(pageNo, pageSize, member).getResultList();
			commissionInfos = commissionInfoManagerDao.queryAllCommissionByMember(pageNo, pageSize, member).getResultList();
			this.tabNum = 3;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "myHongBao";
	}

	/**
	 * 我的分销的商品
	 */
	@SuppressWarnings("unchecked")
	public String queryMemberShareProduct() {
		try {
			super.init();
			// 我的分销产品
			count2 = 20;
			count3 = 1;
			Member member = getMember();
			orderInfos1 = payOrderManagerDao.shareOrderInfoByFather(member, pageNo, pageSize).getResultList();
			cashDouble = Double.valueOf(String.valueOf(parameterSetManagerDao.queryParameter().get(0))); // 获取第一级客户的佣金比例
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "viewFather";
	}

	/**
	 * 申请提现
	 */
	public String applyDrawCash() {
		try {
			//super.init();
			count2 = 20;
			count3 = 2;
			Member member = getMember();
			member = memberManagerDao.retrieveMember(member.getId()); // 重新查询用户资料，防止没有显示实时资料信息
			//实时去查会员总佣金，该会员现在的佣金 = 总佣金 - 已提现佣金    allBonus为总佣金，bonus为现有佣金   
			Double allBonus = commissionInfoManagerDao.findMemberBonus(member);
			if(ObjValid.isValid(member.getSendCommission())){
				bonus = allBonus - member.getSendCommission();
			}else{
				bonus = allBonus;
			}
			// 查询用户能提现的金额 --- moneyToSend()---现在可提现金额
			parameterSet = this.payOrderManagerDao.findParameterSet();
			double moneyCanSend = this.memberManagerDao.queryMoneyToSend(member,parameterSet);// 查询总可提现金额
			if (!ObjValid.isValid(member.getSendCommission()) || member.getSendCommission().equals(0.0)) {
				moneyToSend = moneyCanSend;// 现在可提现金额 = 总可提现金额;当未提现过
			} else {
				moneyToSend = moneyCanSend - member.getSendCommission();// 现在可提现金额
																		// =
																		// 总可提现金额-已提现金额
			}
			// 获取默认的银行卡信息
			bankCardList = new ArrayList<BankCard>();
			bankCardList = this.bankCardManagerDao.findByMember(member);
			if (ObjValid.isValid(bankCardList)) {
				bankCard = bankCardList.get(0);
			} else {
				// 标示该用户并未绑定任何银行卡
				flag = "0";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "viewFather";
	}

	/**
	 * 跳转会员佣金提现信息显示
	 * 
	 * @return
	 */
	public String newDrawCommission() {
		try {
			//super.init();
			Member member = getMember();
			member = memberManagerDao.retrieveMember(member.getId());
			predictTime = commissionInfoManagerDao.predictTiem();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "toSureCash";
	}

	/**
	 * 改跳转到成功提现的页面-----防止刷新重复提交表单导致无限提现
	 * */
	public String commissionSuccess() {
		cashInfo = this.memberManagerDao.findByCashInfoId(id);
		Member member = getMember();
		Date today = new Date();// 今天
		Date endDate = DateHelper.getDateByCalculateDays(today, 7);
		predictTime =  DateHelper.dataToString(endDate, "yyyy-MM-dd HH:mm:ss");
		return "toNewSuccess";
	}

	/**
	 * 确认佣金提现
	 * 
	 * @return
	 */
	public String newDrawCommissionSuccess() {
		try {
			//super.init();
			Member member = getMember();
			bankCard = this.bankCardManagerDao.retrieveBankCard(bankCard.getId());
			cashInfo.setId(null);
			cashInfo.setBankCard(bankCard);

			// 查询用户能提现的金额 --- moneyToSend---现在可提现金额
			Member nowMember = this.memberManagerDao.retrieveMember(member.getId());
			parameterSet = this.payOrderManagerDao.findParameterSet();
			double moneyCanSend = this.memberManagerDao.queryMoneyToSend(nowMember,parameterSet);// 查询总可提现金额
			if (!ObjValid.isValid(nowMember.getSendCommission()) || nowMember.getSendCommission().equals(0.0)) {
				moneyToSend = moneyCanSend;// 现在可提现金额 = 总可提现金额;当未提现过
			} else {
				moneyToSend = moneyCanSend - nowMember.getSendCommission();// 现在可提现金额=总可提现金额-已提现金额
			}
			// 提交申请再一次判断 申请金额是否大于可提现金额 如果大于可提现金额，可能重复提交申请，跳转到提现记录页面
			if (cashInfo.getDrawAmount() > moneyToSend) {
				return "queryCashRecord";
			}
			message = commissionInfoManagerDao.SureDrawCommission(cashInfo, member);
			member = memberManagerDao.retrieveMember(member.getId());
			id = cashInfo.getId();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "toCashSuccess";
	}

	/**
	 * 跳转到绑定手机号升级会员界面(还不是会员时跳转)
	 * 
	 * @return
	 */
	public String newBinkPhoneUpgrade() {
		try {
			super.init();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "toBinkPhone";
	}

	/**
	 * 跳转到会员升级界面(已经是会员时跳转)
	 * 
	 * @return
	 */
	public String newUpgrade() {
		try {
			List<ParameterSet> parameterSetList = this.parameterSetManagerDao.retrieveAllParameterSets();
			if (ObjValid.isValid(parameterSetList)) {
				this.parameterSet = parameterSetList.get(0);
			}
			super.init();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "toUpgrade";
	}

	/**
	 * 根据充值进行会员升级
	 * 
	 * @return
	 */
	public String editUpgrade() {
		try {
			super.init();
			memberManagerDao.editRankById(getMember());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 跳转到会员充值余额界面
	 * 
	 * @return
	 */
	public String newRecharge() {
		try {
			super.init();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "toRecharge";
	}

	/**
	 * 根据充值数目修改会员余额
	 * 
	 * @return
	 */
	public String editRecharge() {
		try {
			super.init();
			Member member = getMember();
			rechargeInfoManagerDao.addRechargeInfo(rechargeInfo);
			memberManagerDao.editRechargeByLoginName(memberManagerDao.retrieveMember(member.getId()),
				rechargeInfo.getRechargeAmount());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 跳转到会员我的红包
	 * 
	 * @return
	 */
	public String newPacket() {
		try {
			super.init();
			// 未使用的红包
			Member member = getMember();
			memberBonus1 = memberBonusManagerDao.queryBonus(member, "1");
			// 已经使用的红包
			memberBonus2 = memberBonusManagerDao.queryBonus(member, "0");
			// 已过期的红包
			memberBonus3 = memberBonusManagerDao.queryOverdueBonus(member);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "toPacket";
	}

	/**
	 * 跳转到会员我的卡券
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String newTicket() {
		try {
			super.init();
			memberTickets = ticketInfoManagerDao.queryTicketByMember(getMember(), pageNo, pageSize, count1).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "toTicket";
	}

	/**
	 * 初始化会员特权（会员特权开始）
	 */
	public String initMemberPrivilege() {
		try {
			super.init();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "toPaticular";
	}

	/**
	 * 会员签到
	 * 
	 * @return
	 */
	public String memberReport() {
		try {
			super.init();
			message = this.memberManagerDao.memberEarnIntegral("签到", getMember());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "toReport";
	}

	/**
	 * 常见问题
	 * 
	 * @return
	 */
	public String commonProblem() {
		try {
			super.init();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "toProblem";
	}
	
	/**
	 * 做重定向到二维码生成页面并且附带 fatherId qrcodeFatherId
	 * @throws IOException 
	 * */
	public void generateQrcode() throws IOException{
		HttpServletRequest request = getRequest();
		Member member = getMember();
		if (ObjValid.isValid(member)) {
			this.fatherId = member.getId();
		}
		//判断页面是否有传qrcodeFatherId参数过来
		String qrcodeId = (String) request.getParameter("qrcodeFatherId");
		if(ObjValid.isValid(qrcodeFatherId)){
			this.qrcodeFatherId = qrcodeId;
		}
		HttpServletResponse response = ServletActionContext.getResponse();
	    response.sendRedirect(Constants.WEBSITE_URL + "/epMemberCenterAction!createQrcode.action?fatherId="+fatherId+"&qrcodeFatherId="+qrcodeFatherId);		
	}

	/**
	 * 生成二维码
	 * 
	 * @throws Exception
	 */
	public String createQrcode() throws Exception {
		log.info("生成会员专属二维码方法开始...");
		HttpServletRequest request = getRequest();
		qrcodeFatherId = (String) request.getParameter("qrcodeFatherId");
		log.info("qrcodeFatherId="+qrcodeFatherId);
		if (ObjValid.isValid(qrcodeFatherId)&&!"null".equals(qrcodeFatherId)) {
			memberInfo = memberManagerDao.retrieveMember(Long.valueOf(qrcodeFatherId));
			qrCodeName = memberInfo.getQrcodeName();
		}else {
			memberInfo = super.getMember();
			if (ObjValid.isValid(memberInfo)
					&& ObjValid.isValid(memberInfo.getId(), memberInfo.getNickname(),
						memberInfo.getImagePath())) {
					if (ObjValid.isValid(memberInfo.getQrcodeName())) {
						qrCodeName = memberInfo.getQrcodeName();
						log.info("会员专属二维码已存在...");
					} else {
						log.info("开始调用二维码ticket api...");
						String token = TokenManager.getToken(Constants.APPID);
						QrcodeTicket qrcodeTicket = QrcodeAPI.qrcodeCreateFinal(token, memberInfo.getId().intValue()); // 生成永久二维码
						log.info(qrcodeTicket.getUrl()+"--------------------------------------------"+qrcodeTicket.getTicket()+"--------------------------------"+qrcodeTicket.getErrmsg());
						if (ObjValid.isValid(qrcodeTicket.getTicket())) {
							log.info("api返回ticket结果有效...");
							File backgroundImg = new File(Constants.QR_TEMP_PATH,Constants.QR_BACKGROUND_IMG_NAME);
							URL wxQRImg = new URL("https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=" + qrcodeTicket.getTicket());
							URL wxImg = new URL(memberInfo.getImagePath());
							log.info("调用生成二维码api...");
							qrCodeName = QrcodeAPI.mergeImage(backgroundImg,
									wxQRImg, wxImg,memberInfo.getNickname());
							log.info("二维码api返回结果为："+qrCodeName);
							memberInfo = memberManagerDao
									.retrieveMember(memberInfo.getId());
							memberInfo.setQrcodeName(qrCodeName);
							memberManagerDao.updateMember(memberInfo);
						}
					}
					
					//TODO: 解决分享二维码页面bug
					//获取分享链接
					getWechatShareUrl();
					if (!ObjValid.isValid(qrcodeFatherId) || qrcodeFatherId.equals("null")) {
						log.info("qrcodeFatherId不存在");
						shareUrl = Constants.WEBSITE_URL+"/epMemberCenterAction!generateQrcode.action?qrcodeFatherId="+memberInfo.getId();
					}
					log.info("二维码分享："+shareUrl);
//				}

			}
		}
		return "generateQrcode";
	}
	
	/**
	 * 测试消息推送
	 * @return
	 */
	/*public void test(){
		TemplateMessageResult templateMessageResult = MessageManager.sendNewMemberTemplateMessage("一级", "552", "小强", DateHelper.dataToString(new Date(), "yyyy-MM-dd HH:mm:ss"), "ojyFcwM56tJl82mS4kmC8OEF_yF8", "0");
		System.out.println(templateMessageResult.getErrcode()+templateMessageResult.getErrmsg());
	}*/
	
	// set、get

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public int getCount1() {
		return count1;
	}

	public void setCount1(int count1) {
		this.count1 = count1;
	}

	public long getCount2() {
		return count2;
	}

	public void setCount2(long count2) {
		this.count2 = count2;
	}

	public PayOrder getPayOrder() {
		return payOrder;
	}

	public void setPayOrder(PayOrder payOrder) {
		this.payOrder = payOrder;
	}

	public List<PayOrder> getPayOrders1() {
		return payOrders1;
	}

	public void setPayOrders1(List<PayOrder> payOrders1) {
		this.payOrders1 = payOrders1;
	}

	public List<OrderInfo> getOrderInfos1() {
		return orderInfos1;
	}

	public void setOrderInfos1(List<OrderInfo> orderInfos1) {
		this.orderInfos1 = orderInfos1;
	}

	public List<PayOrder> getPayOrders2() {
		return payOrders2;
	}

	public void setPayOrders2(List<PayOrder> payOrders2) {
		this.payOrders2 = payOrders2;
	}

	public List<OrderInfo> getOrderInfos2() {
		return orderInfos2;
	}

	public void setOrderInfos2(List<OrderInfo> orderInfos2) {
		this.orderInfos2 = orderInfos2;
	}

	public List<PayOrder> getPayOrders3() {
		return payOrders3;
	}

	public void setPayOrders3(List<PayOrder> payOrders3) {
		this.payOrders3 = payOrders3;
	}

	public List<OrderInfo> getOrderInfos3() {
		return orderInfos3;
	}

	public void setOrderInfos3(List<OrderInfo> orderInfos3) {
		this.orderInfos3 = orderInfos3;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<Address> getAddressList() {
		return addressList;
	}

	public void setAddressList(List<Address> addressList) {
		this.addressList = addressList;
	}

	public Long getAddressId() {
		return addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<Province> getProvinceList() {
		return provinceList;
	}

	public void setProvinceList(List<Province> provinceList) {
		this.provinceList = provinceList;
	}

	public List<IntergralInfo> getIntergralInfos() {
		return intergralInfos;
	}

	public void setIntergralInfos(List<IntergralInfo> intergralInfos) {
		this.intergralInfos = intergralInfos;
	}

	public List<Member> getMembers() {
		return members;
	}

	public void setMembers(List<Member> members) {
		this.members = members;
	}

	public IntergralInfo getIntergralInfo() {
		return intergralInfo;
	}

	public void setIntergralInfo(IntergralInfo intergralInfo) {
		this.intergralInfo = intergralInfo;
	}

	public ProductDrawback getProductDrawback() {
		return productDrawback;
	}

	public void setProductDrawback(ProductDrawback productDrawback) {
		this.productDrawback = productDrawback;
	}

	public Long getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Long provinceId) {
		this.provinceId = provinceId;
	}

	public Long getCityId() {
		return cityId;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}

	public Long getAreaId() {
		return areaId;
	}

	public void setAreaId(Long areaId) {
		this.areaId = areaId;
	}

	public List<CommissionInfo> getCommissionInfos() {
		return commissionInfos;
	}

	public void setCommissionInfos(List<CommissionInfo> commissionInfos) {
		this.commissionInfos = commissionInfos;
	}

	public ReportInfo getReportInfo() {
		return reportInfo;
	}

	public void setReportInfo(ReportInfo reportInfo) {
		this.reportInfo = reportInfo;
	}

	public CommissionInfo getCommissionInfo() {
		return commissionInfo;
	}

	public void setCommissionInfo(CommissionInfo commissionInfo) {
		this.commissionInfo = commissionInfo;
	}

	public List<ReportInfo> getReportInfos() {
		return reportInfos;
	}

	public void setReportInfos(List<ReportInfo> reportInfos) {
		this.reportInfos = reportInfos;
	}

	public MemberBonus getMemberBonu() {
		return memberBonu;
	}

	public void setMemberBonu(MemberBonus memberBonu) {
		this.memberBonu = memberBonu;
	}

	public List<MemberBonus> getMemberBonus1() {
		return memberBonus1;
	}

	public void setMemberBonus1(List<MemberBonus> memberBonus1) {
		this.memberBonus1 = memberBonus1;
	}

	public List<MemberBonus> getMemberBonus2() {
		return memberBonus2;
	}

	public void setMemberBonus2(List<MemberBonus> memberBonus2) {
		this.memberBonus2 = memberBonus2;
	}

	public List<MemberBonus> getMemberBonus3() {
		return memberBonus3;
	}

	public void setMemberBonus3(List<MemberBonus> memberBonus3) {
		this.memberBonus3 = memberBonus3;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public RechargeInfo getRechargeInfo() {
		return rechargeInfo;
	}

	public void setRechargeInfo(RechargeInfo rechargeInfo) {
		this.rechargeInfo = rechargeInfo;
	}

	public List<RechargeInfo> getRechargeInfos() {
		return rechargeInfos;
	}

	public void setRechargeInfos(List<RechargeInfo> rechargeInfos) {
		this.rechargeInfos = rechargeInfos;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public OrderInfo getOrderInfo() {
		return orderInfo;
	}

	public void setOrderInfo(OrderInfo orderInfo) {
		this.orderInfo = orderInfo;
	}

	public int getCount3() {
		return count3;
	}

	public void setCount3(int count3) {
		this.count3 = count3;
	}

	public Evaluate getEvaluate() {
		return evaluate;
	}

	public void setEvaluate(Evaluate evaluate) {
		this.evaluate = evaluate;
	}

	public Long getOrderInfoId() {
		return orderInfoId;
	}

	public void setOrderInfoId(Long orderInfoId) {
		this.orderInfoId = orderInfoId;
	}

	public CashInfo getCashInfo() {
		return cashInfo;
	}

	public void setCashInfo(CashInfo cashInfo) {
		this.cashInfo = cashInfo;
	}

	public String getPredictTime() {
		return predictTime;
	}

	public void setPredictTime(String predictTime) {
		this.predictTime = predictTime;
	}

	public String getOldMobile() {
		return oldMobile;
	}

	public void setOldMobile(String oldMobile) {
		this.oldMobile = oldMobile;
	}

	public List<CashInfo> getCashInfos() {
		return cashInfos;
	}

	public void setCashInfos(List<CashInfo> cashInfos) {
		this.cashInfos = cashInfos;
	}

	public Map<String, List<Member>> getMemberMap() {
		return memberMap;
	}

	public void setMemberMap(Map<String, List<Member>> memberMap) {
		this.memberMap = memberMap;
	}

	public Map<String, List<PayOrder>> getPayOrderMap() {
		return payOrderMap;
	}

	public void setPayOrderMap(Map<String, List<PayOrder>> payOrderMap) {
		this.payOrderMap = payOrderMap;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getPayPassword() {
		return payPassword;
	}

	public void setPayPassword(String payPassword) {
		this.payPassword = payPassword;
	}

	public String getProAttrIds() {
		return proAttrIds;
	}

	public void setProAttrIds(String proAttrIds) {
		this.proAttrIds = proAttrIds;
	}

	public String getProductFlag() {
		return productFlag;
	}

	public void setProductFlag(String productFlag) {
		this.productFlag = productFlag;
	}

	public Double getCashDouble() {
		return cashDouble;
	}

	public void setCashDouble(Double cashDouble) {
		this.cashDouble = cashDouble;
	}

	public ParameterSet getParameterSet() {
		return parameterSet;
	}

	public void setParameterSet(ParameterSet parameterSet) {
		this.parameterSet = parameterSet;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<MemberTicket> getMemberTickets() {
		return memberTickets;
	}

	public void setMemberTickets(List<MemberTicket> memberTickets) {
		this.memberTickets = memberTickets;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getTabNum() {
		return tabNum;
	}

	public void setTabNum(int tabNum) {
		this.tabNum = tabNum;
	}

	public Double getRateSecond() {
		return rateSecond;
	}

	public void setRateSecond(Double rateSecond) {
		this.rateSecond = rateSecond;
	}

	public List<PayOrderVo> getPayOrderVoList() {
		return payOrderVoList;
	}

	public void setPayOrderVoList(List<PayOrderVo> payOrderVoList) {
		this.payOrderVoList = payOrderVoList;
	}

	public int getTeamNum() {
		return teamNum;
	}

	public void setTeamNum(int teamNum) {
		this.teamNum = teamNum;
	}

	public Double getSum() {
		return sum;
	}

	public void setSum(Double sum) {
		this.sum = sum;
	}

	public String getQrCodeName() {
		return qrCodeName;
	}

	public void setQrCodeName(String qrCodeName) {
		this.qrCodeName = qrCodeName;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public Double getBonus() {
		return bonus;
	}

	public void setBonus(Double bonus) {
		this.bonus = bonus;
	}

	public String getProductFlag2() {
		return productFlag2;
	}

	public void setProductFlag2(String productFlag2) {
		this.productFlag2 = productFlag2;
	}

	public BankCard getBankCard() {
		return bankCard;
	}

	public void setBankCard(BankCard bankCard) {
		this.bankCard = bankCard;
	}

	public String getBankCardFlag() {
		return bankCardFlag;
	}

	public void setBankCardFlag(String bankCardFlag) {
		this.bankCardFlag = bankCardFlag;
	}

	public String getBankCardNum() {
		return bankCardNum;
	}

	public void setBankCardNum(String bankCardNum) {
		this.bankCardNum = bankCardNum;
	}

	public String getBankCardMember() {
		return bankCardMember;
	}

	public void setBankCardMember(String bankCardMember) {
		this.bankCardMember = bankCardMember;
	}

	public String getBankCardAddress() {
		return bankCardAddress;
	}

	public void setBankCardAddress(String bankCardAddress) {
		this.bankCardAddress = bankCardAddress;
	}

	public String getBankCardPoint() {
		return bankCardPoint;
	}

	public void setBankCardPoint(String bankCardPoint) {
		this.bankCardPoint = bankCardPoint;
	}

	public String getProvinceIdStr() {
		return provinceIdStr;
	}

	public void setProvinceIdStr(String provinceIdStr) {
		this.provinceIdStr = provinceIdStr;
	}

	public String getCityIdStr() {
		return cityIdStr;
	}

	public void setCityIdStr(String cityIdStr) {
		this.cityIdStr = cityIdStr;
	}

	public List<BankCard> getLb() {
		return bankCardList;
	}

	public void setLb(List<BankCard> bankCardList) {
		this.bankCardList = bankCardList;
	}

	public List<BankCard> getBankCardList() {
		return bankCardList;
	}

	public void setBankCardList(List<BankCard> bankCardList) {
		this.bankCardList = bankCardList;
	}

	public Long getPayOrderId() {
		return payOrderId;
	}

	public void setPayOrderId(Long payOrderId) {
		this.payOrderId = payOrderId;
	}

	public Double getMoneyToSend() {
		return moneyToSend;
	}

	public void setMoneyToSend(Double moneyToSend) {
		this.moneyToSend = moneyToSend;
	}

	public Member getMemberInfo() {
		return memberInfo;
	}

	public void setMemberInfo(Member memberInfo) {
		this.memberInfo = memberInfo;
	}

	public static void main(String[] args) throws IOException {
		/*
		 * Token token = TokenAPI.token(Constants.APPID,Constants.APPSECRET);
		 * String token1 = token.getAccess_token();*/
		 //QrcodeTicket qrcodeTicket = QrcodeAPI.qrcodeCreateFinal("rxUu1BnqzeDug-rEpzYCg77dYaySxAN_rc3clziZ4pfqOIeXPIzfkoTEd8WxVAGsAJBPxFhQdzXxdUlNflLgWhya98CEWjMUCgUAe3rwrIc", 2); // 生成用久二维码
		// log.info(qrcodeTicket.getTicket());
		// System.out.println(qrcodeTicket.getTicket());
		 
		/*String ticket = "gQEE8DoAAAAAAAAAASxodHRwOi8vd2VpeGluLnFxLmNvbS9xL01VelhfcmJtREtOMTlhc053MkJOAAIELjEeVgMEAAAAAA==";

		File backgroundImg = new File(Constants.QR_TEMP_PATH, Constants.QR_BACKGROUND_IMG_NAME);
		
		URL wxQRImg = new URL(
			"https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=gQER8ToAAAAAAAAAASxodHRwOi8vd2VpeGluLnFxLmNvbS9xL19qa2RFY0htRU5kcFUtekFaQldsAAIEqor7VQMEAAAAAA==");
		URL wxImg = new URL(
			"http://wx.qlogo.cn/mmopen/I1YzhXxW8YBzL622Ugw6YMZGNbtUslRhVAASGrjOlMDBqNzmjIlEUAoKFOnYoGkEjgQd1gf7b5ymGXoWF70JtpURwPMIe13Y/0");
		String qrCodeName = QrcodeAPI.mergeImage(backgroundImg, wxQRImg, wxImg, "焯民");
		System.out.println(qrCodeName);*/

		
	}

	public String getQrcodeFatherId() {
		return qrcodeFatherId;
	}

	public void setQrcodeFatherId(String qrcodeFatherId) {
		this.qrcodeFatherId = qrcodeFatherId;
	}

	public Map<String, String> getPayOrderStatusMap() {
		return payOrderStatusMap;
	}

	public void setPayOrderStatusMap(Map<String, String> payOrderStatusMap) {
		this.payOrderStatusMap = payOrderStatusMap;
	}

	public Map<String, String> getOrderInfoStatusMap() {
		return orderInfoStatusMap;
	}

	public void setOrderInfoStatusMap(Map<String, String> orderInfoStatusMap) {
		this.orderInfoStatusMap = orderInfoStatusMap;
	}

	public Map<String, String> getOrderInfoAttrStrMap() {
		return orderInfoAttrStrMap;
	}

	public void setOrderInfoAttrStrMap(Map<String, String> orderInfoAttrStrMap) {
		this.orderInfoAttrStrMap = orderInfoAttrStrMap;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getOtherReason() {
		return otherReason;
	}
	public void setOtherReason(String otherReason) {
		this.otherReason = otherReason;
	}
	public String getPayOrderAmount() {
		return payOrderAmount;
	}
	public void setPayOrderAmount(String payOrderAmount) {
		this.payOrderAmount = payOrderAmount;
	}


	public List<String> getAgentOrderList() {
		return agentOrderList;
	}


	public void setAgentOrderList(List<String> agentOrderList) {
		this.agentOrderList = agentOrderList;
	}

	public List<City> getCities() {
		return cities;
	}

	public void setCities(List<City> cities) {
		this.cities = cities;
	}

	public List<Area> getAreas() {
		return areas;
	}

	public void setAreas(List<Area> areas) {
		this.areas = areas;
	}

}
