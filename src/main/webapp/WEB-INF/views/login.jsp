<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>广州大学-教育技术-网络教育软件设计与开发</title>
   <%@include file="/WEB-INF/views/include/top.jsp" %>
</head>

<body>
	<div class="header">
			<h1 style="text-align: center;">广州大学-教育技术-网络教育软件设计与开发</h1>
	</div>
	<div class="loginWraper">
		<div id="loginform" class="loginBox">
			<form class="form form-horizontal"  action="/login" method="post">
			                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
				<div class="row cl">
					<label class="form-label col-xs-3"><i class="Hui-iconfont">&#xe60d;</i></label>
					<div class="formControls col-xs-8">
						<input id="username" name="username" type="text" placeholder="账户"
							class="input-text size-L">
					</div>
				</div>
				<div class="row cl">
					<label class="form-label col-xs-3"><i class="Hui-iconfont">&#xe60e;</i></label>
					<div class="formControls col-xs-8">
						<input id="password" name="password" type="password" placeholder="密码"
							class="input-text size-L">
					</div>
				</div>
				<div class="row cl">
					<div class="formControls col-xs-8 col-xs-offset-3">
						<label for="online"> <input type="checkbox" name="online"
							id="online" value=""> 使我保持登录状态
						</label>
					</div>
				</div>
				<div class="row cl">
					<div class="formControls col-xs-8 col-xs-offset-3" style="text-align: center;">
						<input name="" type="submit" class="btn btn-success radius size-L"
							value="&nbsp;登&nbsp;&nbsp;&nbsp;&nbsp;录&nbsp;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input
							name="" type="reset" class="btn btn-default radius size-L"
							value="&nbsp;取&nbsp;&nbsp;&nbsp;&nbsp;消&nbsp;">
					</div>
				</div>
					<div class="row cl">
					<div class="formControls col-xs-8 col-xs-offset-3">
					<span style="color: red">${message }</span>	
					</div>
				</div>
			</form>
		</div>
	</div>
	<div class="footer">Copyright @广东诚泰</div>
</body>
</html>
