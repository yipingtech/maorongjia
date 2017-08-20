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
				    "member.realname": {
					     required: true
				    },
				    "member.mobile": {
					     required: true,
					     minlength: 11,
					     maxlength: 11
				    },
				    "vercode":{
				      	required: true, 
				        remote: "checkCodeAction!checkPhoneCode.action?math="+Math.random()
				     }
				   },
				   messages : {
				      "member.realname" : {
					       required :"请输入用户名"
				      },
				      "member.mobile": {
					     required: "请输入手机号码",
					     maxlength: "手机号码格式错误",
					     minlength: "手机号码格式错误"
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
    更新手机
</header>
<section class="e-ad">
    <form id="form1" action="epMemberCenterAction!bindingPhone.action" method="post" class="revise-form">
        <h3 class="revise-h3"><img class="touxiang2" src="${imagePath}/nav1.png">&nbsp;更改手机号<img class="revise-img" src="${imagePath}/revise.png"></h3>
        <section class="upgrade-vip" id="revise-form-infor">
            <p>
                <label class="account-infor" for="card-num" id="change-phone-num">姓名</label>
                <input type="text" id="card-num" name="member.realname" placeholder="请输入名字">&nbsp;
            </p>

            <p>
                <label class="account-infor" for="phone-num" id="change-phone-num">手机号码</label>
                <input type="tel" id="mobile" name="member.mobile" placeholder="请输入手机号码">&nbsp;
                <input type="hidden" name="oldMobile" value="${member.mobile}"/>
            </p>
            <p>
                <label class="account-infor" for="input-ver-code" id="change-phone-num">验证码</label>
                <input type="text" name="vercode" id="input-ver-code" placeholder="验证码">&nbsp;
                <a id="sendCheckCode" href="javascript:void(0)">获取验证码</a>
                <i>&nbsp;</i>
            </p>
            <a id="save" class="save" onclick="submitForm()" href="javascript:void(0)">提交绑定</a>
            <section class="revise-success" id="revise-success"><i>更新成功</i><img id="revise-img" class="revise-img" src="${imagePath}/address_tp.png"></section>
        </section>
    </form>
</section>
</body>
<script src="${jsPath}/style.js"></script>
</html>