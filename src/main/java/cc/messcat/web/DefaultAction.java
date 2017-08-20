package cc.messcat.web;

import java.util.Map;
import java.util.Properties;

import com.opensymphony.xwork2.ActionContext;
import cc.messcat.bases.BaseAction;
import cc.modules.constants.ActionConstants;
import cc.messcat.entity.Users;

/**
 * @author Messcat
 * @version 1.1
 * 
 */
public class DefaultAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Properties prop;
	private String isFine;
	private int epMessCount;
	private int userMessCount;

	public String execute() throws Exception {
		prop = System.getProperties();
		if (this.usersManagerDao.isFullInfo())
			isFine = "1";
		else
			isFine = "0";

		Map session = ActionContext.getContext().getSession();
		Users users = (Users) session.get("users");

		return ActionConstants.SUCCESS_KEY;
	}

	public Properties getProp() {
		return prop;
	}

	public void setProp(Properties prop) {
		this.prop = prop;
	}

	public String getIsFine() {
		return isFine;
	}

	public void setIsFine(String isFine) {
		this.isFine = isFine;
	}

	public int getEpMessCount() {
		return epMessCount;
	}

	public void setEpMessCount(int epMessCount) {
		this.epMessCount = epMessCount;
	}

	public int getUserMessCount() {
		return userMessCount;
	}

	public void setUserMessCount(int userMessCount) {
		this.userMessCount = userMessCount;
	}

}