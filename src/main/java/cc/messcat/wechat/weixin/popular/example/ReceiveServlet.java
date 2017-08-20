package cc.messcat.wechat.weixin.popular.example;


import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cc.messcat.entity.Member;
import cc.messcat.web.front.FrontAction;
import cc.messcat.wechat.weixin.popular.api.UserAPI;
import cc.messcat.wechat.weixin.popular.bean.EventMessage;
import cc.messcat.wechat.weixin.popular.bean.User;
import cc.messcat.wechat.weixin.popular.bean.templatemessage.TemplateMessageResult;
import cc.messcat.wechat.weixin.popular.bean.xmlmessage.XMLTextMessage;
import cc.messcat.wechat.weixin.popular.support.MessageManager;
import cc.messcat.wechat.weixin.popular.support.TokenManager;
import cc.messcat.wechat.weixin.popular.util.ExpireSet;
import cc.messcat.wechat.weixin.popular.util.SignatureUtil;
import cc.messcat.wechat.weixin.popular.util.XMLConverUtil;
import cc.modules.constants.Constants;
import cc.modules.security.EmojiFilter;
import cc.modules.util.DateHelper;
import cc.modules.util.ObjValid;

/**
 * 服务端事件消息接收
 * @author Yi
 *
 */
public class ReceiveServlet extends FrontAction{

    /**
	 *
	 */
	private static final long serialVersionUID = 1L;

	//从官方获取
	private String token = "mc_platform";
	
	private static Logger log = LoggerFactory.getLogger(ReceiveServlet.class); 

    //重复通知过滤  时效60秒
    private static ExpireSet<String> expireSet = new ExpireSet<String>(60);

