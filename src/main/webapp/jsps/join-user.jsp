<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/jsps/modules/top.jsp"%>

<body class="s_p_body">
<header class='clearfix'>
    <a href="epIndexAction.action"><img src="${imagePath}/logo.png" alt="logo"></a>
</header>
<section id="s-p-1">
	<img src="${uploadPath}/${productInfo.imgurl}">
</section>
<section id="s-p-2" class="s-p-2 border-bottom">
	${productInfo.title}<br>
    <span>价格：</span><span class="succeed-o s-p-2-line-through" class="s-p-2-span-color">${productInfo.shopPrice}</span><span class="s-p-2-span-color s-p-2-line-through s-p-2-font-size">RMB</span>
	<a class="s-p-2-a-free-dress" id="s-p-2-a-free-dress">免费试穿</a>
	<span>已有<span class="s-p-2-red">${count}</span>人申请</span>
</section>
<section id="join-user" class="join-user">
	<p class="join-user-founder">发起人</p>
    <span class="founder-img-name clearfix">
    	<img src="${imagePath}/nub.png" alt="" title="">
        <a>${member.realname}</a>
    </span>
    <p class="join-user-founder">应邀好友</p>
    <section>
    <c:forEach items="${activityInvites}" var="activityInvite" varStatus="status">
    	<a href="#"><img src="${uploadPath}/${activityInvite.memberId.imagePath}" alt="" title=""></a>
   </c:forEach>
    </section>
</section>
<%@ include file="/jsps/modules/copyRight.jsp"%>