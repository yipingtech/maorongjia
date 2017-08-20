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
				当前位置: 会员管理(vip) - 提现申请
			</div>
			<div class="clear"></div>
		</div>
		<c:if test="${not empty cashInfos}">
			<s:form action="commissionInfoAction!updateCashApplyBySeller.action"
					namespace="/member" method="post" name="form1" theme="simple" id="form1">
				<!-- 分页 -->
				<%@ include file="../../common/pager.jsp"%>
			</s:form>
			<br/>
		</c:if>
		<table class="table table-bordered">
			<tr>
				<th>序号</th>
				<th>申请人</th>
				<th>提现金额</th>
				<th>持卡人</th>
				<th>提现账号</th>
				<th>银行</th>
				<th>申请时间</th>
				<th>处理时间</th>
				<th>备注</th>
				<th>操作</th>
			</tr>

			<c:forEach var="bean" items="${cashInfos}"
				varStatus="status">
				<tr>
					<td>${status.count}</td>
					<td>${bean.member.nickname}</td>
					<td style="color:red;">${bean.drawAmount}</td>
					<td>${bean.bankCard.bankCardMember}</td>
					<td style="color: orange;">${bean.bankCard.bankCardNum}</td>
					<td>${bean.bankCard.bankCardFlag}</td>
					<td><fmt:formatDate value="${bean.drawTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td style="color:red;"><fmt:formatDate value="${bean.dealTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td>
					   <c:if test="${bean.drawComments eq '0'}">
					             处理提现失败
					   </c:if>
					   <c:if test="${bean.drawComments eq '提现成功'}">
					             处理提现成功
					   </c:if>
					</td>
					<td>
						<a class="btn btn-info btn-small" href="commissionInfoAction!viewPageByCashId.action?id=${bean.id}">查看</a>
						&nbsp; &nbsp; 
						<c:if test="${empty bean.dealTime}">
							<c:if test="${bean.status eq '2'}">
								<a class="btn btn-primary btn-small" href="commissionInfoAction!updateCashApplyBySeller.action?id=${bean.id}" onClick="return confirm('确定提现成功?');">通过</a>&nbsp;&nbsp;&nbsp;
								<a class="btn btn-danger btn-small" href="commissionInfoAction!failToCash.action?id=${bean.id}" onClick="return confirm('确定审核不通过?');">不通过</a>
							</c:if>
						</c:if>
						&nbsp; &nbsp;
						<%-- <a class="btn btn-danger btn-small" href="commissionInfoAction!delCommissionInfo.action?id=${bean.id}"
								 onclick="return doYouWantToDel();">删除</a> --%>
					</td>
				</tr>
			</c:forEach>
		</table>

	</body>
</html>