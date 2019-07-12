<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>编辑标签</title>
<%@include file="/WEB-INF/views/include/top.jsp"%>
</head>
<body>
	<nav class="breadcrumb"> <i class="Hui-iconfont">&#xe67f;</i> 首页
	<span class="c-gray en">&gt;</span> 编辑标签<span class="c-gray en">&gt;</span>
	<a class="btn btn-success radius r"
		style="line-height: 1.6em; margin-top: 3px"
		href="javascript:location.replace(location.href);" title="刷新"><i
		class="Hui-iconfont">&#xe68f;</i></a></nav>
	<div class="pd-20 col-xs-10 col-md-offset-1"></div>

	<div class="container ui-sortable">
		<h1>更新标签</h1>
		<div class="panel panel-default">
			<div class="panel-header"></div>
			<div class="panel-body">
				<form action="#" method="post" id="tagForm"
					class="form form-horizontal responsive">
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />
					<input type="hidden" name="id" value="${topic.id }">	
					<div class="row cl">
						<label class="form-label col-xs-3">词汇主题：</label>
						<div class="formControls col-xs-8">
							<input type="text" class="input-text" required
								placeholder="描述词汇的主题，如教育技术" readonly="readonly" name="topic" value="${topic.topic }"
								id="topic" aria-required="true">
						</div>
					</div>
					<div class="row cl">
						<label class="form-label col-xs-3">主题描述信息：</label>
						<div class="formControls col-xs-8">
							<textarea readonly="readonly" cols="" rows="" class="textarea" name="description"
								aria-required="true" id="description" required
								placeholder="主题描述信息">${topic.description }</textarea>
						</div>
					</div>

					<div class="row cl">
						<label class="form-label col-xs-3">相关词汇：</label>
						<div class="formControls col-xs-8">
							<table class="table table-border table-bordered radius"
								id="tagTable">
								<thead>
									<tr>
										<th width="20%">标签</th>
										<th width="20%">权重</th>
										<th width="20%">频率</th>
										<th width="20%">TFIDF</th>
										<!-- 
										<th width="20%"><input id="addTag"
											class="btn btn-success radius" type="button" value="新增"></th> -->
									</tr>
								</thead>
								<c:forEach var="word" items="${words }">
									<tr>
										<td><input aria-required="true" require
											class="input-text" value="${word.word }" type="text"
											name="words" /></td>
										<td><input aria-required="true" require class="input-text"
											type="text" name="weights" value="${word.weight }" /></td>
										<td><input class="input-text" type="text" require
											aria-required="true" name="frequencys" value="${word.frequency }"  /></td>
										<td><input aria-required="true" require class="input-text"
											type="text" name="tfidfs"  value="${word.tfidf }"/></td>
										<!-- 
										<td><input onclick="delThis(this)"
											class="btn btn-danger radius" type="button" value="删除"></td> -->
									</tr>
								</c:forEach>
							</table>
						</div>
					</div>
					<div class="row cl">
						<div class="col-xs-8 col-xs-offset-3">
							<input class="btn btn-primary" onclick="showTopic()" type="button" 
								value="&nbsp;&nbsp;确定&nbsp;&nbsp;">
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript" src="/validation/jquery.validate.min.js"></script>
<script type="text/javascript" src="/validation/validate-methods.js"></script>
<script type="text/javascript" src="/validation/messages_zh.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){ 
	$("#addTag").click(function(){
		//alert();
		var tr="<tr><td><input aria-required='true' class='input-text'  type='text' name='word'/>"+
        "</td><td><input aria-required='true' class='input-text'	 type='text' name='weight'/></td><td><input class='input-text'"+
       "type='text' aria-required='true' name='frequency'/></td><td><input aria-required='true' class='input-text'	 type='text' name='tfidf'/></td>" +
       "<td><input onclick='delThis(this)' class='btn btn-danger radius' type='button' value='删除'></td></tr>";
		$("#tagTable").append(tr);
	});
}); 
//删除一行
function delThis(obj){
	var tr = obj.parentNode.parentNode;
	console.log(tr);
    tr.parentNode.removeChild(tr);
}

function showTopic(){
	window.location.href="/tag-setting";
}

//上传附件
function uploadFile(){
        var form = new FormData(document.getElementById("tagForm"));
        var fileName = $("#file").val();
        if(fileName.lastIndexOf(".")==-1){
        	alert("上传的文件必须是excel格式");
        	return;
        }
        else{
        	var index = fileName.lastIndexOf(".");
            var suffix = fileName.substr(index+1);
            if(suffix=="xls"||suffix=="xlsx"){
            	$.ajax({
                    url:"/tag/uploadTag",
                    type:"post",
                    data:form,
                    processData:false,
                    contentType:false,
                    success:function(data){
                        if(data=="1"){
                            alert("上传成功")
                        }else {
                            alert("上传失败")
                        }
                    }
                });	
            }else{
            	alert("上传的文件必须是excel格式");
            	return;
            }
        }
        
    }

</script>
</html>
