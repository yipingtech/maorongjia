package cc.messcat.entity;

import java.util.Date;
import java.sql.Timestamp;

/**
 * ActivityInvite entity. @author MyEclipse Persistence Tools
 */

public class ActivityInvite implements java.io.Serializable {

	// Fields

	private Long id;
	private Member memberId;
	private Member inviteFriend;
	private ActivityApply applyId;
	private Date addTime;
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

	public Member getInviteFriend() {
		return inviteFriend;
	}

	public void setInviteFriend(Member inviteFriend) {
		this.inviteFriend = inviteFriend;
	}

	public ActivityApply getApplyId() {
		return applyId;
	}

	public void setApplyId(ActivityApply applyId) {
		this.applyId = applyId;
	}

	public Date getAddTime() {
		return this.addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}