<%@ page contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>MCCMS</title>
		<%@ include file="/common/taglibs.jsp"%>
		<%@ include file="/common/meta.jsp"%>
		<%@	include file="/common/content.jsp" %>
		<script>
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
		    		alert('图片类型不正确，请上传正确的格式！\n(jpg,gif,bmp,png,jpeg)');
		  	   		cleanFilePath(elepath);
			   		return false;
		  	   }
		  }else{
			  if(!sy_FilterFile(path,"jpg,gif,bmp,png,jpeg")){
		   		alert('图片类型不正确，请上传正确的格式！\n(jpg,gif,bmp,png,jpeg)');
			    cleanFilePath(elepath);
			    return false;
			  }
			  if(sy_GetImageToGetSize(path).fileSize>1024000){
		    	alert('上传文件大小不能够超过1MB！');
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
	</head>
	<body>
		<div class="rhead">
			<div class="rpos">
				当前位置: 基本配置 - 公共信息管理
			</div>
			<div class="ropt"><a class="btn" href="javascript:history.back();">返回</a></div>
			<div class="clear"></div>
		</div>
		<form action="standbyAction!update.action" id="form1" name="form1" method="post" enctype="multipart/form-data">
		<input type="hidden" name="id" value="${id}"/>
		    <table class="table table-bordered inner-table">
		    	<c:forEach items="${standbys }" var="standby" begin="0" end="0">
		    	<tr>
		    		<th>公司名字：</th>
		    		<td class="row">	
		    			<input type="text" id="" name="standby.standby1"
							value="${standby.standby1}" class="{required:true,maxlength:35}" />
					</td>
		    	</tr>
		    	<tr>
		    		<th>全国热线：</th>
		    		<td class="row">	
		    			<input type="text" id="" name="standby.standby2"
							value="${standby.standby2}" class="{required:true,maxlength:35}" />
					</td>
		    	</tr>
		    	<!-- <tr>
		    		<th>首页右上角联系电话：</th>
		    		<td class="row">	
		    			<input type="text" id="" name="standby.standby3"
							value="${standby.standby3}" class="{required:true,maxlength:35}" />
					</td>
		    	</tr>
		    	<tr>
		    		<th>左栏联系人：</th>
		    		<td class="row">	
		    			<input type="text" id="" name="standby.standby4"
							value="${standby.standby4}" class="{required:true,maxlength:35}" />
					</td>
		    	</tr>
		    	<tr>
		    		<th>左栏地址：</th>
		    		<td class="row">	
		    			<input type="text" id="" name="standby.standby5"
							value="${standby.standby5}" class="{required:true,maxlength:35}" />
					</td>
		    	</tr>
		    	<tr>
		    		<th>左栏电话：</th>
		    		<td class="row">	
		    			<input type="text" id="" name="standby.standby6"
							value="${standby.standby6}" class="{required:true,maxlength:35}" />
					</td>
		    	</tr>
		    	<tr>
		    		<th>左栏传真：</th>
		    		<td class="row">	
		    			<input type="text" id="" name="standby.standby7"
							value="${standby.standby7}" class="{required:true,maxlength:35}" />
					</td>
		    	</tr>
		    	<tr>
		    		<th>左栏传真：</th>
		    		<td class="row">	
		    			<input type="text" id="" name="standby.standby8"
							value="${standby.standby8}" class="{required:true,maxlength:35}" />
					</td>
		    	</tr>
		    	<tr>
		    		<th>左栏传真：</th>
		    		<td class="row">	
		    			<input type="text" id="" name="standby.standby9"
							value="${standby.standby9}" class="{required:true,maxlength:35}" />
					</td>
		    	</tr>
		    	<tr>
		    		<th>左栏传真：</th>
		    		<td class="row">	
		    			<input type="text" id="" name="standby.standby10"
							value="${standby.standby10}" class="{required:true,maxlength:35}" />
					</td>
		    	</tr>
		    	<tr>
		    		<th>左栏传真：</th>
		    		<td class="row">	
		    			<input type="text" id="" name="standby.standby11"
							value="${standby.standby11}" class="{required:true,maxlength:35}" />
					</td>
		    	</tr>
		    	<tr>
		    		<th>左栏传真：</th>
		    		<td class="row">	
		    			<input type="text" id="" name="standby.standby12"
							value="${standby.standby12}" class="{required:true,maxlength:35}" />
					</td>
		    	</tr> -->
		    	<%-- <tr>
		    		<th>二维码：</th>
		    		<td class="row">
		    		<a href="${ctx}/upload/enterprice/${standby.bgphoto1}"
							target="_blank" name="imgchange" id="imgchange">
							<img id="imgShowTeacherPhoto" name="imgShowTeacherPhoto" border="0"
								width="96"
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
						</a>
						<br />
						<input  type="file" id="upload" name="upload"
							 size="30" onChange="doSelectPhotoChanged('upload');"/>
		    		</td>
		    	</tr>
		    	<tr>
		    		<th>公司logo：</th>
		    		<td class="row">
		    		<a href="${ctx}/upload/enterprice/${standby.bgphoto2}"
							target="_blank" name="imgchange" id="imgchange">
							<img id="imgShowTeacherPhoto" name="imgShowTeacherPhoto" border="0"
								width="96"
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
						</a>
						<br />
						<input  type="file" id="upload2" name="upload2"
							 size="30" onChange="doSelectPhotoChanged('upload2');"/>
		    		</td>
		    	</tr> --%>
		    	
		    	<%-- <tr>
		    		<th>软文顶部图片：</th>
		    		<td class="row">
		    		<a href="${ctx}/upload/enterprice/${standby.bgphoto3}"
							target="_blank" name="imgchange" id="imgchange">
							<img id="imgShowTeacherPhoto" name="imgShowTeacherPhoto" border="0"
								width="96"
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
						</a>
						<br />
						<input  type="file" id="upload3" name="upload3"
							 size="30" onChange="doSelectPhotoChanged('upload3');"/>
		    		</td>
		    	</tr> --%>
		    	<!-- <tr>
		    		<th>公司logo：</th>
		    		<td class="row">
		    		<a href="${ctx}/upload/enterprice/${standby.bgphoto4}"
							target="_blank" name="imgchange" id="imgchange">
							<img id="imgShowTeacherPhoto" name="imgShowTeacherPhoto" border="0"
								width="96"
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
						</a>
						<br />
						<input  type="file" id="upload4" name="upload4"
							 size="30" onChange="doSelectPhotoChanged('upload4');"/>
		    		</td>
		    	</tr>
		    	<tr>
		    		<th>公司logo：</th>
		    		<td class="row">
		    		<a href="${ctx}/upload/enterprice/${standby.bgphoto5}"
							target="_blank" name="imgchange" id="imgchange">
							<img id="imgShowTeacherPhoto" name="imgShowTeacherPhoto" border="0"
								width="96"
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
						</a>
						<br />
						<input  type="file" id="upload5" name="upload5"
							 size="30" onChange="doSelectPhotoChanged('upload5');"/>
		    		</td>
		    	</tr>
		    	<tr>
		    		<th>公司logo：</th>
		    		<td class="row">
		    		<a href="${ctx}/upload/enterprice/${standby.bgphoto6}"
							target="_blank" name="imgchange" id="imgchange">
							<img id="imgShowTeacherPhoto" name="imgShowTeacherPhoto" border="0"
								width="96"
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
						</a>
						<br />
						<input  type="file" id="upload6" name="upload6"
							 size="30" onChange="doSelectPhotoChanged('upload6');"/>
		    		</td>
		    	</tr>
		    	<tr>
		    		<th>公司logo：</th>
		    		<td class="row">
		    		<a href="${ctx}/upload/enterprice/${standby.bgphoto7}"
							target="_blank" name="imgchange" id="imgchange">
							<img id="imgShowTeacherPhoto" name="imgShowTeacherPhoto" border="0"
								width="96"
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
						</a>
						<br />
						<input  type="file" id="upload7" name="upload7"
							 size="30" onChange="doSelectPhotoChanged('upload7');"/>
		    		</td>
		    	</tr>
		    	<tr>
		    		<th>公司logo：</th>
		    		<td class="row">
		    		<a href="${ctx}/upload/enterprice/${standby.bgphoto8}"
							target="_blank" name="imgchange" id="imgchange">
							<img id="imgShowTeacherPhoto" name="imgShowTeacherPhoto" border="0"
								width="96"
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
						</a>
						<br />
						<input  type="file" id="upload8" name="upload8"
							 size="30" onChange="doSelectPhotoChanged('upload8');"/>
		    		</td>
		    	</tr>
		    	<tr>
		    		<th>公司logo：</th>
		    		<td class="row">
		    		<a href="${ctx}/upload/enterprice/${standby.bgphoto9}"
							target="_blank" name="imgchange" id="imgchange">
							<img id="imgShowTeacherPhoto" name="imgShowTeacherPhoto" border="0"
								width="96"
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
						</a>
						<br />
						<input  type="file" id="upload9" name="upload9"
							 size="30" onChange="doSelectPhotoChanged('upload9');"/>
		    		</td>
		    	</tr>
		    	<tr>
		    		<th>公司logo：</th>
		    		<td class="row">
		    		<a href="${ctx}/upload/enterprice/${standby.bgphoto10}"
							target="_blank" name="imgchange" id="imgchange">
							<img id="imgShowTeacherPhoto" name="imgShowTeacherPhoto" border="0"
								width="96"
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
						</a>
						<br />
						<input  type="file" id="upload10" name="upload10"
							 size="30" onChange="doSelectPhotoChanged('upload10');"/>
		    		</td>
		    	</tr>
		    	 -->
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