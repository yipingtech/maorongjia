package cc.messcat.entity;

import java.util.Date;

/**
 * CommissionInfo entity. @author MyEclipse Persistence Tools
 */

public class CommissionInfo implements java.io.Serializable {

	// Fields

	private Long id;
	private Double commission;
	private Date addTime;
	private String commissionComments;
	private String commissionStatus;
	private Member memberId;
	private String status;
	private Member fromMemberId;
	private PayOrder payOrder;

	// Property accessors



	public Member getFromMemberId() {
		return fromMemberId;
	}

	public PayOrder getPayOrder() {
		return payOrder;
	}

	public void setPayOrder(PayOrder payOrder) {
		this.payOrder = payOrder;
	}

	public void setFromMemberId(Member fromMemberId) {
		this.fromMemberId = fromMemberId;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getCommission() {
		return this.commission;
	}

	public void setCommission(Double commission) {
		this.commission = commission;
	}

	public Date getAddTime() {
		return this.addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public String getCommissionComments() {
		return this.commissionComments;
	}

	public void setCommissionComments(String commissionComments) {
		this.commissionComments = commissionComments;
	}

	public String getCommissionStatus() {
		return this.commissionStatus;
	}

	public void setCommissionStatus(String commissionStatus) {
		this.commissionStatus = commissionStatus;
	}

	public Member getMemberId() {
		return memberId;
	}

	public void setMemberId(Member memberId) {
		this.memberId = memberId;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}