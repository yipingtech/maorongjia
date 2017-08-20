<%@ page contentType="text/html;charset=utf-8"%>

<%@ include file="/jsps/modules/top.jsp"%>
<body>
<header class="header_top clearfix">
    <a href="epMemberCenterAction.action"><img src="${imagePath}/left_arrow.png"></a>
    <p>提款记录</p>
</header>
<script type="text/javascript">
      var pageStart=1;
     function more(){
    	 ++pageStart;
 		$.ajax({ 
		      	url:'${ctx}/epMemberCenterAction!queryMoreRecord.action', 
		      	type:'post', 
		      	dataType:"html",
		      	data:{
		      		"pageNo":pageStart,
		      		"pageSize":5,
		      	},
				success: function(data) {
				     $("#container").append(data);
			  },
				 error: function(XMLHttpRequest, textStatus, errorThrown) {
				      openwaring("网络出现异常");
				   }
			});	
     }
</script>
<!--container-->
<section id="container">
    <c:forEach  var="bean" items="${cashInfos }">
         <div class="record_box">
        <div class="record_time clearfix">
            <p class="left">编号：${bean.id }</p><p class="right"><fmt:formatDate value="${bean.drawTime}" pattern="yyyy-MM-dd HH:mm:ss" /></p>
        </div>
        <div class="record_cont">
            <p>提款到银行卡：${bean.bankCard.bankCardFlag }    **************<c:out value="${fn:substring(bean.bankCard.bankCardNum,fn:length(bean.bankCard.bankCardNum)-4, fn:length(bean.bankCard.bankCardNum))}" /></p>
            <p>提款金额：${bean.drawAmount }元</p>
        </div>
        <div class="record_check clearfix">
            <c:if test="${empty bean.dealTime}">
               <div class="check_box right orange">正在审核</div>
            </c:if>
            <c:if test="${not empty bean.dealTime && bean.drawComments eq '提现成功'}">
               <div class="check_box right green">提现成功</div>
            </c:if>
            <c:if test="${not empty bean.dealTime && bean.drawComments eq '0'}">
               <a style="font-size:5px;text-align:left;">银行卡冻结或者信息不对</a><div class="check_box right red">提现失败</div>
            </c:if>
        </div>
    </div>
    </c:forEach>
    
    <a class="see_record" onclick="more()" id="findMoreRecord"><img src="${imagePath}/time_icon_03.png">查看审核历史记录</a>
</section>
<!--end of container-->
</body>
</html>