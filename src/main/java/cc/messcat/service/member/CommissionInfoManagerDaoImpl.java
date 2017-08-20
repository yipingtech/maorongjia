/*
 * CommissionInfoManagerDaoImpl.java
 * 
 * Create by MCGT
 * 
 * Create time 2015-04-22
 * 
 * Version 1.0
 * 
 * Copyright 2013 Messcat, Inc. All rights reserved.
 * 
 */
package cc.messcat.service.member;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cc.messcat.bases.BaseManagerDaoImpl;
import cc.messcat.entity.CashInfo;
import cc.messcat.entity.CommissionInfo;
import cc.messcat.entity.Member;
import cc.messcat.wechat.weixin.popular.api.MessageAPI;
import cc.messcat.wechat.weixin.popular.bean.templatemessage.TemplateMessage;
import cc.messcat.wechat.weixin.popular.bean.templatemessage.TemplateMessageItem;
import cc.messcat.wechat.weixin.popular.bean.templatemessage.TemplateMessageResult;
import cc.messcat.wechat.weixin.popular.support.TokenManager;
import cc.modules.commons.Pager;
import cc.modules.constants.Constants;
import cc.modules.util.DateHelper;
import cc.modules.util.ObjValid;

public class CommissionInfoManagerDaoImpl extends BaseManagerDaoImpl implements CommissionInfoManagerDao {

	private static final long serialVersionUID = 1L;
	MemberManagerDao memberManagerDao;
	private static Logger log = LoggerFactory.getLogger(CommissionInfoManagerDaoImpl.class); 
	//添加佣金记录(添加或者提现)
	public Double addCommissionInfo(CommissionInfo commissionInfo) {		
		commissionInfo.setAddTime(new Date());
		//commissionInfo.setCommissionStatus("0");    //佣金状态（0：添加  1：消费提现）
		commissionInfo.setStatus("1");	
		commissionInfoDao.save(commissionInfo);
		//判断是提现还是添加然后对总的佣金金额进行修改
		return commissionInfo.getMemberId().getCommission()-commissionInfo.getCommission();		
	}
	
	public void modifyCommissionInfo(CommissionInfo commissionInfo) {
		this.commissionInfoDao.update(commissionInfo);
	}
	
	public void removeCommissionInfo(CommissionInfo commissionInfo) {
		this.commissionInfoDao.delete(commissionInfo);
	}

	//根据id来进行删除（修改记录的状态）
	public void removeCommissionInfo(Long id) {
		/*CashInfo cashInfo = new CashInfo();
		cashInfo = this.memberManagerDao.findByCashInfoId(id);
		cashInfo.setStatus("0");*/
		CommissionInfo commissionInfo = commissionInfoDao.get(id);
		commissionInfo.setStatus("0");
		this.commissionInfoDao.update(commissionInfo);
	}
	
	//查询根据id(查询单个佣金的详细信息)
	public CommissionInfo retrieveCommissionInfo(Long id) {
		return (CommissionInfo) this.commissionInfoDao.get(id);
	}
	
	//查询用户的佣金记录(用户的列表分页)
	@SuppressWarnings("unchecked")
	public Pager queryAllCommissionByProducter(int pageStart,int pageSize,Member member) {
		Map<String,Object> attrs = new HashMap<String, Object>();
		if (null!=member) {
			attrs.put("memberId", member);
		}
		attrs.put("status","1");
		attrs.put("commissionStatus","0");
		return new Pager(pageSize, pageStart, Integer.parseInt(String.valueOf(commissionInfoDao.queryTotalRecord(CommissionInfo.class, attrs))),
				commissionInfoDao.queryList(CommissionInfo.class, pageStart, pageSize, "id", "desc", attrs));
	}

	
	//用户佣金提现信息显示
	public String predictTiem(){
		Date endDate = new Date();
		Calendar cl = Calendar.getInstance();
		cl.setTime(endDate);
		cl.add(Calendar.DATE, +1);
		Date startDate = cl.getTime();
		SimpleDateFormat dd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String start = dd.format(startDate);
		//String end = dd.format(endDate);
		return start;	
	}
	
