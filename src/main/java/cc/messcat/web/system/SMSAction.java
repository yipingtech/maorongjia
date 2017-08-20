/*
 * WfParamAction.java
 * 
 * Create by MCGT
 * 
 * Create time 2014-07-15
 * 
 * Version 1.0
 * 
 * Copyright 2013 Messcat, Inc. All rights reserved.
 * 
 */
package cc.messcat.web.system;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;

import cc.messcat.entity.Users;
import cc.modules.commons.PageAction;
import cc.modules.constants.Constants;
import cc.modules.util.RandUtil;
import cc.modules.util.SmsUtil;
import cc.modules.util.XmlUtil;

public class SMSAction extends PageAction {

	private String receivedType;
	private String receivedIds;
	private String content;
	private String mobile;
	private String returnMsg;
	
	
	private Map code = null;
	
	private static String signature = "【签名】";

	@Override
	public String execute() throws Exception {
		
		return super.execute();
	}
	
	/**
	 * 发送短信给所有人
	 * @return
	 
	public String sendSMS(){
		
		String[] IDs = this.receivedIds.split("#");
		List<Users> namelist = this.usersManagerDao.extractAllUsersID(this.receivedType,IDs);
		StringBuffer msg = new StringBuffer();
		
		StringBuffer mobiles = new StringBuffer();
		
		Iterator it = namelist.iterator();
		while(it.hasNext()){
			Users u = (Users)it.next();
			if("".equals(u.getMobile()) || u.getMobile() == null){
				msg.append(u.getName()).append(" 没有相关手机号码!<br />");
			}else{
				mobiles.append(u.getMobile());
				if(it.hasNext()) mobiles.append(",");
			}
		}	
		code = XmlUtil.parseStringXmlToMap(SmsUtil.sendMessage(mobiles.toString(), content+signature));
		msg.append(code.get("msg"));
		
		addActionMessage(msg.toString());
		return "success";
	}
	*/
	
	/**
	 * 发送手机验证码
	 * @throws IOException
	 */
	public String sendCheckCode() throws IOException{
		
		StringBuffer sb = new StringBuffer();
		if("".equals(this.mobile) || this.mobile == null){
			this.returnMsg = "当前用户没有录入手机号码！";
		}else{
			Constants.CHECK_CODE = RandUtil.getRandomStr(4);
			//log.info("==============="+this.content+"=================");
			sb.append("亲，您本次的注册验证码为").append(Constants.CHECK_CODE).append("，请于10分钟内在页面正确输入。切勿把验证码泄露于他人。感谢您的注册，祝您购物愉快！");
			//log.info("==============="+sb.toString()+"=================");
			code = XmlUtil.parseStringXmlToMap(SmsUtil.sendMessage(mobile, sb.toString()));
			//log.info(code);
			this.returnMsg = (String)code.get("msg");
			//this.returnMsg="成功提交短信";
		}
		return SUCCESS;
	}
	
	public void send() throws IOException{	
		code = XmlUtil.parseStringXmlToMap(SmsUtil.sendMessage(mobile,this.content+this.signature));
	}

	public String getReceivedType() {
		return receivedType;
	}

	public void setReceivedType(String receivedType) {
		this.receivedType = receivedType;
	}

	public String getReceivedIds() {
		return receivedIds;
	}

	public void setReceivedIds(String receivedIds) {
		this.receivedIds = receivedIds;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getReturnMsg() {
		return returnMsg;
	}

	public void setReturnMsg(String returnMsg) {
		this.returnMsg = returnMsg;
	}

	public Map getCode() {
		return code;
	}

	public void setCode(Map code) {
		this.code = code;
	}
	
}