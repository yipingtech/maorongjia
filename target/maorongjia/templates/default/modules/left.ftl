<div id="container_newsList_L">
	<#if columnName?exists>
		<div id="container_newsList_L1">
	   		<div class="news_banner">${columnName}</div>
			<div id="container_newsList_L1_info" class="nav_left">
				<ul>
					<#if subColumnList?exists>
		            	<#list subColumnList as column>
							<#if column.typeColumn = 'link'>
								<li class="separator">
									<a href="${column.linkUrl}">${column.names}</a>
								</li>
							<#else>
								<li class="separator">
									<a href="${ctx}/${webSite.htmlName}/${fatherColumnId}/${column.id}/${column.id}_0.html">${column.names}</a>
								</li>
						    </#if>
				    	</#list>
					</#if>
				</ul>
	       </div>
		</div>
	</#if>
	<div id="container_newsList_L2">
       <div class="container_newsList_L2_ad_index"><a href="#"><img src="${ctx}/jsps/theme/${webSite.webSkin.filename}/image/researchcenter.jpg" /></a></div>
       <div class="container_newsList_L2_ad"><a href="#"><img src="${ctx}/jsps/theme/${webSite.webSkin.filename}/image/motorcenter.jpg" /></a></div>
       <div class="container_newsList_L2_ad"><a href="#"><img src="${ctx}/jsps/theme/${webSite.webSkin.filename}/image/mildcenter.jpg" /></a></div>
       <div class="container_newsList_L2_ad"><a href="#"><img src="${ctx}/jsps/theme/${webSite.webSkin.filename}/image/cleancenter.jpg" /></a></div>
       <div class="container_newsList_L2_ad"><a href="#"><img src="${ctx}/jsps/theme/${webSite.webSkin.filename}/image/environmentcenter.jpg" /></a></div>
       <div class="container_newsList_L2_ad"><a href="#"><img src="${ctx}/jsps/theme/${webSite.webSkin.filename}/image/ad1.jpg" /></a></div>
    </div>
</div>