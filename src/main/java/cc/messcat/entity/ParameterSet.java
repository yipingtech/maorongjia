package cc.messcat.entity;

/**
 * ParameterSet entity. @author MyEclipse Persistence Tools
 */

public class ParameterSet implements java.io.Serializable {

	// Fields

	private Long id;
	private Double conditionSend;
	private String agentBonusSet;
	private String saleRoyaltyRate;
	private String timeLimit;
	private String consigneeTime;
	private Long redPacketNum;
	private Long discountCouponNum;
	private String sellerName;
	private String sellerPhone;
	private String sellerAddress;
	private Double vipMember;
	private Double svip;
	private Double diamondMember;
	private String topUpGifts;
	private Long inviteMemberNum;
	private String agentCompensate;
	private String agentAmount;

	// Property accessors

	public String getSellerPhone() {
		return sellerPhone;
	}

	public String getTimeLimit() {
		return timeLimit;
	}

	public void setTimeLimit(String timeLimit) {
		this.timeLimit = timeLimit;
	}

	public void setSellerPhone(String sellerPhone) {
		this.sellerPhone = sellerPhone;
	}

	public String getSellerName() {
		return sellerName;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}

	public String getAgentBonusSet() {
		return agentBonusSet;
	}

	public void setAgentBonusSet(String agentBonusSet) {
		this.agentBonusSet = agentBonusSet;
	}

	public Double getConditionSend() {
		return conditionSend;
	}

	public void setConditionSend(Double conditionSend) {
		this.conditionSend = conditionSend;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSaleRoyaltyRate() {
		return this.saleRoyaltyRate;
	}

	public void setSaleRoyaltyRate(String saleRoyaltyRate) {
		this.saleRoyaltyRate = saleRoyaltyRate;
	}

	public Long getRedPacketNum() {
		return this.redPacketNum;
	}

	public void setRedPacketNum(Long redPacketNum) {
		this.redPacketNum = redPacketNum;
	}

	public Long getDiscountCouponNum() {
		return this.discountCouponNum;
	}

	public void setDiscountCouponNum(Long discountCouponNum) {
		this.discountCouponNum = discountCouponNum;
	}

	public String getSellerAddress() {
		return this.sellerAddress;
	}

	public void setSellerAddress(String sellerAddress) {
		this.sellerAddress = sellerAddress;
	}

	public Double getVipMember() {
		return this.vipMember;
	}

	public void setVipMember(Double vipMember) {
		this.vipMember = vipMember;
	}

	public Double getSvip() {
		return this.svip;
	}

	public void setSvip(Double svip) {
		this.svip = svip;
	}

	public Double getDiamondMember() {
		return this.diamondMember;
	}

	public void setDiamondMember(Double diamondMember) {
		this.diamondMember = diamondMember;
	}

	public String getTopUpGifts() {
		return this.topUpGifts;
	}

	public void setTopUpGifts(String topUpGifts) {
		this.topUpGifts = topUpGifts;
	}

	public Long getInviteMemberNum() {
		return this.inviteMemberNum;
	}

	public void setInviteMemberNum(Long inviteMemberNum) {
		this.inviteMemberNum = inviteMemberNum;
	}

	public String getConsigneeTime() {
		return consigneeTime;
	}

	public void setConsigneeTime(String consigneeTime) {
		this.consigneeTime = consigneeTime;
	}

	public String getAgentCompensate() {
		return agentCompensate;
	}

	public void setAgentCompensate(String agentCompensate) {
		this.agentCompensate = agentCompensate;
	}

	public String getAgentAmount() {
		return agentAmount;
	}

	public void setAgentAmount(String agentAmount) {
		this.agentAmount = agentAmount;
	}

}