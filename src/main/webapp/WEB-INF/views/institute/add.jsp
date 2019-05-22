<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<style type="text/css">
body {
	margin: 10 auto;
	width: 80%;
}
</style>
<title>新增学院</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css"
	href="/static/res/home/lib/bootstrap/css/bootstrap.css">
</head>

<body>
	<div class="container">
		<div class="row" style="margin-left: 30px;">
			<h1>新增学院</h1>
			<form action="/school/save" method="post">
				<div class="form-group">
					<label for="name">学校名称</label> <input type="text"
						class="form-control" id="name" name="name" placeholder="name">
				</div>
				<div class="form-group">
					<label for="address">学校地址</label> <input type="text"
						class="form-control" id="address" name="address" placeholder="address">
				</div>
				<input type="submit" class="btn btn-default" value="提交">
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
			</form>
		</div>
	</div>
</body>
</html>
