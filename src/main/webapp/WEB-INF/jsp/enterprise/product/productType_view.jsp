<%--

	productType_view.jsp

	Create by MCGT

	Create time 2015-04-09

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
			<div class="rpos">当前位置: 模块 - 子模块</div>
			<div class="ropt"><a class="btn" href="${ctx}/product/productTypeAction!retrieveAllProductTypes.action">返回</a></div>
		</div>

		<table class="table table-bordered inner-table">
				<tr>
					<th>类型名称</th>
					<td>${productType.name}</td>
				</tr>
				<tr>
					<th>商品属性分组</th>
					<td>${productType.group}</td>
				</tr>
				<tr>
					<th>类型状态（0:不可选；1:可选）</th>
					<td>${productType.status}</td>
				</tr>

		</table>
	</body>
</html>