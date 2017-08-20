package cc.messcat.web.front;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import cc.messcat.entity.ActivityApply;
import cc.messcat.entity.ActivityInvite;
import cc.messcat.entity.Attribute;
import cc.messcat.entity.EnterpriseColumn;
import cc.messcat.entity.Evaluate;
import cc.messcat.entity.McProductInfo;
import cc.messcat.entity.Member;
import cc.messcat.entity.OrderInfo;
import cc.messcat.entity.ParameterSet;
import cc.messcat.entity.PayOrder;
import cc.messcat.entity.ProductAttr;
import cc.messcat.entity.ProductColumn;

import com.opensymphony.xwork2.ActionContext;

public class EpActivityAction extends FrontAction{

	private static final long serialVersionUID = 1L;
	Map session = (Map) ActionContext.getContext().getSession();
	private Member member=(Member) session.get("member");
	private int count;
	private int count1;
	private Long id;            //活动申请表的id
	private String frontNum;
	private String proAttrIds;
	private Long freeProductId;   //免费试穿商品id
	private long countEvaluate;
	private String message;
	
	private PayOrder payOrder;
	private OrderInfo orderInfo;
	private ProductColumn productColumn;
	private McProductInfo productInfo;
	private McProductInfo mcProductInfo;
	private ActivityInvite activityInvite;
	private ActivityApply activityApply;
	private EnterpriseColumn enterpriseColumn;
	private ParameterSet parameterSet;
	

	private List<ActivityApply> activityApplies;
	private List<ActivityInvite> activityInvites;
	private List<ProductColumn> productColumns;
	private List<EnterpriseColumn> enterpriseColumns;
	private List<Evaluate> evaluates;
	
	private LinkedHashMap<Attribute, List<ProductAttr>> attrMap;
	
	/**
	 * 查找免费试穿栏目中的商品(一个商品直接进去商品界面)
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String queryActivityColum() throws Exception {
		try {
			super.init();
			//免费试穿商品
			pager=activityInfoManagerDao.queryActivityColum(pageNo, pageSize, "APPLY_FREE");
			productColumns=pager.getResultList();
			if (productColumns.size()>1) {
				return "toProductDetail";    //跳转到栏目列表
			} else if(productColumns.size()==1){
				productInfo=productColumns.get(0).getMcProduct();
				enterpriseColumn=productColumns.get(0).getEnterpriseColumn();
				
				count = activityInfoManagerDao.queryApplyMember(null, productColumns.get(0)).size();                 //申请的人数	
				activityApplies = activityInfoManagerDao.queryApplyMember(member, productColumns.get(0));            //判断用户是否已经参与
				count1=activityApplies.size();
				if (count1!=0) {
					activityApply=activityApplies.get(0);
					activityInvites=activityInfoManagerDao.queryInviteFriend(member, null, productColumns.get(0));      //邀请的好友
				}				
				attrMap = mcProductInfoManagerDao.initProductDetail(productInfo, "1");                                   //商品属性和评论
				pager=evaluateManagerDao.queryEvaluates(pageNo, pageSize, productInfo.getId());
				evaluates=pager.getResultList();
				countEvaluate=pager.getRowCount();
				
				//需要邀请人数
				parameterSet=parameterSetManagerDao.retrieveParameterSet();
				//跳转到商品详情
				return "toProductDetail";  
			}
		} catch (Exception ex) {
			addActionMessage("Load page error!");
		}
		return null; //跳转到活动未启动
	}
	
	/**
	 * 点击参与试穿
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String applyFreeProduct() throws Exception {
		try {
			pager=activityInfoManagerDao.queryActivityColum(pageNo, pageSize, "APPLY_FREE");
			productColumns=pager.getResultList();
			activityInfoManagerDao.applyFreeProduct(member,productColumns.get(0));
		} catch (Exception ex) {
			addActionMessage("Load page error!");
		}
		return this.queryActivityColum();
	}
	
	/**
	 * 邀请好友试穿
	 * @return
	 * @throws Exception
	 */
	public String inviteFriend() throws Exception {
		try {
			super.init();
			activityInfoManagerDao.inviteFriend(activityInvite, productColumn);
		} catch (Exception ex) {
			addActionMessage("Load page error!");
		}
		return "viewMember";
	}
	
	/**
	 * 查看邀请的好友
	 * @return
	 * @throws Exception
	 */
	public String queryInviteFriend() throws Exception {
		try {
			super.init();
			this.queryActivityColum();
		} catch (Exception ex) {
			addActionMessage("Load page error!");
		}
		return "toQueryFriend";
	}
	
	
	/**
	 * 邀请足够好友之后下单试穿商品
	 * @return
	 * @throws Exception
	 */
	public String addFreeProductOrder() throws Exception {
		try {
			super.init();
			message=activityInfoManagerDao.addFreeProductOrder(member, payOrder, proAttrIds,freeProductId, "1",id);
		} catch (Exception ex) {
			addActionMessage("Load page error!");
		}
		return "toPaySuccess";
	}
	
	/**
	 * 初次绑定，赠送小礼品
	 * @return
	 */
	public String addSmallGiftOrder(){
		try {
			super.init();
			message=activityInfoManagerDao.addFreeProductOrder(member, payOrder, proAttrIds,freeProductId, "2",id);
		} catch (Exception ex) {
			addActionMessage("Load page error!");
		}
		return "toPaySuccess";
	}
	
