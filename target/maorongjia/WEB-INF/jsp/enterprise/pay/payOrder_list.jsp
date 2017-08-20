<%@ page contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>订单列表</title>
		<style type="text/css">
		a {
				color: #7aa76d;
				text-decoration: none;
			
				-webkit-transition: 0.15s color ease;
				   -moz-transition: 0.15s color ease;
				    -ms-transition: 0.15s color ease;
				     -o-transition: 0.15s color ease;
				        transition: 0.15s color ease;
			}
		
		</style>
		<%@ include file="/common/taglibs.jsp"%>
		<%@ include file="/common/meta.jsp"%>
		<%@ include file="/common/content.jsp"%>
		<meta name="viewport" content="width=800, user-scalable=no"/>
<!-- 		<link rel="stylesheet" href="../ueditor/dialogs/Avgrund-master/css/avgrund.css"> -->
<!-- 		<link rel="stylesheet" href="../ueditor/dialogs/Avgrund-master/css/demo.css"> -->
		<script type="text/javascript" src="../ueditor/dialogs/Avgrund-master/js/avgrund.js"></script>
		<script>
		$(function(){
		  $(".send-product").click(function(){
		     $("#text-orderNum").val($(this).siblings(".payOrderNum").val());
			 $(".pay-order-num").val($(this).siblings(".payOrderNum").val());
		     Avgrund.show( "#default-popup" );
		  });
		  
		  $("#btn-send-product").click(function(){
		     if (null!=$("#text-invoiceNum").val()&&$("#text-invoiceNum").val()!="") {	
		       $("#form-send-product").attr("action","payOrderAction!editSendShippingType.action"); 				     			
			} else {		  
			  alert("发货单号不能为空");
			  return false;
			}
		  });
		  
		  $(".avgrund-cover").click(function(){
		    Avgrund.hide();
		  });
		  
		});
			function doYouWantToDel() {
				if(confirm('您真的要删除吗？')) {
					return true;
				}
				return false;
			}
			
		</script>
		<style>
		#formType label{
		    display: inline-block;
		width:80px;
		    margin-right: 20px;
		    margin-bottom:8px;
		}
		#formType input,#formType select,#formType option{
		width:160px;
		margin-right: 20px;
		}
		</style>
	</head>
	<body><p></p>
		<div class="rhead">
			<div class="rpos">
				当前位置: 订单管理- 订单列表
			</div>
			<!-- <div class="ropt">
				<a href="payOrderAction!newPage.action" class="btn">新增</a>
			</div> -->
			<div class="clear"></div>
		</div>
		<div class="rhead" style="    height: 50px;">
			<form id="formType" name="formType" action="payOrderAction!queryAllOrderPay.action" method="post">
				<!-- 条件搜索 -->
			<label>订单号</label><input type="text" name="payOrder.orderNum" value="${payOrder.orderNum}"/>
	<%-- 		<label>会员编号</label><input type="text" name="payOrder.member.id" value="${payOrder.member.id}" size="8"/> --%>
			<label>收货人</label><input type="text" name="payOrder.name" value="${payOrder.name}" size="8"/>
			<label>订单状态</label>
			<select name="payOrder.payStatus">
			<option value="">--请选择订单状态--</option>
			<option value="0" <c:if test="${payOrder.payStatus eq '0'}">selected="selected"</c:if>>--待付款--</option>
			<option value="1" <c:if test="${payOrder.payStatus eq '1'}">selected="selected"</c:if>>--已付款--</option>
			</select>
			<label>商品配送情况</label>
			<select name="payOrder.shippingStatus">
			<option value="">--请选择商品配送状态--</option>
			<option value="0" <c:if test="${payOrder.shippingStatus eq '0'}">selected="selected"</c:if> >--待发货--</option>
			<option value="1" <c:if test="${payOrder.shippingStatus eq '1'}">selected="selected"</c:if>>--已发货--</option>
			<option value="2" <c:if test="${payOrder.shippingStatus eq '2'}">selected="selected"</c:if>>--已签收--</option>
			<option value="3" <c:if test="${payOrder.shippingStatus eq '3'}">selected="selected"</c:if>>--备货中--</option>
			</select>
			<br/>
			<label  style="margin-right: 16px;">开始时间</label>
	        <input type="text" name="startTime" onchange="checkDate()" id="startTime" value="${startTime}" readonly size="8" />
			<script type="text/javascript">
				Calendar.setup({
					inputField     : "startTime",
					button	  	   : "startTime",
					/*ifFormat       :  "%Y-%m-%d %H:%M",							
			        showsTime      :    true,
			        timeFormat     :    "24"*/
					ifFormat       :    "%Y-%m-%d"
				});
			</script>
			
	       	<label style="margin-right: 16px;">结束时间</label>
	      	<input type="text" name="endTime" onchange="checkDate()" id="endTime" value="${endTime}" readonly size="8"/>
			<script type="text/javascript">
				Calendar.setup({
					inputField     : "endTime",
					button	  	   : "endTime",
					/*ifFormat       :  "%Y-%m-%d %H:%M",							
			        showsTime      :    true,
			        timeFormat     :    "24"*/
					ifFormat       :    "%Y-%m-%d"
				});
			</script> 
			
			<input style="width:78px;height: 25px;" type="button" class="btn" value="搜索" onclick="ljk()"/>
			
			<a href="javascript:void(0);"  class="btn" onclick="javascript:$('#formType').attr('action', 'payOrderAction!export.action');$('#formType').submit();" >导出订单</a>
			
			</form>
		</div>
		<script>
			function ljk(){
				var start  = document.getElementById('startTime');
				var end = document.getElementById('endTime');
				
				var startText = start.value.split('-').join('');
				var endText = end.value.split('-').join('');
				
				if( parseInt(endText)<=parseInt(startText) ){
					alert('开始时间不能大于结束时间!');
					return false;
				}else{
					$('#formType').submit();
				}
				
				
			}
		</script>
	    <c:if test="${not empty payOrders}">
			<s:form action="payOrderAction!queryAllOrderPay.action"
					namespace="/pay" method="post" name="form1" theme="simple" id="form1">
					<input type="hidden" name="flag" value="${flag}"/>
					<input type="hidden" name="payOrder.orderNum" value="${payOrder.orderNum}"/>
					<input type="hidden" name="payOrder.member.id" value="${payOrder.member.id}"/>
					<input type="hidden" name="payOrder.name" value="${payOrder.name}"/>
					<input type="hidden" name="payOrder.payStatus" value="${payOrder.payStatus}"/>
					<input type="hidden" name="payOrder.shippingStatus" value="${payOrder.shippingStatus}"/>
					<input type="hidden" name="startTime" value="${startTime}"/>
					<input type="hidden" name="endTime" value="${endTime}"/>
				<!-- 分页 -->
				<%@ include file="../../common/pager.jsp"%>
			</s:form>
			<br/>
		</c:if>
		
