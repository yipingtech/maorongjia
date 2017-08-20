<%@ page contentType="text/html;charset=utf-8"%>

<%@ include file="/jsps/modules/top.jsp"%>
<link href="${ctx}/js/validate/jquery.validate.css" type="text/css"
			rel="stylesheet" />
<script src="${ctx}/js/jquery.js" type="text/javascript"></script>
<script src="${ctx}/js/validate/jquery.validate.js"
			type="text/javascript"></script>
<script src="${ctx}/js/validate/messages_cn.js" type="text/javascript"></script>
<script type="text/javascript">
			/*
			发送验证码按钮点击事件
			*/
			$(document).ready(function(){
				$("#form1").validate({
				 rules : {
				    "member.payPassword": {
					     required: true,
					     minlength: 6
				    },
				    "checkPassword": {
				    	required: true,
					    equalTo: "#pay_password"
				    },
				    "vercode":{
				      	required: true, 
				        remote: "checkCodeAction!checkPhoneCode.action?math="+Math.random()
				     }
				   },
				   messages : {
				      "member.payPassword" : {
					       required :"请输入密码",
					       minlength:"密码长度不能小于6"
				      },
				      "checkPassword" : {
					       required : "请输入确认密码",
					       equalTo : "两次输入密码不一致"
				      },
				      "vercode": {
				      		required: "请输入验证码",
				       		remote: "验证码错误！"
				      }
				   }
			});
			
				$("#sendCheckCode").click(function() {
					//后台发送短信
					var urls = "${ctx}/ajaxepSendMessageAction!sendCheckCode.htm?rand="+Math.random();
					jQuery.ajax({ 
			      		url: urls, 
			      		type:'get',
			      		dataType: 'json',
			      		data:{
							'mobile' : $("#mobile").val()
			      		},
					    success: function(json) {
						    if (json == '提交成功'){//说明发送成功
								ti = setTimeout('changeName()',1000);
							 } else {
								    window.alert(json);
							 }
					    },
					    error: function(XMLHttpRequest, textStatus, errorThrown) {
					       openwaring("网络出现异常");
					    }
					});
					
				});
			});
			
			var count = 60;
		    function changeName(){
					jQuery("#sendCheckCode").html(count +'秒后重新发送');
					count--;
					if (count == 0) {
						clearTimeout(this);
						jQuery("#sendCheckCode").html('点击发送验证码');
						jQuery("#sendCheckCode").css('cursor','pointer');
						jQuery("#sendCheckCode").attr('disabled',false);
						count = 60;
					} else {
						ti = setTimeout('changeName()',1000);
						jQuery("#sendCheckCode").css('cursor','default');
						jQuery("#sendCheckCode").attr('disabled','true');
					}
		        }
		    function submitForm(){
		    	$("#form1").submit();
		    }
</script>
<body class="edit_address">
<header class='clearfix close-bottom'>
    <a href="javascript:history.back();"><img src="${imagePath}/add-left.png" alt="" id="close-bottom-img"></a>
    重置密码
</header>
<section class="e-ad">
    <form id="form1" action="epMemberCenterAction!resetPayPassword.action" method="post" class="revise-form">
        <h3 class="revise-h3"><img class="touxiang2" src="${imagePath}/nav1.png">&nbsp;重置支付密码<img class="revise-img" src="${imagePath}/revise.png"></h3>
        <section class="upgrade-vip" id="revise-form-infor">
            <p>
                <label class="account-infor" for="card-num" id="change-phone-num">支付密码</label>
                <input type="password" id="pay_password" name="member.payPassword" placeholder="新密码">&nbsp;
            </p>
            
            <p>
                <label class="account-infor" for="card-num" id="change-phone-num">确认密码</label>
                <input type="password" id="check_password" name="checkPassword" placeholder="确认密码">&nbsp;
                <input type="hidden" id="mobile" name="member.mobile" value="${member.mobile}">
            </p>

            <p>
                <label class="account-infor" for="input-ver-code" id="change-phone-num">验证码</label>
                <input type="text" name="vercode" id="input-ver-code" placeholder="验证码">&nbsp;
                <a id="sendCheckCode" href="javascript:void(0)">获取验证码</a>
                <i>&nbsp;</i>
            </p>
            <a id="save" class="save" onclick="submitForm()" href="javascript:void(0)">提交密码</a>
            <section class="revise-success" id="revise-success"><i>更新成功</i><img id="revise-img" class="revise-img" src="${imagePath}/address_tp.png"></section>
        </section>
    </form>
</section>
</body>
<script src="${jsPath}/style.js"></script>
</html>