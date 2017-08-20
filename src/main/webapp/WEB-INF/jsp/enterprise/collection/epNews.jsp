<%@ page contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>MCCMS</title>
		<%@ include file="/common/taglibs.jsp"%>
		<%@ include file="/common/meta.jsp"%>
		<%@ include file="/common/content.jsp"%>
		
<script type="text/javascript">
	$(document).ready(function(){
		
		$("#alllist").click(
			function(){
				if($(this).attr("checked")){
					$(this).attr("checked",true);
					$(".list").attr("checked",true);
				}else{
					$(this).attr("checked",false);
					$(".list").attr("checked",false);
				}
			}
		);
	});
	
	function submitForm(message,flag){
		if(confirm(message)){
			if(flag==1){
				$("#flag").attr("value","true");
			}else{
				$("#flag").attr("value","false");
			}
			$("#enterpriseList").submit();
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
			<div class="rpos">当前位置: 内容管理 -
				<c:if test="${empty column.names}">总站</c:if>${column.names}</div>
				<c:if test="${isShowInsert}">
					<div class="ropt">
						<a href="${ctx}/collection/epNewsAction!add_page.action?father=${column.id}" class="btn">新增</a>
					</div>
				</c:if>
			<div class="clear"></div>
		</div>
		<form action="epNewsAction!queryByTitleAndState.action" method="post">
		<input type="hidden" name="father" value="<c:if test="${empty column.id}">0</c:if><c:if test="${not empty column.id}">${column.id}</c:if>" />
		<table class="table table-bordered" style="margin-bottom:5px;">
			<tr>
				<td class="pn-flabel" style="text-align:left;padding-left:15px;">标题：<input type="text" name="title" value="${title}" />
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;状态：
					<select name="state">
						<option value="-1">全部</option>
						<option value="1" <c:if test="${state==1}">selected</c:if>>已通过</option>
						<option value="0" <c:if test="${state==0}">selected</c:if>>不通过</option>
						<option value="2" <c:if test="${state==2}">selected</c:if>>待审核</option>
					</select>
					<input type="submit" style="margin-left:30px;" class="btn" value='查询' />
				</td>
			</tr>
		</table>
		</form>
		<c:if test="${not empty enterpriseNewsList}">
		<s:form action="epNewsAction!query.action" method="post" id="form1" name="form1" theme="simple">
			<input type="hidden" name="father" value="${father}"/>
			<input type="hidden" name="title" value="<c:if test="${not empty title}">${title}</c:if>" />
			<input type="hidden" name="state" value="<c:if test="${empty state}">-1</c:if><c:if test="${not empty state}">${state}</c:if>" />

			<!-- 分页 -->
			<%@ include file="../../common/pager.jsp"%>
			<br />
		</s:form>
		</c:if>
		<table class="table table-bordered">
			<tr>
				<%-- 
				<td>
					<input type="checkbox" id="alllist" value="${news.id}" />
		   			<input type="hidden" id="flag" name="flag" value="false"/>
				</td>
				--%>
				<th width="480px;">
					标题
				</th>
				<th>
					文档作者
				</th>
				<th width="100px;">
					更新时间
				</th>
				<th width="60px;">
					新闻类型
				</th>
				<th>
					状态
				</th>
				<th>
					操作
				</th>
			</tr>
			<c:forEach var="news" items="${enterpriseNewsList}">
				<tr>
					<!-- 
					<td width="25px;">
			   			<input type="checkbox" class="list" name="list" value="${news.id}" />
			   		</td>
			   		 -->
					<td>
						<font style="font-weight:bold;overflow:hidden;">[${news.enterpriseColumn.names}]</font>
						<c:choose>
	                          <c:when test="${fn:length(news.title)>25}">
	                                ${fn:substring(news.title,0,25)}...
	                          </c:when>
	                          <c:otherwise>
	                                ${news.title}
	                          </c:otherwise>
	                    </c:choose>
					</td>
					<td style="width:129px;">
						${news.users.workunit}
					</td>
					<td style="width:70px;">
						<fmt:formatDate value="${news.editeTime}" pattern="yyyy-MM-dd" />
					</td>
					<td style="width:70px;">
						<c:if test="${news.isPrimPhoto == '1'}">
							<font color="red">图片新闻</font>
						</c:if>
						<c:if test="${news.isPrimPhoto == '0'}">
							普通新闻
						</c:if>
						<c:if test="${news.isPrimPhoto == '2'}">
							<font color="blue">文件新闻</font>
						</c:if>
					</td>
					<td style="width:70px;">
						<c:if test="${news.state == 1}">已通过</c:if>
						<c:if test="${news.state == 0}"><font color="red">不通过</font></c:if>
						<c:if test="${news.state == 2}"><font color="blue">待审核</font></c:if>
					</td>
					<td>
						<a class="btn btn-info btn-small" href="epNewsAction!view.action?id=${news.id}">查看</a>
						<c:if test="${news.enterpriseColumn.typeColumn.templateType != 'content'}">
							<c:if test="${(news.users.loginName == currentUserName) or (isAdmin==1)}">
								&nbsp;&nbsp;<a class="btn btn-primary btn-small" href="epNewsAction!edit.action?father=${father}&&id=${news.id}&&pageSize=${pager.pageSize}&&pageNo=${pager.pageNo}">编辑</a>
								&nbsp;&nbsp;<a class="btn btn-danger btn-small" href="epNewsAction!delete.action?father=${father}&&id=${news.id}"
									onclick="return doYouWantToDel();">删除</a>
							</c:if>
						</c:if>
					</td>
				</tr>
			</c:forEach>
		</table>
		
	</body>
</html>

