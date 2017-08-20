<%@ page contentType="text/html;charset=utf-8"%>

<%@ include file="/jsps/modules/top.jsp"%>

<body class='edit_address bg-dcdcdc'>
<header class='clearfix close-bottom'>
		<a href="javascript:history.back();"><img src="${imagePath}/left_arrow.png" alt="" id="close-bottom-img"></a>
		提现申请
</header>
<script type="text/javascript">
   $(function(){
	   $("#next-step").click(function(){
	    	$("#next-step").attr("disabled","true");
	    	$("#next-step").css("background","#787878");
	    	$("#next-step").val("提交中....");
	    	$("#applyCash").submit();
	    });
	   
   })
    
</script>
<section class="e-ad">
    <section class="fenxiao-content">
        <section class="f-c" id="blo">
        	<form method="post" action="epMemberCenterAction!newDrawCommissionSuccess.action" id="applyCash">
            	<section class="get-cash">
            		<p>您本次提款业务明细如下：</p>
                    <p>提款微信账号：${member.nickname}</p>
                    <p>佣金余额：<span class="color-f84076"><fmt:formatNumber value="${member.commission}" pattern="##.##" minFractionDigits="2" ></fmt:formatNumber></span>元</p>
                    <p>银行卡开户银行：${bankCard.bankCardFlag }</p>
                    <p>银行卡账号：${bankCard.bankCardNum }</p>
            	</section>
                <section class="get-cash-infor border-bottom2">
                	<h3>提款信息</h3>
                	<p class="real-infor"><i>微信账号：</i><i>${cashInfo.wechatNum}</i></p>
                    <p class="real-infor"><i>手机号码：</i><i>${cashInfo.phoneNum}</i></p>
                    <p class="real-infor"><i>提款金额：</i><i><span class="color-f84076">${cashInfo.drawAmount}元</span></i></p>
                    <p class="real-infor"><i>手续费：</i><i><span class="color-f84076">0.00</span>（实际到账${cashInfo.drawAmount}元）</i></p>
                    <p class="real-infor"><i>佣金余额：</i><i><span class="color-f84076"><fmt:formatNumber value="${member.commission-cashInfo.drawAmount}" pattern="##.##" minFractionDigits="2" ></fmt:formatNumber></span>（提款后）</i></p>
                    <p class="real-infor"><i>预期到账时间：</i><i>${predictTime}前</i></p>
                    <c:set var="predictTime" value="${predictTime}" scope="session"></c:set>
                    <input type="hidden" name="cashInfo.wechatNum" value="${cashInfo.wechatNum}">
                    <input type="hidden" name="cashInfo.phoneNum" value="${cashInfo.phoneNum}">
                    <input type="hidden" name="cashInfo.drawAmount" value="${cashInfo.drawAmount}">
                    <input type="hidden" name="cashInfo.id" value="${bankCard.id }">
            	</section>
                <input type="submit" id="next-step" value="确认提款">
            </form>
        </section>
    </section>
</section>

<%@ include file="/jsps/modules/copyRight.jsp"%>