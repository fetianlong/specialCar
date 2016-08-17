<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/common/taglibs.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
</head>
<body>
	<p>当前位置：角色管理 > 角色列表</p>
	<div class="newuesrbox">
		<form id="role" action="${ctx}/role/list" method="get">
			<input type="hidden" name="fid" value="${fid}">
			<input type="hidden" name="cid" value="${cid}">
			<div class="linebox">
				<div class="leftline_3">角色名：</div>
				<div class="rightline_3">
					<input type="text" id="roleName" name="roleName" value="${roleName}" />
				</div>
				<%-- <div class="leftline_3">城市：</div>
				<div class="rightline_3">
					<select name="cityId" id="cityId" >
						<option value="">-----请选择-----</option>
					  <c:forEach items="${cityList}" var="city">
						<option value="${city.id}" <c:if test="${city.id eq cityId}">selected="selected"</c:if>>${city.name}</option>
					  </c:forEach>
					</select>
				</div> --%>
				<div class="rightline_3">
			    	<button id="roleSearch" class="qury_an"><img src="${ctx}/static/hawtai_pc/imgs/anniu_2.png">&nbsp;查询</button>
					<a href="${ctx}/role/add?fid=${fid}&cid=${cid}">
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
					<td>角色名</td>
					<td>备注</td>
					<td>创建日期</td>
					<td>操作</td>
				</tr>
				<c:forEach items="${roleList}" var="role" varStatus="status">
					<tr <c:if test="${status.count%2==0}">class="count_tr2"</c:if> <c:if test="${status.count%2!=0}">class="count_tr"</c:if>>
						<td>
						<a class="a_zelor newadd" href="#" onclick="viewrole('${role.id}')">${role.name}</a>
						</td>
<%-- 						<td>${role.name}</td> --%>
						<td>${role.remark}</td>
						<td><fmt:formatDate value="${role.createDateTime}" pattern="yyyy年MM月dd日" /></td>
						<td>
							<a class="a_zelor" href="${ctx}/role/update/${role.id}?fid=${fid}&cid=${cid}">修改</a>
							<a class="a_zelor" href="${ctx}/role/assignUserInfo/${role.id}?fid=${fid}&cid=${cid}">分配用户</a>
							<a class="a_zelor" href="${ctx}/role/assignMenu/${role.id}?fid=${fid}&cid=${cid}">授权</a>
							<a class="a_zelor" id="deleteroleByIda" onclick="deleterole('${role.id}')" href="#"><span>删除</span></a>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<tags:pagination page="${pageData}" paginationSize="10"/>
		<div class="zhezhao"></div>
		<script type="text/javascript">
		$(function(){
			$("#roleSearch").click(function(){
				//${ctx}/role/list/?fid=${fid}&cid=${cid}
				$('#role').submit();
			});
		})
		function deleterole(uid){
			if(confirm("确定删除该角色吗，此操作会删除跟此角色所有关联的用户和菜单？")){
				var url =  "${ctx}/role/delete/" + uid + "?fid=${fid}&cid=${cid}";
				window.location.href = url;
			}
		}
		</script>
	</div>
</body>
</html>
