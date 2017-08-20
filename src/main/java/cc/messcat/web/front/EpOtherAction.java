package cc.messcat.web.front;

import cc.messcat.entity.EnterpriseColumn;
import cc.modules.constants.PageConstants;

/**
 * 处理并获取新闻列表页面所有的数据及属性
 * 
 * @author Administrator
 * 
 */
@SuppressWarnings("unchecked")
public class EpOtherAction extends FrontAction {

	private static final long serialVersionUID = -5754036333272247675L;

	/**
	 * 选中的栏目
	 */
	private EnterpriseColumn selectColumn;

	/**
	 * 首页入口
	 */
	public String execute() throws Exception {
		super.init();
		this.otherInit();
		String result = null;

		// 前台传过来的页面类型
		if (super.selectPageType != null && !"".equals(super.selectPageType)) {
			// 处理特殊页面的特殊需求
			if (PageConstants.FEEDBACK.equals(super.selectPageType)) {
				result = this.feedback();
			}
		}
		return result;
	}

	/**
	 * 特殊页面Other页面init
	 * 
	 * @return
	 * @throws Exception
	 */
	private void otherInit() throws Exception {

		// 前台传过来的栏目ID
		if (super.selectColumnId == null) {
			throw new Exception("栏目ID没有了，你自己看着办吧！");
		}

		this.selectColumn = super.epColumnManagerDao.getEnterpriseColumn(super.selectColumnId);

		// 设置菜单
		super.findSelectFirstLevelColumn(super.selectColumnId);

		// 设置页面上的当前位置
		super.findLocation();

	}

	/**
	 * 知识问答页面
	 * 
	 * @return
	 */
	private String feedback() throws Exception {
		return PageConstants.FEEDBACK;
	}

	public EnterpriseColumn getSelectColumn() {
		return selectColumn;
	}

	public void setSelectColumn(EnterpriseColumn selectColumn) {
		this.selectColumn = selectColumn;
	}

}
