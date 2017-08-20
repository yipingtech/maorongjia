package cc.messcat.entity;

import java.util.Date;
import java.sql.Timestamp;


public class Stock implements java.io.Serializable {

	// Fields

	private Long id;
	private McProductInfo product;
	private String productAttrIds;
	private Long amount;
	private Double price;
	private Date addTime;
	private Date editTime;
	private String status;

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public String getProductAttrIds() {
		return this.productAttrIds;
	}

	public void setProductAttrIds(String productAttrIds) {
		this.productAttrIds = productAttrIds;
	}

	public Long getAmount() {
		return this.amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Date getAddTime() {
		return this.addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public Date getEditTime() {
		return this.editTime;
	}

	public void setEditTime(Date editTime) {
		this.editTime = editTime;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public McProductInfo getProduct() {
		return product;
	}

	public void setProduct(McProductInfo product) {
		this.product = product;
	}

}