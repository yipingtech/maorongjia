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
				当前位置: 会员管理 - 代理商业绩记录
			</div>
			<div class="ropt">
				<!-- <a href="bonusRecordAction!newPage.action">新增</a> -->
			</div>
			<div class="clear"></div>
		</div>
		<div class="rhead">
		<form id="formType" name="formType" action="memberAction!queryAllAgentRecord.action" method="post" style="display:inline;">
			<!-- 条件搜索 -->
		<!-- <label>代理商区域</label><input type="text" name="member.loginName"/> -->
		<span style="font-weight:bold">开始时间</span> &nbsp;&nbsp;
                	<input type="text" name="begin" onchange="checkDate()" id="starTime" value="${startTime}" readonly/>
						<input id="starTimeBt"  type="button" class="default_button" value="选择"/>
						<script type="text/javascript">
							Calendar.setup({
								inputField     : "starTime",
								button	  	   : "starTimeBt",
								/*ifFormat       :  "%Y-%m-%d %H:%M",							
						        showsTime      :    true,
						        timeFormat     :    "24"*/
								ifFormat       :    "%Y-%m-%d"
							});
						</script>
						&nbsp;&nbsp;
                	<span style="font-weight:bold">结束时间</span> &nbsp;&nbsp;
                	<input type="text" name="end" onchange="checkDate()" id="endTime" value="${endTime}" readonly/>
						<input id="endTimeBt" type="button" class="default_button" value="选择"/>
						<script type="text/javascript">
							Calendar.setup({
								inputField     : "endTime",
								button	  	   : "endTimeBt",
								/*ifFormat       :  "%Y-%m-%d %H:%M",							
						        showsTime      :    true,
						        timeFormat     :    "24"*/
								ifFormat       :    "%Y-%m-%d"
							});
						</script> 
		<input type="submit" value="搜索"/>
		</form>
		<!-- <form action="memberAction!queryAllAgent.action" type="post" style="display:inline;">
		   <input type="submit" value="代理商"/>
		</form> -->
		</div>
		<c:if test="${not empty mvList}">
			<s:form action="memberAction!queryAllAgentRecord.action"
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
				<th>总业绩</th>

			</tr>

			<c:forEach var="bean" items="${mvList}"
				varStatus="status">
				<tr>
					<td>${status.count}</td>
					<%-- <td>${bean.member.agentArea}</td> --%>
					<td>${bean.member.realname}</td>
					<td>${bean.money}</td>

					
				</tr>
			</c:forEach>

		</table>

	</body>
</html>