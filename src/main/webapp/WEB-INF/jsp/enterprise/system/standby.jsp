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
			<div class="rpos">当前位置: 基本配置 - 公共信息管理</div>
			<div class="clear"></div>
		</div>
		<table class="table table-bordered inner-table">
			<c:forEach items="${standbys}" var="standby" begin="0" end="0">
				<tr>
					<th>公司名字：</th>
					<td>${standby.standby1}</td>
				</tr>
				<tr>
					<th>全国热线：</th>
					<td>${standby.standby2}</td>
				</tr>
				<!-- <tr>
					<th>首页右上角联系电话：</th>
					<td>${standby.standby3}</td>
				</tr>
				<tr>
					<th>左栏联系人：</th>
					<td>${standby.standby4}</td>
				</tr>
				<tr>
					<th>左栏地址：</th>
					<td>${standby.standby5}</td>
				</tr>
				<tr>
					<th>左栏电话：</th>
					<td>${standby.standby6}</td>
				</tr>
				<tr>
					<th>左栏传真：</th>
					<td>${standby.standby7}</td>
				</tr>
				<tr>
					<th>左栏传真：</th>
					<td>${standby.standby8}</td>
				</tr>
				<tr>
					<th>左栏传真：</th>
					<td>${standby.standby9}</td>
				</tr>
				<tr>
					<th>左栏传真：</th>
					<td>${standby.standby10}</td>
				</tr>
				<tr>
					<th>左栏传真：</th>
					<td>${standby.standby11}</td>
				</tr>
				<tr>
					<th>左栏传真：</th>
					<td>${standby.standby12}</td>
				</tr> -->
				<%-- <tr>
					<th>二维码：</th>
					<td>
					<img style="height: 110px;"
								src="
							<c:choose>
								<c:when test="${empty standby.bgphoto1}">
										${ctx}/upload/NoExpertPhoto.JPG
								</c:when>
								<c:otherwise>
									${ctx}/upload/enterprice/${standby.bgphoto1}
								</c:otherwise>
							</c:choose>
							" />
					</td>
				</tr> --%>
				<%-- <tr>
					<th>公司logo：</th>
					<td>
					<img style="height: 110px;"
								src="
							<c:choose>
								<c:when test="${empty standby.bgphoto2}">
										${ctx}/upload/NoExpertPhoto.JPG
								</c:when>
								<c:otherwise>
									${ctx}/upload/enterprice/${standby.bgphoto2}
								</c:otherwise>
							</c:choose>
							" />
					</td>
				</tr> --%>
				<%-- <tr>
					<th>软文顶部图片：</th>
					<td>
					<img style="height: 110px;"
								src="
							<c:choose>
								<c:when test="${empty standby.bgphoto3}">
										${ctx}/upload/NoExpertPhoto.JPG
								</c:when>
								<c:otherwise>
									${ctx}/upload/enterprice/${standby.bgphoto3}
								</c:otherwise>
							</c:choose>
							" />
					</td>
				</tr> --%>
			<!-- 	<tr>
					<th>公司logo：</th>
					<td>
					<img style="height: 110px;"
								src="
							<c:choose>
								<c:when test="${empty standby.bgphoto4}">
										${ctx}/upload/NoExpertPhoto.JPG
								</c:when>
								<c:otherwise>
									${ctx}/upload/enterprice/${standby.bgphoto4}
								</c:otherwise>
							</c:choose>
							" />
					</td>
				</tr>
				<tr>
					<th>公司logo：</th>
					<td>
					<img style="height: 110px;"
								src="
							<c:choose>
								<c:when test="${empty standby.bgphoto5}">
										${ctx}/upload/NoExpertPhoto.JPG
								</c:when>
								<c:otherwise>
									${ctx}/upload/enterprice/${standby.bgphoto5}
								</c:otherwise>
							</c:choose>
							" />
					</td>
				</tr>
				<tr>
					<th>公司logo：</th>
					<td>
					<img style="height: 110px;"
								src="
							<c:choose>
								<c:when test="${empty standby.bgphoto6}">
										${ctx}/upload/NoExpertPhoto.JPG
								</c:when>
								<c:otherwise>
									${ctx}/upload/enterprice/${standby.bgphoto6}
								</c:otherwise>
							</c:choose>
							" />
					</td>
				</tr>
				<tr>
					<th>公司logo：</th>
					<td>
					<img style="height: 110px;"
								src="
							<c:choose>
								<c:when test="${empty standby.bgphoto7}">
										${ctx}/upload/NoExpertPhoto.JPG
								</c:when>
								<c:otherwise>
									${ctx}/upload/enterprice/${standby.bgphoto7}
								</c:otherwise>
							</c:choose>
							" />
					</td>
				</tr>
				<tr>
					<th>公司logo：</th>
					<td>
					<img style="height: 110px;"
								src="
							<c:choose>
								<c:when test="${empty standby.bgphoto8}">
										${ctx}/upload/NoExpertPhoto.JPG
								</c:when>
								<c:otherwise>
									${ctx}/upload/enterprice/${standby.bgphoto8}
								</c:otherwise>
							</c:choose>
							" />
					</td>
				</tr>
				<tr>
					<th>公司logo：</th>
					<td>
					<img style="height: 110px;"
								src="
							<c:choose>
								<c:when test="${empty standby.bgphoto9}">
										${ctx}/upload/NoExpertPhoto.JPG
								</c:when>
								<c:otherwise>
									${ctx}/upload/enterprice/${standby.bgphoto9}
								</c:otherwise>
							</c:choose>
							" />
					</td>
				</tr>
				<tr>
					<th>公司logo：</th>
					<td>
					<img style="height: 110px;"
								src="
							<c:choose>
								<c:when test="${empty standby.bgphoto10}">
										${ctx}/upload/NoExpertPhoto.JPG
								</c:when>
								<c:otherwise>
									${ctx}/upload/enterprice/${standby.bgphoto10}
								</c:otherwise>
							</c:choose>
							" />
					</td>
				</tr> -->
				<tr>
					<td colspan="2" style="text-align:center;">
						<input type="button" onclick="javascript:window.location.href='standbyAction!editStandby.htm?id=${standby.id}'" class="btn" value="编 辑 "/>
					</td>
				</tr>
			</c:forEach>
		</table>
	</body>
</html>