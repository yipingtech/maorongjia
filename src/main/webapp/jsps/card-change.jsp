<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/jsps/modules/top.jsp"%>
<body>
<header class="header_top clearfix">
    <a href="epMemberCenterAction!applyDrawCash.action"><img src="${imagePath}/left_arrow.png"></a>
    <p>银行卡绑定</p>
</header>
<!--container-->
<c:if test="${not empty bankCardList }">
		<section id="container">
		    <div class="card-list">
		        <c:forEach var="bean" items="${bankCardList }">
			        <div class="bind_item clearfix">
			            <img src="${imagePath}/jianhang.png" class="bank_image left">
			            <div class="left">
			            <p>${bean.bankCardFlag }</p>
			            <p>尾号<c:out value="${fn:substring(bean.bankCardNum,fn:length(bean.bankCardNum)-4, fn:length(bean.bankCardNum))}" /><!-- 储蓄卡 --></p>
			            </div>
			            <div class="arrow_image right "><a href="epMemberCenterAction!changeCard.action?id=${bean.id }"><img src="${imagePath}/arrow-gright.png"></a></div>
			        </div>
		        </c:forEach>
		      
		    </div>
		    
		</section>
</c:if>


<!--end of container-->
</body>
</html>