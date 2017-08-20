package cc.messcat.service.activity;

import java.util.List;

import cc.messcat.entity.ActivityApply;
import cc.messcat.entity.ActivityInvite;
import cc.messcat.entity.EnterpriseColumn;
import cc.messcat.entity.McProductInfo;
import cc.messcat.entity.Member;
import cc.messcat.entity.OrderInfo;
import cc.messcat.entity.PayOrder;
import cc.messcat.entity.ProductColumn;
import cc.modules.commons.Pager;

public interface ActivityInfoManagerDao {
	
	/**
	 * 查找免费试穿栏目中的商品(一个商品直接进去商品界面)
	 * @return
	 */
	public Pager queryActivityColum(int pageStart,int pageSize,String frontNum);
	
	/**
	 * 点击参与试穿
	 */
	public void applyFreeProduct(Member member,ProductColumn productColumn);
	
	/**
	 * 邀请试穿好友
	 * @param member
	 * @param invitMember
	 */
	public void inviteFriend(ActivityInvite activityInvite,ProductColumn productColumn);
	
	/**
	 * 查询所邀请的好友
	 * @param member
	 * @return
	 */
	public List<ActivityInvite> queryInviteFriend(Member member,Member inviteFriend,ProductColumn productColumn);
	
	/**
	 * 查询申请试穿会员
	 * @param enterpriseColumn
	 * @param mcProductInfo
	 * @return
	 */
	public List<ActivityApply> queryApplyMember(Member member,ProductColumn productColumn);

	/**
	 * 下单免费试穿商品、精美礼品
	 * @param member
	 * @param payOrder
	 * @param orderInfo
	 */
	public String addFreeProductOrder(Member member,PayOrder payOrder,String proAttrIds,Long freeProductId, String flag,Long id);
	
	/**
	 * 根据栏目FRONT_NUM查出活动的栏目
	 * @return
	 */
	public EnterpriseColumn queryActivityColumn(String frontNum);
	
	/**
	 * 根据活动栏目查出所有活动商品
	 * @return
	 */
	public Pager queryActivityProduct(int pageStart,int pageSize,EnterpriseColumn enterpriseColumn);
}
