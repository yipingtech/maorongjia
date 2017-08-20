package cc.messcat.wechat.weixin.popular.support;

import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Set;

import cc.messcat.entity.OrderInfo;
import cc.messcat.entity.PayOrder;
import cc.messcat.wechat.weixin.popular.api.MessageAPI;
import cc.messcat.wechat.weixin.popular.bean.templatemessage.TemplateMessage;
import cc.messcat.wechat.weixin.popular.bean.templatemessage.TemplateMessageItem;
import cc.messcat.wechat.weixin.popular.bean.templatemessage.TemplateMessageResult;
import cc.modules.constants.Constants;
import cc.modules.util.ObjValid;

/**
 * MessageManager	消息通知
 * @author Silver
 *
 */
public class MessageManager {

	/**
	 * 发送新增会员通知
	  * @param memberLevel
	 * 				会员等级：分为一级、二级、三级
	 * @param memberNumber
	 * 				会员编号
	 * @param memberNickName
	 * 				会员昵称
	 * @param addTime
	 * 				加入时间
	 * @param toUserName
	 * 				给谁发通知
	 * @param focusWay
	 * 				关注方式
	 * @return
	 * 				返回通知信息结果；
	 */
	public static TemplateMessageResult sendNewMemberTemplateMessage(String memberLevel, String memberNumber, String memberNickName, String addTime, String toUserName, String focusWay ){
		LinkedHashMap<String, TemplateMessageItem> templateMessageItem = new LinkedHashMap<String, TemplateMessageItem>();
		TemplateMessageItem templateMessageItem1 = new TemplateMessageItem("恭喜您，有新会员加入您的团队", "#000000");
		TemplateMessageItem templateMessageItem2 = new TemplateMessageItem(memberNumber, "#000000");
		TemplateMessageItem templateMessageItem3 = new TemplateMessageItem(addTime, "#000000");
		
		String focusWayString = "";
		if (Constants.FOCUS_WAY_QRCODE.equals(focusWay)) {
			focusWayString = "二维码";
		}else {
			focusWayString = "链接";
		}
		
		//TemplateMessageItem templateMessageItem4 = new TemplateMessageItem(memberNickName+"【"+memberLevel+"】通过"+focusWayString+"关注了【百丽春】成为您的会员", "#000000");
		TemplateMessageItem templateMessageItem4 = new TemplateMessageItem("如有疑问请联系我们！", "#000000");
		templateMessageItem.put("first", templateMessageItem1);
		templateMessageItem.put("keyword1", templateMessageItem2);
		templateMessageItem.put("keyword2", templateMessageItem3);
		templateMessageItem.put("remark", templateMessageItem4);
		
		TemplateMessage templateMessage = new TemplateMessage();
		templateMessage.setTemplate_id(Constants.TEMPLATE_MEMBER_ADD);
		templateMessage.setTopcolor("#000000");
		templateMessage.setTouser(toUserName);
		templateMessage.setUrl("");
		templateMessage.setData(templateMessageItem);
		String token = TokenManager.getToken(Constants.APPID);
		TemplateMessageResult templateMessageResult =  MessageAPI.messageTemplateSend(token, templateMessage);
		return templateMessageResult;
	}
	/**
	 * 
	 * 发货通知
	 * */
	public static TemplateMessageResult sendExpressTemplateMessage(PayOrder payOrder){
	    SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    //String sendTime=df.format(payOrder.getShippingTime());
        LinkedHashMap<String, TemplateMessageItem> templateMessageItem = new LinkedHashMap<String, TemplateMessageItem>();
		TemplateMessageItem templateMessageItem1 = new TemplateMessageItem("您好，您的订单已发货！", "#000000");
		TemplateMessageItem templateMessageItem2 = new TemplateMessageItem(payOrder.getName(), "#000000");
		TemplateMessageItem templateMessageItem3 = new TemplateMessageItem(payOrder.getPhone(), "#000000");
		TemplateMessageItem templateMessageItem4 = new TemplateMessageItem(payOrder.getShippingName(), "#000000");
		TemplateMessageItem templateMessageItem5 = new TemplateMessageItem(payOrder.getInvoiceNum(), "#000000");
		TemplateMessageItem templateMessageItem6 = new TemplateMessageItem(payOrder.getOrderNum(), "#000000");
		TemplateMessageItem templateMessageItem7 = new TemplateMessageItem("百丽春感谢您的支持，欢迎再次光临~", "#000000");
		/*TemplateMessageItem templateMessageItem2 = new TemplateMessageItem(payOrder.getOrderNum(), "#000000");
		TemplateMessageItem templateMessageItem3 = new TemplateMessageItem(payOrder.getInvoiceNum(), "#000000");
		TemplateMessageItem templateMessageItem4 = new TemplateMessageItem(sendTime, "#000000");
		TemplateMessageItem templateMessageItem5 = new TemplateMessageItem("百丽春感谢您的支持，欢迎再次光临~", "#000000");
		TemplateMessageItem templateMessageItem6 = new TemplateMessageItem("支付时间:"+addTime+"/n支付状态:"+paymentType, "#000000");*/
		templateMessageItem.put("first", templateMessageItem1);
		templateMessageItem.put("keyword1", templateMessageItem2);
		templateMessageItem.put("keyword2", templateMessageItem3);
		templateMessageItem.put("keyword3", templateMessageItem4);
		templateMessageItem.put("keyword4", templateMessageItem5);
		templateMessageItem.put("keyword5", templateMessageItem6);
		templateMessageItem.put("remark", templateMessageItem7);
		
		TemplateMessage templateMessage = new TemplateMessage();
		templateMessage.setTemplate_id(Constants.TEMPLATE_ORDER_SEND);
		templateMessage.setTopcolor("#000000");
		templateMessage.setTouser(payOrder.getMember().getLoginName());
		templateMessage.setUrl("");
		templateMessage.setData(templateMessageItem);
		
		TemplateMessageResult templateMessageResult =  MessageAPI.messageTemplateSend(TokenManager.getToken(Constants.APPID), templateMessage);
		return templateMessageResult;
	}
	
	
	/**
	 * 新增订单信息
	 * @param memberLevel
	 * 			会员等级：分为一级、二级、宾妃
	 * @param memberNickName
	 * 			会员昵称
	 * @param payOrderNum
	 * 			订单号
	 * @param goodsName
	 * 			商品名称
	 * @param goodsAmount
	 * 			商品数量
	 * @param addTime
	 * 			下单时间
	 * @param money
	 * 			下单金额
	 * @param paymentType
	 *			付款状态
	 * @param remark
	 * 			备注
	 * @param toUserName
	 * 			被发消息的用户openid
	 * @return
	 * 		返回通知信息结果；
	 */
//	public static TemplateMessageResult sendNewOrderTemplateMessage(String memberLevel,String memberNickName, String payOrderNum, String goodsName, String goodsAmount , String addTime, String money, String paymentType, String remark,String toUserName ){
//		LinkedHashMap<String, TemplateMessageItem> templateMessageItem = new LinkedHashMap<String, TemplateMessageItem>();
//		
//		String tempLevelString = "";
//		if (ObjValid.isValid(memberLevel)) {
//			tempLevelString = "的【"+memberLevel+"】"+memberNickName;
//		}
//		TemplateMessageItem templateMessageItem1 = new TemplateMessageItem("您"+tempLevelString+"已成功下单", "#000000");
//		TemplateMessageItem templateMessageItem2 = new TemplateMessageItem(payOrderNum, "#000000");
//		TemplateMessageItem templateMessageItem3 = new TemplateMessageItem(goodsName, "#000000");
//		TemplateMessageItem templateMessageItem4 = new TemplateMessageItem(goodsAmount, "#000000");
//		TemplateMessageItem templateMessageItem5 = new TemplateMessageItem(money, "#000000");
//		TemplateMessageItem templateMessageItem6 = new TemplateMessageItem(remark, "#000000");
////		TemplateMessageItem templateMessageItem6 = new TemplateMessageItem("支付时间:"+addTime+"/n支付状态:"+paymentType, "#000000");
//		templateMessageItem.put("first", templateMessageItem1);
//		templateMessageItem.put("keyword1", templateMessageItem2);
//		templateMessageItem.put("keyword2", templateMessageItem3);
//		templateMessageItem.put("keyword3", templateMessageItem4);
//		templateMessageItem.put("keyword4", templateMessageItem5);
//		templateMessageItem.put("remark", templateMessageItem6);
//		
//		TemplateMessage templateMessage = new TemplateMessage();
//		templateMessage.setTemplate_id(Constants.TEMPLATE_ORDER_ADD);
//		templateMessage.setTopcolor("#000000");
//		templateMessage.setTouser(toUserName);
//		templateMessage.setUrl("");
//		templateMessage.setData(templateMessageItem);
//		
//		TemplateMessageResult templateMessageResult =  MessageAPI.messageTemplateSend(TokenManager.getToken(Constants.APPID), templateMessage);
//		return templateMessageResult;
//	}
	
