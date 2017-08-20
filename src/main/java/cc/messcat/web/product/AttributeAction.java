/*
 * AttributeAction.java
 * 
 * Create by MCGT
 * 
 * Create time 2015-04-10
 * 
 * Version 1.0
 * 
 * Copyright 2013 Messcat, Inc. All rights reserved.
 * 
 */
package cc.messcat.web.product;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import cc.messcat.entity.Attribute;
import cc.messcat.entity.McProductInfo;
import cc.messcat.entity.ProductType;
import cc.modules.commons.PageAction;
import cc.modules.constants.Constants;
import cc.modules.util.FormatStringUtil;
import cc.modules.util.ObjValid;

public class AttributeAction extends PageAction {
	
	private Long id;
	
	private Long typeId;
	
	private Attribute attribute;
	
	private List<Attribute> attributes;
	
	private List<String> attrValueList;
	
	private List<ProductType> productTypes;
	
	private String choseType;
	
	@SuppressWarnings("unchecked")
	public String retrieveAllAttributes() throws Exception {
		try {
			
			this.productTypes = this.productTypeManagerDao.findAllByStatus(ProductType.class.getName(), Constants.ENABLE);
			super.pager = attributeManagerDao.retrieveObjectsPager(pageSize, pageNo, attribute==null?new Attribute():attribute, "id", Constants.DESC,Constants.ENABLE);
			this.attributes = pager.getResultList();
			
		} catch (Exception ex) {
			ex.printStackTrace();
			addActionMessage("Load page error!");
		}
		return "success";
	}
	
	public String findAttrValueById(){
		this.attribute = this.attributeManagerDao.retrieveAttribute(id);
		this.typeId = this.attribute.getProductType().getId();
		this.attrValueList = FormatStringUtil.splitBySign(this.attribute.getAttrValue(), ",");
		return "value_list";
	}
	
	@SuppressWarnings("unchecked")
	public String retrieveAttributeById() throws Exception {
		try {
			this.productTypes = this.productTypeManagerDao.findAllByStatus(ProductType.class.getName(), Constants.ENABLE);
			this.attribute = (Attribute) this.attributeManagerDao.get(id, Attribute.class.getName());
		} catch (Exception ex) {
			addActionMessage("Load page error!");
		}
		return "editPage";
	}

	@SuppressWarnings("unchecked")
	public String newPage() throws Exception {
		this.productTypes = this.productTypeManagerDao.findAllByStatus(ProductType.class.getName(), Constants.ENABLE);
		return "newPage";
	}
	
	public String viewPage() throws Exception {
		try {
			this.attribute = (Attribute) this.attributeManagerDao.get(id, Attribute.class.getName());
		} catch (Exception ex) {
			addActionMessage("Load page error!");
		}
		return "viewPage";
	}
	
	public String newAttribute() throws Exception {
		try {
			ProductType productType = this.productTypeManagerDao.retrieveProductType(typeId);
			if(this.attribute.getAttrValue()!=null){
				String attrValues = this.attribute.getAttrValue().trim();
				String tempAttrValues = FormatStringUtil.changeStrSign(attrValues, "\\r?\\n", ",");
				this.attribute.setAttrValue(tempAttrValues.toString());
			}
			this.attribute.setAddTime(new Date());
			this.attribute.setEditTime(new Date());
			this.attribute.setProductType(productType);
			this.attributeManagerDao.addAttribute(this.attribute);
			addActionMessage("New successfully!");
		} catch (Exception ex) {
			this.addActionError(ex.getMessage());
			addActionMessage("New fail!");
		}
		return "edit_success";
	}
	
	public String editAttribute() throws Exception {
		try {
			Attribute attribute0 = this.attributeManagerDao.retrieveAttribute(this.id);
			if (ObjValid.isValid(typeId)) {
				ProductType productType = this.productTypeManagerDao.retrieveProductType(typeId);
				attribute0.setProductType(productType);
			}
			attribute0.setAttrName(this.attribute.getAttrName());
			attribute0.setAttrValue(this.attribute.getAttrValue());
			attribute0.setSortOrder(this.attribute.getSortOrder());
			attribute0.setIsLink(this.attribute.getIsLink());
			attribute0.setAttrGroup(this.attribute.getAttrGroup());
			attribute0.setStatus(this.attribute.getStatus());

			this.attributeManagerDao.modifyAttribute(attribute0);
			addActionMessage("Update successfully!");
		} catch (Exception ex) {
			this.addActionError(ex.getMessage());
			addActionMessage("Update fail!");
		}
		return "edit_success";
	}
	
	public String delAttribute() throws Exception {
		try {
			this.attribute = this.attributeManagerDao.retrieveAttribute(id);
			this.typeId = this.attribute.getProductType().getId();
			//判断属否有该类型的商品存在购物车或者订单里，有就将商品改成下架状态，否就将商品类型及类型属性删除
			if(productTypeManagerDao.findOrderByTypeId(this.typeId)){
				this.productTypeManagerDao.updateMcProductInfo(this.typeId);//修改商品为下架
				List<McProductInfo> mcProductInfos = this.productColumnManagerDao.retrieveAllProductInfos(this.typeId);
				if(ObjValid.isValid(mcProductInfos)){
					for(Iterator item = mcProductInfos.iterator();item.hasNext();){
					      McProductInfo mcProductInfo = (McProductInfo)item.next();
					      if(!this.productColumnManagerDao.findByProduct(mcProductInfo.getId())){//判断该商品是否有数据在productColumn
								this.productColumnManagerDao.deleteByProduct(mcProductInfo.getId());
							}
					}
				}
			} else {
				if(this.attributeManagerDao.findProductAttr(id)){//是否有商品引用该属性
					List<McProductInfo> mcProductInfos = this.productColumnManagerDao.retrieveAllProductInfos(this.typeId);
					if(ObjValid.isValid(mcProductInfos)){
						for(Iterator item = mcProductInfos.iterator();item.hasNext();){
						      McProductInfo mcProductInfo = (McProductInfo)item.next();
						      if(this.productColumnManagerDao.findByProduct(mcProductInfo.getId())){//判断该商品是否有数据在productColumn
									this.productColumnManagerDao.deleteByProduct(mcProductInfo.getId());
								}
						}
					}
					this.productTypeManagerDao.removeAttributeProduct(this.typeId,id);//删除商品资料、该属性、库存、商品-属性
				}else{
					this.attributeManagerDao.removeAttribute(this.id);
				}
			}
			addActionMessage("Delete successfully!");
		} catch (Exception ex) {
			this.addActionError(ex.getMessage());
			addActionMessage("Delete fail!");
		}
		return "edit_success";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Attribute getAttribute() {
		return attribute;
	}

	public void setAttribute(Attribute attribute) {
		this.attribute = attribute;
	}

	public List<Attribute> getAttributes() {
		return attributes;
	}

	public void setAttributes(
			List<Attribute> attributes) {
		this.attributes = attributes;
	}

	public Long getTypeId() {
		return typeId;
	}

	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}

	public List<ProductType> getProductTypes() {
		return productTypes;
	}

	public void setProductTypes(List<ProductType> productTypes) {
		this.productTypes = productTypes;
	}

	public List<String> getAttrValueList() {
		return attrValueList;
	}

	public void setAttrValueList(List<String> attrValueList) {
		this.attrValueList = attrValueList;
	}

	public String getChoseType() {
		return choseType;
	}

	public void setChoseType(String choseType) {
		this.choseType = choseType;
	}

}