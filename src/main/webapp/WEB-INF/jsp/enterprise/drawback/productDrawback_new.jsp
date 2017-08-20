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
			<div class="rpos">当前位置: 模块 - 子模块</div>
			<div class="ropt"><a class="btn" href="javascript:history.back();">返回</a></div>
		</div>

		<form action="productDrawbackAction!applyDrawBack.action" method="post" id="productDrawbackForm" name="productDrawbackForm" enctype="multipart/form-data">
			<table  align="center" class="table table-bordered inner-table">
				<tr>
					<td class="pn-flabel" width="100px">订单号</td>
					<td><input type="text" id="orderNum" name="productDrawback.orderNum" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">申请人ID</td>
					<td><input type="text" id="memberId" name="productDrawback.memberId" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">退换商品id号</td>
					<td><input type="text" id="productId" name="productDrawback.productId" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">申请退款金额</td>
					<td><input type="text" id="drawbackMoney" name="productDrawback.drawbackMoney" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">退款原因</td>
					<td><input type="text" id="drawbackCause" name="productDrawback.drawbackCause" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">图片名称</td>
					<td><input type="file" id="imageName" name="productDrawback.imageName" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">联系商家手机号</td>
					<td><input type="text" id="sendPhone" name="productDrawback.sendPhone" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">审核通过寄货地址</td>
					<td><input type="text" id="sendAddress" name="productDrawback.sendAddress" /></td>
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