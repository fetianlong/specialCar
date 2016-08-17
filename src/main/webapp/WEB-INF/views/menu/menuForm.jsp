<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/common/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<link href="${ctx}/static/jquery-validation/1.11.1/validate.css" type="text/css" rel="stylesheet" />
<script src="${ctx}/static/jquery-validation/1.11.1/jquery.validate.min.js" type="text/javascript"></script>
<script src="${ctx}/static/jquery-validation/1.11.1/messages_bs_zh.js" type="text/javascript"></script>
<script src="${ctx}/static/hawtai_pc/js/menu.js" type="text/javascript"></script>
</head>
<body>
	<p>当前位置：菜单管理 > 新增菜单</p>
	<div class="newuesrbox">
		<form id="myform" action="${ctx}/menu/${action}" method="post">
			<input type="hidden" name="id" value="${menu.id}"/>
			<input type="hidden" name="fid" value="${fid}"/>
			<input type="hidden" name="cid" value="${cid}"/>
			<div class="linebox">
				<div class="leftline">菜单名：</div>
				<div class="rightline">
					<input type="text" id="name" name="name" value="${menu.name}" />
				</div>
			</div>
			<div class="linebox">
				<div class="leftline">路径URL：</div>
				<div class="rightline">
					<input type="text" id="url" name="url" value="${menu.url}" />
				</div>
			</div>
			<div class="linebox">
				<div class="leftline">所属菜单：</div>
				<div class="rightline">
					<input type="hidden" id="city" name="city" value="" />
					<c:set var="parentMenuId" value="${menu.parentId}" />
	                <select name="parentId">
	                	<option value="">-----请选择-----</option>
	                	<c:forEach items="${parentMenuList}" var="parentMenu">
						<option value="${parentMenu.id}" <c:if test="${parentMenu.id eq parentMenuId}">selected="selected"</c:if>>${parentMenu.name}</option>
					</c:forEach>
	               	</select>
				</div>
			</div>
			<div class="linebox">
				<div class="leftline">顺序：</div>
				<div class="rightline">
					<input type="text" name="ordIndex" value="${menu.ordIndex}" />
				</div>
			</div>
		<div style="clear: both;"></div>
		<div class="anniuok">
			<a href="#" id="menuSave"><div class="okbutton">确定</div></a> 
			<a href="javascript:history.go(-1)"><div class="okout">取消</div></a>
		</div>
		</form>
	</div>
	<script type="text/javascript">
	$(function(){
		$("#menuSave").click(function(){
			$("#myform").submit();
		});
	}); 
	</script>
</body>
</html>
