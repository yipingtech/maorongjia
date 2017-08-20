<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/jsps/modules/top.jsp"%>

<link href="${imagePath}/bootstrap.min.css" rel="stylesheet" type="text/css">
<script type="text/javascript">
  $(function(){
     $("img.lazy").lazyload({
    	 effect : "fadeIn",
    	 threshold : 200
     });
	  
	 $("#loadingImg").hide();
 	 <c:if test="${not empty configJson}">
	    	var configJson = ${configJson};
	     	wx.config(configJson);
	     	wx.ready(function () {
	             wx.onMenuShareAppMessage({
	                 title: "商品列表",
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
	             	 title: "商品列表",
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

<body class="bg3">
	<div id="container">
		<div id="overFlowContain" style="margin-bottom:13%;">
			<c:forEach var="productRoll" items="${indexProductInfos}">
				<div class="sale_pic describe${status.index}">
					<a href="${ctx}/epProductAction!tampons.action?id=${productRoll.mcProduct.id}&fatherId=${member.id}">
					    <img class="lazy" data-original="${uploadPath}/${productRoll.mcProduct.para12}">
					</a>
					<c:if test="${not empty productRoll.mcProduct.para1 and productRoll.mcProduct.para1 ne '' }">
						 <div>
						    	<i>${productRoll.mcProduct.para1}</i>
						    	<span>点击进入>></span>
						 </div>
						 <script type="text/javascript">
						    function oHeight(){
						    	//var oH = -($(".describe${status.index}>div").height()/2);
						    	$(".describe${status.index}>div span").css('margin-top',"-10.0469px");
						    }
						    oHeight()
						 </script>
					</c:if>
				</div>
			</c:forEach>
		</div>
	</div>
	<div style="text-align: center;margin-bottom:50px;display:none;" id="loadingImg"><img src="${imagePath}/loading.gif"></div>
	<!--container right end-->
	
<%@ include file="/jsps/modules/copyRight.jsp"%>
