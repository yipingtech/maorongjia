package cc.messcat.entity;

import java.util.Date;
import java.sql.Timestamp;

/**
 * ReportInfo entity. @author MyEclipse Persistence Tools
 */

public class ReportInfo implements java.io.Serializable {

	// Fields

	private Long id;
	private Date reportTime;
	private String reportStatus;
	private Member memberId;
	private String status;

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getReportTime() {
		return this.reportTime;
	}

	public void setReportTime(Date reportTime) {
		this.reportTime = reportTime;
	}

	public String getReportStatus() {
		return this.reportStatus;
	}

	public void setReportStatus(String reportStatus) {
		this.reportStatus = reportStatus;
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