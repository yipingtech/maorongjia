package cc.messcat.web.front;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cc.messcat.entity.EnterpriseColumn;
import cc.messcat.entity.Member;
import cc.modules.constants.Constants;
import cc.modules.constants.PageConstants;
import cc.modules.util.ObjValid;

/**
 * 处理并获取新闻列表页面所有的数据及属性
 * 
 * @author Administrator
 * 
 */
@SuppressWarnings("unchecked")
public class EpNewsListMoreAction extends FrontAction {

	private static final long serialVersionUID = -5754036333272247675L;
	private static Logger log = LoggerFactory.getLogger(EpNewsListMoreAction.class); 
	/**
	 * 选中的栏目
	 */
	private EnterpriseColumn selectColumn;
	
	/**
	 * 需跳转的栏目Link
	 */
	private String colLink;
	
	private Date today;
	/**
	 * 父级ID参数
	 * */
	private Long fatherId;

	

	/**
	 * 带参跳转新闻列表首页
	 * @throws IOException 
	 * */
	public void redirectNewsList() throws IOException{
		Member member = getMember();
		if (ObjValid.isValid(member)) {
			this.fatherId = member.getId();
		}
		HttpServletResponse response = ServletActionContext.getResponse();
	    response.sendRedirect(Constants.WEBSITE_URL + "/epNewsListMoreAction!newsList.action?fatherId="+fatherId);		
	}
	/**
	 * 首页入口
	 */
	public String execute() throws Exception {
		//super.init();
		super.findAllColumns();
		super.findStandBy();
		this.newsListMoreInit();
		String result = PageConstants.NEWS_LIST_MORE;

		// 前台传过来的页面类型
		if (super.selectPageType != null && !"".equals(super.selectPageType)) {
			// 处理特殊页面的特殊需求
			if ("showcol".equals(super.selectPageType)) {
				result = this.showcol();
			}
		}
		return result;
	}
	public String newsList() throws Exception{
		//super.init();
		super.findAllColumns();
		super.findStandBy();
		today = new Date();
		
		//获取分享链接
		HttpServletRequest request = getRequest();
		String fatherId = (String) request.getParameter("fatherId");
		getWechatShareUrl();
		String urlString = "";
		if (ObjValid.isValid(fatherId)) {
			urlString = Constants.WEBSITE_URL+"/epNewsListMoreAction!newsList.action?fatherId="+fatherId;
		}else {
			Member member = getMember();
			if (ObjValid.isValid(member)) {
				urlString = Constants.WEBSITE_URL+"/epNewsListMoreAction!newsList.action?fatherId="+member.getId();
			}
		}
		log.info("urlString::"+urlString);
		urlString = URLEncoder.encode(urlString, "UTF-8");
		shareUrl = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+Constants.APPID+"&redirect_uri="+urlString+"&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";
		log.info("软文分享："+shareUrl);
		return "newsList";
	}

	/**
	 * 模板列表listmore页面
	 * 
	 * @return
	 * @throws Exception
	 */
	private void newsListMoreInit() throws Exception {

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
	 * 展示第一个子栏目（直到叶节点）
	 * 
	 * @return
	 */
	private String showcol() throws Exception {
		EnterpriseColumn leafColumn = this.findLeafColumn(this.selectColumn);
		if(leafColumn == this.selectColumn){
			throw new Exception("死循环啦，改改你的pageType吧！");
		}
		this.colLink = leafColumn.getLinkUrl();
		return "showcol";
	}
	
	/**
	 * 递归找出当前栏目的在叶节点上的第一个子栏目
	 * @param column
	 * @return
	 */
	private EnterpriseColumn findLeafColumn(EnterpriseColumn column){
		if(column.getSubColumnList() != null && column.getSubColumnList().size() > 0){
			return this.findLeafColumn(column.getSubColumnList().get(0));
		}else{
			return column;
		}
	}

	public EnterpriseColumn getSelectColumn() {
		return selectColumn;
	}

	public void setSelectColumn(EnterpriseColumn selectColumn) {
		this.selectColumn = selectColumn;
	}

	public String getColLink() {
		return colLink;
	}

	public void setColLink(String colLink) {
		this.colLink = colLink;
	}
	public Date getToday() {
		return today;
	}
	public void setToday(Date today) {
		this.today = today;
	}
	public Long getFatherId() {
		return fatherId;
	}
	public void setFatherId(Long fatherId) {
		this.fatherId = fatherId;
	}

}
