/*
 * MemberAction.java
 * 
 * Create by MCGT
 * 
 * Create time 2015-04-21
 * 
 * Version 1.0
 * 
 * Copyright 2013 Messcat, Inc. All rights reserved.
 * 
 */
package cc.messcat.web.member;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionContext;

import cc.messcat.entity.Address;
import cc.messcat.entity.BonusRecord;
import cc.messcat.entity.Member;
import cc.messcat.entity.Province;
import cc.messcat.vo.MemberVo;
import cc.modules.commons.PageAction;
import cc.modules.util.ObjValid;

public class MemberAction extends PageAction {
	
	private static final long serialVersionUID = 1L;
	private static Logger log = LoggerFactory.getLogger(MemberAction.class); 
	
	private Long id;	
	private int flag;
	private Member member;	
	
	private List<Member> members;
	private List<Province> provinceList;
	private Address address;
	private Long provinceId;
	private Long addressId;
    private Long userId;
	private Long cityId;
	private Long areaId;
	private Boolean flag2;
	private MemberVo mv;
	private List<MemberVo> mvList;
	private String begin;
	private String end;
    private Long fatherId;
    private Long memberId;
    private String rank;

	

    /**
     * 新会员加入通知消息的开关
     * */
    public String message(){
    	try {
    		if(flag==0){
    			this.flag=1;
    		}else if(flag==1){
    			this.flag=0;
    		}
    		//HttpSession session = getSession();
    		Map session = (Map) ActionContext.getContext().getSession();
    		Member member = (Member)session.get("member");
    		member = this.memberManagerDao.retrieveMember(member.getId());
    		member.setMessageFalg(String.valueOf(flag));
    		this.memberManagerDao.modifyMember(member);
    		member = this.memberManagerDao.retrieveMember(member.getId());
    		session.put("member", member);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return "message";
    }

	//查询所有的用户(现在不用)
	@SuppressWarnings("unchecked")
	public String retrieveAllMembers() throws Exception {
		try {
			super.pager = this.memberManagerDao.retrieveMembersPager(pageSize, pageNo);
			this.members = super.pager.getResultList();
		} catch (Exception ex) {
			addActionMessage("Load page error!");
		}
		return "success";
	}

	//跳转的添加的页面
	public String newPage() throws Exception {
		return "newPage";
	}
	
	//通过id查询用户
	public String retrieveMemberById() throws Exception {
		try {
			this.member = this.memberManagerDao.retrieveMember(id);
			//实时计算会员的现有佣金
			Double bonus = 0.0;
			Double allBonus = commissionInfoManagerDao.findMemberBonus(member);
			if(ObjValid.isValid(member.getSendCommission())){
				bonus = allBonus - member.getSendCommission();
			}else{
				bonus = allBonus;
			}
			member.setCommission(bonus);
		} catch (Exception ex) {
			addActionMessage("Load page error!");
		}
		return "editPage";
	}
	/**
	 * 发出所有代理商的提成
	 * */
	public String sendAllAgentMoney(){
		int pageSize =-1;
		int pageNo =-1;
		pager=memberManagerDao.checkAllAgent(pageSize,pageNo);
		members=pager.getResultList();
		mvList =new ArrayList<MemberVo>();
		for(int i=0;i<members.size();i++){
			MemberVo mv = new MemberVo();
			Date beginDate = members.get(i).getAgentTime();
			Date endDate = new Date();
			Double money = this.payOrderManagerDao.findAgentBonus(members.get(i));
			money = Double.parseDouble(String.format("%.2f",money));
			mv.setMember(members.get(i));
			mv.setMoney(money);
			mvList.add(mv);
		}
		for(int i=0;i<mvList.size();i++){
			Member m = mvList.get(i).getMember();
			Double d = mvList.get(i).getMoney();
			BonusRecord b = new BonusRecord();
			b.setMember(m);
			b.setMoney(d);
			b.setSendTime(new Date());
			b.setStatus("1");
			this.bonusRecordManagerDao.addBonusRecord(b);
			m.setBalance(m.getBalance()+d);
			this.memberManagerDao.modifyMember(m);
		}
		
		return this.queryAllAgentMoney();
	}
	/**
	 * 查询出所有代理商的提成
	 * */
	public String queryAllAgentMoney(){
		pager=memberManagerDao.checkAllAgent(pageSize,pageNo);
		members=pager.getResultList();
		mvList =new ArrayList<MemberVo>();
		for(int i=0;i<members.size();i++){
			MemberVo mv = new MemberVo();
			Double money = this.payOrderManagerDao.findAgentBonus(members.get(i));
			money = Double.parseDouble(String.format("%.2f",money));
			mv.setMember(members.get(i));
			mv.setMoney(money);
			mvList.add(mv);
		}
		return "AllBonus";
	}
	/**
	 * 查询所有代理商的业绩
	 * @throws ParseException 
	 * */
	public String queryAllAgentRecord() throws ParseException{
		pager=memberManagerDao.checkAllAgent(pageSize,pageNo);
		members=pager.getResultList();
		mvList =new ArrayList<MemberVo>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if(!ObjValid.isValid(begin)&&!ObjValid.isValid(end)){
			for(int i=0;i<members.size();i++){
				MemberVo mv = new MemberVo();
				Date beginDate = members.get(i).getAgentTime();
				Date endDate = new Date();
				Double money = this.payOrderManagerDao.findAgentWork(members.get(i), beginDate,endDate,-1,-1);
				mv.setMember(members.get(i));
				mv.setMoney(money);
				mvList.add(mv);
			}
		}else{
			for(int i=0;i<members.size();i++){
				MemberVo mv = new MemberVo();
				Date beginDate = sdf.parse(begin);
				Date endDate = sdf.parse(end);
				Double money = this.payOrderManagerDao.findAgentWork(members.get(i), beginDate,endDate,-1,-1);
				mv.setMember(members.get(i));
				mv.setMoney(money);
				mvList.add(mv);
			}
		}
	/*	pager=memberManagerDao.checkAllAgent(pageSize,pageNo);
		members=pager.getResultList();
		mvList =new ArrayList<MemberVo>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if(!ObjValid.isValid(begin)&&!ObjValid.isValid(end)){
			for(int i=0;i<members.size();i++){
				MemberVo mv = new MemberVo();
				Date beginDate = members.get(i).getAgentTime();
				Date endDate = new Date();
				Double money = this.payOrderManagerDao.findAgentWork(members.get(i), beginDate,endDate,-1,-1);
				mv.setMember(members.get(i));
				mv.setMoney(money);
				mvList.add(mv);
			}
		}else{
			for(int i=0;i<members.size();i++){
				MemberVo mv = new MemberVo();
				Date beginDate = sdf.parse(begin);
				Date endDate = sdf.parse(end);
				Double money = this.payOrderManagerDao.findAgentWork(members.get(i), beginDate,endDate,pageSize,pageNo);
				mv.setMember(members.get(i));
				mv.setMoney(money);
				mvList.add(mv);
			}
		}*/
		
		return "record";
	}
	/**
	 *查询是否存在此代理商  -----更改为查询是否为一级分销商，一级分销商才有资格成为代理商
	 * */
	public String checkAgent(){
		 Member member = new Member();
		 member = this.memberManagerDao.retrieveMember(this.memberId);
		 if(ObjValid.isValid(member.getFirstFather())){
			 flag2 = false;
		 }else{
			 if(ObjValid.isValid(member.getAgent())){
				 flag2 = false;
			 }
			 flag2 = true;
		 }
		
		/*if(provinceId!=null){     原区域代理商---ajax查询此区域是否存在代理商
			Province province = this.provinceManagerDao.retrieveProvince(provinceId);
			StringBuffer address = new StringBuffer();
			address.append(String.valueOf(province.getId()));
			flag2 = this.memberManagerDao.findAgent(address.toString());
			return "ajaxSuccess";
		}
		City city = this.cityManagerDao.retrieveCity(this.cityId);
		provinceId = city.getFatherId();
		Province province = this.provinceManagerDao.getByProvinceId(provinceId);
		StringBuffer address = new StringBuffer();
		address.append(String.valueOf(province.getId())+",");
		address.append(String.valueOf(cityId));
		flag2 = this.memberManagerDao.findAgent(address.toString());*/
		return "ajaxSuccess";
	}
	/**
	 * 查找出所有代理商
	 * */
	public String queryAllAgent(){
		pager=memberManagerDao.checkAllAgent(pageSize,pageNo);
		members=pager.getResultList();
		return "success";
	}
	/**
	 * 设置成为代理商
	 * @throws Exception 
	 * */
	public String agentSet() throws Exception{
		member = new Member();
	    member = this.memberManagerDao.retrieveMember(this.id);
	    member.setAgentTime(new Date());
	    member.setAgent("1");
	    this.memberManagerDao.modifyMember(member);
	    BonusRecord br = new BonusRecord();
    	br.setMember(member);
    	br.setMoney(0.0);
    	br.setSendTime(new Date());
    	br.setStatus("1");
    	this.bonusRecordManagerDao.addBonusRecord(br);
		/*member = new Member();
	    member = this.memberManagerDao.retrieveMember(this.id);
	    if(cityId!=null){
	    	member.setAgent(this.provinceId+","+this.cityId);
	    	City city = this.cityManagerDao.retrieveCity(cityId);
	    	member.setAgentArea(city.getCity());
	    }else{
	    	member.setAgent(String.valueOf(this.provinceId));
	    	Province p = this.provinceManagerDao.retrieveProvince(provinceId);
	    	member.setAgentArea(p.getProvince());
	    }
		member.setAgentTime(new Date());
		this.memberManagerDao.modifyMember(member);
		BonusRecord br = new BonusRecord();
    	br.setMember(member);
    	br.setMoney(0.0);
    	br.setSendTime(new Date());
    	br.setStatus("1");
    	this.bonusRecordManagerDao.addBonusRecord(br);*/
		return "edit_success";
	}
	/**
	 * 取消该代理商
	 * @throws Exception 
	 * */
	public String agentDelete() throws Exception{
		member = new Member();
	    member = this.memberManagerDao.retrieveMember(this.id);
	    member.setAgent(null);
	    member.setAddTime(null);
	    member.setAgentArea(null);
	    this.memberManagerDao.modifyMember(member);
		return "edit_success";
	}
	
	
	//查询所有用户（过滤掉删除态）
	@SuppressWarnings("unchecked")
	public String queryAllMember() throws Exception {
		try {
			this.provinceList = this.provinceManagerDao.retrieveAllProvinces();
            if(ObjValid.isValid(member)){
	              log.info(member.getRank());
			}
			pager=memberManagerDao.queryLikeMember(pageNo,pageSize,this.member);
			this.members=pager.getResultList();
			//实时计算会员的现有佣金
			for(int i =0;i<members.size();i++){
				Double bonus = 0.0;
				Double allBonus = commissionInfoManagerDao.findMemberBonus(members.get(i));
				if(ObjValid.isValid(members.get(i).getSendCommission())){
					bonus = allBonus - members.get(i).getSendCommission();
				}else{
					bonus = allBonus;
				}
				members.get(i).setCommission(bonus);
			}
			if (ObjValid.isValid(addressId)) {
				this.address = this.addressManagerDao.retrieveAddress(addressId);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			addActionMessage("Load page error!");
		}
		return "success";
	}
	
	//查询三级分销客户（过滤掉删除态）
	@SuppressWarnings("unchecked")
	public String queryThreeMember() throws Exception {
		try {
			pager=memberManagerDao.queryMemberThreeCosTomerPage(member, pageNo, pageSize, flag);
			members=pager.getResultList();
			this.flag = flag;
		} catch (Exception ex) {
			addActionMessage("Load page error!");
		}
		return "threeMember";
	}

	
	//显示用户信息详情
	public String viewPage() throws Exception {
		try {
			this.member = this.memberManagerDao.retrieveMember(id);
			//实时计算会员的现有佣金
			Double bonus = 0.0;
			Double allBonus = commissionInfoManagerDao.findMemberBonus(member);
			if(ObjValid.isValid(member.getSendCommission())){
				bonus = allBonus - member.getSendCommission();
			}else{
				bonus = allBonus;
			}
			member.setCommission(bonus);
		} catch (Exception ex) {
			addActionMessage("Load page error!");
		}
		return "viewPage";
	}
	
	//添加注册用户
	public String newMember() throws Exception {
		try {
			//关注服务号时获取该用户的微信号和其他信息,如果是通过分享的链接注册还要获取父级的账号id
			
			this.memberManagerDao.addMember(this.member);
			addActionMessage("New successfully!");
		} catch (Exception ex) {
			this.addActionError(ex.getMessage());
			addActionMessage("New fail!");
		}
		return "edit_success";
	}
	
	public Long getFatherId() {
		return fatherId;
	}

	public void setFatherId(Long fatherId) {
		this.fatherId = fatherId;
	}

	//用户修改自己的资料
	public String editMemberByUser() throws Exception {
		try {
			//关注服务号时获取该用户的微信号和其他信息
			this.memberManagerDao.addMember(this.member);
			addActionMessage("New successfully!");
		} catch (Exception ex) {
			this.addActionError(ex.getMessage());
			addActionMessage("New fail!");
		}
		return "edit_success";
	}
	
	//商家修改用户资料
	public String editMember() throws Exception {
		try {
			member.setId(id);
			this.memberManagerDao.modifyMember(member);
			addActionMessage("Update successfully!");
		} catch (Exception ex) {
			this.addActionError(ex.getMessage());
			addActionMessage("Update fail!");
		}
		return "edit_success";
	}
	
	//注销用户
	public String delMember() throws Exception {
		try {
			this.memberManagerDao.removeMember(this.id);
			addActionMessage("Delete successfully!");
		} catch (Exception ex) {
			this.addActionError(ex.getMessage());
			addActionMessage("Delete fail!");
		}
		return "edit_success";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public List<Member> getMembers() {
		return members;
	}

	public void setMembers(
			List<Member> members) {
		this.members = members;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public List<Province> getProvinceList() {
		return provinceList;
	}

	public void setProvinceList(List<Province> provinceList) {
		this.provinceList = provinceList;
	}
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Long getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Long provinceId) {
		this.provinceId = provinceId;
	}

	public Long getCityId() {
		return cityId;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}

	public Long getAreaId() {
		return areaId;
	}

	public void setAreaId(Long areaId) {
		this.areaId = areaId;
	}

	public Long getAddressId() {
		return addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Boolean getFlag2() {
		return flag2;
	}

	public void setFlag2(Boolean flag2) {
		this.flag2 = flag2;
	}

	public MemberVo getMv() {
		return mv;
	}

	public void setMv(MemberVo mv) {
		this.mv = mv;
	}

	public List<MemberVo> getMvList() {
		return mvList;
	}

	public void setMvList(List<MemberVo> mvList) {
		this.mvList = mvList;
	}


	public String getBegin() {
		return begin;
	}

	public void setBegin(String begin) {
		this.begin = begin;
	}
	public void setEnd(String end) {
		this.end = end;
	}

	public String getEnd() {
		return end;
	}
	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

}