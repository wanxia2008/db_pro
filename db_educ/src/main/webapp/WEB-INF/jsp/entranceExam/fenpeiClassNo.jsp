<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>分配班级</title>
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
</style>

</head>
<body>
	<form class="layui-form" action="" method="post">
		<div class="panels_head">
			<span>分配班级</span> 
			<button type="button" class="layui-btn layui-btn-normal" id="confirm_update"
						style="margin-right: 80px; height: 30px; line-height: 30px; padding: 0 18px; top: 20%; margin-top: 0;font-size:14px">确认</button>
			<button type="button" class="layui-btn layui-btn-danger"
				onclick="window.location.href='endExecute.do'"
				style="height: 30px; line-height: 30px; padding: 0 18px; top: 20%; margin-top: 0; font-size: 14px">返回</button>
		</div>
		
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
				    ${tr.schoolYear.year}年
				    </td>
				    
				    <td>所属校区</td>
				    <td class="tf">
				      ${tr.schoolZone.schoolName}
				    </td>
					
				</tr>
				
				<tr>
				<td>所属季节</td>
					<td class="tf">
					   <div class="layui-input-inline" style="width:200px;">
							 ${tr.seasonType.seasonName}
						</div>
					</td> 	
				 <td>所属年级</td>
					<td class="tf">
					   <div class="layui-input-inline" style="width:200px;">
							  ${tr.grade.gradeName}
						</div>
					</td> 		
				 
				</tr>
				
				<tr>
				  <td>考试状态</td>
				  <td class="tf">
				    <div class="layui-input-inline" style="width:200px;">
							已完成入学考试
						</div>
				  </td>
				  <td>考试科目</td>
				  <td class="tf">
				     <div class="layui-input-inline" style="width:200px;">
							  ${tr.subject.subjectName}
						</div>
				  </td>			 			
				</tr>
<!-- 				<tr> -->
<!-- 				  <td>班级人数</td> -->
<!-- 				  <td class="tf" colspan="3"> -->
<!-- 				    <div class="layui-input-inline" style="width:200px;"> -->
<!-- 							<input type="number" min="1" name="classNumber" id="classNumber" -->
<!-- 								lay-verify="classNumber" autocomplete="off" placeholder="请输入人数" -->
<%-- 								value="${classes.getClassNumber()}" class="layui-input"> --%>
<!-- 						</div> -->
<!-- 				  </td> -->
<!-- 				</tr> -->
			</tbody>
		</table>
		<div class="panels_head">
			<span>学员管理</span>
<!-- 			<button type="button" class="layui-btn layui-btn-normal" id="add_stu" style="height:30px;line-height:30px;">新增</button> -->
		</div>
		<c:choose>
			<c:when test="${studentList==null}">
				<p class="no_data_p" style="font-size:14px;margin-top:30px;">~暂无学员~</p>
			</c:when>
			<c:otherwise>
		<table class="layui-table" lay-skin="nob"
			style="width: 95%; margin-left: auto; margin-right: auto; font-size: 10px;">
			<thead>
				<tr>
				    <th>学员id</th>
					<th>学员姓名</th>
					<th>性别</th>
					<th>年龄</th>
<!-- 					<th>入学成绩</th> -->
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${studentList}" var="students">
					<tr>
						<td>
						${students.getStudentId()}
						</td>
						<td>
							${students.getStudentName()}
						</td>
						<c:if test="${students.getSex() eq 1}">
							<td>男</td>
						</c:if>
						<c:if test="${students.getSex() eq 2}">
							<td>女</td>
						</c:if>
						<td>
							${students.getAge()}
						</td>
