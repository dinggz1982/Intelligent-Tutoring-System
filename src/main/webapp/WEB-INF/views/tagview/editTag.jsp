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
			<button type="button" class="btn btn-primary" id="showHistory">编辑记录</button>
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
					document.getElementById("wordId").value = e.target.id;
					saveChange(e.target,"move");
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
			//改变字体
			$("#wordSize").on('change', function() {
				if ($("#wordSize").val() !== '') {
					var wordID = document.getElementById("wordId").value;
					console.log("wordID:"+wordID);
					smText[wordID].attr('style', {
						fontSize: $("#wordSize").val()
					});
					saveChange(smText[wordID],"size");
					//console.log(smText[wordID]);
				}
			});
			//改变颜色
			$("#wordColor").on('change', function() {
				//console.log("change color="+$("#wordColor").val());
				if ($("#wordColor").val() !== '') {
					document.getElementById("colorValue").innerHTML = $("#wordColor").val();
					document.getElementById("wordColor").value= $("#wordColor").val();
					var wordID = document.getElementById("wordId").value;
					smText[wordID].attr('style', {
						textFill: $("#wordColor").val()
					});
					saveChange(smText[wordID],"color");
				}
			});
			//查看编辑历史
						$("#showHistory").on('click', function() {
							window.top.open("/tag/history/${topic_id}");
							
						});

		});
		
		
		//修改历史记录
		function saveChange(e,type){
		var wordID = document.getElementById("wordId").value;
		//console.log("wordID" + wordID);
			var header = $("meta[name='_csrf_header']").attr("content");
			var token =$("meta[name='_csrf']").attr("content");
			var data = {id:wordID,word:e.style.text,positionX:e.position[0],positionY:e.position[1],color:e.style.textFill,size:e.style.fontSize,topic_id:${topic_id},type:type};
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
		//保存标签状态
		function saveMyTag(){
		var wordList=[];  
			for (var i = 0; i < smText.length; i++) {
				if (smText[i] != null && typeof(smText[i]) != undefined && smText[i] != '') {
					myWordId=smText[i].id;
					positionX=smText[i].position[0];
					positionY=smText[i].position[1];
					wordString=smText[i].style.text;
					color=smText[i].style.textFill;
					size=smText[i].style.fontSize;
					var data={myWordId:myWordId,"positionX":positionX,"positionY":positionY,"wordString":wordString,"color":color,"size":size};  
				wordList.push(data);
				}
			}
			console.log(JSON.stringify(wordList));
			//保存编辑记录
			var header = $("meta[name='_csrf_header']").attr("content");
			var token =$("meta[name='_csrf']").attr("content");
			  $.ajax({
		          type: "POST",
		          url: "/saveMyTag",
		          dataType: "json",
		          data: {"myWords":wordList,"topic_id":${topic_id}},
		          beforeSend : function(xhr) {
		              xhr.setRequestHeader(header, token);
		          },
    				error: function (data) {
        			console.log(data);
    				}
		      });
		}
	</script>
</html>
