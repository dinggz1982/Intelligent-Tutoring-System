<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="its" uri="/WEB-INF/tlds/remark.tld" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>我的标注</title>
<%@include file="/WEB-INF/views/include/top.jsp"%>
   <link rel="stylesheet" type="text/css" href="/static/lib/layerui/2.2.5/css/layui.css">

</head>
<body>
	<nav class="breadcrumb">
	<i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span>
	用户评论语料库<span class="c-gray en">&gt;</span> 标注任务总体进展 <a
		class="btn btn-success radius r"
		style="line-height:1.6em;margin-top:3px"
		href="javascript:location.replace(location.href);" title="刷新"><i
		class="Hui-iconfont">&#xe68f;</i></a></nav>
		<div class="pd-20 col-xs-10 col-md-offset-1">
 </div>
			 
	<div class="col-xs-10 col-md-offset-1">
		<table class="table table-border table-bordered table-hover table-bg table-sort">
 		<tr>
 			<th>
 				姓名
 			</th>
 			<th>
 				评论内容
 			</th>
 			<th>
 				内容相关
 			</th>
 			<th>
 				情感相关
 			</th>
 			<th>
 				其他类
 			</th>
 			<th>
 				标注时间
 			</th>
 			<c:forEach items="${remarks }" var="remark" varStatus="status">
 				<tr>
 					<td>${remark.user.username}</td>
 					<td>${remark.userComment.content}</td>
 					<td>${its:content(remark.contentRelated)}</td>
 					<td>${its:emotion(remark.emotionRelated)}</td>
 					<td>${its:other(remark.otherRelated)}</td>
 					<td><fmt:formatDate value="${remark.createTime}" pattern="yyyy-MM-dd HH:mm" /></td>
 				</tr>
 			</c:forEach>
 		</tr>
		</table>
 	</div>
 	  
 	<script type="text/javascript" src="${ctx }/static/lib/layerui/2.2.5/layui.js"></script>
	
</body>
</html>
