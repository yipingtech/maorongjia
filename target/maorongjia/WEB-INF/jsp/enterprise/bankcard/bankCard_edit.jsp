<%--

	bankCard_edit.jsp

	Create by MCGT

	Create time 2015-08-07

	Version 1.0

	Copyright 2013 Messcat, Inc. All rights reserved.

 --%>
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
				$("#bankCardForm").validate({
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
			<div class="ropt"><input type="button" class="defaultButton" value="返回" onclick="location.href='${ctx}/bankcard/bankCardAction!retrieveAllBankCards.action'"/></div>
		</div>

		<form action="bankCardAction!editBankCard.action" method="post" id="bankCardForm" name="bankCardForm" enctype="multipart/form-data">
			<table  align="center" class="listTable3">
				<input type="hidden" name="id" value="${bankCard.id}"/>
				<tr>
					<td class="pn-flabel" width="100px">开户银行（工商）</td>
					<td><input type="text" id="bankCardFlag" name="bankCard.bankCardFlag" value="${bankCard.bankCardFlag}" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">银行卡号</td>
					<td><input type="text" id="bankCardNum" name="bankCard.bankCardNum" value="${bankCard.bankCardNum}" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">持卡人</td>
					<td><input type="text" id="bankCardMember" name="bankCard.bankCardMember" value="${bankCard.bankCardMember}" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">银行卡开户省市地址</td>
					<td><input type="text" id="bankCardAddress" name="bankCard.bankCardAddress" value="${bankCard.bankCardAddress}" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">银行卡开户网点</td>
					<td><input type="text" id="bankCardPoint" name="bankCard.bankCardPoint" value="${bankCard.bankCardPoint}" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">银行卡添加时间或为最后编辑时间（默认银行卡）</td>
					<td><input type="text" id="bankCardTime" name="bankCard.bankCardTime" value="${bankCard.bankCardTime}" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">备用/备注字段</td>
					<td><input type="text" id="bankCardRemarks" name="bankCard.bankCardRemarks" value="${bankCard.bankCardRemarks}" /></td>
				</tr>
				<tr>
					<td class="pn-flabel" width="100px">状态</td>
					<td><input type="text" id="status" name="bankCard.status" value="${bankCard.status}" /></td>
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