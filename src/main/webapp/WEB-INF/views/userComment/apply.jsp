<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>标注任务申请</title>
<%@include file="/WEB-INF/views/include/top.jsp"%>
<style type="text/css">
.page_title {
    border-bottom: solid 1px #ddd;
    padding-bottom: 10px;
    margin-bottom: 15px;
}
</style>
</head>
<body>
	<nav class="breadcrumb"> <i class="Hui-iconfont">&#xe67f;</i> 首页
	<span class="c-gray en">&gt;</span> 用户评论语料库<span class="c-gray en">&gt;</span>
	标注任务申请<a class="btn btn-success radius r"
		style="line-height:1.6em;margin-top:3px"
		href="javascript:location.replace(location.href);" title="刷新"><i
		class="Hui-iconfont">&#xe68f;</i></a></nav>
	<div class="pd-20 col-xs-10 col-md-offset-1">
		<div class="col-xs-10 col-md-offset-1">
			欢迎您：${currentUser.username}
			<h1 class="page_title">选择要标注的数量</h1>
			<form action="/corpus/saveApply">
				<div class="mt-20 skin-minimal">
				<div class="row cl">
					<div class="radio-box">
						<input type="radio" id="radio-1" name="number"> <label
							for="radio-1">5000</label>
					</div>
					<div class="radio-box">
						<input type="radio" id="radio-2" name="number">
						<label for="radio-2">10000</label>
					</div>
					<div class="radio-box">
						<input type="radio" id="radio-3" name="number">
						<label for="radio-3">15000</label>
					</div>
					<div class="radio-box">
						<input type="radio" id="radio-4" name="number">
						<label for="radio-4">20000</label>
					</div>
					</div>
					<div class="row cl">
						<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
							<input class="btn btn-primary radius" type="submit" value="提交">
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>