    public String execute() throws Exception{
    	HttpServletRequest request = ServletActionContext.getRequest();
    	HttpServletResponse response = ServletActionContext.getResponse();
        ServletInputStream inputStream = request.getInputStream();;
        ServletOutputStream outputStream = response.getOutputStream();
		
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");
        
        //首次请求申请验证,返回echostr
        if(echostr!=null){
            outputStreamWrite(outputStream, echostr);
            return null;
        }
        
        //验证请求签名
        if(!signature.equals(SignatureUtil.generateEventMessageSignature(token,timestamp,nonce))){
            log.info("The request signature is invalid");
            return null;
        }
        
        if(inputStream!=null){
            //转换XML
            EventMessage eventMessage = XMLConverUtil.convertToObject(EventMessage.class,inputStream);
            String expireKey = eventMessage.getFromUserName() + "__"
            				   + eventMessage.getToUserName() + "__"
            				   + eventMessage.getMsgId() + "__"
            				   + eventMessage.getCreateTime();
            log.info("---------------------------------------扫码关注哦"+eventMessage.getTicket()+"-------------------------"+eventMessage.getEvent()+"---------------------好的----------------"+eventMessage.getFromUserName());
            if(expireSet.contains(expireKey)){
            	//重复通知不作处理
            	return null;
            }else{
            	expireSet.add(expireKey);
            }
            String openid = eventMessage.getFromUserName();
    		Member condition = new Member();
    		condition.setLoginName(openid);
    		//查找当前用户是否为公众号会员
    		Member member = this.memberManagerDao.retrieveMemberByLoginName(condition);
    		
            StringBuilder welcomString = new StringBuilder();
            if (!ObjValid.isValid(member)) {
	            if ("subscribe".equals(eventMessage.getEvent()) || "SCAN".equals(eventMessage.getEvent())) {
	            	
	            	if (ObjValid.isValid(eventMessage.getTicket())) {  //通过二维码扫描未关注的公众号
	            			member = new Member();
	            			//如果不是，则注册新的用户
	            			String access_token = TokenManager.getToken(Constants.APPID);
	            			log.info("ReceiveServlet:access_token-"+access_token);
	            			log.info("ReceiveServlet:openid-"+openid);
	            			
	            			User user = UserAPI.userInfo(access_token, openid);
	            			log.info(user.toString());
	            			String nickNameFilter = user.getNickname()==null?"":user.getNickname();
	            			nickNameFilter = EmojiFilter.filterEmoji(nickNameFilter);
							member.setNickname(nickNameFilter);
							
							log.info("ReceiveServlet:user.getNickname()-"+user.getNickname());
							member.setImagePath(user.getHeadimgurl());
							member.setLoginName(openid);
							member.setDistributor(Constants.VISITOR);//初始注册为游客
							member.setStatus("1");
							member.setFocusWay(Constants.FOCUS_WAY_QRCODE);
							member.setAgentBonus(0.0);
							member.setSendCommission(0.0);
							member.setMessageFalg("0");
							if ("2".equals(user.getSex())) {
								member.setSex("0");
							}else if ("1".equals(user.getSex())) {
								member.setSex("1");
							}else {
								member.setSex("2");
							}
							member.setFocusWay(Constants.FOCUS_WAY_QRCODE);
							log.info("----------------------------二维码参数--开始--------------------------------");
							log.info(eventMessage.getEvent());
							log.info(eventMessage.getEventKey());
							log.info("----------------------------二维码参数--结束---------------------------------");
							//判断是否通过会员推荐二维码扫描关注
							String fatherId ="";
							if ("SCAN".equals(eventMessage.getEvent())) {
								fatherId = eventMessage.getEventKey();
							}else {
								String[] evenKeyStrings = eventMessage.getEventKey().split("_");
								fatherId = evenKeyStrings[1];
							}
							if (ObjValid.isValid(fatherId)) {
								Member memberFather =this.memberManagerDao.retrieveMember(Long.parseLong(fatherId));
								member.setMemberFather(memberFather);
								if(ObjValid.isValid(memberFather.getMemberFather())){
									 member.setFirstFather(memberFather.getMemberFather());
								}else{
								     member.setFirstFather(memberFather);
								}
								welcomString.append("恭喜您成为百丽春会员！\n");
							}
							this.memberManagerDao.addMember(member);
							
							Member tempmMember = member;
			    			tempmMember = tempmMember.getMemberFather();
				    			if (ObjValid.isValid(tempmMember)) {
				    				//for (int i = 0; i < 3; i++) {
				    				for (int i = 0; i < 2; i++) {
				    					if (ObjValid.isValid(tempmMember)&&tempmMember.getMessageFalg().equals("1")) {
				    						String userLevel = "";
					        				if (i==0) {
					        					userLevel = "一级";
					    					}
					        				if (i==1) {
					        					userLevel = "二级";
					        				}
					        				/*if (i==2) {
					        					userLevel = "三级";
					        				}*/
					        				//TODO：推送给上三级；
					        				//log.info("扫码三级："+member.getNickname()+" --" + member.getFocusWay());
					        				log.info("扫码二级："+member.getNickname()+" --" + member.getFocusWay());
					        				TemplateMessageResult templateMessageResult = MessageManager.sendNewMemberTemplateMessage(userLevel, member.getId()+"", member.getNickname(), DateHelper.dataToString(member.getAddTime(), "yyyy-MM-dd HH:mm:ss"), tempmMember.getLoginName(), member.getFocusWay());
	//						    			log.info("ReceiveServlet:"+templateMessageResult.getErrcode()+" --" + templateMessageResult.getErrmsg());
					        				tempmMember = tempmMember.getMemberFather();
				    					}
				    					
				    				}
								}
						}
					}
			}else {
				if (ObjValid.isValid(member) && ObjValid.isValid(member.getMemberFather())) {
					welcomString.append("恭喜您成为百丽春会员!\n");
				}else {
					welcomString.append("恭喜您成为百丽春会员!\n");
				}
			}
            welcomString.append("你好，感谢您对“百丽春”粘胶的支持与关注，我们将竭诚为您服务。如有任何问题或建议可直接留言，客服上线会第一时间回复您，也可以直接拨打全国客服热线：0755-29926111");
//           HttpUriRequest httpUriRequest = RequestBuilder.get()
//				.setUri("http://990031b8.ngrok.io/distri/epIndexAction.htm")
//				.build();
//           HttpResponse response2= LocalHttpClient.execute(httpUriRequest);
//           HttpEntity entity = response2.getEntity();
//           String aString = EntityUtils.toString(entity,"UTF-8");
            
            //创建回复
            XMLTextMessage xmlTextMessage = new XMLTextMessage(
                    eventMessage.getFromUserName(),
                    eventMessage.getToUserName(),
                    welcomString.toString());
            //回复
            xmlTextMessage.outputStreamWrite(outputStream);
            return null;
        }
        outputStreamWrite(outputStream,"");
        return null;
    }

    /**
     * 数据流输出
     * @param outputStream
     * @param text
     * @return
     */
    private boolean outputStreamWrite(OutputStream outputStream,String text){
        try {
            outputStream.write(text.getBytes("utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
