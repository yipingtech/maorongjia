package cc.modules.security;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cc.messcat.bases.BaseAction;
import cc.messcat.entity.Member;
import cc.messcat.wechat.weixin.popular.api.SnsAPI;
import cc.messcat.wechat.weixin.popular.bean.SnsToken;
import cc.messcat.wechat.weixin.popular.bean.User;
import cc.messcat.wechat.weixin.popular.bean.templatemessage.TemplateMessageResult;
import cc.messcat.wechat.weixin.popular.support.MessageManager;
import cc.messcat.wechat.weixin.popular.util.ExpireSet;
import cc.modules.constants.Constants;
import cc.modules.util.DateHelper;
import cc.modules.util.ObjValid;

@SuppressWarnings("serial")
public class MemberInit extends BaseAction implements Filter {
	
	private static Logger log = LoggerFactory.getLogger(MemberInit.class);
	
	//重复通知过滤  时效60秒
    private static ExpireSet<String> expireSet = new ExpireSet<String>(60);
	
	@Override
	public void destroy() {
		
	}

	@SuppressWarnings({ "unused" })
	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain filterchain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)arg0;
		HttpServletResponse resp=(HttpServletResponse) arg1;
        HttpSession session = request.getSession();
        
		String code = (String) request.getParameter("code");
		log.info("-------------------------------------fatherId-------------"+request.getParameter("fatherId")+"----------------------------"+request.getParameter("code")+"-----------------"+request.getParameter("from"));
		
		//String basePath = request.getScheme()+"://"+request.getServerName();
		String basePath = Constants.WEBSITE_URL;
		String requestURI = request.getRequestURI();  
		String queryString = request.getQueryString();
		String fullPath  = basePath + requestURI;  
		if (ObjValid.isValid(queryString)) {
			fullPath += "?"+queryString;
		}
		
		log.info("过滤器全路径--"+fullPath);
		if (ObjValid.isValid(code)) {
			log.info(code);
			if(expireSet.contains(code)){//重复通知不作处理
				filterchain.doFilter(arg0, arg1);
				return ;
			}else{
				expireSet.add(code);
			}
			String fatherId = (String) request.getParameter("fatherId");
			log.info("-------------------------------------fatherId-------------"+fatherId);
			SnsToken snsToken = SnsAPI.oauth2AccessToken(Constants.APPID, Constants.APPSECRET, code);  //获取openid
			String openid = snsToken.getOpenid();
			if (ObjValid.isValid(openid)) {
				log.info("openid=" + openid);
				session.setAttribute("openid", openid); 
				try {
					User user = SnsAPI.userinfo(snsToken.getAccess_token(), openid, "zh_CN");
					if (ObjValid.isValid(user) && ObjValid.isValid(user.getNickname()) ) {
						//log.info("user:"+ user.getNickname()); 
						Member member = new Member();
						String nickNameFilter = user.getNickname()==null?"":user.getNickname();
						nickNameFilter = EmojiFilter.filterEmoji(nickNameFilter);
						//log.info(nickNameFilter);
						member.setNickname(nickNameFilter);
						member.setImagePath(user.getHeadimgurl());
						member.setLoginName(openid);
						member.setDistributor(Constants.VISITOR);//初始注册为游客
						member.setStatus("1");
						member.setFocusWay(Constants.FOCUS_WAY_URL);
						member.setAgentBonus(0.0);
						member.setSendCommission(0.0);
						member.setMessageFalg("0");
						if ("2".equals(user.getSex())) {
							member.setSex("0");
						}else {
							member.setSex(user.getSex().toString());
						}
						if (ObjValid.isValid(fatherId)) {
							//member.setMemberFather(memberManagerDao.retrieveMember(Long.parseLong(fatherId)));
							Member memberFather =this.memberManagerDao.retrieveMember(Long.parseLong(fatherId));
							log.info("--------------------------------------------父级-----"+memberFather);
							member.setMemberFather(memberFather);
							if(ObjValid.isValid(memberFather.getFirstFather())){
								 member.setFirstFather(memberFather.getFirstFather());
							}else{
								 member.setFirstFather(memberFather);
							}
						}
						log.info("member:"+ member.getNickname()); 
						Member member0 = this.memberManagerDao.retrieveMemberByLoginName(member);
						//正常登陆
						if(ObjValid.isValid(member0)){
							log.info("会员已关注，正常登录...");
							member0.setImagePath(user.getHeadimgurl());
							member0.setNickname(nickNameFilter);
							memberManagerDao.updateMember(member0);
							session.setAttribute("member", member0);
						}else {
							fatherId = null;
							member.setFocusWay(Constants.FOCUS_WAY_URL);
							log.info("新关注得会员保存到本地...");
							this.memberManagerDao.addMember(member);
							//发送添加通知
							Member tempmMember = member;
							if (ObjValid.isValid(tempmMember.getMemberFather())) {
								tempmMember = tempmMember.getMemberFather();
							}
			    			log.info("tempmMember:::"+tempmMember);
			    			if (ObjValid.isValid(tempmMember)) {
				    				log.info("tempmMember:::"+tempmMember.getNickname());
				    				for (int i = 0; i < 2; i++) {
				    					if (ObjValid.isValid(tempmMember)&&tempmMember.getMessageFalg().endsWith("1")) {
				    						String userLevel = "";
					        				if (i==0) {
					        					userLevel = "一级";
					    					}
					        				if (i==1) {
					        					userLevel = "二级";
					        				}
					        				//TODO：推送给上二级；
					        				TemplateMessageResult templateMessageResult = MessageManager.sendNewMemberTemplateMessage(userLevel, member.getId()+"", member.getNickname(), DateHelper.dataToString(member.getAddTime(), "yyyy-MM-dd HH:mm:ss"), tempmMember.getLoginName(), member.getFocusWay());
	//						    			log.info("ReceiveServlet:"+templateMessageResult.getErrcode()+" --" + templateMessageResult.getErrmsg());
				    					}
				    					if (ObjValid.isValid(tempmMember.getMemberFather())) {
				    						tempmMember = tempmMember.getMemberFather();
										}
				    				}
							}
							session.setAttribute("member", member);
						}
					}
					
				} catch (Exception e) {
					e.printStackTrace();
					log.error("登录过滤异常");
				}
				//resp.sendRedirect("epIndexAction.htm");
			}
		}else{
			String fatherId = (String) request.getParameter("fatherId");
			if(ObjValid.isValid(fatherId)){
				log.info("过滤器fatherId = "+ fatherId);
				Member member = (Member) session.getAttribute("member");
				if(!ObjValid.isValid(member)){
					String urlString = Constants.WEBSITE_URL+requestURI+"?"+request.getQueryString();
					urlString = URLEncoder.encode(urlString, "UTF-8");
					String wechatUrl = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + Constants.APPID + "&redirect_uri=" + urlString
						+ "&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";
					log.info("过滤器wechatUrl = "+ wechatUrl);
					resp.sendRedirect(wechatUrl);
					return;	
				}else{
					log.info("过滤器member = "+ member.getNickname());
				}
			}
		}
		filterchain.doFilter(arg0, arg1);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}
}
