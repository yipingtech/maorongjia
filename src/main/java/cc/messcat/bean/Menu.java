/*
 * Menu.java 对应映射文件是 cc.modules.config.Menu.xml
 * 
 * Create by Michael Tang
 * 
 * Create time 2015-3-22
 * 
 * Version 1.0
 * 
 * Copyright 2015 Messcat, Inc. All rights reserved.
 */
package cc.messcat.bean;

import java.util.List;

public class Menu implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	// 菜单名称
	private String menuName;

	// 权限
	private String authorize;

	// 图标
	private String pic;

	// 链接
	private String url;

	// Y：需要右边弹开窗口，N：不需要右边弹开窗口
	private String isShowRightPanel;

	// isShowRightPanel为Y则必填
	private String rightPanelName;

	// isShowRightPanel为Y则必填
	private String rightPanelUrl;

	// 子菜单列表
	private List<Menu> menuChildren;

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getAuthorize() {
		return authorize;
	}

	public void setAuthorize(String authorize) {
		this.authorize = authorize;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getIsShowRightPanel() {
		return isShowRightPanel;
	}

	public void setIsShowRightPanel(String isShowRightPanel) {
		this.isShowRightPanel = isShowRightPanel;
	}

	public String getRightPanelName() {
		return rightPanelName;
	}

	public void setRightPanelName(String rightPanelName) {
		this.rightPanelName = rightPanelName;
	}

	public String getRightPanelUrl() {
		return rightPanelUrl;
	}

	public void setRightPanelUrl(String rightPanelUrl) {
		this.rightPanelUrl = rightPanelUrl;
	}

	public List<Menu> getMenuChildren() {
		return menuChildren;
	}

	public void setMenuChildren(List<Menu> menuChildren) {
		this.menuChildren = menuChildren;
	}
}
