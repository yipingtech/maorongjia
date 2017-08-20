<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/jsps/modules/top.jsp"%>
<body>
<header class="header_top clearfix">
        <c:if test="${flag=='0' ||flag == null }">
     	    <a href="epMemberCenterAction!viewMember.action"><img src="${imagePath}/left_arrow.png"></a>
        </c:if>
        <c:if test="${flag=='1'}">
	        <a href="epMemberCenterAction!applyDrawCash.action"><img src="${imagePath}/left_arrow.png"></a>
        </c:if>
	    <p>银行卡绑定</p>
</header>
<!--container-->
<c:if test="${not empty bankCardList }">
		<section id="container">
		    <div class="card-list">
		        <c:forEach var="bean" items="${bankCardList }">
			        <div class="bind_item clearfix">
			            <c:if test="${flag eq '1' }">
			                <a href="epMemberCenterAction!changeCard.action?id=${bean.id }">
			            </c:if>
			            <c:if test="${flag eq '' || flag == null}">
			                <a>
			            </c:if>
			            <c:if test="${bean.bankCardFlag eq '中国工商银行' }"><img src="${imagePath}/gongshang.png" class="bank_image left"></c:if>
			            <c:if test="${bean.bankCardFlag eq '中国农业银行' }"><img src="${imagePath}/nongye.png" class="bank_image left"></c:if>
			            <c:if test="${bean.bankCardFlag eq '中国建设银行' }"><img src="${imagePath}/jianhang.png" class="bank_image left"></c:if>
			            <c:if test="${bean.bankCardFlag eq '招商银行' }"><img src="${imagePath}/zhaoshang.png" class="bank_image left"></c:if>
			            <div class="left">
			            <p>${bean.bankCardFlag }</p>
			            <p>尾号<c:out value="${fn:substring(bean.bankCardNum,fn:length(bean.bankCardNum)-4, fn:length(bean.bankCardNum))}" /><!-- 储蓄卡 --></p>
			            </div>
			            </a>
			            <div class="arrow_image right "><a href="epMemberCenterAction!bankCardDetail.action?id=${bean.id }&flag=${flag }"><img src="${imagePath}/arrow-gright.png"></a></div>
			        </div>
		        </c:forEach>
		      
		    </div>
		    <div class="bind_new_card clearfix">
		       <a href="epMemberCenterAction!toAddCard.action?flag=${flag }"><img class="left" src="${imagePath}/addcard.png"></a>
		       <p class="left">绑定一张新银行卡</p>
		    </div>
		</section>
</c:if>
<c:if test="${empty bankCardList }">
		<section id="container">
		    <div class="never_lock_text"><p>您仍未绑定任何一张银行卡</p></div>
		    <div class="never_lock"><img src="${imagePath}/nevercard_03.png"></div>
		    <div class="add_card"><a href="epMemberCenterAction!toAddCard.action?flag=${flag }"><img src="${imagePath}/addcard_03.png"></a></div>
		</section>
</c:if>

<!--end of container-->
</body>
</html>