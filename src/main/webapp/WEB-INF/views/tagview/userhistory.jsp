<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="its" uri="/WEB-INF/tlds/remark.tld" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>编辑历史</title>
<%@include file="/WEB-INF/views/include/top.jsp"%>
   <link rel="stylesheet" type="text/css" href="/static/lib/layerui/2.2.5/css/layui.css">
</head>
<body>
	<nav class="breadcrumb">
	<i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span>
	标签词汇列表<span class="c-gray en">&gt;</span>  <a
		class="btn btn-success radius r"
		style="line-height:1.6em;margin-top:3px"
		href="javascript:location.replace(location.href);" title="刷新"><i
		class="Hui-iconfont">&#xe68f;</i></a></nav>
		<div class="pd-20 col-xs-10 col-md-offset-1">
 </div>
		 
	<div class="col-xs-10 col-md-offset-1">
	<div style="margin-bottom: 10px">
		<a class="btn btn-primary radius" href="/tag/editTag/${topic_id}" ><i class="Hui-iconfont"></i> 可视化操作</a>  
	 </div>
		<table class="table table-border table-bordered table-hover table-bg table-sort" style="width: 100%">
 		<tr>
 			<th>
 				序号
 			</th>
 			<th>
 				词汇
 			</th>
 			<th>
 				位置
 			</th>
 			<th>
 				颜色
 			</th>
 			<th>
 				大小
 			</th>
 			<th>
 				修改时间
 			</th>
 			<th>
 				修改内容
 			</th>
 			<c:forEach items="${dataList }" var="editHistory" varStatus="status">
 				<tr>
 					<td>${(pageIndex-1)*pageSize + status.index+1}</td>
 					<td>${editHistory.word.word}</td>
 					<td>(${editHistory.positionX},${editHistory.positionY})</td>
 					<td>${editHistory.color}</td>
 					<td>${editHistory.size}</td>
 					<td><fmt:formatDate value="${editHistory.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
 					<td>${editHistory.operateInfo}</td>
 				</tr>
 			
 			</c:forEach>
 		</tr>
		</table>
		<div style="float: right;margin-right: 50px;" id="page" class="page"></div>
 	</div>
 	  
 	<script type="text/javascript" src="${ctx }/static/lib/layerui/2.2.5/layui.js"></script>
<script type="text/javascript" src="${ctx }/static/lib/laypage/1.2/laypage.js"></script>
<script type="text/javascript">
layui.use(['laypage', 'layer'], function(){
  var laypage = layui.laypage
  ,layer = layui.layer;
 	laypage.render({
    elem: 'page'
    ,count: ${total}
    ,curr :${pageIndex}
    ,layout: ['count', 'prev', 'page', 'next',  'refresh', 'skip']
    , jump:function(obj,first){
                    if(!first) {
    　　　　　　　　　　　　//***第一次不执行,一定要记住,这个必须有,要不然就是死循环,哈哈
                        var pageIndex = obj.curr;
                      self.location='/tag/userhistory/${topic.id}/${user.id}?pageIndex='+pageIndex; 
                    }
                }
  });
  });
</script>
 	
	
</body>
</html>
