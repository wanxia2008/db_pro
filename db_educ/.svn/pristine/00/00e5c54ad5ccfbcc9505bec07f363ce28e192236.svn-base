<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>教师修改</title>
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
.a-btn{
height:30px;
line-height:30px;padding:0 18px;position: absolute;
right: 25px;top: 20%;
font-size: 10px;
border-radius: 5px;
background:#e33}
</style>
</head>
<body>
	<form id="myform" class="layui-form" action="" method="post">
		<div class="panels_head">
			<span>教师修改</span>
			<button class="layui-btn layui-btn-normal" lay-submit=""
				lay-filter="demo1"
				style="margin-right: 80px; height: 30px; line-height: 30px; padding: 0 18px; top: 20%; margin-top: 0; font-size: 14px">确认</button>
			<button type="button" class="layui-btn layui-btn-danger"
				onclick="window.location.href='${ctx}/teacher/teachermanage.do?pageNo=${page}'"
				style="height: 30px; line-height: 30px; padding: 0 18px; top: 20%; margin-top: 0; font-size: 14px">返回</button>
		</div>
		<div class="layui-form-item" style="margin-top: 20px;">
			<div class="layui-form-item">
				<div class="layui-inline">
					<label class="layui-form-label" style="width: 120px;font-size:14px;"><span style="color:red;font-size:16px !important">*&nbsp;&nbsp;</span>登录账号</label>
					<div class="layui-input-inline" style="width: 150px;">
						<input type="text" name="userName" id="userName"
							lay-verify="userName" autocomplete="off" placeholder="请输入登录账号"
							value="${teacher.getUserName()}" class="layui-input">
					</div>
				</div>
				<div class="layui-inline">
					<label class="layui-form-label" style="width:120px;font-size: 14px;"><span style="color:red;font-size:16px !important">*&nbsp;&nbsp;</span>登录密码</label>
					<div class="layui-input-inline" style="width: 150px;">
						<input type="password" name="password" id="password"
							lay-verify="password" autocomplete="off" placeholder="请输入登录密码"
							value="${teacher.getPassword()}" class="layui-input">
					</div>
				</div>
				<div class="layui-inline">
					<label class="layui-form-label" style="font-size:14px;width:120px;"><span style="color:red;font-size:16px !important">*&nbsp;&nbsp;</span>用户名</label>
					<div class="layui-input-inline" style="width: 150px;">
						<input type="text" id="teacherName" name="teacherName"
							value="${teacher.getTeacherName()}" lay-verify="teacherName"
							autocomplete="off" placeholder="请输入真实姓名" class="layui-input">
						<input type="hidden" value="${teacher.getTeacherId()}"
							id="teacherId" name="teacherId" maxlength="6">
					</div>
				</div>
			</div>
		</div>

		<div class="layui-form-item" style="margin-top: 20px;">
			<div class="layui-form-item">
			<div class="layui-inline">
					<label class="layui-form-label" style="width:120px;font-size:14px;"><span style="color:red;font-size:16px !important">*&nbsp;&nbsp;</span>账号有效期</label>
					<div class="layui-input-inline" style="width: 150px;">
						<input type="text" name="accountValidity" id="date"
							lay-verify="date" placeholder="点击选择时间" autocomplete="off"
							class="layui-input" value="${myDate}"
							onclick="layui.laydate({elem: this, istime: true, format: 'YYYY-MM-DD'})">
					</div>
				</div>
				<div class="layui-inline">
					<label class="layui-form-label" style="width: 120px;;font-size:14px;"><span style="color:red;font-size:16px !important">*&nbsp;&nbsp;</span>角色类型</label>
					<div class="layui-input-inline" style="width: 150px;">
						<select name="teacherType" id="teacherType" disabled="disabled">
							<option value="">请选择</option>
							<c:forEach items="${rList}" var="r">
								<c:choose>
									<c:when test="${teacher.getTeacherType()==r.roleId}">
										<option selected="selected" value="${r.roleId}">${r.roleName}</option>
									</c:when>
									<c:otherwise>
										<option value="${r.roleId}">${r.roleName}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="layui-inline">
					<label class="layui-form-label" style="font-size:14px;width:120px;">手机</label>
					<div class="layui-input-inline" style="width: 150px;">
						<input type="text" id="phone" name="phone" lay-verify="phone"
							value="${teacher.getPhone()}" autocomplete="off"
							placeholder="请输入常用手机号" class="layui-input">
					</div>
				</div>
			</div>
		</div>
		<div class="layui-form-item" style="margin-top: 20px;">
			<div class="layui-inline">
				<label class="layui-form-label" style="font-size:14px;width:120px;">性别</label>
				<div class="layui-input-inline" style="width: 150px;">
					<select name="sex" id="sex">
						<option value="1"
							<c:choose>
									<c:when test="${teacher.sex==1}">selected="selected"</c:when>
									</c:choose>>男</option>
						<option value="2"
							<c:choose>
									<c:when test="${teacher.sex==2}">selected="selected"</c:when>
									</c:choose>>女</option>
					</select>
				</div>
			</div>
			<div class="layui-inline">
				<label class="layui-form-label" style="font-size:14px;width:120px;">年龄</label>
				<div class="layui-input-inline" style="width: 150px;">
					<input type="number" id="age" name="age" lay-verify="age"
						value="${teacher.getAge()}" autocomplete="off" placeholder="12"
						class="layui-input">
				</div>
			</div>
			<div class="layui-inline">
				<label class="layui-form-label" style="font-size: 14px;width:120px;">邮箱</label>
				<div class="layui-input-inline" style="width: 150px;">
					<input type="email" id="email" name="email" lay-verify="email"
						value="${teacher.getEmail()}" autocomplete="off"
						placeholder="请输入常用邮箱" class="layui-input">
				</div>
			</div>
		</div>

		<div class="layui-form-item" style="margin-top: 20px;">
		<div class="layui-inline">
				<label class="layui-form-label" style="width: 120px;;font-size: 14px;"><span style="color:red;font-size:16px !important">*&nbsp;&nbsp;</span>所属校区</label>
				<div class="layui-input-inline" style="width: 150px;">
					<select name="campus" id="campus">
						<c:forEach items="${schoolList}" var="school">
							<c:choose>
								<c:when test="${teacher.campus == school.getSchoolId()}">
									<option selected="selected" value="${school.getSchoolId()}">${school.getSchoolName()}</option>
								</c:when>
								<c:otherwise>
									<option value="${school.getSchoolId()}">${school.getSchoolName()}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="layui-inline">
				<label class="layui-form-label"
					style="font-size: 14px; width: 120px;"><span style="color:red;font-size:16px !important">*&nbsp;&nbsp;</span>状态</label>
				<div class="layui-input-inline" style="width: 150px;">
					<select name="teacherStatus" id="teacherStatus">
						<option value="1"
							<c:choose>
									<c:when test="${teacher.teacherStatus==1}">selected="selected"</c:when>
									</c:choose>>在职</option>
						<option value="2"
							<c:choose>
									<c:when test="${teacher.teacherStatus==2}">selected="selected"</c:when>
									</c:choose>>离职</option>
					</select>
				</div>
			</div>
			<div class="layui-inline">
				<label class="layui-form-label"
					style="width: 120px;; font-size: 14px;">教学科目</label>
				<div class="layui-input-inline" style="width: 150px;">
					<select name="subject" id="subject">
					   <option value="">请选择</option>
					   <option value="">全部</option>
						<c:forEach items="${subjectList}" var="subject">
							<c:choose>
								<c:when test="${teacher.subject==subject.subjectId}">
									<option selected="selected" value="${subject.getSubjectId()}">${subject.getSubjectName()}</option>
								</c:when>
								<c:otherwise>
									<option value="${subject.getSubjectId()}">${subject.getSubjectName()}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</select>
				</div>
			</div>
		</div>

		<div class="layui-form-item" style="margin-top: 20px;">
			<label class="layui-form-label" style="width:120px; font-size: 14px;"><span style="color:red;font-size:16px !important">*&nbsp;&nbsp;</span>所属年级</label>
			<div class="layui-input-block" style="margin-left:0">
				<c:forEach items="${gradeList}" var="grade">
				<!-- 重新定义变量 -->
				<c:set var="teacherGrade" value="${teacher.getGrade()}" ></c:set>
				<c:set var="gradeId" value=",${grade.getGradeId()},"></c:set>
					<c:choose>
						<c:when
							test="${fn:containsIgnoreCase(teacherGrade,gradeId)}">
							
							<input type="checkbox" name="grade" id="grade"
								value="${grade.getGradeId()}" lay-verify="grade"
								title="${grade.getGradeName()}" checked="checked">
						</c:when>
						<c:otherwise>
							<input type="checkbox" name="grade" id="grade"
								value="${grade.getGradeId()}" lay-verify="grade"
								title="${grade.getGradeName()}">
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</div>
		</div>

		<!-- 		<div class="layui-form-item" style="margin-top: 20px;"> -->
<!-- 			<div class="layui-inline"> -->
<!-- 				<label class="layui-form-label" style="width: auto;font-size: 14px;">所教班级</label> -->
<!-- 				<div class="layui-input-inline" style="margin-top: 8px;"> -->
<%-- 					<c:if test="${teacher.classId==1}">一班</c:if> --%>
<%-- 					<c:if test="${teacher.classId==2}">二班</c:if> --%>
<%-- 					<c:if test="${teacher.classId==3}">三班</c:if> --%>
<%-- 					<c:if test="${teacher.classId==4}">四班</c:if> --%>
<%-- 					<c:if test="${teacher.classId==5}">五班</c:if> --%>
<!-- 				</div> -->
<!-- 			</div> -->

<!-- 		</div> -->
		<%-- <div class="layui-form-item" style="margin-top: 20px;">
			<div class="layui-inline">
				<label class="layui-form-label" style="width: auto;font-size: 14px;">创建时间</label>
				<div class="layui-input-inline" style="margin-top: 8px;">
					<fmt:formatDate value="${teacher.createTime}"
						pattern="yyyy-MM-dd HH:mm:ss" />
				</div>
			</div>
			<div class="layui-inline">
				<label class="layui-form-label" style="width: auto;font-size: 14px;">修改时间</label>
				<div class="layui-input-inline" style="margin-top: 8px;">
					<fmt:formatDate value="${teacher.updateTime}"
						pattern="yyyy-MM-dd HH:mm:ss" />
				</div>
			</div>
		</div> --%>
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
// 									password : [ /^[a-zA-Z]\w{5,15}$/,
// 											'密码需由字母，数字，下划线组成，字母开头，在6-16位之内' ],
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
										 if(value != ""  && !email_re.test(value)){
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
 							$('input[name="grade"]').val(grade);
// 							 alert($('input[name="grade"]').val());  
							$.ajax({
								url:'${ctx}/teacher/updateteacher.do', 
								type:'post',
								dataType:'json',
								data:{
									teacherId:$('#teacherId').val(),
									userName:$('#userName').val(),
									password:$('#password').val(),
									teacherName:$('#teacherName').val(),
									sex:$('#sex').val(),
									age:$('#age').val(),
									phone:$('#phone').val(),
									email:$('#email').val(),
									teacherStatus:$('#teacherStatus').val(),
									subject:$('#subject').val(),
									grade:$('#grade').val(),
									campus:$('#campus').val(),
									teacherType:$('#teacherType').val(),
									accountValidity:new Date($('#date').val()),
								},
								success:function(res){
									if(res.code == 0) {
										layer.msg("编辑成功!");
										setTimeout(function(){
											window.location.href="${ctx}/teacher/teachermanage.do?pageNo="+${page};
										},1000);
									} else{
										layer.msg(res.msg);
										return;
									}
								},
							})
							return false;
						});

					});
</script>
</html>