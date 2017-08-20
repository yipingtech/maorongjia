<%@ page contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>更新模板</title>
		<%@ include file="/common/taglibs.jsp"%>
		<%@ include file="/common/meta.jsp"%>
		<%@ include file="/common/content.jsp"%>
		<script>
			$(document).ready(function(){
				$("#integralRuleForm").validate({
					rules: { 
			
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
			<div class="rpos">当前位置: 会员管理（VIP） - 积分规则 - 编辑</div>
			<div class="ropt"><a class="btn" href="${ctx}/integralrule/integralRuleAction!retrieveAllIntegralRules.action">返回</a></div>
		</div>

		<form action="integralRuleAction!editIntegralRule.action" method="post" id="integralRuleForm" name="integralRuleForm" enctype="multipart/form-data">
			<table class="table table-bordered inner-table">
				<input type="hidden" name="id" value="${integralRule.id}"/>
				<tr>
					<th>动作名称</th>
					<td><input type="text" id="name" name="integralRule.name" value="${integralRule.name}" /></td>
				</tr>
				<tr>
					<th>周期（以天为单位）</th>
					<td><input type="text" id="period" name="integralRule.period" value="${integralRule.period}" /></td>
				</tr>
				<tr>
					<th>周期内最多次数</th>
					<td><input type="text" id="maxtmie" name="integralRule.maxtmie" value="${integralRule.maxtmie}" /></td>
				</tr>
				<tr>
					<th>获取积分</th>
					<td><input type="text" id="integral" name="integralRule.integral" value="${integralRule.integral}" /></td>
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