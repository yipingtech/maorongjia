<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/jsps/modules/top.jsp"%>
<script type="text/javascript" src="${ctx}/js/ajaxfileupload.js"></script>
<script type="text/javascript">  function del(){  if(!confirm("确认要删除？")){  window.event.returnValue = false;  }  }  </script> 
<script type="text/javascript">
    $(function(){	
    	        //隐藏退换货地址
    	        $(".tk-address").hide();
				//退货原因
				$(".draw-true").click(function(){
				    var str="";
				    $(".selecdraw").each(function(){
						if($(this).prop("checked")){
						  str+=$(this).val()+"   ";
						}
					});
					str+=$("#orther-cause").val();
					$.ajax({ 
				          url:'${ctx}/ajaxMemberCenterAction!applyDrawBackByUser.action', 
				          type:'post', 
				          dataType: 'json',
				          data:{"productDrawback.orderNum":$("#order-num").val(),
				                "productDrawback.drawbackMoney":$("#order-money").val(),
				                "productDrawback.drawbackCause":str,
				                "orderInfo.id":$("#select-orderInfoId").val(),
				                "productDrawback.imageName":$("#fileName").val()
				               }, 
					      success: function(data) {
					      			$(".order-03").empty();	
					                ajaxAllOrder(1); 
					                $(".tk-hide").hide();  
					                $('.bg').hide();         
					      },
					      error: function(XMLHttpRequest, textStatus, errorThrown) {
					           openwaring("网络出现异常");
					        }
					   }); 
				});	
		
				    var flagNum=0;
					var noPayOrderNum=1;				
					 $(window).scroll(function(){
					 if(flagNum=='0'){
				　 	 	 var scrollTop = $(this).scrollTop();               //滚动条距离顶部的高度
				　　	 var scrollHeight = $(document).height();           //当前页面的总高度
				　　	 var windowHeight = $(this).height();              //当前可视的页面高度
																			//alert(windowHeight);
				　	 	 if(scrollTop + windowHeight >= scrollHeight){        //距离顶部+当前高度 >=文档总高度 即代表滑动到底部
							$(".loading").show();
					        ++noPayOrderNum;	 
					        ajaxNoPayOrder(noPayOrderNum,false);
						 }
						 }
					 });
					 $(".loading").hide();
					 

				//待付款
				$("#order-01").click(function(){
				    flagNum=1;
					$(".order-01").empty();
					ajaxNoPayOrder(1,true);
					var moreNum=1;
					 $(window).scroll(function(){
				　 	 	 var scrollTop = $(this).scrollTop();               //滚动条距离顶部的高度
				　　	 	 var scrollHeight = $(document).height();           //当前页面的总高度
				　　	  	 var windowHeight = $(this).height();              //当前可视的页面高度
																			//alert(windowHeight);
				　	 	 if(scrollTop + windowHeight >= scrollHeight){        //距离顶部+当前高度 >=文档总高度 即代表滑动到底部
							//alert("到底部了");
							$(".loading").show();
					        ++moreNum;	 
					        ajaxNoPayOrder(moreNum,false);
						 }
					 });
				});

												
				//待收货
				$("#order-02").click(function(){
				    flagNum=1;
					$(".order-02").empty();
				    ajaxNoReceiveOrder(1,true);
				    var moreNum=1;
					$(window).scroll(function(){
				　 	 	 var scrollTop = $(this).scrollTop();               //滚动条距离顶部的高度
				　　		 var scrollHeight = $(document).height();           //当前页面的总高度
				　　	 	 var windowHeight = $(this).height();              //当前可视的页面高度
																			//alert(windowHeight);
				　	 	 if(scrollTop + windowHeight >= scrollHeight){        //距离顶部+当前高度 >=文档总高度 即代表滑动到底部
							//alert("到底部了");
							$(".loading").show();
							 ++moreNum;	
					        ajaxNoReceiveOrder(moreNum,false);
						 }
					 });
				});
				
				//全部订单
				$("#order-03").click(function(){
					flagNum=1;
					$(".order-03").empty();
					ajaxAllOrder(1,true);				
					var moreNum=1;
					$(window).scroll(function(){
				　 	 	 var scrollTop = $(this).scrollTop();               //滚动条距离顶部的高度
				　　	 var scrollHeight = $(document).height();           //当前页面的总高度
				　　	 var windowHeight = $(this).height();              //当前可视的页面高度
																			//alert(windowHeight);
				　	 	 if(scrollTop + windowHeight >= scrollHeight){        //距离顶部+当前高度 >=文档总高度 即代表滑动到底部
							//alert("到底部了");
							$(".loading").show();
							 ++moreNum;	
					        ajaxAllOrder(moreNum,false);
						 }
					 });
			  });			
		}); 
		
		//确认收货
				function sureSendProduct(orderNum){
				  $.ajax({ 
				          url:'${ctx}/ajaxMemberCenterAction!editReceiveShippingType.action', 
				          type:'post', 
				          dataType: 'json',
				          data:{"payOrder.orderNum":orderNum}, 
					      success: function(data) {
					      alert(data);	
					      $(".order-02").empty();	
					      window.location.reload();                
					      },
					      error: function(XMLHttpRequest, textStatus, errorThrown) {
					           openwaring("网络出现异常");
					        }
					   });
			  }	
			  
			  				
				//获取审核通过收货地址
				function getAddress(){
					$(".order-layer").show();
				    $(".bg").show();
				    $(".shopping_pa_hide").hide();
				    $.ajax({ 
				          url:'${ctx}/ajaxMemberCenterAction!queryAddressByOrderInfoNum.action', 
				          type:'post', 
				          dataType: 'json',
				          data:null, 
					      success: function(data) {
					      $(".drawAddress").empty();
					        $(".drawAddress").append(data);			            
					      },
					      error: function(XMLHttpRequest, textStatus, errorThrown) {
					           openwaring("网络出现异常");
					        }
					   }); 
				}
			  
				 //未付款ajax
				 function ajaxNoPayOrder(pageStart,flag){
				 $.ajax({ 
			      	url:'${ctx}/ajaxMemberCenterAction!noPayOrderByUser.action', 
			      	type:'post', 
			      	dataType: 'json',
			      	data:{
			      		"pageNo":pageStart,
			      	},
					success: function(json) {
					 $(".order-trip-message").empty();
					  $("#order-01").html("待付款("+json.count1+")");
					  $("#order-03").html("全部订单("+json.count2+")");
						if (null!=json.payOrders1&&json.payOrders1!="") {			
						$.each(json.payOrders1, function(index, payOrder) {
						var payOrderStart="<section class='book_detail clearfix shopping-f'>"
						+"<p class='book_name'>"
						+"<span>"+(payOrder.createTime).substring(0,10)+"</span>"
						+"<span>订单号："+payOrder.orderNum+"</span>"
						+"<a onclick='javascript:return del();' href='epMemberCenterAction!delPayOrderByOrderNumFont.action?payOrder.id="+payOrder.id+"' style='color:#6a2473;width: auto;float: right;padding-top: 1px;font-size: 12px;font-weight: 500;margin-right:0'>删除</a>";
						+"</p>";
						var flag = true;
						$.each(json.orderInfos1, function(index, orderInfo) {
						   if(payOrder.orderNum==orderInfo.payOrder.orderNum){
							  //console.log(json);
							  if(orderInfo.product.isSale!=1){//判断是否下架
								//判断属性值是否存在
					              if(null!=json.orderInfoAttrStrMap[orderInfo.productAttrIds]&&json.orderInfoAttrStrMap[orderInfo.productAttrIds]!=""){
								  	 payOrderStart+="<section class='shopping-fome'>"
						            	 +"<img src='${uploadPath}/"+orderInfo.product.imgurl+"' alt='' class='book_cove' class='btn-reduce'>"
						            	 +"<p class='one-more'>"
						            	 +"<span>"+orderInfo.product.title+"</span>"
						            	 +"</p>"
						            	 +"<span style='float:right;color:red;margin-top:-5vw;'>已下架</span>"
						            	 +"<p>"
							             +"<span style='color: #6C6C6C;'>"+json.orderInfoAttrStrMap[orderInfo.productAttrIds]+"</span>"
							             +"</p>"
						            	 +"<p>共 "+orderInfo.amount+" 件商品</p>"
						            	 +"<p>价格：<span>￥"+orderInfo.price*orderInfo.amount
						            	 +"</span>"
						            	 +"</p>"
						            	 +"</section>";
					              }else{
					            	  payOrderStart+="<section class='shopping-fome'>"
							            	 +"<img src='${uploadPath}/"+orderInfo.product.imgurl+"' alt='' class='book_cove' class='btn-reduce'>"
							            	 +"<p class='one-more'>"
							            	 +"<span>"+orderInfo.product.title+"</span>"
							            	 +"</p>"
							            	 +"<span style='float:right;color:red;margin-top:-5vw;'>已下架</span>"
							            	 +"<p>共 "+orderInfo.amount+" 件商品</p>"
							            	 +"<p>价格：<span>￥"+orderInfo.price*orderInfo.amount
							            	 +"</span>"
							            	 +"</p>"
							            	 +"</section>";  
					              }
								  flag = false;
							  }else if(null!=json.orderInfoStatusMap[orderInfo.productAttrIds]&&json.orderInfoStatusMap[orderInfo.productAttrIds]!=""){
								 payOrderStart+="<section class='shopping-fome'>"
					            	 +"<img src='${uploadPath}/"+orderInfo.product.imgurl+"' alt='' class='book_cove' class='btn-reduce'>"
					            	 +"<p class='one-more'>"
					            	 +"<span>"+orderInfo.product.title+"</span>"
					            	 +"</p>"
					            	 +"<span style='float:right;color:red;margin-top:-5vw;'>已更新</span>"
					            	 +"<p>共 "+orderInfo.amount+" 件商品</p>"
					            	 +"<p>价格：<span>￥"+orderInfo.price*orderInfo.amount
					            	 +"</span>"
					            	 +"</p>"
					            	 +"</section>";
								 flag = false;
							 }else{
								//判断属性值是否存在
				            	 if(null!=json.orderInfoAttrStrMap[orderInfo.productAttrIds]&&json.orderInfoAttrStrMap[orderInfo.productAttrIds]!=""){
								   payOrderStart+="<section class='shopping-fome'>"
									 +"<a href='epProductAction!tampons.action?id="+orderInfo.product.id+"' class='shopping-fome-a'>"
					            	 +"<img src='${uploadPath}/"+orderInfo.product.imgurl+"' alt='' class='book_cove' class='btn-reduce'>"
					            	 +"</a>"
					            	 +"<p class='one-more' style='margin-top: -10px;'>"
					            	 +"<span>"+orderInfo.product.title+"</span>"
					            	 +"</p>"
					            	 +"<p>"
						             +"<span style='color: #6C6C6C;'>"+json.orderInfoAttrStrMap[orderInfo.productAttrIds]+"</span>"
						             +"</p>"
					            	 +"<p>共 "+orderInfo.amount+" 件商品</p>"
					            	 +"<p>价格：<span>￥"+orderInfo.price*orderInfo.amount
					            	 +"</span>"
					            	 +"</p>"
					            	 +"</section>";
				            	 }else{
				            		payOrderStart+="<section class='shopping-fome'>"
				            			 +"<a href='epProductAction!tampons.action?id="+orderInfo.product.id+"' class='shopping-fome-a'>"
						            	 +"<img src='${uploadPath}/"+orderInfo.product.imgurl+"' alt='' class='book_cove' class='btn-reduce'>"
						            	 +"</a>"
						            	 +"<p class='one-more'>"
						            	 +"<span>"+orderInfo.product.title+"</span>"
						            	 +"</p>"
						            	 +"<p>共 "+orderInfo.amount+" 件商品</p>"
						            	 +"<p>价格：<span>￥"+orderInfo.price*orderInfo.amount
						            	 +"</span>"
						            	 +"</p>"
						            	 +"</section>"; 
				            	 }	 
							 }
/* 				             payOrderStart+="<section class='shopping-fome'><a href='navigation.htm?newsId="+orderInfo.product.id+"&columnType=product' class='shopping-fome-a'><img src='${uploadPath}/"+orderInfo.product.imgurl+"' alt='' class='book_cove' class='btn-reduce'></a><p><span>"+orderInfo.product.title+"</span></p><p>共 "+orderInfo.amount+" 件商品</p><p>价格：<span>￥"+orderInfo.price+"</span></p></section>"; */
				       	    }
				       	  });
						  if(flag){
							  payOrderStart+="<p class='shopp shopp01'>"
						       	  +"<span></span>"
						       	  +"<span></span>"
						       	  +"<span>"
						       	  +"<a class='order-alredy' id='payOrder' onclick='submitForm("+payOrder.id+")'>确认付款</a>"
						       	  +"</span>"
						       	  +"</p>"
						       	  +"</section>";
						  }else{
							  payOrderStart+="</section>";
						  }
/* 				       	  payOrderStart+="<p class='shopp shopp01'><span></span><span></span><span><a class='order-alredy' href='eppayAction.htm?pageType=goPay&payType=1'>确认付款</a></span></p></section>"; */
				          $(".order-01").append(payOrderStart);  
				        });		     							
					  }else{
					   $(".loading").hide();
					     if(flag){
					       var str="<section style='margin-top:50%'><label class='orderTips1'>您没有未付款的订单</label></section>";
					       $(".order-01").append(str);
					     }else{
					      var str="<section class='order-trip-message'>没有更多订单</section>";
					       $(".order-01").append(str);
					     }
					  }
					  $(".loading").hide();					  
					 },
					 error: function(XMLHttpRequest, textStatus, errorThrown) {
					      openwaring("网络出现异常");
					      $(".loading").hide();
					   }
				});	
				}
				
				 //未收货ajax  -- 现在为未发货
				 function ajaxNoReceiveOrder(pageStart,flag){
				  $.ajax({ 
			      	url:'${ctx}/ajaxMemberCenterAction!noReceiveOrderByUser.action', 
			      	type:'post', 
			      	dataType: 'json',
			      	data:{
			      		"pageNo":pageStart,
			      	},
					success: function(json) {
					$(".order-trip-message").empty();
					  $("#order-01").html("待付款("+json.count1+")");
					  $("#order-03").html("全部订单("+json.count2+")");
						//console.log("库存:"+json.stockList);
						if (null!=json.payOrders2&&json.payOrders2!="") {
						//$(".order-02").empty();
						$.each(json.payOrders2, function(index, payOrder) {
						var payOrderStart="<section class='book_detail clearfix shopping-f'>"
							+"<p class='book_name'>"
							+"<span>"+(payOrder.createTime).substring(0,10)+"</span>"
							+"<span>订单号："+payOrder.orderNum+"</span>"
							+"</p>";
						//$(".order-01").append(payOrderStart);
						  $.each(json.orderInfos2, function(index, orderInfo) {
						   if(payOrder.orderNum==orderInfo.payOrder.orderNum){
				             payOrderStart+="<section class='shopping-fome'>"
				            	 +"<a href='epProductAction!tampons.action' class='shopping-fome-a'>"
				            	 +"<img src='${uploadPath}/"+orderInfo.product.imgurl+"' alt='' class='book_cove' class='btn-reduce'>"
				            	 +"</a>"
				            	 +"<p class='one-more'>"
				            	 +"<span>"+orderInfo.product.title+"</span>"
				            	 +"</p>"
				            	 +"<p>共 "+orderInfo.amount+" 件商品</p>"
				            	 +"<p>价格：<span>￥"+orderInfo.price*orderInfo.amount+"</span></p>"
				            	 +"</section>";
/* 				             payOrderStart+="<section class='shopping-fome'><a href='navigation.htm?newsId="+orderInfo.product.id+"&columnType=product' class='shopping-fome-a'><img src='${uploadPath}/"+orderInfo.product.imgurl+"' alt='' class='book_cove' class='btn-reduce'></a><p><span>"+orderInfo.product.title+"</span></p><p>共 "+orderInfo.amount+" 件商品</p><p>价格：<span>￥"+orderInfo.price+"</span></p></section>"; */
				       	    //$(".shopping-f").append(str);
				       	    }
				       	 });
				       	 //payOrderStart+="<p class='shopp'><span></span><span><a class='wuliu-onclick wuliu-char' onClick=logistics();>查看物流</a></span><span><a class='order-alredy12' onClick=sureSendProduct('"+payOrder.orderNum+"');>确认收货</a></span></p>";
				       	  payOrderStart+="<p class='shopp shopp01'><span></span><span><a class='order-alredy12' onClick=sureSendProduct('"+payOrder.orderNum+"');>确认收货</a></span></p>";
				       	 $(".order-02").append(payOrderStart);
				        });			        							
					  }else{
					  $(".loading").hide();
					     if(flag){
					       var str="<section style='margin-top:50%'><label class='orderTips2'>您没有未收货的订单</label></section>";
					       $(".order-02").append(str);
					     }else{
					      var str="<section class='order-trip-message'>没有更多订单</section>";
					       $(".order-02").append(str);
					     }					  
					  }
					 },
					 error: function(XMLHttpRequest, textStatus, errorThrown) {
					      openwaring("网络出现异常");
					      $(".loading").hide();
					   }
				});
				}

				 //全部订单ajax
				function ajaxAllOrder(pageStart,flag){
				  $.ajax({ 
			      	url:'${ctx}/ajaxMemberCenterAction!allPayOrderByUser.action', 
			      	type:'post', 
			      	dataType: 'json',
			      	data:{
			      		"pageNo":pageStart,
			      	},
					success: function(json) {
					$(".order-trip-message").empty();
					  $("#order-01").html("待付款("+json.count1+")");
					  $("#order-03").html("全部订单("+json.count2+")");
						if (null!=json.payOrders3&&json.payOrders3!="") {
						//$(".order-03").empty();
						$.each(json.payOrders3, function(index, payOrder) {
						var payOrderFlag = true;
						var payOrderStart="<section class='book_detail clearfix shopping-f'>"
							+"<p class='book_name'>"
							+"<span>"+(payOrder.createTime).substring(0,10)+"</span>"
							+"<span>订单号："+payOrder.orderNum+"</span>";
							
						$.each(json.orderInfos3, function(index, orderInfo) {
						   if(payOrder.orderNum==orderInfo.payOrder.orderNum){//判断商品是否被修改1是0否 payOrderStatusMap orderInfoStatusMap
							   if(orderInfo.product.isSale!=1){//判断商品是否上架 1是0否 
								   //判断属性值是否存在
					             	if(null!=json.orderInfoAttrStrMap[orderInfo.productAttrIds]&&json.orderInfoAttrStrMap[orderInfo.productAttrIds]!=""){
					             		payOrderStart+="<a onclick='javascript:return del();' href='epMemberCenterAction!delPayOrderByOrderNumFont.action?payOrder.id="+payOrder.id+"' style='color:#6a2473;width: auto;float: right;padding-top: 1px;font-size: 12px;font-weight: 500;margin-right:0'>删除</a>";
										+"</p>"; 
					             		payOrderStart+="<section class='shopping-fome'>"
									      +"<img src='${uploadPath}/"+orderInfo.product.imgurl+"' alt='' class='book_cove' class='btn-reduce'>"
						            	  +"<p class='one-more'><span>"+orderInfo.product.title+"</span></p>"
						            	  +"<span style='float:right;color:red;margin-top:-5vw;'>已下架</span>"
						            	  +"<p><span style='color: #6C6C6C;'>"+json.orderInfoAttrStrMap[orderInfo.productAttrIds]+"</span></p>"
						            	  +"<p>共 "+orderInfo.amount+" 件商品</p>"
						            	  +"<p>价格：<span>￥"+orderInfo.price*orderInfo.amount+"</span></p>";
						            	 payOrderStart+="<input name='choose' class='selectable' style='display:none' type='checkbox' value="+orderInfo.id+">"
						            	 +"<input type='hidden' class='productPrice' value="+orderInfo.price+">"
						            	 +"<input type='hidden' class='orderNum' value="+payOrder.orderNum+">"
						            	 +"<input type='hidden' class='orderInfoNum' value="+orderInfo.orderinfoNum+">"
						            	 +"<input type='hidden' class='examineNum' value="+orderInfo.productStatus+">";
						             	 +"</section>";
							   		}else{
							   			payOrderStart+="<a onclick='javascript:return del();' href='epMemberCenterAction!delPayOrderByOrderNumFont.action?payOrder.id="+payOrder.id+"' style='color:#6a2473;width: auto;float: right;padding-top: 1px;font-size: 12px;font-weight: 500;margin-right:0'>删除</a>";
										+"</p>"; 
								  		 payOrderStart+="<section class='shopping-fome'>"
									      +"<img src='${uploadPath}/"+orderInfo.product.imgurl+"' alt='' class='book_cove' class='btn-reduce'>"
						            	  +"<p class='one-more'><span>"+orderInfo.product.title+"</span></p>"
						            	  +"<span style='float:right;color:red;margin-top:-5vw;'>已下架</span>";
						            	  +"<p>共 "+orderInfo.amount+" 件商品</p>"
						            	  +"<p>价格：<span>￥"+orderInfo.price*orderInfo.amount+"</span></p>";
						            	 payOrderStart+="<input name='choose' class='selectable' style='display:none' type='checkbox' value="+orderInfo.id+">"
						            	 +"<input type='hidden' class='productPrice' value="+orderInfo.price+">"
						            	 +"<input type='hidden' class='orderNum' value="+payOrder.orderNum+">"
						            	 +"<input type='hidden' class='orderInfoNum' value="+orderInfo.orderinfoNum+">"
						            	 +"<input type='hidden' class='examineNum' value="+orderInfo.productStatus+">";
						             	 +"</section>";
							   		}  
						        }else if(null!=json.orderInfoStatusMap[orderInfo.productAttrIds]&&json.orderInfoStatusMap[orderInfo.productAttrIds]!=""){
						        	payOrderStart+="<a onclick='javascript:return del();' href='epMemberCenterAction!delPayOrderByOrderNumFont.action?payOrder.id="+payOrder.id+"' style='color:#6a2473;width: auto;float: right;padding-top: 1px;font-size: 12px;font-weight: 500;margin-right:0'>删除</a>";
									+"</p>"; 
						        	payOrderStart+="<section class='shopping-fome'>"
									      +"<img src='${uploadPath}/"+orderInfo.product.imgurl+"' alt='' class='book_cove' class='btn-reduce'>"
						            	  +"<p class='one-more'><span>"+orderInfo.product.title+"</span></p>"
						            	  +"<span style='float:right;color:red;margin-top:-5vw;'>已更新</span>"
						            	  +"<p>共 "+orderInfo.amount+" 件商品</p>"
						            	  +"<p>价格：<span>￥"+orderInfo.price*orderInfo.amount+"</span></p>";
								   	 var invoiceNum = orderInfo.payOrder.invoiceNum;
						             if(null!=invoiceNum && ''!=invoiceNum){
						            	 if(payOrderFlag){
						            		 payOrderFlag = false;
							            	 payOrderStart+="<span style='position: absolute;bottom:0;right: 0;'>运货单号："+invoiceNum+"</span>";
						            	 }
						             }
						             payOrderStart+="<input name='choose' class='selectable' style='display:none' type='checkbox' value="+orderInfo.id+">"
						             +"<input type='hidden' class='productPrice' value="+orderInfo.price+">"
						             +"<input type='hidden' class='orderNum' value="+payOrder.orderNum+">"
						             +"<input type='hidden' class='orderInfoNum' value="+orderInfo.orderinfoNum+">"
						             +"<input type='hidden' class='examineNum' value="+orderInfo.productStatus+">";
						             +"</section>";
							   }else{
								   //判断属性值是否存在
								   if(null!=json.orderInfoAttrStrMap[orderInfo.productAttrIds]&&json.orderInfoAttrStrMap[orderInfo.productAttrIds]!=""){
									   if(orderInfo.orderStatus == 0){
										   payOrderStart+="<a onclick='javascript:return del();' href='epMemberCenterAction!delPayOrderByOrderNumFont.action?payOrder.id="+payOrder.id+"' style='color:#6a2473;width: auto;float: right;padding-top: 1px;font-size: 12px;font-weight: 500;margin-right:0'>删除</a>";
											+"</p>"; 
							           }
									   payOrderStart+="<section class='shopping-fome'>"
						            	  /*+"<img src='${imagePath}/icon_choose_no.png' alt='' class='btn_choose' onClick='change_pic(this)'>"*/
						            	  +"<a href='epProductAction!tampons.action?id="+orderInfo.product.id+"' class='shopping-fome-a'>"
						            	  +"<img src='${uploadPath}/"+orderInfo.product.imgurl+"' alt='' class='book_cove' class='btn-reduce'>"
						            	  +"</a>"
						            	  +"<p class='one-more' style='margin-top: -10px;'><span>"+orderInfo.product.title+"</span></p>"
						            	  +"<p><span style='color: #6C6C6C;'>"+json.orderInfoAttrStrMap[orderInfo.productAttrIds]+"</span></p>"
						            	  +"<p>共 "+orderInfo.amount+" 件商品</p>"
						            	  +"<p>价格：<span>￥"+orderInfo.price*orderInfo.amount+"</span></p>";
							        }else{
							        	if(orderInfo.orderStatus == 0){
											   payOrderStart+="<a onclick='javascript:return del();' href='epMemberCenterAction!delPayOrderByOrderNumFont.action?payOrder.id="+payOrder.id+"' style='color:#6a2473;width: auto;float: right;padding-top: 1px;font-size: 12px;font-weight: 500;margin-right:0'>删除</a>";
												+"</p>"; 
								        }
							        	payOrderStart+="<section class='shopping-fome'>"
							            	  /*+"<img src='${imagePath}/icon_choose_no.png' alt='' class='btn_choose' onClick='change_pic(this)'>"*/
							            	  +"<a href='epProductAction!tampons.action?id="+orderInfo.product.id+"' class='shopping-fome-a'>"
							            	  +"<img src='${uploadPath}/"+orderInfo.product.imgurl+"' alt='' class='book_cove' class='btn-reduce'>"
							            	  +"</a>"
							            	  +"<p class='one-more' style='margin-top: -10px;'><span>"+orderInfo.product.title+"</span></p>"
							            	  +"<p>共 "+orderInfo.amount+" 件商品</p>"
							            	  +"<p>价格：<span>￥"+orderInfo.price*orderInfo.amount+"</span></p>";	
							        }	  
						              if(orderInfo.orderStatus == 0){
							             /*  payOrderStart+="<span style='float:right;color:#6a2473;margin-top:-22vw;'>待付款</span>"; */
							              payOrderStart+="<p style='width:100%;' class='shopp shopp01'><span></span><span></span><span><a class='order-alredy' style='display: inline-block; margin-top: 0.5rem;' onclick='submitForm("+payOrder.id+")'> 确认付款</a></span></p>";
						              }
						              if(orderInfo.orderStatus == 1){
						            	  var invoiceNum = orderInfo.payOrder.invoiceNum;
								          if(null!=invoiceNum && ''!=invoiceNum){
							            	 if(payOrderFlag){
							            		 payOrderFlag = false;
								            	 payOrderStart+="<span style='position: absolute;bottom:0;right: 0;'>运货单号："+invoiceNum+"</span>";
							            	 }
								          }
						              }
						              if(orderInfo.orderStatus == 2){
							              payOrderStart+="<span style='float:right;color:#6a2473;margin-top:-22vw;'>已下单</span>";
						              }
		/* 				              payOrderStart+="<section class='shopping-fome'><img src='${imagePath}/icon_choose_no.png' alt='' class='btn_choose' onClick='change_pic(this)'><a href='navigation.htm?newsId="+orderInfo.product.id+"&columnType=product' class='shopping-fome-a'><img src='${uploadPath}/"+orderInfo.product.imgurl+"' alt='' class='book_cove' class='btn-reduce'></a><p><span>"+orderInfo.product.title+"</span></p><p>共 "+orderInfo.amount+" 件商品</p><p>价格：<span>￥"+orderInfo.price+"</span></p>"; */
						            // if(orderInfo.productStatus=='0'){payOrderStart+="<label style='float: right;color: red;'>申请退货中</label>";}
						            // else if(orderInfo.productStatus=='1'){payOrderStart+="<label class='shtg' onClick=getAddress(); style='float: right;color: red;'>审核通过(点击这里)</label>";}
						            // else if(orderInfo.productStatus=='2'){payOrderStart+="<label style='float: right;color: red;'>审核不通过</label>";}
						            // else if(orderInfo.productStatus=='3'){payOrderStart+="<label style='float: right;color: red;'>已退款</label>";}
						            // else if(orderInfo.productStatus=='4'){payOrderStart+="<label style='float: right;color: red;'>已退货</label>";}
						             payOrderStart+="<input name='choose' class='selectable' style='display:none' type='checkbox' value="+orderInfo.id+">"
						             +"<input type='hidden' class='productPrice' value="+orderInfo.price+">"
						             +"<input type='hidden' class='orderNum' value="+payOrder.orderNum+">"
						             +"<input type='hidden' class='orderInfoNum' value="+orderInfo.orderinfoNum+">"
						             +"<input type='hidden' class='examineNum' value="+orderInfo.productStatus+">";
						             +"</section>";
						       	    //$(".shopping-f").append(str);
						       	    //if(orderInfo.evaluateStatus == '0'){payOrderStart+="<a class='order-alredy' href='epMemberCenterAction!goEvaluate.htm?orderInfoId="+orderInfo.id+"'>评论</a></section>";}
									//else if(orderInfo.evaluateStatus == '1'){payOrderStart+="<a class='order-alredy'>已评</a></section>";}
							   }
							}
				       	 });
				       	  //payOrderStart+="<p class='shopp'><span><a class='tuik' onClick=tuikuan(this)>退款/退货</a></span><span></span></p>";
				       	 //payOrderStart+="<p class='shopp'><span><a class='tuik' onClick=tuikuan(this)>退款/退货</a></span>";
				       	 
					       	        	if(payOrder.productDrawback==null||payOrder.productDrawback.status!="1"){
						       	        		if(payOrder.returnStatus == "1"){
							       	        	payOrderStart+="<p class='shopp' id='Peva'><span><a id='Aeva0' class='color-withe' onClick=returnBack('"+payOrder.orderNum+"','"+payOrder.productAmount+"'); id='returnBack'>退货</a></span><span><a id='Aeva0' class='tuik' onClick=exchange('"+payOrder.orderNum+"','"+payOrder.productAmount+"');>换货</a></span>";
						       	        	    }
						       	          }else if(payOrder.productDrawback.returnStatus=="1"){
						       	        	payOrderStart+="<p class='shopp' id='Peva'><span><a id='Aeva2' class='tuik'>申请退货中</a></span><span><a id='Aeva11' class='tuik' onclick='showAddress()'>退换货联系</a></span>";
						       	          }else if(payOrder.productDrawback.returnStatus=="2"){
						       	        	payOrderStart+="<p class='shopp' id='Peva'><span><a id='Aeva1' class='tuik'>退货成功</a></span><a id='Aeva11' class='tuik' onclick='showAddress()'>退换货联系</a></span>";
						       	          }else if(payOrder.productDrawback.returnStatus=="3"){
						       	        	payOrderStart+="<p class='shopp' id='Peva'><span><a id='Aeva1' class='tuik'>退货失败</a></span>";
						       	          }else if(payOrder.productDrawback.returnStatus=="4"){
						       	        	payOrderStart+="<p class='shopp' id='Peva'><span><a id='Aeva2' class='tuik'>申请换货中</a></span><a id='Aeva11' class='tuik' onclick='showAddress()'>退换货联系</a></span>";
						       	          }else if(payOrder.productDrawback.returnStatus=="5"){
						       	        	payOrderStart+="<p class='shopp' id='Peva'><span><a id='Aeva1' class='tuik'>换货成功</a></span><a id='Aeva11' class='tuik' onclick='showAddress()'>退换货联系</a></span>";
						       	          }else if(payOrder.productDrawback.returnStatus=="6"){
						       	        	payOrderStart+="<p class='shopp' id='Peva'><span><a id='Aeva1' class='tuik'>换货失败</a></span>";
						       	          }  
					       	        	if(payOrder.shippingStatus=='1'){ payOrderStart+="<p style='width:100%;' class='shopp shopp01'><span><a id='Aeva1' class='order-alredy' style='display: inline-block; margin-top: 0.5rem; color:white; border:none;float:right;width: 22%;padding: 2.5% 0;margin-right: 0;' onClick=sureSendProduct('"+payOrder.orderNum+"');>确认收货</a></span></p>";}
								       	if(payOrder.shippingStatus=='2'){ payOrderStart+="<span><a style='color:#333; float: right;background-color:#fff; '>已收货</a></span>";
				       	         
				       	 }
				       	 payOrderStart+="</p>";
/* 				       	  payOrderStart+="<p class='shopp'><span><a class='tuik' onClick=tuikuan(this)>退款/退货</a></span><span><a onClick=logistics();>查看物流</a></span><span></span></p>"; */
				       	 $(".order-03").append(payOrderStart);
				       	 $(".loading").hide();
				       	 return "21";
				        });			        							
					  }else{	
					  $(".loading").hide();
						if(flag){
						    var str="<section style='margin-top:50%'><label class='orderTips3'>您没有已收货的订单</label></section>";
							$(".order-03").append(str);	
						}else{
						   var str="<section class='order-trip-message'>没有更多订单</section>";
					       $(".order-03").append(str);
						}
					  }
					 },
					 error: function(XMLHttpRequest, textStatus, errorThrown) {
					      openwaring("网络出现异常");
					      $(".loading").hide();
					   }
				});
				}