	/**
	 * 跳转到送礼界面
	 * @return
	 * @throws Exception
	 */
	public String toGiveGifts() throws Exception {
		try {
			super.init();
			pager=activityInfoManagerDao.queryActivityColum(pageNo, pageSize, "VIP_GIFTS");
			productColumns=pager.getResultList();
		} catch (Exception ex) {
			addActionMessage("Load page error!");
		}
		return "toGiftsColum";
	}
	
	/**
	 * 绑定送礼
	 * @return
	 * @throws Exception
	 */
	public String toBingGift() throws Exception {
		try {
			super.init();
			pager=activityInfoManagerDao.queryActivityColum(pageNo, pageSize, "BINDING_GIFT");
			productColumns=pager.getResultList();
		} catch (Exception ex) {
			addActionMessage("Load page error!");
		}
		return "toBindColum";
	}
	
	/**
	 * 领取精美礼品详细页面
	 * @return
	 * @throws Exception 
	 */
	public String goDrawGift() throws Exception{
		super.init();
		this.mcProductInfo = this.mcProductInfoManagerDao.retrieveMcProductInfo(this.mcProductInfo.getId());
		this.attrMap = this.mcProductInfoManagerDao.initProductDetail(mcProductInfo, "1");
		return "drawGift";
	}
	/**
	 * 去送礼
	 * @return
	 * @throws Exception
	 */
	public String goGiveGifts() throws Exception {
		try {
			super.init();
			count=1;
			productInfo=mcProductInfoManagerDao.retrieveMcProductInfo(freeProductId);
			attrMap = mcProductInfoManagerDao.initProductDetail(productInfo, "1");
			pager=evaluateManagerDao.queryEvaluates(pageNo, pageSize, freeProductId);
			evaluates=pager.getResultList();
			countEvaluate=pager.getRowCount();
		} catch (Exception ex) {
			addActionMessage("Load page error!");
		}
		return "goGiftsColum";
	}

	public String getFrontNum() {
		return frontNum;
	}

	public void setFrontNum(String frontNum) {
		this.frontNum = frontNum;
	}

	public List<ProductColumn> getProductColumns() {
		return productColumns;
	}

	public void setProductColumns(List<ProductColumn> productColumns) {
		this.productColumns = productColumns;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public ProductColumn getProductColumn() {
		return productColumn;
	}

	public void setProductColumn(ProductColumn productColumn) {
		this.productColumn = productColumn;
	}


	public McProductInfo getProductInfo() {
		return productInfo;
	}

	public void setProductInfo(McProductInfo productInfo) {
		this.productInfo = productInfo;
	}

	public ActivityInvite getActivityInvite() {
		return activityInvite;
	}

	public void setActivityInvite(ActivityInvite activityInvite) {
		this.activityInvite = activityInvite;
	}

	public ActivityApply getActivityApply() {
		return activityApply;
	}

	public void setActivityApply(ActivityApply activityApply) {
		this.activityApply = activityApply;
	}

	public List<ActivityApply> getActivityApplies() {
		return activityApplies;
	}

	public void setActivityApplies(List<ActivityApply> activityApplies) {
		this.activityApplies = activityApplies;
	}

	public List<ActivityInvite> getActivityInvites() {
		return activityInvites;
	}

	public void setActivityInvites(List<ActivityInvite> activityInvites) {
		this.activityInvites = activityInvites;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public EnterpriseColumn getEnterpriseColumn() {
		return enterpriseColumn;
	}

	public void setEnterpriseColumn(EnterpriseColumn enterpriseColumn) {
		this.enterpriseColumn = enterpriseColumn;
	}

	public List<EnterpriseColumn> getEnterpriseColumns() {
		return enterpriseColumns;
	}

	public void setEnterpriseColumns(List<EnterpriseColumn> enterpriseColumns) {
		this.enterpriseColumns = enterpriseColumns;
	}

	public LinkedHashMap<Attribute, List<ProductAttr>> getAttrMap() {
		return attrMap;
	}

	public void setAttrMap(LinkedHashMap<Attribute, List<ProductAttr>> attrMap) {
		this.attrMap = attrMap;
	}

	public String getProAttrIds() {
		return proAttrIds;
	}

	public void setProAttrIds(String proAttrIds) {
		this.proAttrIds = proAttrIds;
	}

	public PayOrder getPayOrder() {
		return payOrder;
	}

	public void setPayOrder(PayOrder payOrder) {
		this.payOrder = payOrder;
	}

	public OrderInfo getOrderInfo() {
		return orderInfo;
	}

	public void setOrderInfo(OrderInfo orderInfo) {
		this.orderInfo = orderInfo;
	}

	public Long getFreeProductId() {
		return freeProductId;
	}

	public void setFreeProductId(Long freeProductId) {
		this.freeProductId = freeProductId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public McProductInfo getMcProductInfo() {
		return mcProductInfo;
	}

	public void setMcProductInfo(McProductInfo mcProductInfo) {
		this.mcProductInfo = mcProductInfo;
	}

	public long getCountEvaluate() {
		return countEvaluate;
	}

	public void setCountEvaluate(long countEvaluate) {
		this.countEvaluate = countEvaluate;
	}

	public List<Evaluate> getEvaluates() {
		return evaluates;
	}

	public void setEvaluates(List<Evaluate> evaluates) {
		this.evaluates = evaluates;
	}

	public int getCount1() {
		return count1;
	}

	public void setCount1(int count1) {
		this.count1 = count1;
	}

	public ParameterSet getParameterSet() {
		return parameterSet;
	}

	public void setParameterSet(ParameterSet parameterSet) {
		this.parameterSet = parameterSet;
	}


}
