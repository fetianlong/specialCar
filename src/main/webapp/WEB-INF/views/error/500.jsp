<%@ page contentType="text/html;charset=UTF-8" isErrorPage="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%@ page import="org.slf4j.Logger,org.slf4j.LoggerFactory" %>
<%	
	//设置返回码200，避免浏览器自带的错误页面
	response.setStatus(200);
	//记录日志
	Logger logger = LoggerFactory.getLogger("500.jsp");
	logger.error(exception.getMessage(), exception);
%>
<!-- 
<!DOCTYPE html>
<html>
<head>
	<title>500 - 系统内部错误</title>
</head>

<body>
	<h2>500 - 系统发生内部错误.</h2>
</body>
</html> -->
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>500</title>
</head>

<body>
<div style="width:100%;">
	<img src="${ctx}/static/img/500.png" style="width:579px;margin:150px auto;display:block;margin-bottom:40px;">
    <div style="width:470px;margin:0 auto;">
    <a href="javascript:history.go(-1)" style="display:inline-block;width:200px;height:50px;background:#1c90bf;text-align:center;line-height:50px;text-decoration:none;float:left;color:#fff;font-size:22px;font-family:'微软雅黑';">返回上一页</a>
    <a href="<c:url value="/"/>" style="display:inline-block;width:200px;height:50px;background:#1c90bf;text-align:center;line-height:50px;text-decoration:none;border-radius:2px;float:right;color:#fff;font-size:22px;font-family:'微软雅黑'">返回首页</a>
    
    </div>
</body>
</html>