<%@ page contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>查看模板</title>
		<%@ include file="/common/taglibs.jsp"%>
		<%@ include file="/common/meta.jsp"%>
		<%@ include file="/common/content.jsp"%>
	</head>
	<style>
	 .colorTrips{color:red;}	
	</style>
	<body>
		<div class="rhead">
			<div class="rpos">当前位置: 订单管理 - 订单列表 - 订单信息</div>
			<div class="ropt"><a class="btn" href="javascript:history.back();">返回</a></div>
		</div>
        <div class="rhead">
		商品订单
		</div>
		<table class="table table-bordered inner-table">
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
					<td>${payOrder.name}</td>
				</tr>
				<tr>
					<th>联系方式</th>
					<td>${payOrder.phone}</td>
				</tr>
			<%-- 	<tr>
					<th>邮政编码</th>
					<td>${payOrder.pastalcode}</td>
				</tr> --%>
				<tr>
					<th>地址</th>
					<td>${payOrder.address}</td>
				</tr>
				<tr>
					<th>备注</th>
					<td>${payOrder.comments}</td>
				</tr>
				<tr>
					<th>创建订单时间</th>
					<td><fmt:formatDate value="${payOrder.createTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
				</tr>
				<tr>
					<th>付款时间</th>
					<td><fmt:formatDate value="${payOrder.payTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
				</tr>
				<tr>
					<th>配送时间</th>
					<td><fmt:formatDate value="${payOrder.shippingTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
				</tr>
				<%-- <tr>
					<th>收货人的最佳送货时间</th>
					<td>${payOrder.bestTime}</td>
				</tr> --%>
				<tr>
					<th>商品配送情况</th>
					<td>
					<c:if test="${payOrder.shippingStatus eq '0'}"><label class="colorTrips">待发货</label></c:if>
					<c:if test="${payOrder.shippingStatus eq '1'}"><label class="colorTrips">已发货</label></c:if>
					<c:if test="${payOrder.shippingStatus eq '2'}"><label class="colorTrips">已签收</label></c:if>
					<c:if test="${payOrder.shippingStatus eq '3'}"><label class="colorTrips">备货中</label></c:if>
					</td>
				</tr>
				<%-- <tr>
					<th>配送方式</th>
					<td>${payOrder.shippingId}</td>
				</tr> --%>
				<tr>
					<th>配送方式名称</th>
					<td>${payOrder.shippingName}</td>
				</tr>
				<tr>
					<th>发货单号</th>
					<td>${payOrder.invoiceNum}</td>
				</tr>
				<%-- <tr>
					<th>配送金额</th>
					<td>${payOrder.shippingFee}</td>
				</tr> --%>
				<%-- <tr>
					<th>支付方式</th>
					<td>${payOrder.payId}</td>
				</tr> --%>
				<%-- <tr>
					<th>支付方式名称</th>
					<td>${payOrder.payName}</td>
				</tr> --%>
				<tr>
					<th>总金额</th>
					<td>${payOrder.productAmount}</td>
				</tr>
				<%-- <tr>
					<th>使用积分金额</th>
					<td>${payOrder.integralMoney}</td>
				</tr> --%>
				<%-- <tr>
					<th>使用红包金额</th>
					<td>${payOrder.bonus}</td>
				</tr> --%>
			<%-- 	<tr>
					<th>优惠券总额</th>
					<td>${payOrder.couponAmount}</td>
				</tr> --%>
				<tr>
					<th>应付款金额</th>
					<td>${payOrder.orderAmount}</td>
				</tr>
				<tr>
					<th>订单状态</th>
					<td>
					<c:if test="${payOrder.payStatus eq '0'}"><label class="colorTrips">未付款</label></c:if>
					<c:if test="${payOrder.payStatus eq '1'}"><label class="colorTrips">已付款</label></c:if>
					</td>
				</tr>
		</table>
		</div>
		<div class="rhead">
		订单商品清单
		</div>
		<table style="border:1px solid #ccc;width: 100%;">
			<tr style="background-color: #ccc;height: 40px;">
			<th>订单信息号</th>
			<th>会员账号</th>
			<th>商品名称</th>
			<th>商品属性</th>
			<th>商品单价</th>
			<th>购买数量</th>
			<th>备注</th>
			<th>创建时间</th>
			<th>下单时间</th>
			<th>付款时间</th>
			<th>订单状态</th>
			<th>商品状态</th>
			</tr>
			<c:forEach var="orderInfo" items="${orderInfoList}">
			<tr style="height: 35px;"align="center">			
					<td>${orderInfo.orderinfoNum}</td>								
					<td>${orderInfo.member.loginName}</td>							
					<td>${orderInfo.product.title}</td>		
					<td>
					<c:forEach var="productAttr" items="${productAttrs}">
					<c:if test="${fn:contains(orderInfo.productAttrIds,productAttr.id)}">
					${productAttr.attrValue}
					</c:if>
					</c:forEach>
					</td>							
					<td>${orderInfo.price}</td>									
					<td>${orderInfo.amount}</td>								
					<td>${orderInfo.comments}</td>									
					<td>
						<fmt:formatDate value="${orderInfo.createTime}" pattern="yyyy-MM-dd HH:mm:ss" />
					</td>				
					<td>
						<fmt:formatDate value="${orderInfo.orderTime}" pattern="yyyy-MM-dd HH:mm:ss" />
					</td>	
					<td>
						<fmt:formatDate value="${orderInfo.payTime}" pattern="yyyy-MM-dd HH:mm:ss" />
					</td>				
					<td>
					<c:if test="${(orderInfo.orderStatus eq '0')}"><label class="colorTrips">未付款</c:if>
					<c:if test="${(orderInfo.orderStatus eq '1')}"><label class="colorTrips">已付款</c:if>
					</td>
					<td>
						<%-- <c:if test="${(orderInfo.productStatus eq '0')}"><label class="colorTrips">退款申请中</label></c:if>
						<c:if test="${(orderInfo.productStatus eq '1')}"><label class="colorTrips">审核通过</label></c:if>
						<c:if test="${(orderInfo.productStatus eq '2')}"><label class="colorTrips">审核不通过</label></c:if>
						<c:if test="${(orderInfo.productStatus eq '3')}"><label class="colorTrips">已退款</label></c:if>
						<c:if test="${(orderInfo.productStatus eq '4')}"><label class="colorTrips">已换货</label></c:if> --%>
						<c:if test="${(orderInfo.product.status eq '0')}"><label class="colorTrips">下架</label></c:if>
						<c:if test="${(orderInfo.product.status eq '1')}"><label class="colorTrips">上架</label></c:if>
					</td>
		    </tr>	
		    </c:forEach>
		</table>
	</body>
</html>