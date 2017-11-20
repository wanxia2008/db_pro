<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>寒暑假排课</title>
<link rel="stylesheet" href=".././layui/css/layui.css" />
<link rel="stylesheet" href=".././css/bootstrap.min.css" />
<link rel="stylesheet" href=".././css/lecturenote/style.css" />
<link rel="stylesheet" href=".././css/font-awesome.min.css" />
<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/pepper-grinder/jquery-ui.css" />
<link rel="stylesheet" href=".././mulitDatePicker/jquery-ui.multidatespicker.css" />
<style type="text/css">
.schedule_content {position:relative;}
.schedule_content .icon-remove-circle {
	font-size:23px;
	color:red;
	z-index:10000;
	position:absolute;
	right:-5px;
	bottom:-10px;
}
</style>
</head>
<body>
	<form class="layui-form" action="getLessonBysummer.do">
		<div class="paenls_content2">
			<div style="width: 20%; position: relative; background: #EEEEEE;">
			    
			    <div class="layui-form-item" style="margin-left:65px;height:30px;margin-top:30px;">
					<div class="layui-inline" style="width: 130px;">
						<select name="schoolYear" id="schoolYear">
							<option value="">全部</option>
							<option value="${schoolYears}">全部</option>
							<c:forEach items="${schoolList}" var="sy">
								<option value="${sy.id}">${sy.year }</option>
							</c:forEach>
						</select>
					</div>
				</div>
			
				<div class="layui-form-item"
					style="margin-left: 65px; height: 30px;">
					<div class="layui-inline" style="width: 130px;">
						<select name="subject" id="subject">
							<option value="">全部</option>
							<option selected="selected" value="${subject}">全部</option>
							<c:forEach items="${subjectList}" var="subjects">
								<option value="${subjects.getSubjectId()}">${subjects.getSubjectName()}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				
				<div class="layui-form-item"
					style="margin-left: 65px; height: 30px;">
					<div class="layui-inline" style="width: 130px;">
						<select name="seasontype" id="seasontype">
							<option value="">全部</option>
							<option value="${seasontype}">全部</option>
							<c:forEach items="${seasonList}" var="season">
								<c:if test="${season.getSeasonId()==3 or season.getSeasonId()==4 or season.getSeasonId()==5}">
									<option value="${season.getSeasonId()}">${season.getSeasonName()}</option>
								</c:if>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="layui-form-item"
					style="margin-left: 65px; height: 20px;">
					<div class="layui-inline" style="width: 130px;">
						<select name="grade" id="grade">
							<option value="">全部</option>
							<option value="">全部</option>
							<c:forEach items="${gradeList}" var="grades">
								<option value="${grades.getGradeId()}">${grades.getGradeName()}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="layui-inline"
					style="float: left; margin-right: 20px;margin-left: 65px;margin-top: 10px;">
					
					<button style="width:130px;" type="button" class="layui-btn layui-btn-normal" id="choose_class">筛选</button>
				</div>
				<table class="layui-table" lay-skin="row"
					style="width: 50%; margin-left: 65px; margin-top:90px;">
					<tbody id="tbody">
						<c:forEach items="${classList}" var="classes">
							<tr class="class_tr" style="cursor:pointer;">
								<td class="click_class" data-click="0" data-classid="${classes.getClassId()}" data-season="${classes.getSeasonTypelist().get(0).getSeasonName()}">${classes.getClassName()}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<div style="width: 80%;">
                   <div class="layui-form-item"
					style="margin-left: 50px; height: 30px; margin-top: 30px;">
