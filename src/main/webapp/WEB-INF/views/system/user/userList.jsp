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
    <link href="/content/plugins/jqgrid/jqgrid.css" rel="stylesheet" />
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
        <div class="row">
            <div class="col-md-12">
                <!-- BEGIN SAMPLE TABLE PORTLET-->
                <div class="box-header with-border">
                    <h3 class="box-title">简单表格</h3>
                </div>
                    <div class="box-body">

                        <div class="gridPanel">
                            <table id="gridTable"></table>
                            <div id="gridPager"></div>
                        </div>

                    </div>
              
            </div>

        </div>
    </section>
</body>
<script src="/content/ui/global/jQuery/jquery.min.js"></script>
<!-- Bootstrap 3.3.6 -->

<script src="/content/ui/global/bootstrap/js/bootstrap.min.js"></script>
<script src="/content/min/js/supershopui.common.js"></script>

<script src="/content/plugins/jqgrid/grid.locale-cn.js"></script>
<script src="/content/plugins/jqgrid/jqgrid.min.js"></script>
<script>
    $(function () {
        InitialPage();
        GetGrid();
    });
    
    //初始化页面
    function InitialPage() {
        //resize重设(表格、树形)宽高
        $(window).resize(function (e) {
            window.setTimeout(function () {
                $('#gridTable').setGridWidth(($('.gridPanel').width()));
                var height = App.getIframeLayoutHeight();
                $("#gridTable").setGridHeight($(window).height() - height-500);

            }, 200);
            e.stopPropagation();
        });
    }

    //加载表格
    function GetGrid() {
        var selectedRowIndex = 0;
        var $gridTable = $('#gridTable');
        $gridTable.jqGrid({
            url: "user/userList",
            datatype: "json",
            height: App.getViewPort().height - $('.page-footer').outerHeight() - $('.page-header').outerHeight(),
            colModel: [
                { label: '账户', name: 'username', index: 'username', width: 100, align: 'left' },
                { label: 'nickName', name: 'nickName', index: 'nickName', width: 100, align: 'left' },
                { label: 'sex', name: 'sex', index: 'sex', width: 45, align: 'center' },
                { label: 'email', name: 'email', index: 'email', width: 100, align: 'center' }
            ],
            viewrecords: true,
            rowNum: 20,
            rowList: [30, 50, 100],
            pager: "#gridPager",
            sortname: 'username asc',
            rownumbers: true,
            autowidth: true,
            shrinkToFit: false,
            gridview: true,
            onSelectRow: function () {
                selectedRowIndex = $("#" + this.id).getGridParam('selrow');
            },
            gridComplete: function () {
                $("#" + this.id).setSelection(selectedRowIndex, false);
            }
        });
    }
</script>

</html>
