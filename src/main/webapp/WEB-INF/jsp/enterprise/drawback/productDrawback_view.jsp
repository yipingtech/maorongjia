<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>退货单查看</title>
		<%@ include file="/common/taglibs.jsp"%>
		<%@ include file="/common/meta.jsp"%>
		<%@ include file="/common/content.jsp"%>
		<script>
		</script>
	</head>
	<body>
		<div class="rhead">
			<div class="rpos">当前位置: 订单管理- 退货单查看</div>
			<div class="ropt"><input type="button" class="defaultButton" value="返回" onclick="history.back();"/></div>
		</div>

		<table  align="center" class="table table-bordered inner-table">
				<tr>
					<td class="pn-flabel" width="100px">订单号</td>
					<td>${productDrawback.orderNum}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">用户ID</td>
					<td>${productDrawback.memberId}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">退换商品</td>
					<td>
					    <c:forEach var="bean" items="${productAttrs }">
					         ${bean.product.title} &nbsp;;&nbsp;
					    </c:forEach>
					</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">商品属性</td>
					<td>
					<c:forEach var="productAttr" items="${productAttrs}">
						     ${productAttr.product.title}：${productAttr.attrValue}  &nbsp;;&nbsp;
					</c:forEach>
					</td>	
				</tr>
				<c:if test="${productDrawback.flag eq '0'}">
					<tr>
						<td class="pn-flabel" width="100px">申请退款金额</td>
						<td>${productDrawback.drawbackMoney}</td>
					</tr>
				</c:if>
				<tr>
					<td class="pn-flabel" width="100px">退换原因</td>
					<td>${productDrawback.drawbackCause}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">申请时间</td>
					<td><fmt:formatDate value="${productDrawback.applyTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">审核时间</td>
					<td><fmt:formatDate value="${productDrawback.auditTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
				</tr>
				<%-- <tr>
					<td class="pn-flabel" width="100px">联系商家手机号</td>
					<td>${productDrawback.sendPhone}</td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">审核通过寄货地址</td>
					<td>${productDrawback.sendAddress}</td>
				</tr> --%>
				<tr>
					<td class="pn-flabel" width="100px">退换状态</td>
					<c:if test="${productDrawback.returnStatus eq '1'}"><td style="color:red;">申请退货中 </td></c:if>
					<c:if test="${productDrawback.returnStatus eq '2'}"><td style="color:red;">退货成功</td></c:if>
					<c:if test="${productDrawback.returnStatus eq '3'}"><td style="color:red;">退货失败</td></c:if>
					<c:if test="${productDrawback.returnStatus eq '4'}"><td style="color:red;">申请换货中</td></c:if>
					<c:if test="${productDrawback.returnStatus eq '5'}"><td style="color:red;">换货成功</td></c:if>
					<c:if test="${productDrawback.returnStatus eq '6'}"><td style="color:red;">换货失败</td></c:if>
				</tr>

                <%--  <tr>
					<td class="pn-flabel" width="100px">图片</td>
					<td class="pn-flabel" width="100px">
					<img src="/distri/upload/enterprice/${productDrawback.imageName}" style="width: 120px;height: 120px;" alt="无图片"/></td>
				</tr> --%>
		 </table>
	</body>
</html>