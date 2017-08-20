package cc.messcat.vo;

import java.util.LinkedHashMap;

import cc.messcat.entity.Attribute;
import cc.messcat.entity.McProductInfo;

public class CartInfoVo {
	private Long id;
	private McProductInfo product;
	private String productAttrIds;
	private LinkedHashMap<Attribute, String> attributeMap;
	private Double productPrice;
	private Double productTotal;
	private Long buyAmount;
	
	public McProductInfo getProduct() {
		return product;
	}
	public void setProduct(McProductInfo product) {
		this.product = product;
	}
	public String getProductAttrIds() {
		return productAttrIds;
	}
	public void setProductAttrIds(String productAttrIds) {
		this.productAttrIds = productAttrIds;
	}
	public LinkedHashMap<Attribute, String> getAttributeMap() {
		return attributeMap;
	}
	public void setAttributeMap(LinkedHashMap<Attribute, String> attributeMap) {
		this.attributeMap = attributeMap;
	}
	public Double getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(Double productPrice) {
		this.productPrice = productPrice;
	}
	public Double getProductTotal() {
		return productTotal;
	}
	public void setProductTotal(Double productTotal) {
		this.productTotal = productTotal;
	}
	public Long getBuyAmount() {
		return buyAmount;
	}
	public void setBuyAmount(Long buyAmount) {
		this.buyAmount = buyAmount;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
}
