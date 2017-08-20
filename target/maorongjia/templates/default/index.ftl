<#include "modules/top.ftl"/>

    <!--container begin-->
    <div id="container">
    	<div id="container_1">
        	<div id="container_1L" class="container_L">
            	<div class="banner_gray_style">
                	<div class="banner_gray_style_title2">
						<!-- 信息公告 -->
	                   	<#if bulletin?exists>
                        	${bulletin.enterpriseColumn.names}
						<#else>
				         	模块row1column1
				        </#if>
					</div>
                    <div class="banner_more2">
                    	<#if bulletin?exists>
							<#if bulletin.enterpriseColumn?exists>
								<#if bulletin.enterpriseColumn.htmlName?exists>
									<a href="${ctx}/${bulletin.enterpriseColumn.htmlName}">
										more
									</a>
								</#if>
					         </#if>
					    </#if>
					</div>
                </div>
                <div id="container_1L_infowrap">
                	<div class="container_L_newslist">
                    	<ul>
                    		<#if bulletin?exists>
								<#if bulletin.enterpriseNewsList?exists>
									<#list bulletin.enterpriseNewsList as news>
										<li>
                                	 		<div class="container_L_newslist_headline">
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
														<#if news.title?length gt 9>
															${news.title[0..9]}...
														<#else>
															${news.title}	
														</#if>
													</#if>
												</a>
											</div>
											<div class="date">
												${news.editeTime?string('MM-dd')}
											</div>
										</li>
										<#if news_index==9><#break></#if>
									</#list>
						    	</#if>
						    </#if>
                        </ul>
                    </div>	
                </div>    	
            </div>
            <div id="container_1R">
            	<div class="banner_green_style2">
                	<div class="banner_green_style_titlewrap2">
                    	<div class="banner_green_style_title2">
							<!-- 国内环保新闻 -->
							<#if row1column2?exists>
		                    	${row1column2.enterpriseColumn.names}
							<#else>
						    	模块row1column1
							</#if>
						</div>
                    </div>
                    <div class="banner_more2">
                    	<#if row1column2?exists>
							<#if row1column2.enterpriseColumn?exists>
								<#if row1column2.enterpriseColumn.htmlName?exists>
									<a href="${ctx}/${row1column2.enterpriseColumn.htmlName}">
										more
									</a>
								</#if>
					         </#if>
					    </#if>
					</div>
                </div>
                <div id="container_1R_infowrap">
                	<div id="container_1R1_infowrap">
                		<#if row1column2?exists>
							<#if row1column2.enterprisePhotoNewsList?exists>
								<#list row1column2.enterprisePhotoNewsList as news>
									<a href="${ctx}/${news.htmlName}" target="_blank">
										<img src="${ctx}/upload/enterprice/${news.photo}" />
									</a>
									<#if news_index==0><#break></#if>
								</#list>
					    	</#if>
					    </#if>
                    </div>
                    <div id="container_1R2_infowrap">
                    	<ul>
                    		<#if row1column2?exists>
								<#if row1column2.enterpriseNewsList?exists>
									<#list row1column2.enterpriseNewsList as news>
										<li>
                                	 		<div class="container_1R_newslist_headline">
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
														<#if news.title?length gt 21>
															${news.title[0..21]}...
														<#else>
															${news.title}	
														</#if>
													</#if>
												</a>
											</div>
											<div class="date">
												${news.editeTime?string('MM-dd')}
											</div>
										</li>
										<#if news_index==9><#break></#if>
									</#list>
						    	</#if>
						    </#if>
                    	</ul>
                    </div>
                </div>
            </div>
        </div>
        <div class="clear"></div>
        <div id="container_2">
        	<div id="container_2L" class="container_L">
            	<div class="banner_white_style">
                	<div class="banner_gray_style_title">
                		<!-- 图片新闻 -->
                		<#if row2column1?exists>
	                    	${row2column1.enterpriseColumn.names}
						<#else>
					    	模块row2column1
						</#if>
                	</div>
                    <div class="banner_more2">
                    	<#if row2column1?exists>
							<#if row2column1.enterpriseColumn?exists>
								<#if row2column1.enterpriseColumn.htmlName?exists>
									<a href="${ctx}/${row2column1.enterpriseColumn.htmlName}">
										more
									</a>
								</#if>
					         </#if>
					    </#if>
                    </div>
                </div>
                <div class="clear"></div>
                <div id="container_2L_infowrap">
                	<div id="container_2L_info_L"><img id="lastButton" src="${ctx}/jsps/theme/${webSite.webSkin.filename}/image/newsLast.gif" onclick="lxfLast();" /></div>
                    <div id="container_2L_info_C" >
                    	<div id="container_2L_info_C_img" class="lxfscroll">
                        	<ul>
								<#if row2column1?exists>
									<#if row2column1.enterprisePhotoNewsList?exists>
										<#list row2column1.enterprisePhotoNewsList as news>
											<a href="${ctx}/${news.htmlName}" target="_blank">
												<img src="${ctx}/upload/enterprice/${news.photo}" />
											</a>
											<#if news_index==2><#break></#if>
										</#list>
							    	</#if>
							    </#if>
                            </ul>
                        </div>
                        <div id="container_2L_info_C_text" class="textscroll">
                        	<ul>
                        		<#if row2column1?exists>
								<#if row2column1.enterpriseNewsList?exists>
									<#list row2column1.enterpriseNewsList as news>
										<li>
											<#if news.shortMeta?exists>
												<#if news.shortMeta?length gt 38>
													${news.shortMeta[0..38]}...
												<#else>
													${news.shortMeta}	
												</#if>
											</#if>
										</li>
										<#if news_index==2><#break></#if>
									</#list>
						    	</#if>
						    </#if>
                            </ul>
                        </div>
                    </div>
                    <div id="container_2L_info_R"><img id="nextButton" src="${ctx}/jsps/theme/${webSite.webSkin.filename}/image/newsNext.gif" onclick="lxfNext();"/></div>
                </div>
            </div>
            <div id="container_2C" class="container_C">
            	<div class="banner_green_style2">
                	<div class="banner_green_style_titlewrap2">
                    	<div class="banner_green_style_title2">
                    		<!-- 综合新闻 -->
                    		<#if row2column2?exists>
		                    	${row2column2.enterpriseColumn.names}
							<#else>
						    	模块row2column2
							</#if>
                    	</div>
                    </div>
                    <div class="banner_more2">
                    	<#if row2column2?exists>
							<#if row2column2.enterpriseColumn?exists>
								<#if row2column2.enterpriseColumn.htmlName?exists>
									<a href="${ctx}/${row2column2.enterpriseColumn.htmlName}">
										more
									</a>
								</#if>
					         </#if>
					    </#if>
                    </div>
                </div>
                <div id="container_2C_infowrap">
                	<div class="container_C_newslist">
                        <ul>
                        	<#if row2column2?exists>
								<#if row2column2.enterpriseNewsList?exists>
									<#list row2column2.enterpriseNewsList as news>
										<li>
		                    				<div class="orangeicon"><img src="${ctx}/jsps/theme/${webSite.webSkin.filename}/image/orangeicon.gif"></div>
		                                	<div class="container_C_newslist_headline">
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
														<#if news.title?length gt 24>
															${news.title[0..24]}...
														<#else>
															${news.title}	
														</#if>
													</#if>
												</a>
											</div>
											<div class="date">
												${news.editeTime?string('MM-dd')}
											</div>
										</li>
										<#if news_index==9><#break></#if>
									</#list>
						    	</#if>
						    </#if>
                        </ul>
                    </div>
                </div>
            </div>
            <div id="container_2R" class="container_R">
            	<div class="banner_green_style2">
                	<div class="banner_green_style_titlewrap2">
                    	<div class="banner_green_style_title2">
                    		<!-- 科学研究动态 -->
                    		<#if row2column3?exists>
		                    	${row2column3.enterpriseColumn.names}
							<#else>
						    	模块row2column3
							</#if>
                    	</div>
                    </div>
                    <div class="banner_more2">
                    	<#if row2column3?exists>
							<#if row2column3.enterpriseColumn?exists>
								<#if row2column3.enterpriseColumn.htmlName?exists>
									<a href="${ctx}/${row2column3.enterpriseColumn.htmlName}">
										more
									</a>
								</#if>
					         </#if>
					    </#if>
                    </div>
                </div>
                 <div id="container_2R_infowrap">
                	<div class="container_R_newslist">
                        <ul>
                        	<#if row2column3?exists>
								<#if row2column3.enterpriseNewsList?exists>
									<#list row2column3.enterpriseNewsList as news>
										<li>
		                    				<div class="orangeicon"><img src="${ctx}/jsps/theme/${webSite.webSkin.filename}/image/orangeicon.gif"></div>
		                                	<div class="container_R_newslist_headline">
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
														<#if news.title?length gt 13>
															${news.title[0..13]}...
														<#else>
															${news.title}	
														</#if>
													</#if>
												</a>
											</div>
											<div class="date">
												${news.editeTime?string('MM-dd')}
											</div>
										</li>
										<#if news_index==9><#break></#if>
									</#list>
						    	</#if>
						    </#if>
                    	</ul>
                	</div>
            	</div>
            </div>
        </div>
        <div class="clear"></div>
        <div id="container_3">
        	<div id="container_3L"><a href=""><img src="${ctx}/jsps/theme/${webSite.webSkin.filename}/image/20130105174447.jpg" /></a></div>
            <div id="container_3C"><a href=""><img src="${ctx}/jsps/theme/${webSite.webSkin.filename}/image/20130105174448.jpg" /></a></div>
            <div id="container_3R"><a href=""><img src="${ctx}/jsps/theme/${webSite.webSkin.filename}/image/20130105174449.jpg" /></a></div>
        </div>
        <div class="clear"></div>
        <div id="container_4">
        	<div id="container_4L"><a href=""><img src="${ctx}/jsps/theme/${webSite.webSkin.filename}/image/20130105174450.jpg" /></a></div>
            <div id="container_4C"><a href=""><img src="${ctx}/jsps/theme/${webSite.webSkin.filename}/image/20130105174451.jpg" /></a></div>
            <div id="container_4R"><a href=""><img src="${ctx}/jsps/theme/${webSite.webSkin.filename}/image/20130105174452.jpg" /></a></div>
        </div>
        <div class="clear"></div>
        <div id="container_5">
        	<div id="container_5L" class="container_L">
            	<div class="banner_white_style">
                	<div class="banner_gray_style_title">
                		<!-- 环评公众参与公示 -->
                		<#if row5column1?exists>
	                    	${row5column1.enterpriseColumn.names}
						<#else>
					    	模块row5column1
						</#if>
                	</div>
                    <div class="banner_more2">
                    	<#if row5column1?exists>
							<#if row5column1.enterpriseColumn?exists>
								<#if row5column1.enterpriseColumn.htmlName?exists>
									<a href="${ctx}/${row5column1.enterpriseColumn.htmlName}">
										more
									</a>
								</#if>
					         </#if>
					    </#if>
                    </div>
                </div>
                <div id="container_5L_infowrap">
                	<div class="container_L_newslist">
                    	<ul>
                    		<#if row5column1?exists>
								<#if row5column1.enterpriseNewsList?exists>
									<#list row5column1.enterpriseNewsList as news>
										<li>
		                                	<div class="container_L_newslist_headline">
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
														<#if news.title?length gt 9>
															${news.title[0..9]}...
														<#else>
															${news.title}	
														</#if>
													</#if>
												</a>
											</div>
											<div class="date">
												${news.editeTime?string('MM-dd')}
											</div>
										</li>
										<#if news_index==9><#break></#if>
									</#list>
						    	</#if>
						    </#if>
                        </ul>
                    </div>
                </div>
            </div>
            <div id="container_5C" class="container_C">
            	<div class="banner_green_style2">
                	<div class="banner_green_style_titlewrap2">
                    	<div class="banner_green_style_title2">
                    		<!-- 廉政建设 -->
                    		<#if row5column2?exists>
		                    	${row5column2.enterpriseColumn.names}
							<#else>
						    	模块row5column2
							</#if>
                    	</div>
                    </div>
                    <div class="banner_more2">
                    	<#if row5column2?exists>
							<#if row5column2.enterpriseColumn?exists>
								<#if row5column2.enterpriseColumn.htmlName?exists>
									<a href="${ctx}/${row5column2.enterpriseColumn.htmlName}">
										more
									</a>
								</#if>
					         </#if>
					    </#if>
                    </div>
                </div>
                <div id="container_5C_infowrap">
                	<div class="container_C_newslist">
                        <ul>
                        	<#if row5column2?exists>
								<#if row5column2.enterpriseNewsList?exists>
									<#list row5column2.enterpriseNewsList as news>
										<li>
		                                	<div class="orangeicon"><img src="${ctx}/jsps/theme/${webSite.webSkin.filename}/image/orangeicon.gif"></div>
                                			<div class="container_C_newslist_headline">
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
														<#if news.title?length gt 24>
															${news.title[0..24]}...
														<#else>
															${news.title}	
														</#if>
													</#if>
												</a>
											</div>
											<div class="date">
												${news.editeTime?string('MM-dd')}
											</div>
										</li>
										<#if news_index==9><#break></#if>
									</#list>
						    	</#if>
						    </#if>
                        </ul>
                    </div>
                </div>
            </div>
            <div id="container_5R" class="container_R">
            	<div class="banner_green_style2">
                	<div class="banner_green_style_titlewrap2">
                    	<div class="banner_green_style_title2">
                    		<!-- 技术服务动态 -->
                    		<#if row5column3?exists>
		                    	${row5column3.enterpriseColumn.names}
							<#else>
						    	模块row5column3
							</#if>
                    	</div>
                    </div>
                    <div class="banner_more2">
                    	<#if row5column3?exists>
							<#if row5column3.enterpriseColumn?exists>
								<#if row5column3.enterpriseColumn.htmlName?exists>
									<a href="${ctx}/${row5column3.enterpriseColumn.htmlName}">
										more
									</a>
								</#if>
					         </#if>
					    </#if>
                    </div>
                </div>
                 <div id="container_5R_infowrap">
                	<div class="container_R_newslist">
                        <ul>
                        	<#if row5column3?exists>
								<#if row5column3.enterpriseNewsList?exists>
									<#list row5column3.enterpriseNewsList as news>
										<li>
		                                	<div class="orangeicon"><img src="${ctx}/jsps/theme/${webSite.webSkin.filename}/image/orangeicon.gif"></div>
                                			<div class="container_R_newslist_headline">
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
														<#if news.title?length gt 13>
															${news.title[0..13]}...
														<#else>
															${news.title}	
														</#if>
													</#if>
												</a>
											</div>
											<div class="date">
												${news.editeTime?string('MM-dd')}
											</div>
										</li>
										<#if news_index==9><#break></#if>
									</#list>
						    	</#if>
						    </#if>
                    	</ul>
                	</div>
            	</div>
            </div>
        </div>
        <div class="clear"></div>
        <div id="container_6"><a href=""><img src="${ctx}/jsps/theme/${webSite.webSkin.filename}/image/20130105174453.jpg" /></a></div>
        <div class="clear"></div>
    </div>
     <!--container right end-->

<#include "modules/copyRight.ftl"/>