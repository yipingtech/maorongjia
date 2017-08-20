<%@ page contentType="text/html;charset=utf-8"%>

<%@ include file="/jsps/modules/top.jsp"%>

<!--container begin-->
<div id="container">
	
	<!--container_newsList_L-->
	<%@ include file="/jsps/modules/left.jsp"%>
	
	<div id="container_newsList_R">

		<div class="container_newsList_R_info">
			<c:if test="${empty newList}">
           		<ul>
               		<li>
            			<font color="black">您好,找不到您所要查询的内容的相关内容,请输入其它的关键字进行查询!</font>
                   	</li>
              	</ul>
           	 </c:if>
           	 <c:if test="${not empty newList}">
				<ul>
	        		<c:forEach var="news" items="${newList}">
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
					            			${ctx}/epIndexNewsAction!news.htm?newsId=${news.id}
					            		</c:otherwise>
				            		</c:choose>" target="_blank">
									${mtag:cutString(news.title,50,'...')}
								</a>
							</div>
							<div class="date"><fmt:formatDate value="${news.editeTime}" pattern="MM-dd"/></div>
						</li>
					</c:forEach>
				</ul>
			</c:if>
        </div>
    </div>
</div>
<!--container end-->

<div class="clear"></div>

<%@ include file="/jsps/modules/copyRight.jsp"%>