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
if($.browser.msie)
		window.attachEvent('onbeforeunload',function(){$("#submitButton").val('请等待').attr("disabled","true");});
	else//在chrome中不起作用
 		window.addEventListener('onbeforeunload',function(){$("#submitButton").val('请等待').attr("disabled","true");});
//初始化地址
  addressInit('cmbProvince', 'cmbArea', 'county', '广东', '${tusers.area}', '${tusers.county}'); 		
		$("#name").focus();
		$("#userForm").validate({
			 rules: { 
				name: { 
        			required: true, 
        			maxlength:20
    			},
    			area: {
    				required: true,
    				maxlength:12
    			},
    			workunit: {
    				required: true,
    				maxlength:50
    			},    			
    			workphone:
    			{
    				isPhoneCall: true,
    				maxlength:50
    			},
    			address:{
    				required: true,
    				maxlength:30
    			},
    			idcardno: {
                    isidcard: true , 
        			maxlength:30
    			},
            	email: {
                    email: true , 
        			maxlength:25
    			},
        	    fax:
        	    {
        	    	isPhoneCall: true,
        	    	maxlength:25
				},
            	mobile: {
                    isPhoneCall: true,
			        maxlength:50
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
 	 	   	当前位置: 用户管理 - 编辑用户信息
 	 	</div>
 	 	<div class="ropt"><a class="btn" href="javascript:history.back();">返回</a></div>
    	<div class="clear"></div>
  	</div>
		<form action="usersAction!updateInfo.action" method="post" id="userForm"
		 name="userForm"  enctype="multipart/form-data">
			<input type="hidden" name="id" value="${tusers.id}"/>
		<table class="table table-bordered inner-table">
			<tr>
				<th>用户名</th>
				<td>${tusers.loginName}</td>
			</tr>
			<tr>
				<th>真实名字</th>
				<td><input type="text" name="name" value="${tusers.name}"/></td>
			</tr>
			<tr>
			  <th>所属角色</th>
			  <td>
					<c:forEach var="usersRolesList" items="${usersRolesList}">
									${usersRolesList.roles.name}
										&nbsp;&nbsp;
									
					</c:forEach>
			  </td>
			</tr>
			
			 <!-- <tr>
				<th>性别</td>
				<td>
				  <select name="sex">
						<option value="1">男</option>
						<option value="0" 
						<c:if test="${tusers.sex==0}">selected</c:if>>
							女
						</option>
					</select> 
				</td>
			</tr>-->
			<tr>
				<th>地区</th>
				<td><input type="text" name="area" value="${tusers.area}" readonly="readonly"/></td>
			</tr>
			<tr>
				<th>区/县</th>
				<td>
					<select id="cmbProvince" disabled></select>省&nbsp;&nbsp;
					<select id="cmbArea"    disabled></select>市&nbsp; &nbsp;
					<select id="county"  disabled></select>区/县&nbsp;&nbsp;
					<input type="hidden" name="county" value="${tusers.county}"/>
				</td>
			</tr>			
			<tr>
				<th>地址</th>
				<td><input type="text" name="address" value="${tusers.address}" /></td>
			</tr>
			<tr>
				<th>工作单位</th>
				<td><input type="text" name="workunit" value="${tusers.workunit}" readonly="readonly"/></td>
			</tr>
			<tr>
				<th>联系电话</th>
				<td><input type="text" name="workphone" value="${tusers.workphone}"/></td>
			</tr>
			<tr>
				<th>传真号码</th>
				<td><input type="text" name="fax" value="${tusers.fax}"/></td>
			</tr>
			<tr>
				<th>联系E-mail</th>
				<td><input type="text" name="email" value="${tusers.email}"/></td>
			</tr>
			<tr>
				<th>移动电话</th>
				<td><input type="text" name="mobile" value="${tusers.mobile}"/></td>
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

