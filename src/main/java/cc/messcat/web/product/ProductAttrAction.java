/*
 * ProductAttrAction.java
 * 
 * Create by MCGT
 * 
 * Create time 2015-04-15
 * 
 * Version 1.0
 * 
 * Copyright 2013 Messcat, Inc. All rights reserved.
 * 
 */
package cc.messcat.web.product;

import java.util.List;

import cc.modules.commons.PageAction;
import cc.messcat.entity.ProductAttr;

public class ProductAttrAction extends PageAction {
	
	private Long id;
	
	private ProductAttr productAttr;
	
	private List<ProductAttr> productAttrs;
	
	@SuppressWarnings("unchecked")
	public String retrieveAllProductAttrs() throws Exception {
		try {
			super.pager = this.productAttrManagerDao.retrieveProductAttrsPager(pageSize, pageNo);
			this.productAttrs = super.pager.getResultList();
		} catch (Exception ex) {
			addActionMessage("Load page error!");
		}
		return "success";
	}
	
	public String retrieveProductAttrById() throws Exception {
		try {
			this.productAttr = this.productAttrManagerDao.retrieveProductAttr(id);
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
			this.productAttr = this.productAttrManagerDao.retrieveProductAttr(id);
		} catch (Exception ex) {
			addActionMessage("Load page error!");
		}
		return "viewPage";
	}
	
	public String newProductAttr() throws Exception {
		try {
			this.productAttrManagerDao.addProductAttr(this.productAttr);
			addActionMessage("New successfully!");
		} catch (Exception ex) {
			this.addActionError(ex.getMessage());
			addActionMessage("New fail!");
		}
		return "edit_success";
	}
	
	public String editProductAttr() throws Exception {
		try {
			ProductAttr productAttr0 = this.productAttrManagerDao.retrieveProductAttr(this.id);
			productAttr0.setAttrValue(this.productAttr.getAttrValue());

			this.productAttrManagerDao.modifyProductAttr(productAttr0);
			addActionMessage("Update successfully!");
		} catch (Exception ex) {
			this.addActionError(ex.getMessage());
			addActionMessage("Update fail!");
		}
		return "edit_success";
	}
	
	public String delProductAttr() throws Exception {
		try {
			this.productAttrManagerDao.removeProductAttr(this.id);
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

	public ProductAttr getProductAttr() {
		return productAttr;
	}

	public void setProductAttr(ProductAttr productAttr) {
		this.productAttr = productAttr;
	}

	public List<ProductAttr> getProductAttrs() {
		return productAttrs;
	}

	public void setProductAttrs(
			List<ProductAttr> productAttrs) {
		this.productAttrs = productAttrs;
	}

}