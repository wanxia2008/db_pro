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
<title>学生情况</title>
<link rel="stylesheet" href=".././layui/css/layui.css" />
<link rel="stylesheet" href=".././css/bootstrap.min.css" />
<link rel="stylesheet" href=".././css/lecturenote/style.css" />
<link rel="stylesheet" href=".././css/font-awesome.min.css" />
<style type="text/css">
.a-btn{height:30px;line-height:30px;padding:0 18px;position: absolute;right: 25px;top: 20%;font-size: 10px;background:#e33}

</style>
</head>
<body>
	<form class="layui-form" action="">
	<div class="panels_head">
			<span>学籍信息</span>
	<a class="layui-btn a-btn"
		href="${ctx}/lesson/studentmanage.do?studentId=<c:out value="${stu.studentId}"></c:out>&grade=${grade}&classId=${classId}&subject=${subject}">返回</a>		</div>
	<div class="exam_content_content" style="display: block">
		<p>
			家长:
			<c:choose>
				<c:when test="${stu.custodian1Name == null}">
					<span>暂时没有家长信息</span>
					<br>
				</c:when>
				<c:otherwise>
					<span> <c:out value="${stu.custodian1Name}"></c:out></span>
				</c:otherwise>
			</c:choose>
		<p>
			联系电话:
			<c:choose>
				<c:when test="${stu.custodian1Phone == null}">
					<span>暂无联系电话</span>
				</c:when>
				<c:otherwise>
					<span> <c:out value="${stu.custodian1Phone}"></c:out></span>
				</c:otherwise>
			</c:choose>
		</p>
		<p>
			性别:
			<c:if test="${stu.sex == 1}">男</c:if>
			<c:if test="${stu.sex == 2}">女</c:if>
		</p>
		<p>
			就读学校:
			<c:out value="${stu.attendSchool}"></c:out>
		</p>
		<p>
			目标学校:
			<c:out value="${stu.intentionalSchool}"></c:out>
		</p>
		<p>
			入学考试:
			<c:forEach items="${student}" var="s">
				<c:if test="${s.testRecord.subjectId == 1}">语文</c:if>
				<c:if test="${s.testRecord.subjectId == 2}">数学</c:if>
				<c:if test="${s.testRecord.subjectId == 3}">英语</c:if>
				<c:out value="${s.testRecord.score}"></c:out>
			</c:forEach>
		</p>
	</div>
	</form>
</body>
<script src=".././layui/layui.js" charset="utf-8"></script>
<script type="text/javascript">	
</script>
</html>