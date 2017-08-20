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
			<div class="rpos">
				当前位置: 系统配置 - 角色管理 - 查看角色信息
			</div>
			<div class="ropt"><a class="btn" href="javascript:history.back();">返回</a></div>
			<div class="clear"></div>
	  	</div>
	  
	  <table class="table table-bordered inner-table">
			<tr>
				<th>角色名称</th>
				<td>${roles.name}</td>
			</tr>
			<tr>
				<th>授权</th>
				<td>
					<c:forEach var="ra" items="${roles.rolesAuthoritieses}"  varStatus="status">
						<c:if test="${status.count==10}"><br/></c:if>
						${ra.authorities.displayName}&nbsp;&nbsp;						
					</c:forEach>
				</td>
			</tr>
			<tr>
				<th>
					状态
				</th>
				<td>
					<c:if test="${roles.state == 1}">
						已启用
					</c:if>
					<c:if test="${roles.state == 0}">
						<font color="red">已停用</font>
					</c:if>
				</td>
			</tr>
	 	</table>
	</body>
</html>

