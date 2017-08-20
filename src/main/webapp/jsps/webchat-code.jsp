<%@ page contentType="text/html;charset=utf-8"%>
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
    <title>微信扫码</title>
    <%@ include file="/common/taglibs.jsp"%>
    <link href="${imagePath}/style.css" rel="stylesheet" type="text/css">
    <link href="${imagePath}/bootstrap.min.css" rel="stylesheet" type="text/css">
    <script src="${jsPath}/jquery-1.11.2.min.js"></script>
    <script src="${jsPath}/bootstrap.min.js"></script>
</head>
<body>
<!--header-->
<header class="top-header">

</header>
<!--end of header-->

<!--container-->
<section id="container">
    <section class="soft-text-detail-wrap">
       <%@ include file="/jsps/code.jsp"%>
    </section>
</section>
<!--end of container-->

<!--footer-->
<footer class="bottom-footer">

</footer>
<!--end of footer-->
</body>
</html>