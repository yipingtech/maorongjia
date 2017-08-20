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
				当前位置: 会员管理(vip) - 红包发放记录
			</div>
			<div class="clear"></div>
		</div>
		<c:if test="${not empty commissionInfos}">
			<s:form action="commissionInfoAction!queryAllCommissionInfo.action"
					namespace="/member" method="post" name="form1" theme="simple" id="form1">
				<!-- 分页 -->
				<%@ include file="../../common/pager.jsp"%>
			</s:form>
			<br />
		</c:if>
		<table class="table table-bordered">
			<tr>
				<th>序号</th>
				<th>红包接收人</th>
				<th>昵称</th>
				<th>红包金额</th>
				<th>处理时间</th>
				<th>备注</th>
			</tr>

			<c:forEach var="bean" items="${commissionInfos}"
				varStatus="status">
				<tr>
					<td>${status.count}</td>
					<td>${bean.memberId.loginName}</td>
					<td>${bean.memberId.nickname}</td>
					<td>${bean.commission}</td>
					<td>${bean.addTime}</td>
					<td>${bean.commissionComments}</td>
				</tr>
			</c:forEach>
		</table>

	</body>
</html>