<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/jsps/modules/top.jsp"%>

<script type="text/javascript">
 //我的未使用卡券
 var flag1=1;
 function ticket1(obj){
	   $(".order-01").empty();
	   ajaxTicket1(1);
	   flag1=1;
	 }

    function moreTicket1(obj){
	    $(this).html("加载中...");
	    ++flag1;
	    alert(flag1);
	   ajaxTicket1(flag1);
	 }
	  //我的已使用卡券	    
	 var flag2=1;
	function ticket2(obj){
	   $(".order-02").empty();
	   ajaxTicket2(1);
	   flag2=1;
	 }
	
	 function moreTicket2(obj){
	    $(this).html("加载中...");
	    ++flag2;
	   ajaxTicket2(flag2);
	 }
	  //我的过期卡券
	  var flag3=1;
	 function ticket3(obj){
	   $(".order-03").empty();
	   ajaxTicket3(1);
	   flag3=1;
	 }
	 
	 function moreTicket3(obj){
	    $(this).html("加载中...");
	    ++flag3;
	   ajaxTicket3(flag3);
	 }

	 //我的未使用卡券ajax加载
	    function ajaxTicket1(pageStart){
				 $.ajax({ 
			      	url:'${ctx}/ajaxMemberCenterAction!newTicket.action', 
			      	type:'post', 
			      	dataType: 'json',
			      	data:{
			      		"pageNo":pageStart,
			      		"pageSize":10,
			      		"count1":0,
			      	},
					success: function(json) {
					$(".more-ticket1").empty();
					var str="";
					if(json.memberTickets!=""&&null!=json.memberTickets){
					 $.each(json.memberTickets, function(index, memberTicket) {
					  str+="<a href='#'><img class='ticket' src='${imagePath}/ticket1.png' alt='' title=''><section><h3 class='font-f84076'>优惠券</h3><p class='font-f84076 b-b-f84076'>￥<i>"+memberTicket.ticketId.ticketAmount+"</i></p>";
                      str+="<p class='font-f84076'>有效期："+(memberTicket.addTime).substring(0,4)+"年"+(memberTicket.addTime).substring(5,7)+"月"+(memberTicket.addTime).substring(8,10)+"日-"+(memberTicket.overTime).substring(0,4)+"年"+(memberTicket.overTime).substring(5,7)+"月"+(memberTicket.overTime).substring(8,10)+"日</p><p class='font-f84076'>使用范围谐达微店，满"+memberTicket.ticketId.restrictAmount+"使用</p></section></a>";					
					});
					 $(".order-01").append(str);
					  if (json.memberTickets.length<10) {
							  var more=" <div class='more-ticket1' style='line-height:3.8em'>没有更多</div>";
							   $(".order-01").append(more);
						  } else {
							 var more=" <div class='more-ticket1' style='line-height:3.8em' onClick=moreTicket1(this);>更多</div>";
							  $(".order-01").append(more);
						  }	  
				    }else{
				       var more=" <div class='more-ticket1' style='line-height:3.8em'>没有更多</div>";
                         $(".order-01").append(more);
				       }
				  },
					 error: function(XMLHttpRequest, textStatus, errorThrown) {
					      openwaring("网络出现异常");
					   }
				});	
			}
			
		//我的已使用卡券ajax加载
	    function ajaxTicket2(pageStart){
				 $.ajax({ 
			      	url:'${ctx}/ajaxMemberCenterAction!newTicket.action', 
			      	type:'post', 
			      	dataType: 'json',
			      	data:{
			      		"pageNo":pageStart,
			      		"pageSize":10,
			      		"count1":1,
			      	},
					success: function(json) {
					$(".more-ticket2").empty();
					var str="";
					if(json.memberTickets!=""&&null!=json.memberTickets){
					 $.each(json.memberTickets, function(index, memberTicket) {
					  str+="<a href='#'><img class='ticket' src='${imagePath}/ticket2.png' alt='' title=''><section><h3>优惠券</h3><p>￥<i>"+memberTicket.ticketId.ticketAmount+"</i></p>";
                      str+="<p>有效期："+(memberTicket.addTime).substring(0,4)+"年"+(memberTicket.addTime).substring(5,7)+"月"+(memberTicket.addTime).substring(8,10)+"日-"+(memberTicket.overTime).substring(0,4)+"年"+(memberTicket.overTime).substring(5,7)+"月"+(memberTicket.overTime).substring(8,10)+"日</p><p>使用范围谐达微店，满"+memberTicket.ticketId.restrictAmount+"使用</p></section><img class='used' src='${imagePath}/used.png'></a>";					
					});
					 $(".order-02").append(str);
					  if (json.memberTickets.length<10) {
							  var more=" <div class='more-ticket2' style='line-height:3.8em'>没有更多</div>";
							   $(".order-02").append(more);
						  } else {
							 var more=" <div class='more-ticket2' style='line-height:3.8em' onClick=moreTicket2(this);>更多</div>";
							  $(".order-02").append(more);
						  }	  
				    }else{
				       var more=" <div class='more-ticket2' style='line-height:3.8em'>没有更多</div>";
                         $(".order-02").append(more);
				       }
				  },
					 error: function(XMLHttpRequest, textStatus, errorThrown) {
					      openwaring("网络出现异常");
					   }
				});	
			}
			
		//我的已过期卡券ajax加载
	    function ajaxTicket3(pageStart){
				 $.ajax({ 
			      	url:'${ctx}/ajaxMemberCenterAction!newTicket.action', 
			      	type:'post', 
			      	dataType: 'json',
			      	data:{
			      		"pageNo":pageStart,
			      		"pageSize":10,
			      		"count1":2,
			      	},
					success: function(json) {
					//$(".order-03").empty();
					$(".more-ticket3").empty();
					var str="";
					if(json.memberTickets!=""&&null!=json.memberTickets){
					 $.each(json.memberTickets, function(index, memberTicket) {
					  str+="<a href='#'><img class='ticket' src='${imagePath}/ticket3.png' alt='' title=''><section><h3>优惠券</h3><p>￥<i>"+memberTicket.ticketId.ticketAmount+"</i></p>";
                      str+="<p>有效期："+(memberTicket.addTime).substring(0,4)+"年"+(memberTicket.addTime).substring(5,7)+"月"+(memberTicket.addTime).substring(8,10)+"日-"+(memberTicket.overTime).substring(0,4)+"年"+(memberTicket.overTime).substring(5,7)+"月"+(memberTicket.overTime).substring(8,10)+"日</p><p>使用范围谐达微店，满"+memberTicket.ticketId.restrictAmount+"使用</p></section><img class='passed' src='${imagePath}/passed.png'></a>";					
					});
					 $(".order-03").append(str);
					  if (json.memberTickets.length<10) {
							  var more=" <div class='more-ticket3' style='line-height:3.8em'>没有更多</div>";
							   $(".order-03").append(more);
						  } else {
							 var more=" <div class='more-ticket3' style='line-height:3.8em' onClick=moreTicket3(this);>更多</div>";
							  $(".order-03").append(more);
						  }	  
				    }else{
				       var more=" <div class='more-ticket3' style='line-height:3.8em'>没有更多</div>";
                         $(".order-03").append(more);
				       }
				  },
					 error: function(XMLHttpRequest, textStatus, errorThrown) {
					      openwaring("网络出现异常");
					   }
				});	
			}

