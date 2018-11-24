<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>我的标注任务</title>
<%@include file="/WEB-INF/views/include/top.jsp"%>
<script type="text/javascript" src="/static/echarts/echarts.min.js"></script>
</head>
<body>
	<nav class="breadcrumb">
	<i class="Hui-iconfont">&#xe67f;</i> <a href="/admin" target="_blank">首页 </a><span class="c-gray en">&gt;</span>
	用户评论语料库<span class="c-gray en">&gt;</span> 评论标注 <a
		class="btn btn-success radius r"
		style="line-height:1.6em;margin-top:3px"
		href="javascript:location.replace(location.href);" title="刷新"><i
		class="Hui-iconfont">&#xe68f;</i></a></nav>
	<c:choose>
		<c:when test="${empty comment }">
		<div class="pd-20 col-xs-10 col-md-offset-1">
		<h1>目前您没有标注任务！</h1>
		</div>
		</c:when>
		<c:otherwise>
			<div class="pd-20 col-xs-10 col-md-offset-1">
		<div class="text-c">
			 <div id="main" style="width: 600px;height:200px;"></div>
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
		</div>

		<div class="panel panel-secondary mt-20">
			<div class="panel-header">${comment.courseType }--${comment.course }</div>
			<div class="panel-body">${comment.content}</div>
		</div>
	</div>
	<div class="col-xs-10 col-md-offset-1">
	<form action="/corpus/saveUserComment" method="get" id="userCommentForm">
		<h2>有效性判断</h2>
		<div class="skin-minimal">
			 <div class="radio-box">
			  <input type="radio" id="radio-2" name="invalidComment" value="2">
    <label for="radio-2">有用评论</label>
    <input type="radio" id="radio-1" name="invalidComment" value="1">
    <label for="radio-1">无效评论</label>
  </div>
		</div>
		<h2>内容相关类评论</h2>
			<div class="skin-minimal">
				<div class="check-box">
					<input type="checkbox" id="checkbox-1" name="contentRelated" value="1"> <label
						for="checkbox-1">针对课程内容的提问</label>
				</div>
				<div class="check-box">
					<input type="checkbox" id="checkbox-2" name="contentRelated" value="2"> <label
						for="checkbox-2">针对课程内容的评论与观点</label>
				</div>
				<div class="check-box">
					<input type="checkbox" id="checkbox-3" name="contentRelated" value="3"> <label
						for="checkbox-3">课程内容的相关资源说明</label>
				</div>
			</div>
		<h2>情感相关类评论</h2>	
			<div class="skin-minimal">
				<div class="check-box">
					<input type="checkbox" id="checkbox-4" name="emotionRelated" value="1"> <label
						for="checkbox-4">对课程内容的情感倾向</label>
				</div>
				<div class="check-box">
					<input type="checkbox" id="checkbox-5" name="emotionRelated" value="2"> <label
						for="checkbox-5">对教师或教学风格的情感倾向</label>
				</div>
			</div>
			<h2>其他类评论</h2>	
			<div class="skin-minimal">
				<div class="check-box">
					<input type="checkbox" id="checkbox-6" name="otherRelated" value="1"> <label
						for="checkbox-6">对网络课程技术支持的问题或评价</label>
				</div>
				<div class="check-box">
					<input type="checkbox" id="checkbox-7" name="otherRelated" value="2"> <label
						for="checkbox-7">社交类评论</label>
				</div>
				<div class="check-box">
					<input type="checkbox" id="checkbox-8" name="otherRelated" value="2"> <label
						for="checkbox-8">对学习状态或学习进度的描述</label>
				</div>
				<div class="check-box">
					<input type="checkbox" id="checkbox-9" name="otherRelated" value="2"> <label
						for="checkbox-9">其他不属于上述类别的评论</label>
				</div>
			</div>
			<p/><p/>
			<div class="row cl">
			<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
				<button onclick="save_submit();" class="btn btn-primary radius" type="submit"><i class="Hui-iconfont"></i> 保存</button>
				<button onclick="layer_close();" class="btn btn-default radius" type="button">&nbsp;&nbsp;取消&nbsp;&nbsp;</button>
			</div>
			<input type="hidden" name="commentId" value="${comment.id}">
			<input type="hidden" name="taskId" value="${taskId}">
		</div> 
	</form>
	<div style="margin-bottom: 50px;"></div>
</div>
		</c:otherwise>
	</c:choose>		
	
	<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript">
function save_submit(){
	var form = document.getElementById('userCommentForm');
	//是否有效评论
	/* var invalidComment = $('input:radio[name="invalidComment"]:checked').val();
	if(invalidComment!=1){
		//非无效评论时，要求有选项
	}
	if(!$('#checkbox-id').is(':checked')) {
    
	} */
	form.submit();
}
</script>
</body>
</html>
