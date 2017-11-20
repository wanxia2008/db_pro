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
<title>入学管理</title>
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
</style>
</head>
<body>
	<form class="layui-form" action="${ctx}/entranceexam/execute.do" method="post">
		<div class="panels_head">
			<span>入学考试列表</span>
     <button type="button" class="layui-btn layui-btn-normal"
				lay-submit="" onclick="window.location.href='newExamination.do'"
				lay-filter="demo1"
				style="height: 30px; line-height: 30px; padding: 0 18px; color: #fff; top: 20%; margin-top: 0; font-size: 14px">新增</button>
		</div>
		<div class="layui-form-item" style="margin-top: 20px;">
		<div class="layui-inline">
				<label class="layui-form-label" style="font-size: 14px;">校区</label>
				<div class="layui-input-inline" style="width: 120px;">
					<select name="schoolId" lay-verify="schoolId" id="schoolId" autocomplete="off">
					<option value="">全部</option>
					<option value="">全部</option>
						<c:forEach items="${szList}" var="g">
						<c:choose>
						   <c:when test="${schoolId==g.schoolId}">
						        <option selected="selected" value="${g.schoolId}">${g.schoolName}</option>
						   </c:when>
						   <c:otherwise>
						       <option value="${g.schoolId}">${g.schoolName}</option>
						   </c:otherwise>
						</c:choose>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="layui-inline">
				<label class="layui-form-label" style="font-size:14px;">年级</label>
				<div class="layui-input-inline" style="width: 120px;">
					<select name="gradeId" lay-verify="grade" id="gradeId" autocomplete="off">
					<option value="">全部</option>
					<option value="">全部</option>
                      <c:forEach items="${grades}" var="g">
						<c:choose>
						   <c:when test="${gradeId==g.getGradeId()}">
						        <option selected="selected" value="${g.getGradeId()}">${g.getGradeName()}</option>
						   </c:when>
						   <c:otherwise>
						       <option value="${g.getGradeId()}">${g.getGradeName()}</option>
						   </c:otherwise>
						</c:choose>
						</c:forEach>
					</select>
				</div>
			</div>
			
			<div class="layui-inline">
				<label class="layui-form-label" style="font-size: 14px;">科目</label>
				<div class="layui-input-inline" style="width: 120px;">
					<select name="subjectId" lay-verify="subject" id="subjectId" autocomplete="off">
					<option value="">全部</option>
					<option value="">全部</option>
						<c:forEach items="${subjects}" var="g">
						<c:choose>
						   <c:when test="${subjectId==g.subjectId}">
						        <option selected="selected" value="${g.subjectId}">${g.subjectName}</option>
						   </c:when>
						   <c:otherwise>
						       <option value="${g.subjectId}">${g.subjectName}</option>
						   </c:otherwise>
						</c:choose>
						</c:forEach>
					</select>
				</div>
			<button class="layui-btn layui-btn-normal" lay-submit="" lay-filter="demo1">搜索</button>
			</div>
			
		</div>
		<c:choose>
			<c:when test="${records==null}">
				<div class="no_data_div"></div>
				<p class="no_data_p" style="font-size:16px;">~暂无数据~</p>
			</c:when>
			<c:otherwise>
				<table class="layui-table"
					style="width: 98%; margin-left: auto; margin-right: auto; font-size: 10px;">
					
					<thead>
						<tr>
						    <th>年份</th>
						    <th>季节</th>
							<th>校区</th>
							<th>科目</th>
							<th>年级</th>
							<th>试卷</th>
							<th>考试时间</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${records}" var="students">
								<tr>
									<td>${students.schoolYear.year}</td>
									<td>${students.seasonType.seasonName}</td>
									<td>${students.schoolZone.schoolName}</td>
									<td>${students.subject2.subjectName}</td>
									<td>${students.grade.getGradeName()}</td>
									<td>${students.paperInfo.piName}</td>
									<td><fmt:formatDate value="${students.createTime}"
										pattern="yyyy-MM-dd HH:mm"></fmt:formatDate></td>
									<td>
									<c:if test="${students.status==0}">
									<a class="icon-plus-sign-alt" onclick="kaiqi(${students.examId})"
									style="font-size: 14px; color:#008bca; cursor: pointer;">启动</a>&nbsp;
									</c:if>
									<c:if test="${students.status==1}">
									<a class="icon-plus-sign-alt" onclick="jieshu(${students.examId})"
									style="font-size: 14px; color:#008bca; cursor: pointer;">结束</a>&nbsp;
									</c:if>
									<c:if test="${students.status==3}">
									<span style="font-size: 14px; color:#ff5722;">已结束</span>&nbsp;
									</c:if>
