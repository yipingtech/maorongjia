<%@ page contentType="text/html;charset=utf-8"%>

<%@ include file="/jsps/modules/top.jsp"%>
<script type="text/javascript">
 $(function(){
 	var level;
	level = $(".level").val()-1;
    var tabNum = $(".tabNum").val()-1;
	//我的客户，分销订单，我的佣金样式变化
	$(".fenxiao li a.bg-ffbeb1").removeClass("bg-ffbeb1");
	$(".fenxiao li a:eq("+tabNum+")").addClass("bg-ffbeb1");
	$(".tab-content section:eq("+tabNum+")").show().siblings().hide();
	//一级，二级，三级样式变化
	if(level==-1){
		$(".fenxiao-tab").hide();
	}else{
		$(".fenxiao-tab ul li a.first-active").removeClass("first-active");
		$(".fenxiao-tab ul li a:eq("+level+")").addClass("first-active");
	}
	 
	 
	 
	 //分销的下一步按钮监听
	 $("#next-step").click(function(){
	   var wechat=$("#infor-name").val();
	   var amount=$("#cash-count").val();
	   var phone=$("#infor-tel").val();
	   //判断是否为空
	   if((wechat!="")&&(amount!="")&&(phone!="")){
	   //判断金额填写是否为数字
	     if (!isNaN(amount)){
			if(($(".commsissionAmount").val()-amount)<0){
			$(".cashTrips").show();
	        $(".cashTrips").html("佣金不足");
	        $(".cashTrips").fadeOut(3000);
            }else{
            //判断手机号
              if ((!isNaN(phone))&&(phone.length==11)) {
				 $(".f-c form").attr("action","epMemberCenterAction!newDrawCommission.action");
				 $(".f-c form").submit();
			  } else {
			  $(".cashTrips").show();
	          $(".cashTrips").html("手机号码格式不对");
	          $(".cashTrips").fadeOut(3000);
			  }
            }
		 }else{
		  $(".cashTrips").show();
	      $(".cashTrips").html("金额不是数字");
	      $(".cashTrips").fadeOut(3000);
		 }
	   }else{
	      $(".cashTrips").show();
	      $(".cashTrips").html("不能为空");
	      $(".cashTrips").fadeOut(3000);
	   }
	 });
		
 });
</script>
<body class='edit_address bg-dcdcdc'>
<header class='clearfix close-bottom'>
		<a href="epMemberCenterAction.action"><img src="${imagePath}/add-left.png" alt="" id="close-bottom-img"></a>
		我的分销
