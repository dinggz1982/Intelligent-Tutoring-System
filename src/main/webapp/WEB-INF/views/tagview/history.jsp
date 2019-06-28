<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="its" uri="/WEB-INF/tlds/remark.tld" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>编辑历史</title>
<%@include file="/WEB-INF/views/include/top.jsp"%>
   <link rel="stylesheet" type="text/css" href="/static/lib/layerui/2.2.5/css/layui.css">
<style type="text/css">
.wrapper{ padding:20px;}
.flow-steps{ position:relative; height:30px; list-style:none; font-size:14px; overflow:hidden;}
.flow-steps li{ float:left; height:30px; margin-right:-32px; background:#d7d7d7; line-height:30px; overflow:hidden;}
.flow-steps a{ display:block; float:left; width:80px; padding: 0 18px 0 0; text-align:center; color:#333; text-decoration:none;}
.flow-steps b{ float:left; width:0px; height:0px; margin-top:-6px; border:21px solid #d7d7d7; border-left-color:#fff; font-size:0; line-height:0; z-index:9;}
.flow-steps s{ position:relative; float:left; width:0px; height:0px; margin-top:-2px; border:17px solid transparent; /*For IE6*/ _border-color:snow; _filter:chroma(color=snow);/*For IE6*/ border-left-color:#d7d7d7; font-size:0; line-height:0; z-index:99;}
.flow-steps .on{ background:#ff6600;}
.flow-steps .on a{ color:#fff;}
.flow-steps .on b{ border-color:#ff6600; border-left-color:#fff; }
.flow-steps .on s{ border-left-color:#ff6600;}
.flow-steps .f{ border-color:#d7d7d7!important;}
</style>
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
 				操作类型
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
		<div class="wrapper">
    <ul class="flow-steps">
        <li><b class="f"></b><a href="#">步骤一</a><s></s></li>
        <li class="on"><b></b><a href="#">步骤二</a><s></s></li>
        <li><b></b><a href="#">步骤三</a><s></s></li>
        <li><b></b><a href="#">iinterest.net</a><s></s></li>
    </ul>
</div>
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
