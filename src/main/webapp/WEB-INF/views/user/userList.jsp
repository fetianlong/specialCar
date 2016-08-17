<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/common/taglibs.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
</head>
<body>
	<p>当前位置：用户管理 > 用户列表</p>
	<div class="newuesrbox">
		<form id="userInfo" action="${ctx}/userInfo/list" method="get">
			<input type="hidden" name="fid" value="${fid}">
			<input type="hidden" name="cid" value="${cid}">
			<div class="linebox">
				<div class="leftline_3">用户名：</div>
				<div class="rightline_3">
					<input type="text" id="userName" name="userName" value="${userName}" />
				</div>
				<div class="leftline_3">城市：</div>
				<div class="rightline_3">
					<select name="cityId" id="cityId" >
						<option value="">-----请选择-----</option>
					  <c:forEach items="${cityList}" var="city">
						<option value="${city.id}" <c:if test="${city.id eq cityId}">selected="selected"</c:if>>${city.name}</option>
					  </c:forEach>
					</select>
				</div>
				<div class="rightline_3">
			    	<button id="userInfoSearch" class="qury_an"><img src="${ctx}/static/hawtai_pc/imgs/anniu_2.png">&nbsp;查询</button>
					<a href="${ctx}/userInfo/add?fid=${fid}&cid=${cid}">
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
					<td>用户名</td>
					<td>姓名</td>
					<td>城市</td>
					<td>联系电话</td>
					<td>手机</td>
					<td>Email</td>
					<td>创建日期</td>
					<td>操作</td>
				</tr>
				<c:forEach items="${userList}" var="user" varStatus="status">
					<tr <c:if test="${status.count%2==0}">class="count_tr2"</c:if> <c:if test="${status.count%2!=0}">class="count_tr"</c:if>>
						<td>
						<a class="a_zelor newadd" href="#" onclick="viewUserInfo('${user.id}')">${user.userName}</a>
						</td>
						<td>${user.name}</td>
						<td>${user.city}</td>
						<td>${user.tel}</td>
						<td>${user.phone}</td>
						<td>${user.email}</td>
						<td><fmt:formatDate value="${user.createTime}" pattern="yyyy年MM月dd日  HH时mm分ss秒" /></td>
						<td>
							<a class="a_zelor" href="${ctx}/userInfo/update/${user.id}?fid=${fid}&cid=${cid}">修改</a>
							<a id="deleteUserByIda" onclick="deleteUser('${user.id}')" href="#"><span>删除</span></a>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<tags:pagination page="${pageData}" paginationSize="10"/>
		<div class="zhezhao"></div>
		<div id="divSCA_sm">
			<div class="header_line">
			用戶信息详情
			<div class="close_box">
			  <div class="closes close"><img src="${ctx}/static/hawtai_pc/imgs/closs.png"></div></div>
			</div>
			<div class="count_into">
				<iframe id="viewUserInfoIframe" src="" width="100%" height="200" frameborder="0"></iframe>
			</div>
			<div class="fower_into close">关闭</div>
		</div>
		<script type="text/javascript">
		$(function(){
			$("#userInfoSearch").click(function(){
				//${ctx}/userInfo/list/?fid=${fid}&cid=${cid}
				$('#userInfo').submit();
			});
		})
		function deleteUser(uid){
			if(confirm("确定删除用户吗？")){
				var url =  "${ctx}/userInfo/deleteUser/" + uid + "?fid=${fid}&cid=${cid}";
				window.location.href = url;
			}
		}
		function viewUserInfo(id){
			var url = "${ctx}/userInfo/view/" + id;
			$("#viewUserInfoIframe").attr("src", url);
		}
		</script>
	</div>
</body>
</html>
