<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/jsps/modules/top.jsp"%>

<script type="text/javascript">
   //加载更多商品
    var productNum=1;
	function moreProduct(obj){
	    $(this).html("加载中...");
	    ++productNum;
	   ajaxMoreProduct(productNum,$(".product-link-url").val());
   }
			

 //更多商品ajax
	 function ajaxMoreProduct(pageStart,linkUrl){
		 $.ajax({ 
	      	url:"ajaxepProductActionFindRepertory.htm?"+linkUrl,      
	      	type:"post", 
	      	dataType: "json",
	      	data:{
	      		"pageNo":pageStart,
	      		"pageSize":10
	      	},
			success: function(json) {
			 $(".more-product").empty();
				if (null!=json&&""!=json) {	
				var str="";					
				$.each(json, function(index, productColumn) {
				    str+="<section class='w-3-l'><a href='navigation.htm?columnId="+productColumn.enterpriseColumn.id+"&newsId="+productColumn.mcProduct.id+"&columnType=product'><img src='${uploadPath}/"+productColumn.mcProduct.imgurl+"' class='img-50'></a>";
					str+="<a>立即购买<section class='circle01'>&nbsp;</section></a><span>"+productColumn.mcProduct.shopPrice+"</span>RMB</section>"; 
		        });	
		        str+="<div class='more-product' style='line-height:3.8em;text-align: center;' onClick='moreProduct(this)'>更多</div>"
		        $(".web03_content").append(str); 	     							
			  }else{
			      var str="<div class='more-product' style='line-height:3.8em;text-align: center;'>没有更多商品</div>";
			       $(".web03_content").append(str);
			  }
			  $(".loading").hide();					  
			 },
			 error: function(XMLHttpRequest, textStatus, errorThrown) {
			      openwaring("网络出现异常");
			      $(".loading").hide();
			   }
		});	
		}
</script>		

	<body class='store'>
  <!-- header -->
		<header class='clearfix' id="clearfix">
			<img src="${imagePath}/logo.png" alt="logo">
		</header>
		<!-- header end -->
		<!-- content header -->
		<section class="shop-header">
			<section id="shop-header">
			<a style="color:#000;" href="epProductAction.htm?columnId=${columnId}&pageType=product&ancestorId=${ancestorId}&orderType=addTime">最新产品</a>
			</section>
			<section>价格 
			<c:if test="${empty orderBy || not empty orderType}">
				<a href="epProductAction.htm?columnId=${columnId}&pageType=product&ancestorId=${ancestorId}&orderType=price&orderBy=asc">
				<img src="${imagePath}/shopping02_tb03.png">
				</a>
			</c:if>
			<c:if test="${orderBy eq 'asc'  && orderType eq 'price'}">
				<a href="epProductAction.htm?columnId=${columnId}&pageType=product&ancestorId=${ancestorId}&orderType=price&orderBy=desc">
				<img src="${imagePath}/shopping02_tb01.png">
				</a>
			</c:if>
			<c:if test="${orderBy eq 'desc'  && orderType eq 'price'}">
				<a href="epProductAction.htm?columnId=${columnId}&pageType=product&ancestorId=${ancestorId}&orderType=price&orderBy=asc">
				<img src="${imagePath}/shopping02_tb02.png">
				</a>
			</c:if>
			</a>
			</section>
			<section>销量 
			<c:if test="${empty orderBy || not empty orderType}">
				<a href="epProductAction.htm?columnId=${columnId}&pageType=product&ancestorId=${ancestorId}&orderType=saleVolume&orderBy=asc">
				<img src="${imagePath}/shopping02_tb03.png">
				</a>
			</c:if>
			<c:if test="${orderBy eq 'asc' && orderType eq 'saleVolume'}">
				<a href="epProductAction.htm?columnId=${columnId}&pageType=product&ancestorId=${ancestorId}&orderType=saleVolume&orderBy=desc">
				<img src="${imagePath}/shopping02_tb01.png">
				</a>
			</c:if>
			<c:if test="${orderBy eq 'desc' && orderType eq 'saleVolume'}">
				<a href="epProductAction.htm?columnId=${columnId}&pageType=product&ancestorId=${ancestorId}&orderType=saleVolume&orderBy=asc">
				<img src="${imagePath}/shopping02_tb02.png">
				</a>
			</c:if>
			</section>
			<section>口碑 
			<c:if test="${empty orderBy || not empty orderType}">
				<a href="epProductAction.htm?columnId=${columnId}&pageType=product&ancestorId=${ancestorId}&orderType=grade&orderBy=asc">
				<img src="${imagePath}/shopping02_tb03.png">
				</a>
			</c:if>
			<c:if test="${orderBy eq 'asc' && orderType eq 'grade'}">
				<a href="epProductAction.htm?columnId=${columnId}&pageType=product&ancestorId=${ancestorId}&orderType=grade&orderBy=desc">
				<img src="${imagePath}/shopping02_tb01.png">
				</a>
			</c:if>
			<c:if test="${orderBy eq 'desc' && orderType eq 'grade'}">
				<a href="epProductAction.htm?columnId=${columnId}&pageType=product&ancestorId=${ancestorId}&orderType=grade&orderBy=asc">
				<img src="${imagePath}/shopping02_tb02.png">
				</a>
			</c:if>
			</section>
		</section>
		<!-- content header end -->
		
		<!-- content -->
		<section class="shop-center">
			<p id="shop-center-p">&nbsp;</p>
			<!-- 产品列表 -->
			<section class="shopping-one">
					<ul class="shopping-one-ul">
					<c:forEach items="${secondEnterpriseColumns}" var="column">
						<li>
								<a class="sh-a1" <c:if test="${empty column.subColumnList}">href="epProductAction.htm?columnId=${column.id}&pageType=product&ancestorId=${ancestorId}"</c:if> >
								${column.names}
								</a>
								
								<ul class="shopping-two">
									<c:forEach items="${column.subColumnList}" var="bean">
										<a href="epProductAction.htm?columnId=${bean.id}&pageType=product&ancestorId=${ancestorId}">
										<li class="shopping-two-color">${bean.names}</li>
										</a>
									</c:forEach>
								</ul>
						</li>
					</c:forEach>
					</ul>
			</section>
			<!-- 产品列表 end -->
			<!-- 产品详情 -->
			<section class="web03_content">
					<img src="${uploadPath}/${selectColumn.pic1}" width="100%"><br>
					<c:forEach items="${productColumns}" var="bean">
					<section class="w-3-l">
						<a href="navigation.htm?columnId=${bean.enterpriseColumn.id}&newsId=${bean.mcProduct.id}&columnType=product"><img src="${uploadPath}/${bean.mcProduct.imgurl}" class="img-50"></a>
						<a>立即购买<section class="circle01">&nbsp;</section></a><span><fmt:formatNumber value="${bean.mcProduct.shopPrice}" pattern="#"/></span>RMB
					</section>
					</c:forEach>
					<div class='more-product' style="line-height:3.8em;text-align: center;" onClick="moreProduct(this)">更多</div>
			</section>
			<!-- 产品详情 end -->	
			<!-- 隐藏域 -->	
			<input type="hidden" class="product-link-url" value="columnId=${columnId}&pageType=product&ancestorId=${ancestorId}&orderType=${orderType}&orderBy=${orderBy}"/>	
		</section>
		<!-- content end -->
 		<%@ include file="/jsps/modules/copyRight.jsp"%>
