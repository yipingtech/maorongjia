package cc.messcat.web.front;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cc.messcat.entity.Attribute;
import cc.messcat.entity.EnterpriseColumn;
import cc.messcat.entity.Evaluate;
import cc.messcat.entity.McProductInfo;
import cc.messcat.entity.Member;
import cc.messcat.entity.ProductAttr;
import cc.messcat.entity.ProductColumn;
import cc.messcat.entity.Stock;
import cc.modules.constants.Constants;
import cc.modules.constants.PageConstants;
import cc.modules.util.CollectionUtil;
import cc.modules.util.ObjValid;

/**
 * 处理并获取新闻列表页面所有的数据及属性
 * 
 * @author Administrator
 * 
 */
@SuppressWarnings("unchecked")
public class EpProductAction extends FrontAction {

	private static final long serialVersionUID = -5754036333272247675L;
	private static Logger log = LoggerFactory.getLogger(EpProductAction.class); 
	/**
	 * 栏目的产品列表
	 */
	private List<McProductInfo> productList;

	private List<ProductColumn> productColumns;

	private McProductInfo productInfo;

	private List<EnterpriseColumn> secondEnterpriseColumns;

	private LinkedHashMap<Attribute, List<ProductAttr>> attrMap;

	/**
	 * 选中的栏目
	 */
	private EnterpriseColumn selectColumn;

	/**
	 * 新闻ID
	 */
	private Long newsId;

	/**
	 * 栏目ID
	 */
	private Long columnId;

	private Long ancestorId;

	private Long productId;

	private String proAttrIds;

	private Long repertory;

	private String pageType;

	private String orderType;

	private String orderBy;

	private long countEvaluate;

	private List<Evaluate> evaluates;
	
	private Member member;
	
	private Long fatherId;
	
	private String productAttrs;

	/**
	 * 库存
	 */
	private Stock stock;
	private Long id;
	
	/**
	 * 栏目获取品牌列表用
	 * */
	private EnterpriseColumn enterpriseColumn;

	

	// 查找某一分类商品的数量
	private int num;
	
	private Double price;

	/**
	 * 首页入口
	 */
	public String execute() throws Exception {
		//super.init();
		super.findStandBy();
		String result = PageConstants.PRODUCT_LIST;

		// 前台传过来的页面类型
		if (this.pageType != null && !"".equals(this.pageType)) {
			// 处理特殊页面的特殊需求
			if (newsId == null) {
				result = this.productList();
			} else if (newsId != null) {
				result = this.product();
			}
		} else {
			result = this.productList();
		}
		return result;
	}