<!-- 		<div style="float: right;margin-top:10px;margin-right: 100px;"> -->
		
<!-- 		<a href="payOrderAction!queryAllOrderPay.action?flag=day" class="btn">今天</a> -->
<!-- 		<a href="payOrderAction!queryAllOrderPay.action?flag=week" class="btn" style="margin-left: 20px;">一周内</a> -->
<!-- 		<a href="payOrderAction!queryAllOrderPay.action?flag=month" class="btn" style="margin-left: 20px;">一个月内</a> -->
<!-- 		</div> -->
		<table class="table table-bordered">
			<tr>
				<th>序号</th>
				<th>订单号</th>
				<th>会员编号</th>
				<th>会员昵称</th>
				<th>收货人姓名</th>
				<th>创建订单时间</th>
				<th>总金额</th>
<!-- 				<th>使用积分金额</th> -->
<!-- 				<th>使用红包金额</th> -->
				<th>应付款金额</th>
				<th>订单状态</th>
				<th>商品配送情况</th>
				<th>退换货状态</th>
				<th>操作</th>
			</tr>

			<c:forEach var="bean" items="${payOrders}"
				varStatus="status">
				<tr>
					<td>${status.count}</td>
					<td><a href="payOrderAction!queryOrderAndPay.action?payOrder.orderNum=${bean.orderNum}">${bean.orderNum}</a></td>
					<td>${bean.member.id}</td>
					<td>${bean.member.nickname}</td>
					<td>${bean.name}</td>
					<td><fmt:formatDate value="${bean.createTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td><fmt:formatNumber value='${bean.productAmount}' maxFractionDigits='2'/></td>
