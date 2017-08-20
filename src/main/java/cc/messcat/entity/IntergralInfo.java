package cc.messcat.entity;

import java.util.Date;
import java.sql.Timestamp;

/**
 * IntergralInfo entity. @author MyEclipse Persistence Tools
 */

public class IntergralInfo implements java.io.Serializable {

	// Fields

	private Long id;
	private IntegralRule intergralRule;
	private Long intergral;
	private Date addTime;
	private String comments;
	private Member memberId;
	private String intergralStatus;
	private String status;

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public Date getAddTime() {
		return this.addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Member getMemberId() {
		return memberId;
	}

	public void setMemberId(Member memberId) {
		this.memberId = memberId;
	}

	public String getIntergralStatus() {
		return this.intergralStatus;
	}

	public void setIntergralStatus(String intergralStatus) {
		this.intergralStatus = intergralStatus;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getIntergral() {
		return intergral;
	}

	public void setIntergral(Long intergral) {
		this.intergral = intergral;
	}

	public IntegralRule getIntergralRule() {
		return intergralRule;
	}

	public void setIntergralRule(IntegralRule intergralRule) {
		this.intergralRule = intergralRule;
	}

}