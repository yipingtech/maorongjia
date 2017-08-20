<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/jsps/modules/top.jsp"%>
  
  <body class="s_p_body">
		<!-- header -->
		<c:if test="${empty count}">
		<header class='clearfix'>
			<a href="epIndexAction.htm"><img src="${imagePath}/logo.png" alt="logo"></a>
		</header>
		</c:if>
		<c:if test="${not empty count}">
			<header class='clearfix close-bottom'>
				<a href="javascript:history.back();"><img src="${imagePath}/add-left.png" alt="" id="close-bottom-img"></a>
				我要送礼
		</header>
		</c:if>
		<!-- header end -->
		<!-- content -->
		<section id="s-p-1" style="margin-top: 101px;">
			<img src="${uploadPath}/${productInfo.imgurl}" style="height: 320px;">
		</section>
		<section id="s-p-2" class="s-p-2 border-bottom">
			${productInfo.title}<br>
			<span>价格：</span>
			<span class="succeed-o" class="s-p-2-span-color">
			<fmt:formatNumber value="${productInfo.shopPrice}" type="currency"/>
			</span><span class="s-p-2-span-color">RMB</span>
			<a class="s-p-2-a-first-child">优惠卷</a>
			<a class="s-p-2-a-last-child">红包</a>
			<span>库存：${productInfo.repertory}件</span>
		</section>
		<section class="s-p-6">
			此物非常迷人，快加入我的分销大计！
			<a href="epMemberCenterAction!queryMemberCosTomer.action">我要分销</a>
		</section>
		<section id="s-p-3" class="border-t-b ">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
			  <tr>
				<td width="33%" class="border-right"><span id="s-p-01" class="s-p-o">宝贝详情</span></td>
				<td class="border-right"><span id="s-p-02">评论 （${countEvaluate}）</span></td>
				<td width="33%"><span>已销10笔</span></td>
			  </tr>
			</table>
		</section>
		<!-- 宝贝详情 -->
		<section class="color-black s-p-4 s-p-4-4">
				${productInfo.content}
		</section>
		<!-- 宝贝详情 end -->
		<!-- 评论 -->
		<section id="s-p-5" class="s-p-5">
		<c:if test="${not empty evaluates}">
			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="table-evaluates">
             <c:forEach items="${evaluates}" var="evaluate">
			 <tr class="border-bottom">
				<td width="20%"><img src="${imagePath}/s-p-tp04.png"></td>
				<td align="left">${evaluate.member.loginName}<br><span class="s-p-5-2">${evaluate.evaluate}</span></td>
				<td valign="top" class="s-p-5-3">${fn:substringBefore(evaluate.addTime, " ")}
			  </tr>
			   
			  </c:forEach>
			</table>
			<div class='more-evaluate' style='line-height:3.8em' onClick="moreEvaluate(this);">更多</div>
			</c:if>
			  <c:if test="${empty evaluates}"><p style="margin-top: 30px;">本商品还没有评论</p></c:if>
		</section>
		<!-- 评论 end -->
		<!-- 购买弹出 -->
		<form action="" method="post" id="productForm" name="productForm">
		<section class="shopping_pa_hide"  id="shopping-cart-list">
				<section class="shopping_shopping">
						<section><img src="${uploadPath}/${productInfo.imgurl}"></section>
						<section>
							<input type="hidden" id="productInfoId" name="productId" value="${productInfo.id}" />
								<p>${productInfo.title}</p>
								<p>${productInfo.description}</p>
								<p><fmt:formatNumber value="${productInfo.shopPrice}" type="currency"/></p>
						</section>
						<img src="${imagePath}/address_tp01.png" class="shopping_shopping_img">
				</section>
				<section class="shopping_shopping01">
				<input type="hidden" name="attrCount" id="attrCount" value="${fn:length(attrMap)}" />
				<c:forEach items="${attrMap}" var="bean" varStatus="attrNum">
						<p>请选择${bean.key.attrName}：</p><br>
						<p class="s_s_p2">
							<c:forEach items="${bean.value}" var="productAttr" varStatus="status">
							<c:if test="${status.first}">
								<input type="hidden" name="proAttrId${attrNum.count}" id="proAttrId${attrNum.count}" value="${productAttr.id}" />
							</c:if>
							<span id="${productAttr.id}" <c:if test="${status.first}">class='s_s_p2_color'</c:if> >${productAttr.attrValue}</span>
							</c:forEach>
						</p><br>
				</c:forEach>
				
						<input type="hidden" id="buyType" name="buyType" value="buyNow" />
						<p>请选择数量：</p>
						<p class="s_s01_add">
							<img src="${imagePath}/s_s_jian.png" class="s_s01_add_img btn-reduce">
							<input type="text" id="amount" name="amount" class="s-s-input buy-num" value="1">
							<img src="${imagePath}/s_s_jia.png" class="btn-add">
							<span id="repertory">${productInfo.repertory}件库存</span>
							<input type="hidden" id="proReper" value="${productInfo.repertory}"/> 
							<input type="text" class="max_lq jian-icon" value="100" style="display:none">
						</p>
						<p class="tuikuan-a submitProduct"><a href="javascript:void(0)">确认</a></p>
				</section>
		</section>
		</form>
		<section class="bg"></section>
		<!-- 购买弹出 end -->
		<!-- content end -->
		<section id="s-p-footer">
		<c:if test="${empty count}">
			<a href="epShoppingCarAction.htm?pageType=showCar"><img src="${imagePath}/s-p-tp05.png">&nbsp;&nbsp;&nbsp;&nbsp;</a>
			<a href="javascript:void(0)" class="shop"><img src="${imagePath}/s-p-tp02.png"></a>
			<a href="javascript:void(0)" class="buy"><img src="${imagePath}/s-p-tp03.png"></a>
		</c:if>
		<c:if test="${not empty count}">
			<a href="javascript:void(0)" class="shop" id="gifts-footer"><img src="${imagePath}/gifts_tb.png"></a>
		</c:if>
		</section>
		<!-- 添加购物车成功弹出 -->
		<script>
				$('.submitProduct').on('click', function(){
					var repertory = $("#proReper").val()-$("#amount").val();
					if(repertory>0){
						var buyType = $('#buyType').val();
						var attrCount = $('#attrCount').val();
						var proAttrIds="";
						for(var i = 1; i <= attrCount; i++){
							proAttrIds = proAttrIds + $('#proAttrId'+i).val() + ",";
						}
						proAttrIds=proAttrIds.substring(0,proAttrIds.length-1);
						if(buyType=="buyNow"){
							$('#productForm').prop("action","epShoppingCarAction.htm?pageType=addProductToShoppingCar&proAttrIds="+proAttrIds);
							$('#productForm').submit();
						}else if(buyType=="addCar"){
							 $.ajax({ 
							      	url:'${ctx}/ajaxepShoppingCarAction!addProductToShoppingCar.htm', 
							      	type:'post', 
							      	dataType: 'json',
							      	data:{
							      		"proAttrIds":proAttrIds,
							      		"amount":$("#amount").val(),
							      		"productId":$("#productInfoId").val()
							      	},
									success: function(json) {
										//页面层
										layer.open({
											shadeClose: false,
											content: '<section class="buy-layer"><p><span>您已成功加入购物车</span><a href="javascript:void(0)" onclick="evaluate_close()"><img src="${imagePath}/evaluate_su.png" id="pagebtn"></a></p></section>'
										});
									 },
									 error: function(XMLHttpRequest, textStatus, errorThrown) {
									 		openwaring("网络出现异常");
									  }
								}); 
								//document.form1.action = "epShoppingCarAction.htm?pageType=addProductToShoppingCar";
								//document.form1.submit();
						}
					}else{
						layer.open({
							shadeClose: false,
							content: '<section class="buy-layer"><p><span>库存不足</span><a href="javascript:void(0)" onclick="evaluate_close()"><img src="${imagePath}/evaluate_su.png" id="pagebtn"></a></p></section>'
						});
					}
					
				});
				
				$('.s_s_p2 span').on('click', function(){
					$(this).addClass('s_s_p2_color').siblings().removeClass('s_s_p2_color');
					$(this).siblings().first().val($(this).prop("id"));
					var attrCount = $('#attrCount').val();
					var proAttrIds="";
					for(var i = 1; i <= attrCount; i++){
						proAttrIds = proAttrIds + $('#proAttrId'+i).val() + ",";
					}
					proAttrIds=proAttrIds.substring(0,proAttrIds.length-1);
					$.ajax({ 
				      	url:'${ctx}/ajaxepProductActionFindRepertory!findRepertory.htm', 
				      	type:'post', 
				      	dataType: 'json',
				      	data:{
				      		"proAttrIds":proAttrIds,
				      		"productId":$("#productInfoId").val()
				      	},
						success: function(json) {
					      	$("#repertory").html(json+"件库存");
					      	$("#proReper").val(json);
						 },
						 error: function(XMLHttpRequest, textStatus, errorThrown) {
						      openwaring("网络出现异常");
						   }
					});
				});	
				
				function evaluate_close(){
					layer.closeAll();
				} 
				
			
	
	//点击加载更多评论
	var evaluateNum=1;
	function moreEvaluate(){
	$(this).html("加载中...");
	    ++evaluateNum;
	    ajaxEvaluateText(evaluateNum);
	}
		
				
	//ajax加载商品评论
	function ajaxEvaluateText(pageStart){
				 $.ajax({ 
			      	url:'${ctx}/ajaxepProductActionFindRepertory!queryEvaluates.action', 
			      	type:'post', 
			      	dataType: 'json',
			      	data:{
			      		"pageNo":pageStart,
			      		"pageSize":10,
			      		"productId":$("#productInfoId").val()
			      	},
					success: function(json) {
					$(".more-evaluate").empty();
					var str="";
					if(json!=""&&null!=json){
					 $.each(json, function(index, evaluateText) {
					 alert(evaluateText.member.loginName);
					     str+="<tr class='border-bottom'><td width='20%'><img src='${imagePath}/s-p-tp04.png'></td><td align='left'>"+evaluateText.member.loginName+"<br><span class='s-p-5-2'>"+evaluateText.evaluate+"</span></td><td valign='top' class='s-p-5-3'>"+(evaluateText.addTime).substring(0,10)+"</td></tr>";
                       });
                          $(".table-evaluates").append(str);
                         var more="<div class='more-evaluate' style='line-height:3.8em' onClick=moreEvaluate(this);>更多</div>";
                          $(".s-p-5").append(more);
				       }else{
				         var more=" <div class='more-evaluate' style='line-height:3.8em'>没有更多</div>";
                         $(".s-p-5").append(more);
				       }
					 },
					 error: function(XMLHttpRequest, textStatus, errorThrown) {
					      openwaring("网络出现异常");
					   }
				});	
		}			
				
				
		</script>
		<!-- 添加购物车成功弹出 end -->
		<script>
				$(document).ready(function(e){
					  $("#shopping-cart-list").cartCalculate({
						  addCtrl : '.btn-add',   //+号控制类
						  reduceCtrl : '.btn-reduce',  //-号控制类
						  buyCount : '.buy-num',  //输入框控制类
						  unitPrice : '.one_price',  //单条数据单价控制类
						  unitCount : '.single_lq',  //单条数据小计控制类
						  totalCount : '#total_lq'  //总数据控制类
						});
				});
		</script>
	</body>
	<script src="${jsPath}/jquery.cartCalculate.js"></script>
	<script src="${jsPath}/style.js"></script>
	<script src="${jsPath}/layer.m/layer.m.js"></script>
</html>
