

$(function(){
    $(".ce > li > a").click(function(){
	     $(this).addClass("xz").parents().siblings().find("a").removeClass("xz");
		 $(this).parents().siblings().find(".er").hide(300);
		 $(this).siblings(".er").toggle(300);
		 $(this).parents().siblings().find(".er > li > .thr").hide().parents().siblings().find(".thr_nr").hide();
		
	})
	
    $(".er > li > a").click(function(){
        $(this).addClass("sen_x").parents().siblings().find("a").removeClass("sen_x");
        $(this).parents().siblings().find(".thr").hide(300);	
	    $(this).siblings(".thr").toggle(300);	
	})

    $(".thr > li > a").click(function(){
	     $(this).addClass("xuan").parents().siblings().find("a").removeClass("xuan");
		 $(this).parents().siblings().find(".thr_nr").hide();	
	     $(this).siblings(".thr_nr").toggle();
	})


})

$(function(){
$(".newadds").click(function () {
        $(".zhezhao").css({
            display: "block", height: $(document).height()
        });
        SCAheight=$("#divSCA").outerHeight();
        $("#divSCA").css("margin-top",-(SCAheight/2));
        var $box = $('#divSCA');
        $box.css({
           
            display: "block"
        });
        
    });
$(".newadd").click(function () {
    $(".zhezhao").css({
        display: "block", height: $(document).height()
    });
    SCAheight=$("#divSCA_sm").outerHeight();
    $("#divSCA_sm").css("margin-top",-(SCAheight/2));
    var $box = $('#divSCA_sm');
    $box.css({
       
        display: "block"
    });
    
});
    //点击关闭按钮的时候，遮罩层关闭
    $(".close").click(function () {
        $(".zhezhao,#divSCA_sm").css("display", "none");
    });
	
})
 
$(document).ready(function() { 
	$('.zhcon .tit a').click(function(){
        $(".zhcon .tit a").removeClass("active");
		$(this).addClass("active");
		$('.zhcon .ction').hide();
        $('.zhcon .ction:eq('+$(this).index()+')').show();
    });
});



























