<%@ page contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>MCCMS</title>
		<%@ include file="/common/taglibs.jsp"%>
		<%@ include file="/common/meta.jsp"%>
		<%@ include file="/common/content.jsp"%>
		<script>
			$(document).ready(function(){
				$("#mcParameterForm").validate({
					 rules: { 
			
					},
					success: function(label) {
						label.html("&nbsp;").addClass("checked");
			        }	
				});
				
			});		
		</script>
		<style type="text/css">
		.table.inner-table th{width:110px;}
		</style>
	</head>
	<body>
		<div class="rhead">
			<div class="rpos">
				当前位置: 系统配置 - 产品参数管理
			</div>
			<div class="ropt"><a class="btn" href="javascript:history.back();">返回</a></div>
		</div>

		<form action="mcParameterAction!editMcParameter.action" method="post" id="mcParameterForm" name="mcParameterForm" enctype="multipart/form-data">
			<table class="table table-bordered inner-table">
				<input type="hidden" name="id" value="${mcParameter.id}"/> 
				<tr>
					<th>中文字段名称</th>
					<td><input type="text" id="name" name="mcParameter.name" value="${mcParameter.name}" /></td>
				</tr>
				<tr>
					<th>调用代码</th>
					<td>${mcParameter.mark}</td>
				</tr>
				<tr>
					<th>字段排序</th>
					<td><input type="text" id="noOrder" name="mcParameter.noOrder" value="${mcParameter.noOrder}" class="{required:true,digits:true}"/></td>
				</tr>
				<tr>
					<th>字段类型</th>
					<td>
						<c:if test="${mcParameter.type==0}">简短字段</c:if>
						<c:if test="${mcParameter.type==1}">备用字段</c:if>
						<c:if test="${mcParameter.type==2}">上传字段</c:if>
					</td>
				</tr>
				<tr>
					<th>最大可输入字符串</th>
					<td>${mcParameter.maxsize}</td>
				</tr>
				<tr>
					<th>是否开启该字段</th>
					<td>
						<select name="mcParameter.wrOk">
							<option value="0">否</option>
							<option value="1" <c:if test="${mcParameter.wrOk==1}">selected</c:if>>是</option>
						</select>
					</td>
				</tr>

				<tr>
					<td colspan="2" style="text-align:center;">
						<input type="submit" class="btn" value="提 交 "/>
					</td>
				</tr>
		 	</table>
		</form>
	</body>
</html>