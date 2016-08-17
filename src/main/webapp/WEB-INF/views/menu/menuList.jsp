<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/common/taglibs.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
</head>
<body>
	<p>当前位置：菜单管理 > 菜单列表</p>
	<div class="newuesrbox">
		<form id="menu" action="${ctx}/menu/list" method="get">
			<input type="hidden" name="fid" value="${fid}">
			<input type="hidden" name="cid" value="${cid}">
			<div class="linebox">
				<div class="leftline_3">菜单名：</div>
				<div class="rightline_3">
					<c:set var="parentMenuId" value="${parentMenuId}" />
					<select name="parentMenuId" id="parentMenuId">
						<option value="">-----请选择-----</option>
					  <c:forEach items="${parentMenuList}" var="parentMenu">
						<option value="${parentMenu.id}" <c:if test="${parentMenu.id eq parentMenuId}">selected="selected"</c:if>>${parentMenu.name}</option>
					  </c:forEach>
					</select>
				</div>
				<div class="rightline_3">
			    	<button id="menuSearch" class="qury_an"><img src="${ctx}/static/hawtai_pc/imgs/anniu_2.png">&nbsp;查询</button>
					<a href="${ctx}/menu/add?fid=${fid}&cid=${cid}">
						<div class="add_an">
							<img src="${ctx}/static/hawtai_pc/imgs/anniu_3.png">&nbsp;新增 
						</div>
					</a>
				</div> 
			</div>
		</form>
		<div style="clear: both;"></div>
	</div>
	<div class="newuesrbox">
<!-- 		<p>车辆状态</p> -->
		<div class="tabbox">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr class="top_tr">
					<td>ID</td>
					<td>菜单名</td>
					<td>URL</td>
					<td>父节点ID</td>
					<td>创建日期</td>
					<td>操作</td>
				</tr>
				<c:forEach items="${menusList}" var="menu" varStatus="status">
					<tr <c:if test="${status.count%2==0}">class="count_tr2"</c:if> <c:if test="${status.count%2!=0}">class="count_tr"</c:if>>
						<td>
						<a class="a_zelor newadd" href="#" onclick="viewmenu('${menu.id}')">${menu.id}</a>
						</td>
						<td>${menu.name}</td>
						<td>${menu.url}</td>
						<td>${menu.parentId}</td>
						<td><fmt:formatDate value="${menu.createDateTime}" pattern="yyyy年MM月dd日" /></td>
						<td>
							<a class="a_zelor" href="${ctx}/menu/update/${menu.id}?fid=${fid}&cid=${cid}">修改</a>
							<a id="deletemenuByIda" onclick="deletemenu('${menu.id}')" href="#"><span>删除</span></a>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
<%-- 		<tags:pagination page="${pageData}" paginationSize="10"/> --%>
		<div class="zhezhao"></div>
		<script type="text/javascript">
		$(function(){
			$("#menuSearch").click(function(){
				//${ctx}/menu/list/?fid=${fid}&cid=${cid}
				$('#menu').submit();
			});
		})
		function deletemenu(uid){
			if(confirm("确定删除该菜单吗，此操作会删除跟此菜单所有关联的角色？")){
				var url =  "${ctx}/menu/delete/" + uid + "?fid=${fid}&cid=${cid}";
				window.location.href = url;
			}
		}
		</script>
	</div>
</body>
</html>
