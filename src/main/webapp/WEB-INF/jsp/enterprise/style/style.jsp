<%@ page contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>MCCMS</title>
		<%@ include file="/common/taglibs.jsp"%>
		<%@ include file="/common/meta.jsp"%>
		<%@ include file="/common/content.jsp"%>
	</head>
	<body>
	
	<div class="rhead">
			<div class="rpos">
				当前位置: 界面风格 - 风格管理
			</div>
			<div class="clear"></div>
	 </div>
	
	 <form action="webSkinAction!update_style.action">
 	  <table class="table table-bordered">
			<tr>
				<th>
					风格名称
				</th>
				<th>
					风格选择
				</th>
		   </tr>
		  	 <c:forEach var="webSkinColor" items="${webSkinColorList}">
		  	 	<tr>
				<td>
					${webSkinColor.names}
				</td>
				<td>
					<img src="${ctx}/templates/${webSkin.filename}/theme/${webSkinColor.filename}/style.jpg" width="100px" height="120px"/>
					<c:if test="${webSkinColor.state==1}">	
          	 					默认:<input type="radio" name="webSkinColorId" <c:if test="${webSkinColor.isDefaultId == 1}">checked</c:if> value="${webSkinColor.id}"/>
          	 				</c:if>
          	 				<c:if test="${webSkinColor.state==0}">
          	 					请先付款,再继续使用!
          	 				</c:if>	
				</td>
		   </tr>
		  </c:forEach>
		 	<tr>
				<td colspan="2" style="text-align:center;">
					<input type="submit" class="btn" value="提 交 "/>
				</td>
			</tr>
		 </table>
	</form>
	</body>
</html>

