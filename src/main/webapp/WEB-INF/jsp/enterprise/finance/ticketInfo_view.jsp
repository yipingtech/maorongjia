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
			<div class="rpos">会员管理（vip） - 查看卡券</div>
			<div class="ropt"><a class="btn" href="${ctx}/finance/ticketInfoAction!retrieveAllTicketInfos.action">返回</a></div>
		</div>

		<table class="table table-bordered inner-table">
				<tr>
					<th>金额</th>
					<td>${ticketInfo.ticketAmount}</td>
				</tr>
				<tr>
					<th>限制金额（满于多少元才能使用）</th>
					<td>${ticketInfo.restrictAmount}</td>
				</tr>
				<tr>
					<th>有效期（天）</th>
					<td>${ticketInfo.restrictDay}</td>
				</tr>

		</table>
	</body>
</html>