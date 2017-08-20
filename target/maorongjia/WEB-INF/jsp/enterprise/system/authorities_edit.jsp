<%@ page contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>MCCMS</title>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/common/content.jsp"%>
<link type="text/css" href="${ctx}/resource/AsyncBox/skins/ZCMS/asyncbox.css" rel="stylesheet" />
<script type="text/javascript" src="${ctx}/resource/AsyncBox/AsyncBox.v1.4.js"></script>
<script>
$(document).ready(function(){

		$("#name").focus();
		$("#linksForm").validate({
			 rules: { 
			 	fatherAuthName : {
			 		required: function(){
			 			if(authoritiesType == '2'){
			 				return false;
			 			}
			 		}
			 	},
				name: { 
        			required: true, 
        			maxlength:50,
        			remote: "authoritiesAction!checkName.action?orgName=${authorities.name}&math="+Math.random()
    			},
    			displayName:
    			{
    				required: true, 
        			maxlength:50,
        			remote: "authoritiesAction!checkDisplayName.action?orgName=${authorities.displayName}&math="+Math.random()
    			}
    			
			},
			messages: {
				fatherAuthName : {
			 		//请选择父级权限
					required: '请选择父级权限'
			 	},
				name: {
					//该权限名称已存在
					remote: '该权限名称已存在'
				},
				displayName:
				{
					//该显示名称已存在
					remote: '该显示名称已存在'
				}
			},
			success: function(label) {
				label.html("&nbsp;").addClass("checked");
	        }	
		});
		
		changeType();
		$("#authoritiesType").change(function(){
			changeType();
			//清空父级权限框
			$('#fatherAuthName').val('');
		});
	});	
	
	var authoritiesType = ${authorities.authoritiesType};
	//父级权限树
	function listAuthorities() {
		asyncbox.open({
			//父级权限
			title: '父级权限',
			url : 'authoritiesAction!generateTree.action',
			data : {
				type : authoritiesType,
				id : ${authorities.id}
			},
			width : 300,
    		height : 440,
			btnsbar : $.btn.OKCANCEL,
			callback : function(action, iframe) {
				if(action == 'ok') {
					$("#fatherAuthName").val(iframe.returnValue);
				}
			}
		});
	}
	
	function changeType(){
		authoritiesType = $('#authoritiesType').val();
		if(authoritiesType == '2'){
			$('#fatherAuthName').parent().parent().hide();
		}else{
			$('#fatherAuthName').parent().parent().show();
		}
	}
</script>	
	</head>
	<body style="padding:8px;">
		
		<div class="rhead">
			<div class="rpos">
				当前位置: 系统配置 - 权限管理 - 编辑权限信息
			</div>
			<div class="ropt"><a class="btn" href="javascript:history.back();">返回</a></div>
			<div class="clear"></div>
	  	</div>
 	 
		<form action="authoritiesAction!update.action" method="post" id="linksForm" enctype="multipart/form-data" method="post">
			<input type="hidden" name="authorities.id" value="${authorities.id}"/>
		<table class="table table-bordered inner-table">
			<tr>
				<th>父级权限</th>
				<td>
					<input type="text" name="fatherAuthName" id="fatherAuthName" value="${fatherAuthName}" onfocus="listAuthorities();" readonly/>
				</td>
			</tr>
			<tr>
				<th>权限名称</th>
				<td>
					<input type="text" name="name" value="${authorities.name}"/>
				</td>
			</tr>
			<tr>
				<th>显示名称</th>
				<td>
					<input type="text" name="displayName" value="${authorities.displayName}"/>
				</td>
			</tr>
			<tr>
				<th>
					权限类型
				</th>
				<td>
					<select name="authorities.authoritiesType" id="authoritiesType">
						<option value="1">栏目</option>
						<option value="0" <c:if test="${authorities.authoritiesType == 0}">selected</c:if>>
							菜单
						</option>
						<option value="2" <c:if test="${authorities.authoritiesType == 2}">selected</c:if>>
							功能
						</option>
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

