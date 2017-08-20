// JavaScript Document
//for iPhone
window.onload=function(){  
	/*if(document.documentElement.scrollHeight <= document.documentElement.clientHeight) {  
		bodyTag = document.getElementsByTagName('body')[0];  
		bodyTag.style.height = document.documentElement.clientWidth / screen.width * screen.height + 'px';  
	}*/
	setTimeout(function() {  
		window.scrollTo(0, 1)  
	}, 0);  
};
function showPop(e) {
	var t = $(document).height();
	$(e).height(t).fadeIn()
}
function hidePop(e) {
	$(e).fadeOut()
}
