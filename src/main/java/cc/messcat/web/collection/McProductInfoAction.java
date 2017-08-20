package cc.messcat.web.collection;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import cc.messcat.entity.Attribute;
import cc.messcat.entity.EnterpriseColumn;
import cc.messcat.entity.McParameter;
import cc.messcat.entity.McProductInfo;
import cc.messcat.entity.ProductAttr;
import cc.messcat.entity.ProductType;
import cc.messcat.entity.Stock;
import cc.messcat.service.system.McParameterManagerDao;
import cc.messcat.vo.StockVo;
import cc.modules.commons.PageAction;
import cc.modules.constants.ActionConstants;
import cc.modules.constants.Constants;
import cc.modules.util.CommonUpload;
import cc.modules.util.FormatStringUtil;
import cc.modules.util.ObjValid;

public class McProductInfoAction extends PageAction {

	private Long id;
	private Long colId;
	private String title;

	private String father;
	private String state;

	private List<McProductInfo> mcProductInfos;
	private EnterpriseColumn column;
	
	private McParameterManagerDao mcParameterManagerDao;
	//是否显示新增按钮
	private String isShowInsert;
	//返回图片名称
	private String imgurl;
	/**
	 * 产品信息表
	 */
	private McProductInfo mcProductInfo;
	/**
	 * 可以工作状态的可选属性列及其值
	 */
	private Map<McParameter, String> parameterValue;
	/**
	 * 可以工作状态的可选属性列
	 */
	private List<McParameter> workOkMcParameterList;
	
	private CommonUpload comUpload; //上传图片、视频通用类
	/**
	 * 以下为上传的文件名
	 */
	private String uploadFileName;
	private String para11FileName;
	private String para12FileName;
	private String para13FileName;
	private String para14FileName;
	private String para15FileName;
	private String para16FileName;
	private String para17FileName;
	private String para18FileName;
	private String para19FileName;
	private String para20FileName;
	/**
	 * 以下为上传的文件
	 */
	private File upload;
	private File para11;
	private File para12;
	private File para13;
	private File para14;
	private File para15;
	private File para16;
	private File para17;
	private File para18;
	private File para19;
	private File para20;
	private List<ProductType> productTypes;
	private List<Attribute> attributes;
	private String[] stocklist;
	private int attrAmount;
	private int attrSoleAmount;
	private List<StockVo> stockVoList;
	private List<ProductAttr> productAttrList;
	private String oldStockId;
	private int allSingleAttrVal1;
	
	private boolean ProductTypeExist;//异步查看有没有同名的商品类型
	/**
	 * 产品属性
	 */
	private Attribute attribute;
	
	private ProductType productType;
	


	private Long typeId;

	public McProductInfoAction() {
		comUpload = new CommonUpload();
	}
	
	@SuppressWarnings("unchecked")
	public String retrieveAllMcProductInfos() throws Exception {
		try {
			super.pager = this.mcProductInfoManagerDao.retrieveMcProductInfosPager(pageSize, pageNo);
		} catch (Exception ex) {
			addActionMessage("Load page error!");
		}
		return "success";
	}

	/**
	 * 通过产品类型获取产品该类型的相关属性；
	 * @return
	 * 
	 */
	@SuppressWarnings("unchecked")
	public String findAttrByType(){
		//当传送过来的《option value=“”》请选择《》  值是null ，直接返回空不往页面传数据
		if(!ObjValid.isValid(attribute.getProductType().getId())){
			return "success";
		}
		
		super.pager = attributeManagerDao.retrieveObjectsPager(50, pageNo, attribute==null?new Attribute():attribute, "id", Constants.ASC,Constants.ENABLE);
		this.attributes = this.pager.getResultList();
		return "success";
	}
	
	/**
	 * 查找是否存在产品类型名相同的数据；
	 * @return
	 * 
	 */
	public String FindProductTypeByTypeName(){
		if(!ObjValid.isValid(productType.getName())){
			this.ProductTypeExist = false;
			return "success";
		}
		this.ProductTypeExist = this.productTypeManagerDao.FindProductTypeByTypeName(productType.getName());
		return "success";
	}
	
