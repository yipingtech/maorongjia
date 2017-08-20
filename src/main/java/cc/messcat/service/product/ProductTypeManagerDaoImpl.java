/*
 * ProductTypeManagerDaoImpl.java
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
package cc.messcat.service.product;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cc.messcat.bases.BaseManagerDaoImpl;
import cc.messcat.entity.McProductInfo;
import cc.messcat.entity.ProductType;
import cc.messcat.service.collection.McProductInfoManagerDao;
import cc.messcat.service.collection.McProductInfoManagerDaoImpl;
import cc.modules.util.ObjValid;


public class ProductTypeManagerDaoImpl extends BaseManagerDaoImpl implements ProductTypeManagerDao {
	private McProductInfoManagerDao mcProductInfoManagerDao;
	
	private AttributeManagerDao attributeManagerDao;
	
	public AttributeManagerDao getAttributeManagerDao() {
		return attributeManagerDao;
	}

	public void setAttributeManagerDao(AttributeManagerDao attributeManagerDao) {
		this.attributeManagerDao = attributeManagerDao;
	}

	public McProductInfoManagerDao getMcProductInfoManagerDao() {
		return mcProductInfoManagerDao;
	}

	public void setMcProductInfoManagerDao(
			McProductInfoManagerDao mcProductInfoManagerDao) {
		this.mcProductInfoManagerDao = mcProductInfoManagerDao;
	}

	public void addProductType(ProductType productType) {
		productType.setStatus("1");
		this.productTypeDao.save(productType);
	}
	
	/**
	 * 根据产品类型名称查找是否存在
	 * @param productTypeName
	 * @return boolean
	 */
	public Boolean FindProductTypeByTypeName(String productTypeName) {
		return this.productTypeDao.FindProductTypeByTypeName(productTypeName);
	}
	
	public void modifyProductType(ProductType productType) {
		this.productTypeDao.update(productType);
	}
	
	public void removeProductType(ProductType productType) {
		this.productTypeDao.delete(productType);
	}

	public void removeProductType(Long id) {
		List ids = new ArrayList();
		ids = mcProductInfoManagerDao.retrieveAllMcProductInfosFromTypeId(id);
		for(Iterator item = ids.iterator();item.hasNext();){
		      McProductInfo mcProductInfo = (McProductInfo)item.next();
		      mcProductInfoManagerDao.removeMcProductInfo(mcProductInfo.getId());
		     }
		attributeManagerDao.removeAttributeFromId(id);
		this.productTypeDao.delete(id);
	}
	/*
	 *删除属性 及删除该属性的商品资料
	 *@para id 
	 *@para attributeId
	 */
	public void removeAttributeProduct(Long id , Long attributeId){
		List ids = new ArrayList();
		ids = mcProductInfoManagerDao.retrieveAllMcProductInfosFromTypeId(id);
		for(Iterator item = ids.iterator();item.hasNext();){
		      McProductInfo mcProductInfo = (McProductInfo)item.next();
		      mcProductInfoManagerDao.removeMcProductInfo(mcProductInfo.getId());
		     }
		attributeManagerDao.removeAttribute(attributeId);
	}
	
	public ProductType retrieveProductType(Long id) {
		return (ProductType) this.productTypeDao.get(id);
	}

	@SuppressWarnings("unchecked")
	public List retrieveAllProductTypes() {
		return this.productTypeDao.findAll();
	}
	
 	public boolean findOrderByTypeId(Long typeId){
		List ids = new ArrayList();
		boolean flag=false;
		ids = mcProductInfoManagerDao.retrieveAllMcProductInfosFromTypeId(typeId);
		if(!ObjValid.isValid(ids)){//判断商品资料有没有该类型的商品
			return false;//没有
		}else {//有
			for(Iterator item = ids.iterator();item.hasNext();){
			      McProductInfo mcProductInfo = (McProductInfo)item.next();
			      if(mcProductInfoManagerDao.findOrderByProductId(mcProductInfo.getId()) || 
			    		  mcProductInfoManagerDao.findCartByProductId(mcProductInfo.getId()) ){//true false
			    	  	flag = true;
			      } 
			}
			return flag;
		}
 	}
 	/*
	 *判断是否有该商品存在订单或者购物车中
	 *@param productId 
	 */
	public boolean findOrderAndCartInfobyProductId(Long productId){
		boolean flag=false;
		if(mcProductInfoManagerDao.findOrderByProductId(productId) || 
			    		  mcProductInfoManagerDao.findCartByProductId(productId) ){//true false
			    	  	flag = true;
		} 
		return flag;
	}
	
 	public void updateMcProductInfo(Long typeId){
 		this.productTypeDao.updateMcProductInfo(typeId);
 	}
 	
/*	public static void main(String[] args) {
		  ApplicationContext ct = new ClassPathXmlApplicationContext("applicationContext*.xml");
		  ProductTypeManagerDao dao= (ProductTypeManagerDao)ct.getBean("productTypeManagerDao");
		  dao.removeProductType(22L);
		 }*/
	
}