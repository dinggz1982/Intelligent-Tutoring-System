<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>在线用户</title>
   <%@include file="/WEB-INF/views/include/top.jsp" %>
  </head>
  <body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> <a href="/admin" target="_blank">首页</a> <span class="c-gray en">&gt;</span> 系统管理 <span class="c-gray en">&gt;</span> 在线用户</nav>
<div class="pd-20">

<h1>
<i class="Hui-iconfont">&#xe60d;</i>在线用户（共${userSize }人）</h1>
  <c:forEach items="${users }" var="user">
  	<span class="label label-success radius">
  	${user.username } &nbsp;&nbsp;
  	</span>
  </c:forEach>
</div>
<div class="container">
<a id="tongji_view" href="/corpus/myJob"  class="btn btn-success radius size-XL mt-10">我的标注任务</a>
<a id="tongji_view" href="/corpusprogress"  class="btn btn-success radius size-XL mt-10">标注进展</a>
<a id="tongji_view" href="/corpus/apply"  class="btn btn-success radius size-XL mt-10">申请标注</a>

</div>
  </body>
</html>
