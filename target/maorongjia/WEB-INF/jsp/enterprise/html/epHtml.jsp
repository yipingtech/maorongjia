<%@ page contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>MCCMS</title>
		<%@ include file="/common/taglibs.jsp"%>
		<%@ include file="/common/meta.jsp"%>
		<%@ include file="/common/content.jsp"%>
		
		<script>
			$(document).ready(function(){
				$("[href]").click(function () {
					$.blockUI({
						message: '<h2>正在进行中，请稍候……</h2>',
						css: {
					    	border: '1px solid black'
						}
					});
				});
			});
			</script>
		
	</head>
	<body>
		
		<div class="rhead">
			<div class="rpos">
				当前位置: 优化推广 - 静态页面生成
			</div>
			<div class="clear"></div>
	  	</div>
		
		<table class="table table-bordered inner-table">
			<tr>
				<th>
					内存信息查看
				</th>
				<td>
					<%
						double total = (Runtime.getRuntime().totalMemory()) / (1024.0 * 1024);
						double max = (Runtime.getRuntime().maxMemory()) / (1024.0 * 1024);
						double free = (Runtime.getRuntime().freeMemory()) / (1024.0 * 1024);
						out.println("Java 虚拟机试图使用的最大内存量(当前JVM的最大可用内存)maxMemory(): " + max + "MB<br/>");
						out.println("Java 虚拟机中的内存总量(当前JVM占用的内存总数)totalMemory(): " + total + "MB<br/>");
						out.println("Java 虚拟机中的空闲内存量(当前JVM空闲内存)freeMemory(): " + free + "MB<br/>");
						out.println("因为JVM只有在需要内存时才占用物理内存使用，所以freeMemory()的值一般情况下都很 小，<br/>" +
						"而JVM实际可用内存并不等于freeMemory()，而应该等于 maxMemory()-totalMemory()+freeMemory()。<br/>");
						out.println("JVM实际可用内存: " + (max - total + free) + "MB<br/>");
					%>
				</td>
			</tr>
			
			<tr>
				<th>
					主页静态化
				</th>
				<td>
					<a href="${ctx}/html/enterpriseHtmlAction!createIndex.action">
						点击生成网站首页</a>
						&nbsp;|&nbsp;
					<a href="${ctx}/html/enterpriseHtmlAction!ftpUpload.action?uploadPara=index">
						点击上传主页</a>
				</td>
			</tr>
			<tr>
				<th>
					网站地图静态化
				</th>
				<td>
					<a href="${ctx}/html/enterpriseHtmlAction!createWebmap.action">
						点击生成网站地图</a>
					&nbsp;|&nbsp;
					<a href="${ctx}/html/enterpriseHtmlAction!ftpUpload.action?uploadPara=webmap">
						点击上传网站地图</a>
				</td>
			</tr>
			<c:forEach var="column" items="${columnlist}">
				<tr>
					<th>
						${column.names}
					</th>
					<td>
						<c:if test="${column.typeColumn.templateType == 'content'}">
							<a href="${ctx}/html/enterpriseHtmlAction!createNews.action?epColumnId=${column.id}">
								 点击生成 ${column.names}</a>
						</c:if>
						<c:if test="${column.typeColumn.templateType == 'list'}">
							<a href="${ctx}/html/enterpriseHtmlAction!createMore.action?epColumnId=${column.id}">
								点击生成 ${column.names}
							</a>
						</c:if>
						<c:if test="${column.typeColumn.templateType == 'mostlist'}">
							<a href="${ctx}/html/enterpriseHtmlAction!createMostList.action?epColumnId=${column.id}">
								点击生成 ${column.names}
							</a>
						</c:if>
						&nbsp;|&nbsp;
						<a href="${ctx}/html/enterpriseHtmlAction!ftpUpload.action?uploadPara=${column.id}">
							点击上传 ${column.names}</a>
					</td>
				</tr>
			</c:forEach>
			<tr>
				<th>
					上传文件资料图片
				</th>
				<td>
					<a href="${ctx}/html/enterpriseHtmlAction!ftpUpload.action?uploadPara=upload">
						点击上传upload文件夹</a>
					&nbsp;|&nbsp;
					<a href="${ctx}/html/enterpriseHtmlAction!ftpUpload.action?uploadPara=userfiles">
						点击上传userfiles文件夹</a>
				</td>
			</tr>
			<tr>
				<th>
					全站静态化
				</th>
				<td>
					<a href="${ctx}/html/enterpriseHtmlAction!createWeb.action">
						点击进行全站静态化
					</a>(请谨慎使用，非常消耗系统资源！)
					&nbsp;|&nbsp;
					<a href="${ctx}/html/enterpriseHtmlAction!ftpUpload.action?uploadPara=web">
						点击上传全站</a>
				</td>
			</tr>
		</table>

		
		<div id="message" style="display:none;">
			<s:actionmessage />
		</div>
	</body>
</html>

