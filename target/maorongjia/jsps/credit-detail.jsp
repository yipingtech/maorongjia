<%@ page contentType="text/html;charset=utf-8"%>

<%@ include file="/jsps/modules/top.jsp"%>

<body class='edit_address bg-dcdcdc'>
<header class='clearfix close-bottom'>
		<a href="epMemberCenterAction!viewMember.action"><img src="${imagePath}/add-left.png" alt="" id="close-bottom-img"></a>
		记录明细
</header>
<input type="hidden" class="decideNum" value="${count3}">
<section class="e-ad">
    <section class="fenxiao">
        <ul class="clearfix">
            <li><a class="bg-ffbeb1" href="epMemberCenterAction!queryAllIntergralByUser.action">积分明细</a></li>
            <li><a href="epMemberCenterAction!queryAllRechargeByUser.action">余额明细</a></li>
            <li class="border-none"><a href="epMemberCenterAction!queryAllDrawCashByUser.action">提现明细</a></li>
        </ul>
    </section>
    
    <section class="fenxiao-content">
        <section class="f-c" id="blo">
            <section class="tab-content mt-3">
                <section id="blo">
                    <table align="center" id="credit-detail" class="my-intergral-info">
                    	<p>当前积分：<span class="color-f84076">${member.intergal}</span>分，历史累计<span class="color-f84076">${count1}</span>分</p>
                        <tr>
                            <th>#时间</th>
                            <th>积分流水</th>
                            <th>备注</th>
                        </tr>
		                    <c:forEach items="${intergralInfos}" var="intergralInfo" varStatus="status">
		                       <tr>
		                           <td>${fn:substringBefore(intergralInfo.addTime," ")}</td>
		                           <td>${intergralInfo.intergral}</td>
		                           <td>${intergralInfo.comments}</td>
		                           <input type="hidden" class="intergralTotal" value="${intergralInfo.intergral}">
		                       </tr>
		                    </c:forEach>
		                    <c:if test="${not empty intergralInfos}">
			                    <c:if test="${fn:length(intergralInfos) < 10}">
			                    <tr class="more-intergral"><td></td><td>没有更多</td><td></td></tr>
			                    </c:if>
			                    <c:if test="${fn:length(intergralInfos) eq 10}">
			                    <tr class="more-intergral"><td></td><td onClick="moreIntergral(this)">更多</td><td></td></tr>
			                    </c:if>
		                    </c:if>
		                    <c:if test="${empty intergralInfos}"> <tr><td></td><td>没有积分明细</td><td></td></tr></c:if>
                    </table>
                </section>
            </section>
        </section>
        <section class="f-c">
        	<section class="tab-content mt-3">
                <section id="blo">
                    <table align="center" id="credit-detail" class="my-rechargeInfo-info">
                    	<p>当前余额：<span class="color-f84076">${member.balance}</span>元，历史累计<span id="rechargeTotal" class="color-f84076">
                    	<c:if test="${empty cashDouble}">0</c:if><c:if test="${not empty cashDouble}">${cashDouble}</c:if></span>元</p>
                        <tr>
                            <th>#时间</th>
                            <th>金额</th>
                            <th>备注</th>
                        </tr>
                        <c:forEach items="${rechargeInfos}" var="rechargeInfo" varStatus="status">
                        <tr>
                            <td>${fn:substringBefore(rechargeInfo.rechargeTime," ")}</td>
                            <td>${rechargeInfo.rechargeAmount}</td>
                            <td><c:if test="${rechargeInfo.rechargeComments eq '0'}">充值</c:if><c:if test="${rechargeInfo.rechargeComments eq '1'}">消费</c:if></td>
                            <input type="hidden" class="rechargeTotal" value="${rechargeInfo.rechargeAmount}">
                        </tr>
                        </c:forEach>
                        <c:if test="${not empty rechargeInfos}">
	                        <c:if test="${fn:length(rechargeInfos) < 10}">
	                        <tr class="more-rechargeInfo"><td></td><td>没有更多</td><td></td></tr>
	                        </c:if>
	                        <c:if test="${fn:length(rechargeInfos) eq 10}">
	                        <tr class="more-rechargeInfo"><td></td><td onClick="moreRechargeInfo(this)">更多</td><td></td></tr>
	                        </c:if>
                        </c:if>
                       <c:if test="${empty rechargeInfos}"> <tr><td></td><td>没有余额明细</td><td></td></tr></c:if>
                    </table>
                </section>
            </section>
        </section>
        <section class="f-c">
        	<section class="tab-content mt-3">
                <section id="blo">
                    <table align="center" id="credit-detail" class="my-cashInfo-info">
                    	<p>当前佣金：<span class="color-f84076">${member.commission}</span>元，历史累计<span id="cashTotal" class="color-f84076">
                    	<c:if test="${empty cashDouble}">0</c:if><c:if test="${not empty cashDouble}">${cashDouble}</c:if></span>元</p>
                        <tr>
                            <th>#提现时间</th>
                            <th>消费流水</th>
                            <th>备注</th>
                        </tr>
                        <c:forEach items="${cashInfos}" var="cashInfo" varStatus="status">
                        <tr>
                            <td>${cashInfo.drawTime}</td>
                            <td>${cashInfo.drawAmount}</td>
                            <c:if test="${not empty cashInfo.dealTime}"><td>提现成功</td></c:if>
                            <c:if test="${empty cashInfo.dealTime}"><td style="color: red;">正在处理</td></c:if>
                            <input type="hidden" class="totalCash" value="${cashInfo.drawAmount}">
                        </tr>
                        </c:forEach>
                        <c:if test="${not empty cashInfos}">
                        <c:if test="${fn:length(cashInfos) < 10}">
	                        <tr class="more-cashInfo"><td></td><td>没有更多</td><td></td></tr>
	                        </c:if>
	                        <c:if test="${fn:length(cashInfos) eq 10}">
	                        <tr class="more-cashInfo"><td></td><td onClick="moreCashInfo(this)">更多</td><td></td></tr>
	                        </c:if>
                        </c:if>
                        <c:if test="${empty cashInfos}"> <tr><td></td><td>没有提现明细</td><td></td></tr></c:if>
                    </table>
                </section>
            </section>
        </section>
    </section>