<!-- 					<input hidden="" type="text" id="altField">  -->
					<div class="layui-inline">
						<div class="layui-input-inline" style="width:120px;">
							<input type="text" name="curriculumDate" id="curriculumDate" lay-verify="curriculumDate"
								placeholder="开始时间" value="${courseDate}" autocomplete="off" class="layui-input"
								onclick="layui.laydate({elem: this})">
						</div>
					</div>
					<div class="layui-inline">
					
						<button class="layui-btn layui-btn-normal" lay-submit="" lay-filter="demo1">选择排课日期</button>
					</div>

				</div>
				<c:if test="${courseDate!=null}">
				<div id="holiday_page" style="width:20%;height:30px;margin:0 auto;">
					<span class="toleft" style="width:20px;height:20px;float:left;cursor:pointer;background: url('${ctx}/image/toleft.png') center no-repeat;background-size:100% 100%;"></span>
					<span id="coursedate" style="text-align: center;margin-left:25%;">${courseDate}</span>
					<input hidden="" type="number" id="totalPage" value="${totalPage}" />
					<input hidden="" type="number" id="pageNo" value="${pageNo}" />
					<span class="toright" style="width:20px;height:20px;float:right;cursor:pointer;background: url('${ctx}/image/toright.png') center no-repeat;background-size:100% 100%;"></span>
				</div>
				</c:if>
				<!--表格-->
				<div class="table-responsive"
					style="width: 95%; margin-left: auto; margin-right: auto; padding-top: 30px;">
					<table class="table table-bordered">
						<tr class="active">
							<th>教室</th>
							<th>08: 00-09: 30</th>
							<th>09: 35-11: 05</th>
							<th>11：10-12：40</th>
							<th>13：00-15：20</th>
							<th>15：25-16：55</th>
							<th>17：00-18：30</th>
						</tr>
						<c:forEach items="${classroomList}" var="classroom">
							<tr id="${classroom.getClassroomId()}">
								<!-- xulian要求改成不显示班级名称  byzhangzhaojian  2017/11/16
								<td>${classroom.getClassroomName()}-${classroom.getMaxNumber()}</td> -->
								
								<td>${classroom.getClassroomName()}教室</td>
								<c:forEach begin="1" end="6" var="i">
									<td class="lesson_td" data-roomid="${classroom.getClassroomId()}" data-timeid="${i}" style="cursor:pointer">
										<c:forEach items="${courseList}" var="course">
											<c:if test="${course.getRoomId() eq classroom.getClassroomId() && course.getCourseTimes() eq i}">
												<div class="schedule_content" style="width: 140px;">
													<i class="icon-remove-circle" id="${course.courseId}"></i>
													<p>寒暑假-${course.grade.gradeName}-${course.getClasslist().get(0).getClassName()}</p>
													<p>${course.getClasslist().get(0).getSubjectlist().get(0).getSubjectName()}</p>
												</div>
											</c:if>
										</c:forEach>
									</td>
								</c:forEach>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div>
		</div>
	</form>
	<script id="class_template" type="text/html">
		{{each objects as value i}}
			<tr class="class_tr" style="cursor:pointer;">
				<td class="click_class" data-click="0" data-classid="{{value.classId}}"
					 data-season="{{value.seasonTypelist[0].seasonName}}">{{value.className}}</td>
			</tr>
		{{/each}}
	</script>
</body>
<script type="text/javascript" src=".././js/jquery.min.js"></script>
<script type="text/javascript" src=".././mulitDatePicker/jquery-ui.min.js"></script>
<script type="text/javascript" src=".././mulitDatePicker/jquery-ui.multidatespicker.js"></script>
<script src=".././layui/layui.js" charset="utf-8"></script>
<script type="text/javascript" src=".././js/artTemplate-simple-3.0.js"></script>
<script type="text/javascript">
	var classid,status=0;//判断是否修改过日期
	window.onload=function(){
	    var myDiv = document.getElementById("datepicker");
	    document.addEventListener("click",function(){
	        myDiv.style.display="none";
	    });
	    myDiv.addEventListener("click",function(event){
	        event=event||window.event;
	        event.stopPropagation();
	    });
	};
	
