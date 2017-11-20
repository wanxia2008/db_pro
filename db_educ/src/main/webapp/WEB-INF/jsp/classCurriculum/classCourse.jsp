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
<title>班级管理</title>
<link rel="stylesheet" href=".././layui/css/layui.css" />
<link rel="stylesheet" href=".././css/bootstrap.min.css" />
<link rel="stylesheet" href=".././css/lecturenote/style.css" />
<link rel="stylesheet" href=".././css/font-awesome.min.css" />
<style type="text/css">
.a-btn {
	height: 30px;
	line-height: 30px;
	padding: 0 18px;
	position: absolute;
	right: 25px;
	top: 20%;
	font-size: 10px;
	border-radius: 5px;
}
.pagelist a{padding:6px 18px;border:1px solid #e1e2e3;border-radius:4px;}
.pagelist .current{background:#008bca !important;color:#fff}
.pagelist a:hover{background:#008bca !important;color:#fff}
#pages{height:50px;line-height:50px;}
</style>
</head>
<body>
	<form id="myform" class="layui-form" action="findSubjectList.do">
		<div class="panels_head">
			<span>班级排课情况</span>
		</div>
		<div class="layui-form-item" style="margin-top:20px;">
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
					<input hidden="" name="classId"  id="classId" value="${classId}" />
				</div>
				<button class="layui-btn layui-btn-normal" lay-submit="" lay-filter="demo1"
					style="margin-left:10px;font-size:14px;">搜索</button>
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
							<th>ID</th>
							<th>排课类型</th>
							<th>排课年级</th>
							<th>排课班级</th>
							<th>排课日期</th>
							<th>上课时间</th>
							<th>排课科目</th>
							<th>排课教室</th>
							<th>讲义内容</th>
<!-- 							<th>操作</th> -->
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${courseList}" var="classes">
							<tr>
								<td>${classes.courseId}</td>
								<td>
								<c:if test="${classes.courseType ==0}">
								春秋季
								</c:if>
								<c:if test="${classes.courseType ==1}">
								寒暑假
								</c:if>
								</td>
							   <td>${classes.grade.gradeName}</td>
							   <td>${classes.classNo1.className}</td>
								<td>${classes.courseDate}</td>
							    <td>
							    <c:if test="${classes.courseTimes==1}">
							    8:00-9:30</c:if>
							     <c:if test="${classes.courseTimes==2}">
							   9:35-11:05</c:if>
							     <c:if test="${classes.courseTimes==3}">
							    11:10-12:40</c:if>
							     <c:if test="${classes.courseTimes==4}">
							    13:50-15:20</c:if>
							     <c:if test="${classes.courseTimes==5}">
							    15:25-16:55</c:if>
							     <c:if test="${classes.courseTimes==6}">
							   17:00-18:30</c:if>
							    </td>
								<td>${classes.subject.subjectName}</td>
								<td>${classes.classroom.classroomName}</td>
								<td><c:if
										test="${classes.lectureNotes.wordpdfRoute != null}">
										<span class="icon-eye-open"
											data-pdf="${classes.lectureNotes.wordpdfRoute}"
											style="font-size: 14px; color: #008bca; cursor: pointer;">
											预览 </span>
									</c:if> <c:if test="${classes.lectureNotes.wordpdfRoute == null}">
									暂无讲义
									</c:if></td>
<!-- 								<td> -->
<!-- 							<a class="button border-red" -->
<!-- 									href="javascript:void(0)" -->
<%-- 									onclick="deleteone(<c:out value="${classes.courseId}"></c:out>)"> --%>
<!-- 										<span class="icon-remove-sign" -->
<!-- 										style="font-size: 14px; color: #ff5722;"> -->
<!-- 											删除</span> -->
<!-- 								   </a> -->
<!-- 								</td> -->
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
<!-- 									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;去第&nbsp;&nbsp; <input -->
<!-- 										type="text" id="goPageId" -->
<!-- 										style="width: 50px; height: 30px; line-height: 30px; text-align: center" -->
<%-- 										value="${page}" name="goPage" />&nbsp;&nbsp;页 <a --%>
<!-- 										href="javascript:;" -->
<%-- 										onclick="goMyPage($('#goPageId').val(),${totalPages});">&nbsp;&nbsp;Go</a> --%>
								</div>
							</td>
						</tr>
					</tbody>
				</table>
			</c:otherwise>
		</c:choose>
	</form>
</body>
<script type="text/javascript" src=".././js/jquery.min.js"></script>
<script src=".././layui/layui.js" charset="utf-8"></script>
<script type="text/javascript">
	layui
			.use(
					[ 'form', 'layedit', 'laypage', 'laydate' ],
					function() {
						var form = layui.form(), layer = layui.layer, laypage = layui.laypage, layedit = layui.layedit, laydate = layui.laydate;

						//创建一个编辑器
						var editIndex = layedit.build('LAY_demo_editor');
						
						//自定义验证规则
						form.verify({
							
						});

						//监听指定开关
						form.on('switch(switchTest)', function(data) {
							layer.msg('开关checked：'
									+ (this.checked ? 'true' : 'false'), {
								offset : '6px'
							});
							layer.tips('温馨提示：请注意开关状态的文字可以随意定义，而不仅仅是ON|OFF',
									data.othis)
						});

						//监听提交
						form.on('submit(demo1)', function(data) {

							return true;
						});
					});
	
	
	function goPageAction(page){
		window.location.href="${ctx}/classes/findSubjectList.do?pageNo="+page+"&date=${date}&classId=${classId}";
		
		}
	
	/* 预览讲义内容pdf */
	$('.icon-eye-open').on('click',function(){
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
	function deleteone(uid){
		 layer.confirm('您确认删除该节课么?',{btn:['确认','取消']},function(){
			 $.post('${ctx}/classes/deleteCourseId.do?',{'courseId':uid},function(data){
				  if(data.code == 0){
						layer.msg('删除成功');
						window.location.reload();
// 						 window.location.href="${ctx}/classes/findSubjectList.do";
				  }else{
					  layer.msg(data.msg);
			    	return;
				  }
			 });
		    },function(){	
		    	  layer.closeAll();  
		});
	}
	$('#search').click(function(){
		$('#myform').submit();
	})
</script>
</html>