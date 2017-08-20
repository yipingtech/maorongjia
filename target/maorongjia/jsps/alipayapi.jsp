<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.alipay.direct.config.*,cc.modules.util.DateHelper"%>
<%@ page import="com.alipay.direct.util.*"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.Map"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>支付宝即时到账交易接口</title>
	</head>
	<%
		////////////////////////////////////请求参数//////////////////////////////////////

	

		//订单名称
		//String subject = request.getParameter("WIDsubject").toString();
		String subject = (String) session.getAttribute("WIDsubject");
		
		//必填
		//System.err.println("subject=="+subject);

		//付款金额
		//String total_fee = new String(request.getParameter("WIDtotal_fee").getBytes("utf-8"));
		String total_fee = session.getAttribute("WIDtotal_fee").toString();
		//必填

		//订单描述

		//String body = request.getParameter("WIDbody").toString();
		String body = "";
		
		//String extra_common_param = request.getParameter("playerNo").toString();
		String extra_common_param = "";
		
		//需以http://开头的完整路径，例如：http://www.xxx.com/myorder.html

		//防钓鱼时间戳
		String anti_phishing_key = "";
		//若要使用请调用类文件submit中的query_timestamp函数

		//客户端的IP地址
		String exter_invoke_ip = "";
		//非局域网的外网IP地址，如：221.0.0.1
		String out_trade_no = (String) session.getAttribute("WIDsubject");
		
		//////////////////////////////////////////////////////////////////////////////////
		if(AlipayConfig.partner.trim().equals("") || AlipayConfig.seller_mail.trim().equals("")){
			out.println("<script>alert('OMG,出错了！卖家信息不能为空！');history.go(-1);</script>");
		}
		
		//把请求参数打包成数组
		Map<String, String> sParaTemp = new HashMap<String, String>();
		sParaTemp.put("service", AlipayConfig.alipay_services);
        sParaTemp.put("partner", AlipayConfig.partner);
        sParaTemp.put("_input_charset", AlipayConfig.input_charset);
        sParaTemp.put("sign_type", AlipayConfig.sign_type);
        //Business
        sParaTemp.put("out_trade_no", out_trade_no);
		sParaTemp.put("payment_type", AlipayConfig.payment_type);
		sParaTemp.put("notify_url", AlipayConfig.notify_url);
		sParaTemp.put("return_url", AlipayConfig.return_url);
		sParaTemp.put("seller_email", AlipayConfig.seller_mail);
		
		sParaTemp.put("subject", subject);
		sParaTemp.put("total_fee", total_fee);
		sParaTemp.put("body", body);
		sParaTemp.put("show_url", AlipayConfig.show_url);
		sParaTemp.put("anti_phishing_key", anti_phishing_key);
		sParaTemp.put("exter_invoke_ip", exter_invoke_ip);
		sParaTemp.put("extra_common_param", extra_common_param);
		
		//建立请求
		String sHtmlText = AlipaySubmit.buildRequest(sParaTemp,"get","确认");
		out.println(sHtmlText);
	%>
	<body>
	</body>
</html>
