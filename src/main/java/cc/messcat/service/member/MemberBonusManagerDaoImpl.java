/*
 * MemberBonusManagerDaoImpl.java
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
import cc.messcat.entity.Member;
import cc.messcat.entity.MemberBonus;
import cc.messcat.bases.BaseManagerDaoImpl;

public class MemberBonusManagerDaoImpl extends BaseManagerDaoImpl implements MemberBonusManagerDao {

	/**
	 * 添加红包
	 */
	public void addMemberBonus(MemberBonus memberBonus) {
		this.memberBonusDao.save(memberBonus);
	}
	
	/**
	 * 跟新红包
	 */
	public void modifyMemberBonus(MemberBonus memberBonus) {
		this.memberBonusDao.update(memberBonus);
	}
	
	
	/**
	 * 删除红包根据对像
	 */
	public void removeMemberBonus(MemberBonus memberBonus) {
		this.memberBonusDao.delete(memberBonus);
	}

	
	/**
	 * 删除红包根据id
	 */
	public void removeMemberBonus(Long id) {
		this.memberBonusDao.delete(id);
	}
	
	
	/**
	 * 查询红包根据id
	 */
	public MemberBonus retrieveMemberBonus(Long id) {
		return (MemberBonus) this.memberBonusDao.get(id);
	}

	/**
	 * 查询当个用户的红包
	 */
	@SuppressWarnings("unchecked")
	public List retrieveAllMemberBonuss() {
		return this.memberBonusDao.findAll();
	}
	
	/**
	 * 红包标为已用
	 */
	public void changeBonusUnable(Long id){
		memberBonusDao.changeBonusUnable(id);
	}
	
	/**
	 * 根据status状态的不同分可用，已用
	 * @param member
	 * @param status
	 * @return
	 */
	public List<MemberBonus> queryBonus(Member member,String status){
		return memberBonusDao.queryBonus(member, status);
	}
	
	/**
	 * 查询过期的红包
	 * @param member
	 * @param status
	 * @return
	 */
	public List<MemberBonus> queryOverdueBonus(Member member){
		return memberBonusDao.queryOverdueBonus(member);
	}
	
	public Pager retrieveMemberBonussPager(int pageSize, int pageNo) {
		return this.memberBonusDao.getPager(pageSize, pageNo);
	}

}