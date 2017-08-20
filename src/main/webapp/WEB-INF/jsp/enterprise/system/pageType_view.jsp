<%--

	pageType_view.jsp

	Create by MCGT

	Create time 2013-08-20

	Version 1.0

	Copyright 2013 Messcat, Inc. All rights reserved.

 --%>
<%@ page contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>查看模板</title>
		<%@ include file="/common/taglibs.jsp"%>
		<%@ include file="/common/meta.jsp"%>
		<%@ include file="/common/content.jsp"%>
	</head>
	<body>
		<div class="rhead">
			<div class="rpos">
				当前位置: 系统配置 - 页面类型管理 - 查看页面类型信息
			</div>
			<div class="ropt"><a class="btn" href="${ctx}/pageTypeAction!retrieveAllPageTypes.action">返回</a></div>
		</div>

		<table class="table table-bordered inner-table">
				<tr>
					<th>类型名称</th>
					<td>${pageType.name}</td>
				</tr>
				<tr>
					<th>模板类型</th>
					<td>${pageType.templateType}</td>
				</tr>
				<c:if test="${pageType.templateType != 'link'}">
					<tr>
						<th>URL生成模板</th>
						<td>${pageType.templateUrl}</td>
					</tr>
				</c:if>
				<c:if test="${pageType.templateType == 'other'}">
					<tr>
						<th>新功能URL</th>
						<td>${pageType.featuresUrl}</td>
					</tr>
				</c:if>
				<tr>
					<th>简介</th>
					<td>${pageType.intro}</td>
				</tr>

		 </table>
	</body>
</html>