<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>新增教师</title>
<link rel="stylesheet" href=".././layui/css/layui.css" />
<link rel="stylesheet" href=".././css/bootstrap.min.css" />
<link rel="stylesheet" href=".././css/lecturenote/style.css" />
<link rel="stylesheet" href=".././css/font-awesome.min.css" />
<style type="text/css">
.layui-form-checkbox {
	height: 26px;
	line-height: 24px;
}

.layui-form-checkbox span {
	font-size: 14px;
}

ul li {
	margin-top: 5px;
	padding: 5px;
}
.a-btn{height:30px;line-height:30px;padding:0 18px;position: absolute;right: 25px;top: 20%;font-size: 10px;border-radius: 5px;background:#e33}
</style>
</head>
<body>
	<form id="myform" class="layui-form" method="post">
		<div class="panels_head">
			<span>教师新增</span> 
<!-- 				<button class="layui-btn" lay-submit="" lay-filter="demo1" style="float: right;margin-right: 80px;height:30px;line-height:30px;padding:0 18px;top:20%;margin-top:0">确认增加</button> -->
			<button class="layui-btn layui-btn-normal" lay-submit="" lay-filter="demo1" style="margin-right: 80px;height:30px;line-height:30px;padding:0 18px;top:20%;margin-top:0;font-size:14px">确认</button>
				<button type="button" class="layui-btn layui-btn-danger" onclick="window.location.href='${ctx}/teacher/teachermanage.do'"
						style=" height: 30px; line-height: 30px; padding: 0 18px; top: 20%; margin-top: 0;font-size:14px">返回</button>
		</div>
		<div class="layui-form-item" style="margin-top: 20px;">
			<div class="layui-inline">
				<label class="layui-form-label" style="width: 120px;font-size:14px;"><span style="color:red;font-size:16px !important">*&nbsp;&nbsp;</span>登录账号</label>
				<div class="layui-input-inline" style="width: 130px;">
					<input type="text" id="userName" name="userName" lay-verify="userName"
						autocomplete="off" placeholder="请输入登录账号" class="layui-input">
				</div>
			</div>

			<div class="layui-inline">
				<label class="layui-form-label" style="width: 120px;font-size:14px;"><span style="color:red;font-size:16px !important">*&nbsp;&nbsp;</span>登录密码</label>
				<div class="layui-input-inline" style="width: 130px;">
					<input type="text" id="password" name="password"
						lay-verify="password" autocomplete="off" placeholder="请输入登录密码"
						class="layui-input" onfocus="this.type='password'">
				</div>
			</div>
			<div class="layui-inline">
				<label class="layui-form-label" style="width: 120px;;font-size:14px;"><span style="color:red;font-size:16px !important">*&nbsp;&nbsp;</span>账号有效期</label>
				<div class="layui-input-inline" style="width: 130px;">
					<input type="text"  name="accountValidity" 
						id="date" lay-verify="date" placeholder="点击选择时间"
						autocomplete="off" class="layui-input"
						onclick="layui.laydate({elem: this})">
				</div>
			</div>
           </div>
           
           <div class="layui-form-item" style="margin-top: 20px;">
			
			<div class="layui-inline">
				<label class="layui-form-label" style="font-size:14px;width:120px;"><span style="color:red;font-size:16px !important">*&nbsp;&nbsp;</span>用户名</label>
				<div class="layui-input-inline" style="width: 130px;">
					<input type="text" id="teacherName" name="teacherName"
						lay-verify="teacherName" autocomplete="off" placeholder="请输入真实姓名"
						class="layui-input" maxlength="10">
				</div>
			</div>
			<div class="layui-inline">
				<label class="layui-form-label" style="width: 120px;font-size:14px;"><span style="color:red;font-size:16px !important">*&nbsp;&nbsp;</span>角色类型</label>
				<div class="layui-input-inline" style="width: 130px;">
					<select name="teacherType" id="teacherType"
						lay-verify="teacherType" autocomplete="off" placeholder="请选择角色">
						<option value="">请选择</option>
						<c:forEach items="${rList}" var="school">
							<option value="${school.roleId}">${school.roleName}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="layui-inline">
				<label class="layui-form-label" style="font-size:14px;width:120px;">手机</label>
				<div class="layui-input-inline" style="width: 130px;">
					<input type="text" id="phone" name="phone" lay-verify="phone"
						autocomplete="off" placeholder="请输入常用手机号" class="layui-input">
				</div>
			</div>
		</div>

		<div class="layui-form-item">

			<div class="layui-inline">
				<label class="layui-form-label" style="font-size:14px;width:120px;">年龄</label>
				<div class="layui-input-inline" style="width: 130px;">
					<input type="number" min="1" max="100" value="30" name="age"
						id="age" lay-verify="age" autocomplete="off" placeholder="请输入年龄"
						class="layui-input">
				</div>
			</div>

			<div class="layui-inline">
				<label class="layui-form-label" style="font-size:14px;width:120px;">性别</label>
				<div class="layui-input-inline" style="width: 130px;">
					<select name="sex" id="sex"
					 lay-verify="sex"
						autocomplete="off" placeholder="请选择性别">
						<option value="1" selected="selected">男</option>
						<option value="2">女</option>
					</select>
				</div>
			</div>
			<div class="layui-inline">
				<label class="layui-form-label" style="width:120px;font-size:14px;">所属校区</label>
				<div class="layui-input-inline" style="width: 130px;">
					<select name="campus" id="campus"
					 lay-verify="campus"
						autocomplete="off" placeholder="请选择校区">
						<c:forEach items="${schoolList}" var="school">
							<option value="${school.getSchoolId()}">${school.getSchoolName()}</option>
						</c:forEach>
					</select>
				</div>
			</div>
		</div>

		<div class="layui-form-item">
			

			<div class="layui-inline">
				<label class="layui-form-label" style="font-size:14px;width:120px;">状态</label>
				<div class="layui-input-inline" style="width: 130px;">
					<select name="teacherStatus" id="teacherStatus"
					 lay-verify="teacherStatus"
						autocomplete="off" placeholder="请选择状态">
						<option value="1">在职</option>
						<!-- <option value="2">离职</option> -->
					</select>
				</div>
			</div>
			<div class="layui-inline">
				<label class="layui-form-label"
					style="font-size: 14px; width: 120px;">邮箱</label>
				<div class="layui-input-inline" style="width: 130px;">
					<input type="email" id="email" name="email" lay-verify="email"
						autocomplete="off" placeholder="请输入常用邮箱" class="layui-input">
				</div>
			</div>
			<div class="layui-inline">
				<label class="layui-form-label" style="width: 120px;font-size:14px;">教学科目</label>
				<div class="layui-input-inline" style="width: 130px;">
					<select name="subject" id="subject"
						lay-verify="" autocomplete="off" placeholder="请选择科目">
						<option value="">请选择</option>
						<c:forEach items="${subjectList}" var="subject">
							<option value="${subject.getSubjectId()}">${subject.getSubjectName()}</option>
						</c:forEach>
					</select>
				</div>
			</div>
		</div>
		<div class="layui-form-item">
			
			
			
           </div>
			<div class="layui-form-item" style="margin-top: 20px;">
				<label class="layui-form-label"
					style="width: 120px; font-size: 14px;">所属年级</label>
				<div class="layui-input-block" style="margin-left: 0">
					<c:forEach items="${gradeList}" var="grade">
						<input type="checkbox" id="grade" name="grade"
							title="${grade.getGradeName()}" value="${grade.getGradeId()}"
							class="layui-input" lay-verify="" placeholder="请选择年级">
					</c:forEach>
				</div>
			</div>
	
	</form>
</body>
<script type="text/javascript" src=".././js/jquery.min.js"></script>
<script src=".././layui/layui.js" charset="utf-8"></script>
<script type="text/javascript">
	layui
			.use(
					[ 'form', 'layedit', 'laydate', 'layer' ],
					function() {
						var form = layui.form(), layer = layui.layer, layedit = layui.layedit, laydate = layui.laydate;

						//创建一个编辑器
						var editIndex = layedit.build('LAY_demo_editor');

						//自定义验证规则
						form.verify({
							userName : [ /^[a-zA-Z]\w{3,15}$/,
											'账户由字母、数字、下划线组成，字母开头，在4-16位之内' ],
									// 							password : [ /(.+){6,12}$/, '密码必须6到12位' ],
									password : [ /^[a-zA-Z]\w{5,15}$/,
											'密码需由字母，数字，下划线组成，字母开头，在6-16位之内' ],
									teacherName : function(value) {
										if (value.length == "") {
											return '姓名不能为空~';
										}
									},
									sex : function(value) {
										if(value.length == "") {
											return "性别不能为空";
										}
									},
									age : function(value) {
										var ageName = /^\+?[1-9][0-9]*$/;
										if (value.length == "") {
											return "年龄不能为空~";
										}else if(value.length>2){
											return "年龄不能超过2位";
										}
										 else if(!ageName.test(value)){
											return '只能输入正整数';
										} 
									},
									accountValidity : function(value) {
										if(value.length == "") {
											return "请选择您的账号有效期";
										}
									},
									phone : function(value) {
										var re = /^1\d{10}$/;
										if (value != "" && !re.test(value)) {
											return '请输入正确的手机号~';
										}
									},
									email : function(value) {
										var email_re = /^(\w-*\.*)+@(\w-?)+(\.\w{2,})+$/;
										if(!email_re.test(value) && value !=""){
											return '请输入正确的邮箱';
										} 
									},
									subject :function(value) {
										if(value.length == "") {
											return "请选择科目";
										}
									},
									grade :function(value) {
										if(value.length == "") {
											return "请选择年级";
										}
									},
									teacherType :function(value) {
										if(value.length == "") {
											return "请选择角色";
										}
									},
									campus :function(value) {
										if(value.length == "") {
											return "请选择校区";
										}
									},
						});

						//监听提交
						form.on('submit(demo1)', function(data) {
							var grades = new Array();
							$('input[name="grade"]:checked').each(function() {
								grades.push($(this).val());//向数组循环添加选中的值
							});
							var grade = grades.join(",");//转为字符串
// 							$('input[name="grade"]').val(grade);
							/* alert($('input[name="grade"]').val());  */
							$.ajax({
								url:'${ctx}/teacher/addteacher.do', 
								type:'post',
								dataType:'json',
								data:{
									userName:$('#userName').val(),
									teacherName:$('#teacherName').val(),
									password:$('#password').val(),
									sex:$('#sex').val(),
									age:$('#age').val(),
									phone:$('#phone').val(),
									email:$('#email').val(),
									teacherStatus:$('#teacherStatus').val(),
									subject:$('#subject').val(),
									grade:grade,
									campus:$('#campus').val(),
									teacherType:$('#teacherType').val(),
									accountValidity:new Date($('#date').val()),
								},
								success:function(res){
									if(res.code == 0) {
										layer.msg("新增成功!");
										window.location.href="${ctx}/teacher/teachermanage.do";
									} else{
										layer.msg(res.msg);
									}
								},
							})
							return false;
						});
					});

</script>
</html>