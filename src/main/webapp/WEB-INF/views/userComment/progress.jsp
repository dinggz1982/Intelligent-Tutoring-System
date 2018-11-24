<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>标注任务总体进展</title>
<%@include file="/WEB-INF/views/include/top.jsp"%>
<script type="text/javascript" src="/static/echarts/echarts.min.js"></script>
</head>
<body>
	<nav class="breadcrumb">
	<i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span>
	用户评论语料库<span class="c-gray en">&gt;</span> 标注任务总体进展 <a
		class="btn btn-success radius r"
		style="line-height:1.6em;margin-top:3px"
		href="javascript:location.replace(location.href);" title="刷新"><i
		class="Hui-iconfont">&#xe68f;</i></a></nav>
		<div class="pd-20 col-xs-10 col-md-offset-1">
 <div id="main" style="width: 600px;height:200px;"></div>
 </div>
			  <script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('main'));

        // 指定图表的配置项和数据
        option = {
    title : {
        text: '已标注：${hasRemarkCount}，已标注：${notRemarkCount}',
        x:'center'
    },
    tooltip : {
        trigger: 'item',
        formatter: "{a} <br/>{b} : {c} ({d}%)"
    },
    legend: {
        orient: 'vertical',
        left: 'left',
        data: ['未标注','已标注']
    },
    series : [
        {
            name: '标注任务',
            type: 'pie',
            radius : '55%',
            center: ['50%', '60%'],
            data:[
                {value:${notRemarkCount}, name:'未标注'},
                {value:${hasRemarkCount}, name:'已标注'}
            ],
            itemStyle: {
                emphasis: {
                    shadowBlur: 10,
                    shadowOffsetX: 0,
                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                }
            }
        },
    ],
};
        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
    </script>
	<div class="col-xs-10 col-md-offset-1">
		<table class="table table-border table-bg">
 		<tr>
 			<th>
 				姓名
 			</th>
 			<th>
 				标注总数
 			</th>
 			<th>
 				已标注
 			</th>
 			<th>
 				未标注
 			</th>
 			<th>
 				详细情况
 			</th>
 			<c:forEach items="${remarks}" var="userRemark">
 				<tr>
 					<td>${userRemark.user.username}</td>
 					<td>${userRemark.hasRemarkCount+userRemark.notRemarkCount}</td>
 					<td>${userRemark.hasRemarkCount}</td>
 					<td>${userRemark.notRemarkCount}</td>
 					<td><a href="/corpus/detail/${userRemark.user.id}" target="_blank">详细情况</a></td>
 				</tr>
 			
 			</c:forEach>
 		</tr>
		</table>
 	</div>
	
</body>
</html>
