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
				当前位置: 会员管理(vip) - 会员详细信息- 会员积分
			</div>
			<div class="ropt">
				<a href="intergralInfoAction!newPage.action" class="btn">新增</a>
			</div>
			<div class="clear"></div>
		</div>
		<c:if test="${not empty intergralInfos}">
			<s:form action="intergralInfoAction!queryAllIntergralByMember.action"
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
				<th>积分</th>
				<th>时间</th>
				<th>备注</th>
				<th>使用状态</th>

				<th>操作</th>
			</tr>

			<c:forEach var="bean" items="${intergralInfos}"
				varStatus="status">
				<tr>
					<td>${status.count}</td>
					<td>${bean.memberId.loginName}</td>
					<td>${bean.intergral}</td>
					<td><fmt:formatDate value="${bean.addTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td>${bean.comments}</td>
					<td>
					<c:if test="${bean.intergralStatus eq '0'}">添加</c:if>
					<c:if test="${bean.intergralStatus eq '1'}">消费</c:if>
					</td>

					<td>
						<a class="btn btn-info btn-small" href="intergralInfoAction!viewPage.action?id=${bean.id}">查看</a>
						&nbsp; &nbsp;
						<a class="btn btn-primary btn-small" href="intergralInfoAction!retrieveIntergralInfoById.action?id=${bean.id}">编辑</a>
						&nbsp; &nbsp;
						<a class="btn btn-danger btn-small" href="intergralInfoAction!delIntergralInfo.action?id=${bean.id}"
								 onclick="return doYouWantToDel();">删除</a>
					</td>
				</tr>
			</c:forEach>
		</table>
	</body>
</html>