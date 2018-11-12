<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>广州大学-教育技术-网络教育软件设计与开发</title>
   <%@include file="/WEB-INF/views/include/top.jsp" %>
  </head>
  <body>
<header class="navbar-wrapper">
	<div class="navbar navbar-fixed-top">
		<div class="container-fluid cl"> <a class="logo navbar-logo f-l mr-10 hidden-xs" href="#">智能教学系统</a> <a class="logo navbar-logo-m f-l mr-10 visible-xs" href="/aboutHui.shtml">H-ui</a> 
			<span class="logo navbar-slogan f-l mr-10 hidden-xs">v0.1</span> 
			<a aria-hidden="false" class="nav-toggle Hui-iconfont visible-xs" href="javascript:;">&#xe667;</a>
			<nav class="nav navbar-nav">
				<ul class="cl">
					<li class="dropDown dropDown_hover"><a href="javascript:;" class="dropDown_A"><i class="Hui-iconfont">&#xe600;</i> 新增 <i class="Hui-iconfont">&#xe6d5;</i></a>
						<ul class="dropDown-menu menu radius box-shadow">
							<li><a href="javascript:;" onclick="alert('正在开发ing')"><i class="Hui-iconfont">&#xe616;</i> 资讯</a></li>
							<li><a href="javascript:;" onclick="alert('正在开发ing')"><i class="Hui-iconfont">&#xe613;</i> 应用</a></li>
					</ul>
					<li class="navbar-levelone current"><a href="javascript:;">平台</a></li>
					<li class="navbar-levelone"><a href="javascript:;">知识图谱</a></li>
					<li class="navbar-levelone"><a href="javascript:;">考勤</a></li>
					<li class="navbar-levelone"><a href="javascript:;">用户评论语料库</a></li>
				</li>
			</ul>
		</nav>
		<nav id="Hui-userbar" class="nav navbar-nav navbar-userbar hidden-xs">
			<ul class="cl">
				<li>超级管理员</li>
				<li class="dropDown dropDown_hover">
					<a href="#" class="dropDown_A">${currentUser.username } <i class="Hui-iconfont">&#xe6d5;</i></a>
					<ul class="dropDown-menu menu radius box-shadow">
						<li><a href="javascript:;" onClick="myselfinfo()">个人信息</a></li>
						<li><a href="#">切换账户</a></li>
						<li><a href="#">退出</a></li>
				</ul>
			</li>
				<li id="Hui-msg"> <a href="#" title="消息"><span class="badge badge-danger">1</span><i class="Hui-iconfont" style="font-size:18px">&#xe68a;</i></a> </li>
				<li id="Hui-skin" class="dropDown right dropDown_hover"> <a href="javascript:;" class="dropDown_A" title="换肤"><i class="Hui-iconfont" style="font-size:18px">&#xe62a;</i></a>
					<ul class="dropDown-menu menu radius box-shadow">
						<li><a href="javascript:;" data-val="default" title="默认（黑色）">默认（黑色）</a></li>
						<li><a href="javascript:;" data-val="blue" title="蓝色">蓝色</a></li>
						<li><a href="javascript:;" data-val="green" title="绿色">绿色</a></li>
						<li><a href="javascript:;" data-val="red" title="红色">红色</a></li>
						<li><a href="javascript:;" data-val="yellow" title="黄色">黄色</a></li>
						<li><a href="javascript:;" data-val="orange" title="橙色">橙色</a></li>
					</ul>
				</li>
			</ul>
		</nav>
	</div>
