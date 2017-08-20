package cc.messcat.entity;

import java.util.Date;
import java.sql.Timestamp;

/**
 * ActivityApply entity. @author MyEclipse Persistence Tools
 */

public class ActivityApply implements java.io.Serializable {

	// Fields

	private Long id;
	private Member memberId;
	private EnterpriseColumn columnId;
	private McProductInfo productId;
	private Date addTime;
	private String receiveStatus;
	private String status;

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Member getMemberId() {
		return memberId;
	}

	public void setMemberId(Member memberId) {
		this.memberId = memberId;
	}

	public EnterpriseColumn getColumnId() {
		return columnId;
	}

	public void setColumnId(EnterpriseColumn columnId) {
		this.columnId = columnId;
	}

	public McProductInfo getProductId() {
		return productId;
	}

	public void setProductId(McProductInfo productId) {
		this.productId = productId;
	}

	public Date getAddTime() {
		return this.addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public String getReceiveStatus() {
		return this.receiveStatus;
	}

	public void setReceiveStatus(String receiveStatus) {
		this.receiveStatus = receiveStatus;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


}