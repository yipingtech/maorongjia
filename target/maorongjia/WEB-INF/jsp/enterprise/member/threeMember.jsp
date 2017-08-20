<%@ page contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>模板</title>
		<%@ include file="/common/taglibs.jsp"%>
		<%@ include file="/common/meta.jsp"%>
		<%@ include file="/common/content.jsp"%>
		
		<script>
			function doYouWantToDel() {
				if(confirm('您真的要删除吗？')) {
					return true;
				}
				return false;
			}
		</script>
	</head>
	<body>
		<div class="rhead">
			<div class="rpos">
				当前位置: 会员管理(vip) - 会员列表
			</div>
			<div class="ropt">
				<a href="memberAction!newPage.action" class="btn">新增</a>
			</div>
			<div class="clear"></div>
		</div>
		<div class="rhead" style="float: right;">
		<form id="formType" name="formType" action="memberAction!queryThreeMember.action" method="post">
		<input type="hidden" name="member.id" value="${member.id}"/>
			<!-- 条件搜索 -->
		<label>客户级别</label>
		<select name="flag">
			<option value="1" <c:if test="${flag eq '1' }">selected="selected"</c:if>>一级</option>
			<option value="2" <c:if test="${flag eq '2' }">selected="selected"</c:if>>二级</option>
			<%-- <option value="3" <c:if test="${flag eq '3' }">selected="selected"</c:if>>三级</option> --%>
		</select>
		<input type="submit" value="搜索"/>
		</form>
		</div>
		
		
		<c:if test="${not empty members}">
			<s:form action="memberAction!queryThreeMember.action"
					namespace="/member" method="post" name="form1" theme="simple" id="form1">
					<input type="hidden" name="flag" value="${flag}"/>
					<input type="hidden" name="member.id" value="${member.id}"/>
				<!-- 分页 -->
				<%@ include file="../../common/pager.jsp"%>
			</s:form>
			<br />
		</c:if>
		
		
		
		<table class="table table-bordered">
			<tr>
				<th>序号</th>
				<th>昵称</th>
				<th>会员编号</th>
				<th>是否分销商</th>
				<th>真实姓名</th>
				<th>性别</th>
				<th>手机号码</th>
<!-- 				<th>会员积分</th> -->
<!-- 				<th>会员余额</th> -->
				<th>会员业绩</th>
				<th>佣金金额</th>
				<th>会员级别</th>
				<!-- <th>团队级别</th> -->
<!-- 				<th>父级</th> -->
				<th>操作</th>
			</tr>

			<c:forEach var="bean" items="${members}"
				varStatus="status">
				<tr>
					<td>${status.count}</td>
					<td>${bean.nickname}</td>
					<td>${bean.id}</td>
					<td>
						<c:if test="${bean.distributor eq '1' }">是</c:if>
						<c:if test="${bean.distributor eq '0' or empty bean.distributor }">否</c:if>
					<td>${bean.realname}</td>
					<td><c:if test="${bean.sex eq '1'}">男</c:if>
					<c:if test="${bean.sex eq '0'}">女</c:if></td>
					<td>${bean.mobile}</td>
					<td><fmt:formatNumber type="number" value="${bean.commissionLine}" maxFractionDigits="2"/></td>
					
					<td><fmt:formatNumber type="number" value="${bean.commission}" maxFractionDigits="2"/></td>
					<%-- 
					 
					<td>
					  <c:if test="${bean.rank eq '1'}">普通会员</c:if>
					  <c:if test="${bean.rank eq '2'}">高级会员</c:if>
					  <c:if test="${bean.rank eq '3'}">VIP会员</c:if>
					  <c:if test="${bean.rank eq '4'}">至尊VIP会员</c:if> 
					</td> --%>
					<td>
					   <c:if test="${flag eq '1'}">一级</c:if>
					   <c:if test="${flag eq '2'}">二级</c:if>
					   <c:if test="${flag eq '3'}">三级</c:if>
					</td>
<%-- 					<td>${bean.memberFather.loginName}</td> --%>
					<td>
						<a class="btn btn-info btn-small" href="memberAction!viewPage.action?id=${bean.id}">查看</a>
						&nbsp; &nbsp;
<%-- 						<a class="btn btn-primary btn-small" href="memberAction!retrieveMemberById.action?id=${bean.id}">编辑</a> --%>
<!-- 						&nbsp; &nbsp; -->
<%-- 						<a class="btn btn-danger btn-small" href="memberAction!delMember.action?id=${bean.id}" --%>
<!-- 								 onclick="return doYouWantToDel();">删除</a> -->
					</td>
				</tr>
			</c:forEach>
		</table>

	</body>
</html>