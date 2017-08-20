<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html lang="zh-cn">
<head>
    <meta charset="utf-8">
    <meta name="Keywords" content="">
    <meta name="description" content="">
    <meta name="author" content="candyxue">

    <meta name="viewport" content="initial-scale=1.0, user-scalable=no,width=device-width,minimum-scale=1.0,maximum-scale=1.0" />

    <meta name="format-detection" content="telephone=no,email=no"/>
    <meta name="apple-mobile-web-app-capable" content="yes" />
    <meta name="apple-mobile-web-app-status-bar-style" content="black" />
    <title>${enterpriseNews.title}</title>
    <link href="${imagePath }/style.css" rel="stylesheet" type="text/css">
    <link href="${imagePath }/bootstrap.min.css" rel="stylesheet" type="text/css">
    <script src="${jsPath }/jquery-1.11.2.min.js"></script>
    <script src="${jsPath }/bootstrap.min.js"></script>
     <script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
    
</head>
<body class="detail-bg">
<!--header-->
<header class="top-header">

</header>
<!--end of header-->

<!--container begin-->



<section id="container">
    <section class="soft-text-detail-wrap" style="margin-top:300px;">
        <form action="epMemberCenterAction!editOrder.action" method="post">
             <input type="text" name="orderNum" style="border:solid 1px black"/></br>
             <input type="submit" value="提交"/></br>
             <a>此方法用作给未付款的订单进行付款（PS:慎用）</a>
        </form>
    </section>
</section>
<!--container end-->

<div class="clear"></div>

