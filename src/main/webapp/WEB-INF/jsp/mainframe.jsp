<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>MCCMS</title>
<link rel="stylesheet" type="text/css" href="${ctx}/resource/image/style.css">
<script type="text/javascript" src="${ctx}/resource/js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="${ctx}/resource/js/left_menu.js"></script>
<script type="text/javascript" src="${ctx}/resource/js/common.js" charset="gb2312"></script>
<!-- 这里引入网站图标 -->
<link rel="shortcut icon" href="${ctx}/userfiles/image/favicon.ico" />
<link rel="bookmark"href="${ctx}/userfiles/image/favicon.ico" />
<script type="text/javascript">
$(document).ready(function(){
	init("childMenu1", "name_childMenu1");
	GetMenuID();
	showThreeLeverMenu();
	$("#right .btn").click(showRightList);
	$("#left .left_menu > li > ul > li > a").click(checkedItem);
	$("#left .left_menu .three_lever a").click(checkedSecondItem);
});

function setWH(){
	setHeightWithWindow("#left");
	setHeightWithWindow("#right");
	setHeightWithWindow("#main");
	setWidthWithWindow("#main");
	$("#right .btn").css("top",($("#right").height() - $("#right .btn").height()) / 2);
	setBottomWithWindowSize("#footer", 681);
	$("#main iframe").width($("#main").width() - 25);
	$("#main iframe").height($("#main").height());
	$("#right iframe").width($("#right").width() - 22);
	$("#right iframe").height($("#right").height() - 75);
}

function changeLocale() {
	$("#changeLocaleForm").submit();
}
</script>
</head>

<body>
    <!-- top begin -->
    <div id="top" class="clearfix">
    	<div class="logo_weaper">
    		<a href="${ctx}/defaultAction.action" target="mainframe">
	        	<img src="${ctx}/resource/image/mlogo.png" />
	        </a>
        </div>
        <div class="btn_weaper">
            <a href="${ctx}/j_spring_security_logout">
                <div class="exit">
                    <div class="exit_img"></div>
                    <div class="exit_text">退&nbsp;&nbsp;出</div>
                </div>
            </a>
        </div>
    </div><!-- top end -->
    
    <!-- left begin -->
    <div id="left" class="clearfix">
    	<div class="tab_menu clearfix">
        	<div class="currentuser">欢迎您，${currentUserName}</div>
            <div class="editpwd"><a href="${ctx}/basic/updatePasswordAction.action" target="mainframe">修改密码</a></div>
        </div>
        <div class="bar"></div>
        <!-- left_menu_container begin -->
        <div class="left_menu_container">
            <!-- left_menu_wrap begin -->
            <div class="left_menu_wrap clearfix">
                <!-- left_menu1_wrap begin -->
                <div class="left_menu1_wrap">
                	<ul class="left_menu_list">
                        <a href="javascript:void(0);" target="mainframe" class="onchecked"><li></li></a>
                        <a href="javascript:void(0);" target="mainframe"><li></li></a>
                        <a href="javascript:void(0);" target="mainframe"><li></li></a>
                    </ul>
                </div><!-- left_menu1_wrap end -->
                
                <!-- left_menu2_wrap begin -->
                <div class="left_menu2_wrap">
                    <ul class="left_menu">
                    	<c:forEach var="menu" items="${menuList}" varStatus="status">
                    		<security:authorize ifAnyGranted="${menu.authorize}">
                    			<li>
		                            <a onClick="DoMenu('${menu.pic}');
		                            	<c:if test="${menu.isShowRightPanel == 'N'}">
		                                	hideRightPanel();
		                                </c:if>
		                            	<c:if test="${menu.isShowRightPanel == 'Y'}">
		                            		showRightPanel('${menu.rightPanelName}', '${ctx}/${menu.rightPanelUrl}');
		                            	</c:if>"
		                            	
		                            	<c:if test="${not empty menu.url}">
		                            		href="${ctx}/${menu.url}" target="mainframe"
		                            	</c:if>	
		                            >
		                                <div id="name_${menu.pic}" 
		                                	<c:if test="${status.index == 0}">
		                                		class="open_parent_name" 
		                                	</c:if>
		                                	<c:if test="${status.index > 0}">
		                                		class="parent_name" 
		                                	</c:if>
		                                >
		                                    <div class="pic_wrap">
		                                        <div class="pic"></div>
		                                        <span>${menu.menuName}</span>
		                                    </div>
		                                </div>
		                            </a>
		                            <c:if test="${not empty menu.menuChildren}">
			                            <ul id="${menu.pic}"
			                            	<c:if test="${status.index == 0}">
		                                		class="expanded" 
		                                	</c:if>
		                                	<c:if test="${status.index > 0}">
		                                		class="collapsed" 
		                                	</c:if>
			                            >
		                            		<c:forEach var="menuChild" items="${menu.menuChildren}">
		                            			<security:authorize ifAnyGranted="${menuChild.authorize}">
		                            				<li>
		                            					<c:if test="${empty menuChild.menuChildren}">
		                            						<a href="${ctx}/${menuChild.url}" target="mainframe">${menuChild.menuName}</a>
		                            					</c:if>
		                            					<c:if test="${not empty menuChild.menuChildren}">
		                            						<a href="javascript:void(0);">${menuChild.menuName}</a>
						                            		<div class="three_lever">
						                                        <ul>
						                                            <li class="title">${menuChild.menuName}</li>
						                                            
						                                            <c:forEach var="menuChildChild" items="${menuChild.menuChildren}">
						                                            	<security:authorize ifAnyGranted="${menuChildChild.authorize}">
						                                            		<a href="${ctx}/${menuChildChild.url}" target="mainframe"><li>${menuChildChild.menuName}</li></a>
						                                            	</security:authorize>
						                                            </c:forEach>
						                                        </ul>
						                                    </div>
		                            					</c:if>
		                            					
		                            				</li>
		                            			</security:authorize>
		                            		</c:forEach>
		                            	</ul>
		                            </c:if>
									<div class="separator"></div>
		                        </li>
                    		</security:authorize>
                    	</c:forEach>
                    </ul>
                </div><!-- left_menu2_wrap end -->
            </div><!-- left_menu_wrap end -->
        </div><!-- left_menu_container end -->
    </div><!-- left end -->
    
    <!-- main begin -->
    <div id="main" class="clearfix">
        <iframe scrolling="yes" src="${ctx}/defaultAction.action" id="mainframe" name="mainframe" frameborder="0"></iframe>
    </div><!-- main end -->
    
    <!-- right begin -->
    <div id="right" style="display:none;">
    	<div class="btn out"></div>
        <div class="title clearfix">
        	<div class="col"></div>
        </div>
        <div class="content clearfix">
        	<iframe scrolling="yes" id="rightframe" name="rightframe" frameborder="0"></iframe>
        </div>
    </div><!-- right end -->
    
    <!-- footer begin -->
  	<div id="footer">
    	<div class="content">
            <p>${webSite.names}</p>
        </div>
    </div><!-- footer end -->
</body>
</html>