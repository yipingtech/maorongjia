package cc.messcat.service.system;

import cc.messcat.entity.WebSite;

/**
 * @author Messcat
 * @version 1.1
 */
public interface WebSiteManagerDao {
	public WebSite getWebSite();

	public void addWebSite(WebSite website);

	public void updateWebSite(WebSite website);

	public void deleteWebSite(Long long1);
}