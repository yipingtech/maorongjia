<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/jsps/modules/top.jsp"%>

	<body class="s_p_body">
		<!-- header -->
		<header class='clearfix close-bottom'>
				<a href="javascript:history.back();"><img src="${imagePath}/add-left.png" alt="" id="close-bottom-img"></a>
				我要送礼
		</header>
		<!-- header end -->
		<!-- content -->
		<section id="s-p-1">
			<img src="${imagePath}/shopping_particulars01.png">
		</section>
		<section>
			<p class="gifts-s-1">2015春装新款白色雪纺上衣百搭蕾丝衫</p>
		</section>
		<section id="s-p-2" class="s-p-2 border-bottom">
			<p class="gifts-s-2">满199元,包邮；满299元,包邮,送20元店铺优惠券</p>
			<span class="gifts-s-3">价格：299</span>
			送礼价格：
			<span class="succeed-o gifts-s-4">150</span><span class="s-p-2-span-color gifts-s-5">RMB</span>
			<span>库存：1000件</span>
		</section>
		<section class="s-p-6">
			此物非常迷人，快加入我的分销大计！
			<a href="my-fenxiao.html">我要分销</a>
		</section>
		<section id="s-p-3" class="border-t-b gifts-background">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
			  <tr>
				<td width="33%" class="border-right"><span id="s-p-01" class="s-p-o">宝贝详情</span></td>
				<td class="border-right"><span id="s-p-02">评论 （2）</span></td>
				<td width="33%"><span>已销10笔</span></td>
			  </tr>
			</table>
		</section>
		<!-- 宝贝详情 -->
		<section class="color-black s-p-4 s-p-4-4">
				无论我们是20岁、30岁还是50岁、60岁，或
			许在某个美丽的事物面前，比如孩子、或者一丛花
			朵面前，我们会惊叹于生命的美，有着那种多一分
			嫌胖、少一 分嫌瘦的刚刚好的美妙；或许在某一个
			时刻，当我们回首，我们会讶异于人生为什么如此<br>
			<img src="${imagePath}/s-p-tp01.png">
		</section>
		<!-- 宝贝详情 end -->
		<!-- 评论 -->
		<section id="s-p-5" class="s-p-5">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
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
			  <tr class="border-bottom">
				<td width="20%"><img src="${imagePath}/s-p-tp04.png"></td>
				<td align="left">雷红杏<br><span class="s-p-5-2">最近生活愉快不？烦恼有没有</span></td>
				<td valign="top" class="s-p-5-3">12月19日  19 : 30</td>
			  </tr>
			</table>
		</section>
		<!-- 评论 end -->
		<!-- 购买弹出 -->
		<section class="shopping_pa_hide"  id="shopping-cart-list">
				<section class="shopping_shopping">
						<section><img src="${imagePath}/book4.png"></section>
						<section>
								<p>2015春装新款白色雪纺上衣百搭蕾丝衫</p>
								<p>白色系列</p>
								<p>￥198.00</p>
						</section>
						<img src="${imagePath}/address_tp01.png" class="shopping_shopping_img">
				</section>
				<section class="shopping_shopping01">
						<p>请选择颜色：</p><br>
						<p class="s_s_p2"><span class="s_s_p2_color">白色蕾丝</span><span>黑色蕾丝</span></p><br>
						<p>请选择码数：</p><br>
						<p class="s_s_p2"><span class="s_s_p2_color">S</span><span>M</span><span>均码</span></p><br>
						<p>请选择数量：</p>
						<p class="s_s01_add">
							<img src="${imagePath}/s_s_jian.png" class="s_s01_add_img btn-reduce">
							<input type="text" class="s-s-input buy-num" value="0">
							<img src="${imagePath}/s_s_jia.png" class="btn-add">
							库存1000件
							<input type="text" class="max_lq jian-icon" value="100" style="display:none">
						</p>
						<p class="tuikuan-a"><a href="javascript:void(0)" class="jiesuan">确认</a></p>
				</section>
		</section>
		<section class="bg"></section>
		<!-- 购买弹出 end -->
		<!-- 结算弹出 -->
		<section class="yes-order">
				<p>送礼人:</p>
				<p class="first-child order-adress">广东省广州市天河区猎德村迎东楼11701室</p>
				<a href="add_address.html"><img src="${imagePath}/icon_pencil.png" class="order-img"></a>
				<p class="first-child">卢永康     13423668804</p>
				<p>收礼人:</p>
				<p class="first-child order-adress"><span>广东省广州市天河区猎德村迎东楼11701室</span></p>
				<a href="add_address.html"><img src="${imagePath}/icon_pencil.png" class="order-img"></a>
				<p class="first-child">卢永康     13423668804</p>
				
				<p>付款方式：<select name=""><option>微信支付</option></select></p>
				<p>选择物流：<select name=""><option>顺丰快递</option></select></p>
				<p class="gifts-p">预计星期日（4月5日）送达（请在明天14点前付款）</p>
				<p class="order-p">
					<img src="${imagePath}/icon_choose_no.png"  class="yes-order-ch" onclick="change_pic(this)"><span>红包&nbsp;&nbsp;&nbsp;&nbsp;-￥30</span>
				</p>
				<p class="order-p">
					<img src="${imagePath}/icon_choose_no.png" class="yes-order-ch" onclick="change_pic(this)"><span>卡券<span class="order-span">（无卡券使用）</span></span>
				</p>
				<p>
					<input name="" type="text" value="献上您衷心的祝福语" >
				</p>
				<section class="order-p2">
						<section>
								<span>合计:￥198</span><br>
								<span>包含运费12元</span>
						</section>
						<section>
								<a class="order-p-close">取消</a> <a href="order.html">确认(1)</a>
						</section>
				</section>
		</section>
		<section class="bg"></section>
		<!-- 结算弹出 end -->		
		<!-- content end -->
		<section id="s-p-footer">
			<a href="javascript:void(0)" class="shop" id="gifts-footer"><img src="${imagePath}/gifts_tb.png"></a>
		</section>
		<!-- 添加购物车成功弹出 -->
		<script>
				//页面层
				$('.buy').on('click', function(){
					layer.open({

						shadeClose: false,
						content: '<section class="buy-layer"><p><span>您已成功加入购物车</span><a href="javascript:void(0)" onclick="evaluate_close()"><img src="${imagePath}/evaluate_su.png" id="pagebtn"></a></p></section>'
					});
				});
				function evaluate_close(){
					layer.closeAll();
				}
		</script>
		<!-- 添加购物车成功弹出 end -->
		<script>
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
		<script src="${jsPath}/jquery.cartCalculate.js"></script>
		<script src="${jsPath}/style.js"></script>
	</body>
</html>
