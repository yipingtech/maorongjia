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
				当前位置: 栏目配置 - 查看栏目信息
			</div>
			<div class="ropt"><a class="btn" href="javascript:history.back();">返回</a></div>
			<div class="clear"></div>
		</div>
		<table class="table table-bordered inner-table">
			<tr>
				<th>栏目名称</th>
				<td>
					${enterpriseColumn.names}
				</td>
			</tr>
			<tr>
				<th>栏目简称</th>
				<td>
					${enterpriseColumn.shortName}
				</td>
			</tr>
			<tr>
				<th>栏目编号</th>
				<td>
					${enterpriseColumn.num}
				</td>
			</tr>
			<tr>
				<th>栏目模块名称</th>
				<td>
					${enterpriseColumn.frontNum}
				</td>
			</tr>
			<tr>
				<th>栏目介绍</th>
				<td>
					${enterpriseColumn.intro}
				</td>
			</tr>
			<tr>
				<th>栏目等级</th>
				<td>
					<c:choose>
						<c:when test="${enterpriseColumn.father == 0}">
							<strong>一级栏目</strong>
						</c:when>
						<c:otherwise>
							二级栏目
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
			<tr>
				<th>状态</th>
				<td>
					<c:if test="${enterpriseColumn.state == 1}">已启用</c:if>
					<c:if test="${enterpriseColumn.state == 0}">
						<font color="red">已停用</font>
					</c:if>
				</td>
			</tr>
		</table>
	</body>
</html>