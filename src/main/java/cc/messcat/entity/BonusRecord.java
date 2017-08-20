package cc.messcat.entity;

import java.util.Date;

/**
 * BonusRecord entity. @author MyEclipse Persistence Tools
 */

public class BonusRecord implements java.io.Serializable {

	// Fields

	private Long id;
	private Double money;
	private Member member;
	private Date sendTime;
	private String status;

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getMoney() {
		return this.money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Date getSendTime() {
		return this.sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}