<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="its" uri="/WEB-INF/tlds/remark.tld" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>词汇列表</title>
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
		<a class="btn btn-primary radius" href="/tag/editMyTag/${topic_id}" ><i class="Hui-iconfont"></i> 设定我的标签云</a>  
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
 				频率
 			</th>
 			<th>
 				初始权重
 			</th>
 			<th>
 				tfidf值
 			</th>
 			<th>
 				用户标注
 			</th>
 			<c:forEach items="${dataList }" var="word" varStatus="status">
 				<tr>
 					<td>${(pageIndex-1)*pageSize + status.index+1}</td>
 					<td>${word.word}</td>
 					<td>${word.frequency}</td>
 					<td>${word.weight}</td>
 					<td>${word.tfidf}</td>
 					<td><a href="#">用户标注</a></td>
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
                      self.location='/tag-setting?pageIndex='+pageIndex; 
                    }
                }
  });
  });
</script>
 	
	
</body>
</html>
