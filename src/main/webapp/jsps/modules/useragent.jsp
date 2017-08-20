<%@ include file="/common/taglibs.jsp"%>
<script language="javascript" type="text/javascript">
<!--
	
	if(window.navigator.userAgent.indexOf("MSIE")>=1){
		var needPngFix = false;
		//IE
		var b_version=navigator.appVersion;
		var version=b_version.split(";");
		var trim_Version=version[1].replace(/[ ]/g,"");
		
		if(trim_Version=="MSIE6.0")
		{
			setActiveStyleSheet("IE6-style.css");
		}
		else{
			setActiveStyleSheet("IE7-style.css");
		}
	}
	else if(window.navigator.userAgent.indexOf("Firefox")>=1){
		setActiveStyleSheet("FF-style.css");
	}else if(window.navigator.userAgent.indexOf("Chrome")>=1){
		setActiveStyleSheet("style.css");
	}
	else{
		setActiveStyleSheet("style.css");
	}
	
	function setActiveStyleSheet(filename){
		document.write("<link href='${ctx}/jsps/theme/${webSite.webSkin.filename}/image/"+filename+"' type='text\/css' rel='stylesheet'>");
	}
//-->
</script>