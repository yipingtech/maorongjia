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
		
		if(${showMesscat}){
        	$.blockUI({ 
        		message: $('#message')
        		}) 
	        setTimeout($.unblockUI, 2000);
	         
	    }
	    
		$("#webSiteForm").validate({
			 rules: { 
			},
			success: function(label) {
				label.html("&nbsp;").addClass("checked");
	        }	
		});
	});	
	
	
</script>	
<style>
	.inner-table tr{
		display:none;
	}
</style>
	</head>
	<body>
      	<div class="rhead">
			<div class="rpos">
				当前位置: 基本配置  - 站点配置
			</div>
			<div class="clear"></div>
		</div>
		<form action="webSiteAction!update.action" method="post" id="webSiteForm" enctype="multipart/form-data" method="post">
		<input type="hidden" name="webSite.id" value="${webSite.id}"/>
	 	<table class="table table-bordered inner-table">
			<tr<security:authorize ifAnyGranted="BASE_WEBSITE_NAME"> style="display:table-row;"</security:authorize>>
				<th>站点名称</th>
				<td>
					<input type="text" size="60" name="webSite.names" value="${webSite.names}" class="{required: true,maxlength:30}"/>
												可自定义
				</td>
		   </tr>
		   <tr<security:authorize ifAnyGranted="BASE_WEBSITE_LINK"> style="display:table-row;"</security:authorize>>
				<th>首页链接</th>
				<td>
					<input type="text" size="60"  name="webSite.domain" value="${webSite.domain}"  class="{required: true,url:true,maxlength:50}"/>
						请修改为：http://您的域名:端口${ctx}(本地域名可以使用127.0.0.1代替域名)
				</td>
		   </tr>
		   <tr<security:authorize ifAnyGranted="BASE_WEBSITE_TITLE"> style="display:table-row;"</security:authorize>>
				<th>站点标题</th>
				<td>
					<input type="text" size="60" name="webSite.title" value="${webSite.title}"  class="{required:true,maxlength:200}"/>
												显示在网站的标题
				</td>
		   </tr>
	 		<tr<security:authorize ifAnyGranted="BASE_WEBSITE_PATH"> style="display:table-row;"</security:authorize>>
				<th>主目录名称</th>
				<td>
					<input type="text" size="60" name="webSite.htmlName" value="${webSite.htmlName}"  class="{required:true,maxlength:20}"/>
												生成静态网页时的主目录,例如(kgdoqi)
				</td>
		   </tr>
			<tr<security:authorize ifAnyGranted="BASE_WEBSITE_PORT"> style="display:table-row;"</security:authorize>>
				<th>站点端口</th>
				<td>
					<input type="text" size="60" name="webSite.port" value="${webSite.port}"  class="{required:true,digits:true,maxlength:10}"/>
												请不要修改
				</td>
		   </tr>
		   <tr<security:authorize ifAnyGranted="BASE_WEBSITE_COMPANY"> style="display:table-row;"</security:authorize>>
				<th>公司名称</th>
				<td>
					<input type="text"  size="60" name="webSite.company" value="${webSite.company}" class="{required:true,maxlength:30}"/>
												可自定义
				</td>
		   </tr>
		   <tr<security:authorize ifAnyGranted="BASE_WEBSITE_CORYRIGHT"> style="display:table-row;"</security:authorize>>
				<th>版权信息</th>
				<td>
					<textarea  cols="53" rows="10" name="webSite.copyright"  class="{required:true,maxlength:8000}">${webSite.copyright}</textarea>
					显示在网页底端的内容,支持HTML(特殊效果需要专业人士编写)
				</td>
		   </tr>
		   <tr<security:authorize ifAnyGranted="BASE_WEBSITE_ICP"> style="display:table-row;"</security:authorize>>
				<th>ICP备案</th>
				<td>
					<input type="text" size="60"  name="webSite.recodeCode" value="${webSite.recodeCode}"  class="{maxlength:40}"/>
					ICP备案信息
				</td>
		   </tr>
			<tr style="display:table-row;">
				<td colspan="2" style="text-align:center;">
					<input type="submit" class="btn" value="提 交 "/>
				</td>
			</tr>
		</table>
		</form>
		<div id="message" style="display:none">
			<s:actionmessage />
		</div>
			
	</body>
</html>

