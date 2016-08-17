<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/common/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<body>
	<p>当前位置：角色管理 > 新增角色</p>
	<div class="newuesrbox">
		<form id="myform" action="${ctx}/role/${action}" method="post">
			<input type="hidden" name="id" value="${role.id}"/>
			<input type="hidden" name="fid" value="${fid}"/>
			<input type="hidden" name="cid" value="${cid}"/>
			<div class="linebox">
				<div class="leftline">角色名：</div>
				<div class="rightline">
					<input type="text" id="name" name="name" value="${role.name}"/>
				</div>
			</div>
			<div class="linebox">
				<div class="leftline">备注：</div>
				<div class="rightline">
					<textarea cols="60%" rows="7" name="remark">${role.remark}</textarea>
				</div>
			</div>
			
		<div style="clear: both;"></div>
		<div class="anniuok">
			<a href="#" id="roleSave"><div class="okbutton">确定</div></a> 
			<a href="javascript:history.go(-1)"><div class="okout">取消</div></a>
		</div>
		</form>
	</div>
	<script type="text/javascript">
	$(function(){
		$("#roleSave").click(function(){
			$("#myform").submit();
		});
	}); 
	</script>
</body>
</html>
