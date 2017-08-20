package cc.messcat.entity;

import java.util.Date;
import java.util.Set;

/**
 * PayOrder entity. @author MyEclipse Persistence Tools
 */

public class PayOrder implements java.io.Serializable {

	// Fields

	private Long id;
	private String orderNum;
	private Member member;
	private String name;
	private String phone;
	private String fixPhone;
	private String pastalcode;
	private String address;
	private String comments;
	private Date createTime;
	private Date payTime;
	private Date shippingTime;
	private Date bestTime;
	private String shippingStatus;
	private Long shippingId;
	private String shippingName;
	private String invoiceNum;
	private Double shippingFee;
	private Long payId;
	private String payName;
	private Double productAmount;
	private Double integralMoney;
	private Double bonus;
	private String bonusId;
	private Double couponAmount;
	private String couponId;
	private Double orderAmount;
	private String payStatus;
	private String status;
	private String addressId;
	private ProductDrawback productDrawback;
	private Set<OrderInfo> orderInfos;
	
	//标示是否可以退换货，与数据库无关   0：否   1：是
	private String returnStatus = null;

	// Property accessors

	

	public String getAddressId() {
		return addressId;
	}

	public String getReturnStatus() {
		return returnStatus;
	}

	public void setReturnStatus(String returnStatus) {
		this.returnStatus = returnStatus;
	}

	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrderNum() {
		return this.orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}


	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFixPhone() {
		return this.fixPhone;
	}

	public void setFixPhone(String fixPhone) {
		this.fixPhone = fixPhone;
	}

	public String getPastalcode() {
		return this.pastalcode;
	}

	public void setPastalcode(String pastalcode) {
		this.pastalcode = pastalcode;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getPayTime() {
		return this.payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	public Date getShippingTime() {
		return this.shippingTime;
	}

	public void setShippingTime(Date shippingTime) {
		this.shippingTime = shippingTime;
	}

	public Date getBestTime() {
		return this.bestTime;
	}

	public void setBestTime(Date bestTime) {
		this.bestTime = bestTime;
	}

	public String getShippingStatus() {
		return this.shippingStatus;
	}

	public void setShippingStatus(String shippingStatus) {
		this.shippingStatus = shippingStatus;
	}

	public Long getShippingId() {
		return this.shippingId;
	}

	public void setShippingId(Long shippingId) {
		this.shippingId = shippingId;
	}

	public String getShippingName() {
		return this.shippingName;
	}

	public void setShippingName(String shippingName) {
		this.shippingName = shippingName;
	}

	public String getInvoiceNum() {
		return this.invoiceNum;
	}

	public void setInvoiceNum(String invoiceNum) {
		this.invoiceNum = invoiceNum;
	}

	public Double getShippingFee() {
		return this.shippingFee;
	}

	public void setShippingFee(Double shippingFee) {
		this.shippingFee = shippingFee;
	}

	public Long getPayId() {
		return this.payId;
	}

	public void setPayId(Long payId) {
		this.payId = payId;
	}

	public String getPayName() {
		return this.payName;
	}

	public void setPayName(String payName) {
		this.payName = payName;
	}

	public Double getProductAmount() {
		return this.productAmount;
	}

	public void setProductAmount(Double productAmount) {
		this.productAmount = productAmount;
	}

	public Double getIntegralMoney() {
		return this.integralMoney;
	}

	public void setIntegralMoney(Double integralMoney) {
		this.integralMoney = integralMoney;
	}

	public Double getBonus() {
		return this.bonus;
	}

	public void setBonus(Double bonus) {
		this.bonus = bonus;
	}


	public Double getOrderAmount() {
		return this.orderAmount;
	}

	public void setOrderAmount(Double orderAmount) {
		this.orderAmount = orderAmount;
	}

	public String getPayStatus() {
		return this.payStatus;
	}

	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public String getBonusId() {
		return bonusId;
	}

	public void setBonusId(String bonusId) {
		this.bonusId = bonusId;
	}

	public Double getCouponAmount() {
		return couponAmount;
	}

	public void setCouponAmount(Double couponAmount) {
		this.couponAmount = couponAmount;
	}

	public String getCouponId() {
		return couponId;
	}

	public void setCouponId(String couponId) {
		this.couponId = couponId;
	}

	public Set<OrderInfo> getOrderInfos() {
		return orderInfos;
	}

	public void setOrderInfos(Set<OrderInfo> orderInfos) {
		this.orderInfos = orderInfos;
	}
	public ProductDrawback getProductDrawback() {
		return productDrawback;
	}

	public void setProductDrawback(ProductDrawback productDrawback) {
		this.productDrawback = productDrawback;
	}

}