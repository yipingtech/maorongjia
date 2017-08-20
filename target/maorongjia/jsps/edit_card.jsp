<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/jsps/modules/top.jsp"%>
<link href="${ctx}/js/validate/jquery.validate.css" type="text/css"
			rel="stylesheet" />
<script src="${ctx}/js/jquery.js" type="text/javascript"></script>
<script src="${ctx}/js/validate/jquery.validate.js" type="text/javascript"></script>
<script src="${ctx}/js/validate/messages_cn.js" type="text/javascript"></script>
<body>
<header class="header_top clearfix">
    <a href="epMemberCenterAction!bankCardDetail.action?id=${bankCard.id }&flag=${flag}"><img src="${imagePath}/left_arrow.png"></a>
    <p>编辑银行卡</p>
</header>
<!--container-->
<script type="text/javascript">
$(document).ready(function(){
	
 	$("#province").change(function(){
				$("#city").empty();
	  			$("#city").append("<option value='' selected='selected'>请选择</option>");
	  			if($(this).val()!=''){
	  				if($(this).val()=='32'||$(this).val()=='33'||$(this).val()=='34'){
		  				$("#city").empty();
		  				$("#city").attr("disabled",true); 
		  			}else{
		  				$.ajax({ 
				      		url:'${ctx}/ajaxAddressAction!findCityByProvince.htm', 
				      		type:'post', 
				      		dataType: 'json',
				      		data:{
				      			"province_id":$(this).val()
				      		},
						    success: function(json) {
						    	$("#city").attr("disabled",false); 
				      			$.each(json, function(index, value) {
					      			$("#city").append("<option value='"+value.id+"'>"+value.city+"</option>");
				      			});
						    },
						    error: function(XMLHttpRequest, textStatus, errorThrown) {
						       openwaring("网络出现异常");
						    }
						});
		  			}
 				}
				/* if($(this).val()!=''){
					$.ajax({ 
			      		url:'${ctx}/ajaxAddressAction!findCityByProvince.htm', 
			      		type:'post', 
			      		dataType: 'json',
			      		data:{
			      			"province_id":$(this).val()
			      		},
					    success: function(json) {
			      			$.each(json, function(index, value) {
				      			$("#city").append("<option value='"+value.id+"'>"+value.city+"</option>");
			      			});
					    },
					    error: function(XMLHttpRequest, textStatus, errorThrown) {
					       openwaring("网络出现异常");
					    }
					});
				} */
			});
 	
 	jQuery.validator.addMethod("specialCharValidate", function(value, element) { 
 		var pattern = new RegExp("[`~!@%#$^&*()=|{}':;',　\\[\\]<>/? \\.；：%……+￥（）【】‘”“'。，、？123456789]"); 
 		return this.optional(element)||!pattern.test(value) ; 
 		}, jQuery.format("不能有非法字符或数字")); 
 	var container = $('div.formError');
 		$("#cardform").validate({
 		rules : {
 			"bankCardFlag": {
			     required: true
		    },
		    "bankCardMember": {
			     required: true,
			     specialCharValidate:true,
			     maxlength:15
		    },
		    
		    "bankCardNum": {
			    required: true,
				number:true,
				digits:true,
		    },
		    "provinceIdStr": {
			     required: true
		    },
		    "bankCardPoint": {
			     required: true
		    }
		    
		   },
		   messages : {
			      "bankCardFlag" : {
				       required :"请输入开户银行"
			      },
			      "bankCardMember" : {
				       required :"请输入持卡人",
				       maxlength:"字符长度不能超过15"
			      },
			      "bankCardNum": {
			      		required: "请输入银行卡号",
						number:"请输入数字",
						digits:"不能包含小数点",
			      },
			      "provinceIdStr": {
			      		required: "请选择省份"
			      },
			      "bankCardPoint" : {
				       required :"请输入开户网点"
			      },
			      
		   },
		   errorContainer: container,
	        wrapper: 'span',
	        errorPlacement: function(error, element) {
	            error.appendTo(container);
	            setTimeout("$('div.formError').fadeOut()",500);
	            setTimeout("$('div.formError').empty()",600);
	        },
	        /* 失去焦点时不验证 */
	        onfocusout : false,
	        onkeyup: false
 });
	
});

