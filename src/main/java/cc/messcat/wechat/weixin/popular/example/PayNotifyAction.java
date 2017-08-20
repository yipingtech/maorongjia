package cc.messcat.wechat.weixin.popular.example;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cc.messcat.bases.BaseAction;
import cc.messcat.entity.AgentOrder;
import cc.messcat.entity.Member;
import cc.messcat.entity.PayOrder;
import cc.messcat.wechat.weixin.popular.bean.pay.PayResult;
import cc.messcat.wechat.weixin.popular.util.XMLConverUtil;
import cc.modules.util.ObjValid;

/**
 * 支付回调通知
 * @author LiYi
 *
 */
public class PayNotifyAction extends BaseAction{

	private static Logger log = LoggerFactory.getLogger(PayNotifyAction.class); 
	private static final long serialVersionUID = 1L;

	@Override
	public String execute() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
    	HttpServletResponse response = ServletActionContext.getResponse();
    	log.error("支付回调地址调用");
    	try {
    		//获取请求数据
    		PayResult payResult = XMLConverUtil.convertToObject(PayResult.class, request.getInputStream());
    		//签名验证
    		if(payResult != null && !"SUCCESS".equals(payResult.getResult_code()) && !"SUCCESS".equals(payResult.getReturn_code())){
    			response.getOutputStream().write("error".getBytes());
    		}else{
    			//执行支付成功后的逻辑代码
    			//支付成功后  若是第一次购买就变成分销商
    			String out_trade_no = payResult.getOut_trade_no();
    			PayOrder payOrder = new PayOrder();
    			payOrder = this.payOrderManagerDao.queryByOrderNum(out_trade_no);
    			//订单若无此订单号，则为代理商订单
    			if(!ObjValid.isValid(payOrder)){
    				AgentOrder agentOrder = new AgentOrder();
					agentOrder = this.payOrderManagerDao.queryAgentByOrderNum(out_trade_no);
					//查询订单是否已付款，防止二次回调
    				if(ObjValid.isValid(agentOrder)&&ObjValid.isValid(agentOrder.getPayTime())){
    					log.error("拦截代理商订单二次回调");
    					response.getOutputStream().write("success".getBytes());
    					return null;
    				}
    				//代理商订单新增付款时间
					this.memberManagerDao.changeAgentOrder(agentOrder);
					log.error("代理商订单付款时间:" + agentOrder.getPayTime());
    				//检测该代理商是否有上级，若有发补偿
					log.error("agentOrder.getMember");
    				this.memberManagerDao.sendCompensate(agentOrder);
    				log.error("代理商补偿已发");
    				//递归修改代理商所有下级
    				this.memberManagerDao.becomeAgent(agentOrder);
    				log.error("代理商订单:" + out_trade_no + "已更新.");
    				response.getOutputStream().write("success".getBytes());
    				return null;
    			}
    			Member member  = payOrder.getMember();
    			if(member.getDistributor().equals("0")){
    				member.setDistributor("1");
        			this.memberManagerDao.modifyMember(member);	
    			}
    			
    			log.info("out_trade_no:" + out_trade_no);
    			if(ObjValid.isValid(out_trade_no)){
    				payOrderManagerDao.finishTrade(out_trade_no);
//    				changeOrderStatus(out_trade_no);
    				log.info("订单:" + out_trade_no + "已更新.");
    			}
    		}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("支付回调异常");
		}
    	response.getOutputStream().write("success".getBytes());
		return null;
	}
	
	public static void main(String[] args) throws Exception {
		//消息通知1
//		LinkedHashMap<String, TemplateMessageItem> templateMessageItem = new LinkedHashMap<String, TemplateMessageItem>();
//		TemplateMessageItem templateMessageItem1 = new TemplateMessageItem("消费金额", "#000000");
//		TemplateMessageItem templateMessageItem2 = new TemplateMessageItem("0.01", "#000000");
//		TemplateMessageItem templateMessageItem3 = new TemplateMessageItem("会员", "#000000");
//		TemplateMessageItem templateMessageItem4 = new TemplateMessageItem("小亮", "#000000");
//		TemplateMessageItem templateMessageItem5 = new TemplateMessageItem("2015-08-04 05:25:21", "#000000");
//		TemplateMessageItem templateMessageItem6 = new TemplateMessageItem("感谢您的购物，祝您生活愉快", "#000000");
//		templateMessageItem.put("productType", templateMessageItem1);
//		templateMessageItem.put("name", templateMessageItem2);
//		templateMessageItem.put("accountType", templateMessageItem3);
//		templateMessageItem.put("account", templateMessageItem4);
//		templateMessageItem.put("time", templateMessageItem5);
//		templateMessageItem.put("remark", templateMessageItem6);
//		
//		
//		
//		TemplateMessage templateMessage = new TemplateMessage();
//		templateMessage.setTemplate_id(Constants.TEMPLATE_MEMBER_EXPENSE_MESSAGE);
//		templateMessage.setTopcolor("#000000");
//		templateMessage.setTouser("orxiEtyvbryGCiIsfLcmNjXNWKsM"); //silver
//		templateMessage.setUrl("");
//		templateMessage.setData(templateMessageItem);
//		TemplateMessageResult templateMessageResult =  MessageAPI.messageTemplateSend(TokenManager.getToken(Constants.APPID), templateMessage);
//		log.info("ReceiveServlet:"+templateMessageResult.getErrcode()+" --" + templateMessageResult.getErrmsg());
//		
//		//消息通知2
//		LinkedHashMap<String, TemplateMessageItem> templateMessageItem32 = new LinkedHashMap<String, TemplateMessageItem>();
//		TemplateMessageItem templateMessageItem21 = new TemplateMessageItem("消费金额", "#000000");
//		TemplateMessageItem templateMessageItem22 = new TemplateMessageItem("0.01", "#000000");
//		TemplateMessageItem templateMessageItem23 = new TemplateMessageItem("会员", "#000000");
//		TemplateMessageItem templateMessageItem24 = new TemplateMessageItem("troy", "#000000");
//		TemplateMessageItem templateMessageItem25 = new TemplateMessageItem("2015-08-04 05:25:21", "#000000");
//		TemplateMessageItem templateMessageItem26 = new TemplateMessageItem("感谢您的购物，祝您生活愉快", "#000000");
//		templateMessageItem32.put("productType", templateMessageItem21);
//		templateMessageItem32.put("name", templateMessageItem22);
//		templateMessageItem32.put("accountType", templateMessageItem23);
//		templateMessageItem32.put("account", templateMessageItem24);
//		templateMessageItem32.put("time", templateMessageItem25);
//		templateMessageItem32.put("remark", templateMessageItem26);
//		
//		
//		
//		TemplateMessage templateMessage2 = new TemplateMessage();
//		templateMessage2.setTemplate_id(Constants.TEMPLATE_MEMBER_EXPENSE_MESSAGE);
//		templateMessage2.setTopcolor("#000000");
//		templateMessage2.setTouser("orxiEtyV5w-K2G7SIYu9cejrCMbU");//troy
//		templateMessage2.setUrl("");
//		templateMessage2.setData(templateMessageItem32);
//		TemplateMessageResult templateMessageResult1 =  MessageAPI.messageTemplateSend(TokenManager.getToken(Constants.APPID), templateMessage2);
//		log.info("ReceiveServlet:"+templateMessageResult1.getErrcode()+" --" + templateMessageResult1.getErrmsg());
		log.info(UUID.randomUUID().toString().replace("-", ""));
	}
	
}
