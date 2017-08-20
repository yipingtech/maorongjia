<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE HTML>
<html lang="zh-cn">
<head>
    <meta charset="utf-8">
    <meta name="Keywords" content="">
    <meta name="description" content="">
    <meta name="author" content="Christina">
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no,width=device-width,minimum-scale=1.0,maximum-scale=1.0" />
    <!--设置视窗宽为设备宽度，默认不缩放，不允许用户缩放。-->
    <meta name="format-detection" content="telephone=no,email=no"/>
    <!--忽略电话号码和邮箱识别-->
    <meta name="apple-mobile-web-app-capable" content="yes" />
    <!-- 在iOS中 隐藏工具栏和菜单栏。-->
    <meta name="apple-mobile-web-app-status-bar-style" content="black" />
    <!--顶部状态栏(手机信号、时间、电池)的背景颜色。默认值为default（白色），可以定为black（黑色）和black-translucent（灰色半透明-->
    <title>确认订单</title>
    <link href="${imagePath}/style.css" rel="stylesheet" type="text/css">
    <link href="${imagePath}/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="${imagePath}/video-js.css" rel="stylesheet" type="text/css">
    <script src="${jsPath}/jquery-1.11.2.min.js"></script>
    <script src="${jsPath}/bootstrap.min.js"></script>
    <script src="${jsPath}/TouchSlide.1.1.js"></script>
    <script src="${jsPath}/video.js"></script>
    <script src="${jsPath}/layer.m/layer.m.js"></script>
    <script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
