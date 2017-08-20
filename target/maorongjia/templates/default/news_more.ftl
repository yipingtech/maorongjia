<#include "modules/top.ftl"/>

<!--container begin-->
<div id="container">
	
	<!--container_newsList_L-->
	<#include "modules/left.ftl"/>
	
    <div id="container_newsList_R">
    	<!--container_newsList_R_banner-->
		<#include "modules/right.ftl"/>

        <div class="container_newsList_R_info">
        	<ul>
        		<#if resultList?exists>
					<#list resultList as news>
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
                    </#list>
                </#if>
            </ul>
            <#if resultList?exists>
	            <div class="news_page">
	                <span class="page_info" style="color:#000;width:100%;">
	                	共有 ${totalNumber} 条记录，每页行数：${perPageNumber}，当前第 ${pageIndex+1} 页，
						<#if pageIndex!=0>
							<a href="${ctx}/${webSite.htmlName}/<#if fatherColumnId?exists>${fatherColumnId}/</#if>${htmlFile}/${htmlFile}_0.html">首页</a>
						<#else>
							首页
						</#if>

					    <#if pageIndex!=0>
							<a href="${ctx}/${webSite.htmlName}/<#if fatherColumnId?exists>${fatherColumnId}/</#if>${htmlFile}/${htmlFile}_${pageIndex-1}.html">上一页</a>
						<#else>
							上一页
						</#if>

						<#if pageIndex!=endPage>
							<a href="${ctx}/${webSite.htmlName}/<#if fatherColumnId?exists>${fatherColumnId}/</#if>${htmlFile}/${htmlFile}_${pageIndex+1}.html">下一页</a>
						<#else>
							下一页
						</#if>

						<#if pageIndex!=endPage>
							<a href="${ctx}/${webSite.htmlName}/<#if fatherColumnId?exists>${fatherColumnId}/</#if>${htmlFile}/${htmlFile}_${endPage}.html">尾页</a> 
						<#else>
							尾页
						</#if>
	                </span>
	            </div>
			</#if>
        </div>
    </div>
</div>
<!--container right end-->
<div class="clear"></div>

<#include "modules/copyRight.ftl"/>