	public void buildStockVo(List<Stock> stockListAll){
		String fontProAttId = "";
		String laterProAttId = "";
		String stockIds = "";
		String repertory = "";
		String price = "";
		Map<String, String> proAttrMap = new HashMap<String, String>();
		LinkedHashMap<Attribute, String> attrMap = new LinkedHashMap<Attribute, String>();
		for (int stockLine = 0; stockLine < stockListAll.size(); stockLine++) {
			Stock stock = stockListAll.get(stockLine);
			//从后往前截取解析产品属性
			if (stock.getProductAttrIds().lastIndexOf(",") != -1) {
				fontProAttId = stock.getProductAttrIds().substring(0, stock.getProductAttrIds().lastIndexOf(","));
				laterProAttId = stock.getProductAttrIds().substring(stock.getProductAttrIds().lastIndexOf(",")+1, stock.getProductAttrIds().length());
			}else {
				fontProAttId = stock.getProductAttrIds();
				laterProAttId = stock.getProductAttrIds();
			}
			//获取最后一个属性不同的其他属性相同的库存      !ObjValid.isValid(proAttrMap.get(fontProAttId)) &&
			if (proAttrMap.size()!=0) {
				StockVo stockVo = new StockVo();
				stockVo.setRepertory(repertory.substring(0,repertory.length()-1));
				stockVo.setPrice(price.substring(0,price.length()-1));
				stockVo.setStockId(stockIds.substring(0,stockIds.length()-1));
				LinkedHashMap<Attribute, String> attrMapNew = new LinkedHashMap<Attribute, String>();
				attrMapNew.putAll(attrMap);
				stockVo.setAttMap(attrMapNew);
				stockVoList.add(stockVo);
				proAttrMap.clear();
				attrMap.clear();
				stockIds="";
				repertory = "";
				price = "";
			}
			String proAttrVal = proAttrMap.get(fontProAttId);
			if (!ObjValid.isValid(proAttrVal)) {
				proAttrVal = laterProAttId;
			}else {
				proAttrVal = proAttrVal + "," + laterProAttId ;
			}
			proAttrMap.put(fontProAttId, proAttrVal);
			List<String> productAttrIdList = FormatStringUtil.splitBySign(stock.getProductAttrIds(), ",");
			for (int i = 0; i < productAttrIdList.size(); i++) {
				ProductAttr productAttr = this.productAttrManagerDao.retrieveProductAttr(Long.parseLong(productAttrIdList.get(i)));
				Iterator<Attribute> iter = attrMap.keySet().iterator();
				if (attrMap.size()!=0) {
					while (iter.hasNext()) {
						Attribute key = iter.next();
						String attrVal = attrMap.get(key);
						if (key.getId() == productAttr.getAttr().getId() && !attrVal.equals(productAttr.getAttrValue())) {
							attrVal = attrVal +","+ productAttr.getAttrValue();
							attrMap.put(key, attrVal);
						}else if (!proAttrMap.get(fontProAttId).contains(",")) {
							attrMap.put(productAttr.getAttr(),productAttr.getAttrValue());
						}
					}
				}else {
					attrMap.put(productAttr.getAttr(),productAttr.getAttrValue());
				}
			}
			price = price + stock.getPrice().toString()+",";
			repertory = repertory + stock.getAmount().toString()+",";
			stockIds = stockIds+stock.getId().toString()+",";
			if ((stockLine+1) == stockListAll.size()) {
				StockVo stockVo = new StockVo();
				stockVo.setRepertory(repertory.substring(0,repertory.length()-1));
				stockVo.setPrice(price.substring(0,price.length()-1));
				stockVo.setStockId(stockIds.substring(0,stockIds.length()-1));
				stockVo.setAttMap(attrMap);
				stockVoList.add(stockVo);
			}
		}
	}
	
	
	
