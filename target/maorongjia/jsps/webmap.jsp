<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="keyword" content="${webSite.title}" />
		<title>${webSite.title}</title>
		<%@ include file="/jsps/modules/useragent.jsp"%>
		<script src="${ctx}/jsps/theme/${webSite.webSkin.filename}/js/jquery-1.4.4.min.js" type="text/javascript"></script>
	<style>
		.text_info{
			font-size:13px;
		}
	</style>
</head>
<body>
	<%@ include file="/jsps/modules/top.jsp"%>
	<div id="container">
	<!--left begin-->
    <div id="nl-left">
       <div id="nl-left1">
       		<div class="header">
            	<div class="icon"></div>
                <div class="title">网站地图</div>
            </div>
            <div class="new_ul">
	            <div class="news_nav">
		                	<div class="news_title">
		                		<a href="${ctx}/epIndexNewsAction!webmap.htm">网站地图</a>
		                	</div>
		                    <div class="news_icon"></div>
			     </div>
      		 </div>
  	  </div>
    </div>
    <!--left end-->
    <!--middle begin-->
    <div id="nl-middle">
    	<div id="nl-middle1">
        	<div class="header">
            	<div class="icon"></div>
                <div class="title">网站地图</div>
                
            </div>
            <div class="news_text" style="text-align:left;">
            	<ul>
                		<li>
                            <div class="text_img"></div>
                            <div class="text_info">
                            	<a href="${ctx}/epIndexNewsAction.htm">首页</a>		
                            </div>
                    	</li>
                    	<c:if test="${not empty map.us}">
                    	<li>
								<div class="text_img"></div>
                           		<div class="text_info">
									<a href="${ctx}/epIndexNewsAction!introduction.htm?epColumnId=${map.us.enterpriseColumn.id}">${map.us.enterpriseColumn.names}</a>
		                		</div>
						</li>
						</c:if>
						<c:if test="${not empty map.ditu}">
						<li>
							<div class="text_img"></div>
                           	<div class="text_info">
								<a
									href="${ctx}/epIndexNewsAction!webmap.htm">${map.ditu.enterpriseColumn.names}</a>
							</div>	
						</li>
						</c:if>
                    	<c:forEach var="webMapBean" items="${webMapBean}" begin="0" end="9">
							<c:if test="${webMapBean.enterpriseColumn.isValidInNav=='1'}">
								<c:if test="${webMapBean.enterpriseColumn.typeColumn.templateType == 'link'}">
									<li>
										<div class="text_img"></div>
										<div class="text_info">
											<a href="${webMapBean.enterpriseColumn.linkUrl}">${webMapBean.enterpriseColumn.names}</a>
										</div>
									</li>
								</c:if>
								<c:if test="${webMapBean.enterpriseColumn.typeColumn.templateType == 'mostlist'}">
									<li style="height:auto;">
										<div class="text_img"></div>
										<div class="text_info">
											<a
											href="${ctx}/epIndexNewsAction!news_list_more.htm?epColumnId=${webMapBean.enterpriseColumn.id}">${webMapBean.enterpriseColumn.names}</a>
										</div>
										<br/>
										<div style="margin-left:30px;">
												<c:forEach var="subcolumn" items="${webMapBean.enterpriseColumnList}">
														<c:if
															test="${subcolumn.typeColumn.templateType == 'list' or subcolumn.typeColumn.templateType == 'download'}">
															<div style="width:200px;float:left;">
																<a
																	href="${ctx}/epIndexNewsAction!news_more.htm?epColumnId=${webMapBean.enterpriseColumn.id}&subColumnId=${subcolumn.id}">${subcolumn.names}</a>
															</div>
														</c:if>
														<c:if test="${subcolumn.typeColumn.templateType == 'content'}">
															<div style="width:200px;float:left;">
																<a
																	href="${ctx}/epIndexNewsAction!news_list_more.htm?epColumnId=${subcolumn.id}">${subcolumn.names}</a>
														
															</div>
														</c:if>
													
												</c:forEach>
										</div>
										<div style="clear:both;"></div>
									</li>
								</c:if>
								<c:if
									test="${webMapBean.enterpriseColumn.typeColumn.templateType == 'list' or webMapBean.enterpriseColumn.typeColumn.templateType == 'download'}">
									<li>
										<div class="text_img"></div>
										<div class="text_info">
										<a
											href="${ctx}/epIndexNewsAction!news_more.htm?epColumnId=${webMapBean.enterpriseColumn.id}">${webMapBean.enterpriseColumn.names}</a>
										</div>
										<br/>
										<div style="margin-left:30px;">
												<c:forEach var="subcolumn" items="${webMapBean.enterpriseColumnList}">
													<span style="width:190px;float:left;">
														<c:if
															test="${subcolumn.typeColumn.templateType == 'list' or subcolumn.typeColumn.templateType == 'download'}">
															
																<a
																	href="${ctx}/epIndexNewsAction!news_more.htm?epColumnId=${webMapBean.enterpriseColumn.id}&subColumnId=${subcolumn.id}">${subcolumn.names}</a>
																
															&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
														</c:if>
													</span>
													<span style="width:190px;float:left;">
														<c:if test="${subcolumn.typeColumn.templateType == 'content'}">
															
																<a
																	href="${ctx}/epIndexNewsAction!news_list_more.htm?epColumnId=${subcolumn.id}">${subcolumn.names}</a>
															&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
														</c:if>
													</span>
												</c:forEach>
										</div>
										<div style="clear:both;"></div>
									</li>
								</c:if>
								<c:if test="${webMapBean.enterpriseColumn.typeColumn.templateType == 'content'}">
									<li>
										<div class="text_img"></div>
										<div class="text_info">
										<a
											href="${ctx}/epIndexNewsAction!news_list_more.htm?epColumnId=${webMapBean.enterpriseColumn.id}">${webMapBean.enterpriseColumn.names}</a>
										</div>
									</li>
								</c:if>
							</c:if>
						</c:forEach>
                </ul>
            
            </div>
        </div>
    </div>
    <!--middle end-->
    <!--right begin-->
    <div id="nl-right">
        <div id="nl-right1">
        	<ul>
            	<c:forEach var="ad" items="${map.ad_3}">
            		<li>
            			<a href="${ad.address}"><img src="${ctx}/upload/enterprice/${ad.photo}"/></a>
            		</li>
            	</c:forEach>
            </ul>
        </div>
    </div>
    <!--right end-->
	</div>
	<%@ include file="/jsps/modules/copyRight.jsp"%>
	</body>
</html>