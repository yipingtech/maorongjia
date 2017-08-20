<%@ page contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>MCCMS</title>
		<%@ include file="/common/taglibs.jsp"%>
		<%@ include file="/common/meta.jsp"%>
		<%@ include file="/common/content.jsp"%>
		<script>
			function doYouWantToDel() {
				if(confirm('您真的要删除吗？')) {
					return true;
				}
				return false;
			}
		</script>
	</head>
	<body>

		<div class="rhead">
		   <div class="rpos">当前位置: 系统配置 - 用户管理</div>
		   <div class="ropt"><a href="usersAction!add_page.action" class="btn">新增</a></div>
		   <div class="clear"></div>
  		</div>

		<table class="table table-bordered">
			<tr>
				<th>
					用户名
				</th>
				<th>
					角色
				</th>
				<th>
					真实名字
				</th>
				<th>
					工作单位
				</th>
				<th>
					编辑时间
				</th>
				<th>
					状态
				</th>
				<th>
					操作
				</th>
			</tr>

			<c:forEach var="tusers" items="${usersList}">
				<tr>
					<td>
						
						${tusers.loginName}
					</td>
					<td>
						<c:forEach var="ro" items="${tusers.usersRoleses}">
							${ro.roles.name}&nbsp;&nbsp;&nbsp;&nbsp;
						</c:forEach>
					</td>
					<td>
						${tusers.name}
					</td>
					<td>
						<!--  	<c:if test="${users.sex==1}">男</c:if>
						<c:if test="${users.sex==0}">女</c:if>
						<c:if test="${users.sex!=0 and users.sex!=1}">未知</c:if>-->
						${tusers.workunit}
					</td>
					<td>
						<fmt:formatDate value="${tusers.edittime}" pattern="yyyy-MM-dd" />
					</td>
					<td>
						<c:if test="${tusers.state == 1}">已启用</c:if>
						<c:if test="${tusers.state == 0}">
							<font color="red">已停用</font>
						</c:if>
					</td>
					<td>
						<a class="btn btn-info btn-small" href="usersAction!view.action?id=${tusers.id}">查看</a>&nbsp;	&nbsp;
						<a class="btn btn-primary btn-small" href="usersAction!edit.action?id=${tusers.id}">编辑</a>&nbsp;	&nbsp;
						<a class="btn btn-danger btn-small" href="updatePasswordAction!resetview.action?id=${tusers.id}">重置密码</a>&nbsp;&nbsp;	
						<a class="btn btn-danger btn-small" href="usersAction!delete.action?id=${tusers.id}" onclick="return doYouWantToDel();">删除</a>
					</td>
				</tr>
			</c:forEach>
		</table>
		<s:form action="usersAction" method="get" id="form1" name="form1"
				theme="simple">
				
			<!-- 分页 -->
			<%@ include file="../../common/pager.jsp"%>
			
		</s:form>
	</body>
</html>

