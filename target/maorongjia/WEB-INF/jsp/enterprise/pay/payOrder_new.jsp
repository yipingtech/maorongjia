<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>添加模板</title>
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
			<div class="rpos">当前位置: 订单管理 - 添加订单</div>
			<div class="ropt"><a class="btn" href="javascript:history.back();">返回</a></div>
		</div>

		<form action="payOrderAction!newPayOrder.action" method="post" id="payOrderForm" name="payOrderForm" enctype="multipart/form-data">
			<table class="table table-bordered inner-table">
				<tr>
					<th>收货人姓名</th>
					<td><input type="text" id="name" name="payOrder.name" /></td>
				</tr>
				<tr>
					<th>联系方式</th>
					<td><input type="text" id="phone" name="payOrder.phone" /></td>
				</tr>
				<tr>
					<th>固话</th>
					<td><input type="text" id="fixPhone" name="payOrder.fixPhone" /></td>
				</tr>
				<tr>
					<th>邮政编码</th>
					<td><input type="text" id="pastalcode" name="payOrder.pastalcode" /></td>
				</tr>
				<tr>
					<th>地址</th>
					<td><input type="text" id="address" name="payOrder.address" /></td>
				</tr>
				<tr>
					<th>备注</th>
					<td><input type="text" id="comments" name="payOrder.comments" /></td>
				</tr>
				<tr>
					<th>配送方式</th>
					<td><input type="text" id="shippingId" name="payOrder.shippingId" /></td>
				</tr>
				<tr>
					<th>配送方式名称</th>
					<td><input type="text" id="shippingName" name="payOrder.shippingName" /></td>
				</tr>
				<tr>
					<th>配送金额</th>
					<td><input type="text" id="shippingFee" name="payOrder.shippingFee" /></td>
				</tr>
				<tr>
					<th>支付方式</th>
					<td><input type="text" id="payId" name="payOrder.payId" /></td>
				</tr>
				<tr>
					<th>支付方式名称</th>
					<td><input type="text" id="payName" name="payOrder.payName" /></td>
				</tr>
				<tr>
					<th>总金额</th>
					<td><input type="text" id="productAmount" name="payOrder.productAmount" /></td>
				</tr>
				<tr>
					<th>使用积分金额</th>
					<td><input type="text" id="integralMoney" name="payOrder.integralMoney" /></td>
				</tr>
				<tr>
					<th>使用红包金额</th>
					<td><input type="text" id="bonus" name="payOrder.bonus" /></td>
				</tr>
				<tr>
					<th>红包id</th>
					<td><input type="text" id="bonusId" name="payOrder.bonusId" /></td>
				</tr>
				<tr>
					<th>优惠券总额</th>
					<td><input type="text" id="couponAmount" name="payOrder.couponAmount" /></td>
				</tr>
				<tr>
					<th>优惠券id集合</th>
					<td><input type="text" id="couponId" name="payOrder.couponId" /></td>
				</tr>
				<tr>
					<th>应付款金额</th>
					<td><input type="text" id="orderAmount" name="payOrder.orderAmount" /></td>
				</tr>
				<tr>
					<th>订单信息号</th>
					<td><input type="text" id="orderinfoNum" name="orderInfo.orderinfoNum" /></td>
				</tr>
				<tr>
					<th>具体属性组合</th>
					<td><input type="text" id="productAttrIds" name="orderInfo.productAttrIds" /></td>
				</tr>
				<tr>
					<th>商品单价</th>
					<td><input type="text" id="price" name="orderInfo.price" /></td>
				</tr>
				<tr>
					<th>购买数量</th>
					<td><input type="text" id="amount" name="orderInfo.amount" /></td>
				</tr>
				<tr>
					<th>订单总价</th>
					<td><input type="text" id="totalPrice" name="orderInfo.totalPrice" /></td>
				</tr>
				<tr>
					<th>备注</th>
					<td><input type="text" id="comments" name="orderInfo.comments" /></td>
				</tr>
				<tr>
					<th>订单信息号</th>
					<td><input type="text" id="orderinfoNum" name="orderInfo1.orderinfoNum" /></td>
				</tr>
				<tr>
					<th>具体属性组合</th>
					<td><input type="text" id="productAttrIds" name="orderInfo1.productAttrIds" /></td>
				</tr>
				<tr>
					<th>商品单价</th>
					<td><input type="text" id="price" name="orderInfo1.price" /></td>
				</tr>
				<tr>
					<th>购买数量</th>
					<td><input type="text" id="amount" name="orderInfo1.amount" /></td>
				</tr>
				<tr>
					<th>订单总价</th>
					<td><input type="text" id="totalPrice" name="orderInfo1.totalPrice" /></td>
				</tr>
				<tr>
					<th>备注</th>
					<td><input type="text" id="comments" name="orderInfo1.comments" /></td>
				</tr>
				<tr>
					<td colspan="2" style="text-align:center;">
						<input type="submit" class="btn" value="提 交"/>
					</td>
				</tr>
		 	</table>
		</form>
	</body>
</html>