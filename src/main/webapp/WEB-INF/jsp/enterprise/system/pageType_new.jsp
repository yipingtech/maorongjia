<%--

	pageType_new.jsp

	Create by MCGT

	Create time 2013-08-20

	Version 1.0

	Copyright 2013 Messcat, Inc. All rights reserved.

 --%>
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
				$("#pageTypeForm").validate({
					rules: { 
						"pageType.name": { 
		        			required: true
		    			},
						"pageType.templateUrl": { 
		        			required: function(){
					 			if($("#templateType").val() == 'link'){
					 				return false;
					 			}
				 			}
		    			},
						"pageType.featuresUrl": { 
		        			required: function(){
					 			if($("#templateType").val() != 'other'){
					 				return false;
					 			}
				 			}
		    			}
					},
					success: function(label) {
						label.html("&nbsp;").addClass("checked");
			        }	
				});
				
			});
			
			function isShowFeatures(obj){
				if($(obj).val() == 'link'){
					$("#template").hide();
					$("#templateUrl").val('');
				}else{
					$("#template").show();
				}
				if($(obj).val() == 'other'){
					$("#features").show();
				}else{
					$("#features").hide();
					$("#featuresUrl").val('');
				}
			}
		</script>
	</head>
	<body>
		<div class="rhead">
			<div class="rpos">
				当前位置: 系统配置 - 页面类型管理 - 新增页面类型信息
			</div>
			<div class="ropt"><a class="btn" href="${ctx}/pageTypeAction!retrieveAllPageTypes.action">返回</a></div>
		</div>

		<form action="pageTypeAction!newPageType.action" method="post" id="pageTypeForm" name="pageTypeForm" enctype="multipart/form-data">
			<table class="table table-bordered inner-table">
				<tr>
					<th>类型名称</th>
					<td><input type="text" id="name" name="pageType.name" size="100" /></td>
				</tr>
				<tr>
					<th>模板类型</th>
					<td>
						<select id="templateType" name="pageType.templateType" onchange="isShowFeatures(this);">
							<option value="mostlist">
								模块列表模型(mostlist)
							</option>
							<option value="list">
								列表模型(list)
							</option>
							<option value="content">
								单页模型(content)
							</option>
							<c:if test="${isShowLink}">
								<option value="link">
									单链接模型(link)
								</option>
							</c:if>
							<option value="product">
								产品模型(product)
							</option>
							<option value="other">
								定制模型(other)
							</option>
						</select>
					</td>
				</tr>
				<tr id="template">
					<th>URL生成模板</th>
					<td><input type="text" id="templateUrl" name="pageType.templateUrl" size="100" /></td>
				</tr>
				<tr style="display:none;" id="features">
					<th>新功能URL</th>
					<td><input type="text" id="featuresUrl" name="pageType.featuresUrl" size="100" /></td>
				</tr>
				<tr>
					<th>简介</th>
					<td><textarea rows="10" cols="87" name="pageType.intro"></textarea></td>
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