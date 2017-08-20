<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<c:forEach var="productRoll" items="${indexProductInfos}">
	<div class="sale_pic">
		<a href="${ctx}/epProductAction!tampons.action?id=${productRoll.mcProduct.id}">
		    <img src="${uploadPath}/${productRoll.mcProduct.para12}">
		</a>
	</div>
</c:forEach>