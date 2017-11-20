<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>修改入学考试</title>
<link rel="stylesheet" href=".././layui/css/layui.css" />
<link rel="stylesheet" href=".././css/bootstrap.min.css" />
<link rel="stylesheet" href=".././css/lecturenote/style.css" />
<link rel="stylesheet" href=".././css/font-awesome.min.css" />
<script type="text/javascript" src=".././js/jquery.min.js"></script>
<style type="text/css">
.layui-form-checkbox {
	height: 26px;
	line-height: 24px;
}

.layui-form-checkbox span {
	font-size: 14px;
}

.layui-form-checked span, .layui-form-checked:hover span {
	background-color: #35CE8D;
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

.icon-plus {
	font-size: 20px;
	position: absolute;
	right: 15px;
	top: -13px;
	cursor: pointer;
	color: #35CE8D;
}
</style>
</head>
<body>
	<form id="myform" class="layui-form" action="updateExamination.do"
		method="post">
		<div class="panels_head">
			<span>修改入学考试</span>
			<input hidden="" name="examId" id="examId" value="${tr.examId}"/>
			<button class="layui-btn layui-btn-normal" lay-submit=""
				lay-filter="demo1"
				style="margin-right: 80px; height: 30px; line-height: 30px; padding: 0 18px; top: 20%; margin-top: 0; font-size: 14px">确认</button>
			<button type="button" class="layui-btn layui-btn-danger"
				onclick="window.location.href='${ctx}/entranceexam/execute.do'"
				style="height: 30px; line-height: 30px; padding: 0 18px; top: 20%; margin-top: 0; font-size: 14px">返回</button>
		</div>

		<ul class="newstudentmesg_ul" style="position: relative;">
			<li class="newstudentmesg_li">
				<div class="layui-form-item">
					<div class="layui-inline">
						<label class="layui-form-label" style="font-size: 14px;width:120px;"><span
							style="color: red; font-size: 16px !important">*&nbsp;&nbsp;</span>学期</label>
						<div class="layui-input-inline" style="width: 130px;">
							<select name="year" id="year" lay-verify="year"
								autocomplete="off" placeholder="请选择年级">
								<option value="">选择学期</option>
								<c:forEach items="${syList}" var="grade">
									<c:choose>
										<c:when test="${tr.year==grade.id}">
											<option selected="selected" value="${grade.id}">${grade.year}</option>
										</c:when>
										<c:otherwise>
											<option value="${grade.id}">${grade.year}</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="layui-inline">
						<label class="layui-form-label" style="font-size: 14px;width:120px;"><span
							style="color: red; font-size: 16px !important">*&nbsp;&nbsp;</span>年级</label>
						<div class="layui-input-inline" style="width: 130px;">
							<select name="gradeId" id="gradeId" lay-verify="grade"
								autocomplete="off" placeholder="请选择年级">
								<option value="">选择年级</option>
								<c:forEach items="${grades}" var="grade">
									<c:choose>
										<c:when test="${tr.gradeId==grade.getGradeId()}">
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
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label" style="font-size: 14px;width:120px;"><span
						style="color: red; font-size: 16px !important">*&nbsp;&nbsp;</span>科目</label>
					<div class="layui-input-inline" style="width: 130px;">
						<select name="subjectId" id="subjectId" lay-verify="subject"
							autocomplete="off" placeholder="请选择科目">
							<option value="">选择科目</option>
							<c:forEach items="${subjects}" var="map">
								<c:choose>
									<c:when test="${tr.subjectId==map.getSubjectId()}">
										<option selected="selected" value="${map.getSubjectId()}">${map.getSubjectName()}</option>
									</c:when>
									<c:otherwise>
										<option value="${map.getSubjectId()}">${map.getSubjectName()}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</select>
					</div>

					<div class="layui-inline">
						<label class="layui-form-label" style="font-size: 14px;width:120px;"><span
							style="color: red; font-size: 16px !important">*&nbsp;&nbsp;</span>校区</label>
						<div class="layui-input-inline"
							style="width: 130px; margin-left: 2px;">
							<select name="schoolId" id="schoolId" lay-verify="schoolId"
								autocomplete="off" placeholder="请选择校区">
								<option value="">选择校区</option>
								<c:forEach items="${szList}" var="map">
									<c:choose>
										<c:when test="${tr.schoolId==map.schoolId}">
											<option selected="selected" value="${map.schoolId}">${map.schoolName}</option>
										</c:when>
										<c:otherwise>
											<option value="${map.schoolId}">${map.schoolName}</option>
										</c:otherwise>
									</c:choose>

								</c:forEach>
							</select>
						</div>
					</div>
				</div>
				<div class="layui-inline">
					<label class="layui-form-label" style="font-size: 14px;width:120px;"><span
						style="color: red; font-size: 16px !important">*&nbsp;&nbsp;</span>季节</label>
					<div class="layui-input-inline" style="width: 130px;">
						<select name="choiceId" id="choiceId" lay-verify="choiceId"
							autocomplete="off" placeholder="请选择季节">
							<option value="">选择季节</option>
							<c:forEach items="${stList}" var="grade">
								<c:choose>

									<c:when test="${tr.choiceId==grade.seasonId}">
										<option selected="selected" value="${grade.seasonId}">${grade.seasonName}</option>
									</c:when>
									<c:otherwise>
										<option value="${grade.seasonId}">${grade.seasonName}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</select>
					</div>
					<%-- <div class="layui-inline">
						<label class="layui-form-label"
							style="width: 120px; font-size: 14px;"><span
							style="color: red; font-size: 16px !important">*&nbsp;&nbsp;</span>试卷</label>
						<div class="layui-input-inline" style="margin-top: 20px;">
							<span class="icon-plus" style="color: #008bca;"></span> 
							<span
								id="task_showname" style="margin-left: 25px;">${pInfo.getPiName()}</span>
						</div>
						<input hidden="" type="" name="paperId" lay-verify="taskCount"
							id="task_content" value="" />
					</div> --%>
				</div>
				<div class="layui-form-item" style="margin: 20px;">
		<div class="layui-inline">
				<label class="layui-form-label" style="width:120px;font-size:14px;margin-left:5px;"><span style="color:red;font-size:16px !important">*&nbsp;&nbsp;</span>开始时间</label>
				<div class="layui-input-inline" style="width: 130px;">
					<input type="text" name="createTime" id="createTime" value="${startTime}"
						lay-verify="startTime" placeholder="请输入开始时间" autocomplete="off"
						class="layui-input"
						onclick="layui.laydate({elem: this, istime: true, format: 'YYYY-MM-DD'})">
				</div>
			</div>
			<div class="layui-inline">
				<label class="layui-form-label" style="width:120px;font-size:14px;margin-left:5px;"><span style="color:red;font-size:16px !important">*&nbsp;&nbsp;</span>结束时间</label>
				<div class="layui-input-inline" style="width:130px;">
					<input type="text" name="endTime" id="endTime" value="${endTime}"
						lay-verify="endTime" placeholder="请输入结束时间" autocomplete="off"
						class="layui-input"
						onclick="layui.laydate({elem: this, istime: true, format: 'YYYY-MM-DD'})">
				</div>
			</div>
		</div>
			</li>
		</ul>
		<!--模态框-->
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog" style="width: 80%;">
				<div class="modal-content">
					<div class="modal-body">
						<div class="layui-form-item">
							<div class="layui-inline">
								<label class="layui-form-label" style="width: 100px;">科目</label>
								<div class="layui-input-inline" style="width: 120px;">
									<select name="subject" id="subject2"
										lay-filter="select_subject">
										<option value="">请选择</option>
										<c:forEach items="${subjects}" var="s">
											<option value="${s.subjectId}">${s.subjectName}</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<div class="layui-inline">
								<label class="layui-form-label" style="width: 100px;">年级</label>
								<div class="layui-input-inline" style="width: 120px;">
									<select name="gradeId" id="gradeId2"
										lay-filter="select_gradeId">
										<option value="">请选择</option>
										<c:forEach items="${grades}" var="s">
											<option value="${s.gradeId}">${s.gradeName}</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>
						<div class="layui-form-item" id="tbody">
							<!-- 试卷内容 -->
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
	<script type="text/html" id="exam_template">
		{{each objects as value i}}
			<div class="exam_content" id="{{value.piId}}" data-name="{{value.piName}}">
				<p class="exam_title">标题：{{value.piName}}</p>
				<div class="exam_star">
					<span style="float: left; width: 30%;">难度：</span>
					{{if value.difficultyValue==1}}
						<ul class="uls_showstars" style="width: 70%; float: right;">
						<li class="icon-star" id="1" style="cursor: pointer;"></li>
						<li class="icon-star-empty" id="2" style="cursor: pointer;"></li>
						<li class="icon-star-empty" id="3" style="cursor: pointer;"></li>
						<li class="icon-star-empty" id="4" style="cursor: pointer;"></li>
						<li class="icon-star-empty" id="5" style="cursor: pointer;"></li>
						</ul>
					{{/if}}
					{{if value.difficultyValue==2}}
						<ul class="uls_showstars" style="width: 70%; float: right;">
						<li class="icon-star" id="1" style="cursor: pointer;"></li>
						<li class="icon-star" id="2" style="cursor: pointer;"></li>
						<li class="icon-star-empty" id="3" style="cursor: pointer;"></li>
						<li class="icon-star-empty" id="4" style="cursor: pointer;"></li>
						<li class="icon-star-empty" id="5" style="cursor: pointer;"></li>
						</ul>
					{{/if}}
					{{if value.difficultyValue==3}}
						<ul class="uls_showstars" style="width: 70%; float: right;">
						<li class="icon-star" id="1" style="cursor: pointer;"></li>
						<li class="icon-star" id="2" style="cursor: pointer;"></li>
						<li class="icon-star" id="3" style="cursor: pointer;"></li>
						<li class="icon-star-empty" id="4" style="cursor: pointer;"></li>
						<li class="icon-star-empty" id="5" style="cursor: pointer;"></li>
						</ul>
					{{/if}}
					{{if value.difficultyValue==4}}
						<ul class="uls_showstars" style="width: 70%; float: right;">
						<li class="icon-star" id="1" style="cursor: pointer;"></li>
						<li class="icon-star" id="2" style="cursor: pointer;"></li>
						<li class="icon-star" id="3" style="cursor: pointer;"></li>
						<li class="icon-star" id="4" style="cursor: pointer;"></li>
						<li class="icon-star-empty" id="5" style="cursor: pointer;"></li>
						</ul>
					{{/if}}
					{{if value.difficultyValue==5}}
						<ul class="uls_showstars" style="width: 70%; float: right;">
						<li class="icon-star" id="1" style="cursor: pointer;"></li>
						<li class="icon-star" id="2" style="cursor: pointer;"></li>
						<li class="icon-star" id="3" style="cursor: pointer;"></li>
						<li class="icon-star" id="4" style="cursor: pointer;"></li>
						<li class="icon-star" id="5" style="cursor: pointer;"></li>
						</ul>
					{{/if}}
				</div>
				<p class="exam_writer">作者：${value.buildUserName}</p>
				<p class="exam_time">时间：{{(value.createTime).substring(0,10)}}</p>
				<p class="exam_range">知识范围：{{value.grade2.gradeName}}</p>
				<p class="exam_num">使用次数：{{value.usedTimes}}次</p>
			</div>
		{{/each}}
	</script>
</body>
<script type="text/javascript" src=".././js/jquery.min.js"></script>
<script src=".././layui/layui.js" charset="utf-8"></script>
<script type="text/javascript" src=".././js/bootstrap.min.js"></script>
<script type="text/javascript" src=".././js/artTemplate-simple-3.0.js"></script>
<script type="text/javascript">
	var type, grade, subject;
	layui
			.use(
					[ 'form', 'layedit', 'laydate' ],
					function() {
						var form = layui.form(), layer = layui.layer, layedit = layui.layedit, laydate = layui.laydate;

						//创建一个编辑器
						var editIndex = layedit.build('LAY_demo_editor');
						form.on('select(select_subject)', function(data) {
							subject = data.value;
							var param = {
								type : 4,
								grade : grade,
								subject : subject,
							};
							ajax_exam(param);
						});
						form.on('select(select_gradeId)', function(data) {
							grade = data.value;
							var param = {
								type : 4,
								grade : grade,
								subject : subject,
							};
							ajax_exam(param);
						});

						//自定义验证规则
						form.verify({

							year : function(value) {
								if (value.length == "") {
									return "请选择学期";
								}
							},

							grade : function(value) {
								if (value.length == "") {
									return "请选择年级";
								}
							},
							subject : function(value) {
								if (value.length == "") {
									return "请选择科目";
								}
							},
							choiceId : function(value) {
								if (value.length == "") {
									return "请选择季节";
								}
							},
							schoolId : function(value) {
								if (value.length == "") {
									return "请选择校区";
								}
							},
							startTime : function(value) {
								if (value.length == '') {
									return '请选择开始时间';
								} else {
									var endtime = $('#endTime').val();
									var date1 = new Date(value);
									var date2 = new Date(endTime);
									if(date1.getTime()>date2.getTime()) {
										return '开始时间不能大于结束时间';
									}
								}
							},
							endTime : function(value) {
								if (value.length == "") {
									return '请选择结束时间';
								} else {
									var starttime = $('#createTime').val();
									var date1 = new Date(value);
									var date2 = new Date(starttime);
									if(date1.getTime()<date2.getTime()) {
										return '结束时间不能小于开始时间';
									}
									if(date1.getTime()==date2.getTime()) {
										return '结束时间不能等于开始时间';
									}
								}
							},
						});
						//监听提交
						form.on('submit(demo1)', function(data) {
							var examid = $('#task_content').val();//任务内容即试卷编号
							$.ajax({
								url : 'updateExamination.do',
								type : 'post',
								dataType : 'json',
								data : {
									examId:$('#examId').val(),
									year : $('#year').val(),
									gradeId : $('#gradeId').val(),
									subjectId : $('#subjectId').val(),
									schoolId : $('#schoolId').val(),
									choiceId : $('#choiceId').val(),
									paperId : examid
								},
								success : function(res) {
									if (res.code == 0) {
										layer.msg("修改成功!");
										setTimeout(function(){
											window.location.href = "execute.do";
										},500);
									} else {
										layer.msg(res.msg,{time:3000});
										return;
									}
								},
							})
							return false;
						});
					});
	$('.icon-plus').on('click', function() {
		var param = {
			type : 4
		}
		ajax_exam(param);
	});

	function ajax_exam(param) {
		$.ajax({
			url : '${ctx}/exam/getpaperbyitem.do',
			type : 'post',
			dataType : 'json',
			data : param,
			success : function(res) {
				if (res.code == 0) {
					console.log(JSON.stringify(res));
					var html = template("exam_template", res);
					$('#tbody').html(html);
					$('#myModal').modal('show');
					click_content();
				} else {
					layer.msg(res.msg,{time:3000});
				}
			}
		})
	}
	//点击模态框的试卷事件
	function click_content() {
		$('.exam_content').click(function() {
			var id = $(this).attr("id");
			var name = $(this).attr("data-name");
			layer.confirm('确认添加该试卷吗？', {
				btn : [ '确定' ]
			}, function(index, layero) {//确定的处理
				$('#myModal').modal('hide');
				$('#task_showname').text(name);
				$('#task_content').val(id);
				layer.close(index);
			});
		})
	}
</script>
</html>