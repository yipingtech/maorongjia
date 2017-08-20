/*
 * ProductAttrManagerDaoImpl.java
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
package cc.messcat.service.product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cc.messcat.bases.BaseManagerDaoImpl;
import cc.messcat.entity.Attribute;
import cc.messcat.entity.McProductInfo;
import cc.messcat.entity.ProductAttr;
import cc.modules.commons.Pager;
import cc.modules.util.ObjValid;

public class ProductAttrManagerDaoImpl extends BaseManagerDaoImpl implements ProductAttrManagerDao {

	public void addProductAttr(ProductAttr productAttr) {
		this.productAttrDao.save(productAttr);
	}
	
	public void modifyProductAttr(ProductAttr productAttr) {
		this.productAttrDao.update(productAttr);
	}
	
	public void removeProductAttr(ProductAttr productAttr) {
		this.productAttrDao.delete(productAttr);
	}

	public void removeProductAttr(Long id) {
		this.productAttrDao.delete(id);
	}
	
	public ProductAttr retrieveProductAttr(Long id) {
		return (ProductAttr) this.productAttrDao.get(id);
	}

	@SuppressWarnings("unchecked")
	public List retrieveAllProductAttrs() {
		return this.productAttrDao.findAll();
	}
	
	public Pager retrieveProductAttrsPager(int pageSize, int pageNo) {
		return this.productAttrDao.getPager(pageSize, pageNo);
	}
	
	public void saveProcutAttr(McProductInfo mcProductInfo, Attribute attribute, String attrVal){
		ProductAttr productAttr = new ProductAttr();
		productAttr.setProduct(mcProductInfo);
		productAttr.setAttr(attribute);
		productAttr.setAttrValue(attrVal);
		productAttr.setStatus("1");
		this.productAttrDao.save(productAttr);
	}

	public ProductAttr searchProductAttr(ProductAttr condition){
		Map<String, Object> attrs = new HashMap<String, Object>();
		attrs.put("product", condition.getProduct());
		//attrs.put("status", condition.getStatus());
		attrs.put("attr", condition.getAttr());
		attrs.put("attrValue", condition.getAttrValue());
		ProductAttr newProductAttr = this.productAttrDao.query(ProductAttr.class, attrs);
		if (ObjValid.isValid(newProductAttr)) {
			//把已删除的产品属性关联重新利用，避免数据库太多废弃数据
			newProductAttr.setStatus("1");
			this.productAttrDao.update(newProductAttr);
			return newProductAttr;
		}else {
			this.productAttrDao.save(condition);
			return condition;
		}
	}
	
	public List<ProductAttr> queryByProductAndAttrType(Long productId, String attrType){
		return this.productAttrDao.queryByProductAndAttrType(productId, attrType);
	}

	@Override
	public ProductAttr findAttrByProduct(McProductInfo mcProductInfo) {
		return this.productAttrDao.findAttrByProduct(mcProductInfo);
	}
	
	public List<ProductAttr> findByIds(String ids){
		return this.productAttrDao.queryList(ProductAttr.class, ids);
	}
	
}