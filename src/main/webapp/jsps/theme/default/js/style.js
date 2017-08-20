$(document).ready(function(){
		
		var window_h = window.screen.availHeight;
		var header_h = $('#clearfix').height();
		var footer_h = $('#index_footer').height();
		var left_h=window_h-header_h-(footer_h-4);
		$('.s_s_p2 span').click(function(){
			$(this).addClass('s_s_p2_color').siblings().removeClass('s_s_p2_color');
		});			
		$('.s-c-l').css('height',left_h);
		
		$('.red-footer').css('bottom',footer_h+10);

		$('.shop, .free-product-pay').click(function(){
			$('.shopping_pa_hide').show();
			$('.bg').show();
			var s_s_img=$('.s_s01_add_img').height();	
			$('.s-s-input').css('height',s_s_img);
			$('#buyType').val("buyNow");
		});
		/*$('.buy').click(function(){
			$('.shopping_pa_hide').show();
			$('.bg').show();
			var s_s_img=$('.s_s01_add_img').height();	
			$('.s-s-input').css('height',s_s_img);
			$('#buyType').val("addCar");
		});*/
		$('.shopping_shopping_img').click(function(){
			$('.shopping_pa_hide').hide();
			$('.bg').hide();
		});
		$('#returnBack').click(function(){
			alert("122222");
			var order_images=$('.tuikuan-images').width();
			$('.tuikuan-images').css('height',order_images);
			$('.tuikuan-images').css('lineHeight',order_images+'px');
	   });
		

		$('.order-p-close,.close-car-order').click(function(){
			$('.yes-order,.order-layer,.wl-hide,.tk-hide').hide();
			$('.bg').hide();
		});

});
        //点击弹出退换货地址
        function showAddress(){
        	$('.bg').show();
        	$('.tk-address').show();
        }
        //点击关闭退换货地址
        function hideAddress(){
        	$('.bg').hide();
        	$('.tk-address').hide();
        }
        //点击弹出退换申请
		function returnBack(orderNum,productAmount){
			$('.bg').show();
			$('#flag').val(0);
			$(".tuikuan-p").html("退货");
			$(".returnedReason").html("退货原因");
		    $('#payOrderAmount').show();
		    $('.payOrderAmountTitle').show();
            $('#payOrderNum').val(orderNum);
            $('.payOrderAmount').val(productAmount);
			$('.tk-hide').show();
			var order_images=$('.tuikuan-images').width();
			$('.tuikuan-images').css('height',order_images);
			$('.tuikuan-images').css('lineHeight',order_images+'px');
		}
		 //点击弹出换货申请
		function exchange(orderNum,productAmount){
			$('.bg').show();
			$('#flag').val(1);
			$(".tuikuan-p").html("换货");
			$(".returnedReason").html("换货原因");
		    $('#payOrderAmount').hide();
		    $('.payOrderAmountTitle').hide();
			$('#payOrderNum').val(orderNum);
			$('.payOrderAmount').val(productAmount);
			$('.tk-hide').show();
			var order_images=$('.tuikuan-images').width();
			$('.tuikuan-images').css('height',order_images);
			$('.tuikuan-images').css('lineHeight',order_images+'px');
		}
		
$(document).ready(function(){
		$('#s-p-02').click(function(){
			$('.s-p-5').show();
			$(".s-p-8").hide();
			$('.s-p-4').hide();
			$(this).addClass("s-p-o");
			$("#s-p-01,#s-p-03").removeClass("s-p-o");
		});	
		$('#s-p-01').click(function(){
			$('.s-p-5').hide();
			$('.s-p-8').hide()
			$('.s-p-4').show();
			$(this).addClass("s-p-o");
			$("#s-p-02,#s-p-03").removeClass("s-p-o");
		});
		$('#s-p-03').click(function(){
			$('.s-p-5').hide();
			$('.s-p-8').show()
			$('.s-p-4').hide();
			$(this).addClass("s-p-o");
			$("#s-p-01,#s-p-02").removeClass("s-p-o");
		});	
	    $('#order-01').click(function(){
			$('.order-01').show();
			$(".order-02").hide();
			$('.order-03').hide();
			$(this).parent().parent().addClass("order-header-choose");
			$("#order-02,#order-03").parent().parent().removeClass("order-header-choose");
			$(".orderTips1").css("display","block");
			$(".orderTips2").css("display","none");
			$(".orderTips3").css("display","none");
		});	
		$('#order-02').click(function(){
			$('.order-02').show();
			$(".order-01").hide();
			$('.order-03').hide();
			$(this).parent().parent().addClass("order-header-choose");
			$("#order-01,#order-03").parent().parent().removeClass("order-header-choose");
			$(".orderTips2").css("display","block");
			$(".orderTips1").css("display","none");
			$(".orderTips3").css("display","none");
		});	
		$('#order-03').click(function(){
			$('.order-03').show();
			$(".order-02").hide();
			$('.order-01').hide();
			$(this).parent().parent().addClass("order-header-choose");
			$("#order-02,#order-01").parent().parent().removeClass("order-header-choose");
			$(".orderTips3").css("display","block");
			$(".orderTips2").css("display","none");
			$(".orderTips1").css("display","none");
		});	
});