</section>
			<!-- <section class="loading"><img src="${imagePath}/loading.gif"></section> -->
			<section class="order-trip-message" style="color: red;position: fixed;top: 50%;left: 41%;display: none;">没有更多订单</section>
<%@ include file="/jsps/modules/copyRight.jsp"%>
<script type="text/javascript">
$(function(){
      var decideNum=$(".decideNum").val();
	  $(".fenxiao li a.bg-ffbeb1").removeClass("bg-ffbeb1");
	  $(".fenxiao li a:eq("+decideNum+")").addClass("bg-ffbeb1");
	  $(".fenxiao-content .f-c:eq("+decideNum+")").show().siblings().hide();
  
});

	 
	 //积分加载更多
	 var IntergralNum=1;
	 function moreIntergral(obj){
		  $(this).html("加载中...");
		  ++IntergralNum;	
		  ajaxIntergral(IntergralNum);
	 }
	 
	 	 //余额加载更多
	 var rechargeNum=1;
	 function moreRechargeInfo(obj){
		  $(this).html("加载中...");
		  ++rechargeNum;	
		  ajaxRechargeInfo(rechargeNum);
	 }
	 
	 	 //提现加载更多
	 var cashNum=1;
	 function moreCashInfo(obj){
		  $(this).html("加载中...");
		  ++cashNum;	
		  ajaxCashInfo(cashNum);
	 }

