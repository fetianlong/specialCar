<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
    <a href="${ctx}/global/test?langType=zh">中文</a> | <a href="${ctx}/global/test?langType=en">英文</a><br/>

    下面展示的是后台获取的国际化信息：<br/>
    ${money}<br/>
    ${date}<br/>

    下面展示的是视图中直接绑定的国际化信息：<br/>
<%--     <spring:eval expression="contentModel.money"></spring:eval><br/> --%>
<%--     <spring:message code="username"/>:<br/> --%>
<%--     <spring:eval expression="contentModel.date"></spring:eval><br/> --%>
    
</body>
</html>