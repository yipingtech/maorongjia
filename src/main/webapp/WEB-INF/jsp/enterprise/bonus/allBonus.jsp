<%--

	bonusRecord.jsp

	Create by MCGT

	Create time 2015-07-08

	Version 1.0

	Copyright 2013 Messcat, Inc. All rights reserved.

 --%>
<%@ page contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>代理商提成发放</title>
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
				当前位置: 会员管理 - 代理商提成发放
			</div>
			<div class="ropt">
				<!-- <a href="bonusRecordAction!newPage.action">新增</a> -->
			</div>
			<div class="clear"></div>
		</div>
		<div class="rhead">
		<form id="formType" name="formType" action="memberAction!sendAllAgentMoney.action" method="post" style="display:inline;">
			<!-- 条件搜索 -->
			  <input type="hidden" value="${mvList }" />
		      <input type="submit" value="下发所有提成"/>
		</form>
		<!-- <form action="memberAction!queryAllAgent.action" type="post" style="display:inline;">
		   <input type="submit" value="代理商"/>
		</form> -->
		</div>
		<c:if test="${not empty mvList}">
			<s:form action="memberAction!sendllAgentMoney.action"
					namespace="/bonus" method="post" name="form1" theme="simple" id="form1">
				<!-- 分页 -->
				<%@ include file="../../common/pager.jsp"%>
			</s:form>
			<br/>
		</c:if>
		<table class="table table-bordered">
			<tr id="nc" class="headbg">
				<th>序号</th>
				<!-- <th>代理区域</th> -->
				<th>代理商</th>
				<th>提成</th>

			</tr>

			<c:forEach var="bean" items="${mvList}"
				varStatus="status">
				<tr>
					<td>${status.count}</td>
					<%-- <td>${bean.member.agentArea}</td> --%>
					<td>${bean.member.nickname}</td>
					<td>${bean.money}</td>

					
				</tr>
			</c:forEach>

		</table>

		<c:if test="${not empty mvList}">
			<s:form action="memberAction!sendllAgentMoney.action"
					namespace="/bonus" method="post" name="form1" theme="simple" id="form1">
				<!-- 分页 -->
				<%@ include file="../../common/pager.jsp"%>
			</s:form>
		</c:if>
	</body>
</html>