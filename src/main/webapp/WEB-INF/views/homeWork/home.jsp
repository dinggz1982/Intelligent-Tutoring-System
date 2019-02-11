<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="utf-8">
<title>作业管理系统</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no">
<link rel="stylesheet" href="/static/res/home/css/x-admin.css"
	media="all">
<link rel="stylesheet"
	href="/static/res/home/lib/bootstrap/css/bootstrap.css" media="all">
</head>
<body>
	<style type="text/css">
legend {
	display: block;
	width: 100px;
	border-bottom: 0px;
	font-family: "Microsoft YaHei", "Helvetica Neue";
}

legend a {
	color: #666;
}

legend a:hover {
	text-decoration: none;
}

.layui-table {
	font-family: "Microsoft YaHei", "Helvetica Neue";
}
</style>
	<div class="x-body">
		<blockquote class="layui-elem-quote">
			欢迎您！${currentUser.username}!您的身份有:
			<c:forEach items="${currentUser.roles }" var="role">
             ${role.name } &nbsp;
             </c:forEach>
			<p>
		</blockquote>
		<!-- 相关操作 -->
		<c:forEach items="${currentUser.roles }" var="role">
			<c:if test="${role.name eq '管理员' }">
				<fieldset class="layui-elem-field layui-field-title site-title">
					<legend>
						<a name="default">系统管理</a>
					</legend>

					<div class="row">
						<div class="col-xs-6 col-sm-4 col-md-3">
							<section class="panel">
							<div class="symbol bgcolor-blue">
								<i class="fa fa-address-card"></i>
							</div>
							<div class="value tab-menu" bind="1">
								<a href="javascript:;" data-url="user-info.html"
									data-parent="true" data-title="用户管理"><i class="iconfont "
									data-icon=""></i>
									<h1>${userCount }</h1> </a> <a href="/user/list"
									data-parent="true" data-title="用户管理"> <i class="iconfont "
									data-icon=""></i><span>用户总数</span></a>
							</div>
							</section>
						</div>
						<div class="col-xs-6 col-sm-4 col-md-3">
							<section class="panel">
							<div class="symbol bgcolor-commred">
								<i class="fa fa-handshake-o"></i>
							</div>
							<div class="value tab-menu" bind="1">
								<a href="/class/list" 
									data-parent="true" data-title="班级管理"> <i class="iconfont "
									data-icon=""></i>
									<h1>10</h1>
								</a> <a href="javascript:;" data-url="user-info.html"
									data-parent="true" data-title="班级管理"> <i class="iconfont "
									></i><span>班级总数</span></a>

							</div>
							</section>
						</div>

						<div class="col-xs-6 col-sm-4 col-md-3">
							<section class="panel">
							<div class="symbol bgcolor-dark-green">
								<i class="fa fa-bar-chart"></i>
							</div>
							<div class="value tab-menu" bind="1">
								<a href="javascript:;" data-url="user-info.html"
									data-parent="true" data-title="专业管理"> <i class="iconfont "
									data-icon=""></i>
									<h1>10</h1>
								</a> <a href="javascript:;" data-url="user-info.html"
									data-parent="true" data-title="专业总数"> <i class="iconfont "
									data-icon=""></i><span>专业总数</span></a>
							</div>
							</section>
						</div>

						<div class="col-xs-6 col-sm-4 col-md-3">
							<section class="panel">
							<div class="symbol bgcolor-yellow-green">
								<i class="fa fa-cubes"></i>
							</div>
							<div class="value tab-menu" bind="1">
								<a href="javascript:;" data-url="user-info.html"
									data-parent="true" data-title="今日新增"> <i class="iconfont "
									data-icon=""></i>
									<h1>10</h1>
								</a> <a href="javascript:;" data-url="user-info.html"
									data-parent="true" data-title="今日新增"> <i class="iconfont "
									data-icon=""></i><span>今日新增</span></a>
							</div>
							</section>
						</div>

					</div>
				</fieldset>

			</c:if>

			<c:if test="${role.name eq '教师' }">
				<fieldset class="layui-elem-field layui-field-title site-title">
					<legend>
						<a name="default">教师功能</a>
					</legend>
				</fieldset>
				<div class="row">
					<div class="col-xs-6 col-sm-4 col-md-3">
						<section class="panel">
						<div class="symbol bgcolor-blue">
							<i class="fa fa-address-card"></i>
						</div>
						<div class="value tab-menu" bind="1">
							<a href="javascript:;" data-url="user-info.html"
								data-parent="true" data-title="机构总数"><i class="iconfont "
								data-icon=""></i>
								<h1>10</h1> </a> <a href="javascript:;" data-url="user-info.html"
								data-parent="true" data-title="机构总数"> <i class="iconfont "
								data-icon=""></i><span>机构总数</span></a>

						</div>
						</section>
					</div>
				</div>
			</c:if>
			
			
<c:if test="${role.name eq '学生' }">
				<fieldset class="layui-elem-field layui-field-title site-title">
					<legend>
						<a name="default">学生功能</a>
					</legend>
				</fieldset>
				<div class="row">
					<div class="col-xs-6 col-sm-4 col-md-3">
						<section class="panel">
						<div class="symbol bgcolor-blue">
							<i class="fa fa-address-card"></i>
						</div>
						<div class="value tab-menu" bind="1">
							<a href="javascript:;" data-url="user-info.html"
								data-parent="true" data-title="机构总数"><i class="iconfont "
								data-icon=""></i>
								<h1>10</h1> </a> <a href="javascript:;" data-url="user-info.html"
								data-parent="true" data-title="机构总数"> <i class="iconfont "
								data-icon=""></i><span>机构总数</span></a>

						</div>
						</section>
					</div>
				</div>
			</c:if>
		</c:forEach>



		<!-- 相关操作end -->
		<script src="/static/res/home/lib/layui/layui.js" charset="utf-8"></script>

	</div>

</body>
</html>