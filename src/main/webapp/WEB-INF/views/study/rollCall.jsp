<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>点名系统</title>
<link rel="stylesheet" href="${ctx }/static/rollcall/css/style.css">
<style type="text/css">
h3 {
font-size:14px;
font-weight:bold;
}
pre,p {
color:#1E7ACE;
margin:4px;
}
input, select,textarea {
padding:1px;
margin:2px;
font-size:11px;
}
.buttom{
padding:1px 10px;
font-size:12px;
border:1px #1E7ACE solid;
background:#D0F0FF;
}
#formwrapper {
width:450px;
margin:15px auto;
padding:20px;
text-align:left;
border:1px solid #A4CDF2;
}
fieldset {
padding:10px;
margin-top:5px;
border:1px solid #A4CDF2;
background:#fff;
}
fieldset legend {
color:#1E7ACE;
font-weight:bold;
padding:3px 20px 3px 20px;
border:1px solid #A4CDF2; 
background:#fff;
}
fieldset label {
float:left;
width:120px;
text-align:right;
padding:4px;
margin:1px;
}
fieldset div {
clear:left;
margin-bottom:2px;
}
.enter{ text-align:center;}
.clear {
clear:both;
}
#rollcall{
 {position:absolute;left;0;bottom:0;}
}
</style>
</head>
<body>
<form action="saveRollCall" id="rollcall" method="get"></form>
	<h1 style="text-align: center;margin-top: 20px;">课堂点名
	选择班级：<select id="classInfo" name=classInfo>
		<option value="1">教育技术161</option>
				<option value="2">英语161_1班</option>
						<option value="3">英语161_2班</option>
	</select></h1>
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
					<a href="javascript:;" id="save" onclick="saveClick()">选中人</a>
				</div>
			</div>
		</div>
	</div>
		<input type="hidden" id="cango" name="cango" value="0">
	
	<script src="${ctx }/static/rollcall/js/jquery-2.2.1.min.js"
		type="text/javascript"></script>
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
 			
 			function saveClick(){
				//document.form[0].submit(); 
				/* $("#rollcall").serialize();
				$("#rollcall").submit(); */
				
 			//$("#rollcall").submit();
 		//	var id = new Array();
 	/* 		var els =document.getElementsByName("id[]");
for (var i = 0, j = els.length; i < j; i++){
//alert(els[i].value); //获得值<br>alert(els[i].id);　　//获得ID　　
id[i]=els[i].value;
} */
if($("#cango").val()==1){
$("#rollcall").submit();
}else{
alert("还未选好人！");

}
 //self.location='saveRollCall?id='+els; 
 			}
 			
 	$("#classInfo").change(function () {  
            var ss = $(this).children('option:selected').val();  
            window.location.href="/rollCall?classInfo="+ss;
        }); 		
 			
 			
        </script>
	<script src="${ctx }/static/rollcall/js/Luckdraw.js"
		type="text/javascript"></script>
</body>
</html>
