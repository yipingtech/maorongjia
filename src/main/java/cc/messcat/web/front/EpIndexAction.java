package cc.messcat.web.front;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.opensymphony.xwork2.ActionContext;

import cc.messcat.entity.EnterpriseColumn;
import cc.messcat.entity.Member;
import cc.messcat.entity.ProductColumn;
import cc.messcat.front.IndexInfoBean;
import cc.modules.constants.Constants;
import cc.modules.constants.PageConstants;
import cc.modules.util.ObjValid;

/**
 * 处理并获取首页所有的数据及属性
 * 
 * @author Administrator
 * 
 */
public class EpIndexAction extends FrontAction {

	private static final long serialVersionUID = 7960729775814109475L;
	
	private LinkedHashMap<EnterpriseColumn, List<ProductColumn>> productMap;
	
	private List<ProductColumn> interestProductColumns;
	
	private Long fatherId;
	
	private String name;
	
	private List indexProductInfos;
	
	private IndexInfoBean indexInfoBean;
	
	private Long id;
	
	private int index;
	
	private EnterpriseColumn column0;
	
    
	/**
	 * 微信所有入口的重定向  
	 * @throws IOException 
	 **/
	//模式解说
	public void moShi() throws IOException{
		toInitMember();
		HttpServletResponse response = getResponse();
	    response.sendRedirect(Constants.WEBSITE_URL + "/epFrontNewsAction.htm?columnId=29&fatherId="+fatherId);	
	}
	//如何组建团队
	public void createTeam() throws IOException{
		toInitMember();
		HttpServletResponse response = getResponse();
	    response.sendRedirect(Constants.WEBSITE_URL + "/epFrontNewsAction.htm?columnId=28&fatherId="+fatherId);	
	}
	//我的公司
	public void myCompany() throws IOException{
		toInitMember();
		HttpServletResponse response = getResponse();
	    response.sendRedirect(Constants.WEBSITE_URL + "/epMemberCenterAction.action?fatherId="+fatherId);	
	}
	
	
	//集团简介
	public void intro() throws IOException{
		toInitMember();
		HttpServletResponse response = getResponse();
	    response.sendRedirect(Constants.WEBSITE_URL + "/epFrontNewsAction.htm?columnId=87&fatherId="+fatherId);	
	}
	//集团架构
	public void jiaGou() throws IOException{
		toInitMember();
		HttpServletResponse response = getResponse();
		response.sendRedirect(Constants.WEBSITE_URL + "/epFrontNewsAction.htm?columnId=88&fatherId="+fatherId);	
	}
	//发展历程
	public void develop() throws IOException{
		toInitMember();
		HttpServletResponse response = getResponse();
	    response.sendRedirect(Constants.WEBSITE_URL + "/epFrontNewsAction.htm?columnId=89&fatherId="+fatherId);	
	}
	//企业文化
	public void culture() throws IOException{
		toInitMember();
		HttpServletResponse response = getResponse();
		response.sendRedirect(Constants.WEBSITE_URL + "/epFrontNewsAction.htm?columnId=90&fatherId="+fatherId);	
	}
	//联系我们
	public void contactUs() throws IOException{
		toInitMember();
		HttpServletResponse response = getResponse();
	    response.sendRedirect(Constants.WEBSITE_URL + "/epFrontNewsAction.htm?columnId=91&fatherId="+fatherId);	
	}
	
	public void toInitMember(){
		Member member = getMember();
		if (ObjValid.isValid(member)) {
			this.fatherId = member.getId();
		}
	}
	
	/**
	 * 跳转首页带参入口
	 * @throws IOException 
	 * */
	public void toIndex() throws IOException{
		//this.initMember();
		toInitMember();
		HttpServletResponse response = getResponse();
	    response.sendRedirect(Constants.WEBSITE_URL + "/epIndexAction.htm?fatherId="+fatherId);		
	}

	/**
	 * 首页入口
	 */
	public String execute() throws Exception {
		findFrontMudoles();
		findAds();
 		//this.initMember();
		//this.initIndexProduct();
		
		// 获取分享链接
				HttpServletRequest request = getRequest();
				String fatherId = (String) request.getParameter("fatherId");
				getWechatShareUrl();
				String urlString = "";
				if (ObjValid.isValid(fatherId)) {
					urlString = Constants.WEBSITE_URL + "/epIndexAction.htm?fatherId=" + fatherId;
				} else {
					Member member = getMember();
					if (ObjValid.isValid(member)) {
						urlString = Constants.WEBSITE_URL + "/epIndexAction.htm?fatherId=" + member.getId();
					}
				}
				urlString = URLEncoder.encode(urlString, "UTF-8");
				shareUrl = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + Constants.APPID + "&redirect_uri=" + urlString
					+ "&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";
		return PageConstants.INDEX_KEY;
	}
	
