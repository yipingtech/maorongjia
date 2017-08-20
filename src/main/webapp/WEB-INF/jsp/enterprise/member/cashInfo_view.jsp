<%@ page contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>查看详情</title>
		<%@ include file="/common/taglibs.jsp"%>
		<%@ include file="/common/meta.jsp"%>
		<%@ include file="/common/content.jsp"%>
	</head>
	<body>
		<div class="rhead">
			<div class="rpos">当前位置: 会员管理(vip) - 提现申请- 查看详情 </div>
			<div class="ropt"><a class="btn" href="${ctx }/member/commissionInfoAction!queryAllCashApplyBySeller.action">返回</a></div>
		</div>

		<table class="table table-bordered inner-table">
				<tr>
					<th>用户</th>
					<td>${cashInfo.member.nickname}（${cashInfo.member.id }）</td>
				</tr>
				<tr>
					<th>手机号码</th>
					<td>${cashInfo.phoneNum }</td>
				</tr>
				<tr>
					<th>银行</th>
					<td>${cashInfo.bankCard.bankCardFlag }</td>
				</tr>
				<tr>
					<th>银行卡号</th>
					<td>${cashInfo.bankCard.bankCardNum }</td>
				</tr>
				<tr>
					<th>持卡人姓名</th>
					<td>${cashInfo.bankCard.bankCardMember }</td>
				</tr>
				<tr>
					<th>提现金额</th>
					<td>${cashInfo.drawAmount}</td>
				</tr>
				<tr>
					<th>申请时间</th>
					<td>${cashInfo.drawTime }</td>
				</tr>
				<tr>
					<th>处理时间</th>
					<td>${cashInfo.dealTime }</td>
				</tr>
				<tr>
					<th>备注</th>
					<td>${cashInfo.drawComments }</td>
				</tr>

		</table>
	</body>
</html>