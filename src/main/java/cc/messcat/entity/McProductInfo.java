package cc.messcat.entity;

import java.util.Date;

/**
 * McProductInfo entity. @author MyEclipse Persistence Tools
 */

public class McProductInfo implements java.io.Serializable {

	// Fields

	private Long id;
	private String title;
	private String keywords;
	private String description;
	private String content;
	private String isNew;
	private String imgurl;
	private String isSale;
	private Integer hits;
	private Date updatetime;
	private Date addtime;
	private String productSn;
	private ProductType productType;
	private Double weight;
	private Double marketPrice;
	private Double shopPrice;//本店价格
	private Double promotePrice;
	private Date promoteStartDate;
	private Date promoteEndDate;
	private Long warnNumber;
	private Long repertory;
	private Long shareTime;
	private Long saleValume;
	private Double grade;
	private Long giveIntegral;
	private String isBest;
	private String isHot;
	private String isPromote;
	private String para1;
	private String para2;
	private String para3;
	private String para4;
	private String para5;
	private String para6;
	private String para7;
	private String para8;
	private String para9;
	private String para10;
	private String para11;
	private String para12;
	private String para13;
	private String para14;
	private String para15;
	private String para16;
	private String para17;
	private String para18;
	private String para19;
	private String para20;
	private String status;
	private Integer orderPara;


	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getKeywords() {
		return this.keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImgurl() {
		return this.imgurl;
	}

	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}


	public Integer getHits() {
		return this.hits;
	}

	public void setHits(Integer hits) {
		this.hits = hits;
	}

	public Date getUpdatetime() {
		return this.updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

	public Date getAddtime() {
		return this.addtime;
	}

	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	public String getPara1() {
		return this.para1;
	}

	public void setPara1(String para1) {
		this.para1 = para1;
	}

	public String getPara2() {
		return this.para2;
	}

	public void setPara2(String para2) {
		this.para2 = para2;
	}

	public String getPara3() {
		return this.para3;
	}

	public void setPara3(String para3) {
		this.para3 = para3;
	}

	public String getPara4() {
		return this.para4;
	}

	public void setPara4(String para4) {
		this.para4 = para4;
	}

	public String getPara5() {
		return this.para5;
	}

	public void setPara5(String para5) {
		this.para5 = para5;
	}

	public String getPara6() {
		return this.para6;
	}

	public void setPara6(String para6) {
		this.para6 = para6;
	}

	public String getPara7() {
		return this.para7;
	}

	public void setPara7(String para7) {
		this.para7 = para7;
	}

	public String getPara8() {
		return this.para8;
	}

	public void setPara8(String para8) {
		this.para8 = para8;
	}

	public String getPara9() {
		return this.para9;
	}

	public void setPara9(String para9) {
		this.para9 = para9;
	}

	public String getPara10() {
		return this.para10;
	}

	public void setPara10(String para10) {
		this.para10 = para10;
	}

	public String getPara11() {
		return this.para11;
	}

	public void setPara11(String para11) {
		this.para11 = para11;
	}

	public String getPara12() {
		return this.para12;
	}

	public void setPara12(String para12) {
		this.para12 = para12;
	}

	public String getPara13() {
		return this.para13;
	}

	public void setPara13(String para13) {
		this.para13 = para13;
	}

	public String getPara14() {
		return this.para14;
	}

	public void setPara14(String para14) {
		this.para14 = para14;
	}

	public String getPara15() {
		return this.para15;
	}

	public void setPara15(String para15) {
		this.para15 = para15;
	}

	public String getPara16() {
		return this.para16;
	}

	public void setPara16(String para16) {
		this.para16 = para16;
	}

	public String getPara17() {
		return this.para17;
	}

	public void setPara17(String para17) {
		this.para17 = para17;
	}

	public String getPara18() {
		return this.para18;
	}

	public void setPara18(String para18) {
		this.para18 = para18;
	}

	public String getPara19() {
		return this.para19;
	}

	public void setPara19(String para19) {
		this.para19 = para19;
	}

	public String getPara20() {
		return this.para20;
	}

	public void setPara20(String para20) {
		this.para20 = para20;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getProductSn() {
		return productSn;
	}

	public void setProductSn(String productSn) {
		this.productSn = productSn;
	}

	public ProductType getProductType() {
		return productType;
	}

	public void setProductType(ProductType productType) {
		this.productType = productType;
	}

	public Date getPromoteStartDate() {
		return promoteStartDate;
	}

	public void setPromoteStartDate(Date promoteStartDate) {
		this.promoteStartDate = promoteStartDate;
	}

	public Date getPromoteEndDate() {
		return promoteEndDate;
	}

	public void setPromoteEndDate(Date promoteEndDate) {
		this.promoteEndDate = promoteEndDate;
	}

	

	public String getIsBest() {
		return isBest;
	}

	public void setIsBest(String isBest) {
		this.isBest = isBest;
	}

	public String getIsHot() {
		return isHot;
	}

	public void setIsHot(String isHot) {
		this.isHot = isHot;
	}

	public String getIsPromote() {
		return isPromote;
	}

	public void setIsPromote(String isPromote) {
		this.isPromote = isPromote;
	}

	public String getIsNew() {
		return isNew;
	}

	public void setIsNew(String isNew) {
		this.isNew = isNew;
	}

	public String getIsSale() {
		return isSale;
	}

	public void setIsSale(String isSale) {
		this.isSale = isSale;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public Double getMarketPrice() {
		return marketPrice;
	}

	public void setMarketPrice(Double marketPrice) {
		this.marketPrice = marketPrice;
	}

	public Double getShopPrice() {
		return shopPrice;
	}

	public void setShopPrice(Double shopPrice) {
		this.shopPrice = shopPrice;
	}

	public Double getPromotePrice() {
		return promotePrice;
	}

	public void setPromotePrice(Double promotePrice) {
		this.promotePrice = promotePrice;
	}

	public Long getWarnNumber() {
		return warnNumber;
	}

	public void setWarnNumber(Long warnNumber) {
		this.warnNumber = warnNumber;
	}

	public Long getRepertory() {
		return repertory;
	}

	public void setRepertory(Long repertory) {
		this.repertory = repertory;
	}

	public Long getGiveIntegral() {
		return giveIntegral;
	}

	public void setGiveIntegral(Long giveIntegral) {
		this.giveIntegral = giveIntegral;
	}

	public Long getShareTime() {
		return shareTime;
	}

	public void setShareTime(Long shareTime) {
		this.shareTime = shareTime;
	}

	public Long getSaleValume() {
		return saleValume;
	}

	public void setSaleValume(Long saleValume) {
		this.saleValume = saleValume;
	}

	public Double getGrade() {
		return grade;
	}

	public void setGrade(Double grade) {
		this.grade = grade;
	}

	public Integer getOrderPara() {
		return orderPara;
	}

	public void setOrderPara(Integer orderPara) {
		this.orderPara = orderPara;
	}

}