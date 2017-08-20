<%--

	bonus.jsp

	Create by MCGT

	Create time 2015-04-24

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
				<a href="bonusAction!newPage.action" class="btn">新增</a>
			</div>
			<div class="clear"></div>
		</div>
		<c:if test="${not empty bonuss}">
			<s:form action="bonusAction!retrieveAllBonuss.action"
					namespace="/finance" method="post" name="form1" theme="simple" id="form1">
				<!-- 分页 -->
				<%@ include file="../../common/pager.jsp"%>
			</s:form>
			<br />
		</c:if>
		<table class="table table-bordered">
			<tr>
				<th>序号</th>
				<th>金额</th>
				<th>0:删除；1:未删</th>

				<th>操作</th>
			</tr>

			<c:forEach var="bean" items="${bonuss}"
				varStatus="status">
				<tr>
					<td>${status.count}</td>
					<td>${bean.amount}</td>
					<td>${bean.status}</td>

					<td>
						<a class="btn btn-info btn-small" href="bonusAction!viewPage.action?id=${bean.id}">查看</a>
						&nbsp; &nbsp;
						<a class="btn btn-primary btn-small" href="bonusAction!retrieveBonusById.action?id=${bean.id}">编辑</a>
						&nbsp; &nbsp;
						<a class="btn btn-danger btn-small" href="bonusAction!delBonus.action?id=${bean.id}"
								 onclick="return doYouWantToDel();">删除</a>
					</td>
				</tr>
			</c:forEach>
		</table>
	</body>
</html>