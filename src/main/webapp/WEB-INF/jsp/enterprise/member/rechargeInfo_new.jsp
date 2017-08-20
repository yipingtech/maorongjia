<%--

	rechargeInfo_new.jsp

	Create by MCGT

	Create time 2015-04-22

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
				$("#rechargeInfoForm").validate({
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
			<div class="ropt"><a class="btn" href="${ctx}/recharge/rechargeInfoAction!retrieveAllRechargeInfos.action">返回</a></div>
		</div>

		<form action="rechargeInfoAction!newRechargeInfo.action" method="post" id="rechargeInfoForm" name="rechargeInfoForm" enctype="multipart/form-data">
			<table class="table table-bordered inner-table">
				<tr>
					<th>充值或消费金额</th>
					<td><input type="text" id="rechargeAmount" name="rechargeInfo.rechargeAmount" /></td>
				</tr>
				<tr>
					<th>充值或消费时间</th>
					<td><input type="text" id="rechargeTime" name="rechargeInfo.rechargeTime" /></td>
				</tr>
				<tr>
					<th>充值支付方式（0：微信支付 1：支付宝 2：银行卡）</th>
					<td><input type="text" id="rechargeType" name="rechargeInfo.rechargeType" /></td>
				</tr>
				<tr>
					<th>充值使用卡号</th>
					<td><input type="text" id="rechargeCard" name="rechargeInfo.rechargeCard" /></td>
				</tr>
				<tr>
					<th>备注状态（0：充值 1：消费）</th>
					<td><input type="text" id="rechargeComments" name="rechargeInfo.rechargeComments" /></td>
				</tr>
				<tr>
					<th>状态（0：正常 1：删除）</th>
					<td><input type="text" id="status" name="rechargeInfo.status" /></td>
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