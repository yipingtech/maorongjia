<%@ page contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>MCCMS</title>
		<%@ include file="/common/taglibs.jsp"%>
		<%@ include file="/common/meta.jsp"%>
		<%@ include file="/common/content.jsp"%>

		<script>
			function doYouWantToDel() {
				if(confirm('如果有订单或者购物车存在该商品，则该商品改为下架商品并且不能被删除！')) {
					return true;
				}
				return false;
			}
		</script>
	</head>
	<body>
		<div class="rhead">
			<div class="rpos">
				当前位置: 内容管理 -
				<c:if test="${empty column.names}">产品库</c:if>${column.names}</div>
					<div class="ropt">
						<a href="${ctx}/collection/mcProductInfoAction!newPage.action?father=${column.id}" class="btn">新增</a>
					</div>
			<div class="clear"></div>
		</div>
		<form action="mcProductInfoAction!query.action" method="post">
			<input type="hidden" name="father" value="<c:if test="${empty column.id}">0</c:if><c:if test="${not empty column.id}">${column.id}</c:if>" />
			<table class="table table-bordered" style="margin-bottom:5px;">
				<tr>
					<td class="pn-flabel" style="text-align:left;padding-left:15px;">
						标题：<input type="text" name="title" value="${title}" />
						<input type="submit" style="margin-left:30px;" class="btn" value='查询' />
					</td>
				</tr>
			</table>
		</form>
		<c:if test="${not empty mcProductInfos}">
			<s:form action="mcProductInfoAction!query" method="post" id="form1" name="form1" theme="simple">
				<input type="hidden" name="father" value="${father}"/>
				<input type="hidden" name="title" value="<c:if test="${not empty title}">${title}</c:if>" />
			
				<!-- 分页 -->
				<%@ include file="../../common/pager.jsp"%>
				
			</s:form>
			<br />
		</c:if>
		
		<table class="table table-bordered">
			<tr>
				<th>序号</th>
				<th>产品名称</th>
				<th>最新产品</th>
				<th>推荐产品</th>
				<th>更新时间</th>
				<th>操作</th>
			</tr>

			<c:forEach var="bean" items="${mcProductInfos}"
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
						<td><fmt:formatDate value="${bean.updatetime}" pattern="yyyy-MM-dd" /></td>
	
						<td>
							<a class="btn btn-info btn-small" href="mcProductInfoAction!viewPage.action?id=${bean.id}">查看</a>
							&nbsp; &nbsp;
							<a class="btn btn-primary btn-small" href="mcProductInfoAction!retrieveMcProductInfoById.action?id=${bean.id}">编辑</a>
							&nbsp; &nbsp;
							<%-- <c:if test="${bean.isSale eq '1'}"> --%>
								<a class="btn btn-danger btn-small" href="mcProductInfoAction!delMcProductInfo.action?father=${father }&&id=${bean.id}"
										onclick="return doYouWantToDel();">删除</a>
							<%-- </c:if> --%>
						</td>
					</tr>
			</c:forEach>
		</table>

	</body>
</html>