<%--

	pageType.jsp

	Create by MCGT

	Create time 2013-08-20

	Version 1.0

	Copyright 2013 Messcat, Inc. All rights reserved.

 --%>
<%@ page contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>模板</title>
		<%@ include file="/common/taglibs.jsp"%>
		<%@ include file="/common/meta.jsp"%>
		<%@ include file="/common/content.jsp"%>
		<link type="text/css" href="${ctx}/resource/AsyncBox/skins/ZCMS/asyncbox.css" rel="stylesheet" />
		<script type="text/javascript" src="${ctx}/resource/AsyncBox/AsyncBox.v1.4.js"></script>

		<script>
			function doYouWantToDel() {
				if(confirm('您真的要删除吗？')) {
					return true;
				}
				return false;
			}
		</script>
	</head>
	<body style="padding:8px;">
		<c:if test="${not empty message}">
			<script>
				asyncbox.tips('${message}', 'error');
			</script>
		</c:if>
		<div class="rhead">
			<div class="rpos">当前位置: 系统配置 - 页面类型管理</div>
			<div class="ropt"><a href="pageTypeAction!newPage.action" class="btn">新增</a></div>
			<div class="clear"></div>
		</div>
		
		<table class="table table-bordered">
			<tr>
				<th>序号</th>
				<th>类型名称</th>
				<th>模板类型</th>
				<th>URL生成模板</th>
				<th>简介</th>

				<th>操作</th>
			</tr>

			<c:forEach var="bean" items="${pageTypes}"
				varStatus="status">
				<tr>
					<td>${status.count}</td>
					<td>${bean.name}</td>
					<td>${bean.templateType}</td>
					<td>${bean.templateUrl}</td>
					<td>${bean.intro}</td>

					<td>
						<a class="btn btn-info btn-small" href="pageTypeAction!viewPage.action?id=${bean.id}">查看</a>
						&nbsp; &nbsp;
						<a class="btn btn-primary btn-small" href="pageTypeAction!retrievePageTypeById.action?id=${bean.id}">编辑</a>
						&nbsp; &nbsp;
						<a class="btn btn-danger btn-small" href="pageTypeAction!delPageType.action?id=${bean.id}" onclick="return doYouWantToDel();">删除</a>
					</td>
				</tr>
			</c:forEach>
		</table>

		<c:if test="${not empty pageTypes}">
			<s:form action="pageTypeAction!retrieveAllPageTypes.action"
					method="post" name="form1" theme="simple" id="form1">
				<!-- 分页 -->
				<%@ include file="../../common/pager.jsp"%>
			</s:form>
		</c:if>
	</body>
</html>