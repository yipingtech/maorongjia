<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/jsps/modules/top1.jsp"%>
 <script type="text/javascript">
 <%--  $(function(){
    /*  $("img.lazy").lazyload({
    	 effect : "fadeIn",
    	 threshold : 200
     }); */
	  
    $("#loadingImg").hide();
 	 <c:if test="${not empty configJson}">
	    	var configJson = ${configJson};
	     	wx.config(configJson);
	     	wx.ready(function () {
	             wx.onMenuShareAppMessage({
	                 title: "谐达平台首页 ",
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
	             	 title: "谐达平台首页",
	                  link: "${shareUrl}",
	                  imgUrl: "http://weixin.cppai.com/upload/erwei.jpg",
	                  trigger: function (res) {
	                      alert('用户点击发送给朋友圈');
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
	             alert('wx.error: '+JSON.stringify(res));
	         });
   	    </c:if>
   	   
    }); --%>
  
	</script> 
<body ontouchstart>
<section class="weui_tab">
	      <!-- navbar start -->
	      <section class="weui_navbar">
	           <section class="search-box">
	           	<img  class="search-icon" src="${imagePath}/sousuo.png"/>
				    <input type="text" value="" class="search-input" placeholder="专用胶" />
	           </section>
	           <a class="search-btn" href="javascript:void(0);">搜索</a>
	      </section>
	      <!-- navbar end -->
	      
	    	 <!-- scroll start -->
			<section class="weui_tab_bd body-gary" id="weui_tab_bd_bottom">
	            <!-- banner start -->
				<section class="swiper-container" id="banner">
					<!-- Slides -->
					 <section class="swiper-wrapper">
				          <c:forEach items="${map.AdvertisementProduct }" var="bean0">
				           	 <section class="swiper-slide">
				        	     	<a href="${bean0.address }">
				        	     		<img src="${uploadPath}/${bean0.photo }" />
				        	     	</a>
				        	 </section>
				         </c:forEach>
			         </section> 
			        <!-- Slides -->
			      <section class="swiper-pagination"></section>
			      <!-- swiper end -->
			    </section>
           
			<!-- banner end -->
				<!-- list start-->
				<div class="page-bd back-white">

						<div class="weui-flex  center-box">
							 <c:forEach items="${map.indexProductTypes.enterpriseColumnList }" var="bean1" begin="0" end="2">
						           <div class="weui-flex-item">
						           	<a href="epIndexAction!findProductBySeries.action?selectSecondLevelId=${bean1.id }">
						           		<img class="center-photo" src="${uploadPath}/${bean1.pic2 }">
						           		<p>${bean1.shortName }</p>
						           	</a>
						           </div>
							 </c:forEach>
						</div>
						
						 
						 <div class="weui-flex  center-box">
					   		 <c:forEach items="${map.indexProductTypes.enterpriseColumnList }" var="bean2" begin="3" end="5">
					            <div class="weui-flex-item">
					            	<a href="epIndexAction!findProductBySeries.action?selectSecondLevelId=${bean2.id }">
					            		<img class="center-photo" src="${uploadPath}/${bean2.pic2 }">
					            		<p>${bean2.shortName }</p>
					            	</a>
					            </div>
							 </c:forEach>
							 
							  <%--   <div class="weui-flex-item">
							        <a href="epIndexAction!findProductClassify.action">
							           	<img class="center-photo" src="${imagePath}/cen6.png">
							           	<p>其他行业</p>
							        </a>
							   </div> --%>
						</div>
				</div>
			<!-- list end -->
			
			<!--热门产品开始-->
			<section>
				  <section class="weui_cell weui_cells_access back-white margin-top5">
						<div class="weui_cell_bd weui_cell_primary">
							<img class="title-icon" src="${imagePath}/good.png" />
							热门产品
						</div>
						<div class="weui_cell_ft">
							<a href="epIndexAction!brandProductList.action?selectColumnId=${map.IndexProduct.enterpriseColumn.id }&name=热门产品">更多</a>
						</div>
				 </section>
				
					<ul class="padding15 back-white">
					
						<c:forEach var="productRoll" items="${map.IndexProduct.mcProductInfoList}" begin="0" end="5">
							<li class="product-card">
								<a href="${ctx}/epProductAction!tampons.action?id=${productRoll.mcProduct.id}&fatherId=${sessionScope.member.id}">
									<div class="card-photo">
											<img src="${uploadPath}/${productRoll.mcProduct.para12}" />
									</div>
									<p>
										<c:if test="${fn:length(productRoll.mcProduct.title)<=11}">${productRoll.mcProduct.title }</c:if>
										<c:if test="${fn:length(productRoll.mcProduct.title)>11}">${fn:replace(productRoll.mcProduct.title,fn:substring(productRoll.mcProduct.title,11,fn:length(productRoll.mcProduct.title)),"...")}</c:if>							
									</p>
								</a>
							</li>
						</c:forEach>
						
						<li class="clear"></li>
					</ul>
			</section>
			<!--热门产品结束-->
			
			<!--热门系列开始-->
			<section>
			
				<c:forEach var="bean4" items="${map.indexProductSeries.enterpriseColumnList}" begin="0" end="3">
				    <section class="weui_cell weui_cells_access back-white margin-top5">
							<div class="weui_cell_bd weui_cell_primary lh-13">
								<img class="series-icon" src="${imagePath}/xilie.png" />
								
								${bean4.shortName }				
							</div>
							 <div class="weui_cell_ft">
							 	<a href="epIndexAction!findHotSeries.action?name=热门系列&id=${bean4.id }">更多</a>
							 </div>
					</section>
					<div class="padding15 weui_cell bottom-card">
					
					
					 <c:if test="${not empty bean4.productColumns[0]}">
					        <div class="big-card bigcard-right">
								<a href="${ctx}/epProductAction!tampons.action?id=${bean4.productColumns[0].mcProduct.id}&fatherId=${sessionScope.member.id}">
									<img src="${uploadPath}/${bean4.productColumns[0].mcProduct.para12}"/>
								</a>
								 <div class="product-namebox">
		                         <div class="op-dark2"></div>
		                          <p class="b-product-name">${bean4.productColumns[0].mcProduct.title}</p>
	                             </div>
							</div>	
					</c:if>  
					 <c:if test="${not empty bean4.productColumns[1]}">     					    																					
							<div class="big-card ">
								<a href="${ctx}/epProductAction!tampons.action?id=${bean4.productColumns[1].mcProduct.id}&fatherId=${sessionScope.member.id}">
									<img src="${uploadPath}/${bean4.productColumns[1].mcProduct.para12}"/>
								</a>
								 <div class="product-namebox">
		                         <div class="op-dark2"></div>
		                          <p class="b-product-name">${bean4.productColumns[1].mcProduct.title}</p>
	                             </div>
							</div>  
					</c:if>  	 							 
                          <%--   ${map.indexProductSeries.mcProductInfoList}
					        <div class="weui_cell_primary big-card bigcard-right">
								<a href="${ctx}/epProductAction!tampons.action?id=${map.indexProductSeries.mcProductInfoList[0].mcProduct.id}">
									<img src="${uploadPath}/${map.indexProductSeries.mcProductInfoList[0].mcProduct.para12}"/>
								</a>
								 <div class="product-namebox">
		                         <div class="op-dark2"></div>
		                          <p class="product-name">${map.indexProductSeries.mcProductInfoList[0].mcProduct.title}</p>
	                             </div>
							</div>	
					       					    																					
							<div class="weui_cell_primary big-card ">
								<a href="${ctx}/epProductAction!tampons.action?id=${map.indexProductSeries.mcProductInfoList[1].mcProduct.id}">
									<img src="${uploadPath}/${map.indexProductSeries.mcProductInfoList[1].mcProduct.para12}"/>
								</a>
								 <div class="product-namebox">
		                         <div class="op-dark2"></div>
		                          <p class="product-name">${map.indexProductSeries.mcProductInfoList[1].mcProduct.title}</p>
	                             </div>
							</div>   --%>

					</div>
				</c:forEach>	
					
			</section>
		  <!--热门系列结束-->
		  <!-- scroll end -->
	      </section>
<%@ include file="/jsps/modules/footer.jsp"%>  