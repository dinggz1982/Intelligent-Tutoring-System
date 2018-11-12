<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>我的标注任务</title>
<%@include file="/WEB-INF/views/include/top.jsp"%>
</head>
<body>
	<nav class="breadcrumb">
	<i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span>
	用户评论语料库<span class="c-gray en">&gt;</span> 评论标注 <a
		class="btn btn-success radius r"
		style="line-height:1.6em;margin-top:3px"
		href="javascript:location.replace(location.href);" title="刷新"><i
		class="Hui-iconfont">&#xe68f;</i></a></nav>
		
	<div class="pd-20 col-xs-10 col-md-offset-1">
		<div class="text-c">
			<input type="text" class="input-text" style="width:250px"
				placeholder="检索评论" id="" name="">
			<button type="submit" class="btn btn-success" id="" name="">
				<i class="icon-search"></i> 搜评论
			</button>
		</div>

		<div class="panel panel-secondary mt-20">
			<div class="panel-header">${comment.courseType }--${comment.course }</div>
			<div class="panel-body">${comment.content}</div>
		</div>
	</div>
	<div class="col-xs-10 col-md-offset-1">
	<form action="/saveUserComment" method="get" id="userCommentForm">
		<h2>有效性判断</h2>
		<div class="skin-minimal">
			 <div class="radio-box">
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
		</div> 
	</form>
</div>
	<!--请在下方写此页面业务相关的脚本-->
	<script type="text/javascript" src="lib/laypage/1.2/laypage.js"></script>
<script type="text/javascript">
function save_submit(){
	var form = document.getElementById('userCommentForm');

	form.submit();
}
</script>
</body>
</html>
