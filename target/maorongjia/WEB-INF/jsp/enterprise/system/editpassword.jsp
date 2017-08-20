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
	$("#oldpassword").focus();
	$("#passwordForm").validate({
		 rules: { 
			oldpassword: { 
       			required: true, 
       			maxlength:16,
       			minlength:5,
       			remote: "updatePasswordAction!checkPassword.action?math="+Math.random()
   			},
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
			oldpassword: {
				//原密码错误
				remote: '原密码错误'
			},
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
			<div class="rpos">当前位置: 修改密码</div>
			<div class="clear"></div>
		</div>
		<form action="updatePasswordAction!update.action" method="post" name="passwordForm" id="passwordForm">
		<table class="table table-bordered inner-table">
			<tr>
				<th>原密码</th>
				<td><input type="password" name="oldpassword"/></td>
			</tr>
			<tr>
				<th>新密码</th>
				<td><input type="password" name="newpassword" id="newpassword"/></td>
			</tr>
			<tr>
				<th>确认密码</th>
				<td>
					<input type="password" name="repassword" />
				</td>
			</tr>
			<tr>
				<td colspan="2" style="text-align:center;">
					<input type="submit" class="btn" value="修改密码 "/>
				</td>
			</tr>
		</table>
		
		</form>
	</body>
</html>

