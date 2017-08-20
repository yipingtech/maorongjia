<%@ page contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>添加类型</title>
		<%@ include file="/common/taglibs.jsp"%>
		<%@ include file="/common/meta.jsp"%>
		<%@ include file="/common/content.jsp"%>
		<script>
			$(document).ready(function(){
				$("#productTypeForm").validate({
					rules: { 
						"productType.name" : {
							required: true,
							remote: {
							    url: "ajaxFindProductTypeByTypeName!FindProductTypeByTypeName.action",     //后台处理程序
							    type: "post",               //数据发送方式
							    dataType: "json",           //接受数据格式   
							    data: {                     //要传递的数据
							        username: function() {
							            return $("#productTypeExist").val();
							        }
							    }
							}
						}, 
					},
					messages: {
						"productType.name" : {
							remote:"类型重复！",
						},
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
			<div class="rpos">当前位置: 产品类型 - 添加类型</div>
			<div class="ropt"><a class="btn" href="${ctx}/product/productTypeAction!retrieveAllProductTypes.action">返回</a></div>
		</div>

		<form action="productTypeAction!newProductType.action" method="post" id="productTypeForm" name="productTypeForm" enctype="multipart/form-data">
			<table class="table table-bordered inner-table">
				<tr>
					<th>商品类型名称</th>
					<td><input type="text" id="name" name="productType.name" id="productTypeExist"/></td>
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