package cc.messcat.entity;

import java.util.Date;
import java.sql.Timestamp;

/**
 * RechargeInfo entity. @author MyEclipse Persistence Tools
 */

public class RechargeInfo implements java.io.Serializable {

	// Fields

	private Long id;
	private Double rechargeAmount;
	private Date rechargeTime;
	private String rechargeType;
	private String rechargeCard;
	private String rechargeComments;
	private Member memberId;
	private String status;

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getRechargeAmount() {
		return this.rechargeAmount;
	}

	public void setRechargeAmount(Double rechargeAmount) {
		this.rechargeAmount = rechargeAmount;
	}

	public Date getRechargeTime() {
		return this.rechargeTime;
	}

	public void setRechargeTime(Date rechargeTime) {
		this.rechargeTime = rechargeTime;
	}

	public String getRechargeType() {
		return this.rechargeType;
	}

	public void setRechargeType(String rechargeType) {
		this.rechargeType = rechargeType;
	}

	public String getRechargeCard() {
		return this.rechargeCard;
	}

	public void setRechargeCard(String rechargeCard) {
		this.rechargeCard = rechargeCard;
	}

	public String getRechargeComments() {
		return this.rechargeComments;
	}

	public void setRechargeComments(String rechargeComments) {
		this.rechargeComments = rechargeComments;
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