</header>
<input type="hidden" class="tabNum" value="${tabNum}">
<input type="hidden" class="level" value="${level}">
<section class="e-ad">
    <section class="fenxiao">
        <ul class="clearfix">
            <li><a href="epMemberCenterAction!queryMemberFirstLevel.action">我的客户</a></li>
            <li><a href="epMemberCenterAction!queryMemberShareOrder.action">分销订单</a></li>
            <li><a href="epMemberCenterAction!queryMemberCommission.action">我的佣金</a></li>
        </ul>
    </section>
    
    <section class="fenxiao-content">
        <section class="f-c" id="blo">
        	<section class="fenxiao-tab">
                <ul class="clearfix">
                    <li><a href="epMemberCenterAction!queryMemberFirstLevel.action">一级</a></li>
                    <li><a href="epMemberCenterAction!queryMemberSecondLevel.action">二级</a></li>
                    <li><a href="epMemberCenterAction!queryMemberThirdLevel.action">三级</a></li>
                </ul>
            </section>
            <section class="tab-content">
                <section id="blo">
                <div class="fenxiao-scroll">
					<div class="fenxiao-scroll1 div-costomer">
                    <table align="center" class="table-costomer">
                        <tr>
                            <th>#</th>
                            <th>姓名</th>
                            <th>手机号码</th>
                            <th>加入时间</th>
                        </tr>
                      <!--   <c:forEach items="${memberMap}" var="members">
                        <c:if test="${not empty members.value}">
                        <c:forEach items="${members.value}" var="member">
                        <tr>
                            <td><img src="${imagePath}/nub.png" alt=""></td>
                            <td>${member.loginName}</td>
                            <td>${member.mobile}</td>
                            <td>${fn:substringBefore(member.addTime, " ")}</td>
                        </tr>
                        </c:forEach>
                        </c:if>
                       </c:forEach> -->
                      <c:forEach items="${members}" var="member" varStatus="status">
                        <tr>
                            <td><a href=""><img src="${imagePath}/nub.png" alt=""></a></td>
                            <td>${member.loginName}</td>
                            <td>${member.mobile}</td>
                            <td><fmt:formatDate value="${member.addTime}" pattern="yyyy-MM-dd" /></td>
                        </tr>
                       </c:forEach>
                    </table>
                    <c:if test="${not empty members}">                   
                   		 <c:if test="${fn:length(members) < 10}">
	                     <div class="more-costomer" style="line-height:3.8em">没有更多</div>
	                     </c:if>
	                     <c:if test="${fn:length(members) eq 10}">
	                     <div class="more-costomer" style="line-height:3.8em" onClick="moreCostomer(this)">更多</div>
	                     </c:if>
                    </c:if>
                    <c:if test="${empty members}">
                    <div style="margin-top: 45%">没有客户</div>
                    </c:if>
                    </div>
                   </div>
                    <p>*说明：以上数据是您加入分销后推荐的好友列表，该统计是根据你发送带有独有PID编码的链接，在您的好友访问
                    之后，系统会自动监测统计出来的数据。如有差异或疑问，请及时联系百丽春的客服人员。全国热线：${standbyList.standby2 }.</p>
                </section>
                <section>
                 <div class="fenxiao-scroll">
					<div class="fenxiao-scroll1 div-order">
                    <table align="center" class="table-order">
                        <tr>
                            <th>#&nbsp;客户</th>
                            <th>消费时间</th>
                            <th>金额</th>
                            <th>佣金</th>
                        </tr>
                       <!--  <c:forEach items="${payOrderMap}" var="payOrders" varStatus="status">
                        <c:if test="${not empty payOrders.value}">
                         <c:forEach items="${payOrders.value}" var="payOrder">
                        <tr>
                            <td>${payOrder.member.loginName }</td>
                            <td class="text-indent-5"><fmt:formatDate value="${payOrder.payTime}" pattern="yyyy-MM-dd" /></td>
                            <td class="text-indent-5">${payOrder.orderAmount}</td>
                            <td class="text-indent-5">${cashDouble}</td>
                        </tr>
                        </c:forEach>
                        </c:if>
                        </c:forEach> -->
                         <c:if test="${not empty payOrderVoList}">
                         <c:forEach items="${payOrderVoList}" var="payOrder">
                        <tr>
                            <td>${payOrder.name }</td>
                            <td class="text-indent-5"><fmt:formatDate value="${payOrder.payTime}" pattern="yyyy-MM-dd" /></td> 
                            <td class="text-indent-5">${payOrder.orderAmount}</td>
                            <td class="text-indent-5">${payOrder.orderAmount*payOrder.rate}</td>
                        </tr>
                        </c:forEach>
                        </c:if>
                    </table>
                    <c:if test="${not empty payOrderVoList}">
	                     <c:if test="${fn:length(payOrderVoList) < 10}">
	                     <div class="more-orderInfo" style="line-height:3.8em">没有更多</div>
	                     </c:if>
	                     <c:if test="${fn:length(payOrderVoList) eq 10}">
	                     <div class="more-orderInfo" style="line-height:3.8em" onClick="moreOrderInfo(this)">更多</div>
	                     </c:if>
                    </c:if>
                    <c:if test="${empty payOrderVoList}">
                    <div style="margin-top: 45%">没有分销订单</div>
                    </c:if>
                    </div>
                   </div>
                    <p>*说明：以上数据是您加入分销后推荐的好友列表，该统计是根据你发送带有独有PID编码的链接，在您的好友访问
                    之后，系统会自动监测统计出来的数据。如有差异或疑问，请及时联系百丽春的客服人员。全国热线：${standbyList.standby2 }.</p>
                </section>
                <section>
                <div class="fenxiao-scroll">
					<div class="fenxiao-scroll1 div-commissionInfo">
                    <table align="center" class="table-commissionInfo">
                        <tr>
                            <th></th>
                            <th>#&nbsp;时间</th>
                            <th class="text-center">佣金</th>
                            <th>备注</th>
                            <th></th>
                        </tr>
                        <c:forEach items="${commissionInfos}" var="commissionInfo" varStatus="status">
                        <tr>
                            <td></td>
                            <td><fmt:formatDate value="${commissionInfo.addTime}" pattern="yyyy-MM-dd" /></td>
                            <td class="text-center">${commissionInfo.commission}</td>
                            <td class="text-indent-5">${commissionInfo.commissionComments}</td>
                            <td></td>
                        </tr>
                        </c:forEach>
                    </table>
                    <c:if test="${not empty commissionInfos}">
	                    <c:if test="${fn:length(commissionInfos) < 10}">
	                    <div class="more-commissionInfo" style="line-height:3.8em">没有更多</div>
	                    </c:if>
	                    <c:if test="${fn:length(commissionInfos) eq 10}">
	                    <div class="more-commissionInfo" style="line-height:3.8em" onClick="moreCommissionInfo(this)">更多</div>
	                    </c:if>
                    </c:if>
                    <c:if test="${empty commissionInfos}">
                    <div style="margin-top: 45%">没有佣金信息</div>
                    </c:if>
                    </div>
                   </div>
                    <p>*说明：以上数据是您加入分销后推荐的好友列表，该统计是根据你发送带有独有PID编码的链接，在您的好友访问
                    之后，系统会自动监测统计出来的数据。如有差异或疑问，请及时联系百丽春的客服人员。全国热线：${standbyList.standby2 }.</p>
                </section>
            </section>
        </section>
        
        
    </section>
