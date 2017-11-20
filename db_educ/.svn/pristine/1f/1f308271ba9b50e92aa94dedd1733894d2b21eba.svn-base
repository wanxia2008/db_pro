<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>修改学员</title>
<link rel="stylesheet" href=".././layui/css/layui.css" />
<link rel="stylesheet" href=".././css/bootstrap.min.css" />
<link rel="stylesheet" href=".././css/lecturenote/style.css" />
<link rel="stylesheet" href=".././css/font-awesome.min.css" />
<script type="text/javascript" src=".././js/jquery.min.js"></script>
<script type="text/javascript" src=".././js/bootstrap.min.js"></script>
<style type="text/css">
.layui-form-checkbox {
	height: 26px;
	line-height: 24px;
}

.layui-form-checkbox span {
	font-size: 7px;
}

.layui-form-checked span, .layui-form-checked:hover span {
	background-color: #008bca;
}

ul li {
	margin-top: 5px;
	padding: 5px;
}

.a-btn {
	height: 30px;
	line-height: 30px;
	padding: 0 18px;
	position: absolute;
	right: 25px;
	top: 20%;
	font-size: 10px;
	border-radius: 5px;
	background: #e33
}
.pagelist a{padding:5px 18px;border:1px solid #e1e2e3;}
.pagelist .current{background:#008bca;color:#fff}
.pagelist a:hover{background:#008bca;color:#fff}
#pages{height:50px;line-height:50px;}
.icon-star,.icon-star-empty{color:#333 !important}
ol, ul{margin-bottom:0;}
</style>
</head>
<body>
	<form id="myform" class="layui-form" action="updatestudent.do"
		method="post">
		<input type="number" hidden="" name="studentId" id="studentId"
			value="${students.getStudentId()}">
			
		<div class="panels_head">
			<span>学员查看</span>
		<%--		<button class="layui-btn layui-btn-normal" lay-submit=""
				lay-filter="demo1"
				style="margin-right: 80px; height: 30px; line-height: 30px; padding: 0 18px; top: 20%; margin-top: 0; font-size: 14px">确认</button>   	<div class="layui-form-item">  --%>
			<button type="button" class="layui-btn layui-btn-danger"
				onclick="window.location.href='${ctx}/student/studentmanage.do?pageNo=${page}'"
				style="height: 30px; line-height: 30px; padding: 0 18px; top: 20%; margin-top: 0; font-size: 14px">返回</button>
		</div>
		<div class="layui-form-item" style="margin-top: 20px;">
			 
			<%-- <div class="layui-inline">
				<label class="layui-form-label">登录密码</label>
				<div class="layui-input-inline" style="width: 100px;">
					<input type="password" name="password" id="password" lay-verify="password"
						autocomplete="off" placeholder="请输入登录密码" value="${students.getPassword()}" class="layui-input">
				</div>
			</div> --%>
			<div class="layui-inline">
				<label class="layui-form-label"
					style="font-size: 14px; width: 100px;"><span style="color:red;font-size:16px !important">*&nbsp;&nbsp;</span>姓名</label>
				<div class="layui-input-inline" style="width: 130px;">
					<input type="text" name="studentName" id="studentName" readonly="true" 
						lay-verify="studentName" autocomplete="off" placeholder="请输入姓名"
						value="${students.getStudentName()}" class="layui-input" maxlength="6">
				</div>
			</div>
			<div class="layui-inline">
				<label class="layui-form-label"
					style="font-size: 14px; width: 100px;"><span style="color:red;font-size:16px !important">*&nbsp;&nbsp;</span>状态</label>
				<div class="layui-input-inline" style="width: 130px;">
					<select name="studentStatus" id="studentStatus" disabled="disabled" >
						<option value="1"
							<c:choose>
									<c:when test="${students.studentStatus==1}">selected="selected"</c:when>
									</c:choose>>预报名</option>
						<option value="2"
							<c:choose>
								<c:when test="${students.studentStatus==2}">selected="selected"</c:when></c:choose>>未交学费
						</option>
						<option value="3"
							<c:choose>
								<c:when test="${students.studentStatus==3}">selected="selected"</c:when></c:choose>>未分班
						</option>
						<option value="4"
							<c:choose>
								<c:when test="${students.studentStatus==4}">selected="selected"</c:when></c:choose>>已分班
						</option>
						<option value="5"
							<c:choose>
								<c:when test="${students.studentStatus==5}">selected="selected"</c:when></c:choose>>完成课程
						</option>
					</select>
				</div>
			</div>
			<div class="layui-inline">
				<label class="layui-form-label"
					style="font-size: 14px; width: 100px;"><span style="color:red;font-size:16px !important">*&nbsp;&nbsp;</span>性别</label>
				<div class="layui-input-inline" style="width: 130px;">
					<select name="sex" id="sex" disabled="disabled" >
						<option value="1"
							<c:choose>
									<c:when test="${students.sex==1}">selected="selected"</c:when>
									</c:choose>>男</option>
						<option value="2"
							<c:choose>
								<c:when test="${students.sex==2}">selected="selected"</c:when></c:choose>>女
						</option>
					</select>
				</div>
			</div>
			<div class="layui-inline">
				<label class="layui-form-label"
					style="font-size: 14px; width: 100px;"><span style="color:red;font-size:16px !important">*&nbsp;&nbsp;</span>年龄</label>
				<div class="layui-input-inline" style="width: 130px;">
					<input type="text" name="age" id="age" lay-verify="age" readonly="true" 
						autocomplete="off" placeholder="12" value="${students.getAge()}"
						class="layui-input">
				</div>
			</div>
		</div>
		<div class="layui-form-item">

			
			
			
			<div class="layui-inline">
				<label class="layui-form-label"
					style="font-size: 14px; width: 100px;">父亲</label>
				<div class="layui-input-inline" style="width: 130px;">
					<input type="text" hidden="" id=custodian1Relationship readonly="true" 
						" name="custodian1Relationship" value="父子" /> <input type="text"
						name="custodian1Name" id="custodian1Name"
						lay-verify="custodian1Name" autocomplete="off" placeholder="请输入姓名"
						class="layui-input" value="${students.getCustodian1Name()}" maxlength="6">
				</div>
			</div>
			<div class="layui-inline">
				<label class="layui-form-label"
					style="font-size: 14px; width: 100px;">联系电话</label>
				<div class="layui-input-inline" style="width: 130px;">
					<input type="text" name="custodian1Phone" id="custodian1Phone" readonly="true" 
						lay-verify="custodian1Phone" autocomplete="off"
						placeholder="***********" value="${students.getCustodian1Phone()}"
						class="layui-input">
				</div>
			</div>
			<div class="layui-inline">
				<label class="layui-form-label"
					style="font-size: 14px; width: 100px;">母亲</label>
				<div class="layui-input-inline" style="width: 130px;">
					<input type="text" hidden="" name="custodian2Relationship" readonly="true" 
						id="custodian2Relationship" value="母子" /> <input type="text"
						name="custodian2Name" id="custodian2Name"
						lay-verify="custodian2Name" autocomplete="off" placeholder="请输入姓名"
						class="layui-input" value="${students.getCustodian2Name()}" maxlength="6">
				</div>
			</div>
			<div class="layui-inline">
				<label class="layui-form-label"
					style="font-size: 14px; width: 100px;">联系电话</label>
				<div class="layui-input-inline" style="width: 130px;">
					<input type="text" name="custodian2Phone" id="custodian2Phone" readonly="true" 
						lay-verify="custodian2Phone" autocomplete="off"
						placeholder="***********" value="${students.getCustodian2Phone()}"
						class="layui-input">
				</div>
			</div>
		</div>
		
		<div class="layui-form-item">
		<div class="layui-inline">
				<label class="layui-form-label"
					style="font-size: 14px; width: 100px;">就读学校</label>
				<div class="layui-input-inline" style="width: 130px;">
					<input type="text" name="attendSchool" id="attendSchool" readonly="true" 
						disabled="disabled" value="${students.attendSchool}"
						placeholder="请输入就读学校" class="layui-input" maxlength="10">
				</div>
			</div>
		<div class="layui-inline">
				<label class="layui-form-label"
					style="font-size: 14px; width: 100px;">意向学校</label>
				<div class="layui-input-inline" style="width: 130px;">
					<input type="text" name="intentionalSchool" id="intentionalSchool" readonly="true" 
						value="${students.intentionalSchool}" disabled="disabled"
						placeholder="请输入意向学校" class="layui-input" maxlength="10">
				</div>
			</div>
		<div class="layui-inline">
				<label class="layui-form-label"
					style="font-size: 14px; width: 100px;">学员电话</label>
				<div class="layui-input-inline" style="width: 130px;">
					<input type="text" name="phone" id="phone" readonly="true" 
						value="${students.phone}" disabled="disabled"
						placeholder="请输入联系方式" class="layui-input">
				</div>
			</div>
      <div class="layui-inline">
				<label class="layui-form-label" style="font-size: 14px; width: 100px;">登录账号</label>
				<div class="layui-input-inline" style="width: 130px;">
					<input type="text" name="userName" disabled="disabled" id="userName" lay-verify="userName" readonly="true" 
						autocomplete="off" placeholder="请输入登录账号" value="${students.getUserName()}" class="layui-input">
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
						<label class="layui-form-label"
							style="font-size: 14px; width: 100px;"><span style="color:red;font-size:16px !important">*&nbsp;&nbsp;</span>学期</label>
						<div class="layui-input-inline" style="width: 130px;">
							<select name="year" id="year"disabled="disabled" >
								<c:forEach items="${syList}" var="sy">
									<c:choose>
										<c:when test="${students.year==sy.id}">
											<option selected="selected" value="${sy.id}">${sy.year}</option>
										</c:when>
										<c:otherwise>
											<option value="${sy.id}">${sy.year}</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="layui-inline">
						<label class="layui-form-label"
							style="font-size: 14px; width: 100px;"><span style="color:red;font-size:16px !important">*&nbsp;&nbsp;</span>年级</label>
						<div class="layui-input-inline" style="width: 130px;">
							<select name="grade" id="grade" disabled="disabled" >
								<option value="">选择年级</option>
								<c:forEach items="${gradeList}" var="grade">
									<c:choose>
										<c:when test="${students.getGrade()==grade.getGradeId()}">
											<option selected="selected" value="${grade.getGradeId()}">${grade.getGradeName()}</option>
										</c:when>
										<c:otherwise>
											<option value="${grade.getGradeId()}">${grade.getGradeName()}</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="layui-inline">
						<label class="layui-form-label"
							style="font-size: 14px; width: 100px;"><span style="color:red;font-size:16px !important">*&nbsp;&nbsp;</span>类型</label>
						<div class="layui-input-inline" style="width: 130px;">
							<select name="choiceType" id="choiceType" disabled="disabled" >
								<option value="1"
									<c:choose>
									<c:when test="${students.choiceType==1}">selected="selected"</c:when>
									</c:choose>>春季</option>
								<option value="2"
									<c:choose>
								<c:when test="${students.choiceType==2}">selected="selected"</c:when></c:choose>>秋季
								</option>
								<option value="3"
									<c:choose>
								<c:when test="${students.choiceType==3}">selected="selected"</c:when></c:choose>>寒假
								</option>
								<option value="4"
									<c:choose>
								<c:when test="${students.choiceType==4}">selected="selected"</c:when></c:choose>>暑假1
								</option>
								<option value="5"
									<c:choose>
								<c:when test="${students.choiceType==5}">selected="selected"</c:when></c:choose>>暑假2
								</option>
								<%-- <c:forEach items="${seasonList}" var="map">
									<option value="${map.getSeasonId()}">${map.getSeasonName()}</option>
								</c:forEach> --%>
							</select>
						</div>
					</div>
					<div class="layui-inline">
						<label class="layui-form-label"
							style="font-size: 14px; width: 100px;"><span style="color:red;font-size:16px !important">*&nbsp;&nbsp;</span>校区</label>
						<div class="layui-input-inline" style="width: 130px;">
							<select name="schoolId" id="schoolId" lay-verify="schoolId" disabled="disabled" >
								<option value="">--请选择--</option>
								<c:forEach items="${szList}" var="s">
									<option value="${s.schoolId}"
										<c:if test="${students.schoolId eq s.schoolId}">selected="selected"</c:if>>${s.schoolName}</option>
								</c:forEach>
							</select>
						</div>
					</div>
				</div>
			</li>
		</ul>
		<div class="panels_head">
			<span>历史记录</span>
		</div>
<!-- 		<div class="layui-form-item"> -->
<!-- 					<label class="layui-form-label" -->
<!-- 						style="font-size: 14px; width: 100px;"><span style="color:red;font-size:16px !important">*&nbsp;&nbsp;</span>科目</label> -->
<!-- 					<div class="layui-input-block"> -->
<%-- 						<c:forEach items="${subjectList}" var="subject"> --%>
<%-- 							<c:choose> --%>
<%-- 								<c:when --%>
<%-- 									test="${fn:contains(students.getSubjectType(),subject.getSubjectId())}"> --%>
<!-- 									<input type="checkbox" name="subjectType" id="subjectType" -->
<%-- 										value="${subject.getSubjectId()}" lay-verify="subjectType" --%>
<%-- 										title="${subject.getSubjectName()}" checked="checked"> --%>
<%-- 								</c:when> --%>
<%-- 								<c:otherwise> --%>
<!-- 									<input type="checkbox" name="subjectType" id="subjectType" -->
<%-- 										value="${subject.getSubjectId()}" lay-verify="subjectType" --%>
<%-- 										title="${subject.getSubjectName()}"> --%>
<%-- 								</c:otherwise> --%>
<%-- 							</c:choose> --%>
<%-- 						</c:forEach> --%>
<!-- 					</div> -->
<!-- 				</div> -->
           <c:choose>
			<c:when test="${trList==null}">
				<p class="no_data_p" style="font-size:14px;margin-top: 30px;">~暂无数据~</p>
			</c:when>
			<c:otherwise>
				<table class="layui-table" style="width: 98%; margin: 0 auto;margin-top:13px;">
					<thead>
						<tr>
					     	<th>年份</th>
					     	<th>季节</th>
							<th>科目</th>
							<th>年级</th>
							<th>班级</th>
							<th>状态</th>
							<th>时间</th>
				<!-- 			<th>操作</th>  -->
						</tr>
					</thead>
					<tbody id="tbody">
						<c:forEach items="${trList}" var="s">
								<tr>
									<td>${s.schoolYear.year}</td>
									<td>${s.seasonType2.seasonName}</td>
									<td>${s.subjects.subjectName}</td>
									<td>${s.grades.getGradeName()}</td>
									<td>${s.classNo.className}</td>
									<td>已分班</td>
									<td><fmt:formatDate value="${s.createTime}"
										pattern="yyyy-MM-dd"></fmt:formatDate></td>
	 	<!-- 						<td>  -->
<!-- 								<span class="icon-edit" data-toggle="modal" data-target="#myModal" 
<%-- 							       id="${s.id}" data-year="${s.schoolYear.year}" data-grade="${s.grades.getGradeName()}	" --%>
<%-- 							       data-subject="${s.subjects.subjectName}" data-type="${s.seasonType2.seasonName}" --%>
<%-- 							       data-classNo="${s.classNo.className}" --%>
<!-- 							        style="font-size: 14px; color: #008bca; cursor: pointer;">修改班级</span>&nbsp;  -->
							        
					<!--			<a class="button border-red" href="javascript:void(0)"
									onclick="deleteone(<c:out value="${s.id}"></c:out>,<c:out value="${s.classNo.classId}"></c:out>)"> <span
										class="icon-remove-sign"
										style="font-size: 14px; color: #ff5722;"> 删除</span>
								</a></td>       -->
							</tr>
						</c:forEach>
						<tr id="pages">
							<td colspan="14">
								<div class="pagelist">
									<a href="javascript:;" onclick="goPageAction(1);">首页</a>
									<c:choose>
										<c:when test="${page == 1}">
											<a href="javascript:;">上一页</a>
										</c:when>
										<c:otherwise>
											<a href="javascript:;" onclick="goPageAction(${page - 1});">上一页
											</a>
											&nbsp;
										</c:otherwise>
									</c:choose>

									<c:forEach
										begin="${page>2?(page+2>totalPages?(totalPages>5?totalPages-4:1):page-2):1}"
										end="${page>3?page+2>totalPages?totalPages:page+2:totalPages>5?5:totalPages}"
										var="p">
										<c:choose>
											<c:when test="${page == p}">
												<a href="javascript:;" onclick="goPageAction(${p});"
													class="current">${p}</a>
											</c:when>
											<c:otherwise>
												<a href="javascript:;" onclick="goPageAction(${p});">${p}</a>
											</c:otherwise>
										</c:choose>
									</c:forEach>
									<c:choose>
										<c:when test="${page == totalPages||totalPages==0}">
											<a href="javascript:;">下一页</a>
										</c:when>
										<c:otherwise>
											<a href="javascript:;" onclick="goPageAction(${page + 1});">下一页
											</a>
											&nbsp;
										</c:otherwise>
									</c:choose>
									<a href="javascript:;" onclick="goPageAction(${totalPages});">末页</a>
									&nbsp;总共&nbsp; ${totalPages} &nbsp;页

								</div>
							</td>
						</tr>
					</tbody>
				</table>
			</c:otherwise>
		</c:choose>
		
	</form>
	<!--模态框-->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog" style="width:30%;height: 100%">
			<div class="modal-content">
			<div class="modal-header">
			        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			        <h4 class="modal-title" id="myModalLabel">修改班级</h4>
                     </div>
				<div class="modal-body">
					<form class="layui-form" method="post">
						<div class="layui-form-item" style="margin-top: 10px;">
							<div class="layui-inline" id="classNo">
								<label class="layui-form-label" style="width: 120px;">班级</label>
								<div class="input-group" style="float: left; width: 140px;">
									<select name="classId" id="classId" lay-verify="classId">
										<option value="请选择班级"></option>
										<c:forEach items="${cnList}" var="cn">
											<c:choose>
												<c:when test="${classNo==cn.classId}">
													<option selected="selected" value="${cn.classId}">${cn.className}</option>
												</c:when>
												<c:otherwise>
													<option value="${cn.classId}">${cn.className}</option>
												</c:otherwise>
											</c:choose>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>
						<input type="number" hidden="" name="id" id="id">
					</form>
				</div>
				<div class="modal-footer">
				      <button type="button" class="btn btn-primary" id="confirm_update" style="height:30px;line-height:30px;padding:0 18px;">确定</button>
					<button type="button" class="btn btn-default" data-dismiss="modal" style="height:30px;line-height:30px;padding:0 18px;">关闭</button>
				</div>
			</div>
		</div>
	</div>
	<!--模态框结束-->
</body>
<script type="text/javascript" src=".././js/jquery.min.js"></script>
<script src=".././layui/layui.js" charset="utf-8"></script>
<script type="text/javascript">
	layui.use([ 'form', 'layedit', 'laydate', 'layer' ],
					function() {
						var form = layui.form(), layer = layui.layer, layedit = layui.layedit, laydate = layui.laydate;

						//创建一个编辑器
						var editIndex = layedit.build('LAY_demo_editor');

						//自定义验证规则
						form.verify({
							// 							userName : function(value) {
							// 								if (value.length == "") {
							// 									return '';
							// 								}
							// 							},
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
							age : function(value) {
								if (value.length == "") {
									return "年龄不能为空~";
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
							schoolId : function(value) {
								if (value.length == "") {
									return "请输入校区";
								}
							},
						});
						//监听提交
						form.on('submit(demo1)',
										function(data) {
											var subjects = new Array();
											$('input[name="subjectType"]:checked')
													.each(
															function() {
																subjects.push($(
																				this).val());//向数组循环添加选中的值
															});
											var subject = subjects.join(",");//转为字符串
											$('input[name="subjectType"]').val(
													subject);
											/* 	alert($('input[name="subjectType"]').val()); */
											$.ajax({
														url : 'updatestudent.do',
														type : 'post',
														dataType : 'json',
														data : {
															studentId : $('#studentId').val(),
															userName : $('#userName').val(),
															studentName : $('#studentName').val(),
															password : $('#password').val(),
															year : $('#year').val(),
															sex : $('#sex').val(),
															age : $('#age').val(),
															grade : $('#grade').val(),
															classNo : $('#classNo').val(),
															subjectType : $(
																	'#subjectType').val(),
															choiceType : $(
																	'#choiceType').val(),
															studentStatus : $(
																	'#studentStatus').val(),
															custodian1Relationship : $(
																	'#custodian1Relationship').val(),
															custodian1Name : $(
																	'#custodian1Name').val(),
															custodian1Phone : $(
																	'#custodian1Phone').val(),
															custodian2Relationship : $(
																	'#custodian2Relationship').val(),
															custodian2Name : $(
																	'#custodian2Name').val(),
															custodian2Phone : $(
																	'#custodian2Phone').val(),
															schoolId : $('#schoolId').val(),
															phone : $('#phone').val(),
															attendSchool : $(
																	'#attendSchool').val(),
															intentionalSchool : $(
																	'#intentionalSchool').val(),
														},
														success : function(res) {
															if (res.code == 0) {
																layer.msg("修改成功!");
																setTimeout(function(){
																	window.location.href = "studentmanage.do?pageNo="+${page};
																},1000);
															} else {
																layer.msg(res.msg,{time:3000});
																return;
															}
														}
													})
											return false;
										});
					});
	function deleteone(uid,classId){
		layer.confirm('您确认删除该信息么?',{offset:150,btn:['确认','取消']},function(){
			 $.post('${ctx}/student/testRecoreDelete.do?',{'id':uid,'classId':classId},function(data){
// 				 alert(JSON.stringify(data));
					if(data.code==0){
						layer.msg('删除成功');
						setTimeout(function(){
							window.location.href="${ctx}/student/toupdatestudent.do?studentid="+${students.getStudentId()}+"&type=1";	
						},1000);
					}else{
						layer.msg(data.msg,{time:3000});
						return;
					}
			 });
		    },function(){	
		    	 layer.closeAll();  
		});
	}
	$('.icon-edit').on('click',function(){
		var id = $(this).attr("id");
		$('#id').val(id);
		var classNo = $(this).attr("data-classNo");
		$('#classNo .layui-select-title .layui-unselect').val(classNo);

	});
	$('#confirm_update').on('click',function(){
		$.ajax({
			url:'${ctx}/student/distStudent.do',
			type:'post',
			dateType:'json',
			data:{
				id:$('#id').val(),
				classId:$('#classId').val()
			},
			success:function(data){
				if(data.code==0){
					layer.msg('修改成功!');
					setTimeout(function(){
						window.location.href = "${ctx}/student/toupdatestudent.do?studentid="+${students.getStudentId()}+"&type=1";
					},1000);
				}else{
					layer.msg(data.msg,{time:3000});
					return;
				}
			},
		})
	});
</script>
</html>