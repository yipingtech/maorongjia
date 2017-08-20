<%@ page contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>MCCMS</title>
		<%@ include file="/common/taglibs.jsp"%>
		<%@ include file="/common/meta.jsp"%>
		<%@ include file="/common/content.jsp"%>
		<script>
		$(document).ready(function(){
			$("#passwordForm").validate({
				 rules: {
	            	newpassword: {
	                    required: true, 
	        			maxlength:16,
	        			minlength:5
	        	    },
	        	    repassword:{
	        	    	required: true, 
	        			maxlength:16,
	        			minlength:5,
	        			equalTo:"#newpassword"
	        	    }
				},
				messages: {
					repassword:{
						//两次密码输入不一致
						equalTo:'两次密码输入不一致'
					}	
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
			<div class="rpos">
				当前位置: 重置密码
			</div>
			<div class="ropt"><a class="btn" href="javascript:history.back();">返回</a></div>
			<div class="clear"></div>
		</div>

		<form action="updatePasswordAction!reset.action" method="post"
			name="passwordForm" id="passwordForm">
			<input type="hidden" name="id" value="${id }"/>
			<table class="table table-bordered inner-table">
				<!-- <tr>
				<td>原密码</td><td><input type="password" name="oldpassword"/></td>
			</tr>-->
				<tr>
					<th>
						新密码
					</th>
					<td>
						<input type="password" name="newpassword" id="newpassword" />
					</td>
				</tr>
				<tr>
					<th>
						确认密码
					</th>
					<td>
						<input type="password" name="repassword" />
					</td>
				</tr>
				<tr>
					<td colspan="2" style="text-align:center;">
						<input type="submit" class="btn" value="提 交 "/>
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>

