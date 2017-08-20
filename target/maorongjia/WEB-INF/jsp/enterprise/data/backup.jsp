<%@ page contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>MCCMS</title>
		<%@ include file="/common/taglibs.jsp"%>
		<%@ include file="/common/meta.jsp"%>
		<%@ include file="/common/content.jsp"%>
		<style>
			.textCenter td {
				text-align: center;
			}
		</style>

		<script>

			$(document).ready(function(){
	
				$("#serviceName").focus();
	
				$("#resetButtom").click(function(){
						$("#serviceName").attr("value","");
						$("#initTime").attr("value","");
						$("#endTime").attr("value","");
				});

				if("${showMesscat}"!=null&&"${showMesscat}"!="undefined"&&"${showMesscat}"!=""){
			        $.blockUI({ 
			        	message: $('#message')
			        	}) 
					setTimeout($.unblockUI, 2000); 
				}

			});

			function backupDatabase() {
				//确定备份数据库吗?
				if(confirm('确定备份数据库吗?')) {
					return true;
				}
				return false;
			}
			
			function backupDataFile() {
				//确定备份数据文件吗，可能这要耗费很长的时间?
				if(confirm('确定备份数据文件吗，可能这要耗费很长的时间?')) {
					return true;
				}
				return false;
			}
			
			function doYouWantToDel() {
				if(confirm('您真的要删除吗？')) {
					return true;
				}
				return false;
			}
		</script>

	</head>
	<body>
		<div class="rhead">
			<div class="rpos">
				当前位置: 系统数据管理 - 数据备份
			</div>
		</div>
		
		<div class="rhead">
			<div>
				<a href="dataHandlerAction!backupDatabase.action"
							onclick="return backupDatabase();" class="btn">
								一键备份数据库</a>
				<a href="dataHandlerAction!backupFile.action"
							onclick="return backupDataFile();" class="btn">
								一键备份数据文件 </a>
			</div>
		</div>
		<c:if test="${not empty historyList}">
			<s:form action="dataHandlerAction!showHistory.action"
				namespace="/data" method="post" name="form1" theme="simple">
				
				<!-- 分页 -->
				<%@ include file="../../common/pager.jsp"%>	
				
			</s:form>
			<br/>
		</c:if>
		<table class="table table-bordered">
			<tr>
				<th>
					序号
				</th>
				<th>
					备份文件名
				</th>
				<th>
					日期
				</th>
				<th>
					备份类型
				</th>
				<th>
					状态
				</th>
				<th>
					操作
				</th>
			</tr>

			<c:forEach var="historyList" items="${historyList}" varStatus="status">
				<tr class="textCenter">
					<td>
						${status.count}
					</td>
					<td>
						${historyList.name}
					</td>
					<td>
						<fmt:formatDate value="${historyList.dateTime}" pattern="yyyy-MM-dd" />
					</td>
					<td>
						${historyList.type}
					</td>
					<td>
						<c:if test="${historyList.status=='1'}">
							正常
						</c:if>
						<c:if test="${historyList.status=='0'}">
							已删除
						</c:if>
					</td>
					<td>
						<c:if test="${historyList.status=='1'}">
							<a class="btn btn-info btn-small" href="${ctx}/data/download.action?fileName=${historyList.path}"> 下载</a>
							&nbsp; &nbsp;
							<a class="btn btn-danger btn-small" href="dataHandlerAction!delete.action?id=${historyList.id}"
								onclick="return doYouWantToDel();">
									删除</a>
						</c:if>
					</td>
				</tr>
			</c:forEach>

		</table>

		<c:if test="${not empty historyList}">
			<s:form action="dataHandlerAction!showHistory.action"
				namespace="/data" method="post" name="form1" theme="simple">
				
				<!-- 分页 -->
				<%@ include file="../../common/pager.jsp"%>	
				
			</s:form>
		</c:if>

	</body>
</html>

