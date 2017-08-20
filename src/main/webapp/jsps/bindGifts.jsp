<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/jsps/modules/top.jsp"%>
<script type="text/javascript">
 //我的佣金更多
	 var giftsNum=1;
	 function moreGifts(obj){
	    $(this).html("加载中...");
	    ++giftsNum;
	   ajaxGiveGifts(giftsNum);
	 }


	 //ajax加载
	 function ajaxGiveGifts(pageStart){
				 $.ajax({ 
			      	url:'${ctx}/ajaxActivityAction!toBingGift.action', 
			      	type:'post', 
			      	dataType: 'json',
			      	data:{
			      		"pageNo":pageStart,
			      		"pageSize":10,
			      	},
					success: function(json) {
					$(".more-gifts").empty();
					var str="";
					if(json.productColumns!=""&&null!=json.productColumns){
					 $.each(json.productColumns, function(index, productColumn) {
					   str+="<section class='g-g-con'><section><img src='${uploadPath}/"+productColumn.mcProduct.imgurl+"></section><section class='g-g-c-r'>";
					   str+="<p class='g-g-1'>"+productColumn.mcProduct.title+"</p><p class='g-g-2'>"+productColumn.mcProduct.description+"</p>";
					   str+="</section><a class='g-g-a' href='epActivityAction!goDrawGift.action?productColumn.mcProduct.id='"+productColumn.mcProduct.description+">领取礼品</a></section>";
                         });
                         $(".give-gifts-content").append(str);
                          var more=" <section class='more-gifts' style='line-height:4.8em' onClick=moreGifts(this);>更多</section>";
                          $(".give-gifts-content").append(more);
				       }else{
				       var more=" <section class='more-gifts' style='line-height:4.8em'>没有更多</section>";
                         $(".give-gifts-content").append(more);
				       }
					 },
					 error: function(XMLHttpRequest, textStatus, errorThrown) {
					      openwaring("网络出现异常");
					   }
				});	
		}
</script>
	<body class="s_p_body give-gifts">
		<!-- header -->
		<header class='clearfix'>
			<a href="epIndexAction.action"><img src="${imagePath}/logo.png" alt="logo"></a>
		</header> 
		<!-- header end-->
		<!-- content -->
		<section class="give-gifts-content">
				<!-- 单款送礼产品 -->
				 <c:forEach items="${productColumns}" var="productColumn" varStatus="status">
				<section class="g-g-con">
						<section><img src="${uploadPath}/${productColumn.mcProduct.imgurl}"></section>
						<section class="g-g-c-r">
								<p class="g-g-1">${productColumn.mcProduct.title}</p>
								<p class="g-g-2">${productColumn.mcProduct.description}</p>
						</section>
						<a class="g-g-a" href="epActivityAction!goDrawGift.action?mcProductInfo.id=${productColumn.mcProduct.id}">领取礼品</a>
				</section>
				</c:forEach>
				<section class="more-gifts" style="line-height:4.8em" onClick="moreGifts(this)">更多</section>
				<!-- 单款送礼产品 end -->

		</section>
		<!-- content end -->
		<!-- footer -->
<%@ include file="/jsps/modules/copyRight.jsp"%>
