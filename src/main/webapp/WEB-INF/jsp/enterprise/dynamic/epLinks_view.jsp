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
				当前位置: 互动管理 - 友情链接管理 - 查看友情链接信息
			</div>
			<div class="ropt">
				<a class="btn" href="javascript:history.back();">返回</a>
			</div>
			<div class="clear"></div>
		</div>
		<table class="table table-bordered inner-table">
			<tr>
				<th>
					链接名称
				</th>
				<td>
					${enterpriseLinks.names}
				</td>
			</tr>
			<tr>
				<th>
					链接标识
				</th>
				<td>
					${enterpriseLinks.frontNum}
				</td>
			</tr>
			<tr>
				<th>
					链接排序
				</th>
				<td>
					${enterpriseLinks.orderColumn}
				</td>
			</tr>
			<tr>
				<th>
					链接地址
				</th>
				<td>
					${enterpriseLinks.address}
				</td>
			</tr>
			<tr>
				<th>
					链接简介
				</th>
				<td>
					${enterpriseLinks.intro}
				</td>
			</tr>
			<tr style="display:none;">
				<th>
					显示时间
				</th>
				<td>
					<!-- yyyy 年 MM 月 dd 日-->
					<fmt:formatDate value="${enterpriseLinks.initTime}"
						pattern="yyyy-MM-dd" />
					&nbsp;至&nbsp;
					<fmt:formatDate value="${enterpriseLinks.endTime}"
						pattern="yyyy-MM-dd" />
				</td>
			</tr>
			<tr>
				<th>
					状态
				</th>
				<td>
					<c:if test="${enterpriseLinks.state == 1}">已启用</c:if>
					<c:if test="${enterpriseLinks.state == 0}">
						<font color="red">已停用</font>
					</c:if>
				</td>
			</tr>
		</table>
		<br />
	</body>
</html>

