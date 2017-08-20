<%--

	bonusRecord_new.jsp

	Create by MCGT

	Create time 2015-07-08

	Version 1.0

	Copyright 2013 Messcat, Inc. All rights reserved.

 --%>
<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>添加模板</title>
		<%@ include file="/common/taglibs.jsp"%>
		<%@ include file="/common/meta.jsp"%>
		<%@ include file="/common/content.jsp"%>
		<script>
			$(document).ready(function(){
				$("#bonusRecordForm").validate({
					 rules: { 
			
					},
					success: function(label) {
						label.html("&nbsp;").addClass("checked");
			        }	
				});
				
			});		
		</script>
	</head>
	<body>
		<div class="rhead">
			<div class="rpos">当前位置: 模块 - 子模块</div>
			<div class="ropt"><input type="button" class="defaultButton" value="返回" onclick="location.href='${ctx}/bonus/bonusRecordAction!retrieveAllBonusRecords.action'"/></div>
		</div>

		<form action="bonusRecordAction!newBonusRecord.action" method="post" id="bonusRecordForm" name="bonusRecordForm" enctype="multipart/form-data">
			<table  align="center" class="listTable3">
				<tr>
					<td class="pn-flabel" width="100px">发放金额</td>
					<td><input type="text" id="money" name="bonusRecord.money" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">代理商ID</td>
					<td><input type="text" id="memberId" name="bonusRecord.memberId" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">发放时间</td>
					<td><input type="text" id="sendTime" name="bonusRecord.sendTime" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">状态（1.有效，0无效）</td>
					<td><input type="text" id="status" name="bonusRecord.status" /></td>
				</tr>

				<tr>
					<td></td>
					<td>
						<input type="submit" class="defaultButton" value=" 提 交 "/>
					</td>
				</tr>
		 	</table>
		</form>
	</body>
</html>