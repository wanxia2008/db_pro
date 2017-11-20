<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- pageEncoding="UTF-8"%> -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改任务</title>
<link rel="stylesheet" href=".././layui/css/layui.css" />
<link rel="stylesheet" href=".././css/bootstrap.min.css" />
<link rel="stylesheet" href=".././css/lecturenote/style.css" />
<link rel="stylesheet" href=".././css/font-awesome.min.css" />
<script type="text/javascript" src=".././js/jquery.min.js"></script>
<script src=".././layui/layui.js" charset="utf-8"></script>
<style type="text/css">
.icon-plus {
	font-size: 20px;
	position: absolute;
	left: 0;
	top: 0;
	cursor: pointer;
	color: #35CE8D;
}

.exam_content {
	cursor: pointer;
}
#classobject{
	display:none;
}
.a-btn{height:30px;line-height:30px;padding:0 18px;position: absolute;right: 25px;top: 20%;font-size: 10px;border-radius: 5px;background:#e33}
.icon-star,.icon-star-empty{color:#333 !important}
.layui-table td, .layui-table th{padding:0;height:30px;line-height:30px;}
</style>
</head>
<body>
	<form id="myform" method="post" class="layui-form" action="updateTask.do">
		<div class="panels_head">
			<span>编辑任务</span>
			<button class="layui-btn layui-btn-normal" lay-submit="" lay-filter="demo1" style="margin-right: 80px;height:30px;line-height:30px;padding:0 18px;top:20%;margin-top:0;font-size:14px">确认</button>	
			<button type="button" class="layui-btn layui-btn-danger" onclick="window.location.href='showmytask.do'"
						style=" height: 30px; line-height: 30px; padding: 0 18px; top: 20%; margin-top: 0;font-size:14px">返回</button>
		</div>
		<input hidden="" name="taskId" value="${task.taskId}"/>
		<div class="layui-form-item" style="margin: 20px;">
			<div class="layui-inline">
				<label class="layui-form-label" style="width:120px;font-size:14px;"><span style="color:red;font-size:16px !important">*&nbsp;&nbsp;</span>任务类型</label>
				<div class="layui-input-inline">
					<select name="taskType" lay-verify="taskType" id="taskType">
						<c:forEach items="${tkList}" var="ty">
						<c:choose>
						   <c:when test="${task.taskType==ty.typeId}">
						   <option selected="selected" value="${ty.typeId}">${ty.taskTypeName}</option>
						   </c:when>
						   <c:otherwise>
						         <option value="${ty.typeId}">${ty.taskTypeName}</option>
						   </c:otherwise>
						</c:choose>
						</c:forEach>
					</select>
				</div>
			</div>
		</div>
		<div class="layui-form-item" style="margin: 20px;">
			<div class="layui-inline">
				<label class="layui-form-label" style="width: 120px;font-size:14px;"><span style="color:red;font-size:16px !important">*&nbsp;&nbsp;</span>任务主题</label>
				<div class="layui-input-inline" style="width: 250px;">
					<input type="text" name="taskTitle" lay-verify="taskTitle"
						autocomplete="off" placeholder="请输入主题" value="${task.taskTitle}" 
						class="layui-input" maxlength="10" >
				</div>
			</div>
		</div>
		<div class="layui-form-item" style="margin: 20px;">
			<div class="layui-inline">
				<label class="layui-form-label" style="width: 120px;font-size:14px;"><span style="color:red;font-size:16px !important">*&nbsp;&nbsp;</span>任务要求</label>
				<div class="layui-input-inline">
					<textarea style="width: 500px; height: 100px; border-radius: 5px;"
						placeholder="请输入内容" value="" class="layui-textarea" name="requirement" lay-verify="requirement">${task.requirement}</textarea>
				</div>
				<!--<div class="layui-input-inline">
						<textarea style="width: 500px;height: 100px;border-radius: 5px;" placeholder="要求"></textarea>
					</div>-->
			</div>
		</div>
		<div class="layui-form-item" style="margin: 20px;">
			<div class="layui-inline">
				<label class="layui-form-label" style="width: 120px;font-size:14px;"><span style="color:red;font-size:16px !important">*&nbsp;&nbsp;</span>任务内容</label>
				<div class="layui-input-inline" style="margin-top: 10px;">
					<span class="icon-plus" style="color:#008bca !important;"></span> <span
						id="task_showname" style="margin-left: 25px;">${task.getPaperInfo().getPiName()}</span>
				</div>
				<input hidden="" type="" name="taskCount" lay-verify="taskCount" id="task_content" value="${task.taskCount}" />
			</div>
		</div>
	
		

		<div class="layui-form-item" style="margin: 20px;">
			<div class="layui-inline">
				<label class="layui-form-label" style="width: 120px;font-size:14px;"><span style="color:red;font-size:16px !important">*&nbsp;&nbsp;</span>开始时间</label>
				<div class="layui-input-inline">
					<input type="text" name="startTime" id="startTime" lay-verify="startTime"
						placeholder="请输入开始时间" value="${task.startTime}" autocomplete="off" class="layui-input"
						onclick="layui.laydate({elem: this, istime: true, format: 'YYYY-MM-DD hh:mm'})">
				</div>
			</div>
		</div>
		<div class="layui-form-item" style="margin: 20px;">
			<div class="layui-inline">
				<label class="layui-form-label" style="font-size:14px;width: 120px;">任务规则</label>
				<div class="layui-input-inline">
					<select name="rule" lay-verify="rule">
						<c:forEach items="${trList}" var="role">
							<option value="${role.ruleId}">${role.ruleName}</option>
						</c:forEach>
					</select>
				</div>
			</div>
		</div>
		<div class="layui-form-item" style="margin: 10px 0">
				<div class="layui-inline">
					<label class="layui-form-label" style="width:120px;font-size: 14px;">考试对象</label>
					<table style="width: 40%; position: absolute; left: 120px; font-size: 14px;">					
						<tbody id="tbody2">
							<c:forEach items="${task.getClassNos()}" var="tags">
								<tr id="${tags.classId}" class="tag_tr1" style="cursor: pointer;">
									<td class="hline"><span class="tagclass">${tags.className}</span></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<input hidden="" name="tag" lay-verify="tag1" id="tag1"
						value="${task.taskObjectClass}" />
					<button type="button" class="btn btn-primary" data-toggle="modal"
					    onclick="openModal()"
						style="margin-top: 10px; margin-left: 160px;font-size: 14px;border:none;border-radius:4px;background-color:#008bca !important">+考试对象</button>
				</div>
			</div>

		<!--模态框-->
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog" style="width: 80%;">
				<div class="modal-content">
				<div class="modal-header">
			        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			        <h4 class="modal-title" id="myModalLabel">编辑任务内容</h4>
			      </div>
					<div class="modal-body">
						<div class="layui-form-item" style="margin-top: 20px 0;">
<!-- 							 <div class="layui-inline"> -->
<!-- 								<label class="layui-form-label" style="width: 100px;">试卷题型</label> -->
<!-- 								<div class="layui-input-inline" style="width: 120px;"> -->
<!-- 									<select name="topicType" id="topicType" -->
<!-- 										lay-filter="select_type"> -->
<!-- 										<option value="">请选择</option> -->
<%-- 										<c:forEach items="${tyList}" var="qq"> --%>
<%-- 											<option value="${qq.typeId}">${qq.taskTypeName}</option> --%>
<%-- 										</c:forEach> --%>
<!-- 									</select> -->
<!-- 								</div> -->
<!-- 							</div>  -->
							 <div class="layui-inline">
								<label class="layui-form-label" style="width: 100px;">年级</label>
								<div class="layui-input-inline" style="width: 120px;">
									<select name="knowledge" id="knowledge"
										lay-filter="select_grade">
										<option value="">请选择</option>
										<option value="">全部</option>
										<c:forEach items="${gradeList}" var="g">
											<option value="<c:out value="${g.gradeId}" />"><c:out
													value="${g.gradeName}"></c:out></option>
										</c:forEach>
									</select>
								</div>
							</div> 
							<div class="layui-inline">
								<label class="layui-form-label" style="width: 100px;">科目</label>
								<div class="layui-input-inline" style="width: 120px;">
									<select name="subject" id="subject" lay-filter="select_subject">
										<option value="">请选择</option>
										<option value="">全部</option>
										<c:forEach items="${sList}" var="s">
											<option value="${s.subjectId}">${s.subjectName}</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>
						<table class="layui-table" style="width: 98%; margin: 0 auto">
							<thead>
								<tr class="active">
									<th>标题</th>
									<th>难度</th>
									<th>年级</th>
									<th>科目</th>
									<th>时间</th>
									<th>使用次数</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody id="tbody">
							   <!--试卷内容  -->
							</tbody>
							</table>
						<div id="pages" style="text-align:center;"></div>
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
			<div class="modal-dialog" style="width: 80%;">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
						<h4 class="modal-title" id="myModalLabel">添加普通考试对象</h4>
					</div>
					<div class="modal-body">
						<div class="layui-form-item" style="margin-top: 10px;">
							
							<div class="layui-inline">
								<label class="layui-form-label" style="width: 100px;">校区</label>
								<div class="layui-input-inline" style="width: 120px;">
									<select name="schoolId" id="schoolId">
									    <option value="">全部</option>
										<option value="">全部</option>
										<c:forEach items="${sZones}" var="s">
											<option value="${s.schoolId}">${s.schoolName}</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<div class="layui-inline">
								<label class="layui-form-label" style="width: 100px;">科目</label>
								<div class="layui-input-inline" style="width: 120px;">
									<select name="subjectId" id="subjectId">
									    <option value="">全部</option>
										<option value="">全部</option>
										<c:forEach items="${sList}" var="s">
											<option value="${s.subjectId}">${s.subjectName}</option>
										</c:forEach>
									</select>
								</div>
							</div>
							 <div class="layui-inline">
								<label class="layui-form-label"
									style="width: 100px; font-size: 14px;">班级名称</label>
								<div class="layui-input-inline" style="width: 150px;">
									<input type="text" name="className" id="className"
										value="${className}" placeholder="请输入试卷名称"
										class="layui-input">
								</div>
								<span class="layui-btn layui-btn-normal searchbtn"
									id="search111">搜索</span>
							</div> 
						</div>
						<table class="layui-table" style="width: 98%; margin: 0 auto">
							<thead>
								<tr class="active">
									<th>校区</th>
									<th>类型</th>
									<th>科目</th>
									<th>班级名称</th>
									<th>人数</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody id="tbody1">
							</tbody>
						</table>
						<div id="page11" style="text-align: center;"></div>
					</div>
					<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal" style="height:30px;line-height:30px;padding:0 18px;">关闭</button>
				</div>
				</div>
			</div>
		</div>
		<!--模态框结束-->
	</form>
	<script type="text/html" id="class_template">
		{{each objects as value i}}
		<tr>
			<td>{{value.schoolZonelist[0].schoolName}}</td>
			<td>{{value.seasonTypelist[0].seasonName}}</td>
			<td>{{value.subjectlist[0].subjectName}}</td>
			<td>{{value.className}}</td>
			<td>{{value.classNumber}}</td>
			<td>
				<button type="button" class="layui-btn add_note" 
                  data-id="{{value.classId}}"
					data-name="{{value.className}}"
					style="padding: 0 10px; height: 25px; line-height: 25px; font-size: 10px;">
					添加</button>
			</td>
		</tr>
		{{/each}}
	</script>
	<script type="text/html" id="exam_template">
		{{each objects as value i}}
                <tr class="roomlist"> 
				<td>{{value.piName}}</td>
                   <td>
				
					{{if value.difficultyValue==1}}
						<ul class="uls_showstars">
						<li class="icon-star" id="1" style="cursor: pointer;"></li>
						<li class="icon-star-empty" id="2" style="cursor: pointer;"></li>
						<li class="icon-star-empty" id="3" style="cursor: pointer;"></li>
						<li class="icon-star-empty" id="4" style="cursor: pointer;"></li>
						<li class="icon-star-empty" id="5" style="cursor: pointer;"></li>
						</ul>
					{{/if}}
					{{if value.difficultyValue==2}}
						<ul class="uls_showstars">
						<li class="icon-star" id="1" style="cursor: pointer;"></li>
						<li class="icon-star" id="2" style="cursor: pointer;"></li>
						<li class="icon-star-empty" id="3" style="cursor: pointer;"></li>
						<li class="icon-star-empty" id="4" style="cursor: pointer;"></li>
						<li class="icon-star-empty" id="5" style="cursor: pointer;"></li>
						</ul>
					{{/if}}
					{{if value.difficultyValue==3}}
						<ul class="uls_showstars">
						<li class="icon-star" id="1" style="cursor: pointer;"></li>
						<li class="icon-star" id="2" style="cursor: pointer;"></li>
						<li class="icon-star" id="3" style="cursor: pointer;"></li>
						<li class="icon-star-empty" id="4" style="cursor: pointer;"></li>
						<li class="icon-star-empty" id="5" style="cursor: pointer;"></li>
						</ul>
					{{/if}}
					{{if value.difficultyValue==4}}
						<ul class="uls_showstars">
						<li class="icon-star" id="1" style="cursor: pointer;"></li>
						<li class="icon-star" id="2" style="cursor: pointer;"></li>
						<li class="icon-star" id="3" style="cursor: pointer;"></li>
						<li class="icon-star" id="4" style="cursor: pointer;"></li>
						<li class="icon-star-empty" id="5" style="cursor: pointer;"></li>
						</ul>
					{{/if}}
					{{if value.difficultyValue==5}}
						<ul class="uls_showstars">
						<li class="icon-star" id="1" style="cursor: pointer;"></li>
						<li class="icon-star" id="2" style="cursor: pointer;"></li>
						<li class="icon-star" id="3" style="cursor: pointer;"></li>
						<li class="icon-star" id="4" style="cursor: pointer;"></li>
						<li class="icon-star" id="5" style="cursor: pointer;"></li>
						</ul>
					{{/if}}
                 </td>
                <td>{{value.grade2.gradeName}}</td>
				<td>{{value.subject2.subjectName}}</td>
				<td>{{(value.createTime).substring(0,10)}}</td>
				<td>{{value.usedTimes}}次</td>
                <td><button type="button" class="layui-btn add_paper"
					data-id="{{value.piId}}" data-name="{{value.piName}}"
					style="padding: 0 10px;">
					添加</button></td>
                </tr>
		{{/each}}
	</script>
</body>
<script type="text/javascript" src=".././js/jquery.min.js"></script>
<script src=".././layui/layui.js" charset="utf-8"></script>
<script type="text/javascript" src=".././js/bootstrap.min.js"></script>
<script type="text/javascript" src=".././js/artTemplate-simple-3.0.js"></script>

<script type="text/javascript">
	var pageno = 1, pagesize = 5;
	var type, science, grade, start, end, subject;
	layui
			.use(
					[ 'form', 'layedit', 'laypage', 'laydate' ],
					function() {
						 form = layui.form(), laypage = layui.laypage, layer = layui.layer, layedit = layui.layedit, laydate = layui.laydate;

						//创建一个编辑器
						var editIndex = layedit.build('LAY_demo_editor');

						form.on('select(select_type)', function(data) {
							type = data.value;
							ajax_exam(pageno,type,grade,subject);

						});

						form.on('select(select_knowend)', function(data) {
							var value = data.value.substr(
									data.value.length - 1, 1);
							var start = $('#knowledgeBegin').val().substr(
									$('#knowledgeBegin').val().length - 1, 1);
							if (parseInt(value) <= parseInt(start)) {
								layer.msg("请正确选择知识范围");
								return;
							}
							end = value;
							ajax_exam(pageno,type,grade,subject);

						});

						form.on('radio(exam_radio)', function(data) {
							if (data.value == "入学考试对象") {
								$("#stu_object").val(1);
								$('#class_object').val('');
								var html = '<tr style="cursor:pointer;"><td>入学考试对象</td></tr>';
								$('#tbody2').html(html);
							} else if (data.value == "普通考试对象") {
								$('#myModal1').modal('show');
							}
						});

						form.on('select(select_knowstart)', function(data) {
							var value = data.value.substr(
									data.value.length - 1, 1);
							var end = $('#knowledgeEnd').val().substr(
									$('#knowledgeEnd').val().length - 1, 1);
							if (parseInt(value) >= parseInt(end)) {
								layer.msg("请正确选择知识范围");
								return;
							}
							start = value;
							ajax_exam(pageno,type,grade,subject);

						});

						form.on('select(select_grade)', function(data) {
							grade = data.value;
							ajax_exam(pageno,type,grade,subject);

						});

						form.on('select(select_subject)', function(data) {
							subject = data.value;
							ajax_exam(pageno,type,grade,subject);

						});

						
						

						//自定义验证规则
						form.verify({
							taskType : function(value) {
								if (value.length == "") {
									return '请选择任务类型';
								}
							},

							taskTitle : function(value) {
								if (value.length == "") {
									return '请填写任务主题';
								}
							},
							
							requirement : function(value) {
								if (value.length == "") {
									return '请填写任务要求';
								}
							},
// 							taskCount : function(value) {
// 								if (value.length == "") {
// 									return '请选择任务内容';
// 								}
// 							},
							

							startTime : function(value) {
								if (value.length == '') {
									return '请选择开始时间';
								} 
							},
							endTime : function(value) {
								if (value.length == "") {
									return '请选择排课时间';
								} 
							},
							rule : function(value) {
								if (value.length == "") {
									return '请选任务类型';
								}
							},
						});

						//监听提交
						form.on('submit(demo1)', function(data) {
							var ruleId = $('#rule').val();
								$.ajax({
									url:'courseisexist.do',
									type:'post',
									dataType:'json',
									data:{ruleId:ruleId},
									success:function(res) {
										if(res.code==0) {
											$('#myform').submit();
										} else {
											layer.msg(res.msg);
											return false;
										}
									}
								})
							return false;
						});
					});
	$('.icon-plus').on('click', function() {
		type=$('#taskType').val();
		ajax_exam(pageno,type);
	});


	function ajax_exam(pageno,type,grade,subject) {
		$.ajax({
			url : '${ctx}/exam/getpaperbyitem1.do',
			type : 'post',
			dataType : 'json',
			data : {pageNo:pageno,pageSize:10,type:type,grade:grade,subject:subject},
			success : function(res) {
				if (res.code == 0) {
					var html = template("exam_template", res);
					$('#tbody').html(html);
					$('#myModal').modal('show');
					click_content();
					if (res.count > 1) {
						laypage({
							cont : 'pages',
							pages : res.count,
							skin : 'molv',
							curr : pageno || 1,//当前页
							groups : 5,
							skip : true,
							jump : function(obj, first) {
								//触发分页后的回调
								if (!first) {
									//点击跳页触发函数自身，并传递当前页：obj.curr
									ajax_exam(obj.curr,type,grade,subject);
								}
							}
						});
					}
				} else {
					var html = template("exam_template", res);
					$('#tbody').html(html);
					layer.msg(res.msg,{time:5000});
					return;
				}
			}
		})
	}
	$('#search').on('click', function() {
		var content = $('#examname').val();
		if (content == "") {
			layer.msg("请输入查询的试卷名称");
		} else {
			$.ajax({
				url : '${ctx}/exam/getpaperbycontent.do',
				type : 'post',
				dataType : 'json',
				data : {
					content : content
				},
				success : function(res) {
					if (res.code == 0) {
						console.log(JSON.stringify(res));
						var html = template("exam_template", res);
						$('#tbody').html(html);
						$('#myModal').modal('show');
						click_content();
					} else {
						layer.msg(res.msg);
					}
				}
			})
		}
	})
	//点击模态框的试卷事件
	function click_content() {
		$('.add_paper').click(function() {
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
	//添加考试对象
	function click_addobject() {
		$('.add_note').click(function(){
			var id = $(this).attr("data-id");
			var name = $(this).attr("data-name");
			var val1 = $('#tag1').val();
			var tag1s = val1.split(",");
			let status=0;
			for(var i=0;i<tag1s.length;i++) {
				if(tag1s[i]==id) {
					layer.msg("抱歉，您已经添加了该班级",{time:5000});
					status=1;
					break;
				}
			}
			if(status==0) {
				layer.confirm('确认添加该班级吗？', {
					btn : [ '确定' ]
				}, function(index, layero) {//确定的处理
					$('#myModal1').modal('hide');
					var html = "<tr id=tags"+id+" style='cursor:pointer;margin-bottom:20px;'><td style='height:30px;line-height:40px;'><span style='padding:5px 10px;border:1px solid #e6e6e6;border-radius:4px'>"
					+ name + "</span></td></tr>"

					$('#tbody2').append(html);
					if (val1 == "") {
						$('#tag1').val(id);
					} else {
						$('#tag1').val(val1 + "," + id);
					}
					object_tb_click(id);
					layer.closeAll();
				});
			}
		})
		layer.closeAll();
	}
	
	//不是添加模板的删除操作
	$('.tag_tr1').click(function() {
		var that = this;
		var id = $(that).attr("id");
		let
		val = $('#tag1').val();
		let
		vals = val.split(",");
		layer.confirm('确认删除该班级吗？', {
			btn : [ '确定' ]
		}, function(index, layero) {//确定的处理
			if (val.indexOf(",") > 0) {
				if (vals[vals.length - 1] == id) {
					val = val.replace("," + id, '');
				} else {
					val = val.replace(id + ",", '');
				}
			} else {
				val = val.replace(id, '');
			}
			$('#tag1').val(val);
			$(that).remove();
			layer.close(index);
		});
	})
	
	
	
		function object_tb_click(id) {
		$('#'+id).click(function(res) {
			var val = $('#class_object').val();
// 			alert("一开始的classid"+val);
			layer.confirm('确认删除该对象吗？', {
				btn : [ '确定' ]
			}, function(index, layero) {//确定的处理
				val = val.replace(id,'');
				$('#class_object').val(val);
// 				alert("后面的classid："+val);
				$('#'+id).remove();
				layer.close(index);
			});
			
		})
	}
	
		$('#search111').on('click', function() {
		 className = $('#className').val();
		 schoolId=$('#schoolId').val();
		 subject=$('#subjectId').val();
			$.ajax({
				url : '${ctx}/classes/ajaxclassmanage.do', 
				type : 'post',
				dataType : 'json',
				data : {
					className : className,
					schoolId:schoolId,
					subject:subject
				},
				success : function(res) {
					if (res.code == 0) {
						var html = template('class_template', res);
						$('#tbody1').html(html);
						$('#myModal1').modal('show');
						click_addobject();
// 						form.render('select');	
					} else {
						layer.msg(res.msg,{time:5000});
						var html = template('class_template', res);
						$('#tbody1').html(html);
						$('#myModal1').modal('show');
// 						click_addobject();
						return false;
					}
				}
			})
	})
	function openModal(){
		
		getClass(pageno);
		$("#myModal1").modal('show');
		
	}
	
	function getClass(pageno) {
							$.getJSON("${ctx}/classes/ajaxclassmanage.do", {
								pageNo : pageno,
								pageSize : 10
							}, function(res) {
								if (res.code == 0) {
									var html = template('class_template', res);
									$('#tbody1').html(html);
									click_addobject();
									if (res.count > 1) {
										laypage({
											cont : 'page11',
											pages : res.count,
											skin : 'molv',
											curr : pageno || 1,//当前页
											groups : 5,
											skip : true,
											jump : function(obj, first) {
												//触发分页后的回调
												if (!first) {
													//点击跳页触发函数自身，并传递当前页：obj.curr
													getClass(obj.curr);
												}
											}
										});
									}
								} else if (res.code == 1) {
									var html = template('class_template', res);
									$('#tbody1').html(html);
									layer.msg("您暂时未分配到班级");
								}
							})
						}
</script>
</html>