<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/common/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<link href="${ctx}/static/jquery-validation/1.11.1/validate.css" type="text/css" rel="stylesheet" />
<script src="${ctx}/static/jquery-validation/1.11.1/jquery.validate.min.js" type="text/javascript"></script>
<script src="${ctx}/static/jquery-validation/1.11.1/messages_bs_zh.js" type="text/javascript"></script>
<script src="${ctx}/static/hawtai_pc/js/user.js" type="text/javascript"></script>
</head>
<body>
	<p>当前位置：用户管理 > 新增用户</p>
	<div class="newuesrbox">
		<form id="myform" action="${ctx}/userInfo/${action}" method="post">
			<input type="hidden" name="id" value="${user.id}"/>
			<input type="hidden" name="fid" value="${fid}"/>
			<input type="hidden" name="cid" value="${cid}"/>
			<c:if test="${action eq 'addUserInfo'}">
			<div class="linebox">
				<div class="leftline">用户名：</div>
				<div class="rightline">
					<input type="text" id="userName" name="userName" value="${user.userName}" />
				</div>
			</div>
			<div class="linebox">
				<div class="leftline">密码：</div>
				<div class="rightline">
					<input type="password" id="password" name="password" value="${password}" />
				</div>
			</div>
			<div class="linebox">
				<div class="leftline">重复密码：</div>
				<div class="rightline">
					<input type="password" id="plainPassword" name="plainPassword" value="${plainPassword}" />
				</div>
			</div>
			</c:if>
			<c:if test="${action eq 'updateUserInfo'}">
			<div class="linebox">
				<div class="leftline">用户名：</div>
				<div class="rightline">
					<input type="text" id="userName" name="userName" value="${user.userName}" disabled="disabled"/>
				</div>
			</div>
			</c:if>
			<div class="linebox">
				<div class="leftline">姓名：</div>
				<div class="rightline">
					<input type="text" name="name" value="${user.name}" />
				</div>
			</div>
			<div class="linebox">
				<div class="leftline">所属城市：</div>
				<div class="rightline">
					<input type="hidden" id="city" name="city" value="" />
					<select name="cityId" id="cityId" >
						<option value="">-----请选择-----${user.cityId }</option>
					  <c:forEach items="${cityList}" var="city">
						<option value="${city.id}" <c:if test="${city.id eq user.cityId}">selected="selected"</c:if>>${city.name}</option>
					  </c:forEach>
					</select>
				</div>
			</div>
			<div class="linebox">
				<div class="leftline">联系电话：</div>
				<div class="rightline">
					<input type="text" name="tel" value="${user.tel}" />
				</div>
			</div>
			<div class="linebox">
				<div class="leftline">手机号：</div>
				<div class="rightline">
					<input type="text" name="phone" value="${user.phone}" />
				</div>
			</div>
			<div class="linebox">
				<div class="leftline">Email：</div>
				<div class="rightline">
					<input type="text" name="email" value="${user.email}" />
				</div>
			</div>
		<div style="clear: both;"></div>
		<div class="anniuok">
			<a href="#" id="userSave"><div class="okbutton">确定</div></a> 
			<a href="javascript:history.go(-1)"><div class="okout">取消</div></a>
		</div>
		</form>
	</div>
	<script type="text/javascript">
	$(function(){
	    var validate = $("#myform").validate({
			rules: {
				userName: {
					required: true,
					minlength: 3,
					maxlength: 10,
					remote : {
						url : "${ctx}/userInfo/checkExistByUserName",
						type : "get",
						cache:false,
						dataType : "json",
						data : {
							userName: function() {
								return $("#userName").val();
							}
						}
					}
				},
				password: {
					required: true,
					minlength: 6
				},
				plainPassword: {
					required: true,
					minlength: 6,
					equalTo: "#password"
				}
			},
			messages: {
				userName: {
					required: "用户名不能为空",
					minlength: "用户名长度不能小于3个字符",
					maxlength: "用户名长度不能大于10个字符",
					remote: "该用户名已经存在"
	            },
				password: {
	                required: "没有填写密码",
	                minlength: "密码不能小于{0}个字符"
	            },
	            plainPassword: {
	                required: "没有确认密码",
	                minlength: "确认密码不能小于{0}个字符",
	                equalTo: "两次输入密码不一致"
	            }
			}
		});
	    
	    $("#cityId").change(function(){
	    	var city = $("#cityId").find("option:selected").text();
	    	$("#city").val(city);
	    })
	    
		$("#userSave").click(function(){
			$("#myform").submit();
		});
	}); 
	</script>
</body>
</html>
