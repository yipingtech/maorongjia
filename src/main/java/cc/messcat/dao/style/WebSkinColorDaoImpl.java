// Decompiled by DJ v2.9.9.60 Copyright 2000 Atanas Neshkov  Date: 2010-5-13 14:13:29
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   UsersDaoImpl.java

package cc.messcat.dao.style;

import java.util.List;

import org.hibernate.FlushMode;

import cc.messcat.bases.BaseDaoImpl;
import cc.messcat.entity.WebSkinColor;

// Referenced classes of package org.stnet.dao.enterprice.system:
//            UsersDao

public class WebSkinColorDaoImpl extends BaseDaoImpl implements WebSkinColorDao {

	public WebSkinColorDaoImpl() {
	}

	public void delete(WebSkinColor webSkinColor) {
		getHibernateTemplate().delete(webSkinColor);
	}

	/* (non-Javadoc)
	 * @see org.stnet.dao.enterprice.style.WebSkinDao#delete(java.lang.Long)
	 */
	public void delete(Long id) {
		getHibernateTemplate().delete(get(id));
	}

	public List findAll() {
		List find = getHibernateTemplate().find("from WebSkinColor");
		return find;
	}

	public void save(WebSkinColor webSkinColor) {
		getSessionFactory().getCurrentSession().setFlushMode(FlushMode.AUTO);
		getHibernateTemplate().save(webSkinColor);
	}

	public void update(WebSkinColor webSkinColor) {
		getSessionFactory().getCurrentSession().clear();
		getHibernateTemplate().saveOrUpdate(webSkinColor);
	}

	public boolean isNameUnique(String names) {
		List temp = getHibernateTemplate().find("from WebSkinColor where names = ?", names.trim());
		return temp.size() <= 0;
	}

	public WebSkinColor get(Long id) {
		return (WebSkinColor) getHibernateTemplate().get(WebSkinColor.class, id);
	}

	public List findAllByWebSkinId(Long webSkinId) {
		return getHibernateTemplate().find("from WebSkinColor where webSkinId = ?", webSkinId);
	}

	public WebSkinColor getWebSkinColorByWebSkinId(Long webSkinId) {

		List temp = getHibernateTemplate().find("from WebSkinColor where webSkinId = ? and isDefaultId = '1' ", webSkinId);
		if (temp.size() > 0)
			return (WebSkinColor) temp.get(0);
		return null;

	}

}
