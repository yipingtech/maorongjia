
<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<footer class="clearfix">
    <div class="inner_footer">
        <!-- <div class="footer_li">
        	<a href="epIndexAction.action">商城首页</a>
       	</div>
        <div class="footer_li"> 会员中心
	        <div class="footer_layer_content">
	        	<div id="triangle-down"></div>
	       		<ul class="footer_layer">
	        		<li><a href="epIndexAction!moShi.action">模式解说</a></li>
	        		<li><a href="epIndexAction!createTeam.action">如何组建团队</a></li>
	        		<li><a href="epMemberCenterAction!generateQrcode.action">生成二维码</a></li>
	        		<li><a href="epMemberCenterAction.action">个人中心</a></li>
	      	 	</ul>
	        </div>
        	
        </div>
        <div class="footer_li">关于谐达
         	<div class="footer_layer_content">
	        	<div id="triangle-down"></div>
	        	<ul class="footer_layer">
	        		<li><a href="epIndexAction!intro.action">集团简介</a></li>
	        		<li><a href="epIndexAction!jiaGou.action">集团架构</a></li>
	        		<li><a href="epIndexAction!develop.action">发展历程</a></li>
	        		<li><a href="epIndexAction!culture.action">企业文化</a></li>
	        		<li><a href="epIndexAction!contactUs.action">联系我们</a></li>
	      	 	</ul>
      	 	</div>
        </div>
    </div> -->
</footer>
<script type="text/javascript">
    $(document).ready(function (e) {
        resize_lv1();
    });
    function resize_lv1() {
        var width_lv1 = $(window).width();
        $(".pic>img").css("height", width_lv1 * 1.0);
    }
    $(window).resize(function () {
        resize_lv1();
    });
    $(document).ready(function (e) {
        resize_lv2();
    });
    function resize_lv2() {
        var width_lv2 = $(".nav>a").width();
        $(".nav>a,.nav>a img").css("height", width_lv2 * 1.0);
    }
    $(window).resize(function () {
        resize_lv2();
    });
    $(document).ready(function (e) {
        resize_lv3();
    });
    function resize_lv3() {
        var width_lv3 = $(".index_product>a").width();
        $(".index_product>a img").css("height", width_lv3 * 0.970588);
    }
    $(window).resize(function () {
        resize_lv3();
    });
</script>
<!--nav slide-->
    <script type="text/javascript">
        $(document).ready(function () {
            $('.nav_all').click(function () {
                $(".nav_product>a").addClass("hover_color");
                $(".nav_list").slideToggle(300);
            });
        });
    </script>
    <c:if test="${not empty indexProductInfos}">
        <script type="text/javascript">
	    	window.onload = function(){
	    		$(function(){
						$(".lazy").height($(window).width());
						//$(".lazy").height($(window).width()*0.645);
						$(document).scrollTop($.cookie('productListHeight'));
						/* $("#container").scrollTop($.cookie('productListHeight')); */
						$(".sale_pic a").on("click",function(){
							$.cookie('productListHeight', $(".bg3").scrollTop());
						});
						
						/* var maxHeight = $(".sale_pic").length*($(window).width()+16);
						$("#overFlowContain").height(maxHeight+30);
						$("#container").scrollTop($.cookie('cookie'));
						$(".sale_pic a").on("click",function(){
							$.cookie('cookie', $("#container").scrollTop());
						}); */
				})
	    	}
	   </script>
    </c:if>
    <script>
    	$(function(){
    		$(".inner_footer .footer_li").each(function(){
    			$(this).click(function(){
    				$(this).siblings(".footer_li").find($(".footer_layer_content")).hide();
    				$(this).find(".footer_layer_content").toggle();
    			});	
    			
    		});
    		$("body").bind("touchmove",function(event){
    			$(".footer_layer_content").hide();
    			event.stopPropagation();
    		});
    	});
    </script>
</body>
</html>