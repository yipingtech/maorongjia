<%--

	address.jsp

	Create by MCGT

	Create time 2015-04-17

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
				当前位置: 模块 - 子模块
			</div>
			<div class="ropt">
				<a href="addressAction!newPage.action" class="btn">新增</a>
			</div>
			<div class="clear"></div>
		</div>
		<c:if test="${not empty addresss}">
			<s:form action="addressAction!retrieveAllAddresss.action"
					namespace="/address" method="post" name="form1" theme="simple" id="form1">
				<!-- 分页 -->
				<%@ include file="../../common/pager.jsp"%>
			</s:form>
			<br/>
		</c:if>
		<table class="table table-bordered">
			<tr>
				<th>序号</th>
				<th>会员</th>
				<th>会员所在省</th>
				<th>会员所在市</th>
				<th>会员所在区</th>
				<th>会员所在详细地址</th>
				<th>新增时间</th>
				<th>编辑时间</th>
				<th>收货人姓名</th>
				<th>邮编</th>
				<th>固定电话</th>
				<th>移动电话</th>
				<th>邮箱</th>
				<th>是否为默认地址(1是0否)</th>

				<th>操作</th>
			</tr>

			<c:forEach var="bean" items="${addresss}"
				varStatus="status">
				<tr>
					<td>${status.count}</td>
					<td>${bean.member}</td>
					<td>${bean.province}</td>
					<td>${bean.city}</td>
					<td>${bean.area}</td>
					<td>${bean.address}</td>
					<td>${bean.addTime}</td>
					<td>${bean.editTime}</td>
					<td>${bean.consignee}</td>
					<td>${bean.postcode}</td>
					<td>${bean.telephone}</td>
					<td>${bean.cellphone}</td>
					<td>${bean.email}</td>
					<td>${bean.isdefault}</td>

					<td>
						<a class="btn btn-info btn-small" href="addressAction!viewPage.action?id=${bean.id}">查看</a>
						&nbsp; &nbsp;
						<a class="btn btn-primary btn-small" href="addressAction!retrieveAddressById.action?id=${bean.id}">编辑</a>
						&nbsp; &nbsp;
						<a class="btn btn-danger btn-small" href="addressAction!delAddress.action?id=${bean.id}"
								 onclick="return doYouWantToDel();">删除</a>
					</td>
				</tr>
			</c:forEach>
		</table>

	</body>
</html>