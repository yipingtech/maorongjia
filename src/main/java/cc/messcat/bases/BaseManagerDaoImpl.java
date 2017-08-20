package cc.messcat.bases;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import cc.messcat.dao.activity.ActivityInfoDao;
import cc.messcat.dao.address.AddressDao;
import cc.messcat.dao.bankcard.BankCardDao;
import cc.messcat.dao.bonus.BonusRecordDao;
import cc.messcat.dao.cart.CartInfoDao;
import cc.messcat.dao.collection.EpNewsDao;
import cc.messcat.dao.collection.McProductInfoDao;
import cc.messcat.dao.collection.ProductColumnDao;
import cc.messcat.dao.column.EpColumnDao;
import cc.messcat.dao.data.DataHandlerDao;
import cc.messcat.dao.drawback.ProductDrawbackDao;
import cc.messcat.dao.dynamic.EpAdDao;
import cc.messcat.dao.dynamic.EpLinksDao;
import cc.messcat.dao.finance.BonusDao;
import cc.messcat.dao.finance.TicketInfoDao;
import cc.messcat.dao.member.CommissionInfoDao;
import cc.messcat.dao.member.EvaluateDao;
import cc.messcat.dao.member.IntegralRuleDao;
import cc.messcat.dao.member.IntergralInfoDao;
import cc.messcat.dao.member.MemberBonusDao;
import cc.messcat.dao.member.MemberDao;
import cc.messcat.dao.member.ParameterSetDao;
import cc.messcat.dao.member.RechargeInfoDao;
import cc.messcat.dao.member.ReportInfoDao;
import cc.messcat.dao.order.OrderInfoDao;
import cc.messcat.dao.pay.PayOrderDao;
import cc.messcat.dao.product.AttributeDao;
import cc.messcat.dao.product.ProductAttrDao;
import cc.messcat.dao.product.ProductTypeDao;
import cc.messcat.dao.product.StockDao;
import cc.messcat.dao.style.WebSkinColorDao;
import cc.messcat.dao.style.WebSkinDao;
import cc.messcat.dao.system.AlterUrlDao;
import cc.messcat.dao.system.AreaDao;
import cc.messcat.dao.system.AuthoritiesDao;
import cc.messcat.dao.system.CityDao;
import cc.messcat.dao.system.McParameterDao;
import cc.messcat.dao.system.PageTypeDao;
import cc.messcat.dao.system.ProvinceDao;
import cc.messcat.dao.system.RolesAuthoritiesDao;
import cc.messcat.dao.system.RolesDao;
import cc.messcat.dao.system.StandbyDao;
import cc.messcat.dao.system.UsersDao;
import cc.messcat.dao.system.UsersRolesDao;
import cc.messcat.dao.system.WebSiteDao;
import cc.modules.commons.Pager;

public class BaseManagerDaoImpl implements BaseManagerDao,Serializable {

	private static final long serialVersionUID = -2308147651026388956L;
	protected BaseDao baseDao;
	protected UsersDao usersDao;
	protected RolesDao rolesDao;
	protected UsersRolesDao usersRolesDao;
	protected RolesAuthoritiesDao rolesAuthoritiesDao;
	protected AuthoritiesDao authoritiesDao;
	protected EpNewsDao epNewsDao;
	protected EpColumnDao epColumnDao;
	protected EpAdDao epAdDao;
	protected EpLinksDao epLinksDao;
	protected WebSiteDao webSiteDao;
	protected WebSkinDao webSkinDao;
	protected WebSkinColorDao webSkinColorDao;
	protected DataHandlerDao dataHandlerDao;
	
	//备用字段模块
	protected StandbyDao standbyDao;
	//产品模块
	protected McParameterDao mcParameterDao;
	protected McProductInfoDao mcProductInfoDao;
	//页面类型模块
	protected PageTypeDao pageTypeDao;
	//批量修改URL模块
	protected AlterUrlDao alterUrlDao;
	//产品栏目
	protected ProductColumnDao productColumnDao;
	//产品类型
	protected ProductTypeDao productTypeDao;
	
