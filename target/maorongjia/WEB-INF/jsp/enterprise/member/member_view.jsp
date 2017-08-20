<%@ page contentType="text/html;charset=UTF-8"%>

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
			<div class="rpos">当前位置: 会员管理(vip) - 会员详细信息</div>
			<div class="ropt"><a class="btn" href="javascript:history.back();">返回</a></div>
		</div>

		<table class="table table-bordered inner-table">
		        <c:if test="${!empty agent }">
		            <tr>
						<th>代理商</th>
						<td>${member.agent}</td>
				    </tr>
		            <tr>
						<th>成为代理商时间</th>
						<td>${member.agentTime}</td>
				    </tr>
		        </c:if>
				<%-- <tr>
					<th>登陆账号</th>
					<td>${member.loginName}</td>
				</tr> --%>
				<%-- <tr>
					<th>卡号</th>
					<td>${member.cardNum}</td>
				</tr> --%>
				<%-- <tr>
					<th>密码</th>
					<td>${member.password}</td>
				</tr> --%>
				<tr>
					<th>昵称</th>
					<td>${member.nickname}</td>
				</tr>
				<tr>
					<th>上级</th>
					<c:if test="${empty member.memberFather.id}">
					    <td>无</td>
					</c:if>
					<c:if test="${not empty member.memberFather.id}">
					    <td>${member.memberFather.nickname}</td>
					</c:if>
				</tr>
				<tr>
					<th>性别</th>
					<td>
					<c:if test="${member.sex eq '0'}">女</c:if>
					<c:if test="${member.sex eq '1'}">男</c:if>				
					</td>
				</tr>
				<tr>
					<th>真实姓名</th>
					<td>${member.realname}</td>
				</tr>
			<%-- 	<tr>
					<th>生日</th>
					<td>${member.birthday}</td>
				</tr> --%>
				<%-- <tr>
					<th>职业</th>
					<td>${member.career}</td>
				</tr> --%>
			<%-- 	<tr>
					<th>QQ</th>
					<td>${member.qq}</td>
				</tr> --%>
			<%-- 	<tr>
					<th>电子邮箱</th>
					<td>${member.email}</td>
				</tr> --%>
				<tr>
					<th>手机号码</th>
					<td>${member.mobile}</td>
				</tr>
				<%-- <tr>
					<th>固定电话</th>
					<td>${member.telephone}</td>
				</tr> --%>
				<tr>
					<th>联系地址</th>
					<td>${member.address}</td>
				</tr>
			<%-- 	<tr>
					<th>邮编</th>
					<td>${member.postcode}</td>
				</tr> --%>
				<tr>
					<th>新增时间</th>
					<td><fmt:formatDate value="${member.addTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
				</tr>
				<tr>
					<th>编辑时间</th>
					<td><fmt:formatDate value="${member.editTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
				</tr>
				<!-- <tr>
					<th>会员积分</th>
					<td>${member.intergal}  <a href="member/intergralInfoAction!queryAllIntergralByMember.action?member.loginName=${member.loginName}">详情</a></td>
				</tr>
				<tr>
					<th>会员余额</th>
					<td>${member.balance} <a href="member/rechargeInfoAction!queryRechargeInfoByMember.action?member.loginName=${member.loginName}">详情</a></td>
				</tr> 
				<tr>
					<th>会员签到次数</th>
					<td>${member.report} <a href="member/reportInfoAction!retrieveAllReportByMember.action?member.loginName=${member.loginName}">详情</a></td>
				</tr>-->
				<tr>
					<th>会员的佣金金额</th>
					<td><fmt:formatNumber type="number" value="${member.commission}" maxFractionDigits="2"/>
					<a href="commissionInfoAction!queryCommissionByMember.action?member.loginName=${member.loginName}">详情</a></td>
				</tr>
				<%-- <tr>
					<th>会员级别</th>
					<td>
					  <c:if test="${member.rank eq '1'}">普通会员</c:if>
					  <c:if test="${member.rank eq '2'}">高级会员</c:if>
					  <c:if test="${member.rank eq '3'}">VIP会员</c:if>
					  <c:if test="${member.rank eq '4'}">至尊VIP会员</c:if> 
					</td>
				</tr> --%>
				<tr>
					<th>会员头像</th>
					<td ><img style="width:100px;" src="${member.imagePath}" alt="" /></td>
				</tr>

		</table>
	</body>
</html>