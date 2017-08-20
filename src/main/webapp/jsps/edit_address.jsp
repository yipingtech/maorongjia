<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
		<meta charset="utf-8">
		<meta name="Keywords" content="">
		<meta name="description" content="">
		<meta name="author" content="candyxue">

		<meta name="viewport" content="initial-scale=1.0, user-scalable=no,width=device-width,minimum-scale=1.0,maximum-scale=1.0" />

		<meta name="format-detection" content="telephone=no,email=no"/>
		<meta name="apple-mobile-web-app-capable" content="yes" />
		<meta name="apple-mobile-web-app-status-bar-style" content="black" />
		<title></title>
		<link rel="stylesheet" type="text/css" href="${imagePath}/style.css">
		<link rel="stylesheet" type="text/css" href="${imagePath}/bill-style.css">	
		<link href="${ctx}/js/validate/jquery.validate.css" type="text/css"
			rel="stylesheet" />
		<script src="${ctx}/js/jquery.js" type="text/javascript"></script>
		<script src="${ctx}/js/validate/jquery.validate.js"
					type="text/javascript"></script>
		<script src="${ctx}/js/validate/messages_cn.js" type="text/javascript"></script>
		<script type="text/javascript">
		$(document).ready(function(){
		
		 	$("#province").change(function(){
						$("#city").empty();
						$("#area").empty();
			  			$("#city").append("<option value='' selected='selected'>请选择</option>");
			  			$("#area").append("<option value=''>请选择</option>");
			  			if($(this).val()!=''){
				  			if($(this).val()=='32'||$(this).val()=='33'||$(this).val()=='34'){
				  				$("#city").empty();
				  				$("#area").empty();
				  				$("#city").attr("disabled",true); 
				  				$("#area").attr("disabled",true); 
				  			}else{
				  				$.ajax({ 
						      		url:'${ctx}/ajaxAddressAction!findCityByProvince.htm', 
						      		type:'post', 
						      		dataType: 'json',
						      		data:{
						      			"province_id":$(this).val()
						      		},
								    success: function(json) {
								    	$("#city").attr("disabled",false); 
						      			$.each(json, function(index, value) {
							      			$("#city").append("<option value='"+value.id+"'>"+value.city+"</option>");
						      			});
								    },
								    error: function(XMLHttpRequest, textStatus, errorThrown) {
								       openwaring("网络出现异常");
								    }
								});
				  			}
		 				}
			  			/* if($(this).val()!=''){
							$.ajax({ 
					      		url:'${ctx}/ajaxAddressAction!findCityByProvince.htm', 
					      		type:'post', 
					      		dataType: 'json',
					      		data:{
					      			"province_id":$(this).val()
					      		},
							    success: function(json) {
					      			$.each(json, function(index, value) {
						      			$("#city").append("<option value='"+value.id+"'>"+value.city+"</option>");
					      			});
							    },
							    error: function(XMLHttpRequest, textStatus, errorThrown) {
							       openwaring("网络出现异常");
							    }
							});
						} */
					});
		       
		        $("#city").change(function(){
						$("#area").empty();
			  			$("#area").append("<option value=''>请选择</option>");
						if($(this).val()!=''){
							$.ajax({ 
					      		url:'${ctx}/ajaxAddressAction!findAreaByCity.htm', 
					      		type:'post', 
					      		dataType: 'json',
					      		data:{
					      			"city_id":$(this).val()
					      		},
							    success: function(json) {
					      			$.each(json, function(index, value) { 
						      			$("#area").append("<option value='"+value.id+"'>"+value.area+"</option>");
					      			});
							    },
							    error: function(XMLHttpRequest, textStatus, errorThrown) {
							       openwaring("网络出现异常");
							    }
						});
					}
				});	
		        
		        jQuery.validator.addMethod("specialCharValidate", function(value, element) { 
		     		var pattern = new RegExp("[`~!@%#$^&*()=|{}':;',　\\[\\]<>/? \\.；：%……+￥（）【】‘”“'。，、？123456789]"); 
		     		return this.optional(element)||!pattern.test(value) ; 
		     		}, jQuery.format("不能有非法字符或数字")); 
		        
		     // 手机号码验证 
			 	jQuery.validator.addMethod("isMobile", function(value, element) { 
			 	  var length = value.length; 
			 	  var mobile = /^(13[0-9]{9})|(18[0-9]{9})|(14[0-9]{9})|(17[0-9]{9})|(15[0-9]{9})$/; 
			 	  return this.optional(element) || (length == 11 && mobile.test(value)); 
			 	}, "请正确地手机号码"); 
		     
			 	var container = $('div.formError');
			 	
				//输入框非空判断
				$("#e-ad-fome").validate({
				 rules : {
				    "address.consignee": {
					     required: true,
					     specialCharValidate:true,
				    },
				    "address.cellphone": {
					     required: true,
					     isMobile:true
				    },
				    "address.address": {
					     required: true
				    },
				    "provinceId": {
					     required: true
				    },
				    "cityId": {
					     required: true
				    },
				    "areaId": {
					     required: true
				    }
				   },
				   messages : {
				      "address.consignee" : {
					       required :"请输入收货人"
				      },
				      "address.cellphone": {
					     required: "请输入手机号码",
				      },
				      "address.address": {
				      		required: "请输入详细地址"
				      },
				      "provinceId": {
				      		required: "请选择省份"
				      },
				      "cityId": {
				      		required: "请选择城市"
				      },
				      "areaId": {
				      		required: "请选择区/县"
				      }
				   },
				   errorContainer: container,
			        wrapper: 'span',
			        errorPlacement: function(error, element) {
			            error.appendTo(container);
			            setTimeout("$('div.formError').fadeOut()",500);
			            setTimeout("$('div.formError').empty()",600);
			        },
			        /* 失去焦点时不验证 */
			        onfocusout : false,
			        onkeyup: false
		 });
			
			 });
				
	</script>
	</head>
	<body class="edit_address">
		<!-- header -->
		<header class='clearfix close-bottom'>
				<a href="javascript:history.back();"><img src="${imagePath}/add-left.png" alt="" id="close-bottom-img"></a>
				编辑收货地址
		</header>
		<!-- header end -->
		<!-- content -->
		<section class="e-ad" style="margin-top: 16.5%!important;">
			<form action="epMemberCenterAction!editAddress.action?id=${id}&proAttrIds=${proAttrIds}&productId=${productId}&productFlag=${productFlag}&flag=${flag}&productFlag2=${productFlag2 }" method="post" id="e-ad-fome" class="e-ad-fome">
			<input type="hidden" value="${address.id}" name="addressId" id="addressId">
			<input type="hidden" value="${member.id }" name="memberId" />
				<p class='clearfix'>
					<label for="name">收货人</label>
					<input type="text" placeholder='名字' value="${address.consignee}" name="address.consignee" id="name">
				</p>
				<p class='clearfix'>
				  <label for="tel">手机号码</label>
					<input type="tel" placeholder='手机号码' value="${address.cellphone}" name="address.cellphone" id="tel">
				</p>
				<p class='clearfix'>
					<label for="province">省份</label>
					<select name="provinceId" id="province">
						<option value="">--选择省份--</option>
						<c:forEach items="${provinceList}" var="bean">
							<option value="${bean.id}"<c:if test="${address.province.id eq bean.id}">selected</c:if>>${bean.province}</option>
						</c:forEach>
					</select>
				</p>
				<p class='clearfix'>
					<label for="city">市</label>
					<select name="cityId" id="city">
						<%-- <option value="${address.city.id}">${address.city.city}</option> --%>
						<option value="">--选择市--</option>
						<c:forEach items="${cities }" var="bean">
							<option value="${bean.id}" <c:if test="${bean.id == address.city.id}">selected="selected"</c:if>>${bean.city}</option>
						</c:forEach>
					</select>
				</p>
				<p class='clearfix'>
					<label for="area">区/县</label>
					<select name="areaId" id="area">
						<%-- <option value="${address.area.id}">${address.area.area}</option> --%>
						<option value="">--选择区/县--</option>
						<c:forEach items="${areas }" var="bean">
							<option value="${bean.id}" <c:if test="${bean.id == address.area.id}">selected="selected"</c:if>>${bean.area}</option>
						</c:forEach>
					</select>
				</p>
				<p class='clearfix'>
					<label for="detail">详细地址</label>
					<input type="text" placeholder='详细地址' value="${address.address}" name="address.address" id="detail">
				</p>
				<button type="submit" id="btn-address">确认</button>
				<div class="formError"></div>
			</form>
		</section>
		<!-- content end -->
	</body>
	<script src="${jsPath}/style.js"></script>
</html>