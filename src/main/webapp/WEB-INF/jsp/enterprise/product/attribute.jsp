<%@ page contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>商品属性</title>
		<%@ include file="/common/taglibs.jsp"%>
		<%@ include file="/common/meta.jsp"%>
		<%@ include file="/common/content.jsp"%>
		
		<script>
		
			$(document).ready(function(){
				$("#productType").change(changeType);
			});
			function doYouWantToDel() {
				if(confirm('如果有订单或者购物车商品引用了该属性，则该属性不能被删除并且修改该属性的商品全为下架！')) {
					return true;
				}
				return false;
			}
			
			
			function changeType(){
				$("#formType").submit();
			}
		</script>
	</head>
	<body>
		<div class="rhead">
			<div class="rpos">
				当前位置: 产品 - 产品属性
			</div>
			<div class="ropt">
				<a href="attributeAction!newPage.action?typeId=${typeId}" class="btn">新增</a>
			</div>
			<div class="clear">
			
			</div>
		</div>
		<div class="rhead">
		<form id="formType" name="formType" action="attributeAction!retrieveAllAttributes.action" method="post">
			产品类型：
				<select name="attribute.productType.id" id="productType">
					<c:forEach var="bean" items="${productTypes}">
						<option value="${bean.id}"  <c:if test="${bean.id eq attribute.productType.id}">selected="selected"</c:if>>${bean.name}</option>
					</c:forEach>
				</select>
		</form>
		</div>
		<c:if test="${not empty attributes}">
			<s:form action="attributeAction!retrieveAllAttributes.action"
					namespace="/product" method="post" name="form1" theme="simple" id="form1">
				<!-- 分页 -->
				<%@ include file="../../common/pager.jsp"%>
			</s:form>
			<br />
		</c:if>
		<table class="table table-bordered">
			<tr>
				<th>序号</th>
				<th>属性名称</th>
				<th>商品类型</th>
				<th>属性值录入方式</th>
				<th>可选值列表</th>

				<th>操作</th>
			</tr>

			<c:forEach var="bean" items="${attributes}"
				varStatus="status">
				<tr>
					<td>${status.count}</td>
					<td>${bean.attrName}</td>
					<td>${bean.productType.name}</td>
					<td>
					<c:if test="${bean.attrInputType eq '0'}">
					手工录入
					</c:if>
					<c:if test="${bean.attrInputType eq '1'}">
					选择录入
					</c:if>
					</td>
					<td>${bean.attrValue}</td>

					<td>
						<a class="btn btn-info btn-small" href="attributeAction!viewPage.action?id=${bean.id}">查看</a>
						&nbsp; &nbsp;
						<a class="btn btn-primary btn-small" href="attributeAction!retrieveAttributeById.action?id=${bean.id}&typeId=${bean.productType.id}">编辑</a>
						&nbsp; &nbsp;
						<a class="btn btn-danger btn-small" href="attributeAction!delAttribute.action?id=${bean.id}"
								 onclick="if(confirm('如果有订单或者购物车商品引用了该属性，则该属性不能被删除并且修改该属性的商品全为下架！')==false)return false;">删除</a>
					</td>
				</tr>
			</c:forEach>
		</table>

	</body>
</html>