<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>新增词汇</title>
<%@include file="/WEB-INF/views/include/top.jsp"%>
</head>
<body>
	<nav class="breadcrumb"> <i class="Hui-iconfont">&#xe67f;</i> 首页
	<span class="c-gray en">&gt;</span> 新增词汇<span class="c-gray en">&gt;</span>
	<a class="btn btn-success radius r"
		style="line-height:1.6em;margin-top:3px"
		href="javascript:location.replace(location.href);" title="刷新"><i
		class="Hui-iconfont">&#xe68f;</i></a></nav>
	<div class="pd-20 col-xs-10 col-md-offset-1"></div>

	<div class="container ui-sortable">
		<h1>新增词汇</h1>
		<div class="panel panel-default">
			<div class="panel-header"></div>
			<div class="panel-body">
				<form action="saveWord" method="post"
					class="form form-horizontal responsive">
								                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
					
					<div class="row cl">
						<label class="form-label col-xs-3">词汇主题：</label>
						<div class="formControls col-xs-8">
							<input type="text" class="input-text" placeholder="描述词汇的主题，如教育技术"
								name="topic" id="topic">
						</div>
					</div>
					<div class="row cl">
						<label class="form-label col-xs-3">主题描述信息：</label>
						<div class="formControls col-xs-8">
							<textarea cols="" rows="" class="textarea" name="description"
								id="description" placeholder="主题描述信息"></textarea>
						</div>
					</div>
					<div class="row cl">
						<label class="form-label col-xs-3">相关词汇：</label>
						<div class="formControls col-xs-8">
							<p>按照:词汇,权重,出现频率,TFIDF值  每行一个词汇</p>
							<textarea cols="" rows="" class="textarea" name="words"
								id="words" placeholder="按照:词汇,权重,出现频率,TFIDF值 "></textarea>
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
</html>
