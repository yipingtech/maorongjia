<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/common/content.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>更新模板</title>
		<script>
			$(document).ready(function(){
				$("#productDrawbackForm").validate({
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
			<div class="rpos">当前位置: 订单管理 - 退货单审核 - 退货单编辑</div>
			<div class="ropt"><input type="button" class="defaultButton" value="返回" onclick="location.href='javascript:history.back()'"/></div>
		</div>

		<form action="productDrawbackAction!editProductDrawback.action" method="post" id="productDrawbackForm" name="productDrawbackForm" enctype="multipart/form-data">
			<table class="table table-bordered inner-table" align="center" class="listTable3">
				<input type="hidden" name="id" value="${productDrawback.id}"/>
				<tr>
					<td class="pn-flabel" width="100px">订单号</td>
					<td><input type="text" id="orderNum" name="productDrawback.orderNum" value="${productDrawback.orderNum}" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">申请人ID</td>
					<td><input type="text" id="memberId" name="member.loginName" value="${productDrawback.memberId}" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">退换商品id号</td>
					<td><input type="text" id="productId" name="productDrawback.productId" value="${productDrawback.productId}" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">申请退款金额</td>
					<td><input type="text" id="drawbackMoney" name="productDrawback.drawbackMoney" value="${productDrawback.drawbackMoney}" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">退款原因</td>
					<td><input type="text" id="drawbackCause" name="productDrawback.drawbackCause" value="${productDrawback.drawbackCause}" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">图片名称</td>
					<td><input type="text" id="imageName" name="productDrawback.imageName" value="${productDrawback.imageName}" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">申请时间</td>
					<td><input type="text" id="applyTime" name="productDrawback.applyTime" value="${productDrawback.applyTime}" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">审核时间</td>
					<td><input type="text" id="auditTime" name="productDrawback.auditTime" value="${productDrawback.auditTime}" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">联系商家手机号</td>
					<td><input type="text" id="sendPhone" name="productDrawback.sendPhone" value="${productDrawback.sendPhone}" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">审核通过寄货地址</td>
					<td><input type="text" id="sendAddress" name="productDrawback.sendAddress" value="${productDrawback.sendAddress}" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">退换状态（0:申请中 1:审核通过 2:审核不通过 3:已退款 4:已换货）</td>
					<td><input type="text" id="returnStatus" name="productDrawback.returnStatus" value="${productDrawback.returnStatus}" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">状态（0:未删除 1:已删除）</td>
					<td><input type="text" id="status" name="productDrawback.status" value="${productDrawback.status}" /></td>
				</tr>

				<tr>
					<td></td>
					<td>
						<input type="submit" class="defaultButton" value=" 提 交 "/>
					</td>
				</tr>
		 	</table>
		</form>
	</body>
</html>