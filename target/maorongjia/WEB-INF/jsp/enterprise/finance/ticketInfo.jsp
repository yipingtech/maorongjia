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
				当前位置: 会员管理（vip） - 卡券信息
			</div>
			<div class="ropt">
				<a href="ticketInfoAction!newPage.action" class="btn">新增</a>
			</div>
			<div class="clear"></div>
		</div>
		<c:if test="${not empty ticketInfos}">
			<s:form action="ticketInfoAction!retrieveAllTicketInfos.action"
					namespace="/finance" method="post" name="form1" theme="simple" id="form1">
				<!-- 分页 -->
				<%@ include file="../../common/pager.jsp"%>
			</s:form>
			<br/>
		</c:if>
		<table class="table table-bordered">
			<tr>
				<th>序号</th>
				<th>金额</th>
				<th>限制金额（满于多少元才能使用）</th>
				<th>有效期（天）</th>

				<th>操作</th>
			</tr>

			<c:forEach var="bean" items="${ticketInfos}"
				varStatus="status">
				<tr>
					<td>${status.count}</td>
					<td>${bean.ticketAmount}</td>
					<td>${bean.restrictAmount}</td>
					<td>${bean.restrictDay}</td>

					<td>
						<a class="btn btn-info btn-small" href="ticketInfoAction!viewPage.action?id=${bean.id}">查看</a>
						&nbsp; &nbsp;
						<a class="btn btn-primary btn-small" href="ticketInfoAction!retrieveTicketInfoById.action?id=${bean.id}">编辑</a>
						&nbsp; &nbsp;
						<a class="btn btn-danger btn-small" href="ticketInfoAction!delTicketInfo.action?id=${bean.id}"
								 onclick="return doYouWantToDel();">删除</a>
					</td>
				</tr>
			</c:forEach>
		</table>

	</body>
</html>