/*
 * MemberBonusDao.java
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
package cc.messcat.dao.member;

import java.util.List;
import cc.modules.commons.Pager;
import cc.messcat.bases.BaseDao;
import cc.messcat.entity.Member;
import cc.messcat.entity.MemberBonus;

public interface MemberBonusDao extends BaseDao{

	public void save(MemberBonus memberBonus);
	
	public void update(MemberBonus memberBonus);
	
	public void delete(MemberBonus memberBonus);
	
	public void delete(Long id);
	
	public MemberBonus get(Long id);
	
	@SuppressWarnings("unchecked")
	public List findAll();
	
	public Pager getPager(int pageSize, int pageNo);
	
	/**
	 * 查询过期红包(设定的时间小于当前时间)
	 * @param member
	 * @return
	 */
	public List<MemberBonus> queryOverdueBonus(Member member);
	
	/**
	 * 查询未用和已用红包(设定的时间还大于当前时间就是未过期)
	 * @param member
	 * @param status
	 * @return
	 */
	public List<MemberBonus> queryBonus(Member member,String status);
	
	/**
	 * 红包标为已用
	 * @param id
	 */
	public void changeBonusUnable(Long id);
	
}