	public String retrieveMcProductInfoById() throws Exception {
		try {
			this.colId = colId;
			this.productTypes = this.productTypeManagerDao.retrieveAllProductTypes();
			workOkMcParameterList = this.mcParameterManagerDao.findAllWorkOkParameter();
			this.mcProductInfo = this.mcProductInfoManagerDao.retrieveMcProductInfo(id);
			//初始化产品属性
			this.productAttrList = this.productAttrManagerDao.queryByProductAndAttrType(id, "0");
			//初始化库存
			this.stockVoList = new ArrayList<StockVo>();
			//构建每一条库存vo
			List<Stock> stockListAll = this.stockManagerDao.searchByProduct(mcProductInfo);
			this.buildStockVo(stockListAll);
			
			if(mcProductInfo != null && workOkMcParameterList != null)
				putValue2Map();
		} catch (Exception ex) {
			ex.printStackTrace();
			addActionMessage("Load page error!");
		}
		return "edit";
	}

	@SuppressWarnings("unchecked")
	public String newPage() throws Exception {
		this.productTypes = this.productTypeManagerDao.retrieveAllProductTypes();
		workOkMcParameterList = this.mcParameterManagerDao.findAllWorkOkParameter();
		
		return "new";
	}

	public String viewPage() throws Exception {
		try {
			this.mcProductInfo = this.mcProductInfoManagerDao.retrieveMcProductInfo(id);
			workOkMcParameterList = this.mcParameterManagerDao.findAllWorkOkParameter();
			
			if(mcProductInfo != null && workOkMcParameterList != null)
				putValue2Map();
		} catch (Exception ex) {
			addActionMessage("Load page error!");
		}
		return "view";
	}