<%-- 									<c:if test="${students.status==0}"> --%>
<%-- 									<a href="${ctx}/entranceexam/modityExam.do?examId=<c:out value="${students.examId}"></c:out>">  --%>
<!-- 							      <span class="icon-edit" -->
<!--  										style="font-size: 14px; color: #008bca;">修改</span>  -->
<!-- 								    </a> &nbsp;  -->
<%-- 									</c:if> --%>
									<a class="button border-red"
									href="javascript:void(0)"
									onclick="deleteone(<c:out value="${students.examId}"></c:out>)">
										<span class="icon-remove-sign"
										style="font-size: 14px; color: #ff5722;">
											删除</span>
								</a>
									</td>
								</tr>
						</c:forEach>
						<tr id="pages">
							<td colspan="10">
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
											</a>&nbsp;
										</c:otherwise>
									</c:choose>		
									<a href="javascript:;" onclick="goPageAction(${totalPages});">末页</a>
									&nbsp;总共&nbsp; ${totalPages} &nbsp;页
									<%-- &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;去第&nbsp;&nbsp; <input
										type="text" id="goPageId"
										style="width: 50px; height: 30px; line-height: 30px; text-align: center"
										value="${page}" name="goPage" />&nbsp;&nbsp;页 <a
										href="javascript:;"
										onclick="goMyPage($('#goPageId').val(),${totalPages});">&nbsp;&nbsp;Go</a> --%>
								</div>
							</td>
						</tr>
					</tbody>
				</table>
<!-- 				<div id="pages" style="text-align: center; margin-top: 30px;"></div> -->
			</c:otherwise>
		</c:choose>
	</form>
</body>
<script src=".././layui/layui.js" charset="utf-8"></script>
<script type="text/javascript" src=".././js/artTemplate-simple-3.0.js"></script>
<script type="text/javascript">
	
	layui.use([ 'form', 'layedit', 'layer', 'laypage', 'laydate' ],
					function() {
						var form = layui.form(), layer = layui.layer, laypage = layui.laypage, laydate = layui.laydate;

						//自定义验证规则
						form.verify({
							subject : function(value) {
								if ($(this).val() == "") {
									return "请选择科目";
								}
							},
							grade : function(value) {
								if ($(this).val() == "") {
									return "请选择年级";
								}
							}
						});
						//全选
						form.on('checkbox(allChoose)', function(data) {
							var child = $(data.elem).parents('table').find(
									'tbody input[name="checkbox"]');
							child.each(function(index, item) {
								item.checked = data.elem.checked;
							});
							form.render('checkbox');
						});
						//监听提交
						form.on('submit(demo1)', function(data) {

							return true;
						});
					});

	function goPageAction(page) {
		window.location.href = "${ctx}/entranceexam/execute.do?pageNo="+ page+"&gradeId=${gradeId}&subjectId=${subjectId}&schoolId=${schoolId}";
		
	}
// 	function goMyPage(page, totalPage) {
// 		if (page > 0 && page <= totalPage) {
// 			window.location.href = "${ctx}/entranceexam/execute.do?pageNo="+page+"&gradeId=${gradeId}&subjectId=${subjectId}&schoolId=${schoolId}";
// 		} else {
// 			alert("请输入有效的整数");
// 		}
// 	}
	function kaiqi(uid){
		layer.confirm('您确认开启考试么?',{btn:['确认','取消']},function(){
			 $.post('${ctx}/entranceexam/startExam.do?',{'id':uid},function(data){
				  if(data.code == 0){
						layer.msg('开启考试成功');
						setTimeout(function(){
// 							 window.location.href="${ctx}/entranceexam/execute.do";
							 window.location.reload();
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
	function jieshu(uid){
		layer.confirm('您确认结束考试么?',{btn:['确认','取消']},function(){
			 $.post('${ctx}/entranceexam/endExam.do?',{'examId':uid},function(data){
				  if(data.code == 0){
						layer.msg('结束考试成功');
						setTimeout(function(){
// 						 window.location.href="${ctx}/entranceexam/execute.do";
						 window.location.reload();
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
	function deleteone(uid){
		 layer.confirm('您确认删除入学考试么?',{btn:['确认','取消']},function(){
			 $.post('${ctx}/entranceexam/deleteExam.do?',{'examId':uid},function(data){
				  if(data.code == 0){
						layer.msg('删除入学考试成功');
						setTimeout(function(){
// 							 window.location.href="${ctx}/entranceexam/execute.do";
							 window.location.reload();
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
</script>
</html>