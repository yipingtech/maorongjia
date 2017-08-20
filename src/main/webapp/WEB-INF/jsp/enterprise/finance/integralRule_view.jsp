﻿<%@ page contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>查看模板</title>
		<%@ include file="/common/taglibs.jsp"%>
		<%@ include file="/common/meta.jsp"%>
		<%@ include file="/common/content.jsp"%>
	</head>
	<body>
		<div class="rhead">
			<div class="rpos">当前位置: 会员管理（VIP） - 积分规则 - 查看</div>
			<div class="ropt"><a class="btn" href="${ctx}/integralrule/integralRuleAction!retrieveAllIntegralRules.action">返回</a></div>
		</div>

		<table class="table table-bordered inner-table">
				<tr>
					<th>动作名称</th>
					<td>${integralRule.name}</td>
				</tr>
				<tr>
					<th>周期（以天为单位）</th>
					<td>${integralRule.period}</td>
				</tr>
				<tr>
					<th>周期内最多次数</th>
					<td>${integralRule.maxtmie}</td>
				</tr>
				<tr>
					<th>获取积分</th>
					<td>${integralRule.integral}</td>
				</tr>

		</table>
	</body>
</html>