	public String ajaxUploadImage(){
		//更新产品图片
		comUpload.setHandleType("update");
		try {
			setPic();
			if(uploadFileName != null)
			{
				comUpload.setUpload(upload);//上传的File文件
				comUpload.setUploadFileName(uploadFileName);//上传文件的文件名
				if(!comUpload.uploadFile()){
					return ActionConstants.INPUT_KEY;
				}
			}
			if(ObjValid.isValid(comUpload.getUploadFileName())){
				this.imgurl = comUpload.getUploadFileName();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	
	/**
	 * 新增库存，产品属性关联
	 */
	public void addStockAndProAttr(){
		HttpServletRequest request = ServletActionContext.getRequest();
		Long repertory = 0L;
		if (ObjValid.isValid(this.stocklist)) {
			for (int stockNum = 1; stockNum <= stocklist.length; stockNum++) {
				List<Object> productAttrIdList = new ArrayList<Object>();
				String attrVal = null;
				String amountString = request.getParameter("amount"+stocklist[stockNum-1]);
				String priceString = request.getParameter("price"+stocklist[stockNum-1]);
				//repertory = repertory + amount;
				List<String> attrValList = new ArrayList<String>();
				List<String> amountList = new ArrayList<String>();
				List<String> pricetList = new ArrayList<String>();
				for (int attrNum = attrAmount-1; attrNum >0 ; attrNum--) {
					//获取属性id
					Long attrId = Long.parseLong(request.getParameter("allSingleAttrId1"+attrNum));
					Attribute attribute = this.attributeManagerDao.retrieveAttribute(attrId);
					//获取属性值
					String allSingleAttrVal = "allSingleAttrVal" + stocklist[stockNum-1] + attrNum;
					attrVal = request.getParameter(allSingleAttrVal);
					//将多选属性的属性值存入list
					if (attrNum == 1) {
						attrValList = FormatStringUtil.splitBySign(attrVal, ",");
						amountList = FormatStringUtil.splitBySign(amountString, ",");
						pricetList = FormatStringUtil.splitBySign(priceString, ",");
						for (int i = 0; i < attrValList.size(); i++) {
							ProductAttr productAttr = new ProductAttr();
							productAttr.setProduct(mcProductInfo);
							productAttr.setAttr(attribute);
							productAttr.setAttrValue(attrValList.get(i));
							productAttr.setStatus("1");
							//判断产品-属性关联数据是否存在数据库
							ProductAttr newProductAttr = this.productAttrManagerDao.searchProductAttr(productAttr);
							//拼接产品-属性id（id排序按该属性的排序由大到小排），用于库存
							String productAttrIds = FormatStringUtil.listToString(productAttrIdList);
							if (ObjValid.isValid(productAttrIds)) {
								productAttrIds = productAttrIds +","+ newProductAttr.getId().toString();
							}else {
								productAttrIds = newProductAttr.getId().toString();
							}
							//通过product_attr的id新增库存
							this.stockManagerDao.addStockByAttr(mcProductInfo, productAttrIds, Long.parseLong(amountList.get(i)), Double.parseDouble(pricetList.get(i)));
							repertory = repertory + Long.parseLong(amountList.get(i));
						}
					}else {
						ProductAttr productAttr = new ProductAttr();
						productAttr.setProduct(mcProductInfo);
						productAttr.setAttr(attribute);
						productAttr.setAttrValue(attrVal);
						productAttr.setStatus("1");
						//新增产品-属性关联数据product_attr,拼接id
						Long productAttrId;
						//判断产品-属性关联数据是否存在数据库
						ProductAttr newProductAttr = this.productAttrManagerDao.searchProductAttr(productAttr);
						productAttrId = newProductAttr.getId();
						productAttrIdList.add(productAttrId);
					}
				}
			}
		}
		this.mcProductInfo.setRepertory(repertory);
	}
	
	/**
	 * 新增产品
	 */
	public String newMcProductInfo() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		try {
			if(ObjValid.isValid(this.mcProductInfo)){
				if (ObjValid.isValid(typeId)) {
					ProductType productType = this.productTypeManagerDao.retrieveProductType(typeId);
					mcProductInfo.setProductType(productType);
				}
				Date editDate = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				editDate = sdf.parse(sdf.format(editDate));
				mcProductInfo.setAddtime(editDate);
				mcProductInfo.setUpdatetime(new Date());
				mcProductInfo.setSaleValume(0L);
				mcProductInfo.setShareTime(0L);
				mcProductInfo.setGrade(0D);
				mcProductInfo.setStatus("1");
				this.mcProductInfoManagerDao.addMcProductInfo(this.mcProductInfo);
				//新增产品-唯一属性关联
				for (int attrSoleNum = 1; attrSoleNum < attrSoleAmount; attrSoleNum++) {
					String attrValue = request.getParameter("allSingleAttrVal1"+attrSoleNum);
					System.out.println(request.getParameter("allSingleAttrVal1"+attrSoleNum));
					Long attrId = Long.parseLong(request.getParameter("allSingleAttrId1"+attrSoleNum));
					Attribute attribute = this.attributeManagerDao.retrieveAttribute(attrId);
					this.productAttrManagerDao.saveProcutAttr(mcProductInfo, attribute, attrValue);
				}
				this.addStockAndProAttr();
				this.mcProductInfoManagerDao.modifyMcProductInfo(mcProductInfo);
				addActionMessage("New successfully!");
			}else {
				addActionMessage("New fail!");
			}
		} catch (Exception ex) {
			this.addActionError(ex.getMessage());
			ex.printStackTrace();
			addActionMessage("New fail!");
		}
		
		return this.query();
	}

	/**
	 * 修改唯一属性的值
	 */
	public void updateSoleAttr(){
		HttpServletRequest request = ServletActionContext.getRequest();
		for (int i = 1; i <= attrSoleAmount; i++) {
			Long productSoleAttrId = Long.parseLong(request.getParameter("productAttrId"+i));
			ProductAttr productAttr = this.productAttrManagerDao.retrieveProductAttr(productSoleAttrId);
			String productSoleAttrValue = request.getParameter("allSoleAttrVal"+i);
			productAttr.setAttrValue(productSoleAttrValue);
			this.productAttrManagerDao.modifyProductAttr(productAttr);
		}
	}
	
	/**
	 * 清除旧库存
	 */
	public void deleteProductStock(){
		if (ObjValid.isValid(this.oldStockId)) {
			List<String> stockIdList = FormatStringUtil.splitBySign(this.oldStockId, ",");
			for (int i = 0; i < stockIdList.size(); i++) {
				String realStockId = stockIdList.get(i).replaceAll(" ","");
				Long stockId = Long.parseLong(realStockId);
				Stock stock = this.stockManagerDao.retrieveStock(stockId);
				this.stockManagerDao.removeStock(stock);
			}
		}
	}
	
	
	
	public String editMcProductInfo() throws Exception {
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			Date editDate = new Date();
//			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//			editDate = sdf.parse(sdf.format(editDate));
			//修改产品对应的唯一属性的值
			McProductInfo mcProductInfo0 = this.mcProductInfoManagerDao.retrieveMcProductInfo(this.id);
			this.productAttrList = this.productAttrManagerDao.queryByProductAndAttrType(id, "0");
			if(productAttrList.size()==0){
				//新增产品-唯一属性关联
				for (int attrSoleNum = 1; attrSoleNum < attrSoleAmount; attrSoleNum++) {
					String attrValue = request.getParameter("allSingleAttrVal1"+attrSoleNum);
					System.out.println(request.getParameter("allSingleAttrVal1"+attrSoleNum));
					Long attrId = Long.parseLong(request.getParameter("allSingleAttrId1"+attrSoleNum));
					Attribute attribute = this.attributeManagerDao.retrieveAttribute(attrId);
					this.productAttrManagerDao.saveProcutAttr(mcProductInfo0, attribute, attrValue);
				}
			}else{
				this.updateSoleAttr();
			}
			
			
			//double oldPrice = mcProductInfo0.getShopPrice();
			mcProductInfo0.setTitle(mcProductInfo.getTitle());
			mcProductInfo0.setKeywords(mcProductInfo.getKeywords());
			mcProductInfo0.setDescription(mcProductInfo.getDescription());
			mcProductInfo0.setContent(mcProductInfo.getContent());
			mcProductInfo0.setIsNew(mcProductInfo.getIsNew());
			mcProductInfo0.setImgurl(mcProductInfo.getImgurl());
			mcProductInfo0.setIsSale(mcProductInfo.getIsSale());
			mcProductInfo0.setGiveIntegral(mcProductInfo.getGiveIntegral());
			mcProductInfo0.setMarketPrice(mcProductInfo.getMarketPrice());
			mcProductInfo0.setShopPrice(mcProductInfo.getShopPrice());
			mcProductInfo0.setPromotePrice(mcProductInfo.getPromotePrice());
			mcProductInfo0.setIsBest(mcProductInfo.getIsBest());
			mcProductInfo0.setUpdatetime(editDate);
			mcProductInfo0.setPara1(mcProductInfo.getPara1());
			mcProductInfo0.setPara2(mcProductInfo.getPara2());
			mcProductInfo0.setPara3(mcProductInfo.getPara3());
			mcProductInfo0.setPara4(mcProductInfo.getPara4());
			mcProductInfo0.setPara5(mcProductInfo.getPara5());
			mcProductInfo0.setPara6(mcProductInfo.getPara6());
			mcProductInfo0.setPara7(mcProductInfo.getPara7());
			mcProductInfo0.setPara8(mcProductInfo.getPara8());
			mcProductInfo0.setPara9(mcProductInfo.getPara9());
			mcProductInfo0.setPara10(mcProductInfo.getPara10());
			mcProductInfo0.setPara12(mcProductInfo.getPara12());
			mcProductInfo0.setOrderPara(mcProductInfo.getOrderPara());
			
			//更新产品图片
			if (ObjValid.isValid(mcProductInfo0.getImgurl())) {
				comUpload.setOldUploadFileName(mcProductInfo0.getImgurl());//旧文件的文件名，填上将删除旧文件
				comUpload.delectFile();
				mcProductInfo0.setImgurl(this.mcProductInfo.getImgurl());
			}
			
			
			this.deleteProductStock();
			this.addStockAndProAttr();
			mcProductInfo0.setRepertory(mcProductInfo.getRepertory());
			this.mcProductInfoManagerDao.modifyMcProductInfo(mcProductInfo0);
			//this.mcProductInfoManagerDao.updateProductAndPrice(mcProductInfo0, oldPrice);
			addActionMessage("Update successfully!");
			
		} catch (Exception ex) {
			ex.printStackTrace();
			this.addActionError(ex.getMessage());
			addActionMessage("Update fail!");
		}
		if (ObjValid.isValid(colId)) {
			return "productColumn";
		}
		return this.query();
	}

	public String delMcProductInfo() throws Exception {
		try {
			McProductInfo mcProductInfo = this.mcProductInfoManagerDao.retrieveMcProductInfo(this.id);
			if(productTypeManagerDao.findOrderAndCartInfobyProductId(this.id)){//判断该商品是否有数据在购物车或者订单里
				mcProductInfo.setIsSale("0");
				this.mcProductInfoManagerDao.modifyMcProductInfo(mcProductInfo);;//修改商品为下架
				if(this.productColumnManagerDao.findByProduct(this.id)){//判断该商品是否有数据在productColumn
					this.productColumnManagerDao.deleteByProduct(this.id);
				}
			}else{
				if(this.productColumnManagerDao.findByProduct(this.id)){//判断该商品是否有数据在productColumn
					this.productColumnManagerDao.deleteByProduct(this.id);
				}
				this.mcProductInfoManagerDao.removeMcProductInfo(this.id);
			}
			addActionMessage("Delete successfully!");
		} catch (Exception ex) {
			this.addActionError(ex.getMessage());
			addActionMessage("Delete fail!");
		}
		return this.query();
	}

	/**
	 * 根据栏目分页查询新闻内容信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String query() throws Exception {

		McProductInfo temp = null;

		temp = new McProductInfo();
		
		if (title == null)
			temp.setTitle("");
		else
			temp.setTitle(title);
		
		super.pager = this.mcProductInfoManagerDao.findMCProductInfo(pageSize, pageNo, temp);
		List resultList = super.pager.getResultList();
		this.setMcProductInfos(resultList);

		return ActionConstants.SUCCESS_KEY;
	}

	/**
	 * 把值按照各个可选参数放进对应的位置
	 */
	private void putValue2Map(){
		
		this.parameterValue = new LinkedHashMap<McParameter,String>();
		
		for (McParameter mcParameter : workOkMcParameterList) {
			if(mcParameter.getMark().equals("para1")){
				this.parameterValue.put(mcParameter, mcProductInfo.getPara1());
			}else if (mcParameter.getMark().equals("para2")) {
				this.parameterValue.put(mcParameter, mcProductInfo.getPara2());
			}else if (mcParameter.getMark().equals("para3")) {
				this.parameterValue.put(mcParameter, mcProductInfo.getPara3());
			}else if (mcParameter.getMark().equals("para4")) {
				this.parameterValue.put(mcParameter, mcProductInfo.getPara4());
			}else if (mcParameter.getMark().equals("para5")) {
				this.parameterValue.put(mcParameter, mcProductInfo.getPara5());
			}else if (mcParameter.getMark().equals("para6")) {
				this.parameterValue.put(mcParameter, mcProductInfo.getPara6());
			}else if (mcParameter.getMark().equals("para7")) {
				this.parameterValue.put(mcParameter, mcProductInfo.getPara7());
			}else if (mcParameter.getMark().equals("para8")) {
				this.parameterValue.put(mcParameter, mcProductInfo.getPara8());
			}else if (mcParameter.getMark().equals("para9")) {
				this.parameterValue.put(mcParameter, mcProductInfo.getPara9());
			}else if (mcParameter.getMark().equals("para10")) {
				this.parameterValue.put(mcParameter, mcProductInfo.getPara10());
			}else if (mcParameter.getMark().equals("para11")) {
				this.parameterValue.put(mcParameter, mcProductInfo.getPara11());
			}else if (mcParameter.getMark().equals("para12")) {
				this.parameterValue.put(mcParameter, mcProductInfo.getPara12());
			}else if (mcParameter.getMark().equals("para13")) {
				this.parameterValue.put(mcParameter, mcProductInfo.getPara13());
			}else if (mcParameter.getMark().equals("para14")) {
				this.parameterValue.put(mcParameter, mcProductInfo.getPara14());
			}else if (mcParameter.getMark().equals("para15")) {
				this.parameterValue.put(mcParameter, mcProductInfo.getPara15());
			}else if (mcParameter.getMark().equals("para16")) {
				this.parameterValue.put(mcParameter, mcProductInfo.getPara16());
			}else if (mcParameter.getMark().equals("para17")) {
				this.parameterValue.put(mcParameter, mcProductInfo.getPara17());
			}else if (mcParameter.getMark().equals("para18")) {
				this.parameterValue.put(mcParameter, mcProductInfo.getPara18());
			}else if (mcParameter.getMark().equals("para19")) {
				this.parameterValue.put(mcParameter, mcProductInfo.getPara19());
			}else if (mcParameter.getMark().equals("para20")) {
				this.parameterValue.put(mcParameter, mcProductInfo.getPara20());
			}
		}
	}
	
	/**
	 * 设置上传图片通用信息
	 * @throws Exception
	 */
	private void setPic() throws Exception {
		comUpload.setMaxSize(comUpload.getPhotoMaxSize());//上传文件最大尺寸
		comUpload.setSavePath(Constants.FILE_SEPARATOR+"upload"+Constants.FILE_SEPARATOR+"enterprice");//上传文件保存路径
		comUpload.setAllowTypes(comUpload.getAllowTypePhoto());//上传文件所允许的格式
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public McProductInfo getMcProductInfo() {
		return mcProductInfo;
	}

	public void setMcProductInfo(McProductInfo mcProductInfo) {
		this.mcProductInfo = mcProductInfo;
	}

	public List<McProductInfo> getMcProductInfos() {
		return mcProductInfos;
	}

	public void setMcProductInfos(List<McProductInfo> mcProductInfos) {
		this.mcProductInfos = mcProductInfos;
	}

	public String getFather() {
		return father;
	}

	public void setFather(String father) {
		this.father = father;
	}

	public EnterpriseColumn getColumn() {
		return column;
	}

	public void setColumn(EnterpriseColumn column) {
		this.column = column;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public McParameterManagerDao getMcParameterManagerDao() {
		return mcParameterManagerDao;
	}

	public void setMcParameterManagerDao(McParameterManagerDao mcParameterManagerDao) {
		this.mcParameterManagerDao = mcParameterManagerDao;
	}

	public Map<McParameter, String> getParameterValue() {
		return parameterValue;
	}

	public void setParameterValue(Map<McParameter, String> parameterValue) {
		this.parameterValue = parameterValue;
	}

	public List<McParameter> getWorkOkMcParameterList() {
		return workOkMcParameterList;
	}

	public void setWorkOkMcParameterList(List<McParameter> workOkMcParameterList) {
		this.workOkMcParameterList = workOkMcParameterList;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public CommonUpload getComUpload() {
		return comUpload;
	}

	public void setComUpload(CommonUpload comUpload) {
		this.comUpload = comUpload;
	}

	public String getPara11FileName() {
		return para11FileName;
	}

	public void setPara11FileName(String para11FileName) {
		this.para11FileName = para11FileName;
	}

	public String getPara12FileName() {
		return para12FileName;
	}

	public void setPara12FileName(String para12FileName) {
		this.para12FileName = para12FileName;
	}

	public String getPara13FileName() {
		return para13FileName;
	}

	public void setPara13FileName(String para13FileName) {
		this.para13FileName = para13FileName;
	}

	public String getPara14FileName() {
		return para14FileName;
	}

	public void setPara14FileName(String para14FileName) {
		this.para14FileName = para14FileName;
	}

	public String getPara15FileName() {
		return para15FileName;
	}

	public void setPara15FileName(String para15FileName) {
		this.para15FileName = para15FileName;
	}

	public String getPara16FileName() {
		return para16FileName;
	}

	public void setPara16FileName(String para16FileName) {
		this.para16FileName = para16FileName;
	}

	public String getPara17FileName() {
		return para17FileName;
	}

	public void setPara17FileName(String para17FileName) {
		this.para17FileName = para17FileName;
	}

	public String getPara18FileName() {
		return para18FileName;
	}

	public void setPara18FileName(String para18FileName) {
		this.para18FileName = para18FileName;
	}

	public String getPara19FileName() {
		return para19FileName;
	}

	public void setPara19FileName(String para19FileName) {
		this.para19FileName = para19FileName;
	}

	public String getPara20FileName() {
		return para20FileName;
	}

	public void setPara20FileName(String para20FileName) {
		this.para20FileName = para20FileName;
	}

	public File getPara11() {
		return para11;
	}

	public void setPara11(File para11) {
		this.para11 = para11;
	}

	public File getPara12() {
		return para12;
	}

	public void setPara12(File para12) {
		this.para12 = para12;
	}

	public File getPara13() {
		return para13;
	}

	public void setPara13(File para13) {
		this.para13 = para13;
	}

	public File getPara14() {
		return para14;
	}

	public void setPara14(File para14) {
		this.para14 = para14;
	}

	public File getPara15() {
		return para15;
	}

	public void setPara15(File para15) {
		this.para15 = para15;
	}

	public File getPara16() {
		return para16;
	}

	public void setPara16(File para16) {
		this.para16 = para16;
	}

	public File getPara17() {
		return para17;
	}

	public void setPara17(File para17) {
		this.para17 = para17;
	}

	public File getPara18() {
		return para18;
	}

	public void setPara18(File para18) {
		this.para18 = para18;
	}

	public File getPara19() {
		return para19;
	}

	public void setPara19(File para19) {
		this.para19 = para19;
	}

	public File getPara20() {
		return para20;
	}

	public void setPara20(File para20) {
		this.para20 = para20;
	}

	public String getIsShowInsert() {
		return isShowInsert;
	}

	public void setIsShowInsert(String isShowInsert) {
		this.isShowInsert = isShowInsert;
	}

	public String getImgurl() {
		return imgurl;
	}

	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}

	public List<ProductType> getProductTypes() {
		return productTypes;
	}

	public void setProductTypes(List<ProductType> productTypes) {
		this.productTypes = productTypes;
	}

	public Long getTypeId() {
		return typeId;
	}

	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}

	public List<Attribute> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<Attribute> attributes) {
		this.attributes = attributes;
	}

	public String[] getStocklist() {
		return stocklist;
	}

	public void setStocklist(String[] stocklist) {
		this.stocklist = stocklist;
	}

	public int getAttrAmount() {
		return attrAmount;
	}

	public void setAttrAmount(int attrAmount) {
		this.attrAmount = attrAmount;
	}

	public List<StockVo> getStockVoList() {
		return stockVoList;
	}

	public void setStockVoList(List<StockVo> stockVoList) {
		this.stockVoList = stockVoList;
	}

	public int getAttrSoleAmount() {
		return attrSoleAmount;
	}

	public void setAttrSoleAmount(int attrSoleAmount) {
		this.attrSoleAmount = attrSoleAmount;
	}

	public List<ProductAttr> getProductAttrList() {
		return productAttrList;
	}

	public void setProductAttrList(List<ProductAttr> productAttrList) {
		this.productAttrList = productAttrList;
	}

	public String getOldStockId() {
		return oldStockId;
	}

	public void setOldStockId(String oldStockId) {
		this.oldStockId = oldStockId;
	}

	public Attribute getAttribute() {
		return attribute;
	}

	public void setAttribute(Attribute attribute) {
		this.attribute = attribute;
	}

	public boolean isProductTypeExist() {
		return ProductTypeExist;
	}

	public void setProductTypeExist(boolean productTypeExist) {
		ProductTypeExist = productTypeExist;
	}
	
	public ProductType getProductType() {
		return productType;
	}

	public void setProductType(ProductType productType) {
		this.productType = productType;
	}

	public int getAllSingleAttrVal1() {
		return allSingleAttrVal1;
	}

	public void setAllSingleAttrVal1(int allSingleAttrVal1) {
		this.allSingleAttrVal1 = allSingleAttrVal1;
	}


	public Long getColId() {
		return colId;
	}

	public void setColId(Long colId) {
		this.colId = colId;
	}


}