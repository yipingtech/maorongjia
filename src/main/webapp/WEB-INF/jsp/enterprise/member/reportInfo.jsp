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
				当前位置: 会员管理(vip) - 会员详细信息 - 会员签到信息
			</div>
			<div class="ropt">
				<a href="reportInfoAction!newPage.action" class="btn">新增</a>
			</div>
			<div class="clear"></div>
		</div>
		<c:if test="${not empty reportInfos}">
			<s:form action="reportInfoAction!retrieveAllReportByMember.action"
					namespace="/member" method="post" name="form1" theme="simple" id="form1">
					<input type="hidden" name="member.loginName" value="${member.loginName}"/>
				<!-- 分页 -->
				<%@ include file="../../common/pager.jsp"%>
			</s:form>
			<br />
		</c:if>
		<table class="table table-bordered">
			<tr>
				<th>序号</th>
				<th>签到时间</th>
				<th>当天签到状态</th>
			</tr>

			<c:forEach var="bean" items="${reportInfos}"
				varStatus="status">
				<tr>
					<td>${status.count}</td>
					<td>${bean.reportTime}</td>
					<td>
					<c:if test="${bean.reportStatus eq '0'}">未签</c:if>
					<c:if test="${bean.reportStatus eq '1'}">已签</c:if>
					</td>
				</tr>
			</c:forEach>
		</table>

	</body>
</html>