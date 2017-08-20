<%@ page contentType="text/html;charset=utf-8"%>

<%@ include file="/jsps/modules/top.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>   
<body>
	  <!-- scroll start -->
	<section class="weui_tab_bd body-gary" id="weui_tab_bd_bottom">

<div class="main">
    <section class="vip_head">
        <div class="clearfix">
            <div class="icon left"><img src="${member.imagePath}"></div>
            <div class="name left">
                <input type="hidden" value="${member.distributor }" id="memberSign">
                <div>昵称：${mtag:cutString(member.nickname,5,'...')}</div>
                <div>会员ID：${member.id }</div>
                <div>总营业额：<fmt:formatNumber value="${member.commissionLine}" pattern="##.##" minFractionDigits="2" ></fmt:formatNumber></div>
            </div>
        </div>
        <div class="time clearfix">
            <div class="left">关注时间：<fmt:formatDate value="${member.addTime}" type="date" dateStyle="medium"/></div> 
           <%--  <c:if test="${member.distributor eq 1 }">
                <div class="right">微主：是</div>
            </c:if>
            <c:if test="${member.distributor eq 0 }">
                <div class="right">微主：否<a href="epIndexAction.action">(点击购买成为微主)</a></div>
            </c:if> --%>
        </div>
    </section>
    <section class="vip_module clearfix">
        <ul class="clearfix">
            <li><a href="epMemberCenterAction!noPayOrderByUser.action" class="r1">我的订单<i>${count1}</i></a></li>
            <li><a href="epMemberCenterAction!queryMemberFirstLevel.action" class="r2">我的团队<i>${teamNum}</i></a></li>
            <li><a href="epMemberCenterAction!queryMemberCommission.action" class="r3">我的佣金
		            <i>
		               <fmt:formatNumber value="${bonus}" pattern="##.##" minFractionDigits="2" ></fmt:formatNumber>
		            </i>
               </a>
            </li>
        </ul>
    </section>
    <section class="vip_massege">
        <c:if  test="${empty member.memberFather }" >
            <h4>
              <c:if test="${empty member.agent}">
                                      您是平台会员
              </c:if>
              <c:if test="${not empty member.agent}">
	                              您是平台代理 
              </c:if>
            </h4>
        </c:if>
        <c:if  test="${!empty member.memberFather }" >
            <h4>您是由【${member.memberFather.nickname }】推荐</h4>
        </c:if>
        <p><img src="${imagePath}/spread.png" alt="">&nbsp;&nbsp;分享您的收获，赚取丰富佣金</p>
    </section>
    <section class="vip_list">
       <%--  <c:if test="${member.agent eq null }">
             <a href="epMemberCenterAction!showRecharge.action" class="nav8" id="agentRecharge">成为代理商<span class="right">&gt;</span></a>
        </c:if> --%>
        <a href="epMemberCenterAction!viewMember.action" class="nav1">我的账户<span class="right">&gt;</span></a>
        <!-- <a href="epMemberCenterAction!queryMemberFans.action" class="nav2">我的粉丝<span class="right">&gt;</span></a> -->
        <!--  <a class="nav3" href="epMemberCenterAction!queryMyAgent.action">我的代理<span class="right">&gt;</span></a> -->
        <a href="epShoppingCarAction.htm?pageType=showCar2" class="nav6">我的购物车<span class="right">&gt;</span></a>
        <a href="epMemberCenterAction!applyDrawCash.action" class="nav4"  id="toCash">申请提现<span class="right">&gt;</span></a>
        <a href="epMemberCenterAction!queryCashRecord.action" class="nav7" id="cashRecord">提现记录<span class="right">&gt;</span></a>
        
    </section>
</div>

			  </section>
			  <!-- scroll end -->
<script src='${jsPath}/style.js'></script>
<script type="text/javascript">
    $(document).ready(function (e) {
        resize_lv1();
    });
    function resize_lv1() {
        var width_lv1 = $(".vip_head").width();
       // $(".vip_head").css("height", width_lv1 * 0.28666667);
    }
    $(window).resize(function () {
        resize_lv1();
    });
</script>
</body>
<%@ include file="/jsps/modules/footer2.jsp"%>  