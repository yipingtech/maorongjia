package cc.messcat.entity;

/**
 * IntegralRule entity. @author MyEclipse Persistence Tools
 */

public class IntegralRule implements java.io.Serializable {

	// Fields

	private Long id;
	private String name;
	private Long period;
	private Long maxtmie;
	private Long integral;
	private String status;

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getPeriod() {
		return this.period;
	}

	public void setPeriod(Long period) {
		this.period = period;
	}

	public Long getMaxtmie() {
		return this.maxtmie;
	}

	public void setMaxtmie(Long maxtmie) {
		this.maxtmie = maxtmie;
	}

	public Long getIntegral() {
		return this.integral;
	}

	public void setIntegral(Long integral) {
		this.integral = integral;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}