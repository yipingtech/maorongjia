package cc.messcat.entity;

/**
 * ProductAttr entity. @author MyEclipse Persistence Tools
 */

public class ProductAttr implements java.io.Serializable {

	// Fields

	private Long id;
	private McProductInfo product;
	private Attribute attr;
	private String attrValue;
	private String status;

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public String getAttrValue() {
		return this.attrValue;
	}

	public void setAttrValue(String attrValue) {
		this.attrValue = attrValue;
	}

	public McProductInfo getProduct() {
		return product;
	}

	public void setProduct(McProductInfo product) {
		this.product = product;
	}

	public Attribute getAttr() {
		return attr;
	}

	public void setAttr(Attribute attr) {
		this.attr = attr;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}