	//属性
	protected AttributeDao attributeDao;
	
	//购物车、订单清单、商品订单
	protected CartInfoDao cartInfoDao;
	
	protected OrderInfoDao orderInfoDao;
	
	protected PayOrderDao payOrderDao;
	//产品-属性
	protected ProductAttrDao productAttrDao;
	//库存
	protected StockDao stockDao;
	
	//地区
	protected AreaDao areaDao;
	
	protected CityDao cityDao;
	
	protected ProvinceDao provinceDao;
	
	protected AddressDao addressDao;
	
	protected ProductDrawbackDao productDrawbackDao;
	
	protected MemberDao memberDao;
	
	protected CommissionInfoDao commissionInfoDao;
	
	protected IntergralInfoDao intergralInfoDao;
	
	protected RechargeInfoDao rechargeInfoDao;
	
	protected ReportInfoDao reportInfoDao;
	
	protected ParameterSetDao parameterSetDao;
	
	protected BonusDao bonusDao;
	
	protected MemberBonusDao memberBonusDao;
	
	protected IntegralRuleDao integralRuleDao;
	
	protected EvaluateDao evaluateDao;
	
	protected ActivityInfoDao activityInfoDao;
	
	protected TicketInfoDao ticketInfoDao;
	
	protected BonusRecordDao bonusRecordDao;
	
	protected BankCardDao bankCardDao;


	public BankCardDao getBankCardDao() {
		return bankCardDao;
	}


	public void setBankCardDao(BankCardDao bankCardDao) {
		this.bankCardDao = bankCardDao;
	}


	public BonusRecordDao getBonusRecordDao() {
		return bonusRecordDao;
	}


	public void setBonusRecordDao(BonusRecordDao bonusRecordDao) {
		this.bonusRecordDao = bonusRecordDao;
	}


	public BaseManagerDaoImpl() {
	}

	
	public ProductColumnDao getProductColumnDao() {
		return productColumnDao;
	}

	public void setProductColumnDao(ProductColumnDao productColumnDao) {
		this.productColumnDao = productColumnDao;
	}

	public WebSiteDao getWebSiteDao() {
		return webSiteDao;
	}

	public void setWebSiteDao(WebSiteDao webSiteDao) {
		this.webSiteDao = webSiteDao;
	}

	public EpNewsDao getEpNewsDao() {
		return epNewsDao;
	}

	public void setEpNewsDao(EpNewsDao epNewsDao) {
		this.epNewsDao = epNewsDao;
	}

	public EpColumnDao getEpColumnDao() {
		return epColumnDao;
	}

	public void setEpColumnDao(EpColumnDao epColumnDao) {
		this.epColumnDao = epColumnDao;
	}

	public EpAdDao getEpAdDao() {
		return epAdDao;
	}

	public void setEpAdDao(EpAdDao epAdDao) {
		this.epAdDao = epAdDao;
	}

	public EpLinksDao getEpLinksDao() {
		return epLinksDao;
	}

	public void setEpLinksDao(EpLinksDao epLinksDao) {
		this.epLinksDao = epLinksDao;
	}

	public RolesDao getRolesDao() {
		return rolesDao;
	}

	public void setRolesDao(RolesDao rolesDao) {
		this.rolesDao = rolesDao;
	}

	public UsersDao getUsersDao() {
		return usersDao;
	}

	public void setUsersDao(UsersDao usersDao) {
		this.usersDao = usersDao;
	}

	public UsersRolesDao getUsersRolesDao() {
		return usersRolesDao;
	}

	public void setUsersRolesDao(UsersRolesDao usersRolesDao) {
		this.usersRolesDao = usersRolesDao;
	}

	public RolesAuthoritiesDao getRolesAuthoritiesDao() {
		return rolesAuthoritiesDao;
	}

