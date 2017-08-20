<%@ page contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>MCCMS</title>
		<%@ include file="/common/taglibs.jsp"%>
		<%@ include file="/common/meta.jsp"%>
		<%@ include file="/common/content.jsp"%>
	</head>
	<body>
 		<div class="rhead">
			<div class="rpos">当前位置: 界面风格 - 模板管理 - 查看模板信息</div>
			<div class="ropt"><a class="btn" href="javascript:history.back();">返回</a></div>
			<div class="clear"></div>
		</div>
		
		<table class="table table-bordered inner-table">
			<tr>
				<th>模板名称</th>
				<td>${webSkin.names}</td>
			</tr>
			<tr>
				<th>模板文件夹名称</th>
				<td>${webSkin.filename}</td>
			</tr>
			<tr>
				<th>模板描述</th>
				<td>${webSkin.content}</td>
			</tr>
			<tr>
				<th>状态</th>
				<td>
					<c:if test="${webSkin.state == 1}">已启用</c:if>
					<c:if test="${webSkin.state == 0}"><font color="red">已停用</font></c:if>
				</td>
			</tr>
	 	</table>
	</body>
</html>

