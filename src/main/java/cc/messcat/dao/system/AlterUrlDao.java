/*
 * AlterUrlDao.java
 * 
 * Create by Andy Lin
 * 
 * Create time 2013-08-20
 * 
 * Version 1.0
 * 
 * Copyright 2013 Messcat, Inc. All rights reserved.
 * 
 */
package cc.messcat.dao.system;

import cc.messcat.front.AlterUrlBean;


public interface AlterUrlDao {
	
	//根据查询条件查找记录数
	public Long getCountByCondition(AlterUrlBean condition);

	//更新数据库中的URL记录
	public Long updateUrl(AlterUrlBean alterUrlBean);

}