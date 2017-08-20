<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/jsps/modules/top.jsp"%>

<!DOCTYPE HTML>
<html lang="zh-cn"><!--<![endif]-->
<head>
    <!--meta.jsp-->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=11; IE=10; IE=9; IE=8; IE=7; IE=EDGE" />
    <meta name="renderer" content="webkit">
    <meta name="viewport" content="initial-scale=1.0,user-scalable=no,width=device-width,minimum-scale=1.0,maximum-scale=1.0">
    <meta name="description" content="" />
    <meta name="keywords" content=""/>
    <meta name="author" content="" />
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
	<link rel="icon" href="favicon.ico"><!--客户有需要时添加，ico图标-->
    
    <title>百丽春</title>
    <link rel="stylesheet" type="text/css" href="${imagePath}/style.css">
    
    <script src="${jsPath}/jquery-1.11.2.min.js"></script>
    <script src="${jsPath}/TouchSlide.1.1.js"></script>
	
    <!--[if lt IE 9]>
    <script src="js/html5shiv.min.js"></script>
    <script src="js/respond.min.js"></script>
    <![endif]-->
    <noscript>您的浏览器不支持JavaScript，请启动JavaScript或更换浏览器</noscript>
    <!--end of meta.jsp-->
    
</head>
<script type="text/javascript">
	function submitForm(){
		if($("#addressId").val()!=""){
			$.ajax({ 
	      		url:'ajaxEpPayAction!goPay.htm?math='+Math.random(), 
	      		type:'post', 
	      		data:$('#shopping-cart-list').serialize(),
	      		dataType:'json',
	      		success:function(data){
	      			if(data!=null && data.payType==1){
	      				var obj = eval('(' + data.json + ')');
	      				console.log(data.json);
	          			WeixinJSBridge.invoke('getBrandWCPayRequest',obj,function(res){
	          				switch (res.err_msg){ 
	          	            case 'get_brand_wcpay_request:ok':  
	          	            	alert("支付成功");
	          	            	location.href = "epMemberCenterAction.action";
	          					break;
	          	            case 'get_brand_wcpay_request:cancel':   
	          	                break; 
	          	            case 'get_brand_wcpay_request:fail': 
	          	            	alert(res.err_msg);
	          	                break; 
	          	        	}
	          			});
	      			}
	      		},
			    error: function(XMLHttpRequest, textStatus, errorThrown) {
			       //alert("网络出现异常");
			    }
			});
		}else{
			layer.open({
				shadeClose: false,
				content: '<section class="buy-layer"><p><span>请先填写<a href="epMemberCenterAction!addAddress.action" style="color: #EC9191;">地址</a></span><a href="javascript:void(0)" onclick="layer.closeAll()"><img src="${imagePath}/evaluate_su.png" id="pagebtn"></a></p></section>'
			});
		}
	}
	function unpayMsg(){
		layer.open({
			shadeClose: false,
			content: '<section class="buy-layer"><p><span>余额不足，去<a href="epMemberCenterAction!newRecharge.action" style="color: #EC9191;">充值</a></span><a href="javascript:void(0)" onclick="layer.closeAll()"><img src="${imagePath}/evaluate_su.png" id="pagebtn"></a></p></section>'
		});
	}
