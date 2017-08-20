// JavaScript Document
/* 扩展js中string的功能 */
String.prototype.replaceAll = function(AFindText, ARepText){
	raRegExp = new RegExp(AFindText,"g");
	return this.replace(raRegExp, ARepText);
}

/* 获取浏览器宽高度 */
function getWindowSize() {
	var client = {
		x:0,
		y:0
	};
 
	if(typeof document.compatMode != 'undefined' && document.compatMode == 'CSS1Compat') {
		client.x = document.documentElement.clientWidth;
		client.y = document.documentElement.clientHeight;
	} else if(typeof document.body != 'undefined' && (document.body.scrollLeft || document.body.scrollTop)) {
		client.x = document.body.clientWidth;
		client.y = document.body.clientHeight;
	}
 
	return client;
}

/* 判断是否IE7浏览器 */
function isIE7(){
	if(window.navigator.userAgent.indexOf("MSIE 7.0")>=1){
		return true;
	}else{
		return false;
	}
}

/* 全局设置宽高调用函数 */
$(document).ready(function(){
	setWH();
	$(window).resize(function() {
		setWH();
	});
});

/* 设置mainFrame里头的location的样式 */
function setLocaWidth(){
	$("#common > div").width($("#main",window.top.document.body).width() - 53);
	$("#common .loca .path").width($("#common .loca").width() + 2);
	$("#common .loca .path .shade").width($("#common .loca").width() + 2);
}

/* 设置footer随屏幕高度变化的动态效果 */
function setBottomWithWindowSize(target, height){
	var size = getWindowSize();
	var finalHeight = size.y;
	
	if(finalHeight > height){
		$(target).css("bottom","0px");
	}else{
		$(target).css("bottom","auto");
	}
}

/* 设置框架内main的高度 */
function setHeightWithWindow(target, offset){
	var size = getWindowSize();
	var finalHeight = size.y;
	
	finalHeight = finalHeight - $("#top").height() - $("#footer").height();
	if(offset){
		finalHeight = finalHeight - offset;
	}
	$(target).height(finalHeight);
}

/* 设置框架内main的宽度 */
function setWidthWithWindow(target){
	var size = getWindowSize();
	var finalWidth = size.x;
	
	finalWidth = finalWidth - $("#left").width() - 20;
	$(target).width(finalWidth);
}

/* 展示三级菜单 */
function showThreeLeverMenu(){
	$("#left .left_menu ul > li").hover(function(){
		$(this).find(".three_lever").fadeIn();
	}, function(){
		$(this).find(".three_lever").hide();
	});
}

/* 左侧菜单切换列表 */
function showLeftTabMenu(tab, total){
	for(var i = 1; i <= total; i++){
		$("#left_tab_" + i).removeClass("cur");
	}
	$("#left_tab_" + tab).addClass("cur");
	
	if(tab == 1){
		$("#left .left_menu_wrap").animate({marginLeft:"0px"});
	}else{
		$("#left .left_menu_wrap").animate({marginLeft:"-148px"});
	}
}

/* 展示与隐藏右侧公告列表 */
function showRightList(){
	var status = $("#right").css("right");
	status = parseInt(status);
	if(status != 0){
		$("#right").animate({right:"0px"});
		$("#right .btn").animate({left:"-9px"});
		$("#right .btn").removeClass("out");
		$("#right .btn").addClass("in");
		$("#right .title").show();
		$("#right .content").show();
	}else{
		$("#right").animate({right:"-204px"},"","",function(){
			$("#right .title").hide();
			$("#right .content").hide();
		});
		$("#right .btn").animate({left:"-17px"});
		$("#right .btn").removeClass("in");
		$("#right .btn").addClass("out");
	}
}

/* 左侧二级菜单展示当前所选项 */
function checkedItem(){
	$("#left .left_menu > li > ul > li").removeClass("onchecked");
	$(this).parent().addClass("onchecked");
	var secondColName = $(this).html();
	var firstColName = $(this).parentsUntil($("ul[id*=childMenu]")).parent().prev().find("span").html();
	$("#location").html(firstColName + "&nbsp;&nbsp;&gt;&nbsp;" + secondColName);
}

/* 左侧三级菜单展示当前所选项 */
function checkedSecondItem(){
	$("#left .left_menu > li > ul > li").removeClass("onchecked");
	$(this).parent().parent().parent().addClass("onchecked");
	var thirdColName = $(this).find("li").html();
	var secondColName = $(this).prevAll().filter("li.title").html();
	var firstColName = $(this).parentsUntil($("ul[id*=childMenu]")).parent().prev().find("span").html();
	$("#location").html(firstColName + "&nbsp;&nbsp;&gt;&nbsp;" + secondColName + "&nbsp;&nbsp;&gt;&nbsp;" + thirdColName);
}

