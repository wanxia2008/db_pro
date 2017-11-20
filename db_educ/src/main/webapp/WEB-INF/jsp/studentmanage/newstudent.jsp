<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>新增学员</title>
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

.layui-form-checked span, .layui-form-checked:hover span {
	background-color: #008bca;
}

ul li {
	margin-top: 5px;
	padding: 5px;
}
.a-btn{height:30px;line-height:30px;padding:0 18px;position: absolute;right: 25px;top: 20%;font-size: 10px;border-radius: 5px;background:#e33}
</style>
</head>
<body>
	<form id="myform" class="layui-form" action="addstudent.do"
		method="post">
		<div class="panels_head">
			<span>学员新增</span>
			<button class="layui-btn layui-btn-normal" lay-submit="" lay-filter="demo1" style="margin-right: 80px;height:30px;line-height:30px;padding:0 18px;top:20%;margin-top:0;font-size:14px">确认</button>
<%-- 				<button type="button" class="layui-btn layui-btn-danger" onclick="window.location.href='${ctx}/student/studentEnlistList.do'" --%>
<!-- 						style=" height: 30px; line-height: 30px; padding: 0 18px; top: 20%; margin-top: 0;font-size:14px">返回</button> -->
			<button type="button" class="layui-btn layui-btn-danger" onclick="history.go(-1)"
				style="height:30px;line-height:30px;padding:0 18px;top:20%;margin-top:0;font-size:14px">返回</button>
		</div>
		<div class="layui-form-item" style="margin-top: 20px;">
			<div class="layui-inline">
				<label class="layui-form-label" style="width: 120px;font-size: 14px;"><span style="color:red;font-size:16px !important">*&nbsp;&nbsp;</span>登录账号</label>
				<div class="layui-input-inline" style="width: 130px;">
					<input type="text" name="userName" id="userName" lay-verify="userName"
						autocomplete="off" placeholder="请输入登录账号" class="layui-input">
				</div>
			</div>
			<div class="layui-inline">
				<label class="layui-form-label" style="width: 120px;font-size: 14px;"><span style="color:red;font-size:16px !important">*&nbsp;&nbsp;</span>登录密码</label>
				<div class="layui-input-inline" style="width: 130px;">
					<input type="text" name="password" id="password" lay-verify="password"
						autocomplete="off" placeholder="请输入登录密码"  onfocus="this.type='password'" class="layui-input">
				</div>
			</div>
			<div class="layui-inline">
				<label class="layui-form-label" style="font-size: 14px;width:120px;"><span style="color:red;font-size:16px !important">*&nbsp;&nbsp;</span>姓名</label>
				<div class="layui-input-inline" style="width: 130px;">
					<input type="text" name="studentName" id="studentName" lay-verify="studentName"
						autocomplete="off" placeholder="请输入姓名" class="layui-input" maxlength='6'>
				</div>
			</div>
				<div class="layui-inline">
				<label class="layui-form-label" style="font-size: 14px;width:120px;"><span style="color:red;font-size:16px !important">*&nbsp;&nbsp;</span>性别</label>
				<div class="layui-input-inline" style="width: 130px;">
					<select name="sex" id="sex"
					    lay-verify="sex"
						autocomplete="off" placeholder="请选择性别">
					    <option value="">请选择</option>
						<option value="1">男</option>
						<option value="2">女</option>
					</select>
				</div>
			</div>
<!-- 			<div class="layui-inline"> -->
<!-- 				<label class="layui-form-label">状态</label> -->
<!-- 				<div class="layui-input-inline" style="width: 130px;"> -->
<!-- 					<select name="studentStatus" id="studentStatus" -->
<!-- 					lay-verify="studentStatus" -->
<!-- 						autocomplete="off" placeholder="请选择状态"> -->
<!-- 					    <option value="">--请选择--</option> -->
<!-- 						<option value="1">预报名</option> -->
<!-- 						<option value="2">已交学费</option> -->
<!-- 						<option value="3">未分班</option> -->
<!-- 						<option value="4">已分班</option> -->
<!-- 						<option value="5">入学测试完成</option> -->
<!-- 						<option value="6">完成课程</option> -->
<!-- 					</select> -->
<!-- 				</div> -->
<!-- 			</div> -->
		</div>
		
		<div class="layui-form-item" style="margin-top: 20px;">
			<div class="layui-inline">
				<label class="layui-form-label"
					style="font-size: 14px; width: 120px;"><span
					style="color: red; font-size: 16px !important">*&nbsp;&nbsp;</span>年龄</label>
				<div class="layui-input-inline" style="width: 130px;">
					<input type="number" min="1" max="100" value="10" name="age"
						id="age" lay-verify="age" autocomplete="off" placeholder="请输入年龄"
						class="layui-input">
				</div>
			</div>
			<div class="layui-inline">
				<label class="layui-form-label"
					style="width: 120px; font-size: 14px; width: 10ppx;">学员电话</label>
				<div class="layui-input-inline" style="width: 130px;">
					<input type="text" name="phone" id="phone" lay-verify="phone"
						autocomplete="off" placeholder="请输入联系方式" class="layui-input">
				</div>
			</div>
			<div class="layui-inline">
				<label class="layui-form-label" style="font-size: 14px;width:120px;">父亲</label>
				<div class="layui-input-inline" style="width: 130px;">
					<input type="text" hidden="" name="custodian1Relationship" id="custodian1Relationship"
						value="父亲" /> <input type="text" name="custodian1Name" id="custodian1Name"
						lay-verify="custodian1Name" autocomplete="off" placeholder="请输入姓名"
						class="layui-input" maxlength='6'>
				</div>
			</div>
			<div class="layui-inline">
				<label class="layui-form-label" style="width: 120px;font-size: 14px;">联系电话</label>
				<div class="layui-input-inline" style="width: 130px;">
					<input type="text" name="custodian1Phone" id="custodian1Phone"
						lay-verify="custodian1Phone" autocomplete="off"
						placeholder="请输入联系电话" class="layui-input">
				</div>
			</div>
		</div>
		<div class="layui-form-item">
			<div class="layui-inline">
				<label class="layui-form-label" style="font-size: 14px;width:120px;">母亲</label>
				<div class="layui-input-inline" style="width: 130px;">
					<input type="text" hidden="" name="custodian2Relationship" id="custodian2Relationship"
						value="母亲" /> <input type="text" name="custodian2Name" id="custodian2Name"
						lay-verify="custodian2Name" autocomplete="off" placeholder="请输入姓名"
						class="layui-input" maxlength="6">
				</div>
			</div>
			<div class="layui-inline">
				<label class="layui-form-label" style="width: 120px;font-size: 14px;">联系电话</label>
				<div class="layui-input-inline" style="width: 130px;">
					<input type="text" name="custodian2Phone" id="custodian2Phone"
						lay-verify="custodian2Phone" autocomplete="off"
						placeholder="请输入联系电话" class="layui-input">
				</div>
			</div>
			<div class="layui-inline">
				<label class="layui-form-label" style="width: 120px;font-size: 14px;width:10ppx;">就读学校</label>
				<div class="layui-input-inline" style="width: 130px;">
					<input type="text" name="attendSchool" id="attendSchool"
						lay-verify="attendSchool" autocomplete="off" placeholder="请输入就读学校"
						class="layui-input" maxlength="10">
				</div>
			</div>
			<div class="layui-inline">
				<label class="layui-form-label" style="width: 120px;font-size: 14px;width:10ppx;">意向学校</label>
				<div class="layui-input-inline" style="width: 130px;">
					<input type="text" name="intentionalSchool" id="intentionalSchool"
						lay-verify="intentionalSchool" autocomplete="off"
						placeholder="请输入意向学校" class="layui-input" maxlength="10">
				</div>
			</div>
		</div>
		<div class="panels_head">
			<span>选择课程</span>
		</div>
		<ul class="newstudentmesg_ul" style="position: relative;">
			<li class="newstudentmesg_li">
				<div class="layui-form-item">
					<div class="layui-inline">
						<label class="layui-form-label" style="font-size:14px;"><span style="color:red;font-size:16px !important">*&nbsp;&nbsp;</span>学期</label>
						<div class="layui-input-inline" style="width: 130px;">
							<select name="year" id="year">
								<c:forEach items="${syList}" var="sy">
							     	<option value="${sy.id}">${sy.year}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="layui-inline">
						<label class="layui-form-label" style="font-size:14px;"><span style="color:red;font-size:16px !important">*&nbsp;&nbsp;</span>年级</label>
						<div class="layui-input-inline" style="width: 130px;">
							<select name="grade" id="grade"
							lay-verify="grade" autocomplete="off" placeholder="请选择年级">
								<option value="">选择年级</option>
								<c:forEach items="${gradeList}" var="grade">
									<option value="${grade.getGradeId()}">${grade.getGradeName()}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="layui-inline">
						<label class="layui-form-label" style="font-size:14px;"><span style="color:red;font-size:16px !important">*&nbsp;&nbsp;</span>类型</label>
						<div class="layui-input-inline" style="width: 130px;">
							<select name="choiceType" id="choiceType"
							lay-verify="choiceType"
						autocomplete="off" placeholder="请选择季节">
								<option value="">选择类型</option>
								<c:forEach items="${seasonList}" var="map">
									<option value="${map.getSeasonId()}">${map.getSeasonName()}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="layui-inline">
						<label class="layui-form-label" style="font-size:14px;"><span style="color:red;font-size:16px !important">*&nbsp;&nbsp;</span>校区</label>
						<div class="layui-input-inline" style="width: 130px;">
							<select name="schoolId" id="schoolId"
							lay-verify="schoolId"
						autocomplete="off" placeholder="请选择校区">
								<option value="">选择校区</option>
								<c:forEach items="${szList}" var="map">
									<option value="${map.schoolId}">${map.schoolName}</option>
								</c:forEach>
							</select>
						</div>
					</div>
				</div>
				
				<div class="layui-form-item">
					<label class="layui-form-label" style="font-size: 14px;"><span
						style="color: red; font-size: 16px !important">*&nbsp;&nbsp;</span>科目</label>
					<div class="layui-input-block">
						<c:forEach items="${subjectList}" var="subject">
							<input type="checkbox" name="subjectType" id="subjectType"
								value="${subject.getSubjectId()}"
								title="${subject.getSubjectName()}" lay-verify="subjectType"
								autocomplete="off" placeholder="请选择科目" class="layui-input">
						</c:forEach>
					</div>
				</div>
			</li>
		</ul>
		<script type="text/html" id="template">
				<li class="newstudentmesg_li">
					<div class="layui-form-item">
						<div class="layui-inline">
							<label class="layui-form-label" style="font-size: 14px;">学期</label>
							<div class="layui-input-inline" style="width: 80px;">
								<select name="quiz">
									<option value="1">2017</option>
									<option value="2">2016</option>
									<option value="2">2015</option>
								</select>
							</div>
						</div>
					</div>
					<div class="layui-form-item">
						<div class="layui-inline">
							<label class="layui-form-label"></label>
							<div class="layui-input-inline" style="width: 80px;">
								<select name="quiz">
									<option value="1">春季</option>
									<option value="2">秋季</option>
									<option value="2">寒假</option>
									<option value="2">暑假</option>
								</select>
							</div>
						</div>
					</div>
					<div class="layui-form-item">
						<div class="layui-inline">
							<label class="layui-form-label" style="font-size: 14px;">年级</label>
							<div class="layui-input-inline" style="width: 80px;">
								<select name="quiz">
									<option value="1">六年级</option>
									<option value="2">五年级</option>
									<option value="2">四年级</option>
									<option value="2">三年级</option>
								</select>
							</div>
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label" style="font-size: 14px;">科目</label>
						<div class="layui-input-block">
							<input type="checkbox" name="like[write]" title="语文">
							<input type="checkbox" name="like[read]" title="数学" checked="">
							<input type="checkbox" name="like[game]" title="英语">
						</div>
					</div>
					<span class="icon-plus"></span>
				</li>
			</script>
	</form>
</body>
<script type="text/javascript" src=".././js/jquery.min.js"></script>
<script src=".././layui/layui.js" charset="utf-8"></script>
<script type="text/javascript">
	layui
			.use(
					[ 'form', 'layedit', 'laydate' ],
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
							studentName : function(value) {
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
							custodian1Phone : function(value) {
								var re = /^1\d{10}$/;
								if (value != "" && !re.test(value)) {
									return '请输入正确的手机号~';
								}
							},
							custodian2Phone : function(value) {
								var re = /^1\d{10}$/;
								if (value != "" && !re.test(value)) {
									return '请输入正确的手机号~';
								}
							},
							phone : function(value) {
								var re = /^1\d{10}$/;
								if (value != "" && !re.test(value)) {
									return '请输入正确的手机号~';
								}
							},
							
							 choiceType :function(value) {
								if(value.length == "") {
									return "请选择类型";
								}
							},
							subjectType :function(value) {
								if(value.length == "") {
									return "请选择季节";
								}
							}, 
							studentStatus :function(value) {
								if(value.length == "") {
									return "请选择状态";
								}
							}, 
							grade :function(value) {
								if(value.length == "") {
									return "请选择年级";
								}
							}, 
							schoolId :function(value) {
								if(value.length == "") {
									return "请选择校区";
								}
							}, 
						});
						//监听提交
						form.on('submit(demo1)', function(data) {
							var subjects = new Array();
							$('input[name="subjectType"]:checked').each(function(){
								subjects.push($(this).val());//向数组循环添加选中的值
							});
							var subject = subjects.join(",");//转为字符串
// 							$('input[name="subjectType"]').val(subject);
							/* alert($('input[name="subjectType"]').val()); */
							$.ajax({
								url:'addstudent.do',
								type:'post',
								dataType:'json',
								data:{
									userName:$('#userName').val(),
									studentName:$('#studentName').val(),
									password:$('#password').val(),
									year:$('#year').val(),
									sex:$('#sex').val(),
									age:$('#age').val(),
									grade:$('#grade').val(),
									classNo:$('#classNo').val(),
									subjectType:subject,
									choiceType:$('#choiceType').val(),
									studentStatus:$('#studentStatus').val(),
									custodian1Relationship:$('#custodian1Relationship').val(),
									custodian1Name:$('#custodian1Name').val(),
									custodian1Phone:$('#custodian1Phone').val(),
									custodian2Relationship:$('#custodian2Relationship').val(),
									custodian2Name:$('#custodian2Name').val(),
									custodian2Phone:$('#custodian2Phone').val(),
									schoolId:$('#schoolId').val(),
									phone:$('#phone').val(),
									attendSchool:$('#attendSchool').val(),
									intentionalSchool:$('#intentionalSchool').val(),
								},
								success:function(res){
									if(res.code == 0) {
										layer.msg("新增成功!");
										window.location.href="studentEnlistList.do";
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