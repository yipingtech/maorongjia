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
				当前位置: 会员管理(vip) - 会员详细信息 - 会员佣金信息
			</div>
			<!-- <div class="ropt">
				<a href="commissionInfoAction!newPage.action" class="btn">新增</a>
			</div> -->
			<div class="clear"></div>
		</div>
		<c:if test="${not empty commissionInfos}">
			<s:form action="commissionInfoAction!queryCommissionByMember.action"
					namespace="/member" method="post" name="form1" theme="simple" id="form1">
					<input type="hidden" name="member.loginName" value="${member.loginName}"/>
				<!-- 分页 -->
				<%@ include file="../../common/pager.jsp"%>
			</s:form>
			<br/>
		</c:if>
		<table class="table table-bordered">
			<tr>
				<th>序号</th>
				<th>会员</th>
				<th>佣金金额</th>
				<th>时间</th>
				<th>备注</th>

				<th>操作</th>
			</tr>

			<c:forEach var="bean" items="${commissionInfos}"
				varStatus="status">
				<tr>
					<td>${status.count}</td>
					<td>${bean.memberId.loginName}</td>
					<td><fmt:formatNumber type="number" value="${bean.commission}" maxFractionDigits="2"/></td>
					<td>${bean.addTime}</td>
					<td>${bean.commissionComments}</td>

					<td>
						<a class="btn btn-info btn-small" href="commissionInfoAction!viewPage.action?id=${bean.id}">查看</a>
						&nbsp; &nbsp;
						<a class="btn btn-primary btn-small" href="commissionInfoAction!retrieveCommissionInfoById.action?id=${bean.id}">编辑</a>
						&nbsp; &nbsp;
						<a class="btn btn-danger btn-small" href="commissionInfoAction!delCommissionInfo.action?id=${bean.id}"
								 onclick="return doYouWantToDel();">删除</a>
					</td>
				</tr>
			</c:forEach>
		</table>

	</body>
</html>