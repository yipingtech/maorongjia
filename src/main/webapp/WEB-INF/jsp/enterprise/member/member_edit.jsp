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
				$("#memberForm").validate({
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
			<div class="rpos">当前位置: 会员管理(VIP) - 会员列表 - 会员编辑</div>
			<div class="ropt"><a class="btn" href="javascript:history.back();">返回</a></div>
		</div>

		<form action="memberAction!editMember.action" method="post" id="memberForm" name="memberForm" enctype="multipart/form-data">
			<table class="table table-bordered inner-table">
				<input type="hidden" name="id" value="${member.id}"/>
				<tr>
					<th>登陆账号</th>
					<td><input type="text" id="loginName" name="member.loginName" value="${member.loginName}" disabled="disabled"/></td>
				</tr>
				<%-- <tr>
					<th>卡号</th>
					<td><input type="text" id="cardNum" name="member.cardNum" value="${member.cardNum}" /></td>
				</tr> --%>
				<tr>
					<th>昵称</th>
					<td><input type="text" id="nickname" name="member.nickname" value="${member.nickname}" /></td>
				</tr>
				<tr>
					<th>性别</th>
					<td><input type="text" id="sex" name="member.sex" value="${member.sex}" /><a>性别:（男1，女0）</a></td>
				</tr>
				<tr>
					<th>真实姓名</th>
					<td><input type="text" id="realname" name="member.realname" value="${member.realname}" /></td>
				</tr>
				<%-- <tr>
					<th>生日</th>
					<td><input type="text" id="birthday" name="member.birthday" value="${member.birthday}" /></td>
				</tr>
				<tr>
					<th>职业</th>
					<td><input type="text" id="career" name="member.career" value="${member.career}" /></td>
				</tr>
				<tr>
					<th>QQ</th>
					<td><input type="text" id="qq" name="member.qq" value="${member.qq}" /></td>
				</tr>
				<tr>
					<th>电子邮箱</th>
					<td><input type="text" id="email" name="member.email" value="${member.email}" /></td>
				</tr> --%>
				<tr>
					<th>手机号码</th>
					<td><input type="text" id="mobile" name="member.mobile" value="${member.mobile}" /></td>
				</tr>
			<%-- 	<tr>
					<th>固定电话</th>
					<td><input type="text" id="telephone" name="member.telephone" value="${member.telephone}" /></td>
				</tr> --%>
				<tr>
					<th>联系地址</th>
					<td><input type="text" id="address" name="member.address" value="${member.address}" /></td>
				</tr>
				<%-- <tr>
					<th>邮编</th>
					<td><input type="text" id="postcode" name="member.postcode" value="${member.postcode}" /></td>
				</tr> --%>
				<tr>
					<th>新增时间</th>
					<td><input type="text" id="addTime" name="member.addTime" value="<fmt:formatDate value="${member.addTime}" pattern="yyyy-MM-dd HH:mm:ss" />" disabled="disabled"/></td>
				</tr>
				<tr>
					<th>编辑时间</th>
					<td><input type="text" id="editTime" name="member.editTime" value="<fmt:formatDate value="${member.editTime}" pattern="yyyy-MM-dd HH:mm:ss" />" disabled="disabled"/></td>
				</tr>
				<!-- <tr>
					<th>会员积分</th>
					<td><input type="text" id="intergal" name="member.intergal" value="${member.intergal}" /></td>
				</tr>
				<tr>
					<th>会员余额</th>
					<td><input type="text" id="balance" name="member.balance" value="${member.balance}" /></td>
				</tr> 
				<tr>
					<th>会员签到次数</th>
					<td><input type="text" id="report" name="member.report" value="${member.report}" /></td>
				</tr>-->
				<tr>
					<th>会员的佣金金额</th>
					<td><input type="text" id="commission" name="member.commission" value="${member.commission}" /></td>
				</tr>
				<!-- <tr>
					<th>会员级别</th>
					<td><input type="text" id="rank" name="member.rank" value="${member.rank}" /></td>
				</tr> -->

				<tr>
					<td colspan="2" style="text-align:center;">
						<input type="submit" class="btn" value="提 交"/>
					</td>
				</tr>
		 	</table>
		</form>
	</body>
</html>