package cc.messcat.service.system;

import cc.messcat.bases.BaseManagerDaoImpl;
import cc.messcat.entity.WebSite;

public class WebSiteManagerDaoImpl extends BaseManagerDaoImpl implements WebSiteManagerDao {

	public WebSiteManagerDaoImpl() {
	}

	public WebSite getWebSite() {
		WebSite webSite = webSiteDao.findWebSite();
		webSite.setWebSkin(this.webSkinDao.get(webSite.getDefaultSkin()));
		return webSite;
	}

	public void addWebSite(WebSite webSite) {
		webSiteDao.save(webSite);
	}

	public void deleteWebSite(Long id) {
		webSiteDao.delete(id);
	}

	public void updateWebSite(WebSite webSite) {
		webSiteDao.update(webSite);
	}
}