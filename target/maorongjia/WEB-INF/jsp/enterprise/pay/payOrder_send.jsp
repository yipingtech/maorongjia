<%@ page contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>模板</title>
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
				当前位置: 订单管理- 发货单列表
			</div>
			<!-- <div class="ropt">
				<a href="payOrderAction!newPage.action" class="btn">新增</a>
			</div> -->
			<div class="clear"></div>
		</div>
		<div class="rhead" style="float:left;">
		<form id="formType" name="formType" action="payOrderAction!queryAllSendToReceiveOrder.action" method="post">
			<!-- 条件搜索 -->
		<label>订单号</label><input type="text" name="payOrder.orderNum" value="${payOrder.orderNum }"/>
		<label>收货人</label><input type="text" name="payOrder.name" value="${payOrder.name }"/>
		<label>订单状态</label>
		<select name="payOrder.shippingStatus">
		<option value="">--请选择订单状态--</option>
		<option value="2" <c:if test="${payOrder.shippingStatus eq '2'}">selected="selected"</c:if>>--待确认--</option>
		<option value="3" <c:if test="${payOrder.shippingStatus eq '3'}">selected="selected"</c:if>>--已签收--</option>
		</select>
		<input type="submit" value="搜索"/>
		</form>
		</div>
		<div style="float: right;margin-top:10px;margin-right: 100px;">
		<a href="payOrderAction!queryAllSendToReceiveOrder.action?flag=day" class="btn">今天</a>
		<a href="payOrderAction!queryAllSendToReceiveOrder.action?flag=week" class="btn" style="margin-left: 20px;">一周内</a>
		<a href="payOrderAction!queryAllSendToReceiveOrder.action?flag=month" class="btn" style="margin-left: 20px;">一个月内</a>
		</div>
		
		
		<c:if test="${not empty payOrders}">
			<s:form action="payOrderAction!queryAllSendToReceiveOrder.action"
					namespace="/pay" method="post" name="form1" theme="simple" id="form1">
					<input type="hidden" name="flag" value="${flag}"/>
					<c:if test="${payOrder.orderNum != null && payOrder.orderNum != ''  }">
						<input type="hidden" name="payOrder.orderNum" value="${payOrder.orderNum}"/>
					</c:if>
					<c:if test="${payOrder.name != null && payOrder.name != ''  }">
						<input type="hidden" name="payOrder.name" value="${payOrder.name}"/>
					</c:if>
					<c:if test="${payOrder.shippingStatus != null && payOrder.shippingStatus != ''  }">
						<input type="hidden" name="payOrder.shippingStatus" value="${payOrder.shippingStatus}"/>
					</c:if>
				<!-- 分页 -->
				<%@ include file="../../common/pager.jsp"%>
			</s:form>
			<br />
		</c:if>
		
		
		<table class="table table-bordered">
			<tr>
				<th>序号</th>
				<th>订单号</th>
				<th>收货人姓名</th>
				<th>创建订单时间</th>
				<th>总金额</th>
				<th>使用积分金额</th>
				<th>使用红包金额</th>
				<th>应付款金额</th>
				<th>订单状态</th>
				<th>商品配送情况</th>

				<th>操作</th>
			</tr>

			<c:forEach var="bean" items="${payOrders}"
				varStatus="status">
				<tr>
					<td>${status.count}</td>
					<td><a href="payOrderAction!queryOrderAndPay.action?payOrder.orderNum=${bean.orderNum}">${bean.orderNum}</a></td>
					<td>${bean.name}</td>
					<td><fmt:formatDate value="${bean.createTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td>${bean.productAmount}</td>
					<td>${bean.integralMoney}</td>
					<td>${bean.bonus}</td>
					<td>${bean.orderAmount}</td>
					<td>
					<c:if test="${bean.payStatus eq '0'}">待付款</c:if>
					<c:if test="${bean.payStatus eq '1'}">已付款</c:if>
					</td>
					<td>
					<c:if test="${bean.shippingStatus eq '0'}">待发货</c:if>
					<c:if test="${bean.shippingStatus eq '1'}">已发货</c:if>
					<c:if test="${bean.shippingStatus eq '2'}"><label style="color:red;">已签收</label></c:if>
					<c:if test="${bean.shippingStatus eq '3'}">备货中</c:if>
					</td>
					<td>
						<a class="btn btn-info btn-small" href="payOrderAction!queryOrderAndPay.action?payOrder.orderNum=${bean.orderNum}">查看</a>
						&nbsp; &nbsp;
						<a class="btn btn-primary btn-small" href="payOrderAction!retrievePayOrderById.action?payOrder.orderNum=${bean.orderNum}">编辑</a>
						&nbsp; &nbsp;
						<a class="btn btn-danger btn-small" href="payOrderAction!delPayOrderByOrderNum.action?payOrder.id=${bean.id}"
								 onclick="return doYouWantToDel();">移除</a>
					</td>
				</tr>
			</c:forEach>
		</table>
	</body>
</html>