<%@ page contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>更新模板</title>
		<%@ include file="/common/taglibs.jsp"%>
		<%@ include file="/common/meta.jsp"%>
		<%@ include file="/common/content.jsp"%>
		<script>
			$(document).ready(function(){
				$("#payOrderForm").validate({
					rules: { 
			
					},
					success: function(label) {
						label.html("&nbsp;").addClass("checked");
			        }	
				});
				
			});		
		</script>
	</head>
	<body>
		<div class="rhead">
			<div class="rpos">当前位置: 订单管理 - 订单编辑</div>
			<div class="ropt"><input type="button" class="defaultButton" value="返回" onclick="javascript:history.back();" /></div>
		</div>

		<form action="payOrderAction!editPayOrder.action" method="post" id="payOrderForm" name="payOrderForm" enctype="multipart/form-data">
			<table class="table table-bordered inner-table" style="width: 45%;float: left;">
				<input type="hidden" name="id" value="${payOrder.id}"/>
				<input type="hidden" name="orderNum" value="${payOrder.orderNum}"/>
				<input type="hidden" name="payOrder.orderNum" value="${payOrder.orderNum}"/>
				<tr>
					<th>订单号</th>
					<td>${payOrder.orderNum}</td>
				</tr>
				<tr>
					<th>会员账号</th>
					<td>${payOrder.member.loginName}</td>
				</tr>
				<tr>
					<th>收货人姓名</th>
					<td><input type="text" id="name" name="payOrder.name" value="${payOrder.name}" /></td>
				</tr>
				<tr>
					<th>联系方式</th>
					<td><input type="text" id="phone" name="payOrder.phone" value="${payOrder.phone}" /></td>
				</tr>
			<%-- 	<tr>
					<th>固话</th>
					<td><input type="text" id="fixPhone" name="payOrder.fixPhone" value="${payOrder.fixPhone}" /></td>
				</tr>
				<tr>
					<th>邮政编码</th>
					<td><input type="text" id="pastalcode" name="payOrder.pastalcode" value="${payOrder.pastalcode}" /></td>
				</tr> --%>
				<tr>
					<th>地址</th>
					<td><input type="text" id="address" name="payOrder.address" value="${payOrder.address}" /></td>
				</tr>
				<tr>
					<th>备注</th>
					<td><input type="text" id="comments" name="payOrder.comments" value="${payOrder.comments}" /></td>
				</tr>
				<tr>
					<th>创建订单时间</th>
					<td>
						<fmt:formatDate value="${payOrder.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
					</td>
				</tr>
				<tr>
					<th>付款时间</th>
					<td>
						<fmt:formatDate value="${payOrder.payTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
					</td>
				</tr>
				<tr>
					<th>配送时间</th>
					<td>
						<fmt:formatDate value="${payOrder.shippingTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
					</td>
				</tr>		
				<%-- <tr>
					<th>最佳送货时间</th>
					<td><input type="text" id="bestTime" name="payOrder.bestTime" value="${payOrder.bestTime}" /></td>
				</tr> --%>
				</table>
		    <table class="table table-bordered inner-table" style="width: 45%;float: right;">
				<tr>
					<th>商品配送情况</th>
					<td>
					<c:if test="${payOrder.shippingStatus eq '0'}">待发货</c:if>
					<c:if test="${payOrder.shippingStatus eq '1'}">已发货</c:if>
					<c:if test="${payOrder.shippingStatus eq '2'}">已签收</c:if>
					<c:if test="${payOrder.shippingStatus eq '3'}">备货中</c:if>
					</td>
				</tr>
			<%-- 	<tr>
					<th>配送方式</th>
					<td><input type="text" id="shippingId" name="payOrder.shippingId" value="${payOrder.shippingId}" /></td>
				</tr> --%>
				<tr>
					<th>配送方式名称</th>
					<td><input type="text" id="shippingName" name="payOrder.shippingName" value="${payOrder.shippingName}" /></td>
				</tr>
				<tr>
					<th>发货单号</th>
					<td><input type="text" id="invoiceNum" name="payOrder.invoiceNum" value="${payOrder.invoiceNum}" /></td>
				</tr>
				<tr>
					<th>配送金额</th>
					<td><input type="text" id="shippingFee" name="payOrder.shippingFee" value="${payOrder.shippingFee}" /></td>
				</tr>
				<%-- <tr>
					<th>支付方式名称</th>
					<td><input type="text" id="payName" name="payOrder.payName" value="${payOrder.payName}" /></td>
				</tr> --%>
				<tr>
					<th>总金额</th>
					<td><input type="text" id="productAmount" name="payOrder.productAmount" value="${payOrder.productAmount}" /></td>
				</tr>
				<%-- <tr>
					<th>使用积分金额</th>
					<td><input type="text" id="integralMoney" name="payOrder.integralMoney" value="${payOrder.integralMoney}" /></td>
				</tr>
				<tr>
					<th>使用红包金额</th>
					<td><input type="text" id="bonus" name="payOrder.bonus" value="${payOrder.bonus}" /></td>
				</tr> --%>
				<tr>
					<th>应付款金额</th>
					<td><input type="text" id="orderAmount" name="payOrder.orderAmount" value="${payOrder.orderAmount}" /></td>
				</tr>
				<tr>
					<th>订单状态</th>
					<td>
                    <c:if test="${payOrder.payStatus eq '0'}">未付款</c:if>
					<c:if test="${payOrder.payStatus eq '1'}">已付款</c:if>
					</td>
				</tr>
				</table>
				
				<c:forEach var="orderInfo" items="${orderInfoList}">
				<table class="table table-bordered inner-table">
				<tr>
					<th>订单信息号</th>
					<th>会员ID</th>
					<th>产品</th>
					<th>商品单价</th>
					<th>购买数量</th>
					<th>备注</th>
					<th>创建时间</th>
					<th>下单时间</th>
					<th>付款时间</th>
					<th>付款状态</th>		
					<th>商品状态</th>	
				</tr>
				<tr>
			      	<td>${orderInfo.orderinfoNum}</td>				
					<td>${orderInfo.member.loginName}</td>	
					<td>${orderInfo.product.title}</td>				
					<td>${orderInfo.price}</td>
					<td>${orderInfo.amount}</td>
					<td>${orderInfo.comments}</td>
					<td><fmt:formatDate value="${orderInfo.createTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td><fmt:formatDate value="${orderInfo.orderTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>				
					<td><fmt:formatDate value="${orderInfo.payTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>	
					<td>
					<c:if test="${orderInfo.orderStatus eq '0'}">未付款</c:if>
					<c:if test="${orderInfo.orderStatus eq '1'}">已付款</c:if>
					</td>
					 <td>
					<%-- <c:if test="${orderInfo.productStatus eq '3'}">已退款</c:if>
					<c:if test="${orderInfo.productStatus eq '4'}">已换货</c:if> --%>
						<c:if test="${(orderInfo.product.status eq '0')}"><label class="colorTrips">下架</label></c:if>
						<c:if test="${(orderInfo.product.status eq '1')}"><label class="colorTrips">上架</label></c:if>
					</td> 		
				</tr>
		 	</table></c:forEach>
				<div>
					<td colspan="2" style="text-align:center;">
						<input type="submit" class="defaultButton" value=" 提 交 "/>
					</td>
				</div> 	
		</form>
	</body>
</html>