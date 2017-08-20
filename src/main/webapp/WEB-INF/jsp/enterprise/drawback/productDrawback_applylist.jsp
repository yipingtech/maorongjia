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
				当前位置: 订单管理 - 退货单审核
			</div>
			<!-- <div class="ropt">
				<a href="productDrawbackAction!newPage.action" class="btn">新增</a>
			</div> -->
			<div class="clear"></div>
		</div>
		<div class="rhead" style="float:left;">
		<form id="formType" name="formType" action="productDrawbackAction!queryLikeDrawBackByApply.action" method="post">
		<!-- 条件搜索 -->
		<input type="hidden" name="flag" value="0"/>
		<label>清单号</label><input type="text" name="productDrawback.orderNum"/>
		<label>申请人ID</label><input type="text" name="productDrawback.memberId"/>
		<input type="hidden" name="productDrawback.returnStatus"/>
		<!-- <label>申请状态</label>
		<select name="productDrawback.returnStatus">
		<option value="">--请选择申请状态--</option>
		<option value="0">--申请中--</option>
		<option value="1">--已通过--</option>
		</select> -->
		<input type="submit" value="搜索"/>
		</form>
		</div>
		</div>
		<div style="float: right;margin-top:10px;margin-right: 100px;">
		<a href="productDrawbackAction!queryLikeDrawBackByApply.action?dateStr=day&&flag=0" class="btn">今天</a>
		<a href="productDrawbackAction!queryLikeDrawBackByApply.action?dateStr=week&&flag=0" class="btn" style="margin-left: 20px;">一周内</a>
		<a href="productDrawbackAction!queryLikeDrawBackByApply.action?dateStr=month&&flag=0" class="btn" style="margin-left: 20px;">一个月内</a>
		</div>
		
		
		<c:if test="${not empty productDrawbacks}">
			<s:form action="productDrawbackAction!queryLikeDrawBackByApply.action"
					namespace="/drawback" method="post" name="form1" theme="simple" id="form1">
					<input type="hidden" name="flag" value="0"/>
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
				<td>清单号</td>
				<td>申请人ID</td>
				<td>退换商品</td>
				<td>申请退款金额</td>
				<td>退款原因</td>
				<td>申请时间</td>
				<td>审核时间</td>
				<td>退换状态</td>
				<td>审核操作</td>
				<td>操作</td>
			</tr>

			<c:forEach var="bean" items="${productDrawbacks}"
				varStatus="status">
				<tr>
					<td>${status.count}</td>
					<td><a href="productDrawbackAction!queryDrawBackIsOrder.action?orderInfoNum=${bean.orderNum}">${bean.orderNum}</a></td>
					<td>${bean.memberId}</td>
					<td>${bean.productId.title}</td>
					<td>${bean.drawbackMoney}</td>
					<td>${bean.drawbackCause}</td>
					<td><fmt:formatDate value="${bean.applyTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td><fmt:formatDate value="${bean.auditTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<c:if test="${bean.returnStatus eq '0'}">
						<td>用户申请退货</td>
						<td>
							<a class="btn btn-info btn-small" href="productDrawbackAction!passDrawBack.action?productDrawback.orderNum=${bean.orderNum}&&productDrawback.id=${bean.id}">通过</a>
							&nbsp; &nbsp;
							<a class="btn btn-primary btn-small" href="productDrawbackAction!editDrawBackStatus.action?productDrawback.orderNum=${bean.orderNum}&&productDrawback.id=${bean.id}">不通过</a>
						</td>
					</c:if>

					<td>
						<a class="btn btn-info btn-small" href="productDrawbackAction!queryDrawBackIsProducter.action?id=${bean.id}">查看</a>
					</td>
				</tr>
			</c:forEach>

		</table>

		
	</body>
</html>