<%@ page contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>MCCMS</title>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/common/content.jsp"%>
<link href="${ctx}/js/validate/jquery.validate.css" type="text/css"
			rel="stylesheet" />
<script src="${ctx}/js/jquery.js" type="text/javascript"></script>
<script src="${ctx}/js/validate/jquery.validate.js" type="text/javascript"></script>
<script src="${ctx}/js/validate/messages_cn.js" type="text/javascript"></script>
<script>
$(document).ready(function(){
	if($.browser.msie)
		window.attachEvent('onbeforeunload',function(){$("#submitButton").val('请等待').attr("disabled","true");});
	else//在chrome中不起作用
 		window.addEventListener('onbeforeunload',function(){$("#submitButton").val('请等待').attr("disabled","true");});


		
	});		
 </script>
 <script >
 $(document).ready(function(){
	 $("#name").focus();
		$("#userForm").validate({
			 rules: { 
			 	loginName: 
			 	{ 
			 		required: true, 
	 			maxlength:12,
	 			minlength:6,
	 			remote: "usersAction!checkName.action?orgName=''&math="+Math.random(),
	 			isLoginName: true
			 	},
				name: { 
	 			required: true, 
	 			maxlength:12
				},
				password: {
					required: true, 
	 			minlength:6,
	 			maxlength:100
				},
				repassword: {
					required: true, 
					minlength:6,
	 			maxlength:100,
	 			equalTo:"#newpassword"
				},
				checkmenu: {
					required: true
				},
				workphone:
				{
					isPhoneCall: true,
					maxlength:50
				},
				area: {
					required: true,
					maxlength:12
				},
				workunit: {
					required: true,
					maxlength:50
				},
				address: {
	             required: true , 
	 			maxlength:30
				},
	     	email: {
	             email: true, 
	 			maxlength:25
				},
	 	    fax:{
	 	    	isPhoneCall: true,
	 	    	maxlength:25
				},
	     	 mobile: {
	                  isPhoneCall: true,
			 			maxlength:50
				}
			},
			messages: {
				loginName: 
			 	{ 
			 		required: '请输入', 
	 			maxlength:'请输入长度不超过12的字符串',
	 			minlength:'请输入长度不小于6的字符串',
	 			remote: '该用户已存在',
			 	},
				name: { 
	 			required: '请输入',
	 			maxlength:'请输入长度不超过12的字符串',
				},
				password: {
					required: '请输入',
	 			minlength:'请输入长度不小于6的字符串',
	 			maxlength:'请输入长度不超过100的字符串',
				},
				repassword: {
					required:'请输入',
					minlength:'请输入长度不小于6的字符串',
	 			maxlength:'请输入长度不超过100的字符串',
	 			equalTo:'两次输入不一致'
				},
				checkmenu: {
					required: '请选择',
				},
				workphone:
				{
					isPhoneCall: '请输入正确的联系电话',
					maxlength:'请输入长度不超过50的字符串',
				},
				area: {
					required: '请输入',
					maxlength:'请输入长度不超过12的字符串',
				},
				workunit: {
					required: '请输入',
					maxlength:'请输入长度不超过50的字符串',
				},
				address: {
	             required: '请输入',
	 			maxlength:'请输入长度不超过30的字符串',
				},
	     	email: {
	             email: '请输入正确的邮箱',
	 			maxlength:'请输入长度不超过25的字符串',
				},
	 	    fax:{
	 	    	isPhoneCall: '请输入正确的联系电话',
	 	    	maxlength:'请输入长度不超过25的字符串',
				},
	     	 mobile: {
	              isPhoneCall: '请输入正确的联系电话',
			 		 maxlength:'请输入长度不超过50的字符串',
				}
			},
			success: function(label) {
				label.html("&nbsp;").addClass("checked");
	     }	
		});
 });
 </script>
 <style>
 <!--
	#userRole label.error{
	 	position: absolute;
		background-position-y: 6px;
	 	margin-left: 341px;
	}
 -->
 </style>
	</head>
	<body>
		<div class="rhead">
			<div class="rpos">
				当前位置: 系统配置 - 用户管理 - 添加用户信息
			</div>
			<div class="ropt"><a class="btn" href="javascript:history.back();">返回</a></div>
			<div class="clear"></div>
		</div>
		<form action="usersAction!add.action" method="post" id="userForm"
		 name="userForm"  enctype="multipart/form-data">
		
		<table class="table table-bordered inner-table">
			<tr>
				<th>用户名</th>
				<td><input type="text" name="loginName"/></td>
			</tr>
			<tr>
				<th>密码</th>
				<td>
					<input type="password" name="password" id="newpassword"/>
				</td>
			</tr>
			<tr>
				<th>重复密码</th>
				<td>
					<input type="password" name="repassword"/>
				</td>
			</tr>
			<tr>
				<th>真实名字</th>
				<td><input type="text" name="name"/></td>
			</tr>
			<tr>
			  <th>所属角色</th>
			  <td id="userRole"><!-- 省级管理员或者超级管理员 -->
			      <c:forEach var="roles" items="${rolesList}">
					<input type="radio" value="${roles.id}" name="checkmenu"/>${roles.name}&nbsp;&nbsp;
				  </c:forEach>
			  </td>
			</tr>
			<!-- <tr>
			 	<th>性别</th>
				<td>
				   <select name="users.sex">
						<option value="1">男</option>
						<option value="0">女	</option>
					</select>
				</td>
			</tr>-->
			<tr>
				<th>工作单位</th>
				<td><input type="text" name="workunit" value=""/></td>
			</tr>
			<tr>
				<th>地区</th>
				<td><input type="text" name="area" id="showArea"
					<c:if test="${isAdmin==2}"> value="${users.area}" readonly</c:if>
				/></td>
			</tr>
			<%-- <tr>
				<th>区/县</th>
				<td>
					<select id="cmbProvince" disabled></select>省&nbsp;&nbsp;
					<select id="cmbArea"  <c:if test="${isAdmin==2}">  disabled</c:if>></select>市&nbsp; &nbsp;
					<select id="county" name="county"></select>区/县&nbsp;&nbsp;
				</td>
			</tr> --%>

			 <!--<tr>
				<th>身份证号码</th><td><input type="text" name="address"/></td>
			</tr>  -->
			<tr>
				<th>地址</th>
				<td><input type="text" name="address"  value="${tusers.address}"/></td>
			</tr>			
			 <tr>
				<th>联系电话</th>
				<td><input type="text" name="workphone"/></td>
			</tr>
			<tr>
				<th>传真号码</th>
				<td><input type="text" name="fax"/></td>
			</tr>
			
			<tr>
				<th>联系E-mail</th>
				<td><input type="text" name="email"/></td>
			</tr>
			<tr>
				<th>移动电话</th>
				<td><input type="text" name="mobile"/></td>
			</tr>
			<tr>
				<th>状态</th>
				<td>
					<select name="state">
						<option value="1">已启用</option>
						<option value="0"><font color="red">已停用</font></option>
					</select>
				</td>
			</tr>
			<tr>
				<td colspan="2" style="text-align:center;">
					<input type="submit" class="btn" value="提 交 "/>
				</td>
			</tr>
	 	</table>
	 	<s:token></s:token>
	</form>
	</body>
</html>

