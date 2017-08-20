package cc.messcat.entity;

import java.util.Date;

/**
 * Member entity. @author MyEclipse Persistence Tools
 */

public class Member implements java.io.Serializable {

	// Fields

	private Long id;
	private String loginName;
	private String cardNum;
	private String password;
	private String payPassword;
	private String nickname;
	private String sex;
	private String realname;
	private String birthday;
	private String career;
	private String qq;
	private String email;
	private String mobile;
	private String telephone;
	private String address;
	private String postcode;
	private Date addTime;
	private Date editTime;
	private String imagePath;
	private Double intergal;
	private Double balance;
	private Long report;
	private Double commission;
	private Double sendCommission;
	private Double bonus;
	private String distributor;
	private String rank;
	private Member memberFather;
	private String status;
	private Double commissionLine;
	private String agent;
	private Date agentTime;
	private String agentArea;
	private Double agentBonus;
	
	private Member firstFather;
	private String qrcodeName;
	private String focusWay;
	private String messageFalg;

	// Property accessors
	
	public Double getSendCommission() {
		return sendCommission;
	}

	public void setSendCommission(Double sendCommission) {
		this.sendCommission = sendCommission;
	}
	public Double getAgentBonus() {
		return agentBonus;
	}

	public void setAgentBonus(Double agentBonus) {
		this.agentBonus = agentBonus;
	}

	public Member getFirstFather() {
		return firstFather;
	}

	public void setFirstFather(Member firstFather) {
		this.firstFather = firstFather;
	}

	public String getAgentArea() {
		return agentArea;
	}

	public void setAgentArea(String agentArea) {
		this.agentArea = agentArea;
	}

	public String getAgent() {
		return agent;
	}

	public void setAgent(String agent) {
		this.agent = agent;
	}

	public Date getAgentTime() {
		return agentTime;
	}

	public void setAgentTime(Date agentTime) {
		this.agentTime = agentTime;
	}

	public Double getCommissionLine() {
		return commissionLine;
	}

	public void setCommissionLine(Double commissionLine) {
		this.commissionLine = commissionLine;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLoginName() {
		return this.loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getCardNum() {
		return this.cardNum;
	}

	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickname() {
		return this.nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getRealname() {
		return this.realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getBirthday() {
		return this.birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getCareer() {
		return this.career;
	}

	public void setCareer(String career) {
		this.career = career;
	}

	public String getQq() {
		return this.qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPostcode() {
		return this.postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public Date getAddTime() {
		return this.addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public Date getEditTime() {
		return this.editTime;
	}

	public void setEditTime(Date editTime) {
		this.editTime = editTime;
	}

	public String getImagePath() {
		return this.imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public Double getIntergal() {
		return this.intergal;
	}

	public void setIntergal(Double intergal) {
		this.intergal = intergal;
	}

	public Double getBalance() {
		return this.balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public Long getReport() {
		return this.report;
	}

	public void setReport(Long report) {
		this.report = report;
	}

	public Double getCommission() {
		return this.commission;
	}

	public void setCommission(Double commission) {
		this.commission = commission;
	}

	public String getRank() {
		return this.rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public Member getMemberFather() {
		return memberFather;
	}

	public void setMemberFather(Member memberFather) {
		this.memberFather = memberFather;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Double getBonus() {
		return bonus;
	}

	public void setBonus(Double bonus) {
		this.bonus = bonus;
	}

	public String getPayPassword() {
		return payPassword;
	}

	public void setPayPassword(String payPassword) {
		this.payPassword = payPassword;
	}


	public String getDistributor() {
		return distributor;
	}

	public void setDistributor(String distributor) {
		this.distributor = distributor;
	}

	public String getQrcodeName() {
		return qrcodeName;
	}

	public void setQrcodeName(String qrcodeName) {
		this.qrcodeName = qrcodeName;
	}

	public String getFocusWay() {
		return focusWay;
	}

	public void setFocusWay(String focusWay) {
		this.focusWay = focusWay;
	}

	public String getMessageFalg() {
		return messageFalg;
	}

	public void setMessageFalg(String messageFalg) {
		this.messageFalg = messageFalg;
	}

}