// 	$(document).click(function(){
// 		$('.datepicker').css('display','none');
// 	});
	layui.use([ 'form', 'laydate' ], function() {
		var form = layui.form(), layer = layui.layer, laydate = layui.laydate;
		form.verify({
			curriculumDate : function(value) {
				if(value.length=="") {
					return "您还没有选择日期呢╮(╯﹏╰）╭";
				}
			}
		});
		//监听提交
		form.on('submit(demo1)', function(data) {
			return true;
		});
	});
	clickclass();
	function clickclass(){
		$('.click_class').on('click',function(){
			classid = $(this).attr("data-classid");
			$(this).parents('.class_tr').css('background','#008bca');
			$(this).parents('.class_tr').css('color','#fff');
			$(this).parents('.class_tr').siblings().css('background','#ffffff');
			$(this).parents('.class_tr').siblings().css('color','#333');
		});
	}
	$('#choose_class').on('click',function(){
		var subject = $('#subject').val();
		var grade = $('#grade').val();
		var type = $('#seasontype').val();
		var schoolYear=$('#schoolYear').val();
	if (subject == "") {
			subject = null;
		}
		if (grade == "") {
			grade = null;
		}
		if (type == "") {
			type = null;
		}
		if(schoolYear==""){
			schoolYear=null;
		}
		
		$.ajax({
			url : 'getHolidaySubject.do',
			type : 'post',
			dataType : 'json',
			data : {
				subjectid : subject,
				gradeid : grade,
				seasonid : type,
				schoolYear:schoolYear
			},
			success : function(res) {
				if (res.code == 1) {
					layer.msg("没有找到班级哦");
					$('#tbody').html();
					var html = template("class_template", res);
					$('#tbody').html(html);
					clickclass();
				} else if (res.code == 0) {
					$('#tbody').html();
					var html = template("class_template", res);
					$('#tbody').html(html);
					clickclass();
				}
			}
		})
	});
	$('.lesson_td').on('click', function() {
		if ($(this).children().length != 0) {
			layer.alert('抱歉~已有课程，无法添加', {
				icon : 2
			});
			return;
		}
		var roomid = $(this).attr("data-roomid");
		var timestatus = $(this).attr("data-timeid");
		var date = $('#curriculumDate').val();
		var subjectId = $('#subjectId').val();
		if (!classid) {
			layer.msg("你还没有选择班级哦！");
			return;
		}
		if (!date) {
			layer.msg("你还没有选择时间哦");
			return;
		}
		// 		$('#altField').val("");
		var date1 = date.split(",");
		var str = "";
		for (var i = 0; i < date1.length; i++) {
			var arr = date1[i].split("/");
			arr[0] = arr[0].replace(/(^\s+)|(\s+$)/g, "");
			str = str + arr[2] + "-" + arr[0] + "-" + arr[1] + ",";
		}
		str = str.substring(0, str.length - 1);
		// 		$('#altField').val(str);
		layer.confirm('确认添加课程吗？', {
			btn : [ '确定' ]
		}, function(index, layero) {//确定的处理
			$.ajax({
				url : 'addHolidayLesson.do',
				type : 'post',
				dataType : 'json',
				data : {
					roomId : roomid,
					classId : classid,
					courseDate : date,
					//dateList:$('#altField').val(),
					courseTimes : timestatus
				},
				success : function(res) {
					if (res.code == 0) {
						layer.msg('添加课程成功!');
						window.location.href = "getLessonBysummer.do?curriculumDate="+date;
						window.location.reload();
					} else {
						layer.msg(res.msg,{time:5000});
					}
				}
			})
			layer.close(index);
		});
	});
	$('#setting').click(function() {
		$('#coursedate').text("");
		$('.lesson_td').text("");
		$('.datepicker').css('display', 'block');
		// 		$('.datepicker').multiDatesPicker({
		// 			altField: '#altField'
		// 		});
		event.stopPropagation();
	})
	$('.toright').click(function() {
		var pageNo = $('#pageNo').val();
		var total = $('#totalPage').val();
		pageNo = parseInt(pageNo) + 1;
		if (pageNo > total) {
			layer.msg("对不起，没有更多的排课信息了");
		} else {
			window.location.href = "holiday.do?pageNo=" + pageNo;
		}
	})
	$('.toleft').click(function() {
		var pageNo = $('#pageNo').val();
		var total = $('#totalPage').val();
		pageNo = parseInt(pageNo) - 1;
		if (pageNo <= 0) {
			layer.msg("对不起，已经是第一页了");
		} else {
			window.location.href = "holiday.do?pageNo=" + pageNo;
		}
	})
	$('.icon-remove-circle').click(function(event) {
		var id = $(this).attr("id");
		event.stopPropagation();
		layer.confirm('确认删除该课程吗？', {
			btn : [ '确定' ]
		}, function(index, layero) {//确定的处理
			$.ajax({
				url : 'deleteCourse.do',
				type : 'post',
				dataType : 'json',
				data : {
					courseId : id
				},
				success : function(res) {
					if (res.code == 0) {
						layer.msg("删除课程成功");
// 						window.location.href = "holiday.do?pageNo="+{pageNo};
						window.location.reload();
					} else {
						layer.msg(res.msg);
					}
				}
			})
		});
	})
</script>
</html>