</script>
<script type="text/javascript">
	    var isNull = function isNull(obj){
	    	if(obj==null || obj=='' || obj=='undifined' ){
	    		return true;
	    	}else{
	    		return false;
	    	}
	    }
	    
	    var layerOpen = function layerOpen(msg, url){
		    layer.open({
				shadeClose: false,
				content: '<section class="buy-layer"><p><span>'+msg+'</span><a href="'+url+'" onclick="evaluate_close()"><img src="${imagePath}/evaluate_su.png" id="pagebtn"></a></p></section>'
			});
	    }
	    
	    function evaluate_close(){
			layer.closeAll();
		} 
    </script>
	<body class='cart'>
			<!-- header -->
			<header class='clearfix close-bottom'>
					<a href="epMemberCenterAction.action"><img src="${imagePath}/add-left.png" alt="" id="close-bottom-img"></a>
					我的购物车
			</header>
			<!-- header end -->
			<!-- content car-->
			<form action="epPayAction.htm?pageType=goPay" method="post" class="car-form" id="shopping-cart-list">
					<c:forEach items="${cartInfoVos}" var="cartInfo" varStatus="status">
						<section class="book_detail clearfix">
								<p class="book_name">${mtag:cutString(cartInfo.product.title,20,'...')}</p>
												<c:choose>
													<c:when test="${cartInfo.product.isSale!=1}">
														<section class='car-fome'>
														<img src="${uploadPath}/${cartInfo.product.imgurl}" alt="" class='book_cover'>
															<p style="color: red;font-size: 16px">该商品已下架</p>
														</section>
															<a href="epShoppingCarAction.htm?pageType=deleteFormCart2&cartId=${cartInfo.id}"><img src="${imagePath}/my-car-x.png" alt=""></a>
													</c:when>
													<c:when test="${empty cartInfo.attributeMap}">
														<section class='car-fome'>
														<img src="${uploadPath}/${cartInfo.product.imgurl}" alt="" class='book_cover'>
														<a style="color: red;font-size: 10px;padding-left:36%;padding-top:5%" href="epProductAction!tampons.action?id=${cartInfo.product.id}">商品已更新，请删除后重新添加购物车</a>
														</section>
												   		<a href="epShoppingCarAction.htm?pageType=deleteFormCart2&cartId=${cartInfo.id}"><img src="${imagePath}/my-car-x.png" alt=""></a>
													</c:when>
													<c:otherwise>
														<section class='car-fome'>
														<img src="${imagePath}/icon_choose_no.png" alt="" class='btn_choose' onClick='change_pic(this)'>
														<img src="${uploadPath}/${cartInfo.product.imgurl}" alt="" class='book_cover'>
														<span onclick="onReduce(this)" class="btn-reduce" style="margin-right:30px">-</span>
														<%-- <img  src="${imagePath}/icon_jian.png" alt="" onclick="onReduce(this)" class="btn-reduce"> --%>
														<input type="text" class="buy-num"  style="width: 20px; height: 24px; position: absolute;top:18%;left:47%" onblur="valExam(this);this.value = this.value<=0?1:this.value" value="${cartInfo.buyAmount}"
														onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" >
														<span  onclick="onAdd(this)" class="btn-add">+</span>
														<%-- <img src="${imagePath}/icon_jia.png" alt="" onclick="onAdd(this)" class="btn-add">	 --%>		
														<p>${mtag:cutString(cartInfo.product.description,11,'...')}</p>
														<input name="choose"  class="selectable" style="display:none" type="checkbox"  value="${cartInfo.id}">
														<input type="hidden" class="totalPrice" value="<fmt:formatNumber value='${cartInfo.product.marketPrice*cartInfo.buyAmount}' maxFractionDigits='2'/>" />
														<span class='book_price clearfix'>
														<span class="one_price" id="one_price">￥<fmt:formatNumber value="${cartInfo.productPrice}" pattern="0.00"  maxFractionDigits="2"/></span>
														<div class="clearfix"><span>x</span><span class="buy-num">${cartInfo.buyAmount}</span></div>
														</span>
														<input type="text" class="max_lq jian-icon" value="100" style="display:none">
														<span style="float:left;padding-left: 3%;font-size: 1.2em;">
														<c:forEach items="${cartInfo.attributeMap}" var="map">
										    			${map.value}
														</c:forEach>
														</span>
														</section>
														<p class="fortotal">共<span class="cartBuyAmount">${cartInfo.buyAmount}</span>件商品&nbsp;&nbsp;
														总价：
														<span class="car-b-w">
														<fmt:formatNumber value="${cartInfo.productTotal}" type="currency"/>
														</span></p>
														<a href="epShoppingCarAction.htm?pageType=deleteFormCart2&cartId=${cartInfo.id}"><img src="${imagePath}/my-car-x.png" alt=""></a>
													</c:otherwise>
												</c:choose>
							</section>			
					</c:forEach>
					

					<!-- 结算弹出 -->
					<section class="yes-order" style="height:250px;">
					        <c:if test="${!empty address }">
								<input type="hidden" name="addressId" id="addressId" value="${address.id}"/>
								
								<p class="first-child order-adress">${address.province.province}${address.city.city}${address.area.area}${address.address}</p>
								
								<a href="epMemberCenterAction!addAddress.action" style="font-size:3px;"><img src="${imagePath}/icon_pencil.png" class="order-img"></a>
								
								<p class="first-child">${address.consignee}     ${address.cellphone}</p>
							</c:if>
							<c:if test="${empty address }">
							     <a href="epMemberCenterAction!addAddress.action" style="font-size:3px;"><img src="${imagePath}/icon_pencil.png" class="order-img"></a>
							     <a href="epMemberCenterAction!addAddress.action" style="font-size:3px;color:black;">没有地址，点此添加地址</a>
							     <div style="margin-bottom:20px;"></div>
							</c:if>
							<p class="pay-ship-type">
							付款方式：
							<select id="selectPayType" name="payType">
								<option value="1">微信支付</option>
								<option value="2">余额支付</option>
							</select>
							<span id="balance_p" style="display:none;">余额：<span id="balance"><fmt:formatNumber value="${member.balance}" pattern="#0.00"/></span></span>
							</p>
							<!-- <p class="pay-ship-type">选择物流：
							<select name=""><option>顺丰快递</option></select></p>
							<p>预计星期日（4月5日）送达（请在明天14点前付款）</p> -->
							<%-- <p class="order-p">
								<img src="${imagePath}/icon_choose_no.png"  class="yes-order-ch" onclick="change_pic(this)"><span>红包&nbsp;&nbsp;&nbsp;&nbsp;-￥30</span>
							</p>
							<p class="order-p">
								<img src="${imagePath}/icon_choose_no.png" class="yes-order-ch" onclick="change_pic(this)"><span>卡券<span class="order-span">（无卡券使用）</span></span>
							</p> --%>
							<p>
								<input name="" type="text" placeholder="补充说明：选填可告诉卖家您的特殊要求" >
							</p>
							<section class="order-p2">
								<section>
									<span id="alltotalprice">合计:￥0</span><br>
								</section>
								<section>
								<a class="order-p-close">取消</a> <a id="submitForm" href="javascript:void(0);" onclick="submitForm()">确认</a>
								</section>
							</section>
							<!-- 余额支付--弹出支付密码 -->
							<section class="pay_password">
								<p>请输入支付密码</p>
								<input type="password" id="payPassword" name="payPassword" />
								<p class="pay_password_p2"><a onclick="hidePayWin();">取消</a><a onclick="payByBalance();">确认</a></p>
							</section>
							<section class="pay_password_bg"></section>
							<!--弹出支付密码结束 -->
					</section>
					<section class="bg"></section>
					<!-- 结算弹出 end -->
					
					
			</form>
			<!-- content ca -->
			<!-- content 结算all-->
			<section class="account">
					<img  style="margin:0; width:14%;" src="${imagePath}/icon_choose_no.png" id="selectAllImg" alt="" onClick='change_all(this)'>
					<input type="checkbox" style="display:none" name="selectall" id="selectall">
					<span>全选</span>
					
					<a  id="toOrder"><span class='jiesuan'>确认订单</span></a> 
					
					<p>
							<span id="car-o-w">合计：￥<span  id="total_lq">0</span></span><br/>
							<span id="car-r-w">不含运费</span>
					</p>
			</section>

			
			<script>
			$(document).ready(function(){
				$(".totalPrice").each(function(){
					valExam($(this).siblings(".buy-num"));
				});
				//是否为全选
				if($("#selectall").prop("checked")){
					$("#selectAllImg").prop('src','${imagePath}/icon_book_choose.png');
					$(".btn_choose").attr('src','${imagePath}/icon_book_choose.png');
					countPrice();
				}else{
					$(".totalPrice").each(function(){
						if($(this).siblings(".selectable").prop("checked")){
							$(this).siblings(".btn_choose").prop('src','${imagePath}/icon_book_choose.png');
						}
					});
					countPrice();
				}
			});
			$('#selectPayType').on('change', function(){
				var balance = parseFloat($("#balance").html());
				var total_price = parseFloat($("#total_lq").html());
				if($("#selectPayType").val()==2){
					$("#balance_p").show();
					if(balance < total_price){
						layer.open({
							shadeClose: false,
							content: '<section class="buy-layer"><p><span>余额不足，去<a href="epMemberCenterAction!newRecharge.action" style="color: #EC9191;">充值</a></span><a href="javascript:void(0)" onclick="layer.closeAll()"><img src="${imagePath}/evaluate_su.png" id="pagebtn"></a></p></section>'
						});
						$("#submitForm").attr("onclick","unpayMsg();");
					}else{
						$("#submitForm").attr("onclick","showPayWin();");
					}
				}else{
					$("#balance_p").hide();
					$("#submitForm").attr("onclick","submitForm();");
				}
			});
			
			function payByBalance(){
				var payPassword = $("#payPassword").val();
				if($("#payPassword").val()!=""){
					$.ajax({ 
					    url:'${ctx}/ajaxeCheckPassword!checkPayPassword.htm', 
				      	type:'post', 
				      	dataType: 'json',
				      	data:{
				      		"payPassword":$("#payPassword").val()
				      	},
						success: function(json) {
				      		if(json=="true"){
				      			submitForm();
				      		}else{
				      			layer.open({
									shadeClose: false,
									content: '<section class="buy-layer"><p><span style="color: #EC9191;">密码错误，请重新输入！<a style="color: #EC9191;" href="epMemberCenterAction!resetPasswordPage.action">重置密码</a></span><a href="javascript:void(0)" onclick="layer.closeAll()"><img src="${imagePath}/evaluate_su.png" id="pagebtn"></a></p></section>'
								});
				      		}
						},
						error: function(XMLHttpRequest, textStatus, errorThrown) {
						     openwaring("网络出现异常");
						}
					});
				}else{
					layer.open({
						shadeClose: false,
						content: '<section class="buy-layer"><p><span style="color: #EC9191;">请输入支付密码！</span><a href="javascript:void(0)" onclick="layer.closeAll()"><img src="${imagePath}/evaluate_su.png" id="pagebtn"></a></p></section>'
					});
				}
			}
			
			$('.jiesuan').on('click', function(){
				var subTotal = parseFloat($("#total_lq").html());
				//console.log($("#total_lq").html());
				if(subTotal!=0){
				/* 	$("#toOrder").click();  */
					var cartInfoIds="";
				    $(".totalPrice").each(function(){
						if($(this).siblings(".selectable").prop("checked")){
							cartInfoIds=cartInfoIds+$(this).siblings(".selectable").val()+",";
						}
					});
				    cartInfoIds=cartInfoIds.substring(0,cartInfoIds.length-1);
				    $("#toOrder").attr("href","epShoppingCarAction.htm?pageType=showCar&cartInfoIds="+cartInfoIds);
				}else{
					layerOpen("请选择商品", "javascript:void(0)");
				}
			});
			
			function showPayWin(){
				$(".pay_password").show();
				$(".pay_password_bg").show();
			}
			
			function hidePayWin(){
				$(".pay_password").hide();
				$(".pay_password_bg").hide();
			}
			
			function onReduce(obj) {
				 num = $(obj).next('input').val();
				if (num <= 1)
					return;
				$(obj).next('input').val(Number(num) - 1);
				valExam($(obj).next('input')); 
			}
		
			function onAdd(obj) {
				num = $(obj).prev('input').val();
				$(obj).prev('input').val(Number(num) + 1);
				valExam($(obj).prev('input'));
			}
			
			function valExam(obj) {
				if($(obj).val() > 0){
					$.ajax({ 
					   url:'${ctx}/ajaxepShoppingCarAction!updateCart.htm', 
					      	type:'post', 
					      	dataType: 'json',
					      	data:{
					      		"cartId":$(obj).siblings('input.selectable').val(),
					      		"amount":$(obj).val()
					      	},
							success: function(json) {
					      		$(obj).val(json.amount);
					      		$(obj).siblings('.book_price').find('.buy-num').html(json.amount);
					      		$(obj).siblings('.totalPrice').val(json.totalPrice.toFixed(2));
					      		$(obj).siblings('.one_price').html("￥"+json.price.toFixed(2));
					      		$(obj).parent().siblings('.fortotal').find('.car-b-w').html("￥"+json.totalPrice.toFixed(2));
					      		$(obj).parent().siblings('.fortotal').find('.cartBuyAmount').html(json.amount);
					      		if(json.flag=="false"){
					      			if(json.amount != 0){
					      				alert("最多只能买"+json.amount+"件");
					      			}else{
					      				alert("库存不足");
					      			}
					      		}
					      		countPrice();
							 },
							 error: function(XMLHttpRequest, textStatus, errorThrown) {
							      openwaring("网络出现异常");
							   }
					}); 
				}
			}
			function onDelete() {
				if(subtotal!=0){
					document.form1.action = "epShoppingCarAction.htm?pageType=deleteProductFromShoppingCar";
					document.form1.submit();
				}else{
					alert("请选择商品");
				}
			}
			
			function change_all(obj){
				var srvName =  $(obj).attr('src');
				if (srvName.indexOf('icon_book_choose.png') != -1 ) {
					$(obj).attr('src','${imagePath}/icon_choose_no.png');
					$('.btn_choose').attr('src','${imagePath}/icon_choose_no.png');
	
				} else{
					$(obj).attr('src','${imagePath}/icon_book_choose.png');
					$('.btn_choose').attr('src','${imagePath}/icon_book_choose.png');
				}
				selectAll();
				countPrice();
			}
			
			function change_pic(obj){
				var srvName =  $(obj).attr('src');
				if (srvName.indexOf('icon_book_choose.png') != -1 ) {
					$(obj).attr('src','${imagePath}/icon_choose_no.png');
		
				} else{
					$(obj).attr('src','${imagePath}/icon_book_choose.png');
				}
				selectItemClick($(obj).siblings(".selectable"));
				countPrice();
			}
			
			function selectAll() {
				if(!$("#selectall").prop("checked")){
					$("#selectall").prop("checked",true);
				}else{
					$("#selectall").prop("checked",false);
				}
				$(".selectable").each(function() {
					$(this).prop("checked", $("#selectall").prop("checked"));
				});
			}
		
			function selectItemClick(obj) {
				var subchecked = $(obj).prop("checked");
				var selectClass = $(obj).prop("class");
				if (!subchecked) {
					$(obj).prop("checked", true);
				} else {
					$(obj).prop("checked", false);
				} 
				$("."+selectClass).each(function(){
					if(!$(this).prop("checked")){
						$("#selectall").prop("checked", false);
						$("#selectAllImg").prop('src','${imagePath}/icon_choose_no.png');
						return false;
					}else{
						$("#selectall").prop("checked", true);
						$("#selectAllImg").prop('src','${imagePath}/icon_book_choose.png');
					}
				});
			}
			
			function countPrice(){
				var total=0;
				var subtotal=0;
				$(".totalPrice").each(function(){
					if($(this).siblings(".selectable").prop("checked")){
						var thisPrice = parseFloat($(this).val());
					    total = total + thisPrice;
					}
				});
				$("#total_lq").html(total.toFixed(2));
				$("#alltotalprice").html("合计:"+total.toFixed(2));
				
			}
			
			
			</script>
			<!-- 购物车计算插件 end -->
		
	</body>
			
	<script src="${jsPath}/style.js"></script>
	<script src="${jsPath}/layer.m/layer.m.js"></script>
</html>