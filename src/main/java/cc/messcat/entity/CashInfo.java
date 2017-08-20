package cc.messcat.entity;

import java.util.Date;
import java.sql.Timestamp;

/**
 * CashInfo entity. @author MyEclipse Persistence Tools
 */

public class CashInfo implements java.io.Serializable {

	// Fields

	private Long id;
	private String wechatNum;
	private Double drawAmount;
	private String phoneNum;
	private Date drawTime;
	private Date dealTime;
	private String drawComments;
	private Member member;
	private BankCard bankCard;
	private String status;

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getWechatNum() {
		return this.wechatNum;
	}

	public void setWechatNum(String wechatNum) {
		this.wechatNum = wechatNum;
	}

	public Double getDrawAmount() {
		return this.drawAmount;
	}

	public void setDrawAmount(Double drawAmount) {
		this.drawAmount = drawAmount;
	}

	public String getPhoneNum() {
		return this.phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public Date getDrawTime() {
		return this.drawTime;
	}

	public void setDrawTime(Date drawTime) {
		this.drawTime = drawTime;
	}

	public Date getDealTime() {
		return this.dealTime;
	}

	public void setDealTime(Date dealTime) {
		this.dealTime = dealTime;
	}

	public String getDrawComments() {
		return this.drawComments;
	}

	public void setDrawComments(String drawComments) {
		this.drawComments = drawComments;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	public BankCard getBankCard() {
		return bankCard;
	}

	public void setBankCard(BankCard bankCard) {
		this.bankCard = bankCard;
	}

}