<%--

	cartInfo_new.jsp

	Create by MCGT

	Create time 2015-04-10

	Version 1.0

	Copyright 2013 Messcat, Inc. All rights reserved.

 --%>
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
				$("#cartInfoForm").validate({
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
			<div class="ropt"><a class="btn" href="${ctx}/cart/cartInfoAction!retrieveAllCartInfos.action">返回</a></div>
		</div>

		<form action="cartInfoAction!newCartInfo.action" method="post" id="cartInfoForm" name="cartInfoForm" enctype="multipart/form-data">
			<table class="table table-bordered inner-table">
				<tr>
					<th>会员ID</th>
					<td><input type="text" id="memberId" name="cartInfo.memberId" /></td>
				</tr>
				<tr>
					<th>商品ID</th>
					<td><input type="text" id="productId" name="cartInfo.productId" /></td>
				</tr>
				<tr>
					<th>商品单价</th>
					<td><input type="text" id="productPrice" name="cartInfo.productPrice" /></td>
				</tr>
				<tr>
					<th>商品总价</th>
					<td><input type="text" id="productTotal" name="cartInfo.productTotal" /></td>
				</tr>
				<tr>
					<th>购买数量</th>
					<td><input type="text" id="buyAmount" name="cartInfo.buyAmount" /></td>
				</tr>
				<tr>
					<th>添加时间</th>
					<td><input type="text" id="createTime" name="cartInfo.createTime" /></td>
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