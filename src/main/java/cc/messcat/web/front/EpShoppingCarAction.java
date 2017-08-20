package cc.messcat.web.front;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;

import cc.messcat.entity.Address;
import cc.messcat.entity.CartInfo;
import cc.messcat.entity.McProductInfo;
import cc.messcat.entity.Member;
import cc.messcat.entity.Stock;
import cc.messcat.vo.CartInfoVo;
import cc.modules.util.FormatStringUtil;
import cc.modules.util.ObjValid;

/**
 * 	
 *  Class Name: EpShoppingCarAction.java
 *  Function:	购物车功能，在页面点击加入购物车或者删除购物车商品时会调用Action
 *  Modifications:   
 *  @author Kael  DateTime 2015-04-28 上午11:17:28
 */
public class EpShoppingCarAction extends FrontAction{
	/**
	 * 单条产品信息
	 * */
	private McProductInfo mcProductInfo;
	private Long productId;
	private Long amount;
	private String proAttrIds;
	private Long orderInfoId;
	private List<Long> choose;
	private int carNum;
	private String loginName;
	private List<CartInfoVo> cartInfoVos;
	private Long cartId;
	private Double price;
	private Double totalPrice;
	private String flag;
	private Long addressId;
	private List<Address> addressList;
	private Address address;
	//购物车点击结算 拼接选中的购物车ids
	private String cartInfoIds;
	
	public EpShoppingCarAction() {
	}
	
	public String execute() throws Exception {
//		super.init();
		super.findPageTypeFromFront();
		String result=null;
		if(super.selectPageType!=null&&!"".equals(super.selectPageType)){
			if("addProductToShoppingCar".equals(super.selectPageType)){
				this.addProductToShoppingCar();
				result = "buyNow";
			} else if("deleteFormCart".equals(selectPageType)) {
				result = this.deleteProductOrder();
			} else if("deleteFormCart2".equals(selectPageType)) {
				result = this.deleteProduct();
			} else if("showCar".equals(selectPageType)){
				result = this.showMyCar();
			} else if("showCar2".equals(selectPageType)){
				result = this.showMyCar2();
			}else if("showCar3".equals(selectPageType)){
					result = this.showMyCar3();
			} else if("addProductToCar".equals(selectPageType)){
				this.addProductToCar();
				result = "buyNow";
			}
		}
		return result;
	}
	public String addToCart() throws Exception{
		Map session = ActionContext.getContext().getSession();
		Member member = (Member) session.get("member");
		McProductInfo mcProductInfo = this.mcProductInfoManagerDao.retrieveMcProductInfo(productId);
		CartInfo cartInfo = new CartInfo();
		cartInfo.setBuyAmount(1L);
		cartInfo.setMember(member);
		cartInfo.setProduct(mcProductInfo);
		cartInfo.setProductAttrIds("45");
		cartInfo.setProductPrice(mcProductInfo.getShopPrice());
		cartInfo.setProductTotal(mcProductInfo.getShopPrice()*1);
		cartInfo.setCreateTime(new Date());
		this.cartInfoManagerDao.addProductToCart(cartInfo);
		return showMyCar();
	}
	
	/**
	 * 删除购物车商品
	 */
	public String deleteProduct(){
		String event = "delete_success";
		this.cartInfoManagerDao.removeCartInfo(cartId);
		if ("deleteFormCart2".equals(selectPageType)) {
			event = "delete_success2";
		}
		return event;
	}
	
