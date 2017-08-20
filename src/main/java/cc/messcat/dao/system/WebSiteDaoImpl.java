package cc.messcat.dao.system;

import java.util.List;

import cc.messcat.bases.BaseDaoImpl;
import cc.messcat.entity.WebSite;

public class WebSiteDaoImpl extends BaseDaoImpl implements WebSiteDao {

	public WebSiteDaoImpl() {
	}

	public void delete(WebSite webSite) {
		getHibernateTemplate().delete(webSite);
	}

	public void delete(Long id) {
		getHibernateTemplate().delete(get(id));
	}

	public WebSite get(Long id) {
		return (WebSite) getHibernateTemplate().get(WebSite.class, id);

	}

	public void save(WebSite webSite) {
		getHibernateTemplate().save(webSite);
	}

	public void update(WebSite webSite) {
		getHibernateTemplate().merge(webSite);
	}

	public WebSite findWebSite() {
		List list = getHibernateTemplate().find("from WebSite");
		if (list != null)
			return (WebSite) list.get(0);
		else
			return null;
	}
}