	public void setRolesAuthoritiesDao(RolesAuthoritiesDao rolesAuthoritiesDao) {
		this.rolesAuthoritiesDao = rolesAuthoritiesDao;
	}

	public AuthoritiesDao getAuthoritiesDao() {
		return authoritiesDao;
	}

	public void setAuthoritiesDao(AuthoritiesDao authoritiesDao) {
		this.authoritiesDao = authoritiesDao;
	}

	public WebSkinColorDao getWebSkinColorDao() {
		return webSkinColorDao;
	}

	public void setWebSkinColorDao(WebSkinColorDao webSkinColorDao) {
		this.webSkinColorDao = webSkinColorDao;
	}

	public WebSkinDao getWebSkinDao() {
		return webSkinDao;
	}

	public void setWebSkinDao(WebSkinDao webSkinDao) {
		this.webSkinDao = webSkinDao;
	}

	public DataHandlerDao getDataHandlerDao() {
		return dataHandlerDao;
	}

	public void setDataHandlerDao(DataHandlerDao dataHandlerDao) {
		this.dataHandlerDao = dataHandlerDao;
	}

	public StandbyDao getStandbyDao() {
		return standbyDao;
	}

	public void setStandbyDao(StandbyDao standbyDao) {
		this.standbyDao = standbyDao;
	}

	public McParameterDao getMcParameterDao() {
		return mcParameterDao;
	}

	public void setMcParameterDao(McParameterDao mcParameterDao) {
		this.mcParameterDao = mcParameterDao;
	}

	public McProductInfoDao getMcProductInfoDao() {
		return mcProductInfoDao;
	}

	public void setMcProductInfoDao(McProductInfoDao mcProductInfoDao) {
		this.mcProductInfoDao = mcProductInfoDao;
	}

	public PageTypeDao getPageTypeDao() {
		return pageTypeDao;
	}

	public void setPageTypeDao(PageTypeDao pageTypeDao) {
		this.pageTypeDao = pageTypeDao;
	}

	public AlterUrlDao getAlterUrlDao() {
		return alterUrlDao;
	}

	public void setAlterUrlDao(AlterUrlDao alterUrlDao) {
		this.alterUrlDao = alterUrlDao;
	}

	public BaseDao getBaseDao() {
		return baseDao;
	}

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	@Override
	public void save(Object obj) {
		baseDao.save(obj);
	}

	@Override
	public void update(Object obj) {
		baseDao.update(obj);
	}

	@Override
	public void delete(Long id, String objName) {
		baseDao.delete(id, objName);
	}

	@Override
	public Object get(Long id, String objName) {
		return baseDao.get(id, objName);
	}

	@Override
	public List getAll(String objName) {
		return baseDao.getAll(objName);
	}
	
	/**
	 * @param objName
	 * @param status
	 * @return
	 */
	public List findAllByStatus(String objName, String status) {
		return baseDao.findAllByStatus(objName, status);
	}

	@Override
	public int update(Class entityClass, Map<String, Object> props,
			Map<String, Object> attrs) {
		return baseDao.update(entityClass, props, attrs);
	}

	@Override
	public void delete(Object entity) {
		baseDao.delete(entity);
	}

	@Override
	public int queryMaxId(Class entityClass) {
		return baseDao.queryMaxId(entityClass);
	}

	@Override
	public int delete(Class entityClass, Map<String, Object> attrs) {
		return baseDao.delete(entityClass, attrs);
	}

	@Override
	public int deleteOr(Class entityClass, Map<String, Object> attrs) {
		return baseDao.deleteOr(entityClass, attrs);
	}

	@Override
	public <T> List<T> queryList(Class entityClass, String ids) {
		return baseDao.queryList(entityClass, ids);
	}
	
	@Override
	public <T> List<T> queryNotInList(Class entityClass, String ids) {
		// TODO Auto-generated method stub
		return baseDao.queryNotInList(entityClass, ids);
	}