/* 用于设置结果列表的高度 */
function setHeightInCommon(target){
	var finalHeight = 0;
	
	var searchPanelTop = $("#common .search_panel").css("margin-top");
	var resultPanelTop = $("#common .result_panel").css("margin-top");
	var pagerTop = $("#common .pager").css("margin-top");
	
	searchPanelTop = parseInt(searchPanelTop);
	resultPanelTop = parseInt(resultPanelTop);
	pagerTop = parseInt(pagerTop);
	
	if($("#common .search_panel").height() != null){
		finalHeight = $("#mainframe",window.top.document.body).height() - $("#common .search_panel").height() - $("#common .pager").height() - searchPanelTop - resultPanelTop - pagerTop - 42;
	}else{
		finalHeight = $("#mainframe",window.top.document.body).height() - $("#common .pager").height() - resultPanelTop - pagerTop - 42;
	}
	$(target).height(finalHeight);
}

/* 显示全部与隐藏全部 */
function showAll(exampleWidth){
	//用查找面板显示与否作为判断开闭条件
	var status = $("#common .search_panel .content").css("display");
	if(status != 'none'){
		//控制页面小图标及文字变化
		$("#common .search_panel .title .showall").removeClass("up");
		$("#common .search_panel .title .showall").addClass("down");
		$("#common .search_panel .title .showall").html("显示全部");
		
		//计算结果面板的显示高度
		var panelHeight = $("#common .result_panel").height() + $("#common .search_panel .content").height() + parseInt($("#common .search_panel .content").css("padding-top"));
		animateForResultPanel(panelHeight);
		
		//收起查找面板
		$("#common .search_panel .content").slideUp();
	}else{
		//控制页面小图标及文字变化
		$("#common .search_panel .title .showall").removeClass("down");
		$("#common .search_panel .title .showall").addClass("up");
		$("#common .search_panel .title .showall").html("隐藏全部");
		
		//计算结果面板的显示高度
		var panelHeight = $("#common .result_panel").height() - $("#common .search_panel .content").height() - parseInt($("#common .search_panel .content").css("padding-top"));
		animateForResultPanel(panelHeight);
		
		//展开查找面板并控制查找按钮的样式
		$("#common .search_panel .content").slideDown();
		$("#common .search_panel .content .search_panel_btn").css("margin-right",($("#example").width() - exampleWidth) + "px");
	}
}

/* 结果面板动画效果 */
function animateForResultPanel(panelHeight){
	$("#common .result_panel").animate({height:panelHeight + "px"});
	//IE7浏览器下特殊处理
	if(isIE7()){
		$("#common .result_panel .scroll").animate({height:panelHeight - 62 + "px"});
		$("#common .result_panel .scroll2").animate({height:panelHeight - 76 + "px"},"normal","",function(){
			if($("#common .result_panel .scroll2 .eXtremeTable .gift").length > 0){
				$("#common .result_panel .scroll2 .eXtremeTable .gift").remove();
			}else{
				$("#common .result_panel .scroll2 .eXtremeTable > table > tbody").append("<tr class='gift'></tr>");
			}
			$("#common .result_panel .scroll2 .mCSB_buttonDown").css("top",$("#common .result_panel .scroll2 .mCSB_draggerContainer").height() + "px");
		});
	}else{
		$("#common .result_panel .scroll").animate({height:panelHeight - 76 + "px"},"normal","",function(){
			if($("#common .result_panel .scroll2 .eXtremeTable .gift").length > 0){
				$("#common .result_panel .scroll2 .eXtremeTable .gift").remove();
			}else{
				$("#common .result_panel .scroll2 .eXtremeTable > table > tbody").append("<tr class='gift'></tr>");
			}
		});
	}
}

/* 重置下拉列表 */
function resetSelect(range){
	$(range+" .tag_select").each(function(){
		$(this).html($(this).parent().find("li").first().html());
		$(this).parent().find("li").removeClass("open_selected").addClass("open");
		$(this).parent().find("li").first().removeClass("open").addClass("open_selected");
	});
	$("input[type='text']").each(function(i){
    	$(this).val(""); 
    });
	$("select").each(function(i){
    	$(this).val(""); 
    });
}