</script>
<body class='cart ba-co'>
			<!-- header -->
			<header class='clearfix close-bottom' >
					<a href="epMemberCenterAction.action"><img src="${imagePath}/add-left.png" alt="" id="close-bottom-img"></a>
					我的订单
					<!-- <input type="hidden" class="decideNum" value="${count3}"> -->
			</header>
			<!-- header end -->
			<!-- content -->
			<form class="car-form" id="shopping-cart-list ">
				<!-- content header -->
				
				<section class="order-header">
						<section class="order-header-choose"><p><a style="text-decoration:none" id="order-01">待付款(${count1})</a></p></section>
						<%-- <section><p><a id="order-02" >未发货(${count2})</a></p></section> --%>
						<section><p><a id="order-03" >全部订单(${count2 }) </a></p></section>
				</section>  
				<!-- content header end -->
				<!-- 待付款 -->
				
				<section class="order-01" style="display: block;">   
						<!-- 订单 -->
					<c:if test="${not empty payOrders1}">
					  <c:forEach items="${payOrders1}" var="payOrder" varStatus="status">
						<section class="book_detail clearfix shopping-f">
							<p class="book_name"><span>${fn:substringBefore(payOrder.createTime, " ")}</span><span>订单号：${payOrder.orderNum}</span>
							<a  onclick='javascript:return del();' href="epMemberCenterAction!delPayOrderByOrderNumFont.action?payOrder.id=${payOrder.id}" style="color:#6a2473;width: auto;float: right;padding-top: 1px;font-size: 12px;font-weight: 500;margin-right:0">删除</a>
							</p>
						<c:forEach items="${orderInfos1}" var="orderInfo" varStatus="status">
						 <c:choose>
						 	<c:when test="${orderInfo.product.isSale!=1}">
						 		<c:if test="${payOrder.orderNum eq orderInfo.payOrder.orderNum}">
								<section class='shopping-fome'>
								<!-- <img src="${imagePath}/icon_choose_no.png" alt="" class='btn_choose' onClick='change_pic(this)'> -->
								<%-- <a href="navigation.htm?newsId=${orderInfo.product.id}&columnType=product" class="shopping-fome-a"> --%>
								<img src="${uploadPath}/${orderInfo.product.imgurl}" alt="" class='book_cove' class="btn-reduce">
								<p class="one-more"><span>${orderInfo.product.title}</span></p>
								<span style="float:right;color:red;margin-top:-5vw;">已下架</span>
								<c:if test="${not empty orderInfoAttrStrMap[orderInfo.productAttrIds]}">
									<p><span style="color: #6C6C6C;">${orderInfoAttrStrMap[orderInfo.productAttrIds]}</span></p>
								</c:if>
								
								<p>共  ${orderInfo.amount} 件商品</p>
								<p>价格：<span>￥<fmt:formatNumber type="number" value="${orderInfo.price*orderInfo.amount}" pattern="0.00" maxFractionDigits="2"/></span></p>
								</section>
						 		</c:if>
						 	</c:when>
						 	<c:when test="${not empty orderInfoStatusMap[orderInfo.productAttrIds]}">
						 		<c:if test="${payOrder.orderNum eq orderInfo.payOrder.orderNum}">
								<section class='shopping-fome'>
								<!-- <img src="${imagePath}/icon_choose_no.png" alt="" class='btn_choose' onClick='change_pic(this)'> -->
								<%-- <a href="navigation.htm?newsId=${orderInfo.product.id}&columnType=product" class="shopping-fome-a"> --%>
								<img src="${uploadPath}/${orderInfo.product.imgurl}" alt="" class='book_cove' class="btn-reduce">
								<p class="one-more"><span>${orderInfo.product.title}</span></p>
								<span style="float:right;color:red;margin-top:-5vw;">已更新</span>
								<p>共  ${orderInfo.amount} 件商品</p>
								<p>价格：<span>￥<fmt:formatNumber type="number" value="${orderInfo.price*orderInfo.amount}" pattern="0.00" maxFractionDigits="2"/></span></p>
								</section>
						 		</c:if>
						 	</c:when>
						 	<c:otherwise>
						 		<c:if test="${payOrder.orderNum eq orderInfo.payOrder.orderNum}">
								<section class='shopping-fome'>
								<!-- <img src="${imagePath}/icon_choose_no.png" alt="" class='btn_choose' onClick='change_pic(this)'> -->
								<a href="epProductAction!tampons.action?id=${orderInfo.product.id}" class="shopping-fome-a">
								<%-- <a href="navigation.htm?newsId=${orderInfo.product.id}&columnType=product" class="shopping-fome-a"> --%>
								<img src="${uploadPath}/${orderInfo.product.imgurl}" alt="" class='book_cove' class="btn-reduce"></a>
								<p class="one-more" style="margin-top: -10px;"><span>${orderInfo.product.title}</span></p>
								<c:if test="${not empty orderInfoAttrStrMap[orderInfo.productAttrIds]}">
									<p><span style="color: #6C6C6C;">${orderInfoAttrStrMap[orderInfo.productAttrIds]}</span></p>
								</c:if>
								<p>共  ${orderInfo.amount} 件商品</p>
								<p>价格：<span>￥<fmt:formatNumber type="number" value="${orderInfo.price*orderInfo.amount}" pattern="0.00" maxFractionDigits="2"/></span></p>
								</section>
						 		</c:if>
						 	</c:otherwise>
						 </c:choose>
						 
						</c:forEach>
						<!-- <p class="shopp shopp01"><span></span><span></span><span><a class="order-alredy" href="epPayAction.htm?pageType=goPay&payType=1"> 确认付款</a></span></p> -->
						<c:if test="${empty payOrderStatusMap[payOrder.orderNum]}">
							
								<p class="shopp shopp01"><span></span><span></span><span><a class="order-alredy" onclick="submitForm(${payOrder.id})"> 确认付款</a></span></p>
							
						</c:if>
						</section>
					</c:forEach>
					</c:if>
					<c:if test="${empty payOrders1}"><section style="margin-top:50%"><label class="orderTips1">您没有待付款的订单</label></section></c:if>
						<!-- 订单 end -->
				</section>
				<script type="text/javascript">
						function submitForm(payOrderId){
								$.ajax({ 
						      		url:'ajaxEpPayAction!goPay.htm?math='+Math.random(), 
						      		type:'post', 
						      		data:{
						      			payOrderId:payOrderId,
								        payType:1
						      		},
						      		dataType:'json',
						      		success:function(data){
						      			if(data!=null && data.payType==1){
						      				var obj = eval('(' + data.json + ')');
						      				console.log(data.json);
						          			WeixinJSBridge.invoke('getBrandWCPayRequest',obj,function(res){
						          				switch (res.err_msg){ 
						          	            case 'get_brand_wcpay_request:ok':  
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
						}
				</script>
				 <!-- 待付款 end -->
				 <!-- 待收货 -->
				
				<section class="order-02">  
						<!-- 订单 -->
						<!--  <c:if test="${not empty payOrders2}">
						<c:forEach items="${payOrders2}" var="payOrder" varStatus="status">
						<section class="book_detail clearfix shopping-f">
							<p class="book_name"><span>${fn:substringBefore(payOrder.createTime, " ")}</span><span>订单号：${payOrder.orderNum}</span></p>
						<c:forEach items="${orderInfos2}" var="orderInfo" varStatus="status">
						 <c:if test="${payOrder.orderNum eq orderInfo.payOrder.orderNum}">
							<section class='shopping-fome'>
								<img src="${uploadPath}/${orderInfo.product.imgurl}" alt="" class='book_cove' class="btn-reduce">
								<p><span>${orderInfo.product.title}</span></p>
								<p>共  ${orderInfo.amount} 件商品</p>
								<p>价格：<span>￥${orderInfo.price}</span></p>
								<input name="choose"  class="selectable" style="display:none" type="checkbox" value="${orderInfo.id}">
							</section>
						 </c:if>
						</c:forEach>
							<p class="shopp"><span></span><span><a class="wuliu-onclick">查看物流</a></span><span><a href="epMemberCenterAction!editReceiveShippingType.action?payOrder.orderNum=${payOrder.orderNum}" class="order-alredy12">确认收货</a></span></p>
							<input type="hidden" class="receive-product" value="${message}">-->
							
						<!--</section>
						</c:forEach>
						</c:if>-->
					   <!-- <c:if test="${empty payOrders2}"><section style="margin-top:50%"><label class="orderTips2" style="display: none;">您没有未收货的订单</label></section></c:if>-->
						<!-- 订单 end -->
				 </section>
			
				 <!-- 待收货 end -->
				 <!-- 全部订单 -->
				 
				 <section class="order-03">  
						<!-- 订单 -->
						<!--  <c:if test="${not empty payOrders3}">
						<c:forEach items="${payOrders3}" var="payOrder" varStatus="status">
						<section class="book_detail clearfix shopping-f">
							<p class="book_name"><span>${fn:substringBefore(payOrder.createTime, " ")}</span><span>订单号：${payOrder.orderNum}</span></p>
						<c:forEach items="${orderInfos3}" var="orderInfo" varStatus="status">
						 <c:if test="${payOrder.orderNum eq orderInfo.payOrder.orderNum}">
							<section class='shopping-fome'>
								<img src="${imagePath}/icon_choose_no.png" alt="" class='btn_choose' onClick='change_pic(this)'>
								<img src="${uploadPath}/${orderInfo.product.imgurl}" alt="" class='book_cove' class="btn-reduce">
								<p><span>${orderInfo.product.title}</span></p>
								<p>共  ${orderInfo.amount} 件商品</p>
								<p>价格：<span>￥${orderInfo.price}</span></p>
								<c:if test="${orderInfo.productStatus eq '0'}"><label style="float: right;color: red;">申请退货中</label></c:if>
								<c:if test="${orderInfo.productStatus eq '1'}"><label class="shtg" style="float: right;color: red;">审核通过(点击这里)</label></c:if>
								<c:if test="${orderInfo.productStatus eq '2'}"><label style="float: right;color: red;">审核不通过</label></c:if>
								<c:if test="${orderInfo.productStatus eq '3'}"><label style="float: right;color: red;">已退款</label></c:if>
								<c:if test="${orderInfo.productStatus eq '4'}"><label style="float: right;color: red;">已退货</label></c:if>
								<input name="choose" class="selectable" style="display:none" type="checkbox" value="${orderInfo.id}">
								<input type="hidden" class="productPrice" value="${orderInfo.price}">
								<input type="hidden" class="orderNum" value="${payOrder.orderNum}">
								<input type="hidden" class="orderInfoNum" value="${orderInfo.orderinfoNum}">
								<input type="hidden" class="examineNum" value="${orderInfo.productStatus}">
								<c:if test="${orderInfo.evaluateStatus eq '0'}">
								<a class="order-alredy" href="epMemberCenterAction!goEvaluate.htm?orderInfoId=${orderInfo.id}">评论</a>
								</c:if>
								<c:if test="${orderInfo.evaluateStatus eq '1'}">
								<a class="order-alredy">已评</a>
								</c:if>
							</section>
						 </c:if>
						</c:forEach>
						<p class="shopp"><span><a class="tuik">退款/退货</a></span><span><a>查看物流</a></span><span></span></p>
						</section>
						</c:forEach>
						</c:if>-->
					    <c:if test="${empty payOrders3}"><section style="margin-top:50%"><label class="orderTips3" style="display: none;">您没有已收货的订单</label></section></c:if>
						<!-- 订单 end -->
				 </section>
				 <!-- 全部订单 end -->
			</form>
			<!-- content end -->
            <!-- 审核弹窗 -->
            <section class='order-layer'><p>审核通过</p><p>提示：收到退货后，给予退款，请将退货商品快递到以下<br>地址：<label class='drawAddress'></label><span>祝您购物愉快！</span><p><img src='${imagePath}/address_tp.png' class='order-p-close'></section>
            <!-- 审核弹窗end -->
			
			<!-- 批量收货 -->
			<!-- <section class="account">
					<img src="${imagePath}/icon_choose_no.png" alt="" onClick='change_all(this)'>
					<span>全选</span>
					<span class='jiesuan-order'>批量收货</span>
			</section> -->
			<!-- 批量收货 end -->
			<section class="messageTrips" style="position: fixed;top: 10.3%;background: #e7e7e7;color:red;text-align: center;width: 100%;display: none;z-index: 9999"></section>
			<section class="bg"></section>
			<section class="loading" style="text-align: center; margin-top:-70px;"><img src="${imagePath}/loading.gif" style="width:20px;height:20px"></section>
			
			<!-- 退款弹出 -->
			<section class="tk-hide">
				<section class="tuikuan">
					<img class="close-car-order" src="${imagePath}/tuikuan01.png" alt="" title="">
			    	<form action="epMemberCenterAction!returnedPurchase.action" method="post" id="returnedPurchase">
						<p class="tuikuan-p">退货</p>
							<section>
									<li><label for="order-num">订单号：</label></li>
									<li><label for="order-money" class="payOrderAmountTitle">申请金额：</label></li>
									<li class="returnedReason">退款原因：</li>
									
							</section>
							<section>
									<li><input type="text" value="" id="payOrderNum" onfocus="this.blur()" name="orderNum"></li>
									<li><input type="text" value="" id="payOrderAmount" class="payOrderAmount" onfocus="this.blur()"></li>
									<li>
										<p>
											<input name="reason" type="checkbox" value="破损" />&nbsp破损
										</p>
										<p>
											<input name="reason" type="checkbox" value="货不对板" />&nbsp货不对板
										</p>
										<p>
											<input name="reason" type="checkbox" value="尺码不符" />&nbsp尺码不符
										</p>
										<p>
											<input name="reason" type="checkbox" value="颜色不符" />&nbsp颜色不符
										</p>
									</li>
							</section>
							<section>
									<textarea class="bill-input" name="otherReason" placeholder="其他/填写原因" cols="" rows="" value=""></textarea>
							</section>
							<!-- <section>
									<section class="tuikuan-images"><a href="javascript:void(0)"><input type="file" id="upload" name="upload" size="30" onChange="doSelectPhotoChanged('upload');"/>上传图片</a></section>
									<section>
										拍照上传货物图片<br>
										<span>大小不得超过200KB</span>
									</section>
							</section> --> 
							<section>
									<p class="tuikuan-a" onclick="confirmSumit()"><a class="tuikuan-confirm">确认</a></p>
							</section>
							<input type="hidden" name="flag" id="flag" value="0" >
							<input type="hidden" value="" class="payOrderAmount"  name="payOrderAmount">
						</form>
				</section>
			</section>
			<!-- 退款弹出 end-->
			
			
			
			<!-- 退换货地址弹出 -->
			<section class="tk-address" style="display:none;">
				<img src="${imagePath}/sanjiao.png" class="order-sanjiao">
				<section class="tuikuan">
						<p class="tuikuan-p">退换货联系</p>
							<section>
									<li><label for="order-num">联系人</label></li>
									<li><label for="order-money" class="payOrderAmountTitle">微信</label></li>
									<li class="returnedReason">联系电话</li>
									
							</section>
							<section>
									<li>${parameterSet.sellerName } </li>
									<li>${parameterSet.sellerAddress }</li>
									<li>${parameterSet.sellerPhone }</li>
							</section>
							<!-- <section>
									<section class="tuikuan-images"><a href="javascript:void(0)"><input type="file" value=" "/>上传图片</a></section>
									<section>
										拍照上传货物图片<br>
										<span>大小不得超过200KB</span>
									</section>
							</section> -->
							<section style="margin-top:10%;">
									<p class="tuikuan-a" onclick="hideAddress()"><a>关闭</a></p>
							</section>
				</section>
			</section>
			<!-- 退换货地址弹出 end-->
	</body>
<script type="text/javascript">
function confirmSumit(){
	$(".tuikuan-confirm").html("提交中....");
	$(".tuikuan-a").attr("disabled","true");
	$(".tuikuan-a").css("background","#787878");
	$('#returnedPurchase').submit();
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
			}
			
			function change_pic(obj){
				var srvName =  $(obj).attr('src');
				if (srvName.indexOf('icon_book_choose.png') != -1 ) {
				   // $('.btn_choose').attr('src','${imagePath}/icon_book_choose.png');
					$(obj).attr('src','${imagePath}/icon_choose_no.png');
				} else{
				    //$('.btn_choose').attr('src','${imagePath}/icon_choose_no.png');
				    $(obj).parent().parent().find(".btn_choose").attr('src','${imagePath}/icon_choose_no.png')
					$(obj).attr('src','${imagePath}/icon_book_choose.png');
				}
				selectItemClick($(obj).siblings(".selectable"));
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
				    $(obj).parent().parent().find(".selectable").prop("checked", false);
					$(obj).prop("checked", true);
				} else {
					$(obj).prop("checked", false);
				} 
			}
			
			function change_draw(obj){
				var srvName =  $(obj).attr('src');
				if (srvName.indexOf('icon_book_choose.png') != -1 ) {
				   // $('.btn_choose').attr('src','${imagePath}/icon_book_choose.png');
					$(obj).attr('src','${imagePath}/icon_choose_no.png');
				} else{
				    //$('.btn_choose').attr('src','${imagePath}/icon_choose_no.png');
					$(obj).attr('src','${imagePath}/icon_book_choose.png');
				}
				selectItemDraw($(obj).siblings(".selecdraw"));
			}
			
			function selectItemDraw(obj) {
				var subchecked = $(obj).prop("checked");
				var selectClass = $(obj).prop("class");
				if (!subchecked) {
				   // $(".selectable").prop("checked", false);
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
			
			//图片上传
			function doSelectPhotoChanged(fileNameId,upLoadFileId,imageId) {
		        $.ajaxFileUpload({
		            url: '${ctx}/ajaxepUploadImage!ajaxUploadImage.htm', 
		            type: 'post',
		            secureuri: false, //一般设置为false
		            fileElementId: upLoadFileId, // 上传文件的id、name属性名
		            dataType: 'json', //返回值类型，一般设置为json、application/json
		            success: function(data, status){
		                //$('#'+upLoadFileId).val(data);
		                //alert(data);
		                $('#'+fileNameId).val(data);
						//$('#'+imageId).attr('src','${ctx}/upload/enterprice/'+data);
						$('#'+imageId).css("background-image","url("+'${ctx}/upload/enterprice/'+data+")");
		            },
		            error: function(data, status, e){ 
		                alert(e);
		            }
		        });
		        //return false;
		 }

</script>
<script type="text/javascript">
    	   $("#payOrder").click(function(){
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
   	          	           		layerOpen("支付成功", "javascript:void(0)");
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
   		
    	   })
</script>
<script>
		//当选择图片时发生
		function doSelectPhotoChanged(elepath){
			filePath = document.getElementById(elepath).value;
			
			if(checkFileUpload(elepath)){
				if(window.navigator.userAgent.indexOf("Chrome")<1){
					/*document.getElementById(elepath).value = "";
					document.getElementById("imgShowTeacherPhoto").src=filePath;
					document.getElementById("imgchange").rel=$("#imgShowTeacherPhoto").attr("src");*/
				}
			}
		}
		
		//文件上传验证
		function checkFileUpload(elepath)
		{
		  var path = document.getElementById(elepath).value;
		  if(window.navigator.userAgent.indexOf("Chrome")<1){
		  	   if(path.indexOf(".jpg")<1 && path.indexOf(".gif")<1 &&  path.indexOf(".bmp")<1 &&  path.indexOf(".png")<1 && path.indexOf(".jpeg")<1){
		    		alert('图片类型不正确，请上传正确的格式！\n(jpg,gif,bmp,png,jpeg)');
		  	   		cleanFilePath(elepath);
			   		return false;
		  	   }
		  }else{
			  if(!sy_FilterFile(path,"jpg,gif,bmp,png,jpeg")){
		   		alert('图片类型不正确，请上传正确的格式！\n(jpg,gif,bmp,png,jpeg)');
			    cleanFilePath(elepath);
			    return false;
			  }
			  if(sy_GetImageToGetSize(path).fileSize>1024000){
		    	alert('上传文件大小不能够超过1MB！');
			    cleanFilePath(elepath);
			    return false;
			  }
		  }
		  return true;
		}
		//提交表单前验证准备上传的文件
		function doBeforeSubmit(){
			if(document.getElementById("upload").value!=""){
				if(!checkFileUpload()){
					return false;
				}
			}
		}
		//验证上传文件错误后删除file框内路径
		function cleanFilePath(elepath){
			if(window.navigator.userAgent.indexOf("MSIE")<1){
				$("#"+elepath).attr("value","");
			}else{
				var file = $("#"+elepath);
				file.after(file.clone().val(""));
				file.remove();
			}
		}
		</script>
	<script src="${jsPath}/style.js"></script>