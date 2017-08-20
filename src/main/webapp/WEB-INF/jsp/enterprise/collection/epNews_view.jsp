<%@ page contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>MCCMS</title>
		<%@ include file="/common/taglibs.jsp"%>
		<%@ include file="/common/meta.jsp"%>
		<%@ include file="/common/content.jsp"%>
		<style type="text/css">
		h1 {
			text-align: center;
			font-size: 18px;
		}
		
		.info {
			text-align: center;
		}
		</style>
	</head>
	<body>
		<div class="rhead">
			<div class="rpos">
				当前位置: 内容管理 - ${enterpriseNews.enterpriseColumn.names} - 查看新闻信息
			</div>
			<div class="ropt">
				<!-- 前台预览-->
				<a class="btn" href="${ctx}/navigation.htm?newsId=${enterpriseNews.id}&columnType=news" target="_blank">前台预览</a>
				<!-- 返回列表 -->
				<a class="btn" href="${ctx}/collection/epNewsAction!query.action?father=${enterpriseNews.enterpriseColumn.id}">返回</a>
			</div>
			<div class="clear"></div>
		</div>
		<c:if test="${isAdmin == 1}">
		<div>
			<div style="display:none;">
			<!-- 已通过-->
			<input type="button" value='已通过' />
			&nbsp;
			<!-- 不通过-->
			<input type="button" value='不通过' />
			&nbsp;
			</div>
		</div>
		</c:if>
		<div style="border: 1px solid #ccc;">
			<h1>
				${enterpriseNews.title}
			</h1>
			<p class="info">
				发布者:${enterpriseNews.users.name} &nbsp;来源:${enterpriseNews.source}
				&nbsp; 发布时间:
				<fmt:formatDate value="${enterpriseNews.editeTime}"
					pattern="yyyy-MM-dd" />
			</p>
			<div>
				${enterpriseNews.contents}
			</div>
			<br/>
			<hr/>
			<div>
			<c:if test="${enterpriseNews.isPrimPhoto == 2}">
				<p><font color="red">已经上传文件：</font></p>
				<div>
					<a href="${ctx}/upload/enterprice/${enterpriseNews.photo}"
								target="_blank"
								>${enterpriseNews.photo}
						</a>
				</div>
			</c:if>
				<c:if test="${enterpriseNews.isPrimPhoto == 1}">
				<p><font color="red">附：图片新闻已上传的图片：</font></p>
					<div class="info">
						<img src="
						<c:choose>
							<c:when test="${empty enterpriseNews.photo}">
									${ctx}/upload/MissPhoto.JPG
							</c:when>
							<c:otherwise>
								${ctx}/upload/enterprice/${enterpriseNews.photo}
							</c:otherwise>
						</c:choose>
						"/>
					</div>
				</c:if>
			</div>
		</div>
	</body>
</html>

