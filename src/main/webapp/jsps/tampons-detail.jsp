
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/jsps/modules/top.jsp"%>
 <c:if test="${not empty configJson}">
	<script type="text/javascript" src="${jsPath}/jquery-2.1.4.js"></script>
	<script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.1.0.js"></script>
	<script>
		$(function(){
			var configJson = ${configJson};
			var fatherId = ${fatherId};
			if(null == fatherId){
				fatherId = ${member.id}
			}
			var url  = window.location.href;
			wx.config(configJson);
			wx.ready(function() {
				wx.onMenuShareAppMessage({
					title: "${productInfo.title}",
					link: url,
					imgUrl: "http://test5.messcat.com${uploadPath}/${productInfo.imgurl}",
					desc: "${productInfo.keywords}",
					trigger: function (res) {
						// alert('用户点击发送给朋友');
					},
					success: function (res) {
						//alert('已分享');
					},
					cancel: function (res) {
						//alert('已取消');
					},
					fail: function (res) {
						//alert(JSON.stringify(res));
					}
				});
				
				 wx.onMenuShareTimeline({
					 title: "${productInfo.title}",
						link: url,
						imgUrl: "http://test5.messcat.com${uploadPath}/${productInfo.imgurl}",
						trigger: function (res) {
							//alert('用户点击发送给朋友圈');
						},
						success: function (res) {
							//alert('已分享');
						},
						cancel: function (res) {
							//alert('已取消');
						},
						fail: function (res) {
							//alert(JSON.stringify(res));
						}
				}); 
			});
			wx.error(function (res) {
             //alert('wx.error: '+JSON.stringify(res));
			});

		});
	</script>
</c:if>
    
  <body class="s_p_body">
		<!-- header -->
		<!-- header end -->
		<!-- content -->
	    <section id="s-p-1" class="margin-0">
            <img src="${uploadPath}/${productInfo.imgurl}">
        </section>
		<section id="s-p-2" class="s-p-2">
		     <div>${mtag:cutString(productInfo.title,32,'...')}</div>
		     <%-- <c:if test="${sessionScope.distributor eq '0'}"> 
		        <span class="price"><span>￥</span>${productInfo.shopPrice}</span>
		     </c:if> 
		     <c:if test="${sessionScope.member.distributor eq '1'}"> 
		     	 <span class="price"><span>￥</span>${productInfo.promotePrice}</span> 
		     </c:if>  --%>
		     <span class="price partic-com-price">${productInfo.shopPrice}</span> 
		     <c:if test="${not empty productInfo.marketPrice or productInfo.marketPrice eq ''}"> 
		   		 <del class="price" style="margin-left:30px;"><span>市场价：￥</span>${productInfo.marketPrice}</del>
		     </c:if>
			<!-- <a class="s-p-2-a-first-child">优惠卷</a>
			<a class="s-p-2-a-last-child">红包</a> -->
			<%-- <span>库存：${productInfo.repertory}件</span> --%>
			<p style="color:#999999;">满199元包邮</p>
		</section>
		<section class="choose_detail">
		
		
		<form action="" method="post" id="productForm" name="productForm">
		<c:forEach items="${attrMap}" var="bean" varStatus="attrNum">
		    <div class="choose_set s_s_p2" >请选择${bean.key.attrName}：
		        <c:forEach items="${bean.value}" var="productAttr" varStatus="status">
					<c:if test="${status.first}">
