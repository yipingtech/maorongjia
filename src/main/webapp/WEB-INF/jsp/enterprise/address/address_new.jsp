<%--

	address_new.jsp

	Create by MCGT

	Create time 2015-04-17

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
				$("#addressForm").validate({
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
			<div class="ropt"><a class="btn" href="${ctx}/address/addressAction!retrieveAllAddresss.action">返回</a></div>
		</div>

		<form action="addressAction!newAddress.action" method="post" id="addressForm" name="addressForm" enctype="multipart/form-data">
			<table class="table table-bordered inner-table">
				<tr>
					<th>会员</th>
					<td><input type="text" id="member" name="address.member" /></td>
				</tr>
				<tr>
					<th>会员所在省</th>
					<td><input type="text" id="province" name="address.province" /></td>
				</tr>
				<tr>
					<th>会员所在市</th>
					<td><input type="text" id="city" name="address.city" /></td>
				</tr>
				<tr>
					<th>会员所在区</th>
					<td><input type="text" id="area" name="address.area" /></td>
				</tr>
				<tr>
					<th>会员所在详细地址</th>
					<td><input type="text" id="address" name="address.address" /></td>
				</tr>
				<tr>
					<th>新增时间</th>
					<td><input type="text" id="addTime" name="address.addTime" /></td>
				</tr>
				<tr>
					<th>编辑时间</th>
					<td><input type="text" id="editTime" name="address.editTime" /></td>
				</tr>
				<tr>
					<th>收货人姓名</th>
					<td><input type="text" id="consignee" name="address.consignee" /></td>
				</tr>
				<tr>
					<th>邮编</th>
					<td><input type="text" id="postcode" name="address.postcode" /></td>
				</tr>
				<tr>
					<th>固定电话</th>
					<td><input type="text" id="telephone" name="address.telephone" /></td>
				</tr>
				<tr>
					<th>移动电话</th>
					<td><input type="text" id="cellphone" name="address.cellphone" /></td>
				</tr>
				<tr>
					<th>邮箱</th>
					<td><input type="text" id="email" name="address.email" /></td>
				</tr>
				<tr>
					<th>是否为默认地址(1是0否)</th>
					<td><input type="text" id="isdefault" name="address.isdefault" /></td>
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