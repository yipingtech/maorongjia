<%@ page contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>模板</title>
		<%@ include file="/common/taglibs.jsp"%>
		<%@ include file="/common/meta.jsp"%>
		<%@ include file="/common/content.jsp"%>
		<style>
			.headbg1 {
				background: #c9e6f5;
				font-weight: bold;
				padding-left: 15px;
			}
			
			.headbg1 td {
				font-weight: bold;
				text-align: center;
			}
		</style>

		<script>
		</script>
	</head>
	<body>
		<div class="rhead">
			<div class="rpos">
				当前位置: 订单管理 - 退货单列表
			</div>
			<div class="ropt">
				<!-- <a href="productDrawbackAction!newPage.action" class="btn">新增</a> -->
			</div>
			<div class="clear"></div>
		</div>
		<div class="rhead" style="float:left;">
		<form id="formType" name="formType" action="productDrawbackAction!queryLikeDrawBackByDraw.action" method="post">
		<!-- 条件搜索 -->
		<input type="hidden" name="flag" value="1"/>
		<label>订单号</label><input type="text" name="productDrawback.orderNum" value="${productDrawback.orderNum }"/>
		<label>申请人ID</label><input type="text" name="productDrawback.memberId" value="${productDrawback.memberId}"/>
		<label>申请状态</label>
		<select name="productDrawback.returnStatus">
		<option value="">--请选择申请状态--</option>
		<option value="1" <c:if test="${productDrawback.returnStatus eq '1'}">selected="selected"</c:if>>--申请退货--</option>
		<option value="2" <c:if test="${productDrawback.returnStatus eq '2'}">selected="selected"</c:if>>--退货成功--</option>
		<option value="3" <c:if test="${productDrawback.returnStatus eq '3'}">selected="selected"</c:if>>--退货失败--</option>
		<option value="4" <c:if test="${productDrawback.returnStatus eq '4'}">selected="selected"</c:if>>--申请换货--</option>
		<option value="5" <c:if test="${productDrawback.returnStatus eq '5'}">selected="selected"</c:if>>--换货成功--</option>
		<option value="6" <c:if test="${productDrawback.returnStatus eq '6'}">selected="selected"</c:if>>--换货失败--</option>
		</select>
		<input type="submit" value="搜索"/>
		</form>
		</div>
		<div style="float: right;margin-top:10px;margin-right: 100px;">
		<a href="productDrawbackAction!queryLikeDrawBackByDraw.action?dateStr=day&&flag=1" class="btn">今天</a>
		<a href="productDrawbackAction!queryLikeDrawBackByDraw.action?dateStr=week&&flag=1" class="btn" style="margin-left: 20px;">一周内</a>
		<a href="productDrawbackAction!queryLikeDrawBackByDraw.action?dateStr=month&&flag=1" class="btn" style="margin-left: 20px;">一个月内</a>
		</div>
		<c:if test="${not empty productDrawbacks}">
			<s:form action="productDrawbackAction!queryLikeDrawBackByDraw.action"
					namespace="/drawback" method="post" name="form1" theme="simple" id="form1">
					<input type="hidden" name="flag" value="1"/>
					<input type="hidden" name="dateStr" value="${dateStr}"/>
					<input type="hidden" name="productDrawback.orderNum" value="${productDrawback.orderNum}"/>
					<input type="hidden" name="productDrawback.memberId" value="${productDrawback.memberId}"/>
					<input type="hidden" name="productDrawback.returnStatus" value="${productDrawback.returnStatus}"/>
				<!-- 分页 -->
				<%@ include file="../../common/pager.jsp"%>
			</s:form>
			<br/>
		</c:if>
		<table class="table table-bordered" onmouseover="changeto()" onmouseout="changeback()">
			<tr id="nc" class="headbg">
				<td>序号</td>
				<td>订单号</td>
				<td>申请人ID</td>
				<!-- <td>退换商品</td> -->
				<td>申请退款金额</td>
				<td>退款原因</td>
				<td>申请时间</td>
				<td>审核时间</td>
<!-- 				<td>联系商家手机号</td>
				<td>审核通过寄货地址</td> -->
				<td>退换状态</td>

				<td>操作</td>
			</tr>

			<c:forEach var="bean" items="${productDrawbacks}"
				varStatus="status">
				<tr>
					<td>${status.count}</td>
					<td><a href="productDrawbackAction!queryDrawBackIsOrder.action?orderInfoNum=${bean.orderNum}">${bean.orderNum}</a></td>
					<td>${bean.memberId}</td>
					<%-- <td>${bean.productId.title}</td> --%>
					<td>${bean.drawbackMoney}</td>
					<td>${bean.drawbackCause}</td>
					<td><fmt:formatDate value="${bean.applyTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td><fmt:formatDate value="${bean.auditTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
<%-- 					<td>${bean.sendPhone}</td>
					<td>${bean.sendAddress}</td> --%>
					<c:if test="${bean.returnStatus eq '1'}">
					<td>申请退货中</td>
					</c:if>
					<c:if test="${bean.returnStatus eq '2'}">
					<td>退货成功</td>
					</c:if>
					<c:if test="${bean.returnStatus eq '3'}">
					<td>退货失败</td>
					</c:if>
					<c:if test="${bean.returnStatus eq '4'}">
					<td>申请换货中</td>
					</c:if>
					<c:if test="${bean.returnStatus eq '5'}">
					<td>换货成功</td>
					</c:if>
					<c:if test="${bean.returnStatus eq '6'}">
					<td>换货失败</td>
					</c:if>

					<td>
						<a class="btn btn-info btn-small" href="productDrawbackAction!queryDrawBackIsProducter.action?id=${bean.id}">查看订单详情</a>
						&nbsp; &nbsp;
						<c:if test="${empty bean.auditTime}">
							<a class="btn btn-primary btn-small"  href="productDrawbackAction!passDrawBack.action?id=${bean.id}"
									onclick="{if(confirm('确定要审核通过吗?')){return true;}return false;}">审核成功</a>
							&nbsp; &nbsp; 
							<a class="btn btn-danger btn-small"  href="productDrawbackAction!editDrawBackStatus.action?id=${bean.id}"
									onclick="{if(confirm('确定要审核失败吗?')){return true;}return false;}">审核失败</a>
							&nbsp; &nbsp; 
						</c:if>
						<a class="btn btn-danger btn-small"  href="productDrawbackAction!delProductDrawback.action?id=${bean.id}"
								onclick="{if(confirm('你真的要删除吗?')){return true;}return false;}">删除</a>
					</td>
				</tr>
			</c:forEach>

		</table>

	</body>
</html>