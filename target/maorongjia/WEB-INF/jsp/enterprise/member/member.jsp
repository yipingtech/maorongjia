<%@ page contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>模板</title>
		<%@ include file="/common/taglibs.jsp"%>
		<%@ include file="/common/meta.jsp"%>
		<%@ include file="/common/content.jsp"%>
		<script type="text/javascript">
		$(document).ready(function(){
			$("#formType").validate({
				rules: { 
					'member.id':{
						number:true,
						digits:true
					}
				},
				message:{
					'member.id':{
						number: "请输入有效的数字",
						digits: "只能输入数字"
					}
				}	
			});
			
		    $(".button").attr("disabled","disabled");
		 	$(".province").change(function(){
		 		var provinceVal = $(this).val();
						$(".city").empty();
			  			$(".city").append("<option value='' selected='selected'>请选择</option>");
						if($(this).val()!=''){
							$.ajax({ 
					      		url:'${ctx}/ajaxAddressAction!findCityByProvince.htm', 
					      		async: false ,
					      		type:'post', 
					      		dataType: 'json',
					      		data:{
					      			"province_id":$(this).val()
					      		},
							    success: function(json) {
							    	$(".button").attr("disabled","disabled");
							    	$(".tips").html("");
							    	console.log(json);
					      			$.each(json, function(index, value) {
						      			$(".city").append("<option value='"+value.id+"'>"+value.city+"</option>");
					      			});
					      			if(json.length==0){
					      				$.ajax({
					      					url:'${ctx}/ajaxCheckAgent!checkAgent.htm',
					      					async: false ,
					      					type:'post',
					      					dataType: 'json',
								      		data:{
								      			"provinceId":provinceVal
								      		},
								      		success: function(json) {
								      			if(json.flag2){
								      				$(".button").removeAttr("disabled");
								      				$(".tips").html("&nbsp&nbsp可升级为代理商");
								      				$(".tips").css('color', 'green');
								      			}else{
								      				$(".button").attr("disabled","disabled");
								      				$(".tips").html("&nbsp&nbsp已存在代理商");
								      				$(".tips").css('color', 'red');
								      			}
								      		},
								      		error: function(XMLHttpRequest, textStatus, errorThrown) {
											       openwaring("网络出现异常");
											    }
					      					
					      				});
					      			}
							    },
							    error: function(XMLHttpRequest, textStatus, errorThrown) {
							       openwaring("网络出现异常");
							    }
							});
						}
					});
		       
		        $(".city").change(function(){
						if($(this).val()!=''){
							$.ajax({ 
					      		url:'${ctx}/ajaxCheckAgent!checkAgent.htm', 
					      		type:'post', 
					      		dataType: 'json',
					      		data:{
					      			"cityId":$(this).val()
					      		},
							    success: function(json) {
						      			if(json.flag2){
						      				$(".button").removeAttr("disabled");
						      				$(".tips").html("&nbsp&nbsp可升级为代理商");
						      				$(".tips").css('color', 'green');
						      			}else{
						      				$(".button").attr("disabled","disabled");
						      				$(".tips").html("&nbsp&nbsp已存在代理商");
						      				$(".tips").css('color', 'red');
						      			}
							    },
							    error: function(XMLHttpRequest, textStatus, errorThrown) {
							       openwaring("网络出现异常");
							    }
						});
					}
				});	
				
				//输入框非空判断
				/* $(".e-ad-fome").validate({
				 rules : {
				    "address.address": {
					     required: true
				    }
				   },
				   messages : {
				      "address.address": {
				      		required: "请输入地址"
				      }
				   }
		 }); */
			
			 });
				
	</script>
		<script>
			function doYouWantToDel() {
				if(confirm('您真的要删除吗？')) {
					return true;
				}
				return false;
			}
		    function openSend(id) {
		    	/* Avgrund.show("#send"+id); 原区域代理*/   
		    }
				
			function closeSend() {
				
			/* 	//.city里面的option
				//判断option的个数是否超过1
				if($(".city").option个数>1){
					if($(".city").val()!=""){
						//不提交
					}else{
						//允许提交
					}
				} */
				$(".button").attr("disabled","disabled");
				$(".tips").html("");
				Avgrund.hide();
			}
			function deleteAgent(){
				 return confirm("您确认要取消该代理商吗?");
			}
			
			

		</script>
	</head>
	<body>
	<div class="clr"></div>
        
	
		<div class="rhead">
			<div class="rpos">
				当前位置: 会员管理(vip) - 会员列表
			</div>
			<!-- <div class="ropt">
				<a href="memberAction!newPage.action" class="btn">新增</a>
			</div> -->
			<div class="clear"></div>
		</div>
		<div class="rhead">
			<form id="formType" name="formType" action="memberAction!queryAllMember.action" method="post" style="display:inline;">
					<!-- 条件搜索 -->
					<label>会员昵称</label><input type="text" name="member.nickname" value="${member.nickname}"/>
					<label>会员编号</label><input type="text" name="member.id" value="${member.id}"/>
			<!-- 	<label>会员等级</label>
					<select name="member.rank">
			 		<option value="">--请选择会员等级--</option>
					<option value="1">--普通会员--</option> 
			 		<option value="2">--高级会员--</option> 
			 		<option value="3">--VIP会员--</option> 
					<option value="4">--至尊VIP会员--</option> 
					</select> 
					 <label>代理商</label>
					<select name="member.agent">
						<option value="" >--否--</option>
						<option value="1" <c:if test="${member.agent eq 1 }">selected="selected"</c:if>>--是--</option>
					</select> -->
					<label>一级分销商</label>
					<select name="member.distributor">
						<option value="" >--否--</option>
						<option value="1" <c:if test="${member.distributor eq '1' }">selected="selected"</c:if>>--是--</option>
					</select>
					<input type="submit" value="搜索"/>
			</form>
		</div>
		
		<c:if test="${not empty members}">
				<s:form action="memberAction!queryAllMember.action"
						namespace="/member" method="post" name="form1" theme="simple" id="form1">
						<input type="hidden" name="member.nickname" value="${member.nickname}"/>
						<input type="hidden" name="member.id" value="${member.id}"/>
						<input type="hidden" name="member.distributor" value="${member.distributor}"/>
					<!-- 分页 -->
					<%@ include file="../../common/pager.jsp"%>
				</s:form>
		</c:if>
		
		<table class="table table-bordered">
			<tr>
				<th>序号</th>
				<th>昵称</th>
				<th>会员编号</th>
				<th>真实姓名</th>
				<th>性别</th>
				<th>手机号码</th>
				<!-- <th>会员积分</th>
				<th>会员余额</th> -->
				<th>会员业绩</th>
				
				<th>会员的佣金金额</th>
				<!-- <th>会员级别</th> -->
				<!--  <th>代理商</th> -->
				<th>是否分销商</th>
				<th>分销客户</th>
				<th>操作</th>
			</tr>

			<c:forEach var="bean" items="${members}"
				varStatus="status">
				<tr>
					<td>${status.count}</td>
					<td>${bean.nickname}</td>
					<td>${bean.id}</td>
					<td>${bean.realname}</td>
					<td><c:if test="${bean.sex eq '1'}">男</c:if>
					<c:if test="${bean.sex eq '0'}">女</c:if></td>
					<td>${bean.mobile}</td>
					<%-- <td>${bean.intergal}</td>
					<td>${bean.balance}</td> --%>
					
					<td><fmt:formatNumber type="number" value="${bean.commissionLine}" pattern="##.##" /></td>
					<td>
						<fmt:formatNumber type="number" value="${bean.commission}" pattern="##.##" />
					</td>
					<%-- <td>
					  <c:if test="${bean.rank eq '1'}">普通会员</c:if>
					  <c:if test="${bean.rank eq '2'}">高级会员</c:if>
					  <c:if test="${bean.rank eq '3'}">VIP会员</c:if>+
					  <c:if test="${bean.rank eq '4'}">至尊VIP会员</c:if> 
					</td> --%>
					<%-- <td>
                        <c:if test="${empty bean.agent }">否
                            <c:if test="${empty bean.firstFather }">
                               <form action="memberAction!agentSet.action">
                                   <input type="hidden" value="${bean.id }" name="id"/>
                                                                       否&nbsp&nbsp&nbsp<input type="submit" value="成为代理商" />
                               </form>
                            </c:if>
                            <c:if test="${!empty bean.firstFather }">
                                                                 非一级分销商
                            </c:if>
                        </c:if>
                         <c:if test="${!empty bean.agent }">是
                             <form action="memberAction!agentDelete.action">
                                                  <input type="hidden" value="${bean.id }" name="id"/>
                                                                  是&nbsp&nbsp&nbsp<input type="submit" value="取消该代理商" onclick="return deleteAgent();"/>
                             </form>
                         </c:if>
                    </td>
                     --%>
					<td>
						<c:if test="${bean.distributor eq '1' }">是</c:if>
						<c:if test="${bean.distributor eq '0' or empty bean.distributor }">否</c:if>
					</td>
					<td><a class="btn btn-info btn-small"  style="background: #cde;" href="memberAction!queryThreeMember.action?member.id=${bean.id}&flag=1">点击查看</a></td>
					<td>
						<a class="btn btn-info btn-small" href="memberAction!viewPage.action?id=${bean.id}">查看</a>
						&nbsp; &nbsp;
						<a class="btn btn-primary btn-small" href="memberAction!retrieveMemberById.action?id=${bean.id}">编辑</a>
						&nbsp; &nbsp;
						<!--  <a class="btn btn-danger btn-small" href="memberAction!delMember.action?id=${bean.id}"
								 onclick="return doYouWantToDel();">删除</a>-->
					</td>
				</tr>
			</c:forEach>
		</table>

	</body>
</html>