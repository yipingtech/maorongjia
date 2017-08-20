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
			<div class="rpos">
				当前位置: 栏目配置 - 栏目信息列表
			</div>
			<security:authorize ifAnyGranted="COLUMN_ADD">
				<div class="ropt">
					<a href="epColumnAction!add_page.action?father=${father}" class="btn">新增</a>
				</div>
			</security:authorize>
			<div class="clear"></div>
		</div>
		<table class="table table-bordered">
			<tr>
				<th>
					栏目排序
				</th>
				<th>
					栏目名称
				</th>
				<th>
					栏目模块名称
				</th>
				<th>
					模板类型
				</th>
				<th>
					是否在主导航显示
				</th>
				<th>
					状态
				</th>
				<th>
					操作
				</th>
			</tr>
			<c:forEach var="epColumn" items="${enterpriseColumnList}">
				<tr>
					<td>
						${epColumn.orderColumn}
					</td>
					<td>
						${epColumn.names}
					</td>
					<td>
						${epColumn.frontNum}
					</td>
					<td>
						${epColumn.typeColumn.name}(${epColumn.typeColumn.templateType})
					</td>
					<td>
						<c:if test="${epColumn.isValidInNav=='1'}">
							是
						</c:if>
						<c:if test="${epColumn.isValidInNav=='0'}">
							否
						</c:if>
					</td>
					<td>
						<c:if test="${epColumn.state == 1}">已启用</c:if>
						<c:if test="${epColumn.state == 0}">
							<font color="red">已停用</font>
						</c:if>
					</td>
					<td>
						<a class="btn btn-primary btn-small" href="epColumnAction!edit.action?id=${epColumn.id}">编辑</a>&nbsp;
						&nbsp;
						<c:if test="${empty epColumnList&&father!=102}">
							<a class="btn btn-danger btn-small" href="epColumnAction!delete.action?id=${epColumn.id}"
								onclick="return doYouWantToDel();">
								删除 </a>
						</c:if>
					</td>
				</tr>
			</c:forEach>
		</table>
	</body>
</html>

