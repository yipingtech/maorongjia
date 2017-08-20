<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>${webSite.title}</title>
		<%@ include file="/common/taglibs.jsp"%>
		<%@ include file="/common/meta.jsp"%>
		<%@ include file="/common/content.jsp"%>
	</head>
	<body>
		<div class="rhead">
			<div class="rpos">当前位置: 基本配置 - 欢迎信息</div>
			<security:authorize ifAnyGranted="BASE_WELCOME_INSTRUCTION">
				<div class="ropt">
					<a href="" class="btn">操作说明书</a>
				</div>
			</security:authorize>
			<div class="clear"></div>
		</div>
		<table class="table table-bordered" style="margin-bottom: 5px;">
		   <tr>
		   	   <th colspan="2">
		   	   		系统属性
		   	   </th>
		   </tr>
		</table>
		<security:authorize ifAnyGranted="BASE_WELCOME_BROWSER">
			<table class="table table-bordered">
		    	<tr>
		    		<td colspan="2">推荐浏览器下载：
		    		<p style="color:red;">
		    			Google浏览器下载：
						<a href="http://www.google.cn/chrome/eula.html?hl=zh-CN&platform=win" target="_blank">
							点击下载
						</a>
		    		</p>
		    		<label class="comments">
							（Chrome 浏览器是一款设计极简洁、技术先进的浏览器，它可以让您更快速、安全且轻松地使用网络。适用于 Windows XP、Windows Vista 和 Windows 7）
					</label>
		    		<p style="color:red;">
			    		 IE9浏览器下载：
			    		 <a href="http://mydown.yesky.com/ie9/BOIE9_ZHCN_WIN7.EXE"  target="_blank">
			    			点击下载
			    		</a>
		    		</p>
		    		<label class="comments">
		    			(全新的 Internet Explorer 9 浏览器（以下简称 IE 9），挖掘电脑硬件潜能，充分利用多核 CPU 及显卡性能，让浏览速度更快，色彩更真实、图形更清晰。适用于 Windows7、VistaSP2。)
		    		</label>
		    		<p  style="color:red;">
		    			 IE8浏览器下载：
		    			 <a href="http://download.microsoft.com/download/1/6/1/16174D37-73C1-4F76-A305-902E9D32BAC9/IE8-WindowsXP-x86-CHS.exe"  target="_blank">
		    			 	点击下载
		    			 </a>
		    		</p>
		    		<label class="comments">
		    			(Internet Explorer 8 浏览器（以下简称 IE 8）中新增的“加速器”让您无论在浏览哪个网页，都可以更快、更轻松地直接调用您所需要的 Web 服务，而无需打开新的窗口。适用于 Windows7、Vista/XP。)
		    		</label>
		    		<p  style="color:red;">
		    			360浏览器下载：
		    			<a href="http://down.360safe.com/se/360se_5.0_20120425.exe"  target="_blank">
		    				点击下载
		    			</a>
		    		</p>
		    		<label class="comments">
		    			(360极速浏览器(360chrome)无缝融合双核引擎，最好用的极速双核浏览器！ 360极速浏览器采用了最快速的Chromium内核及兼容性最好的IE内核。适用于 Windows7、Vista/XP。)
		    		</label>
		    		</td>
		    	</tr>
		    </table>
		</security:authorize>
	</body>
</html>

