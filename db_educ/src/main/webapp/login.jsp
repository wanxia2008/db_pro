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

<link rel="stylesheet" href="${ctx}/loginCss/supersized.css">
<link rel="stylesheet" href="${ctx}/loginCss/login.css">
<link href="${ctx}/loginCss/bootstrap.min.css" rel="stylesheet">
<link rel="shortcut icon" type="image/x-icon"
	href="http://139.199.194.237:8086/db_educ/favicon.ico">
<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
	<script src="${ctx}/js/html5.js"></script>
<![endif]-->
<script src="${ctx}/loginJs/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="${ctx}/loginJs/jquery.form.js"></script>
<script type="text/javascript" src="${ctx}/loginJs/tooltips.js"></script>
</head>

<body>

<div class="page-container">
	<div class="main_box">
		<div class="login_box">
			<div class="login_logo">
				<img src="${ctx}/loginImages/login.png" >
			</div>
		
			<div class="login_form">
				<form action="${ctx}/user/teacherLogin.do" class="layui-form" method="post">
					<div class="form-group">
						<label for="j_username" class="t">账　号：</label> 
						<input name="userName" id="userName" type="text" value="${userName}" class="form-control x319 in" 
						lay-verify="userName" autocomplete="off" placeholder=""  />
						<div style="margin-left:130px;text-align:left;color:red;height:20px;">${errorUserName}${accountValidity}${teacherStatus}</div>
					</div>
					
					<div class="form-group">
						<label for="j_password" class="t">密　码：</label> 
						<input name="password"  id="password" type="password" value="${password}"
						class="password form-control x319 in" lay-verify="password"
						autocomplete="off" placeholder=""  />
						<div style="margin-left:130px;text-align:left;color:red;height:20px;">${errorPassword}</div>
					</div>
					<div class="form-group">
						<label for="j_captcha" class="t">验证码：</label>
						<input name="imageCode" id="j_captcha" type="text" class="form-control x164 in" lay-verify="j_captcha"
						autocomplete="off" placeholder="" />
						<img id="imageCode" alt="点击更换" title="点击更换" src="${ctx}/imageCode/createCode.do" class="m" onclick="this.src=this.src+'?'">
						<div style="margin-left:130px;text-align:left;color:red;height:20px;">${errorImageCode}</div>
					</div>
					<div class="form-group space">
						<label class="t"></label>　　　
						<button lay-submit="" lay-filter="demo1" 
						class="btn btn-primary btn-lg">&nbsp;登&nbsp;录&nbsp; </button>
						<input type="reset" value="&nbsp;重&nbsp;置&nbsp;" class="btn btn-default btn-lg">
					</div>
				</form>
			</div>
		</div>
	</div>
</div>

<!-- Javascript -->
<script src="${ctx}/loginJs/supersized.3.2.7.min.js"></script>
<script src="${ctx}/loginJs/supersized-init.js"></script>
<script src="${ctx}/loginJs/scripts.js"></script>
<script src="${ctx}/layui/layui.js" charset="utf-8"></script>
<script type="text/javascript">

layui.use([ 'form', 'layedit', 'laydate', 'layer' ],
		function() {
			var form = layui.form(), layer = layui.layer, layedit = layui.layedit, laydate = layui.laydate;

			//创建一个编辑器
			var editIndex = layedit.build('LAY_demo_editor');

			//自定义验证规则
			form.verify({
						userName :function(value) {
							if(value.length == "") {
								return "账号不能为空";
							}
						},
						password :function(value) {
							if(value.length == "") {
								return "密码不能为空";
							}
						},
						/* j_captcha :function(value) {
							if(value.length == "") {
								return "验证码不能为空";
							}
						}, */
			});

			//监听提交
			form.on('submit(demo1)', function(data) {
				
				return true;
			});
		});
		
		
		
$(document).ready(function(){
	if(window != top) {
		top.location.href = location.href;
	}
});
//防止页面后退
history.pushState(null, null, document.URL);
window.addEventListener('popstate', function () {
    history.pushState(null, null, document.URL);
});
jQuery(function($){
    $.supersized({

        // Functionality
        slide_interval     : 4000,    // Length between transitions
        transition         : 1,    // 0-None, 1-Fade, 2-Slide Top, 3-Slide Right, 4-Slide Bottom, 5-Slide Left, 6-Carousel Right, 7-Carousel Left
        transition_speed   : 5000,    // Speed of transition
        performance        : 1,    // 0-Normal, 1-Hybrid speed/quality, 2-Optimizes image quality, 3-Optimizes transition speed // (Only works for Firefox/IE, not Webkit)

        // Size & Position
        min_width          : 0,    // Min width allowed (in pixels)
        min_height         : 0,    // Min height allowed (in pixels)
        vertical_center    : 1,    // Vertically center background
        horizontal_center  : 1,    // Horizontally center background
        fit_always         : 0,    // Image will never exceed browser width or height (Ignores min. dimensions)
        fit_portrait       : 1,    // Portrait images will not exceed browser height
        fit_landscape      : 0,    // Landscape images will not exceed browser width

        // Components
        slide_links        : 'blank',    // Individual links for each slide (Options: false, 'num', 'name', 'blank')
        slides             : [    // Slideshow Images
                                 {image : '${ctx}/loginImages/backgrounds/2.jpg'}, 
                                /* {image : '${ctx}/loginImages/backgrounds/2.jpg'},*/
                                
                       ]

    });

});


</script>
</body>
</html>