</div>
</header>
<aside class="Hui-aside">
	<div class="menu_dropdown bk_2">
		<c:forEach items="${resources }" var="resource">
		<dl id="menu-article">
			<dt><i class="Hui-iconfont">&#xe616;</i> ${resource.name }<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<c:choose>
					<c:when test="${!empty resource.children }">
				<ul>
					<c:forEach items="${resource.children }" var="subRes">
							<li><a data-href="${subRes.url }" data-title="${subRes.name }" href="javascript:void(0)">${subRes.name }</a></li>
					</c:forEach>
				</ul>
				</c:when>
				</c:choose>
			</dd>
		</dl>
		</c:forEach>
		
	</div>

	<div class="menu_dropdown bk_2" style="display:none">
		<dl id="menu-aaaaa">
			<dt><i class="Hui-iconfont">&#xe616;</i> 知识图谱<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					<li><a data-href="article-list.html" data-title="学科" href="javascript:void(0)">学科</a></li>
				</ul>
			</dd>
		</dl>
	</div>

	<div class="menu_dropdown bk_2" style="display:none">
		<dl id="menu-bbbbb">
			<dt><i class="Hui-iconfont">&#xe616;</i> 考勤<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					<li><a data-href="/rollCall" data-title="考勤" href="javascript:void(0)">考勤</a></li>
					<li><a data-href="/rollCallList" data-title="考勤信息" href="javascript:void(0)">考勤信息</a></li>
					
				</ul>
			</dd>
		</dl>
	</div>

	<div class="menu_dropdown bk_2" style="display:none">
		<dl id="menu-ccccc">
			<dt><i class="Hui-iconfont">&#xe616;</i> 用户评论语料库<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					<li><a data-href="/myJob" data-title="用户评论语料库" href="javascript:void(0)">用户评论语料库</a></li>
				</ul>
			</dd>
		</dl>
	</div>

</aside>
<div class="dislpayArrow hidden-xs"><a class="pngfix" href="javascript:void(0);" onClick="displaynavbar(this)"></a></div>
<section class="Hui-article-box">
	<div id="Hui-tabNav" class="Hui-tabNav hidden-xs">
		<div class="Hui-tabNav-wp">
			<ul id="min_title_list" class="acrossTab cl">
				<li class="active">
					<span title="我的桌面" data-href="welcome.html">我的桌面</span>
					<em></em></li>
		</ul>
	</div>
		<div class="Hui-tabNav-more btn-group"><a id="js-tabNav-prev" class="btn radius btn-default size-S" href="javascript:;"><i class="Hui-iconfont">&#xe6d4;</i></a><a id="js-tabNav-next" class="btn radius btn-default size-S" href="javascript:;"><i class="Hui-iconfont">&#xe6d7;</i></a></div>
</div>
	<div id="iframe_box" class="Hui-article">
		<div class="show_iframe">
			<div style="display:none" class="loading"></div>
			<iframe scrolling="yes" frameborder="0" src="/userOnline"></iframe>
	</div>
</div>
</section>

<div class="contextMenu" id="Huiadminmenu">
	<ul>
		<li id="closethis">关闭当前 </li>
		<li id="closeall">关闭全部 </li>
</ul>
</div>

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="${ctx }/static/lib/jquery.contextmenu/jquery.contextmenu.r2.js"></script>
<script type="text/javascript">
$(function(){
	/*$("#min_title_list li").contextMenu('Huiadminmenu', {
		bindings: {
			'closethis': function(t) {
				console.log(t);
				if(t.find("i")){
					t.find("i").trigger("click");
				}		
			},
			'closeall': function(t) {
				alert('Trigger was '+t.id+'\nAction was Email');
			},
		}
	});*/


	$("body").Huitab({
		tabBar:".navbar-wrapper .navbar-levelone",
		tabCon:".Hui-aside .menu_dropdown",
		className:"current",
		index:0,
	});
});
/*个人信息*/
function myselfinfo(){
	layer.open({
		type: 1,
		area: ['300px','200px'],
		fix: false, //不固定
		maxmin: true,
		shade:0.4,
		title: '查看信息',
		content: '<div>管理员信息</div>'
	});
}

/*资讯-添加*/
function article_add(title,url){
	var index = layer.open({
		type: 2,
		title: title,
		content: url
	});
	layer.full(index);
}
/*图片-添加*/
function picture_add(title,url){
	var index = layer.open({
		type: 2,
		title: title,
		content: url
	});
	layer.full(index);
}
/*产品-添加*/
function product_add(title,url){
	var index = layer.open({
		type: 2,
		title: title,
		content: url
	});
	layer.full(index);
}
/*用户-添加*/
function member_add(title,url,w,h){
	layer_show(title,url,w,h);
}


</script> 
  </body>
</html>
