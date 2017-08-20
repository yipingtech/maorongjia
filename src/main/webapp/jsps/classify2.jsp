<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/jsps/modules/top1.jsp"%>

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
				<section class="weui_tab_bd back-white" id="weui_tab_bd_bottom">

				<section class="weui_cell pd-0 flex-start">
					<!--左侧导航栏开始-->
					<div class="weui_cell_primary tab-left back-white">
						<ul>
						<c:forEach items="${indexInfoBean.enterpriseColumnList }" var="bean" >
							<li class="tab-li tab-active">
								<a href="epIndexAction!findProductBySeries.action?selectSecondLevelId=${bean.id }" 
									<c:if test="${bean.id eq selectSecondLevelId }">style="color: #f08300;"</c:if>>
									${bean.names }
								</a>
							</li>
						</c:forEach>	
							<li class="clear"></li>
						</ul>
					</div>
						<!--左侧导航栏结束-->
					<!--右侧内容开始-->
					<div class="weui_cell_ft right-content">
						<div class="right-photo">
							<%-- <img src="${imagePath}/ban1.jpg"/> --%>					
							<img src="${ctx}/upload/enterprice/${column0.pic1}"/>
							
						</div>
						<%-- <p class="right-title">系列</p>
					<ul class="classify-box">
							<c:forEach items="${indexInfoBean.enterpriseColumnList[index].subColumnList }" var="bean1">
								<li class="classify-li">
									<a href="epIndexAction!findProductBySeries.action?id=${bean1.id }&selectSecondLevelId=${selectSecondLevelId }">
										${bean1.names }
									</a>
								</li>
							</c:forEach>
					<li class="clear"></li>
					</ul> --%>
					
					<p class="right-title">产品</p>
					<ul class="product-box">
						<c:forEach items="${indexProductInfos }" var="bean3">
								<li class="product-card">
									<a href="${ctx}/epProductAction!tampons.action?id=${bean3.mcProduct.id }&fatherId=${sessionScope.member.id}">
										<div class="card-photo">
												<img src="${uploadPath }/${bean3.mcProduct.para12 }" />
										</div>
										<p class="two-more">${bean3.mcProduct.title }</p>
									</a>
								</li>
						</c:forEach>
					<li class="clear"></li>
					</ul>
					</div>
					<!--右侧内容结束-->
						</section>
			      </section>
				  <!-- scroll end -->
    <script src="${jsPath}/style.js"></script>
    <%@ include file="/jsps/modules/footer1.jsp"%>  