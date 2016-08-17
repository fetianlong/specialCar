<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%response.setStatus(200);%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>404</title>
</head>

<body>
<div style="width:100%;">
	<img src="${ctx}/static/img/404.png" style="width:712px;margin:150px auto;display:block;margin-bottom:40px;">
    <div style="width:470px;margin:0 auto;">
    <a href="javascript:history.go(-1)" style="display:inline-block;width:200px;height:50px;background:#1c90bf;text-align:center;line-height:50px;text-decoration:none;float:left;color:#fff;font-size:22px;font-family:'微软雅黑';">返回上一页</a>
    <a href="<c:url value="/"/>" style="display:inline-block;width:200px;height:50px;background:#1c90bf;text-align:center;line-height:50px;text-decoration:none;border-radius:2px;float:right;color:#fff;font-size:22px;font-family:'微软雅黑'">返回首页</a>
    
    </div>
</body>
</html>
