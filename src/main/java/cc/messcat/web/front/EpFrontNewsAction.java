package cc.messcat.web.front;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cc.messcat.entity.EnterpriseColumn;
import cc.messcat.entity.EnterpriseNews;
import cc.messcat.entity.Member;
import cc.messcat.web.drawback.ProductDrawbackAction;
import cc.modules.constants.Constants;
import cc.modules.constants.PageConstants;
import cc.modules.util.CommonDownload;
import cc.modules.util.ObjValid;
import cc.modules.util.PropertiesFileReader;

/**
 * 处理并获取新闻页面的数据及属性
 * 
 * @author Administrator
 * 
 */
public class EpFrontNewsAction extends FrontAction {

	private static final long serialVersionUID = 2441570372422185617L;
	
	private static Logger log = LoggerFactory.getLogger(EpFrontNewsAction.class); 

	/**
	 * 新闻
	 */
	private EnterpriseNews enterpriseNews;

	/**
	 * 新闻外链
	 */
	private String linkUrl;

	/**
	 * 选中的栏目
	 */
	private EnterpriseColumn selectColumn;
	
	/**
	 * 新闻页面入口
	 */
	public String execute() throws Exception {
		//super.init();
		super.findColumnIdFromFront();
		super.findPageTypeFromFront();
	    super.findNewsIdFromFront();
	    super.findStandBy();
		this.contentToNews();
		String result = this.news();
		
		//获取分享链接
		HttpServletRequest request = getRequest();
		String fatherId = (String) request.getParameter("fatherId");
		getWechatShareUrl();
		String urlString = "";
		if (ObjValid.isValid(fatherId)) {
			urlString = Constants.WEBSITE_URL+"/navigation.htm?columnType=news&newsId="+selectNewsId+"&fatherId="+fatherId;
		}else {
			Member member = getMember();
			if (ObjValid.isValid(member)) {
				urlString = Constants.WEBSITE_URL+"/navigation.htm?columnType=news&newsId="+selectNewsId+"&fatherId="+member.getId();
			}
		}
		log.error("urlString::"+urlString);
		urlString = URLEncoder.encode(urlString, "UTF-8");
		shareUrl = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+Constants.APPID+"&redirect_uri="+urlString+"&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";
		// 前台传过来的页面类型
		if(super.selectPageType != null && !"".equals(super.selectPageType)){
			//处理特殊页面的特殊需求
			/*if("aboutus".equals(super.selectPageType)){
				result = this.aboutus();
			}*/
		}
		return result;
	}

	/**
	 * 查看一条新闻页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String news() throws Exception {

		// 前台传过来的新闻ID
		if (super.selectNewsId == null) {
			throw new Exception("新闻ID没有了！");
		}

		this.enterpriseNews = super.epNewsManagerDao.getEpNews(super.selectNewsId);
		if (this.enterpriseNews == null) {
			return PageConstants.ERROR_KEY;
		}

		this.selectColumn = super.epColumnManagerDao.getEnterpriseColumn(this.enterpriseNews.getEnterpriseColumn().getId());
		
		// 设置菜单
		super.findSelectFirstLevelColumn(this.enterpriseNews.getEnterpriseColumn().getId());

		// 设置页面上的当前位置
		super.findLocation();

		// 判断是否外链新闻
		String otherLink = this.enterpriseNews.getOtherURL();
		if (otherLink != null && !"".equals(otherLink)) {
			this.linkUrl = otherLink;
			return "linkUrl";
		}

		// 判断是否文件新闻
		if (enterpriseNews.getIsPrimPhoto() != null && "2".equals(enterpriseNews.getIsPrimPhoto()) && "news".equals(super.selectPageType)) {
			String rootPath = PropertiesFileReader.get("upload.file.path", "/app.properties");
			String fileName = enterpriseNews.getFileInfo();
			CommonDownload cd = new CommonDownload();
			cd.downloadFile(fileName, rootPath);
			return null;
		}

		return PageConstants.NEWS;
	}
	
	/**
	 * 根据栏目ID获取到新闻ID
	 */
	private void contentToNews(){
		if(super.selectNewsId == null && super.selectColumnId != null){
			EnterpriseNews epNews = this.epNewsManagerDao.getEpNewsByColumnId(super.selectColumnId);
			super.selectNewsId = epNews.getId().longValue();
		}
	}

	public EnterpriseNews getEnterpriseNews() {
		return enterpriseNews;
	}

	public void setEnterpriseNews(EnterpriseNews enterpriseNews) {
		this.enterpriseNews = enterpriseNews;
	}

	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	public EnterpriseColumn getSelectColumn() {
		return selectColumn;
	}

	public void setSelectColumn(EnterpriseColumn selectColumn) {
		this.selectColumn = selectColumn;
	}

}
