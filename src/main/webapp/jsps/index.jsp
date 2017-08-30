<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/jsps/modules/top1.jsp"%>
<body class="body-fixed">
<!--头部-->
<header class="site-header fixed-header">
	<div class="header-wrap">
		<a href="" class="logo"><img src="${imagePath}/maorongjia/logo.png"></a>
		<div class="menu">
			<div class="m-nav">
				<a href="">最新资讯</a>
				<a href="">信用卡</a>
				<a href="">贷款</a>
			</div>
		</div>
	</div>
</header>
<section class="wrap">
	<div class="flexslider">
  		<ul class="slides">
    		<li><img src="${imagePath}/maorongjia/banner.jpg" /></li>
    		<li><img src="${imagePath}/maorongjia/banner2.jpg" /></li>
            <li><img src="${imagePath}/maorongjia/banner3.jpg" /></li>
  		</ul>
   </div>
	<script type="text/javascript">
	$(function() {
	    $(".flexslider").flexslider({
	    	animation:"slide",
			slideshowSpeed: 4000, //展示时间间隔ms
			animationSpeed: 400, //滚动时间ms
			touch: true //是否支持触屏滑动
		});
	});	
	</script>

	<div class="rz-item xt-item">
		<ul class="cl">
			<li>
				<a href="">
					<span class="rz-txt"><font class="r-txt">信用卡</font><br><font>干货</font></span>
					<span class="rz-pic"><img src="${imagePath}/maorongjia/mx_tab1.png"></span>
				</a>
			</li>
			<li>
				<a href="">
					<span class="rz-txt"><font class="y-txt">贷款</font><br><font>干货</font></span>
					<span class="rz-pic"><img src="${imagePath}/maorongjia/mx_tab2.png"></span>
				</a>
			</li>
		</ul>
	</div>
	
	<!-- 最新资讯 -->
	<div class="rz-section">
		<div class="rz-hd">
			<span class="news-icon">最热干货</span>
		</div>
		<div class="news-list">
			<ul>
				<c:forEach var="hot" items="${map.WanDai.enterprisePhotoNewsList}" begin="0" end="5">
							<li>
								<div class="n-pic"><a href="epFrontNewsAction!news.action?selectNewsId=${hot.id}"><img src="${uploadPath}/${hot.photo}"></a></div>
								<div class="auto">
									<div class="tit">
										<a href="epFrontNewsAction!news.action?selectNewsId=${hot.id}" title="">
											<c:if test="${fn:length(hot.title)<=11}">${hot.title }</c:if>
											<c:if test="${fn:length(hot.title)>11}">${fn:replace(hot.title,fn:substring(hot.title,11,fn:length(hot.title)),"...")}</c:if>
										</a>
									</div>
									<div class="desc">
										<c:if test="${fn:length(hot.shortMeta)<=30}">${hot.shortMeta }</c:if>
										<c:if test="${fn:length(hot.shortMeta)>30}">${fn:replace(hot.shortMeta,fn:substring(hot.shortMeta,30,fn:length(hot.shortMeta)),"...")}</c:if>
									</div>
									<div class="meta">
										<span class="pl-ico">${hot.clickTimes}</span>
										<span class="t-ico"><fmt:formatDate value="${hot.editeTime}" pattern="yyyy-MM-dd"/></span>
									</div>
								</div>	
							</li>
				</c:forEach>
			
				<%-- <li>
					<div class="n-pic"><a href=""><img src="${imagePath}/maorongjia/news1.jpg"></a></div>
					<div class="auto">
						<div class="tit"><a href="" title="">“金融科技”将对传统银行影响</a></div>
						<div class="desc">金融科技与互联网金融之间或许还在玩着概念消灭概念的游戏，银行</div>
						<div class="meta">
							<span class="pl-ico">227</span>
							<span class="t-ico">2016-07-17</span>
						</div>
					</div>
				</li> --%>
			</ul>
		</div>
	</div>
</section>

<%@ include file="/jsps/modules/footer_new.jsp"%>  

