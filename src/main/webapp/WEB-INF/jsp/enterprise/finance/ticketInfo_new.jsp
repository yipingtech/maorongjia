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
				$("#ticketInfoForm").validate({
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
			<div class="rpos">当前位置: 会员管理（vip） - 添加卡券</div>
			<div class="ropt"><a class="btn" href="${ctx}/finance/ticketInfoAction!retrieveAllTicketInfos.action">返回</a></div>
		</div>

		<form action="ticketInfoAction!newTicketInfo.action" method="post" id="ticketInfoForm" name="ticketInfoForm" enctype="multipart/form-data">
			<table class="table table-bordered inner-table">
				<tr>
					<th>金额</th>
					<td><input type="text" id="ticketAmount" name="ticketInfo.ticketAmount" /></td>
				</tr>
				<tr>
					<th>限制金额（满于多少元才能使用）</th>
					<td><input type="text" id="restrictAmount" name="ticketInfo.restrictAmount" /></td>
				</tr>
				<tr>
					<th>有效期（天）</th>
					<td><input type="text" id="restrictDay" name="ticketInfo.restrictDay" /></td>
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