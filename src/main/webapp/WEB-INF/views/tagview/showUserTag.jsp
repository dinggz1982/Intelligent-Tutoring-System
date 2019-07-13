<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta name="_csrf" content="${_csrf.token}" />
<meta name="_csrf_header" content="${_csrf.headerName}" />
<title>标签编辑</title>
<script src="/tag/js/zrender.js"></script>
<link href="/tag/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

	<div class="container" style="margin-top: 20px;margin-bottom: 13px;">
		<div class="row" style="text-align: center;">
			<h2>${user.username }的标签编辑结果</h2>
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
							<td>颜色：<input type="color" id="wordColor" />&nbsp;&nbsp;<span
								id="colorValue"></span></td>
						</tr>
						<tr>
							<td>词汇位置：[<span id="wordPosition"></span>]
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="col-md-8" id="main"
				style="border:1px solid #000000;height: 600px;"></div>
		</div>
		<div class="row"
			style="margin-top: 20px;margin-bottom: 15px;text-align: center;">
			<button type="button" class="btn btn-primary" onclick="showTagEditHistory()" id="showHistory">编辑记录</button>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<button type="button" class="btn btn-danger" onclick="saveMyTag()"
				id="saveMyTagBtn">保存</button>
		</div>

	</div>
</body>
<script src="/tag/js/jquery.min.js"></script>
<script>
		var zr = zrender.init(document.getElementById('main'));
		
		var smText = new Array;
		 var words = new Array();
         <c:forEach items="${wordList }" var="word"> 
         var word = {"id":${word.id},"word":"${word.word.word}","size":${word.size},"color":"${word.color}","positionX":${word.positionX},"positionY":${word.positionY}}
         words.push(word)
         </c:forEach>   
		for (var i = 0; i < words.length; i++) {
			smText[words[i].id] = new zrender.Text({
				id: words[i].id,
				style: {
					text: words[i].word,
					textAlign: 'center',
					textFill: words[i].color,
					fontSize: words[i].size
				},
				position: [words[i].positionX,words[i].positionY],
				draggable: false,
				//鼠标点击事情
				onclick: function(e) {
					var wordID = document.getElementById("wordId").value;
					document.getElementById("selectWord").innerHTML = e.target.style.text;
					//document.getElementById("colorValue").innerHTML = e.target.style.textFill;
					document.getElementById("wordSize").value = e.target.style.fontSize;
					document.getElementById("wordId").value = e.target.id;
					document.getElementById("wordColor").value = e.target.style.textFill;
					document.getElementById("wordPosition").innerHTML = e.target.position[0] + "," + e.target.position[1];
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
		
		function showTagEditHistory(){
		window.top.open("/tag/userhistory/${topic_id}/${user_id}");
		}
	</script>
</html>
