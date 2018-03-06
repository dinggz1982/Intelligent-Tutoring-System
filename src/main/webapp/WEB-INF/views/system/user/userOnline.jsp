<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>在线用户</title>
   <%@include file="/WEB-INF/views/include/top.jsp" %>
  </head>
  <body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 系统管理 <span class="c-gray en">&gt;</span> 在线用户</nav>
<div class="pd-20">

<h1>
<i class="Hui-iconfont">&#xe60d;</i>在线用户（共${userSize }人）</h1>
  <c:forEach items="${users }" var="user">
  	<span class="label label-success radius">
  	${user.username } &nbsp;&nbsp;
  	</span>
  </c:forEach>
</div>

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="lib/My97DatePicker/4.8/WdatePicker.js"></script> 
<script type="text/javascript" src="lib/datatables/1.10.0/jquery.dataTables.min.js"></script> 
<script type="text/javascript" src="lib/laypage/1.2/laypage.js"></script>
  </body>
</html>
