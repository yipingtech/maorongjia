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
			<div class="rpos">当前位置: 内容管理 - 查看产品信息</div>
			<div class="ropt"><a class="btn" href="javascript:history.back();">返回</a></div>
			<div class="clear"></div>
		</div>

		<table class="table table-bordered inner-table">
			<tr>
				<th>产品名称</th>
				<td>${mcProductInfo.title}</td>
			</tr>
			<tr>
				<th>关键字</th>
				<td>${mcProductInfo.keywords}</td>
			</tr>
			<%-- <tr>
				<th>简短描述</th>
				<td>${mcProductInfo.description}</td>
			</tr> --%>
			<tr>
				<th>商品的详情图</th>
				<td>
					<img src="
						<c:choose>
							<c:when test="${empty mcProductInfo.imgurl}">
									${ctx}/upload/MissPhoto.JPG
							</c:when>
							<c:otherwise>
								${ctx}/upload/enterprice/${mcProductInfo.imgurl}
							</c:otherwise>
						</c:choose>
					" />
				</td>
			</tr>
			<c:forEach var="parameter" items="${parameterValue}">
				<tr>
					<th>${parameter.key.name}</th>
					<td>
						<c:choose>
							<c:when test="${parameter.key.type == 0}">
								${parameter.value}
							</c:when>
							<c:when test="${parameter.key.type == 1}">
								${parameter.value}
							</c:when>
							<c:otherwise>
								<img src="
									<c:choose>
										<c:when test="${empty parameter.value}">
												${ctx}/upload/MissPhoto.JPG
										</c:when>
										<c:otherwise>
											${ctx}/upload/enterprice/${parameter.value}
										</c:otherwise>
									</c:choose>
								" />
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
			</c:forEach>
			<tr style="display:none;">
				<th>点击次数</th>
				<td>${mcProductInfo.hits}</td>
			</tr>
			<tr>
				<th>是否为最新产品</th>
				<td>
					<c:if test="${mcProductInfo.isNew==0}">否</c:if>
					<c:if test="${mcProductInfo.isNew==1}">是</c:if>
				</td>
			</tr>
			<tr>
				<th>是否为推荐产品</th>
				<td>
					<c:if test="${mcProductInfo.isSale==0}">否</c:if>
					<c:if test="${mcProductInfo.isSale==1}">是</c:if>
				</td>
			</tr>
			<tr>
				<th>更新时间</th>
				<td><fmt:formatDate value="${mcProductInfo.updatetime}" pattern="yyyy-MM-dd" /></td>
			</tr>
			<tr>
				<th>添加时间</th>
				<td><fmt:formatDate value="${mcProductInfo.addtime}" pattern="yyyy-MM-dd" /></td>
			</tr>
			<tr>
				<th>内容</th>
				<td>${mcProductInfo.content}</td>
			</tr>
		 </table>
	</body>
</html>