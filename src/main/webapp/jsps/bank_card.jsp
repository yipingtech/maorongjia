<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/jsps/modules/top.jsp"%>
<body>
<header class="header_top clearfix">
    <a href="epMemberCenterAction!findBankCardByMember.action?flag=${flag }"><img src="${imagePath}/left_arrow.png"></a>
    <p>银行卡详情</p>
</header>
<!--container-->
<section id="container">
    <div class="card-list">
        <div class="card-info clearfix">
            <strong class="left">持卡人</strong><p class="left">${bankCard.bankCardMember }</p>
        </div>
        <div class="card-info clearfix">
            <strong class="left">卡号</strong><p class="left">${bankCard.bankCardNum }</p>
        </div>
        <div class="card-info clearfix">
            <strong class="left">开户银行</strong><p class="left">${bankCard.bankCardFlag }</p>
        </div>
        <div class="card-info clearfix">
            <strong class="left">开户城市</strong><p class="left">${bankCard.bankCardAddress }</p>
        </div>
        <div class="card-info clearfix" style="border-bottom:none">
            <strong class="left">开户网点</strong><p class="left" style="width: 62%;">${bankCard.bankCardPoint }</p>
        </div>
    </div>
    <div class="card-list-btn">
        <a href="epMemberCenterAction!deleteBankCard.action?id=${bankCard.id }&flag=${flag}" onClick="return confirm('确定删除?');">删除此卡</a>
        <a href="epMemberCenterAction!toEditCard.action?id=${bankCard.id }&flag=${flag}">修改信息</a>
    </div>
</section>
<!--end of container-->
</body>
</html>