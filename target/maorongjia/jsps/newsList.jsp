<!DOCTYPE html>
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
    <%@ include file="/common/taglibs.jsp"%>
    <title>软文列表</title>
    <link href="${imagePath }/style.css" rel="stylesheet" type="text/css">
    <link href="${imagePath }/bootstrap.min.css" rel="stylesheet" type="text/css">
    <script src="${jsPath }/jquery-1.11.2.min.js"></script>
    <script src="${jsPath }/bootstrap.min.js"></script>
</head>
<body>
	<script type="text/javascript">
  $(function(){
 	 <c:if test="${not empty configJson}">
	    	var configJson = ${configJson};
	     	wx.config(configJson);
	     	wx.ready(function () {
	             wx.onMenuShareAppMessage({
	                 title: "谐达 ",
	                 link: "${shareUrl}",
	                 imgUrl: "http://weixin.cppai.com/upload/erwei.jpg",
	                 desc: "",
	                 trigger: function (res) {
	                     //alert('用户点击发送给朋友');
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
	             
	             wx.onMenuShareTimeline({
	             	 title: "谐达",
	                  link: "${shareUrl}",
	                  imgUrl: "http://weixin.cppai.com/upload/erwei.jpg",
	                  trigger: function (res) {
<%--	                      alert('用户点击发送给朋友圈');--%>
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
<%--	             alert('wx.error: '+JSON.stringify(res));--%>
	         });
   	    </c:if>
    });
    
    </script>
<!--header-->
<header class="top-header">
    <section class="logo"> 
        <a href="javascript:void(0);"><img src="${ctx}/upload/enterprice/${standbyList.bgphoto3}" alt="logo"></a>
    </section>
</header>
<!--end of header-->

<!--container-->
<section id="container">
    <section class="soft-text-list-wrap">
        <c:forEach var="bean" items="${topMap.newsList.enterpriseNewses}">
           <section class="soft-text-list clearfix">
	            <a href="${ctx}/navigation.htm?columnType=news&newsId=${bean.id}&fatherId=${member.id}">
	                <section class="list-state">
	                    <h3>
	                        ${bean.title }
	                    </h3>
	                    <p>${bean.shortMeta}</p>
                        <i><fmt:formatDate value="${bean.editeTime}" pattern="yyyy-MM-dd"/></i>
	                </section>
	                <c:if  test="${!empty bean.photo }">
	                  <section class="list-img"><img src="${uploadPath}/${bean.photo}"></section>
	                </c:if>
	            </a>
	        </section>
        </c:forEach>
        
</section>
<!--end of container-->
<%@ include file="/jsps/modules/copyRight.jsp"%>