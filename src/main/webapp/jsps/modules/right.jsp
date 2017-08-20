<%@ page contentType="text/html;charset=utf-8"%>

<div class="container_newsList_R_banner">
	<div class="container_newsList_R_banner_L"><span>您当前的位置</span></div>
    <div class="container_newsList_R_banner_R">
    	<span>
    		主页 
    		<c:if test="${not empty columnName}">> ${columnName}</c:if>
    		<c:if test="${not empty subColumnName}">> ${subColumnName}</c:if>
    	</span>
    </div>
</div>