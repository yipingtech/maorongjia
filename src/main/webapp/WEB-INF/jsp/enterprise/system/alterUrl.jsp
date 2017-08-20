<%--

	pageType_edit.jsp

	Create by MCGT

	Create time 2013-08-20

	Version 1.0

	Copyright 2013 Messcat, Inc. All rights reserved.

 --%>
<%@ page contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>更新模板</title>
		<%@ include file="/common/taglibs.jsp"%>
		<%@ include file="/common/meta.jsp"%>
		<%@ include file="/common/content.jsp"%>
		<link type="text/css" href="${ctx}/resource/AsyncBox/skins/ZCMS/asyncbox.css" rel="stylesheet" />
		<script type="text/javascript" src="${ctx}/resource/AsyncBox/AsyncBox.v1.4.js"></script>
		<script>
			$(document).ready(function(){
				$("#alterUrlForm").validate({
					rules: {
						"alterUrlBean.oldUrl": { 
		        			required: true
		    			},
		    			"alterUrlBean.newUrl": { 
		        			required: true
		    			}
					},
					success: function(label) {
						label.html("&nbsp;").addClass("checked");
			        }	
				});
			});
			
			function searchRecord(){
				if($("#oldUrl").val() == '' || $("#newUrl").val() == ''){
					$("#alterUrlForm").submit();
				}else{
					$.ajax({ 
			      		url:'${ctx}/searchRecordAction!retrieveByCondition.htm', 
			      		type:'post',
			      		dataType: 'json', 
			      		data:{
			      			"alterUrlBean.tableName": $("#tableName").val(),
			      			"alterUrlBean.oldUrl": $("#oldUrl").val()
			      		},
					    success: function(json) {
					    	if(json.count == '0'){
					    		asyncbox.tips('共找到0条匹配的记录', 'error');
					    	}else{
					    		asyncbox.confirm('共找到' + json.count + '条匹配的记录，是否执行更新？', '确认更新', sureUpdate);
					    	}
					    },
					    error: function(XMLHttpRequest, textStatus, errorThrown) {
					        asyncbox.tips('网络出现异常', 'error');
					    }
				    });
				}
			}
			
			function sureUpdate(action){
				if(action == 'ok'){
					$("#alterUrlForm").submit();
				}
			}
		</script>
	</head>
	<body style="padding:8px;">
		<c:if test="${not empty message}">
			<script>
				asyncbox.tips('${message}', 'success');
			</script>
		</c:if>	
		<div class="rhead">
			<div class="rpos">
				当前位置: 系统配置 - 批量修改URL
			</div>
		</div>

		<form action="alterUrlAction!alterUrl.action" method="post" id="alterUrlForm" name="alterUrlForm" enctype="multipart/form-data">
			<table class="table table-bordered inner-table">
				<tr>
					<th>表名</th>
					<td>
						<select id="tableName" name="alterUrlBean.tableName">
							<option value="enterprise_column">
								栏目表(enterprise_column)
							</option>
							<option value="enterprise_news"
								<c:if test="${alterUrlBean.tableName=='enterprise_news'}">selected</c:if>>
								新闻表(enterprise_news)
							</option>
						</select>
					</td>
				</tr>
				<tr>
					<th>旧的URL</th>
					<td><input type="text" id="oldUrl" name="alterUrlBean.oldUrl" size="100" value="${alterUrlBean.oldUrl}" /></td>
				</tr>
				<tr>
					<th>新的URL</th>
					<td><input type="text" id="newUrl" name="alterUrlBean.newUrl" size="100" value="${alterUrlBean.newUrl}" /></td>
				</tr>

				<tr>
					<td colspan="2" style="text-align:center;">
						<input type="button" class="btn" value="替换 " onclick="searchRecord();"/>
					</td>
				</tr>
		 	</table>
		</form>
	</body>
</html>