	/**
	 * 删除订单商品
	 */
	public String deleteProductOrder(){
		String event = "delete_success";
		Map session = ActionContext.getContext().getSession();
		String cartInfoIDS = session.get("cartInfoIDS").toString();
		List<String> cartInfoIDsList = FormatStringUtil.splitBySign(cartInfoIDS, ",");
//		cartInfoIDsList.remove(cartId);//删除购物车id
		if (ObjValid.isValid(cartInfoIDsList)) {
			for(int i=0;i<cartInfoIDsList.size();i++){  
	            String obj = (String)cartInfoIDsList.get(i);  
	            if(obj.equals(String.valueOf(cartId))){  
	            	cartInfoIDsList.remove(i);  
	                //for循环是先根据中间的值判断是否为true，然后再执行后面的i++  
	                i--;  
	            }  
	        }
		}
		String newCartInfoIDS = "";
		if (ObjValid.isValid(cartInfoIDsList)) {
			for(int i=0;i<cartInfoIDsList.size();i++){
				newCartInfoIDS = newCartInfoIDS +cartInfoIDsList.get(i)+ ",";
			}
			newCartInfoIDS = newCartInfoIDS.substring(0, newCartInfoIDS.length()-1);
		}
		session.put("cartInfoIDS", newCartInfoIDS);
//		this.cartInfoManagerDao.removeCartInfo(cartId);
		return event;
	}
	
	/**
	 * 异步修改购物车
	 * @return
	 */
	public String updateCart(){
		Stock condition = new Stock();
		Long repertory = 0L;
		this.totalPrice = 0D;
		CartInfo cartInfo = this.cartInfoManagerDao.retrieveCartInfo(cartId);
		condition.setProductAttrIds(cartInfo.getProductAttrIds());
		condition.setProduct(cartInfo.getProduct());
		Stock stock = this.stockManagerDao.findByProAttr(condition);
		if (ObjValid.isValid(stock)) {
			repertory = stock.getAmount();
			this.price = stock.getPrice();
		}
		if (repertory-this.amount < 0) {
			this.amount=repertory;
			this.flag="false";
		}else {
			this.flag="true";
			cartInfo.setBuyAmount(amount);
			cartInfo.setProductPrice(stock.getPrice());
			this.totalPrice = amount*stock.getPrice();
			cartInfo.setProductTotal(totalPrice);
			this.cartInfoManagerDao.modifyCartInfo(cartInfo);
		}
		return "OperateShoppingCar";
	}
	
	@SuppressWarnings({ "rawtypes" })
	public String showMyCar() throws Exception{
		Map session = ActionContext.getContext().getSession();
		
		//TODO 修改会员
		Member member = (Member)session.get("member");
		CartInfo cartInfoPro = (CartInfo)session.get("cartInfoPro");
		if (ObjValid.isValid(this.addressId)) {
			this.address = this.addressManagerDao.retrieveAddress(addressId);
		}else {
			this.address = this.addressManagerDao.getDefaultAddress(member);
		}
		
		if (ObjValid.isValid(this.cartInfoIds)) {
			this.cartInfoVos = this.cartInfoManagerDao.buildCart(member,this.cartInfoIds);
			session.put("cartInfoIDS", this.cartInfoIds);
		}else{
			if(ObjValid.isValid(cartInfoPro)){
				this.cartInfoVos = this.cartInfoManagerDao.buildCart(member,cartInfoPro);
				session.remove("cartInfoPro");
			}else{
				if(ObjValid.isValid(session.get("cartInfoIDS"))){
					this.cartInfoVos = this.cartInfoManagerDao.buildCart(member,session.get("cartInfoIDS").toString());
				}else{
					this.cartInfoVos = null;
				}
			}
		}
		return "myCar";
	}
	@SuppressWarnings({ "rawtypes" })
	public String showMyCar2() throws Exception{
		Map session = ActionContext.getContext().getSession();
		
		//TODO 修改会员
		Member member = (Member)session.get("member");
		if (ObjValid.isValid(this.addressId)) {
			this.address = this.addressManagerDao.retrieveAddress(addressId);
		}else {
			this.address = this.addressManagerDao.getDefaultAddress(member);
		}
		this.cartInfoVos = this.cartInfoManagerDao.buildCart(member);
		return "myCar2";
	}
	
