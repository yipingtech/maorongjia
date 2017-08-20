package cc.messcat.entity;

import java.util.Date;

/**
 * MemberTicket entity. @author MyEclipse Persistence Tools
 */

public class MemberTicket implements java.io.Serializable {

	// Fields

	private Long id;
	private Member memberId;
	private TicketInfo ticketId;
	private Date addTime;
	private Date overTime;
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

	public TicketInfo getTicketId() {
		return ticketId;
	}

	public void setTicketId(TicketInfo ticketId) {
		this.ticketId = ticketId;
	}

	public Date getAddTime() {
		return this.addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public Date getOverTime() {
		return overTime;
	}

	public void setOverTime(Date overTime) {
		this.overTime = overTime;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}