//积分ajax
function ajaxIntergral(moreNum){
  $.ajax({ 
          url:'${ctx}/ajaxMemberCenterAction!queryAllIntergralByUser.action', 
          type:'post', 
          dataType: 'json',
          data:{
	      		"pageNo":moreNum,
	      		"pageSize":10
			      	}, 
	      success: function(json) {	 
	      $(".more-intergral").empty(); 
	      if (json.intergralInfos!=""&&null!=json.intergralInfos) {
	       var str="";
	         $.each(json.intergralInfos, function(index, intergralInfo) {
				str+="<tr><td>"+(intergralInfo.addTime).substring(0,10)+"</td><td>"+intergralInfo.intergral+"</td><td>"+intergralInfo.comments+"</td><input type='hidden' class='intergralTotal' value='"+intergralInfo.intergral+"'></tr>";
				});
				if (json.intergralInfos.length<10) {
					str+="<tr class='more-intergral'><td></td><td class='more-intergral-message'>没有更多</td><td></td></tr>";
				} else {
				    str+="<tr class='more-intergral'><td></td><td class='more-intergral-message' onClick=moreIntergral(this);>更多</td><td></td></tr>";
				}
				
			    $(".my-intergral-info").append(str);
			  } else {
				       var str="<tr class='more-intergral'><td></td><td class='more-intergral-message'>没有更多</td><td></td></tr>";
				       $(".my-intergral-info").append(str);
				}	            
	      },
	      error: function(XMLHttpRequest, textStatus, errorThrown) {
	           openwaring("网络出现异常");
	        }
	   });
}

//余额ajax
function ajaxRechargeInfo(moreNum){
  $.ajax({ 
          url:'${ctx}/ajaxMemberCenterAction!queryAllRechargeByUser.action', 
          type:'post', 
          dataType: 'json',
          data:{
	      		"pageNo":moreNum,
	      		"pageSize":10
			      	}, 
	      success: function(json) {	  
	      $(".more-rechargeInfo").empty(); 
	      if (json.rechargeInfos!=""&&null!=json.rechargeInfos) {
	       var str="";
	         $.each(json.rechargeInfos, function(index, rechargeInfo) {
				str+="<tr><td>"+(rechargeInfo.rechargeTime).substring(0,10)+"</td><td"+rechargeInfo.rechargeAmount+"</td><td>";
				if(rechargeInfo.rechargeComments=="0"){str+="充值";}else if(rechargeInfo.rechargeComments=="1"){str+="消费";}
				str+="</td><input type='hidden' class='rechargeTotal' value="+rechargeInfo.rechargeAmount+"></tr>";
			  });
			  if (json.rechargeInfos.length<10) {
				str+="<tr class='more-rechargeInfo'><td></td><td>没有更多</td><td></td></tr>";
			} else {
				str+="<tr class='more-rechargeInfo'><td></td><td onClick=moreRechargeInfo(this);>更多</td><td></td></tr>";
			}
			  
			  $(".my-rechargeInfo-info").append(str);
			  } else {
				       var str="<tr class='more-rechargeInfo'><td></td><td>没有更多</td><td></td></tr>";
				       $(".my-rechargeInfo-info").append(str);
				}	            
	      },
	      error: function(XMLHttpRequest, textStatus, errorThrown) {
	           openwaring("网络出现异常");
	        }
	   });
}

//提现ajax
function ajaxCashInfo(moreNum){
  $.ajax({ 
          url:'${ctx}/ajaxMemberCenterAction!queryAllDrawCashByUser.action', 
          type:'post', 
          dataType: 'json',
          data:{
	      		"pageNo":moreNum,
	      		"pageSize":10
			      	}, 
	      success: function(json) {	  
	      $(".more-cashInfo").empty(); 
	      if (json.cashInfos!=""&&null!=json.cashInfos) {
	       var str="";
	         $.each(json.cashInfos, function(index, cashInfo) {
				str+="<tr><td>"+cashInfo.drawTime+"</td><td>"+cashInfo.drawAmount+"</td>";
                            if(null!=cashInfo.dealTime){str+="<td>提现成功</td>";}
                            else{str+="<td style='color: red;'>正在处理</td>";}
                            str+="<input type='hidden' class='totalCash' value='"+cashInfo.drawAmount+"'></tr>";
				});
				if (json.cashInfos.length<10) {
					str+="<tr class='more-cashInfo'><td></td><td>没有更多</td><td></td></tr>";
				} else {
				    str+="<tr class='more-cashInfo'><td></td><td onClick=moreCashInfo(this);>更多</td><td></td></tr>";
				}
				$(".my-cashInfo-info").append(str);
			  } else {
				       var str="<tr class='more-cashInfo'><td></td><td>没有更多</td><td></td></tr>";
				       $(".my-cashInfo-info").append(str);
				}	            
	      },
	      error: function(XMLHttpRequest, textStatus, errorThrown) {
	           openwaring("网络出现异常");
	        }
	   });
}
</script>