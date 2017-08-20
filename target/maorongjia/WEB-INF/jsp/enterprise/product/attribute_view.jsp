<%@ page contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>查看模板</title>
		<%@ include file="/common/taglibs.jsp"%>
		<%@ include file="/common/meta.jsp"%>
		<%@ include file="/common/content.jsp"%>
	</head>
	<body>
		<div class="rhead">
			<div class="rpos">当前位置: 产品属性 -- 视图</div>
			<div class="ropt"><a class="btn" href="${ctx}/product/attributeAction!retrieveAllAttributes.action">返回</a></div>
		</div>

		<table class="table table-bordered inner-table">
				<tr>
					<th>属性名</th>
					<td>${attribute.attrName}</td>
				</tr>
				<tr>
					<th>属性值</th>
					<td>${attribute.attrValue}</td>
				</tr>
				<tr>
					<th>所属产品类型</th>
					<td>${attribute.productType.name}</td>
				</tr>
				<tr>
					<th>录入方式</th>
					<td>
					<c:if test="${attribute.attrInputType eq '0'}">
					手动录入
					</c:if>
					<c:if test="${attribute.attrInputType eq '1'}">
					选择录入
					</c:if>
					</td>
				</tr>
				<tr>
					<th>属性类型</th>
					<td>
					<c:if test="${attribute.attrType eq '0'}">
					唯一属性
					</c:if>
					<c:if test="${attribute.attrType eq '1'}">
					单选属性
					</c:if>
					</td>
				</tr>
				<tr>
					<th>是否启用</th>
					<td>
						<c:if test="${attribute.status eq '0'}">
						否
						</c:if>
						<c:if test="${attribute.status eq '1'}">
						是
						</c:if>
					</td>
				</tr>
				

		</table>
	</body>
</html>