</script>
<script type="text/javascript">
	function submitCard(){
		$("form").submit();	
	}
</script>
<section id="container">
  <!--  <p class="promise_text">为了确保本人操作，请先验证。</p>
   <div class="card-list">
        <div class="card-info clearfix">
            <strong class="left">手机号</strong><input class="left" type="text" name="phone" placeholder="请输入手机号">
        </div>
        <div class="card-info clearfix" style="border-bottom:none ">
            <strong class="left">手机验证码</strong><input class="left" type="text" name="phone" placeholder="请输入获取的验证码">
            <a class="promise_code left" href="#">获取验证码</a>
        </div>
    </div> -->
    <p class="promise_text">填写银行卡信息</p>
    <div class="card-list">
        <form action="epMemberCenterAction!editBankCard.action" method="post" id="cardform" style="position:relative">
            <input type="hidden" value="${bankCard.id }" name="id">
            <input type="hidden" value="${flag}" name="flag">
	        <div class="card-info clearfix">
	            <strong class="left">持卡人</strong><input class="left" type="text" name="bankCardMember" value="${bankCard.bankCardMember }"/>
	        </div>
	        <div class="card-info clearfix">
	            <strong class="left">卡号</strong><input class="left" type="text" name="bankCardNum" value="${bankCard.bankCardNum }">
	        </div>
	        <div class="card-info clearfix">
	            <strong class="left">开户银行</strong>
	            <select name="bankCardFlag" id="bankname" style="width:50%" class="left">
	                <%-- <option value="${bankCard.bankCardFlag }">中国工商银行(原)</option>
	                <option value="中国工商银行">中国工商银行</option>
	                <option value="中国农业银行">中国农业银行</option>
	                <option value="中国建设银行">中国建设银行</option> --%>
	                
	                <option value="中国建设银行" <c:if test="${bankCard.bankCardFlag == '中国建设银行'}">selected="selected"</c:if>>中国建设银行</option>
	                <option value="中国农业银行" <c:if test="${bankCard.bankCardFlag == '中国农业银行'}">selected="selected"</c:if>>中国农业银行</option>
	                <option value="中国工商银行" <c:if test="${bankCard.bankCardFlag == '中国工商银行'}">selected="selected"</c:if>>中国工商银行</option>
	                <option value="招商银行" <c:if test="${bankCard.bankCardFlag == '招商银行'}">selected="selected"</c:if>>招商银行</option>
	                <option value="中国银行" <c:if test="${bankCard.bankCardFlag == '中国银行'}">selected="selected"</c:if>>中国银行</option>
	            </select>
	        </div>
	       <div class="card-info clearfix">
	            <strong class="left">开户城市</strong>
	            <select name="provinceIdStr" id="province" class="left" style="width:34%;height:3rem;margin-right:1%;">
	                <option value="">请选择省份</option>
	                <c:forEach items="${provinceList}" var="bean">
	                    <option value="${bean.id}"
	                        <c:if test="${bean.id == bankCard.province.id}">selected</c:if>
	                    >${bean.province}</option>
	                </c:forEach>
	            </select>
	            <input id="cityId" value="${bean.city.id }" type="hidden">
	            <select name="cityIdStr" id="city" class="left" style="width:32%;height:3rem;margin-right:1%;">
					<c:forEach items="${cities}" var="bean">
	                    <option value="${bean.id}"
	                        <c:if test="${bean.id == bankCard.city.id}">selected</c:if>
	                    >${bean.city}</option>
	                </c:forEach>
	            </select>
	            </br> </br>
	            <input type="hidden" value="${bankCard.bankCardAddress }" name="bankCardAddress">
	        </div>
	        <div class="card-info clearfix" style="border-bottom:none ">
	            <strong class="left">开户网点</strong><input class="left" type="text" name="bankCardPoint" value="${bankCard.bankCardPoint }">
	        </div>
	    </div>
	    <div class="card-list-btn">
	        <a  style="width:90%" onclick="submitCard()">确认修改</a>
	        
	    </div>
     <div class="formError" style="z-index: 10000;top:10%"></div>
    </form>
</section>
<!--end of container-->
</body>
</html>