<%-- 					<td><fmt:formatNumber value='${bean.integralMoney}' maxFractionDigits='2'/></td> --%>
<%-- 					<td><fmt:formatNumber value='${bean.bonus}' maxFractionDigits='2'/></td> --%>
					<td><fmt:formatNumber value='${bean.orderAmount}' maxFractionDigits='2'/></td>
					<td>
						<c:if test="${bean.payStatus eq '0'}"><label style="color:red;">待付款</label></c:if>
						<c:if test="${bean.payStatus eq '1'}">已付款</c:if>
					</td>
					<td>
						<c:if test="${bean.shippingStatus eq '0'}">待发货</c:if>
						<c:if test="${bean.shippingStatus eq '1'}">已发货</c:if>
						<c:if test="${bean.shippingStatus eq '2'}"><label style="color:red;">已签收</label></c:if>
						<c:if test="${bean.shippingStatus eq '3'}"><label>备货中</label></c:if>
					</td>
					<td>
					    <c:if test="${bean.productDrawback==null}">无</c:if>
					    <c:if test="${bean.productDrawback.returnStatus=='0'}">无</c:if>
					    <c:if test="${bean.productDrawback.returnStatus=='1'}">申请退货中</c:if>
					    <c:if test="${bean.productDrawback.returnStatus=='2'}">退货成功</c:if>
					    <c:if test="${bean.productDrawback.returnStatus=='3'}">退货失败</c:if>
					    <c:if test="${bean.productDrawback.returnStatus=='4'}">申请换货中</c:if>
					    <c:if test="${bean.productDrawback.returnStatus=='5'}">换货成功</c:if>
					    <c:if test="${bean.productDrawback.returnStatus=='6'}">换货失败</c:if>
					</td>
					<td>
						<a class="btn btn-info btn-small" href="payOrderAction!queryOrderAndPay.action?payOrder.orderNum=${bean.orderNum}">查看</a>
						&nbsp; &nbsp;
						<a class="btn btn-primary btn-small" href="payOrderAction!retrievePayOrderById.action?payOrder.orderNum=${bean.orderNum}">编辑</a>
						&nbsp; &nbsp;
<%--						<c:if test="${bean.payStatus eq '0'}">--%>
<%--						<a class="btn btn-primary btn-small" href="payOrderAction!editPayType.action?payOrder.orderNum=${bean.orderNum}">付款</a>--%>
<%--						</c:if>						--%>
						&nbsp; &nbsp;
						<a class="btn btn-danger btn-small" href="payOrderAction!delPayOrderByOrderNum.action?payOrder.id=${bean.id}"
								 onclick="return doYouWantToDel();">移除</a>
								 &nbsp; &nbsp;
					  <c:if test="${(bean.payStatus eq '1') and (bean.shippingStatus eq '0')}">
						<a class="btn btn-info btn-small send-product" style="background-color: green;color: white;">去发货</a>
						<input type="hidden" class="payOrderNum" value="${bean.orderNum}"/>
				  	  </c:if>
					</td>
				</tr>
			</c:forEach>
		</table>
	   <form action="payOrderAction!editSendShippingType.action" method="post" id="form-send-product">
			<div id="default-popup" class="avgrund-popup">
	          &nbsp;&nbsp;&nbsp;
	                   订单号：<input type="text" id="text-orderNum" disabled="disabled" style="width: 250px;height: 25px;"/><br/>
			   发货单号：<input type="text" name="payOrder.invoiceNum" id="text-invoiceNum"  placeholder="请输入发货单号" style="width: 250px;height: 25px;margin-top: 20px"/>
			  &nbsp;&nbsp;&nbsp; &nbsp;&nbsp; 物流：<select name="payOrder.shippingName" style="width: 250px;height: 25px;margin-top: 20px">
					<option value="">--请选择快递--</option>
					<option value="顺丰">--顺丰--</option>
					<option value="申通">--申通--</option>
					<option value="中通">--中通--</option>
					<option value="韵达">--韵达--</option>
				</select>
			   <input type="hidden" name="payOrder.orderNum" class="pay-order-num" value=""/>
			   <div style="margin-top: 30px;">
			  <button type="submit" id="btn-send-product" style="margin-left:40%;">确认发货</button>			
			   </div>
			</div>
	</form>
		<div class="avgrund-cover"></div>
	</body>	
</html>