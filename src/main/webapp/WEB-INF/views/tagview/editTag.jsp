<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
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
         <c:forEach items="${wordList }" var="word"> 
         var word = {"word":"${word.word}","size":${word.size},"color":"${word.color}","positionX":${word.positionX},"positionY":${word.positionY}}
         words.push(word)
         </c:forEach>   
		for (var i = 0; i < words.length; i++) {
			smText[i] = new zrender.Text({
				id: i,
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
					//console.log(e.target.style.text);
					document.getElementById("selectWord").innerHTML = e.target.style.text;
					//document.getElementById("colorValue").innerHTML = e.target.style.textFill;
					document.getElementById("wordSize").value = e.target.style.fontSize;
					document.getElementById("wordId").value = e.target.id;
					document.getElementById("wordColor").value = e.target.style.textFill;
					document.getElementById("wordPosition").innerHTML = e.target.position[0] + "," + e.target.position[1];
				},
				//鼠标在松开移动的事件
				onmouseup: function(e) {
					console.log(e.target.position[0] + "," + e.target.position[1]);
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
			zr.add(smText[i]);
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
				console.log("change color="+$("#wordColor").val());
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
			var header = $("meta[name='_csrf_header']").attr("content");
			var token =$("meta[name='_csrf']").attr("content");
			  $.ajax({
		          type: "POST",
		          url: "/saveTagEditHistory",
		          dataType: "json",
		          data: {wordModels:JSON.stringify(wordlist)},
		          beforeSend : function(xhr) {
		              xhr.setRequestHeader(header, token);
		          },
		          success: function (data, msg) {
		              if (data.status == 1) {
		                  alert(data.msg);
		              }
		              else {
		                  alert(data.msg);
		              }
		          }
		      })
			
			
		}
	</script>
</html>
