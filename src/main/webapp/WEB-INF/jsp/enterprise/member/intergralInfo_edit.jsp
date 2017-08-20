<%--

	intergralInfo_edit.jsp

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
				$("#intergralInfoForm").validate({
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
			<div class="ropt"><a class="btn" href="${ctx}/intergral/intergralInfoAction!retrieveAllIntergralInfos.action">返回</a></div>
		</div>

		<form action="intergralInfoAction!editIntergralInfo.action" method="post" id="intergralInfoForm" name="intergralInfoForm" enctype="multipart/form-data">
			<table class="table table-bordered inner-table">
				<input type="hidden" name="id" value="${intergralInfo.id}"/>
				<tr>
					<th>积分</th>
					<td><input type="text" id="intergral" name="intergralInfo.intergral" value="${intergralInfo.intergral}" /></td>
				</tr>
				<tr>
					<th>时间</th>
					<td><input type="text" id="addTime" name="intergralInfo.addTime" value="${intergralInfo.addTime}" /></td>
				</tr>
				<tr>
					<th>备注</th>
					<td><input type="text" id="comments" name="intergralInfo.comments" value="${intergralInfo.comments}" /></td>
				</tr>
				<tr>
					<th>使用状态（0：添加增多 1：使用减少）</th>
					<td><input type="text" id="intergralStatus" name="intergralInfo.intergralStatus" value="${intergralInfo.intergralStatus}" /></td>
				</tr>
				<tr>
					<th>积分记录状态（0：正常 1：删除）</th>
					<td><input type="text" id="status" name="intergralInfo.status" value="${intergralInfo.status}" /></td>
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