/* 分页数据初始化 */
function pageDataInit(tableNameId){
	var source = $(".statusBar").html();
	
	//总页数
	var pageCount = "";
	//总条数
	var total = source.substring(3,source.indexOf("条记录") - 1);
	//当前页码
	var pageNum = $("input[name=" + tableNameId + "_p]").val();
	//每页行数
	var pageSize = $("input[name=" + tableNameId + "_crd]").val();
	
	if(source != '找不到记录.'){
		var totalInt = total.replaceAll(",","");
		var testInt = totalInt/pageSize;
		var r = /(^[1-9]\d*$)/;       //正整数 
		if(r.test(testInt)){
			pageCount = testInt;
		}else{
			pageCount = Math.floor(testInt + 1);
		}
		total = '共' + total + '条记录';
		
		if(pageNum == 1 && pageNum == pageCount){
			$("#first").css("cursor","text");
			$("#front").css("cursor","text");
			$("#next").css("cursor","text");
			$("#last").css("cursor","text");
		}
		else if(pageNum == 1){
			$("#first").css("cursor","text");
			$("#front").css("cursor","text");
			$("#next").click(function(){gotoPageNo(tableNameId, parseInt(pageNum) + 1);});
			$("#last").click(function(){gotoPageNo(tableNameId, pageCount);});
		}
		else if(pageNum == pageCount){
			$("#first").click(function(){gotoPageNo(tableNameId, 1);});
			$("#front").click(function(){gotoPageNo(tableNameId, parseInt(pageNum) - 1);});
			$("#next").css("cursor","text");
			$("#last").css("cursor","text");
		}else{
			$("#first").click(function(){gotoPageNo(tableNameId, 1);});
			$("#front").click(function(){gotoPageNo(tableNameId, parseInt(pageNum) - 1);});
			$("#next").click(function(){gotoPageNo(tableNameId, parseInt(pageNum) + 1);});
			$("#last").click(function(){gotoPageNo(tableNameId, pageCount);});
		}
	}else{
		pageCount = 1;
		total = '找不到记录';
		$("#first").css("cursor","text");
		$("#front").css("cursor","text");
		$("#next").css("cursor","text");
		$("#last").css("cursor","text");
	}
	
	$("#total").html(total);
	$("#pageNum").html(pageNum);
	$("#pageCount").html(pageCount);
	$("select[name='" + tableNameId + "'] option[value=" + pageSize + "]").attr("selected","selected");
	$("#select_info_" + tableNameId).html(pageSize);
	$("#options_" + tableNameId + " li").removeClass("open_selected").addClass("open");
	$("#options_" + tableNameId + " li[value='" + pageSize + "']").removeClass("open").addClass("open_selected");
}

/* 分页跳转到第几页 */
function gotoPageNo(tableNameId, pageNum){
	$("input[name=" + tableNameId + "_p]").val(pageNum);
	$("#" + tableNameId).submit();
}

/* 分页改变每页行数 */
function changePageSize(tableNameId, pageSize){
	$("input[name=ec_eti]").val('');
	$("input[name=" + tableNameId + "_crd]").val(pageSize);
	$("input[name=" + tableNameId + "_p]").val(1);
	$("#" + tableNameId).submit();
}

/* 结果列表通用代码 */
function commonResultPanel(){
	$("#common .result_panel .eXtremeTable thead tr").first().hide();
	if(isIE7()){
		$("#common .result_panel .scroll").mCustomScrollbar({
			horizontalScroll:true,
			autoDraggerLength:true,
			mouseWheel:false,
			scrollButtons:{
				enable:true
			}
		});
		$("#common .result_panel .scroll2").mCustomScrollbar({
			horizontalScroll:false,
			autoDraggerLength:true,
			mouseWheel:true,
			scrollButtons:{
				enable:true
			},
			advanced:{
				updateOnBrowserResize: true,
				updateOnContentResize: true
			}
		});
		$("#common .result_panel .scroll2 .mCSB_buttonDown").css("top",$("#common .result_panel .scroll2 .mCSB_draggerContainer").height() + "px");
	}else{
		$("#common .result_panel .scroll").mCustomScrollbar({
			horizontalScroll:false,
			autoDraggerLength:true,
			mouseWheel:true,
			scrollButtons:{
				enable:true
			},
			advanced:{
				updateOnBrowserResize: true,
				updateOnContentResize: true
			}
		});
		$("#common .result_panel .scroll2").mCustomScrollbar({
			horizontalScroll:true,
			autoDraggerLength:true,
			mouseWheel:false,
			scrollButtons:{
				enable:true
			}
		});
		$("#common .result_panel .scroll2 .mCSB_scrollTools").width($("#common .result_panel .scroll2").width());
	}
}

/* 结果列表通用代码2 */
function commonResultPanel2(){
	if(isIE7()){
		$("#common .result_panel .scroll").height($("#common .result_panel").height() - 62);
		$("#common .result_panel .scroll2").height($("#common .result_panel").height() - 76);
		$("#common .result_panel .scroll2 .mCSB_buttonDown").css("top",$("#common .result_panel .scroll2 .mCSB_draggerContainer").height() + "px");
	}else{
		$("#common .result_panel .scroll").height($("#common .result_panel").height() - 76);
		$("#common .result_panel .scroll2 .mCSB_scrollTools").width($("#common .result_panel .scroll2").width());
	}
}

/* 展示右边面板 */
function showRightPanel(title, url){
	$("#right").show();
	$("#right .title .col").html(title);
	$("#rightframe").attr("src", url);
	var status = $("#right").css("right");
	status = parseInt(status);
	if(status != 0){
		$("#right").animate({right:"0px"});
		$("#right .btn").animate({left:"-9px"});
		$("#right .btn").removeClass("out");
		$("#right .btn").addClass("in");
		$("#right .title").show();
		$("#right .content").show();
	}
}

/* 隐藏右边面板 */
function hideRightPanel(){
	var status = $("#right").css("right");
	status = parseInt(status);
	if(status == 0){
		$("#right").animate({right:"-204px"},"","",function(){
			$("#right .title").hide();
			$("#right .content").hide();
			$("#right").hide();
		});
		$("#right .btn").animate({left:"-17px"});
		$("#right .btn").removeClass("in");
		$("#right .btn").addClass("out");
	}
}