</section>
            <section class="alert-share">
                <img src="${imagePath}/share.png">
                <p class="share-content">点击右上角微信朋友圈分享哦!</p>
            </section>
<section class="cashTrips" style="position: fixed;top: 10.3%;background: #e7e7e7;color:red;text-align: center;width: 100%;display: none;z-index: 9999"></section>
<script type="text/javascript">	 

	 //我的客户更多
	 var CostomerNum=1;
	 function moreCostomer(obj){
	    $(this).html("加载中...");
	    ++CostomerNum;
	   ajaxNoPayOrder(CostomerNum);
	 }
	 
	 //我的分销订单更多
	 var orderNum=1;
	 function moreOrderInfo(obj){
	    $(this).html("加载中...");
	    ++orderNum;
	   ajaxOrder(orderNum);
	 }
	 
	 //我的佣金更多
	 var commissionNum=1;
	 function moreCommissionInfo(obj){
	    $(this).html("加载中...");
	    ++commissionNum;
	   ajaxCommission(commissionNum);
	 }
	 
    //我的分销商品更多
	 var productNum=1;
	 function moreProduct(obj){
	    $(this).html("加载中...");
	    ++productNum;
	   ajaxPayProduct(productNum);
	 }


	 //我的客户ajax加载（没完成）
	 function ajaxNoPayOrder(pageStart){
				 $.ajax({ 
			      	url:'${ctx}/ajaxMemberCenterAction!queryMemberCosTomer.action', 
			      	type:'post', 
			      	dataType: 'json',
			      	data:{
			      		"pageNo":pageStart,
			      		"pageSize":10,
			      		"level":${level}
			      	},
					success: function(json) {
					$(".more-costomer").empty();
					var str="";
					if(json.members!=""&&null!=json.members){
					 $.each(json.members, function(index, member) {
					   str+="<tr><td><a href=''><img src='${imagePath}/nub.png' alt=''></a></td><td>"+member.loginName+"</td><td>"+member.mobile+"</td><td>"+(member.addTime).substring(0,10)+"</td></tr>";
                         });
                         $(".table-costomer").append(str);
                         if (json.members.length<10) {
							 var more=" <div class='more-costomer' style='line-height:3.8em'>没有更多</div>";
							 $(".div-costomer").append(more);
						  } else {
							 var more=" <div class='more-costomer' style='line-height:3.8em' onClick=moreCostomer(this);>更多</div>";
							 $(".div-costomer").append(more);
						  }
				       }else{
				       var more=" <div class='more-costomer' style='line-height:3.8em'>没有更多</div>";
                         $(".div-costomer").append(more);
				       }
					 },
					 error: function(XMLHttpRequest, textStatus, errorThrown) {
					      openwaring("网络出现异常");
					   }
				});	
		}
		
			 //我的分销的订单ajax加载                          （没完成.....）
	    function ajaxOrder(pageStart){
				 $.ajax({ 
			      	url:'${ctx}/ajaxMemberCenterAction!queryMemberShareOrder.action', 
			      	type:'post', 
			      	dataType: 'json',
			      	data:{
			      		"pageNo":pageStart,
			      		"pageSize":10,
			      	},
					success: function(json) {
					$(".more-orderInfo").empty();
					 var str="";
					if(json.payOrders1!=""&&null!=json.payOrders1){
					 $.each(json.payOrders1, function(index, payOrder) {
					str+="<tr><td>"+payOrder.member.loginName+"</td><td class='text-indent-5'>"+(payOrder.payTime).substring(0,10)+"</td><td class='text-indent-5'>"+payOrder.orderAmount+"</td><td class='text-indent-5'>"+(json.cashDouble)*(payOrder.orderAmount)+"</td></tr>";
					});
					$(".table-order").append(str);
					    if (json.payOrders1.length<10) {
							 var more=" <div class='more-orderInfo' style='line-height:3.8em'>没有更多</div>";
							 $(".div-order").append(more);
						  } else {
							 var more=" <div class='more-orderInfo' style='line-height:3.8em' onClick=moreOrderInfo(this);>更多</div>";
							 $(".div-order").append(more);
						  }				                             
				       }else{
				       var more=" <div class='more-orderInfo' style='line-height:3.8em'>没有更多</div>";
                         $(".div-order").append(more);
				       }
				  },
					 error: function(XMLHttpRequest, textStatus, errorThrown) {
					      openwaring("网络出现异常");
					   }
				});	
				}
				
		 //我的佣金ajax加载
	    function ajaxCommission(pageStart){
				 $.ajax({ 
			      	url:'${ctx}/ajaxMemberCenterAction!queryMemberCommission.action', 
			      	type:'post', 
			      	dataType: 'json',
			      	data:{
			      		"pageNo":pageStart,
			      		"pageSize":10,
			      	},
					success: function(json) {
					$(".more-commissionInfo").empty();
					var str="";
					if(json.commissionInfos!=""&&null!=json.commissionInfos){
					 $.each(json.commissionInfos, function(index, commissionInfo) {
					  str+="<tr><td></td><td>"+(commissionInfo.addTime).substring(0,10)+"</td><td class='text-center'>"+commissionInfo.commission+"</td><td class='text-indent-5'>"+commissionInfo.commissionComments+"</td><td></td></tr>";
					});
					$(".table-commissionInfo").append(str);
				    	 if (json.commissionInfos.length<10) {
							 var more=" <div class='more-commissionInfo' style='line-height:3.8em'>没有更多</div>";
							 $(".div-commissionInfo").append(more);
						  } else {
							 var more=" <div class='more-commissionInfo' style='line-height:3.8em' onClick=moreCommissionInfo(this);>更多</div>";
							  $(".div-commissionInfo").append(more);
						  }		                       
				    }else{
				       var more=" <div class='more-commissionInfo' style='line-height:3.8em'>没有更多</div>";
                         $(".div-commissionInfo").append(more);
				       }
				  },
					 error: function(XMLHttpRequest, textStatus, errorThrown) {
					      openwaring("网络出现异常");
					   }
				});	
				}
				
			 //我的分销的产品ajax加载
	    function ajaxPayProduct(pageStart){
				 $.ajax({ 
			      	url:'${ctx}/ajaxMemberCenterAction!queryMemberShareProduct.action', 
			      	type:'post', 
			      	dataType: 'json',
			      	data:{
			      		"pageNo":pageStart,
			      		"pageSize":10,
			      	},
					success: function(json) {
					$(".more-product").empty();
					var str="";
					if(json.orderInfos1!=""&&null!=json.orderInfos1){
					 var tripStr="<label style='position: absolute;left:10px;'>"+((pageStart-1)*10+1)+"-"+((pageStart)*10)+"条</label>"
					 $(".div-product").append(tripStr);
					 $.each(json.orderInfos1, function(index, shareProduct) {
					  str+="<section class='f-c-pro clearfix'><img src='${uploadPath}/"+shareProduct.product.imgurl+"' alt='' title='''><span><h3>"+shareProduct.product.title+"</h3>";
                	  str+="<p>价格￥"+shareProduct.price+"<i>销售量："+shareProduct.amount+"</i></p><p>每单佣金："+shareProduct.amount*(json.cashDouble)+"</p>";
                	  str+="</span><span><a href='#'><img src='${imagePath}/advance.png' alt=''></a></span><span><p><i>被分享："+shareProduct.product.shareTime+"次</i><i class='share'>分享</i></p></span></section>";					
					});
					 $(".div-product").append(str);
					  if (json.orderInfos1.length<10) {
							  var more=" <div class='more-product' style='line-height:3.8em'>没有更多</div>";
							   $(".div-product").append(more);
						  } else {
							 var more=" <div class='more-product' style='line-height:3.8em' onClick=moreProduct(this);>更多</div>";
							  $(".div-product").append(more);
						  }	  
				    }else{
				       var more=" <div class='more-product' style='line-height:3.8em'>没有更多</div>";
                         $(".div-product").append(more);
				       }
				  },
					 error: function(XMLHttpRequest, textStatus, errorThrown) {
					      openwaring("网络出现异常");
					   }
				});	
				}

</script>
<%@ include file="/jsps/modules/copyRight.jsp"%>