<%--						<input type="hidden" name="proAttrId${attrNum.count}" id="proAttrId${attrNum.count}" value="${productAttr.id}" />--%>
						<input type="hidden" name="proAttrId${attrNum.count}" id="proAttrId${attrNum.count}" />
					</c:if>
					<span id="${productAttr.id}" class="statusIndex${status.index }" >${productAttr.attrValue}</span>
				</c:forEach>
		    </div>
		    <%--<c:if test="${status.last}">class='s_s_p2_color'</c:if> 
		--%></c:forEach>
		    <div class="shop_count clearfix" >
		         <input type="hidden" id="buyType" name="buyType" value="buyNow" />
		        <div class="left">请选择数量：</div>
		        <div class="add_product left">
		            <span class="btn-reduce" >-</span>
		           	   <input type="hidden" id="buy-price" name="price" value="${productInfo.shopPrice}"/> 
		               <input type="text" id="amount2" name="amount" class="s-s-input buy-num" value="1" 
		               onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')">
		            <span class="btn-add">+</span>
		            <input type="text" class="max_lq jian-icon" value="100" style="display:none">
		        </div>
		        <div class="repertory left" id="repertory">
		        	（库存${productInfo.repertory}件）
		        </div>
		        <input type="hidden" id="proReper" value="${repertory}"/> 
				<input type="text" class="max_lq jian-icon" value="100" style="display:none">
		    </div>
		    
		       <section class="shopping_shopping" style="display:none;">
						<section><img src="${uploadPath}/${productInfo.imgurl}"></section>
						<section>
							<input type="hidden" id="productInfoId" name="productId" value="${productInfo.id}" />
								<p>${productInfo.title}</p>
								<p>${productInfo.description}</p>
								<p><fmt:formatNumber value="${productInfo.shopPrice}" type="currency"/></p>
						</section>
						<img src="${imagePath}/address_tp01.png" class="shopping_shopping_img">
				</section>

       </form>
       <script type="text/javascript">
            $(".btn-reduce").click(function(){
            	var count= $("#amount2").val();
            	if(count>=2){
            		count = parseInt(count)-1;
                	$("#amount2").val(count);
            	}
            	
            })
            $(".btn-add").click(function(){
            	var count= $("#amount2").val();
            		count = parseInt(count)+1;
                	$("#amount2").val(count);
            })
       </script>    
                   
                   
       </section>
		<!-- <section class="s-p-6">
			此物非常迷人，快加入我的分销大计！
			<a class="share" >我要分销</a>
		</section> -->
		<section id="s-p-3" class="border-t-b ">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
			  <tr>
				<td width="33%"><span id="s-p-01" class="s-p-o">商品详情</span></td>
				<%-- <td class="border-right"><span id="s-p-02">评论 （${countEvaluate}）</span></td>
				<td width="33%"><span>已销10笔</span></td> --%>
			  </tr>
			</table>
		</section>
		<!-- 宝贝详情 -->
		<section class="color-black s-p-4 s-p-4-4" style="display: block; padding-left:15px; padding-right:15px;">
		        ${productInfo.content}
				${enterpriseColumn.contents }
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
				<td valign="top" class="s-p-5-3">
				${fn:substringBefore(evaluate.addTime, " ")}
				</td>
			  </tr>
			   
	    </c:forEach>
			</table>
			<div class='more-evaluate' style='line-height:3.8em' onClick="moreEvaluate(this);">更多</div>
			</c:if>
			  <c:if test="${empty evaluates}"><p style="margin-top: 30px;">本商品还没有评论</p></c:if>
		</section>
		<!-- 评论 end -->
		<!-- 购买弹出 -->
		<form action="" method="post" id="productForm2" name="productForm">
		<section class="shopping_pa_hide"  id="shopping-cart-list">
				<%-- <section class="shopping_shopping">
						<section><img src="${uploadPath}/${productInfo.imgurl}"></section>
						<section>
							<input type="hidden" id="productInfoId" name="productId" value="${productInfo.id}" />
								<p>${productInfo.title}</p>
						</section>
						<img src="${imagePath}/address_tp01.png" class="shopping_shopping_img">
				</section> --%>
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
				<%-- <section class="shopping_shopping01">
					<input type="hidden" name="attrCount" id="attrCount" value="${fn:length(attrMap)}" />
					<c:forEach items="${attrMap}" var="bean" varStatus="attrNum">
						<c:forEach items="${bean.value}" var="productAttr" varStatus="status">
							<c:if test="${status.first}">
								<input type="hidden" name="proAttrId${attrNum.count}" id="proAttrId${attrNum.count}" value="${productAttr.id}" />
							</c:if>
						</c:forEach>
					</c:forEach>
					<input type="hidden" id="buyType" name="buyType" value="buyNow" />
							<p>请选择数量：</p>
							<p class="s_s01_add">
							    <img src="image/s_s_jian.png" class="s_s01_add_img btn-reduce">
							    <input type="text" class="s-s-input buy-num" value="1">
							    <img src="image/s_s_jia.png" class="btn-add">
							    库存${productInfo.repertory}件
							    <input type="text" class="max_lq jian-icon" value="100" style="display:none">
							</p>
							<p class="tuikuan-a submitProduct"><a href="javascript:void(0)">确认</a></p>
				</section> --%>
				<section class="shopping_shopping01">
				<input type="hidden" name="attrCount" id="attrCount" value="${fn:length(attrMap)}" />
				<c:forEach items="${attrMap}" var="bean" varStatus="attrNum">
						<p>请选择${bean.key.attrName}：</p><br>
						<p class="s_s_p2">
							<c:forEach items="${bean.value}" var="productAttr" varStatus="status">
							<c:if test="${status.first}">
								<input type="hidden" name="proAttrId${attrNum.count}" id="proAttrId${attrNum.count}"  />
							</c:if>
							<span id="${productAttr.id}" >${productAttr.attrValue}</span>
							</c:forEach>
						</p><br>
				</c:forEach>
				
						<!-- <input type="hidden" id="buyType" name="buyType" value="buyNow" /> -->
						<p>请选择数量：</p>
						<p class="s_s01_add">
							<img src="${imagePath}/s_s_jian.png" class="s_s01_add_img btn-reduce">
							<input type="text" id="amount" name="amount" class="s-s-input buy-num" value="1">
							<img src="${imagePath}/s_s_jia.png" class="btn-add">
							<span id="repertory">${productInfo.repertory}件库存</span>
							<input type="hidden" id="proReper" value="${productInfo.repertory}"/> 
							<input type="text" class="max_lq jian-icon" value="100" style="display:none">
						</p>
						<p class="tuikuan-a submitProduct" onclick="submitProduct()";><a href="javascript:void(0)">确认</a></p>
				</section>
		</section>
		</form>
		<section class="bg"></section>
		<section class="alert-share">
            <img src="${imagePath}/share.png">
            <p class="share-content">点击右上角微信朋友圈分享哦!</p>
        </section>
		<!-- 购买弹出 end -->
		<!-- content end -->
		<section id="s-p-footer" class="clearfix">
		<c:if test="${empty count}">
			<a href="epShoppingCarAction.htm?pageType=showCar2" class="cart_icon"><img src="${imagePath}/cart_icon.png"></a>
			<a href="javascript:void(0)" class="cart"><%-- <img src="${imagePath}/cart.png"> --%><span class="text">加入购物车</span><%-- <img src="${imagePath}/s-p-tp03.png"> --%></a>
			<a href="javascript:void(0)" class="buy" onclick="submitProduct('buyNow')" ><%-- <img src="${imagePath}/dollar.png"> --%><span class="text">立即购买</span><%-- <img src="${imagePath}/s-p-tp02.png"> --%></a>
		   	
		     <%--  <a href="epShoppingCarAction!addToCart.action?productId=${productInfo.id }"><img src="${imagePath}/s-p-tp03.png"></a> --%>
