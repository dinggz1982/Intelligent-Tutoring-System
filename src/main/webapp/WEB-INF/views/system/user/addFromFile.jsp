<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>导入实验人员信息</title>
	<meta name="_csrf" content="${_csrf.token}"/>
<meta name="_csrf_header"  content="${_csrf.headerName}"/>
<link rel="stylesheet" type="text/css"
	href="/static/lib/layerui/2.2.5/css/layui.css" />
</head>

<body>
	<div class="layui-container">
		<div class="layui-row" align="center" style="margin-top: 30px;">
			<button type="button" class="layui-btn" id="upload">
				<i class="layui-icon">&#xe67c;</i>选择文件
			</button>
		</div>
		<div class="layui-row" align="center" style="margin-top: 30px;">
			<textarea id="result" cols="50" rows="10"></textarea>
		</div>
	</div>

</body>
<script type="text/javascript" src="/tag/jquery.min.js"></script>
<script type="text/javascript" src="/static/lib/layerui/2.2.5/layui.js"></script>

<script>
	layui.use('upload', function() {
		var upload = layui.upload;
		var header = $("meta[name='_csrf_header']").attr("content");
		var token =$("meta[name='_csrf']").attr("content");
		//执行上传
		var uploadInst = upload.render({
			elem : '#upload', //绑定元素
			url : '/user/addUserFromFile', //上传接口
			method : 'POST',
			accept : 'file',
			exts :'xls',
			size : 50,
			before : function(obj) {
				layer.load();
			},
			beforeSend : function(xhr) {
		              xhr.setRequestHeader(header, token);
		          },
			done : function(res) { //上传完毕回调
				layer.closeAll('loading');
				var result = '';

				for (var i = 0; i < res.length; i++) {
					result = result + res[i].nsrsbh + "=" + res[i].container + "\n";
				}

				$("#result").html(result);
			},
			error : function(e) { //请求异常回调
				console.log(e);
				layer.closeAll('loading');
				layer.msg('网络异常，请稍后重试！');
			}
		});
	});
</script>
</html>
