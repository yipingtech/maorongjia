<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE HTML>
<html lang="zh-cn">
<head>
    <meta charset="utf-8">
    <meta name="Keywords" content="">
    <meta name="description" content="">
    <meta name="author" content="Christina">
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no,width=device-width,minimum-scale=1.0,maximum-scale=1.0" />
    <!--设置视窗宽为设备宽度，默认不缩放，不允许用户缩放。-->
    <meta name="format-detection" content="telephone=no,email=no"/>
    <!--忽略电话号码和邮箱识别-->
    <meta name="apple-mobile-web-app-capable" content="yes" />
    <!-- 在iOS中 隐藏工具栏和菜单栏。-->
    <meta name="apple-mobile-web-app-status-bar-style" content="black" />
    <!--顶部状态栏(手机信号、时间、电池)的背景颜色。默认值为default（白色），可以定为black（黑色）和black-translucent（灰色半透明-->
    <title>授权代理商</title>
    <link href="${imagePath}/style.css" rel="stylesheet" type="text/css">
    <link href="${imagePath}/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="${imagePath}/video-js.css" rel="stylesheet" type="text/css">
    <script src="${jsPath}/jquery-1.11.2.min.js"></script>
    <script src="${jsPath}/bootstrap.min.js"></script>
    <script src="${jsPath}/TouchSlide.1.1.js"></script>
    <script src="${jsPath}/video.js"></script>
    <script src="${jsPath}/layer.m/layer.m.js"></script>
    <script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
</head>
<body style="background: #eeeeee;">
<script type="text/javascript">
     function recharge(){
    	 $('.sure_btn').text('提交中...');
    	 $('.sure_btn').removeAttr('onclick');
    	 $.ajax({ 
	      		url:'ajaxEpPayAction!payAgent.htm?math='+Math.random(), 
	      		type:'post', 
	      		success:function(data){		
	      				var obj = eval('(' + data.json + ')');
	      				console.log(data.json);
	          			WeixinJSBridge.invoke('getBrandWCPayRequest',obj,function(res){
	          				switch (res.err_msg){ 
	          	            case 'get_brand_wcpay_request:ok':  
	          	            	location.href = "epMemberCenterAction.action";
	          					break;
	          	            case 'get_brand_wcpay_request:cancel': 
	          	            	location.href = "epMemberCenterAction.action";
	          	                break; 
	          	            case 'get_brand_wcpay_request:fail': 
<%--	          	            	alert(res.err_msg);--%>
	          	                break; 
	          	        	}
	          			});
	      		},
			    error: function(XMLHttpRequest, textStatus, errorThrown) {
			       //alert("网络出现异常");
			    }
			});
     }
</script>
<div id="container">
    <div class="recharge_money">
        <form action="#" method="post">
            <div class="input_money"><label for="money">金额（元）</label><input type="text" id="money" readonly="readonly" placeholder="${parameterSet.agentAmount}"></div>
            <a href="#" class="sure_btn" onclick="recharge();">确认充值</a>
        </form>
    </div>
    <div class="recharge_way">
        <div class="recharge_title">充值方式</div>
        <div class="clearfix">
            <div class="left"><img src="${imagePath}/wechat.png" class="wechat">微信钱包</div>
            <img src="${imagePath}/select.png" class="select right">
        </div>
    </div>
    <div class="warm_tips">
        <p>温馨提示：</p>
        <p>谐达平台代理商授权费为10000元人民币，享受付费起之后70年代理商特权，如下：</p>
        <p>1、成为代理商后，享有推荐其他人成为代理商的资格；</p>
        <p>2、代理商获得谐达平台线上特许经营权；</p>
        <p>3、代理商获得谐达平台线下特许经营权；</p>
        <p>4、代理商直接享受优惠购买折扣，不再需要首批购货满3万元。线下需要展柜等配送要求的，每次仍需要购物满5万元；</p>
        <p>5、推荐方式为直接推荐，一级推荐人获得40%的推荐佣金，即4000元人民币。付款3个工作日后，即可提现申请，申请后7天内到账；</p>
        <p>6、代理商正常享受原推荐渠道三级比例佣金，额外享受直接和间接推荐的所有会员消费金额5%的佣金；</p>
        <p>7、被推荐人成为代理商后，推荐人获得直接推荐佣金，不再享受被推荐人渠道带来的收益；</p>
        <p>8、不定期赠送给代理商精美礼品；</p>
        <p>9、必须拥有代理商授权资格后，线下实体店加盟才能享受优惠购货折扣；</p>
        <p>10、享受其他暂未详明和未来增加项目的代理授权或优惠。</p>
    </div>
</div>

<script type="text/javascript">
    $(document).ready(function (e) {
        resize_lv1();
    });
    function resize_lv1() {
        var width_lv1 = $(window).width();
        $(".pic>img").css("height", width_lv1 * 0.61165);
    }
    $(window).resize(function () {
        resize_lv1();
    });
    $(document).ready(function (e) {
        resize_lv2();
    });
    function resize_lv2() {
        var width_lv2 = $(".nav>a").width();
        $(".nav>a,.nav>a img").css("height", width_lv2 * 1.0);
    }
    $(window).resize(function () {
        resize_lv2();
    });
    $(document).ready(function (e) {
        resize_lv3();
    });
    function resize_lv3() {
        var width_lv3 = $(".index_product>a").width();
        $(".index_product>a img").css("height", width_lv3 * 0.970588);
    }
    $(window).resize(function () {
        resize_lv3();
    });
</script>
</body>
</html>