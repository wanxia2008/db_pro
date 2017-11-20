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
<title>上课管理</title>
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
.tcenter{text-align: center !important}
.layui-laypage a, .layui-laypage span{margin-right:5px;padding:0 18px;border-radius:4px;font-size:14px;}
.layui-laypage .layui-laypage-curr .layui-laypage-em{border-radius:4px;}
.layui-laypage>:first-child, .layui-laypage>:first-child em{border-radius:4px;}
.layui-laypage button, .layui-laypage input{border-radius:4px;}
</style>
</head>
<body>
	<form id="myform" class="layui-form"
		action="${ctx}/lesson/lecturenotesmanage.do">
		<div class="panels_head">
			<span>上课管理</span>
		</div>	
		<div class="layui-form-item" style="margin-left:1%;margin-top:20px">
			<div class="layui-inline">
				<label class="layui-form-label" style="font-size: 14px;">日期</label>
				<div class="layui-input-inline" style="width: 130px;">
					<select name="date" id="date" lay-verify="subject"
						lay-filter="select_date">
						<option value="">全部</option>
						<option value="">全部</option>
						<c:forEach items="${dateList}" var="s">
							<option value="${s.courseDate}" <c:if test="${date eq s.courseDate}">selected="selected"</c:if>>${s.courseDate}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="layui-inline">
				<label class="layui-form-label" style="font-size: 14px;">班级</label>
				<div class="layui-input-inline" style="width: 130px;">
					<select name="classId" id="classId" lay-filter="select_class">
					<option value="">全部</option>
					<option value="">全部</option>
						<c:forEach items="${classList}" var="qt">
							<option value="${qt.classId}" 
							<c:if test="${classId eq qt.classId}">selected="selected"</c:if>>${qt.className}
							</option>
						</c:forEach>
					</select>
				</div>
					<button class="layui-btn layui-btn-normal searchbtn" lay-submit="" lay-filter="demo1">搜索</button>
			</div>
		</div>
		<c:choose>
			<c:when test="${courseList==null}">
				<div class="no_data_div"></div>
				<p class="no_data_p" style="font-size: 14px;">~暂无数据~</p>
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
							<th>讲义内容</th>
							<th>课堂讲义</th>
							<th>试卷内容</th>
							<th>课堂测验</th>
							<th>作业内容</th>
							<th>课后作业</th>
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
									<td>
								      <c:if test="${lecture.getLecturenoteId() eq null}">
										未设置
									  </c:if> 
								      <c:if test="${lecture.getLecturenoteId()!=null}">
								      <c:if test="${lecture.getLectureNotes().getWordpdfRoute() !=null}">
                                            <span class="openpdf icon-eye-open"
								           data-pdf="${lecture.getLectureNotes().getWordpdfRoute()}"
									         style="font-size: 14px; color: #008bca;cursor:pointer;">查看</span>&nbsp;
									   </c:if>
									   <c:if test="${lecture.getLectureNotes().getWordpdfRoute() ==null}">
									               ${lecture.getLectureNotes().getHandoutTitle()}
									   </c:if>
									  </c:if>
									</td>
									<td>
									<c:choose>
											<c:when test="${lecture.getLecturenoteId() eq null}">
												<a class="icon-plus-sign-alt" 
													data-id="${lecture.getCourseId()}"
													data-classid="${lecture.getClassId()}"
													data-date="${lecture.courseDate}"
													data-lectId="${lecture.getLectureNotes().getHandoutId()}"
													data-sub="${lecture.getClasslist().get(0).getSubjectlist().get(0).getSubjectId()}"
													data-grade="${lecture.getClasslist().get(0).getGrade()}"
													style="font-size: 14px; color: #008bca; cursor: pointer;">设置</a>
											</c:when>
											<c:otherwise>
												<a class="icon-minus-sign" data-toggle="modal"
													id="${lecture.getCourseId()}"
													data-classid="${lecture.getClassId()}"
													data-date="${lecture.courseDate}"
													data-lectId="${lecture.getLectureNotes().getHandoutId()}"
													style="font-size: 14px; color: #ff5722; cursor: pointer;">取消</a>
											</c:otherwise>
										</c:choose></td>
										<!-- 试卷-->
										<td>
									      <c:if test="${lecture.getExamId() eq null}">
											未设置
										  </c:if> 
									      <c:if test="${lecture.getExamId()!=null}">
                                            <a href="${ctx}/exam/paperTypeDetails.do?piId=<c:out value="${lecture.getExamId()}"></c:out>&pageNo=${page}">
										<span class="icon-eye-open"
										style="font-size: 14px; color: #008bca;"> 查看</span></a>&nbsp;
										  </c:if>
									</td>
									<td>
									<c:choose>
									      <c:when test="${lecture.getExamId() eq null}">
									<p><span class="add_exam"  
									                data-id="${lecture.getCourseId()}"
													data-classid="${lecture.getClassId()}"
													data-date="${lecture.courseDate}"
													data-sub="${lecture.getClasslist().get(0).getSubjectlist().get(0).getSubjectId()}"
													data-grade="${lecture.getClasslist().get(0).getGrade()}"
										style="font-size: 14px; color: #008bca; cursor: pointer;">设置</span></p>
										</c:when>
										<c:otherwise>
										<c:if test="${lecture.paperStatus==0}">
							             <p><span data-toggle="modal" class=""
										    onclick="qidongPaper(${lecture.getCourseId()})"
											style="font-size:14px;color:#008bca;cursor:pointer;">启动</span>
										</p>
										<p><span class="cancelEeam" data-toggle="modal" id="${lecture.getCourseId()}"
											style="font-size: 14px; color: #ff5722; cursor: pointer;">取消</span></p>			
										</c:if>
										<c:if test="${lecture.paperStatus==1}">
										<span data-toggle="modal" class=""
										    onclick="jieshuPaper(${lecture.getCourseId()})"
											style="font-size:14px;color:#008bca;cursor:pointer;">结束</span>
										</c:if>
										<c:if test="${lecture.paperStatus==3}">
									  
										<a href="${ctx}/examRecord/homeworhAnalysis.do?&classId=${lecture.getClassId()}&homeworkId=${lecture.homeworkId}">
										<span class="" style="font-size: 14px; color:#008bca;">分析</span>
								         </a>
								
										</c:if>
										</c:otherwise>
									</c:choose>
									
										</td>
										<!-- 作业-->
										<td>
									      <c:if test="${lecture.getHomeworkId() eq null}">
											未设置
										  </c:if> 
									      <c:if test="${lecture.getHomeworkId()!=null}">
                                           <a href="${ctx}/homework/paperTypeDetails.do?hkId=<c:out value="${lecture.getHomeworkId()}"></c:out>&pageNo=${page}">
										<span class="icon-eye-open"
										style="font-size: 14px; color:#008bca;"> 查看</span></a>
										  </c:if>
									</td>
									<td>
									<c:choose>
								    <c:when test="${lecture.getHomeworkId() eq null}">
									<span  class="add_home" 
									                data-id="${lecture.getCourseId()}"
													data-classid="${lecture.getClassId()}"
													data-date="${lecture.courseDate}"
													data-sub="${lecture.getClasslist().get(0).getSubjectlist().get(0).getSubjectId()}"
													data-grade="${lecture.getClasslist().get(0).getGrade()}"
										style="font-size: 14px; color: #008bca; cursor: pointer;">设置</span>
										</c:when>
										<c:otherwise>
										<c:if test="${lecture.homeworkStatus==0}">
								             <p><span data-toggle="modal" class=""
										    onclick="qidongHomework(${lecture.getCourseId()})"
											style="font-size:14px;color:#008bca;cursor:pointer;">启动</span>
										</p>
										<p><span class="cancelHome" data-toggle="modal" id="${lecture.getCourseId()}"
											style="font-size: 14px; color: #ff5722; cursor: pointer;">取消</span></p>			
											
										</c:if>
										<c:if test="${lecture.homeworkStatus==1}">
										<span data-toggle="modal" class=""
										    onclick="jieshuHomework(${lecture.getCourseId()})"
											style="font-size:14px;color:#008bca;cursor:pointer;">结束</span>
										</c:if>
										<c:if test="${lecture.homeworkStatus==3}">
										<a href="${ctx}/examRecord/homeworhAnalysis.do?&classId=${lecture.getClassId()}&homeworkId=${lecture.homeworkId}">
										<span class="" style="font-size: 14px; color:#008bca;">分析</span>
								         </a>
										</c:if>
										</c:otherwise>
									</c:choose>
							</td>
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
			        <h4 class="modal-title" id="myModalLabel">设置讲义</h4>
                     </div>
					<div class="modal-body">
						<table class="layui-table"
							style="width: 98%; margin-left: auto; margin-right: auto; margin-top: 10px;">							
							<thead>
								<tr>
									<th>讲义编号</th>
									<th>讲义标题</th>
									<th>讲义科目</th>
									<th>知识范围</th>
									<th>使用次数</th>
									<th>创建时间</th>
									<th>操作</th>
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
	<!--模态框-->
	<div class="modal fade" id="myModal23" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog" style="width: 80%;">
			<div class="modal-content">
			<div class="modal-header">
			        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			        <h4 class="modal-title" id="myModalLabel">设置作业</h4>
            </div>
				<div class="modal-body">
						<table class="layui-table" style="width: 98%; margin-left: auto; margin-right: auto; margin-top: 10px;">
						<thead>
							<tr>
								<th>年级</th>
								<th>作业科目</th>
								<th>作业题目</th>
								<th>作业难度</th>
								<th>使用次数</th>
								<th>创建时间</th>
								<th>操作</th>
							</tr>
						</thead>	
						<tbody id="tbody23">
							
						</tbody>
					</table>
					  <div id="page123" style="text-align: center;"></div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal" style="height:30px;line-height:30px;padding:0 18px;">关闭</button>
				</div>
			</div>
		</div>
	</div>
	<!--模态框结束-->
			<!--模态框-->
		<div class="modal fade" id="myModal567" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog" style="width: 85%;">
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
							<tbody id="tbody567">
							
							</tbody>
						</table>
						<div id="pageExam" style="text-align: center;"></div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal" style="height:30px;line-height:30px;padding:0 18px;">关闭</button>
					</div>
				</div>
			</div>
		</div>
		<!--模态框结束-->
	</form>
	<script type="text/html" id="note_template">
		{{each objects as value i}}
		<tr>
			<td>{{value.handoutId}}</td>
			<td>{{value.handoutTitle}}</td>
			<td>{{value.subName.subjectName}}</td>
			<td>{{value.grades.gradeName}}</td>
			<td>{{value.usedCount}}</td>
			<td>{{value.createTime.substring(0,10)}}</td>
			<td>
				<button type="button" onclick="shezhijiangyi({{value.handoutId}})" class="layui-btn"
					data-id="{{value.handoutId}}" data-courseid=""
					data-classid="" data-date=""
					style="height:30px;line-height:30px;padding:0 18px;">
					添加</button>
			</td>
		</tr>
		{{/each}}
	</script>
		<script type="text/html" id="home_template12">
		{{each objects as value i}}
		    <tr>
            <td>{{value.grade2.gradeName}}</td>
			<td>{{value.subject2.subjectName}}</td>
             <td>{{value.hkName}}</td>
			<td
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
            <td>{{value.userTimes}}</td>
			<td>{{value.createTime}}</td>
			<td>
			<button type="button"
			  class="layui-btn add_homeId"
				 data-id="{{value.hkId}}"
				 data-subject="{{value.subject}}" 
                 data-courseid=""
				 data-classid="">添加</button>
			</td>
		</tr>
      {{/each}}
	</script>
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
				<button type="button" class="layui-btn layui-btn-normal add_paperExam"
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
	var pageno = 1, pagesize = 6,grade,subject;
	var status=0;
	layui.use([ 'form', 'layedit','laypage', 'laydate', 'layer' ], function() {
		var form = layui.form();
		var layer = layui.layer;
		laypage = layui.laypage;
		var layedit = layui.layedit;
		var laydate = layui.laydate;
		var layer = layui.layer;
		
		//监听提交
		form.on('submit(demo1)', function(data) {

			return true;
		});

	});
	
	/* 设置讲义 */
	$('.icon-plus-sign-alt').on('click',function() {
		courseid = $(this).attr("data-id");
		classid = $(this).attr('data-classid');
		date = $(this).attr("data-date");
		grade = $(this).attr("data-grade");
		subject = $(this).attr("data-sub");
		$.ajax({
			url:'${ctx}/examRecord/isEnd.do',
		    type:'post',
		    dataType:'json',
		    data:{
		    	classId:classid
		    },
		    success:function(data){
		    	if(data.code==0){
		    		 $('#myModal').modal('show');
		    		 getNote(pageno,grade,subject);
		    	}else{
		    		layer.msg(data.msg,{time:1000});
		    	}
		    }
		})
	   
	});