	//用户佣金提现确认(添加佣金记录)
	public String SureDrawCommission(CashInfo cashInfo,Member member){
		member=memberDao.get(member.getId());
		cashInfo.setWechatNum(member.getLoginName());
		cashInfo.setDrawTime(new Date());
		cashInfo.setMember(member);
		cashInfo.setDrawComments("佣金提现");
		cashInfo.setStatus("2");
		
		CommissionInfo commissionInfo = new CommissionInfo();
		commissionInfo.setCommission(cashInfo.getDrawAmount());
		commissionInfo.setMemberId(member);
		commissionInfo.setCommissionStatus("1");   //设为提现状态
		commissionInfo.setAddTime(new Date());
		commissionInfo.setStatus("1");	
		commissionInfo.setCommissionComments("");
		commissionInfoDao.save(cashInfo);
		commissionInfoDao.save(commissionInfo);
		//修改用户总的佣金金额
		member.setCommission(member.getCommission()-cashInfo.getDrawAmount());
		//修改用户的已提现金额
		if(!ObjValid.isValid(member.getSendCommission())||member.getSendCommission().equals(0.0)){
			member.setSendCommission(cashInfo.getDrawAmount());
		}else{
			member.setSendCommission(member.getSendCommission()+cashInfo.getDrawAmount());
		}
		memberDao.update(member);
		return "提款申请已提交";	
	}
	
	// 提现 信息记录明细(商家和用户都可以查)
	@SuppressWarnings({ "unchecked" })
	public Pager queryCashInfosByUser(Member member,int pageStart,int pageSize){
		/*Map<String,Object> attrs = new HashMap<String, Object>();
		if (ObjValid.isValid(member)) {
			attrs.put("member", member);
		}
		attrs.put("status", "2");
		return new Pager(pageSize, pageStart, Integer.parseInt(String.valueOf(commissionInfoDao.queryTotalRecord(CashInfo.class, attrs))),commissionInfoDao.queryList(CashInfo.class,pageStart ,
				pageSize, "id", "desc", attrs));	*/
		return commissionInfoDao.queryCashInfosByUser(member, pageStart, pageSize);
	}
	/**
	 *  查询所有符合发放佣金条件的用户
	 * */
	public Pager queryAllUserByCondition(int pageNo,int pageSize,Double conditionLine){
		return commissionInfoDao.queryAllUserByCondition(pageSize, pageNo, conditionLine);
	}

	
	//商家处理用户提现申请
	public String updateCashApplyBySeller(Long id){
		/*Map<String,Object> attrs = new HashMap<String, Object>();
		Map<String,Object> props = new HashMap<String, Object>();
		props.put("dealTime",new Date());
		attrs.put("id", id);
		props.put("drawComments", "提现成功");
		attrs.put("status", "1");
		commissionInfoDao.update(CashInfo.class, props, attrs);*/
		
		//Stephen
		CashInfo cashInfo = this.memberManagerDao.findByCashInfoId(id);
		cashInfo.setDealTime(new Date());
		cashInfo.setDrawComments("提现成功");
		cashInfo.setStatus("1");
		commissionInfoDao.update(cashInfo);
//		weixinWithdrawMessage(id);
		return "处理成功";		
	}
	
