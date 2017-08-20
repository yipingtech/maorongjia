<div id="container_newsList_L">
	<c:if test="${not empty columnName}">
		<div id="container_newsList_L1">
	   		<div class="news_banner">${columnName}</div>
			<div id="container_newsList_L1_info" class="nav_left">
				<ul>
					<c:forEach var="index" items="${newslist}">
						<li class="separator">
							<a href="${index.enterpriseColumn.linkUrl}">${index.enterpriseColumn.names}</a>
						</li>
					</c:forEach>
				</ul>
	       </div>
		</div>
	</c:if>
	<div id="container_newsList_L2">
       <div class="container_newsList_L2_ad_index"><a href="${ctx}/epIndexNewsAction!researchCenter.htm?epColumnId=${epColumnId}"><img src="${ctx}/jsps/theme/${webSite.webSkin.filename}/image/researchcenter.jpg" /></a></div>
       <div class="container_newsList_L2_ad"><a href="${ctx}/epIndexNewsAction!researchCenter.htm?epColumnId=${epColumnId}"><img src="${ctx}/jsps/theme/${webSite.webSkin.filename}/image/motorcenter.jpg" /></a></div>
       <div class="container_newsList_L2_ad"><a href="${ctx}/epIndexNewsAction!researchCenter.htm?epColumnId=${epColumnId}"><img src="${ctx}/jsps/theme/${webSite.webSkin.filename}/image/mildcenter.jpg" /></a></div>
       <div class="container_newsList_L2_ad"><a href="${ctx}/epIndexNewsAction!researchCenter.htm?epColumnId=${epColumnId}"><img src="${ctx}/jsps/theme/${webSite.webSkin.filename}/image/cleancenter.jpg" /></a></div>
       <div class="container_newsList_L2_ad"><a href="${ctx}/epIndexNewsAction!researchCenter.htm?epColumnId=${epColumnId}"><img src="${ctx}/jsps/theme/${webSite.webSkin.filename}/image/environmentcenter.jpg" /></a></div>
       <div class="container_newsList_L2_ad"><a href="${ctx}/epIndexNewsAction!researchCenter.htm?epColumnId=${epColumnId}"><img src="${ctx}/jsps/theme/${webSite.webSkin.filename}/image/ad1.jpg" /></a></div>
    </div>
</div>