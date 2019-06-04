<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>My JSP 'qa.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link rel="stylesheet" href="/layim/css/layui.css">
<script src="/layim/layui.js"></script>
<script src="/static/lib/jquery/1.9.1/jquery.min.js"></script>
</head>

<body>
	<script>
		layui.use('layim', function(layim) {
			//先来个客服模式压压精
			layim.config({
				brief : false, //是否简约模式（如果true则不显示主面板）
				title : '测试',
				init : {
					url : '/qa/init', //接口地址（返回的数据格式见下文）
					type : 'get', //默认get，一般可不填
					data : {} //额外参数
				}
			});
	
			layim.on('sendMessage', function(res) {
				var mine = res.mine;
				var sendId = mine.id;
				var content = mine.content;
				var to = res.to;
				var toId = to.id;
				$.ajax({
					type : 'get',
					url : '/qa/question',
					data : {
						"sendId" : sendId,
						"content" : content,
						"toId" : toId
					},
					success : function(result) {
						layim.getMessage({
							username : res.to.username, //消息来源用户名
							avatar : res.to.avatar, //消息来源用户头像
							id : sendId, //消息的来源ID（如果是私聊，则是用户id，如果是群聊，则是群组id）
							type : "friend", //聊天窗口来源类型，从发送消息传递的to里面获取
							content : result.answer, //消息内容
							mine : false, //是否我发送的消息，如果为true，则会显示在右方
							fromid : toId, //消息的发送者id（比如群组中的某个消息发送者），可用于自动解决浏览器多窗口时的一些问题
							timestamp : result.timestamp //服务端时间戳毫秒数。注意：如果你返回的是标准的 unix 时间戳，记得要 *1000
						});
					},
					error : function(msg) {
						alert("failed:" + JSON.stringify(msg));
					},
					dataType : "json"
				});
	
			});
		});
		function success() {
			alert();
		}
	</script>
</body>
</html>
