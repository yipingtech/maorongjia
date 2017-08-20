// Decompiled by DJ v2.9.9.60 Copyright 2000 Atanas Neshkov  Date: 2010-5-13 14:13:53
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   UsersRolesDaoImpl.java

package cc.messcat.dao.system;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;

import cc.messcat.bases.BaseDaoImpl;
import cc.messcat.entity.UsersRoles;

// Referenced classes of package org.stnet.dao.enterprice.system:
//            UsersRolesDao

public class UsersRolesDaoImpl extends BaseDaoImpl implements UsersRolesDao {

	public UsersRolesDaoImpl() {
	}

	public void delete(UsersRoles usersRoles) {
		getHibernateTemplate().delete(usersRoles);
	}

	public void delete(Long id) {
		getHibernateTemplate().delete(get(id));
	}

	public List findAll() {
		List find = getHibernateTemplate().find("from UsersRoles");
		return find;
	}

	public UsersRoles get(Long id) {
		return (UsersRoles) getHibernateTemplate().load(UsersRoles.class, id);
	}

	public void save(UsersRoles usersRoles) {
		getHibernateTemplate().save(usersRoles);
	}

	public void update(UsersRoles usersRoles) {
		getSessionFactory().getCurrentSession().clear();
		getHibernateTemplate().saveOrUpdate(usersRoles);
	}

	public List findByUsersId(Long id) {
		return getHibernateTemplate().find("from UsersRoles where users.id = ?", id);
	}

	public void deleteUsersRoleByUserId(Long userId) {
		Session session = getHibernateTemplate().getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		StringBuffer sql = new StringBuffer();
		sql.append("delete UsersRoles where users.id=" + userId);
		Query query = session.createQuery(sql.toString());
		//int dels =
		query.executeUpdate();
		tx.commit();
		session.close();
	}

	public List findByRolesId(Long id) {
		return getHibernateTemplate().find("from UsersRoles where roles.id = ?", id);
	}

	/**
	 * 是否能够删除
	 */
	@Override
	public boolean isCanBeDelete(Long id) {
		Session session = getHibernateTemplate().getSessionFactory().openSession();
		String sql = "select * from users_roles where ROLE_ID ="+id;
		Query query = session.createSQLQuery(sql);
		List result = query.list();
		if(result.size()>0){
			return false;
		}
		return true;
	}
}