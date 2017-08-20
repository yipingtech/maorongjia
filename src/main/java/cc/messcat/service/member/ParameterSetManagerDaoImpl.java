/*
 * ParameterSetManagerDaoImpl.java
 * 
 * Create by MCGT
 * 
 * Create time 2015-04-24
 * 
 * Version 1.0
 * 
 * Copyright 2013 Messcat, Inc. All rights reserved.
 * 
 */
package cc.messcat.service.member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cc.modules.commons.Pager;
import cc.modules.util.FormatStringUtil;
import cc.modules.util.ObjValid;
import cc.messcat.entity.ParameterSet;
import cc.messcat.bases.BaseManagerDaoImpl;

public class ParameterSetManagerDaoImpl extends BaseManagerDaoImpl implements ParameterSetManagerDao {

	public void addParameterSet(ParameterSet parameterSet) {
		this.parameterSetDao.save(parameterSet);
	}
	
	/**
	 * 修改默认配置
	 */
	public void modifyParameterSet(ParameterSet parameterSet) {		
		Map<String, Object> attrs = new HashMap<String, Object>();
		Map<String, Object> props = new HashMap<String, Object>();
		attrs.put("id", parameterSet.getId());
		props.put("conditionSend", parameterSet.getConditionSend());
		props.put("agentBonusSet", parameterSet.getAgentBonusSet());
		props.put("agentCompensate", parameterSet.getAgentCompensate());
		props.put("agentAmount", parameterSet.getAgentAmount());
		props.put("saleRoyaltyRate", parameterSet.getSaleRoyaltyRate());
		props.put("timeLimit", parameterSet.getTimeLimit());
		props.put("consigneeTime", parameterSet.getConsigneeTime());
		props.put("redPacketNum", parameterSet.getRedPacketNum());
		props.put("discountCouponNum", parameterSet.getDiscountCouponNum());
		props.put("sellerName", parameterSet.getSellerName());
		props.put("sellerPhone", parameterSet.getSellerPhone());
		props.put("sellerAddress", parameterSet.getSellerAddress());
		props.put("vipMember", parameterSet.getVipMember());
		props.put("svip", parameterSet.getSvip());
		props.put("diamondMember", parameterSet.getDiamondMember());
		props.put("topUpGifts", parameterSet.getTopUpGifts());
		props.put("inviteMemberNum", parameterSet.getInviteMemberNum());
		parameterSetDao.update(ParameterSet.class, props, attrs);
	}
	
	public void removeParameterSet(ParameterSet parameterSet) {
		this.parameterSetDao.delete(parameterSet);
	}

	public void removeParameterSet(Long id) {
		this.parameterSetDao.delete(id);
	}
	
	/**
	 * 查询当前配置
	 */
	public ParameterSet retrieveParameterSet() {
		//return (ParameterSet) this.parameterSetDao.get(id);
		return (ParameterSet) this.retrieveAllParameterSets().get(0);
	}

	@SuppressWarnings("unchecked")
	public List retrieveAllParameterSets() {
		return this.parameterSetDao.findAll();
	}
	
	//查询佣金等配置
	public List<String> queryParameter(){
		List<ParameterSet> parameterSetList = this.retrieveAllParameterSets();
		ParameterSet parameterSet = new ParameterSet();
		if (ObjValid.isValid(parameterSetList)) {
			parameterSet =parameterSetList.get(0);
		}
		List<String> rateList = FormatStringUtil.splitBySign(parameterSet.getSaleRoyaltyRate(), ",");
		return rateList;
	}
	
	public Pager retrieveParameterSetsPager(int pageSize, int pageNo) {
		return this.parameterSetDao.getPager(pageSize, pageNo);
	}

}