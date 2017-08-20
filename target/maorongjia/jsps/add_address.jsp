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
		<script src="${jsPath}/jquery-1.11.2.min.js"></script>
		<script src="${jsPath}/style.js?v=1.0"></script>
		<script src="${jsPath}/html5.js"></script>
		<script src='${jsPath}/page.js'></script>
		
	</head>
  <script type="text/javascript">
 $(function(){
   $(".free-product").click(function(){
     if(confirm('确定下单？')) {
					return true;
				}
				return false;
   });
 });

</script>
  <body>
		<!-- header -->
		<header class='clearfix close-bottom'>
		<c:if test="${productFlag eq '1'}">
				<a href="epActivityAction!queryActivityColum.action"><img src="${imagePath}/add-left.png" alt="" id="close-bottom-img"></a>
				免费试穿</c:if>
		<c:if test="${productFlag eq '2'}">
				<a href="javascript:history.back();"><img src="${imagePath}/add-left.png" alt="" id="close-bottom-img"></a>
				领取礼品</c:if>
		<c:if test="${productFlag2 eq '5'}">
				<a href="epMemberCenterAction!newEditMemberPage.action"><img src="${imagePath}/add-left.png" alt="" id="close-bottom-img"></a>
				地址管理</c:if>
		<c:if test="${empty productFlag && productFlag2 ne '5'}">
				<a href="epShoppingCarAction.htm?pageType=showCar"><img src="${imagePath}/add-left.png" alt="" id="close-bottom-img"></a>
				地址管理${productFlag2 }</c:if>
		</header>
		<!-- header end -->
		<!-- 地址栏显示 -->
		<section class="address-top" style="margin-top: 70px !important;">
			<c:forEach items="${addressList}" var="bean" varStatus="status">
				<section class="address">
						<a href="epMemberCenterAction!deleteAddress.action?addressId=${bean.id}"><img src="${imagePath}/address_tp.png" alt="logo" class="address-img"></a>
						<c:if test="${empty productFlag }">
						<a href="epShoppingCarAction.htm?pageType=showCar&addressId=${bean.id}"><p>${bean.province.province}${bean.city.city}${bean.area.area}${bean.address}</p></a>
						</c:if>
						<c:if test="${productFlag eq '1'}">
						<a href="epActivityAction!addFreeProductOrder.action?id=${id}&proAttrIds=${proAttrIds}&freeProductId=${productId}&payOrder.name=${bean.consignee}&payOrder.phone=${bean.cellphone}&payOrder.address=${bean.province.province}${bean.city.city}${bean.area.area}${bean.address}"
						 class="free-product"><p>${bean.province.province}${bean.city.city}${bean.area.area}${bean.address}</p></a>
						</c:if>
						<c:if test="${productFlag eq '2'}">
						<a href="epActivityAction!addSmallGiftOrder.action?proAttrIds=${proAttrIds}&freeProductId=${productId}&payOrder.name=${bean.consignee}&payOrder.phone=${bean.cellphone}&payOrder.address=${bean.province.province}${bean.city.city}${bean.area.area}${bean.address}" class="free-product"><p>${bean.province.province}${bean.city.city}${bean.area.area}${bean.address}</p></a>
						</c:if>
						<p> ${bean.consignee}     ${bean.cellphone} </p>
						<a href="epMemberCenterAction!editAddressPage.action?addressId=${bean.id}&productFlag2=${productFlag2}"><img src="${imagePath}/icon_pencil.png" alt="" class="address_img"></a>
				</section>
			</c:forEach>	
				<section class="add clearfix">
					<a href="epMemberCenterAction!editAddressPage.action?id=${id}&proAttrIds=${proAttrIds}&productId=${productId}&productFlag=${productFlag}&flag=${flag}" style="color:#000;">
							<p>
								<span>新增收货地址</span>
								<img src="${imagePath}/icon_add.png" alt="">
							</p>
					</a>
				</section>
		</section>
		<!-- 地址栏显示 end -->
	</body>
</html>
