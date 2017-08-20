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
				当前位置: 系统配置 - 产品参数管理
			</div>
			<div class="clear"></div>
		</div>
		
		<table class="table table-bordered">
			<tr>
				<th>序号</th>
				<th>中文字段名称</th>
				<th>调用代码</th>
				<th>字段排序</th>
				<th>字段类型</th>
				<th>是否开启该字段</th>
				<th>操作</th>
			</tr>

			<c:forEach var="bean" items="${mcParameters}"
				varStatus="status">
				<tr>
					<td>${bean.id}</td>
					<td>${bean.name}</td>
					<td>${bean.mark}</td>
					<td>${bean.noOrder}</td>
					
					<td>
						<c:if test="${bean.type==0}">简短字段</c:if>
						<c:if test="${bean.type==1}">备用字段</c:if>
						<c:if test="${bean.type==2}">上传字段</c:if>
					
					</td>

					<td>
						<c:if test="${bean.wrOk==1}">已启用</c:if>
						<c:if test="${bean.wrOk==0}">已停用</c:if>
					</td>
					<td>
						<a class="btn btn-primary btn-small" href="mcParameterAction!retrieveMcParameterById.action?id=${bean.id}">编辑</a>
					</td>
				</tr>
			</c:forEach>
		</table>
	</body>
</html>