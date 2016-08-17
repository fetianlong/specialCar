<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator" %> 
<%@ include file="/WEB-INF/common/taglibs.jsp"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<title>华泰专车后台<sitemesh:title/></title>
<!-- <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<meta http-equiv="Cache-Control" content="no-store" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="0" /> -->
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<%-- <link type="image/x-icon" href="${ctx}/static/images/favicon.ico" rel="shortcut icon"> --%>
<link href="${ctx}/static/hawtai_pc/css/mainstyle.css" rel="stylesheet">

<script src="${ctx}/static/bootstrap/js/jquery_1.12.4/jquery.min.js" type="text/javascript" charset="utf-8"></script>
<script src="${ctx}/static/bootstrap/js/bootstrap.min.js" type="text/javascript" charset="utf-8"></script>

<script src="${ctx}/static/hawtai_pc/js/main.js"></script>

<sitemesh:head/>
</head>

<body>
	<%@ include file="/WEB-INF/layouts/header.jsp"%>
	<div style="clear:both"></div>
	<div class="countpage">
	<%@ include file="/WEB-INF/layouts/leftMenu.jsp"%>
	</div>
	<div class="countintos">
		<sitemesh:body/>
	</div>
</body>
</html>