/* 设置讲义列表 */
	function getNote(pageno,grade,subject) {
		$.getJSON("getpublicnote.do", {
			pageNo : pageno,
			pageSize : pagesize,
			grade :　grade,
			subject :　subject
			
		}, function(res) {
			if (res.code == 0) {
				var html = template('note_template', res);
				$('#tbody1').html(html);
				click_addnote();
				if (res.count > 1) {
					laypage({
						cont : 'page',
						pages : res.count,
						skin : 'molv',
						curr : pageno || 1,//当前页
						groups : 5,
// 						skip : true,
						jump : function(obj, first) {
							//触发分页后的回调
							if (!first) {
								//点击跳页触发函数自身，并传递当前页：obj.curr
								getNote(obj.curr,grade,subject);
							}
						}
					});
				}
			} else{
					layer.msg(res.msg);
					var html = template('note_template', res);
					$('#tbody1').html(html);
			}
		})
	}
	/* 设置作业 */
	$('.add_home').on('click',function() {
		courseid = $(this).attr("data-id");
		classid = $(this).attr('data-classid');
		date = $(this).attr("data-date");
		grade = $(this).attr("data-grade");
		subject = $(this).attr("data-sub");
		type=2;
		$.ajax({
			url:'${ctx}/examRecord/isEnd.do',
		    type:'post',
		    dataType:'json',
		    data:{
		    	classId:classid
		    },
		    success:function(data){
		    	if(data.code==0){
		    		$('#myModal23').modal('show');
		    		getHomeNote(pageno,grade,subject);
		    	}else{
		    		layer.msg(data.msg,{time:1000});
		    	}
		    }
		})
		
	});
	
	/* 课堂测验 */
	$('.add_exam').on('click',function() {
		courseid = $(this).attr("data-id");
		classid = $(this).attr('data-classid');
		date = $(this).attr("data-date");
		grade = $(this).attr("data-grade");
		subject = $(this).attr("data-sub");
		$.ajax({
			url:'${ctx}/examRecord/isEnd.do',
		    type:'post',
		    dataType:'json',
		    data:{
		    	classId:classid
		    },
		    success:function(data){
		    	if(data.code==0){
		    		$('#myModal567').modal('show');
		    		getExam(pageno,grade,subject);
		    	}else{
		    		layer.msg(data.msg,{time:1000});
		    	}
		    }
		})
	});
	
	//设置课堂测验 
	function getExam(pageno,grade,subject) {
		$.getJSON("${ctx}/lesson/getpublicexam.do", {
			pageNo : pageno,
			pageSize : pagesize,
			grade : grade,
			subject : subject,
			isTest : 100
		}, function(res) {
			if (res.code == 0) {
				var html = template('exam_template', res);
				$('#tbody567').html(html);
				click_addexam();
				if (res.count > 2) {
					laypage({
						cont : 'pageExam',
						pages : res.count,
						skin : 'molv',
						curr : pageno || 1,//当前页
						groups : 5,
//						skip : true,
						jump : function(obj, first) {
							//触发分页后的回调
							if (!first) {
								//点击跳页触发函数自身，并传递当前页：obj.curr
								getExam(obj.curr,grade,subject);
							}
						}
					});
				}
			} else {
					layer.msg(res.msg);
			}
		})
	}
	
	/* 设置作业列表 */
	function getHomeNote(pageno,grade,subject) { 
		$.getJSON("${ctx}/homework/paperInfoList.do", {
			pageNo : pageno,
			pageSize:pagesize,
			grade:grade,
			subject:subject
		}, function(res) {
			if (res.code == 0) {
				var html = template('home_template12', res);
				$('#tbody23').html(html);
				click_addnote();
				if (res.count > 1) {
					laypage({
						cont : 'page123',
						pages : res.count,
						skin : 'molv',
						curr : pageno || 1,//当前页
						groups : 5,
// 						skip : true,
						jump : function(obj, first) {
							//触发分页后的回调
							if (!first) {
								//点击跳页触发函数自身，并传递当前页：obj.curr
								getHomeNote(obj.curr,grade,subject);
							}
						}
					});
				}
			} else{
				layer.msg(res.msg);
				var html = template('home_template12', res);
				$('#tbody23').html(html);
			}
		})
	}
	
	
	//取消讲义
	$('.icon-minus-sign').on('click',function(){
		var courseid = $(this).attr("id");
		var classid = $(this).attr('data-classid');
		var date = $(this).attr("data-date");
		var handoutId=$(this).attr("data-lectId");
		layer.confirm('确认取消该讲义吗?', {
			btn : [ '确定' ]
		}, function(index, layero) {//确定的处理
			$.ajax({
				url:'deletenote.do',
				type:'post',
				dataType:'json',
				data:{courseid:courseid,handoutId:handoutId},
				success:function(res) {
					if(res.code==0) {
						layer.msg('取消该讲义成功!');
						window.location.reload();
						//window.location.href="lecturenotesmanage.do";//date="+date+"&classId="+classid
					} else {
						layer.msg(res.msg);
						return;
					}
				}
			})
		});
	})
	
	      //取消课后作业
		$('.cancelHome').on('click',function(){
		var courseid = $(this).attr("id");
		layer.confirm('确认取消该作业吗?', {
			btn : [ '确定' ]
		}, function(index, layero) {//确定的处理
			$.ajax({
				url:'deletehomework.do',
				type:'post',
				dataType:'json',
				data:{courseId:courseid,type:2},
				success:function(res) {
					if(res.code==0) {
						layer.msg('取消该作业成功!');
						 window.location.reload();//跳转当前页
					} else {
						layer.msg(res.msg);
						return;
					}
				}
			})
		});
	})
		//取消考试
		$('.cancelEeam').on('click',function(){
		var courseid = $(this).attr("id");
		layer.confirm('确认取消该考卷吗?', {
			btn : [ '确定' ]
		}, function(index, layero) {//确定的处理
			$.ajax({
				url:'deleteExam.do',
				type:'post',
				dataType:'json',
				data:{courseId:courseid},
				success:function(res) {
					if(res.code==0) { 
						layer.msg('取消该考卷成功!');
						 window.location.reload();//跳转当前页
					} else {
						layer.msg(res.msg);
						return;
					}
				}
			})
		});
	})

	
	//添加试卷
	click_addexam();
	function click_addexam(){
		$('.add_paperExam').on('click',function(){
			var paperId = $(this).attr('data-id');
			layer.confirm('确认添加试卷么?', {
				btn : [ '确定' ]
			}, function(index, layero) {//确定的处理
				$.ajax({
					url:'updateCourse.do',
					type:'post',
					dataType:'json',
					data:{courseId:courseid,paperId:paperId,subject:subject,classId:classid},
					success:function(res) {
						if(res.code ==0) {
							layer.msg('添加试卷成功!');
							window.location.reload();//跳转当前页
						}else{
							layer.msg(res.msg);
							return;
						}
					}
				})
				layer.closeAll();
			});
		})
	}
	//添加作业
	click_addnote();
	function click_addnote() {
		$('.add_homeId').on('click',function(){
			var hkId = $(this).attr('data-id');
			layer.confirm('确认添加作业么?', {
				btn : [ '确定' ]
			}, function(index, layero) {//确定的处理
					$.ajax({
						url:'addHomeworkCourse.do',
						type:'post',
						dataType:'json',
						data:{courseId:courseid,homeworkId:hkId,type:type,classId:classid},
						success:function(res) {
							if(res.code ==0) {
								layer.msg('添加作业成功!');
								setTimeout(function(){
									 window.location.reload();//跳转当前页
// 									window.location.href="lecturenotesmanage.do?date="+date+"&classId="+classid;
								},1000);
							}else{
								layer.msg(res.msg);
								return;
							}
						}
					})
				layer.closeAll();
			})
		});
	}
	
	//查看讲义
	$('.openpdf').on('click',function(){
		 var pdfroute = $(this).attr('data-pdf');
		   if(!pdfroute){
			   layer.msg('暂无可预览讲义');
			   return;
		   }else{
			   var encodeData = window.btoa(window.encodeURIComponent(pdfroute))//编码
			   var url="${ctx}/pdftest/web/viewer2.html?file="+encodeData;	
               window.open(url); 		   
           }
	});
	
	
	function goPageAction(page){
		window.location.href="${ctx}/lesson/lecturenotesmanage.do?pageNo="+page+"&date=${date}&classId=${classId}";
		
		}
	
	function shezhijiangyi(hid){
		layer.confirm('确认添加讲义?', {
			btn : [ '确定' ]
		}, function(index, layero) {//确定的处理
			$.ajax({
				url:'updateLectureNote.do',
				type:'post',
				dataType:'json',
				data:{courseId:courseid,lecturenoteId:hid},
				success:function(res) {
					if(res.code ==0) {
						layer.msg('添加讲义成功!');
						setTimeout(function(){
							window.location.reload();
// 							window.location.href="lecturenotesmanage.do?date="+date+"&classId="+classid;
						},1000);
					}else{
						layer.msg(res.msg);
						return;
					}
				}
			})
			layer.closeAll();
		});
	}
	
	/* 启动试卷 */
	function qidongPaper(courseId){
		layer.confirm('确认启动试卷吗？', {
			btn : [ '确定','取消' ]
		}, function(index, layero) {//确定的处理
			$.ajax({
				url:'${ctx}/lesson/startUpPaper.do',
				type:'post',
				dataType:'json',
				data:{
					courseId:courseId,
					paperStatus:1
				},
				success:function(data){
					if(data.code==0){
						layer.msg('启动试卷成功!');
						setTimeout(function(){
							window.location.reload();
						},1000);
					}else{
						layer.msg(data.msg);
						return;
					}
				},
			});
		},function(){
		       layer.closeAll();  
		});
	}
	
	/* 启动作业*/
	function qidongHomework(courseId){
		layer.confirm('确认启动作业吗？', {
			btn : [ '确定','取消' ]
		}, function(index, layero) {//确定的处理
			$.ajax({
				url:'${ctx}/lesson/startUpHomework.do',
				type:'post',
				dataType:'json',
				data:{
					courseId:courseId,
					homeworkStatus:1
				},
				success:function(data){
					if(data.code==0){
						layer.msg('启动作业成功!');
						setTimeout(function(){
							window.location.reload();
						},1000);
					}else{
						layer.msg(data.msg);
						return;
					}
				},
			});
		},function(){
		       layer.closeAll();  
		});
	}
    //结束试卷
    function jieshuPaper(courseId){
    	layer.confirm('确认结束试卷吗？', {
			btn : [ '确定','取消' ]
		}, function(index, layero) {//确定的处理
			$.ajax({
				url:'${ctx}/lesson/endPaper.do',
				type:'post',
				dataType:'json',
				data:{
					courseId:courseId,
					paperStatus:3
				},
				success:function(data){
					if(data.code==0){
						layer.msg('结束试卷成功!');
						setTimeout(function(){
							window.location.reload();
						},1000);
					}else{
						layer.msg(data.msg);
						return;
					}
				},
			});
		},function(){
		       layer.closeAll();  
		});
    }
    
    //结束作业
    function jieshuHomework(courseId){
    	layer.confirm('确认结束作业吗？', {
			btn : [ '确定','取消' ]
		}, function(index, layero) {//确定的处理
			$.ajax({
				url:'${ctx}/lesson/endHomework.do',
				type:'post',
				dataType:'json',
				data:{
					courseId:courseId,
					homeworkStatus:3
				},
				success:function(data){
					if(data.code==0){
						layer.msg('结束作业成功!');
						setTimeout(function(){
							window.location.reload();
						},1000);
					}else{
						layer.msg(data.msg);
						return;
					}
				},
			});
		},function(){
		       layer.closeAll();  
		});
    }
</script>
</html>