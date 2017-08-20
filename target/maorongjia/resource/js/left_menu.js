// JavaScript Document
var LastLeftID = "";
var parentname = "";

function init(para1, para2){
	LastLeftID = para1;
	parentname = para2;
}

function menuFix(menuid) {
	var obj = document.getElementById(menuid).getElementsByTagName("li");
	
	for (var i=0; i<obj.length; i++) {
	obj[i].onmouseover=function() {
	this.className+=(this.className.length>0? " ": "") + "sfhover";
	}
	obj[i].onMouseDown=function() {
	this.className+=(this.className.length>0? " ": "") + "sfhover";
	}
	obj[i].onMouseUp=function() {
	this.className+=(this.className.length>0? " ": "") + "sfhover";
	}
	obj[i].onmouseout=function() {
	this.className=this.className.replace(new RegExp("( ?|^)sfhover\\b"), "");
	}
	}
}

function DoMenu(emid)
{
	var nameid = 'name_'+emid;
	var nameobj = document.getElementById(nameid);
	var obj = document.getElementById(emid); 
	if($("#" + emid).hasClass("expanded")){
		$("#" + emid).slideUp("normal",function(){$(this).removeClass("expanded");$(this).addClass("collapsed");});
	}else{
		$("#" + emid).slideDown("normal",function(){$(this).removeClass("collapsed");$(this).addClass("expanded");});
	}
	nameobj.className = (nameobj.className.toLowerCase() == "open_parent_name"?"parent_name":"open_parent_name");
	
	if((LastLeftID!="")&&(emid!=LastLeftID)) //关闭上一个Menu
	{
		$("#" + LastLeftID).slideUp("normal",function(){$(this).removeClass("expanded");$(this).addClass("collapsed");});
	}
	if((parentname!="")&&(nameid!=parentname)) //改变上一个Menu的图标
	{
	document.getElementById(parentname).className = "parent_name";
	}
	LastLeftID = emid;
	parentname = nameid;
}

function GetMenuID()
{
	var MenuID="";
	var _paramStr = new String(window.location.href);
	var _sharpPos = _paramStr.indexOf("#");
	
	if (_sharpPos >= 0 && _sharpPos < _paramStr.length - 1)
	{
	_paramStr = _paramStr.substring(_sharpPos + 1, _paramStr.length);
	}
	else
	{
	_paramStr = "";
	}
	
	if (_paramStr.length > 0)
	{
	var _paramArr = _paramStr.split("&");
	if (_paramArr.length>0)
	{
	var _paramKeyVal = _paramArr[0].split("=");
	if (_paramKeyVal.length>0)
	{
	MenuID = _paramKeyVal[1];
	}
	}
	}
	
	if(MenuID!="")
	{
	DoMenu(MenuID)
	}
}