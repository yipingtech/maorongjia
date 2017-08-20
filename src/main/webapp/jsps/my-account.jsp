<%@ page contentType="text/html;charset=utf-8"%>

<%@ include file="/jsps/modules/top.jsp"%>

<script type="text/javascript">
	function resetPsw(){
		var mobile = $("#mobile").val();
		if(mobile==""){
			window.location.href = "epMemberCenterAction!EditMemberPhonePage.action"; 
		}else{
			window.location.href = "epMemberCenterAction!resetPasswordPage.action";
		}
	}
</script>
	<script type="text/javascript">
	$(function(){
		$('#switch').click(function(){
			var flag = $('#messageFalg').val();
			$.ajax({ 
				  url:'${ctx}/ajaxCheckAgent!message.action', 
		          type:'post', 
		          dataType: 'json',
		          data:{
		        	    "flag":flag,
		               }, 
			      success: function(data) {
			    	  $('#switch').empty();
			    	  if(data.flag=='0'){
			    		  $('#switch').html('<img src="${imagePath}/off.png">');
			    	  }else if(data.flag=='1'){
			    		  $('#switch').html('<img src="${imagePath}/on.png">');
			    	  }
			    	  $('#messageFalg').val(data.flag);
			      },
			      error: function(XMLHttpRequest, textStatus, errorThrown) {
			           openwaring("网络出现异常");
			        }
			});
		});
	})
	
</script>
<body class="edit_address">
<header class='clearfix close-bottom'>
		<a href="epMemberCenterAction.action"><img src="${imagePath}/add-left.png" alt="" id="close-bottom-img"></a>
		我的账号
</header>
<section class="e-ad">
	<form action="">
        <h3 class="per-data"><img src="${imagePath}/nav1.png">&nbsp;&nbsp;个人资料<a href="epMemberCenterAction!newEditMemberPage.action"><img src="${imagePath}/icon_pencil.png"></a></h3>
        <section class="upgrade-vip">
			<%-- <p><label class="account-infor" for="card-num">卡号：</label><input type="text" id="card-num" value="${member.cardNum}" disabled="disabled"> --%>
			<%-- <i>
			<c:if test="${member.rank eq '1'}">普通会员</c:if>
			<c:if test="${member.rank eq '2'}">高级会员</c:if>
			<c:if test="${member.rank eq '3'}">VIP会员</c:if>
			<c:if test="${member.rank eq '4'}">至尊VIP会员</c:if>
			</i>
			</p> --%>
        	<p>姓名：${member.realname}</p>
        	<p>手机号码：${member.mobile}</p>
          <!--   <p>地址：${member.address} -->
        </section>  
	</form>
    <section class="my-account">
<%--        <a href="javascript:void(0);" onclick="resetPsw();">重置支付密码<i>></i></a>--%>
        <!-- <a href="epMemberCenterAction!queryAllIntergralByUser.action">积分明细<i>></i></a>
        <a href="epMemberCenterAction!queryAllRechargeByUser.action">充值消费明细<i>></i></a>-->
        <a href="epMemberCenterAction!findBankCardByMember.action?flag=0">银行卡绑定<i>></i></a>
        <a href="javascript:void(0);">新会员加入消息开关(关/开)
                <i id="switch" style="width:14%;">
                     <c:if test="${member.messageFalg eq '0'}"><img src="${imagePath}/off.png"></c:if>
                     <c:if test="${member.messageFalg eq '1'}"><img src="${imagePath}/on.png"></c:if>
                </i>
        </a>
        <input type="hidden" value="${member.messageFalg}" id="messageFalg">
       <!--  <a href="#">门店介绍<i>></i></a>
        <a href="epMemberCenterAction!newVipPaticularIntro.action">会员特权介绍<i>></i></a>
        <a href="epMemberCenterAction!commonProblem.action">常见问题<i>></i></a>  -->
    </section>
</section>
<script src="${jsPath}/style.js"></script>
</body>
</html>