<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>学校列表</title>
   <%@include file="/WEB-INF/views/include/top.jsp" %>
   <link rel="stylesheet" type="text/css" href="/static/lib/layerui/2.2.5/css/layui.css">
  </head>
  <body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> <a href="/homeWork/home">首页</a> <span class="c-gray en">&gt;</span> 学校列表</nav>
<div class="pd-20">
  <div class="cl pd-5 bg-1 bk-gray mt-20">
    <span class="l">
    <a href="#" onclick="addNewSchool()" class="btn btn-primary radius">新增学校</a>
    </span>
  </div>
  <table class="table table-border table-bordered table-hover table-bg table-sort">
    <thead>
      <tr class="text-c">
        <th width="25"><input type="checkbox" name="" value=""></th>
        <th width="80">序号</th>
        <th width="100">学校名</th>
        <th width="150">地址</th>
        <th width="100">操作</th>
      </tr>
    </thead>
    <tbody>
    	<c:forEach items="${dataList }" var="school" varStatus="status">
      <tr class="text-c">
        <td><input type="checkbox" value="1" name=""></td>
        <td>${status.index+1 }</td>
        <td><u style="cursor:pointer" class="text-primary">${school.name }</u></td>
        <td>${school.address }</td>
        <td class="user-status"><span class="label label-success">已启用</span></td>
        <td class="f-14 user-manage"><a style="text-decoration:none" onClick="user_stop(this,'10001')" href="javascript:;" title="停用"><i class="icon-hand-down"></i></a> <a title="编辑" href="javascript:;" onclick="user_edit('4','550','','编辑','user-add.html')" class="ml-5" style="text-decoration:none"><i class="icon-edit"></i></a> <a style="text-decoration:none" class="ml-5" onClick="user_password_edit('10001','370','228','修改密码','user-password-edit.html')" href="javascript:;" title="修改密码"><i class="icon-key"></i></a> <a title="删除" href="javascript:;" onclick="user_del(this,'1')" class="ml-5" style="text-decoration:none"><i class="icon-trash"></i></a></td>
      </tr>
      </c:forEach>
    </tbody>
  </table>
  <div style="float: right;margin-right: 50px;" id="page" class="page"></div>
</div>

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="${ctx }/static/lib/My97DatePicker/4.8/WdatePicker.js"></script> 
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
                      self.location='/user/list?pageIndex='+pageIndex; 
                    }
                }
  });
  });
  
  //新增学校
  function addNewSchool(){
  layer.open({
  type: 2,
  title:'新增学校',
  area: ['700px', '450px'],
  fixed: false, //不固定
  maxmin: true,
  content: '/school/add'
});
  
  }
  
  
</script>
  </body>
</html>
