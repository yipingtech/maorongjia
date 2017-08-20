<%--

	address_view.jsp

	Create by MCGT

	Create time 2015-04-17

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
			<div class="ropt"><a class="btn" href="${ctx}/address/addressAction!retrieveAllAddresss.action">返回</a></div>
		</div>

		<table class="table table-bordered inner-table">
				<tr>
					<th>会员</th>
					<td>${address.member}</td>
				</tr>
				<tr>
					<th>会员所在省</th>
					<td>${address.province}</td>
				</tr>
				<tr>
					<th>会员所在市</th>
					<td>${address.city}</td>
				</tr>
				<tr>
					<th>会员所在区</th>
					<td>${address.area}</td>
				</tr>
				<tr>
					<th>会员所在详细地址</th>
					<td>${address.address}</td>
				</tr>
				<tr>
					<th>新增时间</th>
					<td>${address.addTime}</td>
				</tr>
				<tr>
					<th>编辑时间</th>
					<td>${address.editTime}</td>
				</tr>
				<tr>
					<th>收货人姓名</th>
					<td>${address.consignee}</td>
				</tr>
				<tr>
					<th>邮编</th>
					<td>${address.postcode}</td>
				</tr>
				<tr>
					<th>固定电话</th>
					<td>${address.telephone}</td>
				</tr>
				<tr>
					<th>移动电话</th>
					<td>${address.cellphone}</td>
				</tr>
				<tr>
					<th>邮箱</th>
					<td>${address.email}</td>
				</tr>
				<tr>
					<th>是否为默认地址(1是0否)</th>
					<td>${address.isdefault}</td>
				</tr>

		</table>
	</body>
</html>