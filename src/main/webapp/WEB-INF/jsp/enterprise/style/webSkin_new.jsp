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

		$("#names").focus();
		$("#columnForm").validate({
			 rules: { 
				names: { 
        			required: true, 
        			maxlength:20,
        			remote: "webSkinAction!checkName.action?orgName=''&math="+Math.random()
    			},
            	filename: {
                    required: true ,
			        maxlength:20
        	    },
        	    content:{
        	    	required: true ,
        	    	maxlength:200
        	    }
			},
			messages: {
				names: {
					//该模板名称已存在
					remote: '该模板名称已存在'
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
			<div class="rpos">当前位置: 界面风格 - 模板管理 - 添加模板信息</div>
			<div class="ropt"><a class="btn" href="javascript:history.back();">返回</a></div>
			<div class="clear"></div>
		</div>
 	 <form action="webSkinAction!add.action" method="post" id="columnForm">
 	 <table class="table table-bordered inner-table">
			<tr>
				<th>模板名称</th>
				<td><input type="text" name="names"  size="60" /></td>
			</tr>
			<tr>
				<th>模板文件夹名称</th>
				<td><input type="text" name="filename"  size="60" /></td>
			</tr>
			<tr>
				<th>模板描述</th>
				<td>
					<textarea rows="10" cols="47" name="content"></textarea>
				</td>
			</tr>
			<tr>
				<th>状态</th>
				<td>
					<select name="webSkin.state">
						<option value="1">已启用</option>
						<option value="0">已停用</option>
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

