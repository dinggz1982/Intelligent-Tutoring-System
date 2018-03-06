<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>    
    <title>资源管理</title>
   <%@include file="/WEB-INF/views/include/top.jsp" %>
   <link rel="stylesheet" href="${ctx }/static/lib/zTree/v3/css/zTreeStyle/zTreeStyle.css" type="text/css">
   
  </head>
  
  <body>
    <nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 系统管理 <span class="c-gray en">&gt;</span> 资源管理 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<table class="table">
	<tr>
		<td width="200" class="va-t"><ul id="tree" class="ztree"></ul></td>
		<td class="va-t">
		
		</td>
	</tr>
</table>
<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="${ctx }/static/lib/zTree/v3/js/jquery.ztree.all-3.5.min.js"></script> 
<script type="text/javascript">
var setting = {
			data : {
				simpleData : {
					enable : true
				}
			},
			async : {
				enable : true,
				url : "/resource/tree",
				type : "get",
				dataFilter : filter
			}/* ,
			callback : {
				onClick : function(event, treeId, treeNode) {
					location.href = "${ctx}/system/module/index?id=" + treeNode.id;
				}
			} */
		}
		function filter(treeId, parentNode, childNodes) {
			if (!childNodes) return null;
			//console.log(childNodes);
			childNodes = eval( childNodes );
			for (var i = 0, l = childNodes.length; i < l; i++) {
				childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
			}
			return childNodes;
		}
	
		$(document).ready(function() {
			$.fn.zTree.init($("#tree"), setting);
		});
</script>
  </body>
</html>
