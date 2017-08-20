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
				当前位置: 会员管理(vip) - 红包发放
			</div>
			<div class="clear"></div>
		</div>
		<c:if test="${not empty ml}">
			<s:form action="commissionInfoAction!queryAllNestToSend.action"
					namespace="/member" method="post" name="form1" theme="simple" id="form1">
				<!-- 分页 -->
				<%@ include file="../../common/pager.jsp"%>
			</s:form>
			<br/>
		</c:if>
		<table class="table table-bordered">
			<tr>
				<th>序号</th>
				<th>会员账号</th>
				<th>会员昵称</th>
				<th>佣金待发放金额  &nbsp&nbsp(&nbsp发放金额为设置金额的倍数&nbsp)</th>
				<th>操作</th>

			</tr>

			<c:forEach var="bean" items="${ml}"
				varStatus="status">
				<tr>
				    <td>${status.count}</td>
					<td>${bean.loginName}</td>
					<td>${bean.nickname}</td>
					<td>${bean.commissionLine}</td>
					<td ><a href="commissionInfoAction!sendCommission.action?id=${bean.id }" style="color:red;">发放红包</a></td>
				</tr>
			</c:forEach>
		</table>

	</body>
</html>