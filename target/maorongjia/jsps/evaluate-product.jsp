<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/jsps/modules/top.jsp"%>

	<body class='cart ba-co'>
		<!-- header -->
		<header class='clearfix close-bottom'>
				<a href="javascript:history.back();"><img src="${imagePath}/add-left.png" alt="" id="close-bottom-img"></a>
				发表评论
		</header>
		<!-- header end -->
		<!-- content -->
		<section class="evalvate-top">
				<section>
						<form action="" method="post" class='clearfix' id="clearfix">
						<input type="hidden" name="orderInfoId" value="${orderInfo.id}"/>
						<section class="book_detail clearfix border-t">
								<img src="${uploadPath}/${orderInfo.product.imgurl}" alt="" class='book_cover' id="book_cover_img">
								<p class="evalvate02-span1">${orderInfo.product.title}</p>
								<p id="evalvate02-p">价格：<span class="evalvate02-span2"><fmt:formatNumber value="${orderInfo.price}" type="currency"/></span><span class='book_price' id="position-pl">未评论</span><p>
								<span id="e-e-w" style="font-size:1.1em">${orderInfo.createTime}</span>
							<hr class="e-e-hr"><br>
						</section>
						<section class="eva-02">
							<textarea class="evaluateText" name="evaluate.evaluate" cols="" rows="" value="填写评论"></textarea>
						</section>
						<section class="e-e-2 border-bottom"><br>
						<select name="evaluate.level">
							<option value="5">好评</option>
							<option value="3">一般</option>
							<option value="1">差评</option>
						</select>
						<input class="e-e-2-a1" type="submit" value="发表评论"/>
						<p>&nbsp;</p>
						</section>
						
						</form>
						<script>
						//页面层
						$('.e-e-2-a1').on('click', function(){
						var evaluateText=$(".evaluateText").val();
						if (""!=evaluateText&&null!=evaluateText) {
						 $(".evalvate-top form").attr("action","epMemberCenterAction!evaluateProduct.action");
							layer.open({
								shadeClose: false,
								content: '<section class="evaluate-layer"><p><span>评论成功</span><a href="javascript:void(0)" onclick="evaluate_close()"><img src="${imagePath}/evaluate_su.png" id="pagebtn"></a></p><p><a href="javascript:void(0)" class="btns">分享可得丰厚佣金！快快点我！</a></p></section>'
							});
						} else {
						alert("评论不能为空");
						return false;
						}
						});
						function evaluate_close(){
							layer.closeAll();
						}
						</script>
				</section>
		</section>
		<!-- content end -->
<%@ include file="/jsps/modules/copyRight.jsp"%>
