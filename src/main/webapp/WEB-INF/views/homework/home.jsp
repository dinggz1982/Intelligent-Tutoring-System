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
<link rel="stylesheet" href="/static/bootstrap-4.3.1/css/bootstrap.css"
	media="all">
<style type="text/css">
.mainbody {
	margin: 0 auto;
	width: 95%
}
</style>
</head>
<body>
	<div class="layui-elem-quote">
		欢迎您！${currentUser.username}!您的身份有:
		<c:forEach items="${currentUser.roles }" var="role">
             ${role.name } &nbsp;
             </c:forEach>
	</div>
	<div class="mainbody">

		<!-- 相关操作 -->
		<c:forEach items="${currentUser.roles }" var="role">
			<c:if test="${role.name eq '管理员' }">
				<h2>系统管理</h2>
				<div class="row">
					<div class="col" style="margin-bottom: 10px;">
						<div class="card" style="width: 200px;height: 180px;">
							<div class="card-body">
								<a href="#" class="card-link">
									<h4 class="card-title">用户管理</h4>
									<p class="card-text">共有${userCount }个用户.</p>
								</a>
							</div>
						</div>
					</div>
					<!-- 角色信息 -->
					<div class="col" style="margin-bottom: 10px;">
						<div class="card" style="width: 200px;height: 180px;">
							<div class="card-body">
								<a href="#" class="card-link">
									<h4 class="card-title">角色管理</h4>
									<p class="card-text">查看系统角色信息.</p>
								</a>
							</div>
						</div>
					</div>
					<!-- 菜单信息 -->
					<div class="col" style="margin-bottom: 10px;">
						<div class="card" style="width: 200px;height: 180px;">
							<div class="card-body">
								<a href="#" class="card-link">
									<h4 class="card-title">菜单管理</h4>
									<p class="card-text">查看系统菜单信息.</p>
								</a>
							</div>
						</div>
					</div>
					<!-- 学校信息 -->
					<div class="col" style="margin-bottom: 10px;">
						<div class="card" style="width: 200px;height: 180px;">
							<div class="card-body">
									<h4 class="card-title"><a href="/school/list" class="card-link">学校管理</a></h4>
							</div>
						</div>
					</div>

					<!-- 学院信息 -->
					<div class="col" style="margin-bottom: 10px;">
						<div class="card" style="width: 200px;height: 180px;">
							<div class="card-body">
								<h4 class="card-title"><a href="/institute/list" class="card-link">学院管理</a></h4>
							</div>
						</div>
					</div>

					<!-- 专业信息 -->
					<div class="col" style="margin-bottom: 10px;">
						<div class="card" style="width: 200px;height: 180px;">
							<div class="card-body">
								<a href="#" class="card-link">
									<h4 class="card-title">专业管理</h4>
									<p class="card-text">查看专业列表.</p>
								</a>
							</div>
						</div>
					</div>

					<!-- 课程信息 -->
					<div class="col" style="margin-bottom: 10px;">
						<div class="card" style="width: 200px;height: 180px;">
							<div class="card-body">
								<a href="#" class="card-link">
									<h4 class="card-title">课程管理</h4>
									<p class="card-text">查看课程列表.</p>
								</a>
							</div>
						</div>
					</div>
				</div>
			</c:if>

			<c:if test="${role.name eq '教师' }">
				<h2 style="clear:both;margin-top: 20px;">教师功能</h2>
				<!-- 我的课程 -->
				<div class="row">
					<div class="col" style="margin-bottom: 10px;">
						<div class="card" style="width: 200px;height: 180px;">
							<img class="card-img-top"
								style="width: 100px;height: 100px;margin: 10 auto;"
								src="/static/images/contacts.png" alt="Card image">
							<div class="card-body">
								<h4 class="card-title">
									<a href="/course/list" class="card-link">我的课程</a>
								</h4>
							</div>
						</div>
					</div>
					
					<!-- 我的学生 -->
					<div class="col" style="margin-bottom: 10px;">
						<div class="card" style="width: 200px;height: 180px;">
							<img class="card-img-top"
								style="width: 100px;height: 100px;margin: 10 auto;"
								src="/static/images/attach.png" alt="Card image">
							<div class="card-body">
								<h4 class="card-title">
									<a href="#" class="card-link">我的学生</a>
								</h4>
							</div>
						</div>
					</div>
					
					<!-- 我的作业 -->
					<div class="col" style="margin-bottom: 10px;">
						<div class="card" style="width: 200px;height: 180px;">
							<img class="card-img-top"
								style="width: 100px;height: 100px;margin: 10 auto;"
								src="/static/images/email-app-icon.png" alt="Card image">
							<div class="card-body">
								<h4 class="card-title">
									<a href="#" class="card-link">我的作业</a>
								</h4>
							</div>
						</div>
					</div>
					
					<!-- 标签词汇 -->
					<div class="col" style="margin-bottom: 10px;">
						<div class="card" style="width: 200px;height: 180px;">
							<img class="card-img-top"
								style="width: 100px;height: 100px;margin: 10 auto;"
								src="/static/images/email-app-icon.png" alt="Card image">
							<div class="card-body">
								<h4 class="card-title">
									<a href="/tag-setting" class="card-link">标签词汇设定</a>
								</h4>
							</div>
						</div>
					</div>
					<!-- 标签词汇end -->
				</div>
			</c:if>

	<!-- 学生功能 -->
			<c:if test="${role.name eq '学生' }">
				<h2 style="clear:both;margin-top: 20px; ">学生功能</h2>
				<div class="row">
					<div class="col" style="margin-bottom: 10px;">
						<div class="card" style="width: 200px;height: 180px;">
							<div class="card-body">
								<a href="#" class="card-link">
									<h4 class="card-title">用户管理</h4>
									<p class="card-text">查看所有用户信息.</p>
								</a>
							</div>
						</div>
					</div>
				</div>
			</c:if>
		</c:forEach>
		<h2 style="clear:both;margin-top: 20px; ">实验</h2>
				<div class="row">
					<div class="col" style="margin-bottom: 10px;">
						<div class="card" style="width: 200px;height: 180px;">
							<div class="card-body">
								<a href="/tagView" class="card-link">
									<h4 class="card-title">标签实验</h4>
									<p class="card-text">标签可视化实验.</p>
								</a>
							</div>
						</div>
					</div>
					<div class="col" style="margin-bottom: 10px;">
						<div class="card" style="width: 200px;height: 180px;">
							<div class="card-body">
								<a href="/corpus/list" class="card-link">
									<h4 class="card-title">语料库实验</h4>
									<p class="card-text">学习资源用户评论语料库.</p>
								</a>
							</div>
						</div>
					</div>
				</div>
				
	</div>
	<div style="margin-bottom: 30px;"></div>
	<!-- 相关操作end -->
	<script src="/static/res/home/lib/layui/layui.js" charset="utf-8"></script>

</body>
</html>