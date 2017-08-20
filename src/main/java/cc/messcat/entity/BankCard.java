package cc.messcat.entity;

import java.util.Date;
import java.sql.Timestamp;

/**
 * BankCard entity. @author MyEclipse Persistence Tools
 */

public class BankCard implements java.io.Serializable {

	// Fields

	

	private Long id;
	private Member member;
	private String bankCardFlag;
	private String bankCardNum;
	private String bankCardMember;
	private String bankCardAddress;
	private Province province;
	private City city;
	private String bankCardPoint;
	private Date bankCardTime;
	private String bankCardRemarks;
	private String status;

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public String getBankCardFlag() {
		return this.bankCardFlag;
	}

	public void setBankCardFlag(String bankCardFlag) {
		this.bankCardFlag = bankCardFlag;
	}

	public String getBankCardNum() {
		return this.bankCardNum;
	}

	public void setBankCardNum(String bankCardNum) {
		this.bankCardNum = bankCardNum;
	}

	public String getBankCardMember() {
		return this.bankCardMember;
	}

	public void setBankCardMember(String bankCardMember) {
		this.bankCardMember = bankCardMember;
	}

	public String getBankCardAddress() {
		return this.bankCardAddress;
	}

	public void setBankCardAddress(String bankCardAddress) {
		this.bankCardAddress = bankCardAddress;
	}

	public String getBankCardPoint() {
		return this.bankCardPoint;
	}

	public void setBankCardPoint(String bankCardPoint) {
		this.bankCardPoint = bankCardPoint;
	}

	public Date getBankCardTime() {
		return this.bankCardTime;
	}

	public void setBankCardTime(Date bankCardTime) {
		this.bankCardTime = bankCardTime;
	}

	public String getBankCardRemarks() {
		return this.bankCardRemarks;
	}

	public void setBankCardRemarks(String bankCardRemarks) {
		this.bankCardRemarks = bankCardRemarks;
	}

	public String getStatus() {
		return this.status;
	}
	public Province getProvince() {
		return province;
	}

	public void setProvince(Province province) {
		this.province = province;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

}