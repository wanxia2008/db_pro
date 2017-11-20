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
<title>作业详情页面</title>
<link rel="stylesheet" href=".././layui/css/layui.css" />
<link rel="stylesheet" href=".././css/bootstrap.min.css" />
<link rel="stylesheet" href=".././css/lecturenote/style.css" />
<link rel="stylesheet" href=".././css/font-awesome.min.css" />
<style type="text/css">
</style>
</head>
<body>
	<div class="exam_content_content" style="display: block">
		<h4>
			2017年
			<%-- <c:if test="${hk.getSeasonType()==1}">春季</c:if>
			<c:if test="${hk.getSeasonType()==2}">秋季</c:if>
			<c:if test="${hk.getSeasonType()==3}">暑假</c:if>
			<c:if test="${hk.getSeasonType()==4}">寒假</c:if> --%>
			<c:if test="${hk.getGrade()==1}">一年级</c:if>
			<c:if test="${hk.getGrade()==2}">二年级</c:if>
			<c:if test="${hk.getGrade()==3}">三年级</c:if>
			<c:if test="${hk.getGrade()==4}">四年级</c:if>
			<c:if test="${hk.getGrade()==5}">五年级</c:if>
			<c:if test="${hk.getGrade()==6}">六年级</c:if>
			<%-- <c:if test="${hk.getPiType()==1}">随堂测试</c:if>
			<c:if test="${hk.getPiType()==2}">期中考试</c:if>
			<c:if test="${hk.getPiType()==3}">期末考试</c:if>
			<c:if test="${hk.getPiType()==4}">入学测试</c:if> --%>
		</h4>
		<button type="button" class="layui-btn" style="float: right;">
			<a href="${ctx}/homework/showHomeworkList.do">返回</a>
		</button>
		<div class="titlecreated" style="display: block; font-size: 15px;">
			<h5>
				一、
				<%-- <c:if test="${hk.getHomeworkType()==1}">单选题</c:if>
				<c:if test="${hk.getHomeworkType()==2}">多选题</c:if>
				<c:if test="${hk.getHomeworkType()==3}">判断题</c:if>
				<c:if test="${hk.getHomeworkType()==4}">阅读理解</c:if>
				(${hk.getTotal()}*${hk.getValue()}) --%>
			</h5>
			<p>
				<c:forEach items="${list}" var="h">
					<c:out value="${h.choiceId}"></c:out>
			  .<c:out value="${h.choiceDesc}"></c:out>
			</p>
			<p style="margin-left: 10px;">
				A、
				<c:out value="${h.optionD}"></c:out>
				&nbsp;&nbsp; B、
				<c:out value="${h.optionB}"></c:out>
				&nbsp;&nbsp; C、
				<c:out value="${h.optionC}"></c:out>
				&nbsp;&nbsp; D、
				<c:out value="${h.optionD}"></c:out>
				<br />
			<p>
				</c:forEach>
			</p>
		</div>
	</div>
</body>
<script src=".././layui/layui.js" charset="utf-8"></script>
<script type="text/javascript">
</script>
</html>