	/**
	 * 
	 * @param id
	 * 		提现表id
	 * 
	 * 微信提款消息
	 */
	public void weixinWithdrawMessage(Long id){
		CashInfo  c = this.memberManagerDao.findByCashInfoId(id);
		Member member = c.getMember();
		
		
		LinkedHashMap<String, TemplateMessageItem> templateMessageItem = new LinkedHashMap<String, TemplateMessageItem>();
		TemplateMessageItem templateMessageItem1 = null;
		if(ObjValid.isValid(c.getStatus()) && "0".equals(c.getStatus())){ //0为审核失败
			templateMessageItem1 = new TemplateMessageItem("尊敬的客户您好，您的提款申请编号为"+c.getId()+"审核失败", "#000000");
		}else{
			templateMessageItem1 = new TemplateMessageItem("尊敬的客户您好，您的提款申请编号为"+c.getId()+"提现成功", "#000000");
		}
		TemplateMessageItem templateMessageItem2 = new TemplateMessageItem(member.getNickname(), "#000000");
		TemplateMessageItem templateMessageItem3 = new TemplateMessageItem(DateHelper.dataToString(c.getDealTime(), "yyyy-MM-dd HH:mm:ss"), "#000000");
		TemplateMessageItem templateMessageItem4 = new TemplateMessageItem("感谢您的支持~", "#000000");
		templateMessageItem.put("first", templateMessageItem1);
		templateMessageItem.put("keyword1", templateMessageItem2);
		templateMessageItem.put("keyword2", templateMessageItem3);
		templateMessageItem.put("remark", templateMessageItem4);
		
		
		TemplateMessage templateMessage = new TemplateMessage();
//		templateMessage.setTemplate_id(Constants.TEMPLATE_MESSAGE_SUBMIT);
		templateMessage.setTopcolor("#000000");
		templateMessage.setTouser(member.getLoginName());
		//templateMessage.setTouser("orxiEtyV5w-K2G7SIYu9cejrCMbU");
		log.error("member.getLoginName()：" + member.getLoginName());
		templateMessage.setUrl("");
		templateMessage.setData(templateMessageItem);
		TemplateMessageResult templateMessageResult =  MessageAPI.messageTemplateSend(TokenManager.getToken(Constants.APPID), templateMessage);
//		log.error("weixinMessage:"+templateMessageResult.getErrcode()+" --" + templateMessageResult.getErrmsg());
	}
	
	/**
	 * 查询历史累计提现
	 */
	public Double queryTotalCashInfo(Member member){
		return commissionInfoDao.queryTotalCashInfo(member);
	}
	
	
	public Pager retrieveCommissionInfosPager(int pageSize, int pageNo) {
		return this.commissionInfoDao.getPager(pageSize, pageNo);
	}
	public Pager retrieveCommissionInfos(int pageSize, int pageNo) {
		return this.commissionInfoDao.retrieveCommissionInfos(pageSize, pageNo);
	}

	public MemberManagerDao getMemberManagerDao() {
		return memberManagerDao;
	}

	public void setMemberManagerDao(MemberManagerDao memberManagerDao) {
		this.memberManagerDao = memberManagerDao;
	}

	@Override
	public void failToCash(Long id) {
		CashInfo cashInfo = this.memberManagerDao.findByCashInfoId(id);
		cashInfo.setDrawComments("0");
		cashInfo.setDealTime(new Date());
		cashInfo.setStatus("1");
		this.commissionInfoDao.update(cashInfo);
		
		//当提现失败时，将金额换还到 用户的红包里面，并且相应的减去 已提现金额...
		Member member = memberManagerDao.retrieveMember(cashInfo.getMember().getId());
		member.setSendCommission(member.getSendCommission()-cashInfo.getDrawAmount());
		member.setCommission(member.getCommission()+cashInfo.getDrawAmount());
		this.memberDao.update(member);
	}

	@Override
	public Double findMemberBonus(Member member) {
		return this.commissionInfoDao.findMemberBonus(member);
	}

	@Override
	public CashInfo findByCashInfoId(Long id) {
		return this.memberDao.findCashInfoId(id);
	}

	@Override
	public Double findAgentCommissionInfo(Member member) {
		return this.commissionInfoDao.findAgentCommissionInfo(member);
	}

	@Override
	public Pager queryAllCommissionByMember(int pageStart, int pageSize, Member member) {
		return this.commissionInfoDao.queryAllCommissionByMember(pageStart,pageSize,member);
	}



	


}