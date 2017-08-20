<%@ page contentType="text/html;charset=utf-8"%>

<%@ include file="/jsps/modules/top.jsp"%>
<body class='edit_address bg-dcdcdc'>
<header class='clearfix close-bottom'>
		<a href="javascript:history.back();"><img src="${imagePath}/add-left.png" alt="" id="close-bottom-img"></a>
		我的代理
</header>
<input type="hidden" class="tabNum" value="${tabNum}">
<input type="hidden" class="level" value="${level}">
<section class="e-ad">
	<section class="myAgentul">
        <ul class="clearfix">
            <li><a href="javascrip:void(0)">总人数(${teamNum})</a></li>
            <li><a href="javascrip:void(0)">总业绩(<fmt:formatNumber value="${sum}" type="currency" pattern="￥"/>)</a></li>
            <li style="border-right:none"><a href="javascrip:void(0)">总提成(<fmt:formatNumber value="${bonus}" type="currency" pattern="￥"/>)</a></li>
        </ul>
    </section>
    <section class="fenxiao-content">
        <section class="f-c" id="blo">
            <section class="tab-content">
                <section id="blo">
                 <div class="fenxiao-scroll">
					<div class="fenxiao-scroll1 div-order">
                    <table align="center" class="table-order">
                        <tr>
                            <th>#&nbsp;客户</th>
                            <th>交易时间</th>
                            <th>金额</th>
                            <th>提成</th>
                        </tr>
                         <c:if test="${not empty payOrders1}">
	                         <c:forEach items="${payOrders1}" var="payOrder">
		                        <tr>
		                            <td>
		                               <c:if test="${payOrder.member.memberFather.id == member.id ||  
		                                             payOrder.member.memberFather.memberFather.id == member.id ||
		                                             payOrder.member.memberFather.memberFather.memberFather.id == member.id}">
		                                   <img src="${payOrder.member.imagePath }" />
		                               </c:if>
		                                 ${payOrder.member.nickname}
		       	                      </td>
		                            <td class="text-indent-5"><fmt:formatDate value="${payOrder.payTime}" pattern="yyyy-MM-dd" /></td>
		                            <td class="text-indent-5"><fmt:formatNumber value="${payOrder.orderAmount}" type="currency" pattern="#"/></td>
		                            <td class="text-indent-5"><fmt:formatNumber value="${payOrder.orderAmount*rateSecond/100}" type="currency" pattern="#0.00"/></td>
		                        </tr>
	                        </c:forEach>
                        </c:if>
                    </table>
                    <c:if test="${not empty payOrders1}">
	                     <c:if test="${fn:length(payOrders1) < 10}">
	                     <div class="more-orderInfo" style="line-height:3.8em">没有更多</div>
	                     </c:if>
	                     <c:if test="${fn:length(payOrders1) eq 10}">
	                     <div class="more-orderInfo" style="line-height:3.8em" onClick="moreOrderInfo(this)">更多</div>
	                     </c:if>
                    </c:if>
                    <c:if test="${empty payOrders1}">
                    <div style="margin-top: 45%">没有代理</div>
                    </c:if>
                    </div>
                   </div>
                    <p>*说明：以上数据是您加入分销后推荐的好友列表，该统计是根据你发送带有独有PID编码的链接，在您的好友访问
                    之后，系统会自动监测统计出来的数据。如有差异或疑问，请及时联系百丽春的客服人员。全国热线：${standbyList.standby2 }.</p>
                </section>
                
            </section>
        </section>
        <script type="text/javascript">
          //我的分销商品更多
		   	 var productNum=0;
		   	 moreOrderInfo(productNum);
		   	 function moreOrderInfo(obj){
		   	    $(this).html("加载中...");
		   	    ++productNum;
		   	    ajaxOrder(productNum);
		   	 }
		   	 function ajaxOrder(pageStart){
				 $.ajax({ 
			      	url:'${ctx}/epMemberCenterAction!queryMyAgentMore.action', 
			      	type:'post', 
			      	dataType:"html",
			      	data:{
			      		"pageNo":pageStart,
			      		"pageSize":10,
			      	},
					success: function(data) {
					     $("#more").append(data);
				  },
					 error: function(XMLHttpRequest, textStatus, errorThrown) {
					      openwaring("网络出现异常");
					   }
				});	
				}
        </script>
        
    </section>
</section>
            <section class="alert-share">
                <img src="${imagePath}/share.png">
                <p class="share-content">点击右上角微信朋友圈分享哦!</p>
            </section>
<section class="cashTrips" style="position: fixed;top: 10.3%;background: #e7e7e7;color:red;text-align: center;width: 100%;display: none;z-index: 9999"></section>
<%@ include file="/jsps/modules/copyRight.jsp"%>