	/**
	 * 商品详情页
	 * */
	public String tampons() throws Exception {
		/*
		 * List<McProductInfo> productList =
		 * this.mcProductInfoManagerDao.retrieveAllMcProductInfos();
		 * this.productInfo = productList.get(0);
		 */
		try {
			this.productInfo = this.mcProductInfoManagerDao.retrieveMcProductInfo(this.id);
			this.attrMap = this.mcProductInfoManagerDao.initProductDetail(productInfo, "1");
			pager = evaluateManagerDao.queryEvaluates(pageNo, pageSize, this.newsId);
			evaluates = pager.getResultList();
			countEvaluate = pager.getRowCount();
	        //品牌故事
			enterpriseColumn = this.productColumnManagerDao.findByProduct(productInfo);

			// 产品库存查找
			productId = productInfo.getId();
			// proAttrIds = "50";
			/*List<ProductAttr> proAttrList = this.productAttrManagerDao.queryByProductAndAttrType(id, "1");
			if (ObjValid.isValid(proAttrList)) {
				for (ProductAttr temp : proAttrList) {
					proAttrIds = String.valueOf(temp.getId())+",";
				}
				proAttrIds = proAttrIds.substring(0, proAttrIds.length()-1);
			}*/
			List<Stock> stocks = this.stockManagerDao.searchByProduct(productInfo);
			if (ObjValid.isValid(stocks)) {
				proAttrIds = stocks.get(0).getProductAttrIds();
				price = stocks.get(0).getPrice();
				List<ProductAttr> proAttrList = this.productAttrManagerDao.findByIds(proAttrIds);
				productAttrs = "";
				for (ProductAttr temp : proAttrList) {
					productAttrs = productAttrs + temp.getAttrValue()+" ";
				}
			}
			/*
			 * List<ProductAttr> proAttrs =
			 * this.productAttrManagerDao.queryByProductAndAttrType(productId, "1");
			 * Long proAttrIds = proAttrs.get(0).getId();
			 * log.info(proAttrIds+"-------------------");
			 */
			findRepertory();

			// 获取分享链接0
			
			this.member = super.getMember();
			if (ObjValid.isValid(member)&&ObjValid.isValid(member.getId())) {
				log.error("member--"+member.getId());
			}
			String urlString = "";
			if (ObjValid.isValid(fatherId)) {
				getProductShareUrl(id,fatherId);
				urlString = Constants.WEBSITE_URL + "/epProductAction!tampons.action?id="+this.id+"&fatherId=" + fatherId;
			} else {
				if (ObjValid.isValid(member)) {
					getProductShareUrl(id,member.getId());
					urlString = Constants.WEBSITE_URL + "/epProductAction!tampons.action?id="+this.id+"&fatherId=" + member.getId();
				}
			}
			urlString = URLEncoder.encode(urlString, "UTF-8");
			log.error("urlString--"+urlString);
			shareUrl = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + Constants.APPID + "&redirect_uri=" + urlString
				+ "&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";
			log.error("shareUrl--"+shareUrl);
			/*String openid = super.getOpenId();
			if(!ObjValid.isValid(openid)){
				HttpServletResponse response = getResponse();
			    response.sendRedirect(shareUrl);
			}*/
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "tampons";
	}

	// 查找某一分类的商品
	public String productOneList() throws Exception {
		// 查找所有的标示为非空的栏目模块,包括了栏目，与栏目新闻
		findFrontMudoles();
		// 找columnId所有的商品
		if (ObjValid.isValid(this.columnId)){
			this.productList = (List<McProductInfo>) productColumnManagerDao.findProductByColumnIsSale(this.columnId);
			this.selectColumn = this.epColumnManagerDao.getEnterpriseColumn(this.columnId);
		} else{
			this.productList = (List<McProductInfo>) this.mcProductInfoManagerDao.retrieveAllMcProductInfosIsSale();
		}
		this.num = this.productList.size();
		return "productOneList";
	}

	private List<EnterpriseColumn> convertColumn(List<EnterpriseColumn> allColumns, EnterpriseColumn fatherColumn) {
		List<EnterpriseColumn> columnList = new ArrayList<EnterpriseColumn>();

		if (CollectionUtil.isListNotEmpty(allColumns)) {
			for (EnterpriseColumn col : allColumns) {
				if (fatherColumn == null) {
					if (col.getFather().longValue() == 0) {
						col.setSubColumnList(this.convertColumn(allColumns, col));
						columnList.add(col);
					}
				} else {
					if (col.getFather().longValue() == fatherColumn.getId().longValue()) {
						col.setSubColumnList(this.convertColumn(allColumns, col));
						columnList.add(col);
					}
				}
			}
		}

		return columnList;
	}

	/**
	 * 查找产品库存
	 */
	public String findRepertory() {
		this.repertory = 0L;
		
		Stock condition = new Stock();
		McProductInfo product = this.mcProductInfoManagerDao.retrieveMcProductInfo(productId);
		condition.setProductAttrIds(this.proAttrIds);
		condition.setProduct(product);
		stock = this.stockManagerDao.findByProAttr(condition);
		if (ObjValid.isValid(stock)) {
			this.repertory = stock.getAmount();
			this.price = stock.getPrice();
		}
		return SUCCESS;
	}

	/**
	 * 产品列表页面
	 * 
	 * @return
	 */
	private String productList() throws Exception {
		/*
		 * List<EnterpriseColumn> allColumns =
		 * this.epColumnManagerDao.findAllEnterpriseColumn(); if
		 * (ObjValid.isValid(ancestorId)) { this.selectColumn =
		 * this.epColumnManagerDao.getEnterpriseColumn(ancestorId); }else {
		 * this.selectColumn =
		 * this.epColumnManagerDao.getEnterpriseColumn(this.columnId);
		 * this.ancestorId = this.columnId; }
		 */
		// 产品列表
		this.productList = this.mcProductInfoManagerDao.retrieveAllMcProductInfos();

		/*
		 * this.secondEnterpriseColumns = this.convertColumn(allColumns,
		 * this.selectColumn); this.queryProductColumn();
		 */
		return PageConstants.PRODUCT_LIST;
	}

	/**
	 * @return
	 * 
	 *         查询产品-栏目关联
	 */
	public String queryProductColumn() {
		try {
			if (super.selectColumnId != null) {
				EnterpriseColumn ec = epColumnManagerDao.getEnterpriseColumn(this.columnId);
				this.productInfo = new McProductInfo();
				if ("addTime".equals(this.orderType)) {
					this.productInfo.setAddtime(new Date());
				}
				if ("price".equals(this.orderType)) {
					if ("asc".equals(this.orderBy)) {
						this.productInfo.setShopPrice(12D);
					}
					if ("desc".equals(this.orderBy)) {
						this.productInfo.setShopPrice(21D);
					}
				}
				if ("saleVolume".equals(this.orderType)) {
					if ("asc".equals(this.orderBy)) {
						this.productInfo.setSaleValume(12L);
					}
					if ("desc".equals(this.orderBy)) {
						this.productInfo.setSaleValume(21L);
					}
				}
				if ("grade".equals(this.orderType)) {
					if ("asc".equals(this.orderBy)) {
						this.productInfo.setGrade(12D);
					}
					if ("desc".equals(this.orderBy)) {
						this.productInfo.setGrade(21D);
					}
				}
				pager = productColumnManagerDao.findProductByCondition(productInfo, this.columnId, pageSize, pageNo);
				this.productColumns = pager.getResultList();
			}
		} catch (Exception e) {
			e.printStackTrace();
			addActionMessage("查询失败, 请检查参数是否都正确！");
		}
		return "column_productinfo";
	}

	/**
	 * 产品详情页面
	 * 
	 * @return
	 */
	private String product() throws Exception {
		this.productInfo = this.mcProductInfoManagerDao.retrieveMcProductInfo(this.newsId);
		this.attrMap = this.mcProductInfoManagerDao.initProductDetail(productInfo, "1");
		pager = evaluateManagerDao.queryEvaluates(pageNo, pageSize, this.newsId);
		evaluates = pager.getResultList();
		countEvaluate = pager.getRowCount();
		return PageConstants.PRODUCT;
	}

	/**
	 * 商品评论
	 * 
	 * @return
	 */
	public String queryEvaluates() {
		pager = evaluateManagerDao.queryEvaluates(pageNo, pageSize, productId);
		evaluates = pager.getResultList();
		return "queryEvaluates";
	}

	public List<McProductInfo> getProductList() {
		return productList;
	}

	public void setProductList(List<McProductInfo> productList) {
		this.productList = productList;
	}

	public EnterpriseColumn getSelectColumn() {
		return selectColumn;
	}

	public void setSelectColumn(EnterpriseColumn selectColumn) {
		this.selectColumn = selectColumn;
	}

	public List<EnterpriseColumn> getSecondEnterpriseColumns() {
		return secondEnterpriseColumns;
	}

	public void setSecondEnterpriseColumns(List<EnterpriseColumn> secondEnterpriseColumns) {
		this.secondEnterpriseColumns = secondEnterpriseColumns;
	}

	public Long getNewsId() {
		return newsId;
	}

	public void setNewsId(Long newsId) {
		this.newsId = newsId;
	}

	public McProductInfo getProductInfo() {
		return productInfo;
	}

	public void setProductInfo(McProductInfo productInfo) {
		this.productInfo = productInfo;
	}

	public LinkedHashMap<Attribute, List<ProductAttr>> getAttrMap() {
		return attrMap;
	}

	public void setAttrMap(LinkedHashMap<Attribute, List<ProductAttr>> attrMap) {
		this.attrMap = attrMap;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProAttrIds() {
		return proAttrIds;
	}

	public void setProAttrIds(String proAttrIds) {
		this.proAttrIds = proAttrIds;
	}

	public Long getRepertory() {
		return repertory;
	}

	public void setRepertory(Long repertory) {
		this.repertory = repertory;
	}

	public List<ProductColumn> getProductColumns() {
		return productColumns;
	}

	public void setProductColumns(List<ProductColumn> productColumns) {
		this.productColumns = productColumns;
	}

	public Long getAncestorId() {
		return ancestorId;
	}

	public void setAncestorId(Long ancestorId) {
		this.ancestorId = ancestorId;
	}

	public Long getColumnId() {
		return columnId;
	}

	public void setColumnId(Long columnId) {
		this.columnId = columnId;
	}

	public String getPageType() {
		return pageType;
	}

	public void setPageType(String pageType) {
		this.pageType = pageType;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public List<Evaluate> getEvaluates() {
		return evaluates;
	}

	public void setEvaluates(List<Evaluate> evaluates) {
		this.evaluates = evaluates;
	}

	public long getCountEvaluate() {
		return countEvaluate;
	}

	public void setCountEvaluate(long countEvaluate) {
		this.countEvaluate = countEvaluate;
	}

	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}
	public EnterpriseColumn getEnterpriseColumn() {
		return enterpriseColumn;
	}

	public void setEnterpriseColumn(EnterpriseColumn enterpriseColumn) {
		this.enterpriseColumn = enterpriseColumn;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Long getFatherId() {
		return fatherId;
	}

	public void setFatherId(Long fatherId) {
		this.fatherId = fatherId;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}


}
