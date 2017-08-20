<%--

	bankCard.jsp

	Create by MCGT

	Create time 2015-08-07

	Version 1.0

	Copyright 2013 Messcat, Inc. All rights reserved.

 --%>
<%@ page contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>模板</title>
		<%@ include file="/common/taglibs.jsp"%>
		<%@ include file="/common/meta.jsp"%>
		<%@ include file="/common/content.jsp"%>
		<style>
			.headbg1 {
				background: #c9e6f5;
				font-weight: bold;
				padding-left: 15px;
			}
			
			.headbg1 td {
				font-weight: bold;
				text-align: center;
			}
		</style>

		<script>
		</script>
	</head>
	<body>
		<div class="rhead">
			<div class="rpos">
				当前位置: 模块 - 子模块
			</div>
			<div class="ropt">
				<a href="bankCardAction!newPage.action">新增</a>
			</div>
			<div class="clear"></div>
		</div>
		<c:if test="${not empty bankCards}">
			<s:form action="bankCardAction!retrieveAllBankCards.action"
					namespace="/bankcard" method="post" name="form1" theme="simple" id="form1">
				<!-- 分页 -->
				<%@ include file="../../common/pager.jsp"%>
			</s:form>
			<br/>
		</c:if>
		<table class="listTable3" onmouseover="changeto()" onmouseout="changeback()">
			<tr id="nc" class="headbg">
				<td>序号</td>
				<td>开户银行（工商）</td>
				<td>银行卡号</td>
				<td>持卡人</td>
				<td>银行卡开户省市地址</td>
				<td>银行卡开户网点</td>
				<td>银行卡添加时间或为最后编辑时间（默认银行卡）</td>
				<td>备用/备注字段</td>
				<td>状态</td>

				<td>操作</td>
			</tr>
		
			<c:forEach var="bean" items="${bankCards}"
				varStatus="status">
				<tr>
					<td>${status.count}</td>
					<td>${bean.bankCardFlag}</td>
					<td>${bean.bankCardNum}</td>
					<td>${bean.bankCardMember}</td>
					<td>${bean.bankCardAddress}</td>
					<td>${bean.bankCardPoint}</td>
					<td>${bean.bankCardTime}</td>
					<td>${bean.bankCardRemarks}</td>
					<td>${bean.status}</td>

					<td>
						<a href="bankCardAction!viewPage.action?id=${bean.id}">查看</a>
						&nbsp; &nbsp;
						<a href="bankCardAction!retrieveBankCardById.action?id=${bean.id}">编辑</a>
						&nbsp; &nbsp;
						<a href="bankCardAction!delBankCard.action?id=${bean.id}"
								onclick="{if(confirm('你真的要删除吗?')){return true;}return false;}">删除</a>
					</td>
				</tr>
			</c:forEach>

		</table>

	</body>
</html>