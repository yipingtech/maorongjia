package cc.messcat.web.system;

import java.util.List;
import java.util.Map;

import org.springframework.security.Authentication;
import org.springframework.security.context.SecurityContext;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.security.userdetails.UserDetails;

import cc.messcat.bases.BaseAction;
import cc.messcat.entity.Users;
import cc.messcat.entity.WebSite;
import cc.messcat.service.system.UsersManagerDao;
import cc.messcat.web.left.Authorize;
import cc.modules.util.ParseXmlToBean;

import com.opensymphony.xwork2.ActionContext;

@SuppressWarnings("unchecked")
public class LoginAction extends BaseAction {

	private static final long serialVersionUID = -5876455173672710380L;

	private List menuList;

	public LoginAction() {
	}

	public String execute() throws Exception {
		SecurityContext sc = SecurityContextHolder.getContext();
		Authentication auth = sc.getAuthentication();
		UserDetails ud = null;
		try {
			ud = (UserDetails) auth.getPrincipal();
		} catch (Exception e) {
			addActionMessage("登陆出错");
			return "input";
		}
		Map session = (Map) ActionContext.getContext().getSession();

		String username = ud.getUsername();
		String result_value = "success";

		Users users = usersManagerDao.getUsersByLoginName(username);

		if (session.get("users") != null)
			session.remove("users");

		session.put("users", users);

		if (session.get("currentUserName") != null)
			session.remove("currentUserName");

		session.put("currentUserName", ud.getUsername());

		if (session.get("userID") != null)
			session.remove("userID");

		session.put("userID", users.getId());
		if (session.get("isAdmin") != null)
			session.remove("isAdmin");

		session.put("isAdmin", isAdmin(users));

		webSite = webSiteManagerDao.getWebSite();

		// 加载菜单
		this.menuList = ParseXmlToBean.parserXml("Menu");

		return result_value;
	}

	private UsersManagerDao usersManagerDao;
	private WebSite webSite;

	public int isAdmin(Users users) {

		/**
		 * 清洁网旧有代码
		 */
		/*
		 * Long rolesId = null; // 如果管理员身份中只有一个角色，并且只有等于 4
		 * 既然“区/县管理员”,如果等于2为地方管理员 // 省厅管理员 等于1 // isAdmin=4 》区/县管理员 //
		 * isAdmin=2》地方管理员 // isAdmin=1》省厅管理员 if (users.getUsersRoleses().size()
		 * == 1) { Iterator iterator = users.getUsersRoleses().iterator();
		 * UsersRoles usersRoles = (UsersRoles) iterator.next(); rolesId =
		 * usersRoles.getRoles().getId(); if (rolesId == 4) { return 4;
		 * 
		 * } else if (rolesId == 2) { return 2; }
		 * 
		 * } // 省厅管理员 1 for (Iterator iterator =
		 * users.getUsersRoleses().iterator(); iterator.hasNext();) { UsersRoles
		 * usersRoles = (UsersRoles) iterator.next(); rolesId =
		 * usersRoles.getRoles().getId(); // 当遍历角色rolesId=1的时候，跳出循环 if (rolesId
		 * == 1L || rolesId == 5L) break;
		 * 
		 * } if (rolesId == 1L || rolesId == 5L) { res = 1; }
		 */

		int res = 0;
		Authorize isAuthorize = new Authorize();
		if (isAuthorize.isAuthorize("REVIEW_NEWS")) {
			res = 1;
		}

		return res;
	}

	public WebSite getWebSite() {
		return webSite;
	}

	public void setWebSite(WebSite webSite) {
		this.webSite = webSite;
	}

	public UsersManagerDao getUsersManagerDao() {
		return usersManagerDao;
	}

	public void setUsersManagerDao(UsersManagerDao usersManagerDao) {
		this.usersManagerDao = usersManagerDao;
	}

	public List getMenuList() {
		return menuList;
	}

	public void setMenuList(List menuList) {
		this.menuList = menuList;
	}
}