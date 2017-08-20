<%@ page contentType="text/html;charset=utf-8"%>

<%@ include file="/jsps/modules/top.jsp"%>

<body class="body-eee">
<header class='clearfix' id="clearfix">
    <img src="${imagePath}/logo.png" alt="logo">
</header>
<section class="vip-particular">
    <%-- <section><a href="epActivityAction!queryActivityColum.action"><img src="${imagePath}/par1.png"></a></section>
    <section><a href="epActivityAction!toGiveGifts.action"><img src="${imagePath}/par2.png"></a></section>
    <section><a class="sign" href="#"><img src="${imagePath}/par3.png"></a></section> --%>
</section>
<script type="text/javascript">
$(function(){
    $('.sign').click(function(){
     $.ajax({ 
		          url:'${ctx}/ajaxMemberCenterAction!memberReport.action', 
		          type:'post', 
		          dataType: 'json',
		          data:null,
		     success: function(data) {
		       if(data=='0'){
		       layer.open({
                          content: '<section class="sign-success"><p>签到成功</p><p>随机奖励积分<span class="font-fbbbbb">+5</span>分</p><a href="javascript:void(0)" onClick="layer.closeAll()"><img src="${imagePath}/close2.png"></a></section>'
                              });
		       }else{
		        layer.open({
                          content: '<section class="sign-success"><p>您今天已经签到了</p><a href="javascript:void(0)" onClick="layer.closeAll()"><img src="${imagePath}/close2.png"></a></section>'
                              });		       
		       }
		            
		      },
		      error: function(XMLHttpRequest, textStatus, errorThrown) {
		           openwaring("网络出现异常");
		        }
		    }); 
    });
    
});
</script>
<%@ include file="/jsps/modules/copyRight.jsp"%>
