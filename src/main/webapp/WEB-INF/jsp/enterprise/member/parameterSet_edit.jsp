<%@ page contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>更新模板</title>
		<%@ include file="/common/taglibs.jsp"%>
		<%@ include file="/common/meta.jsp"%>
		<%@ include file="/common/content.jsp"%>
		<script>
			$(document).ready(function(){
				$("#parameterSetForm").validate({
					rules: { 
			
					},
					success: function(label) {
						label.html("&nbsp;").addClass("checked");
			        }	
				});
				
				$(".btn").click(function(){
				   var sale=$("#saleRoyaltyRate").val();
				   var topUpGifts=$("#gifts-text").val();
				   if (sale.indexOf("，")>0||topUpGifts.indexOf("，")>0) {
					alert("分销提成， 充值优惠处不能用中文逗号");
					return false;
				   }
				});
			});		
		</script>
	</head>
	<body>
		<div class="rhead">
			<div class="rpos">当前位置: 会员管理（VIP）- 信息配置</div>
		</div>

		<form action="parameterSetAction!editParameterSet.action" method="post" id="parameterSetForm" name="parameterSetForm" enctype="multipart/form-data">
			<table class="table table-bordered inner-table">
				<input type="hidden" name="parameterSet.id" value="${parameterSet.id}"/>
				<!-- <tr>
					<th>红包发放条件设置</th>
					<td>
					    <input type="text" class="conditionSend" name="parameterSet.conditionSend" value="${parameterSet.conditionSend}" />
					    &nbsp; &nbsp;&nbsp; &nbsp;<label style="color: red;">如（50） 即达金额到达50以上就具备发放红包条件</label>
					</td>
				</tr> -->
				<%-- <tr>
					<th>代理商提成设置</th>
					<td>
					    <input type="text" class="conditionSend" name="parameterSet.agentBonusSet" value="${parameterSet.agentBonusSet}" />
					    &nbsp; &nbsp;&nbsp; &nbsp;<label style="color: red;">如（5） 给予5%的提成 </label>
					</td>
				</tr> 
				<tr>
					<th>代理商授权费用</th>
					<td>
					    <input type="text" class="conditionSend" name="parameterSet.agentAmount" value="${parameterSet.agentAmount}" />
					    &nbsp; &nbsp;&nbsp; &nbsp;<label style="color: red;">如（10000） 则代理商授权费用为1万 </label>
					</td>
				</tr> 
				<tr>
					<th>代理商上级补偿比例</th>
					<td>
					    <input type="text" class="conditionSend" name="parameterSet.agentCompensate" value="${parameterSet.agentCompensate}" />
					    &nbsp; &nbsp;&nbsp; &nbsp;<label style="color: red;">如（5） 给予5%的补偿 </label>
					</td>
				</tr>  --%>
				<%-- <tr>
					<th>分销红包</th>
					<td><input type="text" id="saleRoyaltyRate" name="parameterSet.saleRoyaltyRate" value="${parameterSet.saleRoyaltyRate}" />
					&nbsp; &nbsp;&nbsp; &nbsp;<label style="color: red;">*用英文  ,  隔开, 如 （18,10,12）标示 一级18% 二级10% 三级12%</label></td>
					
				</tr> --%>
				<tr>
					<th>分销红包</th>
					<td><input type="text" id="saleRoyaltyRate" name="parameterSet.saleRoyaltyRate" value="${parameterSet.saleRoyaltyRate}" />
					&nbsp; &nbsp;&nbsp; &nbsp;<label style="color: red;">*用英文  ,  隔开, 如 （18,10）标示 一级18% 二级10% </label></td>
					
				</tr>
				<tr>
					<th>提现期限</th>
					<td><input type="text" id="saleRoyaltyRate" name="parameterSet.timeLimit" value="${parameterSet.timeLimit}" />
					&nbsp; &nbsp;&nbsp; &nbsp;<label style="color: red;">如7 则表示会员获得的佣金在该订单确认收货的7天后才能够提现，7天内才允许退换货（一旦确定切勿修改）</label></td>
					
				</tr>
				<tr>
					<th>自动确认收货限时天数</th>
					<td><input type="text" id="saleRoyaltyRate" name="parameterSet.consigneeTime" value="${parameterSet.consigneeTime}" />
					&nbsp; &nbsp;&nbsp; &nbsp;<label style="color: red;">如10 则表示会员的订单在发货的第10天内不签收的话，后台会自动确认收货）</label></td>
					
				</tr>
				<tr>
				    <th>退换货联系人</th>
					<td><input type="text" id="saleRoyaltyRate" name="parameterSet.sellerName" value="${parameterSet.sellerName}" />
					&nbsp; &nbsp;&nbsp; &nbsp;<label style="color: red;"></label></td>
					
				</tr>
				<tr>
					<th>退换货联系电话</th>
					<td><input type="text" id="saleRoyaltyRate" name="parameterSet.sellerPhone" value="${parameterSet.sellerPhone}" />
					&nbsp; &nbsp;&nbsp; &nbsp;<label style="color: red;"></label></td>
					
				</tr>
				<tr>
					<th>退换货微信</th>
					<td><input type="text" class="discountCouponNum" name="parameterSet.sellerAddress" value="${parameterSet.sellerAddress}" /></td>
				</tr>
				<!--  
				<tr>
					<th>每次交易红包使用个数</th>
					<td><input type="text" class="redPacketNum" name="parameterSet.redPacketNum" value="${parameterSet.redPacketNum}" /></td>
				</tr>
				<tr>
					<th>每次交易卡券使用个数</th>
					<td><input type="text" class="discountCouponNum" name="parameterSet.discountCouponNum" value="${parameterSet.discountCouponNum}" /></td>
				</tr>
			
				<tr>
					<th>VIP会员充值金额</th>
					<td><input type="text" class="discountCouponNum" name="parameterSet.vipMember" value="${parameterSet.vipMember}" /></td>
				</tr>
				<tr>
					<th>白金会员充值金额</th>
					<td><input type="text" class="discountCouponNum" name="parameterSet.svip" value="${parameterSet.svip}" /></td>
				</tr>
				<tr>
					<th>钻石会员</th>
					<td><input type="text" class="discountCouponNum" name="parameterSet.diamondMember" value="${parameterSet.diamondMember}" /></td>
				</tr>
				<tr>
					<th>充值赠送</th>
					<td><input type="text" id="gifts-text" name="parameterSet.topUpGifts" value="${parameterSet.topUpGifts}" />
					&nbsp; &nbsp;&nbsp; &nbsp;<label style="color: red;">*用英文  ,  隔开</label>
					</td>
				</tr>
				<tr>
					<th>试穿邀请用户数量</th>
					<td><input type="text"  name="parameterSet.inviteMemberNum" value="${parameterSet.inviteMemberNum}" /></td>
				</tr>
				-->
				<tr>
					<td colspan="2" style="text-align:center;">
						<input type="submit" class="btn" value="提 交"/>
					</td>
				</tr>
		 	</table>
		</form>
	</body>
</html>