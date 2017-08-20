package cc.messcat.entity;

import java.util.Date;
import java.sql.Timestamp;

/**
 * ProductDrawback entity. @author MyEclipse Persistence Tools
 */

public class ProductDrawback implements java.io.Serializable {

	// Fields

	private Long id;
	private String orderNum; // 表示清单号
	private String memberId;
	private McProductInfo productId;
	private Double drawbackMoney;
	private String drawbackCause;
	private String imageName;
	private Date applyTime;
	private Date auditTime;
	private String sendPhone;
	private String sendAddress;
	private String flag;
	private String returnStatus;
	private String status;

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getOrderNum() {
		return this.orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public String getMemberId() {
		return this.memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public McProductInfo getProductId() {
		return productId;
	}

	public void setProductId(McProductInfo productId) {
		this.productId = productId;
	}

	public Double getDrawbackMoney() {
		return this.drawbackMoney;
	}

	public void setDrawbackMoney(Double drawbackMoney) {
		this.drawbackMoney = drawbackMoney;
	}

	public String getDrawbackCause() {
		return this.drawbackCause;
	}

	public void setDrawbackCause(String drawbackCause) {
		this.drawbackCause = drawbackCause;
	}

	public String getImageName() {
		return this.imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public Date getApplyTime() {
		return this.applyTime;
	}

	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}

	public Date getAuditTime() {
		return this.auditTime;
	}

	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}

	public String getSendPhone() {
		return this.sendPhone;
	}

	public void setSendPhone(String sendPhone) {
		this.sendPhone = sendPhone;
	}

	public String getSendAddress() {
		return this.sendAddress;
	}

	public void setSendAddress(String sendAddress) {
		this.sendAddress = sendAddress;
	}

	public String getReturnStatus() {
		return this.returnStatus;
	}

	public void setReturnStatus(String returnStatus) {
		this.returnStatus = returnStatus;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}