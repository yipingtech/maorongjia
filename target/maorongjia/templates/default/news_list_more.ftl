<#include "modules/top.ftl"/>

<!--container begin-->
<div id="container">

	<!--container_newsList_L-->
	<#include "modules/left.ftl"/>

	<!--container_newsList_more_R begin-->
	<div id="container_newsList_more_R">
		<#if newslist?exists>
			<#list newslist as index>
				<#if index.enterpriseColumn?exists>
					<#if index.enterpriseColumn.typeColumn?exists>
						<#if index.enterpriseColumn.typeColumn=='list'>
							<div id="container_newsList_more_R1" class="container_newsList_more" >
								<div class="container_newsList_R_banner">
				            		<div class="container_newsList_R_banner_L">
				            			<#if index.enterpriseColumn.names?exists>
											<span>${index.enterpriseColumn.names}</span>
										</#if>
				                	</div>
				                	<div class="container_newsList_R_banner_R">
	                					<div class="banner_more2">
	                						<a href="${ctx}/${webSite.htmlName}/${fatherColumnId}/${index.enterpriseColumn.id}/${index.enterpriseColumn.id}_0.html">more</a>
	                					</div>
									</div>
								</div>
								<div class="container_newsList_more_info">
									<ul>
										<#if index.enterpriseNewsList?exists>
											<#list index.enterpriseNewsList as news>
												<li>
							                        <div class="greenticon"><img src="${ctx}/jsps/theme/${webSite.webSkin.filename}/image/greenicon.gif"></div>
							                        <div class="headline">
							                        	<#if news.isPrimPhoto?exists && news.isPrimPhoto=='2'>
						                                	<a href="${ctx}/upload/enterprice/${news.photo}" target="_blank">
														<#else>
															<#if news.otherURL?exists &&  news.otherURL!="">
																<a href="${news.otherURL}" target="_blank">
															<#else>
																<a href="${ctx}/<#if news.htmlName?exists>${news.htmlName}</#if>" target="_blank">
						                                    </#if>
						                                </#if>
															<#if news.title?exists>
																<#if news.title?length gt 35>
																	${news.title[0..35]}...
																<#else>
																	${news.title}	
																</#if>
															</#if>
														</a>
													</div>
							                        <div class="date">${news.editeTime?string('MM-dd')}</div>
							                    </li>
							                    <#if news_index==6><#break></#if>
											</#list>
										</#if>
									</ul>
								</div>
							</div>
						</#if>
					</#if>
				</#if>
			</#list>
		</#if>
	</div>
	<!--container_newsList_more_R end-->

</div>
<!--container end-->

<div class="clear"></div>

<#include "modules/copyRight.ftl"/>