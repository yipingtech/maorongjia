<%@ page contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>属性值</title>
		<%@ include file="/common/taglibs.jsp"%>
		<%@ include file="/common/meta.jsp"%>
		<%@ include file="/common/content.jsp"%>

		<script>
			var selectValue;
			var count;
			function copyValue(obj){
				//console.log($(obj).val());
				selectValue = $(obj).val().trim();
			}
			
			function choseValues(obj){
				$(':checked').each(function (index,value){
					if(index==0){
						selectValue = value.value.trim();
					}else{
						selectValue = selectValue+","+value.value.trim();
					}
					count = index+1;
				});
			}
		</script>
	</head>
	<body>
		<div class="rhead">
			<div class="rpos">
				当前位置: 属性 - ${attribute.attrName}值
			</div>
			<div class="ropt">
				<a href="attributeAction!editAttribute.action?id=${id}&&typeId=${typeId}" class="btn">新增</a>
			</div>
			<div class="clear"></div>
		</div>
		
		<table class="table table-bordered">
			<tr>
				<th></th>
				<th>序号</th>
				<th>${attribute.attrName}</th>
				<th>所属类型</th>
			</tr>
			<c:forEach var="attrValue" items="${attrValueList}"
				varStatus="status">
				<tr>
					<td>
					<c:if test="${choseType eq 'radio'}">
						<input type="radio" name="id" value="${attrValue}" onclick="copyValue(this);" />
					</c:if>
					<c:if test="${choseType eq 'checkBox'}">
						<input type="checkbox" name="id" value="${attrValue}" onclick="choseValues(this);" />
					</c:if>
					</td>
					<td>${status.count}</td>
					<td>${attrValue}</td>
					<td>${attribute.productType.name}</td>
					
				</tr>
			</c:forEach>

		</table>
		
	</body>
</html>