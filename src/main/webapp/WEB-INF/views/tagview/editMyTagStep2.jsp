<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
	<meta name="_csrf" content="${_csrf.token}"/>
<meta name="_csrf_header"  content="${_csrf.headerName}"/>
		<title>标签编辑</title>
				<script src="/tag/js/zrender.js"></script>
		<link href="/tag/css/bootstrap.min.css" rel="stylesheet">
	</head>
	<body>
		<div class="container">
			<div class="row" style="text-align: center;">
				<h2>标签云实验</h2>
			</div>
			<div class="row">
				<div class="col-md-3">
					<table class="table">
						<input id="wordId" type="hidden" />
						<tbody>
							<tr>
								<td>选中的词汇：<span id="selectWord"></span></td>
							</tr>
							<tr>
								<td>大小：<input type="number" id="wordSize"></td>
							</tr>
							<tr>
								<td>颜色：<input type="color" id="wordColor" />&nbsp;&nbsp;<span id="colorValue"></span></td>
							</tr>
							<tr>
								<td>词汇位置：[<span id="wordPosition"></span>]</td>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="col-md-8" id="main" style="border:1px solid #000000;height: 600px;">
				</div>
			</div>
		</div>
	</body>
	<script src="/tag/js/jquery.min.js"></script>
	<script>
		var zr = zrender.init(document.getElementById('main'));
		
		var smText = new Array;
		 var words = new Array();
         <c:forEach items="${wordList }" var="myWord"> 
         var word = {"id":${myWord.word.id},"word":"${myWord.word.word}","size":${myWord.size},"color":"${word.color}","positionX":${myWord.positionX},"positionY":${myWord.positionY}}
         words.push(word)
         </c:forEach>   
		for (var i = 0; i < words.length; i++) {
			//console.log(i);
			smText[words[i].id] = new zrender.Text({
				id: words[i].id,
				style: {
					text: words[i].word,
					textAlign: 'center',
					textFill: words[i].color,
					fontSize: words[i].size
				},
				position: [words[i].positionX,words[i].positionY],
				draggable: true,
				//鼠标点击事情
				onclick: function(e) {
					var wordID = document.getElementById("wordId").value;
					console.log("wordID:"+wordID);
					document.getElementById("selectWord").innerHTML = e.target.style.text;
					//document.getElementById("colorValue").innerHTML = e.target.style.textFill;
					document.getElementById("wordSize").value = e.target.style.fontSize;
					document.getElementById("wordId").value = e.target.id;
					document.getElementById("wordColor").value = e.target.style.textFill;
					document.getElementById("wordPosition").innerHTML = e.target.position[0] + "," + e.target.position[1];
				},
				//鼠标在松开移动的事件
				onmouseup: function(e) {
					//保存用户的移动操作
					saveChange(e,"move");
					document.getElementById("wordId").value = e.target.id;
					//console.log(e.target.position[0] + "," + e.target.position[1]);
				},
				//鼠标在移动中的事件
				onmouseover: function(e) {
					//document.getElementById("colorValue").innerHTML = e.target.style.textFill;
					document.getElementById("selectWord").innerHTML = e.target.style.text;
					document.getElementById("wordSize").value = e.target.style.fontSize;
					document.getElementById("wordColor").value = e.target.style.textFill;
					document.getElementById("wordPosition").innerHTML = e.target.position[0] + "," + e.target.position[1];
					document.getElementById("wordId").value = e.target.id;
				}
			});
			zr.add(smText[words[i].id]);
		}
		$(document).ready(function() {
			$("#wordSize").on('change', function() {
				if ($("#wordSize").val() !== '') {
					var wordID = document.getElementById("wordId").value;
					console.log("wordID:"+wordID);
					smText[wordID].attr('style', {
						fontSize: $("#wordSize").val()
					});
				}
			});
			$("#wordColor").on('change', function() {
				//console.log("change color="+$("#wordColor").val());
				if ($("#wordColor").val() !== '') {
					document.getElementById("colorValue").innerHTML = $("#wordColor").val();
					document.getElementById("wordColor").value= $("#wordColor").val();
					var wordID = document.getElementById("wordId").value;
					smText[wordID].attr('style', {
						textFill: $("#wordColor").val()
					});
				}
			});
		});
		
		//修改历史记录
		function saveChange(e,type){
							var wordID = document.getElementById("wordId").value;
		//console.log("wordID" + wordID);
			var header = $("meta[name='_csrf_header']").attr("content");
			var token =$("meta[name='_csrf']").attr("content");
			var data = {id:wordID,word:e.target.style.text,positionX:e.target.position[0],positionY:e.target.position[1],color:e.target.style.textFill,size:e.target.style.fontSize,topic_id:${topic_id},type:type};
			  $.ajax({
		          type: "POST",
		          url: "/saveTagEditHistory",
		          dataType: "json",
		          data: data,
		          beforeSend : function(xhr) {
		              xhr.setRequestHeader(header, token);
		          }
		      })
			
			
		}
	</script>
</html>
