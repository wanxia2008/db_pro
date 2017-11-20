<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>修改班级</title>
<link rel="stylesheet" href=".././layui/css/layui.css" />
<link rel="stylesheet" href=".././css/bootstrap.min.css" />
<link rel="stylesheet" href=".././css/lecturenote/style.css" />
<link rel="stylesheet" href=".././css/font-awesome.min.css" />
<script type="text/javascript" src=".././js/jquery.min.js"></script>
<style type="text/css">
.layui-form-item .layui-inline {
	
}

#confirm_update {
	height: 30px;
	line-height: 30px;
	padding: 0 18px;
/* 	background-color: #009688 !important;
 */	margin-right: 80px;
	top: 45%
}

#add_tec, #add_stu {
/* 	background-color: #009688 !important;
 */	padding: 0 18px;
 font-size:14px !important
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
.tf{text-align:left !important}
.pagelist a{padding:6px 18px;border:1px solid #e1e2e3;border-radius:4px;}
.pagelist .current{background:#008bca !important;color:#fff}
.pagelist a:hover{background:#008bca !important;color:#fff}
#pages{height:50px;line-height:50px;}
</style>
</head>
<body>
	<form class="layui-form" action="" method="post">
		<div class="panels_head">
			<span>班级修改</span> 
			<button type="button" class="layui-btn layui-btn-normal" id="confirm_update"
						style="margin-right: 80px; height: 30px; line-height: 30px; padding: 0 18px; top: 20%; margin-top: 0;font-size:14px">确认</button>
			<button type="button" class="layui-btn layui-btn-danger"
				onclick="window.location.href='classmanage.do?pageNo=${pageNo}'"
				style="height: 30px; line-height: 30px; padding: 0 18px; top: 20%; margin-top: 0; font-size: 14px">返回</button>
		</div>
		    <input type="number" hidden="" name="classId" id="classId" value="${classes.getClassId()}" /> 
			<input type="number" hidden=""  id="gradeId" value="${classes.getGrade()}" /> 
			<input type="number" hidden="" id="year1" value="${classes.getYear()}" />
			<input type="number" hidden="" id="schoolId" value="${classes.getSchoolArea()}"> 
			<input type="number" hidden="" id="subjectId2" value="${classes.getSubject()}">
			<input type="number" hidden="" id="seasonId" value="${classes.seasonType}" />
		<table class="layui-table"
			style="width: 98%; margin-left: auto; margin-right: auto;">
			<colgroup>
				<col width="25%"/>
				<col width="25%"/>
				<col width="25%"/>
				<col width="25%">
			</colgroup>
			<tbody>
				<tr>			     						    
				    <td>所属年份</td>
				    <td class="tf">
				      <div class="layui-input-inline" style="width:200px;">
							<select name="year" id="year">
								<c:forEach items="${yearList}" var="year">
									<option value="${year.id}" <c:if test="${classes.year eq year.id}">selected="selected"</c:if>>${year.year}</option>
								</c:forEach>
							</select>
						</div>
				    </td>
				    
				    <td>班级类型</td>
				    <td class="tf">
				        <div class="layui-input-inline" style="width:200px;" >
							<select name="seasonType" id="seasonType">
								<option value="${classes.getSeasonType()}"></option>
								<c:forEach items="${seasonList}" var="map">
									<option value="${map.getSeasonId()}" <c:if test="${classes.seasonType eq map.getSeasonId()}">selected="selected"</c:if>>${map.getSeasonName()}</option>
								</c:forEach>
							</select>
						</div>
				    </td>
					
				</tr>
				
				<tr>
				<td>校区名称</td>
					<td class="tf">
					   <div class="layui-input-inline" style="width:200px;">
							<select name="schoolArea" id="schoolArea">
								<c:forEach items="${schoolList}" var="school">
									<option value="${school.getSchoolId()}" <c:if test="${classes.getSchoolArea() eq school.getSchoolId()}">selected="selected"</c:if>>${school.getSchoolName()}</option>
								</c:forEach>
							</select>
						</div>
					</td> 	
				 <td>所属年级</td>
					<td class="tf">
					   <div class="layui-input-inline" style="width:200px;">
							<select name="grade" id="grade">
								<c:forEach items="${gradesList}" var="grade">
									<option value="${grade.getGradeId()}" <c:if test="${classes.grade eq grade.getGradeId()}">selected="selected"</c:if>>${grade.getGradeName()}</option>
								</c:forEach>
							</select>
						</div>
					</td> 		
				 
				</tr>
				
				<tr>
				  <td>班级名称</td>
				  <td class="tf">
				    <div class="layui-input-inline" style="width:200px;">
							<input type="text" name="className" id="className"
								lay-verify="className" autocomplete="off" placeholder="请输入班级名称"
								value="${classes.getClassName()}" class="layui-input" maxlength="10" >
								<input hidden="" id="classId" value="${classes.classId}" />
						</div>
				  </td>
				  <td>上课科目</td>
				  <td class="tf">
				     <div class="layui-input-inline" style="width:200px;">
							<select name="subject" id="subject" lay-verify="subject">
								<option value="${classes.getSubject()}"></option>
								<c:forEach items="${subjectList}" var="subjects">
									<option value="${subjects.getSubjectId()}" <c:if test="${classes.subject eq subjects.getSubjectId()}">selected="selected"</c:if>>${subjects.getSubjectName()}</option>
								</c:forEach>
							</select>
						</div>
				  </td>			 			
				</tr>
				<tr>
				  <td>排课数/天</td>
				  <td class="tf" >
				    <div class="layui-input-inline" style="width:200px;">
							<input type="number" min="1" max="6" name="maxNumber" id="maxNumber"
								lay-verify="maxNumber" autocomplete="off" placeholder="请输入总数"
								value="${classes.maxNumber}" class="layui-input">
						</div>
						<p>注：这里的数字，比如 2， 表示此班级一天可以排2次课， 一天最多可以排6次课。</p>
				  </td>
				  <td>班级人数</td>
				  <td class="tf">
				    <div class="layui-input-inline" style="width:200px;">
							<input type="number" min="1" name="classNumber" id="classNumber"
								lay-verify="classNumber" autocomplete="off" placeholder="请输入人数"
								value="${classes.getClassNumber()}" class="layui-input">
						</div>
				  </td>
				</tr>
				<tr>
				 <td>排课总数(总课程数)</td>
				  <td class="tf" >
				    <div class="layui-input-inline" style="width:200px;">
							<input type="number" min="1" max="6" name="courseNumber" id="courseNumber"
								lay-verify="maxNumber" autocomplete="off" placeholder="请输入总数"
								value="${classes.courseNumber}" class="layui-input">
						</div>
						<p>注：这里的数字，比如 17， 表示此班级总共17节课。</p>
				  </td>
				</tr>
				
			</tbody>
		</table>
		<div class="panels_head">
			<span>教师管理</span>
			<button type="button" class="layui-btn layui-btn-normal" id="add_tec" style="height:30px;line-height:30px;">新增</button>
		</div>
		<c:choose>
			<c:when test="${classTeacher==null}">
				<p class="no_data_p" style="font-size:14px;margin-top:30px;">~暂无教师~</p>
			</c:when>
			<c:otherwise>
		<table class="layui-table" lay-skin="nob"
			style="width: 95%; margin-left: auto; margin-right: auto; font-size: 10px;">
			
			<thead>
				<tr>
					<th>姓名</th>
					<th>性别</th>
					<th>年龄</th>
					<th>科目</th>
					<th>年级</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${classTeacher}" var="teacher1">
					<tr>
						<td>
							<p>${teacher1.getTeacherName()}</p>
						</td>
						<td>
							<p>
							<c:if test="${teacher1.getSex()==1}">
							男
							</c:if>
							<c:if test="${teacher1.getSex()==2}">
							女
							</c:if>
							</p>
						</td>
						<td>
							<p>${teacher1.getAge()}</p>
						</td>
						<td>
							<p>${teacher1.getSubjectName()}</p>
						</td>
						<td>
							<p>${teacher1.getGrade()}</p>
						</td>
						<td>
							<p class="icon-remove-sign delelte_tec"
								id="${teacher1.getTeacherId()}" style="margin-left:10px;font-size: 14px; color:#ff5722;">移除</p>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</c:otherwise>
	</c:choose>
	<div class="panels_head">
			<span>学员管理</span>
<!-- 			<button type="button" class="layui-btn layui-btn-normal" id="add_stu">增加</button> -->
		</div>
		<table class="layui-table" lay-skin="nob"
			style="width: 95%; margin-left: auto; margin-right: auto; font-size: 10px;">
			<thead>
				<tr>
				   <th>学员Id</th>
					<th>学员姓名</th>
					<th>性别</th>
					<th>年龄</th>
					<th>联系方式</th>
					<th>就读学校</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${studentList}" var="students">
					<tr>
					 <td>
							<p>${students.getStudentId()}</p>
						</td>
						<td>
							<p>${students.getStudent().getStudentName()}</p>
						</td>
						<c:if test="${students.getStudent().getSex() eq 1}">
							<td>男</td>
						</c:if>
						<c:if test="${students.getStudent().getSex() eq 2}">
							<td>女</td>
						</c:if>
						<td>
							<p>${students.getStudent().getAge()}</p>
						</td>
						<td>
							<p><c:choose>
							<c:when test="${students.getStudent().getPhone()!=null}">
							    ${students.getStudent().getPhone()}
							</c:when>
							<c:otherwise>
							   暂无
							</c:otherwise>
							</c:choose></p>
						</td>
						<td>
							<p><c:choose>
							<c:when test="${students.getStudent().getAttendSchool()!=null}">
							    ${students.getStudent().getAttendSchool()}
							</c:when>
							<c:otherwise>
							   暂无
							</c:otherwise>
							</c:choose></p>
						</td>
						<td>
							<p class="icon-remove-sign delete_stu"
								id="${students.getStudentId()}" style="margin-left:10px;">移除</p>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

		<!--模态框-->
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog" style="width: 80%;">
				<div class="modal-content">
				<div class="modal-header">
			        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			        <h4 class="modal-title" id="myModalLabel">选择教师</h4>
            </div>
					<div class="modal-body">
						<div class="layui-form-item"
							style="margin-top: 20px; margin-left: 10px;">
							<div class="layui-inline">
								<label class="layui-form-label"
									style="width: 100px; font-size: 14px;">年份</label>
								<div class="layui-input-inline" style="width: 120px;">
									<select name="" lay-filter="select_year">
										<c:forEach items="${yearList}" var="year">
											<option value="${year.id}">${year.year}</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<div class="layui-inline">
								<label class="layui-form-label"
									style="width: 100px; font-size: 14px;">学员姓名</label>
								<div class="layui-input-inline" style="width: 150px;">
									<input type="text" id="stu_name" autocomplete="off"
										placeholder="输入姓名快速检索" class="layui-input">
								</div>
								<button type="button" class="layui-btn layui-btn-normal"
									id="search_stu"
									style="padding: 0 10px; height: 30px; line-height:30px; font-size: 14px;">搜索</button>
							
							</div>
						</div>

						<table class="layui-table"
							style="width: 95%; margin-left: auto; margin-right: auto; font-size: 10px;">

							<thead>
								<tr>
									<th>年份</th>
									<th>校区</th>
									<th>年级</th>
									<th>课程</th>
									<th>姓名</th>
									<th>性别</th>
									<th>状态</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody id="tbody">


							</tbody>
						</table>
						<div id="pages1" style="text-align: center;"></div>
					</div>
				</div>
			</div>
		</div>
		<!--模态框结束-->
		<!--模态框-->
		<div class="modal fade" id="myModal1" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog" style="width: 60%;">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">选择教师</h4>
					</div>
					<div class="modal-body">
						<div class="layui-form-item" style="margin-top: 10px;">
							<div class="layui-inline">
								<label class="layui-form-label" style="font-size: 14px;">角色</label>
								<div class="layui-input-inline" style="width: 120px;">
									<select name="roleId" id="roleId">
										<option value="">全部</option>
										<option value="">全部</option>
										<c:forEach items="${roleList}" var="y">
											<option value="<c:out value="${y.roleId}" />"><c:out
													value="${y.roleName}"></c:out></option>
										</c:forEach>
									</select>
								</div>
								<button type="button" class="layui-btn layui-btn-normal" id="addRoleId"
									    style="margin-top:0px;">搜索</button>
							</div>
						</div>
						<table class="layui-table"
							style="width: 95%; margin-left: auto; margin-right: auto; font-size: 10px;">

							<thead>
								<tr>
									<th>姓名</th>
									<th>性别</th>
									<th>年龄</th>
									<th>科目</th>
									<th>年级</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody id="tbody1">

							</tbody>
						</table>
						<div id="pages2" style="text-align: center;"></div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal"
							style="height: 30px; line-height: 30px; padding: 0 18px;">关闭</button>
					</div>
				</div>
			</div>
		</div>
		<!--模态框结束-->
	</form>
	<script id="teacher_template" type="text/html">
		{{each objects as value i}}
			<tr>
									<td>{{value.teacherName}}</td>
									{{if value.sex==1}}<td>男</td>{{/if}}
									{{if value.sex==2}}<td>女</td>{{/if}}
									<td>{{value.age}}</td>
									<td>{{value.subjectName}}</td>
									<td>{{value.grade}}</td>
									<td>
										<button type="button" data-id="{{value.teacherId}}" class="layui-btn layui-btn-normal true_add1">添加</button>
									</td>
								</tr>
		{{/each}}
	</script>
	<script id="student_template" type="text/html">
		{{each objects as value i}}
				<tr>                   
                                    <td>{{value.schoolYear.year}}</td>
                                     <td>{{value.schoolZone.schoolName}}</td>
                                     <td>{{value.grade2.gradeName}}</td>
                                     <td>{{value.subject.subjectName}}</td>
									<td>{{value.studentName}}</td>
									{{if value.sex==1}}<td>男</td>{{/if}}
									{{if value.sex==2}}<td>女</td>{{/if}}
                                     <td>
									{{if value.studentStatus==1}}预报名{{/if}}
                                    {{if value.studentStatus==2}}已交学费{{/if}}
                                    {{if value.studentStatus==3}}未分班{{/if}}
                                    {{if value.studentStatus==4}}已分班{{/if}}
                                    {{if value.studentStatus==4}}入学考试完成{{/if}}
									</td>
                                     <td>
										{{if value.studentStatus != 6}}
                                <button type="button" data-id="{{value.studentId}}"  
                                 
                                  class="layui-btn layui-btn-normal true_add">添加</button>
                                 {{/if}}
									</td>
								</tr>
		{{/each}}
	</script>
</body>
<script type="text/javascript" src=".././js/jquery.min.js"></script>
<script src=".././layui/layui.js" charset="utf-8"></script>
<script type="text/javascript" src=".././js/bootstrap.min.js"></script>
<script type="text/javascript" src=".././js/artTemplate-simple-3.0.js"></script>
<script type="text/javascript">
	var pageNo = 1, pageSize = 5,roleId;
	layui.use([ 'form', 'layedit', 'laypage', 'laydate' ],
					function() {
						var form = layui.form(), layer = layui.layer, laypage = layui.laypage, layedit = layui.layedit, laydate = layui.laydate;

						//创建一个编辑器
						var editIndex = layedit.build('LAY_demo_editor');

						//自定义验证规则
						form.verify({
							classNumber : function(value) {
								if (value.length == "") {
									return '请输入班级人数';
								}
							},
							className : function(value) {
								if (value.length == "") {
									return "请输入班级名称";
								}
							},
							subject : function(value) {
								if ($(this).val() == "") {
									return "请选择科目";
								}
							}
						});

						form.on('select(select_year)', function(data) {
							var value = data.value;
							var param = {
								pageNo : pageNo,
								pageSize : pageSize,
								gradeId : $('#gradeId').val(),
								subjectId : $('#subject').val(),
								year : value,
								classId : $('#classId').val(),
								schoolId : $('#schoolId').val(),
								seasonId : $('#seasonId').val()
							};
							ajaxstu_param(param).then(function(res) {
								if(res.count!=0) {
									var html = template('student_template', res);
									$('#tbody').html(html);
									$('#myModal').modal('show');
									if (res.count > 1) {
										laypage({
											cont : 'pages1',
											pages : res.count,
											skin : 'molv',
											curr : pageno,//当前页
											groups : 5,
											skip : true,
											jump : function(obj, first) {
												//触发分页后的回调
												if (!first) {
													//点击跳页触发函数自身，并传递当前页：obj.curr
													getAllStudent(obj.curr);
												}
											}
										});
									}
									$('.true_add').on('click', function() {
										var studentid = $(this).attr("data-id");
										layer.confirm('确定添加吗？', {
											btn : [ '确定' ]
										}, function(index, layero) {//确定的处理
											$.ajax({
												url : 'updatestudentbyclass.do',
												type : 'post',
												dataType : 'json',
												data : {
													classId : $('#classId').val(),
													studentId :studentid
												},
												success : function(res) {
													if (res.code == 1) {
														layer.msg(res.msg)
													} else if (res.code == 0) {
														layer.msg(res.msg);
														window.location.reload();
													}
												}
											});
											layer.close(index);
										});
									});
								} else {
									layer.msg("暂时没有符合条件的学生");
								}
							}, function(error) {

							});
						});

						//监听提交
						form.on('submit(demo1)', function(data) {

							return false;
						});
						
						$('#add_tec').on('click', function() {
							getAllTeacher(pageNo,null);
						});
						$('#addRoleId').on('click',function(){
							roleId=$('#roleId').val();
							getAllTeacher(pageNo,roleId);
						});
						function getAllTeacher(pageno,roleId) {
							$.getJSON("getallteacher.do",{
												pageNo : pageno,
												pageSize : pageSize,
												gradeId : $('#gradeId').val(),
												subjectId : $('#subject').val(),
												schoolId : $('#schoolId').val(),
												roleId:roleId
											},
											function(res) {
												console.log(JSON.stringify(res));
												if (res.code == 0) {
													var html = template('teacher_template',res);
													$('#tbody1').html(html);
													$('#myModal1').modal('show');
													if (res.count > 2) {
														laypage({
															cont : 'pages2',
															pages : res.count,
															skin : 'molv',
															curr : pageno,//当前页
															groups : 5,
															skip : true,
															jump : function(
																	obj, first) {
																//触发分页后的回调
																if (!first) {
																	//点击跳页触发函数自身，并传递当前页：obj.curr
																	getAllStudent(obj.curr);
																}
															}
														});
													}
													$('.true_add1').on('click',function() {
																		var teacherid = $(this).attr("data-id");
																		// 																		alert(teacherid);
																		layer.confirm('确定添加吗？',
																						{btn : [ '确定' ]
																						},
																						function(index,layero) {//确定的处理
																							$.ajax({
																										url : 'updateteacherbyclass.do',
																										type : 'post',
																										dataType : 'json',
																										data : {
																											classId : $('#classId').val(),
																											teacherId : teacherid
																										},
																										success : function(res) {
																											 if (res.code == 0) {
																												layer.msg(res.msg);
																												setTimeout(function(){
																													 window.location.reload();//跳转当前页
// 																													window.location.href = "classmanage.do";
// 																													parent.refreshframe("${ctx}/lesson/exammanage.do");
																												},1000);
																											} else {
																												layer.msg(res.msg,{time:3000});
																											}
																										}
																									});
																							layer.close(index);
																						});
																	});
												} else {
// 													$('#myModal1').modal('hide');
                                                    var html = template('teacher_template',res);
													$('#tbody1').html(html);
													layer.msg("暂时没有符合条件的老师");
												}
											})
						}
					});
	$('.delete_stu').on('click', function() {
		var studentid = $(this).attr("id");
		var classid = $('#classId').val();
		var param = "";
		layer.confirm('确定删除该学员吗？', {
			btn : [ '移除', '结课' ]
		//按钮
		}, function(index) {//移除
			$.ajax({
				url : 'deletestudentbyclass.do',
				type : 'post',
				dataType : 'json',
				data : {
					studentId : studentid,
					classId : classid,
					status:2
				},
				success : function(res) {
					 if (res.code == 0) {
						layer.msg("操作成功!");
						window.location.reload();
					} else{
						layer.msg(res.msg);
					}
				}
			});
			layer.close(index);
		}, function() {//结课
			$.ajax({
				url : 'deletestudentbyclass.do',
				type : 'post',
				dataType : 'json',
				data : {
					studentId : studentid,
					classId : classid,
					status:1
				},
				success : function(res) {
					 if (res.code == 0) {
						layer.msg("结课成功！");
						window.location.reload();
					} else{
						layer.msg(res.msg);
					}
				}
			});
			layer.close(index);
		});
		
	});
	$('.delelte_tec').on('click', function() {
		var teacherid = $(this).attr("id");
		var classid = $('#classId').val();
		layer.confirm('确认删除吗？', {
			btn : [ '确定' ]
		}, function(index, layero) {//确定的处理
			$.ajax({
				url : 'deleteteacherbyclass.do',
				type : 'post',
				dataType : 'json',
				data : {
					teacherId : teacherid,
					classId : classid
				},
				success : function(res) {
					if (res.code == 1) {
						layer.msg("删除失败!请重试",{time:1000})
					} else if (res.code == 0) {
						layer.msg("删除成功!");
						setTimeout(function(){
// 							window.location.href = "classmanage.do";
                          window.location.reload();//跳转当前页
						},3000);
					}else{
						layer.msg(res.msg);
					}
				}
			});
			layer.close(index);
		});
	});
	$('#confirm_update').on('click', function() {
		var maxnumber = $('#maxNumber').val();
		var courseNumber = $('#courseNumber').val();
		if(maxnumber<1){
			layer.msg('课程限制最小为1');
			return false;
		}
		if(maxnumber>6){
			layer.msg('课程限制最大为6');
			return false;
		}
		if(courseNumber<1){
			layer.msg('课程总数限制最小为1');
			return false;
		}
		if(courseNumber>30){
			layer.msg('课程总数限制最大为30');
			return false;
		}
		$.ajax({
			url : 'updateclass.do',
			type : 'post',
			dataType : 'json',
			data : {
				classId : $('#classId').val(),
				classNumber : $('#classNumber').val(),
				className : $('#className').val(),
				subject : $('#subject').val(),
				year : $('#year').val(),
				grade : $('#grade').val(),
				seasonType : $('#seasonType').val(),
				schoolArea : $('#schoolArea').val(),
				maxNumber : maxnumber,
				courseNumber:courseNumber
			},
			success : function(res) {
				if (res.code == 0) {
					layer.msg("修改成功！");
					//top.location.reload();
					setTimeout(function(){
						window.location.reload();
					},1000);
				} else {
					layer.msg(res.msg);
				}
			}
		});
	});

	

	
	function goPageAction(page){
		window.location.href="${ctx}/classes/toupdateclasses.do?pageNo="+page+"&classId=${classId}";
		
		}
</script>
</html>