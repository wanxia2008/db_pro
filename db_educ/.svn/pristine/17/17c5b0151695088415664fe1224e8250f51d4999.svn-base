<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
 
<html lang="en" class="no-js">
<head>
<meta charset="utf-8">
<title>云教育后台登陆</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">

<!-- CSS -->

<link rel="stylesheet" href="loginCss/supersized.css">
<link rel="stylesheet" href="loginCss/login.css">
<link href="loginCss/bootstrap.min.css" rel="stylesheet">
<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
	<script src="js/html5.js"></script>
<![endif]-->
<script src="loginJs/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="loginJs/jquery.form.js"></script>
<script type="text/javascript" src="loginJs/tooltips.js"></script>
<script type="text/javascript" src="loginJs/login.js"></script>
</head>

<body>

<div class="page-container">
	<div class="main_box">
		<div class="login_box">
			<div class="login_logo">
				<!-- <img src="loginImages/logo.png" > -->
				云教育登录
			</div>
		
			<div class="login_form">
				<form action="teacherLogin.html" id="login_form" method="post">
					<div class="form-group">
						<label for="j_username" class="t">账　号：</label> 
						<input name="userName" type="text" class="form-control x319 in" 
						autocomplete="off"/>
						<div style="margin-left:130px;text-align:left;color:red;">${errorUserName}${accountValidity}${teacherStatus}</div>
					</div>
					
					<div class="form-group">
						<label for="j_password" class="t">密　码：</label> 
						<input name="password"  id="password" type="password" 
						class="password form-control x319 in"/>
						<div style="margin-left:130px;text-align:left;color:red;">${errorPassword}</div>
					</div>
					<div class="form-group">
						<label for="j_captcha" class="t">验证码：</label>
						<input name="imageCode" id="j_captcha" type="text" class="form-control x164 in"/>
						<img id="imageCode" alt="点击更换" title="点击更换" src="${ctx}/imageCode/createCode.do" class="m" onclick="this.src=this.src+'?'">
						<div style="margin-left:130px;text-align:left;color:red;">${errorImageCode}</div>
					</div>
					<div class="form-group space">
						<label class="t"></label>　　　
						<button type="submit"  
						class="btn btn-primary btn-lg">&nbsp;登&nbsp;录&nbsp; </button>
						<input type="reset" value="&nbsp;重&nbsp;置&nbsp;" class="btn btn-default btn-lg">
					</div>
				</form>
			</div>
		</div>
		
	</div>
</div>

<!-- Javascript -->

<script src="loginJs/supersized.3.2.7.min.js"></script>
<script src="loginJs/supersized-init.js"></script>
<script src="loginJs/scripts.js"></script>
<script type="text/javascript">
/* $.ajax({
	url: "${ctx}/login/studentLogin.do",
	type: "post",	
	dataType: "json",
	data:{},
	timeout:100,
	success: function(data){
		alert(123444);	
	}
}); */
</script>
</body>
</html>