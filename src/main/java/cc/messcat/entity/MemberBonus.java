package cc.messcat.entity;

import java.util.Date;

/**
 * MemberBonus entity. @author MyEclipse Persistence Tools
 */

public class MemberBonus implements java.io.Serializable {

	// Fields

	private Long id;
	private Date validPeriod;
	private Member member;
	private Bonus bonus;
	private Date sendTime;
	private String status;


	// Property accessors

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getValidPeriod() {
		return this.validPeriod;
	}

	public void setValidPeriod(Date validPeriod) {
		this.validPeriod = validPeriod;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Bonus getBonus() {
		return bonus;
	}

	public void setBonus(Bonus bonus) {
		this.bonus = bonus;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}