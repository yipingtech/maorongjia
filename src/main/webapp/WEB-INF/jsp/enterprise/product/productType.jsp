
<%@ page contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>类型管理</title>
		<%@ include file="/common/taglibs.jsp"%>
		<%@ include file="/common/meta.jsp"%>
		<%@ include file="/common/content.jsp"%>
		
		<script>
			function doYouWantToDel() {
				if(confirm('如果有订单或者购物车商品引用了该类型，则该类型不能被删除！')) {
					return true;
				}
				return false;
			}
		</script>
		
	</head>
	<body>
		<div class="rhead">
			<div class="rpos">
				当前位置: 产品 - 类型管理
			</div>
			<div class="ropt">
				<a href="productTypeAction!newPage.action" class="btn">新增</a>
			</div>
			<div class="clear"></div>
		</div>
		<c:if test="${not empty productTypes}">
			<s:form action="productTypeAction!retrieveAllProductTypes.action"
					namespace="/product" method="post" name="form1" theme="simple" id="form1">
				<!-- 分页 -->
				<%@ include file="../../common/pager.jsp"%>
			</s:form>
			<br /> 
		</c:if>
		<table class="table table-bordered">
			<tr>
				<th>序号</th>
				<th>类型名称</th>
				<!-- <th>商品属性分组</th> -->
				<th>操作</th>
			</tr>

			<c:forEach var="bean" items="${productTypes}"
				varStatus="status">
				<tr>
					<td>${status.count}</td>
					<td>${bean.name}</td>
					<%-- <td>${bean.typeGroup}</td> --%>

					<td>
						<a href="attributeAction!retrieveAllAttributes.action?attribute.productType.id=${bean.id}">属性列表</a>
						&nbsp; &nbsp;
						<a href="productTypeAction!retrieveProductTypeById.action?id=${bean.id}">编辑</a>
						&nbsp; &nbsp;
						<a href="productTypeAction!delProductType.action?id=${bean.id}"
								onclick="return doYouWantToDel();">删除</a>
					</td>
				</tr>
			</c:forEach>

		</table>

	</body>
</html>