</script>

<body class="s_p_body">
<header class='clearfix'>
    <img src="${imagePath}/logo.png" alt="logo">
</header> 
<section class="red-p-header">
    <section class="order-header">
        <section class="order-header-choose"><p><a id="order-01" onClick="ticket1(this)">未使用</a></p></section>
        <section><p><a id="order-02" onClick="ticket2(this)">已使用</a></p></section>
        <section><p><a id="order-03" onClick="ticket3(this)">已过期</a></p></section>
    </section>
</section>
<section class="red-content order-01">
<c:if test="${not empty memberTickets}">
<c:forEach items="${memberTickets}" var="memberTicket">
        <a href="#">
        	<img class="ticket" src="${imagePath}/ticket1.png" alt="" title="">
            <section>
                <h3 class="font-f84076">优惠券</h3>
                <p class="font-f84076 b-b-f84076">￥<i>${memberTicket.ticketId.ticketAmount}</i></p>
                <p class="font-f84076">有效期：<fmt:formatDate value="${memberTicket.addTime}" pattern="yyyy年MM月dd日" />-<fmt:formatDate value="${memberTicket.overTime}" pattern="yyyy年MM月dd日" /></p>
                <p class="font-f84076">使用谐达微店，满${memberTicket.ticketId.restrictAmount}使用</p>
            </section>
        </a>