	@Override
	public <T> T query(Class<T> entityClass, Map<String, Object> attrs) {
		return baseDao.query(entityClass, attrs);
	}

	@Override
	public <T> T query(Class<T> entityClass, String likeAttr, String likeValue,
			Map<String, Object>... attrs) {
		return baseDao.query(entityClass, likeAttr, likeValue, attrs);
	}

	@Override
	public long queryTotalRecord(Class entityClass,
			Map<String, Object>... attrs) {
		return baseDao.queryTotalRecord(entityClass, attrs);
	}

	@Override
	public long queryTotalRecord(Class entityClass, String likeAttr,
			String likeValue) {
		return baseDao.queryTotalRecord(entityClass, likeAttr, likeValue);
	}

	@Override
	public <T> List<T> queryList(Class<T> entityClass, int pageStart,
			int pageSize, String orderAttr, String orderType,
			Map<String, Object>... attrs) {
		return baseDao.queryList(entityClass, pageStart, pageSize, orderAttr, orderType, attrs);
	}

	@Override
	public <T> List<T> queryList2(Class<T> entityClass, int pageStart,
			int pageSize, String orderAttr, String orderType, String likeAttr,
			String likeValue) {
		return baseDao.queryList2(entityClass, pageStart, pageSize, orderAttr, orderType, likeAttr, likeValue);
	}

	@Override
	public <T> List<T> queryList(Class<T> entityClass, String orderAttr,
			String orderType, Map<String, Object>... attrs) {
		return baseDao.queryList(entityClass, orderAttr, orderType, attrs);
	}

	@Override
	public <T> List<T> queryOrList(Class<T> entityClass, String orderAttr,
			String orderType, Map<String, Object>... attrs) {
		return baseDao.queryOrList(entityClass, orderAttr, orderType, attrs);
	}

	@Override
	public <T> List<T> queryList(Class<T> entityClass, String orderAttr,
			String orderType, String groupAttr, Map<String, Object>... attrs) {
		return baseDao.queryList(entityClass, orderAttr, orderType, groupAttr, attrs);
	}

	@Override
	public <T> List<T> likeQueryList(Class<T> entityClass, String orderAttr,
			String orderType, String likeAttr, String likeValue, int... limit) {
		return baseDao.likeQueryList(entityClass, orderAttr, orderType, likeAttr, likeValue, limit);
	}
	
	public Object getObjectByProperty(String propertyName, Object value,
		Class entityType, String status) {
	return baseDao.getObjectByProperty(propertyName, value, entityType, status);
}
	
	@Override
	public Pager retrieveObjectsPager(int pageSize, int pageNo, Object object, String orderAttr,String orderType, String status) {
		return baseDao.retrieveObjectsPager(pageSize, pageNo, object, orderAttr, orderType, status);
	}

	public ProductTypeDao getProductTypeDao() {
		return productTypeDao;
	}




	public void setProductTypeDao(ProductTypeDao productTypeDao) {
		this.productTypeDao = productTypeDao;
	}


	public AttributeDao getAttributeDao() {
		return attributeDao;
	}


	public void setAttributeDao(AttributeDao attributeDao) {
		this.attributeDao = attributeDao;
	}


	public CartInfoDao getCartInfoDao() {
		return cartInfoDao;
	}


	public void setCartInfoDao(CartInfoDao cartInfoDao) {
		this.cartInfoDao = cartInfoDao;
	}


	public OrderInfoDao getOrderInfoDao() {
		return orderInfoDao;
	}


	public void setOrderInfoDao(OrderInfoDao orderInfoDao) {
		this.orderInfoDao = orderInfoDao;
	}


	public PayOrderDao getPayOrderDao() {
		return payOrderDao;
	}


	public void setPayOrderDao(PayOrderDao payOrderDao) {
		this.payOrderDao = payOrderDao;
	}


