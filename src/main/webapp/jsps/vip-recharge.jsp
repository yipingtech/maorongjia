<%@ page contentType="text/html;charset=utf-8"%>

<%@ include file="/jsps/modules/top.jsp"%>
<script type="text/javascript">
$(function(){
   $("#sure-recharge").click(function(){
      var rechargeAmount=$("#recharge-count").val();  
     if(rechargeAmount==null||rechargeAmount==""){
       alert("请输入金额");
      }
     else{
       $("#sure-recharge").attr("type","submit");
   }
   });
});
</script>
<body class="body-eee">
<header class='clearfix close-bottom' >
		<a href="javascript:history.back();"><img src="${imagePath}/add-left.png" alt="" id="close-bottom-img"></a>
		会员充值
</header>
<section class="vip-recharge">
	<form action="epMemberCenterAction!editRecharge.action" method="post">
    	<section class="clearfix">
        	<p class="border-none">
        		<label class="recharge-count" for="recharge-count">金额：（元）</label><input type="text" id="recharge-count" name="rechargeInfo.rechargeAmount" placeholder="请填写充值金额"><input type="button" id="sure-recharge" value="确认充值">
        	</p>
        </section>
        <section class="clearfix">
        	<h3>充值方式</h3>
        	 <p class="border-none"><img src="${imagePath}/weixin.png"><label for="wx-wallet">&nbsp;&nbsp;微信钱包</label><img class="recharge-img" src="${imagePath}/recharge01.png" alt=""></p>
        </section>
        <section class="clearfix">
        	<p><img src="${imagePath}/zhifubao.png"><label for="zhifubao">&nbsp;&nbsp;支付宝</label><img src="${imagePath}/recharge02.png" class="recharge-img" alt=""></p>
        	<p class="border-none"><img src="${imagePath}/bank-card.png"><label for="bank-card">&nbsp;&nbsp;银行卡</label><img class="recharge-img" src="${imagePath}/recharge02.png" alt=""></p>
        </section>
        <input type="hidden" name="rechargeInfo.rechargeComments" value="0">
	</form>
    
</section>
<section class="recharge-tips">
    <h3>充值优惠：</h3>
    <p>1.一次性充值200元送20元；</p>
    <p>2.一次性充值2000元送300元；</p>
    <p>3.一次性充值10000元送2000元；</p>
    <h3>温馨提示：</h3>
    <p>1.储值金额永久有效且适用于谐达微信商城，赠送金额不予退款；</p>
    <p>2.在线充值及赠送金额不提供发票，按每次时间消费金额在消费门店开剧发票；</p>
</section>
<script src="${jsPath}/style.js"></script>
</body>
</html>