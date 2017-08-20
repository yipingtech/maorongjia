﻿<%--

	commissionInfo_edit.jsp

	Create by MCGT

	Create time 2015-04-22

	Version 1.0

	Copyright 2013 Messcat, Inc. All rights reserved.

 --%>
<%@ page contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>更新模板</title>
		<%@ include file="/common/taglibs.jsp"%>
		<%@ include file="/common/meta.jsp"%>
		<%@ include file="/common/content.jsp"%>
		<script>
			$(document).ready(function(){
				$("#commissionInfoForm").validate({
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
			<div class="ropt"><a class="btn" href="javascript:history.back();">返回</a></div>
		</div>

		<form action="commissionInfoAction!editCommissionInfo.action" method="post" id="commissionInfoForm" name="commissionInfoForm" enctype="multipart/form-data">
			<table class="table table-bordered inner-table">
				<input type="hidden" name="id" value="${commissionInfo.id}"/>
				<tr>
					<th>佣金金额</th>
					<td><input type="text" id="commission" name="commissionInfo.commission" value="${commissionInfo.commission}" /></td>
				</tr>
				<tr>
					<th>时间</th>
					<td><input type="text" id="addTime" name="commissionInfo.addTime" value="${commissionInfo.addTime}" /></td>
				</tr>
				<tr>
					<th>佣金状态（0：添加 1：消费提现）</th>
					<td><input type="text" id="commissionStatus" name="commissionInfo.commissionStatus" value="${commissionInfo.commissionStatus}" /></td>
				</tr>
				<tr>
					<th>状态（0：正常 1：删除）</th>
					<td><input type="text" id="status" name="commissionInfo.status" value="${commissionInfo.status}" /></td>
				</tr>

				<tr>
					<td colspan="2" style="text-align:center;">
						<input type="submit" class="btn" value="提 交"/>
					</td>
				</tr>
		 	</table>
		</form>
	</body>
</html>