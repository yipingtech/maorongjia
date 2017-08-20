<%@ page contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>属性编辑</title>
		<%@ include file="/common/taglibs.jsp"%>
		<%@ include file="/common/meta.jsp"%>
		<%@ include file="/common/content.jsp"%>
		<script>
			$(document).ready(function(){
				$("#attributeForm").validate({
					rules: { 
						"attribute.attrName" : {
							required: true
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
			<div class="rpos">当前位置: 产品属性 - 编辑</div>
			<div class="ropt"><a class="btn" href="${ctx}/product/attributeAction!retrieveAllAttributes.action">返回</a></div>
		</div>

		<form action="attributeAction!editAttribute.action" method="post" id="attributeForm" name="attributeForm" enctype="multipart/form-data">
		    <input type="hidden" name="id" value="${attribute.id }"/>
			<table class="table table-bordered inner-table">
				<tr>
					<th>属性名称</th>
					<td><input type="text" id="attrName" value="${attribute.attrName}" name="attribute.attrName" /></td>
				</tr>
				<tr style="display: none">
					<th>所属产品类型</th>
					<td>
						<select name="typeId" id="productType">
							<c:forEach var="bean" items="${productTypes}">
								<option value="${bean.id}" <c:if test="${bean.id eq typeId}">selected</c:if>>${bean.name}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<th>排序</th>
					<td>
						<input type="text" name="attribute.sortOrder"  value="${attribute.sortOrder }" />
					    属性展示排序，从大到小，从前到后
					</td>
				</tr>
				
				<tr style="display: none">
					<th>属性是否可选</th>
					<td>
						<input type="radio" class="attrType" onclick="clickAttrType()" name="attribute.attrType"  value="0" <c:if test="${attribute.attrType eq '0'}">checked</c:if> />唯一属性&nbsp; &nbsp;&nbsp; &nbsp;
					    <input type="radio" calss="attrType" onclick="clickAttrType()" name="attribute.attrType" value="1" <c:if test="${attribute.attrType eq '1'}">checked</c:if> />单选属性</br>
					         选择"单选"时，可以对商品该属性设置多个值，同时还能对不同属性值指定不同的价格加价，用户购买商品时需要选定具体的属性值。
					         选择"唯一属性"时，商品的该属性值只能设置一个值，用户只能查看该值。
					</td>
				</tr>
				
				<tr style="display: none">
					<th>录入方式</th>
					<td>
					<input type="radio" id="shougong" onchange="inputWay(this)" name="attribute.attrInputType" value="0" <c:if test="${attribute.attrInputType eq '0'}">checked</c:if> />手工录入&nbsp; &nbsp;&nbsp; &nbsp;
					<input type="radio" id="xiala" onchange="inputWay(this)" name="attribute.attrInputType" value="1" <c:if test="${attribute.attrInputType eq '1'}">checked</c:if> />从下面的列表中选择（一行代表一个可选值）&nbsp; &nbsp;&nbsp; &nbsp;
					</td>
				</tr>
				<tr>
					<th>属性值（提供选择属性）</th>
					<td>
					   <textarea rows="10" cols="20" id="attrValue" name="attribute.attrValue"  style="background-color:rgb(240, 240, 240)" required="true">
					   ${attribute.attrValue }
					   </textarea>
					   <span style="color: red;">注意：请用英文逗号隔开</span>
					</td>
				</tr>
				<tr style="display: none">
					<th>状态</th>
					<td>
					   <select id="status" name="attribute.status">
					      <option value="1">启用</option>
					      <option value="0">停用</option>
					   </select>
					 </td>  
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