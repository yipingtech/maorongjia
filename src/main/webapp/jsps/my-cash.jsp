<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/jsps/modules/top.jsp"%>
<body>
<header class="header_top clearfix">
    <a href="epMemberCenterAction.action"><img src="${imagePath}/left_arrow.png"></a>
    <p>提款申请</p>
</header>
<script type="text/javascript">
var ok=true;
	function checkForm(){
		
		   var wechat=$("#infor-name").val();
		   var amount=$("#cash-count").val();
		   var phone=$("#infor-tel").val();
		   var flag=$("#flag").val();
		   
		   //获取可提现金额
		   var allowAmount = parseInt($("#allowAmount").val());
		   //判断是否为空
		   if(flag==null || flag==""){
			   layerOpen("请先绑定银行卡", "javascript:void(0)");
			   return;
		   }
		    if(amount!=""){
			   	 //判断金额填写是否为数字
			     if (!isNaN(amount)){
					if(($(".commsissionAmount").val()-amount)<0){
						  $(".cashTrips").show();
					      $(".cashTrips").html("佣金不足");
					      $(".cashTrips").fadeOut(3000);
		            }else if(amount==0){
		            	  $(".cashTrips").show();
			  		      $(".cashTrips").html("提现金额不能为零");
			  		      $(".cashTrips").fadeOut(3000);
		            }else if(amount>allowAmount){
		            	  $(".cashTrips").show();
		  		          $(".cashTrips").html("输入的金额不能大于可提现金额");
		  		          $(".cashTrips").fadeOut(3000);
		            }else if(amount!=parseInt(amount)||amount<0.0){
		            	  $(".cashTrips").show();
		  		          $(".cashTrips").html("请输入整数的提现金额");
		  		          $(".cashTrips").fadeOut(3000);
		            }else{
		            	if(phone != ""){
		    				if(!(/^1(3|4|5|7|8)\d{9}$/.test(phone))){ 
		    					   $(".cashTrips").show();
		    					   $(".cashTrips").html("请输入有效的手机号码");
		    					   $(".cashTrips").fadeOut(3000);
		    				 }else if(ok){
		    					 ok=false;
		    					 $(".card-list form").submit();
		    				 }
		    			}else{
		    			      $(".cashTrips").show();
		    			      $(".cashTrips").html("请输入手机号码");
		    			      $(".cashTrips").fadeOut(3000);
		    			}
		            }
				 }else{
				  $(".cashTrips").show();
			      $(".cashTrips").html("金额不是数字");
			      $(".cashTrips").fadeOut(3000);
				 }
		   }else{
		      $(".cashTrips").show();
		      $(".cashTrips").html("金额不能为空");
		      $(".cashTrips").fadeOut(3000);
		   } 

	}
</script>
<!--container-->
<section id="container">
    <div class="card-list">
		        <form action="epMemberCenterAction!newDrawCommissionSuccess.action" method="post">
		             <input type="hidden" name="bankCard.id" value="${bankCard.id }">
		             <input type="hidden" class="commsissionAmount" value="${bonus}">
		             <input type="hidden" name="bankCard.bankCardNum" value="${bankCard.bankCardNum }">
		             <input type="hidden" name="bankCard.bankCardFlag" value="${bankCard.bankCardFlag }">
		             <section class="cashTrips" style="position: fixed;top: 10.3%;background: #e7e7e7;color:red;text-align: center;width: 100%;display: none;z-index: 9999"></section>
			         <div class="card-info clearfix card_new_info">
			              <c:if test="${empty flag}">
				              <div class="bank_info left">
				                  <p>
				                  		绑定的银行卡号：${bankCard.bankCardNum }<%-- <c:out value="${fn:substring(bankCard.bankCardNum,fn:length(bankCard.bankCardNum)-4, fn:length(bankCard.bankCardNum))}" /> --%>
				                  </p>
				                  <p>
				                  		开户银行名称：${bankCard.bankCardFlag }
				                  </p>
				                  <input type="hidden" id="flag" value="${bankCard.bankCardFlag }" />
				              </div>
				              <a href="epMemberCenterAction!findBankCardByMember.action?flag=1" class="change_card right">
				              		更换
				              		<img src="${imagePath}/arrow-gright.png">
				              </a>
			              </c:if>
			              <c:if test="${flag eq '0' }">
			                  <p>
			                  	<a href="epMemberCenterAction!findBankCardByMember.action?flag=1">
			                  		没有任何绑定的银行卡,请先点此去绑定
			                  	</a>
			                  </p>
			                  <input type="hidden" id="flag" value="" />
			              </c:if>
			        </div>
			        <section class="cashTrips2" style="position: fixed;top: 15.3%;background: #e7e7e7;color:red;text-align: center;width: 100%;display: none;z-index: 9999"></section>
			        <div class="card-info clearfix card_new_info">
			            <strong class="left">我的私房钱</strong><input id="infor-name" value="<fmt:formatNumber value="${bonus }" pattern="##.##"  ></fmt:formatNumber>" class="left" type="text"  disabled="true">
			        </div> 
			        <div class="card-info clearfix card_new_info">
			            <strong class="left">可提款金额</strong>
			            <input  id ="allowAmount" class="left" type="text" value="<fmt:formatNumber value="${moneyToSend }" pattern="##.##"  ></fmt:formatNumber>" disabled="true">
			        </div>
			        <div class="card-info clearfix card_new_info">
			            <strong class="left">提款金额</strong>
			            <input id="cash-count" class="left" type="text" name="cashInfo.drawAmount" placeholder="请输入提款金额">
			        </div>
			        <div class="card-info clearfix card_new_info">
			          	<strong class="left">手机号码</strong>
				        <input id="infor-tel" class="left" type="text" name="cashInfo.phoneNum" placeholder="请填手机号" >
			        </div>
			         
				    <div class="card-list-btn">
				        <a  onclick="checkForm();" class="next_btn" id="next_btn">下一步</a>
				    </div>
		    </form>
	</div>
    <div class="all_tips" style="height:100%;margin-bottom:10%;">
        <p class="tips_text">温馨提示：</p>
	    <p class="tips_text">1.【我的私房钱】是佣金总额，数额包括了已计入佣金但未达到提现标准的金额。</p>
	    <p class="tips_text">2.【可提款金额】是已达到提现标准的金额。“提现标准”是下级会员购买并收到产品后，在百丽春平台【个人中心】--【我的订单】里签收该产品7天（退换期）后，对应的佣金即可提现。</p>
	    <p class="tips_text">3.【提款金额】是不超过“可提款金额”，想要提现的总额。</p>
	    <p class="tips_text">4.【申请提现】申请提现后，进行资格审核。同意提现后，7个工作日内，转账至会员指定账户。</p>
	    <p class="tips_text">5.【同意提现】周一至周五9:00-17:00是为会员同意提现的工作时间，超出工作时间申请提现的，会在下一个工作日为您办理同意提现业务。</p>
    </div>

</section>
<!--end of container-->
