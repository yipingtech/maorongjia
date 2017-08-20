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
			if(${showMesscat}){
	        	$.blockUI({ 
	        		message: $('#message')
	        		}) 
		        setTimeout($.unblockUI, 2000); 
		    }
		</script>
	</head>
	<body>

		<div class="rhead">
			<div class="rpos">
				当前位置: 优化推广 - 静态网页设置
			</div>
			<div class="clear"></div>
	  	</div>

		<form action="enterpriseHtmlAction!setHtmlByName.htm">
		<table class="table table-bordered inner-table">
			<tr>
				<th>
					静态页面开启
				</th>
				<td>
					<input name="htmlState" type="radio" value="1"
						<c:if test="${htmlState=='1'}">checked</c:if>></input>
					已启用
					<input name="htmlState" type="radio" value="0"
						<c:if test="${htmlState=='0'}">checked</c:if>></input>
					已停用
				</td>
			</tr>
			<tr>
				<td colspan="2" style="text-align:center;">
					<input type="submit" class="btn" value="提 交 "/>
				</td>
			</tr>
		</table>
		</form>
		<div id="message" style="display:none">
			<s:actionmessage />
		</div>
	</body>
</html>

