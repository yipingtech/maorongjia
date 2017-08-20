<%@ page contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>查看模板</title>
		<%@ include file="/common/taglibs.jsp"%>
		<%@ include file="/common/meta.jsp"%>
		<%@ include file="/common/content.jsp"%>
	</head>
	<body>
		<div class="rhead">
			<div class="rpos">当前位置: 会员管理(vip) - 会员详细信息 - 会员余额信息 - 余额详细信息</div>
			<div class="ropt"><a class="btn" href="javascript:history.back();">返回</a></div>
		</div>

		<table class="table table-bordered inner-table">
				<tr>
					<th>充值或消费金额</th>
					<td>${rechargeInfo.rechargeAmount}</td>
				</tr>
				<tr>
					<th>充值或消费时间</th>
					<td><fmt:formatDate value="${rechargeInfo.rechargeTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
				</tr>
				<tr>
					<th>充值支付方式</th>
					<td><c:if test="${rechargeInfo.rechargeType eq '0'}">微信支付 </c:if>
					<c:if test="${rechargeInfo.rechargeType eq '1'}">支付宝 </c:if>
					<c:if test="${rechargeInfo.rechargeType eq '2'}">银行卡 </c:if>
					</td>
				</tr>
				<tr>
					<th>充值使用卡号</th>
					<td>${rechargeInfo.rechargeCard}</td>
				</tr>
				<tr>
					<th>备注状态</th>
					<td><c:if test="${rechargeInfo.rechargeComments eq '0'}">充值 </c:if>
					<c:if test="${rechargeInfo.rechargeComments eq '1'}">消费 </c:if></td>
					
				</tr>

		</table>
	</body>
</html>