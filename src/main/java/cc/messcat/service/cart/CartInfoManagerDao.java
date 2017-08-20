/*
 * CartInfoManagerDao.java
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

import java.util.List;
import cc.modules.commons.Pager;
import cc.messcat.entity.CartInfo;
import cc.messcat.entity.Member;
import cc.messcat.vo.CartInfoVo;

public interface CartInfoManagerDao {

	public void addCartInfo(CartInfo cartInfo);
	
	public void modifyCartInfo(CartInfo cartInfo);
	
	public void removeCartInfo(CartInfo cartInfo);
	
	public void removeCartInfo(Long id);
	
	public CartInfo retrieveCartInfo(Long id);
	
	@SuppressWarnings("unchecked")
	public List retrieveAllCartInfos();
	
	public Pager retrieveCartInfosPager(int pageSize, int pageNo);
	
	/**
	 * 构造我的购物车
	 * @param member
	 * @return
	 */
	public List<CartInfoVo> buildCart(Member member);
	
	/**
	 * 构造我的订单信息
	 * @param member
	 * @param cartInfoIds 购物车ids
	 * @return
	 */
	public List<CartInfoVo> buildCart(Member member,String cartInfoIds);
	
	/**商品详情页点击直接购买 -- 单条数据
	 * 构造我的订单信息
	 * @param member
	 * @param cartInfoPro 购物车信息
	 * @return
	 */
	public List<CartInfoVo> buildCart(Member member,CartInfo cartInfoPro);
	
	
	/**
	 * 添加产品到购物车（重复的产品合并）---改为添加产品到订单中
	 * @param cart
	 */
	public void addProductToCart(CartInfo cart);
	
	/**
	 * 添加产品到购物车中 =======  新
	 * */
	public void addProductToShoppingCart(CartInfo cart);
	
}