<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>导入实验用户</title>
<%@include file="/WEB-INF/views/include/top.jsp"%>
</head>
<body>
	<nav class="breadcrumb"> <i class="Hui-iconfont">&#xe67f;</i> 首页
	<span class="c-gray en">&gt;</span> 导入实验用户<span class="c-gray en">&gt;</span>
	<a class="btn btn-success radius r"
		style="line-height: 1.6em; margin-top: 3px"
		href="javascript:location.replace(location.href);" title="刷新"><i
		class="Hui-iconfont">&#xe68f;</i></a></nav>
	<div class="pd-20 col-xs-10 col-md-offset-1"></div>

	<div class="container ui-sortable">
		<h1>导入实验用户</h1>
		<div class="panel panel-default">
			<div class="panel-header"></div>
			<div class="panel-body">
				<form action="/user/upload" method="post" id="userForm" enctype="multipart/form-data"
					class="form form-horizontal responsive">
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />
					 <div class="row cl">
						<label class="form-label col-xs-3">导入用户：</label>
						<div class="formControls col-xs-8">
							<p><a href="/files/userFile.xls">文件模板下载</a></p>
							<input type="file" required id="file" name="file" accept=".xls,.xlsx" aria-required="true">
						</div>
					</div>
					<div class="row cl">
						<div class="col-xs-8 col-xs-offset-3">
							<input class="btn btn-primary" type="submit"
								value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript" src="/validation/jquery.validate.min.js"></script>
<script type="text/javascript" src="/validation/validate-methods.js"></script>
<script type="text/javascript" src="/validation/messages_zh.min.js"></script>
</html>
