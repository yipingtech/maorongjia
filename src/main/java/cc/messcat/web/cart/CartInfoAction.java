/*
 * CartInfoAction.java
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
package cc.messcat.web.cart;

import java.util.List;

import cc.modules.commons.PageAction;
import cc.messcat.entity.CartInfo;
import cc.messcat.entity.McProductInfo;

/**
 * @描述 购物车action
 * @author karhs
 * @date 2015-4-14
 */
public class CartInfoAction extends PageAction {

	private static final long serialVersionUID = 1L;
	private Long id;	
	private CartInfo cartInfo;	
	private List<CartInfo> cartInfos;
	
	/**
	 * 添加产品到购物车
	 * @param product
	 * @param productAttrId
	 * @return
	 * 需要帮到前台
	 */
	/*public String addToCart(McProductInfo product, String productAttrId){
		CartInfo cart = new CartInfo();
		cart.setProduct(product);
		cart.setMember(member);
		cart.setProductAttrIds(productAttrIds);
		cart.setBuyAmount(buyAmount);
		cart.setProductPrice(productPrice);
		cart.setProductTotal(productTotal);
		this.cartInfoManagerDao.addCartInfo(cart);
		return "success";
	}*/
	
	@SuppressWarnings("unchecked")
	public String retrieveAllCartInfos() throws Exception {
		try {
			super.pager = this.cartInfoManagerDao.retrieveCartInfosPager(pageSize, pageNo);
			this.cartInfos = super.pager.getResultList();
		} catch (Exception ex) {
			addActionMessage("Load page error!");
		}
		return "success";
	}
	
	public String retrieveCartInfoById() throws Exception {
		try {
			this.cartInfo = this.cartInfoManagerDao.retrieveCartInfo(id);
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
			this.cartInfo = this.cartInfoManagerDao.retrieveCartInfo(id);
		} catch (Exception ex) {
			addActionMessage("Load page error!");
		}
		return "viewPage";
	}
	
	public String newCartInfo() throws Exception {
		try {
			this.cartInfoManagerDao.addCartInfo(this.cartInfo);
			addActionMessage("New successfully!");
		} catch (Exception ex) {
			this.addActionError(ex.getMessage());
			addActionMessage("New fail!");
		}
		return "edit_success";
	}
	
	public String editCartInfo() throws Exception {
		try {
			CartInfo cartInfo0 = this.cartInfoManagerDao.retrieveCartInfo(this.id);
			cartInfo0.setMember(this.cartInfo.getMember());
			cartInfo0.setProduct(this.cartInfo.getProduct());
			cartInfo0.setProductPrice(this.cartInfo.getProductPrice());
			cartInfo0.setProductTotal(this.cartInfo.getProductTotal());
			cartInfo0.setBuyAmount(this.cartInfo.getBuyAmount());
			cartInfo0.setCreateTime(this.cartInfo.getCreateTime());

			this.cartInfoManagerDao.modifyCartInfo(cartInfo0);
			addActionMessage("Update successfully!");
		} catch (Exception ex) {
			this.addActionError(ex.getMessage());
			addActionMessage("Update fail!");
		}
		return "edit_success";
	}
	
	public String delCartInfo() throws Exception {
		try {
			this.cartInfoManagerDao.removeCartInfo(this.id);
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

	public CartInfo getCartInfo() {
		return cartInfo;
	}

	public void setCartInfo(CartInfo cartInfo) {
		this.cartInfo = cartInfo;
	}

	public List<CartInfo> getCartInfos() {
		return cartInfos;
	}

	public void setCartInfos(
			List<CartInfo> cartInfos) {
		this.cartInfos = cartInfos;
	}

}