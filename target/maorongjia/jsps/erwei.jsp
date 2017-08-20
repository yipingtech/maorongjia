<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/jsps/modules/top.jsp"%>
<c:if test="${not empty configJson}">
 	<script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.1.0.js"></script>
  	<script type="text/javascript">
	   $(function(){
	    	var configJson = ${configJson};
	     	wx.config(configJson);
	     	wx.ready(function () {
	             wx.onMenuShareAppMessage({
	                 title: "${memberInfo.nickname}",
	                 link: "${shareUrl}",
	                 //imgUrl: "http://weixin.cppai.com/upload/enterprice/member_qrcode/${qrCodeName}",
	                 //imgUrl: "http://weixin.cppai.com/upload/erwei.jpg",
	                 imgUrl: "${member.imagePath}",
	                 desc: "",
	                 trigger: function (res) {
	                    // alert('用户点击发送给朋友');
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
	             	  title: "${memberInfo.nickname}",
	                  link: "${shareUrl}",
	                 // imgUrl: "http://weixin.cppai.com/upload/enterprice/member_qrcode/${qrCodeName}",
	                  imgUrl: "${member.imagePath}",
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
	<%--		             alert('wx.error: '+JSON.stringify(res));--%>
	         });
	  	   
	   });
   </script>
</c:if>
<body class="pink">
<div class="erwei" style="display: table;">

		<img src="${uploadPath}/member_qrcode/${qrCodeName}">

</div>
<script type="text/javascript">
    $(document).ready(function (e) {
        resize_lv1();
    });
    function resize_lv1() {
<%--        var width_lv1 = $(".erwei_icon img").width();--%>
<%--        $(".erwei_icon img").css("height", width_lv1 * 1.0);--%>
<%--        var width_lv2 = $(".erwei_code img").width();--%>
<%--        $(".erwei_code img").css("height", width_lv2 * 1.0);--%>
		var heighjt_lv1 = $(window).height();
		 $(".erwei").css("height", heighjt_lv1);
    }
    $(window).resize(function () {
        resize_lv1();
    });
</script>
</body>
</html>
