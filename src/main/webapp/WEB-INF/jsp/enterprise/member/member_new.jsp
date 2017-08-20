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
				$("#memberForm").validate({
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
			<div class="ropt"><a class="btn" href="${ctx}/member/memberAction!queryAllMember.action">返回</a></div>
		</div>

		<form action="memberAction!newMember.action" method="post" id="memberForm" name="memberForm" enctype="multipart/form-data">
			<table class="table table-bordered inner-table">
				<tr>
					<th>登陆账号</th>
					<td><input type="text" id="loginName" name="member.loginName" /></td>
				</tr>
				<tr>
					<th>卡号</th>
					<td><input type="text" id="cardNum" name="member.cardNum" /></td>
				</tr>
				<tr>
					<th>昵称</th>
					<td><input type="text" id="nickname" name="member.nickname" /></td>
				</tr>
				<tr>
					<th>真实姓名</th>
					<td><input type="text" id="realname" name="member.realname" /></td>
				</tr>
				<tr>
					<th>手机号码</th>
					<td><input type="text" id="mobile" name="member.mobile" /></td>
				</tr>
				<tr>
					<th>邮编</th>
					<td><input type="text" id="postcode" name="member.postcode" /></td>
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