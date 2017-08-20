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
    <script type="text/javascript">
	    $(function(){
	    	 <c:if test="${not empty configJson}">
		    	var configJson = ${configJson};
		     	wx.config(configJson);
		     	wx.ready(function () {
		             wx.onMenuShareAppMessage({
		                 title: "${enterpriseNews.title}",
		                 link: "${shareUrl}",
		                 imgUrl: "http://weixin.cppai.com/upload/erwei.jpg",
		                 desc: "${enterpriseNews.priKey}",
		                 trigger: function (res) {
		                    // alert('用户点击发送给朋友');
		                 },
		                 success: function (res) {
		                     //alert('已分享');
		                 },
		                 cancel: function (res) {
		                     //alert('已取消');
		                 },
		                 fail: function (res) {
		                     //alert(JSON.stringify(res));
		                 }
		             });
		             
		            
		         });
		         wx.error(function (res) {
<%--		             alert('wx.error: '+JSON.stringify(res));--%>
		         });
	   	    </c:if>
	    });
    
    </script>
</head>
<body>
<!--header-->
<header class="top-header">

</header>
<!--end of header-->

<!--container begin-->

<!-- <script>
$(document).ready(function(){
	uParse('.news_detail', {
	    rootPath: '${ctx}/ueditor/'
	});
});
</script> -->
<section id="container">
<c:if test="${enterpriseNews.isOnlyContent==0}">
    <section class="soft-text-detail-wrap">
        <%-- <section class="detail-top">
            <h3>${enterpriseNews.title}</h3>
            <p><fmt:formatDate value="${enterpriseNews.editeTime}" pattern="yyyy-MM-dd"/><a href="epNewsMoreAction!webchat.action">谐达</a></p>
        </section> --%>
        <section class="detail-info">
            <section>
<%--             <h3>${enterpriseNews.title}</h3> --%>
                <p>${enterpriseNews.contents}</p>
            </section>
        </section>
        <%-- <%@ include file="/jsps/code.jsp"%> --%>
    </section>
</section>
</c:if>
<!--container end-->
<div class="clear"></div>
<%@ include file="/jsps/modules/copyRight.jsp"%>