	@SuppressWarnings({ "rawtypes" })
	public String showMyCar3() throws Exception{
		Map session = ActionContext.getContext().getSession();
		
		//TODO 修改会员
		Member member = (Member)session.get("member");
		if (ObjValid.isValid(this.addressId)) {
			this.address = this.addressManagerDao.retrieveAddress(addressId);
		}else {
			this.address = this.addressManagerDao.getDefaultAddress(member);
		}
		this.cartInfoVos = this.cartInfoManagerDao.buildCart(member);
		return "myCar3";
	}
	
	
	/**
	 * 添加商品到购物车中 ---新
	 * */
	public String addProductToCar(){
		Map session = ActionContext.getContext().getSession();
		Member member = (Member) session.get("member");
		McProductInfo mcProductInfo = this.mcProductInfoManagerDao.retrieveMcProductInfo(productId);
		CartInfo cartInfo = new CartInfo();
		cartInfo.setBuyAmount(amount);
		cartInfo.setMember(member);
		cartInfo.setProduct(mcProductInfo);
		cartInfo.setProductAttrIds(proAttrIds);
		//当第一次购买的时候为原价  108  第二次以后为98
		if (ObjValid.isValid(member.getDistributor())) {
			if(member.getDistributor().equals("1")){
				cartInfo.setProductPrice(price);
				cartInfo.setProductTotal(price*amount);
			}else{
				cartInfo.setProductPrice(price);
				cartInfo.setProductTotal(price*amount);	
			}
		}
		cartInfo.setCreateTime(new Date());
		this.cartInfoManagerDao.addProductToShoppingCart(cartInfo);
		return "success";
	}
	/**
	 * 添加商品到购物车中  ——————》现改为订单中
	 * @throws Exception 
	 * */
	@SuppressWarnings("rawtypes")
	public String addProductToShoppingCar() throws Exception{
		Map session = ActionContext.getContext().getSession();
		Member member = (Member) session.get("member");
		McProductInfo mcProductInfo = this.mcProductInfoManagerDao.retrieveMcProductInfo(productId);
		CartInfo cartInfo = new CartInfo();
		cartInfo.setBuyAmount(amount);
		cartInfo.setMember(member);
		cartInfo.setProduct(mcProductInfo);
		cartInfo.setProductAttrIds(proAttrIds);
		//当第一次购买的时候为原价  108  第二次以后为98
		if (ObjValid.isValid(member.getDistributor())) {
			if(member.getDistributor().equals("1")){
				cartInfo.setProductPrice(price);
				cartInfo.setProductTotal(price*amount);
			}else{
				cartInfo.setProductPrice(price);
				cartInfo.setProductTotal(price*amount);	
			}
		}
		
		cartInfo.setCreateTime(new Date());
		this.cartInfoManagerDao.addProductToCart(cartInfo);
		session.put("cartInfoPro", cartInfo);
		return "success";
	}
	
	
	
	public McProductInfo getMcProductInfo() {
		return mcProductInfo;
	}

	public void setMcProductInfo(McProductInfo mcProductInfo) {
		this.mcProductInfo = mcProductInfo;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}


	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	
	
	public Long getOrderInfoId() {
		return orderInfoId;
	}

	public void setOrderInfoId(Long orderInfoId) {
		this.orderInfoId = orderInfoId;
	}

	public List<Long> getChoose() {
		return choose;
	}

	public void setChoose(List<Long> choose) {
		this.choose = choose;
	}

	public int getCarNum() {
		return carNum;
	}

	public void setCarNum(int carNum) {
		this.carNum = carNum;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getProAttrIds() {
		return proAttrIds;
	}

	public void setProAttrIds(String proAttrIds) {
		this.proAttrIds = proAttrIds;
	}

	public List<CartInfoVo> getCartInfoVos() {
		return cartInfoVos;
	}

	public void setCartInfoVos(List<CartInfoVo> cartInfoVos) {
		this.cartInfoVos = cartInfoVos;
	}

	public Long getCartId() {
		return cartId;
	}

	public void setCartId(Long cartId) {
		this.cartId = cartId;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Long getAddressId() {
		return addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

	public List<Address> getAddressList() {
		return addressList;
	}

	public void setAddressList(List<Address> addressList) {
		this.addressList = addressList;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getCartInfoIds() {
		return cartInfoIds;
	}

	public void setCartInfoIds(String cartInfoIds) {
		this.cartInfoIds = cartInfoIds;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}


	
}
