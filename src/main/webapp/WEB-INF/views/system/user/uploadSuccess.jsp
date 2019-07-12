<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="its" uri="/WEB-INF/tlds/remark.tld"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>文件上传用户列表</title>
<%@include file="/WEB-INF/views/include/top.jsp"%>
   <link rel="stylesheet" type="text/css" href="/static/lib/layerui/2.2.5/css/layui.css">

</head>
<body>
	<nav class="breadcrumb">
	<i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span>
	文件上传用户列表<span class="c-gray en">&gt;</span>  <a
		class="btn btn-success radius r"
		style="line-height:1.6em;margin-top:3px"
		href="javascript:location.replace(location.href);" title="刷新"><i
		class="Hui-iconfont">&#xe68f;</i></a></nav>
		<div class="pd-20 col-xs-10 col-md-offset-1">
 </div>
		 
	<div class="col-xs-10 col-md-offset-1">
	<div style="margin-bottom: 10px">
	上传成功的用户：
	 </div>
		<table class="table table-border table-bordered table-hover table-bg table-sort" style="width: 100%">
 		<tr>
 			<th>
 				序号
 			</th>
 			<th>
 				用户名（学号）
 			</th>
 			<th>
 				真实姓名
 			</th>
 			<th>
 				性别
 			</th>
 			<th>
 				邮箱
 			</th>
 			<c:forEach items="${newUsers }" var="user" varStatus="status">
 				<tr>
 					<td>${status.index+1}</td>
 					<td>${user.username}</td>
 					<td>${user.realname}</td>
 					<td>${user.sex}</td>
 					<td>${user.email}</td>
 				</tr>
 			</c:forEach>
 		</tr>
		</table>
		
		<div style="margin-bottom: 10px">
	已存在的用户：
	 </div>
		<table class="table table-border table-bordered table-hover table-bg table-sort" style="width: 100%">
 		<tr>
 			<th>
 				序号
 			</th>
 			<th>
 				用户名（学号）
 			</th>
 			<th>
 				真实姓名
 			</th>
 			<th>
 				性别
 			</th>
 			<th>
 				邮箱
 			</th>
 			<c:forEach items="${exitsUser }" var="user" varStatus="status">
 				<tr>
 					<td>${status.index+1}</td>
 					<td>${user.username}</td>
 					<td>${user.realname}</td>
 					<td>${user.sex}</td>
 					<td>${user.email}</td>
 				</tr>
 			</c:forEach>
 		</tr>
		</table>
 	</div>
 
 	  
 	<script type="text/javascript" src="${ctx }/static/lib/layerui/2.2.5/layui.js"></script>
<script type="text/javascript" src="${ctx }/static/lib/laypage/1.2/laypage.js"></script>
<script type="text/javascript">
layui.use(['laypage', 'layer'], function(){
  var layer = layui.layer;
  });
</script>
</body>
</html>
