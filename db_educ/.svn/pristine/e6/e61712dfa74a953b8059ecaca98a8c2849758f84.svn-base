<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>考试管理</title>
<link rel="stylesheet" href=".././layui/css/layui.css" />
<link rel="stylesheet" href=".././css/bootstrap.min.css" />
<link rel="stylesheet" href=".././css/lecturenote/style.css" />
<link rel="stylesheet" href=".././css/font-awesome.min.css" />
<script type="text/javascript" src=".././js/jquery.min.js"></script>
<script type="text/javascript" src=".././js/bootstrap.min.js"></script>
<style type="text/css">
.pagelist a{padding:6px 18px;border:1px solid #e1e2e3;border-radius:4px;}
.pagelist .current{background:#008bca !important;color:#fff}
.pagelist a:hover{background:#008bca !important;color:#fff}
#pages{height:50px;line-height:50px;}
.icon-star,.icon-star-empty{color:#333 !important}
ol, ul{margin-bottom:0;}
.layui-laypage a, .layui-laypage span{margin-right:5px;padding:0 18px;border-radius:4px;font-size:14px;}
.layui-laypage .layui-laypage-curr .layui-laypage-em{border-radius:4px;}
.layui-laypage>:first-child, .layui-laypage>:first-child em{border-radius:4px;}
.layui-laypage button, .layui-laypage input{border-radius:4px;}
</style>
</head>
<body>
	<form id="myform" class="layui-form" action="exammanage.do">
		<div class="panels_head">
			<span>考试管理</span>
		</div>	
		<div class="layui-form-item" style="margin-top: 20px;">
			<div class="layui-inline">
				<label class="layui-form-label" style="font-size:14px;">日期</label>
				<div class="layui-input-inline" style="width: 150px;">
					<select name="date" id="date" lay-filter="select_date">
						<option value="">全部</option>
						<option value="">全部</option>
						<c:forEach items="${dateList}" var="s">
							<option value="${s.courseDate}"
								<c:if test="${date eq s.courseDate}">selected="selected"</c:if>>${s.courseDate}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="layui-inline">
				<label class="layui-form-label" style="font-size:14px;">班级</label>
				<div class="layui-input-inline" style="width: 150px;">
					<select name="classId" id="classId" lay-filter="select_class">
						<option value="">全部</option>
						<option value="">全部</option>
						<c:forEach items="${classList}" var="qt">
							<option value="${qt.classId}"
								<c:if test="${classId eq qt.classId}">selected="selected"</c:if>>${qt.className}</option>
						</c:forEach>
					</select>
<%-- 					<c:forEach items="${classList}" var="qt"> --%>
<%-- 						<c:if test="${classId eq qt.classId}"> --%>
<%-- 							<input hidden="" id="subject" value="${qt.subject}" /> --%>
<%-- 							<input hidden="" id="grade" value="${qt.grade}" /> --%>
<%-- 						</c:if> --%>
<%-- 					</c:forEach> --%>
				</div>
				<button class="layui-btn layui-btn-normal searchbtn" lay-submit="" lay-filter="demo1">搜索</button>
			</div>
		</div>
		<c:choose>
			<c:when test="${courseList==null}">
				<div class="no_data_div"></div>
				<p class="no_data_p" style="font-size:14px;">~暂无数据~</p>
			</c:when>
			<c:otherwise>
				<table class="layui-table"
					style="width: 98%; margin-left: auto; margin-right: auto;">
					<thead>
						<tr>
							<th>日期/时间</th>
							<th>年级</th>
							<th>班级</th>
							<th>科目</th>
							<th>时间</th>
							<th>内容</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${courseList}" var="lecture">
							<c:if test="${lecture.getCourseDate()!=null}">
								<tr>
									<td>${lecture.getCourseDate()}</td>
									<td>${lecture.getClasslist().get(0).getGradelist().get(0).getGradeName()}</td>
									<td>${lecture.getClasslist().get(0).getClassName()}</td>
									<td>${lecture.getClasslist().get(0).getSubjectlist().get(0).getSubjectName()}</td>
									<td><c:if test="${lecture.getCourseTimes() eq 1}">
								08:00-09:30
							</c:if> <c:if test="${lecture.getCourseTimes() eq 2}">
								09:35-11:05
							</c:if> <c:if test="${lecture.getCourseTimes() eq 3}">
								11:10-12:40
							</c:if> <c:if test="${lecture.getCourseTimes() eq 4}">
								13:50-15:20
							</c:if> <c:if test="${lecture.getCourseTimes() eq 5}">
								15:25-16:55
							</c:if> <c:if test="${lecture.getCourseTimes() eq 6}">
								17:00-18:30
							</c:if></td>
									<td><c:if test="${lecture.getExamId() eq null}">
											<p>还未添加考试</p>
										</c:if> <c:if test="${lecture.getExamId()!=''}">
											<p>${lecture.getPaperInfo().getPiName()}</p>
										</c:if></td>
									<td><c:choose>
											<c:when test="${lecture.getExamId() eq null}">
												<p>
													<span class="icon-plus-sign-alt"
														data-id="${lecture.getCourseId()}"
														data-classid="${lecture.getClassId()}"
														data-subject="${lecture.getPaperInfo().getSubject()}"
														data-date="${lecture.courseDate}"
														style="font-size: 14px; color: #008bca; cursor: pointer;">设置</span>
												</p>
											</c:when>
											<c:otherwise>
												<c:if test="${lecture.getPaperRecord().getRecordId()==null}">
													<p>
														<span class="icon-plus-sign-alt"
															data-id="${lecture.getCourseId()}"
															data-classid="${lecture.getClassId()}"
															data-subject="${lecture.getPaperInfo().getSubject()}"
															data-date="${lecture.courseDate}"
															style="font-size: 14px; color: #008bca; cursor: pointer;">替换</span>
													</p>
													<p>
														<span data-toggle="modal" id="openexam"
															class="openexam icon-bullhorn"
															data-classid="${lecture.getClassId()}"
															data-paperid="${lecture.getPaperInfo().getPiId()}"
															data-papertype="${lecture.getPaperInfo().getPiType()}"
															data-subject="${lecture.getPaperInfo().getSubject()}"
															data-courseid="${lecture.getCourseId()}"
															data-date="${lecture.courseDate}"
															style="font-size: 14px; color: #008bca; cursor: pointer;">启动</span>
													</p>
													<p>
														<span data-toggle="modal" id="deleteexam"
															class="icon-bullhorn"
															data-classid="${lecture.getClassId()}"
															data-courseid="${lecture.getCourseId()}"
															data-date="${lecture.courseDate}"
															style="font-size: 14px; color: #008bca; cursor: pointer;">撤回</span>
													</p>
												</c:if>
												<c:if test="${lecture.getPaperRecord().getStatus()==1}">
													<p>
														<span data-toggle="modal" class="closeexam icon-bullhorn"
															id="closeexam" data-classid="${lecture.getClassId()}"
															data-paperid="${lecture.getPaperInfo().getPiId()}"
															data-paperrecord="${lecture.getPaperRecord().getRecordId()}"
															data-date="${lecture.courseDate}"
															style="font-size: 14px; color: #008bca; cursor: pointer;">结束</span>
													</p>
												</c:if>
												<c:if test="${lecture.getPaperRecord().getStatus()==0}">
													<p style="color: #ff5722">已结束</p>
												</c:if>
											</c:otherwise>
										</c:choose></td>
								</tr>
							</c:if>
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
		<!--模态框-->
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog" style="width: 80%;">
				<div class="modal-content">
				<div class="modal-header">
			        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			        <h4 class="modal-title" id="myModalLabel">设置考试</h4>
                     </div>
					<div class="modal-body">
						<table class="layui-table"
							style="width: 98%; margin-left: auto; margin-right: auto; margin-top: 10px;">							
							<thead>
								<tr>
									<th style="text-align: center;">知识范围</th>
									<th style="text-align: center;">试卷科目</th>
									<th style="text-align: center;">试卷类型</th>
									<th style="text-align: center;">试卷题目</th>
									<th style="text-align: center;">试卷难度</th>
									<th style="text-align: center;">使用次数</th>
									<th style="text-align: center;">创建时间</th>
									<th style="text-align: center;">操作</th>
								</tr>
							</thead>
							<tbody id="tbody1">
							</tbody>
						</table>
						<div id="page" style="text-align: center;"></div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal" style="height:30px;line-height:30px;padding:0 18px;">关闭</button>
					</div>
				</div>
			</div>
		</div>
		<!--模态框结束-->
	</form>
	<script type="text/html" id="exam_template">
		{{each objects as value i}}
		<tr>
			<td>{{value.grade2.gradeName}}</td>
			<td>{{value.subject2.subjectName}}</td>
			<td>{{value.paperType.taskTypeName}}</td>
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
			<td>{{value.usedTimes}}</td>
			<td>{{value.createTime.substring(0,10)}}</td>
			<td>
				<button type="button" class="layui-btn layui-btn-normal add_note"
					data-id="{{value.piId}}" data-type="{{value.piType}}"
					data-subject="{{value.subject}}" data-courseid=""
					data-classid=""
					style="height:30px;line-height:30px;padding:0 18px;">
					添加</button>
			</td>
		</tr>
		{{/each}}
	</script>
</body>
<script type="text/javascript" src=".././js/artTemplate-simple-3.0.js"></script>
<script src=".././layui/layui.js" charset="utf-8"></script>
<script>
	var pageno = 1, pagesize = 5;
	var status=0;//判断列表状态
	layui.use([ 'form', 'layedit','laypage', 'laydate', 'layer' ], function() {
		var form = layui.form();
		var layer = layui.layer;
		var laypage = layui.laypage;
		var layedit = layui.layedit;
		var laydate = layui.laydate;
		var layer = layui.layer;

		//创建一个编辑器
		var editIndex = layedit.build('LAY_demo_editor');

		//自定义验证规则
		form.verify({
			title : function(value) {
				if (value.length < 5) {
					return '标题至少得5个字符啊';
				}
			},
			pass : [ /(.+){6,12}$/, '密码必须6到12位' ],
			content : function(value) {
				layedit.sync(editIndex);
			}
		});
		
// 		form.on('select(select_date)', function(data) {
// 			$('#myform').submit();
// 		});
		
// 		form.on('select(select_class)', function(data) {
// 			$('#myform').submit();
// 		});

		//监听指定开关
		form.on('switch(switchTest)', function(data) {
			layer.msg('开关checked：' + (this.checked ? 'true' : 'false'), {
				offset : '6px'
			});
			layer.tips('温馨提示：请注意开关状态的文字可以随意定义，而不仅仅是ON|OFF', data.othis)
		});
		//监听提交
		form.on('submit(demo1)', function(data) {

			return;
		});
		getExam(pageno);
		function getExam(pageno) {
			$.getJSON("getpublicexam.do", {
				pageNo : pageno,
				pageSize : pagesize,
				type:10,
				subject : $('#subject').val(),
				grade : $('#grade').val()
			}, function(res) {
				if (res.code == 0) {
					status=1;
					var html = template('exam_template', res);
					$('#tbody1').html(html);
					click_addexam();
					if (res.count > 1) {
						laypage({
							cont : 'page',
							pages : res.count,
							skin : 'molv',
							curr : pageno || 1,//当前页
							groups : 5,
// 							skip : true,
							jump : function(obj, first) {
								//触发分页后的回调
								if (!first) {
									//点击跳页触发函数自身，并传递当前页：obj.curr
									getExam(obj.curr);
								}
							}
						});
					}
				} else {
// 					layer.msg(res.msg);
				}
			})
		}
	});
	$('.icon-plus-sign-alt').on('click',function() {
		var courseid = $(this).attr("data-id");
		var classid = $(this).attr("data-classid");
		$('.add_note').attr('data-courseid',courseid);
		$('.add_note').attr('data-classid',classid);
		if(status==1) {
			$('#myModal').modal('show');
		} else {
			layer.msg("暂时没有对应的试卷，请前往试卷列表添加");
		}
	});
	click_addexam();
	function click_addexam() {
		$('.add_note').on('click',function(){
			var courseid = $(this).attr('data-courseid');
			var type = $(this).attr('data-type');//试卷类型
			var subject = $(this).attr('data-subject');//科目
			var examid = $(this).attr('data-id');
			var classid = $(this).attr('data-classid');
			layer.confirm('确认添加该考卷吗？', {
				btn : [ '确定' ]
			}, function(index, layero) {//确定的处理,暂未添加到记录表
				$.ajax({
					url:'updateCourse.do',
					type:'post',
					dataType:'json',
					data:{courseId:courseid,examId:examid,subject:subject,type:type,classid:classid},
					success:function(res) {
						 if(res.code ==0) {
							window.location.href="exammanage.do?classId="+classid+"&date="+$('#date').val();
						} else {
							layer.msg(res.msg,{time:3000});
						}
					}
				})
				layer.close(index);
			});
		})
	}
	
	$('.openexam').on('click',function(){
		var classid = $(this).attr('data-classid');
		var examid = $(this).attr('data-paperid');
		var type = $(this).attr("data-papertype");
		var subject = $(this).attr("data-subject");
		var courseid = $(this).attr("data-courseid");
		var date = $(this).attr("data-date");
		layer.confirm('确认启动考试吗？', {
			btn : [ '确定' ]
		}, function(index, layero) {//确定的处理
			$.ajax({
				url:'startExam.do',
				type:'post',
				dataType:'json',
				data:{classid:classid,paperid:examid,type:type,subject:subject,courseid:courseid},
				success:function(res) {
					if(res.code ==1) {
						layer.msg(res.msg);
					} else if(res.code ==0) {
						window.location.href="exammanage.do?classId="+classid+"&date="+date;
						window.location.reload();
					}
				}
			})
			layer.close(index);
		});
	});
	
	$('#deleteexam').on('click', function(){
		var classid = $(this).attr('data-classid');
		var courseid = $(this).attr("data-courseid");
		var date = $(this).attr("data-date");
		layer.confirm('确认撤回考试吗？', {
			btn : [ '确定' ]
		}, function(index, layero) {//确定的处理
			$.ajax({
				url:'deleteExam.do',
				type:'post',
				dataType:'json',
				data:{courseid:courseid},
				success:function(res) {
					if(res.code ==1) {
						layer.msg("删除失败！请重试");
					} else if(res.code ==0) {
						window.location.href="exammanage.do?classId="+classid+"&date="+date;
						window.location.reload();
					}
				}
			})
			layer.close(index);
		});
	})
	
	$('.closeexam').on('click',function(){
		var classid = $(this).attr('data-classid');
		var examid = $(this).attr('data-paperid');
		var recordid = $(this).attr("data-paperrecord");
		var date = $(this).attr("data-date");
		layer.confirm('确认结束考试吗？', {
			btn : [ '确定' ]
		}, function(index, layero) {//确定的处理
			$.ajax({
				url:'endExam.do',
				type:'post',
				dataType:'json',
				data:{classid:classid,paperid:examid,recordid:recordid},
				success:function(res) {
					if(res.code ==1) {
						layer.msg("结束失败!请重试");
					} else if(res.code ==0) {
						window.location.href="exammanage.do?classId="+classid+"&date="+date;
						window.location.reload();
					}
				}
			})
			layer.close(index);
		});
	});
	function goPageAction(page){
		window.location.href="${ctx}/lesson/exammanage.do?pageNo="+page+"&classId=${classId}&date=${date}";
		
		}
</script>
</html>