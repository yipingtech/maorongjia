/*
 * TicketInfoManagerDaoImpl.java
 * 
 * Create by MCGT
 * 
 * Create time 2015-06-01
 * 
 * Version 1.0
 * 
 * Copyright 2013 Messcat, Inc. All rights reserved.
 * 
 */
package cc.messcat.service.finance;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cc.messcat.bases.BaseManagerDaoImpl;
import cc.messcat.entity.Member;
import cc.messcat.entity.MemberTicket;
import cc.messcat.entity.TicketInfo;
import cc.modules.commons.Pager;
import cc.modules.util.DateHelper;

public class TicketInfoManagerDaoImpl extends BaseManagerDaoImpl implements TicketInfoManagerDao {
	private static Logger log = LoggerFactory.getLogger(TicketInfoManagerDaoImpl.class); 
	
	/**
	 * 商家添加卡券配置
	 */
	public void addTicketInfo(TicketInfo ticketInfo) {
		ticketInfo.setStatus("1");
		this.ticketInfoDao.save(ticketInfo);
	}
	
	/**
	 * 商家修改卡券配置
	 */
	public void modifyTicketInfo(TicketInfo ticketInfo) {
		this.ticketInfoDao.update(ticketInfo);
	}
	
	/**
	 * 商家移除卡券配置
	 */
	public void removeTicketInfo(TicketInfo ticketInfo) {
		this.ticketInfoDao.delete(ticketInfo);
	}

	/**
	 * 商家根据id移除卡券配置
	 */
	public void removeTicketInfo(Long id) {
		Map<String,Object> attrs = new HashMap<String, Object>();
		Map<String,Object> props = new HashMap<String, Object>();
		attrs.put("id", id);
		attrs.put("status", "1");
		props.put("status", "0");
		this.ticketInfoDao.update(TicketInfo.class, props, attrs);
	}
	
	/**
	 * 根据id查询卡券配置
	 */
	public TicketInfo retrieveTicketInfo(Long id) {
		return (TicketInfo) this.ticketInfoDao.get(id);
	}

	/**
	 * 商家查询所有的卡券配置
	 */
	public Pager retrieveAllTicketInfos(int pageStart,int pageSize) {
		/*Map<String,Object> attrs = new HashMap<String, Object>();
		attrs.put("status", "1");
		return new Pager(pageSize, pageStart, Integer.parseInt(String.valueOf(ticketInfoDao.queryTotalRecord(TicketInfo.class, attrs))),
				ticketInfoDao.queryList(TicketInfo.class,(pageStart-1)*pageSize,pageSize,"id", "desc", attrs));*/
		return ticketInfoDao.getPager(pageSize, pageStart);
	}
	
	/**
	 * 用户获得卡券
	 * @param member
	 * @param ticketInfo
	 * @return
	 */
	public String addMemberTicket(Member member,Long id){
		MemberTicket memberTicket = new MemberTicket();
		TicketInfo ticketInfo=ticketInfoDao.get(id);
		memberTicket.setMemberId(member);
		memberTicket.setTicketId(ticketInfo);
		memberTicket.setAddTime(new Date());
		memberTicket.setOverTime(DateHelper.getDateByCalculateDays(new Date(),Integer.parseInt(String.valueOf(ticketInfo.getRestrictDay()))));
		log.info(DateHelper.getDateByCalculateDays(new Date(),Integer.parseInt(String.valueOf(ticketInfo.getRestrictDay())))+"---------------------------");
		memberTicket.setStatus("0");
		ticketInfoDao.save(memberTicket);
		return "添加成功";		
	}
	
	/**
	 * 用户消费卡券
	 * @param id
	 * @return
	 */
	public String updateMemberTicket(Long id){
		Map<String,Object> attrs = new HashMap<String, Object>();
		Map<String,Object> props = new HashMap<String, Object>();
		attrs.put("id", id);
		props.put("status", "1");
		ticketInfoDao.update(MemberTicket.class, props, attrs);
		return "消费成功";
	}
	
	/**
	 * 用户查询自己的所有卡券（未使用，已使用，已过期）
	 * @param member
	 * @return
	 */
	public Pager queryTicketByMember(Member member,int pageStart,int pageSize,int flag){
		return ticketInfoDao.queryTicketByMember(member,pageStart, pageSize, flag);
	}
	
	
	public Pager retrieveTicketInfosPager(int pageSize, int pageNo) {
		return this.ticketInfoDao.getPager(pageSize, pageNo);
	}

}