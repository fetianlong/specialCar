<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<body style="background:#ffffff;">
	<div class="linebox_tan_sm">
		<div class="leftline_tan_1">用户名：</div>
		<div class="rightline_tan_1">
			${user.userName}
		</div>
	
		<div class="leftline_tan_1">姓名：</div>
		<div class="rightline_tan_1">
			${user.name}
		</div>
	</div>
	<div class="linebox_tan_sm">
		<div class="leftline_tan_1">所属城市：</div>
		<div class="rightline_tan_1">
			${user.city}
		</div>
	
		<div class="leftline_tan_1">联系电话：</div>
		<div class="rightline_tan_1">
			${user.tel}
		</div>
	</div>
	<div class="linebox_tan_sm">
		<div class="leftline_tan_1">手机号：</div>
		<div class="rightline_tan_1">
			${user.phone}
		</div>
	
		<div class="leftline_tan_1">Email：</div>
		<div class="rightline_tan_1">
			${user.email}
		</div>
	</div>
	<div style="clear: both"></div>
</body>
</html>
