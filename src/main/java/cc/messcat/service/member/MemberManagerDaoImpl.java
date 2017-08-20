package cc.messcat.service.member;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cc.messcat.bases.BaseManagerDaoImpl;
import cc.messcat.entity.AgentOrder;
import cc.messcat.entity.CashInfo;
import cc.messcat.entity.CommissionInfo;
import cc.messcat.entity.IntegralRule;
import cc.messcat.entity.IntergralInfo;
import cc.messcat.entity.Member;
import cc.messcat.entity.ParameterSet;
import cc.messcat.entity.RechargeInfo;
import cc.modules.commons.Pager;
import cc.modules.util.DateHelper;
import cc.modules.util.ObjValid;

public class MemberManagerDaoImpl extends BaseManagerDaoImpl implements MemberManagerDao {
	private static Logger log = LoggerFactory.getLogger(MemberManagerDaoImpl.class); 

	private static final long serialVersionUID = 1L;

	/**
	 * 注册用户
	 */
	public void addMember(Member member) {
		IntegralRule integralRule = this.integralRuleDao.getIntegralRuleByName("注册");
		member.setStatus("1");
		member.setAddTime(new Date());
		member.setBalance(0.0); // 刚注册设置余额为零
		member.setReport(0L); // 签到次数
		member.setCommission(0.0); // 佣金金额
		member.setCommissionLine(0.0); // 会员待发放佣金
		member.setRank("1"); // 普通会员
		if (ObjValid.isValid(integralRule)) {
			member.setIntergal(Double.parseDouble(integralRule.getIntegral().toString())); // 首次关注注册送积分（需要商家自己决定）
			IntergralInfo intergralInfo = new IntergralInfo();
			intergralInfo.setAddTime(new Date());
			intergralInfo.setComments("注册");
			intergralInfo.setIntergral(integralRule.getIntegral());
			intergralInfo.setIntergralRule(integralRule);
			intergralInfo.setMemberId(member);
			intergralInfo.setStatus("1");
			this.intergralInfoDao.save(intergralInfo);
		}
		this.memberDao.save(member);
	}

	/**
	 * 用户获取积分方法
	 * 
	 * @param ruleName,member
	 * @return
	 * @throws ParseException
	 */
	public String memberEarnIntegral(String ruleName, Member member) throws ParseException {
		IntegralRule integralRule = this.integralRuleDao.getIntegralRuleByName(ruleName);
		int count = 0;
		if (ObjValid.isValid(integralRule)) {
			// 判断该规则是否每个会员终生只能获取一次
			if (integralRule.getPeriod() != 0) {
				Calendar rightNow = Calendar.getInstance();
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
				rightNow.setTime(new Date());
				df.format(rightNow.getTime());
				String startDate = df.format(rightNow.getTime());
				rightNow.add(Calendar.DAY_OF_YEAR, new Long(integralRule.getPeriod()).intValue());
				String endDate = df.format(rightNow.getTime());
				count = this.intergralInfoDao.countEarnTimes(member, startDate, endDate);
				// 该用户当天获取积分的次数是否已达到上限
				if (count < integralRule.getMaxtmie()) {
					IntergralInfo intergralInfo = new IntergralInfo();
					intergralInfo.setAddTime(new Date());
					intergralInfo.setComments(ruleName);
					intergralInfo.setIntergral(integralRule.getIntegral());
					intergralInfo.setIntergralRule(integralRule);
					intergralInfo.setMemberId(member);
					intergralInfo.setStatus("1");
					this.intergralInfoDao.save(intergralInfo);
					double memberIntergral = member.getIntergal() + Double.parseDouble(integralRule.getIntegral().toString());
					member.setIntergal(memberIntergral);
					if (ruleName.equals("签到")) {
						member.setReport(member.getReport() + 1);
					}
					this.memberDao.update(member);
					return "0";
				}
			}
		}
		return "1";
	}

	/**
	 * 查询用户根据id
	 */
	public Member retrieveMember(Long id) {
		Map<String, Object> attrs = new HashMap<String, Object>();
		attrs.put("id", id);
		attrs.put("status", "1");
		return memberDao.query(Member.class, attrs);
		// return (Member) this.memberDao.get(id);
	}

