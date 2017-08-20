package cc.messcat.entity;

import java.util.Date;
import java.sql.Timestamp;

/**
 * CartInfo entity. @author MyEclipse Persistence Tools
 */

public class CartInfo implements java.io.Serializable {

	// Fields

	private Long id;
	private Member member;
	private McProductInfo product;
	private String productAttrIds;
	private Double productPrice;
	private Double productTotal;
	private Long buyAmount;
	private Date createTime;

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public Double getProductPrice() {
		return this.productPrice;
	}

	public void setProductPrice(Double productPrice) {
		this.productPrice = productPrice;
	}

	public Double getProductTotal() {
		return this.productTotal;
	}

	public void setProductTotal(Double productTotal) {
		this.productTotal = productTotal;
	}

	public Long getBuyAmount() {
		return this.buyAmount;
	}

	public void setBuyAmount(Long buyAmount) {
		this.buyAmount = buyAmount;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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

}