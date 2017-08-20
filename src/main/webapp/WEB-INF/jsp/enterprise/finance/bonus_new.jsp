<%--

	bonus_new.jsp

	Create by MCGT

	Create time 2015-04-24

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
				$("#bonusForm").validate({
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
			<div class="ropt"><a class="btn" href="${ctx}/finance/bonusAction!retrieveAllBonuss.action">返回</a></div>
		</div>

		<form action="bonusAction!newBonus.action" method="post" id="bonusForm" name="bonusForm" enctype="multipart/form-data">
			<table class="table table-bordered inner-table">
				<tr>
					<th>金额</th>
					<td><input type="text" id="amount" name="bonus.amount" /></td>
				</tr>
				<tr>
					<th>0:删除；1:未删</th>
					<td><input type="text" id="status" name="bonus.status" /></td>
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