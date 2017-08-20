<%@ include file="/jsps/modules/top.jsp"%>

<!--container begin-->
<div id="container">

	<!--container_newsList_L begin-->
	<%@ include file="/jsps/modules/left.jsp"%>
	<!--container_newsList_L end-->

	<!--container_newsList_more_R begin-->
	<div id="container_newsList_more_R">
		<c:forEach var="index" items="${newslist}">
			<c:if test="${index.enterpriseColumn.typeColumn.templateType == 'list'}">
				<div id="container_newsList_more_R1" class="container_newsList_more" >
					<div class="container_newsList_R_banner">
	            		<div class="container_newsList_R_banner_L">
							<span>${index.enterpriseColumn.names}</span>
	                	</div>
						<div class="container_newsList_R_banner_R">
	                		<div class="banner_more2">
		                  		<a href="${index.enterpriseColumn.linkUrl}">more</a>
				            </div>
						</div>
					</div>
			
					<div class="container_newsList_more_info">
						<ul>
	                  		<c:forEach var="news" items="${index.enterpriseNewsList}" begin="0" end="6">
	                    		<li>
		                        	<div class="greenticon"><img src="${ctx}/jsps/theme/${webSite.webSkin.filename}/image/greenicon.gif"></div>
									<div class="headline">
										<a href="
											<c:choose>
												<c:when test="${news.isPrimPhoto=='2'}">
													${ctx}/upload/enterprice/${news.photo}
												</c:when>
												<c:when test="${not empty news.otherURL}">
										        	${news.otherURL}
										        </c:when>
										        <c:otherwise>
										        	${ctx}/epIndexNewsAction!news.htm?newsId=${news.id}&epColumnId=${epColumnId}
										        </c:otherwise>
											</c:choose>" target="_blank">
											${mtag:cutString(news.title,30,'...')}
										</a>
									</div>
		                        	<div class="date"><fmt:formatDate value="${news.editeTime}" pattern="MM-dd"/></div>
								</li>
							</c:forEach>
						</ul>
					</div>
				</div>
   			</c:if>
   		</c:forEach>
	</div>
	<!--container_newsList_more_R end-->

</div>
<!--container end-->

<div class="clear"></div>

<%@ include file="/jsps/modules/copyRight.jsp"%>