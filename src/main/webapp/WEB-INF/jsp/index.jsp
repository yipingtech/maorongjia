<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
		
<!DOCTYPE html>
<html lang="zh">
<head>
<%@ include file="/common/meta.jsp"%>
<title>MCCMS</title>
<script src="${ctx}/resource/validate/jquery.validate.min.js" type="text/javascript"></script>
<script src="${ctx}/resource/validate/messages_cn.js" type="text/javascript"></script>
<script src="${ctx}/resource/js/common.js" type="text/javascript"></script>
<link href="${ctx}/resource/image/indexstyle.css" rel="stylesheet" type="text/css" />
<!-- 这里引入网站图标 -->
<link rel="shortcut icon" href="${ctx}/userfiles/image/favicon.ico" />
<link rel="bookmark"href="${ctx}/userfiles/image/favicon.ico" />
<script type="text/javascript">
$(document).ready(function(){
	if(self != top){
		top.location='${ctx}/j_spring_security_logout';
	}
		
	$("#loginForm").validate({
		 groups: {
		    login:"mc_username mc_password"
		 },
		 rules: { 
			 mc_username:
			{
				required: true,
				minlength: 4,
				maxlength: 32
			},
			mc_password:
			{
				required: true,
				minlength: 4,
				maxlength: 64
			}
		 },
		 messages: {
			 mc_username:{
		 		minlength: '用户名长度不能小于4位',
		 		maxlength: '用户名长度不能大于32位'
		 	},
		 	mc_password:{
		 		minlength: '密码长度不能小于4位',
		 		maxlength: '密码长度不能大于64位'
		 	}
		 },
		 success: function(label) {
			$("#loginError").hide();
	        },
	        showErrors:function(errorMap,errorList) {
	        	$("#loginError").html("");
			this.defaultShowErrors();
		 },
		 errorContainer: "#loginError",
		 errorLabelContainer: "#loginError"
	});
});

function setWH(){
	var size = getWindowSize();
	var windowHeight = size.y;
	$(".container").height(windowHeight - $("#header").height() - $("#footer").height());
	var innerTop = ($(".container").height() - $(".inner").height() - 20) / 2;
	$(".inner").css("padding-top",innerTop + "px");
}
</script>
</head>

<body>
<div id="header">
	<div>
		<img src="${ctx}/resource/image/logo.png">
	</div>
</div>
<div class="container">
	<div class="inner clearfix">
		<div class="computer"></div>
		<form id="loginForm" name="loginForm" action="${ctx}/j_spring_security_check" method="post">
			<div id="loginError"<c:if test="${empty sessionScope['SPRING_SECURITY_LAST_EXCEPTION']}"> style="display:none"</c:if>>${sessionScope['SPRING_SECURITY_LAST_EXCEPTION'].message}</div>
	    	<div class="name">
	        	<img src="${ctx}/resource/image/man.png" />
	            <input type="text" value="请输入账号" name="j_username" id="mc_username" maxlength="20" onFocus="if(value==defaultValue){this.value='';}" onBlur="if(!value){value=defaultValue;}" />
	        </div>
	        <div class="psw">
	        	<img src="${ctx}/resource/image/lock.png" />
                <input type="text" value="请输入密码" id="pass_text" maxlength="20" onFocus="$(this).hide();$('#mc_password').show();$('#mc_password').focus();" />
	            <input type="password" name="j_password" id="mc_password" style="display:none;" maxlength="20" onBlur="if(!value){$(this).hide();$('#pass_text').show();}" />
	        </div>
	        <input type="submit" class="submit" value="登 录" />
	    </form>
    </div>
</div>
<div id="footer">
	<p>版权所有 © 2014 广州讯猫软件有限公司   粤ICP备05014984号</p>
</div>

</body>
</html>