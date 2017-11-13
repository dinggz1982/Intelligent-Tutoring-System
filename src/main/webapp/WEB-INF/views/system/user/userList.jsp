<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>用户列表</title>
    <link rel="stylesheet" href="/content/ui/global/bootstrap/css/bootstrap.min.css"/>
    <!-- Font Awesome -->
    <link href="/content/ui/global/font-awesome/css/font-awesome.css" rel="stylesheet" />
    <!-- Theme style -->
    <link rel="stylesheet" href="/content/adminlte/dist/css/AdminLTE.css">
    <link rel="stylesheet" href="/content/adminlte/dist/css/skins/_all-skins.min.css">
    <link href="/content/min/css/supershopui.common.min.css" rel="stylesheet"/>
    <link rel="stylesheet" href="/content/plugins/bootstrap-table/bootstrap-table.min.css"/>
<style type="text/css">
.pull-right.pagination-detail{
display:none;
}
</style>
</head>

<body>
	<section class="content-header">
	<h1>
		用户列表<small>管理系统用户</small>
	</h1>
	<ol class="breadcrumb">
		<li><a href="#"><i class="fa fa-dashboard"></i> 主页</a></li>
		<li class="active">用户列表</li>
	</ol>
	</section>
	 <section class="content">
        <table id="table" class="table table-hover"></table>
    </section>
</body>
<script src="/content/ui/global/jQuery/jquery.min.js"></script>
<!-- Bootstrap 3.3.6 -->
<script src="/content/ui/global/bootstrap/js/bootstrap.min.js"></script>
<script src="/content/min/js/supershopui.common.js"></script>
<script src="/content/plugins/bootstrap-table/bootstrap-table.min.js"></script>
<script src="/content/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>
<script type="text/javascript">
$(function () {  
  
    var t = $("#table").bootstrapTable({  
        url: '/user/userList',  
        method: 'get',  
        dataType: "json",  
        striped: true,//设置为 true 会有隔行变色效果  
        undefinedText: "空",//当数据为 undefined 时显示的字符  
        pagination: true, //分页  
        // paginationLoop:true,//设置为 true 启用分页条无限循环的功能。  
        showToggle: "true",//是否显示 切换试图（table/card）按钮  
        showColumns: "true",//是否显示 内容列下拉框  
        pageNumber: 1,//如果设置了分页，首页页码  
        // showPaginationSwitch:true,//是否显示 数据条数选择框  
        pageSize: 5,//如果设置了分页，页面数据条数  
        pageList: [5, 10, 20, 40],  //如果设置了分页，设置可供选择的页面数据条数。设置为All 则显示所有记录。  
        paginationPreText: '‹',//指定分页条中上一页按钮的图标或文字,这里是<  
        paginationNextText: '›',//指定分页条中下一页按钮的图标或文字,这里是>  
        // singleSelect: false,//设置True 将禁止多选  
        search: false, //显示搜索框  
        data_local: "zh-US",//表格汉化  
        sidePagination: "server", //服务端处理分页  
        queryParams: function (params) {//自定义参数，这里的参数是传给后台的，我这是是分页用的  
            return {//这里的params是table提供的  
                cp: params.offset,//从数据库第几条记录开始  
                ps: params.limit//找多少条  
            };  
        },  
        idField: "id",//指定主键列  
        columns: [  
            {  
                title: '#',//表的列名  
                field: 'id',//json数据中rows数组中的属性名  
                align: 'center'//水平居中  
            },  
            {  
                title: '账号',  
                field: 'username',  
                align: 'center'  
            }
        ]  
    });  
  
});   
</script>
</html>
