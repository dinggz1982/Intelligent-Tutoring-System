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
			}).chat({
				name : '客服姐姐',
				type : 'friend',
				avatar : 'https://cdn.layui.com/avatar/168.jpg?t=1507143496817',
				id : 2000
			});
			
			layim.on('sendMessage', function(res){
  var mine = res.mine; //包含我发送的消息及我的信息
  console.log(mine);
  obj = {
          username: "客服姐姐"
          ,avatar: mine.avatar
          ,id: 2000
          ,type: mine.type
          ,content: "1234"
        }
  layim.getMessage(obj);
  	
  });
		
		
		layim.getMessage({
  username: "纸飞机" //消息来源用户名
  ,avatar: "http://tp1.sinaimg.cn/1571889140/180/40030060651/1" //消息来源用户头像
  ,id: "100000" //消息的来源ID（如果是私聊，则是用户id，如果是群聊，则是群组id）
  ,type: "friend" //聊天窗口来源类型，从发送消息传递的to里面获取
  ,content: "嗨，你好！本消息系离线消息。" //消息内容
  ,cid: 0 //消息id，可不传。除非你要对消息进行一些操作（如撤回）
  ,mine: false //是否我发送的消息，如果为true，则会显示在右方
  ,fromid: "100000" //消息的发送者id（比如群组中的某个消息发送者），可用于自动解决浏览器多窗口时的一些问题
  ,timestamp: 1467475443306 //服务端时间戳毫秒数。注意：如果你返回的是标准的 unix 时间戳，记得要 *1000
});
	});	
	</script>
</body>
</html>
