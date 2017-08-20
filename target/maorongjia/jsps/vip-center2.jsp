<%@ page contentType="text/html;charset=utf-8"%>

<%@ include file="/jsps/modules/top.jsp"%>
<body class='store'>
<header class='clearfix' id="clearfix">
	<section id="vip-banner">
		<img src="${imagePath}/top-banner.png" alt="">
        <section id="vip-card">
            <img src="${imagePath}/vip-ad.png" alt="" >
            <section class="vip-infor">
            
                <a class="upload-tx" href="epMemberCenterAction!viewMember.action"><img src="${member.imagePath}" alt="" style="width: 52px; height: 52px;"></a>
                <p>
                	 <a href="epMemberCenterAction!viewMember.action"><span class="color-5a5a5a">${member.nickname}</span></a>
                    <br>
                    <c:if test="${not empty member.mobile}">
                    <%--  <a href="epMemberCenterAction!newUpgrade.action">
                     <span class="color-b9aba6">
                     	<c:if test="${member.rank eq '1'}">
                     	升级VIP会员
                     	</c:if>
                     	<c:if test="${member.rank eq '2'}">
                     	升级白金会员
                     	</c:if>
                     	<c:if test="${member.rank eq '3'}">
                     	升级钻石会员
                     	</c:if>
                     </span>
                     </a> --%>
                    </c:if>
                    <c:if test="${empty member.mobile}">
                     <a href="epMemberCenterAction!EditMemberPhonePage.action"><span class="color-b9aba6">升级VIP会员</span></a>
                    </c:if>
                </p>
            </section>
            <section class="vip-point">
                <p style="text-align: left;">
<%--                	积分：<a href="epMemberCenterAction!queryAllIntergralByUser.action" style="color: #5a5a5a;margin-right: 13px;"><span>${member.intergal}</span></a>--%>
                    <br>
                    总额：<a href="epMemberCenterAction!queryAllRechargeByUser.action" style="color: #5a5a5a;"><span class="color-875f37">￥${member.balance}</span></a>
                </p>
            </section>
        </section>
    </section>
</header>

<section class="shop-head">
    <section class="vip-phone">
    	<img src="${imagePath}/phone.png" alt="">&nbsp;&nbsp;衣裳常常显示人品
    </section>
</section>
<section class="shop-center">
	<section class="shop-list">
	    <div class="wave"></div>
    	<ul class="clearfix">
        	<li><a href="epMemberCenterAction!noPayOrderByUser.action" class="r1"><div>我的订单<br><i>${count1}</i></div></a></li>
            <li><a href="epMemberCenterAction!queryMemberFirstLevel.action" class="r2"><div>我的团队<br><i>${count2}</i></div></a></li>
            <li><a href="epMemberCenterAction!queryMemberCommission.action" class="r3"><div>我的红包<br><i>${member.commission}</i></div></a></li>
        </ul>
        <p><img src="${imagePath}/spread.png" alt="">&nbsp;&nbsp;分享您的收获，赚取丰富佣金</p>
    </section>
    <section class="shop-nav">
    	<ul class="clearfix">
        	<li><a class="nav1" href="epMemberCenterAction!viewMember.action">我的账户</a></li>
            <li><a class="nav2" href="epMemberCenterAction!queryMemberFans.action">我的粉丝</a></li>
            <c:if test="${not empty member.agent}">
	            <li><a class="nav3" href="epMemberCenterAction!queryMyAgent.action">我的代理</a></li>
            </c:if>
            <li><a class="nav6" href="epShoppingCarAction.htm?pageType=showCar2 ">&nbsp&nbsp&nbsp我的购物车</a></li>
            <!--  <li><a class="nav4" href="epMemberCenterAction!newRecharge.action">会员充值</a></li>
            <li><a class="nav5" href="epMemberCenterAction!newPacket.action">我的红包</a></li>
            <li><a class="nav6" href="epMemberCenterAction!newTicket.action?count1=0">我的卡券</a></li> -->
        </ul>
    </section>
</section>

<section id="index_footer" class="index_footer" style="width:100%">
                <a class="i-f-blue" href="index.html">
                    <img src="${imagePath}/footer-house.png" >
                </a>
                <a class="i-f" href="vip-particular.html">
                    <img src="${imagePath}/footer-VIP.png" class="index_footer_img">
                    <p>会员特权</p>
                </a>
                <a class="re_c" href="vip-center.html">
                    <img src="${imagePath}/footer-center.png" class="index_footer_img">
                    <p>会员中心</p>
                </a>
</section>
<script type="text/javascript">
    $(document).ready(function (e) {
        resize_lv1();
    });
    function resize_lv1() {
        var width_lv1 = $(".shop-center .shop-list li a").width();
        $(".shop-center .shop-list li a").css("height", width_lv1 * 1.0);
    }
    $(window).resize(function () {
        resize_lv1();
    });
</script>
     <!--container right end-->

<%@ include file="/jsps/modules/copyRight.jsp"%>