 <%@ page contentType="text/html;charset=utf-8"%>	
	        <!-- tabbar start -->
	      <section class="weui_tabbar" style="position:fixed;">
		        <a href="epIndexAction.action" class="weui_tabbar_item">
			          <section class="weui_tabbar_icon">
			            <img src="${imagePath}/shouye.png" alt="">
			          </section>
			          <p class="weui_tabbar_label">首页</p>
		        </a>
		        <a href="epIndexAction!findProductClassify.action" class="weui_tabbar_item">
			          <section class="weui_tabbar_icon">
			            <img src="${imagePath}/fenlei.png" alt="">
			          </section>
			          <p class="weui_tabbar_label">分类</p>
		        </a>
		        <a href="epShoppingCarAction.htm?pageType=showCar3" class="weui_tabbar_item">
			          <section class="weui_tabbar_icon">
			            <img src="${imagePath}/gwc-ture.png" alt="">
			          </section>
			          <p class="weui_tabbar_label relative">购物车<div class="red-round absolute"></div></p>
		        </a>
		        <a href="epMemberCenterAction.action" class="weui_tabbar_item">
			          <section class="weui_tabbar_icon">
			            <img src="${imagePath}/my.png" alt="">
			          </section>
			          <p class="weui_tabbar_label relative">我的<div class="red-round absolute"></div></p>
		        </a>
	      </section>
	  </section>
	  
	  <!-- tabbar end -->

	</section>	
	
	<script type="text/javascript" src="${jsPath}/jquery-2.1.4.js"></script>
	<script type="text/javascript" src="${jsPath}/jquery-weui.js"></script>
	<script type="text/javascript" src="${jsPath}/swiper.js"></script>
	<script type="text/javascript" src="${jsPath}/bill.js" ></script>
	<script type="text/javascript">
      $(function(){
      	  $(".swiper-container").swiper({
	        loop: true,
	        autoplay: 3000
	      });
      })
      $(function(){
      	$(".tab-li").on("click",function(){
      		$(".tab-li").removeClass("tab-active");
      		$(this).addClass("tab-active");
      	})

      })
    </script>
</body>
</html>

  