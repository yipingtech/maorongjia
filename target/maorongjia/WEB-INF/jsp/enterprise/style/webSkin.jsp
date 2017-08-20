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
				当前位置: 界面风格 - 模板管理
			</div>
			<div class="ropt">
				<a href="webSkinAction!add_page.action" class="btn">新增</a>
			</div>
			<div class="clear"></div>
	 </div>
 	 
 	 <table class="table table-bordered">
			<tr>
				<th>
					模板名称
				</th>
				<th>
					模板文件夹名称
				</th>
				<th>
					状态
				</th>
				<th>
					操作
				</th>
		   </tr>
		    <c:forEach var="webSkin" items="${webSkinList}">
		    <tr>
		   		 <td>
					${webSkin.names}
				</td>
				<td>
					${webSkin.filename}
				</td>
				<td>
					<c:if test="${webSkin.state == 1}">已启用</c:if>
					<c:if test="${webSkin.state == 0}"><font color="red">已停用</font></c:if>
				</td>
				<td>
					<c:if test="${webSite.defaultSkin==webSkin.id}">已启用
	            	</c:if>
	            	<c:if test="${webSite.defaultSkin!=webSkin.id}">
	            	<a class="btn btn-info btn-small" href="webSkinAction!updateDefaultSkin.action?id=${webSkin.id}">
	            		更改为默认
	            	 </a>
	            	</c:if>
		            &nbsp;&nbsp;|
		            <a class="btn btn-primary btn-small" href="webSkinAction!edit.action?id=${webSkin.id}">编辑</a>
		         	 <!--|
		         	 <a href="webSkinAction!delete.action?id=${webSkin.id}">
		            	<img src="${ctx}/image/jwc/del.gif" width="16" height="16" border="0px;" />删除
		            </a>
		            -->
				</td>
		    	</tr>
		    </c:forEach>
	</table>
  
	</body>
</html>

           