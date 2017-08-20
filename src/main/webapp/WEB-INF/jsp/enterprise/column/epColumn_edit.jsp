<%@ page contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>MCCMS</title>
		<%@ include file="/common/taglibs.jsp"%>
		<%@ include file="/common/meta.jsp"%>
		<%@ include file="/common/content.jsp"%>
		<link type="text/css" href="${ctx}/resource/AsyncBox/skins/ZCMS/asyncbox.css" rel="stylesheet" />
		<script type="text/javascript" src="${ctx}/resource/AsyncBox/AsyncBox.v1.4.js"></script>

		<script type="text/javascript">
			$(document).ready(function(){
				$("#names").focus();
				$("#columnForm").validate({
					 rules: { 
						names: { 
		        			required: true, 
		        			maxlength:50,
		        			remote: "epColumnAction!checkName.action?orgName=${enterpriseColumn.names}&columnName="+$("#father").attr("value")+"&math="+Math.random()
		    			},
		            	shortName: {
		                    required: true ,
					        maxlength:50
		        	    },
		        	    orderColumn:{
		        	    	required: true ,
		        	    	number:true
		        	    },
		        	    linkUrl:{
		        	    	url:true,
		        	    	maxlength:100
		        	    },
		        	    typeColumn:{
		        	    	required: true ,
		        	    	maxlength:50
		        	    },
		        	    frontNum:{
		        	    	maxlength:20
		        	    },
		        	    num:
		        	    {
		        	    	maxlength:20
		        	    },
		        	    intro:
		        	    {
		        	    	maxlength:500
		        	    }
					},messages: {
						names: {
							//该栏目已存在
							remote: '该栏目已存在'
						}
					},
					success: function(label) {
						label.html("&nbsp;").addClass("checked");
			        }	
				});
			});	

			//论坛栏目树
			function listEnterpriseColumn() {
				asyncbox.open({
					//论坛树
					title: '栏目树',
					url : '${ctx}/epColumnTreeAction!generateTree.action',
					data : {
						<c:if test="${not empty father}">
							father : ${father},
						</c:if>
						id : ${enterpriseColumn.id}
					},
					width : 300,
		    		height : 440,
					btnsbar : $.btn.OKCANCEL,
					callback : function(action, iframe) {
						if(action == 'ok') {
							$("#columnName").val(iframe.returnValue);
							$("#father").val(iframe.returnId);
						}
					}
				});
			}

			//当选择图片时发生
			function doSelectPhotoChanged(elepath){
				filePath = document.getElementById(elepath).value;
				
				if(checkFileUpload(elepath)){
					if(window.navigator.userAgent.indexOf("Chrome")<1){
						/*document.getElementById(elepath).value = "";
						document.getElementById("imgShowTeacherPhoto").src=filePath;
						document.getElementById("imgchange").rel=$("#imgShowTeacherPhoto").attr("src");*/
					}
				}
			}
			
			//文件上传验证
			function checkFileUpload(elepath)
			{
			  var path = document.getElementById(elepath).value;
			  if(window.navigator.userAgent.indexOf("Chrome")<1){
			  	   if(path.indexOf(".jpg")<1 && path.indexOf(".gif")<1 &&  path.indexOf(".bmp")<1 &&  path.indexOf(".png")<1 && path.indexOf(".jpeg")<1){
			  	   	//图片类型不正确，请上传正确的格式！
				    alert('<s:text name="enterprise.collection.message.phototype.error" />\n(jpg,gif,bmp,png,jpeg)');
			  	   		cleanFilePath(elepath);
				   		return false;
			  	   }
			  }else{
				  if(!sy_FilterFile(path,"jpg,gif,bmp,png,jpeg")){
				    //图片类型不正确，请上传正确的格式！
				    alert('<s:text name="enterprise.collection.message.phototype.error" />\n(jpg,gif,bmp,png,jpeg)');
				    cleanFilePath(elepath);
				    return false;
				  }
				  if(sy_GetImageToGetSize(path).fileSize>1024000){
				    //上传文件大小不能够超过1MB！
				    alert('<s:text name="enterprise.collection.message.photosize.error" />');
				    cleanFilePath(elepath);
				    return false;
				  }
			  }
			  return true;
			}
			//提交表单前验证准备上传的文件
			function doBeforeSubmit(){
				if(document.getElementById("upload").value!=""){
					if(!checkFileUpload()){
						return false;
					}
				}
			}
			//验证上传文件错误后删除file框内路径
			function cleanFilePath(elepath){
				if(window.navigator.userAgent.indexOf("MSIE")<1){
					$("#"+elepath).attr("value","");
				}else{
					var file = $("#"+elepath);
					file.after(file.clone().val(""));
					file.remove();
				}
			}
			
		</script>
		<style>
			.notview{
				display:none;
			}
			#typeColumn optgroup{background-color:#C1EBFF;}
			.table.inner-table th{width:110px;}
		</style>
	</head>
	<body style="padding: 8px;">
		<div class="rhead">
			<div class="rpos">
				当前位置: 栏目配置 - 编辑栏目信息
			</div>
			<div class="ropt"><a class="btn" href="javascript:history.back();">返回</a></div>
			<div class="clear"></div>
		</div>
		<form action="epColumnAction!update.action" method="post"  enctype="multipart/form-data"
			id="columnForm">
			<input type="hidden" name="enterpriseColumn.id"
				value="${enterpriseColumn.id}" />
			<table class="table table-bordered inner-table">
				<tr class="notview"<security:authorize ifAnyGranted="COLUMN_FATHER"> style="display:table-row;"</security:authorize>>
					<th>
						父级栏目
					</th>
					<td>
						<input type="text" name="columnName" id="columnName" value="<s:property value='fatherName'/>" onfocus="listEnterpriseColumn();" readonly/>
							<font color="red">注意选好父栏目，免得以后重新选</font>
							<input type="hidden" id="father" name="enterpriseColumn.father" value="<s:property value='enterpriseColumn.father'/>"/>
						</td>
				</tr>
				<tr>
					<th>
						栏目名称
					</th>
					<td>
						<input type="hidden" id="nametemp" name="nametemp"
							value="${enterpriseColumn.names}" />
						<input type="text" id="names" name="names"
							value="${enterpriseColumn.names}" size="60" />
						请不要重复
					</td>
				</tr>
				<tr>
					<th>
						栏目简称
					</th>
					<td>
						<input type="text" name="shortName"
							value="${enterpriseColumn.shortName}" size="60" />
						一般显示在前台
					</td>
				</tr>
				<tr class="notview"<security:authorize ifAnyGranted="COLUMN_MODULE"> style="display:table-row;"</security:authorize>>
					<th>
						栏目模块名称
					</th>
					<td>
						<input type="text" name="frontNum"
							value="${enterpriseColumn.frontNum}" size="60" />
						模块名称的设置将影响到页面的显示效果,请参考开发文档
					</td>
				</tr>
				<tr>
					<th>
						栏目排序
					</th>
					<td>
						<input type="text" name="orderColumn"
							value="${enterpriseColumn.orderColumn}" size="60" />
						数字越小,排序越前,同级同栏目下请不要重复,以免影响效果
					</td>
				</tr>
				<tr class="notview"<security:authorize ifAnyGranted="COLUMN_TEMPLATE"> style="display:table-row;"</security:authorize>>
					<th>
						模板类型
					</th>
					<td>
						<select name="enterpriseColumn.typeColumn.id" id="typeColumn">
							<c:forEach var="mostlistType" items="${pageTypeOut.mostlistTypes}" varStatus="info">
								<c:if test="${info.first}">
									<!-- 模块列表模型(mostlist) -->
									<optgroup label="模块列表模型(mostlist)">
								</c:if>
									<option value="${mostlistType.id}"
										<c:if test="${enterpriseColumn.typeColumn.id == mostlistType.id}">selected</c:if>>
										${mostlistType.name}(${mostlistType.templateType})
									</option>
								<c:if test="${info.last}">
									</optgroup>
								</c:if>
							</c:forEach>
							<c:forEach var="listType" items="${pageTypeOut.listTypes}" varStatus="info">
								<c:if test="${info.first}">
									<!-- 列表模型(list) -->
									<optgroup label="列表模型(list)">
								</c:if>
									<option value="${listType.id}"
										<c:if test="${enterpriseColumn.typeColumn.id == listType.id}">selected</c:if>>
										${listType.name}(${listType.templateType})
									</option>
								<c:if test="${info.last}">
									</optgroup>
								</c:if>
							</c:forEach>
							<c:forEach var="contentType" items="${pageTypeOut.contentTypes}" varStatus="info">
								<c:if test="${info.first}">
									<!-- 单页模型(content) -->
									<optgroup label="单页模型(content)">
								</c:if>
									<option value="${contentType.id}"
										<c:if test="${enterpriseColumn.typeColumn.id == contentType.id}">selected</c:if>>
										${contentType.name}(${contentType.templateType})
									</option>
								<c:if test="${info.last}">
									</optgroup>
								</c:if>
							</c:forEach>
							<c:if test="${not empty pageTypeOut.linkType}">
								<option value="${pageTypeOut.linkType.id}"
									<c:if test="${enterpriseColumn.typeColumn.id == pageTypeOut.linkType.id}">selected</c:if>>
									单链接模型(link)
								</option>
							</c:if>
							<c:forEach var="productType" items="${pageTypeOut.productTypes}" varStatus="info">
								<c:if test="${info.first}">
									<!-- 产品模型(product) -->
									<optgroup label="产品模型(product)">
								</c:if>
									<option value="${productType.id}"
										<c:if test="${enterpriseColumn.typeColumn.id == productType.id}">selected</c:if>>
										${productType.name}(${productType.templateType})
									</option>
								<c:if test="${info.last}">
									</optgroup>
								</c:if>
							</c:forEach>
							<c:forEach var="otherType" items="${pageTypeOut.otherTypes}" varStatus="info">
								<c:if test="${info.first}">
									<!-- 定制模型(other) -->
									<optgroup label="定制模型(other)">
								</c:if>
									<option value="${otherType.id}"
										<c:if test="${enterpriseColumn.typeColumn.id == otherType.id}">selected</c:if>>
										${otherType.name}(${otherType.templateType})
									</option>
								<c:if test="${info.last}">
									</optgroup>
								</c:if>
							</c:forEach>
						</select>
					</td>
				</tr>

				<tr class="notview"<security:authorize ifAnyGranted="COLUMN_INDEX_SHOW"> style="display:table-row;"</security:authorize>>
					<th>
						是否可以点击
					</th>
					<td>
						<select name="enterpriseColumn.isValidInNav">
							<option value="0">
								是
							</option>
							<option value="1"
								<c:if test="${enterpriseColumn.isValidInNav=='1'}">selected</c:if>>
								否
							</option>
						</select>
						(只有一级导航才能进行设置生效)
					</td>
				</tr>
				<tr class="notview"<security:authorize ifAnyGranted="COLUMN_URL"> style="display:table-row;"</security:authorize>>
					<th>
						链接地址
					</th>
					<td>
						<input type="text" name="linkUrl"
							value="${enterpriseColumn.linkUrl}" size="60" />
						只有栏目设置为link时,此字段才有效果
					</td>
				</tr>

				<tr>
					<th>
						栏目编号
					</th>
					<td>
						<input type="text" name="num" value="${enterpriseColumn.num}"
							size="60" />
						可自定义
					</td>
				</tr>
				<tr>
					<th>
						栏目banner
					</td>
					<td>
						<a href="${ctx}/upload/enterprice/${enterpriseColumn.pic1}"
							target="_blank" name="imgchange" id="imgchange"
							class="screenshot" rel="doSelectPhotoChanged('upload')"><img
								id="imgShowTeacherPhoto" name="imgShowTeacherPhoto" border="0"
								width="96"
								src="
										<c:choose>
											<c:when test="${empty enterpriseColumn.pic1}">
													${ctx}/upload/NoExpertPhoto.JPG
											</c:when>
											<c:otherwise>
												${ctx}/upload/enterprice/${enterpriseColumn.pic1}
											</c:otherwise>
										</c:choose>
										" />
						</a>
						<br />
						<input type="file" id="upload" name="upload"
							onChange="doSelectPhotoChanged('upload');" size="30" />
					</td>
				</tr>
				<tr>
					<th>
						栏目图片
					</th>
					<td>
						<a href="${ctx}/upload/enterprice/${enterpriseColumn.pic2}"
							target="_blank" name="imgchange" id="imgchange"
							class="screenshot" rel="doSelectPhotoChanged('upload1')"><img
								id="imgShowTeacherPhoto" name="imgShowTeacherPhoto" border="0"
								width="96"
								src="
										<c:choose>
											<c:when test="${empty enterpriseColumn.pic2}">
													${ctx}/upload/NoExpertPhoto.JPG
											</c:when>
											<c:otherwise>
												${ctx}/upload/enterprice/${enterpriseColumn.pic2}
											</c:otherwise>
										</c:choose>
										" />
						</a>
						<br />
						<input type="file" id="upload1" name="upload1"
							onChange="doSelectPhotoChanged('upload1');" size="30" />
					</td>
				</tr>
				<tr class="notview"<security:authorize ifAnyGranted="COLUMN_INTRO"> style="display:table-row;"</security:authorize>>
					<th>
						栏目介绍
					</th>
					<td>
						<textarea id="editor1" name="intro" style="width:400px;height:200px;">${enterpriseColumn.intro}</textarea>
					</td>
				</tr>
				<tr>
					<th>
						状态
					</th>
					<td>
						<select name="enterpriseColumn.state">
							<option value="1">
								已启用
							</option>
							<option value="0"
								<c:if test="${enterpriseColumn.state==0}">selected</c:if>>
								已停用
							</option>
						</select>
					</td>
				</tr>
				<c:if test="${enterpriseColumn.father eq '49' }">
					<tr>
						<th>品牌故事</th>
						<td>
							<textarea id="editor" name="contents" style="width:870px;height:400px;">${enterpriseColumn.contents}</textarea>
						</td>
					</tr>
				</c:if>
				<tr>
					<td colspan="2" style="text-align:center;">
						<input type="submit" class="btn" value="提 交 "/>
					</td>
				</tr>
			</table>
			<s:token></s:token>
		</form>
	</body>
</html>

