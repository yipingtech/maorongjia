package cc.messcat.entity;

import java.util.Date;

/**
 * OrderInfo entity. @author MyEclipse Persistence Tools
 */

public class OrderInfo implements java.io.Serializable {

	// Fields

	private Long id;
	private String orderinfoNum;
	private Member member;
	private McProductInfo product;
	private String productAttrIds;
	private Double price;
	private Long amount;
	private Double totalPrice;
	private String comments;
	private Date createTime;
	private Date orderTime;
	private Date payTime;
	private PayOrder payOrder;
	private String orderStatus;
	private String evaluateStatus;
	private String productStatus;
	private String status;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getOrderinfoNum() {
		return orderinfoNum;
	}
	public void setOrderinfoNum(String orderinfoNum) {
		this.orderinfoNum = orderinfoNum;
	}
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	public McProductInfo getProduct() {
		return product;
	}
	public void setProduct(McProductInfo product) {
		this.product = product;
	}
	public String getProductAttrIds() {
		return productAttrIds;
	}
	public void setProductAttrIds(String productAttrIds) {
		this.productAttrIds = productAttrIds;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Long getAmount() {
		return amount;
	}
	public void setAmount(Long amount) {
		this.amount = amount;
	}
	public Double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}
	public Date getPayTime() {
		return payTime;
	}
	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}
	public PayOrder getPayOrder() {
		return payOrder;
	}
	public void setPayOrder(PayOrder payOrder) {
		this.payOrder = payOrder;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public String getProductStatus() {
		return productStatus;
	}
	public void setProductStatus(String productStatus) {
		this.productStatus = productStatus;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getEvaluateStatus() {
		return evaluateStatus;
	}
	public void setEvaluateStatus(String evaluateStatus) {
		this.evaluateStatus = evaluateStatus;
	}
}