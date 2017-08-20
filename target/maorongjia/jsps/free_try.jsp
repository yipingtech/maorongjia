<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/jsps/modules/top.jsp"%>
 <script src="${jsPath}/jquery.cartCalculate.js"></script>
 <script type="text/javascript">
    $(function(){
    //点击弹出购买窗口

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
							$('#productForm').prop("action","epMemberCenterAction!addAddress.action?proAttrIds="+proAttrIds+"&&productFlag=1");
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
					$(".bg").hide();
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
					     str+="<tr class='border-bottom'><td width='20%'><img src='${imagePath}/s-p-tp04.png'></td><td align='left'>"+evaluateText.member.loginName+"<br><span class='s-p-5-2'>"+evaluateText.evaluate+"</span></td><td valign='top' class='s-p-5-3'>"+evaluateText.addTime+"</td></tr>";
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
	<body class="s_p_body">
		<!-- header -->
		<header class='clearfix'>
			<a href="epIndexAction.action"><img src="${imagePath}/logo.png" alt="logo"></a>
		</header> 
		<!-- header end-->
		<!-- content -->
		<section id="s-p-1">
			<img src="${uploadPath}/${productInfo.imgurl}">
		</section>

		<section id="s-p-2" class="s-p-2 border-bottom">
			${productInfo.title}<br>
			<span>价格：</span>
			<span class="succeed-o s-p-2-span-color f-t-2">${productInfo.shopPrice}</span><span class="s-p-2-span-color f-t-2">RMB</span>
			<a class="s-p-2-a-last-child">免费试穿</a>
			<span>已有<span>${count}</span>人申请</span>
		</section>

		<section class="f-t-6">
		<c:if test="${count1 eq 1}">
			<p>邀请好友试穿</p>
			<p>
			 <c:forEach items="${activityInvites}" var="activityInvite" varStatus="status">
			   <c:if test="${status<5}">
				<a href="#"><img src="${uploadPath}/${activityInvite.memberId.imagePath}" alt="" title=""></a>
			  </c:if>
			</c:forEach>
			</p>
			<p>您已邀请${fn:length(activityInvites)}位好友试穿，还要邀请${parameterSet.inviteMemberNum-(fn:length(activityInvites))}位！<a href="epActivityAction!queryInviteFriend.action">查看</a></p>
			<p>
			<c:if test="${activityApply.receiveStatus eq '0'}">
				<c:if test="${fn:length(activityInvites) < parameterSet.inviteMemberNum}"><a href="javascript:void(0)">邀请好友试穿</a></c:if>
				<c:if test="${fn:length(activityInvites) >= parameterSet.inviteMemberNum}"><a href="javascript:void(0)" class="free-product-pay">立即试穿</a></c:if></p>
			</c:if>
			<c:if test="${activityApply.receiveStatus eq '1'}">
				<a href="javascript:void(0)">你已领取</a>
			</c:if>
		</c:if>
		<c:if test="${count1 eq 0}">
		<p>点击参与该商品试穿活动</p>
			<p>
			 <c:forEach items="${activityInvites}" var="activityInvite" varStatus="status">
			   <c:if test="${status<5}">
				<a href="#"><img src="${uploadPath}/${activityInvite.memberId.imagePath}" alt="" title=""></a>
			  </c:if>
			</c:forEach>
			</p>
			<p>您还没参与试穿活动，快点击参与吧</p>
		<p><a href="epActivityAction!applyFreeProduct.action">点击参与试穿</a></p>
		</c:if>
		</section>

		<section id="s-p-3" class="border-t-b f-t-3">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
			  <tr>
				<td width="33%" class="border-right"><span id="s-p-01" class="s-p-o">活动宣传</span></td>
				<td class="border-right"><span id="s-p-03">宝贝详情</span></td>
				<td width="33%"><span  id="s-p-02">评论 （${countEvaluate}）</span></td>
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
			${productInfo.content}
		<img src="${uploadPath}/${productInfo.imgurl}">
		</section>
		<!-- 宝贝详情 end-->
		<!-- 评论 -->
		<section id="s-p-5" class="s-p-5">
			<c:if test="${not empty evaluates}">
			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="table-evaluates">
             <c:forEach items="${evaluates}" var="evaluate">
			 <tr class="border-bottom">
				<td width="20%"><img src="${imagePath}/s-p-tp04.png"></td>
				<td align="left">${evaluate.member.loginName}<br><span class="s-p-5-2">${evaluate.evaluate}</span></td>
				<td valign="top" class="s-p-5-3">${evaluate.addTime}</td>
			  </tr>
			  </c:forEach>
			</table>
			<div class='more-evaluate' style='line-height:3.8em' onClick="moreEvaluate(this);">更多</div>
			</c:if>
			  <c:if test="${empty evaluates}"><p style="margin-top: 30px;">本商品还没有评论</p></c:if>
		</section>
		<!-- 评论 end-->
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
						<input type="hidden" name="id" value="${activityApply.id}"/>
						<p>请选择数量：</p>
						<p class="s_s01_add">
							<img src="${imagePath}/s_s_jian.png" class="s_s01_add_img btn-reduce">
							<input type="text" id="amount" name="amount" class="s-s-input buy-num" value="1" disabled="disabled">
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
		<!-- footer -->
<%@ include file="/jsps/modules/copyRight.jsp"%>
