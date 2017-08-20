<%--

	bankCard_view.jsp

	Create by MCGT

	Create time 2015-08-07

	Version 1.0

	Copyright 2013 Messcat, Inc. All rights reserved.

 --%>
<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>查看模板</title>
		<%@ include file="/common/taglibs.jsp"%>
		<%@ include file="/common/meta.jsp"%>
		<%@ include file="/common/content.jsp"%>
		<script>
		</script>
	</head>
	<body>
		<div class="rhead">
			<div class="rpos">当前位置: 模块 - 子模块</div>
			<div class="ropt"><input type="button" class="defaultButton" value="返回" onclick="location.href='${ctx}/bankcard/bankCardAction!retrieveAllBankCards.action'"/></div>
		</div>

		<table  align="center" class="listTable3">
				<tr>
					<td class="pn-flabel" width="100px">开户银行（工商）</td>
					<td>${bankCard.bankCardFlag}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">银行卡号</td>
					<td>${bankCard.bankCardNum}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">持卡人</td>
					<td>${bankCard.bankCardMember}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">银行卡开户省市地址</td>
					<td>${bankCard.bankCardAddress}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">银行卡开户网点</td>
					<td>${bankCard.bankCardPoint}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">银行卡添加时间或为最后编辑时间（默认银行卡）</td>
					<td>${bankCard.bankCardTime}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">备用/备注字段</td>
					<td>${bankCard.bankCardRemarks}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">状态</td>
					<td>${bankCard.status}</td>
				</tr>

		 </table>
	</body>
</html>