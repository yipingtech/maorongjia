<%@ page contentType="text/html;charset=utf-8"%>

<%@ include file="/jsps/modules/top.jsp"%>

<!--container begin-->
<div id="container">
	
	<!--container_newsList_L-->
	<%@ include file="/jsps/modules/left.jsp"%>
	
	<div id="container_newsList_R">
		<!--container_newsList_R_banner-->
		<%@ include file="/jsps/modules/right.jsp"%>

		<div class="container_newsList_R_info">
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
				            			${ctx}/epIndexNewsAction!news.htm?newsId=${news.id}&epColumnId=${epColumnId}
				            		</c:otherwise>
			            		</c:choose>" target="_blank">
								${mtag:cutString(news.title,35,'...')}
							</a>
						</div>
						<div class="date"><fmt:formatDate value="${news.editeTime}" pattern="MM-dd"/></div>
					</li>
				</c:forEach>
			</ul>
			<c:if test="${not empty newList}">
				<s:form action="epIndexNewsAction!news_more" method="get" id="form1" name="form1" theme="simple">
					<input type="hidden" name="epColumnId" value="${epColumnId}"/>
					<input type="hidden" name="subColumnId" value="${subColumnId}"/>
			    	<div class="news_page">
		            	<span class="page_info" style="color:#000;width:100%;">
		               		共有 <s:property value="pager.rowCount" />条记录，每页行数：

		               		<s:property value="pager.pageSize" />

							<s:select name="pageSize" list="pager.pageSizeIndexs" style="display:none;"
								theme="simple"
								value="pager.pageSize"
								onchange="$('#pageNo').val('1');$('#form1').submit();" />当前第

		          			<s:select name="pageNo" list="pager.pageNoIndexs" theme="simple"
								value="pager.pageNo" onchange="$('#form1').submit();" />页

							<c:if test="${pager.pageNo == '1'}">
								首页
								上一页
							</c:if>
							<c:if test="${pager.pageNo != '1'}">
								<a href="javascript:$('#pageNo').val('<s:property value="pager.firstPageNo" />');$('#form1').submit();">
									首页
								</a>
								<a href="javascript:$('#pageNo').val('<s:property value="pager.prePageNo" />');$('#form1').submit();">
									上一页
								</a>
							</c:if>
							
							<c:if test="${pager.pageNo == pager.pageCount}">
								下一页
								尾页
							</c:if>
							<c:if test="${pager.pageNo != pager.pageCount}">
								<a href="javascript:$('#pageNo').val('<s:property value="pager.nextPageNo" />');$('#form1').submit();">
									下一页
								</a>
								<a href="javascript:$('#pageNo').val('<s:property value="pager.lastPageNo" />');$('#form1').submit();">
									尾页
								</a>
							</c:if>
						</span>
					</div>
				</s:form>
			</c:if>
        </div>
    </div>
</div>
<!--container end-->

<div class="clear"></div>

<%@ include file="/jsps/modules/copyRight.jsp"%>