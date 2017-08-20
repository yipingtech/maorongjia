<%@ page contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>商品评论表</title>
		<%@ include file="/common/taglibs.jsp"%>
		<%@ include file="/common/meta.jsp"%>
		<%@ include file="/common/content.jsp"%>
		<meta name="viewport" content="width=800, user-scalable=no"/>
		<link rel="stylesheet" href="../ueditor/dialogs/Avgrund-master/css/avgrund.css">
		<link rel="stylesheet" href="../ueditor/dialogs/Avgrund-master/css/demo.css">
		<script type="text/javascript" src="../ueditor/dialogs/Avgrund-master/js/avgrund.js"></script>
		<script>
		$(function(){
		  $(".reply-evaluate").click(function(){
		     $("#text-product-title").val($(this).siblings(".product-title").val());
			 $(".reply-evaluate-id").val($(this).siblings(".product-evaluate-id").val());
		     Avgrund.show( "#default-popup" );
		  });
		  
		  $("#btn-send-product").click(function(){
		     if (null!=$("#text-invoiceNum").val()&&$("#text-invoiceNum").val()!="") {	
		       $("#form-send-product").attr("action","evaluateAction!replyEvaluates.action"); 				     			
			} else {		  
			  alert("回复不能为空");
			  return false;
			}
		  });
		  
		  $(".avgrund-cover").click(function(){
		    Avgrund.hide();
		  });
		});
			function doYouWantToDel() {
				if(confirm('您真的要删除吗？')) {
					return true;
				}
				return false;
			}
		</script>
	</head>
	<body>
	<p></p>
		<div class="rhead">
			<div class="rpos">
				当前位置: 会员管理(vip) - 商品评论
			</div>
			<div class="clear"></div>
		</div>
		<c:if test="${not empty evaluates}">
			<s:form action="evaluateAction!queryAllEvaluates.action"
					namespace="/member" method="post" name="form1" theme="simple" id="form1">
				<!-- 分页 -->
				<%@ include file="../../common/pager.jsp"%>
			</s:form>
			<br />
		</c:if>
		<table class="table table-bordered">
			<tr>
				<th>序号</th>
				<th>会员</th>
				<th>商品</th>
				<th>评分</th>
				<th>评价</th>
				<th>评论时间</th>
				<th>商家回复</th>
				<th>回复时间</th>
				<th>操作</th>
			</tr>

			<c:forEach var="bean" items="${evaluates}"
				varStatus="status">
				<tr>
					<td>${status.count}</td>
					<td>${bean.member.loginName}</td>
					<td>${bean.product.title}</td>
					<td>${bean.level}</td>
					<td>${bean.evaluate}</td>
					<td>${fn:substringBefore(bean.addTime, " ")}</td>
					<td>${bean.evaluateReply}</td>
					<td>${fn:substringBefore(bean.replyTime, " ")}</td>

					<td>
						<a class="btn btn-info btn-small reply-evaluate" style="background-color: green;color: white;">回复</a>
				  	    &nbsp; &nbsp;
						<a class="btn btn-danger btn-small" href="evaluateAction!deleteEvaluates.action?evaluatesId=${bean.id}"
								 onclick="return doYouWantToDel();">移除</a>		
					 <input type="hidden" class="product-title" value="${bean.product.title}"/>
					 <input type="hidden" class="product-evaluate-id" value="${bean.id}"/>					 
					  
					</td>					 
				</tr>
			</c:forEach>
		</table>

		<c:if test="${not empty evaluates}">
			<s:form action="evaluateAction!queryAllEvaluates.action"
					namespace="/member" method="post" name="form1" theme="simple" id="form1">
				<!-- 分页 -->
				<%@ include file="../../common/pager.jsp"%>
			</s:form>
		</c:if>
	   <form action="" method="get" id="form-send-product">
		<div id="default-popup" class="avgrund-popup">
          <br />
                   商品：<input type="text" id="text-product-title" disabled="disabled" style="width: 250px;height: 25px;"/><br/>
		   回复：<input type="text" name="evaluate.evaluateReply" id="text-invoiceNum"  placeholder="请输入回复信息" style="width: 250px;height: 25px;margin-top: 20px"/>
		   <input type="hidden" name="evaluate.id" class="reply-evaluate-id" value=""/>
		   <div style="margin-top: 30px;">
		  <button type="submit" id="btn-send-product" style="margin-left:40%;">确认回复</button>			
		   </div>
		</div>
		</form>
		<div class="avgrund-cover"></div>
	</body>	
</html>