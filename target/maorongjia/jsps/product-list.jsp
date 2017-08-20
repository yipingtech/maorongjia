<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/jsps/modules/top.jsp"%>
<script type="text/javascript">
  $(function(){
     $("img.lazy").lazyload({
    	 effect : "fadeIn",
    	 threshold : 200
     });
	  
	 $("#loadingImg").hide();
 	 <c:if test="${not empty configJson}">
	    	var configJson = ${configJson};
	     	wx.config(configJson);
	     	wx.ready(function () {
	             wx.onMenuShareAppMessage({
	                 title: "商品列表",
	                 link: "${shareUrl}",
	                 imgUrl: "http://weixin.cppai.com/upload/erwei.jpg",
	                 desc: "",
	                 trigger: function (res) {
	                     //alert('用户点击发送给朋友');
	                 },
	                 success: function (res) {
	                     //alert('已分享');
	                 },
	                 cancel: function (res) {
	                     //alert('已取消');
	                 },
	                 fail: function (res) {
	                     //alert(JSON.stringify(res));
	                 }
	             });
	             
	             wx.onMenuShareTimeline({
	             	 title: "商品列表",
	                  link: "${shareUrl}",
	                  imgUrl: "http://weixin.cppai.com/upload/erwei.jpg",
	                  trigger: function (res) {
<%--	                      alert('用户点击发送给朋友圈');--%>
	                  },
	                  success: function (res) {
	                      //alert('已分享');
	                  },
	                  cancel: function (res) {
	                      //alert('已取消');
	                  },
	                  fail: function (res) {
	                      //alert(JSON.stringify(res));
	                  }
	             });
	            
	         });
	         wx.error(function (res) {
<%--	             alert('wx.error: '+JSON.stringify(res));--%>
	         });
   	    </c:if>
    });
</script>
<body>
    <!-- hot-product -->
    <div class="hot-product-content">
        <div class="hot-product-title">${name }</div>
        <ul class="hot-products">
             <c:forEach var="productRoll" items="${indexProductInfos}">
		            <li class="hot-product">
		                <a href="${ctx}/epProductAction!tampons.action?id=${productRoll.mcProduct.id}">
		                    <div class="product-img"><img src="${uploadPath}/${productRoll.mcProduct.para12}"></div>
		                    <div class="product-name font-black">${productRoll.mcProduct.title}</div>
		                    <div class="product-price">￥${productRoll.mcProduct.shopPrice}&nbsp;&nbsp;<span><c:if test="${not empty productRoll.mcProduct.marketPrice or productRoll.mcProduct.marketPrice eq ''}">市场价：￥${productRoll.mcProduct.marketPrice}</c:if></span></div>
		                </a>
		            </li>
            </c:forEach>
        </ul>
    </div>
    <script>
        $(function(){
            pSwiper = new Swiper('.swiper-container', {
                pagination: '.swiper-pagination',
                paginationClickable: true,
                spaceBetween: 0,
                centeredSlides: true,
                autoplay: 2000,
                autoplayDisableOnInteraction: false
            });
        });
    </script>
</body>
</html>