	/**
	 * 商家修改用户根据用户对象
	 */
	public void modifyMember(Member member) {
		Member member0 = retrieveMember(member.getId());
		member0.setCardNum(member.getCardNum());
		member0.setNickname(member.getNickname());
		member0.setSex(member.getSex());
		member0.setRealname(member.getRealname());
		member0.setBirthday(member.getBirthday());
		member0.setCareer(member.getCareer());
		member0.setQq(member.getQq());
		member0.setEmail(member.getEmail());
		member0.setMobile(member.getMobile());
		member0.setTelephone(member.getTelephone());
		member0.setAddress(member.getAddress());
		member0.setPostcode(member.getPostcode());
		member0.setEditTime(new Date());
		member0.setIntergal(member.getIntergal());
		member0.setBalance(member.getBalance());
		member0.setReport(member.getReport());
		member0.setCommission(member.getCommission());
		member0.setRank(member.getRank());
		member0.setDistributor(member.getDistributor());
		this.memberDao.update(member0);
	}

	public void updateMember(Member member) {
		this.memberDao.update(member);
	}

	/**
	 * 用户修改自己的资料
	 */
	public void editMemberByUser(Member member, String year, String month, String day) {
		Map<String, Object> attrs = new HashMap<String, Object>();
		Map<String, Object> props = new HashMap<String, Object>();
		if (ObjValid.isValid(month) && (month.equals("1") || month.equals("2") || month.equals("3") || month.equals("4")
			|| month.equals("5") || month.equals("6") || month.equals("7") || month.equals("8") || month.equals("9"))) {
			month = "0" + month;
		}
		if (ObjValid.isValid(day) && (day.equals("1") || day.equals("2") || day.equals("3") || day.equals("4") || day.equals("5")
			|| day.equals("6") || day.equals("7") || day.equals("8") || day.equals("9"))) {
			day = "0" + day;
		}
		String birthday = null;
		if (ObjValid.isValid(year, month, day)) {
			birthday = year + "-" + month + "-" + day;
		}
		member.setBirthday(birthday);
		// props.put("nickname", member.getNickname());
		props.put("sex", member.getSex());
		props.put("realname", member.getRealname());
		props.put("birthday", member.getBirthday());
		// props.put("career", member.getNickname());
		// props.put("qq", member.getNickname());
		// props.put("email", member.getNickname());
		props.put("mobile", member.getMobile());
		props.put("address", member.getAddress());
		props.put("editTime", new Date());
		// props.put("postcode", member.getNickname());
		// props.put("imagePath", member.getImagePath());

		attrs.put("loginName", member.getLoginName());
		attrs.put("status", "1");
		memberDao.update(Member.class, props, attrs);
	}

