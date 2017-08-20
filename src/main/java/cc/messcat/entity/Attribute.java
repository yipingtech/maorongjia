package cc.messcat.entity;

import java.util.Date;

/**
 * Attribute entity. @author MyEclipse Persistence Tools
 */

public class Attribute implements java.io.Serializable {

	// Fields

	private Long id;
	private ProductType productType;
	private String attrName;
	private String attrValue;
	private String attrInputType;
	private String attrType;
	private Long sortOrder;
	private String isLink;
	private String attrGroup;
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


	public String getAttrName() {
		return this.attrName;
	}

	public void setAttrName(String attrName) {
		this.attrName = attrName;
	}

	public String getAttrValue() {
		return this.attrValue;
	}

	public void setAttrValue(String attrValue) {
		this.attrValue = attrValue;
	}

	public Long getSortOrder() {
		return this.sortOrder;
	}

	public void setSortOrder(Long sortOrder) {
		this.sortOrder = sortOrder;
	}

	public String getIsLink() {
		return this.isLink;
	}

	public void setIsLink(String isLink) {
		this.isLink = isLink;
	}

	public String getAttrGroup() {
		return this.attrGroup;
	}

	public void setAttrGroup(String attrGroup) {
		this.attrGroup = attrGroup;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public ProductType getProductType() {
		return productType;
	}

	public void setProductType(ProductType productType) {
		this.productType = productType;
	}

	public String getAttrInputType() {
		return attrInputType;
	}

	public void setAttrInputType(String attrInputType) {
		this.attrInputType = attrInputType;
	}

	public String getAttrType() {
		return attrType;
	}

	public void setAttrType(String attrType) {
		this.attrType = attrType;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public Date getEditTime() {
		return editTime;
	}

	public void setEditTime(Date editTime) {
		this.editTime = editTime;
	}

}