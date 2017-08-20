<%@ page contentType="text/html;charset=utf-8"%>

<%@ include file="/jsps/modules/top.jsp"%>
	<body class="s_p_body">
		<!-- header -->
		<header class='clearfix'>
			<img src="${imagePath}/logo.png" alt="logo">
		</header> 
		<!-- header end -->
		<!-- content header -->
		<section class="red-p-header">
				<section class="order-header">
							<section class="order-header-choose"><p><a id="order-01">未使用( ${fn:length(memberBonus1)} )</a></p></section>
							<section><p><a id="order-02">已使用 ( ${fn:length(memberBonus2)} )</a></p></section>
							<section><p><a id="order-03">已过期 ( ${fn:length(memberBonus3)} ) </a></p></section>
				 </section> 
		</section>
		<!-- content header end -->
		<!-- 未使用 -->
		<section class="red-content order-01">
		  <c:if test="${not empty memberBonus1}">
		     <c:forEach items="${memberBonus1}" var="memberBonu1" varStatus="status">
				<section class="red-c">
					<section><p>红包<span>￥${memberBonu1.bonus.amount}</span></p><p>有效期：${fn:substringBefore(memberBonu1.validPeriod, " ")}</p></section>
					<section><img src="${imagePath}/red.png"></section>
				</section>
			</c:forEach>
		</c:if>
		<c:if test="${empty memberBonus1}"><section style="margin-top:50%">您没有未使用红包</section></c:if>
		</section>
		<!-- 未使用 end -->
		<!-- 已使用 -->
		<section class="red-content order-02">
		 <c:if test="${not empty memberBonus2}">
		   <c:forEach items="${memberBonus2}" var="memberBonu2" varStatus="status">
				<section class="red-c">
					<section><p>红包<span>￥${memberBonu2.bonus.amount}</span></p><p>有效期：${fn:substringBefore(memberBonu3.validPeriod, " ")}</p></section>
					<section><img src="${imagePath}/red.png"></section>
					<img src="${imagePath}/red_02.png" class="red-content-img" alt="" title="">
				</section>
			</c:forEach>
		</c:if>
		<c:if test="${empty memberBonus2}"><section style="margin-top:50%">您没有使用过的红包</section></c:if>
		</section>
		<!-- 未使用 end -->
		<!-- 已过期 -->
		<section class="red-content order-03">
	     <c:if test="${not empty memberBonus2}">
		    <c:forEach items="${memberBonus3}" var="memberBonu3" varStatus="status">
				<section class="red-c">
					<section><p>红包<span>￥${memberBonu3.bonus.amount}</span></p><p>有效期：${fn:substringBefore(memberBonu3.validPeriod, " ")}</p></section>
					<section><img src="${imagePath}/red.png"></section>
					<img src="${imagePath}/red_01.png" class="red-content-img" alt="" title="">
				</section>
			</c:forEach>
		 </c:if>
		 <c:if test="${empty memberBonus1}"><section style="margin-top:50%">您没有过期的红包</section></c:if>
		</section>
		<!-- 已过期 end -->
		<!-- content footer  -->
		<section class="red-footer">
				<section><p>您已获得 <span>${fn:length(memberBonus1)}</span> 个红包，快快走起购物吧</p></section>
				<section><a href="epIndexAction.action">使用</a></section>
		</section>
		<!-- content footer end -->
<%@ include file="/jsps/modules/copyRight.jsp"%>
