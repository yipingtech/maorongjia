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
		$("#names").focus();
		$("#epAdForm").validate({
			 rules: { 
				'names': { 
        			required: true, 
        			maxlength:50
    			},
        	    'frontNum':{
        	    	maxlength:20
        	    },
        	    'orderColumn':{
        	    	required: true ,
        	    	number:true,
        	    	digits:true
        	    },
        	    'address':{
        	    	url:true
        	    },
        	    'intro': {
                    required: true ,
			        maxlength:5000
        	    },
            	'cost': {
			        maxlength:50
        	    },
            	'clientName': {
			        maxlength:50
        	    }
			},
			message:{
				'names': { 
        			required: "请输入广告名称", 
        			maxlength:"长度不能超过50个字符"
    			},
        	    'frontNum':{
        	    	maxlength:"长度不能超过20个字符"
        	    },
        	    'orderColumn':{
        	    	required:"请输入广告排序" ,
        	    	number: "请输入有效的数字",
        	    	digits: "必须输入整数",
        	    },
        	    'address':{
        	    	url : "请输入正确的链接地址"
        	    },
        	    'intro': {
                    required: "请输入广告简介" ,
			        maxlength:"长度不能超过5000个字符"
        	    },
            	'cost': {
			        maxlength:"长度不能超过50个字符"
        	    },
            	'clientName': {
			        maxlength:"长度不能超过50个字符"
        	    }
			},
			success: function(label) {
				label.html("&nbsp;").addClass("checked");
	        }	
		});
		
		document.getElementById("imgchange").rel=$("#imgShowTeacherPhoto").attr("src");
	});		
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
		    	alert('图片类型不正确，请上传正确的格式！\n(jpg,gif,bmp,png,jpeg)');
	  	   		cleanFilePath(elepath);
		   		return false;
	  	   }
	  }else{
		  if(!sy_FilterFile(path,"jpg,gif,bmp,png,jpeg")){
		    //图片类型不正确，请上传正确的格式！
		    alert('图片类型不正确，请上传正确的格式！\n(jpg,gif,bmp,png,jpeg)');
		    cleanFilePath(elepath);
		    return false;
		  }
		  if(sy_GetImageToGetSize(path).fileSize>1024000){
		    //上传文件大小不能够超过1MB！
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
				当前位置: 互动管理 - 广告信息管理 - 添加广告信息
			</div>
			<div class="ropt">
				<a class="btn" href="javascript:history.back();">返回</a>
			</div>			
			<div class="clear"></div>
		</div>
		<form action="enterpriseAdAction!add.action" method="post"
			id="epAdForm" name="epAdForm" enctype="multipart/form-data"
			method="post">
			<table class="table table-bordered inner-table">
				<tr>
					<th>
						广告名称
					</th>
					<td>
						<input type="text" name="names" size="100" />
					</td>
				</tr>

				<tr>
					<th>
						广告标识
					</th>
					<td>
						<input type="text" name="frontNum" />
					</td>
				</tr>
				<tr>
					<th>
						广告排序
					</th>
					<td>
						<input type="text" name="orderColumn" />
					</td>
				</tr>

				<tr>
					<th>
						广告图片
					</th>
					<td>
						<a href="${ctx}/upload/enterprice/${enterpriseAd.photo}"
							target="_blank" name="imgchange" id="imgchange"
							class="screenshot" rel="doSelectPhotoChanged('upload')"><img
								id="imgShowTeacherPhoto" name="imgShowTeacherPhoto" border="0"
								width="96"
								src="
										<c:choose>
											<c:when test="${empty enterpriseAd.photo}">
													${ctx}/upload/NoExpertPhoto.JPG
											</c:when>
											<c:otherwise>
												${ctx}/upload/enterprice/${enterpriseAd.photo}
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
						广告链接地址
					</th>
					<td>
						<input type="text" size="100" name="address" />
					</td>
				</tr>
				<tr>
					<th>
						 广告简介
					</th>
					<td>
						<textarea rows="10" cols="100" name="intro"></textarea>
					</td>
				</tr>
				<tr style="display:none;">
					<th>
						广告费用
					</th>
					<td>
						<input type="text" name="acost" value="0.0" />
					</td>
				</tr>
				<tr style="display:none;">
					<th>
						客户名称
					</th>
					<td>
						<input type="text" name="clientName" />
					</td>
				</tr>
				<tr style="display:none;">
					<th>
						显示时间
					</th>
					<td>
						<input type="text" name="initTime" onchange="checkDate()"
							id="initTime" readonly />
						<input id="starttimeBt" type="button" class="default_button"
							value='选择' />
						<script type="text/javascript">
						Calendar.setup({
							inputField     : "initTime",
							button	  	   : "starttimeBt",
							/*ifFormat       :    "%Y-%m-%d %H:%M",							
					        showsTime      :    true,
					        timeFormat     :    "24"*/
							ifFormat       :    "%Y-%m-%d"
						});
					</script>
						至
						<input type="text" name="endTime" onchange="checkDate()"
							id="endTime" readonly />
						<input id="endtimeBt" type="button" class="default_button"
							value='选择' />
						<script type="text/javascript">
						Calendar.setup({
							inputField     : "endTime",
							button	  	   : "endtimeBt",
							/*ifFormat       :    "%Y-%m-%d %H:%M",							
					        showsTime      :    true,
					        timeFormat     :    "24"*/
							ifFormat       :    "%Y-%m-%d"
						});
					</script>
					</td>
				</tr>
				<tr>
					<th>
						状态
					</th>
					<td>
						<select name="enterpriseAd.state">
							<option value="1">
								已启用
							</option>
							<option value="0">
								已停用
							</option>
						</select>
					</td>
				</tr>
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

