<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>广州大学-教育技术-网络教育软件设计与开发</title>
<link rel="stylesheet" type="text/css" href="${ctx}/static/h-ui/css/H-ui.min.css" />
    <!-- css,js -->
<style type="text/css">
*{margin:0;padding:0;}
body{background:#e5e5e3;font-size:12px;font-family:"微软雅黑";#666}

.course{width:1024px;height:680px;margin:30px auto;}
.course .course_box{width:255px;height:155px;background:#FFCC66;float:left;margin-left:1px;
	cursor:pointer;margin-bottom:20px;color:#fff;position:relative;
}
.course .course_box h3{font-size:24px;font-weight:300;text-align:center;margin-top:63px;}
.course .course_box p{width:255px;height:155px;position:absolute;left:0;top:0;padding:10px;background:#003333;opacity:0.5;
						filter:alpha(opacity=50);display:none;	
}
.course .course_box p span{display:block;margin-top:2px;padding:2px;}
.course .course_box p .course_title{font-size:22px;}
.course .tz_blue{background:#2d8af1;}
.course .tz_red{background:#D44825;}
.course .tz_gray{background:#666;}
.course .tz_org{background:#ff6e1a;}
.course .tz_lv{background:#0cc5e7;}
.course .tz_qing{background:#64d500;}
.course .tz_yellow{background:#d5c300;} 
.course .tz_blue{background:#2d8af1;}
.course .tz_bluees{background:#2a45f1;}
.course .tz_redd{background:#D44835;}
.course .tz_grayy{background:black;}
.course .tz_orgg{background:#ff6e4a;}
.course .tz_lvv{background:#0cc5a7;}
.course .tz_qingg{background:#64c500;}
.course .tz_yelloww{background:#d45300;}
.course .tz_bluee{background:#2ddff1;}
</style>
<link rel="stylesheet" type="text/css" href="${ctx}/static/css/animate.min.css" />
  </head>
  <body>
 <header class="navbar-wrapper">
	<div class="navbar navbar-black">
		<div class="container cl">
			<a class="logo navbar-logo f-l mr-10 hidden-xs" href="#">智能教学系统</a>
			<span class="logo navbar-slogan f-l mr-10 hidden-xs">科研&middot; 实验</span>
			<a aria-hidden="false" class="nav-toggle Hui-iconfont visible-xs" href="javascript:;">&#xe667;</a>
			<nav class="nav navbar-nav nav-collapse" role="navigation" id="Hui-navbar">
				<ul class="cl">
					<li class="current"><a href="/login">登录</a></li>
				</ul>
			</nav>
			<nav class="navbar-userbar hidden-xs">
			</nav>
		</div>
	</div>
</header>  	
<div class="course">

	<div class="course_box tz_blue">
	<h3>Java程序基础</h3><p class="desc animated fadeIn" style="display: none;">
	<span class="course_title"><a href="#">Java</a></span>
	<span>·Java语法基础<br>·Java中的变量与数据类型<br>·运算符、表达式与语句<br>·循环与选择结构<br>·数组与方法的使用</span></p></div>
	
	<div class="course_box tz_gray"><h3>HTML5 </h3><p class="desc">
	<span class="course_title">Web 前端</span>
	<span>JavaScript、ActionScript、CSS、xHTML</span></p></div>
	
	<div class="course_box tz_red"><h3>Spring Boot</h3><p class="desc animated fadeIn" style="display: none;">
	<span class="course_title">JAVA WEB</span>
	<span>快速构建JAVA WEB程序</span></p></div>
	
	<div class="course_box tz_org"><h3>GOC图形化编程教育</h3><p class="desc">
	<span class="course_title">goc</span>
	<span>通过GoC作图快速掌握C++</span></p></div>
	
	<div class="course_box tz_lv"><h3>基于群体智慧的语言人文性知识图谱构建</h3><p class="desc">
	<a href="/humanism/index" target="_blank"><span class="course_title">汉语言系统中的人文系知识网络</span>
	<span>通过知识图谱解决纵横交错、蕴含映射、盘根错节的复杂性问题</span></a></p></div>
	
	<div class="course_box tz_qing"><h3>古诗词知识图谱</h3><p class="desc">
	<span class="course_title">基于JENA构建古诗词知识图谱</span>
	<span>知识图谱/语义网/本体入门案例</span></p></div>
	
	<div class="course_box tz_yellow"><h3>SPARQL</h3><p class="desc">
	<span class="course_title">语义检索</span>
	<span>展示常见的sparql检索</span></p></div>
	
	<div class="course_box tz_blue"><h3>群体智慧进化</h3><p class="desc">
	<span class="course_title">知识进化</span>
	<span>利用群体智慧促进知识演化</span></p></div>
	
	<div class="course_box tz_bluees"><h3>学习资源推荐算法</h3><p class="desc">
	<span class="course_title">常见的学习资源推荐算法</span>
	<span>协同过滤、基于知识的推荐...</span></p></div>
	
	<div class="course_box tz_redd animated swing"><h3>机器学习</h3><p class="desc animated fadeIn" style="display: block;">
	<span class="course_title">常见的机器学习算法</span>
	<span>一阶谓词、SVM、贝叶斯、决策树、线性/非线性分类、聚类</span></p></div>
	
	<div class="course_box tz_bluee"><h3>MOOC情感交互网络可视化</h3><p class="desc">
	<span class="course_title">基于社会网络分析，将学习者情感网络可视化</span>
	<span>情感网络可视化</span></p></div>

</div>

<script type="text/javascript" src="${ctx}/static/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript">
$(function(){
	$(".course_box").hover(function(){
		$(this).addClass("animated swing");
		$(this).find(".desc").show().removeClass("fadeOut").addClass("animated fadeIn");
	},function(){
		$(this).removeClass("animated swing");
		$(this).find(".desc").hide().remoceClass("fadeIn").addClass("animated fadeOut");
	});
});
</script>
  </body>
</html>
