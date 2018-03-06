<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>角色列表</title>
   <%@include file="/WEB-INF/views/include/top.jsp" %>
   <link rel="stylesheet" type="text/css" href="${ctx}/static/lib/layerui/2.2.5/css/layui.css" />
  </head>
  <body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 系统管理 <span class="c-gray en">&gt;</span> 角色管理 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="pd-20">
  <div class="text-c"> 日期范围：
    <input type="text" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'datemax\')||\'%y-%M-%d\'}'})" id="datemin" class="input-text Wdate" style="width:120px;">
    -
    <input type="text" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'datemin\')}',maxDate:'%y-%M-%d'})" id="datemax" class="input-text Wdate" style="width:120px;">
    <input type="text" class="input-text" style="width:250px" placeholder="输入会员名称、电话、邮箱" id="" name=""><button type="submit" class="btn btn-success" id="" name=""><i class="icon-search"></i> 搜用户</button>

  </div>
  <div class="cl pd-5 bg-1 bk-gray mt-20">
    <span class="l"><a href="javascript:;" onclick="datadel()" class="btn btn-danger radius"><i class="icon-trash"></i> 批量删除</a>
    <a href="javascript:;" onclick="user_add('550','','添加用户','user-add.html')" class="btn btn-primary radius"><i class="icon-plus"></i> 添加用户</a></span>
    <span class="r">共有数据：<strong>88</strong> 条</span>
  </div>
  <table class="table table-border table-bordered table-hover table-bg table-sort">
    <thead>
      <tr class="text-c">
        <th width="25"><input type="checkbox" name="" value=""></th>
        <th width="80">序号</th>
        <th width="100">角色名</th>
        <th width="70">状态</th>
        <th width="100">操作</th>
      </tr>
    </thead>
    <tbody>
    	<c:forEach items="${dataList }" var="role" varStatus="status">
      <tr class="text-c">
        <td><input type="checkbox" value="1" name=""></td>
        <td>${status.index+1 }</td>
        <td><u style="cursor:pointer" class="text-primary">${role.name }</u></td>
        <td class="user-status"><span class="label label-success">已启用</span></td>
        <td class="f-14 user-manage"><a style="text-decoration:none" onClick="user_stop(this,'10001')" href="javascript:;" title="停用"><i class="icon-hand-down"></i></a> <a title="编辑" href="javascript:;" onclick="user_edit('4','550','','编辑','user-add.html')" class="ml-5" style="text-decoration:none"><i class="icon-edit"></i></a> <a style="text-decoration:none" class="ml-5" onClick="user_password_edit('10001','370','228','修改密码','user-password-edit.html')" href="javascript:;" title="修改密码"><i class="icon-key"></i></a> <a title="删除" href="javascript:;" onclick="user_del(this,'1')" class="ml-5" style="text-decoration:none"><i class="icon-trash"></i></a></td>
      </tr>
      </c:forEach>
    </tbody>
  </table>
  <div id="page" class="page"></div>
</div>

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="lib/My97DatePicker/4.8/WdatePicker.js"></script> 
<script type="text/javascript" src="lib/laypage/1.2/laypage.js"></script>
<script type="text/javascript">
layui.use(['laypage' ], function() {
		var laypage = layui.laypage;
			 laypage.render({
		    elem: 'page'
		    ,count: ${total}
		    ,curr :${pageIndex}
		    ,layout: ['count', 'prev', 'page', 'next']
		    ,jump: function(obj,first){
		    if (!first) {
		      self.location='/role/list?page='+obj.curr; 
		      }
		    }
  	});
  	});
</script>
  </body>
</html>
