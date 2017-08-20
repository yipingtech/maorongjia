/*
 * MemberBonusManagerDao.java
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

import java.util.List;
import cc.modules.commons.Pager;
import cc.messcat.entity.Member;
import cc.messcat.entity.MemberBonus;

public interface MemberBonusManagerDao {

	public void addMemberBonus(MemberBonus memberBonus);
	
	public void modifyMemberBonus(MemberBonus memberBonus);
	
	public void removeMemberBonus(MemberBonus memberBonus);
	
	public void removeMemberBonus(Long id);
	
	public MemberBonus retrieveMemberBonus(Long id);
	
	@SuppressWarnings("unchecked")
	public List retrieveAllMemberBonuss();
	
	/**
	 * 红包标为已用
	 */
	public void changeBonusUnable(Long id);
	
	/**
	 * 根据status状态的不同分可用，已用
	 * @param member
	 * @param status
	 * @return
	 */
	public List<MemberBonus> queryBonus(Member member,String status);
	
	/**
	 * 查询过期的红包
	 * @param member
	 * @param status
	 * @return
	 */
	public List<MemberBonus> queryOverdueBonus(Member member);
	
	public Pager retrieveMemberBonussPager(int pageSize, int pageNo);
	
}