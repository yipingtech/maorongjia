<%@ page contentType="text/html;charset=utf-8"%>

<%@ include file="/jsps/modules/top.jsp"%>
<body class='edit_address bg-eee'>
<header class='clearfix close-bottom'>
		<a href="epMemberCenterAction.action"><img src="${imagePath}/add-left.png" alt="" id="close-bottom-img"></a>
		提现申请
</header>
<section class="e-ad">
    <section class="fenxiao-content">
        <section class="f-c" id="blo">
            <section class="submit-success"><img src="${imagePath}/submit-success.png">&nbsp;&nbsp;提款申请已提交</section>
            <section class="get-cash">
                <p>您本次提款业务明细如下：</p>
                <p>提款微信账号：${member.nickname}</p>
                <p>佣金余额：<span class="color-f84076"><fmt:formatNumber value="${member.commission}" pattern="##.##"  ></fmt:formatNumber></span>元</p>
            </section>
            <section class="get-cash-infor border-bottom2">
                <h3>提款信息</h3>
                <p class="real-infor"><i>持卡人：</i><i>${cashInfo.bankCard.bankCardMember}</i></p>
                <p class="real-infor"><i>手机号码：</i><i>${cashInfo.phoneNum}</i></p>
                <p class="real-infor"><i>提款金额：</i><i><span class="color-f84076">${cashInfo.drawAmount}元</span></i></p>
                <p class="real-infor"><i>手续费：</i><i><span class="color-f84076">0.00</span>（实际到账${cashInfo.drawAmount}元）</i></p>
                <p class="real-infor"><i>账户余额：</i><i><span class="color-f84076"><fmt:formatNumber value="${member.commission}" pattern="##.##"  ></fmt:formatNumber></span>（提款后）</i></p>
               	<p class="real-infor"><i>预期到账时间：</i><i>${predictTime}之前</i></p>
            </section>
        </section>
    </section>
</section>


<%@ include file="/jsps/modules/copyRight.jsp"%>