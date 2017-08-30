/*! jQuery v1.7.2 jquery.com | jquery.org/license */
function SwapTab(name,title,content,Sub,cur){
  $(name+' '+title).click(function(){
	  $(this).addClass(cur).siblings().removeClass(cur);
	  $(content+" > "+Sub).eq($(name+' '+title).index(this)).show().siblings().hide();
  });
}
function divselect(divselectid,inputselectid) {
    var inputselect = $(inputselectid);
    $(divselectid+" cite").click(function(){
        event.stopPropagation();
        //var ul = $(divselectid+" ul");
        var ul = $(this).siblings('ul');
        var oDiv = $(divselectid);
        $(".info-group ul,.basic-group ul").hide();
        $('.divselect,divselect2').css('z-index', '100');
        if(ul.css("display")=="none"){
            ul.slideDown("fast");
            oDiv.css('z-index','1000');
        }else{
            ul.hide();
            oDiv.css('z-index','100');
        }
    });
    $("body").click(function(){
        var ul = $(divselectid+" ul");
        var oDiv = $(divselectid);
        if(ul.css("display")=="none"){
            oDiv.css('z-index','3');
        }else{
            ul.hide();
            oDiv.css('z-index','2');
        }
    });
    $(divselectid+" ul li a").click(function(){
        var txt = $(this).text();
        $(divselectid+" cite").html(txt);
        var value = $(this).attr("selectid");
        inputselect.val(value);
        $(divselectid+" ul").hide();
        
    });
};
$(function(){
     //菜单弹窗
    $('.menu').click(function(){
        $(this).find('.m-nav').slideToggle('fast');
    });
    //同意选中
    $('.ag-checkbox').click(function(event) {
    	$(this).toggleClass('ag-checkbox-selected')
    });

    //表单失去焦点
    $(".a-control").focus(function(){
            if($(this).val() ==this.defaultValue){  
                $(this).val("");           
            } 
      }).blur(function(){
           if ($(this).val() == '') {
              $(this).val(this.defaultValue);
           }
      });
    
    new divselect("#Money","#Money-option");
    new divselect("#dDate","#dDate-option");
    new divselect("#profession","#profession-option");
    new divselect("#Xueli","#Xueli-option");

    //详情
    $('.d-product-box .d-hd').click(function(){
        $(this).siblings('.d-bd').slideToggle('fast');
    })

    //圆扭
    $('.rec-hd .right').click(function(){
        $(this).toggleClass('right-active');
    });

    //资质评估2
    $('.sxnav-list2 >span').click(function(){
        $('.sxnav-list2').hide();
        $('.mask2').hide();
    });
    $('#month_1').click(function(){
        $("#time_1").show();
        $('.mask2').show();
    });
    $('#month_2').click(function(){
        $("#time_2").show();
        $('.mask2').show();
    });

     $('#gj_li').click(function(){
        $("#gj").show();
        $('.mask2').show();
    });
     $('#sb_li').click(function(){
        $("#sb").show();
        $('.mask2').show();
    });
     $('#house_li').click(function(){
        $("#house").show();
        $('.mask2').show();
    });
     $('#car_li').click(function(){
        $("#car").show();
        $('.mask2').show();
    });

    $('#time_1 li').click(function(){
        $(this).addClass('cur').siblings('li').removeClass('cur');
        $('.sxnav-list2').hide();
        $('.mask2').hide();
        var li_xt = $(this).text();
        $('#month_1').text(li_xt);
    });
    $('#time_2 li').click(function(){
        $('.sxnav-list2').hide();
        $('.mask2').hide();
        var li_xt = $(this).text();
        $('#month_2').text(li_xt);
    });

    $('#gj li').click(function(){
        $('.sxnav-list2').hide();
        $('.mask2').hide();
        var li_xt = $(this).text();
        $('#gj_li>span').text(li_xt);
    });
    $('#sb li').click(function(){
        $('.sxnav-list2').hide();
        $('.mask2').hide();
        var li_xt = $(this).text();
        $('#sb_li>span').text(li_xt);
    });
    $('#house li').click(function(){
        $('.sxnav-list2').hide();
        $('.mask2').hide();
        var li_xt = $(this).text();
        $('#house_li>span').text(li_xt);
    });
    $('#car li').click(function(){
        $('.sxnav-list2').hide();
        $('.mask2').hide();
        var li_xt = $(this).text();
        $('#car_li>span').text(li_xt);
    });


    $('#rz-tab li').eq(2).click(function(){
        $(this).addClass('active').siblings('li').removeClass('active');
        $('.sxnav-zdy').show();
        //$('body').addClass('b-hidden');
    })
    $('#rz-tab li').eq(0).click(function(){
        $(this).addClass('active').siblings('li').removeClass('active');
        $('.sxnav-zdy').hide();
        $('.sxnav-list').hide();
        $('.sxnav-list').eq(0).show();
        $('.mask1').show();
    })
    $('#rz-tab li').eq(1).click(function(){
        $(this).addClass('active').siblings('li').removeClass('active');
        $('.sxnav-zdy').hide();
        $('.sxnav-list').hide();
        $('.sxnav-list').eq(1).show();
        $('.mask1').show();
    })
    $('.mask1').click(function(){
        $('.sxnav-list').hide();
        $(this).hide();
    });

    $('.profession').click(function(){
        $('#zyBox').show();
        $('.mask2').show();
    });

    $('.qualifications').click(function(){
        $('#xlBox').show();
        $('.mask2').show();
    });

    $('#xlBox li').click(function(){
        $(this).addClass('cur').siblings('li').removeClass('cur');
        $('.sxnav-list2').hide();
        $('.mask2').hide();
        var li_xt = $(this).text();
        $('.qualifications').val(li_xt);
    });
    $('#zyBox li').click(function(){
        $(this).addClass('cur').siblings('li').removeClass('cur');
        $('.sxnav-list2').hide();
        $('.mask2').hide();
        var li_xt = $(this).text();
        $('.profession').val(li_xt);
    });

    $('.profession2').click(function(){
        $('#zyBox2').show();
        $('.mask2').show();
    });

    $('.qualifications2').click(function(){
        $('#xlBox2').show();
        $('.mask2').show();
    });

    $('#xlBox2 li').click(function(){
        $(this).addClass('cur').siblings('li').removeClass('cur');
        $('.sxnav-list2').hide();
        $('.mask2').hide();
        var li_xt = $(this).text();
        $('.qualifications2').val(li_xt);
    });
    $('#zyBox2 li').click(function(){
        $(this).addClass('cur').siblings('li').removeClass('cur');
        $('.sxnav-list2').hide();
        $('.mask2').hide();
        var li_xt = $(this).text();
        $('.profession2').val(li_xt);
    });

    $('.profession3').click(function(){
        $('#zyBox3').show();
        $('.mask2').show();
    });

    $('.qualifications3').click(function(){
        $('#xlBox3').show();
        $('.mask2').show();
    });

    $('#xlBox3 li').click(function(){
        $(this).addClass('cur').siblings('li').removeClass('cur');
        $('.sxnav-list2').hide();
        $('.mask2').hide();
        var li_xt = $(this).text();
        $('.qualifications3').val(li_xt);
    });
    $('#zyBox3 li').click(function(){
        $(this).addClass('cur').siblings('li').removeClass('cur');
        $('.sxnav-list2').hide();
        $('.mask2').hide();
        var li_xt = $(this).text();
        $('.profession3').val(li_xt);
    });

    $('.sxnav-zdy').height($(document).height()-130);

    if($("#zizhipinggupages").length>0)
    {
        var iwindowHeight = $(window).height();
        var iScrollHeight = $(".slimScrollDiv").height();
        $(window).resize(function () {
            if($(window).height()!=iwindowHeight)
            {
                resizePage ();
            }
            else
            {
                resizePage ();

            }
        });
        function resizePage ()
        {
            $('#dowebok').fullpage({
                verticalCentered:false,
                scrollOverflow:true,
                anchors: ['page1', 'page2']
            });
            $.fn.fullpage.setAllowScrolling(false);
        }
        resizePage ();

        if(/Android/.test(navigator.appVersion)){
            window.addEventListener("resize", function(){
                //alert("A");
                if(document.activeElement.tagName=="INPUT"){
                    window.setTimeout(function(){
                        document.activeElement.scrollIntoViewIfNeeded();
                    },0);
                }
            })
        }
    }

    
});