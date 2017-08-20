<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/jsps/modules/top.jsp"%>
 <script src="${jsPath}/jquery.cartCalculate.js"></script>
 <script type="text/javascript">
    $(function(){
     $(".free-product-pay").click(function(){
       $(".shopping_pa_hide").show();
     });
     
     $('.submitProduct').on('click', function(){
    				 $(".shopping_pa_hide").hide();
					var repertory = $("#proReper").val()-$("#amount").val();
					if(repertory>0){
						var attrCount = $('#attrCount').val();
						var proAttrIds="";
						for(var i = 1; i <= attrCount; i++){
							proAttrIds = proAttrIds + $('#proAttrId'+i).val() + ",";
						}
						proAttrIds=proAttrIds.substring(0,proAttrIds.length-1);
							$('#productForm').prop("action","epMemberCenterAction!addAddress.action?proAttrIds="+proAttrIds+"&&productFlag=2");
							$('#productForm').submit();
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
     });
     
    function evaluate_close(){
					layer.closeAll();
				} 
				
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
	<body class="s_p_body">
		<!-- header -->
		<header class='clearfix'>
			<a href="epIndexAction.action"><img src="${imagePath}/logo.png" alt="logo"></a>
		</header> 
		<!-- header end-->
		<!-- content -->
		<section id="s-p-1">
			<img src="${uploadPath}/${mcProductInfo.imgurl}">
		</section>

		<section id="s-p-2" class="s-p-2 border-bottom">
			${mcProductInfo.title}<br>
			<span>价格：</span>
			<span class="succeed-o s-p-2-span-color f-t-2">${mcProductInfo.shopPrice}</span><span class="s-p-2-span-color f-t-2">RMB</span>
			
		</section>

		<section class="s-p-6">
			此物非常迷人，快去领取！
			<a href="javascript:void(0)" class="free-product-pay">立即领取</a>
		</section>

		<section id="s-p-3" class="border-t-b f-t-3">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
			  <tr>
				<td width="33%" class="border-right"><span id="s-p-01" class="s-p-o">活动宣传</span></td>
				<td class="border-right"><span id="s-p-03">宝贝详情</span></td>
				<td width="33%"><span  id="s-p-02">评论 （2）</span></td>
			  </tr>
			</table>
		</section>
		<!-- 活动宣传 -->
		<section class="color-black s-p-4 s-p-4-4">
			${enterpriseColumn.intro}<br>
		<img src="${uploadPath}/${enterpriseColumn.pic2}">
		</section>
		<!-- 活动宣传 end-->
		<!-- 宝贝详情 -->
		<section class="color-black s-p-8 s-p-4-4">
			${mcProductInfo.content}
		<img src="${uploadPath}/${mcProductInfo.imgurl}">
		</section>
		<!-- 宝贝详情 end-->
		<!-- 评论 -->
		<section id="s-p-5" class="s-p-5">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
			  <tr class="border-bottom">
				<td width="20%"><img src="${imagePath}/book4.png"></td>
				<td align="left">雷红杏<br><span class="s-p-5-2">最近生活愉快不？烦恼有没有</span></td>
				<td valign="top" class="s-p-5-3">12月19日  19 : 30</td>
			  </tr>
			  <tr class="border-bottom">
				<td width="20%"><img src="${imagePath}/s-p-tp04.png"></td>
				<td align="left">雷红杏<br><span class="s-p-5-2">最近生活愉快不？烦恼有没有</span></td>
				<td valign="top" class="s-p-5-3">12月19日  19 : 30</td>
			  </tr>
			  <tr class="border-bottom">
				<td width="20%"><img src="${imagePath}/s-p-tp04.png"></td>
				<td align="left">雷红杏<br><span class="s-p-5-2">最近生活愉快不？烦恼有没有</span></td>
				<td valign="top" class="s-p-5-3">12月19日  19 : 30</td>
			  </tr>
			  <tr class="border-bottom">
				<td width="20%"><img src="${imagePath}/s-p-tp04.png"></td>
				<td align="left">雷红杏<br><span class="s-p-5-2">最近生活愉快不？烦恼有没有</span></td>
				<td valign="top" class="s-p-5-3">12月19日  19 : 30</td>
			  </tr>
			</table>
		</section>
		<!-- 评论 end-->
		<!-- 购买弹出 -->
		<form action="" method="post" id="productForm" name="productForm">
		<section class="shopping_pa_hide"  id="shopping-cart-list">
				<section class="shopping_shopping">
						<section><img src="${uploadPath}/${mcProductInfo.imgurl}"></section>
						<section>
							<input type="hidden" id="productInfoId" name="productId" value="${mcProductInfo.id}" />
								<p>${mcProductInfo.title}</p>
								<p>${mcProductInfo.description}</p>
								<p><fmt:formatNumber value="${mcProductInfo.shopPrice}" type="currency"/></p>
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
							<input type="text" id="amount" name="amount" class="s-s-input buy-num" value="1" disabled="disabled">
							<img src="${imagePath}/s_s_jia.png" class="btn-add">
							<span id="repertory">${mcProductInfo.repertory}件库存</span>
							<input type="hidden" id="proReper" value="${mcProductInfo.repertory}"/> 
							<input type="text" class="max_lq jian-icon" value="100" style="display:none">
						</p>
						<p class="tuikuan-a submitProduct"><a href="javascript:void(0)">确认</a></p>
				</section>
		</section>
		</form>
		<section class="bg"></section>
		<!-- 购买弹出 end -->
		<!-- content end -->
		<!-- footer -->
<%@ include file="/jsps/modules/copyRight.jsp"%>