</head>
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
<script type="text/javascript">
	function submitForm(){
		if("undefined"!=$("#addressId").val() && null!=$("#addressId").val() && $("#addressId").val()!=""){
			$.ajax({ 
	      		url:'ajaxEpPayAction!goPay.htm?math='+Math.random(), 
	      		type:'post', 
	      		data:$('#shopping-cart-list').serialize(),
	      		dataType:'json',
	      		success:function(data){
	      			 if(data!=null && data.payType==1){
	      				var obj = eval('(' + data.json + ')');
	          			WeixinJSBridge.invoke('getBrandWCPayRequest',obj,function(res){
	          				switch (res.err_msg){ 
	          	            case 'get_brand_wcpay_request:ok':  
	          	            	location.href = "epMemberCenterAction.action";
	          					break;
	          	            case 'get_brand_wcpay_request:cancel': 
	          	            	location.href = "epMemberCenterAction!noPayOrderByUser.action?payOrderId="+data.payOrderId;
	          	                break; 
	          	            case 'get_brand_wcpay_request:fail': 
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
			layerOpen("请先填写收货地址", "javascript:void(0)");
		}
	}
	function unpayMsg(){
		layer.open({
			shadeClose: false,
			content: '<section class="buy-layer"><p><span>余额不足，去<a href="epMemberCenterAction!newRecharge.action" style="color: #EC9191;">充值</a></span><a href="javascript:void(0)" onclick="layer.closeAll()"><img src="${imagePath}/evaluate_su.png" id="pagebtn"></a></p></section>'
		});
	}
</script>
<body>
<!--container-->
<section id="container">
<form action="epPayAction.htm?pageType=goPay" method="post" id="shopping-cart-list">
    <a href="epMemberCenterAction!addAddress.action?flag=toOrder" class="address-block">
        <img src="${imagePath}/address-icon.png" class="address-icon">
		        <c:if test="${!empty address }">
		           <span>
						<input type="hidden" name="addressId" id="addressId" value="${address.id}"/>
						${address.consignee} 
							${address.province.province}${address.city.city}${address.area.area}${address.address}
							${address.cellphone}
							<%-- <img src="${imagePath}/arrow-right.png" class="right" style="margin-left:50px;"> --%>
					</span>
				</c:if>
				<c:if test="${empty address }">
				     <span>
				        	收货地址
				         <%-- <img src="${imagePath}/arrow-right.png" class="right"> --%>
				     </span>
				</c:if>
    </a>
   	<c:forEach items="${cartInfoVos}" var="cartInfo" varStatus="status">
         <div class="order-wrap clearfix">
	        <a  class="left">
	             <input name="choose"  class="selectable"  type="hidden"  value="${cartInfo.id}">
	            <div class="product-img left" style="width:70px;"><img src="${uploadPath}/${cartInfo.product.imgurl}"></div>
	            <div class="product-detail left" style="width:68%;float:left;">
	                <div>
	                	<c:if test="${fn:length(cartInfo.product.title)>12 }">${fn:substring( cartInfo.product.title ,0,12)}...</c:if>
 						<c:if test="${fn:length(cartInfo.product.title)<=12 }">${cartInfo.product.title }</c:if>
	                </div>
	                <%-- <div style="font-size:1.3rem">
	                	${cartInfo.product.description}
	                </div> --%>
	                <div>
	                	<c:forEach items="${cartInfo.attributeMap }" var="attr">
	                		${attr.value }
	                	</c:forEach>
	                </div>
	            </div>
	        </a>
	        <div class="order-detail right">
	            <div>￥${cartInfo.productPrice}</div>
	            <input type="hidden" class="totalPrice" value="<fmt:formatNumber value='${cartInfo.productTotal}' pattern='#.00'/>" />
	            <div class="count">×${cartInfo.buyAmount}</div>
	            <div class="delete-icon"><a href="epShoppingCarAction.htm?pageType=deleteFormCart&cartId=${cartInfo.id}"><img src="${imagePath}/delete-icon.png"></a></div>
	        </div>
	    </div>
  	</c:forEach>
  	<input type="hidden" name="payType" value="1" />
	   <!--  <div class="note">
	        <textarea placeholder="备注"></textarea>
	    </div> -->
	    <div class="total clearfix" style="border:none;">
	    
	        <div class="right">
	           	合计：<span>￥</span><span  id="total_lq">0</span>
	        </div>
	        
	    </div>
	    
   </form>
    <c:if test="${!empty cartInfoVos }">
	    <a id="submitForm" href="javascript:void(0);" onclick="submitForm()" class="confirm-btn">
	               确认订单
	    </a>
    </c:if>
    <c:if test="${empty cartInfoVos }">
    	 <a id="goToShop" class="confirm-btn" href="epIndexAction.action" >
	               亲，您的购物车(订单)没有任何商品，点此去购买吧~
	    </a>
    </c:if>
</section>
<!--end of container-->
<script>
countPrice();
			$('#selectPayType').click(function(){
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
					$('.yes-order,.order-layer,.tk-hide').show();
					$('.bg').show();
					$('.shopping_pa_hide').hide();
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
					      		$(obj).siblings('.book_price').find('.buy-num').val(json.amount);
					      		$(obj).siblings('.totalPrice').val(json.totalPrice.toFixed(2));
					      		$(obj).parent().siblings('.fortotal').find('.car-b-w').html("￥"+json.totalPrice.toFixed(2));
					      		if(json.flag=="false"){
					      			if(json.amount != 0){
					      				layerOpen("最多只能买"+json.amount+"件", "javascript:void(0)");
					      			}else{
					      				layerOpen("库存不足", "javascript:void(0)");
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
					layerOpen("请选择商品", "javascript:void(0)");
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
					var thisPrice = parseFloat($(this).val());
				    total = total + thisPrice;
				});
				$("#total_lq").html(total.toFixed(2));
				if(total<190){
					str = '<p style="color:red;font-size:12px;width:100%;border-bottom: 1px solid #e3e3e3;text-align: left;padding-left:15px;">您的订单未满199元，需支付运费！</p>';
					$(".total").after(str);
				}
				$("#alltotalprice").html("合计:"+total.toFixed(2));
				
			}
			
			</script>
			<script src="${jsPath}/style.js"></script>
</body>
</html>