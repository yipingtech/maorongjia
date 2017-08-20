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
				当前位置: 互动管理 - 广告信息管理 - 查看广告信息
			</div>
			<div class="ropt">
				<a class="btn" href="javascript:history.back();">返回</a>
			</div>
			<div class="clear"></div>
		</div>
		<table class="table table-bordered inner-table">
			<tr>
				<th>
					广告名称
				</th>
				<td>
					${enterpriseAd.names}
				</td>
			</tr>
			<tr>
				<th>
					广告标识
				</th>
				<td>
					${enterpriseAd.frontNum}
				</td>
			</tr>
			<tr>
				<th>
					广告排序
				</th>
				<td>
					${enterpriseAd.orderColumn}
				</td>
			</tr>
			<tr>
				<th>
					广告图片
				</th>
				<td>
					<img src="
					<c:choose>
						<c:when test="${empty enterpriseAd.photo}">
								${ctx}/upload/MissPhoto.JPG
						</c:when>
						<c:otherwise>
							${ctx}/upload/enterprice/${enterpriseAd.photo}
						</c:otherwise>
					</c:choose>
					" />
				</td>
			</tr>
			<tr>
				<th>
					广告链接地址
				</th>
				<td>
					${enterpriseAd.address}
				</td>
			</tr>
			<tr>
				<th>
					广告简介
				</th>
				<td>
					${enterpriseAd.intro}
				</td>
			</tr>
			<tr style="display:none;">
				<th>
					广告费用
				</th>
				<td>
					${enterpriseAd.acost}
				</td>
			</tr>
			<tr style="display:none;">
				<th>
					客户名称
				</th>
				<td>
					${enterpriseAd.clientName}
				</td>
			</tr>
			<tr style="display:none;">
				<th>
					显示时间
				</th>
				<td>
					<fmt:formatDate value="${enterpriseAd.initTime}"
						pattern="yyyy-MM-dd" />
					-- 至 --
					<fmt:formatDate value="${enterpriseAd.endTime}"
						pattern="yyyy-MM-dd" />
				</td>
			</tr>
			<tr>
				<th>
					更新时间
				</th>
				<td>
					<fmt:formatDate value="${enterpriseAd.editTime}"
						pattern="yyyy-MM-dd" />
				</td>
			</tr>
			<tr>
				<th>
					编辑人
				</th>
				<td>
					${enterpriseAd.users.name}
				</td>
			</tr>
			<tr>
				<th>
					状态
				</th>
				<td>
					<c:if test="${enterpriseAd.state == 1}">已启用</c:if>
					<c:if test="${enterpriseAd.state == 0}">
						<font color="red">已停用</font>
					</c:if>
				</td>
			</tr>
		</table>
	</body>
</html>

