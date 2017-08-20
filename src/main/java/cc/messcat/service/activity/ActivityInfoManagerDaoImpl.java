package cc.messcat.service.activity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cc.messcat.bases.BaseManagerDaoImpl;
import cc.messcat.entity.ActivityApply;
import cc.messcat.entity.ActivityInvite;
import cc.messcat.entity.CartInfo;
import cc.messcat.entity.EnterpriseColumn;
import cc.messcat.entity.McProductInfo;
import cc.messcat.entity.Member;
import cc.messcat.entity.OrderInfo;
import cc.messcat.entity.PayOrder;
import cc.messcat.entity.ProductColumn;
import cc.modules.commons.Pager;
import cc.modules.util.ObjValid;

public class ActivityInfoManagerDaoImpl extends BaseManagerDaoImpl implements ActivityInfoManagerDao{
	

	/**
	 * 查询试穿商品
	 */
	@Override
	public Pager queryActivityColum(int pageStart,int pageSize,String frontNum) {
		return this.queryActivityProduct(pageStart,pageSize,this.queryActivityColumn(frontNum));
	}
	
	/**
	 * 点击参与试穿
	 */
	@Override
	public void applyFreeProduct(Member member,ProductColumn productColumn) {
		ActivityApply activityApply = new ActivityApply();
		activityApply.setMemberId(member);
		activityApply.setProductId(productColumn.getMcProduct());
		activityApply.setColumnId(productColumn.getEnterpriseColumn());
		activityApply.setAddTime(new Date());
		activityApply.setReceiveStatus("0");
		activityApply.setStatus("1");
		activityInfoDao.save(activityApply);
	}

	/**
	 * 邀请好友试穿
	 */
	@Override
	public void inviteFriend(ActivityInvite activityInvite,ProductColumn productColumn) {
		//判断好友是否已经邀请
		if (queryInviteFriend(activityInvite.getMemberId(), activityInvite.getInviteFriend(), productColumn).size()>0) {
			activityInvite.setApplyId(this.queryApplyMember(activityInvite.getMemberId(), productColumn).get(0));
			activityInvite.setAddTime(new Date());
			activityInvite.setStatus("1");
			activityInfoDao.save(activityInvite);	
		}
	}
	
	/**
	 * 查询用户所有邀请的好友
	 * @param member
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<ActivityInvite> queryInviteFriend(Member member,Member inviteFriend,ProductColumn productColumn){
		Map<String, Object> attrs1 = new HashMap<String, Object>();
		if (null!=inviteFriend&&!("".equals(inviteFriend))) {
			//判断好友是否存在
			attrs1.put("inviteFriend", inviteFriend);
		}
		attrs1.put("status", "1");
		attrs1.put("memberId", member);
		attrs1.put("applyId", queryApplyMember(member,productColumn).get(0));   //关联申请表
		return activityInfoDao.queryList(ActivityInvite.class, "id", "desc", attrs1);
	}
	
	/**
	 * 查询申请试穿该商品的会员数量
	 * @param enterpriseColumn
	 * @param mcProductInfo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<ActivityApply> queryApplyMember(Member member,ProductColumn productColumn){
		Map<String, Object> attrs = new HashMap<String, Object>();
		if (null!=member&&!"".equals(member)) {
			attrs.put("memberId", member);
		}
		attrs.put("status", "1");
		attrs.put("columnId", productColumn.getEnterpriseColumn());
		attrs.put("productId", productColumn.getMcProduct());
		return activityInfoDao.queryList(ActivityApply.class, "id", "desc", attrs);
	}

	/**
	 * 试穿商品,精美礼品下单
	 */
	@Override
	public String addFreeProductOrder(Member member, PayOrder payOrder,String proAttrIds,Long freeProductId, String flag,Long id) {
		OrderInfo orderInfo = new OrderInfo();
		McProductInfo productInfo = mcProductInfoDao.get(freeProductId);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String payOrderNum = sdf.format(new Date())+String.format("%1$04d",1);
		payOrder.setOrderNum(payOrderNum);
		payOrder.setMember(member);
		payOrder.setCreateTime(new Date());
		payOrder.setPayTime(new Date());
		//payOrder.setBestTime(new Date());      //送货最佳时间
		payOrder.setShippingStatus("3");         //备货状态
		payOrder.setPayStatus("1");
		if (null!=productInfo.getPromotePrice()||!"".equals(productInfo.getPromotePrice())) {
			payOrder.setProductAmount(productInfo.getPromotePrice());
			orderInfo.setPrice(productInfo.getPromotePrice());
		} else {
			payOrder.setProductAmount(productInfo.getShopPrice());
			orderInfo.setPrice(productInfo.getShopPrice());
		}	
		payOrder.setOrderAmount(0.0);
		payOrder.setStatus("1");
		activityInfoDao.save(payOrder);
		
		orderInfo.setMember(member);
		orderInfo.setOrderinfoNum(payOrderNum);
		orderInfo.setProductAttrIds(proAttrIds);  //加入商品属性集合
		orderInfo.setProduct(productInfo);
		orderInfo.setCreateTime(new Date());
		orderInfo.setOrderTime(new Date());
		orderInfo.setAmount(1L);
		orderInfo.setTotalPrice(0.0);
		orderInfo.setCreateTime(payOrder.getCreateTime());
		orderInfo.setOrderTime(payOrder.getCreateTime());
		orderInfo.setPayTime(payOrder.getCreateTime());
		orderInfo.setPayOrder(payOrder);
		orderInfo.setEvaluateStatus("0");
		orderInfo.setOrderStatus("1");       
		orderInfo.setStatus("1"); 
		if (flag.equals("1")) {
			orderInfo.setComments("免费试穿商品");
			orderInfo.setProductStatus("5");     //免费试穿商品
		}else if (flag.equals("2")) {
			orderInfo.setComments("精美礼品");
			orderInfo.setProductStatus("6");     //免费试穿商品
		}
		activityInfoDao.save(orderInfo);
		
		//修改试穿商品领取属性
		Map<String, Object> attrs = new HashMap<String, Object>();
		Map<String, Object> props = new HashMap<String, Object>();
		attrs.put("id", id);
		props.put("receiveStatus", "1");
		activityInfoDao.update(ActivityApply.class, props, attrs);
		return "下单成功";
	}
	
	/**
	 * 根据父类栏目查出活动的所有栏目
	 * @return
	 */
	public List<EnterpriseColumn> queryActivityColumn(Long fatherId, Long pageTypeId){
		return null;	
	}
	
	/**
	 * 根据栏目FRONT_NUM查出活动的栏目
	 * @return
	 */
	public EnterpriseColumn queryActivityColumn(String frontNum){
		Map<String,Object> attrs = new HashMap<String, Object>();
		attrs.put("frontNum", frontNum);
		attrs.put("state", "1");
		return activityInfoDao.query(EnterpriseColumn.class, attrs);
	}

	/**
	 * 根据活动栏目查出所有活动商品
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Pager queryActivityProduct(int pageStart,int pageSize,EnterpriseColumn enterpriseColumn){
		Map<String,Object> attrs = new HashMap<String, Object>();
		attrs.put("enterpriseColumn", enterpriseColumn);
		return new Pager(pageSize,pageStart,Integer.parseInt(String.valueOf(activityInfoDao.queryTotalRecord(ProductColumn.class, attrs))),
				activityInfoDao.queryList(ProductColumn.class,(pageStart-1)*pageSize,pageSize, "id", "desc", attrs));
	}

	
}
