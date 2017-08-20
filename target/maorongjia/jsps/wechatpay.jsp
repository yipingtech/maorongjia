<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/jsps/modules/top.jsp"%>
<script type="text/javascript">
$(function(){
<%--	var obj = eval('(' + data.json + ')');--%>
var json = ${json};
$("#pay").click(function(){
	WeixinJSBridge.invoke('getBrandWCPayRequest',json,function(res){
		switch (res.err_msg){ 
         case 'get_brand_wcpay_request:ok':   
        	 alert("支付成功");
			//location.href = "epMallAction!completeBuying.action?order_id="+data.order_id;
			//location.href = "epMallAction!findOrder.action";
			break;
         case 'get_brand_wcpay_request:cancel':   
        	 alert("取消支付");
             break; 
         case 'get_brand_wcpay_request:fail': 
         	alert(res.err_msg);
             break; 
     	}
	});
	
});
});


</script>
  <body>
    <a href="javascript:void(0);" id="pay">微信支付功能尚未完成. </a><br>
  </body>
</html>
