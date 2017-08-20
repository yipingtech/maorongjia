/*
 * IntergralInfoManagerDaoImpl.java
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
import java.util.Map;

import cc.modules.commons.Pager;
import cc.messcat.entity.IntergralInfo;
import cc.messcat.entity.Member;
import cc.messcat.bases.BaseManagerDaoImpl;

public class IntergralInfoManagerDaoImpl extends BaseManagerDaoImpl implements IntergralInfoManagerDao {

	private static final long serialVersionUID = 1L;

	
	public int countEarnTimes(Member member, String startDate, String endDate){
		return intergralInfoDao.countEarnTimes(member, startDate, endDate);
	}
	
	/**
	 * 添加积分记录（添加[注册赠送和签到]或者抽奖消费）
	 */
	public Double addIntergralInfo(IntergralInfo intergralInfo) {
		intergralInfo.setStatus("1");
		intergralInfo.setIntergral(intergralInfo.getIntergral());
		intergralInfo.setAddTime(new Date());
		intergralInfo.setComments(intergralInfo.getComments());
		intergralInfo.setIntergralStatus("0");                         //还要判断是添加还是消费(0:增加  1:减少)
		intergralInfoDao.save(intergralInfo);
		Map<String,Object> attrs = new HashMap<String, Object>();
		attrs.put("id",intergralInfo.getMemberId().getId());
		attrs.put("status", "1");
		return intergralInfoDao.query(Member.class, attrs).getIntergal()+intergralInfo.getIntergral();     
	}
	
	/**
	 * 查询历史累计积分
	 */
	public int queryTotalInterGralInfo(Member member){
		return intergralInfoDao.queryTotalIntergralInfos(member);
	}
	
	/**
	 * 修改积分记录
	 */
	public void modifyIntergralInfo(IntergralInfo intergralInfo) {
		this.intergralInfoDao.update(intergralInfo);
	}
	
	/**
	 * 删除积分记录
	 */
	public void removeIntergralInfo(IntergralInfo intergralInfo) {
		this.intergralInfoDao.delete(intergralInfo);
	}

	/**
	 * 删除根据id(修改为删除的状态)
	 */
	public void removeIntergralInfo(Long id) {
		Map<String,Object> attrs = new HashMap<String, Object>();
		Map<String,Object> props = new HashMap<String, Object>();
		attrs.put("id",id);
		props.put("status", "0");
		intergralInfoDao.update(IntergralInfo.class, props, attrs);
		//this.intergralInfoDao.delete(id);
	}
	
	/**
	 * 查询根据id(查询单个积分的详细信息)
	 */
	public IntergralInfo retrieveIntergralInfo(Long id) {
		return (IntergralInfo) this.intergralInfoDao.get(id);
	}
	
	/**
	 * 查询单个所有积分记录
	 */
	@SuppressWarnings("unchecked")
	public Pager retrieveAllIntergralByUser(Member member,int pageStart,int pageSize){
		Map<String,Object> attrs = new HashMap<String, Object>();
		attrs.put("status","1");
		if (null!=member) {
			attrs.put("memberId", member);
		}
		return new Pager(pageSize, pageStart, Integer.parseInt(String.valueOf(intergralInfoDao.queryTotalRecord(IntergralInfo.class, attrs))),  intergralInfoDao.queryList(IntergralInfo.class,(pageStart-1)*pageSize,pageSize,"id","desc", attrs));
	}
	
	
	public Pager retrieveIntergralInfosPager(int pageSize, int pageNo) {
		return intergralInfoDao.getPager(pageSize, pageNo);
	}

}