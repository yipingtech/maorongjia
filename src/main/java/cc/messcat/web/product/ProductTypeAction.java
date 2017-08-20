/*
 * ProductTypeAction.java
 * 
 * Create by MCGT
 * 
 * Create time 2015-04-09
 * 
 * Version 1.0
 * 
 * Copyright 2013 Messcat, Inc. All rights reserved.
 * 
 */
package cc.messcat.web.product;

import java.util.Iterator;
import java.util.List;

import cc.messcat.entity.McProductInfo;
import cc.messcat.entity.ProductType;
import cc.modules.commons.PageAction;
import cc.modules.constants.Constants;
import cc.modules.util.ObjValid;

public class ProductTypeAction extends PageAction {
	
	private Long id;
	
	private ProductType productType;
	
	private List<ProductType> productTypes;
	
	@SuppressWarnings("unchecked")
	public String retrieveAllProductTypes() throws Exception {
		try {
			super.pager = productTypeManagerDao.retrieveObjectsPager(pageSize, pageNo, new ProductType(), "id", Constants.DESC,Constants.ENABLE);
//			super.pager = productTypeManagerDao.retrieveObjectsPager(pageSize, pageNo, productType==null?new ProductType():productType, "id", Constants.ASC,Constants.ENABLE);
			this.productTypes = super.pager.getResultList();
		} catch (Exception ex) {
			addActionMessage("Load page error!");
		}
		return SUCCESS;
	}
	
	public String retrieveProductTypeById() throws Exception {
		try {
			this.productType = this.productTypeManagerDao.retrieveProductType(id);
		} catch (Exception ex) {
			addActionMessage("Load page error!");
		}
		return "editPage";
	}

	public String newPage() throws Exception {
		return "newPage";
	}
	
	public String viewPage() throws Exception {
		try {
			this.productType = this.productTypeManagerDao.retrieveProductType(id);
		} catch (Exception ex) {
			addActionMessage("Load page error!");
		}
		return "viewPage";
	}
	
	public String newProductType() throws Exception {
		try {
			this.productType.setStatus(Constants.ENABLE);
			this.productTypeManagerDao.save(this.productType);
			addActionMessage("New successfully!");
		} catch (Exception ex) {
			this.addActionError(ex.getMessage());
			addActionMessage("New fail!");
		}
		return this.retrieveAllProductTypes();
	}
	
	public String editProductType() throws Exception {
		try {
			ProductType productType0 = (ProductType) this.productTypeManagerDao.get(id, ProductType.class.getName());
			productType0.setName(this.productType.getName());
			
			this.productTypeManagerDao.modifyProductType(productType0);
			addActionMessage("Update successfully!");
		} catch (Exception ex) {
			this.addActionError(ex.getMessage());
			addActionMessage("Update fail!");
		}
		return this.retrieveAllProductTypes();
	}
	
	public String delProductType() throws Exception {
		try {
			//判断该类型是否存在订单或者购物车 ，是就将商品改成下架状态，否就将商品类型及类型属性删除
			if(productTypeManagerDao.findOrderByTypeId(this.id)){
				this.productTypeManagerDao.updateMcProductInfo(this.id);//修改商品为下架
				List<McProductInfo> mcProductInfos = this.productColumnManagerDao.retrieveAllProductInfos(this.id);
				if(ObjValid.isValid(mcProductInfos)){
					for(Iterator item = mcProductInfos.iterator();item.hasNext();){
					      McProductInfo mcProductInfo = (McProductInfo)item.next();
					      if(this.productColumnManagerDao.findByProduct(mcProductInfo.getId())){//判断该商品是否有数据在productColumn
								this.productColumnManagerDao.deleteByProduct(mcProductInfo.getId());
							}
					}
				}
			} else {
				List<McProductInfo> mcProductInfos = this.productColumnManagerDao.retrieveAllProductInfos(this.id);
				if(ObjValid.isValid(mcProductInfos)){
					for(Iterator item = mcProductInfos.iterator();item.hasNext();){
					      McProductInfo mcProductInfo = (McProductInfo)item.next();
					      if(this.productColumnManagerDao.findByProduct(mcProductInfo.getId())){//判断该商品是否有数据在productColumn
								this.productColumnManagerDao.deleteByProduct(mcProductInfo.getId());
							}
					}
				}
				this.productTypeManagerDao.removeProductType(this.id);//删除商品资料、类型、属性、库存、商品-属性
			}
			addActionMessage("Delete successfully!");
		} catch (Exception ex) {
			this.addActionError(ex.getMessage());
			ex.printStackTrace();
			addActionMessage("Delete fail!");
		}
		return this.retrieveAllProductTypes();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ProductType getProductType() {
		return productType;
	}

	public void setProductType(ProductType productType) {
		this.productType = productType;
	}

	public List<ProductType> getProductTypes() {
		return productTypes;
	}

	public void setProductTypes(
			List<ProductType> productTypes) {
		this.productTypes = productTypes;
	}
	
}