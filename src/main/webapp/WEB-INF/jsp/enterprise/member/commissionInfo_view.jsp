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
			<div class="rpos">当前位置: 会员管理(vip) - 会员详细信息 - 会员佣金信息 - 佣金详细信息</div>
			<div class="ropt"><a class="btn" href="javascript:history.back();">返回</a></div>
		</div>

		<table class="table table-bordered inner-table">
				<tr>
					<th>开户行网点</th>
					<td>${cashInfo.bankCard.bankCardPoint}</td>
				</tr>
				<%-- <tr>
					<th>时间</th>
					<td><fmt:formatDate value="${commissionInfo.addTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
				</tr>
				<tr>
					<th>备注</th>
					<td>${commissionInfo.commissionComments}</td>
				</tr> --%>

		</table>
	</body>
</html>