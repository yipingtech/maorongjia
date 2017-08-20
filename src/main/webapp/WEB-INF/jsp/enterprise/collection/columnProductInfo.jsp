<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/common/content.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>MCCMS</title>
		<script>
			function doYouWantToDel() {
				if(confirm('您真的要删除吗？')) {
					return true;
				}
				return false;
			}
		</script>
	</head>
	<body>
		<div class="rhead">
			<div class="rpos">
				<!-- 当前位置: 内容管理 -->当前位置: 内容管理 - 
				<c:if test="${empty column.names}"><!-- 总站 -->总站</c:if>${column.names}</div>
			<div class="clear"></div>
		</div>
			<c:if test="${not empty readyProductColumns}">
			<s:form action="productColumnAction!queryProductColumn" method="post" id="form1" name="form1" theme="simple">
				<input type="hidden" name="productInfo.title" value="${productInfo.title}" />
				<input type="hidden" name="colId" value="${colId}" />
				<!-- 分页 -->
				<%@ include file="../../common/pager.jsp"%>
				
			</s:form>
			<br />
		</c:if>
		<table class="table table-bordered">
			<tr>
				<th>序号</th>
				<th>产品名称</th>
				<th>新品</th>
				<th>推荐</th>
				<th>操作</th>
			</tr>

			<c:forEach var="bean" items="${readyProductColumns}" varStatus="status">
				<tr>
					<td>${status.count}</td>
					<td><font style="font-weight:bold;overflow:hidden;">[${bean.enterpriseColumn.names}]</font>${bean.mcProduct.title}</td>
					<td>
						<c:if test="${bean.mcProduct.isNew==0}"><!-- 否 -->否</c:if>
						<c:if test="${bean.mcProduct.isNew==1}"><!-- 是 -->是</c:if>
					</td>
					<td>
						<c:if test="${bean.mcProduct.isSale==0}"><!-- 否 -->否</c:if>
						<c:if test="${bean.mcProduct.isSale==1}"><!-- 是 -->是</c:if>
					</td>
					<td>
						<a href="mcProductInfoAction!viewPage.action?id=${bean.mcProduct.id}"><!-- 查看  -->查看</a>
						&nbsp; &nbsp;
						<a href="mcProductInfoAction!retrieveMcProductInfoById.action?id=${bean.mcProduct.id}&colId=${bean.enterpriseColumn.id}"><!-- 编辑  -->编辑</a>
						&nbsp; &nbsp;
						<a href="productColumnAction!delProductColumn.action?id=${bean.id}&colId=${colId}"
								onclick="return doYouWantToDel();"><!-- 删除  -->删除</a>
					</td>
				</tr>
			</c:forEach>

		</table>

		<c:if test="${not empty readyProductColumns}">
			<s:form action="productColumnAction!queryProductColumn" method="post" id="form1" name="form1" theme="simple">
				<input type="hidden" name="productInfo.title" value="${productInfo.title}" />
				<input type="hidden" name="colId" value="${colId}" />
				<!-- 分页 -->
				<%@ include file="../../common/pager.jsp"%>
				
			</s:form>
		</c:if>
		
		<div class="rhead" style="margin-top: 50px; margin-bottom: 0;">
			<table  width="100%">
				<tr>
					<td width="20%"><!-- 未关联的产品列表  -->未关联的产品列表</td>
					<td width="80%">
						<form action="productColumnAction!queryProductColumn.action" id="searchResForm">
							<!-- 按产品名称搜索  -->按产品名称搜索 
							<input type="hidden" name="colId" value="<c:if test="${empty colId}">0</c:if><c:if test="${not empty colId}">${colId}</c:if>" />
							<input type="text" name="productInfo.title" value=""/>
							<input type="submit" class="defaultButton" value='搜索'/>
						</form>
					</td>
				</tr>
			</table>
		</div>
			<c:if test="${not empty noProductColumns}">
			<br/>
			<s:form action="productColumnAction!queryProductColumn.action"
					namespace="/collection" method="post" name="pageform2" theme="simple" id="pageform2">
				<!-- 分页 -->
				<%@ include file="../../common/pager2.jsp"%>
				<input type="hidden" name="colId" value="${colId}"/>
			</s:form>
			<br />
		</c:if>
		<table class="table table-bordered">
			<tr>
				<th>序号</th>
				<th>产品名称</th>
				<th>新品</th>
				<th>推荐</th>
				<th>操作</th>
			</tr>

			<c:forEach var="bean" items="${noProductColumns}"
				varStatus="status">
				<tr>
					<td>${status.count}</td>
					<td>${bean.title}</td>
					<td>
						<c:if test="${bean.isNew==0}">否</c:if>
						<c:if test="${bean.isNew==1}">是</c:if>
					</td>
					<td>
						<c:if test="${bean.isSale==0}">否</c:if>
						<c:if test="${bean.isSale==1}">是</c:if>
					</td>
					<td>
						<a href="mcProductInfoAction!viewPage.action?id=${bean.id}"><!-- 查看  -->查看</a>
						<form action="productColumnAction!newProductColumn.action" id="addResForm${status.count}" >
							<input type="hidden" name="productColumn.mcProduct.id" value="${bean.id}"/>
							<input type="hidden" name="productColumn.enterpriseColumn.id" value="${colId}"/>
							<input type="hidden" name="colId" value="${colId}"/>
							<input type="submit"  value='添加' />
						</form>
						
					</td>
				</tr>
			</c:forEach>
		</table>
		<c:if test="${not empty noProductColumns}">
			<s:form action="productColumnAction!queryProductColumn.action"
					namespace="/collection" method="post" name="pageform2" theme="simple" id="pageform2">
				<!-- 分页 -->
				<%@ include file="../../common/pager2.jsp"%>
				<input type="hidden" name="colId" value="${colId}"/>
			</s:form>
		</c:if>
		
	</body>
</html>