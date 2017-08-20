/*
 * RechargeInfoManagerDaoImpl.java
 * 
 * Create by MCGT
 * 
 * Create time 2015-04-22
 * 
 * Version 1.0
 * 
 * Copyright 2013 Messcat, Inc. All rights reserved.
 * 
 */
package cc.messcat.service.member;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;

import cc.modules.commons.Pager;
import cc.messcat.entity.Member;
import cc.messcat.entity.RechargeInfo;
import cc.messcat.bases.BaseManagerDaoImpl;

public class RechargeInfoManagerDaoImpl extends BaseManagerDaoImpl implements RechargeInfoManagerDao {

	//保存记录
	public void addRechargeInfo(RechargeInfo rechargeInfo) {
		rechargeInfo.setRechargeTime(new Date());
		rechargeInfo.setStatus("1");
		this.rechargeInfoDao.save(rechargeInfo);
	}
	
	//修改记录（还没用上）
	public void modifyRechargeInfo(RechargeInfo rechargeInfo) {
		this.rechargeInfoDao.update(rechargeInfo);
	}

	//删除余额充值消费记录
	public void removeRechargeInfo(Long id) {
		Map<String,Object> attrs = new HashMap<String, Object>();
		Map<String,Object> props = new HashMap<String, Object>();
		attrs.put("status","1");
		attrs.put("id", id);
		props.put("status", "0");
		rechargeInfoDao.update(RechargeInfo.class, props, attrs);
	}
	
	//根据id查询单个对象
	public RechargeInfo retrieveRechargeInfo(Long id) {
		return (RechargeInfo) this.rechargeInfoDao.get(id);
	}
		
	//查询单个用户所有余额记录(单个用户的列表分页)
	@SuppressWarnings("unchecked")
	public Pager retrieveAllRechargeInfos(int pageStart,int pageSize,Member member) {
		Map<String,Object> attrs = new HashMap<String, Object>();
		attrs.put("status","1");
		if(null!=member&&!("".equals(member))){
		attrs.put("memberId", member);
		}
		return new Pager(pageSize, pageStart, Integer.parseInt(String.valueOf(rechargeInfoDao.queryTotalRecord(RechargeInfo.class, attrs))),rechargeInfoDao.queryList(RechargeInfo.class,(pageStart-1)*pageSize,pageSize, "id", "desc", attrs));
		//this.rechargeInfoDao.queryAllRecharge(pageStart, pageSize, member);
	}
	
	/**
	 * 查询历史累计佣金余额
	 */
	public Double queryTotalCommission(Member member){
		return rechargeInfoDao.queryTotalRechargeInfo(member);
	}

	public Pager retrieveRechargeInfosPager(int pageSize, int pageNo) {
		return this.rechargeInfoDao.getPager(pageSize, pageNo);
	}

}