	public ProductAttrDao getProductAttrDao() {
		return productAttrDao;
	}


	public void setProductAttrDao(ProductAttrDao productAttrDao) {
		this.productAttrDao = productAttrDao;
	}


	public StockDao getStockDao() {
		return stockDao;
	}


	public void setStockDao(StockDao stockDao) {
		this.stockDao = stockDao;
	}


	public AreaDao getAreaDao() {
		return areaDao;
	}


	public void setAreaDao(AreaDao areaDao) {
		this.areaDao = areaDao;
	}


	public CityDao getCityDao() {
		return cityDao;
	}


	public void setCityDao(CityDao cityDao) {
		this.cityDao = cityDao;
	}


	public ProvinceDao getProvinceDao() {
		return provinceDao;
	}


	public void setProvinceDao(ProvinceDao provinceDao) {
		this.provinceDao = provinceDao;
	}


	public AddressDao getAddressDao() {
		return addressDao;
	}


	public void setAddressDao(AddressDao addressDao) {
		this.addressDao = addressDao;
	}


	public ProductDrawbackDao getProductDrawbackDao() {
		return productDrawbackDao;
	}


	public void setProductDrawbackDao(ProductDrawbackDao productDrawbackDao) {
		this.productDrawbackDao = productDrawbackDao;
	}


	public MemberDao getMemberDao() {
		return memberDao;
	}


	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}


	public CommissionInfoDao getCommissionInfoDao() {
		return commissionInfoDao;
	}


	public void setCommissionInfoDao(CommissionInfoDao commissionInfoDao) {
		this.commissionInfoDao = commissionInfoDao;
	}


	public IntergralInfoDao getIntergralInfoDao() {
		return intergralInfoDao;
	}


	public void setIntergralInfoDao(IntergralInfoDao intergralInfoDao) {
		this.intergralInfoDao = intergralInfoDao;
	}


	public RechargeInfoDao getRechargeInfoDao() {
		return rechargeInfoDao;
	}


	public void setRechargeInfoDao(RechargeInfoDao rechargeInfoDao) {
		this.rechargeInfoDao = rechargeInfoDao;
	}


	public ReportInfoDao getReportInfoDao() {
		return reportInfoDao;
	}


	public void setReportInfoDao(ReportInfoDao reportInfoDao) {
		this.reportInfoDao = reportInfoDao;
	}


	public ParameterSetDao getParameterSetDao() {
		return parameterSetDao;
	}


	public void setParameterSetDao(ParameterSetDao parameterSetDao) {
		this.parameterSetDao = parameterSetDao;
	}


	public BonusDao getBonusDao() {
		return bonusDao;
	}


	public void setBonusDao(BonusDao bonusDao) {
		this.bonusDao = bonusDao;
	}


	public MemberBonusDao getMemberBonusDao() {
		return memberBonusDao;
	}


	public void setMemberBonusDao(MemberBonusDao memberBonusDao) {
		this.memberBonusDao = memberBonusDao;
	}


	public IntegralRuleDao getIntegralRuleDao() {
		return integralRuleDao;
	}


	public void setIntegralRuleDao(IntegralRuleDao integralRuleDao) {
		this.integralRuleDao = integralRuleDao;
	}


	public EvaluateDao getEvaluateDao() {
		return evaluateDao;
	}


	public void setEvaluateDao(EvaluateDao evaluateDao) {
		this.evaluateDao = evaluateDao;
	}


	public ActivityInfoDao getActivityInfoDao() {
		return activityInfoDao;
	}


	public void setActivityInfoDao(ActivityInfoDao activityInfoDao) {
		this.activityInfoDao = activityInfoDao;
	}


	public TicketInfoDao getTicketInfoDao() {
		return ticketInfoDao;
	}


	public void setTicketInfoDao(TicketInfoDao ticketInfoDao) {
		this.ticketInfoDao = ticketInfoDao;
	}

}