$(document).ready(function(){
	var header_h=$("#clearfix").height();
	var ifa_h=$('.index-content-first img').height();
	var ifa_w=$('.index-content-first img').width()*1.3;
	var s_p_h=$('.close-bottom').get(0).offsetHeight;
	var jian_icon=$('.car-icon').height();
	var red=$('.clearfix').height()+$('.red-p-header').height();
	var s02=$("#shop-center-p").height();
	var slideBox_imgh = $('.slideBox .bd li img').width();

	$('.slideBox .bd li img').css('height',slideBox_imgh*0.7);
	$('.jian-icon').css('width',jian_icon);
	$('.jian-icon').css('height',jian_icon);
	$('.store').css('marginTop',header_h);
	$('.index-content-a,.index-content-first img').css('height',ifa_w);
	$("#s-p-1").css('marginTop',s_p_h);
	$(".e-ad").css('marginTop',s_p_h);
	$(".address-top,.red-p-header").css('marginTop',s_p_h);
	$(".evalvate-top,.order-form,.problem,.give-gifts-content").css('marginTop',s_p_h+10);
	//$(".close-bottom").css('lineHeight',s_p_h+"px");
	$(".bg").css('height',screen.availHeight+200);
	$('.red-content').css('top',red+10);
	$('.web03_content').css('top',s02);

});
$(document).ready(function(){
	$('.sh-a1').click(function(){
		$(this).parent().addClass('sh-li-color').siblings().removeClass('sh-li-color');
		if($(this).siblings(".shopping-two").is(":hidden")){
			$(".shopping-two").hide();
			$(this).siblings(".shopping-two").show();
			$('.web03_content').hide();
		}else{
			$(".shopping-two").hide();
			$('.web03_content').show();
		}
	});
	$('.recharge-img').click(function(){
		$('.recharge-img').attr('src','/distri/jsps/theme/default/image/recharge02.png');
		$(this).attr('src','/nlmt/jsps/distri/default/image/recharge01.png');
	});
	
});

//退款
//$('.order-03.tuik').click(function(){
function tuikuan(obj){
	var order_images=window.screen.availWidth*0.18;
	$('.tuikuan-images').css('height',order_images);
	$('.tuikuan-images').css('lineHeight',order_images+'px');
	$('.tuikuan-images').css('backgroundSize',order_images+'px');
    var flag;
	$(obj).parent().parent().parent().find(".selectable").each(function(){
		if($(this).prop("checked")){
    		var examimeNum=$(this).siblings(".examineNum").val();
			if ((examimeNum!="6")&&(examimeNum!="5")&&(examimeNum!="3")&&(examimeNum!="2")&&(examimeNum!="1")&&(examimeNum!="0")) {
				$("#select-orderInfoId").attr("value",$(this).val());
				$("#order-money").attr("value",$(this).siblings(".productPrice").val());
				$("#order-num").attr("value",$(this).siblings(".orderNum").val());
				$('.yes-order,.tk-hide').show();
				$('.bg').show();
				$('.shopping_pa_hide').hide();
				  flag=false;
		          return false;
			} else {
				$(".messageTrips").show();
				if ((examimeNum=="3")){
					$(".messageTrips").html("该商品已退款");
				}
				else if ((examimeNum=="2")){
					$(".messageTrips").html("该商品审核不通过");
				}
				else if ((examimeNum=="1")){
					$(".messageTrips").html("该商品已审核通过");
				}
				else if ((examimeNum=="0")){
					$(".messageTrips").html("该商品已在申请中");
				}
				else if ((examimeNum=="5")){
					$(".messageTrips").html("试穿商品不可退");
				}
				else if ((examimeNum=="6")){
					$(".messageTrips").html("奖品不可退");
				}
				$(".messageTrips").fadeOut(3000);
				flag=false;
				return false;
				}					
			}else{
			   flag=true;				
			}
		});
		if (flag) {
			//alert("请勾选退货商品");
			$(".messageTrips").show();
			$(".messageTrips").html("请勾选退货商品");
			$(".messageTrips").fadeOut(5000);
		}
}
	
		function tabList(obj,bg){
			$(document).ready(function(){		  
			  $('.'+obj.id+'_content').show().siblings().hide();
			  $('.web03_content').show();
			});
			$('.'+obj.id+'_content','.'+obj.id+'_product').siblings().hide();
			$('.s-c-l').show();
			$(obj).addClass(bg);
			$(obj).siblings().removeClass(bg);
		}

		function tabthird(obj){
			$(obj).parent().parent().parent().hide();
			$('.'+obj.id+'_content').show();
		}

	/*物流弹出*/
	function logistics(){
		$('.wl-hide').show();
		$('.bg').show();
	}

		
	
		/*会员中心*/
		$(function(){
			//$('.fenxiao-tab ul li a').each(function(index) {
				//$(this).click(function(){
					//$('.fenxiao-tab ul li a.first-active').removeClass('first-active');
					//$(this).addClass('first-active');
					//$(".tab-content section:eq("+index+")").show().siblings().hide();
				//})
			//});
		})
		

		/*会员特权介绍*/
		$(function(){
			$('#vip-intro li a').each(function(index) {
				$(this).click(function(){
					$('#vip-intro li a.first-active').removeClass('first-active');
					$(this).addClass('first-active');
					$("#fenxiao-content .f-c:eq("+index+")").show().siblings().hide();
				})
			});
		})

		
		//我的分销-我的产品之弹窗	
		$(function(){
			$('.share').click(function(){
				$('.alert-share').show();
			})
			$('.alert-share').click(function(){
				$(this).hide();
			})
		})	
		//我的分销,积分明细

		$(function(){
			$('.fenxiao li a').each(function(index) {
				$(this).click(function(){
					//$('.fenxiao li a.bg-ffbeb1').removeClass('bg-ffbeb1');
					//$(this).addClass('bg-ffbeb1');
					//$(".fenxiao-content .f-c:eq("+index+")").show().siblings().hide();
				})
			});
		})
	

	
	
	
	
	
	
	
	
	
	