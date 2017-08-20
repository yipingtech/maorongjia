package cc.messcat.vo;

import java.util.LinkedHashMap;

import cc.messcat.entity.Attribute;

public class StockVo {
	
	private String stockId;
	private LinkedHashMap<Attribute, String> attMap;
	private String repertory;
	private String price;

	public String getRepertory() {
		return repertory;
	}
	public void setRepertory(String repertory) {
		this.repertory = repertory;
	}
	public LinkedHashMap<Attribute, String> getAttMap() {
		return attMap;
	}
	public void setAttMap(LinkedHashMap<Attribute, String> attMap) {
		this.attMap = attMap;
	}
	public String getStockId() {
		return stockId;
	}
	public void setStockId(String stockId) {
		this.stockId = stockId;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	
}
