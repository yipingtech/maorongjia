package cc.messcat.entity;

/**
 * TicketInfo entity. @author MyEclipse Persistence Tools
 */

public class TicketInfo implements java.io.Serializable {

	// Fields

	private Long id;
	private Double ticketAmount;
	private Double restrictAmount;
	private Long restrictDay;
	private String status;

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getTicketAmount() {
		return this.ticketAmount;
	}

	public void setTicketAmount(Double ticketAmount) {
		this.ticketAmount = ticketAmount;
	}

	public Double getRestrictAmount() {
		return this.restrictAmount;
	}

	public void setRestrictAmount(Double restrictAmount) {
		this.restrictAmount = restrictAmount;
	}

	public Long getRestrictDay() {
		return this.restrictDay;
	}

	public void setRestrictDay(Long restrictDay) {
		this.restrictDay = restrictDay;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}