	/**
	 * 绑定手机号
	 */
	public void editMemberPhone(Member member, String oldMobile) {
		Map<String, Object> attrs = new HashMap<String, Object>();
		Map<String, Object> props = new HashMap<String, Object>();
		props.put("mobile", member.getMobile());
		props.put("realname", member.getRealname());
		attrs.put("status", "1");
		attrs.put("loginName", member.getLoginName());
		memberDao.update(Member.class, props, attrs);
		try {
			if (!ObjValid.isValid(oldMobile)) {
				this.memberEarnIntegral("绑定手机", member);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 删除用户根据id
	 */
	public void removeMember(Long id) {
		Map<String, Object> attrs = new HashMap<String, Object>();
		Map<String, Object> props = new HashMap<String, Object>();
		attrs.put("id", id);
		props.put("status", "0");
		memberDao.update(Member.class, props, attrs);
	}

	/**
	 * 查询用户根据微信号
	 */
	public Member retrieveMemberByLoginName(Member member) {
		Map<String, Object> attrs = new HashMap<String, Object>();
		attrs.put("loginName", member.getLoginName());
		// attrs.put("password", member.getPassword());
		attrs.put("status", "1");
		return memberDao.query(Member.class, attrs);
	}

	/**
	 * 联合查询用户的三级客户 -->二级
	 */
	public Pager queryUnionMemberFather(Member member, int pageSrtart, int pageSize) {
		return memberDao.queryUnionMemberFather(member, pageSrtart, pageSize);
	}

	/**
	 * 查询用户的第一级客户(点击一级查一级)
	 */
	@SuppressWarnings("unchecked")
	public Pager queryMemberFather(Member member, int count, int pageSrtart, int pageSize) {
		if (count < 3) {
			Map<String, Object> attrs = new HashMap<String, Object>();
			attrs.put("memberFather", member.getId());
			attrs.put("status", "1");
			return new Pager(pageSize, pageSrtart,
				Integer.parseInt(String.valueOf(memberDao.queryTotalRecord(Member.class, attrs))),
				memberDao.queryList(Member.class, (pageSrtart - 1) * pageSize, pageSize, "id", "desc", attrs));
		}
		return null;
	}

	/**
	 * 分开查询用户的三级客户(分页) -->二级
	 */
	public Pager queryMemberThreeCosTomerPage(Member member, int pageStart, int pageSize, int flag) {
		return memberDao.queryThreeMember(member, pageStart, pageSize, flag);
	}

	/**
	 * 查询用户的三级客户 --> 二级
	 */
	@SuppressWarnings("unchecked")
	public Map<String, List<Member>> queryMemberThreeCosTomer(Member member) {
		Map<String, List<Member>> memberMap = new HashMap<String, List<Member>>();
		Map<String, Object> attrs = new HashMap<String, Object>();
		attrs.put("memberFather", member.getId());
		attrs.put("status", "1");
		List<Member> members1 = memberDao.queryList(Member.class, "id", "desc", attrs); // 第一级客户
		int count = 1;
		if (members1.size() > 0) {
			memberMap.put("members1", members1);
			for (Member member1 : members1) {
				// 先根据第一级客户查询第二级客户
				Map<String, Object> attrs1 = new HashMap<String, Object>();
				attrs1.put("memberFather", member1.getId());
				attrs1.put("status", "1");
				List<Member> members2 = memberDao.queryList(Member.class, "id", "desc", attrs1);
				if (members2.size() > 0) {
					memberMap.put("members2" + (++count) + "", members2); // 第二级所有的客户
					/*for (Member member2 : memberDao.queryList(Member.class, "id", "desc", attrs1)) {
						// 根据第二级客户查询第三级
						Map<String, Object> attrs2 = new HashMap<String, Object>();
						attrs2.put("memberFather", member2.getId());
						attrs2.put("status", "1");
						if (memberDao.queryList(Member.class, "id", "desc", attrs2).size() > 0) {
							memberMap.put("members3" + (++count) + "", memberDao.queryList(Member.class, "id", "desc", attrs2)); // 第三级所有的客户
						}
					}*/
				}
			}
		}
		return memberMap;
	}

	/**
	 * 查询用户的三级客户的总数   --> 二级
	 */
	@SuppressWarnings("unchecked")
	public int queryTotalThreeCosTomer(Member member) {
		Map<String, Object> attrs = new HashMap<String, Object>();
		attrs.put("memberFather", member.getId());
		attrs.put("status", "1");
		List<Member> members1 = memberDao.queryList(Member.class, "id", "desc", attrs); // 第一级客户
		int count1 = members1.size();
		int count2 = 0;
		int count3 = 0;
		for (Member member1 : members1) {
			// 先根据第一级客户查询第二级客户
			Map<String, Object> attrs1 = new HashMap<String, Object>();
			attrs1.put("memberFather", member1.getId());
			attrs1.put("status", "1");
			count2 += Integer.parseInt(String.valueOf(memberDao.queryTotalRecord(Member.class, attrs1)));
			/*for (Member member2 : memberDao.queryList(Member.class, "id", "desc", attrs1)) {
				// 根据第二级客户查询第三级
				Map<String, Object> attrs2 = new HashMap<String, Object>();
				attrs2.put("memberFather", member2.getId());
				attrs2.put("status", "1");
				count3 += Integer.parseInt(String.valueOf(memberDao.queryTotalRecord(Member.class, attrs2)));
			}*/
		}
		return count1 + count2 + count3;
	}

	/**
	 * 查询所有用户不分页
	 */
	public List<Member> queryAllMember(String obj) {
		return this.memberDao.getAll(obj);
	}

	/**
	 * 查询所有用户(过滤掉删除态)
	 */
	@SuppressWarnings("unchecked")
	public Pager retrieveAllMembers(int pageStart, int pageSize) {
		Map<String, Object> attrs = new HashMap<String, Object>();
		attrs.put("status", "1");
		attrs.put("agent", null);
		return new Pager(pageSize, pageStart, (int) memberDao.queryTotalRecord(Member.class, attrs),
			memberDao.queryList(Member.class, (pageStart - 1) * pageSize, pageSize, "id", "desc", attrs));
	}

	/**
	 * 模糊查询用户，根据会员级别查询
	 */
	public Pager queryLikeMember(int pageStart, int pageSize, Member member) {
		return memberDao.queryCardAndRankByLike(pageStart, pageSize, member);
	}

	private Pager retrieveMembers(int pageStart, int pageSize) {
		return this.memberDao.queryMember(pageSize, pageStart);
	}

	/**
	 * 修改用户的总积分（消费）
	 */
	@Override
	public void editIntergralByLoginName(Member member, Double intergral) {
		Map<String, Object> attrs = new HashMap<String, Object>();
		Map<String, Object> props = new HashMap<String, Object>();
		attrs.put("loginName", member.getLoginName());
		attrs.put("status", "1");
		props.put("intergal", intergral + member.getIntergal());
		memberDao.update(Member.class, props, attrs);
	}

	/**
	 * 修改用户的总佣金（消费）
	 */
	@Override
	public void editCommissionByLoginName(Member member, Double commission) {
		Map<String, Object> attrs = new HashMap<String, Object>();
		Map<String, Object> props = new HashMap<String, Object>();
		attrs.put("loginName", member.getLoginName());
		attrs.put("status", "1");
		props.put("commission", commission + member.getCommission());
		memberDao.update(Member.class, props, attrs);
	}

	/**
	 * 修改用户的余额(充值或消费)
	 */
	@Override
	public void editRechargeByLoginName(Member member, Double balance) {
		Map<String, Object> attrs = new HashMap<String, Object>();
		Map<String, Object> props = new HashMap<String, Object>();
		attrs.put("loginName", member.getLoginName());
		attrs.put("status", "1");
		props.put("balance", balance + member.getBalance());
		memberDao.update(Member.class, props, attrs);
	}

	/**
	 * 余额支付
	 * 
	 * @param member
	 * @param balance
	 */
	public void payByBalance(Member member, Double balance) {
		Member member0 = this.retrieveMember(member.getId());
		member0.setBalance(member0.getBalance() - balance);
		this.memberDao.update(member0);
		RechargeInfo rechargeInfo = new RechargeInfo();
		rechargeInfo.setMemberId(member0);
		rechargeInfo.setRechargeAmount(balance);
		rechargeInfo.setRechargeTime(new Date());
		rechargeInfo.setRechargeType("1");
		rechargeInfo.setStatus("1");
		this.rechargeInfoDao.save(rechargeInfo);
	}

	/**
	 * 会员充值升级
	 */
	public void editRankById(Member member) {
		Map<String, Object> attrs = new HashMap<String, Object>();
		Map<String, Object> props = new HashMap<String, Object>();
		props.put("rank", member.getRank());
		attrs.put("id", member.getId());
		attrs.put("status", "1");
		memberDao.update(Member.class, props, attrs);
	}

	public Pager retrieveMembersPager(int pageSize, int pageNo) {
		return this.memberDao.getPager(pageSize, pageNo);
	}

	@Override
	public Pager checkAllAgent(int pageSize, int pageNo) {
		return this.memberDao.checkAllAgent(pageSize, pageNo);
	}

	@Override
	public boolean findAgent(String address) {
		Map<String, Object> attrs = new HashMap<String, Object>();
		attrs.put("agent", address);
		Member member = memberDao.query(Member.class, attrs);
		if (ObjValid.isValid(member)) {
			return false;
		}
		return true;
	}

	@Override
	public Pager findMyFans(int pageSize, int pageNo, Member member) {
		return this.memberDao.findMyFans(pageSize, pageNo, member);
	}

	@Override
	public int queryAllTeam(Member member) {
		int pageSrtart = 0;
		int pageSize = 0;
		Pager pager = memberDao.queryAllTeam(member, pageSrtart, pageSize);
		int count = pager.getResultList().size();
		return count;
	}

	@Override
	public CashInfo findByCashInfoId(Long id) {
		return this.memberDao.findCashInfoId(id);
	}

	@Override
	public Double queryMoneyToSend(Member member,ParameterSet parameterSet) {
		//所有可提现金额（分销提成+代理商补偿）
		double allMoney = 0.0;
		//分销提成
		double distriMoney = 0.0;
		Date today = new Date();// 今天
		String timeLimit = "0";
		if(ObjValid.isValid(parameterSet.getTimeLimit())){
			timeLimit = "-"+parameterSet.getTimeLimit();
		}
		Date sevenDate = DateHelper.getDateByCalculateDays(today, Integer.valueOf(timeLimit)); // N天前
		String sevenDateStr = DateHelper.dataToString(sevenDate, "yyyy-MM-dd HH:mm:ss");
		distriMoney =  this.memberDao.queryMoneyToSend(member, sevenDateStr);
		//代理商补偿
		double agentMoney = 0.0;
		Date threeDate = DateHelper.getDateByCalculateDays(today, -3); // 3天前
		String threeDateStr = DateHelper.dataToString(threeDate, "yyyy-MM-dd HH:mm:ss");
		agentMoney = this.memberDao.queryAgentMoneyToSend(member,threeDateStr);
		//（分销提成+代理商补偿）
		allMoney = distriMoney + agentMoney;
		return allMoney;
	}

	/**
	 * 生成代理商订单记录（未付款）
	 * */
	public AgentOrder agentOrder(Member AgentMember){
		List<ParameterSet> sl = this.parameterSetDao.findAll();
		ParameterSet ps = sl.get(0);
		//生成订单
		AgentOrder agentOrder = new AgentOrder();
		agentOrder.setMember(AgentMember);
		agentOrder.setAddTime(new Date());
		if(ObjValid.isValid(ps.getAgentAmount())){
			agentOrder.setAmount(Double.valueOf(ps.getAgentAmount()));
		}
		if(ObjValid.isValid(AgentMember.getFirstFather())){
			agentOrder.setFatherMember(AgentMember.getFirstFather());
			if(ObjValid.isValid(ps.getAgentCompensate())){
				double fatherBonus = Double.valueOf(ps.getAgentAmount())*Double.valueOf(ps.getAgentCompensate())*0.01;
				agentOrder.setFatherBonus(fatherBonus);
			}
		}
		agentOrder.setStatus("1");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String agentOrderNum = sdf.format(new Date())+String.format("%1$04d",1);
		agentOrder.setOrderNum(agentOrderNum);
		this.memberDao.saveAgentOrder(agentOrder);
		return agentOrder;
	}
	
	/**
	 * 成为代理商
	 * */
	@Override
	public void becomeAgent(AgentOrder agentOrder) {
		try {
			agentOrder = this.receiveAgentOrder(agentOrder.getId());
			//递归修改该会员的所有下级（的一级会员）
			Member agentMember = agentOrder.getMember();
			agentMember.setAgentTime(new Date());
			agentMember.setAgent("1");
			agentMember.setFirstFather(null);
			agentMember.setMemberFather(null);
			this.memberDao.update(agentMember);
			log.error("将代理商上级设空完毕");
			List<Member> ml = new ArrayList<Member>();
			ml = this.findMember(agentMember);
			if(ObjValid.isValid(ml)){
				log.error("有下级："+ml.size());
				this.changeFather(ml, agentMember);
				log.error("递归修改完毕");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("becomeAgent出错");
		}
	}
	
	//给代理商上级发补偿
	public void sendCompensate(AgentOrder agentOrder){
		Member member = agentOrder.getMember();
		if(ObjValid.isValid(member.getMemberFather())){
			Member memberFather = member.getMemberFather();
			this.newComissionInfo(agentOrder);
		}
	}
	
	/**
	 * 查找代理商订单
	 * */
	public AgentOrder receiveAgentOrder(Long id){
		return this.memberDao.receiveAgentOrder(id);
	}
	
	/**
	 * 为代理商订单补偿新增一条记录
	 * */
	public void newComissionInfo(AgentOrder agentOrder){
		CommissionInfo comissionInfo = new CommissionInfo();
		comissionInfo.setAddTime(new Date());
		comissionInfo.setCommission(agentOrder.getFatherBonus());
		comissionInfo.setCommissionComments("代理商上级补偿,"+agentOrder.getOrderNum());
		comissionInfo.setCommissionStatus("4");
		comissionInfo.setMemberId(agentOrder.getFatherMember());
		comissionInfo.setFromMemberId(agentOrder.getMember());
		comissionInfo.setStatus("1");
		this.commissionInfoDao.save(comissionInfo);
		
	}
	
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext*.xml");
		MemberManagerDao pp = (MemberManagerDao) context.getBean("memberManagerDao");
		AgentOrder agentOrder = pp.receiveAgentOrder(27L);
		pp.newComissionInfo(agentOrder);
	}
	
	//将代理商订单状态改为已付
	public void changeAgentOrder(AgentOrder agentOrder){
		agentOrder.setPayTime(new Date());
		Map<String, Object> attrs = new HashMap<String, Object>();
		Map<String, Object> props = new HashMap<String, Object>();
		attrs.put("id", agentOrder.getId());
		props.put("payTime", new Date());
		this.memberDao.update(AgentOrder.class, props, attrs);
	}
	
	//查找下一级
	public List<Member> findMember(Member member){
		return this.memberDao.findMember(member);
	}
	
	//修改下级会员的父级
	public void changeFather(List<Member> ml,Member AgentMember){
		for(int i=0;i<ml.size();i++){
			ml.get(i).setFirstFather(AgentMember);
			this.memberDao.update(ml.get(i));
			List<Member> nextList = new ArrayList<Member>();
			nextList = this.findMember(ml.get(i));
			if(ObjValid.isValid(nextList)){
				this.changeFather(nextList, AgentMember);
			}
		}
	}
	


}