</c:forEach>
<c:if test="${not empty memberTickets}">
      <c:if test="${fn:length(memberTickets) < 10}">
      <div class="more-ticket1" style="line-height:3.8em">没有更多</div>
      </c:if>
      <c:if test="${fn:length(memberTickets) eq 10}">
      <div class="more-ticket1" style="line-height:3.8em" onClick="moreTicket1(this)">更多</div>
      </c:if>
</c:if>
</c:if>
<c:if test="${empty memberTickets}"><section style="margin-top:50%">您没有未使用的卡券</section></c:if>
</section>

<section class="red-content order-02">
 <!-- <c:forEach items="${memberTickets}" var="memberTicket">
    <a href="#">
        <img class="ticket" src="${imagePath}/ticket2.png" alt="" title="">
        <section>
            <h3>优惠券</h3>
            <p>￥<i>${memberTicket.ticketId.ticketAmount}</i></p>
            <p>有效期：<fmt:formatDate value="${memberTicket.addTime}" pattern="yyyy年MM月dd日" />-<fmt:formatDate value="${memberTicket.overTime}" pattern="yyyy年MM月dd日" /></p>
            <p>使用范谐达米特微店，满${memberTicket.ticketId.restrictAmount}使用</p>
        </section>
        <img class="used" src="${imagePath}/used.png">
    </a>
    </c:forEach>
  <c:if test="${not empty memberTickets}">
      <c:if test="${fn:length(memberTickets) < 10}">
      <div class="more-ticket1" style="line-height:3.8em">没有更多</div>
      </c:if>
      <c:if test="${fn:length(memberTickets) eq 10}">
      <div class="more-ticket1" style="line-height:3.8em" onClick="moreTicket1(this)">更多</div>
      </c:if>
</c:if> -->
</section>

<section class="red-content order-03">
<!-- <c:forEach items="${memberTickets}" var="memberTicket">
    <a href="#">
        <img class="ticket" src="${imagePath}/ticket3.png" alt="" title="">
        <section>
            <h3>优惠券</h3>
            <p>￥<i>${memberTicket.ticketId.ticketAmount}</i></p>
            <p>有效期：<fmt:formatDate value="${memberTicket.addTime}" pattern="yyyy年MM月dd日" />-<fmt:formatDate value="${memberTicket.overTime}" pattern="yyyy年MM月dd日" /></p>
            <p>使用范谐达米特微店，满${memberTicket.ticketId.restrictAmount}使用</p>
        </section>
        <img class="passed" src="${imagePath}/passed.png">
    </a>
 </c:forEach>
 <c:if test="${not empty memberTickets}">
      <c:if test="${fn:length(memberTickets) < 10}">
      <div class="more-ticket1" style="line-height:3.8em">没有更多</div>
      </c:if>
      <c:if test="${fn:length(memberTickets) eq 10}">
      <div class="more-ticket1" style="line-height:3.8em" onClick="moreTicket1(this)">更多</div>
      </c:if>
</c:if> -->
</section>
<%@ include file="/jsps/modules/copyRight.jsp"%>
