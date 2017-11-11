<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>智能教学系统--登录</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="智能教学系统,知识图谱">
<meta http-equiv="description" content="登录页面">
<link href="content/ui/css/layout.css" rel="stylesheet" />
<link href="content/ui/css/login.css" rel="stylesheet" />
<style>
.ibar {
	display: none;
}
</style>
</head>
<body class="login-bg">
<div class="main ">
        <!--登录-->
        <div class="login-dom login-max">
            <div class="logo text-center">
                <a href="#">
                    <img src="content/ui/img/logo.png" width="180px" height="180px" />
                </a>
            </div>
            <div class="login container " id="login">
                <p class="text-big text-center logo-color">
                   JAVA研究生課程
                </p>

                <p class="text-center margin-small-top logo-color text-small">
                  读书、实践、反思，三步一循环
                </p>
                <form class="login-form" action="/doLogin" method="post" autocomplete="off">
                    <div class="login-box border text-small" id="box">
                        <div class="name border-bottom">
                            <input type="text" placeholder="账号" id="username" name="username" datatype="*" nullmsg="请填写帐号信息" />
                        </div>
                        <div class="pwd">
                            <input type="password" placeholder="密码" datatype="*" id="password" name="password" nullmsg="请填写帐号密码" />
                        </div>
                    </div>
                    <input type="submit" class="btn text-center login-btn" value="立即登录" />
                </form>
                <div class="forget">
                    <a href="#" class="forget-pwd text-small fl"> 忘记登录密码？</a><a href="#" class="forget-new text-small fr" id="forget-new">注册账号</a>
                </div>
            </div>
        </div>
        <div class="footer text-center text-small ie">
            Copyright XXXXX      <a href="#" target="_blank">Intelligent-Tutoring-System</a>
            <span class="margin-left margin-right">|</span>
            <script src="#" language="JavaScript"></script>
        </div>
        <div class="popupDom">
            <div class="popup text-default">
            </div>
        </div>
    </div>
</body>
<script src="/content/ui/global/jQuery/jquery.min.js"></script>
<script type="text/javascript">
	function popup_msg(msg) {
		$(".popup").html("" + msg + "");
		$(".popupDom").animate({
			"top" : "0px"
		}, 400);
		setTimeout(function() {
			$(".popupDom").animate({
				"top" : "-40px"
			}, 400);
		}, 2000);
	}

	/*动画（注册）*/
	$(document).ready(function(e) {
		// $("input[name=username]").focus();
		// $('.login-form').Validform({
		// 	ajaxPost: true,
		// 	tiptype: function(msg) {
		// 		if (msg) popup_msg('' + msg + '');
		// 	},
		// 	callback: function(ret) {
		// 		popup_msg('' + ret.info + '');
		// 		if (ret.status == 1) {
		// 			if (ret.uc_user_synlogin) {
		// 				$("body").append(ret.uc_user_synlogin);
		// 			}
		// 			setTimeout("window.location='" + ret.url + "'", 2000);
		// 		}
		// 	}
		// })

	});
</script>
</html>
