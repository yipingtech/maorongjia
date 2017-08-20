<%@ page contentType="text/html;charset=utf-8"%>

<%@ include file="/jsps/modules/top.jsp"%>
<link href="${ctx}/js/validate/jquery.validate.css" type="text/css"
			rel="stylesheet" />
<script src="${ctx}/js/jquery.js" type="text/javascript"></script>
<script src="${ctx}/js/validate/jquery.validate.js" type="text/javascript"></script>
<script src="${ctx}/js/validate/messages_cn.js" type="text/javascript"></script>
<body class="edit_address">
<header class='clearfix close-bottom'>
    <a href="epMemberCenterAction!viewMember.action"><img src="${imagePath}/add-left.png" alt="" id="close-bottom-img"></a>
    我的账号
</header>
<section class="e-ad">
    <form action="epMemberCenterAction!editMember.action" method="post" class="revise-form" style="position: relative;">
    <div class="loading" style="display:none;position: absolute;top:0;left:0;bottom:0;right:0;margin:auto;width:20px;height:20px"><img src="${imagePath}/loading.gif" /></div>
        <h3 class="revise-h3"><img class="touxiang2" src="${imagePath}/touxiang2.png">&nbsp;个人资料<img class="revise-img" src="${imagePath}/revise.png"></h3>
        <section class="upgrade-vip" id="revise-form-infor" style="position:relative">
           <%--  <p>
                <!-- <label class="account-infor" for="card-num">卡号：</label> -->
                <input type="text" id="card-num" name="member.cardNum" placeholder="${member.cardNum}" disabled="disabled" style="padding-bottom:0px;margin-top:-3px;">
                <c:if test="${not empty member.mobile}">
                <a href="epMemberCenterAction!newUpgrade.action">升级</a>
                </c:if>
                <c:if test="${empty member.mobile}">
                <a href="epMemberCenterAction!EditMemberPhonePage.action">升级</a>
                </c:if>
            </p> --%>

            <p style="padding-top: 3%;">
                <label class="account-infor" for="card-name">姓名：</label>
                <input type="text" id="card-name" name="memberInfo.realname" value="${member.realname}" style="padding-bottom:0px;margin-top:0;">
                
            </p>
            <p>
                <label class="account-infor" for="phone-num">手机号码：</label>
                <input type="tel" id="phone-num" name="memberInfo.mobile" value="${member.mobile}" style="padding-bottom:0px;margin-top:0;">
<%--               	<a href="epMemberCenterAction!EditMemberPhonePage.action">--%>
<%--                	<c:if test="${empty member.mobile}">--%>
<%--                	绑定--%>
<%--                	</c:if>--%>
<%--                	<c:if test="${not empty member.mobile}">--%>
<%--	               	更换--%>
<%--                 	</c:if>--%>
<%--               	</a>--%>
            </p>
            <div class="formError"></div>
            <p>
                <label class="account-infor" for="">性别：</label>
                <input type="radio" name="memberInfo.sex" value="0"  style="margin-top:1rem;"<c:if test="${member.sex eq '0'}">checked</c:if> id="RadioGroup1_1"><label for="" class="sex-w"  style="margin-top:0.5rem;">女</label>
                <input type="radio" name="memberInfo.sex" value="1" style="margin-top:1rem;"<c:if test="${member.sex eq '1'}">checked</c:if> id="RadioGroup1_0"> <label for="" class="sex-m"  style="margin-top:0.5rem;">男</label>
            </p>
           <!--  <p>
                <label class="account-infor" for="revise-address">地址：</label>
                <input type="text" id="revise-address" name="memberInfo.address" value="${member.address}" style="padding-bottom:0px;margin-top:0;"/>
            </p> -->
