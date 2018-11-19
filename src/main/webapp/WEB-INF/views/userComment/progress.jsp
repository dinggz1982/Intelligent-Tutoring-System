<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>标注任务总体进展</title>
<%@include file="/WEB-INF/views/include/top.jsp"%>
</head>
<body>
	<nav class="breadcrumb">
	<i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span>
	用户评论语料库<span class="c-gray en">&gt;</span> 标注任务总体进展 <a
		class="btn btn-success radius r"
		style="line-height:1.6em;margin-top:3px"
		href="javascript:location.replace(location.href);" title="刷新"><i
		class="Hui-iconfont">&#xe68f;</i></a></nav>

	<div class="col-xs-10 col-md-offset-1">
		<table class="table table-border table-bg">
 		<tr>
 			<th>
 				姓名
 			</th>
 			<th>
 				标注总数
 			</th>
 			<th>
 				已标注
 			</th>
 			<th>
 				未标注
 			</th>
 			<c:forEach items="${remarks}" var="userRemark">
 				<tr>
 					<td>${userRemark.user.username}</td>
 					<td>${userRemark.hasRemarkCount+userRemark.notRemarkCount}</td>
 					<td>${userRemark.hasRemarkCount}</td>
 					<td>${userRemark.notRemarkCount}</td>
 				</tr>
 			
 			</c:forEach>
 		</tr>
		</table>
 	</div>
	
</body>
</html>
