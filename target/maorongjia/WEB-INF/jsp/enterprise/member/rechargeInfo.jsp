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
				当前位置: 会员管理(vip) - 会员详细信息 - 会员余额信息
			</div>
			<div class="ropt">
				<a href="rechargeInfoAction!newPage.action" class="btn">新增</a>
			</div>
			<div class="clear"></div>
		</div>
		<c:if test="${not empty rechargeInfos}">
			<s:form action="rechargeInfoAction!queryRechargeInfoByMember.action"
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
				<th>充值或消费金额</th>
				<th>充值或消费时间</th>
				<th>充值支付方式</th>
				<th>充值使用卡号</th>
				<th>备注状态</th>

				<th>操作</th>
			</tr>

			<c:forEach var="bean" items="${rechargeInfos}"
				varStatus="status">
				<tr>
					<td>${status.count}</td>
					<td>${bean.memberId.loginName}</td>
					<td>${bean.rechargeAmount}</td>
					<td><fmt:formatDate value="${bean.rechargeTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td><c:if test="${bean.rechargeType eq '0'}">微信支付 </c:if>
					<c:if test="${bean.rechargeType eq '1'}">支付宝 </c:if>
					<c:if test="${bean.rechargeType eq '2'}">银行卡 </c:if>
					</td>
					<td>${bean.rechargeCard}</td>
					<td><c:if test="${bean.rechargeComments eq '0'}">充值 </c:if>
					<c:if test="${bean.rechargeComments eq '1'}">消费 </c:if></td>

					<td>
						<a class="btn btn-info btn-small" href="rechargeInfoAction!viewPage.action?id=${bean.id}">查看</a>
						&nbsp; &nbsp;
						<a class="btn btn-primary btn-small" href="rechargeInfoAction!retrieveRechargeInfoById.action?id=${bean.id}">编辑</a>
						&nbsp; &nbsp;
						<a class="btn btn-danger btn-small" href="rechargeInfoAction!delRechargeInfo.action?id=${bean.id}"
								 onclick="return doYouWantToDel();">删除</a>
					</td>
				</tr>
			</c:forEach>
		</table>

	</body>
</html>