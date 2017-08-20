/*
 * CartInfoManagerDaoImpl.java
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
package cc.messcat.service.cart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;

import cc.messcat.bases.BaseManagerDaoImpl;
import cc.messcat.entity.Attribute;
import cc.messcat.entity.CartInfo;
import cc.messcat.entity.Member;
import cc.messcat.entity.ProductAttr;
import cc.messcat.entity.Stock;
import cc.messcat.vo.CartInfoVo;
import cc.modules.commons.Pager;
import cc.modules.util.FormatStringUtil;
import cc.modules.util.ObjValid;

public class CartInfoManagerDaoImpl extends BaseManagerDaoImpl implements CartInfoManagerDao {

	public void addCartInfo(CartInfo cartInfo) {
		this.cartInfoDao.save(cartInfo);
	}
	
	public void modifyCartInfo(CartInfo cartInfo) {
		this.cartInfoDao.update(cartInfo);
	}
	
	public void removeCartInfo(CartInfo cartInfo) {
		this.cartInfoDao.delete(cartInfo);
	}

	public void removeCartInfo(Long id) {
		this.cartInfoDao.delete(id);
	}
	
	public CartInfo retrieveCartInfo(Long id) {
		return (CartInfo) this.cartInfoDao.get(id);
	}

	@SuppressWarnings("unchecked")
	public List retrieveAllCartInfos() {
		return this.cartInfoDao.findAll();
	}
	
	public Pager retrieveCartInfosPager(int pageSize, int pageNo) {
		return this.cartInfoDao.getPager(pageSize, pageNo);
	}
	
	/**商品详情页点击直接购买 -- 单条数据
	 * 构造我的订单信息
	 * @param member
	 * @param cartInfoPro 购物车信息
	 * @return
	 */
	public List<CartInfoVo> buildCart(Member member,CartInfo cartInfoPro){
		Map<String, Object> attrs = new HashMap<String, Object>();
		attrs.put("member", cartInfoPro.getMember());
		attrs.put("product", cartInfoPro.getProduct());
		attrs.put("productAttrIds", cartInfoPro.getProductAttrIds());
		CartInfo newCart = this.cartInfoDao.query(CartInfo.class, attrs);
		Map session = ActionContext.getContext().getSession();
		session.put("cartInfoIDS", newCart.getId());
		//判断是否已有相同的产品存在购物车，若有就只做修改，没有就添加
		List<CartInfo> cartList = new ArrayList<CartInfo>();
		if (ObjValid.isValid(newCart)) {
			cartList.add(newCart);
		}
		List<CartInfoVo> cartInfoVoList = new ArrayList<CartInfoVo>();
		//遍历构造购物车vo类
		for (CartInfo cartInfo : cartList) {
			CartInfoVo cartInfoVo = new CartInfoVo();
			LinkedHashMap<Attribute, String> attributeMap = new LinkedHashMap<Attribute, String>();
			List<String> proAttrIdList = FormatStringUtil.splitBySign(cartInfo.getProductAttrIds(), ",");
			if(ObjValid.isValid(proAttrIdList)){
				for (int i = 0; i < proAttrIdList.size(); i++) {
					ProductAttr productAttr = this.productAttrDao.get(Long.parseLong(proAttrIdList.get(i)));
					attributeMap.put(productAttr.getAttr(), productAttr.getAttrValue());
				}
			}
			cartInfoVo.setId(cartInfo.getId());
			cartInfoVo.setAttributeMap(attributeMap);
			cartInfoVo.setBuyAmount(cartInfo.getBuyAmount());
			cartInfoVo.setProduct(cartInfo.getProduct());
			cartInfoVo.setProductAttrIds(cartInfo.getProductAttrIds());
			cartInfoVo.setProductPrice(cartInfo.getProductPrice());
			cartInfoVo.setProductTotal(cartInfo.getProductTotal());
			cartInfoVoList.add(cartInfoVo);
		}
		return cartInfoVoList;
	}
	
	/**
	 * 构造我的订单信息
	 * @param member
	 * @param cartInfoIds 购物车ids
	 * @return
	 */
	public List<CartInfoVo> buildCart(Member member,String cartInfoIds){
		List<CartInfoVo> cartInfoVoList = new ArrayList<CartInfoVo>();
		Map<String, Object> attrs = new HashMap<String, Object>();
		attrs.put("member", member);
		//查找该会员的购物车
		List<CartInfo> cartList = this.cartInfoDao.queryListByIds(CartInfo.class,cartInfoIds);
		//遍历构造购物车vo类
		for (CartInfo cartInfo : cartList) {
			CartInfoVo cartInfoVo = new CartInfoVo();
			LinkedHashMap<Attribute, String> attributeMap = new LinkedHashMap<Attribute, String>();
			List<String> proAttrIdList = FormatStringUtil.splitBySign(cartInfo.getProductAttrIds(), ",");
			for (int i = 0; i < proAttrIdList.size(); i++) {
				ProductAttr productAttr = this.productAttrDao.get(Long.parseLong(proAttrIdList.get(i)));
				attributeMap.put(productAttr.getAttr(), productAttr.getAttrValue());
			}
			cartInfoVo.setId(cartInfo.getId());
			cartInfoVo.setAttributeMap(attributeMap);
			cartInfoVo.setBuyAmount(cartInfo.getBuyAmount());
			cartInfoVo.setProduct(cartInfo.getProduct());
			cartInfoVo.setProductAttrIds(cartInfo.getProductAttrIds());
			cartInfoVo.setProductPrice(cartInfo.getProductPrice());
			cartInfoVo.setProductTotal(cartInfo.getProductTotal());
			cartInfoVoList.add(cartInfoVo);
		}
		return cartInfoVoList;
	}
	
	@SuppressWarnings("unchecked")
	public List<CartInfoVo> buildCart(Member member){
		List<CartInfoVo> cartInfoVoList = new ArrayList<CartInfoVo>();
		Map<String, Object> attrs = new HashMap<String, Object>();
		attrs.put("member", member);
		//查找改会员的购物车
		List<CartInfo> cartList = this.cartInfoDao.queryList(CartInfo.class, "id", "desc", attrs);
		//遍历构造购物车vo类
		for (CartInfo cartInfo : cartList) {
			Stock condition = new Stock();
			condition.setProductAttrIds(cartInfo.getProductAttrIds());
			condition.setProduct(cartInfo.getProduct());
			Stock stock = this.stockDao.findByProAttr(condition);
			CartInfoVo cartInfoVo = new CartInfoVo();
			LinkedHashMap<Attribute, String> attributeMap = new LinkedHashMap<Attribute, String>();
			List<String> proAttrIdList = FormatStringUtil.splitBySign(cartInfo.getProductAttrIds(), ",");
			boolean isDelete = false;
			if (ObjValid.isValid(proAttrIdList) && proAttrIdList.size() > 0) {
				for (int i = 0; i < proAttrIdList.size(); i++) {
					ProductAttr productAttr = this.productAttrDao.get(Long.parseLong(proAttrIdList.get(i)));
					if(ObjValid.isValid(productAttr)){
						if ((cartInfo.getProductPrice().toString()).equals(stock.getPrice().toString())) {
							attributeMap.put(productAttr.getAttr(), productAttr.getAttrValue());
						}
					}else{
						isDelete = true;
					}
				}
			}
			if(isDelete){
				attributeMap.clear();
			}
			cartInfoVo.setId(cartInfo.getId());
			cartInfoVo.setAttributeMap(attributeMap);
			cartInfoVo.setBuyAmount(cartInfo.getBuyAmount());
			cartInfoVo.setProduct(cartInfo.getProduct());
			cartInfoVo.setProductAttrIds(cartInfo.getProductAttrIds());
			cartInfoVo.setProductPrice(cartInfo.getProductPrice());
			cartInfoVo.setProductTotal(cartInfo.getProductTotal());
			cartInfoVoList.add(cartInfoVo);
		}
		return cartInfoVoList;
	}

	public void addProductToCart(CartInfo cart){
		Map<String, Object> attrs = new HashMap<String, Object>();
		attrs.put("member", cart.getMember());
		attrs.put("product", cart.getProduct());
		attrs.put("productAttrIds", cart.getProductAttrIds());
		CartInfo newCart = this.cartInfoDao.query(CartInfo.class, attrs);
		//判断是否已有相同的产品存在购物车，若有就只做修改，没有就添加
		if (ObjValid.isValid(newCart)) {
			//newCart.setBuyAmount(cart.getBuyAmount()+newCart.getBuyAmount());
			newCart.setBuyAmount(cart.getBuyAmount());
//			newCart.setProductTotal(cart.getProductTotal()+newCart.getProductTotal());
			newCart.setProductTotal(cart.getProductTotal());
			this.cartInfoDao.update(newCart);
		}else {
			try {
				this.cartInfoDao.save(cart);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void addProductToShoppingCart(CartInfo cart) {
		Map<String, Object> attrs = new HashMap<String, Object>();
		attrs.put("member", cart.getMember());
		attrs.put("product", cart.getProduct());
		attrs.put("productAttrIds", cart.getProductAttrIds());
		CartInfo newCart = this.cartInfoDao.query(CartInfo.class, attrs);
		//判断是否已有相同的产品存在购物车，若有就只做修改，没有就添加
		if (ObjValid.isValid(newCart)) {
			newCart.setBuyAmount(cart.getBuyAmount()+newCart.getBuyAmount());
     		newCart.setProductTotal(cart.getProductTotal()+newCart.getProductTotal());
			this.cartInfoDao.update(newCart);
		}else {
			try {
				this.cartInfoDao.save(cart);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
}