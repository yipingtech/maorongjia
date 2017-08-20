<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE HTML>
<html lang="zh-cn"><!--<![endif]-->
<head>
    <!--meta.jsp-->
    <meta charset="utf-8">
    <meta name="Keywords" content="">
    <meta name="description" content="">
    <meta name="author" content="candyxue">

    <meta name="viewport" content="initial-scale=1.0, user-scalable=no,width=device-width,minimum-scale=1.0,maximum-scale=1.0" />

    <meta name="format-detection" content="telephone=no,email=no"/>
    <meta name="apple-mobile-web-app-capable" content="yes" />
    <meta name="apple-mobile-web-app-status-bar-style" content="black" />
    

    
	<link rel="icon" href="favicon.ico"><!--客户有需要时添加，ico图标-->
    <title>百丽春</title>
    <link rel="stylesheet" href="${imagePath}/style2.css">
    <link rel="stylesheet" href="${imagePath}/style3.css">
    <link rel="stylesheet" type="text/css" href="${imagePath}/style.css">
    <link rel="stylesheet" href="${imagePath}/font-awesome.min.css">
    <link rel="stylesheet" href="${imagePath}/swiper.min.css">
	<link rel="stylesheet" type="text/css" href="${imagePath}/weui.min.css">
    <link rel="stylesheet" type="text/css" href="${imagePath}/jquery-weui.css">
    <link rel="stylesheet" type="text/css" href="${imagePath}/bill-style.css">
	<link href="${ctx}/resource/validate/jquery.validate.css" type="text/css" rel="stylesheet" />
    <script src="${jsPath}/swiper.min.js"></script>
    <script src="${jsPath}/jquery-1.11.2.min.js"></script>
    <script src="${ctx}/resource/validate/jquery.validate.min.js" type="text/javascript"></script>
	<script src="${ctx}/resource/validate/messages_cn.js" type="text/javascript"></script>
    <script src="${jsPath}/jquery.cookie.js"></script>
	<script src="${jsPath}/TouchSlide.1.1.js"></script>
	<script src="${jsPath}/layer.m/layer.m.js"></script>
    <link href="${imagePath}/video-js.css" rel="stylesheet" type="text/css">
    <script src="${jsPath}/bootstrap.min.js"></script>
    <script src="${jsPath}/video.js"></script>
    <script src="${jsPath}/common.js"></script>
    <script src="${jsPath}/ie10-viewport-bug-workaround.js"></script>
    <script src="${jsPath}/TouchSlide.1.1.source.js"></script>
    <script src="${jsPath}/jquery.lazyload.min.js"></script>
    <script src="${jsPath}/style.js"></script>
    <!--[if lt IE 9]>
    <script src="${jsPath}/html5shiv.min.js"></script>
    <script src="${jsPath}/respond.min.js"></script>
    <![endif]-->
    <noscript>您的浏览器不支持JavaScript，请启动JavaScript或更换浏览器</noscript>
    <!--end of meta.jsp-->
    <!-- <script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script> -->
    <script type="text/javascript">
	    var isNull = function isNull(obj){
	    	if(obj==null || obj=='' || obj=='undifined' ){
	    		return true;
	    	}else{
	    		return false;
	    	}
	    }
	    
	    var layerOpen = function layerOpen(msg, url){
		    layer.open({
				shadeClose: false,
				content: '<section class="buy-layer"><p><span>'+msg+'</span><a href="'+url+'" onclick="evaluate_close()"><img src="${imagePath}/evaluate_su.png" id="pagebtn"></a></p></section>'
			});
	    }
	    
	    function evaluate_close(){
			layer.closeAll();
		} 
    </script>

	 <style type="text/css">
		body{font-size: 14px;font-family: "微软雅黑";height: 100%;width: 100%;line-height: 1.6;}
		.body-gary{background: #EEEEEE;}
		p {-webkit-margin-before: 0; -webkit-margin-after: 0;-webkit-margin-start: 0;-webkit-margin-end: 0;}
		a{color: #333333;text-decoration:none;}
		a img{border:0}
		* { margin: 0;padding: 0;}
		.weui_tab_bd{box-sizing:border-box;height:100%;padding-bottom:55px;overflow:auto;-webkit-overflow-scrolling:touch}
		.weui_tabbar{display:-webkit-box;display:-webkit-flex;display:flex;position:absolute;z-index:500;bottom:0;width:100%;background-color:#f7f7fa}
		.weui_tabbar_item{display:block;-webkit-box-flex:1;-webkit-flex:1;flex:1;padding:7px 0 0;-webkit-tap-highlight-color:transparent}
		.weui_tabbar_icon {   margin: 0 auto;  width: 30px;height: 30px;}
		.weui_tabbar_icon+.weui_tabbar_label{ margin-top: 5px;}
		.weui_tabbar_label {text-align: center;color: #888;font-size: 12px;}
		#weui_tab_bd_bottom{padding-bottom: 80px;}
	</style>
</head>
<!--top.jsp end-->