<!-- 						<td> -->
<%-- 							<p>${students.getEntranceScore()}</p> --%>
<!-- 						</td> -->
						<td>
							<p class="icon-remove-sign delete_stu"
								id="${students.getStudentId()}" style="margin-left: 10px;"></p>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		</c:otherwise>
		</c:choose>
		<!--模态框-->
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog" style="width: 80%;">
				<div class="modal-content">
				<div class="modal-header">
			        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			        <h4 class="modal-title" id="myModalLabel">分配班级</h4>
               </div>
					<div class="modal-body">
						<div class="layui-form-item"
							style="margin-top: 20px; margin-left: 10px;">
							<div class="layui-inline" style="width: 80px;">
								<select name="" lay-filter="select_year">
									<c:forEach items="${yearList}" var="year">
										<option value="${year.year}">${year.year}</option>
									</c:forEach>
								</select>
							</div>
							<!-- 							<div class="layui-inline" style="width: 80px;"> -->
							<!-- 								<select name="" lay-filter="select_subject"> -->
							<!-- 									<option value="">--请选择--</option> -->
							<%-- 									<c:forEach items="${subjectList}" var="sb"> --%>
							<%-- 										<option value="${sb.subjectId}">${sb.subjectName}</option> --%>
							<%-- 									</c:forEach> --%>

							<!-- 								</select> -->
							<!-- 							</div> -->
							<!-- 							<div class="layui-inline" style="width: 80px;"> -->
							<!-- 								<select name="" lay-filter="select_season"> -->
							<!-- 									<option value="">--请选择--</option> -->
							<%-- 									<c:forEach items="${seasonList}" var="season"> --%>
							<%-- 										<option value="${season.seasonId}">${season.seasonName}</option> --%>
							<%-- 									</c:forEach> --%>
							<!-- 								</select> -->
							<!-- 							</div> -->
							<div class="layui-inline"
								style="margin-left: 10px; float: right; margin-right: 100px;">
								<input type="text" id="stu_name" autocomplete="off"
									placeholder="输入姓名快速检索" class="layui-input">
							</div>
							<div class="layui-inline"
								style="position: absolute; right: 35px; top: 40px;">
								<button type="button" class="layui-btn layui-btn-normal" id="search_stu"
									style="padding: 0 10px; height: 30px; line-height: 30px; font-size: 14px;">搜索</button>
							</div>
						</div>
						<!-- 						<div class="layui-form-item" style="margin-left: 10px;"> -->
						<!-- 							<div class="layui-inline"> -->
						<!-- 								<label class="layui-form-label">入学成绩</label> -->
						<!-- 								<div class="layui-input-inline" style="width: 50px;"> -->
						<!-- 									<input type="text" name="title" lay-verify="title" -->
						<!-- 										autocomplete="off" placeholder="" class="layui-input"> -->
						<!-- 								</div> -->
						<!-- 								<div class="layui-form-mid">-</div> -->
						<!-- 								<div class="layui-input-inline" style="width: 50px;"> -->
						<!-- 									<input type="text" name="title" lay-verify="title" -->
						<!-- 										autocomplete="off" placeholder="" class="layui-input"> -->
						<!-- 								</div> -->
						<!-- 							</div> -->
						<!-- 							<div class="layui-inline" -->
						<!-- 								style="margin-left: 10px;"> -->
						<!-- 								<label class="layui-form-label">状态</label> <input -->
						<!-- 									type="checkbox" name="like1[write]" lay-skin="primary" -->
						<!-- 									title="仅查看未分班学员" checked=""> -->
						<!-- 							</div> -->
						<!-- 						</div> -->
						<table class="layui-table"
							style="width: 95%; margin-left: auto; margin-right: auto; font-size: 10px;">
							<colgroup>
								<col width="80" />
								<col width="50" />
								<col width="100" />
								<col width="100" />
								<col width="100" />
								<col width="100" />
								<col width="80" />
							</colgroup>
							<thead>
								<tr>
									<th>姓名</th>
									<th>性别</th>
									<th>报名课程</th>
									<th>报名年级</th>
									<th>入学成绩</th>
									<th>状态</th>
									<th>选择</th>
								</tr>
							</thead>
							<tbody id="tbody">
								<!-- 								<tr> -->
								<!-- 									<td>马思琪</td> -->
								<!-- 									<td>男</td> -->
								<!-- 									<td>六年级</td> -->
								<!-- 									<td>89=23+24+30+12</td> -->
								<!-- 									<td>未分班</td> -->
								<!-- 									<td><input type="checkbox" name="" lay-skin="primary"></td> -->
								<!-- 								</tr> -->

							</tbody>
						</table>
						<div id="pages1" style="text-align: center;"></div>
					</div>
					<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal" style="height:30px;line-height:30px;padding:0 18px;">关闭</button>
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
			        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			        <h4 class="modal-title" id="myModalLabel">编辑试卷类型</h4>
               </div>
					<div class="modal-body">
						<table class="layui-table"
							style="width: 95%; margin-left: auto; margin-right: auto; font-size: 10px;">
							<colgroup>
								<col width="100" />
								<col width="50" />
								<col width="100" />
								<col width="100" />
								<col width="100" />
								<col width="80" />
							</colgroup>
							<thead>
								<tr>
									<th>姓名</th>
									<th>性别</th>
									<th>年龄</th>
									<th>科目</th>
									<th>年级</th>
									<th>选择</th>
								</tr>
							</thead>
							<tbody id="tbody1">

								<!-- 								<tr> -->
								<!-- 									<td>马思琪</td> -->
								<!-- 									<td>男</td> -->
								<!-- 									<td>六年级</td> -->
								<!-- 									<td>89=23+24+30+12</td> -->
								<!-- 									<td>未分班</td> -->
								<!-- 									<td><input type="checkbox" name="" lay-skin="primary"></td> -->
								<!-- 								</tr> -->
							</tbody>
						</table>
						<div id="pages2" style="text-align: center;"></div>
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
									<td>{{value.student.studentName}}</td>
									{{if value.student.sex==1}}<td>男</td>{{/if}}
									{{if value.student.sex==2}}<td>女</td>{{/if}}
									<td>
										{{if value.subjects.subjectName!=null}}
										{{value.subjects.subjectName}}
										{{/if}}
									</td>
									<td>
										{{if value.grades.gradeName!=null}}
										{{value.grades.gradeName}}
										{{/if}}
									</td>
									<td>
										{{if value.entranceScore!=null}}
										{{value.entranceScore}}分
										{{/if}}
									</td>
									<td>
										{{if value.status==1}}<p>未分班</p>{{/if}}
										{{if value.status==2}}<p>已分班</p>{{/if}}
									</td>
									<td>
										{{if value.status==1}}<button type="button" data-id="{{value.studentId}}" class="layui-btn layui-btn-normal true_add">添加</button>{{/if}}
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
	var pageNo = 1, pageSize = 5;
	layui
			.use(
					[ 'form', 'layedit', 'laypage', 'laydate' ],
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
										//	 											alert(studentid);
										layer.confirm('确定添加吗？', {
											btn : [ '确定' ]
										}, function(index, layero) {//确定的处理
											$.ajax({
												url : 'updatestudentbyclass.do',
												type : 'post',
												dataType : 'json',
												data : {
													classId : $('#classId').val(),
													studentId : studentid
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
							layer.alert(JSON.stringify(data.field), {
								title : '最终的提交信息'
							});

							return false;
						});
						$('#add_stu').on('click', function() {
							getAllStudent(pageNo);
						});
						function getAllStudent(pageno) {
							var param = {
								pageNo : pageno,
								pageSize : pageSize,
								gradeId : $('#gradeId').val(),
								subjectId : $('#subject').val(),
								year : $('#year1').val(),
								classId : $('#classId').val(),
								schoolId : $('#schoolId').val(),
								seasonId : $('#seasonId').val()
							};
							ajaxstu_param(param).then(function(res) {
								console.log(JSON.stringify(res));
								if(res.count>0) {
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
										//	 											alert(studentid);
										layer.confirm('确定添加吗？', {
											btn : [ '确定' ]
										}, function(index, layero) {//确定的处理
											$.ajax({
												url : 'updatestudentbyclass.do',
												type : 'post',
												dataType : 'json',
												data : {
													classId : $('#classId').val(),
													studentId : studentid,
													schoolId : $('#schoolId').val()
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
								alert("错误");
							});
						}
						$('#add_tec').on('click', function() {
							getAllTeacher(pageNo);
						});
						function getAllTeacher(pageno) {
							$
									.getJSON(
											"getallteacher.do",
											{
												pageNo : pageno,
												pageSize : pageSize,
												gradeId : $('#gradeId').val(),
												subjectId : $('#subject').val(),
												schoolId : $('#schoolId').val()
											},
											function(res) {
												console
														.log(JSON
																.stringify(res));
												if (res.code == 0) {
													var html = template(
															'teacher_template',
															res);
													$('#tbody1').html(html);
													$('#myModal1')
															.modal('show');
													if (res.count > 1) {
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
													$('.true_add1')
															.on(
																	'click',
																	function() {
																		var teacherid = $(
																				this)
																				.attr(
																						"data-id");
																		// 																		alert(teacherid);
																		layer
																				.confirm(
																						'确定添加吗？',
																						{
																							btn : [ '确定' ]
																						},
																						function(
																								index,
																								layero) {//确定的处理
																							$
																									.ajax({
																										url : 'updateteacherbyclass.do',
																										type : 'post',
																										dataType : 'json',
																										data : {
																											classId : $(
																													'#classId')
																													.val(),
																											teacherId : teacherid
																										},
																										success : function(
																												res) {
																											if (res.code == 1) {
																												layer
																														.msg(res.msg)
																											} else if (res.code == 0) {
																												layer
																														.msg(res.msg);
																												parent.refreshframe("${ctx}/lesson/exammanage.do");
																											} else if (res.code == 2) {
																												layer
																														.msg(res.msg);
																											}
																										}
																									});
																							layer
																									.close(index);
																						});
																	});
												} else if (res.code == 1) {
													$('#myModal1')
															.modal('hide');
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
					if (res.code == 1) {
						layer.msg("操作失败")
					} else if (res.code == 0) {
						layer.msg("操作成功！");
						window.location.reload();
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
					if (res.code == 1) {
						layer.msg("操作失败")
					} else if (res.code == 0) {
						layer.msg("操作成功！");
						window.location.reload();
					}
				}
			});
			layer.close(index);
		});
		// 		layer.confirm('确认删除吗？', {
		// 			btn : [ '确定' ]
		// 		}, function(index, layero) {//确定的处理
		// 			$.ajax({
		// 				url : 'deletestudentbyclass.do',
		// 				type : 'post',
		// 				dataType : 'json',
		// 				data : {
		// 					studentId : studentid,
		// 					classId : classid
		// 				},
		// 				success : function(res) {
		// 					if (res.code == 1) {
		// 						layer.msg("删除失败！请重试")
		// 					} else if (res.code == 0) {
		// 						layer.msg("删除成功！");
		// 						window.location.reload();
		// 					}
		// 				}
		// 			});
		// 			layer.close(index);
		// 		});
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
						layer.msg("删除失败！请重试")
					} else if (res.code == 0) {
						layer.msg("删除成功！");
						parent.refreshframe("lesson/exammanage.do");
					}
				}
			});
			layer.close(index);
		});
	});
	$('#confirm_update').on('click', function() {
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
				schoolArea : $('#schoolArea').val()
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

	function ajaxstu_param(param) {
		return new Promise(function(resolve, reject) {
			$.getJSON("getallstudent.do", param, function(res) {
				console.log(JSON.stringify(res));
				if (res.code == 0) {
					resolve(res);
				} else if (res.code == 1) {
					layer.msg("没有找到符合条件的学生");
				}
			})
		});
	}
	$('#search_stu').click(function() {
		var val = $('#stu_name').val();
		if (val == "") {
			layer.msg("请输入要查询的学生姓名");
		} else {
			var param = {
				pageNo : pageNo,
				pageSize : pageSize,
				gradeId : $('#gradeId').val(),
				subjectId : $('#subject').val(),
				year : $('#year1').val(),
				classId : $('#classId').val(),
				content : val,
				schoolId : $('#schoolId').val(),
				seasonId : $('#seasonId').val()
			};
			ajaxstu_param(param).then(function(res) {
				var html = template('student_template', res);
				$('#tbody').html(html);
				$('#myModal').modal('show');
				if (res.count > 1) {
					laypage({
						cont : 'pages',
						pages : res.count,
						skin : 'molv',
						curr : pageno,//当前页
						groups : 5,
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
					//	 											alert(studentid);
					layer.confirm('确定添加吗？', {
						btn : [ '确定' ]
					}, function(index, layero) {//确定的处理
						$.ajax({
							url : 'updatestudentbyclass.do',
							type : 'post',
							dataType : 'json',
							data : {
								classId : $('#classId').val(),
								studentId : studentid
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
			}, function(error) {

			});
		}
	});
</script>
</html>