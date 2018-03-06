<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>点名系统</title>

<link rel="stylesheet" href="${ctx }/static/rollcall/css/style.css">

</head>

<body>
	<div class='luck-back'>
		<div class="luck-content ce-pack-end">
			<div id="luckuser" class="slotMachine">
				<div class="slot">
					<span class="name">姓名</span>
				</div>
			</div>
			<div class="luck-content-btn">
				<a id="start" class="start" onclick="start()">开始</a>
			</div>
			<div class="luck-user">
				<div class="luck-user-title">
					<span>点名名单</span>
				</div>
				<ul class="luck-user-list"></ul>
				<div class="luck-user-btn">
					<a href="#">选中人</a>
				</div>
			</div>
		</div>
	</div>
	<script src="${ctx }/static/rollcall/js/jquery-2.2.1.min.js" type="text/javascript"></script>
	<script>
		var xinm = new Array();
		xinm[0] = "img/1.jpg"
		xinm[1] = "img/2.jpg"
		xinm[2] = "img/3.jpg"
		xinm[3] = "img/4.jpg"
		xinm[4] = "img/5.jpg"
		xinm[5] = "img/6.jpg"
		xinm[6] = "img/1.jpg"
		xinm[7] = "img/2.jpg"
		xinm[8] = "img/3.jpg"
		xinm[9] = "img/4.jpg"
		xinm[10] = "img/5.jpg"
		xinm[11] = "img/6.jpg"
	
		var phone = new Array();
		phone[0] = "张三"
		phone[1] = "李四"
		phone[2] = "王二麻"
		phone[3] = "赵钱"
		phone[4] = "孙丽"
		phone[5] = "周五"
		phone[6] = "郑旺"
		phone[7] = "李二蛋"
		phone[8] = "苟云强"
		phone[9] = "德玛西亚"
		phone[10] = "无知小辈"
		phone[11] = "郝强"
	</script>
	<div style="text-align:center;"></div>
	<script type="text/javascript">

             var arrList = new Array();   

            // arrList = "${users}".replace('[','').replace(']','').split(',');   

			 arrList = eval(${users});
			
             //console.log(arrList[0]);   
             
             var xinm = new Array();
             
             xinm = eval(${imgs});

            var phone = new Array();
            
 			phone = eval(${usernames});
 			
 			var xuehao = new Array();
            
 			xuehao = eval(${xuehaos});
 			
 			var ids = new Array();
            
 			id = eval(${ids});
 			
        </script>  
        	<script src="${ctx }/static/rollcall/js/Luckdraw.js" type="text/javascript"></script>
        
</body>
</html>
