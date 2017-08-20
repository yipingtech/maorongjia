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
				if(confirm('您确定要删除吗？（如果该角色下有未删除的角色，则删除不生效）')) {
					return true;
				}
				return false;
			}
		</script>
	</head>
	<body>
	
	<div class="rhead">
		<div class="rpos">当前位置: 系统配置 - 角色管理</div>
		<div class="ropt"><a href="rolesAction!add_page.action" class="btn">新增</a></div>
		<div class="clear"></div>
  	</div>
	
	<table class="table table-bordered">
		<tr>
			<th>
				角色名称
			</th>
			<th>
				授权
			</th>
			<th>
				状态
			</th>
			<th>
				操作
			</th>
		</tr>
         <c:forEach var="rolesList" items="${rolesList}">
        	 <tr>
				<td width="70px">
					${rolesList.name}
				</td>
				<td>
					<c:forEach var="ra" items="${rolesList.rolesAuthoritieses}" varStatus="status">
						${ra.authorities.displayName}&nbsp;&nbsp;						
					</c:forEach>
				</td>
				<td width="40px">
					<c:if test="${rolesList.state == 1}">已启用</c:if>
					<c:if test="${rolesList.state == 0}"><font color="red">已停用</font></c:if>
				</td>
				<td width="150px">
					<a class="btn btn-info btn-small" href="rolesAction!view.action?id=${rolesList.id}">查看</a>&nbsp; &nbsp;
		        	<a class="btn btn-primary btn-small" href="rolesAction!edit.action?id=${rolesList.id}">编辑</a>&nbsp; &nbsp;
		        	<a class="btn btn-danger btn-small" href="rolesAction!delete.action?id=${rolesList.id}" onclick="return doYouWantToDel();">删除</a>
				</td>
			</tr>
        </c:forEach>
        </table>
  
		<s:form action="rolesAction" method="get" id="form1" name="form1" theme="simple">
			
			<!-- 分页 -->
			<%@ include file="../../common/pager.jsp"%>
			
		</s:form>
	</body>
</html>

