<%@ page contentType="text/html;charset=utf-8"%>

<%@ include file="/jsps/modules/top.jsp"%>
<body class="edit_address">
<header class='clearfix close-bottom'>
    <a href="javascript:history.back();"><img src="${imagePath}/add-left.png" alt="" id="close-bottom-img"></a>
    会员升级
</header>
<section class="e-ad">
    <form action="epMemberCenterAction!editUpgrade.action" class="e-ad-form">
        <p class="pre-vip">当前您的会员级别是：
            <c:if test="${member.rank eq '1'}">普通会员</c:if>
			<c:if test="${member.rank eq '2'}">高级会员</c:if>
			<c:if test="${member.rank eq '3'}">VIP会员</c:if>
			<c:if test="${member.rank eq '4'}">至尊VIP会员</c:if></p>
        <h3>请选择充值金额（元）进行升级</h3>
        <section class="upgrade-vip">
        <c:if test="${member.rank eq '1'}">
            <p><label class="vip-level" for="vip-level">充值
            <fmt:formatNumber value="${parameterSet.vipMember}" pattern="#"/>
                        元升级到贵宾会员</label><input type="checkbox" class="vip-level" name="member.rank" value="2"></p>
        </c:if>
        <c:if test="${member.rank eq '1' || member.rank eq '2'}">
        	<p><label class="vip-level" for="vip-level">充值
        	<fmt:formatNumber value="${parameterSet.svip}" pattern="#"/>
        	元升级到白金会员</label><input type="checkbox" class="vip-level" name="member.rank" value="3"></p>
        </c:if>
        <c:if test="${member.rank != '4'}">
        	<p><label class="vip-level" for="vip-level">充值
        	<fmt:formatNumber value="${parameterSet.diamondMember}" pattern="#"/>
        	元升级到钻石会员</label><input type="checkbox" class="vip-level" name="member.rank" value="4"></p>
        </c:if>
        </section>
       <select name="" class="select-vip">
		  <option>支付宝付款</option>
		  <option>微信付款</option>
		  <option>银行卡付款</option>
		</select>
        <button id="sure">确认</button>
        <section class="tips">
            <p>温馨提示</p>
            <p>1.储值金额永久有效且使用于谐达微信商城；</p>
            <p>2.充值成功后，会员级别会自动调整；</p>
        </section>
    </form>
</section>
<script src="${jsPath}/style.js"></script>
</body>
</html>