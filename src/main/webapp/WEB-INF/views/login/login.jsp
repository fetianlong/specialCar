<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ page import="org.apache.shiro.authc.ExcessiveAttemptsException"%>
<%@ page import="org.apache.shiro.authc.IncorrectCredentialsException"%>
<%@ include file="/WEB-INF/common/taglibs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>华泰专车后台登录</title>
    <link href="${ctx}/static/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="${ctx}/static/hawtai_pc/css/logon.css" rel="stylesheet">
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>
	<div class="Headers">
		<div class="topbs">
			<div class="container">
				<div class="row">
					<div class="col-md-6 piclogo">
						<img src="${ctx}/static/hawtai_pc/imgs/logo.png" class="img-responsive sign_left">
					</div>
					<div class="col-md-6 textin">欢迎登录！</div>
				</div>
			</div>
		</div>
		<div class="inCarousel">
			<div class="container">
				<div class="row">
					<div class="col-md-6 left_in">
						<img src="${ctx}/static/hawtai_pc/imgs/left_pic.png" class="img-responsive sign_left">
					</div>
					<div class="col-md-4 col-sm-offset-1 logonbox">
						<div class="logontops">
							<img src="${ctx}/static/hawtai_pc/imgs/deng.png">
						</div>
						<div class="LoginBlock">
							<form id="login" action="${ctx}/login" method="post" autocomplete="off">
								<div class="form-group">
									<div class="input-group">
										<div class="input-group-addon">
											<img src="${ctx}/static/hawtai_pc/imgs/users.png">
										</div>
										<input type="text" class="form-control form-height" id="username" name="username" value="${username}" placeholder="用户名">
									</div>
								</div>
								<div class="form-group">
									<div class="input-group">
										<div class="input-group-addon">
											<img src="${ctx}/static/hawtai_pc/imgs/word.png">
										</div>
										<input type="password" class="form-control form-height" name="password" value="${password}" placeholder="密码" id="password"/>
									</div>
								</div>
								<%
								String error = (String) request.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
								if(error != null){
								%>
					               	<div class="form-group">
										<p class="help-block">
											<i class="fa fa-times-circle"></i><span>手机号或密码输入不正确，请重新输入</span>
										</p>
									</div>
								<%
								}
								%>
								
								<div class="checkbox">
									<label><input type="checkbox" id="rememberMe" name="rememberMe"/> 记住密码
									</label><a href="#" class="pull-right ForgetPW">忘记密码？</a>
								</div>
								<button type="submit" class="btn btn-block SubmitButton">登录</button>
							</form>
						</div>
					</div>
				</div>
			</div>

		</div>
	</div>
	<div class="fows"></div>
</body>
</html>
