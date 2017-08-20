package cc.messcat.entity;

/**
 * Bonus entity. @author MyEclipse Persistence Tools
 */

public class Bonus implements java.io.Serializable {

	// Fields

	private Long id;
	private Double amount;
	private String status;

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getAmount() {
		return this.amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}