	/**
	 * @param memberLevel
	 * 			会员等级：分为一级、二级、宾妃
	 * @param memberNickName
	 * 			会员昵称
	 * @param goodsInfo
	 * 			商品信息
	 * @param money
	 * 			下单金额
	 * @param paymentType
	 * 			付款状态
	 * @param remark
	 * 			备注
	 * @param toUserName
	 * 			被发消息的用户openid
	 * @return
	 */
	public static TemplateMessageResult sendNewOrderTemplateMessage(String memberLevel,String memberNickName, String goodsInfo, String money, String paymentType, String remark1,String toUserName,PayOrder payOrder){
		LinkedHashMap<String, TemplateMessageItem> templateMessageItem = new LinkedHashMap<String, TemplateMessageItem>();
		
		String tempLevelString = "";
		if (ObjValid.isValid(memberLevel)) {
			tempLevelString = "的【"+memberLevel+"】"+memberNickName;
		}
		//计算商品总数量
		long productAmount = 0;
		Set<OrderInfo> set = new HashSet<OrderInfo>();  
		set = payOrder.getOrderInfos();
		for(OrderInfo o:set){
			productAmount = productAmount + o.getAmount();
		}
		// 快递方式（未支付：未备货，已支付：备货中）
		String payStatus = null;  //支付状态
		String express = null;    //快递方式（未支付：未备货，已支付：备货中）
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String timeStr = formatter.format(payOrder.getCreateTime());
		if(ObjValid.isValid(payOrder.getPayStatus())&&payOrder.getPayStatus().equals("1")){
			payStatus = "并且成功支付";
			express = "备货中";
		}else{
			payStatus = "但是尚未支付";
			express = "未备货";
		}
		
		TemplateMessageItem templateMessageItem1 = new TemplateMessageItem("您"+tempLevelString+"已成功下单,"+payStatus, "#000000");
		TemplateMessageItem templateMessageItem2 = new TemplateMessageItem(goodsInfo, "#000000");
		TemplateMessageItem templateMessageItem3 = new TemplateMessageItem(String.valueOf(productAmount), "#000000");
		TemplateMessageItem templateMessageItem4 = new TemplateMessageItem((String.valueOf(payOrder.getProductAmount())), "#000000");
		TemplateMessageItem templateMessageItem5 = new TemplateMessageItem(timeStr, "#000000");
		TemplateMessageItem templateMessageItem6 = new TemplateMessageItem(express, "#000000");
		TemplateMessageItem remark = new TemplateMessageItem("感谢您对百丽春平台的支持~", "#000000");
		templateMessageItem.put("first", templateMessageItem1);
		templateMessageItem.put("keyword1", templateMessageItem2);
		templateMessageItem.put("keyword2", templateMessageItem3);
		templateMessageItem.put("keyword3", templateMessageItem4);
		templateMessageItem.put("keyword4", templateMessageItem5);
		templateMessageItem.put("keyword5", templateMessageItem6);
		templateMessageItem.put("remark", remark);
		
		TemplateMessage templateMessage = new TemplateMessage();
		templateMessage.setTemplate_id(Constants.TEMPLATE_ORDER_ADD);
		templateMessage.setTopcolor("#000000");
		templateMessage.setTouser(toUserName);
		templateMessage.setUrl("");
		templateMessage.setData(templateMessageItem);
		
		TemplateMessageResult templateMessageResult =  MessageAPI.messageTemplateSend(TokenManager.getToken(Constants.APPID), templateMessage);
		return templateMessageResult;
	}
}
