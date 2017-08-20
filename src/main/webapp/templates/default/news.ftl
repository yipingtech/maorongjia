<#include "modules/top.ftl"/>

<!--container begin-->
<div id="container">

	<!--container_newsList_L-->
	<#include "modules/left.ftl"/>
	
    <div id="container_newsList_R">
    	<!--container_newsList_R_banner-->
		<#include "modules/right.ftl"/>
		
        <div class="container_newsList_R_info">
			<#if news?exists>
        		<#if news.isOnlyContent?exists>
        			<#if news.isOnlyContent==0>
		            	<div class="news_title">
							<#if news?exists>
								<#if news.title?exists>
									${news.title}
								</#if>
							</#if>
						</div>
						<div class="news_disc">
							<div class="news_author">发表者：
								<#if news?exists>
									<#if news.autor?exists>
										${news.autor}
									</#if>
								</#if>
							</div>
							<div class="news_source">来源：
								<#if news?exists>
									<#if news.source?exists>
										${news.source}
									</#if>
								</#if>
							</div>
							<div class="news_time">更新时间：
								<#if news?exists>
									<#if news.editeTime?exists>
										${news.editeTime?string('yyyy-MM-dd')}
									</#if>
								</#if>
							</div>
						</div>
					</#if>
				</#if>
				<div class="news_detail">
					<#if news.contents?exists>
						${news.contents}
					</#if>
				</div>
			</#if>
        </div>
    </div>
</div>
<!--container right end-->
<div class="clear"></div>

<#include "modules/copyRight.ftl"/>