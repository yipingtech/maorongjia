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
				当前位置: 系统配置 - 用户管理 - 查看用户信息
			</div>
			<div class="ropt"><a class="btn" href="javascript:history.back();">返回</a></div>
			<div class="clear"></div>
		</div>
	  <table class="table table-bordered inner-table">
			<tr>
				<th>用户名</th>
				<td>${tusers.loginName}</td>
			</tr>
			<tr>
				<th>真实名字</th>
				<td>${tusers.name}</td>
			</tr>
			<!-- <tr>
				<th>性别</td><td>
				<c:if test="${tusers.sex==1}">男</c:if>
				<c:if test="${tusers.sex==0}">女</c:if>
				<c:if test="${tusers.sex!=0 and tusers.sex!=1}">未知</c:if>
				</td>
			</tr> -->
			<tr>
				<th>地区</th>
				<td>${tusers.workunit}</td>
			</tr>
			<tr>
				<th>工作单位</th>
				<td>${tusers.workunit}</td>
			</tr>
			
			<tr>
				<th>地址</th>
				<td>${tusers.address}</td>
			</tr>
			<tr>
				<th>联系电话</th>
				<td>${tusers.workphone}</td>
			</tr>
			<tr>
				<th>传真号码</th>
				<td>${tusers.fax}</td>
			</tr>
			<tr>
				<th>联系E-mail</th>
				<td>${tusers.email}</td>
			</tr>
			<tr>
				<th>移动电话</th>
				<td>${tusers.mobile}</td>
			</tr>
			<tr>
				<th>编辑时间</th>
				<td>
					<fmt:formatDate value="${tusers.edittime}" 
					pattern="yyyy-MM-dd"/>
				</td>
			</tr>
			<tr>
				<th>角色</th>
				<td>
					<c:forEach var="usersRolesList" items="${usersRolesList}">
						${usersRolesList.roles.name}&nbsp;&nbsp;
					</c:forEach>
				</td>
			</tr>
			<tr>
				<th>状态</th>
				<td>
					<c:if test="${tusers.state == 1}">已启用</c:if>
					<c:if test="${tusers.state == 0}"><font color="red">已停用</font></c:if>
				</td>
			</tr>
	 	</table>
	</body>
</html>

