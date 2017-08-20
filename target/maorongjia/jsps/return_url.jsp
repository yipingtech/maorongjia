
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.util.Map"%>
<%@ page import="com.alipay.direct.util.*"%>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@ page import="org.springframework.context.ApplicationContext"%>
<%@page import="cc.messcat.service.pay.PayOrderManagerDao"%>
<%@page import="org.apache.taglibs.standard.tag.common.xml.ForEachTag"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>支付宝页面跳转同步通知页面</title>
	</head>
	<body>
	<%
	//获取支付宝GET过来反馈信息
	Map<String,String> params = new HashMap<String,String>();

	Map requestParams = request.getParameterMap();
	
	for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
		String name = (String) iter.next();
		String[] values = (String[]) requestParams.get(name);
		String valueStr = "";
		for (int i = 0; i < values.length; i++) {
			valueStr = (i == values.length - 1) ? valueStr + values[i]
					: valueStr + values[i] + ",";
		}
		//乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
	
		params.put(name, valueStr);
	}
	
	//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
	//商户订单号

	String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("utf-8"));

	//支付宝交易号

	String trade_no = new String(request.getParameter("trade_no").getBytes("utf-8"));

	//交易状态
	String trade_status = params.get("trade_status");
	
	String body = params.get("body");
	String subject = params.get("subject");
	String buyer_email = params.get("buyer_email");
	String buyer_id = params.get("buyer_id");
	String seller_email = params.get("seller_email");
	String seller_id = params.get("seller_id");
	String passId = params.get("extra_common_param");
	String total_fee = params.get("total_fee");

	//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
	//log.info("return_url.jsp:"+trade_status);
	//计算得出通知验证结果
	boolean verify_result = AlipayNotify.verify(params);
	
	if(verify_result){//验证成功
		//////////////////////////////////////////////////////////////////////////////////////////
		//请在这里加上商户的业务逻辑程序代码

		//——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
		if(trade_status.equals("TRADE_FINISHED") || trade_status.equals("TRADE_SUCCESS")){
			//判断该笔订单是否在商户网站中已经做过处理
			//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
			//如果有做过处理，不执行商户的业务程序
			
			
			ServletContext sc=this.getServletConfig().getServletContext(); 
			ApplicationContext ac2 = WebApplicationContextUtils.getWebApplicationContext(sc);
			//GamePlayerManagerDao gamePlayerManagerDao = (GamePlayerManagerDao)ac2.getBean("gamePlayerManagerDao");
			PayOrderManagerDao payOrderManagerDao = (PayOrderManagerDao) ac2.getBean("payOrderManagerDao");
			//结束交易返回处理
			payOrderManagerDao.finishTrade(out_trade_no);
			
		}
		
		//该页面可做页面美工编辑
		//out.println("验证成功<br />");
		//——请根据您的业务逻辑来编写程序（以上代码仅作参考）——epOtherAction.htm?columnId=${3}&amp;pageType=${pageType}&amp;message=${message}
		response.sendRedirect("epMemberCenterAction.htm");

		//////////////////////////////////////////////////////////////////////////////////////////
	}else{
		//该页面可做页面美工编辑
		out.println("验证失败");
		//response.sendRedirect("http://messcats-ip-39.westhost.cn/phoneme/epOtherAction.htm?columnId=3&pageType=notifyUrl&message=fail");
	}
%>
	</body>
</html>