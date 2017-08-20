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
				当前位置: 互动管理 - 广告信息管理
			</div>
			<div class="ropt">
				<a href="enterpriseAdAction!add_page.action" class="btn">新增</a>
			</div>
			<div class="clear"></div>
		</div>
		<s:form action="enterpriseAdAction" method="get" id="form1" name="form1"
			theme="simple">
			
			<!-- 分页 -->
			<%@ include file="../../common/pager.jsp"%>
		</s:form>
		<br/>
		<table class="table table-bordered">
			<tr>
				<th>广告名称</th>
				<th>广告标识</th>
				<th>广告排序</th>
				<th style="display:none;">客户名称</th>
				<th style="display:none;">显示时间</th>
				<th>更新时间</th>
				<th>状态</th>
				<th>操作</th>
			</tr>
			<c:forEach var="enterpriseAd" items="${EnterpriseAdList}">
				<tr>
					<td>
						${enterpriseAd.names}
					</td>
					<td>
						${enterpriseAd.frontNum}
					</td>
					<td>
						${enterpriseAd.orderColumn}
					</td>
					<td style="display:none;">
						${enterpriseAd.clientName}
					</td>
					<td style="display:none;">
						<fmt:formatDate value="${enterpriseAd.initTime}"
							pattern="yyyy-MM-dd" />
						&nbsp;&nbsp;至&nbsp;&nbsp;
						<fmt:formatDate value="${enterpriseAd.endTime}"
							pattern="yyyy-MM-dd" />
					</td>
					<td>
						<fmt:formatDate value="${enterpriseAd.editTime}"
							pattern="yyyy-MM-dd" />
					</td>
					<td>
						<c:if test="${enterpriseAd.state == 1}">已启用</c:if>
						<c:if test="${enterpriseAd.state == 0}">
							<font color="red">已停用</font>
						</c:if>
					</td>
					<td>
						<a class="btn btn-info btn-small" href="enterpriseAdAction!view.action?id=${enterpriseAd.id}">查看</a>&nbsp; &nbsp;
						<a class="btn btn-primary btn-small" href="enterpriseAdAction!edit.action?id=${enterpriseAd.id}">编辑</a>&nbsp; &nbsp;
						<a class="btn btn-danger btn-small" href="enterpriseAdAction!delete.action?id=${enterpriseAd.id}" onclick="return doYouWantToDel();">删除</a>
					</td>
				</tr>
			</c:forEach>
		</table>
	</body>
</html>

