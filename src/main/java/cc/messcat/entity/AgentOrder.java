package cc.messcat.entity;

import java.util.Date;
import java.sql.Timestamp;

/**
 * AgentOrder entity. @author MyEclipse Persistence Tools
 */

public class AgentOrder implements java.io.Serializable {

	// Fields

	private Long id;
	private String orderNum;
	private Double amount;
	private Member member;
	private Date addTime;
	private Date payTime;
	private Date endTime;
	private Member fatherMember;
	private Double fatherBonus;
	private String status;

	// Property accessors

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

	public Double getAmount() {
		return this.amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}


	public Date getAddTime() {
		return this.addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public Date getPayTime() {
		return this.payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	public Date getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}



	public Double getFatherBonus() {
		return this.fatherBonus;
	}

	public void setFatherBonus(Double fatherBonus) {
		this.fatherBonus = fatherBonus;
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

	public Member getFatherMember() {
		return fatherMember;
	}

	public void setFatherMember(Member fatherMember) {
		this.fatherMember = fatherMember;
	}

}