<%-- 		    <a href="epShoppingCarAction!addToCart.action?productId=${productInfo.id }"><img src="${imagePath}/s-p-tp03.png"></a> --%>
		   <%--  <a href="javascript:void(0)" class="shop" ><img src="${imagePath}/s-p-tp02.png"></a> --%>		   
		</c:if>
		<c:if test="${not empty count}">
			<a href="javascript:void(0)" class="shop" id="gifts-footer"><img src="${imagePath}/gifts_tb.png"></a>
		</c:if>
		</section>

		<!-- 添加购物车成功弹出 -->
		<script>
		       
		       $('.cart').click(function(){
					/* $('.shopping_pa_hide').show();
					$('.bg').show();
					var s_s_img=$('.s_s01_add_img').height();	
					$('.s-s-input').css('height',s_s_img); */
					$('#buyType').val("addCar");
// 					console.log(2266);
					submitProduct("addCar");
				});
		       
		      
		       function submitProduct(buyType){
					var repertory = $("#proReper").val()-$("#amount2").val();
					var amount = $("#amount2").val();
					
					var attrCount = $('#attrCount').val();
					var proAttrIds="";
					for(var i = 1; i <= attrCount; i++){
						if(isNull($('#proAttrId'+i).val())){
							layer.open({
								shadeClose: false,
								content: '<section class="buy-layer"><p><span>请勾选您要的商品信息！</span><a href="javascript:void(0)" onclick="evaluate_close()"><img src="${imagePath}/evaluate_su.png" id="pagebtn"></a></p></section>'
							});
							return false;
						}
						proAttrIds = proAttrIds + $('#proAttrId'+i).val() + ",";
					}
					if(repertory>=0){
						if(amount>0){
						proAttrIds=proAttrIds.substring(0,proAttrIds.length-1);
						if(buyType=="buyNow"){
							/* if(amount>10){
								layerOpen("抱歉，一次最多只能购买10件，感谢亲对我们的支持~", "javascript:void(0)");
								return false;
							} */
							$('#productForm').prop("action","epShoppingCarAction.htm?pageType=addProductToShoppingCar&proAttrIds="+proAttrIds);
							$('#productForm').submit();
						}else if(buyType=="addCar"){
							 $.ajax({ 
							      	url:'${ctx}/ajaxepShoppingCarAction!addProductToCar.htm', 
							      	type:'post', 
							      	dataType: 'json',
							      	data:{
							      		"proAttrIds":proAttrIds,
							      		"amount":$("#amount2").val(),
							      		"productId":$("#productInfoId").val(),
							      		"price":$("#buy-price").val()
							      	},
									success: function(json) {
										layerOpen("您已成功加入购物车", "javascript:void(0)");
									 },
									 error: function(XMLHttpRequest, textStatus, errorThrown) {
									 		alert("网络出现异常");
									  }
								}); 
								//document.form1.action = "epShoppingCarAction.htm?pageType=addProductToShoppingCar";
								//document.form1.submit();
						}
						}else{
							layerOpen("请输入正确的数量", "javascript:void(0)");
						}
					}else{
						layerOpen("库存不足", "javascript:void(0)");
					}
		       }
				/* $('.submitProduct').click(function(e){
					
				}); */
				
				
				$('.s_s_p2 span').on('click', function(){
					$(this).addClass('s_s_p2_color').siblings().removeClass('s_s_p2_color');
					$(this).siblings().first().val($(this).prop("id"));
					var attrCount = $('#attrCount').val();
					var proAttrIds="";
					for(var i = 1; i <= attrCount; i++){
						if(isNull($('#proAttrId'+i).val())){
							return;
						}
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
							var shopPrice = "${productInfo.shopPrice}";
					      	$("#repertory").html("(库存"+json.repertory+"件)");
					      	$("#proReper").val(json.repertory);
					      	if(null != json.price && '' != json.price){
					      		$(".partic-com-price").text(json.price);
						      	$("#buy-price").val(json.price);
					      	}else{
					      		$(".partic-com-price").text(shopPrice);
						      	$("#buy-price").val(shopPrice);
					      	}
						 },
						 error: function(XMLHttpRequest, textStatus, errorThrown) {
						      ("网络出现异常");
						   }
					});
				});	
				
				
			
	
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
						 layerOpen("网络出现异常", "javascript:void(0)");
					   }
				});	
		}			
				
				
		</script>
		<!-- 添加购物车成功弹出 end -->
		<script>
				$(document).ready(function(e){
						var statusIndex2 = $(".statusIndex1")
						if(!statusIndex2.length>0){
							$(".s_s_p2 span:first").click();
						}
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
	<script src="${jsPath}/jquery.cartCalculate.js"></script>
	<script src="${jsPath}/style.js"></script>
	<script src="${jsPath}/layer.m/layer.m.js"></script>
    <%@ include file="/jsps/modules/copyRight.jsp"%>