<%--            <p>--%>
<%--                <label class="account-infor" for="">生日：</label>--%>
<%--                <select id="year" name="year" class="year">--%>
<%--                  <c:if test="${!empty member.birthday}">--%>
<%--                    <option value="${fn:substring(member.birthday, 0, 4)}" selected>${fn:substring(member.birthday, 0, 4)}</option>--%>
<%--                  </c:if>--%>
<%--                  <c:if test="${empty member.birthday}">--%>
<%--                    <option value="" selected>年份</option>--%>
<%--                  </c:if>--%>
<%--                  <%--%>
<%--                    int year=1960;--%>
<%--                    for(int i=0;i<80;i++){--%>
<%--                    ++year;--%>
<%--                    %>--%>
<%--                    <option value="<%=year%>"><%=year%></option>--%>
<%--                    <%--%>
<%--                    }--%>
<%--                   %>--%>
<%--                </select>--%>
<%--                <select id="month" name="month" class="month">--%>
<%--                  <c:if test="${!empty member.birthday}">--%>
<%--                    <option value="${fn:substring(member.birthday, 5, 7)}" selected>${fn:substring(member.birthday, 5, 7)}</option>--%>
<%--                  </c:if>--%>
<%--                  <c:if test="${empty member.birthday}">--%>
<%--                    <option value="" selected>月份</option>--%>
<%--                  </c:if>--%>
<%--                    <%--%>
<%--                    int month=00;--%>
<%--                    for(int i=0;i<12;i++){--%>
<%--                    ++month;--%>
<%--                    %>--%>
<%--                    <option value="<%=month%>"><%=month%></option>--%>
<%--                    <%--%>
<%--                    }--%>
<%--                   %>--%>
<%--                </select>--%>
<%--                <select id="day" name="day" class="day">--%>
<%--                  <c:if test="${!empty member.birthday}">--%>
<%--                    <option value="${fn:substring(member.birthday, 8, 10)}" selected>${fn:substring(member.birthday, 8, 10)}</option>--%>
<%--                  </c:if>--%>
<%--                  <c:if test="${empty member.birthday}">--%>
<%--                    <option value="" selected>日期</option>--%>
<%--                  </c:if>--%>
<%--                    <%--%>
<%--                    int day=00;--%>
<%--                    for(int i=0;i<31;i++){--%>
<%--                    ++day;--%>
<%--                    %>--%>
<%--                    <option value="<%=day%>"><%=day%></option>--%>
<%--                    <%--%>
<%--                    }--%>
<%--                   %>--%>
<%--                </select>--%>
<%--                <i>&nbsp;</i>--%>
<%--            </p>--%>
            <input style="height=60px;line-height:60px;font-size: 24px;" type="submit" id="sure" class="sure" value="保存"></input>
        </section>
    </form>
    <section class="my-account">
<%--        <a href="epMemberCenterAction!queryAllIntergralByUser.action">积分明细<i>></i></a>--%>
<%--        <a href="epMemberCenterAction!queryAllRechargeByUser.action">充值消费明细<i>></i></a>--%>
        <a href="epMemberCenterAction!addAddress.action?productFlag2=5">收货地址管理<i>></i></a>
    </section>
</section>
</body>
 <script type="text/javascript">
 $(document).ready(function(){
	 	
	 	jQuery.validator.addMethod("specialCharValidate", function(value, element) { 
	 		var pattern = new RegExp("[`~!@%#$^&*()=|{}':;',　\\[\\]<>/? \\.；：%……+￥（）【】‘”“'。，、？123456789]"); 
	 		return this.optional(element)||!pattern.test(value) ; 
	 		}, jQuery.format("不能包含特殊字符或数字!")); 
	 	
		// 手机号码验证 
	 	jQuery.validator.addMethod("isMobile", function(value, element) { 
	 	  var length = value.length; 
	 	  var mobile = /^(13[0-9]{9})|(18[0-9]{9})|(14[0-9]{9})|(17[0-9]{9})|(15[0-9]{9})$/; 
	 	  return this.optional(element) || (length == 11 && mobile.test(value)); 
	 	}, "请正确填写手机号码"); 
		
	 	var container = $('div.formError');
	 
	 	$(".revise-form").validate({
	 		rules : {
	 			"memberInfo.realname": {
				     required: true,
				     specialCharValidate:true,
				     maxlength:15
			    },
			    "memberInfo.mobile": {
				    required: true,
				    isMobile:true,
				},
			},
			messages : {
			   "memberInfo.realname": {
				   required :"请输入姓名",
			       maxlength:"字符长度不能超过15"
			    },
			    "memberInfo.mobile": {
				    required: "请输入手机号码",
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
      function Close(){
          layer.closeAll();
      }
 </script>
<script src="${jsPath}/style.js"></script>
</html>