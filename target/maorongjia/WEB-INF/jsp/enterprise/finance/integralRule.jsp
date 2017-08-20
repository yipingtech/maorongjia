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
				当前位置: 会员管理（VIP） - 积分规则
			</div>
			<div class="ropt">
				<a href="integralRuleAction!newPage.action" class="btn">新增</a>
			</div>
			<div class="clear"></div>
		</div>
		<c:if test="${not empty integralRules}">
			<s:form action="integralRuleAction!retrieveAllIntegralRules.action"
					namespace="/member" method="post" name="form1" theme="simple" id="form1">
				<!-- 分页 -->
				<%@ include file="../../common/pager.jsp"%>
			</s:form>
			<br/>
		</c:if>
		<table class="table table-bordered">
			<tr>
				<th>序号</th>
				<th>动作名称</th>
				<th>周期（以天为单位）</th>
				<th>周期内最多次数</th>
				<th>获取积分</th>

				<th>操作</th>
			</tr>

			<c:forEach var="bean" items="${integralRules}"
				varStatus="status">
				<tr>
					<td>${status.count}</td>
					<td>${bean.name}</td>
					<td>${bean.period}</td>
					<td>${bean.maxtmie}</td>
					<td>${bean.integral}</td>

					<td>
						<a class="btn btn-info btn-small" href="integralRuleAction!viewPage.action?id=${bean.id}">查看</a>
						&nbsp; &nbsp;
						<a class="btn btn-primary btn-small" href="integralRuleAction!retrieveIntegralRuleById.action?id=${bean.id}">编辑</a>
						&nbsp; &nbsp;
						<a class="btn btn-danger btn-small" href="integralRuleAction!delIntegralRule.action?id=${bean.id}"
								 onclick="return doYouWantToDel();">删除</a>
					</td>
				</tr>
			</c:forEach>
		</table>

	</body>
</html>