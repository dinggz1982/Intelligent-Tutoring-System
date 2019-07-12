<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>设定我的标签信息--选择词汇</title>
<style type="text/css">
* {
	padding: 0;
	margin: 0;
}

a {
	text-decoration: none;
	clear: inherit;
}

li {
	list-style: none;
}

.box {
	width: 600px;
	margin: 20px auto;
}

.box_l, .box_r {
	width: 240px;
	height: 360px;
	border: 1px solid #ccc;
	overflow: auto;
}

.box_l {
	float: left;
}

.box_m {
	float: left;
	width: 116px;
	text-align: center;
}

.box_m a {
	padding: 5px 10px;
	border: 1px solid #ccc;
	display: block;
	width: 70px;
	margin: 10px auto;
	border-radius: 5px;
}

.box_r {
	float: right;
}

.box_l li, .box_r li {
	line-height: 35px;
	font-size: 14px;
	padding-left: 15px;
	border-bottom: 1px solid #CCCCCC;
	cursor: pointer
}

.box_l li:last-child, .box_r li:last-child {
	border: none
}

.box_l li.choose, .box_r li.choose {
	background: #ddd;
}

.button {
	background-color: #4CAF50; /* Green */
	border: none;
	color: white;
	padding: 15px 32px;
	text-align: center;
	text-decoration: none;
	display: inline-block;
	font-size: 16px;
}
</style>
</head>
<body>
	<div style="margin: 0 auto;margin-top: 50px">
		<c:if test="${isTaged==0 }">
			<div style="margin:0 auto;text-align: center;">
				<span style="color: red">您还没选择标签</span>
			</div>
		</c:if>
		<div style="margin:0 auto;text-align: center;">
			<h1>第一步：选择标签</h1>
		</div>
		<div class="box">
			<div class="box_l">
				<ul>
					<c:forEach items="${words }" var="word">
						<li><span id="${word.id}">${word.word }</span> <input
							type="hidden" name="word" value="${word.id}"></li>
					</c:forEach>
				</ul>
			</div>
			<div class="box_m">
				<a href="javascript:" id="allleft">取消全部</a> <a href="javascript:"
					id="left">去掉一个</a> <a href="javascript:" id="right">选中一个</a> <a
					href="javascript:" id="allright">选中全部</a>
			</div>
			<form action="/tag/saveMyWord" method="post" id="myword">
				<div class="box_r">

					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" /> <input type="hidden" name="topic_id"
						value="${topic_id}" />

					<ul>

					</ul>

				</div>
			</form>

		</div>

	</div>
	<div style="clear: both;text-align: center;">
		<button id="submit" class="button">提交</button>
	</div>

</body>
<script src="/tag/js/jquery.min.js"></script>
<script src="/tag/js/orso.min.js"></script>
<script>
	$(document).ready(function(){
		$(".box").orso({
			boxl:".box_l",//左边大盒子
			boxr:".box_r",//右边大盒子
			boxlrX:"li",//移动小盒子
			boxon:"choose",//点击添加属性
			idclass:true,//添加的属性是否为class//true=class; false=id;
			boxlan:"#left",//单个向左移动按钮
			boxran:"#right",//单个向右移动按钮
			boxtan:"#top",//单个向上移动按钮
			boxban:"#bottom",//单个向下移动按钮
			boxalllan:"#allleft",//批量向左移动按钮
			boxallran:"#allright",//批量向右移动按钮
			boxalltan:"#alltop",//移动第一个按钮
			boxallban:"#allbottom"//移动最后一个按钮
		})
		$("#submit").click(function(){
			var form = document.getElementById('myword');
			form.submit();
		});
		
	});
</script>
</html>
