<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>入学考试列表</title>
<link rel="stylesheet" href=".././layui/css/layui.css" />
<link rel="stylesheet" href=".././css/bootstrap.min.css" />
<link rel="stylesheet" href=".././css/lecturenote/style.css" />
<link rel="stylesheet" href=".././css/font-awesome.min.css" />
<script type="text/javascript"
	src="${ctx}/My97DatePicker/WdatePicker.js"></script>

<style type="text/css">
body {
	font-size: 10px;
}

.layui-form-checkbox {
	height: 26px;
	line-height: 24px;
}

.layui-form-checkbox span {
	font-size: 7px;
}

.layui-form-checked span, .layui-form-checked:hover span {
	background-color: #35CE8D;
}

ul li {
	margin-top: 5px;
	padding: 5px;
}
</style>
</head>
<body>
	<form class="layui-form" action="${ctx}/lesson/paperEndList.do">
		<div class="panels_head">
			<span>入学考试查询</span>
		</div>
		<div class="layui-form-item" style="margin-top: 20px">

			<div class="layui-inline">
				<label class="layui-form-label">科目</label>
				<div class="layui-input-inline" style="width: 120px;">
					<select name="subject" id="subject">
						<option value="">全部</option>
						<c:forEach items="${subList}" var="s">
							<option value="<c:out value="${s.subjectId}"></c:out>"><c:out
									value="${s.subjectName}"></c:out></option>
						</c:forEach>
					</select>
				</div>
			</div>

			  <div class="layui-inline">
				<label class="layui-form-label">年级</label>
				<div class="layui-input-inline" style="width: 120px;">
					<select name="gradeId" id="gradeId">
						<option value="">全部</option>
						<c:forEach items="${gradeList}" var="s">
							<option value="<c:out value="${s.gradeId}"></c:out>"><c:out
									value="${s.gradeName}"></c:out></option>
						</c:forEach>
					</select>
					</div>
               </div>
			<div class="layui-inline">
				<label class="layui-form-label">班级</label>
				<div class="layui-input-inline" style="width: 120px;">
					<select name="classId" id="classId">
						<option value="">全部</option>
						<c:forEach items="${classNosList}" var="s">
							<option value="<c:out value="${s.classId}"></c:out>"><c:out
									value="${s.className}"></c:out></option>
						</c:forEach>
					</select>
				</div>
			</div>

			<div class="search_content" style="width: 20%; margin-top: 8px;">

				<button class="layui-btn" lay-submit="" lay-filter="demo1"
					style="margin-left: -350px;">搜索</button>
			</div>
		</div>
		<div class="panels_head" style="margin-top: 0px;">
			<span>入学考试列表</span>
		</div>
		<table class="layui-table"
			style="width: 99%; height: 10px; margin-top: 0px; margin-left: auto; margin-right: auto; font-size: 10px;">
			<colgroup>
				<col width="20" />
				<col width="70" />
				<col width="60" />
				<col width="100" />
				<col width="100" />
				<col width="100" />
				<col width="100" />
				<col width="130" />
				<col>
			</colgroup>
			<thead>
				<tr>
					<th>试卷ID</th>
					<th>试卷名称</th>
					<th>试卷类型</th>
					<th>科目</th>
					<th>年级</th>
					<th>班级</th>
					<th>状态</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${trList}" var="t">
					<tr>
						<!-- <td><input type="checkbox" name="" lay-skin="primary"></td> -->
						<td><c:out value="${t.paperId}"></c:out></td>
						<td><c:out value="${t.paperInfo.piName}"></c:out></td>
						<td><c:out value="${t.taskType.taskTypeName}"></c:out></td>
						<td><c:out value="${t.subject.subjectName}"></c:out></td>
						<td><c:out value="${t.grades.gradeName}"></c:out></td>
						<td><c:out value="${t.classNo.className}"></c:out></td>
						<td>入学考试结束</td>
						
						<td><a href="${ctx}/lesson/studentmanage.do?classId=<c:out value="${t.classId}"></c:out>&grageId=<c:out value="${t.gradeId}"></c:out>">
						<span  class="icon-eye-open"
								style="color: green">查看</span></a></td>
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
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;去第&nbsp;&nbsp;<input
								type="text" id="goPageId"
								style="width: 50px; height: 30px; line-height: 30px; text-align: center"
								value="${page}" name="goPage" />&nbsp;&nbsp;页<a
								href="javascript:;"
								onclick="goMyPage($('#goPageId').val(),${totalPages});">&nbsp;&nbsp;Go</a>
						</div>
					</td>
				</tr>
			</tbody>
		</table>
	</form>
</body>
<script type="text/javascript" src=".././js/jquery-1.8.3.min.js"></script>
<script src=".././layui/layui.js" charset="utf-8"></script>
<script type="text/javascript">
	layui.use([ 'form', 'laypage', 'laydate' ], function() {
		var form = layui.form(), laypage = layui.laypage, layer = layui.layer;
		var $ = layui.jquery;
		//全选
		form.on('checkbox(allChoose)', function(data) {
			var child = $(data.elem).parents('table').find(
					'tbody input[type="checkbox"]');
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
	
	
function goPageAction(page){
	window.location.href="${ctx}/lesson/paperEndList.do?pageNo="+page+"&subject=${subject}&classId=${classId}&gradeId=${gradeId}";
	
	}
	
function goMyPage(page,totalPage){
	if(page>0 && page<=totalPage){
		window.location.href="${ctx}/lesson/paperEndList.do?pageNo="+page+"&subject=${subject}&classId=${classId}&gradeId=${gradeId}";
	}else{
		alert("请输入有效的整数");
	}
}
</script>
</html>