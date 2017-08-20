package cc.messcat.entity;

/**
 * ProductType entity. @author MyEclipse Persistence Tools
 */

public class ProductType implements java.io.Serializable {

	// Fields

	private Long id;
	private String name;
	private String typeGroup;
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

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTypeGroup() {
		return typeGroup;
	}

	public void setTypeGroup(String typeGroup) {
		this.typeGroup = typeGroup;
	}

}