	/**
	 * 产品分类页
	 */
	public String findProductClassify(){
		try {
			indexInfoBean = epColumnManagerDao.findProductClassify();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "productClassify";
	}
	
	/**
	 * 分类 -- 查询系列
	 * @return
	 */
	public String findProductBySeries(){
		try {
			String id="";
			this.selectSecondLevelId = selectSecondLevelId;
			indexInfoBean = epColumnManagerDao.findProductClassify();
			for(int i=0; i< indexInfoBean.getEnterpriseColumnList().size(); i++){
				EnterpriseColumn column = (EnterpriseColumn) indexInfoBean.getEnterpriseColumnList().get(i);
				if (column.getId().intValue() == selectSecondLevelId.intValue()) {
					/*this.index = i;
					if (!ObjValid.isValid(id)) {
						id = column.getSubColumnList().get(0).getId();
					}*/
					column0=column;
					for(int j=0;j<column.getSubColumnList().size();j++){
						id+=column.getSubColumnList().get(j).getId().toString()+",";
					}
				}
				
				
			}
			//indexProductInfos = mcProductInfoManagerDao.findHotSeries(id);findAllSeries
			if(id!=""){
				id=id.substring(0, id.length()-1);
			}
			indexProductInfos = mcProductInfoManagerDao.findAllSeries(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "productClassify2";
	}
	
	/**
	 * 搜索栏模糊查询商品
	 * @return
	 */
	public String findProductBySearch(){
		try {
			indexProductInfos = mcProductInfoManagerDao.findMCProductInfoByName(name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "productList";
	}
	
	/**
	 * 首页-热门系列-更多
	 * @return
	 */
	public String findHotSeries(){
		try {
			this.name = name;
			indexProductInfos = mcProductInfoManagerDao.findHotSeries(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "productList";
	}
	
	/**
	 * 品牌列表页
	 * */
	public String brandProductList(){
		this.initIndexProduct();
		return "brandProductList";
	}
	
	public void initFatherId(){
		this.fatherId = fatherId;
	}
	
	@SuppressWarnings("unchecked")
	public void initIndexProduct(){
		try {
			this.name =name;
			EnterpriseColumn ec =  epColumnManagerDao.getEnterpriseColumn(Long.valueOf(selectColumnId));
			pager = productColumnManagerDao.findProductByColumnPro(null, ec, Integer.MAX_VALUE, 1);
			indexProductInfos = pager.getResultList();
			this.interestProductColumns = pager.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void initMember(){
		Map session = (Map) ActionContext.getContext().getSession();
		Member member = this.memberManagerDao.retrieveMember(1L);
		session.put("member", member);
	}

	public LinkedHashMap<EnterpriseColumn, List<ProductColumn>> getProductMap() {
		return productMap;
	}

	public void setProductMap(
			LinkedHashMap<EnterpriseColumn, List<ProductColumn>> productMap) {
		this.productMap = productMap;
	}

	public List<ProductColumn> getInterestProductColumns() {
		return interestProductColumns;
	}

	public void setInterestProductColumns(List<ProductColumn> interestProductColumns) {
		this.interestProductColumns = interestProductColumns;
	}
	public Long getFatherId() {
		return fatherId;
	}

	public void setFatherId(Long fatherId) {
		this.fatherId = fatherId;
	}
	public List getIndexProductInfos() {
		return indexProductInfos;
	}

	public void setIndexProductInfos(List indexProductInfos) {
		this.indexProductInfos = indexProductInfos;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public IndexInfoBean getIndexInfoBean() {
		return indexInfoBean;
	}
	public void setIndexInfoBean(IndexInfoBean indexInfoBean) {
		this.indexInfoBean = indexInfoBean;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public EnterpriseColumn getColumn0() {
		return column0;
	}
	public void setColumn0(EnterpriseColumn column0) {
		this.column0 = column0;
	}
	
	
	
	
}
